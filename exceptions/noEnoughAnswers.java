package exceptions;

public class noEnoughAnswers extends Exception {
	public noEnoughAnswers(int missingAnswers) {
		super("You have " + missingAnswers + "question with not enough answers ");
	}
	
}
