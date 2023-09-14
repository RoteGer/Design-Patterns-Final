package il.ac.hit.quizzy;

import java.util.LinkedList;
import java.util.List;

public class QuizQuestion implements IQuizQuestion {
    private String title;
    private String question;
    private List<QuizAnswer> answerList = new LinkedList<>();

    public void setAnswerList(List<QuizAnswer> answerList) {
        this.answerList = answerList;
    }

    @Override
    public void setTitle(String text) {
        this.title = text;
    }

    @Override
    public void setQuestion(String text) {
        this.question = text;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String[] getOptions() {
        int i = 0;
        String[] answerStrArr = new String[answerList.size()]; // Initialize the array with the appropriate size
        for (QuizAnswer answer : answerList) {
            answerStrArr[i] = (answer.getQuestion());
            i++;
        }

        return answerStrArr;
    }

    @Override
    public boolean isUserCorrect(int markedOption) {
        return answerList.get(markedOption).isCorrect();
    }

    // Converts the QuizQuestion object to a string for saving to a CSV file
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

    @Override
    public String getQuestion() {
        return question;
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

        // Creates a QuizQuestion object based on the builder's configuration
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