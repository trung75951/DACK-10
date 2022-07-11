package CongDongShop;

import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;


//step by step
//        1)Vào trang chủ
//        2)Bấm vào phần mô hình anime trên thanh danh mục
//        3)Chọn danh mục Jujutsu Kaisen
//        4)Chọn sản phẩm hiển thị hết hàng
@Test
public class NC7 {
    public static void testSelenium(){
        WebDriver driver = driverFactory.getChromeDriver();
        try{
            //1)Vào trang chủ

            driver.get("https://congdongshop.com/");
            Thread.sleep(4000);

            //2)Bấm vào phần mô hình anime trên thanh danh mục
            driver.findElement(By.xpath("//body/div[1]/header[1]/div[1]/div[3]/div[1]/div[1]/ul[1]/li[5]/a[1]")).click();
            Thread.sleep(3000);
            //3)Chọn danh mục DragonBall
            driver.findElement(By.xpath("//div[2]/div[1]/a[1]/div[1]/div[2]/div[1]/h5[1]")).click();
            Thread.sleep(8000);
            String title = driver.findElement(By.xpath("//*[@id=\"main\"]/div/div[2]/div/div[2]/div[1]/div/div[2]/div[2]/div[1]/h3/a")).getText().toUpperCase();

            //4)Chọn sản phẩm hiển thị hết hàng
            driver.findElement(By.xpath("//body/div[1]/main[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/h3[1]/a[1]")).click();
            String strhethang = "Hết hàng";
            String strhethangdetail = driver.findElement(By.xpath("//div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/p[1]")).getText();
            Thread.sleep(3000);
            String strTitleProductDetail = driver.findElement(By.xpath("//*[@id=\"product-23004\"]/div[1]/div[1]/div/div[2]/h1")).getText().toUpperCase();
            System.out.println("Tiêu đề sản phẩm: "+title+"\n"+"Tiêu đề sản phẩm detail: "+strTitleProductDetail);
            System.out.println(title.equals(strTitleProductDetail));
            System.out.println("Sản phẩm hết hàng: "+strhethang.equals(strhethangdetail));
            Thread.sleep(6000);
        }catch (Exception e) {
        }
        driver.quit();

    }
}
