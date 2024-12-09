import java.util.Scanner;

public class Main {

    private static String[][] userDetails = new String[100][4];
    //Create book
    private static String[][] books = new String[100][4];                // 3 pieces of information for each book: title, author, year
    private static int bookCount = 0;                                   // Counter to track the number of books
    // Static variable to auto-increment book ID
    public static int nextID = 1;
    private static String currentUser = "";
    private static boolean isAdmin = false;

    private static int userCount = 0; // Counter to track the number of registered users

    static Scanner scanner = new Scanner(System.in);

    private static void userRegistration() {
        // Sign up
        System.out.println("If you have already signed up, please click here to login.");
        System.out.println("Sign up");

        System.out.println("Enter your name: ");
        userDetails[userCount][0] = scanner.nextLine();

        System.out.println("Enter your surname: ");
        userDetails[userCount][1] = scanner.nextLine();

        System.out.println("Enter your email: ");
        userDetails[userCount][2] = scanner.nextLine();

        System.out.println("Enter your password: ");
        userDetails[userCount][3] = scanner.nextLine();

        System.out.println("You registered successfully.");
        userCount++; // Increment the counter for each new user
    }

    private static boolean login() {
        // Login
        System.out.println("\nLogin");

        boolean isLogin = false;
        String loggedInUserEmail = "";

        // Continue to prompt for email and password until login is successful
        while (!isLogin) {
            System.out.println("\nEnter your email: ");
            String loginEmail = scanner.nextLine();

            System.out.println("Enter your password: ");
            String loginPassword = scanner.nextLine();

            // Check credentials against all registered users
            for (int i = 0; i < userCount; i++) {
                if (loginEmail.equals(userDetails[i][2]) && loginPassword.equals(userDetails[i][3])) {
                    System.out.println("Login successful! Welcome " + userDetails[i][0] + " " + userDetails[i][1]);
                    loggedInUserEmail = loginEmail;
                    isLogin = true;
                    break;
                }
            }

            if (!isLogin) {
                System.out.println("Login failed. Email or password is incorrect. Please try again.");
            }
        }

        if (isAdmin(loggedInUserEmail)) {
            System.out.println("You are logged in as Admin.");
            isAdmin = true;
        }
        currentUser = loggedInUserEmail;
        return isLogin;
    }

    // Book addition operation (Create)
    private static void createBook() {

        if (isAdmin) {                                  // Admin check
            // If the user is an admin, they can add a new book
            System.out.println("The book ID: ");
            int ID = nextID++;
            System.out.println(ID);

            System.out.println("Enter the book title: ");
            String title = scanner.nextLine();

            System.out.println("Enter the book author: ");
            String author = scanner.nextLine();

            System.out.println("Enter the book publication year: ");
            int year = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            // Add the new book
            // Store book details in the books array
            books[bookCount][0] = String.valueOf(ID);
            books[bookCount][1] = title;
            books[bookCount][2] = author;
            books[bookCount][3] = String.valueOf(year);

            bookCount++;

            System.out.println("New book added successfully.");
        } else {
            // If the user is not an admin, no action is performed
            System.out.println("You do not have permission to add new books.");
        }
    }

    public static boolean isAdmin(String email) {
        String adminEmail = "Mobin@gmail.com";
        return email.equals(adminEmail);
    }


    public static void selectChoice() {
        boolean exit = false;
        while (!exit) {

            System.out.println("Please choose an option to continue");
            System.out.println("Press 1 for Signup");
            System.out.println("Press 2 for Login");
            System.out.println("Press 3 for exiting");
            String loggedInEmail = "";
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    userRegistration();
                    break;

                case 2:
                    boolean flag = login();
                    if (flag) {
                        // After successful login, prompt for book creation
                        mainMenu();
                    }
                    break;
                case 3:
                    System.out.println("Exiting the system...");
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid option. Please restart the program and choose one of valid options.");
            }
        }
    }


    // Searching books by book title
    public static void searchByTitle() {
        System.out.println("Enter the book title to search: ");
        String title = scanner.nextLine();
        boolean find = false;

        for (int i = 0; i < bookCount; i++) {
            if (books[i][1].equalsIgnoreCase(title)) {
                System.out.println("Found: ID: " + books[i][0] + " | Title: " + books[i][1] + " | Author: " + books[i][2] + " | Year: " + books[i][3] + "\n");
                find = true;
            }
        }
        if (!find) {
            System.out.println("No books found with the title \"" + title + "\"." + "\n");
        }
    }


    // Searching books by author name
    public static void searchByAuthor() {
        System.out.println("Enter author name to search: ");
        String author = scanner.nextLine();
        boolean find = false;

        for (int i = 0; i < bookCount; i++) {
            if (books[i][2].equalsIgnoreCase(author)) {
                System.out.println("Found: ID: " + books[i][0] + " | Title: " + books[i][1] + " | Author: " + books[i][2] + " | Year: " + books[i][3] + "\n");
                find = true;
            }
        }
        if (!find) {
            System.out.println("No books found with the author name \"" + author + "\"." + "\n");
        }
    }

    //
    public static void searchByPublicationYear() {
        System.out.println("Enter the publication year of the book to search: ");
        int year = scanner.nextInt();
        boolean find = false;

        for (int i = 0; i < bookCount; i++) {
            if (books[i][3].equals(String.valueOf(year))) {
                System.out.println("Found: ID: " + books[i][0] + " | Title: " + books[i][1] + " | Author: " + books[i][2] + " | Year: " + books[i][3] + "\n");
                find = true;
            }
        }
        if (!find) {
            System.out.println("No books found with the year of \"" + year + "\"." + "\n");
        }
    }


    // READ
    public static void mainMenu() {
        boolean exit = false;
        while (!exit) {

            System.out.println("Please choose a category of searching");
            System.out.println("Press 1 for searching by book's name");
            System.out.println("Press 2 for searching by author's name");
            System.out.println("Press 3 for searching by year");
            System.out.println("Press 4 to exit the page.");
            if (isAdmin)
                System.out.println("Press 5 to go main menu if you're an admin.");


            int select = scanner.nextInt();
            scanner.nextLine();

            switch (select) {
                case 1:
                    searchByTitle();
                    break;

                case 2:
                    searchByAuthor();
                    break;

                case 3:
                    searchByPublicationYear();
                    break;

                case 5:
                    adminMainMenu();
                    break;
                case 4:
                    exit = true;
                    break;

                default:
                    System.out.println("\nPlease enter a valid number!\n");
            }
        }
    }

    // Admin main menu (it will be visible only to admins)
    public static void adminMainMenu() {
        if (isAdmin) {
            boolean exit = false;
            while (!exit) {
                System.out.println("\nPlease choose a category.");
                System.out.println("Press 1 for searching by book's name");
                System.out.println("Press 2 for searching by author's name");
                System.out.println("Press 3 for searching by year");
                if (isAdmin)
                    System.out.println("Press 4 to create a book.");
                if (isAdmin)
                    System.out.println("Press 5 to update books by searching title.");
                if (isAdmin)
                    System.out.println("Press 6 to update books by searching author name.");
                if (isAdmin)
                    System.out.println("Press 7 to update books by searching publication year.");
                if (isAdmin)
                    System.out.println("Press 8 to update books by searching ID.");
                if (isAdmin)
                    System.out.println("Press 9 to remove book by ID.");
                System.out.println("Press 10 to exit the page.");

                int choose = scanner.nextInt();
                scanner.nextLine();

                switch (choose) {
                    case 1:
                        searchByTitle();
                        break;

                    case 2:
                        searchByAuthor();
                        break;

                    case 3:
                        searchByPublicationYear();
                        break;

                    case 4:
                        createBook();
                        break;

                    case 5:
                        updateBooksByTitle();
                        break;

                    case 6:
                        updateBooksByAuthor();
                        break;

                    case 7:
                        updateBooksByYear();
                        break;

                    case 8:
                        updateBooksByID();
                        break;

                    case 9:
                        removeBookByID();
                        break;

                    case 10:
                        exit = true;
                        break;

                    default:
                        System.out.println("Please enter a valid number!");
                }
            }
        }
    }

    // Beginning of the updating methods
    // Updating books by title
    public static void updateBooksByTitle() {
        if (isAdmin) {
            System.out.println("Search the book which you want to update it, (with title): ");
            System.out.println("Enter the book's title: ");
            String title = scanner.nextLine();

            boolean bookFind = false;
            int bookIndex = -1;
            int ID = 0;
            // Finding by book's title
            for (int i = 0; i < bookCount; i++) {
                if (books[i][1].equalsIgnoreCase(title)) {
                    System.out.println("The book found: ");
                    System.out.println("ID: " + books[i][0] + " | Title: " + books[i][1] + " | Author: " + books[i][2] + " | Year: " + books[i][3] + "\n");
                    bookFind = true;
                    bookIndex = i;
                    ID = Integer.parseInt(books[i][0]);
                    break;
                }
            }
            updateBook(bookFind, bookIndex, ID);

            if (!bookFind) {
                System.out.println("No books found with title \"" + title + "\".");
            }
        }
    }

    // Updating books by author
    public static void updateBooksByAuthor() {
        if (isAdmin) {
            System.out.println("Search the book which you want to update it, (with author name): ");
            System.out.println("Enter the author's name: ");
            String author = scanner.nextLine();
            int ID = 0;
            boolean bookFind = false;
            int bookIndex = -1;
            // Finding book by author's name
            for (int i = 0; i < bookCount; i++) {
                if (books[i][2].equalsIgnoreCase(author)) {
                    System.out.println("The book found: ");
                    System.out.println("ID: " + books[i][0] + " | Title: " + books[i][1] + " | Author: " + books[i][2] + " | Year: " + books[i][3] + "\n");
                    bookFind = true;
                    bookIndex = i;
                    ID = Integer.parseInt(books[i][0]);
                    break;
                }
            }
            updateBook(bookFind, bookIndex, ID);

            if (!bookFind) {
                System.out.println("No books found with author \"" + author + "\".");
            }
        }
    }

    // Updating books by publication year
    public static void updateBooksByYear() {
        if (isAdmin) {
            System.out.println("Search the book which you want to update it, (with publication year): ");
            System.out.println("Enter the publication year: ");
            int year = scanner.nextInt();
            int ID = 0;
            boolean bookFind = false;
            int bookIndex = -1;
            // Finding book by author's name
            for (int i = 0; i < bookCount; i++) {
                if (books[i][3].equals(String.valueOf(year))) {
                    System.out.println("The book found: ");
                    System.out.println("ID: " + books[i][0] + " | Title: " + books[i][1] + " | Author: " + books[i][2] + " | Year: " + books[i][3] + "\n");
                    bookFind = true;
                    bookIndex = i;
                    ID = Integer.parseInt(books[i][0]);
                    break;
                }
            }
            updateBook(bookFind, bookIndex, ID);

            if (!bookFind) {
                System.out.println("No books found with year \"" + year + "\".");
            }
        }
    }

    // Updating books by ID
    public static void updateBooksByID() {
        if (isAdmin) {
            System.out.println("Search the book which you want to update it, (with publication year): ");
            System.out.println("Enter the ID: ");
            int ID = scanner.nextInt();

            boolean bookFind = false;
            int bookIndex = -1;
            // Finding book by author's name
            for (int i = 0; i < bookCount; i++) {
                if (books[i][0].equals(String.valueOf(ID))) {
                    System.out.println("The book found: ");
                    System.out.println("ID: " + books[i][0] + " | Title: " + books[i][1] + " | Author: " + books[i][2] + " | Year: " + books[i][3] + "\n");
                    bookFind = true;
                    bookIndex = i;
                    break;
                }
            }
            updateBook(bookFind, bookIndex, ID);

            if (!bookFind) {
                System.out.println("No books found with ID \"" + ID + "\".");
            }
        }
    }

    // Update book
    public static void updateBook(boolean isFound, int bookIndex, int ID) {
        if (isFound) {
            // Prompt for new details
            System.out.println("Enter the new details for the book, and keep blank to keep the current value.");

            // Update Title
            System.out.println("New title: ");
            String newTitle = scanner.nextLine();
            if (!newTitle.isEmpty()) {
                books[bookIndex][1] = newTitle;
            }

            // Update Author
            System.out.println("New author: ");
            String newAuthor = scanner.nextLine();
            if (!newAuthor.isEmpty()) {
                books[bookIndex][2] = newAuthor;
            }

            // Update Year
            System.out.println("New year: ");
            String newYear = scanner.nextLine();
            if (!newYear.isEmpty()) {
                try {
                    int year = Integer.parseInt(newYear);
                    books[bookIndex][3] = (newYear);
                } catch (Exception e) {
                    System.out.println("Please enter a valid number, no text!");
                }
            }

            System.out.println("The book updated successfully");
            System.out.println("Updated Book: ID: " + books[bookIndex][0] + " | Title: " + books[bookIndex][1] + " | Author: " + books[bookIndex][2] + " | Year: " + books[bookIndex][3]);
        }
    }


    // Removing by ID
    public static void removeBookByID() {
        if (isAdmin) {
            System.out.println("Enter ID of the book which you want to delete: ");
            boolean bookFind = false;
            int ID = scanner.nextInt();


            for (int i = 0; i < bookCount; i++) {
                if (books[i][0].equals(String.valueOf(ID))) {
                    System.out.println("The book found: ");
                    System.out.println("ID: " + books[i][0] + " | Title: " + books[i][1] + " | Author: " + books[i][2] + " | Year: " + books[i][3] + "\n");
                    bookFind = true;

                    // Shift all subsequent books left
                    for (int j = i; j < bookCount - 1; j++) {
                        books[j] = books[j + 1];                // Shift each book one position to the left
                    }

                    // Set the last book entry to null (or empty values)
                    books[bookCount - 1] = new String[4];

                    bookCount--;
                    System.out.println("Book with ID " + ID + "has been removed.");
                    break;
                }
            }
            if (!bookFind)
                System.out.println("Book with ID " + ID + " not found");
        }
    }

    public static void main(String[] args) {
        selectChoice();
    }
}
