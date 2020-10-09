
import controller.systemManagerController;
import javafx.application.Application;
import javafx.stage.Stage;
import submittd1.SystemManager;
import view.View;

public class Program extends Application{
		
	public static void main(String args[]) {
			launch(args);
		}


	@Override
	public void start(Stage primaryStage) throws Exception {
		SystemManager theModel = new SystemManager();
		View theView = new View(primaryStage);
		systemManagerController controller = new systemManagerController(theModel, theView);
				
	}
}
