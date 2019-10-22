package qianlei.entity;

import qianlei.enums.StatusEnum;

import java.util.Date;

/**
 * vip类
 *
 * @author qianlei
 */
public class Vip {
    private String id;
    private String name;
    private String sex;
    private String phone;
    private String address;
    private int postcode;
    private Date createTime;
    private StatusEnum status;

    public Vip() {
    }

    public Vip(String id, String name, String sex, String phone, String address, int postcode, Date createTime, StatusEnum status) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.phone = phone;
        this.address = address;
        this.postcode = postcode;
        this.createTime = createTime;
        this.status = status;
    }

    public Vip(String id, String name, String sex, String phone, String address, int postcode) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.phone = phone;
        this.address = address;
        this.postcode = postcode;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Vip{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", postcode=" + postcode +
                ", createTime=" + createTime +
                ", status=" + status +
                '}';
    }
}
