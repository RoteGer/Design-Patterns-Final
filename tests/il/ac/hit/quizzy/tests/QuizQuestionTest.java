package il.ac.hit.quizzy.tests;

import il.ac.hit.quizzy.IQuizQuestion;
import il.ac.hit.quizzy.IQuizQuestionBuilder;
import il.ac.hit.quizzy.QuizAnswer;
import il.ac.hit.quizzy.QuizQuestion;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class QuizQuestionTest {

    private IQuizQuestionBuilder builder;
    private List<QuizAnswer> answerList = new LinkedList<>();

    @Before
    public void setUp() {
        // Initialize a QuizQuestion.Builder instance before each test
        builder = new QuizQuestion.Builder();
        IQuizQuestion question = builder.setTitle("Sample Title Test").create();

    }

    @Test
    public void testSetTitle() {
        // Test setting the title of a quiz question
        IQuizQuestion question = builder.setTitle("Sample Title").create();

        // Assert that the title is correctly set
        assertEquals("Sample Title", question.getTitle());
    }

    @Test
    public void testSetQuestion() {
        // Test setting the question text of a quiz question
        IQuizQuestion question = builder.setQuestion("Sample Question Text").create();

        // Assert that the question text is correctly set
        assertEquals("Sample Question Text", question.getQuestion());
    }

    @Test
    public void testAddAnswer() {
        // Test adding answer options to a quiz question
        IQuizQuestion question = builder
                .addAnswer("Option 1", true)
                .addAnswer("Option 2", false)
                .create();

        // Get the options and verify that they are correctly added
        String[] options = question.getOptions();
        assertEquals(2, options.length);
        assertEquals("Option 1", options[0]);
        assertEquals("Option 2", options[1]);
    }

    @Test
    public void testCreate() {
        // Test creating a complete QuizQuestion object using the builder
        IQuizQuestion question = builder
                .setTitle("Sample Title")
                .setQuestion("Sample Question Text")
                .addAnswer("Option 1", true)
                .addAnswer("Option 2", false)
                .create();


        // Assert that the created question is not null
        assertNotNull(question);

        // Verify that the title is correctly set
        assertEquals("Sample Title", question.getTitle());

        // Verify that the question text is correctly set
        assertEquals("Sample Question Text", question.getQuestion());

        // Verify that answer options are correctly set
        String[] answers = question.getOptions();

        // Mock
        answerList.add(new QuizAnswer("Option 1", true));
        answerList.add(new QuizAnswer("Option 2", false));

        assertEquals(2, answers.length);
        assertEquals("Option 1", answers[0]);
        assertEquals("Option 2", answers[1]);
        assertTrue(answerList.get(0).isCorrect());
        assertFalse(answerList.get(1).isCorrect());
    }
}
