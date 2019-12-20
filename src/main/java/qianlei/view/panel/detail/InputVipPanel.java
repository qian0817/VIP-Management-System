package qianlei.view.panel.detail;

import org.jetbrains.annotations.NotNull;
import qianlei.entity.Vip;
import qianlei.exception.WrongDataException;
import qianlei.utils.StringUtil;
import qianlei.view.component.BaseComponentPanel;
import qianlei.view.component.InputPanelBase;
import qianlei.view.component.SexChoosePanelBase;

import java.awt.*;

/**
 * 输入VIP信息
 *
 * @author qianlei
 */
public class InputVipPanel extends BaseInputPanel {

    private final InputPanelBase idInputPanel = new InputPanelBase("会员卡号", "请输入会员卡号,必填", "vip_no.svg");
    private final InputPanelBase nameInputPanel = new InputPanelBase("会员姓名", "请输入会员姓名，必填", "vip_name.svg");
    private final SexChoosePanelBase sexInputPanel = new SexChoosePanelBase("会员性别");
    private final InputPanelBase phoneInputPanel = new InputPanelBase("电话号码", "请输入电话号码", "phone.svg");
    private final InputPanelBase addressInputPanel = new InputPanelBase("联系地址", "请输入联系地址", "address.svg");
    private final InputPanelBase emailInputPanel = new InputPanelBase("会员邮箱", "请输入邮箱", "email.svg");
    private Integer vipId = null;

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
            vipId = vip.getId();
            nameInputPanel.setItem(vip.getName());
            idInputPanel.setItem(vip.getVipNo());
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
        vip.setId(vipId);
        setId(vip);
        setName(vip);
        setSex(vip);
        setPhone(vip);
        setAddress(vip);
        setEmail(vip);
        return vip;
    }

    private void setEmail(@NotNull Vip vip) throws WrongDataException {
        String email = emailInputPanel.getItem();
        if (email.isEmpty()) {
            vip.setEmail("");
            return;
        }
        if (!StringUtil.isEmailAddress(email)) {
            throw new WrongDataException("邮箱" + email + "格式错误");
        }
        vip.setEmail(email);
    }

    private void setAddress(@NotNull Vip vip) {
        String address = addressInputPanel.getItem();
        vip.setAddress(address);
    }

    private void setPhone(@NotNull Vip vip) throws WrongDataException {
        String phone = phoneInputPanel.getItem();
        if (phone.isEmpty()) {
            vip.setPhone("");
            return;
        }
        if (!StringUtil.isBigInteger(phone)) {
            throw new WrongDataException("电话号码" + phone + "只能包含数字");
        }
        vip.setPhone(phone);
    }

    private void setSex(@NotNull Vip vip) {
        String sex = sexInputPanel.getItem();
        vip.setSex(sex);
    }

    private void setName(@NotNull Vip vip) throws WrongDataException {
        String name = nameInputPanel.getItem();
        if (name.isEmpty()) {
            throw new WrongDataException("姓名不能为空");
        }
        vip.setName(name);
    }

    private void setId(@NotNull Vip vip) throws WrongDataException {
        String id = idInputPanel.getItem();
        if (StringUtil.containsBlank(id)) {
            throw new WrongDataException("id" + id + "不能包含空格");
        }
        vip.setVipNo(id);
    }
}
