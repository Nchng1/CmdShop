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
            User[] users = readUsersExcel.getAllUsers(in);

            InputStream proIn = Class.forName("Test").getResourceAsStream("/products.xlsx"); // /表示的是classpath
            ReadProductsExcel readProductsExcel = new ReadProductsExcel();
            Product[] products = readProductsExcel.getAllProducts(proIn);

            for (User user : users) {
                if (testName.equals(user.getName()) && testPassword.equals(user.getPassword())) {
                    System.out.println("登录成功");
                    /*
                    显示商品
                    */
                    int count = 0;
                    Product proSelected[] = new Product[5];
                    for (Product p : products) {
                        System.out.print("" + p.getProId());
                        System.out.print("\t\t" + p.getProName());
                        System.out.print("\t\t" + p.getPrice());
                        System.out.println("\t\t" + p.getDecription());
                    }
                    System.out.println("请输入商品ID：");
                    String pId=scanner.next();
                    proIn = Class.forName("Test").getResourceAsStream("/products.xlsx");
                    ReadProductsExcel readProductsExcel1 = new ReadProductsExcel();
                    Product product = readProductsExcel1.getProById(pId, proIn);
                    if ( product!= null) {
                        /*
                        把商品加入购物车
                        */
//                        proIn = Class.forName("Test").getResourceAsStream("/products.xlsx");
                        proSelected[count++] = product;
                        System.out.println("商品已加入购物车");
                    }
                    /*
                    1、查看购物车
                     （1）购物车是用数组模拟的
                     （2）对数组遍历
                    2、继续购物
                       显示所有的商品
                    */
                    flag = false;
                    break;
                }
            }
            if(flag == true)
                System.out.println("登录失败");
        }

    }

}
