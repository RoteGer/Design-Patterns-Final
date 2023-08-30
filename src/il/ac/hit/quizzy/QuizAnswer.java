package il.ac.hit.quizzy;

// Holds data (record class)
public class QuizAnswer {
    private String question;
    private boolean correct;

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

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }



}
