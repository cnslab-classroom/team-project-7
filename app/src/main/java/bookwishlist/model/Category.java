package bookwishlist.model;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private String name;
    private List<Book> books;

    public Category(String name) {
        this.name = name;
        this.books = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public Book findByBook(Book book) {
        for (Book b : books) {
            if (b.equals(book)) {
                return b;
            }
        }
        return null;
    }

    public void printBooks() {
        if (books.isEmpty()) {
            System.out.println("책이 없습니다.");
            return;
        }
        
        System.out.println("=== " + name + " 카테고리의 책 목록 ===");
        for (int i = 0; i < books.size(); i++) {
            System.out.println((i + 1) + ". " + books.get(i));
        }
    }
}
