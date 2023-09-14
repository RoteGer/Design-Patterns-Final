package il.ac.hit.quizzy;

public class QuizFactory {

    // Variable declarations
    private IQuiz guiQuizPrototype;
    private IQuiz terminalQuizPrototype;

    // Constructor
    public QuizFactory() {
        // Initialize prototypes
        guiQuizPrototype = new GUIQuiz();
        terminalQuizPrototype = new TerminalQuiz();
    }

    // Method
    public IQuiz createQuiz(QuizType type) {
        if (type != null) {
            if (type == QuizType.TERMINAL) {
                return cloneQuiz(terminalQuizPrototype);
            } else if (type == QuizType.GUI) {
                return cloneQuiz(guiQuizPrototype);
            }
        }
        return null; // Handle unsupported quiz type
    }

    private IQuiz cloneQuiz(IQuiz quizPrototype) {
        // Create a new instance
        try {
            return quizPrototype.clone();
        } catch (CloneNotSupportedException e) {

            e.printStackTrace();
            return null;
        }
    }
}


