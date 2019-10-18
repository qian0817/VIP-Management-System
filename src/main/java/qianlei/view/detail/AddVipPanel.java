package qianlei.view.detail;

import com.alee.laf.button.WebButton;
import com.alee.laf.label.WebLabel;
import com.alee.laf.optionpane.WebOptionPane;
import com.alee.laf.panel.WebPanel;
import qianlei.exception.WrongDataException;
import qianlei.service.VipService;
import qianlei.view.component.InputPanel;
import qianlei.view.component.SexChoosePanel;

import java.awt.*;

/**
 * 添加Vip界面
 *
 * @author qianlei
 */
public class AddVipPanel extends WebPanel {
    private InputPanel nameInputPanel = new InputPanel("姓名", "请输入商品姓名");
    private InputPanel idInputPanel = new InputPanel("证件号", "请输入证件号");
    private SexChoosePanel sexInputPanel = new SexChoosePanel();
    private InputPanel phoneInputPanel = new InputPanel("电话号码", "请输入电话号码");
    private InputPanel addressInputPanel = new InputPanel("联系地址", "请输入联系地址");
    private InputPanel postcodeInputPanel = new InputPanel("邮编", "请输入邮编");
    private WebButton button = new WebButton("确认");

    public AddVipPanel() {
        button.addActionListener((e) -> {
            VipService vipService = new VipService();
            String id = idInputPanel.getText();
            String name = nameInputPanel.getText();
            String sex = sexInputPanel.getSelect();
            String phone = phoneInputPanel.getText();
            String address = addressInputPanel.getText();
            String postcode = postcodeInputPanel.getText();
            try {
                vipService.addVip(id, name, sex, phone, address, postcode);
                WebOptionPane.showMessageDialog(AddVipPanel.this, "添加成功", "添加成功", WebOptionPane.INFORMATION_MESSAGE);
            } catch (WrongDataException ex) {
                WebOptionPane.showMessageDialog(AddVipPanel.this, ex.getMessage(), "添加失败", WebOptionPane.INFORMATION_MESSAGE);
            }
        });
        init();
    }

    public void init() {
        removeAll();
        setLayout(new GridLayout(8, 1));
        add(new WebLabel("商品信息录入", WebLabel.CENTER));
        add(idInputPanel);
        add(nameInputPanel);
        add(sexInputPanel);
        add(phoneInputPanel);
        add(addressInputPanel);
        add(postcodeInputPanel);
        WebPanel panel = new WebPanel(new FlowLayout());
        panel.add(button);
        add(panel);
        repaint();
        setVisible(true);
    }
}
