package CongDongShop;

import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;


//step by step
//        1)Vào trang chủ
//        2)Bấm vào phần mô hình anime trên thanh danh mục
//        3)Chọn danh mục DragonBall
//        4)Chọn sản phẩm hiển thị đầu tiên
//        5)Thêm sản phẩm vào giỏ hàng
//        6)Nhấn tiến hành đặt hàng
//        7)Nhập đầy dủ thông tin
//        8)Nhấn đặt hàng

@Test
public class NC16 {
    public static void testSelenium() {
        WebDriver driver = driverFactory.getChromeDriver();
        try {
            //1)Vào trang chủ

            driver.get("https://congdongshop.com/");
            Thread.sleep(4000);
            //2)Bấm vào phần mô hình anime trên thanh danh mục
            driver.findElement(By.xpath("//div[3]/div[1]/div[1]/ul[1]/li[5]/a[1]")).click();
            Thread.sleep(5000);
            //3)Bấm vào mô hình dragon ball
            driver.findElement(By.xpath("//body/div[1]/main[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/a[1]/div[1]/div[1]/img[1]")).click();
            Thread.sleep(3000);
            //4)Chọn sản phẩm đầu tiên

            driver.findElement(By.xpath("//div[1]/div[1]/div[2]/div[1]/div[1]/a[1]/img[1]")).click();
            Thread.sleep(3000);
            //5)Thêm sản phẩm vào giỏ hàng

            driver.findElement(By.xpath("//*[@id=\"product-22995\"]/div[1]/div[1]/div/div[2]/form/button")).click();
            Thread.sleep(5000);

            //6)Nhấn tiến hành đặt hàng

            driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/div[2]/div/div[1]/div/a")).click();
            Thread.sleep(2000);

            //7)Nhập đầy dủ thông tin

            driver.findElement(By.xpath("//*[@id=\"billing_last_name\"]")).clear();
            driver.findElement(By.xpath("//*[@id=\"billing_last_name\"]")).sendKeys("test");
            driver.findElement(By.xpath("//*[@id=\"billing_address_1\"]")).clear();
            driver.findElement(By.xpath("//*[@id=\"billing_address_1\"]")).sendKeys("hcm");
            driver.findElement(By.xpath("//*[@id=\"billing_phone\"]")).clear();
            driver.findElement(By.xpath("//*[@id=\"billing_phone\"]")).sendKeys("1234567890");
            driver.findElement(By.xpath("//*[@id=\"billing_email\"]")).clear();
            driver.findElement(By.xpath("//*[@id=\"billing_email\"]")).sendKeys("Testtp05@gmail.com");
            driver.findElement(By.xpath("//*[@id=\"order_comments\"]")).clear();
            driver.findElement(By.xpath("//*[@id=\"order_comments\"]")).sendKeys("Test website");
            Thread.sleep(1000);
            // 8)Nhấn đặt hàng
            driver.findElement(By.xpath("//*[@id=\"payment_method_cod\"]")).click();

        }catch (Exception e) {

        }
        driver.quit();

    }
}
