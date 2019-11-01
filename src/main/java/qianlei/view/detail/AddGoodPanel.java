package qianlei.view.detail;

import qianlei.entity.Good;
import qianlei.entity.Result;
import qianlei.exception.WrongDataException;
import qianlei.service.GoodService;
import qianlei.view.detail.linedetail.InputGoodPanelBase;

import javax.swing.*;
import java.awt.*;

/**
 * 添加商品界面
 *
 * @author qianlei
 */
public class AddGoodPanel extends JPanel implements CanInitPanel, CanSubmitPanel {
    private final InputGoodPanelBase inputGoodPanel = new InputGoodPanelBase();
    private final JButton button = new JButton("确认");
    private final GoodService goodService = new GoodService();
    private final JPanel panel = new JPanel(new FlowLayout());

    public AddGoodPanel() {
        panel.add(button);
        init();
        addAction();
    }

    /**
     * 添加监听器
     */
    private void addAction() {
        button.addActionListener((e) -> {
            int a = JOptionPane.showConfirmDialog(AddGoodPanel.this, "是否添加该商品");
            if (a == JOptionPane.YES_OPTION) {
                Result result = submit();
                if (result.isSuccess()) {
                    JOptionPane.showMessageDialog(AddGoodPanel.this, result.getMessage(), "添加成功", JOptionPane.INFORMATION_MESSAGE);
                    init();
                } else {
                    JOptionPane.showMessageDialog(AddGoodPanel.this, result.getMessage(), "添加失败", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }

    /**
     * 提交
     *
     * @return 结果
     */
    @Override
    public Result submit() {
        try {
            Good good = inputGoodPanel.getGood();
            goodService.addGood(good);
            return new Result(true, "添加成功");
        } catch (WrongDataException e) {
            return new Result(false, e.getMessage());
        } catch (Exception e) {
            return new Result(false, "未知错误" + e.getMessage());
        }

    }

    @Override
    public void init() {
        inputGoodPanel.init();
        removeAll();
        setLayout(new BorderLayout());
        add(inputGoodPanel);
        add(panel, BorderLayout.SOUTH);
        repaint();
        setVisible(true);
    }
}
