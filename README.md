# SeleniumUIAutomation

This Selenium UI Automation project is based on Java and Selenium using open sourc libraries. TestNg is used as unit testing framework.
You can run the project either in Eclipse or any other IDE or run it as a maven commandline project.
JRE System Library[JavaSE-9] used in this project. pom.xml is configured with required dependencies. Chrome or firefox browsers are needed to run the tests.

In IDE - Run it as a TestNg suite using testng.xml file. 

In Commandline as maven project- The testng.xml file is already configured in the pom.xml to execute tests automatically. 

I have created two tests which are independent of each other. There is a bit of slight performance issue with the website.
As a result, I have to increase the wait timesto solve synchronisation issues. Please increase wait times if there is any issue running the tests.

The folder Structure- 

Under the src/main/java
com.endclothing.automation.base - Contains BrowserBase.java, TestBase.java

com.endclothing.automation.config - Contains environment.properties file. 

com.endclothing.automation.listeners - Contains ExtentListener.java.

com.endclothing.automation.pages - Page Object Model java classes


Under the src/test/java
com.endclothing.automation.test - Contains test classes. 

com.endclothing.automation.reports - Contains ExtentManager.java. This along with ExtentListener.java listener class generates ExtentReports.

src/main/resources - contains log4j2.properties file. This configures Log4j to generate the logs.

Other folders - All thes folders and the files are automatically generated. 
logs- Test execution logs are generated in this folder using endclothing.log file. 
reports - ExtentReports are generated in this folder for test result
screenshots - Screenshots are automatically captured when any test result fails.





