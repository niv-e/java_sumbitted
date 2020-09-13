package exceptions;

public class MaxQuestionsException extends  Exception{
    public MaxQuestionsException(String msg){
        super(msg);
    }
    public MaxQuestionsException(){
        super("More question then the maximum possible were entered ");
    }


}
