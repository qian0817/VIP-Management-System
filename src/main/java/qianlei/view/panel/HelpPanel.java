package qianlei.view.panel;

import qianlei.utils.Log;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

/**
 * 帮助界面
 *
 * @author qianlei
 */
public class HelpPanel extends AbstractCanInitPanel {
    private final JEditorPane editorPane = new JEditorPane();
    private final JScrollPane scrollPane = new JScrollPane(editorPane);

    public HelpPanel() {
        setLayout(new BorderLayout());
        editorPane.setEditable(false);
        initView();
    }

    @Override
    public void initView() {
        SwingUtilities.invokeLater(() -> {
            try {
                URL url = getClass().getClassLoader().getResource("help.html");
                if (url != null) {
                    editorPane.setPage(url);
                } else {
                    editorPane.setText("未找到帮助页面");
                }
                removeAll();
                add(scrollPane);
            } catch (IOException e) {
                Log.error(Thread.currentThread(), e);
            }
        });
    }
}
