package il.ac.hit.quizzy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class QuizQuestion implements IQuizQuestion {
    private String title;
    private String question;
    private List<IQuizQuestion> questions = new ArrayList<>();
    private List<QuizAnswer> answerList = new LinkedList<>();
    private boolean correct;

    @Override
    public void setTitle(String text) {
        this.title = text;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public List<IQuizQuestion> getQuestions() {
        return questions;
    }

    @Override
    public void setQuestion(String text) {
        this.question = text;
    }

    @Override
    public void setAnswerList(List<QuizAnswer> answerList) {
        this.answerList = answerList;
    }

    @Override
    public String toString() {
        StringBuilder text = new StringBuilder(title + "\n");
        text.append(question).append("\n");
        for (QuizAnswer answer : answerList) {
            text.append(answer.getQuestion()).append(", ").append(answer.isCorrect());
            text.append("\n");
        }
        return text.toString();
    }

    public List<QuizAnswer> getOptions() {
        return answerList;
    }

    // Question Builder
    public static class Builder implements IQuizQuestionBuilder {
        String questionTitle;
        String builderQuestion;
        List<QuizAnswer> answers = new LinkedList<>();

        @Override
        public IQuizQuestionBuilder setTitle(String text) {
            questionTitle = text;
            return this;
        }

        @Override
        public IQuizQuestionBuilder setQuestion(String text) {
            builderQuestion = text;
            return this;
        }

        @Override
        public IQuizQuestionBuilder addAnswer(String text, boolean isCorrect) {
            answers.add(new QuizAnswer(text, isCorrect));
            return this;
        }

        @Override
        public IQuizQuestion create() {
            IQuizQuestion question = new QuizQuestion();
            question.setTitle(questionTitle);
            question.setQuestion(builderQuestion);
            question.setAnswerList(answers);

            return question;
        }
    }
}
