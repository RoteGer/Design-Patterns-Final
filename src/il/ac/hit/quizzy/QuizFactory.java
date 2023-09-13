package il.ac.hit.quizzy;

public class QuizFactory {
    private IQuiz guiQuizPrototype;
    private IQuiz terminalQuizPrototype;

    public QuizFactory() {
        // Initialize prototypes
        guiQuizPrototype = new GuiQuiz();
        terminalQuizPrototype = new TerminalQuiz();
    }

    private IQuiz cloneQuiz(IQuiz quizPrototype) {
        // Create a new instance
        try {
            return (IQuiz) quizPrototype.clone();
        } catch (CloneNotSupportedException e) {

            e.printStackTrace();
            return null;
        }
    }

    public IQuiz createQuiz(QuizType type) {
        if (type != null) {
            if (type == QuizType.TERMINAL) {
                return cloneQuiz(terminalQuizPrototype);
            } else if (type == QuizType.GUI) {
                //return cloneQuiz(GuiQuiz());
            }
        }
        return null; // Handle unsupported quiz type
    }


}


