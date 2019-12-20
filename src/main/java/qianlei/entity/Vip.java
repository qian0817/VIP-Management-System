package qianlei.entity;

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
    private Integer id;
    private Integer userId;
    private String vipNo;
    private String name;
    private String sex;
    private String phone;
    private String address;
    private String email;
    private Date createTime;

    public Vip() {
    }

    public Vip(Integer userId, String vipNo, String name, String sex, String phone, String address, String email, Date createTime) {
        this.userId = userId;
        this.vipNo = vipNo;
        this.name = name;
        this.sex = sex;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.createTime = createTime;
    }

    public Vip(Integer id, Integer userId, String vipNo, String name, String sex, String phone, String address, String email, Date createTime) {
        this.id = id;
        this.userId = userId;
        this.vipNo = vipNo;
        this.name = name;
        this.sex = sex;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.createTime = createTime;
    }

    public Vip(Integer userId, String vipNo, String name, String sex, String phone, String address, String email) {
        this.userId = userId;
        this.vipNo = vipNo;
        this.name = name;
        this.sex = sex;
        this.phone = phone;
        this.address = address;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public Vip setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getUserId() {
        return userId;
    }

    public Vip setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public String getVipNo() {
        return vipNo;
    }

    public Vip setVipNo(String vipNo) {
        this.vipNo = vipNo;
        return this;
    }

    public String getName() {
        return name;
    }

    public Vip setName(String name) {
        this.name = name;
        return this;
    }

    public String getSex() {
        return sex;
    }

    public Vip setSex(String sex) {
        this.sex = sex;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Vip setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Vip setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Vip setEmail(String email) {
        this.email = email;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Vip setCreateTime(Date createTime) {
        this.createTime = createTime;
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
        Vip vip = (Vip) o;
        return Objects.equals(id, vip.id) &&
                Objects.equals(userId, vip.userId) &&
                Objects.equals(vipNo, vip.vipNo) &&
                Objects.equals(name, vip.name) &&
                Objects.equals(sex, vip.sex) &&
                Objects.equals(phone, vip.phone) &&
                Objects.equals(address, vip.address) &&
                Objects.equals(email, vip.email) &&
                Objects.equals(createTime, vip.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, vipNo, name, sex, phone, address, email, createTime);
    }

    @Override
    public String toString() {
        return "Vip{" +
                "id=" + id +
                ", userId=" + userId +
                ", vipId='" + vipNo + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
