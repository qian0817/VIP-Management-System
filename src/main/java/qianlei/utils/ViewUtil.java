package qianlei.utils;

import com.alibaba.fastjson.JSON;

import javax.swing.*;
import java.awt.*;
import java.io.*;

/**
 * 界面的工具类
 *
 * @author qianlei
 */
public class ViewUtil {
    private static Font curFont;

    public static Font getCurFont() {
        return curFont;
    }

    @SuppressWarnings("all")
    public static void loadFont(String configPath) {
        Font font;
        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(new File("config.json")))) {
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            font = JSON.parseObject(bytes, Font.class);
        } catch (Exception e) {
            e.printStackTrace();
            font = new Font("微软雅黑", Font.PLAIN, 20);
        }
        try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(new File("config.json")))) {
            String s = JSON.toJSONString(font);
            outputStream.write(s.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        curFont = font;
        setViewFont();
    }

    /**
     * 设置界面的字体
     */
    private static void setViewFont() {
        UIManager.put("ToolTip.font", curFont);
        UIManager.put("Table.font", curFont);
        UIManager.put("TableHeader.font", curFont);
        UIManager.put("TextField.font", curFont);
        UIManager.put("ComboBox.font", curFont);
        UIManager.put("TextField.font", curFont);
        UIManager.put("PasswordField.font", curFont);
        UIManager.put("TextArea.font", curFont);
        UIManager.put("TextPane.font", curFont);
        UIManager.put("EditorPane.font", curFont);
        UIManager.put("FormattedTextField.font", curFont);
        UIManager.put("Button.font", curFont);
        UIManager.put("CheckBox.font", curFont);
        UIManager.put("RadioButton.font", curFont);
        UIManager.put("ToggleButton.font", curFont);
        UIManager.put("ProgressBar.font", curFont);
        UIManager.put("DesktopIcon.font", curFont);
        UIManager.put("TitledBorder.font", curFont);
        UIManager.put("Label.font", curFont);
        UIManager.put("List.font", curFont);
        UIManager.put("TabbedPane.font", curFont);
        UIManager.put("MenuBar.font", curFont);
        UIManager.put("Menu.font", curFont);
        UIManager.put("MenuItem.font", curFont);
        UIManager.put("PopupMenu.font", curFont);
        UIManager.put("CheckBoxMenuItem.font", curFont);
        UIManager.put("RadioButtonMenuItem.font", curFont);
        UIManager.put("Spinner.font", curFont);
        UIManager.put("Tree.font", curFont);
        UIManager.put("ToolBar.font", curFont);
        UIManager.put("OptionPane.messageFont", curFont);
        UIManager.put("OptionPane.buttonFont", curFont);
    }

}
