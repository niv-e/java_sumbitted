package submitted1;

import exceptions.MaxAnswerException;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Vector;

public class SystemManager {
   
	private final int MAX_NUM_OF_QUESTIONS = 10;
	private int numOfCurrentQuestion;
    private Exam exam;
    private Vector<Question> systemAllQuestions = new Vector<Question>();


    public SystemManager(){
        exam = new Exam(MAX_NUM_OF_QUESTIONS);
    }

    public boolean addNewQuestionToExam(String questionText){
        exam.getAllQuestions().add(new Question(questionText));
        return true;
    }

    public boolean addNewQuestionToSystem(String questionText){
        systemAllQuestions.add(new Question(questionText));
        return true;
    }


    public boolean updateQuestion(int numOfQuestion , String questionText) throws ArrayIndexOutOfBoundsException{
        exam.getAllQuestions().get(numOfQuestion-1).setQuestionText(questionText);
        return true;
    }

    public void deleteQuestion(int questionForDelete) {
        questionForDelete -= 1;
        exam.getAllQuestions().remove(questionForDelete);
        numOfCurrentQuestion--;
    }


    public Exam handleCreateExam(int numOfQuestion){
       Exam exam = new Exam(numOfQuestion);
        return exam;
    }

    public boolean addQuestionToExam(Exam e ,int questionNumber)  {
        questionNumber-=1;
        String textToCopy = systemAllQuestions.get(questionNumber).getQuestionText();
        Question q = new Question(textToCopy);
        e.getAllQuestions().add(q);
        return true;
    }

    public boolean addNewAnswer(int numOfQuestion,String answerText , boolean isRight) throws MaxAnswerException {
        Answer ans = new Answer(answerText,isRight);
        systemAllQuestions.get(numOfQuestion-1).addNewAnswer(ans);
        return true;
    }

    public boolean updateAnswerText(int numOfQuestion,int numOfAnswer , String answerText){
        numOfQuestion-=1;
        numOfAnswer-=1;
        boolean currentAnswerRes = getIfTheRightAnswer(numOfQuestion,numOfAnswer);
        exam.getAllQuestions().get(numOfQuestion).getAllAnswers().get(numOfAnswer).setAnswerText(answerText, currentAnswerRes);
        return true;
    }

    public boolean deleteAnswer(int numOfQuestion, int numOfAnswer){
        numOfQuestion-=1;
        numOfAnswer-=1;
        exam.getAllQuestions().get(numOfQuestion).getAllAnswers().remove(numOfAnswer);
        return true;
    }

    public void showAnswerForSelectedQuestion(int numOfQuestion){
        numOfQuestion-=1;
        Vector <Answer> tempAnswerList = systemAllQuestions.get(numOfQuestion).getAllAnswers();
        for (int i=0; i<tempAnswerList.size() ; i++){
            System.out.println("Answer number " + (i+1) + ":\n"
                + tempAnswerList.get(i).toString());
        }
    }

    public boolean addAnswerToLastQuestion(Exam exam ,int numOfQuestionFromSystem,int numOfAnswerToAdd) throws CloneNotSupportedException, MaxAnswerException {
        numOfQuestionFromSystem-=1;
        numOfAnswerToAdd-=1;

        exam.getAllQuestions().lastElement().addNewAnswer(systemAllQuestions.get(numOfQuestionFromSystem).getAllAnswers()
            .get(numOfAnswerToAdd));
        return true;
    }
    
    public void saveQuestions(PrintWriter pw) throws FileNotFoundException {
		for(int i = 0; i < systemAllQuestions.size(); i++) {
			pw.println(systemAllQuestions.get(i));
			pw.println(systemAllQuestions.get(i).getAllAnswers());
		}	
	}

    public boolean getIfTheRightAnswer(int numOfQuestion, int numOfAnswer){
        return exam.getAllQuestions().get(numOfQuestion-1).getAllAnswers().get(numOfAnswer-1).isTheAnswer();
    }

    public Vector<Question> getSystemAllQuestions() {
		return systemAllQuestions;
	}

    public int getNumOfCurrentQuestion(){
        return systemAllQuestions.size();
    }

    @Override
    public String toString(){
        if(systemAllQuestions.size()==0)
            return "there is not question on the system yet \n";
        else {
            StringBuilder sb = new StringBuilder();
            for(int i =0 ; i< systemAllQuestions.size() ; i++) {
                sb.append("Question " + (i+1) + ": \n" );
                sb.append(systemAllQuestions.get(i).toString() + "\n");
            }
            return sb.toString();
        }
    }
}
