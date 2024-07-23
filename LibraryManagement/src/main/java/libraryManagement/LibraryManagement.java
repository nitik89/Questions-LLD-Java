/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package libraryManagement;

/**
 *
 * @author nitiknarang
 */
public class LibraryManagement {

    public static void main(String[] args) {
        Library library = Library.getInstance();

        // Add books to the library
        library.addBook(new Book("978-0-470-17883-9", "Clean Code", "Robert C. Martin", 2008));
        library.addBook(new Book("978-0-13-235088-2", "Clean Architecture", "Robert C. Martin", 2011));
        library.addBook(new Book("978-0-321-11185-5", "Refactoring", "Martin Fowler", 1999));

        // Register members
        library.registerMember(new Member("M001", "John Doe", "john.doe@example.com"));
        library.registerMember(new Member("M002", "Jane Smith", "jane"));

        // Borrow books
        library.borrowBook("M001", "978-0-470-17883-9");
        library.borrowBook("M001", "978-0-321-11185-5");
        library.borrowBook("M002", "978-0-13-235088-2");

        // Search for books
        System.out.println("Search for books containing 'Clean':");
        library.searchBook("Clean").forEach(book -> System.out.println(book.getTitle()));

        // Return books
        library.returnBook("M001", "978-0-470-17883-9");
        library.returnBook("M002", "978-0-321-11185-5");
    }
}
