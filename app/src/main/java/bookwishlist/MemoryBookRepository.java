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

    // 책 저장
    public void saveBook(Book book) {
        book.setId(id++);
        booksStore.put(book.getId(), book);
    }

    // 카테고리 저장
    public void saveCategory(String name) {
        categoriesStore.put(name, new Category(name));
    }

    // 책 삭제
    public void removeBook(Book book) {
        // 책을 삭제할 경우 모든 카테고리에서 해당 책을 삭제
        for(String key : categoriesStore.keySet()) {
            categoriesStore.get(key).removeBook(book);
        }

        // 책을 삭제
        booksStore.remove(book.getId());
    }

    // 모든 카테고리 인스턴스 반환
    public List<Category> findAllCategories() {
        return new ArrayList<>(categoriesStore.values());
    }

    // 카테고리 이름으로 카테고리 인스턴스 찾기
    public Category findCategoryByName(String name) {
        return categoriesStore.get(name);
    }

    // 책제목과 저자 둘다 같은 책 찾기 (동일한 책 찾기)
    public Book findBook(Book book) {
        for (Long key : booksStore.keySet()) {
            Book target = booksStore.get(key);
            if ((target.getTitle()).equals(book.getTitle()) && (target.getAuthor()).equals(book.getAuthor())) {
                return target;
            }
        }
        return null;
    }

    // 책 제목으로 해당하는 책 모두 찾기
    public List<Book> findByBookTitle(String title) {
        List<Book> result = new ArrayList<>(); // 제목이 같은 책이 존재할 수 있음으로 검색 결과를 리스트에 담아 반환

        // 해쉬맵을 키 값을 이용하여 순회하면서 제목이 같은 책을 찾아 리스트에 추가
        for (Long key : booksStore.keySet()) {
            if ((booksStore.get(key).getTitle()).equals(title)) {
                result.add(booksStore.get(key));
            }
        }
        return result;
    }
}
