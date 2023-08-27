package il.ac.hit.quizzy;

public interface IQuiz extends Cloneable{
    public abstract void start();
    public abstract void setName(String text);
    public abstract String getName();
    public abstract void addQuestion(QuizQuestion question);
    public abstract void endQuiz();
    IQuiz clone(); // Prototype method

}



