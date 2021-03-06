package org.jbehave.tutorials.etsy.pages.fluent;

import org.jbehave.tutorials.etsy.pages.SearchResults;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.WebElement;
import org.seleniumhq.selenium.fluent.FluentMatcher;
import org.seleniumhq.selenium.fluent.FluentWebElements;

import static org.openqa.selenium.By.className;
import static org.openqa.selenium.By.xpath;

public class FluentSearchResults extends FluentPage implements SearchResults {

    public FluentSearchResults(WebDriverProvider webDriverProvider) {
        super(webDriverProvider);
    }

    public String buyFirst(String thing) {
        getResultElements().first(lowerCaseTitleContaining(thing)).click();
        int ix = getCurrentUrl().indexOf("/listing/") + 9;
        String id = getCurrentUrl().substring(ix, ix + 8);
        // id.isNumber().shouldBe true, "no listing found";
        input(xpath("@value = 'Add to Cart'")).click();
        return id;
    }

    public  int resultsFound() {
        return getResultElements().size();
    }


    private FluentWebElements getResultElements() {
        return links(className("listing-thumb"));
    }

    private FluentMatcher lowerCaseTitleContaining(final String thing) {
        return new FluentMatcher() {
            public boolean matches(WebElement webElement) {
                return webElement.getAttribute("title").toLowerCase().contains(thing);
            }
        };
    }

}
