package fr.codeclass.devandroid.todolist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;


public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private MyAdapter myAdapter;
    private TodoDao todoDao = TodoDao.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.todoList);

        myAdapter = new MyAdapter(this, R.layout.one_todo_item, todoDao.getTodoItemList());
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                TodoItem todoItem = (TodoItem)
                adapterView.getItemAtPosition(i);

                todoItem.setStatus(
                        todoItem.getStatus().equals(TodoItem.TodoStatus.Done) ? TodoItem.TodoStatus.Not_Done : TodoItem.TodoStatus.Done);

                listView.invalidateViews();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if(id== R.id.action_add){

            Intent intent = new Intent(this,TodoDetailActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        listView.invalidateViews();
    }



    private class MyAdapter extends ArrayAdapter<TodoItem>{
        private int resourceLayout;
        public MyAdapter(Context context, int resource, List<TodoItem> todoItems) {
            super(context, resource, todoItems);
            this.resourceLayout = resource;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);

            View v = inflater.inflate(resourceLayout, parent, false);

            TextView textView = (TextView) v.findViewById(R.id.todoTextView);

            TodoItem todoItem = getItem(position);
            textView.setText(todoItem.getText());
            if(TodoItem.TodoStatus.Done.equals(todoItem.getStatus())){
                textView.getPaint().setStrikeThruText(true);
                ImageView imageView = (ImageView) v.findViewById(R.id.checkBoxImageView);
                imageView.setImageResource(R.drawable.ic_action_checkbox_ok);
            }

            return v;
        }
    }
}
