package il.ac.hit.quizzy;

import java.util.ArrayList;
import java.util.List;

public class TerminalQuiz implements IQuiz {
    // Implementation for terminal-based quiz
    private String name;
    private int correctAnswers;
    private final String type = "TERMINAL";
    private List<IQuizQuestion> questions = new ArrayList<>();

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
    public List<IQuizQuestion> getQuestions() {
        return questions;
    }

    @Override
    public void addQuestion(IQuizQuestion question) {
        questions.add(question);
    }


    @Override
    public void endQuiz() {
        int totalQuestions = questions.size();

        double percentageCorrect = (double) correctAnswers / totalQuestions * 100;
        System.out.println("Quiz ended. Here are your results: ");
        System.out.println("You answered " + correctAnswers + " out of " + totalQuestions + " questions correctly.");
        System.out.println("Your score: " + percentageCorrect + "%");

    }

    @Override
    public IQuiz clone() {
        try {
            IQuiz clone = (IQuiz) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
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

