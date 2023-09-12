package il.ac.hit.quizzy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class SimpleCSVQuizFilesDAO implements IQuizFilesDAO {
    private static SimpleCSVQuizFilesDAO instance;

    private SimpleCSVQuizFilesDAO() {
        // Constructor implementation
    }

    public static SimpleCSVQuizFilesDAO getInstance() {
        if (instance == null) {
            instance = new SimpleCSVQuizFilesDAO();
        }
        return instance;
    }

    @Override
    public void saveQuizToFile(IQuiz quiz, String fileName) throws QuizException {
        try {
            FileWriter fileWriter = new FileWriter(fileName + ".csv");  // Save the quiz's type
            List<IQuizQuestion> questions = quiz.getQuestions();
            fileWriter.write(quiz.getClass().toString() + "\n");
            fileWriter.write(quiz.getName() + "\n");  // Save the quiz's name

            for (IQuizQuestion question : questions) {  // Save the questions
                fileWriter.write(question.toString());
            }
            fileWriter.close();
        } catch (IOException e) {

            throw new QuizException("File not saved");
        }
    }

    @Override
    public IQuiz loadQuizFromFile(String fileName) throws QuizException {
        String data; // Holds csv line
        String answer;
        IQuizQuestion question;
        List<IQuizQuestionBuilder> builder = new ArrayList<>(); // A list of builders. each builder will hold a question
        int i = 0;
        builder.add(new QuizQuestion.Builder()); // First builder
        int count = 0;
        File file = new File(fileName + ".csv");
        try {
            Scanner scanner = new Scanner(file);
            String lowerCaseText = scanner.nextLine().toLowerCase();
            boolean containsTerminal = lowerCaseText.contains("terminal");
            boolean containsGui = lowerCaseText.contains("gui");
            String quizTypeStr;

            // Getting the QuizType from the first line in the csv
            if (containsTerminal) {
                quizTypeStr = "TERMINAL";
            } else if (containsGui) {
                quizTypeStr = "GUI";
            } else {
                throw new QuizException("Error parsing file");
            }

            // Convert the quiz type string to the QuizType enum
            QuizType quizType = QuizType.valueOf(quizTypeStr);

            // Create and return an instance of IQuiz
            QuizFactory factory = new QuizFactory();
            IQuiz quiz = factory.createQuiz(quizType);

            quiz.setName(scanner.nextLine()); // Setting the quiz name from the csv

            while (scanner.hasNextLine()) {
                data = scanner.nextLine();

                // Configure the builder of quizQuestion,
                // it is assumed each question has: title, question, and 5 answers options in this order
                if (count == 0) {  // Question's title
                    builder.get(i).setTitle(data);
                } else if (count == 1) {  // Question
                    builder.get(i).setQuestion(data);
                } else {  // Answers Options. count = 2-7
                    if (!data.isEmpty()) {
                        answer = "false";  // Default
                        String trimmeData = data;
                        if (data.indexOf(',') != -1) {  // Removing true and false
                            trimmeData = data.substring(0, data.indexOf(',')).trim();
                            answer = data.substring(data.indexOf(',') + 1).trim();
                        }
                        builder.get(i).addAnswer(trimmeData, answer.equals("true")); // if true gives true else gives false
                    }
                }


                count++;

                // finished parsing 1 question
                if (count == 7) {
                    // Create the builder after the configuration
                    question = builder.get(i).create();
                    // Adding the question to the quiz
                    quiz.addQuestion(question);
                    count = 0;
                    i++;
                    // Add new builder to the list
                    builder.add(new QuizQuestion.Builder());

                }
            }
            scanner.close();

            return quiz;
        } catch (FileNotFoundException e) {
            throw new QuizException("The file not found");
        }
    }
}