import java.io.*;
import java.util.Scanner;

public class NotesApp {

    private static final String FILE_NAME = "notes.txt";

    // Method to write a note to the file
    public static void writeNote() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter your note:");
        String note = sc.nextLine();

        System.out.println("Do you want to append (A) or overwrite (O) the file?");
        String choice = sc.nextLine().trim().toUpperCase();

        boolean append = choice.equals("A");

        // Using try-with-resources for automatic resource management
        try (FileWriter writer = new FileWriter(FILE_NAME, append)) {
            writer.write(note + "\n");
            System.out.println("Note saved successfully!");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
            e.printStackTrace(); // shows stack trace
        }
    }

    // Method to read all notes from the file
    public static void readNotes() {
        System.out.println("\n Saved Notes:");
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            boolean empty = true;

            while ((line = reader.readLine()) != null) {
                System.out.println("- " + line);
                empty = false;
            }

            if (empty) {
                System.out.println("(No notes found yet.)");
            }

        } catch (FileNotFoundException e) {
            System.out.println("No notes file found. Add a note first!");
        } catch (IOException e) {
            System.out.println(" Error reading file: " + e.getMessage());
        }
    }

    // Main menu
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        System.out.println("Welcome to Notes Manager App");
        System.out.println("-------------------------------");

        do {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add Note");
            System.out.println("2. View Notes");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1:
                        writeNote();
                        break;
                    case 2:
                        readNotes();
                        break;
                    case 3:
                        System.out.println("Exiting... Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice! Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                choice = 0; // reset choice to continue loop
            }

        } while (choice != 3);

        sc.close();
    }
}
