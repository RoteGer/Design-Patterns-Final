package il.ac.hit.quizzy;

import java.util.ArrayList;
import java.util.List;

public interface IQuiz {
    public abstract void start();
    public abstract void setName(String text);
    public abstract String getName();
    public List<IQuizQuestion> getQuestions();
    public abstract void addQuestion(IQuizQuestion question);
    public abstract void endQuiz();
    IQuiz clone(); // Prototype method
}



