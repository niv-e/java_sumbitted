import submitted1.Answer;

import java.util.Vector;

public class check {
    public static void main(String[] args) {

        Vector<Answer> answers = new Vector<Answer>();
        Answer a = new Answer("answer1 ", true);
        Answer b = new Answer("answer2 ", false);

        answers.add(a);
        answers.add(b);
//
//        for (int i = 0; i < answers.size(); i++) {
//            System.out.println("get " + (i+1) + answers.get(i));
//            System.out.println("index of " + (i+1) + answers.indexOf(i));
//        }

        System.out.println("index of 0: " + answers.indexOf(0));
    }
}
