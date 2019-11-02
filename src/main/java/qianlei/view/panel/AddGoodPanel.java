package qianlei.view.panel;

import qianlei.entity.Good;
import qianlei.entity.Result;
import qianlei.exception.WrongDataException;
import qianlei.service.GoodService;
import qianlei.view.panel.linedetail.InputGoodPanel;

import javax.swing.*;
import java.awt.*;

/**
 * 添加商品界面
 *
 * @author qianlei
 */
public class AddGoodPanel extends AbstractCanInitPanel implements CanSubmitPanel {
    private final InputGoodPanel inputGoodPanel = new InputGoodPanel();
    private final JButton button = new JButton("确认");
    private final GoodService goodService = new GoodService();
    private final JPanel panel = new JPanel(new FlowLayout());

    public AddGoodPanel() {
        panel.add(button);
        initView();
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
                    initView();
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
    public void initView() {
        inputGoodPanel.init();
        removeAll();
        setLayout(new BorderLayout());
        add(inputGoodPanel);
        add(panel, BorderLayout.SOUTH);
        repaint();
        setVisible(true);
    }
}
