package contacts;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Record implements Serializable {

    private static final long serialVersionUID = 1L;

    private String phoneNumber = "";
    private final LocalDateTime created = LocalDateTime.now();
    private LocalDateTime edited = LocalDateTime.now();

    private final Scanner scanner = new Scanner(System.in);

    public abstract void edit();

    public abstract String getIdentifier();

    protected boolean checkPhoneNumber(String phoneNumber){

        Pattern pattern = Pattern.compile("(\\+?\\(?\\w+\\)?)?[ -]?(\\w{2,})?[ -]?(\\w{2,})?[ -]?(\\w{2,})?|(\\+?\\w+)?[ -]?(\\(?\\w{2,}\\)?)?[ -]?(\\w{2,})?[ -]?(\\w{2,})?[ -]?(\\w{2,})?");

        Matcher matcher = pattern.matcher(phoneNumber);

        return matcher.matches();
    }

    public void close(){

        scanner.close();
    }

    public String getPhoneNumber() {

        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {

        if(checkPhoneNumber(phoneNumber)) {
            this.phoneNumber = phoneNumber;
        } else {
            this.phoneNumber = "";
            System.out.println("Wrong number format!");
        }
    }

    public Scanner getScanner() {

        return scanner;
    }

    public LocalDateTime getCreated() {

        return created;
    }

    public LocalDateTime getEdited() {

        return edited;
    }

    public void setEdited(LocalDateTime edited) {

        this.edited = edited;
    }
}
