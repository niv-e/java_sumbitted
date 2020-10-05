package Daniel_Niazov;
//work with niv eliahu
//207437997
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

	public void saveExam() throws IOException {
        LocalDate now = LocalDate.now(Clock.systemDefaultZone());
        String questionFileName = "Random_exam "+ now + ".txt";
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


    public String toString(){
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i< allQuestions.size() ; i ++ ){
            sb.append((i+1) + " . " + allQuestions.get(i).toString() + "\n");
        }
        return  sb.toString();
    }

}
