package bookwishlist;

public class BookWishListMain {
    public static void main(String[] args) {
        BookService service = new BookService();

        System.out.println("=== 북 위시리스트 프로그램 ===");

        int input = 0;
        boolean isRun = true;
        while(isRun) {
            printMenu();
            input = Input.getInt();

            switch (input) {
                case 1: {
                    service.addCategory(Input.getString("추가할 카테고리 이름을 입력하세요: "));
                    break;
                }
                case 2: {
                    service.printCategories();
                    break;
                }
                case 3: {
                    String bookName = Input.getString("추가할 책 이름을 입력하세요: ");
                    String author = Input.getString("\""+bookName + "\" 의 저자를 입력하세요: ");
                    String categoryName = Input.getString("\""+bookName + "\" 카테고리를 입력하세요: ");
                    
                    // 카테고리가 비어있을 경우 default로 설정
                    if(categoryName.equals("")) {
                        categoryName = "default";
                    }

                    if(service.addBook(bookName, author, categoryName)) {
                        System.out.printf("\"%s\"이(가) 카테고리 [%s]에 추가되었습니다.\n",bookName, categoryName);
                    } else {
                        System.out.println("책 추가 실패!");
                    }
                    break;
                }
                case 4: {
                    String name = Input.getString("삭제할 책 이름을 입력하세요: ");
                    service.deleteBook(name);
                    break;
                }
                case 5: {
                    String name = Input.getString("책 목록을 볼 카테고리 이름을 입력하세요: ");
                    service.printBooks(name);
                    break;
                }
                case 6: {
                    System.out.println("프로그램을 종료합니다.");
                    isRun = false;
                    break;
                }
                default: {
                    System.out.println("잘못된 선택입니다.");
                    break;
                }
                    
            }
        }
    }

    // 메뉴 출력
    public static void printMenu() {
        System.out.println();
        System.out.println("[메뉴]");
        System.out.println("1. 카테고리 추가");
        System.out.println("2. 카테고리 목록 보기");
        System.out.println("3. 책 추가");
        System.out.println("4. 책 삭제");
        System.out.println("5. 책 목록 보기");
        System.out.println("6. 종료");
        System.out.print("선택: ");
    }
    
}
