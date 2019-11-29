package qianlei.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 验证码辅助类
 *
 * @author qianlei
 */
public final class VerifyCodeUtil {
    /**
     * 验证码中可以出现的字符
     */
    private static final String VERIFY_CODES = "23456789abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ";
    /**
     * 验证码干扰线条数
     */
    private static final int NUMBER_OF_INTERFERING_LINE = 30;
    /**
     * 验证码长度
     */
    private static final int NUMBER_OF_CHARS = 4;

    private VerifyCodeUtil() {
    }

    public static ImageIcon createVerifyCode(int width, int height) {
        Random random = new Random();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();
        //背景填充为白色
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, width, height);
        //设置字体
        Font font = new Font(ViewUtil.getSupportedFont().get(random.nextInt(ViewUtil.getSupportedFont().size())), Font.BOLD, height / 2);
        graphics.setFont(font);
        //生成干扰线
        for (int i = 0; i < NUMBER_OF_INTERFERING_LINE; i++) {
            // 产生随机的颜色分量来构造颜色值
            graphics.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(40) - 20;
            int yl = random.nextInt(40) - 20;
            graphics.drawLine(x, y, xl + x, yl + y);
        }
        StringBuilder verifyCode = new StringBuilder();
        for (int i = 0; i < NUMBER_OF_CHARS; i++) {
            char c = VERIFY_CODES.charAt(random.nextInt(VERIFY_CODES.length()));
            // 得到随机产生的验证码数字。
            verifyCode.append(c);
            graphics.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
            graphics.drawString(String.valueOf(c), i * width / 4, random.nextInt(height / 2) + height / 2);
        }
        return new ImageIcon(image, verifyCode.toString());
    }
}
