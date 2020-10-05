package view;

import Daniel_Niazov.Answer;
import Daniel_Niazov.Exam;
import controller.systemMnagerController;
import exceptions.MaxAnswerException;
import listeners.SysManUIEventsListener;

public interface ExamViewable {

	void registerListener(systemMnagerController smc);
	void addNewQuestionToSystemToUI(String questionText);//1
	void updateQuestionToUI(int numOfQuestion, String questionText);//2
	void deleteQuestionFromUI(int questionForDelete);//3
	void createHandleExamToUI(int numOfQuestion);//4
	void addAnswerToUI(int numOfQuestion, String answerText, boolean isRight) throws MaxAnswerException;//5
	void updateAnswerFromUI(int numOfQuestion, int numOfAnswer, String answerText);//6
	void deleteAnswerFromUI(int numOfQuestion, int numOfAnswer);//7
	void createRandomExamToUI(int numOfQuestions);
	void saveQuestionToFileToUI();//8
	void loadQuestionFromFileToUI();//9
	void printAllQuestionsAndAnswersToUI();
	
	
	
	
	
	
	
}
