SMART BEAR - BDD This project test features of the SmartBear website using:

Java as a programming language
Maven as a project build tool
Selenium as a UI script automation tool
Junit for its assertions
WebDriverManager/HtmlUnitDriver to set the browser for the UI automation scripts
Cucumber as the BDD framework helper
We use a page object model design pattern and maintain a separate class for each webpage. Each page class stores the members, locators, and associated methods for each webpage. This helps with refactoring.

We have a base class for the common functions, hooks class, and a very useful utility class for the repetitive methods which helps us achieve better usability.

The runner class is used to run steps classes, run execute test suites, save reports, and more.

Screenshots are captured for failed scripts and are included in Html and emailable reports generated after suite execution.

It is easy for both technical and non-technical personnel to follow along.

========================T E S T I N G========================

You can run the following Maven commands in the terminal to execute the smoke and regression suites:

SMOKE mvn clean test -Dcucumber.options=”--tags @Smoke” OR mvn clean test -D"cucumber.options.tags=@Smoke"

REGRESSION mvn clean test -Dcucumber.options=”--tags @Regression” OR mvn clean test -D"cucumber.options.tags=@Regression"