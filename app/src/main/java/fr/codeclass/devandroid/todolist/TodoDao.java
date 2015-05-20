package fr.codeclass.devandroid.todolist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fengqin on 20/05/2015.
 */
public class TodoDao {

    private static TodoDao todoDao = new TodoDao();

    private TodoDao(){}

    public static TodoDao getInstance(){
        return todoDao;
    }

    private List<TodoItem> db=new ArrayList<>();

    private Integer idCounter = 0;

    public void putTodoItem(TodoItem todoItem){
        idCounter++;
        todoItem.setId(idCounter);
        db.add(todoItem);
    }

    public TodoItem getTodoItemById(Integer id){
       for (TodoItem ti : db){
           if(ti.getId().equals(id))
               return ti;
       }
        return null;
    }

    public List<TodoItem> getTodoItemList(){
        return db;
    }

}
