package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseTest;

public class TC_002_LoginTest extends BaseTest {
    @Test(groups={"Sanity", "Master"})
    public void test_Login(){
        try {
            logger.info("**** Starting TC_002_LoginTest ****");

            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            logger.info("Clicked on MyAccount");
            hp.clickLogin();
            logger.info("Clicked on Login link");

            LoginPage lp = new LoginPage(driver);
            logger.info("Providing Login details");
            lp.setEmail(rb.getString("email")); //valid email, get it from config.properties file
            lp.setPassword(rb.getString("password")); //valid password, get it from config.properties file
            lp.clickLogin();
            logger.info("Clicked on Login button");

            MyAccountPage macp = new MyAccountPage(driver);
            Assert.assertEquals(macp.isAccountPageExists(), true, "Invalid Login data");
        }catch(Exception e){
            logger.error("Test failed");
            Assert.fail();
        }

        logger.info("**** Finishing TC_002_LoginTest ****");

    }
}
