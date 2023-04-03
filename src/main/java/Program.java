import model.Coupon;

import DAO.DAO;
import java.util.Date;
import java.util.Scanner;
import java.util.List;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Starting... Type EXIT to finish");
        String input = "";
        label:
        while (true){
            System.out.println("If you want to add a new coupon type ADD");
            System.out.println("If you want to see all valid coupons type CHECK");
            input = scanner.next();
            switch (input) {
                case "EXIT":
                    break label;
                case "ADD":
                    System.out.println("Please enter coupon's name");
                    String name = scanner.nextLine();
                    System.out.println("For how many seconds is it valid?");
                    int expiryTime = scanner.nextInt();
                    Coupon coupon = new Coupon(name, new Date(), expiryTime);
                    DAO.addObject(coupon);
                    break;
                case "CHECK":
                    List couponList = DAO.getAllObjects(Coupon.class);
                    for (Object o : couponList) {
                        System.out.println(o);
                    }
                    DAO.closeOpenedSession();
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }
        }

    }
}
