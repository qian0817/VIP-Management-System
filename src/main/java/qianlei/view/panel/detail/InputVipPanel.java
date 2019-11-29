package qianlei.view.panel.detail;

import qianlei.entity.Vip;
import qianlei.exception.WrongDataException;
import qianlei.utils.StringUtil;
import qianlei.view.panel.component.BaseComponentPanel;
import qianlei.view.panel.component.InputPanelBase;
import qianlei.view.panel.component.SexChoosePanelBase;

import java.awt.*;

/**
 * 输入VIP信息
 *
 * @author qianlei
 */
public class InputVipPanel extends BaseInputPanel {

    private final InputPanelBase nameInputPanel = new InputPanelBase("会员姓名", "请输入会员姓名");
    private final InputPanelBase idInputPanel = new InputPanelBase("会员卡号", "请输入会员卡号");
    private final SexChoosePanelBase sexInputPanel = new SexChoosePanelBase("会员性别");
    private final InputPanelBase phoneInputPanel = new InputPanelBase("电话号码", "请输入电话号码");
    private final InputPanelBase addressInputPanel = new InputPanelBase("联系地址", "请输入联系地址");
    private final InputPanelBase emailInputPanel = new InputPanelBase("会员邮箱", "请输入邮箱");

    public InputVipPanel() {
        panels.add(idInputPanel);
        panels.add(nameInputPanel);
        panels.add(sexInputPanel);
        panels.add(phoneInputPanel);
        panels.add(addressInputPanel);
        panels.add(emailInputPanel);
        init();
    }

    public void init() {
        init(null);
    }

    public void init(Vip vip) {
        if (vip != null) {
            nameInputPanel.setItem(vip.getName());
            idInputPanel.setItem(vip.getId());
            sexInputPanel.setItem(vip.getSex());
            phoneInputPanel.setItem(vip.getPhone());
            addressInputPanel.setItem(vip.getAddress());
            emailInputPanel.setItem(vip.getEmail());
        } else {
            for (BaseComponentPanel panel : panels) {
                panel.setItem(null);
            }
        }
        removeAll();
        setLayout(new GridLayout(panels.size() * 2 + 1, 1));
        addToView();
        repaint();
        setVisible(true);
    }

    public Vip getVip() throws WrongDataException {
        Vip vip = new Vip();
        setId(vip);
        setName(vip);
        setSex(vip);
        setPhone(vip);
        setAddress(vip);
        setEmail(vip);
        return vip;
    }

    private void setEmail(Vip vip) throws WrongDataException {
        String email = emailInputPanel.getItem();
        if (!StringUtil.isEmailAddress(email)) {
            throw new WrongDataException("邮箱" + email + "格式错误");
        }
        vip.setEmail(email);
    }

    private void setAddress(Vip vip) {
        String address = addressInputPanel.getItem();
        vip.setAddress(address);
    }

    private void setPhone(Vip vip) throws WrongDataException {
        String phone = phoneInputPanel.getItem();
        if (!StringUtil.isBigInteger(phone)) {
            throw new WrongDataException("电话号码" + phone + "只能包含数字");
        }
        vip.setPhone(phone);
    }

    private void setSex(Vip vip) {
        String sex = sexInputPanel.getItem();
        vip.setSex(sex);
    }

    private void setName(Vip vip) {
        String name = nameInputPanel.getItem();
        vip.setName(name);
    }

    private void setId(Vip vip) throws WrongDataException {
        String id = idInputPanel.getItem();
        if (StringUtil.containsBlank(id)) {
            throw new WrongDataException("id" + id + "不能包含空格");
        }
        vip.setId(id);
    }
}
