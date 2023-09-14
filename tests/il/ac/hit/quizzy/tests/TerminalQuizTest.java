package il.ac.hit.quizzy.tests;

import il.ac.hit.quizzy.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

// setName, getName, addQuestion, clone
public class TerminalQuizTest {

    private IQuiz terminalQuiz;

    @Before
    public void setUp() {
        // Create the QuizFactory and the GUIQuiz instance
        QuizFactory factory = new QuizFactory();
        terminalQuiz = factory.createQuiz(QuizType.TERMINAL);
        terminalQuiz.setName("Quiz Test Terminal");

        // Create a sample question and add it to the guiQuiz
        IQuizQuestionBuilder builderTest = new QuizQuestion.Builder();
        builderTest.setTitle("We Love Canada");
        builderTest.setQuestion("Canada starts with…?");
        builderTest.addAnswer("Canada starts with the letter ‘A’.", false);
        builderTest.addAnswer("Canada starts with the letter ‘B’.", false);
        builderTest.addAnswer("Canada starts with the letter ‘C’.", true);
        builderTest.addAnswer("Canada starts with the letter ‘D’.", false);
        builderTest.addAnswer("Canada starts with the letter ‘E’.", false);
        IQuizQuestion questionTest = builderTest.create();
        terminalQuiz.addQuestion(questionTest);
    }

    @Test
    public void testSetName() {

        // Check when name is not sent
        terminalQuiz.setName(null);
        assertEquals("Default Name", terminalQuiz.getName());

        // Check when name is set
        terminalQuiz.setName("TestQuizName");
        assertEquals("TestQuizName", terminalQuiz.getName());
    }

    @Test
    public void testGetName() {

        // "Quiz Test Terminal" was set in the setUp method
        assertEquals("Quiz Test Terminal", terminalQuiz.getName());
    }

    @Test
    public void testAddQuestion() {

        // Question was added in the setUp method
        assertEquals(1, terminalQuiz.getQuestions().size());
    }

    @Test
    public void testClone() throws CloneNotSupportedException {
        TerminalQuiz clonedQuiz = (TerminalQuiz) terminalQuiz.clone();

        // Check that the cloned quiz is a different object
        assert (terminalQuiz != clonedQuiz);

        // Check that the questions are cloned correctly
        assertEquals(terminalQuiz.getQuestions().size(), clonedQuiz.getQuestions().size());
        assertEquals(terminalQuiz.getQuestions().get(0).getQuestion(), clonedQuiz.getQuestions().get(0).getQuestion());
    }
}
