package CongDongShop;

import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;


//step by step
//        1)Vào trang chủ
//        2)Bấm vào phần mô hình anime trên thanh danh mục
//        3)Chọn danh mục DragonBall
//        4)Chọn sản phẩm hiển thị đầu tiên
//        5)Thêm sản phẩm vào giỏ hàng

@Test
public class NC14 {
    public static void testSelenium(){
        WebDriver driver = driverFactory.getChromeDriver();
        try{
            //1)Vào trang chủ

            driver.get("https://congdongshop.com/");
            Thread.sleep(4000);
            //2)Bấm vào phần mô hình anime trên thanh danh mục
            driver.findElement(By.xpath("//div[3]/div[1]/div[1]/ul[1]/li[5]/a[1]")).click();
            Thread.sleep(5000);
            //3)Bấm vào mô hình dragon ball
            driver.findElement(By.xpath("//body/div[1]/main[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/a[1]/div[1]/div[1]/img[1]")).click();
            Thread.sleep(6000);
            //4)Chọn sản phẩm đầu tiên

            driver.findElement(By.xpath("//div[1]/div[1]/div[2]/div[1]/div[1]/a[1]/img[1]")).click();
            String strDetailProduct = driver.findElement(By.xpath("//body/div[1]/main[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/h1[1]")).getText().toLowerCase();
            Thread.sleep(5000);
            //5)Thêm sản phẩm vào giỏ hàng

            driver.findElement(By.xpath("//*[@id=\"product-22995\"]/div[1]/div[1]/div/div[2]/form/button")).click();
            Thread.sleep(5000);
            String strProductCart = driver.findElement(By.xpath("//body/div[1]/main[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/table[1]/tbody[1]/tr[1]/td[3]/a[1]")).getText().toLowerCase();
            System.out.println("Tiêu đề sản phẩm trang detail: "+strDetailProduct+"\n"+"Tiểu dề sản phẩm trang cart: "+strProductCart);
            System.out.println(strDetailProduct.equals(strProductCart));
            Thread.sleep(4000);
        }catch (Exception e) {
        }
        driver.quit();

    }
}
