import java.util.ArrayList;
import java.util.Scanner;

// Main class: shows the menu
public class LibraryBookManager {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library library = new Library();

        while (true) {
            System.out.println("\n--- Library Book Manager ---");
            System.out.println("1. Add book");
            System.out.println("2. View all books");
            System.out.println("3. Search book by title");
            System.out.println("4. Issue book");
            System.out.println("5. Return book");
            System.out.println("6. Delete book");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            try {
                int choice = Integer.parseInt(sc.nextLine().trim());

                switch (choice) {
                    case 1:
                        System.out.print("Enter title: ");
                        String title = sc.nextLine().trim();
                        System.out.print("Enter author: ");
                        String author = sc.nextLine().trim();
                        library.addBook(title, author);
                        break;
                    case 2:
                        library.viewBooks();
                        break;
                    case 3:
                        System.out.print("Enter title to search: ");
                        library.searchByTitle(sc.nextLine().trim());
                        break;
                    case 4:
                        System.out.print("Enter book ID to issue: ");
                        library.issueBook(Integer.parseInt(sc.nextLine().trim()));
                        break;
                    case 5:
                        System.out.print("Enter book ID to return: ");
                        library.returnBook(Integer.parseInt(sc.nextLine().trim()));
                        break;
                    case 6:
                        System.out.print("Enter book ID to delete: ");
                        library.deleteBook(Integer.parseInt(sc.nextLine().trim()));
                        break;
                    case 7:
                        System.out.println("Goodbye!");
                        sc.close();
                        return;
                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
}

// Book class: stores data of one book (Encapsulation: private fields + getters/setters)
class Book {
    private int id;
    private String title;
    private String author;
    private boolean issued;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.issued = false;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isIssued() { return issued; }
    public void setIssued(boolean issued) { this.issued = issued; }

    public void display() {
        String status = issued ? "Issued" : "Available";
        System.out.println("ID: " + id + " | " + title + " | " + author + " | " + status);
    }
}

// Library class: manages a list of books
class Library {
    private ArrayList<Book> books = new ArrayList<>();
    private int nextId = 1;

    public void addBook(String title, String author) {
        books.add(new Book(nextId, title, author));
        System.out.println("Book added with ID " + nextId);
        nextId++;
    }

    public void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
            return;
        }
        for (Book b : books) {
            b.display();
        }
    }

    public void searchByTitle(String keyword) {
        boolean found = false;
        for (Book b : books) {
            if (b.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                b.display();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No book found.");
        }
    }

    private Book findById(int id) {
        for (Book b : books) {
            if (b.getId() == id) {
                return b;
            }
        }
        return null;
    }

    public void issueBook(int id) {
        Book b = findById(id);
        if (b == null) {
            System.out.println("Book ID not found.");
        } else if (b.isIssued()) {
            System.out.println("Book is already issued.");
        } else {
            b.setIssued(true);
            System.out.println("Book issued.");
        }
    }

    public void returnBook(int id) {
        Book b = findById(id);
        if (b == null) {
            System.out.println("Book ID not found.");
        } else if (!b.isIssued()) {
            System.out.println("Book was not issued.");
        } else {
            b.setIssued(false);
            System.out.println("Book returned.");
        }
    }

    public void deleteBook(int id) {
        Book b = findById(id);
        if (b == null) {
            System.out.println("Book ID not found.");
        } else {
            books.remove(b);
            System.out.println("Book deleted.");
        }
    }
}
