package il.ac.hit.quizzy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GuiQuiz implements IQuiz {

    // Class-level variables
    private String name;
    private List<IQuizQuestion> questions = new ArrayList<>();
    private int score = 0;
    private int currentQuestionIndex = 0;

    // GUI components
    private JFrame frame;
    private JLabel titleLabel;
    private JLabel questionLabel;
    private JButton[] optionButtons;
    private JLabel scoreLabel;

    // Constructor
    public GuiQuiz() {
        //initializeUI(); // Commented out for manual UI initialization
    }

    // Initialize the graphical user interface
    private void initializeUI() {
        frame = new JFrame("GUI Quiz: " + name);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        // Title Panel
        JPanel titlePanel = new JPanel(new BorderLayout());

        titleLabel = new JLabel("Welcome to the GUI Quiz: " + name);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titlePanel.add(titleLabel, BorderLayout.NORTH);

        // Question Panel
        questionLabel = new JLabel();
        questionLabel.setHorizontalAlignment(JLabel.CENTER);
        questionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        titlePanel.add(questionLabel, BorderLayout.CENTER);

        frame.add(titlePanel, BorderLayout.NORTH);

        // Options Panel
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(5, 1, 10, 10)); // Add some spacing
        optionButtons = new JButton[5]; // Assuming there are 5 options for each question

        for (int i = 0; i < optionButtons.length; i++) {
            optionButtons[i] = new JButton();
            optionButtons[i].setFont(new Font("Arial", Font.PLAIN, 14)); // Set font
            optionButtons[i].setFocusPainted(false); // Remove button border
            optionButtons[i].addActionListener(new OptionButtonListener());
            optionsPanel.add(optionButtons[i]);
        }

        frame.add(optionsPanel, BorderLayout.CENTER);

        // Score Panel
        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setHorizontalAlignment(JLabel.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        frame.add(scoreLabel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    // Start the quiz
    @Override
    public void start() {
        initializeUI();

        currentQuestionIndex = 0;
        score = 0;
        updateScoreLabel();

        if (questions.size() > 0) {
            displayQuestion(currentQuestionIndex);
        } else {
            JOptionPane.showMessageDialog(null, "No questions available.");
            endQuiz();
        }
    }

    // Set the name for the quiz
    @Override
    public void setName(String text) {
        if (text.isEmpty()) {
            text = "default name";
        }
        this.name = text;
    }

    // Get the name of the quiz
    @Override
    public String getName() {
        return name;
    }

    // Get the list of quiz questions
    @Override
    public List<IQuizQuestion> getQuestions() {
        return questions;
    }

    // Add a quiz question to the list
    public void addQuestion(IQuizQuestion question) {
        questions.add(question);
    }

    // Update the score label displayed in the GUI
    private void updateScoreLabel() {
        scoreLabel.setText("Score: " + score);
    }

    // Display a quiz question and its options in the GUI
    private void displayQuestion(int index) {
        if (index >= 0 && index < questions.size()) {
            IQuizQuestion question = questions.get(index);

            questionLabel.setText("*** " + question.getTitle() + " *** " + question.getQuestion());

            for (int i = 0; i < optionButtons.length; i++) {
                optionButtons[i].setText(question.getOptions()[i]);
            }
        }
    }

    // ActionListener for option buttons
    private class OptionButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            int selectedOption = -1;

            for (int i = 0; i < optionButtons.length; i++) {
                if (optionButtons[i] == button) {
                    selectedOption = i;
                    break;
                }
            }

            if (selectedOption != -1) {
                checkAnswer(selectedOption);
            }
        }
    }

    // Check if the selected option is correct and update the score
    private void checkAnswer(int selectedOption) {
        IQuizQuestion question = questions.get(currentQuestionIndex);

        if (question.isUserCorrect(selectedOption)) {
            score++;
        }

        updateScoreLabel();
        currentQuestionIndex++;

        // Check if there are more question
        if (currentQuestionIndex < questions.size()) {
            displayQuestion(currentQuestionIndex);
        } else {
            endQuiz();
        }
    }

    // End the quiz and display the results
    @Override
    public void endQuiz() {
        double percentageCorrect = (double) score / questions.size() * 100;
        JOptionPane.showMessageDialog(null, "Quiz ended. Here are your results:\n" +
                "You answered " + score + " out of " + questions.size() + " questions correctly.\n" +
                "Your score: " + percentageCorrect + "%");

        frame.dispose();
        int playAgain = JOptionPane.showConfirmDialog(null, "Do you want to play again?", "Play Again", JOptionPane.YES_NO_OPTION);
        if (playAgain == JOptionPane.YES_OPTION) {
            // If user wants to play again, so start the quiz again.
            start();
        }
    }

    // Clone the GuiQuiz instance
    @Override
    public IQuiz clone() {
        GuiQuiz clonedQuiz = null;
        try {
            clonedQuiz = (GuiQuiz) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        clonedQuiz.questions = new ArrayList<>(this.questions);
        return clonedQuiz;
    }
}
