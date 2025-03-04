import java.util.Scanner;

class AddressBook {

    
    static class Entry {
        String fullName;
        String contactNumber;
        String emailAddress;
        Entry nextEntry;

        public Entry(String fullName, String contactNumber, String emailAddress) {
            this.fullName = fullName;
            this.contactNumber = contactNumber;
            this.emailAddress = emailAddress;
            this.nextEntry = null;
        }

        public String toString() {
            return "Full Name: " + fullName + "\nContact Number: " + contactNumber + "\nEmail Address: " + emailAddress;
        }
    }

    
    private Entry start = null;
    private Entry end = null;

    
    public void addEntry(String fullName, String contactNumber, String emailAddress) {
        Entry newEntry = new Entry(fullName, contactNumber, emailAddress);
        if (start == null) {
            start = newEntry;
        } else {
            end.nextEntry = newEntry;
        }
        end = newEntry;
        System.out.println("Entry successfully added for " + newEntry.fullName);
    }

    
    public void removeEntry(String fullName) {
        if (start == null) {
            System.out.println("No entries to remove.");
            return;
        }
        if (start.fullName.equals(fullName)) {
            start = start.nextEntry;
            System.out.println("Removed entry for " + fullName);
            return;
        }
        Entry previous = start;
        Entry current = start.nextEntry;
        while (current != null && !current.fullName.equals(fullName)) {
            previous = current;
            current = current.nextEntry;
        }
        if (current != null) {
            previous.nextEntry = current.nextEntry;
            if (current == end) {
                end = previous;
            }
            System.out.println("Removed entry for " + fullName);
        } else {
            System.out.println("No entry found for " + fullName);
        }
    }

    
    public String lookupByEmail(String emailAddress) {
        if (start == null) {
            return "Address book is empty.";
        }
        Entry current = start;
        while (current != null && !current.emailAddress.equals(emailAddress)) {
            current = current.nextEntry;
        }
        if (current != null) {
            return "Full Name: " + current.fullName + "\nContact Number: " + current.contactNumber;
        } else {
            return "No entry found with the provided email.";
        }
    }

    
    public void displayEntries() {
        if (start == null) {
            System.out.println("The Address Book is currently empty.");
            return;
        }
        System.out.println("********** Address Book Entries **********");
        Entry current = start;
        while (current != null) {
            System.out.println(current.fullName);
            current = current.nextEntry;
        }
        System.out.println();
    }

    
    public String lookupByName(String fullName) {
        if (start == null) {
            return "The Address Book is empty.";
        }
        Entry current = start;
        while (current != null && !current.fullName.equals(fullName)) {
            current = current.nextEntry;
        }
        if (current != null) {
            return current.toString();
        } else {
            return "Entry not found.";
        }
    }

    
    public static void main(String[] args) {
        AddressBook myBook = new AddressBook();
        Scanner scanner = new Scanner(System.in);
        boolean active = true;

        while (active) {
            System.out.print(
                    """
                    **********************************************
                    (A)dd Entry
                    (R)remove Entry
                    (L)lookup by Email
                    (D)display Entries
                    (S)search by Name
                    (Q)uit
                    **********************************************
                    Enter a command:
                    """
            );

            String command = scanner.next().toUpperCase();

            switch (command) {
                case "A" -> {
                    System.out.print("Enter Full Name: ");
                    String name = scanner.next();
                    System.out.print("Enter Contact Number: ");
                    String number = scanner.next();
                    System.out.print("Enter Email Address: ");
                    String email = scanner.next();
                    myBook.addEntry(name, number, email);
                    System.out.println();
                }
                case "R" -> {
                    System.out.print("Enter the Full Name to remove: ");
                    String nameToRemove = scanner.next();
                    myBook.removeEntry(nameToRemove);
                    System.out.println();
                }
                case "L" -> {
                    System.out.print("Enter Email Address to search: ");
                    String emailToSearch = scanner.next();
                    System.out.println(myBook.lookupByEmail(emailToSearch));
                    System.out.println();
                }
                case "D" -> myBook.displayEntries();
                case "S" -> {
                    System.out.print("Enter Full Name to search: ");
                    String nameToSearch = scanner.next();
                    System.out.println(myBook.lookupByName(nameToSearch));
                    System.out.println();
                }
                case "Q" -> {
                    System.out.println("Exiting the Address Book...");
                    active = false;
                }
                default -> System.out.println("Invalid command. Please try again.\n");
            }
        }
        scanner.close();
    }
}
