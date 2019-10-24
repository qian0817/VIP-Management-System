package qianlei.view.component;

import com.alee.laf.text.WebTextField;
import qianlei.utils.VerifyCodeUtil;
import qianlei.utils.ViewUtil;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VerifyCodePanel extends JPanel {
    private String verifyCode = VerifyCodeUtil.createVerifyCode();
    private WebTextField field = new WebTextField(20);
    private ImageIcon image = new ImageIcon("verifyCode.png");
    private JLabel imageLabel = new JLabel(image);
    public VerifyCodePanel() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        JLabel label = new JLabel("验证码");
        field.setInputPrompt("请输入验证码");
        image.getImage().flush();
        //保证右对齐
        add(Box.createHorizontalStrut(ViewUtil.getCurFont().getSize() * 4));
        add(Box.createHorizontalStrut(ViewUtil.getCurFont().getSize()));
        add(Box.createHorizontalBox());
        add(label);
        //添加提示文字
        add(field);
        add(imageLabel);
        add(Box.createHorizontalStrut(ViewUtil.getCurFont().getSize() * 4));
        imageLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                VerifyCodePanel.this.changeVerifyCode();
            }
        });
        changeVerifyCode();
    }

    public boolean isVerifyCodeWrong() {
        return !field.getText().equalsIgnoreCase(verifyCode);
    }

    private void changeVerifyCode() {
        verifyCode = VerifyCodeUtil.createVerifyCode();
        VerifyCodePanel.this.remove(imageLabel);
        image.getImage().flush();
        VerifyCodePanel.this.add(imageLabel, 5);
        imageLabel.repaint();
        VerifyCodePanel.this.updateUI();
    }
}
