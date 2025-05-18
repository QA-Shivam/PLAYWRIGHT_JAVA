package org.example;

import com.google.gson.JsonObject;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class SampleLamdatest {

    @Test
    public void sampletest() throws UnsupportedEncodingException {
         String LAMBDATEST_USERNAME = "";
         String LAMBDATEST_ACCESS_KEY = "";
        JsonObject capabilities = new JsonObject();
        JsonObject ltOptions = new JsonObject();
        Playwright playwright = Playwright.create();
        capabilities.addProperty("browsername", "Chrome"); // Browsers allowed: `Chrome`, `MicrosoftEdge`, `pw-chromium`, `pw-firefox` and `pw-webkit`
        capabilities.addProperty("browserVersion", "latest");
        ltOptions.addProperty("platform", "Windows 10");
        ltOptions.addProperty("name", "Playwright Test");
        ltOptions.addProperty("build", "Playwright Testing in Java");
        ltOptions.addProperty("user", LAMBDATEST_USERNAME);
        ltOptions.addProperty("accessKey", LAMBDATEST_ACCESS_KEY);
        capabilities.add("LT:Options", ltOptions);

        BrowserType chromium = playwright.chromium();
        String caps = URLEncoder.encode(capabilities.toString(), "utf-8");
        String cdpUrl = "wss://cdp.lambdatest.com/playwright?capabilities=" + caps;
        Browser browser = chromium.connect(cdpUrl);
        Page page = browser.newPage();

        // Navigate to the login page
        page.navigate("https://www.orangehrm.com/");
        String title = page.title();
        System.out.println("Page title after login: " + title);
        assert (title.equals("Human Resources Management Software | OrangeHRM HR Software"));
        browser.close();
    }
}
