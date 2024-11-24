package bookwishlist;

import java.util.List;

public class BookService {
    private final MemoryBookRepository repository = new MemoryBookRepository();

    public void addCategory(String categoryName) {
        repository.saveCategory(categoryName);
    }

    public void printCategories() {
        System.out.println("=== 카테고리 목록 ===");
        List<Category> categories = repository.findAllCategories();

        for (Category category : categories) {
            System.out.println(category.getName());
        }
    }

    public boolean addBook(String bookName, String author, String categoryName) {
        Book newBook = new Book(bookName, author); // 추가할 책
        Book oldBook = repository.findBook(newBook); // 추가할 책이 이미 존재하는지 확인
        
        if(oldBook == null) {  // 책이 존재하지 않을 경우 추가
            repository.saveBook(newBook);
            oldBook = newBook;
        } 

        return addBookToCategory(oldBook, categoryName);
    }


    public void deleteBook(String bookName) {
    }

    public void printBooks(String categoryName) {
    }

    // 카테고리에 책을 추가하는 메소드
    private boolean addBookToCategory(Book book, String categoryName) {
        Category category = repository.findCategoryByName(categoryName);
        
        if(category == null || category.findByBook(book) != null) { // 해당하는 카테고리가 없거나 카테고리에 이미 책이 존재하는 경우, 추가하지 않고 실패했다는 뜻으로 false 반환
            return false;
        }

        category.addBook(book);
        return true;
    }
}
