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
    private Integer id;
    private Integer userId;
    private String recordNo;
    private String vipId;
    private String vipName;
    private String vipPhone;
    private Date createTime;
    private BigDecimal price;

    public Record() {
    }

    public Record(Integer userId, String recordNo, String vipId, String vipName, String vipAddress, Date createTime, BigDecimal price) {
        this.userId = userId;
        this.recordNo = recordNo;
        this.vipId = vipId;
        this.vipName = vipName;
        this.vipPhone = vipAddress;
        this.createTime = createTime;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRecordNo() {
        return recordNo;
    }

    public void setRecordNo(String recordNo) {
        this.recordNo = recordNo;
    }

    public String getVipId() {
        return vipId;
    }

    public void setVipId(String vipId) {
        this.vipId = vipId;
    }

    public String getVipName() {
        return vipName;
    }

    public void setVipName(String vipName) {
        this.vipName = vipName;
    }

    public String getVipPhone() {
        return vipPhone;
    }

    public void setVipPhone(String vipPhone) {
        this.vipPhone = vipPhone;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Record record = (Record) o;
        return Objects.equals(id, record.id) &&
                Objects.equals(userId, record.userId) &&
                Objects.equals(recordNo, record.recordNo) &&
                Objects.equals(vipId, record.vipId) &&
                Objects.equals(vipName, record.vipName) &&
                Objects.equals(vipPhone, record.vipPhone) &&
                Objects.equals(createTime, record.createTime) &&
                Objects.equals(price, record.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, recordNo, vipId, vipName, vipPhone, createTime, price);
    }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", userId=" + userId +
                ", recordNo='" + recordNo + '\'' +
                ", vipId='" + vipId + '\'' +
                ", vipName='" + vipName + '\'' +
                ", vipAddress='" + vipPhone + '\'' +
                ", createTime=" + createTime +
                ", price=" + price +
                '}';
    }
}
