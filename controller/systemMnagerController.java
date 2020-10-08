package controller;

import java.io.FileNotFoundException;

import exceptions.MaxAnswerException;
import listeners.SysManEventListener;
import listeners.SysManUIEventsListener;
import submittd1.Exam;
import view.ExamViewable;


public class systemMnagerController implements SysManEventListener, SysManUIEventsListener{
	
	private submittd1.SystemManager theModel;
	private ExamViewable theView;
	
	public systemMnagerController(submittd1.SystemManager m, ExamViewable v) {
		theModel = m;
		theView = v;
		
		theModel.registerListener(this);
		theView.registerListener(this);
	}
	
	//Events to view

	@Override
	public void addNewQuestionToModelEvent(String questionText) {
		theView.addNewQuestionToSystemToUI(questionText);	
	}

	@Override
	public void updateQuestionToModelEvent(int numOfQuestion, String questionText) throws ArrayIndexOutOfBoundsException {
		theView.updateQuestionToUI(numOfQuestion, questionText);
	}

	@Override
	public void deleteQuestionFromModelEvent(int questionForDelete) {
		theView.deleteQuestionFromUI(questionForDelete);
	}

	@Override
	public Exam handleCreateExamFromModelEvent(int numOfQuestion) {
		return theView.createHandleExamToUI(numOfQuestion);
	}

	@Override
	public void addNewAnswerToModelEvent(int numOfQuestion, String answerText, boolean isRight) throws MaxAnswerException {
		theView.addAnswerToUI(numOfQuestion, answerText, isRight);	
	}

	@Override
	public void updateAnswerTextToModelEvent(int numOfQuestion, int numOfAnswer, String answerText) {
		theView.updateAnswerFromUI(numOfQuestion, numOfAnswer, answerText);
	}

	@Override
	public void deleteAnswerFromModelEvent(int numOfQuestion, int numOfAnswer) {
		theView.deleteAnswerFromUI(numOfQuestion, numOfAnswer);
	}
	
	@Override
	public Exam createExamFromRandomQuestionsFromModelEvent(int numOfQuestions) {
		return theView.createRandomExamToUI(numOfQuestions);	
	}

	@Override
	public void showAllQuestionsAndAnswersFromModelEvent() {
		theView.printAllQuestionsAndAnswersToUI();	
	}
	
	@Override
	public void loadQuestionFromFileToModelEvent(String filePath) {
		theView.loadQuestionsAndAnswersFromFile(filePath);	
	}
	
	@Override
	public void FireSuccesfullAdding() {
		theView.addingData();
	}
	
	//Events to Model
	
	@Override
	public void viewAddingQuestion(String questionText) {
		theModel.addNewQuestionToSystem(questionText);	
	}
	
	public Boolean viewUpdateQuestion(int numOfQuestion , String questionText) {
		return theModel.updateQuestion(numOfQuestion, questionText);
	}
	
	public void viewRemoveQuestion(int questionForDelete) {
		theModel.deleteQuestion(questionForDelete);
	}

	@Override
	public Exam viewCreateHandleExam(int numOfQuestion) {
		return theModel.handleCreateExam(numOfQuestion);
	}
	
	@Override
	public void viewAddingAnswer(int numOfQuestion, String answerText, boolean isRight) throws MaxAnswerException{
		theModel.addNewAnswer(numOfQuestion, answerText, isRight);
	}
	
	@Override
	public Boolean viewUpdateAnswer(int numOfQuestion, int numOfAnswer, String answerText) {
		return theModel.updateAnswerText(numOfQuestion, numOfAnswer, answerText);
	}
	
	@Override
	public Boolean viewRemoveAnswer(int numOfQuestion, int numOfAnswer) {
		return theModel.deleteAnswer(numOfQuestion, numOfAnswer);
	}

	@Override
	public Exam viewCreateRandomExam(int numOfQuestions) {
		return theModel.createExamFromRandomQuestions();	
	}
	
	@Override
	public void viewLoadQuestionFromFile(String filePath) throws FileNotFoundException, MaxAnswerException {
		theModel.loadQuestionFromFile(filePath);
		
	}
	
	@Override
	public String printAllQuestionsAndAnswersToUI() {
		return theModel.toString();
	}

	

	
	
	
}

