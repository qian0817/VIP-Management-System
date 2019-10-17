package enums;

public enum StatusEnum {
    //normal 代表状态正常 deleted代表已被删除
    Normal(0, "正常"), Deleted(1, "已删除");
    private int id;
    private String message;

    StatusEnum() {
    }

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

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
