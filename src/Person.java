public class Person {
    private String surname;
    private String bday;

    public Person(String surname, String bday){
        this.surname = surname;
        this.bday = bday;
    }

    public String getSurname(){
        return surname;
    }

    public String getBday(){
        return bday;
    }

    public String toString(){
        return surname + ", " + bday;
    }
}
