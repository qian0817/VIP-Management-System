package qianlei.entity;

import java.math.BigDecimal;
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
    private BigDecimal price;

    public Record() {
    }

    public Record(Integer id, String goodId, String vipId, Date createTime, BigDecimal buyPrice) {
        this.id = id;
        this.goodId = goodId;
        this.vipId = vipId;
        this.createTime = createTime;
        this.price = buyPrice;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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
                ", buyPrice=" + price +
                '}';
    }
}
