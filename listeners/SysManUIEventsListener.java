package listeners;

import java.io.FileNotFoundException;

import exceptions.MaxAnswerException;

public interface SysManUIEventsListener {

	void viewAddingQuestion(String questionText);//1
	Boolean viewUpdateQuestion(int numOfQuestion, String questionText);//2
	void viewRemoveQuestion(int questionForDelete);//3
	void viewAddingAnswer(int numOfQuestion, String answerText, boolean isRight) throws MaxAnswerException;//5
	Boolean viewUpdateAnswer(int numOfQuestion, int numOfAnswer, String answerText);//6
	Boolean viewRemoveAnswer(int numOfQuestion, int numOfAnswer);//7
	submittd1.Exam viewCreateHandleExam(int numOfQuestion);//4
	submittd1.Exam viewCreateRandomExam(int numOfQuestions);
	void viewLoadQuestionFromFile(String filePath) throws FileNotFoundException, MaxAnswerException;
	void printAllQuestionsAndAnswersToUI();
	
}
