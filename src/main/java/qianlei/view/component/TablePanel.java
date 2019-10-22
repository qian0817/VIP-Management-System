package qianlei.view.component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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

    public void init(Object[][] data, Object[] columnNames) {
        removeAll();
        this.data = data;
        ExtendTableModel model = new ExtendTableModel(data, columnNames);
        table = new JTable(model);
        table.setRowHeight(30);
        JScrollPane scrollPane = new JScrollPane(table);
        table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(scrollPane);
        repaint();
        setVisible(true);
    }

    public Object getRowFirst(Point point) {
        return data[table.rowAtPoint(point)][0];
    }

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
