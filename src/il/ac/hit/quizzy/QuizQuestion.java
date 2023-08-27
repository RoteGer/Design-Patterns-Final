package il.ac.hit.quizzy;

import java.util.ArrayList;
import java.util.List;

public class QuizQuestion implements IQuizQuestion {
    // Fields for holding question data
    private String title;
    private String question;
    private List<String> answers;
    private List<Boolean> correctAnswers;

    // Constructor (private to enforce Builder pattern)
    private QuizQuestion() {
        answers = new ArrayList<>();
        correctAnswers = new ArrayList<>();
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public String getQuestion() {
        return null;
    }

    @Override
    public List<String> getAnswers() {
        return null;
    }

    @Override
    public boolean getCorrectAnswers() {
        return false;
    }

    public static class Builder implements IQuizQuestionBuilder{

        private QuizQuestion question;

        public Builder() {
            question = new QuizQuestion();
        }
        @Override
        public IQuizQuestionBuilder setTitle(String text) {
            question.title = text;
            return this;
        }

        @Override
        public IQuizQuestionBuilder setQuestion(String text) {
            question.question = text;
            return this;
        }

        @Override
        public IQuizQuestionBuilder addAnswer(String text, boolean correct) {
            question.answers.add(text);
            question.correctAnswers.add(correct);
            return this;
        }

        @Override
        public IQuizQuestion create() {
            return question;
        }
    }

}
