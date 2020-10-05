package submitted1.examView;//ExanManagerVier.java

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExamManagerView extends Application {
	private Desktop desktop = Desktop.getDesktop();


	@Override
	public void start(Stage theStage) {
		final int BUTTON_SIZE = 250;
		theStage.setTitle("Exam Manager");
		BorderPane bpRoot = new BorderPane();
		bpRoot.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));

		VBox leftVbRoot = new VBox();
		leftVbRoot.setSpacing(10);
		leftVbRoot.setPadding(new Insets (10));

		//Create operator
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Load exist Questions");

		//Create loading file button
		Button loadFile = new Button("Load exist Questions");
		loadFile.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				configureFileChooser(fileChooser);
				File file = fileChooser.showOpenDialog(theStage);
				if (file != null) {
					openFile(file);
				}
			}
		});

		Vector<Button> leftVboxButtons = new Vector<Button>();

		Button showAllQuestion = new Button ("Show all current question");
		leftVboxButtons.add(showAllQuestion);
		Button addNewQuestion = new Button("Add new Question");
		leftVboxButtons.add(addNewQuestion);
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

		Button saveQuestion = new Button("save");
		Label topLabel = new Label ("Welcome to Exam manager");

		HBox addQuestion = new HBox();
		addQuestion.setSpacing(20);

		TextField textfield = new TextField();
		addQuestion.getChildren().addAll(textfield, saveQuestion);

		addNewQuestion.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				bpRoot.getCenter().visibleProperty().setValue(true);
			}
		});

		bpRoot.setTop(topLabel);
		bpRoot.setLeft(leftVbRoot);
		bpRoot.setCenter(addQuestion);

		bpRoot.getCenter().visibleProperty().setValue(false);
		bpRoot.setAlignment(topLabel,Pos.CENTER);
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

	public static void main(String args[]) {
		launch(args);
	}
}