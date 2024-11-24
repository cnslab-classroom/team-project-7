package bookwishlist;

import java.util.List;

public class BookService {
    private final MemoryBookRepository bookRepository = new MemoryBookRepository();

    public void addCategory(String categoryName) {
        bookRepository.saveCategory(categoryName);
    }

    public void printCategories() {
        System.out.println("=== 카테고리 목록 ===");
        List<Category> categories = bookRepository.findAllCategories();

        for (Category category : categories) {
            System.out.println(category.getName());
        }
    }

    public boolean addBook(String bookName, String author, String categoryName) {
        return false;
    }

    public void deleteBook(String bookName) {
    }

    public void printBooks(String categoryName) {
    }
}
