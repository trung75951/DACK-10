package CongDongShop;

import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

//step by step
//        1)Vào trang chủ
//        2)Nhấn vào Đăng nhập /Đăng ký
//        3)Bấm quên mặt khâu
//        4)Nhập địa chỉ mail đăng ký của tài khoản
//        5)Nhấn đặt lại mật khẩu

@Test
public class NC3 {
    public static void testSelenium(){
        WebDriver driver = driverFactory.getChromeDriver();
        try{
            //1)Vào trang chủ

            driver.get("https://congdongshop.com/");
            Thread.sleep(3000);

            //2)Nhân vào Đăng nhập/Đăng ký
            driver.findElement(By.xpath("//*[@id=\"top-bar\"]/div/div[3]/ul/li[2]/a")).click();
            Thread.sleep(3000);

            //3)Bấm quên mặt khâu
            driver.findElement(By.xpath("//*[@id=\"login-form-popup\"]/div[2]/div/form/p[4]/a")).click();
            Thread.sleep(1000);

            //4)Nhập địa chỉ mail đăng ký của tài khoản
            driver.findElement(By.xpath("//*[@id='user_login']")).clear();
            driver.findElement(By.xpath("//*[@id='user_login']")).sendKeys("Testtp05@gmail.com");
            Thread.sleep(1000);


            //5)Nhấn nút đăng ký
            driver.findElement(By.xpath("//p[3]/button[1]")).click();
            Thread.sleep(3000);

            //6)Nhấn đặt lại mật khẩu
            driver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/div/div/form/p[3]/button")).click();
            Thread.sleep(3000);
        }catch (Exception e) {
        }
        driver.quit();

    }
}
