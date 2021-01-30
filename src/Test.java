import java.io.InputStream;
import java.sql.Date;
import java.util.HashMap;
import java.util.Scanner;

public class Test {

    static int count;
    static Product proSelected[] = new Product[5];//���ﳵ
    static Product product;
    static Product[] products;
    static String pId;
    static InputStream proIn;
    static ReadProductsExcel readProductsExcel;
    static Scanner scanner = new Scanner(System.in);
    static HashMap<Product, Integer> amount = new HashMap<Product, Integer>();;

    public static void main(String[] args) throws ClassNotFoundException {
        boolean flag = true;
        while (flag) {
            System.out.println("�������û�����");
            String testName = scanner.next().trim();
            System.out.println("���������룺");
            String testPassword = scanner.next().trim();
/*
        ��ȡ�ļ�
        */
//        File file = new File("C:\\Users\\zed\\IdeaProjects\\CmdShop\\src\\users.xlsx");
            InputStream in = Class.forName("Test").getResourceAsStream("/users.xlsx"); // /��ʾ����classpath
            ReadUsersExcel readUsersExcel = new ReadUsersExcel();
            User[] users = readUsersExcel.getAllUsers(in);

            proIn = Class.forName("Test").getResourceAsStream("/products.xlsx"); // /��ʾ����classpath
            readProductsExcel = new ReadProductsExcel();
            products = readProductsExcel.getAllProducts(proIn);

            for (User user : users) {
                if (testName.equals(user.getName()) && testPassword.equals(user.getPassword())) {
                    System.out.println("��¼�ɹ�");
                    flag = false;
                    /*
                    ��ʾ��Ʒ
                    */
                    /*
                    1���鿴���ﳵ
                     ��1�����ﳵ��������ģ���
                     ��2�����������
                    2����������
                       ��ʾ���е���Ʒ
                    */
                    while (true) {
                        System.out.println("�鿴�ѹ���Ʒ������1\n����������2\n����������3\n�˳��̳�������4");
                        int number = scanner.nextInt();
                        if (number == 1 || number == 2) {
                            //����Mothod���mothod����
                            choose(number);
                        } else if (number == 3) {
                            /*
                            * 1.���������������ж����ࣩ
                            * 2.��poi����Order.xlsx�ļ�
                            * 3.�ѹ��ﳵ����Ʒд��Order.xlsx�ļ�
                            * */
                            Order order = new Order();
                            order.setUser(user);//���������û�
                            Product carts[] = new Product[count];
                            for(int i = 0 ; i < carts.length; i++){
                                if(proSelected[i] != null) {
                                    carts[i] = proSelected[i];
                                }
                            }
                            order.setProduct(carts);//����������Ʒ

                            order.setAmount(amount);

                            //order.setTotalPay();
                            long currentTime = System.currentTimeMillis();
                            Date date = new Date(currentTime);
                            order.setOrderDate(date);
                            //�¶���
                            CreateOrder.createOrder(order);

                        } else if (number == 4) {
                            System.out.println("�̳����˳���");
                            break;
                        }
                    }
                }
            }
            if (flag == true)
                System.out.println("��¼ʧ��");
        }
    }

    //������ȡ
    public static void choose(int number) throws ClassNotFoundException {
        /*
        1���鿴���ﳵ
         ��1�����ﳵ��������ģ���
         ��2�����������
        2����������
           ��ʾ���е���Ʒ
        */
        if (number == 1) {
            //��ʾ�ѹ���Ʒ
            for (Product p : proSelected) {
                if (p == null) {
                    break;
                }
                System.out.print(p.getProId());
                System.out.print("\t" + p.getProName());
                System.out.print("\t\t" + p.getPrice());
                System.out.print("\t\t" + p.getDecription());
                System.out.println("\t��������X" + amount.get(p));
            }
        } else if (number == 2) {
            //��ʾ��Ʒ
            for (Product p : products) {
                System.out.print("" + p.getProId());
                System.out.print("\t\t" + p.getProName());
                System.out.print("\t\t" + p.getPrice());
                System.out.println("\t\t" + p.getDecription());
            }
            System.out.println("��������ƷID������:");
            pId = scanner.next();
            int proAmount = scanner.nextInt();
            proIn = Class.forName("Test").getResourceAsStream("/products.xlsx");
//                        ReadProductsExcel readProductsExcel2 = new ReadProductsExcel();
            product = readProductsExcel.getProById(pId, proIn);
            if (product != null) {
                        /*
                        ����Ʒ���빺�ﳵ
                        */
//                        proIn = Class.forName("Test").getResourceAsStream("/products.xlsx");
                if (count < 5) {
                    proSelected[count] = product;
                    amount.put(proSelected[count],proAmount);
                    count++;
                    System.out.println("��Ʒ�Ѽ��빺�ﳵ");
                    System.out.println("");
                } else {
                    System.out.println("���ﳵ����,�޷������µ���Ʒ");
                }
            }
        }
    }
}
