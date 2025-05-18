package org.example;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;

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
        page.locator("a").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^Company$"))).click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Book a Free Demo")).click();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Contact Sales")).click();
        page.locator("a").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^Resources$"))).click();
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("eBooks")).click();
        page.locator("a").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^Company$"))).click();
        page.locator("a").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^Solutions$"))).click();
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("OrangeHRM Logo")).click();
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Pricing")).click();
        page.locator("a").filter(new Locator.FilterOptions().setHasText(Pattern.compile("^Why OrangeHRM$"))).click();
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Flexible Hosting")).click();
        browser.close();

    }
}
