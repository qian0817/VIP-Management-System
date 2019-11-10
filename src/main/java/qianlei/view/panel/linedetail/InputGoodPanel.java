package qianlei.view.panel.linedetail;

import qianlei.entity.Good;
import qianlei.exception.WrongDataException;
import qianlei.utils.DateUtil;
import qianlei.utils.StringUtil;
import qianlei.view.panel.linedetail.component.BaseComponentPanel;
import qianlei.view.panel.linedetail.component.ComboPanelBase;
import qianlei.view.panel.linedetail.component.DateChoosePanelBase;
import qianlei.view.panel.linedetail.component.InputPanelBase;

import java.awt.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 输入商品信息界面
 * 有9个输入框分别输入编号 名称 制造商 创建时间 价格 折扣 剩余 简介 备注
 * 用于添加商品界面和修改商品界面
 *
 * @author qianlei
 */
public class InputGoodPanel extends BaseInputPanel {
    private static List<String> discountRangeList = new ArrayList<>(100);

    static {
        for (int i = 100; i > 0; i--) {
            discountRangeList.add(String.valueOf(i * 1.0 / 100));
        }
    }

    public InputGoodPanel() {
        PanelEnum.addToPanelMap(this);
        init();
    }

    public void init() {
        init(null);
    }

    /**
     * 根据商品初始化信息
     *
     * @param good 商品
     */
    public void init(Good good) {
        if (good != null) {
            panelMap.get(PanelEnum.ID.getId()).setItem(good.getId());
            panelMap.get(PanelEnum.NAME.getId()).setItem(good.getName());
            panelMap.get(PanelEnum.MAKER.getId()).setItem(good.getMaker());
            panelMap.get(PanelEnum.PRICE.getId()).setItem(good.getPrice().toString());
            panelMap.get(PanelEnum.REMAIN.getId()).setItem(String.valueOf(good.getRemain()));
            panelMap.get(PanelEnum.INTRODUCTION.getId()).setItem(good.getIntroduction());
            panelMap.get(PanelEnum.REMARK.getId()).setItem(good.getRemarks());
            panelMap.get(PanelEnum.CREATE_TIME.getId()).setItem(DateUtil.transferToString(good.getCreateTime()));
            panelMap.get(PanelEnum.DISCOUNT.getId()).setItem(String.valueOf(good.getDiscount()));
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

    /**
     * 获取填写的商品
     *
     * @return 填写的商品
     * @ 错误的填写数据
     */
    public Good getGood() throws WrongDataException {
        Good good = new Good();
        setId(good);
        setName(good);
        setMaker(good);
        setCreateTime(good);
        setPrice(good);
        setDiscount(good);
        setRemain(good);
        setIntroduction(good);
        setRemark(good);
        return good;
    }

    private void setRemark(Good good) {
        String remark = get(PanelEnum.REMARK.getId());
        good.setRemarks(remark);
    }

    private void setIntroduction(Good good) {
        String introduction = get(PanelEnum.INTRODUCTION.getId());
        good.setIntroduction(introduction);
    }

    private void setRemain(Good good) throws WrongDataException {
        String remain = get(PanelEnum.REMAIN.getId());
        if (!StringUtil.isBigInteger(remain)) {
            throw new WrongDataException("库存" + remain + "格式错误");
        }
        good.setRemain(Long.parseLong(remain));
    }

    private void setDiscount(Good good) {
        String discount = get(PanelEnum.DISCOUNT.getId());
        good.setDiscount(Double.parseDouble(discount));
    }

    private void setPrice(Good good) throws WrongDataException {
        String price = get(PanelEnum.PRICE.getId());
        if (!StringUtil.isBigDecimal(price)) {
            throw new WrongDataException("价格：" + price + "格式错误");
        }
        good.setPrice(new BigDecimal(price));
    }

    private void setCreateTime(Good good) throws WrongDataException {
        String createTime = get(PanelEnum.CREATE_TIME.getId());
        Date time;
        try {
            time = DateUtil.transferToDate(createTime);
        } catch (ParseException e) {
            throw new WrongDataException("日期格式错误");
        }
        good.setCreateTime(time);
    }

    private void setMaker(Good good) {
        String maker = get(PanelEnum.MAKER.getId());
        good.setMaker(maker);
    }

    private void setName(Good good) throws WrongDataException {
        String name = get(PanelEnum.NAME.getId());
        if ("".equals(name.trim())) {
            throw new WrongDataException("name不能为空");
        }
        good.setName(name);
    }

    private void setId(Good good) throws WrongDataException {
        String id = get(PanelEnum.ID.getId());
        if (StringUtil.containsBlank(id)) {
            throw new WrongDataException("id" + id + "不能包含空格");
        }
        good.setId(id);
    }

    /**
     * 为每个输入框分配对应的id 将按照id大小从上到下添加到界面
     */
    public enum PanelEnum {
        ID(1, new InputPanelBase("商品编号", "请输入商品编号")),
        NAME(2, new InputPanelBase("商品名称", "请输入商品名称")),
        MAKER(3, new InputPanelBase("制造商", "请输入制造商")),
        CREATE_TIME(4, new DateChoosePanelBase("生产日期")),
        PRICE(5, new InputPanelBase("价格", "请输入价格")),
        DISCOUNT(6, new ComboPanelBase("折扣", discountRangeList)),
        REMAIN(7, new InputPanelBase("商品库存", "请输入商品库存")),
        INTRODUCTION(8, new InputPanelBase("商品简介", "请输入商品简介")),
        REMARK(9, new InputPanelBase("备注", "请输入备注"));

        private int id;
        private BaseComponentPanel panel;

        PanelEnum(int id, BaseComponentPanel panel) {
            this.id = id;
            this.panel = panel;
        }

        public static void addToPanelMap(InputGoodPanel panel) {
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
