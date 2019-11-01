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
    private int id;
    private String goodId;
    private String vipId;
    private Date createTime;
    private Good good;
    private Vip vip;
    private BigDecimal price;

    public Record() {
    }

    public Record(int id, String goodId, String vipId, Date createTime, BigDecimal buyPrice) {
        this.id = id;
        this.goodId = goodId;
        this.vipId = vipId;
        this.createTime = new Date(createTime.getTime());
        this.price = buyPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
        return new Date(createTime.getTime());
    }

    public void setCreateTime(Date createTime) {
        this.createTime = new Date(createTime.getTime());
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
        return id == record.id &&
                Objects.equals(goodId, record.goodId) &&
                Objects.equals(vipId, record.vipId) &&
                Objects.equals(createTime, record.createTime) &&
                Objects.equals(good, record.good) &&
                Objects.equals(vip, record.vip) &&
                Objects.equals(price, record.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, goodId, vipId, createTime, good, vip, price);
    }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", goodId='" + goodId + '\'' +
                ", vipId='" + vipId + '\'' +
                ", createTime=" + createTime +
                ", good=" + good +
                ", vip=" + vip +
                ", price=" + price +
                '}';
    }
}
