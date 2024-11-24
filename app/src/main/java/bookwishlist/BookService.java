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
        List<Book> books = repository.findByBookTitle(bookName);
        if (books.isEmpty()) {
            System.out.println("해당 책이 존재하지 않습니다.");
            return;
        }
        
        // 책이 하나만 존재할 경우 삭제 (굳이 뭘 삭제할 것인지 물어볼 필요가 없음)
        if(books.size() == 1) {
            repository.removeBook(books.get(0));
            System.out.println("책이 삭제되었습니다.");
            return;
        }

        // 같은 이름의 책이 여러개 존재할 경우 삭제할 책을 선택하도록 함
        for(int i = 0;i<books.size();i++) {
            System.out.println((i+1) + ". " + books.get(i));
        }
        System.out.print("삭제할 책의 번호를 입력하세요: ");
        int num = Input.getInt();
        repository.removeBook(books.get(num-1));
        System.out.println("책이 삭제되었습니다.");
    }


    public void printBooks(String categoryName) {
        Category category = repository.findCategoryByName(categoryName);
        if(category == null) {
            System.out.println("해당 카테고리가 존재하지 않습니다.");
            return;
        }

        category.printBooks();
    }

    // 카테고리에 책을 추가하는 메소드
    private boolean addBookToCategory(Book book, String categoryName) {
        Category category = repository.findCategoryByName(categoryName);
        
        // 해당하는 카테고리가 없거나 카테고리에 이미 책이 존재하는 경우, 추가하지 않고 실패했다는 뜻으로 false 반환
        if(category == null || category.findByBook(book) != null) { 
            return false;
        }

        category.addBook(book);
        return true;
    }
}
