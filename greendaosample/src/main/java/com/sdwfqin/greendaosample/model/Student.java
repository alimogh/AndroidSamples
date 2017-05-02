package com.sdwfqin.greendaosample.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Unique;

/**
 * Created by sdwfqin on 2016/12/13.
 */
@Entity
public class Student {

    // 对象的Id，使用Long类型作为EntityId，否则会报错。
    // (autoincrement = true)表示主键会自增，如果false就会使用旧值
    @Id(autoincrement = true)
    private long id;
    // 在数据库中必须是唯一的
    @Unique
    private String name;
    // 属性不能为空
    @NotNull
    private String sex;
    private String address;

    // 编译后自动生成的构造函数、方法等的注释，提示构造函数、方法等不能被修改
    @Generated(hash = 611957646)
    public Student(long id, String name, @NotNull String sex, String address) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.address = address;
    }

    @Generated(hash = 1556870573)
    public Student() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age='" + sex + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
