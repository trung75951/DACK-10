package CongDongShop;

import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;


//step by step
//        1)Vào trang chủ
//        2)Bấm vào phần mô hình anime trên thanh danh mục
//        3)Chọn loại sản phẩm mô hình thuyền
//        4)Nhập dữ liệu cho thanh search
//        5)Nhấn tìm kiếm

@Test
public class NC9 {
    public static void testSelenium(){
        WebDriver driver = driverFactory.getChromeDriver();
        try{
            //1)Vào trang chủ

            driver.get("https://congdongshop.com/");
            Thread.sleep(4000);

            //2)Bấm vào thanh search chọn mô hình anime

            Select selectOption = new Select(driver.findElement(By.xpath("//body/div[1]/header[1]/div[1]/div[2]/div[1]/div[3]/ul[1]/li[1]/div[1]/div[1]/form[1]/div[1]/div[1]/select[1]")));
            selectOption.selectByVisibleText("Mô Hình Anime");
            Thread.sleep(4000);

            //3)Nhập dữ liệu cho thanh search
            driver.findElement(By.xpath("//body/div[1]/header[1]/div[1]/div[2]/div[1]/div[3]/ul[1]/li[1]/div[1]/div[1]/form[1]/div[1]/div[2]/input[1]")).clear();
            driver.findElement(By.xpath("//body/div[1]/header[1]/div[1]/div[2]/div[1]/div[3]/ul[1]/li[1]/div[1]/div[1]/form[1]/div[1]/div[2]/input[1]")).sendKeys("truyện black jack");
            Thread.sleep(8000);
            driver.findElement(By.xpath("//body/div[1]/header[1]/div[1]/div[2]/div[1]/div[3]/ul[1]/li[1]/div[1]/div[1]/form[1]/div[1]/div[3]/button[1]")).click();
            String strSearch = "truyện black jack";
            String strSearchResult = "Không tìm thấy sản phẩm nào khớp với lựa chọn của bạn.";
            System.out.println("Kết quả tìm kiếm: "+strSearch +"\n"+strSearch.equals(strSearchResult));
            Thread.sleep(4000);
        }catch (Exception e) {
        }
        driver.quit();

    }
}
