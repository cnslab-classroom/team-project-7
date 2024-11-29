package bookwishlist.repository;

import java.util.List;

import bookwishlist.model.Book;
import bookwishlist.model.Category;

public interface BookRepository {
    void saveBook(Book book);
    void saveCategory(String name);
    void removeBook(Book book);
    List<Category> findAllCategories();
    Category findCategoryByName(String name);
    Book findBook(Book book);
    List<Book> findByBookTitle(String title);
    void saveDataToFile();
}
