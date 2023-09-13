package il.ac.hit.quizzy;

import java.util.List;

public interface IQuizQuestion {

    public abstract void setAnswerList(List<QuizAnswer> answers);

    public abstract void setTitle(String text);

    public abstract void setQuestion(String text);

    public abstract String[] getOptions();

    public abstract boolean isUserCorrect(int markedOption);

    public abstract String getTitle();

    public abstract String getQuestion();

}