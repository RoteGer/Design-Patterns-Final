package il.ac.hit.quizzy;

// Holds data (record class)
public class QuizAnswer implements IQuizAnswer {
    private String question;
    private boolean correct;
    private String answer;

    public QuizAnswer(String question, boolean isCorrect) {
        setQuestion(question);
        setCorrect(isCorrect);
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }


    public void setQuestion(String question) {
        this.question = question;
    }


    public String getQuestion() {
        return question;
    }
}
