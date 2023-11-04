package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseTest;
import utilities.DataProviders;

public class TC_003_LoginDDT extends BaseTest {
    @Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class)
    void test_loginDDT(String email, String password, String expected){
        try{
        logger.info("**** Starting TC_003_LoginDDT ****");

        HomePage hp = new HomePage(driver);
        hp.clickMyAccount();
        logger.info("Clicked on MyAccount");
        hp.clickLogin();
        logger.info("Clicked on Login link");

        LoginPage lp = new LoginPage(driver);
        logger.info("Providing Login details");
        lp.setEmail(email);
        lp.setPassword(password);
        lp.clickLogin();
        logger.info("Clicked on Login button");

        MyAccountPage macp = new MyAccountPage(driver);

        if(expected.equals("Valid")){
            if(macp.isAccountPageExists()==true){
                macp.clickLogout();
                Assert.assertTrue(true);
            }else{
                Assert.assertTrue(false);
            }
        }

        if (expected.equals("Invalid")){
            if (macp.isAccountPageExists()==true){
                macp.clickLogout();
                Assert.assertTrue(false);
            }else{
                Assert.assertTrue(true);
            }
        }

        }catch(Exception e){
        logger.error("Test failed");
        Assert.fail();
        }

        logger.info("**** Finishing TC_003_LoginDDT ****");

    }
}
