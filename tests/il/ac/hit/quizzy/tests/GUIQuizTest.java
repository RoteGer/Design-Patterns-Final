package il.ac.hit.quizzy.tests;

import il.ac.hit.quizzy.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

// setName, getName, addQuestion, clone
public class GUIQuizTest {

    private IQuiz guiQuiz;

    @Before
    public void setUp() {
        // Create the QuizFactory and the GUIQuiz instance
        QuizFactory factory = new QuizFactory();
        guiQuiz = factory.createQuiz(QuizType.GUI);
        guiQuiz.setName("Quiz Test");

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
        guiQuiz.addQuestion(questionTest);
    }

    @Test
    public void testSetName() {

        // Check when name is not sent
        guiQuiz.setName(null);
        assertEquals("Default Name", guiQuiz.getName());

        // Check when name is set
        guiQuiz.setName("TestQuizName");
        assertEquals("TestQuizName", guiQuiz.getName());
    }

    @Test
    public void testGetName() {

        // "Quiz Test" was set in the setUp method
        assertEquals("Quiz Test", guiQuiz.getName());
    }

    @Test
    public void testAddQuestion() {

        // Question was added in the setUp method
        assertEquals(1, guiQuiz.getQuestions().size());
    }

    @Test
    public void testClone() throws CloneNotSupportedException {
        GUIQuiz clonedQuiz = (GUIQuiz) guiQuiz.clone();

        // Check that the cloned quiz is a different object
        assert (guiQuiz != clonedQuiz);

        // Check that the questions are cloned correctly
        assertEquals(guiQuiz.getQuestions().size(), clonedQuiz.getQuestions().size());
        assertEquals(guiQuiz.getQuestions().get(0).getQuestion(), clonedQuiz.getQuestions().get(0).getQuestion());
    }
}