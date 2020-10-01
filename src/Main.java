import java.io.IOException;

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

       System.out.println("");

       // Удаление аккаунта
        try {
            manager.removeAccount("email", "1234");
            manager.removeAccount("email", "password");
            //Удаляю акк с неверными данными
            manager.removeAccount("eil", "passord");
        }catch (WrongCredentialsException | IOException e){
            System.out.println(e.getMessage());
        }

        System.out.println("");

        //Проверка на наличие данного человека
        try {
            System.out.println("Данный аккаунт существует/не существует (true/false): " + manager.hasAccount("el"));
        }catch(IOException e){
            e.printStackTrace();
        }

        System.out.println("");

        //Количество аккаунтов
        try {
            System.out.println("Количество аккаунтов: " + manager.numOfAccounts());
        }catch (IOException e){
            e.printStackTrace();
        }

        System.out.println("");

        //Попытки авторизоваться
        authorizationPerson("em", "12", manager);
        authorizationPerson("emal", "124", manager);
        authorizationPerson("email", "123", manager);
        authorizationPerson("email", "2345", manager);
        System.out.println("");
        authorizationPerson("emal", "1234", manager);
        authorizationPerson("email", "134", manager);
        authorizationPerson("il", "4", manager);
        authorizationPerson("", "", manager);
        authorizationPerson("em", "134", manager);
        authorizationPerson("em", "", manager);

    }
    public static void authorizationPerson(String email, String password, AccountManagerImpl manager) {
        try {
            System.out.println(manager.getPerson(email, password));
        }catch (WrongCredentialsException | TooManyLoginAttemptsException | IOException e){
            System.out.println(e.getMessage());
        }
    }

}
