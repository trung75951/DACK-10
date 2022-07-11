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
//        6)Tăng số lượng sản phẩm trong giỏ hàng
//        7)Nhấn cập nhật

@Test
public class NC15 {
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
            Thread.sleep(6000);
            //4)Chọn sản phẩm đầu tiên

            driver.findElement(By.xpath("//div[1]/div[1]/div[2]/div[1]/div[1]/a[1]/img[1]")).click();
            String strDetailProduct = driver.findElement(By.xpath("//body/div[1]/main[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/h1[1]")).getText().toLowerCase();
            Thread.sleep(5000);
            //5)Thêm sản phẩm vào giỏ hàng

            driver.findElement(By.xpath("//*[@id=\"product-22995\"]/div[1]/div[1]/div/div[2]/form/button")).click();
            Thread.sleep(5000);
            String strProductCart = driver.findElement(By.xpath("//body/div[1]/main[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/table[1]/tbody[1]/tr[1]/td[3]/a[1]")).getText().toLowerCase();
            System.out.println("Tiêu đề sản phẩm trang detail: " + strDetailProduct + "\n" + "Tiểu dề sản phẩm trang cart: " + strProductCart);
            System.out.println("Tiêu đề lấy được giá trị: "+strDetailProduct.equals(strProductCart));
            Thread.sleep(4000);

            //6)Tăng số lượng sản phẩm trong giỏ hàng
            driver.findElement(By.xpath("//body/div[1]/main[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/table[1]/tbody[1]/tr[1]/td[5]/div[1]/input[2]")).clear();
            driver.findElement(By.xpath("//body/div[1]/main[1]/div[1]/div[1]/div[2]/div[1]/form[1]/div[1]/table[1]/tbody[1]/tr[1]/td[5]/div[1]/input[2]")).sendKeys("2");
            Thread.sleep(3000);
            //7)Nhấn cập nhật
            driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/div[1]/form/div/table/tbody/tr[2]/td/button")).click();
            Thread.sleep(3000);
            //Kiểm tra chọn phí vận chuyển ngoài tỉnh thành
            driver.findElement(By.xpath("//*[@id='shipping_method_0_flat_rate2']")).click();
            String phigiaohang = driver.findElement(By.xpath("//*[@id=\"shipping_method\"]/li[2]/label/span")).getText();
            String tinhtam = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/div[1]/form/div/table/tbody/tr[1]/td[6]/span")).getText();
            Thread.sleep(2000);
            //Chuyển kiêu biến string thành biến float để kiểm tra
            float tinhTongPhiVanChuyen = Float.parseFloat(phigiaohang.replaceAll("\\W+", "")) / 100 + Float.parseFloat(tinhtam.replaceAll("\\W+", "")) / 100;
            String tong = driver.findElement(By.xpath("//strong[1]/span[1]/bdi[1]")).getText();
            float tongconvert = Float.parseFloat(tong.replaceAll("\\W+", "")) / 100;
            System.out.println("Tổng tiền: " + tongconvert + "\n" + "Tổng trong cart: " + tinhTongPhiVanChuyen);
            Thread.sleep(2000);
            if (tongconvert == tinhTongPhiVanChuyen){
                System.out.println("Giá trị tiền so sánh : true");
            }else {
                System.out.println("Giá trị tiền so sánh : false");
            }
            Thread.sleep(3000);
        }catch (Exception e) {

        }
        driver.quit();

    }
}
