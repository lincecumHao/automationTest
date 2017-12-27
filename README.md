# automationTest

## Introduction

These files are my practice for automation testing about website.
I would like to design the testing framework to slove the testing problem.

Idea: [Selenium](http://www.seleniumhq.org/) with JAVA + [Grid](http://www.seleniumhq.org/projects/grid/) + [TestNG](http://testng.org/doc/) + data driven testing + [Extentreport](http://extentreports.com/) + log management + screenshot

###TestNG.xml
TestNG.xml is the configuration file. I use this file to set my report related information, browser type and test case.
This file also is the program entry. It can execute by eclipse-TestNG suite. 

###TestSuiteBase.java
This file is the test suite.
It include the grid setting, webdriver environment setting and extentReport setting.

###mainTestCase.Java
This file is the main test case.
It will implement and verify the login test case.

loginTestCase: It use the LoginPage module to do login test
loginDataProviderCase: It use the data driven testing module to do exception test

###LoginPage.java
This file is the login testing module.
It include webelement information and function of condition. 
For example: 
1.findElement by id or xpath
2.login/logout function

###TestReport.png
This file is the testing report.
It can present the testing result.
This module is [Extentreport](http://extentreports.com/)
