# TR_ui
Test fraimwork for UI tests of TopReviews project

For creating this project i picked next stack: Java, Selenium WebDriver, TestNG, Maven.
Also i choose Pege Object pattern for implementation - http://www.seleniumhq.org/docs/06_test_design_considerations.jsp

Fraimwork contain five modules:
1. BrowserFactory for manage browseres (Chorme, FireFox, Edge).
    
2. PageRepository - contain TR site pages(main, review etc). One page equels one java class. In each page_class stored locators to web elements of the page and methods for work with it.

3. Suites of test keyses - tests for verify main logic of the project.

4. Reporting - for generate and manage reports after tests complite.
   For report generate used ExtentReport lib http://extentreports.relevantcodes.com/java/

5. Helper with read/write exel table - for save and getting test data
   For read\write test data used apach.poi api wich can connect with exel table http://poi.apache.org/
   

