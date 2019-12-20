package qianlei.view.component;

import com.alee.laf.button.WebButton;
import org.jetbrains.annotations.NotNull;
import qianlei.utils.ViewUtil;

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
    private final WebButton searchButton = new WebButton("搜索", ViewUtil.getSvgIcon("search.svg", ViewUtil.getCurConfig().getFont().getSize(), ViewUtil.getCurConfig().getFont().getSize()));

    public SearchBar(@NotNull List<String> items) {
        for (String item : items) {
            map.put(item, new InputPanelBase(item, "", 0));
        }
        setLayout(new GridLayout(1, map.size() + 1));
        for (String item : items) {
            add(map.get(item));
        }
        add(searchButton);
        //快捷键
        searchButton.addHotkey(10);
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
        searchButton.addActionListener(actionListener);
    }
}
