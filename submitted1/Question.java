package submitted1;

import exceptions.MaxAnswerException;

import java.util.*;

public class Question implements Cloneable {

	private final int MAX_NUMBER_OF_ANSWER = 10;
	private String questionText;
	private Vector<Answer> allAnswers = new Vector<Answer>();
	private int numOfCurrentAnswers = allAnswers.size();
	private boolean rightAnswerExist = false;

	Scanner s = new Scanner(System.in);

	public Question(String questionText) {
		setQuestionText(questionText);
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public String getQuestionText() {
		return questionText;
	}
	
	public Vector<Answer> getAllAnswers() {
		return allAnswers;
	}

	public void addNewAnswer(Answer ans) throws MaxAnswerException {
		if(allAnswers.size()>MAX_NUMBER_OF_ANSWER)
			throw new MaxAnswerException();
		allAnswers.add(ans);
	}

	public void addStandardAnswer(String answerText, boolean isRight){
		allAnswers.add(new Answer(answerText,isRight));
	}


	public void deleteAnswer(int answerForDelete) {
		if(allAnswers.get(answerForDelete).isTheAnswer())
			rightAnswerExist = false;
		allAnswers.remove(answerForDelete);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append( questionText + "\n");
		for (int i = 0; i < allAnswers.size() ; i++)
			sb.append("Answer " + (i + 1) + " is: " + allAnswers.get(i).toString() + " \n");
		return sb.toString();
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	@Override
	public boolean equals(Object other){
		if(!(other instanceof Question))
			return false;
		Question q = (Question)other;
		return q.questionText.equalsIgnoreCase(questionText);
	}

	public class CompareByQuestionText implements Comparator<Question>{
		@Override
		public int compare(Question q1, Question q2) {
			if(q1.questionText.equalsIgnoreCase(q2.questionText))
				return -1;
			else
				return 1;
		}
	}


}


