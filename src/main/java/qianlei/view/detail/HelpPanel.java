package qianlei.view.detail;

import qianlei.utils.ViewUtil;
import qianlei.view.MainFrame;
import qianlei.view.component.ComboPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

public class HelpPanel extends JPanel {
    public HelpPanel() {
        setLayout(new BorderLayout());
        List<String> fontList = ViewUtil.getSupportedFont();
        ComboPanel<String> fontFamilyChoosePanel = new ComboPanel<>("字体", fontList);
        ComboPanel<Integer> fontSizeChoosePanel = new ComboPanel<>("大小", Arrays.asList(8, 9, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 36, 48));
        JButton checkButton = new JButton("确认");
        fontFamilyChoosePanel.setSelectItem(ViewUtil.getCurFont().getFontName());
        fontSizeChoosePanel.setSelectItem(ViewUtil.getCurFont().getSize());
        JPanel selectFontPanel = new JPanel();
        checkButton.addActionListener((e) -> {
            String fontFamily = fontFamilyChoosePanel.getSelect();
            int fontSize = fontSizeChoosePanel.getSelect();
            ViewUtil.changeFont(new Font(fontFamily, Font.PLAIN, fontSize));
            MainFrame.getMainFrame().dispose();
            MainFrame.getMainFrame().init();
            MainFrame.getMainFrame().setVisible(true);
        });
        selectFontPanel.add(fontFamilyChoosePanel);
        selectFontPanel.add(fontSizeChoosePanel);
        selectFontPanel.add(checkButton);
        add(selectFontPanel, BorderLayout.NORTH);
        JButton jButton = new JButton("帮助");
        jButton.addActionListener(new ActionListener() {
            @SuppressWarnings("all")
            @Override
            public void actionPerformed(ActionEvent e) {
                try (
                        BufferedInputStream inputStream = new BufferedInputStream(getClass().getClassLoader().getResourceAsStream("help.html"));
                        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream("help.html"))
                ) {
                    byte[] bytes = new byte[inputStream.available()];
                    inputStream.read(bytes);
                    outputStream.write(bytes);
                    URI uri = new URI("help.html");
                    Desktop.getDesktop().browse(uri);
                } catch (IOException | URISyntaxException ex) {
                    ex.printStackTrace();
                }
            }
        });
        add(jButton);
    }
}
