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
//        4)Chọn lọc theo mức độ phổ biến

@Test
public class NC10 {
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
            Thread.sleep(4000);
            //4)Chọn lọc theo mức độ phổ biến

            Select selectOption = new Select(driver.findElement(By.xpath("//form[1]/select[1]")));
            selectOption.selectByVisibleText("Thứ tự theo mức độ phổ biến");
            Thread.sleep(4000);

            String strSearch = driver.findElement(By.xpath("//*[@id=\"wrapper\"]/div/div/div[2]/form/select/option[1]")).getText();
            String strSearchResult = "Thứ tự theo mức độ phổ biến";
            System.out.println("Kết quả tìm kiếm: "+strSearch +"\n"+strSearch.equals(strSearchResult));
            Thread.sleep(4000);
        }catch (Exception e) {
        }
        driver.quit();

    }
}
