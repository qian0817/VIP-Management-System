package qianlei.entity;

import java.util.Date;

/**
 * 记录类
 *
 * @author qianlei
 */
public class Record {
    private Integer id;
    private String goodId;
    private String vipId;
    private Date createTime;
    private Good good;
    private Vip vip;

    public Record() {
    }

    public Record(Integer id, String goodId, String vipId, Date createTime) {
        this.id = id;
        this.goodId = goodId;
        this.vipId = vipId;
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Good getGood() {
        return good;
    }

    public void setGood(Good good) {
        this.good = good;
    }

    public Vip getVip() {
        return vip;
    }

    public void setVip(Vip vip) {
        this.vip = vip;
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
