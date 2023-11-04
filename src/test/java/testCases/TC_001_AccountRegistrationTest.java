package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseTest;

public class TC_001_AccountRegistrationTest extends BaseTest {
    @Test(groups={"Regression", "Master"})
    public void testAcctRegistration(){
        logger.debug("application logs......");
        logger.info("*** Starting TC_001_AccountRegistrationTest ***");
        try {
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            logger.info("Clicked on My Account link");
            hp.clickRegister();
            logger.info("Clicked on Register link");

            AccountRegistrationPage regPage = new AccountRegistrationPage(driver);
            logger.info("Providing customer data");
            regPage.setFirstName(randomString());
            regPage.setLastName(randomString()+randomNum());
            regPage.setEmail(randomString()+"@gmail.com");
            regPage.setTelephone(randomNum());
            regPage.setPassword("test@123");
            regPage.setConfirmPassword("test@123");
            regPage.setPrivacyPolicy();
            regPage.clickContinue();
            logger.info("Clicked on Continue");

            logger.info("Validating expected message");
            Assert.assertEquals(regPage.getConfirmationMsg(), "Your Account Has Been Created!", "Didn't get the expected message");
        }
        catch(Exception e){
            logger.error("Test failed");
            Assert.fail();
        }

        logger.info("*** Finishing TC_001_AccountRegistrationTest ***");
    }

}
