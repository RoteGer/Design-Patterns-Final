package il.ac.hit.quizzy.tests;

import il.ac.hit.quizzy.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

// createQuiz
public class QuizFactoryTest {

    private QuizFactory quizFactory;

    @Before
    public void setUp() {
        quizFactory = new QuizFactory();
    }

    @Test
    public void testCreateQuizWithGUIType() {
        // Test creating a GUIQuiz
        IQuiz guiQuiz = quizFactory.createQuiz(QuizType.GUI);

        // Check if the created quiz is not null
        assertNotNull(guiQuiz);

        // Check if the created quiz is an instance of GUIQuiz
        assertTrue(guiQuiz instanceof GUIQuiz);

        // Check if the default name is set for GUIQuiz
        assertEquals("Default Name", guiQuiz.getName());
    }

    @Test
    public void testCreateQuizWithTerminalType() {
        // Test creating a TerminalQuiz
        IQuiz terminalQuiz = quizFactory.createQuiz(QuizType.TERMINAL);

        // Check if the created quiz is not null
        assertNotNull(terminalQuiz);

        // Check if the created quiz is an instance of TerminalQuiz
        assertTrue(terminalQuiz instanceof TerminalQuiz);

        // Check if the default name is set for TerminalQuiz
        assertEquals("Default Name", terminalQuiz.getName());
    }

    @Test
    public void testCreateQuizWithUnsupportedType() {
        // Test creating a quiz with an unsupported type (null)
        IQuiz unsupportedQuiz = quizFactory.createQuiz(null);

        // Check if the result is null, indicating an unsupported type
        assertNull(unsupportedQuiz);
    }
}
