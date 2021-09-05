import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.List;

@Test
public class task_3 {
    public static void getname_usflag(WebDriver driver) {

        List<WebElement> shooroopovert_catalog_firstpage = driver.findElements(By.cssSelector("body > div.page > div.wrapper > main > div.row > div.col-xs-9 > ul > li"));
        for (int i = 0; i < shooroopovert_catalog_firstpage.toArray().length; ++i) {
            WebElement flag_us = shooroopovert_catalog_firstpage.get(i).findElement(By.cssSelector("item-brand-country"));

            String src = flag_us.getAttribute("src");
            String substring = "United_states";
            System.out.println(src.contains(substring));
            if (src.contains(substring)) {
                System.out.println(flag_us.findElement(By.className("s_title")).getText());
            }
        }
    }

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "C:\\tools\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.get("https://kulibin.com.ua/");

        WebElement catalog_electrotools = driver.findElement(By.cssSelector("body > div.page > div > div.nav-wrap > div > div > div.col-xs-3 > div > div > ul > li:nth-child(3) > a"));
        catalog_electrotools.click();

        WebElement shooroopovert = driver.findElement(By.cssSelector("body > div.page > div.wrapper > main > ul.article-list.row.article-list-row > li:nth-child(1) > div > div.text-box > ul > li:nth-child(24) > h4 > a"));
        shooroopovert.click();

        int i = 0;
        while (i < 4) {
            getname_usflag(driver);
            WebElement next_page = driver.findElement(By.cssSelector("body > div.page > div.wrapper > main > div.row > div.col-xs-9 > div.paging > a.next.btn-blue"));
            next_page.click();
            i++;
        }
    }
}

