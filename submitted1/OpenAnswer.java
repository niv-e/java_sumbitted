package Daniel_Niazov;
//work with niv eliahu
//207437997
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
