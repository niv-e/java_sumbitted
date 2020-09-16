package submitted1;
import exceptions.MaxAnswerException;
import exceptions.noQuestionsInTheList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.Clock;
import java.time.LocalDate;
import java.util.InputMismatchException;

import java.util.Scanner;

public class main {

	public static void main(String[] args) throws FileNotFoundException, CloneNotSupportedException, MaxAnswerException {
		Scanner s = new Scanner(System.in);
		int choice =-1; //menu choice
		boolean fcountinue = true;
		LocalDate now = LocalDate.now(Clock.systemDefaultZone());



		SystemManager systemManager = new SystemManager();
		//load question and answers from file
		String fileNamePath = "C:\\Java\\project1\\java_sumbitted\\questions_list_ 2020-09-16.txt"; // hey user, change here to your computer path.
		systemManager.loadQuestionFromFile(fileNamePath);
		System.out.println("all questions from the system loaded succesfully \n");

		do {
			System.out.println("choose one of the following options: ");
			System.out.println("press 1 to show all Questions and Answers of this exam");
			System.out.println("press 2 to add new Question to the system");
			System.out.println("press 3 to add new answer exist question");
			System.out.println("press 4 to Update wording of question");
			System.out.println("press 5 to Update wording of answer");
			System.out.println("press 6 to Delete answer to question");
			System.out.println("press 7 to Delete question (with answers)");
			System.out.println("press 8 to to move to handle write mood");
			System.out.println("press 9 to to save all Questions and answers that you have entered");
			System.out.println("press 10 to create exam from random questions ");
			
			System.out.println("press 0 to Exit");
			System.out.println("enter your choice-->");

			try {
				choice = s.nextInt();

				switch (choice) { //show all Questions and Answers on System manager
					case 1: {
						System.out.print(systemManager.toString());
						break;
					}

					case 2: { //add a new question
						s.nextLine(); // clean the buffer
						System.out.println("Please enter question text: ");
						String questionText = s.nextLine();
						systemManager.addNewQuestionToSystem(questionText);
						System.out.println("If you finish to add questions, press 9 to save your questions in the system!");
						break;
					}

					case 3: { //add new answer to exist question
						System.out.println(systemManager.getSystemAllQuestions().toString());//view the data
						System.out.println("please enter the question number for add new answer: \n");
						
						int questionChoice = s.nextInt();

						boolean flag = true;
						while (flag) {
							s.nextLine(); // clean the buffer
							System.out.println("Please enter the answer text");
							String answerText = s.nextLine();
							
							System.out.println("this is the right answer? (enter true or false)");
							boolean isTheRightAnswer = s.nextBoolean();
							
							Answer ans = new Answer(answerText, isTheRightAnswer);
							if(isTheRightAnswer = true) {
								systemManager.checkIfAnotherTrueAnswer(questionChoice, ans);
							}

							systemManager.addNewAnswer(questionChoice, answerText, isTheRightAnswer);

							System.out.println("would you like to add another answer? ");
							flag = checkIfToDo();
						}
						System.out.println("If you finish to add answers, press 9 to save your answers in the system!");
						break;
					}

					case 4: {//press 4 to Update wording of question
						System.out.println("please enter the question number you want to change ");
						s.nextLine(); // clean the buffer
						int questionChoice = s.nextInt() ;
						System.out.println("Please enter the new question: ");
						s.nextLine(); // clean the buffer
						String questionText = s.nextLine();

						systemManager.updateQuestion(questionChoice, questionText);
						System.out.println("press 9 to save your changes ");
						break;
					}

					case 5: {//Update wording of answer
						System.out.println("To which question you would like to change the answer?");
						int questionChoice = s.nextInt();

						System.out.println("which answer you would like to change?");
						int answerChoice = s.nextInt();
						s.nextLine(); // clean the buffer
						System.out.println("Please enter the new answer: ");
						String answerText = s.nextLine();

						systemManager.updateAnswerText(questionChoice, answerChoice, answerText);
						System.out.println("press 9 to save your changes ");
						break;
					}

					case 6: {//press 6 to Delete answer to question
						System.out.println("To which question you would like to delete an answer ?");
						int questionChoice = s.nextInt();
						System.out.println("which answer you would like to delete?");
						int answerChoice = s.nextInt();
						systemManager.deleteAnswer(questionChoice, answerChoice);
						System.out.println("press 9 to save your changes ");
						break;
					}

					case 7: {
						System.out.println("which question you would like to delete?");
						int questionChoice = s.nextInt();

						systemManager.deleteQuestion(questionChoice);
						System.out.println("press 9 to save your changes ");
						break;
					}
					

					case 8: {
							int counter2 = 0;
							System.out.println("how many questions you would like to add to the exam? (without open questions) ");
							int numOfQuestionRes = s.nextInt();
							System.out.println("how many open questions you would like to add? ");
							int numOfOpenQuestionRes = s.nextInt();
							System.out.println(systemManager.toString());
							Exam e = systemManager.handleCreateExam(numOfQuestionRes+numOfOpenQuestionRes);
						
						do {
							System.out.println("Please enter the number of the question that you would like to add  :");
							System.out.println("press 0 to stop the adding ");
							int questionChoice = s.nextInt();
							

							int counter;
						
							systemManager.addQuestionToExam(e,questionChoice);
							System.out.println("select how many answers you want to add (press 0 to end selecting) ");
							
							int numOfTotalAnswers = s.nextInt();
							
							int counter1 = 0;
							systemManager.showAnswerForSelectedQuestion(questionChoice);
							while (counter1 < numOfTotalAnswers) {
								System.out.println("Enter the num of answer: ");
								int numOfAnswerToAdd = s.nextInt();
								systemManager.addAnswerToLastQuestion(e, questionChoice, numOfAnswerToAdd);	
								counter1++;
							}
							String standardAnswer1 = "none of the above";
							boolean standard_1_isRight = false;
							String standardAnswer2 = "More than one answer correctly";
							boolean standard_2_isRight = false;

							e.getAllQuestions().lastElement().addStandardAnswer(standardAnswer1, standard_1_isRight);
							e.getAllQuestions().lastElement().addStandardAnswer(standardAnswer2, standard_2_isRight);

							counter2++;
						} while(counter2 < numOfQuestionRes);
						

						System.out.println("Please enter the open question text");
						for (int i = 0 ; i < numOfOpenQuestionRes ; i ++ ) {
							System.out.println("open question number " + (i+1) + " of " + numOfOpenQuestionRes);
							String oq = s.next();							
							OpenQuestion q = new OpenQuestion(oq);
							System.out.println("q value: " + q);
							e.getAllQuestions().add(q);
							System.out.println(e.getAllQuestions().toString());
						}


						String fileName1 = "exam" + now +".txt";
						File file1 = new File(fileName1);
						file1.createNewFile();
						PrintWriter pw1 = new PrintWriter(file1);
						
						String fileName2 = "Answers" + now + ".txt";
						File file2 = new File(fileName2);
						file2.createNewFile();
						PrintWriter pw2 = new PrintWriter(file2);

						System.out.println(e);
						pw1.println(e.toString());
						
						for(int i = 0; i < numOfQuestionRes; i++) {
							pw2.println(e.getAllQuestions().get(i).getAllAnswers().toString());
						}

						pw1.close();
						pw2.close();
						System.out.println("A new exam file was create");
						System.out.println("A new answers file was create");
						break;
					}

					case 9: { // save all question and answer from System to file
						String questionFileName = "questions_list_ "+ now + ".txt";
						systemManager.saveQuestionToFile();
						System.out.println("A new file named: "+ questionFileName + " was create!, and you have there some questions..");
						break;
					}
					
					case 10: {
						if(systemManager.getSystemAllQuestions().size() == 0) {
							throw new noQuestionsInTheList();
						}
						System.out.println("How many questions you want in the random exam? ");
						int randomQuestions = s.nextInt();
						Exam e = systemManager.pickRandomQuestions(randomQuestions);
						System.out.println(e);
						break;
					}

					case 0: {
						fcountinue = false;
						break;
					}

					default:
						System.out.println("Invalid option");
						break;
				}
				System.out.println();

			} catch (InputMismatchException e) {
				System.out.println("Input Mismatch exception ");
			}
			catch (MaxAnswerException e){
				System.out.println(e.getMessage());
			}
			catch (Exception e){
				System.out.println(e.getMessage());
			}
		}while (choice != 0) ;
		System.out.println("Goodbye!");
		s.close();
	}

		public static boolean checkIfToDo(){
		Scanner s = new Scanner(System.in);
		System.out.println("enter y for yes / any key if no");
		char c = s.next().charAt(0);
		boolean flag = (c == 'y' || c == 'Y') ? true : false;
		return flag;
	}
}



