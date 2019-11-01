package qianlei.entity;

import qianlei.enums.StatusEnum;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

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
    private long remain;
    private String introduction;
    private String remarks;
    private StatusEnum status;

    public Good() {
        id = "";
        name = "";
        maker = "";
        createTime = new Date();
        price = BigDecimal.ZERO;
        discount = 1.0;
        remain = 0;
        introduction = "";
        remarks = "";
    }

    public Good(String id, String name, String maker, Date createTime, BigDecimal price, double discount, long remain, String introduction, String remarks, StatusEnum status) {
        this.id = id;
        this.name = name;
        this.maker = maker;
        this.createTime = new Date(createTime.getTime());
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
        return new Date(createTime.getTime());
    }

    public void setCreateTime(Date createTime) {
        this.createTime = new Date(createTime.getTime());
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

    public long getRemain() {
        return remain;
    }

    public void setRemain(long remain) {
        this.remain = remain;
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

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Good good = (Good) o;
        return Double.compare(good.discount, discount) == 0 &&
                remain == good.remain &&
                Objects.equals(id, good.id) &&
                Objects.equals(name, good.name) &&
                Objects.equals(maker, good.maker) &&
                Objects.equals(createTime, good.createTime) &&
                Objects.equals(price, good.price) &&
                Objects.equals(introduction, good.introduction) &&
                Objects.equals(remarks, good.remarks) &&
                status == good.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, maker, createTime, price, discount, remain, introduction, remarks, status);
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
                ", status=" + status +
                '}';
    }
}
