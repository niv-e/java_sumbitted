package submitted1;

import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class check {
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
//		System.out.println("enter num");
//		int num = s.nextInt();
//		System.out.println("enter name");
//		String name = s.nextLine();
//		
//		System.out.println(name);
		
//		Vector<Question> allQuestions = new Vector<Question>();
//		String q = "who is david? ";
//		OpenQuestion oq = new OpenQuestion(q);
//		System.out.println(oq);
//		allQuestions.add(oq);
//		System.out.println(allQuestions.toString());
		
		
		
			int min = 1, max = 5;
	        Random r = new Random();
	        int randomInRange = r.nextInt(max - min);
	        System.out.println(randomInRange);
}	    
}	

