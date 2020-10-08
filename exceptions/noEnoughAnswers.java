package exceptions;
//work with niv eliahu
//207437997
public class noEnoughAnswers extends Exception {
	public noEnoughAnswers(int missingAnswers) {
		super("You have " + missingAnswers + " question with not enough answers \n");
	}
	
}
