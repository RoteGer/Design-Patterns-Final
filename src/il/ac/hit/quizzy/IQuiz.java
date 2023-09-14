package il.ac.hit.quizzy;

import java.util.List;

public interface IQuiz extends Cloneable {
    public abstract void start();

    public abstract void setName(String text);

    public abstract String getName();

    public List<IQuizQuestion> getQuestions();

    public abstract void addQuestion(IQuizQuestion question);

    public abstract void endQuiz();

    IQuiz clone() throws CloneNotSupportedException;  // Prototype method
}