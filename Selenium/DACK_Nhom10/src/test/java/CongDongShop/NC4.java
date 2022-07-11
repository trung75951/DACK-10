package CongDongShop;

import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

//step by step
//        1)Vào trang chủ
//        2)Nhập email cho phần đăng ký nhận tin
//        3)Nhấn gửi đi
@Test
public class NC4 {
    public static void testSelenium(){
        WebDriver driver = driverFactory.getChromeDriver();
        try{
            //1)Vào trang chủ

            driver.get("https://congdongshop.com/");
            Thread.sleep(3000);

            //2)Nhập email cho phần đăng ký nhận tin
            driver.findElement(By.xpath("//span[1]/input[1]")).clear();
            driver.findElement(By.xpath("//span[1]/input[1]")).sendKeys("Testtp05@gmail.com");
            Thread.sleep(3000);

            //3)Nhấn gửi đi
            driver.findElement(By.xpath("//form[1]/div[2]/input[1]")).click();
            Thread.sleep(8000);
        }catch (Exception e) {
        }
        driver.quit();

    }
}
