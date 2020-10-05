package listeners;

import java.io.FileNotFoundException;
import java.io.IOException;

import Daniel_Niazov.Exam;
import exceptions.MaxAnswerException;

public interface SysManEventListener {

	public void loadQuestionFromFile() throws FileNotFoundException, MaxAnswerException;//9
	public void saveQuestionToFile() throws IOException;//8
	public void createExamFromRandomQuestions(int numOfQuestions);//complete this method in system Manager
	public void handleCreateExam(int numOfQuestion);//4
	public void addNewQuestionToSystem(String questionText);//1
	public void updateQuestion(int numOfQuestion , String questionText) throws ArrayIndexOutOfBoundsException;//2
	public void deleteQuestion(int questionForDelete);//3
	public void addNewAnswer(int numOfQuestion,String answerText , boolean isRight) throws MaxAnswerException;//5
	public void updateAnswerText(int numOfQuestion,int numOfAnswer , String answerText);//6
	public void deleteAnswer(int numOfQuestion, int numOfAnswer);//7
	public void showAllQuestionsAndAnswers();//complete this method in system Manager
	
	
	
	
}
