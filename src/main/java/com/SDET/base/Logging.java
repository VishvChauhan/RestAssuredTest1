package com.SDET.base;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public interface Logging {

    String VERIFY_MESSAGE = "Verify that %s in db equals = %s";
    String ASSERT_MESSAGE = "Check that %s value from DB equals with value in XML file.";

     default Logger getLogger(){
        return LogManager.getLogger(this.getClass());
    }

}
