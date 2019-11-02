package qianlei.view.panel.linedetail;

/**
 * @author qianlei
 */
public interface ILine {
    /**
     * 设置该项可否被需改
     *
     * @param field    哪一项的id
     * @param editable 可否被修改
     */
    void setEditable(int field, boolean editable);

    /**
     * 获取值
     *
     * @param field 哪一项的id
     * @return 获取到的值
     */
    String get(int field);

    /**
     * 添加到界面
     */
    void addToView();
}
