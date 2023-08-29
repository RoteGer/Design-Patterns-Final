package il.ac.hit.quizzy;

import java.util.ArrayList;
import java.util.List;

public class TerminalQuiz implements IQuiz{
    // Implementation for terminal-based quiz
    private String name;
    private List<QuizQuestion> questions = new ArrayList<>();
    @Override
    public void start() {
        System.out.println("Welcome to the Terminal Quiz: " + name);

    }

    @Override
    public void setName(String text) {
        this.name = text;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void addQuestion(QuizQuestion question) {
        questions.add(question);
    }

    @Override
    public void endQuiz() {
        int totalQuestions = questions.size();
        int correctAnswers = 0;

        double percentageCorrect = (double) correctAnswers / totalQuestions * 100;
        System.out.println("Quiz ended. Here are your results:");
        System.out.println("You answered " + correctAnswers + " out of " + totalQuestions + " questions correctly.");
        System.out.println("Your score: " + percentageCorrect + "%");

    }

    @Override
    public IQuiz clone() {
        // Implement the clone logic for TerminalQuiz
        // Return a new instance with copied properties
        TerminalQuiz clonedQuiz = new TerminalQuiz();
        clonedQuiz.name = this.name;
        for (IQuizQuestion question : this.questions) {
            clonedQuiz.questions.add((QuizQuestion) question);
        }
        return clonedQuiz;
    }
}
