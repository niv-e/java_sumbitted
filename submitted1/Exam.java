package Daniel_Niazov;

import java.util.*;

public class Exam {
    
	private Vector<Question> allQuestions = new Vector<Question>();
    private int numOfCurrentQuestion = allQuestions.size();
	private int numOfQuestion;
	private OpenQuestion openQuestion;


    Scanner s = new Scanner(System.in);

    public Exam(int numOfQuestion){
        this.numOfQuestion = numOfQuestion;
    }

    public void addOpenQuestion(String questionText){
        openQuestion = new OpenQuestion(questionText);
    }

    public boolean addQuestion(String questionText) {
        allQuestions.add(new Question(questionText));
        return true;
    }

    public void addQuestion(Question question){
        allQuestions.add(question);
    }
    
    public Vector<Question> getAllQuestions() {
		return allQuestions;
	}

    public String toString(){
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i< allQuestions.size() ; i ++ ){
            sb.append((i+1) + " . " + allQuestions.get(i).toString() + "\n");
        }
        return  sb.toString();
    }

}
