package submitted1;

public class OpenAnswer extends Answer {
	private boolean isTrue = true;
	
    public OpenAnswer (String answerText){
        super(answerText);
    }

	public boolean isTrue() {
		return isTrue;
	}
}
