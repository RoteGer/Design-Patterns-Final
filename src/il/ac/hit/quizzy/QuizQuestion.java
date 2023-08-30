package il.ac.hit.quizzy;
import java.util.LinkedList;
import java.util.List;

public class QuizQuestion implements IQuizQuestion {
    private String title;
    private String question;
    private List<QuizAnswer> answerList = new LinkedList<>();


    @Override
    public void setTitle(String text) {
        this.title = text;
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
        String text = title + "\n";
        text += question + "\n";
        for (QuizAnswer answer : answerList) {
            text += answer.getQuestion() + ", " + answer.isCorrect();
            text += "\n";
        }
        return text;
    }

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
