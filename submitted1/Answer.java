package submitted1;
import exceptions.AnswerNotFoundException;

import java.util.Comparator;
import java.util.Scanner;

public class Answer  implements Cloneable  {

	String answerText;
	boolean isTheAnswer;
	int answerNumber;

	Scanner s = new Scanner(System.in);

	public Answer(String answerText, boolean isTheAnswer) {
		setAnswerText(answerText, isTheAnswer);
		this.answerNumber = answerNumber;
	}

	public void setAnswerText(String answerText, boolean isTheAnswer) {
		this.answerText = answerText;
		this.isTheAnswer = isTheAnswer;
	}


	public boolean getIfTheRightAnswer() {
		return isTheAnswer;
	}

	public String toString() {
		return (answerText + "\t\t" + isTheAnswer);
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

	@Override
	public int hashCode(){
		return answerNumber;
	}
}




