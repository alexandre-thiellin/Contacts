package contacts;

import java.time.LocalDateTime;

public class Organization extends Record {


    private String organizationName;
    private String address;

    public Organization() {

        System.out.print("Enter the organization name: ");
        organizationName = getScanner().nextLine();

        System.out.print("Enter the address: ");
        address = getScanner().nextLine();

        System.out.print("Enter the number: ");
        String phoneNumber = getScanner().nextLine();
        if(checkPhoneNumber(phoneNumber)) {
            this.setPhoneNumber(phoneNumber);
        } else {
            System.out.println("Wrong number format!");
        }
    }

    @Override
    public void edit() {

        System.out.print("Select a field (organizationName, address, number): ");
        String action = getScanner().nextLine();

        switch (action) {
            case "organizationName":
                System.out.print("Enter the organization name: ");
                organizationName = getScanner().nextLine();
                break;
            case "address":
                System.out.print("Enter the address: ");
                address = getScanner().nextLine();
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

        return organizationName;
    }

    public String getOrganizationName() {

        return organizationName;
    }

    @Override
    public String toString() {

        return "Organization name: " + organizationName + "\n" +
                "Address: " + address + "\n" +
                "Number: " + getPhoneNumber() + "\n" +
                "Time created: " + getCreated() + "\n" +
                "Time last edit: " + getEdited() + "\n";
    }
}
