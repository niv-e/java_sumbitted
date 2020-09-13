package submitted1;
import exceptions.MaxAnswerException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.Clock;
import java.time.LocalDate;
import java.util.InputMismatchException;

import java.util.Scanner;

public class main {

	public static void main(String[] args) throws FileNotFoundException, CloneNotSupportedException {
		Scanner s = new Scanner(System.in);
		int choice =-1; //menu choice
		boolean fcountinue = true;


		SystemManager systemManager = new SystemManager();

		do {
			System.out.println("choose one of the following options: ");
			System.out.println("press 1 to show all Questions and Answers of this exam");
			System.out.println("press 2 to add new Question to the system");
			System.out.println("press 3 to add new answer exist question");
			System.out.println("press 4 to Update wording of question");
			System.out.println("press 5 to Update wording of answer");
			System.out.println("press 6 to Delete answer to question");
			System.out.println("press 7 to Delete question (with answers)");
			System.out.println("press 9 to to move to handle write mood");
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
						break;
					}

					case 3: { //add new answer to exist question
						System.out.println("please enter the question number for add new answer: ");
						//s.nextLine(); // clean the buffer

						int questionChoice = s.nextInt();

						boolean flag = true;
						while (flag) {
							s.nextLine(); // clean the buffer
							System.out.println("Please enter the answer text");
							String answerText = s.nextLine();
							System.out.println("this is the right answer? (enter true or false)");

							boolean isTheRightAnswer = s.nextBoolean();

							systemManager.addNewAnswer(questionChoice, answerText, isTheRightAnswer);

							System.out.println("would you like to add another answer? ");
							flag = checkIfToDo();
						}
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
						break;
					}

					case 6: {//press 6 to Delete answer to question
						System.out.println("To which question you would like to delete an answer ?");
						int questionChoice = s.nextInt();
						System.out.println("which answer you would like to delete?");
						int answerChoice = s.nextInt();
						systemManager.deleteAnswer(questionChoice, answerChoice);
						break;
					}

					case 7: {
						System.out.println("which question you would like to delete?");
						int questionChoice = s.nextInt();

						systemManager.deleteQuestion(questionChoice);
					}
					break;

					case 9: {

						System.out.println("how many question you would like to add to the exam?");
						int numOfQuestionRes = s.nextInt();
						System.out.println("Please enter the number of the question that you would like to add:");
						System.out.println("press 0 to stop the adding ");
						System.out.println(systemManager.toString());
						int questionChoice = s.nextInt();
						Exam e = systemManager.handleCreateExam(numOfQuestionRes);

						do {
							systemManager.addQuestionToExam(e,questionChoice);
							systemManager.showAnswerForSelectedQuestion(questionChoice);

							System.out.println("select the answer number that you wont to add (press 0 to end selecting ");
							int numOfAnswerToAdd = s.nextInt();
							while (numOfAnswerToAdd != 0) {
								systemManager.addAnswerToLastQuestion(e, questionChoice, numOfAnswerToAdd);
								System.out.println("new answer was added, waiting for next answer");
								numOfAnswerToAdd = s.nextInt();

							}
							String standardAnswer1 = "none of the above";
							boolean standard_1_isRight = false;
							String standardAnswer2 = "More than one answer correctly";
							boolean standard_2_isRight = false;

							e.allQuestions.lastElement().addStandardAnswer(standardAnswer1, standard_1_isRight);
							e.allQuestions.lastElement().addStandardAnswer(standardAnswer2, standard_2_isRight);

							System.out.println("Waiting for the next question / exit");
							questionChoice = s.nextInt();
						} while(questionChoice != 0);

//						System.out.println("db: Start the saving proses");
//						System.out.println("db: printing the exam object" + e);

						LocalDate now = LocalDate.now(Clock.systemDefaultZone());
						String fileName = "exam" + now +".txt";
						File file = new File(fileName);
						PrintWriter pw = new PrintWriter(file);

						System.out.println(e);

						pw.println(e.toString());

						pw.close();
						System.out.println("A new exam file was create");

						break;
					}

					case 8 : {
						SystemManager m = new SystemManager();
						Answer a1 = new Answer("answer1" , true);
						Answer a2 = new Answer("answer2" , true);
						m.addNewQuestionToSystem("question number 1");
						m.systemAllQuestions.lastElement().addNewAnswer(a1);
						m.systemAllQuestions.lastElement().addNewAnswer(a2);
						System.out.println(m);





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
				s.nextInt();
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



