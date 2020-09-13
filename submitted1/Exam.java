package submitted1;

import java.util.*;

public class Exam {
    Vector<Question> allQuestions = new Vector <Question>();
    int numOfCurrentQuestion=allQuestions.size();
    int numOfQuestion;
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

    public String toString(){
        StringBuilder sb = new StringBuilder();

        for (int i=0 ; i<allQuestions.size() ; i ++ ){
            sb.append(allQuestions.get(i).toString() + "\n");
        }
        return  sb.toString();
    }

}
