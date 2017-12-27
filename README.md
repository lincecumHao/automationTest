# automationTest

## Introduction

These code segment are my practice for automation testing about website.

I would like to design the testing framework to slove the testing problems.

Idea: [Selenium](http://www.seleniumhq.org/) with JAVA + [Grid](http://www.seleniumhq.org/projects/grid/) + [TestNG](http://testng.org/doc/) + data driven testing + [Extentreport](http://extentreports.com/) + log management + screenshot

### TestNG.xml
TestNG.xml is the configuration file. I use this file to set my report related information, browser type and test case.<br>
This file is also the program entry. It can be executed via the eclipse-TestNG suite. <br><br>

### TestSuiteBase.java
This file is the test suite.<br>
It includes the grid setting, webdriver environment setting and extentReport setting.<br><br>

### mainTestCase.Java
This file is the main test case.<br>
It will implement and verify the login test case.<br>

loginTestCase: It uses the LoginPage module to run login test<br>
loginDataProviderCase: It uses the data driven testing module to run exception test<br>

### LoginPage.java
This file is the login testing module.<br>
It includes information of WebElements and functions. <br>
For example: <br>
1.findElement by id or xpath<br>
2.login/logout function<br><br>

### TestReport.png
This picture is the testing result.<br>
After the testing script has been executed, it will produce the testing report.<br>
This module is [Extentreport](http://extentreports.com/)
