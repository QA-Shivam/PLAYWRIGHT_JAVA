package org.example;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

public class SampleTest {
    @Test
    public void demo() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setChannel("chrome"));

        Browser.NewContextOptions contextOptions = new Browser.NewContextOptions()
                .setRecordVideoDir(Paths.get("videos/"))
                .setRecordVideoSize(1280, 720);

        BrowserContext context = browser.newContext(contextOptions);
        Page page = context.newPage();

        // Navigate to the login page
        page.navigate("https://www.orangehrm.com/");
        String title = page.title();
        System.out.println("Page title after login: " + title);
        assert (title.equals("Human Resources Management Software | OrangeHRM HR Software"));
        browser.close();

    }
}
