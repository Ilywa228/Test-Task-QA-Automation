import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

@Test
public class task_2 {
    public static void get_prices(WebDriver driver) {

        List<Integer> check_prices = new ArrayList<Integer>();
        List<WebElement> perforator_catalog_firstpage = driver.findElements(By.cssSelector("body > div.page > div.wrapper > main > div.row > div.col-xs-9 > ul > li"));
        for (int i = 0; i < perforator_catalog_firstpage.toArray().length; ++i) {
            String prices = perforator_catalog_firstpage.get(i).findElement(By.className("price")).getText();
            if (prices == null) {
                check_prices.add(1);
            } else {
                check_prices.add(0);
            }
        }
        int sum = check_prices.stream().mapToInt(Integer::intValue).sum();
        if (sum == 0) {
            System.out.println("Have prices on this page");
        } else {
            System.out.println(sum + " Misses prices on this page");
        }
    }

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "C:\\tools\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.get("https://kulibin.com.ua/");

        WebElement catalog_electrotools = driver.findElement(By.cssSelector("body > div.page > div > div.nav-wrap > div > div > div.col-xs-3 > div > div > ul > li:nth-child(3) > a"));
        catalog_electrotools.click();

        WebElement perforator = driver.findElement(By.cssSelector("body > div.page > div.wrapper > main > ul.article-list.row.article-list-row > li:nth-child(1) > div > div.text-box > ul > li:nth-child(12) > h4 > a"));
        perforator.click();

        int i = 0;
        while (i < 2) {
            get_prices(driver);
            WebElement next_page = driver.findElement(By.cssSelector("body > div.page > div.wrapper > main > div.row > div.col-xs-9 > div.paging > a.next.btn-blue"));
            next_page.click();
            i++;
        }
    }
}
