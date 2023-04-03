package servlets;

import DAO.DAO;
import model.Coupon;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.List;

@WebListener
public class BackgroundExecutor implements ServletContextListener {
    private ScheduledExecutorService scheduler;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        this.scheduler = Executors.newSingleThreadScheduledExecutor();
        this.scheduler.scheduleAtFixedRate(() -> {
            List couponList = DAO.getAllObjects(Coupon.class);
            for (Object o : couponList) {
                Coupon coupon = (Coupon) o;
                if (coupon.getRegistrationDate().getTime() + coupon.getExpiryTime() * 1000L < System.currentTimeMillis()) {
                    DAO.deleteObject(coupon);
                }
            }
        }, 60, 60, TimeUnit.SECONDS);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        this.scheduler.shutdownNow();
    }
}