package il.ac.hit.quizzy;

import java.util.List;

public interface IQuizQuestion {
    public abstract String getTitle();
    public abstract String getQuestion();
    public abstract List<String> getAnswers();
    public abstract boolean getCorrectAnswers();
}