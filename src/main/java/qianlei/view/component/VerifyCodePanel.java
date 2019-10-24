package qianlei.view.component;

import com.alee.extended.image.WebImage;
import com.alee.laf.text.WebTextField;
import qianlei.utils.VerifyCodeUtil;
import qianlei.utils.ViewUtil;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VerifyCodePanel extends JPanel {
    private String verifyCode = VerifyCodeUtil.createVerifyCode();
    private WebImage imgLabel = new WebImage();
    private WebTextField field = new WebTextField(20);

    public VerifyCodePanel() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        //保证右对齐
        add(Box.createHorizontalStrut(ViewUtil.getCurFont().getSize() * 4));
        add(Box.createHorizontalStrut(ViewUtil.getCurFont().getSize()));
        add(Box.createHorizontalBox());
        JLabel label = new JLabel("验证码");
        add(label);
        //添加提示文字
        field.setInputPrompt("请输入验证码");
        add(field);
        ImageIcon imageIcon = new ImageIcon("verifyCode.png");
        imgLabel.setImage(imageIcon);
        add(imgLabel);
        add(Box.createHorizontalStrut(ViewUtil.getCurFont().getSize() * 4));
        imgLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                verifyCode = VerifyCodeUtil.createVerifyCode();
                System.out.println(verifyCode);
                imgLabel.setImage(new ImageIcon("verifyCode.png"));
            }
        });
    }

    public boolean isVerifyCodeRight() {
        return field.getText().equalsIgnoreCase(verifyCode);
    }
}
