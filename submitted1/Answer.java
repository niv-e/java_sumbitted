package submitted1;
import exceptions.AnswerNotFoundException;

import java.util.Comparator;
import java.util.Scanner;

public class Answer  implements Cloneable  {

	private String answerText;
	private boolean isTheAnswer;

	Scanner s = new Scanner(System.in);

	public Answer(String answerText) {
		setAnswerText(answerText);
	}

	public Answer(String answerText, boolean isTheAnswer) {
		setAnswerText(answerText);
		setIsTheAnswer(isTheAnswer);
	}

	public String getAnswerText(){
		return answerText;
	}

	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}
	
	public void setIsTheAnswer(boolean isTheAnswer){
		this.isTheAnswer = isTheAnswer;
	}
	
	public boolean isTheAnswer() {
		return isTheAnswer;
	}

	public boolean getIfTheRightAnswer() {
		return isTheAnswer;
	}

	public String toString() {
		return (answerText + "\t " + isTheAnswer);
	}

	@Override
	public boolean equals(Object other){
		if(!(other instanceof Answer))
			return false;
		Answer a = (Answer)other;
		return a.answerText.equalsIgnoreCase(answerText);
	}

	public class CompareByAnswerText implements Comparator<Answer> {
		@Override
		public int compare(Answer w1, Answer w2) {
			if (w1.answerText.equalsIgnoreCase(w2.answerText)) {
				return -1;
			} else return 1;
		}
	}

	@Override
	public Answer clone() throws CloneNotSupportedException {
		return (Answer) super.clone();
	}
	
}




