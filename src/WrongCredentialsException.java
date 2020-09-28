public class WrongCredentialsException extends Exception{
    private String message;

    WrongCredentialsException(String message){
        super();
        this.message = message;
    }

    WrongCredentialsException(){
        super();
        this.message = "Введены неверные данные";
    }

    public String getMessage(){
        return message;
    }
}
