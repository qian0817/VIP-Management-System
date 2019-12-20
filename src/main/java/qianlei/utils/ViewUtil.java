package qianlei.utils;

import com.alee.api.resource.ClassResource;
import com.alee.extended.svg.SvgIcon;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import qianlei.Application;
import qianlei.entity.Config;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * 界面的工具类
 *
 * @author qianlei
 */
public final class ViewUtil {

    private static Config curConfig;
    private static List<String> supportFonts;

    private ViewUtil() {
    }

    public static Config getCurConfig() {
        return curConfig;
    }

    /**
     * 加载配置文件
     *
     * @param configPath 字体配置文件路径
     */
    public static void loadFont(String configPath) {
        Config config;
        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(new File(configPath)))) {
            config = JSON.parseObject(inputStream, StandardCharsets.UTF_8, Config.class);
        } catch (Exception ignored) {
            config = new Config();
        }
        curConfig = config;
        changeFont(config.getFont());
    }

    /**
     * 写配置文件
     */
    private static void writeConfig() {
        try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(new File("config.json")))) {
            String s = JSON.toJSONString(curConfig, SerializerFeature.PrettyFormat);
            outputStream.write(s.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            Log.error(Thread.currentThread(), e);
        }
    }

    /**
     * 修改字体
     *
     * @param font 字体
     */
    public static void changeFont(Font font) {
        curConfig.setFont(font);
        new Thread(ViewUtil::writeConfig).start();
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
        List<String> ret = new ArrayList<>();
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
        return getSvgIcon(fileName, curConfig.getFont().getSize(), curConfig.getFont().getSize());
    }

    public static SvgIcon getSvgIcon(String fileName, int width, int height) {
        ClassResource resource = new ClassResource(Application.class, "/icon/" + fileName);
        return new SvgIcon(resource, width, height);
    }

    public static int getFontSize() {
        return curConfig.getFont().getSize();
    }

}
