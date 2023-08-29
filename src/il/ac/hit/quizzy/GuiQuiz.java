package il.ac.hit.quizzy;
import javax.swing.*;
import java.awt.*;

public class GuiQuiz implements IQuiz {
    // Implementation for GUI-based quiz
    private String name;
    private JFrame frame; // The main GUI frame
    private JLabel gradeLabel; // A label to display the final grade

    @Override
    public void start() {
        // Create the main frame
        frame = new JFrame("Quiz App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600); // Set the dimensions of the frame
        // Initialize and add GUI components (questions, buttons, etc.)
        // You would need to create GUI components and add them to the frame here

        // Display the frame
        frame.setVisible(true);
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

    }

    @Override
    public void endQuiz() {
        // Calculate the final grade
        int finalGrade = calculateFinalGrade();

        // Create a label to display the grade
        gradeLabel = new JLabel("Final Grade: " + finalGrade);
        gradeLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Set font and size

        // Add the label to the frame
        frame.getContentPane().removeAll(); // Remove previous components
        frame.getContentPane().add(gradeLabel, BorderLayout.CENTER);

        // Update the frame
        frame.revalidate();
        frame.repaint();
    }

    private int calculateFinalGrade() {
        return 1;
    }

    @Override
    public IQuiz clone() {
        GuiQuiz clonedQuiz = new GuiQuiz();
        // Copy properties from the current instance to the cloned instance
        clonedQuiz.name = this.name;
        // You might need to copy other GUI-related properties here

        return clonedQuiz;
    }
}