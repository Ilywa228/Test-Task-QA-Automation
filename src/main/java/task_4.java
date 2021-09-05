import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Test
public class task_4 {
    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "C:\\tools\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.get("https://kulibin.com.ua/");

        WebElement catalog_electrotools = driver.findElement(By.cssSelector("body > div.page > div > div.nav-wrap > div > div > div.col-xs-3 > div > div > ul > li:nth-child(3) > a"));
        catalog_electrotools.click();

        WebElement bolgarka = driver.findElement(By.cssSelector("body > div.page > div.wrapper > main > ul.article-list.row.article-list-row > li:nth-child(1) > div > div.text-box > ul > li:nth-child(1) > h4 > a"));
        bolgarka.click();

        List<WebElement> bolgarka_catalog = driver.findElements(By.cssSelector("body > div.page > div.wrapper > main > div.row > div.col-xs-9 > ul > li"));


        for (int i = 0; i < bolgarka_catalog.toArray().length; ++i) {
            String old_prices = "";
            String new_prices = "";
            String discount = bolgarka_catalog.get(i).findElement(By.className("stick-list")).getText();
            if (discount.length() != 5) {
                continue;
            }

            try {
                old_prices = bolgarka_catalog.get(i).findElement(By.className("old-price")).getText();
                new_prices = bolgarka_catalog.get(i).findElement(By.className("price")).getText();

            } catch (NoSuchElementException e) {
                System.out.println(e.getMessage());
            }
            Pattern pattern = Pattern.compile("(\\d+)");
            Pattern pattern_2 = Pattern.compile("((\\d{1,})(\\s{1,})?){1,}");
            Matcher disc = pattern.matcher(discount);
            Matcher oldprice = pattern_2.matcher(old_prices);

            if (disc.find() | oldprice.find()) {

                String names = bolgarka_catalog.get(i).findElement(By.className("title")).getText();
                String Oldprice = oldprice.group().replaceAll("\\s+", "");

                int num_discount = Integer.parseInt(disc.group());
                int num_old_prices = Integer.parseInt(Oldprice);

                double sale_prices = num_old_prices - (num_old_prices * num_discount * 0.01);

                Assert.assertEquals(sale_prices, new_prices);

                System.out.println(names);
                System.out.println("Цена расчитаная по скидке: " + sale_prices + " " + "грн.");
                System.out.println("Фактическая цена: " + new_prices);
            }
        }
    }
}

