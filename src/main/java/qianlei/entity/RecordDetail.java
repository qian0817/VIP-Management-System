package qianlei.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * 消费记录中具体的购买商品
 *
 * @author qianlei
 */
public class RecordDetail {
    private Integer id;
    private String recordNo;
    private Integer userId;
    private String goodNo;
    private String goodName;
    private BigDecimal price;
    private Integer number;

    public RecordDetail(Integer id, String recordNo, Integer userId, String goodNo, String goodName, BigDecimal price, Integer number) {
        this.id = id;
        this.recordNo = recordNo;
        this.userId = userId;
        this.goodNo = goodNo;
        this.goodName = goodName;
        this.price = price;
        this.number = number;
    }

    public RecordDetail(String recordNo, Integer userId, String goodNo, String goodName, BigDecimal price, Integer number) {
        this.recordNo = recordNo;
        this.userId = userId;
        this.goodNo = goodNo;
        this.goodName = goodName;
        this.price = price;
        this.number = number;
    }

    public RecordDetail() {
    }

    public BigDecimal getTotalPrice() {
        return price.multiply(new BigDecimal(number)).setScale(2, RoundingMode.HALF_UP);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRecordNo() {
        return recordNo;
    }

    public void setRecordNo(String recordNo) {
        this.recordNo = recordNo;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getGoodNo() {
        return goodNo;
    }

    public void setGoodNo(String goodNo) {
        this.goodNo = goodNo;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RecordDetail that = (RecordDetail) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(recordNo, that.recordNo) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(goodNo, that.goodNo) &&
                Objects.equals(goodName, that.goodName) &&
                Objects.equals(price, that.price) &&
                Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, recordNo, userId, goodNo, goodName, price, number);
    }

    @Override
    public String toString() {
        return "RecordDetail{" +
                "id=" + id +
                ", recordNo='" + recordNo + '\'' +
                ", userId=" + userId +
                ", goodNo='" + goodNo + '\'' +
                ", goodName='" + goodName + '\'' +
                ", price=" + price +
                ", number=" + number +
                '}';
    }
}
