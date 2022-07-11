package CongDongShop;

import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

//step by step
//        1)Vào trang chủ
//        2)Nhấn vào phần liên hệ trên navbar
//        3)Nhập thông tin đầy đủ
//        4)Nhấn gửi ngay
@Test
public class NC5 {
    public static void testSelenium(){
        WebDriver driver = driverFactory.getChromeDriver();
        try{
            //1)Vào trang chủ

            driver.get("https://congdongshop.com/");
            Thread.sleep(3000);

            //2)Nhấn vào phần liên hệ trên navbar
            driver.findElement(By.xpath("//*[@id=\"menu-item-204\"]/a")).click();

            //3)Nhập thông tin đầy đủ
            driver.findElement(By.xpath("//div[1]/span[1]/input[1]")).clear();
            driver.findElement(By.xpath("//div[1]/span[1]/input[1]")).sendKeys("Test");
            Thread.sleep(1000);

            driver.findElement(By.xpath("//div[2]/div[2]/span[1]/input[1]")).clear();
            driver.findElement(By.xpath("//div[2]/div[2]/span[1]/input[1]")).sendKeys("Testtp05@gmail.com");
            Thread.sleep(1000);

            driver.findElement(By.xpath("//p[1]/span[1]/input[1]")).clear();
            driver.findElement(By.xpath("//p[1]/span[1]/input[1]")).sendKeys("1234567890");
            Thread.sleep(1000);

            driver.findElement(By.xpath("//*[@id=\"wpcf7-f1431-p87-o1\"]/form/div[2]/p[1]/span[2]/textarea")).clear();
            driver.findElement(By.xpath("//*[@id=\"wpcf7-f1431-p87-o1\"]/form/div[2]/p[1]/span[2]/textarea")).sendKeys("Test Website");
            Thread.sleep(1000);

            //4)Nhấn gửi ngay
            driver.findElement(By.xpath("//*[@id=\"wpcf7-f1431-p87-o1\"]/form/div[2]/p[2]/input")).click();
            Thread.sleep(8000);
        }catch (Exception e) {
        }
        driver.quit();

    }
}
