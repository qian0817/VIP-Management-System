package entity;

import java.util.Date;

/**
 * 记录类
 *
 * @author qianlei
 */
public class Record {
    private String id;
    private String goodId;
    private String vipId;
    private Date createTime;

    public Record() {
    }

    public Record(String id, String goodId, String vipId, Date createTime) {
        this.id = id;
        this.goodId = goodId;
        this.vipId = vipId;
        this.createTime = createTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGoodId() {
        return goodId;
    }

    public void setGoodId(String goodId) {
        this.goodId = goodId;
    }

    public String getVipId() {
        return vipId;
    }

    public void setVipId(String vipId) {
        this.vipId = vipId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Record{" +
                "id='" + id + '\'' +
                ", goodId='" + goodId + '\'' +
                ", vipId='" + vipId + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
