package fr.codeclass.devandroid.todolist;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity implements TodoListFragment.TodoListItemClickAction,
        EditTodoFragment.TextSubmitListener {

    private boolean m2panelsMode=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        m2panelsMode=(getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE);

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

    }

    @Override
    public void onTodoListItemClicked(TodoItem todoItem) {

        if(m2panelsMode){
            EditTodoFragment fragment = (EditTodoFragment)
                    getSupportFragmentManager().findFragmentById(R.id.fragmentEditTodo);
            fragment.updateSelectedTodoItem(todoItem);

        }else {
            Intent intent = new Intent(this, TodoDetailActivity.class);
            intent.putExtra(TodoItem.ID, todoItem.getId());
            startActivity(intent);
        }
    }

    @Override
    public void onTextSubmitted() {
        if(m2panelsMode){
            ListView listView = (ListView) this.findViewById(R.id.todoList);
            listView.invalidateViews();
        }

    }
}
