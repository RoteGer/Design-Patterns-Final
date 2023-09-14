package il.ac.hit.quizzy.tests;

import il.ac.hit.quizzy.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

// saveQuizToFile, loadQuizFromFile
public class SimpleCSVQuizFilesDAOTest {

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    private SimpleCSVQuizFilesDAO dao;

    @Before
    public void setUp() {
        dao = SimpleCSVQuizFilesDAO.getInstance();
    }

    @Test
    public void testSaveQuizToFile() throws QuizException, IOException {
        // Create a temporary directory for testing
        File tempDir = tempFolder.newFolder();

        // Create a sample quiz
        QuizFactory factory = new QuizFactory();
        IQuiz guiQuiz = factory.createQuiz(QuizType.GUI);
        guiQuiz.setName("Test Quiz Name");

        // Add a sample question to the quiz
        IQuizQuestionBuilder builder = new QuizQuestion.Builder();
        builder.setTitle("Sample Question");
        builder.setQuestion("Who lives in a pineapple under the sea?");
        builder.addAnswer("Squidward Tentacles", false);
        builder.addAnswer("SpongeBob SquarePants", true);
        builder.addAnswer("Patrick Star", false);
        builder.addAnswer("Mr. Krabs", false);
        builder.addAnswer("Sendy Chicks", false);
        IQuizQuestion question = builder.create();
        guiQuiz.addQuestion(question);

        // Define the file path for saving the quiz
        String filePath = new File(tempDir, "test_quiz").getAbsolutePath();
        System.out.println(filePath + ".csv");

        // Save the quiz to the file
        dao.saveQuizToFile(guiQuiz, filePath);

        // Assert that the file was created
        File savedFile = new File(filePath + ".csv");
        System.out.println(savedFile);
        assertTrue(savedFile.exists());
    }

    @Test
    public void testLoadQuizFromFile() throws QuizException, IOException {
        // Create a temporary directory for testing
        File tempDir = tempFolder.newFolder();

        // Create a sample quiz
        QuizFactory factory = new QuizFactory();
        IQuiz guiQuiz = factory.createQuiz(QuizType.GUI);
        guiQuiz.setName("Test Quiz Name");

        // Add a sample question to the quiz
        IQuizQuestionBuilder builder = new QuizQuestion.Builder();
        builder.setTitle("Sample Question");
        builder.setQuestion("Who lives in a pineapple under the sea?");
        builder.addAnswer("Squidward Tentacles", false);
        builder.addAnswer("SpongeBob SquarePants", true);
        builder.addAnswer("Patrick Star", false);
        builder.addAnswer("Mr. Krabs", false);
        builder.addAnswer("Sendy Chicks", false);
        IQuizQuestion question = builder.create();
        guiQuiz.addQuestion(question);

        // Define the file path for saving and loading the quiz
        String filePath = new File(tempDir, "test_quiz").getAbsolutePath();

        // Save the quiz to the file
        dao.saveQuizToFile(guiQuiz, filePath);

        // Load the quiz from the file
        IQuiz loadedQuiz = dao.loadQuizFromFile(filePath);

        // Assert that the loaded quiz is not null
        assertNotNull(loadedQuiz);

        // Assert that the loaded quiz has the correct name
        assertEquals("Test Quiz Name", loadedQuiz.getName());

        // Assert that the loaded quiz has questions
        List<IQuizQuestion> loadedQuestions = loadedQuiz.getQuestions();
        assertEquals(1, loadedQuestions.size());

        // Assert that the loaded question has the correct title
        assertEquals("Sample Question", loadedQuestions.get(0).getTitle());

        // Assert that the loaded question has the correct options
        String[] options = loadedQuestions.get(0).getOptions();
        assertEquals(5, options.length);
        assertEquals("Squidward Tentacles", options[0]);
        assertEquals("SpongeBob SquarePants", options[1]);
        assertEquals("Patrick Star", options[2]);
        assertEquals("Mr. Krabs", options[3]);
        assertEquals("Sendy Chicks", options[4]);
    }
}
