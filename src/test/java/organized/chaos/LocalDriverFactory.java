package organized.chaos;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

/**
 * Author: Confusions Personified
 * src: http://rationaleemotions.wordpress.com/2013/07/31/parallel-webdriver-executions-using-testng/
 */
public class LocalDriverFactory {

    static Logger log = Logger.getLogger(LocalDriverFactory.class);

    static WebDriver createInstance(String browserName) {
        WebDriver driver = null;
        if (browserName.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
            return driver;
        }
        if (browserName.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "binaries/linux/googlechrome/64bit/2.9/chromedriver");
            driver = new ChromeDriver();
            return driver;
        }
        if (browserName.equalsIgnoreCase("ie")) {
            driver = new InternetExplorerDriver();
            return driver;
        }
        if (browserName.equalsIgnoreCase("htmlUnit")) {
            driver = new HtmlUnitDriver();
            return driver;
        }
        if (browserName.equalsIgnoreCase("htmlUnitWithJs")) {
            driver = new HtmlUnitDriver(true);
            return driver;
        }
        if (browserName.toLowerCase().contains("safari")) {
            driver = new SafariDriver();
            return driver;
        }
        /*
        These browsers are not supported yet by this project.
        It's just a matter of adding more browser specific dependencies and it should work fine :)
        if (browserName.toLowerCase().contains("opera")) {
            driver = new OperaDriver();
            return driver;
        }
        if (browserName.toLowerCase().contains("phantomjs")) {
            driver = new ();
            return driver;
        }
        if (browserName.toLowerCase().contains("android")) {
            driver = new ();
            return driver;
        }
        if (browserName.toLowerCase().contains("ipad")) {
            driver = new ();
            return driver;
        }
        if (browserName.toLowerCase().contains("iphone")) {
            driver = new ();
            return driver;
        }
        */
        log.info("LocalDriverFactory created aa instance of WebDriver for: " + browserName);
        return driver;
    }
}