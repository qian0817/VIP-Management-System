package qianlei.view.panel;

import qianlei.entity.Good;
import qianlei.entity.Result;
import qianlei.exception.WrongDataException;
import qianlei.service.GoodService;
import qianlei.view.MainFrame;
import qianlei.view.panel.linedetail.InputGoodPanel;

import javax.swing.*;
import java.awt.*;

/**
 * @author qianlei
 */
class UpdateGoodPanel extends JPanel implements CanSubmitPanel {
    private final InputGoodPanel inputGoodPanel = new InputGoodPanel();
    private final JPanel checkPanel = new JPanel(new FlowLayout());
    private final JButton checkButton = new JButton("确认");
    private final JButton deleteButton = new JButton("删除");
    private final GoodService goodService = new GoodService();
    private final MainFrame parent;

    UpdateGoodPanel(String id, MainFrame parent) {
        this.parent = parent;
        setLayout(new BorderLayout());
        initInputGoodPanel(id);
        addComponent();
        addAction(id);
    }

    /**
     * 初始化数据
     *
     * @param id 商品id
     */
    private void initInputGoodPanel(String id) {
        Good good = goodService.getGoodById(id);
        inputGoodPanel.init(good);
        inputGoodPanel.setEditable(InputGoodPanel.ID, false);
    }

    /**
     * 添加事件
     *
     * @param id 当前商品id
     */
    private void addAction(String id) {
        deleteButton.addActionListener((e) -> {
            int a = JOptionPane.showConfirmDialog(UpdateGoodPanel.this, "是否删除该商品");
            if (a == JOptionPane.YES_OPTION) {
                goodService.deleteGoodById(id);
                changeToShowGoodPanel();
            }
        });
        checkButton.addActionListener((e) -> {
            int a = JOptionPane.showConfirmDialog(UpdateGoodPanel.this, "是否修改该商品");
            if (a == JOptionPane.YES_OPTION) {
                Result result = submit();
                if (result.isSuccess()) {
                    JOptionPane.showMessageDialog(UpdateGoodPanel.this, result.getMessage(), "修改成功", JOptionPane.INFORMATION_MESSAGE);
                    changeToShowGoodPanel();
                } else {
                    JOptionPane.showMessageDialog(UpdateGoodPanel.this, result.getMessage(), "修改失败", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }

    @Override
    public Result submit() {
        try {
            Good good = inputGoodPanel.getGood();
            goodService.updateGood(good);
            return new Result(true, "修改成功");
        } catch (WrongDataException e) {
            return new Result(false, e.getMessage());
        } catch (Exception e) {
            return new Result(false, "未知错误" + e.getMessage());
        }
    }

    /**
     * 回到showGoodPanel
     */
    private void changeToShowGoodPanel() {
        removeAll();
        setLayout(new BorderLayout());
        add(new ShowGoodPanel(parent));
        repaint();
        setVisible(true);
    }

    /**
     * 添加组件
     */
    private void addComponent() {
        checkPanel.add(checkButton);
        checkPanel.add(deleteButton);
        add(inputGoodPanel);
        add(checkPanel, BorderLayout.SOUTH);
    }
}
