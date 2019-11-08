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
    @SuppressWarnings("WeakerAccess")
    public static final int NAME = 1;
    public static final int ID = 2;
    @SuppressWarnings("WeakerAccess")
    public static final int SEX = 3;
    @SuppressWarnings("WeakerAccess")
    public static final int PHONE = 4;
    @SuppressWarnings("WeakerAccess")
    public static final int ADDRESS = 5;
    @SuppressWarnings("WeakerAccess")
    public static final int POSTCODE = 6;

    public InputVipPanel() {
        panelMap.put(NAME, new InputPanelBase("姓名", "请输入商品姓名"));
        panelMap.put(ID, new InputPanelBase("证件号", "请输入证件号"));
        panelMap.put(SEX, new SexChoosePanelBase());
        panelMap.put(PHONE, new InputPanelBase("电话号码", "请输入电话号码"));
        panelMap.put(ADDRESS, new InputPanelBase("联系地址", "请输入联系地址"));
        panelMap.put(POSTCODE, new InputPanelBase("邮编", "请输入邮编"));
        init();
    }

    public void init() {
        init(null);
    }

    public void init(Vip vip) {
        if (vip != null) {
            panelMap.get(NAME).setItem(vip.getName());
            panelMap.get(ID).setItem(vip.getId());
            panelMap.get(SEX).setItem(vip.getSex());
            panelMap.get(PHONE).setItem(vip.getPhone());
            panelMap.get(ADDRESS).setItem(vip.getAddress());
            panelMap.get(POSTCODE).setItem(String.valueOf(vip.getPostcode()));
        } else {
            for (Map.Entry<Integer, BaseComponentPanel> entry : panelMap.entrySet()) {
                entry.getValue().setItem(null);
            }
        }
        removeAll();
        setLayout(new GridLayout(13, 1));
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
        String postcode = get(InputVipPanel.POSTCODE);
        if (!StringUtil.isBigInteger(postcode) || postcode.length() != Vip.POSTCODE_LENGTH) {
            throw new WrongDataException("邮编" + postcode + "只能包含数字且只有6位");
        }
    }

    private void setAddress(Vip vip) {
        String address = get(InputVipPanel.ADDRESS);
        vip.setAddress(address);
    }

    private void setPhone(Vip vip) throws WrongDataException {
        String phone = get(InputVipPanel.PHONE);
        if (!StringUtil.isBigInteger(phone)) {
            throw new WrongDataException("电话号码" + phone + "只能包含数字");
        }
        vip.setPhone(phone);
    }

    private void setSex(Vip vip) {
        String sex = get(InputVipPanel.SEX);
        vip.setSex(sex);
    }

    private void setName(Vip vip) {
        String name = get(InputVipPanel.NAME);
        vip.setName(name);
    }

    private void setId(Vip vip) throws WrongDataException {
        String id = get(InputVipPanel.ID);
        if (StringUtil.containsBlank(id)) {
            throw new WrongDataException("id" + id + "不能包含空格");
        }
        vip.setId(id);
    }
}
