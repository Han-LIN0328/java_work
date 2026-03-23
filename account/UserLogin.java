import java.util.ArrayList;
import java.util.Scanner;

// 1. 自訂例外處理
class LoginFailedException extends Exception {
    public LoginFailedException(String message) {
        super(message);
    }
}

// Person 類別 (符合 protected 要求)
class Person {
    protected String name;
    public Person(String name) {
        this.name = name;
    }
}

// User 類別
class User extends Person {
    // 2. 封裝：將 username 與 password 改為 private
    private String username;
    private String password;

    public User(String name, String username, String password) {
        super(name);
        this.username = username;
        this.password = password;
    }

    // 取得帳號
    public String getUsername() {
        return username;
    }

    // 檢查密碼
    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    // 顯示角色
    public void showRole() {
        System.out.println("User: " + name);
    }
}

// StudentUser 子類別 (繼承與多型維持不變)
class StudentUser extends User {
    public StudentUser(String name, String username, String password) {
        super(name, username, password);
    }

    @Override
    public void showRole() {
        System.out.println("Student User: " + name);
    }
}

public class UserLogin {
    // 3. 使用 ArrayList 管理多個使用者
    private static ArrayList<User> users = new ArrayList<>();

    // 4. 會拋出例外的登入邏輯方法
    public static User login(String u, String p) throws LoginFailedException {
        for (User user : users) {
            if (user.getUsername().equals(u)) {
                if (!user.checkPassword(p)) {
                    throw new LoginFailedException("Login failed: Incorrect password.");
                }
                return user; // 帳密皆正確
            }
        }
        throw new LoginFailedException("Login failed: User not found.");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 初始化資料：將你原本的 StudentUser 加入 ArrayList
        users.add(new StudentUser("Flame", "student", "1234"));

        int choice = 0;

        // 5. 流程控制 (while + switch)
        while (true) {
            System.out.println("\n--- System Menu ---");
            System.out.println("1. Login");
            System.out.println("2. Exit");
            System.out.print("Enter choice: ");

            // 處理選單輸入的例外
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String inputUsername = sc.nextLine();

                    System.out.print("Enter password: ");
                    String inputPassword = sc.nextLine();

                    // 6. 完整的 try-catch-finally 處理登入程序
                    try {
                        User loggedInUser = login(inputUsername, inputPassword);
                        System.out.println("Login successful!");
                        loggedInUser.showRole();
                    } catch (LoginFailedException e) {
                        System.out.println(e.getMessage());
                    } finally {
                        System.out.println("Login attempt finished.");
                    }
                    break;

                case 2:
                    System.out.println("Exiting program...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please select 1 or 2.");
                    break;
            }
        }
    }
}