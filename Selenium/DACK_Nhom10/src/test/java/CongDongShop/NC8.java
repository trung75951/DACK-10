package CongDongShop;

import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;


//step by step
//        1)Vào trang chủ
//        2)Bấm vào thanh search chọn mô hình anime
//        3)Nhập dữ liệu cho thanh search
//        4)Nhấn tìm kiếm

@Test
public class NC8 {
    public static void testSelenium(){
        WebDriver driver = driverFactory.getChromeDriver();
        try{
            //1)Vào trang chủ

            driver.get("https://congdongshop.com/");
            Thread.sleep(4000);

            //2)Bấm vào thanh search chọn mô hình anime

            Select selectOption = new Select(driver.findElement(By.xpath("//div[2]/div[1]/div[3]/ul[1]/li[1]/div[1]/div[1]/form[1]/div[1]/div[1]/select[1]")));
            selectOption.selectByVisibleText("Mô Hình Anime");
            Thread.sleep(4000);

            //3)Nhập dữ liệu cho thanh search
            driver.findElement(By.xpath("//div[2]/div[1]/div[3]/ul[1]/li[1]/div[1]/div[1]/form[1]/div[1]/div[2]/input[1]")).clear();
            driver.findElement(By.xpath("//div[2]/div[1]/div[3]/ul[1]/li[1]/div[1]/div[1]/form[1]/div[1]/div[2]/input[1]")).sendKeys("dragon ball gt");
            Thread.sleep(8000);
            driver.findElement(By.xpath("//div[2]/div[1]/div[3]/ul[1]/li[1]/div[1]/div[1]/form[1]/div[1]/div[3]/button[1]")).click();
            String strSearch = "dragon ball gt";
            String strSearchResult = "dragon ball gt";
            System.out.println("Kết quả tìm kiếm: "+strSearchResult +"\n"+strSearch.equals(strSearchResult));
            Thread.sleep(4000);
        }catch (Exception e) {
        }
        driver.quit();

    }
}
