package Daniel_Niazov;

import java.io.FileNotFoundException;
import java.io.IOException;

import exceptions.MaxAnswerException;
import exceptions.noEnoughAnswers;

public interface able {

	public boolean addNewQuestionToExam(String questionText);
	public boolean addNewQuestionToSystem(String questionText);
	public boolean updateQuestion(int numOfQuestion , String questionText) throws ArrayIndexOutOfBoundsException;
	public void deleteQuestion(int questionForDelete);
	public Exam handleCreateExam(int numOfQuestion);
	public boolean addQuestionToExam(Exam e ,int questionNumber);
	public void addNewAnswer(int numOfQuestion,String answerText , boolean isRight) throws MaxAnswerException;
	public void addNewOpenAnswer(int numOfQuestion,String answerText) throws MaxAnswerException;
	public boolean updateAnswerText(int numOfQuestion,int numOfAnswer , String answerText);
	public boolean deleteAnswer(int numOfQuestion, int numOfAnswer);
	public void showAnswerForSelectedQuestion(int numOfQuestion);
	public boolean addAnswerToLastQuestion(Exam exam ,int numOfQuestionFromSystem,int numOfAnswerToAdd) throws CloneNotSupportedException, MaxAnswerException;
	public void saveQuestionToFile() throws IOException;
	public void loadQuestionFromFile(String filePath) throws FileNotFoundException, MaxAnswerException;
	public int getRandomInRange(int min ,int max);
	public Exam pickRandomQuestions(int numOfQuestion) throws CloneNotSupportedException, noEnoughAnswers, MaxAnswerException;
	public boolean checkIfAnotherTrueAnswer(int numOfQues ,Answer answer) throws Exception;
	public boolean getIfTheRightAnswer(int numOfQuestion, int numOfAnswer);
	 
}
