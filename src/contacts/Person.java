package contacts;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Person extends Record {

    private String name;
    private String surname;
    private String birthDate = "";
    private String gender = "";

    public Person() {

        System.out.print("Enter the name: ");
        name = getScanner().nextLine();

        System.out.print("Enter the surname: ");
        surname = getScanner().nextLine();

        System.out.print("Enter the birth date: ");
        String birthDate = getScanner().nextLine();
        if(checkBirthDate(birthDate)) {
            this.birthDate = birthDate;
        } else {
            System.out.println("Bad birth date!");
        }

        System.out.print("Enter the gender (M, F): ");
        String gender = getScanner().nextLine();
        if(checkGender(gender)) {
            this.gender = gender;
        } else {
            System.out.println("Bad gender!");
        }

        System.out.print("Enter the number: ");
        String phoneNumber = getScanner().nextLine();
        if(checkPhoneNumber(phoneNumber)) {
            this.setPhoneNumber(phoneNumber);
        } else {
            System.out.println("Wrong number format!");
        }
    }

    private boolean checkBirthDate(String birthDate) {

        try {
            LocalDateTime localDateTime = LocalDateTime.parse(birthDate);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private boolean checkGender(String gender) {

        Pattern pattern = Pattern.compile("M|F");

        Matcher matcher = pattern.matcher(gender);

        return matcher.matches();
    }

    @Override
    public void edit() {

        System.out.print("Select a field (name, surname, birth, gender, number): ");
        String action = getScanner().nextLine();

        switch (action) {
            case "name":
                System.out.print("Enter the name: ");
                name = getScanner().nextLine();
                break;
            case "surname":
                System.out.print("Enter the surname: ");
                surname = getScanner().nextLine();
                break;
            case "birth":
                System.out.print("Enter the birth date: ");
                String birthDate = getScanner().nextLine();
                setBirthDate(birthDate);
                break;
            case "gender":
                System.out.print("Enter the gender: ");
                String gender = getScanner().nextLine();
                setGender(gender);
                break;
            case "number":
                System.out.print("Enter the number: ");
                String phoneNumber = getScanner().nextLine();
                setPhoneNumber(phoneNumber);
                break;
            default:
                break;
        }
        setEdited(LocalDateTime.now());
        System.out.println("The record updated!");
    }

    @Override
    public String getIdentifier() {

        return name+" "+surname;
    }

    public String getName() {

        return name;
    }

    public String getSurname() {

        return surname;
    }

    public void setBirthDate(String birthDate) {

        if(checkBirthDate(birthDate)) {
            this.birthDate = birthDate;
        } else {
            this.birthDate = "";
            System.out.println("Bad birth date!");
        }
    }

    public void setGender(String gender) {

        if(checkGender(gender)) {
            this.gender = gender;
        } else {
            this.gender = "";
            System.out.println("Bad gender!");
        }
    }

    @Override
    public String toString() {

        return "Name: " + name + "\n" +
                "Surname: " + surname + "\n" +
                "Birth date: " + (birthDate.equals("") ? "[no data]" : birthDate) + "\n" +
                "Gender: " + (gender.equals("") ? "[no data]" : gender) + "\n" +
                "Number: " + (getPhoneNumber().equals("") ? "[no number]" : getPhoneNumber()) + "\n" +
                "Time created: " + getCreated() + "\n" +
                "Time last edit: " + getEdited() + "\n";
    }
}
