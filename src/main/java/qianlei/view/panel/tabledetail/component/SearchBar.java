package qianlei.view.panel.tabledetail.component;

import com.alee.laf.button.WebButton;
import qianlei.view.panel.linedetail.component.InputPanelBase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 搜索栏
 *
 * @author qianlei
 */
public class SearchBar extends JPanel {
    private final Map<String, InputPanelBase> map = new HashMap<>();
    private final WebButton search = new WebButton("搜索");

    public SearchBar(List<String> items) {
        for (String item : items) {
            map.put(item, new InputPanelBase(item, "请输入" + item, 0));
        }
        setLayout(new GridLayout(1, map.size() + 1));
        for (String item : items) {
            add(map.get(item));
        }
        add(search);
        //快捷键
        search.addHotkey(10);
    }

    /**
     * 获取输入的文字 key为类型 value为输入的文字
     *
     * @return 输入的文字
     */
    public Map<String, String> getInput() {
        Map<String, String> ret = new HashMap<>(8);
        for (Map.Entry<String, InputPanelBase> s : map.entrySet()) {
            ret.put(s.getKey(), s.getValue().getItem());
        }
        return ret;
    }

    /**
     * 添加点击search按钮的事件
     *
     * @param actionListener 事件
     */
    public void addActionListener(ActionListener actionListener) {
        search.addActionListener(actionListener);
    }
}
