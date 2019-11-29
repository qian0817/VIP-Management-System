package qianlei.view.panel.detail;

import qianlei.entity.Good;
import qianlei.exception.WrongDataException;
import qianlei.utils.DateUtil;
import qianlei.utils.StringUtil;
import qianlei.view.panel.component.BaseComponentPanel;
import qianlei.view.panel.component.ComboPanelBase;
import qianlei.view.panel.component.DateChoosePanelBase;
import qianlei.view.panel.component.InputPanelBase;

import java.awt.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 输入商品信息界面
 * 有9个输入框分别输入编号 名称 制造商 创建时间 价格 折扣 剩余 简介 备注
 * 用于添加商品界面和修改商品界面
 *
 * @author qianlei
 */
public class InputGoodPanel extends BaseInputPanel {
    private static final List<String> DISCOUNT_RANGE_LIST = new ArrayList<>(100);

    static {
        BigDecimal range = new BigDecimal("0.01");
        for (BigDecimal i = BigDecimal.ONE; i.compareTo(BigDecimal.ZERO) > 0; i = i.subtract(range)) {
            DISCOUNT_RANGE_LIST.add(i.toString());
        }
    }

    private InputPanelBase idInputPanel = new InputPanelBase("商品编号", "请输入商品编号");
    private InputPanelBase nameInputPanel = new InputPanelBase("商品名称", "请输入商品名称");
    private InputPanelBase makerInputPanel = new InputPanelBase("制造商", "请输入制造商");
    private DateChoosePanelBase createTimeChoosePanel = new DateChoosePanelBase("生产日期");
    private InputPanelBase priceInputPanel = new InputPanelBase("价格", "请输入价格");
    private ComboPanelBase discountInputPanel = new ComboPanelBase("折扣", DISCOUNT_RANGE_LIST);
    private InputPanelBase remainInputPanel = new InputPanelBase("商品库存", "请输入商品库存");
    private InputPanelBase introductionInputPanel = new InputPanelBase("商品简介", "请输入商品简介");
    private InputPanelBase remarkInputPanel = new InputPanelBase("备注", "请输入备注");

    public InputGoodPanel() {
        panels.add(idInputPanel);
        panels.add(nameInputPanel);
        panels.add(makerInputPanel);
        panels.add(createTimeChoosePanel);
        panels.add(priceInputPanel);
        panels.add(discountInputPanel);
        panels.add(remainInputPanel);
        panels.add(introductionInputPanel);
        panels.add(remarkInputPanel);
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
            idInputPanel.setItem(good.getId());
            nameInputPanel.setItem(good.getName());
            makerInputPanel.setItem(good.getMaker());
            priceInputPanel.setItem(good.getPrice().toString());
            remainInputPanel.setItem(String.valueOf(good.getRemain()));
            introductionInputPanel.setItem(good.getIntroduction());
            remarkInputPanel.setItem(good.getRemarks());
            createTimeChoosePanel.setItem(DateUtil.transferToString(good.getCreateTime()));
            discountInputPanel.setItem(String.valueOf(good.getDiscount()));
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

    /**
     * 获取填写的商品
     *
     * @return 填写的商品
     * @exception WrongDataException 错误的填写数据
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
        String remark = remarkInputPanel.getItem();
        good.setRemarks(remark);
    }

    private void setIntroduction(Good good) {
        String introduction = introductionInputPanel.getItem();
        good.setIntroduction(introduction);
    }

    private void setRemain(Good good) throws WrongDataException {
        String remain = remainInputPanel.getItem();
        if (!StringUtil.isBigInteger(remain)) {
            throw new WrongDataException("库存" + remain + "格式错误");
        }
        good.setRemain(Long.parseLong(remain));
    }

    private void setDiscount(Good good) {
        String discount = discountInputPanel.getItem();
        good.setDiscount(new BigDecimal(discount));
    }

    private void setPrice(Good good) throws WrongDataException {
        String price = priceInputPanel.getItem();
        if (!StringUtil.isBigDecimal(price)) {
            throw new WrongDataException("价格：" + price + "格式错误");
        }
        good.setPrice(new BigDecimal(price));
    }

    private void setCreateTime(Good good) throws WrongDataException {
        String createTime = createTimeChoosePanel.getItem();
        Date time;
        try {
            time = DateUtil.transferToDate(createTime);
        } catch (ParseException e) {
            throw new WrongDataException("日期格式错误");
        }
        good.setCreateTime(time);
    }

    private void setMaker(Good good) {
        String maker = makerInputPanel.getItem();
        good.setMaker(maker);
    }

    private void setName(Good good) throws WrongDataException {
        String name = nameInputPanel.getItem();
        if ("".equals(name.trim())) {
            throw new WrongDataException("name不能为空");
        }
        good.setName(name);
    }

    private void setId(Good good) throws WrongDataException {
        String id = idInputPanel.getItem();
        if (StringUtil.containsBlank(id)) {
            throw new WrongDataException("id" + id + "不能包含空格");
        }
        good.setId(id);
    }

}
