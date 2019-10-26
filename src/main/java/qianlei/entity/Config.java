package qianlei.entity;

import java.awt.*;

/**
 * 配置文件
 *
 * @author qianlei
 */
public class Config {
    private Font font;
    private String skin;

    public Config(Font font, String skin) {
        this.font = font;
        this.skin = skin;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public String getSkin() {
        return skin;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }

    @Override
    public String toString() {
        return "Config{" +
                "font=" + font +
                ", skin='" + skin + '\'' +
                '}';
    }
}
