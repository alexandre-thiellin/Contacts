package contacts;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneBook implements Serializable {

    private static final long serialVersionUID = 1L;

    private final ArrayList<Record> records = new ArrayList<>();

    private final transient Scanner scanner = new Scanner(System.in);

    private String fileName = "";

    public PhoneBook() {

    }

    public PhoneBook(String fileName) {

        this.fileName = fileName;

        try {
            PhoneBook phoneBook = (PhoneBook) SerializationUtils.deserialize(fileName);
            records.addAll(phoneBook.records);
        } catch (Exception e) {
            try {
                SerializationUtils.serialize(this, fileName);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    public void run() {

        boolean finished = false;

        Scanner scanner = new Scanner(System.in);
        String action;

        while(!finished) {

            System.out.print("[menu] Enter action (add, list, search, count, exit): ");
            action = scanner.nextLine();

            switch (action) {
                case "add": add();
                    break;
                case "list": list();
                    break;
                case "search": search();
                    break;
                case "count": count();
                    break;
                case "exit": finished = true;
                    break;
                default:
                    break;
            }
            System.out.println();
        }
        close();
    }

    public void add() {

        System.out.print("Enter the type (person, organization): ");
        String type = scanner.nextLine();
        switch (type) {
            case "person":
                Record person = new Person();
                records.add(person);
                break;
            case "organization":
                Record organization = new Organization();
                records.add(organization);
                break;
            default:
                break;
        }
        System.out.println("The record added.");
        if(!fileName.equals("")) {
            try {
                SerializationUtils.serialize(this, fileName);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    public void edit(int index) {

        if(records.get(index) == null) {
            System.out.println("No record to edit!");
        } else {
            records.get(index).edit();
            if(!fileName.equals("")) {
                try {
                    SerializationUtils.serialize(this, fileName);
                    System.out.println("Saved");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            System.out.print(records.get(index));
        }
    }

    public void count() {

        System.out.println("The Phone Book has "+records.size()+" records.");
    }

    public void list() {

        System.out.print(this);
        System.out.println();
        System.out.print("[list] Enter action ([number], back): ");
        try {
            int index = Integer.parseInt(scanner.nextLine()) - 1;
            System.out.print(records.get(index));
            record(index);
        } catch (NumberFormatException ignored) {
        }
    }

    public void search() {

        query();

        boolean finished = false;

        while(!finished) {
            System.out.print("[search] Enter action ([number], back, again): ");
            String action = scanner.nextLine();
            try {
                int index = Integer.parseInt(action) - 1;
                System.out.println(records.get(index));
                record(index);
                finished = true;
            } catch (NumberFormatException e) {
                switch (action) {
                    case "back": finished = true;
                        break;
                    case "again": query();
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public void query() {

        System.out.print("Enter search query: ");
        String searchQuery = scanner.nextLine();

        Pattern pattern = Pattern.compile(".*"+searchQuery+".*", Pattern.CASE_INSENSITIVE);
        Matcher matcherId;
        Matcher matcherPhone;

        ArrayList<Record> matched = new ArrayList<>();

        for (Record r : records) {
            matcherId = pattern.matcher(r.getIdentifier());
            matcherPhone = pattern.matcher(r.getPhoneNumber());
            if(matcherId.matches() || matcherPhone.matches()) {
                matched.add(r);
            }
        }

        System.out.println("Found "+matched.size()+" results:");
        System.out.println(recordsToString(matched));
    }

    public void record(int index) {

        boolean finished = false;

        while(!finished) {
            System.out.println();
            System.out.print("[record] Enter action (edit, delete, menu): ");
            String action = scanner.nextLine();
            switch (action) {
                case "edit": edit(index);
                    break;
                case "delete": records.remove(index); finished = true;
                    break;
                case "menu": finished = true;
                    break;
                default:
                    break;
            }
        }
    }

    public void close() {

        for (Record r : records) {
            r.close();
        }
        scanner.close();
    }

    public String recordsToString(ArrayList<Record> records) {

        StringBuilder stringBuilder = new StringBuilder();
        int i = 1;

        for (Record  r: records) {
            stringBuilder.append(i).append(". ").append(r.getIdentifier()).append("\n");
            i++;
        }

        return stringBuilder.toString();
    }

    @Override
    public String toString() {

        return recordsToString(records);
    }
}
