import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){

        //Создаю людей
        Person mike = new Person("Smith", "01.02.1998");
        Person john = new Person("Lark", "01.02.1990");
        Person edward = new Person("Cullen", "01.02.1899");
        Person bella = new Person("Swan", "01.02.1997");

        //Создаю менеджера
        AccountManagerImpl manager = new AccountManagerImpl();

       //Записываю в файл новых людей
       try {
           manager.registerNewAccount("email", "password", mike);
           manager.registerNewAccount("email", "1234", john);
           manager.registerNewAccount("email", "2345", edward);
           manager.registerNewAccount("email", "2345678", bella);
           //Записываю уже существующего человека, должна появиться ошибка
           manager.registerNewAccount("email", "password", mike);
       }catch(DuplicateAccountException | IOException e){
           System.out.println(e.getMessage());
       }

       // //Удаление аккаунта
        try {
            manager.removeAccount("email", "1234");
            manager.removeAccount("email", "password");
            //Удаляю акк с неверными данными
            manager.removeAccount("eil", "passord");
        }catch (WrongCredentialsException | IOException e){
            System.out.println(e.getMessage());
        }

        //Проверка на наличие данного человека
        try {
            System.out.println("Данный аккаунт существует/не существует (true/false): " + manager.hasAccount("el"));
        }catch(IOException e){
            e.printStackTrace();
        }

        //Количество аккаунтов
        try {
            System.out.println("Количество аккаунтов: " + manager.numOfAccounts());
        }catch (IOException e){
            e.printStackTrace();
        }

        //Попытки авторизоваться
        authorizationPerson("em", "12", manager);

    }
    public static void authorizationPerson(String email, String password, AccountManagerImpl manager) {
        try {
            System.out.println(manager.getPerson(email, password));
        }catch (WrongCredentialsException | TooManyLoginAttemptsException | IOException e){
            System.out.println(e.getMessage());
        }
    }

}
