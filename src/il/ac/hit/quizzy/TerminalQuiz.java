package il.ac.hit.quizzy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TerminalQuiz implements IQuiz {

    // Variable declarations
    private String name;
    private List<IQuizQuestion> questions = new ArrayList<>();
    private int score = 0;

    // Constructor
    public TerminalQuiz() {
        setName(name); // Use the setter to initialize name
    }

    // Start the terminal-based quiz
    @Override
    public void start() {
        System.out.println("\n" + "Welcome to the Terminal Quiz: " + name + "\n");
        play();
    }

    // Set the name for the quiz
    @Override
    public void setName(String text) {
        if (text == null) {
            this.name = "default name";
        } else {
            this.name = text;
        }
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

    // Display terminal options for a given question
    private void terminalOptions(IQuizQuestion question) {
        int i = 1;

        // Get each option from String array
        for (String option : question.getOptions()) {
            System.out.println(i + ") " + option);
            i++;
        }
    }

    // Add a quiz question to the list
    public void addQuestion(IQuizQuestion question) {
        if (question != null) {
            questions.add(question);
        }
    }

    // Get the current quiz score
    public int getScore() {
        return this.score;
    }

    // Set the quiz score
    public void setScore(int score) {
        if (score > 0) {
            this.score = score;
        }
    }

    // Play the terminal quiz
    private void play() {
        score = 0;
        for (IQuizQuestion question : questions) {
            boolean validInput = false;
            int usersInput = -1;
            System.out.println(question.getTitle());

            // Get each question
            System.out.println("\n<<< Question >>>\n" + question.getQuestion());
            System.out.println("\n<<< Options >>>");

            // Print each option to the terminal
            terminalOptions(question);

            Scanner scanner;
            scanner = new Scanner(System.in);

            while (!validInput) {
                System.out.println("\n\n<< Choose an answer between 1 to " + question.getOptions().length + " >>>");
                try {
                    usersInput = scanner.nextInt();
                    scanner.nextLine();
                    if (usersInput < 1 || usersInput > question.getOptions().length) {
                        System.out.println("Number not in options. Try again.");
                    } else {
                        validInput = true;
                        if (question.isUserCorrect(usersInput - 1)) {
                            System.out.println("Correct!!!! \n\n");
                            setScore(getScore() + 1);
                        } else {
                            System.out.println("False!!!! \n\n");
                        }
                    }

                } catch (Exception e) {
                    System.out.println("Invalid input. Try again.");
                    scanner.nextLine();
                }
            }
        }

        System.out.println("Thanks for playing. \n");
        endQuiz();
    }

    // End the terminal quiz and display results
    @Override
    public void endQuiz() {
        int totalQuestions = questions.size();
        double percentageCorrect = (double) score / totalQuestions * 100;
        System.out.println("Quiz ended. Here are your results: ");
        System.out.println("You answered " + score + " out of " + totalQuestions + " questions correctly.");
        System.out.println("Your score: " + percentageCorrect + "%");

        System.out.println("\n\nDo you want to play again? Write YES if you do");
        Scanner playAgain = new Scanner(System.in);
        String playAgainChoice = playAgain.nextLine().trim(); // Read user input and remove leading/trailing spaces
        if ("yes".equalsIgnoreCase(playAgainChoice)) { // Use equalsIgnoreCase for case-insensitive comparison
            play();
        } else {
            System.out.println("\nOkay. Goodbye.");
        }
    }

    // Clone the TerminalQuiz instance
    @Override
    public IQuiz clone() {
        TerminalQuiz clonedQuiz = null;
        try {
            clonedQuiz = (TerminalQuiz) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        clonedQuiz.questions = new ArrayList<>(this.questions);
        return clonedQuiz;
    }
}
