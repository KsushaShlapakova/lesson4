import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AccountManagerImpl implements MailAccountManager {

    AttemptCounter counter = AttemptCounter.getInstance();

    public List<String> readingFile() throws IOException {
        List<String> lines = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Ksusha\\IdeaProjects\\lesson4\\src\\persons.csv"))){
            String line;
            while ((line = reader.readLine()) != null){
                lines.add(line);
            }
        }
        return lines;
    }

    public void writingFile(List<String> persons, boolean append) throws IOException {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\Ksusha\\IdeaProjects\\lesson4\\src\\persons.csv", append))){
            for (String person : persons) {
                writer.write(person);
                writer.newLine();
            }
        }
    }

    @Override
    public void registerNewAccount(String email, String password, Person person) throws DuplicateAccountException, IOException {
        String[] registerPerson = new String[]{person.toString() + ", " + email + ", " + password};
        for (String line : readingFile()){
            if (line.split(", ")[0].equals(person.getSurname())) {
                throw new DuplicateAccountException("Аккаунт уже существует!");
            }
        }

        writingFile(Arrays.asList(registerPerson), true);

    }

    @Override
    public void removeAccount(String email, String password) throws WrongCredentialsException, IOException {
        List<String> persons = readingFile();
        String control = null;

        for (String i:persons) {
            if (i.split(", ")[2].equals(email) && i.split(", ")[3].equals(password)) {
                control = i;
            }
        }

        persons.remove(control);

        writingFile(persons, false);


        if (control == null){
            throw new WrongCredentialsException("Введены неверные данные!");
        }

        System.out.println("Аккаунт " + control.split(", ")[0] + " успешно удален!");
    }

    @Override
    public boolean hasAccount(String email) throws IOException {
        boolean control = false;
        List<String> persons = readingFile();
        for (String person : persons){
            if (person.split(", ")[2].equals(email)) {
                control = true;
                break;
            }
        }

        return control;
    }

    @Override
    public Person getPerson(String email, String password) throws TooManyLoginAttemptsException, IOException, WrongCredentialsException {
        Person person = null;
        List<String> persons = readingFile();
        for (String account : persons){
            if (account.split(", ")[2].equals(email) && account.split(", ")[3].equals(password)){
                person = new Person(account.split(", ")[0], account.split(", ")[1]);
                counter.setCount(0);
            }
        }
        if (person == null){
            throw new WrongCredentialsException("Введены неверные данные! Попытка: " + counter.Counter());

        }
        return person;
    }

    @Override
    public int numOfAccounts() throws IOException {
        return readingFile().size();
    }
}
