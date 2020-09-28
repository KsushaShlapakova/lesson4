public class DuplicateAccountException extends Exception {
    private String message;

    DuplicateAccountException(String message){
        super();
        this.message = message;
    }

    DuplicateAccountException() {
        super();
        this.message = "Аккаунт уже существует!";
    }

    public String getMessage(){
        return message;
    }
}


