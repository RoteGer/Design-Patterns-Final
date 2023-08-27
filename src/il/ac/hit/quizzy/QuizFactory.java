package il.ac.hit.quizzy;

public class QuizFactory {
    public IQuiz createQuiz(QuizType type) {
        if (type != null) {
            if (type == QuizType.TERMINAL) {
                return new TerminalQuiz();
            } else if (type == QuizType.GUI) {
                return new GuiQuiz();
            }
        }
        return null; // Handle unsupported quiz type
    }
}


