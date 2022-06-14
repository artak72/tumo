import POM.TumoPage;
import org.testng.annotations.Test;

public class TumoTest extends BaseTest {
    private TumoPage tumoPage = new TumoPage(driver);

    @Test
    public void exc() {
        tumoPage.login("artak.militosyan", "Artak.1996")
                .goToExaminationPage()
                .findActivity("Elements of Programming")
                .selectFirstStudent()
                .checkLink();
    }
}
