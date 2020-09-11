package contacts;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        PhoneBook phoneBook;
        if(args.length != 0) {
            phoneBook = new PhoneBook(args[0]);
        } else {
            phoneBook = new PhoneBook();
        }
        phoneBook.run();
    }
}
