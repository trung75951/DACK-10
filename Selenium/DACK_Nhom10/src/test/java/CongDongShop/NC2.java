package CongDongShop;

import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

//step by step
//        1)Vào trang chủ
//        2)Nhân vào  Đăng nhập/Đăng ký
//        3)Tiến hành nhập dữ liệu vào khung input
//        4)Nhấn nút đăng nhập

@Test
public class NC2 {
    public static void testSelenium(){
        WebDriver driver = driverFactory.getChromeDriver();
        try{
            //1)Vào trang chủ

            driver.get("https://congdongshop.com/");
            Thread.sleep(3000);

            //2)Nhân vào Đăng nhập/Đăng ký
            driver.findElement(By.xpath("//*[@id=\"top-bar\"]/div/div[3]/ul/li[2]/a")).click();
            Thread.sleep(4000);

            //3)Tiến hành nhập dữ liệu vào khung input
            driver.findElement(By.xpath("//p[1]/input[1]")).clear();
            driver.findElement(By.xpath("//p[1]/input[1]")).sendKeys("Test05");
            Thread.sleep(1000);


            driver.findElement(By.xpath("//*[@id='password']")).clear();
            driver.findElement(By.xpath("//*[@id='password']")).sendKeys("test123456789");
            Thread.sleep(1000);

            //4)Nhấn nút đăng nhập
            driver.findElement(By.xpath("//p[3]/button[1]")).click();
            Thread.sleep(5000);

            //lần 2
            driver.findElement(By.xpath("//*[@id=\"top-bar\"]/div/div[3]/ul/li[2]/a")).click();
            Thread.sleep(4000);

            //3)Tiến hành nhập dữ liệu vào khung input
            driver.findElement(By.xpath("//p[1]/input[1]")).clear();
            driver.findElement(By.xpath("//p[1]/input[1]")).sendKeys("Test05");
            Thread.sleep(1000);


            driver.findElement(By.xpath("//*[@id='password']")).clear();
            driver.findElement(By.xpath("//*[@id='password']")).sendKeys("test123456789");
            Thread.sleep(1000);

            //4)Nhấn nút đăng nhập
            driver.findElement(By.xpath("//p[3]/button[1]")).click();
            Thread.sleep(3000);

        }catch (Exception e) {
        }
        driver.quit();

    }
}
