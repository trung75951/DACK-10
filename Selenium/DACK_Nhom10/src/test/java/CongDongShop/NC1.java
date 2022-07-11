package CongDongShop;

import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

//step by step
//        1)Vào trang chủ
//        2)Nhân vào icon có chữ đăng ký tài khoản
//        3)Tiến hành nhập dữ liệu vào khung input
//        4)Nhấn nút đăng ký

@Test
public class NC1 {
    public static void testSelenium(){
        WebDriver driver = driverFactory.getChromeDriver();
        try{
            //1)Vào trang chủ

            driver.get("https://congdongshop.com/");
            Thread.sleep(3000);

            //2)Nhân vào icon có chữ đăng ký tài khoản
            driver.findElement(By.xpath("//*[@id=\"login-home\"]/a")).click();
            Thread.sleep(3000);

            //3)Tiến hành nhập dữ liệu vào khung input
            driver.findElement(By.xpath("//div[1]/div[1]/span[1]/input[1]")).clear();
            driver.findElement(By.xpath("//div[1]/div[1]/span[1]/input[1]")).sendKeys("Test05");
            Thread.sleep(1000);

            driver.findElement(By.xpath("//*[@id='email']")).clear();
            driver.findElement(By.xpath("//*[@id='email']")).sendKeys("Testtp05@gmail.com");
            Thread.sleep(1000);

            driver.findElement(By.xpath("//*[@id='pwd1']")).clear();
            driver.findElement(By.xpath("//*[@id='pwd1']")).sendKeys("test123456789");
            Thread.sleep(1000);

            driver.findElement(By.xpath("//*[@id='pwd2']")).clear();
            driver.findElement(By.xpath("//*[@id='pwd2']")).sendKeys("test123456789");
            Thread.sleep(1000);

            //4)Nhấn nút đăng ký
            driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/div/div[1]/form/div[5]/div/button")).click();
            Thread.sleep(3000);

        }catch (Exception e) {
        }
        driver.quit();

    }
}
