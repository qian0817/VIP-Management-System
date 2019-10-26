package qianlei.view.component;

import qianlei.utils.ViewUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseListener;

/**
 * @author qianlei
 */
public class TablePanel extends JPanel {
    private JTable table;
    private Object[][] data;

    public TablePanel(Object[][] data, Object[] columnNames) {
        setLayout(new BorderLayout());
        init(data, columnNames);
    }

    /**
     * 初始化
     *
     * @param data        数据
     * @param columnNames 列表名称
     */
    public void init(Object[][] data, Object[] columnNames) {
        removeAll();
        this.data = data;
        ExtendTableModel model = new ExtendTableModel(data, columnNames);
        table = new JTable(model);
        //排序
        RowSorter<ExtendTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);
        table.setRowHeight(ViewUtil.getCurFont().getSize() * 2);
        JScrollPane scrollPane = new JScrollPane(table);
        table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(scrollPane);
        repaint();
        setVisible(true);
    }

    /**
     * 获取指定点第一列的内容
     *
     * @param point 点
     * @return 第一列的内容
     */
    public Object getRowByIndex(Point point, int index) {
        return data[table.rowAtPoint(point)][index];
    }

    /**
     * 获取选择的内容
     *
     * @return 选择的内容 空返回null
     */
    public Object getSelectedId() {
        if (table.getSelectedRow() == -1) {
            return null;
        }
        return data[table.getSelectedRow()][0];
    }

    @Override
    public void addMouseListener(MouseListener mouseListener) {
        table.addMouseListener(mouseListener);
    }

    @Override
    public void removeMouseListener(MouseListener mouseListener) {
        table.removeMouseListener(mouseListener);
    }


    private static class ExtendTableModel extends DefaultTableModel {
        ExtendTableModel(Object[][] data, Object[] columnNames) {
            super(data, columnNames);
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return getValueAt(0, columnIndex).getClass();
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }

    }
}
