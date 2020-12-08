package com;

import javax.persistence.*;
import java.util.Objects;

/**
 * @Author: ZhangDehong
 * @Describe: TODO
 * @Date Create in  2:18 上午 2020/10/21
 */
@Entity
@Table(name = "worker", schema = "ad_extension_study", catalog = "")
public class WorkerEntity {

    private Object id;
    private String type;
    private String name;
    private Object salary;
    private String province;
    private String city;

    @Id
    @Column(name = "id")
    public Object getId () {
        return id;
    }

    public void setId (Object id) {
        this.id = id;
    }

    @Basic
    @Column(name = "type")
    public String getType () {
        return type;
    }

    public void setType (String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "name")
    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "salary")
    public Object getSalary () {
        return salary;
    }

    public void setSalary (Object salary) {
        this.salary = salary;
    }

    @Basic
    @Column(name = "province")
    public String getProvince () {
        return province;
    }

    public void setProvince (String province) {
        this.province = province;
    }

    @Basic
    @Column(name = "city")
    public String getCity () {
        return city;
    }

    public void setCity (String city) {
        this.city = city;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkerEntity that = (WorkerEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(type, that.type) && Objects.equals(name, that.name) && Objects.equals(salary, that.salary) && Objects.equals(province, that.province) && Objects.equals(city, that.city);
    }

    @Override
    public int hashCode () {
        return Objects.hash(id, type, name, salary, province, city);
    }
}
