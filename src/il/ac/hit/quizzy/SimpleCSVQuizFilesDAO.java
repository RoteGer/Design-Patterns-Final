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
        try {
            FileWriter writer = new FileWriter(fileName + ".csv");  // Save the quiz's type
            List<IQuizQuestion> questions = quiz.getQuestions();
            writer.write(quiz.getClass().toString() + "\n");
            writer.write(quiz.getName() + "\n");  // Save the quiz's name

            for (IQuizQuestion question : questions) {  // Save the questions
                writer.write(question.toString());
            }
            writer.close();
        } catch (IOException e) {

            throw new QuizException("File not saved");
        }
    }

    @Override
    public IQuiz loadQuizFromFile(String fileName) throws QuizException {
        return null;
    }

}
