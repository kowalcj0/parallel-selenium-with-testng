# STNG

An example project that runs JUnit tests in parallel using TestNG.
Tests are grouped by the browser in which they're going to be executed.
(have a look at the TestNG XML Suite files in src/test/resources)
To drive the browsers we use Selenium WebDriver.
Tests can be executed locally or remotely.


## Running WebDriver in parallel
Idea of parallel WebDriver execution is based on this article:
http://rationaleemotions.wordpress.com/2013/07/31/parallel-webdriver-executions-using-testng/


## Reporting
As we all know reporting is very important.
That's why in both local and remote WebDriverListeners I'm changing the name of the test method
that will appear in the final HTML report to one that also contains browser name its version and OS name.
It's very handy when you need to analyse the results.
Another very important thing is that, this report contains results from all browsers, which I found problematic when
trying to run JBehave stories in parallel with TestNG

btw. after running tests from CLI, HTML report is here:

    target/surefire-reports/index.html


## Configuration
Before you run your tests locally or remotely, you need to:

* decide in what browsers you want to run them
* configure TestNG XML suites accordingly (they are in src/test/resources)
* get the OS specific driver binaries
    * this process can be automated using Mark Collin's "selenium-standalone-server-plugin"
    * more details below


### Local configuration
Change the path to driver binaries in src/test/java/organized/chaos/LocalDriverFactory.java
Of course you can parametrize this class differently, but for now I tried to keep things simple.

Btw. there's no need to do it for Firefox as its driver binary comes together with Selenium jar.

### Remote configuration
You don't have to change anything in project, simply:

Start the hub

    java -jar selenium-server-standalone-2.39.0.jar -role hub

Then register the nodes:
FF:

    java -jar selenium-server-standalone-2.39.0.jar -role node -hub http://127.0.0.1:4444/grid/register -browser browserName=firefox,version=27,maxInstances=1,platform=LINUX

Chrome:

    java -Dwebdriver.chrome.driver="/path/to/the/chrome/driver/binary/chromedriver" -jar selenium-server-standalone-2.39.0.jar -role node -hub http://127.0.0.1:4444/grid/register -browser browserName=chrome,version=30,maxInstances=1,platform=LINUX -port 5557


## How to run tests locally from CLI

    mvn clean test

or if you want to explicitly specify the profile then:

    mvn clean test -P localRunner


## How to run tests remotely using Selenium GRID
First of all prepare your Grid environment, then:

    mvn clean test -P remoteRunner


## How to run tests from IDE
Simply right click on the "src/test/resources/org.stng.jbehave.LocalJbehaveWebDriverListener.xml"
and chose "Run ...."
Tested with IntelliJ Idea 13.0.1


## How to download WebDriver binaries automatically
This project is using Mark Collin's "selenium-standalone-server-plugin" which is a Maven plugin that can download
WebDriver binaries automatically.
The pom.xml is currently configured to download only a Chrome driver binary for 64bit Linux OSes.
If you need a different one, then change the plugin configuration or download the binary manually and set the path to it
accordingly in the:

    src/test/java/organized/chaos/LocalDriverFactory.java

If you can't download desired binary, check if the URL specified in:

    src/main/resources/RepositoryMapForMavenWebDriverBinaryDownloaderPlugin.xml

To this file and its hash are correct.


## Known issues
Dunno why I can't run tests with HTMLUnit!


## Varia
Interesting posts with some ideas that can be used later in this project:
https://groups.google.com/forum/#!search/Can$20i$20call$20JBehave$20java$20class$20in$20TestNG/selenium-users/hgHmQJPwPhg/6KJ8u7VcJD4J
http://packtlib.packtpub.com/library/9781849515740/pref05