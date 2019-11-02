package qianlei.view.panel;

import qianlei.entity.Result;
import qianlei.entity.Vip;
import qianlei.exception.WrongDataException;
import qianlei.service.VipService;
import qianlei.view.MainFrame;
import qianlei.view.panel.linedetail.InputVipPanel;

import javax.swing.*;
import java.awt.*;

/**
 * 修改VIP信息界面
 *
 * @author qianlei
 */
class UpdateVipPanel extends JPanel implements CanSubmitPanel {
    private final VipService vipService = new VipService();
    private final InputVipPanel inputVipPanel = new InputVipPanel();
    private final JButton checkButton = new JButton("确认");
    private final JButton deleteButton = new JButton("删除");
    private final JPanel panel = new JPanel(new FlowLayout());
    private final MainFrame parent;

    UpdateVipPanel(String id, MainFrame parent) {
        this.parent = parent;
        //组件初始化
        setLayout(new BorderLayout());
        initInputVipPanel(id);
        addComponent();
        addAction(id);
    }

    /**
     * 添加组件
     */
    private void addComponent() {
        panel.add(checkButton);
        panel.add(deleteButton);
        add(inputVipPanel);
        add(panel, BorderLayout.SOUTH);
    }

    /**
     * 初始化主界面
     *
     * @param id vipId
     */
    private void initInputVipPanel(String id) {
        Vip vip = null;
        try {
            vip = vipService.getVipById(id);
        } catch (WrongDataException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        inputVipPanel.init(vip);
        inputVipPanel.setEditable(InputVipPanel.ID, false);
    }

    /**
     * 添加事件
     *
     * @param id vipId
     */
    private void addAction(String id) {
        checkButton.addActionListener((e) -> {
            int a = JOptionPane.showConfirmDialog(UpdateVipPanel.this, "是否修改该VIP");
            if (a == JOptionPane.YES_OPTION) {
                Result result = submit();
                if (result.isSuccess()) {
                    JOptionPane.showMessageDialog(UpdateVipPanel.this, result.getMessage(), "修改成功", JOptionPane.INFORMATION_MESSAGE);
                    changeToShowVipPanel();
                } else {
                    JOptionPane.showMessageDialog(UpdateVipPanel.this, result.getMessage(), "修改失败", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        deleteButton.addActionListener((e) -> {
            int a = JOptionPane.showConfirmDialog(UpdateVipPanel.this, "是否删除该VIP");
            if (a == JOptionPane.YES_OPTION) {
                try {
                    vipService.deleteVipById(id);
                } catch (WrongDataException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }
                changeToShowVipPanel();
            }
        });
    }

    @Override
    public Result submit() {
        try {
            Vip vip = inputVipPanel.getVip();
            vipService.updateVip(vip);
            return new Result(true, "修改成功");
        } catch (WrongDataException e) {
            return new Result(false, e.getMessage());
        } catch (Exception e) {
            return new Result(false, "未知错误" + e.getMessage());
        }

    }

    /**
     * 回到showVipPanel
     */
    private void changeToShowVipPanel() {
        removeAll();
        setLayout(new BorderLayout());
        add(new ShowVipPanel(parent));
        repaint();
        setVisible(true);
    }


}
