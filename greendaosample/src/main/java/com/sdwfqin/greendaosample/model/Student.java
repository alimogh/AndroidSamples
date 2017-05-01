package com.sdwfqin.greendaosample.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

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
    private String age;
    private String address;
    @NotNull
    private int top;

    // 编译后自动生成的构造函数、方法等的注释，提示构造函数、方法等不能被修改
    @Generated(hash = 563604607)
    public Student(long id, String name, @NotNull String age, String address,
            int top) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.top = top;
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
    public String getAge() {
        return this.age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public int getTop() {
        return this.top;
    }
    public void setTop(int top) {
        this.top = top;
    }
}
