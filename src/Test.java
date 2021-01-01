import java.io.File;
import java.util.List;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        /*
        读取文件
        */
        File file = new File("C:\\Users\\zed\\IdeaProjects\\CmdShop\\src\\users.xlsx");
        ReadExcel readExcel = new ReadExcel();
        User[] users = readExcel.readExcel(file);

        System.out.println("请输入用户名：");
        String testName = scanner.next();

        System.out.println("请输入密码：");
        String testPassword = scanner.next();

        for (User user : users){
            if(testName.equals(user.getName()) && testPassword.equals(user.getPassword())){
                System.out.println("登录成功");
                continue;
            }
        }
    }
}
