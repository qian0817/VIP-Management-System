package qianlei.view.panel;

import qianlei.entity.Result;
import qianlei.entity.Vip;
import qianlei.exception.WrongDataException;
import qianlei.service.VipService;
import qianlei.view.panel.linedetail.InputVipPanel;

import javax.swing.*;
import java.awt.*;

/**
 * 添加Vip界面
 *
 * @author qianlei
 */
public class AddVipPanel extends AbstractCanInitPanel implements CanSubmitPanel {
    private final VipService vipService = new VipService();
    private final JButton button = new JButton("确认");
    private final InputVipPanel inputVipPanel = new InputVipPanel();

    AddVipPanel() {
        initView();
        addAction();
    }

    /**
     * 添加事件
     */
    private void addAction() {
        button.addActionListener((e) -> {
            int a = JOptionPane.showConfirmDialog(AddVipPanel.this, "是否添加该VIP");
            if (a == JOptionPane.YES_OPTION) {
                Result result = submit();
                if (result.isSuccess()) {
                    JOptionPane.showMessageDialog(AddVipPanel.this, result.getMessage(), "添加成功", JOptionPane.INFORMATION_MESSAGE);
                    initView();
                } else {
                    JOptionPane.showMessageDialog(AddVipPanel.this, result.getMessage(), "添加失败", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }

    @Override
    public Result submit() {
        try {
            Vip vip = inputVipPanel.getVip();
            vipService.addVip(vip);
            return new Result(true, "添加成功");
        } catch (WrongDataException ex) {
            return new Result(false, ex.getMessage());
        } catch (Exception e) {
            return new Result(false, "未知错误" + e.getMessage());
        }
    }

    @Override
    public void initView() {
        inputVipPanel.init();
        removeAll();
        //重置界面
        setLayout(new BorderLayout());
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(button);
        //添加组件
        add(inputVipPanel);
        add(panel, BorderLayout.SOUTH);
        repaint();
        setVisible(true);
    }
}
