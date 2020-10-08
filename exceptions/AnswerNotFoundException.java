package exceptions;
//work with niv eliahu
//207437997

public class AnswerNotFoundException extends Exception{
    public AnswerNotFoundException(){
        super("The selected answer for not found please try again");
    }
}
