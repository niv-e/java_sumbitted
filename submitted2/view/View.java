package view;

import java.util.ArrayList;
import java.util.Vector;

import Daniel_Niazov.Answer;
import Daniel_Niazov.Exam;
import controller.systemMnagerController;
import exceptions.MaxAnswerException;
import javafx.scene.Scene;
import javafx.stage.Stage;
import listeners.SysManUIEventsListener;

public class View implements ExamViewable {
	
	private Vector<SysManUIEventsListener> allListeners = new Vector<SysManUIEventsListener>();
	
	public View(Stage theStage) {
		theStage.setTitle("The Exam");
		
		theStage.setScene(new Scene(gpRoot, 450, 120));
		theStage.show();
	}
	
	
	
	
	
	
	
	
	
	

	public void registerListener(SysManUIEventsListener l) {
		allListeners.add(l);
	}

	@Override
	public void addNewQuestionToSystemToUI(String questionText) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void updateQuestionToUI(int numOfQuestion, String questionText) {
		// TODO Auto-generated method stub
	}

	@Override
	public void deleteQuestionFromUI(int questionForDelete) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void createHandleExamToUI(int numOfQuestion) {
		// TODO Auto-generated method stub
	}

	@Override
	public void addAnswerToUI(int numOfQuestion, String answerText, boolean isRight) throws MaxAnswerException {
		// TODO Auto-generated method stub
	}

	@Override
	public void updateAnswerFromUI(int numOfQuestion, int numOfAnswer, String answerText) {
		// TODO Auto-generated method stub	
	}
	
	@Override
	public void deleteAnswerFromUI(int numOfQuestion, int numOfAnswer) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void createRandomExamToUI(int numOfQuestions) {
		// TODO Auto-generated method stub
	}

	@Override
	public void saveQuestionToFileToUI() {
		// TODO Auto-generated method stub	
	}

	@Override
	public void loadQuestionFromFileToUI() {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void printAllQuestionsAndAnswersToUI() {
		// TODO Auto-generated method stub		
	}











	@Override
	public void registerListener(systemMnagerController smc) {
		// TODO Auto-generated method stub
		
	}
	
}
