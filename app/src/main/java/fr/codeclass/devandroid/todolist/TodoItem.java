package fr.codeclass.devandroid.todolist;

import android.content.Intent;

/**
 * Created by fengqin on 20/05/2015.
 */
public class TodoItem {

    private Integer id;
    private String text;
    private TodoStatus status = TodoStatus.Not_Done;

    public TodoItem(String s){
        this.text=s;
    }

    enum TodoStatus{
        Not_Done,
        Done
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public TodoStatus getStatus() {
        return status;
    }

    public void setStatus(TodoStatus status) {
        this.status = status;
    }
}

