package qianlei.entity;

import qianlei.enums.StatusEnum;

import java.util.Date;
import java.util.Objects;

/**
 * vip类
 *
 * @author qianlei
 */
public class Vip {
    public static final int POSTCODE_LENGTH = 6;
    public static final String MAN = "男";
    public static final String WOMAN = "女";
    private String id;
    private String name;
    private String sex;
    private String phone;
    private String address;
    private int postcode;
    private Date createTime;
    private StatusEnum status;

    public Vip() {
        id = "";
        name = "";
        sex = MAN;
        phone = "";
        address = "";
        postcode = 100000;
        createTime = new Date();
    }

    public Vip(String id, String name, String sex, String phone, String address, int postcode, Date createTime, StatusEnum status) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.phone = phone;
        this.address = address;
        this.postcode = postcode;
        this.createTime = new Date(createTime.getTime());
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
        return new Date(createTime.getTime());
    }

    public void setCreateTime(Date createTime) {
        this.createTime = new Date(createTime.getTime());
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
        Vip vip = (Vip) o;
        return postcode == vip.postcode &&
                Objects.equals(id, vip.id) &&
                Objects.equals(name, vip.name) &&
                Objects.equals(sex, vip.sex) &&
                Objects.equals(phone, vip.phone) &&
                Objects.equals(address, vip.address) &&
                Objects.equals(createTime, vip.createTime) &&
                status == vip.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, sex, phone, address, postcode, createTime, status);
    }

    @Override
    public String toString() {
        return "Vip{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", postcode=" + postcode +
                ", createTime=" + createTime +
                ", status=" + status +
                '}';
    }
}
