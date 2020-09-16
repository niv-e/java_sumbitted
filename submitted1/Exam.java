package submitted1;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Clock;
import java.time.LocalDate;
import java.util.*;

public class Exam {
    
	private Vector<Question> allQuestions = new Vector<Question>();
    private int numOfCurrentQuestion = allQuestions.size();
	private int numOfQuestion;
    LocalDate now = LocalDate.now(Clock.systemDefaultZone());


    public Exam(int numOfQuestion){
        this.numOfQuestion = numOfQuestion;
    }

    public void addQuestion(Question question){
        allQuestions.add(question);
    }
    
    public Vector<Question> getAllQuestions() {
		return allQuestions;
	}

	public void saveExam() throws IOException {
        String questionFileName = "Exam " + now + ".txt";
        File examFile = new File(questionFileName);
        examFile.createNewFile();
        PrintWriter pw = new PrintWriter(examFile);
        pw.println(allQuestions.size());

        for(int i = 0; i< allQuestions.size() ; i++){
            pw.println(allQuestions.get(i).getQuestionText());
            pw.println(allQuestions.get(i).getAllAnswers().size());
            for(int j = 0; j< allQuestions.get(i).getAllAnswers().size() ; j++){
                pw.println(allQuestions.get(i).getAllAnswers().get(j).getAnswerText());
                if(!(allQuestions.get(i).getAllAnswers().get(j) instanceof OpenAnswer)) {
                    pw.println(allQuestions.get(i).getAllAnswers().get(j).isTheAnswer());
                }
            }
        }
        pw.close();
    }

    public void saveExamSolutions() throws IOException {
        String solutionsFileName =  "Solution " + now + ".txt";
        File solutionsFile = new File(solutionsFileName);
        solutionsFile.createNewFile();
        PrintWriter pw = new PrintWriter(solutionsFile);
        for(int i = 0 ; i <allQuestions.size() ; i++){
            for(Answer a : allQuestions.get(i).getAllAnswers()){
                pw.println(a);

            }
        }
        pw.close();
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i< allQuestions.size() ; i ++ ){
            sb.append((i+1) + " . " + allQuestions.get(i).toString() + "\n");
        }
        return  sb.toString();
    }

}
