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
        while (!input.equals("EXIT")){
            System.out.println("If you want to add a new coupon type ADD");
            System.out.println("If you want to see all valid coupons type CHECK");
            input = scanner.next();
            if (input.equals("EXIT")) break;
            if (input.equals("ADD")) {
                System.out.println("Please enter coupon's name");
                String name = scanner.nextLine();
                System.out.println("For how many seconds is it valid?");
                int expiryTime = scanner.nextInt();
                Coupon coupon = new Coupon(name, new Date(), expiryTime);
                DAO.addObject(coupon);
            }
            if (input.equals("CHECK")){
                List couponList = DAO.getAllObjects(Coupon.class);
                for (int i = 0; i < couponList.size(); i++) {
                    System.out.println(couponList.get(i));
                }
                DAO.closeOpenedSession();
            }
        }

    }
}
