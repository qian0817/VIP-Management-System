package qianlei;

import com.alee.laf.WebLookAndFeel;
import com.alibaba.fastjson.JSON;
import qianlei.utils.DaoUtil;
import qianlei.utils.ViewUtil;
import qianlei.view.LoginFrame;

import javax.swing.*;
import java.awt.*;
import java.io.*;

/**
 * 启动类
 *
 * @author qianlei
 */
public class Application {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(WebLookAndFeel::install);
        t.start();
        ViewUtil.setFont(getFont());
        setFont(ViewUtil.getFont());
        DaoUtil.init("main.db");
        t.join();
        new LoginFrame().setVisible(true);
    }

    @SuppressWarnings("all")
    private static Font getFont() {
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
        return font;
    }


    private static void setFont(Font font) {
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
}
