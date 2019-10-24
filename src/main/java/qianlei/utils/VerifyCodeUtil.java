package qianlei.utils;

import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class VerifyCodeUtil {
    public static String createVerifyCode() {
        try (OutputStream outputStream = new FileOutputStream("verifyCode.png")) {
            Captcha captcha = new SpecCaptcha(ViewUtil.getCurFont().getSize() * 6, ViewUtil.getCurFont().getSize() * 2, 4);
            captcha.out(outputStream);
            return captcha.text();
        } catch (IOException e) {
            e.printStackTrace();
            return createVerifyCode();
        }
    }
}
