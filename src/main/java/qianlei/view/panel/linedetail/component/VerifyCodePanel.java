package qianlei.view.panel.linedetail.component;

import com.alee.laf.text.WebTextField;
import qianlei.utils.VerifyCodeUtil;
import qianlei.utils.ViewUtil;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 验证码组件
 *
 * @author qianlei
 */
public class VerifyCodePanel extends JPanel {
    private ImageIcon verifyCode;
    private final WebTextField field = new WebTextField(20);
    private final JLabel imageLabel = new JLabel();

    public VerifyCodePanel() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        JLabel label = new JLabel("验证码");
        field.setInputPrompt("请输入验证码");
        //保证右对齐
        add(Box.createHorizontalStrut(ViewUtil.getFontSize() * 4));
        add(Box.createHorizontalStrut(ViewUtil.getFontSize()));
        add(Box.createHorizontalBox());
        add(label);
        //添加提示文字
        add(field);
        add(imageLabel);
        add(Box.createHorizontalStrut(ViewUtil.getFontSize() * 4));
        imageLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                VerifyCodePanel.this.changeVerifyCode();
            }
        });
        changeVerifyCode();
    }

    /**
     * 检验验证码是否正确
     *
     * @return 验证码是否正确
     */
    public boolean isVerifyCodeWrong() {
        return !field.getText().equalsIgnoreCase(verifyCode.getDescription());
    }

    /**
     * 修改验证码图片
     */
    public void changeVerifyCode() {
        verifyCode = VerifyCodeUtil.createVerifyCode(ViewUtil.getFontSize() * 6, 50);
        VerifyCodePanel.this.remove(imageLabel);
        verifyCode.getImage().flush();
        imageLabel.setIcon(verifyCode);
        VerifyCodePanel.this.add(imageLabel, 5);
        imageLabel.repaint();
        VerifyCodePanel.this.updateUI();
    }

    public void setText(String s) {
        field.setText(s);
    }
}
