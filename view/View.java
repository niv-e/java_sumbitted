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
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
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

	public class View implements ExamViewable{
		private Desktop desktop = Desktop.getDesktop();
		private Vector<SysManUIEventsListener> allListeners = new Vector<SysManUIEventsListener>();
		Vector<Button> leftVboxButtons = new Vector<Button>();
		Vector <Node> centerNodes = new Vector <Node>();

		BorderPane bpRoot;
		VBox leftVbRoot;
		HBox addQuestionHBox;
		
		//Create operator
		FileChooser fileChooser = new FileChooser();
		
		Label topLabel;
		Label lblLoadQuestion;
		
		Button loadFromFile;
		Button showAllQuestion;
		Button addNewQuestion;
		Button btnSaveQuestion;
		Button addNewAnswer;
		Button deleteQuestion;
		Button deleteAnswer;
		
		TextField tfQuestion;
		
		TextArea area ;
		
		public View(Stage theStage) {
			final int BUTTON_SIZE = 250;
			theStage.setTitle("Exam Manager");
			topLabel = new Label ("Welcome to Exam manager");
			area = new TextArea();
			area.setVisible(false);


			bpRoot = new BorderPane();
			bpRoot.setBackground(new Background(new BackgroundFill(Color.BURLYWOOD, CornerRadii.EMPTY, Insets.EMPTY)));
	

			leftVbRoot = new VBox();
			leftVbRoot.setSpacing(10);
			leftVbRoot.setPadding(new Insets (10));

			addQuestionHBox = new HBox();
			addQuestionHBox.setSpacing(20);			
			
	
			addNewAnswer = new Button("Add new answer");
			deleteQuestion = new Button("Delete question");
			deleteAnswer = new Button("Delete answer");
		
		
			
			centerNodes.add(addQuestionHBox);
			centerNodes.add(lblLoadQuestion);
			centerNodes.add(area);
			
			
			bpRoot.setTop(topLabel);
			bpRoot.setLeft(leftVbRoot);
			bpRoot.setCenter(lblLoadQuestion);
	
			
			fileChooser.setTitle("Load exist Questions");
			lblLoadQuestion = new Label();
			
			loadFromFile = new Button("Load from file");
			loadFromFile.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					try {					
						for(SysManUIEventsListener l :allListeners) {
							String filePath = fileChooser.showOpenDialog(theStage).getAbsolutePath();
							l.viewLoadQuestionFromFile(filePath);
							}	
					}catch (Exception e1) {
						System.out.println(e1.getMessage());
						e1.printStackTrace();
					}
				}
			
			});

			showAllQuestion = new Button ("Show all current question");
			showAllQuestion.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
						for(SysManUIEventsListener l :allListeners) {
							l.printAllQuestionsAndAnswersToUI();
							}	
				}	
			});	
			
			
			addNewQuestion = new Button("Add new Question");
			tfQuestion = new TextField("");
			btnSaveQuestion = new Button("Save question");
			
			addQuestionHBox.getChildren().addAll(tfQuestion, btnSaveQuestion);
			addQuestionHBox.setVisible(false);

			addNewQuestion.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					bpRoot.setCenter(addQuestionHBox);
					if(!addQuestionHBox.isVisible()) {
						addQuestionHBox.setVisible(true);
					}
					else {
						addQuestionHBox.setVisible(false);
					}
				}
			});	
			
			btnSaveQuestion.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					for(SysManUIEventsListener l :allListeners) {
						l.viewAddingQuestion(tfQuestion.getText());
					tfQuestion.setText("");
					JOptionPane.showMessageDialog(null, "A new question was added to the system");
					}
				}
			});


			leftVboxButtons.add(loadFromFile);
			leftVboxButtons.add(showAllQuestion);
			leftVboxButtons.add(addNewQuestion);
			leftVboxButtons.add(addNewAnswer);
			leftVboxButtons.add(deleteQuestion);
			leftVboxButtons.add(deleteAnswer);
			
			for(Button b : leftVboxButtons){
				b.setMaxWidth(BUTTON_SIZE);
//				System.out.println(b.isVisible());
			}
			
			//adding the file chooser to the vbox.
			for(int i=0 ; i<leftVboxButtons.size() ; i++){
				leftVbRoot.getChildren().add(leftVboxButtons.get(i));
			}
		
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
		
		public void showLoadedFile(String allQuestion) {
			lblLoadQuestion.setText(allQuestion);
			lblLoadQuestion.setAlignment(Pos.CENTER_LEFT);
		}

		@Override
		public void printAllQuestionsAndAnswersToUI(String dataToShow) {
			bpRoot.setCenter(area);
			area.setText(dataToShow);
			if(!area.isVisible()) {
				area.setVisible(true);
			}
			else {
				area.setVisible(true);
			}
		}
		
		public void turnVisibleOff(Node[] nodeList) {
			for(Node l : nodeList)
				l.setVisible(false);			
		}
		
}
	

	

