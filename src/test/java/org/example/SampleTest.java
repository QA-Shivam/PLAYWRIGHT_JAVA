package org.example;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.Test;

public class SampleTest {
    @Test
    public void demo() throws InterruptedException {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setChannel("chrome"));
        Page page = browser.newPage();

        // Navigate to the login page
        page.navigate("https://www.orangehrm.com/");
        String title = page.title();
        System.out.println("Page title after login: " + title);
        assert (title.equals("Human Resources Management Software | OrangeHRM HR Software"));
        Thread.sleep(5000);
//        browser.close();

    }
}
