package com.SDET.base;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {

    public int RESPONSE_STATUS_CODE_200 = 200;
    public int RESPONSE_STATUS_CODE_201 = 201;
    public int RESPONSE_STATUS_CODE_204 = 204;

    public Properties prop;

    public TestBase() {
         prop = new Properties();
        try {
            FileInputStream ip = new FileInputStream("src/test/resources/config.properties");
            prop.load(ip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
