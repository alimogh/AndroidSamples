package com.sdwfqin.greendaosample.view;

import com.sdwfqin.greendaosample.model.entry.Student;

import java.util.List;

/**
 * 描述：V层接口
 *
 * @author sdwfqin
 * @date 2017/5/5
 */
public interface MainView {

    /**
     * 显示加载动画
     */
    void showProgress();

    /**
     * 隐藏加载动画
     */
    void hideProgress();

    /**
     * 显示底部弹窗
     */
    void showBottomSheetDialog();

    /**
     * 更新数据
     *
     * @param student
     * @param position
     */
    void upData(Student student, int position);

    /**
     * 更新列表
     *
     * @param student
     * @param position
     */
    void upAdapter(Student student, int position);

    /**
     * 更新列表
     *
     * @param position
     */
    void upAdapter(int position);

    /**
     * 向列表添加数据
     *
     * @param student
     */
    void addAdapter(Student student);

    /**
     * 删除数据
     *
     * @param student
     * @param position
     */
    void delData(Student student, int position);

    /**
     * 显示吐司
     *
     * @param message
     */
    void showMessage(String message);

    /**
     * 设置条目
     *
     * @param items
     */
    void setItems(List<Student> items);
}
