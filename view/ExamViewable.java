package view;

import exceptions.MaxAnswerException;
import listeners.SysManUIEventsListener;
import submittd1.Exam;

public interface ExamViewable {

	void registerListener(SysManUIEventsListener listener);
	
	void addNewQuestionToSystemToUI(String questionText);
	void updateQuestionToUI(int numOfQuestion, String questionText);
	void deleteQuestionFromUI(int questionForDelete);
	Exam createHandleExamToUI(int numOfQuestion);
	void addAnswerToUI(int numOfQuestion, String answerText, boolean isRight) throws MaxAnswerException;
	void updateAnswerFromUI(int numOfQuestion, int numOfAnswer, String answerText);
	void deleteAnswerFromUI(int numOfQuestion, int numOfAnswer);
	Exam createRandomExamToUI(int numOfQuestions);
	void saveQuestionToFileToUI();
	void loadQuestionsAndAnswersFromFile(String filePath);
	void printAllQuestionsAndAnswersToUI(String toShow);
	void addingData();
}
