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
 *
 * @author qianlei
 */
public class InputGoodPanel extends BaseInputPanel {
    public static final int ID = 1;
    @SuppressWarnings("WeakerAccess")
    public static final int NAME = 2;
    @SuppressWarnings("WeakerAccess")
    public static final int MAKER = 3;
    @SuppressWarnings("WeakerAccess")
    public static final int CREATE_TIME = 4;
    @SuppressWarnings("WeakerAccess")
    public static final int PRICE = 5;
    @SuppressWarnings("WeakerAccess")
    public static final int DISCOUNT = 6;
    @SuppressWarnings("WeakerAccess")
    public static final int REMAIN = 7;
    @SuppressWarnings("WeakerAccess")
    public static final int INTRODUCTION = 8;
    @SuppressWarnings("WeakerAccess")
    public static final int REMARK = 9;
    private static final int MAX_DISCOUNT = 100;
    private static final int MIN_DISCOUNT = 0;

    public InputGoodPanel() {
        List<String> doubleList = new ArrayList<>();
        for (int i = MAX_DISCOUNT; i > MIN_DISCOUNT; i--) {
            doubleList.add(String.valueOf(i * 1.0 / 100));
        }
        panelMap.put(ID, new InputPanelBase("商品编号", "请输入商品编号"));
        panelMap.put(NAME, new InputPanelBase("商品名称", "请输入商品名称"));
        panelMap.put(MAKER, new InputPanelBase("制造商", "请输入制造商"));
        panelMap.put(PRICE, new InputPanelBase("价格", "请输入价格"));
        panelMap.put(REMAIN, new InputPanelBase("商品库存", "请输入商品库存"));
        panelMap.put(INTRODUCTION, new InputPanelBase("商品简介", "请输入商品简介"));
        panelMap.put(REMARK, new InputPanelBase("备注", "请输入备注"));
        panelMap.put(CREATE_TIME, new DateChoosePanelBase("生产日期"));
        panelMap.put(DISCOUNT, new ComboPanelBase("折扣", doubleList));
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
            panelMap.get(ID).setItem(good.getId());
            panelMap.get(NAME).setItem(good.getName());
            panelMap.get(MAKER).setItem(good.getMaker());
            panelMap.get(PRICE).setItem(good.getPrice().toString());
            panelMap.get(REMAIN).setItem(String.valueOf(good.getRemain()));
            panelMap.get(INTRODUCTION).setItem(good.getIntroduction());
            panelMap.get(REMARK).setItem(good.getRemarks());
            panelMap.get(CREATE_TIME).setItem(DateUtil.transferToString(good.getCreateTime()));
            panelMap.get(DISCOUNT).setItem(String.valueOf(good.getDiscount()));
        } else {
            for (Map.Entry<Integer, BaseComponentPanel> entry : panelMap.entrySet()) {
                entry.getValue().setItem(null);
            }
        }
        removeAll();
        setLayout(new GridLayout(19, 1));
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
        String remark = get(InputGoodPanel.REMARK);
        good.setRemarks(remark);
    }

    private void setIntroduction(Good good) {
        String introduction = get(InputGoodPanel.INTRODUCTION);
        good.setIntroduction(introduction);
    }

    private void setRemain(Good good) throws WrongDataException {
        String remain = get(InputGoodPanel.REMAIN);
        if (!StringUtil.isBigInteger(remain)) {
            throw new WrongDataException("库存" + remain + "格式错误");
        }
        good.setRemain(Long.parseLong(remain));
    }

    private void setDiscount(Good good) {
        String discount = get(InputGoodPanel.DISCOUNT);
        good.setDiscount(Double.parseDouble(discount));
    }

    private void setPrice(Good good) throws WrongDataException {
        String price = get(InputGoodPanel.PRICE);
        if (!StringUtil.isBigDecimal(price)) {
            throw new WrongDataException("价格：" + price + "格式错误");
        }
        good.setPrice(new BigDecimal(price));
    }

    private void setCreateTime(Good good) throws WrongDataException {
        String createTime = get(InputGoodPanel.CREATE_TIME);
        Date time;
        try {
            time = DateUtil.transferToDate(createTime);
        } catch (ParseException e) {
            throw new WrongDataException("日期格式错误");
        }
        good.setCreateTime(time);
    }

    private void setMaker(Good good) {
        String maker = get(InputGoodPanel.MAKER);
        good.setMaker(maker);
    }

    private void setName(Good good) throws WrongDataException {
        String name = get(InputGoodPanel.NAME);
        if ("".equals(name.trim())) {
            throw new WrongDataException("name不能为空");
        }
        good.setName(name);
    }

    private void setId(Good good) throws WrongDataException {
        String id = get(InputGoodPanel.ID);
        if (StringUtil.containsBlank(id)) {
            throw new WrongDataException("id" + id + "不能包含空格");
        }
        good.setId(id);
    }
}
