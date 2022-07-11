package CongDongShop;

import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;


//step by step
//            1)Vào trang chủ
//            2)Bấm vào phần mô hình anime trên thanh danh mục
//            3)Chọn danh mục DragonBall
//            4)Chọn sản phẩm hiển thị đầu tiên
@Test
public class NC6 {
    public static void testSelenium(){
        WebDriver driver = driverFactory.getChromeDriver();
        try{
            //1)Vào trang chủ

            driver.get("https://congdongshop.com/");
            Thread.sleep(3000);

            //2)Bấm vào phần mô hình anime trên thanh danh mục
            driver.findElement(By.xpath("//*[@id=\"menu-item-22838\"]/a")).click();
            Thread.sleep(2000);
            //3)Chọn danh mục DragonBall
            driver.findElement(By.xpath("//*[@id=\"main\"]/div/div[2]/div/div[2]/div[1]/div/a")).click();
            String title = driver.findElement(By.xpath("//div[1]/div[1]/div[2]/div[2]/div[1]/h3[1]/a[1]")).getText();
            Thread.sleep(5000);

            //4)Chọn sản phẩm hiển thị đầu tiên
            driver.findElement(By.xpath("//div[1]/div[1]/div[2]/div[2]/div[1]/h3[1]/a[1]")).click();
            Thread.sleep(3000);
            String strTitleProductDetail = driver.findElement(By.xpath("//*[@id=\"product-22995\"]/div[1]/div[1]/div/div[2]/h1")).getText();
            String upCaseTitle = title.toUpperCase();
            String upCasestrTitleProductDetail = strTitleProductDetail.toUpperCase();
            System.out.println("Tiểu để sản phẩm: "+title + " \nTiêu đề sản phẩm trang DETAIL: "+strTitleProductDetail);
            System.out.println("so sánh 2 tiêu đề product " +upCaseTitle.equals(upCasestrTitleProductDetail));
            Thread.sleep(4000);
        }catch (Exception e) {
        }
        driver.quit();

    }
}
