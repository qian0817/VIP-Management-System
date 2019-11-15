package qianlei.entity;

import java.awt.*;

/**
 * 配置文件
 *
 * @author qianlei
 */
public class Config {
    private Font font;

    public Config(Font font) {
        this.font = font;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }
}
