package qianlei.view.panel.linedetail;

import qianlei.entity.Vip;
import qianlei.exception.WrongDataException;
import qianlei.utils.StringUtil;
import qianlei.view.panel.linedetail.component.BaseComponentPanel;
import qianlei.view.panel.linedetail.component.InputPanelBase;
import qianlei.view.panel.linedetail.component.SexChoosePanelBase;

import java.awt.*;
import java.util.Map;

/**
 * 输入VIP信息
 *
 * @author qianlei
 */
public class InputVipPanel extends BaseInputPanel {
    public InputVipPanel() {
        PanelEnum.addToPanelMap(this);
        init();
    }

    public void init() {
        init(null);
    }

    public void init(Vip vip) {
        if (vip != null) {
            panelMap.get(PanelEnum.NAME.getId()).setItem(vip.getName());
            panelMap.get(PanelEnum.ID.getId()).setItem(vip.getId());
            panelMap.get(PanelEnum.SEX.getId()).setItem(vip.getSex());
            panelMap.get(PanelEnum.PHONE.getId()).setItem(vip.getPhone());
            panelMap.get(PanelEnum.ADDRESS.getId()).setItem(vip.getAddress());
            panelMap.get(PanelEnum.POSTCODE.getId()).setItem(String.valueOf(vip.getPostcode()));
        } else {
            for (Map.Entry<Integer, BaseComponentPanel> entry : panelMap.entrySet()) {
                entry.getValue().setItem(null);
            }
        }
        removeAll();
        setLayout(new GridLayout(PanelEnum.values().length * 2 + 1, 1));
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
        setPostcode();
        return vip;
    }

    private void setPostcode() throws WrongDataException {
        String postcode = get(PanelEnum.POSTCODE.getId());
        if (!StringUtil.isBigInteger(postcode) || postcode.length() != Vip.POSTCODE_LENGTH) {
            throw new WrongDataException("邮编" + postcode + "只能包含数字且只有6位");
        }
    }

    private void setAddress(Vip vip) {
        String address = get(PanelEnum.ADDRESS.getId());
        vip.setAddress(address);
    }

    private void setPhone(Vip vip) throws WrongDataException {
        String phone = get(PanelEnum.PHONE.getId());
        if (!StringUtil.isBigInteger(phone)) {
            throw new WrongDataException("电话号码" + phone + "只能包含数字");
        }
        vip.setPhone(phone);
    }

    private void setSex(Vip vip) {
        String sex = get(PanelEnum.SEX.getId());
        vip.setSex(sex);
    }

    private void setName(Vip vip) {
        String name = get(PanelEnum.NAME.getId());
        vip.setName(name);
    }

    private void setId(Vip vip) throws WrongDataException {
        String id = get(PanelEnum.ID.getId());
        if (StringUtil.containsBlank(id)) {
            throw new WrongDataException("id" + id + "不能包含空格");
        }
        vip.setId(id);
    }

    /**
     * 为每个输入框分配对应的id 将按照id大小从上到下添加到界面
     */
    public enum PanelEnum {
        NAME(1, new InputPanelBase("姓名", "请输入商品姓名")),
        ID(2, new InputPanelBase("证件号", "请输入证件号")),
        SEX(3, new SexChoosePanelBase()),
        PHONE(4, new InputPanelBase("电话号码", "请输入电话号码")),
        ADDRESS(5, new InputPanelBase("联系地址", "请输入联系地址")),
        POSTCODE(6, new InputPanelBase("邮编", "请输入邮编"));
        private int id;
        private BaseComponentPanel panel;

        PanelEnum(int id, BaseComponentPanel panel) {
            this.id = id;
            this.panel = panel;
        }

        private static void addToPanelMap(InputVipPanel panel) {
            for (PanelEnum panelEnum : values()) {
                panelEnum.panel.setEditable(true);
                panel.panelMap.put(panelEnum.id, panelEnum.panel);
            }
        }

        public int getId() {
            return id;
        }
    }
}
