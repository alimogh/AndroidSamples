package com.zhangqin.test;

/**
 * |================================================================================================
 * |
 * |    作    者：张钦
 * |
 * |    版    本：1.0
 * |
 * |    创建时间：2016/2/27  18:29
 * |
 * |    简要描述：学生类
 * |
 * |    修改历史：
 * |
 * |================================================================================================
 */
public class Student {

    private String name;
    private String result;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Student{" +
                ", name='" + name + '\'' +
                ", result='" + result + '\'' +
                '}';
    }

    public Student(String name, String result) {
        this.name = name;
        this.result = result;
    }
}
