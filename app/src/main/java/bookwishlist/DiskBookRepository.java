package bookwishlist;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class DiskBookRepository extends MemoryBookRepository {
    private static final String FILE_PATH = "data.json";

    public DiskBookRepository() {
        loadDataFromFile();
    }

    // 데이터를 JSON 파일에 저장하는 메서드
    @Override
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
    private void loadDataFromFile() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(FILE_PATH)) {
            // JSON 파일에서 전체 데이터를 읽어와 Map<String, Object> 타입으로 변환
            Type dataType = new TypeToken<Map<String, Object>>(){}.getType();
            Map<String, Object> data = gson.fromJson(reader, dataType);

            // booksStore 데이터를 JSON 문자열로 변환한 후, Map<Long, Book> 타입으로 역직렬화
            booksStore = gson.fromJson(gson.toJson(data.get("booksStore")), new TypeToken<Map<Long, Book>>(){}.getType());
            // categoriesStore 데이터를 JSON 문자열로 변환한 후, Map<String, Category> 타입으로 역직렬화
            categoriesStore = gson.fromJson(gson.toJson(data.get("categoriesStore")), new TypeToken<Map<String, Category>>(){}.getType());

            // data.get("id")가 Double, Integer, 또는 다른 숫자 타입일 수 있기에 Number로 캐스팅 후 변환
            id = ((Number) data.get("id")).longValue();
        } 
        catch (FileNotFoundException e) { // 파일이 존재하지 않을 경우 초기화
            initializeDefaults();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 기본 상태로 초기화
    private void initializeDefaults() {
        booksStore = new HashMap<>();
        categoriesStore = new HashMap<>();
        categoriesStore.put("default", new Category("default"));
        id = 0L;
    }
}
