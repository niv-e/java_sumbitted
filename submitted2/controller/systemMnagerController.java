package controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import Daniel_Niazov.Answer;
import Daniel_Niazov.Exam;
import Daniel_Niazov.SystemManager;
import Daniel_Niazov.able;
import exceptions.MaxAnswerException;
import exceptions.noEnoughAnswers;
import listeners.SysManEventListener;
import listeners.SysManUIEventsListener;
import view.ExamViewable;


public class systemMnagerController implements SysManEventListener, SysManUIEventsListener{
	
	private SystemManager theModel;
	private ExamViewable theView;
	
	public systemMnagerController(SystemManager m, ExamViewable v) {
		theModel = m;
		theView = v;
		
		theModel.registerListener(this);
		theView.registerListener(this);
	}
	
	//Events to view

	@Override
	public void addNewQuestionToSystem(String questionText) {
		theView.addNewQuestionToSystemToUI(questionText);	
	}

	@Override
	public void updateQuestion(int numOfQuestion, String questionText) throws ArrayIndexOutOfBoundsException {
		theView.updateQuestionToUI(numOfQuestion, questionText);
	}

	@Override
	public void deleteQuestion(int questionForDelete) {
		theView.deleteQuestionFromUI(questionForDelete);
	}

	@Override
	public void handleCreateExam(int numOfQuestion) {
		theView.createHandleExamToUI(numOfQuestion);
	}

	@Override
	public void addNewAnswer(int numOfQuestion, String answerText, boolean isRight) throws MaxAnswerException {
		theView.addAnswerToUI(numOfQuestion, answerText, isRight);	
	}

	@Override
	public void updateAnswerText(int numOfQuestion, int numOfAnswer, String answerText) {
		theView.updateAnswerFromUI(numOfQuestion, numOfAnswer, answerText);
	}

	@Override
	public void deleteAnswer(int numOfQuestion, int numOfAnswer) {
		theView.deleteAnswerFromUI(numOfQuestion, numOfAnswer);
	}

	@Override
	public void saveQuestionToFile() throws IOException {
		theView.saveQuestionToFileToUI();
		
	}

	@Override
	public void loadQuestionFromFile() throws FileNotFoundException, MaxAnswerException {
		theView.loadQuestionFromFileToUI();	
	}
	
	@Override
	public void createExamFromRandomQuestions(int numOfQuestions) {
		theView.createRandomExamToUI(numOfQuestions);	
	}

	@Override
	public void showAllQuestionsAndAnswers() {
		theView.printAllQuestionsAndAnswersToUI();	
	}
	
	//Events to Model
	
	@Override
	public void addNewQuestionToSystemFromUI(String questionText) {
		theModel.addNewQuestionToSystem(questionText);	
	}
	
	public void updateQuestionToUI(int numOfQuestion , String questionText) {
		theModel.updateQuestion(numOfQuestion, questionText);
	}
	
	public void deleteQuestionFromUI(int questionForDelete) {
		theModel.deleteQuestion(questionForDelete);
	}

	@Override
	public void createHandleExamToUI(int numOfQuestion) {
		theModel.handleCreateExam(numOfQuestion);
	}
	
	@Override
	public void addAnswerToUI(int numOfQuestion, String answerText, boolean isRight) throws MaxAnswerException{
		theModel.addNewAnswer(numOfQuestion, answerText, isRight);
	}
	
	@Override
	public void updateAnswerToUI(int numOfQuestion, int numOfAnswer, String answerText) {
		theModel.updateAnswerText(numOfQuestion, numOfAnswer, answerText);
	}
	
	@Override
	public void deleteAnswerToUI(int numOfQuestion, int numOfAnswer) {
		theModel.deleteAnswer(numOfQuestion, numOfAnswer);
	}

	@Override
	public void createRandomExamToUI(int numOfQuestions) {
		theModel.createExamFromRandomQuestions();	
	}

	@Override
	public void printAllQuestionsAndAnswersToUI() {
		theModel.toString();
	}
	
	

	

	

	

	
	


	
	

	
	
	
	
	
	
	
	
	
}

