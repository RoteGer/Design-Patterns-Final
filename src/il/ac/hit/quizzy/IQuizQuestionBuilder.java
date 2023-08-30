package il.ac.hit.quizzy;

public interface IQuizQuestionBuilder {

    public IQuizQuestionBuilder setTitle(String text);
    public IQuizQuestionBuilder setQuestion(String text);
    public IQuizQuestionBuilder addAnswer(String text, boolean correct);
    public IQuizQuestion create();
}

