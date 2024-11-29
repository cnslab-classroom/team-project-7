package bookwishlist.io;

import java.util.Scanner; // 입력을 위한 Scanner 클래스 import

public abstract class Input {
    private static Scanner sc = new Scanner(System.in);

    public static int getInt(String msg) {
        System.out.print(msg);
        return getInt();
    }

    public static int getInt() {
        int num = sc.nextInt();
        sc.nextLine(); // 버퍼에 남아 있는 개행문자('\n') 제거
        return num;
    }

    public static String getString(String msg) {
        System.out.print(msg);
        return sc.nextLine();
    }
}
