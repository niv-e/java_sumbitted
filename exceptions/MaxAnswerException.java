package exceptions;
//work with niv eliahu
//207437997
public class MaxAnswerException extends  Exception{
    public MaxAnswerException(){
        super("More answers then the maximum possible were entered ");
    }
}
