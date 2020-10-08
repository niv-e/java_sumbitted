package listeners;

import exceptions.MaxAnswerException;

public interface SysManEventListener {

	public void addNewQuestionToModelEvent(String questionText);
	public void updateQuestionToModelEvent(int numOfQuestion , String questionText) throws ArrayIndexOutOfBoundsException;
	public void deleteQuestionFromModelEvent(int questionForDelete);
	public submittd1.Exam handleCreateExamFromModelEvent(int numOfQuestion);
	public void addNewAnswerToModelEvent(int numOfQuestion,String answerText , boolean isRight) throws MaxAnswerException;
	public void updateAnswerTextToModelEvent(int numOfQuestion,int numOfAnswer , String answerText);
	public void deleteAnswerFromModelEvent(int numOfQuestion, int numOfAnswer);
	public submittd1.Exam createExamFromRandomQuestionsFromModelEvent(int numOfQuestions);
	public void loadQuestionFromFileToModelEvent(String filePath);
	public void showAllQuestionsAndAnswersFromModelEvent();
	public void FireSuccesfullAdding();
}
