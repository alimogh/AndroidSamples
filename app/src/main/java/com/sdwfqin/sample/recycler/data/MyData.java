package com.sdwfqin.sample.recycler.data;

import java.util.UUID;

/**
 * Created by sdwfqin on 2016/7/7.
 */
public class MyData {

    private UUID id;
    private String title;

    private String text;

    public MyData() {

        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
