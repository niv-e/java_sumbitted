package view;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import exceptions.MaxAnswerException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import listeners.SysManUIEventsListener;
import submittd1.Exam;

	public class View implements ExamViewable /*extends Application*/ {
		private Desktop desktop = Desktop.getDesktop();
		private Vector<SysManUIEventsListener> allListeners = new Vector<SysManUIEventsListener>();
		

		public View(Stage theStage) {
			final int BUTTON_SIZE = 250;
			theStage.setTitle("Exam Manager");
			BorderPane bpRoot = new BorderPane();
			bpRoot.setBackground(new Background(new BackgroundFill(Color.BURLYWOOD, CornerRadii.EMPTY, Insets.EMPTY)));
	

			VBox leftVbRoot = new VBox();
			leftVbRoot.setSpacing(10);
			leftVbRoot.setPadding(new Insets (10));

			//Create operator
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Load exist Questions");

			Vector<Button> leftVboxButtons = new Vector<Button>();

			Button showAllQuestion = new Button ("Show all current question");
			leftVboxButtons.add(showAllQuestion);
			showAllQuestion.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					try {
						allListeners.get(0).viewLoadQuestionFromFile("C:\\\\Java\\\\Sproject\\\\questions_list_ 2020-10-07.txt");
						Label lblString = new Label(allListeners.get(0).printAllQuestionsAndAnswersToUI()); 
						lblString.setAlignment(Pos.CENTER_LEFT);
						bpRoot.setCenter(lblString);
					} catch (FileNotFoundException | MaxAnswerException e1) {
						System.out.println(e1.getMessage());
						e1.printStackTrace();
					}	
					//configureFileChooser(fileChooser);
					//File file = fileChooser.showOpenDialog(theStage);
					//if (file != null) {
					//	openFile(file);
					//}
				}
			});
			HBox addQuestion = new HBox();
			addQuestion.setSpacing(20);
			Button addNewQuestion = new Button("Add new Question");
			leftVboxButtons.add(addNewQuestion);
			
			
			addNewQuestion.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					TextField tfQuestion = new TextField();
					Button btnAddNewQuestion = new Button("Add question");
					tfQuestion.setText("");
					addQuestion.getChildren().addAll(tfQuestion, btnAddNewQuestion);
					//bpRoot.setCenter(tfQuestion);
					//allListeners.get(0).viewAddingQuestion(tfQuestion.getText());
					//tfQuestion.setAlignment(Pos.CENTER);
					//tfQuestion.setText("");
					//bpRoot.setCenter(tfQuestion);
				
					btnAddNewQuestion.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent e) {
							allListeners.get(0).viewAddingQuestion(tfQuestion.getText());
							addingData();
						//	bpRoot.setCenter(btnAddNewQuestion);			
						}
					});
				}
			});	
			
			Button addNewAnswer = new Button("Add new answer");
			leftVboxButtons.add(addNewAnswer);
			Button deleteQuestion = new Button("Delete question");
			leftVboxButtons.add(deleteQuestion);
			Button deleteAnswer = new Button("Delete answer");
			leftVboxButtons.add(deleteAnswer);

			for(Button b : leftVboxButtons){
				b.setMaxWidth(BUTTON_SIZE);
			}

			//adding the file chooser to the vbox.
			for(int i=0 ; i<leftVboxButtons.size() ; i++){
				leftVbRoot.getChildren().add(leftVboxButtons.get(i));

			}
			Label topLabel = new Label ("Welcome to Exam manager");
			//Button saveQuestion = new Button("save");
			

			//HBox addQuestion = new HBox();
			//addQuestion.setSpacing(20);
			
				
			bpRoot.setTop(topLabel);
			bpRoot.setLeft(leftVbRoot);
			bpRoot.setCenter(addQuestion);
			//bpRoot.setCenter(addNewQuestion);

			//bpRoot.getCenter().visibleProperty().setValue(false);
			
		
			theStage.setScene(new Scene(bpRoot, 800, 600));
			theStage.show();
		}

		private static void configureFileChooser(final FileChooser fileChooser){
			fileChooser.setTitle("View Pictures");
			fileChooser.setInitialDirectory(
					new File(System.getProperty("user.home"))
			);
		}
		private void openFile(File file) {
			try {
				desktop.open(file);
			} catch (IOException ex) {
				Logger.getLogger(
						FileChooser.class.getName()).log(
						Level.SEVERE, null, ex
				);
			}
		}

		@Override
		public void registerListener(SysManUIEventsListener listener) {
			allListeners.add(listener);	
		}
		@Override
		public void addingData() {
			JOptionPane.showMessageDialog(null, "Add completed! ");	
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
		public Exam createHandleExamToUI(int numOfQuestion) {
			// TODO Auto-generated method stub
			return null;
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
		public Exam createRandomExamToUI(int numOfQuestions) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void saveQuestionToFileToUI() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void loadQuestionsAndAnswersFromFile(String filePath) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void printAllQuestionsAndAnswersToUI() {
			// TODO Auto-generated method stub
			
		}

		

		

		
}
	

	

