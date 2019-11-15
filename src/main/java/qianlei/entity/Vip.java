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
    public static final String MAN = "男";
    public static final String WOMAN = "女";
    private String id;
    private String name;
    private String sex;
    private String phone;
    private String address;
    private String email;
    private Date createTime;
    private StatusEnum status;

    public Vip() {
        id = "";
        name = "";
        sex = MAN;
        phone = "";
        address = "";
        email = "";
        createTime = new Date();
    }

    public Vip(String id, String name, String sex, String phone, String address, String email, Date createTime, StatusEnum status) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.createTime = new Date(createTime.getTime());
        this.status = status;
    }

    public Vip(String id, String name, String sex, String phone, String address, String email) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.phone = phone;
        this.address = address;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        return email.equals(vip.email) &&
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
        return Objects.hash(id, name, sex, phone, address, email, createTime, status);
    }

    @Override
    public String toString() {
        return "Vip{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", email=" + email +
                ", createTime=" + createTime +
                ", status=" + status +
                '}';
    }
}
