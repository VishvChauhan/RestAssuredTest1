package com.SDET.utilities;

import org.apache.commons.lang3.RandomStringUtils;

public class RestUtils {
    public static String userName(String UserName){
        String GenratedUserName = UserName + RandomStringUtils.randomAlphabetic(1);
        return GenratedUserName;
    }

}
