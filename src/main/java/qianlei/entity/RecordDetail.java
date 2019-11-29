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
    private String recordId;
    private String goodId;
    private String goodName;
    private BigDecimal price;
    private Integer number;

    public RecordDetail(String recordId, String goodId, String goodName, BigDecimal price, Integer number) {
        this.recordId = recordId;
        this.goodId = goodId;
        this.goodName = goodName;
        this.price = price;
        this.number = number;
    }

    public RecordDetail() {
    }

    public BigDecimal getTotalPrice() {
        return price.multiply(new BigDecimal(number)).setScale(2, RoundingMode.HALF_UP);
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getGoodId() {
        return goodId;
    }

    public void setGoodId(String goodId) {
        this.goodId = goodId;
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
        return recordId.equals(that.recordId) &&
                Objects.equals(goodId, that.goodId) &&
                Objects.equals(goodName, that.goodName) &&
                Objects.equals(price, that.price) &&
                Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recordId, goodId, goodName, price, number);
    }

    @Override
    public String toString() {
        return "RecordDetail{" +
                "recordId=" + recordId +
                ", goodId=" + goodId +
                ", goodName='" + goodName + '\'' +
                ", price=" + price +
                ", number=" + number +
                '}';
    }
}
