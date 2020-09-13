package submitted1;

import exceptions.MaxAnswerException;

import java.util.Vector;

public class SystemManager {
    int numOfQuestion=10;
    int numOfCurrentQuestion;
    Exam exam;
    Vector <Question> systemAllQuestions = new Vector <Question>();


    public SystemManager(){
        exam = new Exam(numOfQuestion);
    }

    public boolean addNewQuestionToExam(String questionText){
        exam.allQuestions.add(new Question(questionText));
        return true;
    }

    public boolean addNewQuestionToSystem(String questionText){
        systemAllQuestions.add(new Question(questionText));
        return true;
    }


    public boolean updateQuestion(int numOfQuestion , String questionText) throws ArrayIndexOutOfBoundsException{
        exam.allQuestions.get(numOfQuestion-1).setQuestionText(questionText);
        return true;
    }

    public void deleteQuestion(int questionForDelete) {
        questionForDelete-=1;
        exam.allQuestions.remove(questionForDelete);
        numOfCurrentQuestion--;
    }


    public Exam handleCreateExam(int numOfQuestion){
       Exam exam = new Exam(numOfQuestion);
        return exam;
    }

    public boolean addQuestionToExam(Exam e ,int questionNumber)  {
        questionNumber-=1;
        String textToCopy = systemAllQuestions.get(questionNumber).questionText;
        Question q = new Question(textToCopy);
        e.allQuestions.add(q);
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
        exam.allQuestions.get(numOfQuestion).allAnswers.get(numOfAnswer).setAnswerText(answerText, currentAnswerRes);
        return true;
    }

    public boolean deleteAnswer(int numOfQuestion, int numOfAnswer){
        numOfQuestion-=1;
        numOfAnswer-=1;
        exam.allQuestions.get(numOfQuestion).allAnswers.remove(numOfAnswer);
        return true;
    }

    public void showAnswerForSelectedQuestion(int numOfQuestion){
        numOfQuestion-=1;
        Vector <Answer> tempAnswerList = systemAllQuestions.get(numOfQuestion).allAnswers;
        for (int i=0; i<tempAnswerList.size() ; i++){
            System.out.println("Answer number " + (i+1) + ":\n"
                + tempAnswerList.get(i).toString());
        }
    }

    public boolean addAnswerToLastQuestion(Exam exam ,int numOfQuestionFromSystem,int numOfAnswerToAdd) throws CloneNotSupportedException, MaxAnswerException {
        numOfQuestionFromSystem-=1;
        numOfAnswerToAdd-=1;

        exam.allQuestions.lastElement().addNewAnswer(systemAllQuestions.get(numOfQuestionFromSystem).allAnswers
            .get(numOfAnswerToAdd));
        return true;

    }

    public boolean getIfTheRightAnswer(int numOfQuestion, int numOfAnswer){
        return exam.allQuestions.get(numOfQuestion-1).allAnswers.get(numOfAnswer-1).isTheAnswer;
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
