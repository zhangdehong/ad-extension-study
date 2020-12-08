package com.hong.extension.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author: ZhangDeHong
 * @Describe: TODO
 * @Date Create in  9:27 下午 2020/8/23
 */
@Entity
@Table(name = "worker")
public class Worker implements Serializable {

    /**
     * @Id 主键标识
     * GenerationType 自增策略
     * TABLE 使用一个特定的表去保存主键
     * SEQUENCE: 根据数据库底层序列生成主键
     * IDENTITY：主键有数据库自动生成
     * AUTO: 由程序控制，是GenerationType的默认值
     * <p>
     * Column ：数据表列与属性的映射关系
     * name:数据表中字段的名称
     * nullable: 该字段是否允许为null，默认是true
     * unique: 字段值是否唯一，默认是false
     * length: 字段的大小，仅仅对String类型的字段有效
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * 员工类型
     **/
    @Basic //标识一个属性到数据表字段的映射
    @Column(name = "type", nullable = false)
    private String type;

    /**
     * 员工姓名
     **/
    @Basic
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * 工资
     **/
    @Basic
    @Column(name = "salary", nullable = false)
    private Long salary;

    @Basic
    @Column(name = "province", nullable = false)
    private String province;

    @Basic
    @Column(name = "city", nullable = false)
    private String city;
    /**
     * orm将忽略这个属性
     **/
    @Transient
    private String extraInfo;


    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public String getType () {
        return type;
    }

    public void setType (String type) {
        this.type = type;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public Long getSalary () {
        return salary;
    }

    public void setSalary (Long salary) {
        this.salary = salary;
    }

    public String getProvince () {
        return province;
    }

    public void setProvince (String province) {
        this.province = province;
    }

    public String getCity () {
        return city;
    }

    public void setCity (String city) {
        this.city = city;
    }

    public String getExtraInfo () {
        return extraInfo;
    }

    public void setExtraInfo (String extraInfo) {
        this.extraInfo = extraInfo;
    }

    public Worker () {
    }

    /**
     * <h2>构造函数</h2>
     *
     * @param name
     * @param salary
     */
    public Worker (String name, Long salary) {
        this.name = name;
        this.salary = salary;
    }
}
