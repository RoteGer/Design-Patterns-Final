package il.ac.hit.quizzy;

public interface IQuiz {
    public abstract void start();
    public abstract void setName(String text);
    public abstract String getName();
    public abstract void addQuestion(QuizQuestion question);
}

