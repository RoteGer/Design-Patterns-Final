package il.ac.hit.quizzy;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

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
        if (quiz == null) {
            throw new QuizException("Quiz object is null");
        }
                try (FileWriter fileWriter = new FileWriter(fileName)) {

                    // Write the quiz data to the CSV file
                    for (IQuizQuestion question : quiz.getQuestions()) {
                        StringBuilder line = new StringBuilder();

                        line.append(escapeCsvValue(question.toString()));

//                        for (QuizAnswer option : question.getOptions()) {
//                            System.out.println(option);
//                            line.append(escapeCsvValue(option.toString())).append(",");
//                        }

                       // line.append(question.getOptions());
                        fileWriter.write(line.toString());
                        fileWriter.write(System.lineSeparator());
                    }
                } catch (IOException e) {
                    throw new QuizException("Error writing to file: " + e.getMessage());
                }
            }

    private static String escapeCsvValue(String value) {
        if (value.contains(",") || value.contains("\"")) {
            value = "\"" + value.replace("\"", "\"\"") + "\"";
        }
        return value;
    }


    @Override
    public IQuiz loadQuizFromFile(String fileName) throws QuizException {
        return null;
    }

}
