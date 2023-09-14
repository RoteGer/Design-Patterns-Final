package il.ac.hit.quizzy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TerminalQuiz implements IQuiz {

    // Implementation for terminal-based quiz
    private String name;
    List<Integer> inputOptions = Arrays.asList(1, 2, 3, 4, 5);
    private List<IQuizQuestion> questions = new ArrayList<>();
    private int score = 0;
    public ArrayList<String> questionsArr = new ArrayList<String>();
    ;

    public TerminalQuiz() {
    }

    @Override
    public void start() {
        System.out.println("Welcome to the Terminal Quiz: " + name);
        play();
    }

    @Override
    public void setName(String text) {
        if (text.isEmpty()) {
            text = "default name";
        }
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

    private void terminalOptions(IQuizQuestion question) {
        int i = 1;

        // Get each option from String array
        for (String option : question.getOptions()) {
            System.out.println(i + ") " + option);
            i++;
        }
    }

    public void addQuestion(IQuizQuestion question) {
        questions.add(question);
    }


    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        if (score > 0) {
            this.score = score;
        }
    }

    private void play() {

        for (IQuizQuestion question : questions) {
            boolean validInput = false;
            int usersInput = -1;
            System.out.println(question.getTitle());

            //  Get each question
            System.out.println("\n<<< Question >>>\n" + question.getQuestion());
            System.out.println("\n<<< Options >>>");

            //  Print each option to terminal
            terminalOptions(question);

            Scanner scanner;
            scanner = new Scanner(System.in);

            while (!validInput) {
                System.out.println("\n\n<< Choose an answer between 1 to " + inputOptions.size() + " >>>");
                try {
                    usersInput = scanner.nextInt();
                    scanner.nextLine();
                    if (!inputOptions.contains(usersInput)) {
                        System.out.println("Invalid input. Try again");
                    } else {
                        validInput = true;
                        if (question.isUserCorrect(usersInput - 1)) {
                            System.out.println("Correct! \n\n");
                            setScore(getScore() + 1);
                        } else {
                            System.out.println("False! \n\n");
                        }
                    }

                } catch (Exception e) {
                    System.out.println("Enter new answer.");
                    scanner.nextLine();
                }
            }


        }
        System.out.println("Do you want to play again? Write YES if you do");
        Scanner playAgain = new Scanner(System.in);
        String playAgainChoice = playAgain.nextLine().trim(); // Read user input and remove leading/trailing spaces        if ("yes".equals(playAgain.toString()))
        if ("yes".equalsIgnoreCase(playAgainChoice)) { // Use equalsIgnoreCase for case-insensitive comparison
            play();
        } else {
            System.out.println("Thanks for playing. \n\n");
            endQuiz();
        }
    }

    @Override
    public void endQuiz() {
        int totalQuestions = questions.size();

        double percentageCorrect = (double) score / totalQuestions * 100;
        System.out.println("Quiz ended. Here are your results: ");
        System.out.println("You answered " + score + " out of " + totalQuestions + " questions correctly.");
        System.out.println("Your score: " + percentageCorrect + "%");

    }

    @Override
    public IQuiz clone() throws CloneNotSupportedException {
        Object clone = null;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return (IQuiz) clone;
    }
}

