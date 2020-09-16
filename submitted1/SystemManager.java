package Daniel_Niazov;

import exceptions.MaxAnswerException;
import exceptions.noEnoughAnswers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Clock;
import java.time.LocalDate;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class SystemManager implements able {
   
	private final int MAX_NUM_OF_QUESTIONS = 10;
	private int numOfCurrentQuestion;
    private Exam exam;
    private Vector<Question> systemAllQuestions = new Vector<Question>();
    LocalDate now = LocalDate.now(Clock.systemDefaultZone());



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
        systemAllQuestions.get(numOfQuestion-1).setQuestionText(questionText);
        return true;
    }

    public void deleteQuestion(int questionForDelete) {
        systemAllQuestions.remove(questionForDelete - 1);
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

    public void addNewAnswer(int numOfQuestion,String answerText , boolean isRight) throws MaxAnswerException {
        Answer ans = new Answer(answerText,isRight);
        systemAllQuestions.get(numOfQuestion-1).addNewAnswer(ans);
    }

    public void addNewOpenAnswer(int numOfQuestion,String answerText) throws MaxAnswerException {
        OpenAnswer ans = new OpenAnswer(answerText);
        systemAllQuestions.get(numOfQuestion-1).addNewAnswer(ans);
    }

    public boolean updateAnswerText(int numOfQuestion,int numOfAnswer , String answerText){
        boolean currentAnswerRes = getIfTheRightAnswer(numOfQuestion, numOfAnswer);
        systemAllQuestions.get(numOfQuestion - 1).getAllAnswers().get(numOfAnswer - 1).setAnswerText(answerText);
        systemAllQuestions.get(numOfQuestion - 1).getAllAnswers().get(numOfAnswer - 1).setIsTheAnswer(currentAnswerRes);

        return true;
    }

    public boolean deleteAnswer(int numOfQuestion, int numOfAnswer){
        systemAllQuestions.get(numOfQuestion - 1).getAllAnswers().remove(numOfAnswer - 1);
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

    public void saveQuestionToFile() throws IOException {
        String questionFileName = "questions_list_ "+ now + ".txt";
        File questionFile = new File(questionFileName);
        questionFile.createNewFile();
        PrintWriter pw = new PrintWriter(questionFile);
        pw.println(systemAllQuestions.size());

        for(int i = 0; i< systemAllQuestions.size() ; i++){
            pw.println(systemAllQuestions.get(i).getQuestionText());
            pw.println(systemAllQuestions.get(i).getAllAnswers().size());
            for(int j = 0; j< systemAllQuestions.get(i).getAllAnswers().size() ; j++){
                pw.println(systemAllQuestions.get(i).getAllAnswers().get(j).getAnswerText());
                if(!(systemAllQuestions.get(i).getAllAnswers().get(j) instanceof OpenAnswer)) {
                    pw.println(systemAllQuestions.get(i).getAllAnswers().get(j).isTheAnswer());
                }
            }
        }
        pw.close();
    }

    public void loadQuestionFromFile(String filePath) throws FileNotFoundException, MaxAnswerException {
        File loadedFile = new File(filePath);
        System.out.println("is file exist? " + loadedFile.exists());
    	Scanner sf = new Scanner(loadedFile);
        int numOfQuestionInFile = sf.nextInt();
        sf.nextLine();
        int numOfAnswerOfCurrentQuestion;

        for(int i=0 ; i< numOfQuestionInFile ; i++){
            Question q = new Question(sf.nextLine());
            numOfAnswerOfCurrentQuestion = sf.nextInt();
            sf.nextLine();
            for(int j = 0 ; j< numOfAnswerOfCurrentQuestion ; j++){
                String currentAnswerText = sf.nextLine();
                boolean currentAnswerResults = sf.nextBoolean();
                sf.nextLine();
                Answer a = new Answer(currentAnswerText,currentAnswerResults);
                q.addNewAnswer(a);
            }
            if (!(systemAllQuestions.contains(q))) {//check if the question is already exists on the system
                systemAllQuestions.add(q);
            }
        }
    }
/*
    public void getLoadSaveQuestionsProtocol(){
        System.out.println("first line in file is the number of question on file");
        System.out.println("for each question below save first the Question text");
        System.out.println("line after the number of answers of the question");
        System.out.println("line after the answer text ");
        System.out.println("line after the true of false ");
        System.out.println("Example: ");
        System.out.println("2 (number of question on this file)");
        System.out.println("how old you are? (question one text)");
        System.out.println("2 (number of answers) ");
        System.out.println("18 (answer one) ");
        System.out.println("false ()");
        System.out.println("25 (answer two) ");
        System.out.println("true ()");
        System.out.println("are you student? (question two text)");
        System.out.println("2 (number of answers) ");
        System.out.println("yes (answer one) ");
        System.out.println("true ()");
        System.out.println("no (answer two) ");
        System.out.println("false ()");

    }
*/
    public int getRandomInRange(int min ,int max){
    	if(min >= max) {
    		throw new IllegalArgumentException("min grader than max ");
    	}
        int range = max-min;
        int randomInRange = (int)(Math.random()*(range) * min);
        return randomInRange;
    }

    public Exam pickRandomQuestions(int numOfQuestion) throws CloneNotSupportedException, noEnoughAnswers, MaxAnswerException {

        final int MIN = 1;
        int max = systemAllQuestions.size();
        int numberOfAnswerToAdd = 4;
        int randomQuestionNumber;
        Exam e = new Exam(numOfQuestion);
        int haveEnoughAnswers = 0;

        for(int counter = 0; counter < numOfQuestion ;){
        	
            randomQuestionNumber = getRandomInRange(MIN ,max);
            String questionText = systemAllQuestions.get(randomQuestionNumber).getQuestionText();
            Question q = new Question(questionText);
            int numOfRandomQuestionAnswers = systemAllQuestions.get(randomQuestionNumber).getAllAnswers().size();

            if(numOfRandomQuestionAnswers >= numberOfAnswerToAdd) {
                haveEnoughAnswers += 1;
            }else {
                numberOfAnswerToAdd = numOfRandomQuestionAnswers;
            }

            for(int i = 0; i < numberOfAnswerToAdd; i++) {
                int randomAnswer = getRandomInRange(1,numOfRandomQuestionAnswers);
                Answer a = systemAllQuestions.get(randomQuestionNumber).getAllAnswers().get(randomAnswer).clone();
                e.getAllQuestions().lastElement().addNewAnswer(a);
            }
            
            if(!(e.getAllQuestions().contains(q))){          	
               e.addQuestion(q);
               counter++;
                
            }
            numberOfAnswerToAdd = 4;
            

        }
        if(haveEnoughAnswers < numberOfAnswerToAdd) {
        	throw new noEnoughAnswers(numberOfAnswerToAdd - haveEnoughAnswers);
        }

        return e;
    }

    public boolean checkIfAnotherTrueAnswer(int numOfQues ,Answer answer) throws Exception {
		for(int i = 0; i < systemAllQuestions.get(numOfQues).getAllAnswers().size(); i++) {
			if(systemAllQuestions.get(numOfQues).getAllAnswers().get(i).getIfTheRightAnswer() == answer.getIfTheRightAnswer()) {
				throw new Exception("There is more than one true answer ");
			}	
		}
		return true;
	}


    public boolean getIfTheRightAnswer(int numOfQuestion, int numOfAnswer){
        return systemAllQuestions.get(numOfQuestion-1).getAllAnswers().get(numOfAnswer-1).isTheAnswer();
    }

    public Vector<Question> getSystemAllQuestions() {
		return systemAllQuestions;
	}

    public int getNumOfCurrentQuestion(){
        return systemAllQuestions.size();
    }

    @Override
    public String toString(){
        if(systemAllQuestions.size() == 0)
            return "there is not question on the system yet \n";
        else {
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < systemAllQuestions.size() ; i++) {
                sb.append("Question " + (i+1) + " --> \n" );
                sb.append(systemAllQuestions.get(i).toString() + "\n");
            }
            return sb.toString();
        }
    }
}
