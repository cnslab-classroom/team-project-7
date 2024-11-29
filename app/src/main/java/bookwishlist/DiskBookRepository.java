package bookwishlist;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiskBookRepository implements BookRepository{
    private static Map<Long,Book> booksStore;
    private static Map<String,Category> categoriesStore;
    private static Long id;
    private static final String FILE_PATH = "data.json";

    DiskBookRepository() {
        loadDataFromFile();
    }


    // 데이터를 JSON 파일에 저장하는 메서드
    public void saveDataToFile() {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            // 두 개의 맵을 하나의 객체로 감싸서 저장
            Map<String, Object> data = new HashMap<>();
            data.put("booksStore", booksStore);
            data.put("categoriesStore", categoriesStore);
            data.put("id", id);

            gson.toJson(data, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // JSON 파일에서 데이터를 불러오는 메서드
    @SuppressWarnings("unchecked")
    private void loadDataFromFile() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type dataType = new TypeToken<Map<String, Object>>(){}.getType();
            Map<String, Object> data = gson.fromJson(reader, dataType);

            // 각 맵을 적절한 타입으로 캐스팅하여 로드
            booksStore = (Map<Long, Book>) data.get("booksStore");
            categoriesStore = (Map<String, Category>) data.get("categoriesStore");
            // data.get("id")가 Double, Integer, 또는 다른 숫자 타입일 수 있기에 Number로 캐스팅 후 변환
            id = ((Number) data.get("id")).longValue();
        } 
        catch (FileNotFoundException e) { // 파일이 존재하지 않을 경우 초기화
            booksStore = new HashMap<>();
            categoriesStore = new HashMap<>();
            categoriesStore.put("default", new Category("default"));
            id = 0L;
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 책 저장
    @Override
    public void saveBook(Book book) {
        book.setId(id++);
        booksStore.put(book.getId(), book);
    }

    // 카테고리 저장
    @Override
    public void saveCategory(String name) {
        categoriesStore.put(name, new Category(name));
    }

    // 책 삭제
    @Override
    public void removeBook(Book book) {
        // 책을 삭제할 경우 모든 카테고리에서 해당 책을 삭제
        for(String key : categoriesStore.keySet()) {
            categoriesStore.get(key).removeBook(book);
        }

        // 책을 삭제
        booksStore.remove(book.getId());
    }

    // 모든 카테고리 인스턴스 반환
    @Override
    public List<Category> findAllCategories() {
        return new ArrayList<>(categoriesStore.values());
    }

    // 카테고리 이름으로 카테고리 인스턴스 찾기
    @Override
    public Category findCategoryByName(String name) {
        return categoriesStore.get(name);
    }

    // 책제목과 저자 둘다 같은 책 찾기 (동일한 책 찾기)
    @Override
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
    @Override
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
