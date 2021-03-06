package qianlei.view.component;

import com.alee.laf.table.WebTable;
import com.alee.managers.style.StyleId;
import qianlei.utils.ViewUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.MouseListener;
import java.util.Enumeration;

/**
 * @author qianlei
 */
public class TablePanel extends JPanel {
    private final WebTable table = new WebTable(StyleId.tableNonOpaque);
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
        table.setRowHeight(ViewUtil.getFontSize() * 2);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        changeData(data, columnNames);
    }

    public void changeData(Object[][] data, Object[] columnNames) {
        removeAll();
        JScrollPane scrollPane = new JScrollPane(table);
        this.data = data.clone();
        ExtendTableModel model = new ExtendTableModel(data, columnNames);
        table.setModel(model);
        fitTableColumns(table);
        add(scrollPane);
        repaint();
        setVisible(true);
    }

    /**
     * 设置table的列宽随内容调整
     *
     * @param myTable table
     */
    private void fitTableColumns(JTable myTable) {
        JTableHeader header = myTable.getTableHeader();
        int rowCount = myTable.getRowCount();
        @SuppressWarnings("rawtypes")
        Enumeration columns = myTable.getColumnModel().getColumns();
        while (columns.hasMoreElements()) {
            TableColumn column = (TableColumn) columns.nextElement();
            int col = header.getColumnModel().getColumnIndex(column.getIdentifier());
            int width = (int) myTable.getTableHeader().getDefaultRenderer().getTableCellRendererComponent(myTable,
                    column.getIdentifier(), false, false, -1, col).getPreferredSize().getWidth();
            for (int row = 0; row < rowCount; row++) {
                int preferedWidth = (int) myTable.getCellRenderer(row, col).getTableCellRendererComponent(myTable,
                        myTable.getValueAt(row, col), false, false,
                        row, col).getPreferredSize().getWidth();
                width = Math.max(width, preferedWidth);
            }
            header.setResizingColumn(column);
            column.setWidth(width + myTable.getIntercellSpacing().width + 150);
        }
    }

    /**
     * 获取选择的内容
     *
     * @return 选择的内容 空返回null
     */
    public Object[] getSelectedObject() {
        if (table.getSelectedRow() == -1) {
            return null;
        }
        return data[table.getSelectedRow()];
    }

    /**
     * 获取选中的行
     *
     * @return 选中的行
     */
    public int getSelectedRow() {
        return table.getSelectedRow();
    }

    @Override
    public void removeMouseListener(MouseListener mouseListener) {
        table.removeMouseListener(mouseListener);
    }

    @Override
    public synchronized void addMouseListener(MouseListener l) {
        table.addMouseListener(l);
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
