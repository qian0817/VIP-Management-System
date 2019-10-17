package view.component;

import com.alee.laf.panel.WebPanel;
import com.alee.laf.scroll.WebScrollPane;
import com.alee.laf.table.WebTable;

import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseListener;

public class TablePanel extends WebPanel {
    private WebTable table;
    private ExtendTableModel model;
    // private WebScrollPane scrollPane=new WebScrollPane();
    private Object[][] data;

    public TablePanel(Object[][] data, Object[] columnNames) {
        setLayout(new BorderLayout());
        init(data, columnNames);
    }

    public void init(Object[][] data, Object[] columnNames) {
        removeAll();
        this.data = data;
        model = new ExtendTableModel(data, columnNames);
        table = new WebTable(model);
        table.setRowHeight(30);
        WebScrollPane scrollPane = new WebScrollPane(table);
        add(scrollPane);
        repaint();
        setVisible(true);
    }

    public Object getRowFirst(Point point) {
        return data[table.rowAtPoint(point)][0];
    }

    @Override
    public void addMouseListener(MouseListener mouseListener) {
        table.addMouseListener(mouseListener);
    }


    static class ExtendTableModel extends DefaultTableModel {
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
