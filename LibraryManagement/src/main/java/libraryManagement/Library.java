package libraryManagement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Library {
    private static Library instance;
    private final Map<String,Book>catalog;
    private final Map<String, Member> members;
    private final int MAX_BOOKS_PER_MEMBER=5;
    private final int LOAN_DURATION_DAYS=14;

    private Library() {
        this.catalog = new ConcurrentHashMap<>();
        this.members = new ConcurrentHashMap<>();
    }

    public static synchronized Library getInstance() {
        if (instance == null) {
            instance = new Library();
        }
        return instance;
    }

    public void addBook(Book book) {
        catalog.put(book.getISBN(), book);
    }

    public void removeBook(Book book) {
        catalog.remove(book.getISBN());
    }

    public void registerMember(Member member) {
        members.put(member.getMemberId(), member);
    }

    public void unregisterMember(Member member) {
        members.remove(member.getMemberId());
    }

    public Member getMember(String memberId) {
        return members.get(memberId);
    }

    public Book getBook(String isbn) {
        return catalog.get(isbn);
    }

    public synchronized void borrowBook(String memberId,String isbn) {
        Member member = members.get(memberId);
        Book book = getBook(isbn);
        if(member != null && book!=null && book.isAvailable() ){
            if(member.getBorrowedBooks().size() < MAX_BOOKS_PER_MEMBER){
                member.borrowBook(book);
                book.setAvailable(false);
                System.out.println("Book borrowed: " + book.getTitle() + " by " + member.getName());
            } else {
                System.out.println("Member " + member.getName() + " has reached the maximum number of borrowed books.");
            }
        }
        else{
            throw new IllegalStateException("Invalid member or book.");
        }

    }
    public synchronized void returnBook(String memberId,String isbn){
        Member member = getMember(memberId);
        Book book = getBook(isbn);

        if(member!=null&&book!=null){
            member.returnBook(book);
            book.setAvailable(true);
            System.out.println("Book returned: " + book.getTitle() + " by " + member.getName());
        } else {
            System.out.println("Book or member not found.");
        }
        }

    public List<Book> searchBook(String keyword){
        List<Book> matchingBooks = new ArrayList<>();
        for (Book book : catalog.values()) {
            if (book.getTitle().contains(keyword) || book.getAuthor().contains(keyword)) {
                matchingBooks.add(book);
            }
        }
        return matchingBooks;
    }
}
