package CongDongShop;

import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;


//step by step
//        1)Vào trang chủ
//        2)Bấm vào phần mô hình anime trên thanh danh mục
//        3)Bấm vào mô hình dragon ball
//        4)Chọn lọc theo giá cao đến thâp

@Test
public class NC13 {
    public static void testSelenium(){
        WebDriver driver = driverFactory.getChromeDriver();
        try{
            //1)Vào trang chủ

            driver.get("https://congdongshop.com/");
            Thread.sleep(3000);

            //2)Bấm vào phần mô hình anime trên thanh danh mục
            driver.findElement(By.xpath("//div[3]/div[1]/div[1]/ul[1]/li[5]/a[1]")).click();
            Thread.sleep(2000);
            //3)Chọn danh mục DragonBall
            driver.findElement(By.xpath("//div[1]/div[1]/a[1]/div[1]/div[2]/div[1]/h5[1]")).click();
            Thread.sleep(5000);
            //4)Chọn lọc giá từ Cao xuống thấp

            Select selectOption = new Select(driver.findElement(By.xpath("//form[1]/select[1]")));
            selectOption.selectByVisibleText("Thứ tự theo giá: cao xuống thấp");
            Thread.sleep(5000);

            String strSearch = driver.findElement(By.xpath("//*[@id=\"wrapper\"]/div/div/div[2]/form/select/option[5]")).getText();
            String strSearchResult = "Thứ tự theo giá: cao xuống thấp";
            System.out.println("Kết quả tìm kiếm: "+strSearch +"\n"+strSearch.equals(strSearchResult));
            Thread.sleep(4000);
        }catch (Exception e) {
        }
        driver.quit();

    }
}