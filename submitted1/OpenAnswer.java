package Daniel_Niazov;

public class OpenAnswer extends Answer {
	private boolean isTrue = true;
	
    public OpenAnswer (String answerText){
        super(answerText);
        this.isTrue = isTrue;
    }

	public boolean isTrue() {
		return isTrue;
	}
}
