package qianlei.entity;

import java.awt.*;

/**
 * 配置文件
 * 暂时只有字体的配置
 *
 * @author qianlei
 */
public class Config {
    private Font font;

    public Config() {
        font = new Font("微软雅黑", Font.PLAIN, 20);
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }
}
