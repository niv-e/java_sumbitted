package submitted1;

import exceptions.MaxAnswerException;

import java.util.*;

public class Question implements Cloneable {

	final int MAX_NUMBER_OF_ANSWER = 10;
	String questionText;
	Vector <Answer> allAnswers = new Vector <Answer>();
	int numOfCurrentAnswers =allAnswers.size();
	boolean rightAnswerExist = false;


	Scanner s = new Scanner(System.in);

	public Question(String questionText) {
		numOfCurrentAnswers = 0;
		setQuestionText(questionText);
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void addNewAnswer(Answer ans) throws MaxAnswerException {
		if(allAnswers.size()>MAX_NUMBER_OF_ANSWER)
			throw new MaxAnswerException();
		allAnswers.add(ans);
	}


	public void addNewAnswer(String answerText , boolean isRight) throws MaxAnswerException {
		if(allAnswers.size()>MAX_NUMBER_OF_ANSWER)
			throw new MaxAnswerException();
		allAnswers.add(new Answer(answerText,isRight));
	}

	public void copyAnswer(Answer answer) throws CloneNotSupportedException {
		allAnswers.add(answer.clone());
	}

	public void addStandardAnswer(String answerText, boolean isRight){
		allAnswers.add(new Answer(answerText,isRight));
	}


	public void deleteAnswer(int answerForDelete) {
		if(allAnswers.get(answerForDelete).isTheAnswer)
			rightAnswerExist=false;
		allAnswers.remove(answerForDelete);
	}

	public boolean checkIfRightAnswerExist() {
		return rightAnswerExist;
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append(questionText + ":\n");
		for (int i = 0; i < allAnswers.size() ; i++)
			sb.append("the " + (i + 1) + " answer is: " + allAnswers.get(i).toString() +"\n");
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
			else return 1;
		}
	}


}


