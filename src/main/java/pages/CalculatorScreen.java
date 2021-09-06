package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class CalculatorScreen  extends BasePOMpage{

    @AndroidFindBy(id = "com.android.calculator2:id/digit_1")
    private AndroidElement button1;

    @AndroidFindBy(id = "com.android.calculator2:id/digit_2")
    private AndroidElement button2;

    @AndroidFindBy(id = "com.android.calculator2:id/digit_3")
    private AndroidElement button3;

    @AndroidFindBy(id = "com.android.calculator2:id/digit_4")
    private AndroidElement button4;

    @AndroidFindBy(id = "com.android.calculator2:id/digit_5")
    private AndroidElement button5;

    @AndroidFindBy(id = "com.android.calculator2:id/digit_6")
    private AndroidElement button6;

    @AndroidFindBy(id = "com.android.calculator2:id/digit_7")
    private AndroidElement button7;

    @AndroidFindBy(id = "com.android.calculator2:id/digit_8")
    private AndroidElement button8;

    @AndroidFindBy(id = "com.android.calculator2:id/digit_9")
    private AndroidElement button9;

    @AndroidFindBy(id = "com.android.calculator2:id/digit_0")
    private AndroidElement button0;

    @AndroidFindBy(id = "com.android.calculator2:id/op_add")
    private AndroidElement buttonPlus;

    @AndroidFindBy(id = "com.android.calculator2:id/eq")
    private AndroidElement buttonEqual;

    public CalculatorScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    // Click Button 1 (Digit 1)
    public void clickButton_1() {
        button1.click();
    }

    // Click Button 2 (Digit 2)
    public void clickButton_2() {
        button2.click();
    }

    // Click Plus Button(+)
    public void clickPlusButton() {
        buttonPlus.click();
    }
}