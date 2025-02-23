package submittd1;
//work with niv eliahu
//207437997

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Clock;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

import exceptions.MaxAnswerException;
import exceptions.noEnoughAnswers;
import listeners.SysManEventListener;

public class SystemManager implements able {
   
	private final int MAX_NUM_OF_QUESTIONS = 10;
	private int numOfCurrentQuestion;
    private Exam exam;
    private Vector<Question> systemAllQuestions = new Vector<Question>();
    LocalDate now = LocalDate.now(Clock.systemDefaultZone());

    private ArrayList<SysManEventListener> allListeners;

    public SystemManager() {
        exam = new Exam(MAX_NUM_OF_QUESTIONS);
        allListeners = new ArrayList<SysManEventListener>();
    }
    
    public void registerListener(SysManEventListener l) {
		allListeners.add(l);
	}

    public boolean addNewQuestionToExam(String questionText){
        exam.getAllQuestions().add(new Question(questionText));
        return true;
    }

    public boolean addNewQuestionToSystem(String questionText){
        systemAllQuestions.add(new Question(questionText));
        for (SysManEventListener l : allListeners) {
        	l.addNewQuestionToModelEvent(questionText.toString());
        }
        return true;
    }

    public boolean updateQuestion(int numOfQuestion , String questionText) throws ArrayIndexOutOfBoundsException{
        systemAllQuestions.get(numOfQuestion - 1).setQuestionText(questionText);
        FireupdatingQuestion(numOfQuestion - 1, questionText);
        return true;
    }
    
    public void FireupdatingQuestion(int numOfQuestion , String questionText) {
    	for (SysManEventListener l : allListeners) {
    		l.updateQuestionToModelEvent(numOfQuestion, questionText);
    	}
    }

    public void deleteQuestion(int questionForDelete) {
        systemAllQuestions.remove(questionForDelete - 1);
        numOfCurrentQuestion--;
        for (SysManEventListener l : allListeners) {
    		l.deleteQuestionFromModelEvent(questionForDelete - 1);
    	}
    }


    public Exam handleCreateExam(int numOfQuestion){
       Exam exam = new Exam(numOfQuestion);
       FireCreateHandExam(numOfQuestion);
       return exam;
    }
    
    public void FireCreateHandExam(int numOfQuestion) {
    	for (SysManEventListener l : allListeners) {
    		l.handleCreateExamFromModelEvent(numOfQuestion);
    	}
    }

    public boolean addQuestionToExam(Exam e ,int questionNumber)  {
        questionNumber-=1;
        String textToCopy = systemAllQuestions.get(questionNumber).getQuestionText();
        Question q = new Question(textToCopy);
        e.getAllQuestions().add(q);
        return true;
    }

    public void addNewAnswer(int numOfQuestion, String answerText , boolean isRight) throws MaxAnswerException {
        Answer ans = new Answer(answerText,isRight);
        systemAllQuestions.get(numOfQuestion - 1).addNewAnswer(ans);
        for (SysManEventListener l : allListeners) {
    		l.addNewAnswerToModelEvent(numOfQuestion - 1, answerText, isRight);
    	}
    }

    public void addNewOpenAnswer(int numOfQuestion,String answerText) throws MaxAnswerException {
        OpenAnswer ans = new OpenAnswer(answerText);
        systemAllQuestions.get(numOfQuestion-1).addNewAnswer(ans);
    }

    public boolean updateAnswerText(int numOfQuestion,int numOfAnswer , String answerText){
        boolean currentAnswerRes = getIfTheRightAnswer(numOfQuestion, numOfAnswer);
        systemAllQuestions.get(numOfQuestion - 1).getAllAnswers().get(numOfAnswer - 1).setAnswerText(answerText);
        systemAllQuestions.get(numOfQuestion - 1).getAllAnswers().get(numOfAnswer - 1).setIsTheAnswer(currentAnswerRes);
        for (SysManEventListener l : allListeners) {
    		l.updateAnswerTextToModelEvent(numOfQuestion - 1, numOfAnswer, answerText);
    	}
        return true;
    }

    public boolean deleteAnswer(int numOfQuestion, int numOfAnswer){
        systemAllQuestions.get(numOfQuestion - 1).getAllAnswers().remove(numOfAnswer - 1);
        for (SysManEventListener l : allListeners) {
    		l.deleteAnswerFromModelEvent(numOfQuestion - 1, numOfAnswer);
    	}
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

    public boolean addAnswerToLastQuestion(Exam exam, int numOfQuestionFromSystem, int numOfAnswerToAdd) throws CloneNotSupportedException, MaxAnswerException {
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

    public void loadQuestionFromFile(String filePath) throws MaxAnswerException, FileNotFoundException{
        File loadedFile = new File(filePath);
        System.out.println("is file exist? " + loadedFile.exists());
        Scanner sf = new Scanner(loadedFile);
    	try{
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
			 
			}for (SysManEventListener l : allListeners) {
			    l.loadQuestionFromFileToModelEvent(filePath);
			}
    	}catch(Exception e) {
    		System.out.println(e.getMessage());
    	}
    }
    
    public int getRandomInRange(int min ,int max){
    	if(min >= max) {
    		throw new IllegalArgumentException("min grader than max ");
    	}
        int range = max-min;
        Random rand = new Random();
        return rand.nextInt(range + 1);
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

            if(!(e.getAllQuestions().contains(q))){
                e.addQuestion(q);
                counter++;
            }

            int numOfRandomQuestionAnswers = systemAllQuestions.get(randomQuestionNumber).getAllAnswers().size();

            if(numOfRandomQuestionAnswers > numberOfAnswerToAdd) {
                haveEnoughAnswers++;

                for(int i = 0; i < numberOfAnswerToAdd;) {
                    int randomAnswer = getRandomInRange(1, numOfRandomQuestionAnswers);
                    Answer a = systemAllQuestions.get(randomQuestionNumber).getAllAnswers().get(randomAnswer).clone();

                    if (!(e.getAllQuestions().lastElement().getAllAnswers().contains(a))) {
                        e.getAllQuestions().lastElement().addNewAnswer(a);
                        i++;
                    }
                }

            }else {
                if(numOfRandomQuestionAnswers == numberOfAnswerToAdd){
                    haveEnoughAnswers ++;
                }
                numberOfAnswerToAdd = numOfRandomQuestionAnswers;
                for( Answer a : systemAllQuestions.get(randomQuestionNumber).getAllAnswers() ) {
                    e.getAllQuestions().lastElement().addNewAnswer(a.clone());
                }
            }

        }
   
        return e;
        }
    
    public Exam createExamFromRandomQuestions() {
    	final int min = 1;
    	int max = systemAllQuestions.size();
    	int numOfQuestions = getRandomInRange(min, max);
    	Exam exam = new Exam(numOfQuestions);
    	for (SysManEventListener l : allListeners) {
    		l.createExamFromRandomQuestionsFromModelEvent(numOfQuestions);
    	}
    	return exam;
    }
        

    public boolean checkIfAnotherTrueAnswer(int numOfQues, Answer answer) throws Exception {
		for(int i = 0; i < systemAllQuestions.get(numOfQues - 1).getAllAnswers().size(); i++) {
			if(systemAllQuestions.get(numOfQues - 1).getAllAnswers().get(i).getIfTheRightAnswer() == answer.getIfTheRightAnswer()) {
				throw new Exception("There is more than one true answer ");
			}	
		}
		return true;
	}

    public boolean getIfTheRightAnswer(int numOfQuestion, int numOfAnswer){
        return systemAllQuestions.get(numOfQuestion - 1).getAllAnswers().get(numOfAnswer - 1).isTheAnswer();
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

        if(systemAllQuestions.size()==0)
            return "there is not question on the system yet \n"; // change to exception

        else {
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < systemAllQuestions.size() ; i++) {
                sb.append("Question " + (i+1) + " --> \n" );
                sb.append(systemAllQuestions.get(i).toString() + "\n");
            }
            for (SysManEventListener l : allListeners) {
//        		l.showAllQuestionsAndAnswersFromModelEvent();
        	}
            return sb.toString();
        }
    }
}
