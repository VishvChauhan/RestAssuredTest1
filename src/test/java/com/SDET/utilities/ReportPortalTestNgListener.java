/*
package utils;

import com.epam.reportportal.listeners.Statuses;
import com.epam.reportportal.testng.BaseTestNGListener;
import com.epam.reportportal.testng.ITestNGService;
import com.epam.reportportal.testng.TestNGService;
import com.google.common.io.BaseEncoding;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import rp.com.google.common.base.Supplier;
import rp.com.google.common.base.Suppliers;

import java.io.File;
import java.io.IOException;

@Log4j2
public class ReportPortalTestNgListener extends BaseTestNGListener implements IInvokedMethodListener {

    private static final Supplier<ITestNGService> SERVICE = Suppliers.memoize(TestNGService::new);
    private static final Logger LOGGER = LoggerFactory.getLogger("binary_data_logger");

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
NOP


    }

*
     * Attach screenshot if test method failed
     *
     * @param method     method
     * @param testResult test result


    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if ((method.isTestMethod() && !testResult.isSuccess()) || (method.isConfigurationMethod() && !testResult.isSuccess())) {
            log.warn("Failed test - trying make screenshot. Method - {}", testResult.getName());
            try {
                if (testResult.getInstance() instanceof BaseTestCase) {
                    File file = ((BaseTestCase) testResult.
                            getTestContext().getAllTestMethods()[0].getInstance()).getContext().makeScreenshot();
                    try {
                        if (file != null) {
                            log.info("Screenshot name - " + file.getName());
                            log.error("Attached screenshot.", file);
                            LOGGER.error("RP_MESSAGE#BASE64#{}#{}",
                                    BaseEncoding.base64().encode(FileUtils.readFileToByteArray(file)), "Attached screenshot.");
                            testResult.setAttribute("screen", file);
                        }
                    } catch (IOException e) {
                        log.info("Can't take screenshot.");
                    }
                }
            } catch (Exception e) {
                log.debug("Error when make screenshot...");
            }
            log.warn("Screenshot made. Method name - {}", testResult.getName());
        }
    }

    public ReportPortalTestNgListener() {
        super(SERVICE.get());
    }

    @Override
    public void onConfigurationSkip(ITestResult testResult) {
        log.info("On configuration skip - {}", testResult.getName());
        SERVICE.get().finishTestMethod(Statuses.SKIPPED, testResult);
    }

    @Override
    public void onTestStart(ITestResult testResult) {
        try {
            log.info("On test start - {}", testResult.getName());
            super.onTestStart(testResult);
        } catch (Exception e) {
            log.error("On test start error - {}", e.getMessage());
            SERVICE.get().startTestMethod(testResult);
        }
    }

    @Override
    public void onTestFailure(ITestResult testResult) {
        try {
            log.info("On test failure - {}", testResult.getName());
            super.onTestFailure(testResult);
        } catch (Exception e) {
            log.error("On test failure completed with error - {}", e.getMessage());
            SERVICE.get().finishTestMethod(Statuses.FAILED, testResult);
        }
    }

    @Override
    public void onTestSkipped(ITestResult testResult) {
        try {
            log.info("On test skip - {}", testResult.getName());
            super.onTestSkipped(testResult);
        } catch (Exception e) {
            log.error("On test skipped finished with error - {}", e.getMessage());
            SERVICE.get().finishTestMethod(Statuses.SKIPPED, testResult);
        }
    }

    @Override
    public void onTestSuccess(ITestResult testResult) {
        try {
            log.info("On test success - {}", testResult.getName());
            super.onTestSuccess(testResult);
        } catch (Exception e) {
            log.error("On test success finished with error - {}", e.getMessage());
            SERVICE.get().finishTestMethod(Statuses.PASSED, testResult);
        }
    }
}

*/
