package qianlei.view.component;

import com.alee.laf.button.WebButton;
import com.alee.laf.panel.WebPanel;

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
public class SearchBar extends WebPanel {
    private Map<String, InputPanel> map = new HashMap<>();
    private WebButton search = new WebButton("搜索");

    public SearchBar(List<String> items) {
        for (String item : items) {
            map.put(item, new InputPanel(item, "请输入" + item, 0));
        }
        setLayout(new GridLayout(1, map.size() + 1));
        for (String item : items) {
            add(map.get(item));
        }
        add(search);
    }

    public Map<String, String> getInput() {
        Map<String, String> ret = new HashMap<>();
        for (String s : map.keySet()) {
            ret.put(s, map.get(s).getText());
        }
        return ret;
    }

    public void addActionListener(ActionListener actionListener) {
        search.addActionListener(actionListener);
    }
}
