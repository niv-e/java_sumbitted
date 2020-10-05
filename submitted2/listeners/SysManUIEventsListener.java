package listeners;

import Daniel_Niazov.Exam;
import controller.systemMnagerController;
import exceptions.MaxAnswerException;

public interface SysManUIEventsListener {

	void addNewQuestionToSystemFromUI(String questionText);//1
	void updateQuestionToUI(int numOfQuestion, String questionText);//2
	void deleteQuestionFromUI(int questionForDelete);//3
	void addAnswerToUI(int numOfQuestion, String answerText, boolean isRight) throws MaxAnswerException;//5
	void updateAnswerToUI(int numOfQuestion, int numOfAnswer, String answerText);//6
	void deleteAnswerToUI(int numOfQuestion, int numOfAnswer);//7
	void createHandleExamToUI(int numOfQuestion);//4
	void createRandomExamToUI(int numOfQuestions);
	public void printAllQuestionsAndAnswersToUI();
}
