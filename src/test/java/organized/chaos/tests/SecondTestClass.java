package organized.chaos.tests;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import organized.chaos.DriverManager;


public class SecondTestClass {

    static Logger log = Logger.getLogger(SecondTestClass.class);

    @Test
    public void testMethod3() {
        invokeBrowser("http://www.google.com");
    }

    @Test
    public void testMethod4() {
        invokeBrowser("http://www.google.pl");

    }

    private void invokeBrowser(String url) {
        log.info("Thread id = " + Thread.currentThread().getId());
        log.info("Hash code of webDriver instance = " + DriverManager.getDriver().hashCode());
        DriverManager.getDriver().get(url);
    }
}