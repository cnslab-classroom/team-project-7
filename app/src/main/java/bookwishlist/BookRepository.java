package bookwishlist;

import java.util.List;

public interface BookRepository {
    void saveBook(Book book);
    void saveCategory(String name);
    void removeBook(Book book);
    List<Category> findAllCategories();
    Category findCategoryByName(String name);
    Book findBook(Book book);
    List<Book> findByBookTitle(String title);
}
