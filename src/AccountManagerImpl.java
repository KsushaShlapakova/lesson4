import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AccountManagerImpl implements MailAccountManager {

    AttemptCounter counter = AttemptCounter.getInstance();

    @Override
    public void registerNewAccount(String email, String password, Person person) throws DuplicateAccountException, IOException {
        try(BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Ksusha\\IdeaProjects\\lesson4\\src\\persons.csv"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\Ksusha\\IdeaProjects\\lesson4\\src\\persons.csv", true));){
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(person.getSurname())) {
                    throw new DuplicateAccountException("Аккаунт уже существует!");
                }
            }
            writer.write(person.toString() + ", " + email + ", " + password + "\n");
        }

    }

    @Override
    public void removeAccount(String email, String password) throws WrongCredentialsException, IOException {
        BufferedWriter writer = null;
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Ksusha\\IdeaProjects\\lesson4\\src\\persons.csv"));){
            List<String> persons = new ArrayList<String>();

            String line;
            String control = null;

            while ((line = reader.readLine()) != null) {
                persons.add(line);
            }

            for (String i:persons) {
                if (i.split(", ")[2].equals(email) && i.split(", ")[3].equals(password)) {
                    control = i;
                }
            }

            persons.remove(control);

            writer = new BufferedWriter(new FileWriter("C:\\Users\\Ksusha\\IdeaProjects\\lesson4\\src\\persons.csv"));
            for (String x: persons){
                writer.write(x);
                writer.newLine();

            }

            if (control == null){
                throw new WrongCredentialsException("Введены неверные данные!");
            }
            System.out.println("Аккаунт успешно удален!");
        } finally {
            if(writer != null) {
                writer.close();
            }
        }
    }

    @Override
    public boolean hasAccount(String email) throws IOException {
        boolean control = false;
        try(BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Ksusha\\IdeaProjects\\lesson4\\src\\persons.csv"));){
            String line;
            while ((line = reader.readLine()) != null){
                if (line.split(", ")[2].equals(email)){
                    control = true;
                }
            }
        }
        return control;
    }

    @Override
    public Person getPerson(String email, String password) throws TooManyLoginAttemptsException, IOException, WrongCredentialsException {
        Person person = null;
        try(BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Ksusha\\IdeaProjects\\lesson4\\src\\persons.csv"));){
            String line;
            while ((line = reader.readLine()) != null){
                if (line.split(", ")[2].equals(email) && line.split(", ")[3].equals(password)){
                    person = new Person(line.split(", ")[0], line.split(", ")[1]);
                    counter.setCount(0);
                }
            }
            if (person == null){
                System.out.println(counter.Counter());
                throw new WrongCredentialsException("Введены неверные данные!");
            }
        }
        return person;
    }

    @Override
    public int numOfAccounts() throws IOException {
        int lines = 0;
        try(BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Ksusha\\IdeaProjects\\lesson4\\src\\persons.csv"));){
            while (reader.readLine() != null){
                lines++;
            }

        }
        return lines;
    }
}
