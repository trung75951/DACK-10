package CongDongShop;

import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

@Test
public class Testcase14 {
    public static void testSelenium(){
        WebDriver driver = driverFactory.getChromeDriver();
        try{
            //1)Vào trang chủ

            driver.get("https://congdongshop.com/");
            Thread.sleep(3000);

            //2)Bấm vào phần mô hình anime trên thanh danh mục
            driver.findElement(By.xpath("//div[3]/div[1]/div[1]/ul[1]/li[5]/a[1]")).click();
            Thread.sleep(3000);
            //3)Chọn danh mục DragonBall
            driver.findElement(By.xpath("//div[1]/div[1]/a[1]/div[1]/div[2]/div[1]/h5[1]")).click();
            Thread.sleep(5000);
            String title = driver.findElement(By.xpath("//div[1]/div[1]/div[2]/div[2]/div[1]/h3[1]/a[1]")).getText().toUpperCase();
            Thread.sleep(3000);
            //4)Chọn sản phẩm hiển thị đầu tiên
            driver.findElement(By.xpath("//div[1]/div[1]/div[2]/div[2]/div[1]/h3[1]/a[1]")).click();
            Thread.sleep(3000);
            String strTitleProductDetail = driver.findElement(By.xpath("//div[2]/h1[1]")).getText().toUpperCase();
            System.out.println("Tiểu để sản phẩm: "+title + " \nTiêu đề sản phẩm trang DETAIL: "+strTitleProductDetail);
            System.out.println("so sánh 2 tiêu đề product " +title.equals(strTitleProductDetail));
            Thread.sleep(4000);
        }catch (Exception e) {
        }
        driver.quit();

    }
}
