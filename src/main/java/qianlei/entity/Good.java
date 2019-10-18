package qianlei.entity;

import qianlei.enums.StatusEnum;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品类
 *
 * @author qianlei
 */
public class Good {
    private String id;
    private String name;
    private String maker;
    private Date createTime;
    private BigDecimal price;
    private double discount;
    private Long remain;
    private String introduction;
    private String remarks;
    private StatusEnum status;

    public Good() {
    }

    public Good(String id, String name, String maker, Date createTime, BigDecimal price, double discount, Long remain, String introduction, String remarks, StatusEnum status) {
        this.id = id;
        this.name = name;
        this.maker = maker;
        this.createTime = createTime;
        this.price = price;
        this.discount = discount;
        this.remain = remain;
        this.introduction = introduction;
        this.remarks = remarks;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Long getRemain() {
        return remain;
    }

    public void setRemain(Long remain) {
        this.remain = remain;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "Good{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", maker='" + maker + '\'' +
                ", createTime=" + createTime +
                ", price=" + price +
                ", discount=" + discount +
                ", remain=" + remain +
                ", introduction='" + introduction + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
