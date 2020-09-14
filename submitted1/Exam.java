package submitted1;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Exam {
    
	private Vector<Question> allQuestions = new Vector<Question>();
    private int numOfCurrentQuestion = allQuestions.size();
    

	private int numOfQuestion;
    Scanner s = new Scanner(System.in);

    public Exam(int numOfQuestion){
        this.numOfQuestion = numOfQuestion;
    }
   

//    public Boolean pickRandomQuestions(int numOfQuestion){
//


//        Question randomQuestion = questions.keySet().
//        if(Map.Entry<Question,Answer> e)
//
//
//        return
//    }

    public boolean addQuestion(String questionText) {
        allQuestions.add(new Question(questionText));
        return true;
    }
    
    public Vector<Question> getAllQuestions() {
		return allQuestions;
	}

    public String toString(){
        StringBuilder sb = new StringBuilder();

        for (int i=0 ; i<allQuestions.size() ; i ++ ){
            sb.append(allQuestions.get(i).toString() + "\n");
        }
        return  sb.toString();
    }

}
