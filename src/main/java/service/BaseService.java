package service;

import org.openqa.selenium.WebDriver;
import utils.DriverUtils;

import java.util.Random;

public class BaseService {

    private WebDriver driver;
    private static final String credentials = "src/main/resources/user.properties";
    protected Random random;

    public BaseService() {
        this.driver = DriverUtils.getDriver();
    }

    public static String getCredentials() {
        return credentials;
    }

}