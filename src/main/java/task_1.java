import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.List;


@Test
public class task_1 {


    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\tools\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.get("https://kulibin.com.ua/");

        WebElement catalog_electrotools = driver.findElement(By.cssSelector("body > div.page > div > div.nav-wrap > div > div > div.col-xs-3 > div > div > ul > li:nth-child(3) > a"));
        catalog_electrotools.click();

        WebElement drell = driver.findElement(By.cssSelector("body > div.page > div.wrapper > main > ul.article-list.row.article-list-row > li:nth-child(1) > div > div.text-box > ul > li:nth-child(3) > h4 > a"));
        drell.click();

        List<WebElement> drell_catalog = driver.findElements(By.cssSelector("body > div.page > div.wrapper > main > div.row > div.col-xs-9 > ul > li"));


        for (int i = 0; i < drell_catalog.toArray().length; ++i) {
            String old_prices = "";
            String new_prices = "";
            String discount = drell_catalog.get(i).findElement(By.className("stick-list")).getText();
            if (discount.length() != 5) {
                continue;
            }

            try {
                old_prices = drell_catalog.get(i).findElement(By.className("old-price")).getText();
                new_prices = drell_catalog.get(i).findElement(By.className("price")).getText();

            } catch (NoSuchElementException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("Старая цена: " + old_prices);
            System.out.println("Скидочная цена: " + new_prices);

        }
    }
}


