package il.ac.hit.quizzy;

// Holds data (record class)
public class QuizAnswer implements IQuizAnswer {

    // Variable declarations
    private String question;  // The text of the answer option
    private boolean correct;  // Indicates whether the answer is correct or not

    // Constructor to create a QuizAnswer object
    public QuizAnswer(String question, boolean isCorrect) {
        setQuestion(question);  // Initialize the answer text using the setter
        setCorrect(isCorrect);  // Initialize the correctness flag using the setter
    }

    // Setter method to set the correctness of the answer
    public void setCorrect(boolean correct) {
        // Add validation to ensure that 'correct' is set to either true or false
        if (correct || correct == false) {
            this.correct = correct;
        } else {
            throw new IllegalArgumentException("'correct' must be set to true or false");
        }
    }

    // Setter method to set the text of the answer option
    public void setQuestion(String question) {
        if (question != null && !question.trim().isEmpty()) {
            this.question = question;  // Set the answer text
        } else {
            // If the provided question is empty or null, throw an exception
            throw new IllegalArgumentException("Question cannot be empty or null");
        }
    }

    // Getter method to check if the answer is correct
    public boolean isCorrect() {
        return correct;
    }


    // Getter method to retrieve the text of the answer option
    public String getQuestion() {
        return question;
    }
}
