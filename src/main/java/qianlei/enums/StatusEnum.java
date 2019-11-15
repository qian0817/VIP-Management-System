package qianlei.enums;

/**
 * 状态的枚举类
 *
 * @author qianlei
 */
public enum StatusEnum {
    //normal 代表状态正常 deleted代表已下架
    NORMAL(0, "正常"), DELETED(1, "已下架");
    private int id;
    private String message;

    StatusEnum(int id, String message) {
        this.id = id;
        this.message = message;
    }

    public static StatusEnum getById(int id) {
        for (StatusEnum cur : values()) {
            if (cur.id == id) {
                return cur;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }
}
