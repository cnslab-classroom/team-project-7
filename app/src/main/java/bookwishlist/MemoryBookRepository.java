package bookwishlist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryBookRepository {
    private static Map<Long,Book> booksStore = new HashMap<>();
    private static Map<String,Category> categoriesStore = new HashMap<>();
    private static Long id = 0L;

    MemoryBookRepository() {
        categoriesStore.put("default", new Category("default"));
    }

    public void saveBook(Book book) {
        book.setId(id++);
        booksStore.put(book.getId(), book);
    }

    public void saveCategory(String name) {
        categoriesStore.put(name, new Category(name));
    }

    public void removeBook(Book book) {
    }

    public void updateBook(Book book) {
        
    }

    public List<Category> findAllCategories() {
        return new ArrayList<>(categoriesStore.values());
    }

    public Category findCategoryByName(String name) {
        return categoriesStore.get(name);
    }

    public Book findBook(Book book) {
        for (Long key : booksStore.keySet()) {
            Book target = booksStore.get(key);
            if ((target.getTitle()).equals(book.getTitle()) && (target.getAuthor()).equals(book.getAuthor())) {
                return target;
            }
        }
        return null;
    }
}