import java.io.InputStream;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException {
        boolean flag = true;
        while (flag) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("请输入用户名：");
            String testName = scanner.next().trim();

            System.out.println("请输入密码：");
            String testPassword = scanner.next().trim();
/*
        读取文件
        */
//        File file = new File("C:\\Users\\zed\\IdeaProjects\\CmdShop\\src\\users.xlsx");
            InputStream in = Class.forName("Test").getResourceAsStream("/users.xlsx"); // /表示的是classpath
            ReadUsersExcel readUsersExcel = new ReadUsersExcel();
            User[] users = readUsersExcel.readExcel(in);

            InputStream prpIn = Class.forName("Test").getResourceAsStream("/products.xlsx"); // /表示的是classpath
            ReadProductsExcel readProductsExcel = new ReadProductsExcel();
            Product[] products = readProductsExcel.readExcel(prpIn);

            for (User user : users) {
                if (testName.equals(user.getName()) && testPassword.equals(user.getPassword())) {
                    System.out.println("登录成功");
                    /*
                    显示商品
                    */
                    for (Product p : products) {
                        System.out.print("" + p.getProId());
                        System.out.print("\t\t" + p.getProName());
                        System.out.print("\t\t" + p.getPrice());
                        System.out.println("\t\t" + p.getDecription());
                    }
                    flag = false;
                    break;
                }
            }
            if(flag == true)
                System.out.println("登录失败");
        }

    }

}
