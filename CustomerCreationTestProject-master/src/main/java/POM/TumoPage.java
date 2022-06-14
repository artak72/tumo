package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class TumoPage extends BasePage {
    @FindBy(id = "input-16")
    private WebElement loginField;

    @FindBy(id = "input-20")
    private WebElement passwordField;

    @FindBy(id = "activity-manager-login-form-card-login-button")
    private WebElement submit;

    @FindBy(css = "[class='home-page-buttons']>button:nth-child(2)")
    private WebElement examinationsButton;

    @FindBy(id = "input-49")
    private WebElement searchField;

    @FindBy(css = "[class='activity-filter-list-item']")
    private WebElement select;

    @FindBy(css = "[class='examination-list-table-list-item']:nth-child(1)")
    private WebElement firstUser;

    @FindBy(css = "[class='examiner-task-body']:nth-child(2)>div>div>a")
    private List<WebElement> links;

    @FindBy(id = "dialogDoneText")
    private WebElement doneText;

    @FindBy(css = "[class='primary']:nth-child(1)")
    private WebElement run;


    public TumoPage(WebDriver driver) {
        super(driver);
    }

    public TumoPage login(String login, String password) {
        waitForPageToLoad();
        click(loginField);
        type(loginField, login);
        click(passwordField);
        type(passwordField, password);
        click(submit);
        return this;
    }

    public TumoPage goToExaminationPage() {
        click(examinationsButton);
        waitForPageToLoad();
        return this;
    }

    public TumoPage findActivity(String activityName) {
        type(searchField, activityName);
        click(select);
        return this;
    }

    public TumoPage selectFirstStudent() {
        waitForPageToLoad();
        forceWait(3000);
        waitForElementToBeClickable(firstUser);
        forceWait(2000);
        click(firstUser);
        waitForPageToLoad();
        return this;
    }

    public TumoPage checkLink() {
        waitForPageToLoad();
        forceWait(3000);
        waitForElementToBeVisible(links.get(0));
        String s = links.get(0).getText();
        if (s.contains("level=")) {
            String[] arr = s.split("level=");
            String s1 = arr[1];
            String[] arr1 = s1.split("&");
            if (Integer.parseInt(arr1[0]) > 3) {
                click(links.get(0));
                waitForPageToLoad();
                forceWait(10000);
                waitForPageToLoad();
                waitForElementToBeClickable(run);
                run.click();
                boolean flag = true;
                int i = 0;
                while (flag) {
                    i++;
                    forceWait(1000);
                    String done = doneText.getText();
                    if (!(done.equals(""))) {
                        flag = false;
                        System.out.println(done);
                    } else if (i == 60) {
                        flag = false;
                        System.out.println("false");
                    }
                }

            }
        }
        return this;
    }

}
