package qianlei.utils;

import com.alee.extended.svg.SvgIcon;
import com.alee.managers.style.StyleManager;
import com.alee.skin.dark.DarkSkin;
import com.alee.skin.web.WebSkin;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import qianlei.entity.Config;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

/**
 * 界面的工具类
 *
 * @author qianlei
 */
public final class ViewUtil {
    public static final String DARK_SKIN = "暗色模式";
    public static final String LIGHT_SKIN = "亮色模式";

    private static Config curConfig;
    private static List<String> supportFonts;

    private ViewUtil() {
    }

    public static Config getCurConfig() {
        return curConfig;
    }

    /**
     * 加载字体配置文件
     *
     * @param configPath 字体配置文件路径
     */
    public static void loadFont(String configPath) {
        Config config;
        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(new File(configPath)))) {
            config = JSON.parseObject(inputStream, Config.class);
        } catch (Exception e) {
            config = new Config(new Font(getSupportedFont().get(0), Font.PLAIN, 20), "亮色模式");
        }
        if (config == null) {
            config = new Config(new Font(getSupportedFont().get(0), Font.PLAIN, 20), "亮色模式");
        }
        if (config.getFont() == null) {
            config.setFont(new Font(getSupportedFont().get(0), Font.PLAIN, 20));
        }
        if (config.getSkin() == null) {
            config.setSkin("亮色模式");
        }
        curConfig = config;
        changeFont(config.getFont());
        changeSkin(config.getSkin());
    }

    /**
     * 写入字体配置文件
     */
    private static void writeFontConfig() {
        try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(new File("config.json")))) {
            String s = JSON.toJSONString(curConfig, SerializerFeature.PrettyFormat);
            outputStream.write(s.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            LogUtil.error(e);
        }
    }

    /**
     * 修改界面主题
     *
     * @param skin 皮肤名称
     */
    public static void changeSkin(String skin) {
        if (DARK_SKIN.equals(skin)) {
            StyleManager.setSkin(new DarkSkin());
        } else {
            StyleManager.setSkin(new WebSkin());
            skin = LIGHT_SKIN;
        }
        curConfig.setSkin(skin);
        new Thread(ViewUtil::writeFontConfig).start();
    }

    /**
     * 修改字体
     *
     * @param font 字体
     */
    public static void changeFont(Font font) {
        curConfig.setFont(font);
        new Thread(ViewUtil::writeFontConfig).start();
        setViewFont(curConfig.getFont());
    }

    /**
     * 获取支持中文的字体
     *
     * @return 支持中文的字体的集合
     */
    public static List<String> getSupportedFont() {
        if (supportFonts != null) {
            return supportFonts;
        }
        List<String> ret = new LinkedList<>();
        String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        for (String s : fonts) {
            Font f = new Font(s, Font.PLAIN, 20);
            if (f.canDisplay('认')) {
                ret.add(s);
            }
        }
        supportFonts = ret;
        return ret;
    }

    /**
     * 设置界面的字体
     */
    private static void setViewFont(Font font) {
        UIManager.put("ToolTip.font", font);
        UIManager.put("Table.font", font);
        UIManager.put("TableHeader.font", font);
        UIManager.put("TextField.font", font);
        UIManager.put("ComboBox.font", font);
        UIManager.put("TextField.font", font);
        UIManager.put("PasswordField.font", font);
        UIManager.put("TextArea.font", font);
        UIManager.put("TextPane.font", font);
        UIManager.put("EditorPane.font", font);
        UIManager.put("FormattedTextField.font", font);
        UIManager.put("Button.font", font);
        UIManager.put("CheckBox.font", font);
        UIManager.put("RadioButton.font", font);
        UIManager.put("ToggleButton.font", font);
        UIManager.put("ProgressBar.font", font);
        UIManager.put("DesktopIcon.font", font);
        UIManager.put("TitledBorder.font", font);
        UIManager.put("Label.font", font);
        UIManager.put("List.font", font);
        UIManager.put("TabbedPane.font", font);
        UIManager.put("MenuBar.font", font);
        UIManager.put("Menu.font", font);
        UIManager.put("MenuItem.font", font);
        UIManager.put("PopupMenu.font", font);
        UIManager.put("CheckBoxMenuItem.font", font);
        UIManager.put("RadioButtonMenuItem.font", font);
        UIManager.put("Spinner.font", font);
        UIManager.put("Tree.font", font);
        UIManager.put("ToolBar.font", font);
        UIManager.put("OptionPane.messageFont", font);
        UIManager.put("OptionPane.buttonFont", font);
    }

    public static SvgIcon getSvgIcon(String fileName) {
        return getSvgIcon(fileName, curConfig.getFont().getSize() * 2, curConfig.getFont().getSize() * 2);
    }

    public static SvgIcon getSvgIcon(String fileName, int width, int height) {
        return new SvgIcon(ViewUtil.class.getClassLoader().getResource(fileName), width, height);
    }

    public static int getFontSize() {
        return curConfig.getFont().getSize();
    }

}
