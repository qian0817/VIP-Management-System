package qianlei.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * 记录类
 *
 * @author qianlei
 */
public class Record {
    private String id;
    private String vipId;
    private Date createTime;
    private Vip vip;
    private BigDecimal price;

    public Record() {
    }

    public Record(String id, String vipId, Date createTime, BigDecimal buyPrice) {
        this.id = id;
        this.vipId = vipId;
        this.createTime = new Date(createTime.getTime());
        this.price = buyPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVipId() {
        return vipId;
    }

    public void setVipId(String vipId) {
        this.vipId = vipId;
    }

    public Date getCreateTime() {
        return new Date(createTime.getTime());
    }

    public void setCreateTime(Date createTime) {
        this.createTime = new Date(createTime.getTime());
    }

    public Vip getVip() {
        return vip;
    }

    public void setVip(Vip vip) {
        this.vip = vip;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Record record = (Record) o;
        return id.equals(record.id) &&
                Objects.equals(vipId, record.vipId) &&
                Objects.equals(createTime, record.createTime) &&
                Objects.equals(vip, record.vip) &&
                Objects.equals(price, record.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, vipId, createTime, vip, price);
    }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", vipId='" + vipId + '\'' +
                ", createTime=" + createTime +
                ", vip=" + vip +
                ", price=" + price +
                '}';
    }
}
