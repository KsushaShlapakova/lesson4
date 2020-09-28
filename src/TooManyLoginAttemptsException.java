public class TooManyLoginAttemptsException extends Exception{
    private String message;

    TooManyLoginAttemptsException(String message){
        super();
        this.message = message;
    }

    TooManyLoginAttemptsException(){
        super();
        this.message = "Превышен лимит ввода данных!";
    }

    public String getMessage(){
        return message;
    }
}
