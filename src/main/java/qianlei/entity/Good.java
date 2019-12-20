package qianlei.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.Objects;

/**
 * 商品类
 *
 * @author qianlei
 */
public class Good {
    private Integer id;
    private Integer userId;
    private String goodNo;
    private String name;
    private String maker;
    private Date createTime;
    private BigDecimal price;
    private BigDecimal discount;
    private long remain;
    private String introduction;
    private String remarks;

    public Good() {

    }

    public Good(Integer userId, String goodNo, String name, String maker, Date createTime, BigDecimal price, BigDecimal discount, long remain, String introduction, String remarks) {
        this.userId = userId;
        this.goodNo = goodNo;
        this.name = name;
        this.maker = maker;
        this.createTime = createTime;
        this.price = price;
        this.discount = discount;
        this.remain = remain;
        this.introduction = introduction;
        this.remarks = remarks;
    }

    public Good(Integer id, Integer userId, String goodNo, String name, String maker, Date createTime, BigDecimal price, BigDecimal discount, long remain, String introduction, String remarks) {
        this.id = id;
        this.userId = userId;
        this.goodNo = goodNo;
        this.name = name;
        this.maker = maker;
        this.createTime = createTime;
        this.price = price;
        this.discount = discount;
        this.remain = remain;
        this.introduction = introduction;
        this.remarks = remarks;
    }

    public BigDecimal getRealPrice() {
        return price.multiply(discount).setScale(2, RoundingMode.HALF_UP);
    }

    public Integer getId() {
        return id;
    }

    public Good setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getUserId() {
        return userId;
    }

    public Good setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public String getGoodNo() {
        return goodNo;
    }

    public Good setGoodNo(String goodNo) {
        this.goodNo = goodNo;
        return this;
    }

    public String getName() {
        return name;
    }

    public Good setName(String name) {
        this.name = name;
        return this;
    }

    public String getMaker() {
        return maker;
    }

    public Good setMaker(String maker) {
        this.maker = maker;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Good setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Good setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public Good setDiscount(BigDecimal discount) {
        this.discount = discount;
        return this;
    }

    public long getRemain() {
        return remain;
    }

    public Good setRemain(long remain) {
        this.remain = remain;
        return this;
    }

    public String getIntroduction() {
        return introduction;
    }

    public Good setIntroduction(String introduction) {
        this.introduction = introduction;
        return this;
    }

    public String getRemarks() {
        return remarks;
    }

    public Good setRemarks(String remarks) {
        this.remarks = remarks;
        return this;
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
        return remain == good.remain &&
                Objects.equals(id, good.id) &&
                Objects.equals(goodNo, good.goodNo) &&
                Objects.equals(userId, good.userId) &&
                Objects.equals(name, good.name) &&
                Objects.equals(maker, good.maker) &&
                Objects.equals(createTime, good.createTime) &&
                Objects.equals(price, good.price) &&
                Objects.equals(discount, good.discount) &&
                Objects.equals(introduction, good.introduction) &&
                Objects.equals(remarks, good.remarks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, goodNo, userId, name, maker, createTime, price, discount, remain, introduction, remarks);
    }

    @Override
    public String toString() {
        return "Good{" +
                "id=" + id +
                ", goodId='" + goodNo + '\'' +
                ", userId='" + userId + '\'' +
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
