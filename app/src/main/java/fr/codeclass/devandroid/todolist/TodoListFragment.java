package fr.codeclass.devandroid.todolist;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class TodoListFragment extends Fragment {

    private TodoListItemClickAction todoListItemClickAction;
    private ListView listView;
    private MyAdapter myAdapter;
    private TodoDao todoDao = TodoDao.getInstance();

    public interface TodoListItemClickAction{

        void onTodoListItemClicked(TodoItem todoItem);
    }

    public TodoListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_todo_list, container, false);
        listView = (ListView) view.findViewById(R.id.todoList);

        myAdapter = new MyAdapter(getActivity(), R.layout.one_todo_item, todoDao.getTodoItemList());
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                todoListItemClickAction.onTodoListItemClicked((TodoItem)listView.getItemAtPosition(position));
            }
        });

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            todoListItemClickAction = (TodoListItemClickAction) getActivity();
        }catch (ClassCastException e){
            throw new ClassCastException(getActivity().toString() +" must implement TodoListItemClickAction");
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        listView.invalidateViews();
    }

    private class MyAdapter extends ArrayAdapter<TodoItem> {
        private int resourceLayout;
        public MyAdapter(Context context, int resource, List<TodoItem> todoItems) {
            super(context, resource, todoItems);
            this.resourceLayout = resource;
        }

        private static final int TODO_TEXT_LENGTH = 30;
        private static final String ADDITIONAL_POINTS = "...";
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater)getActivity().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            View v = inflater.inflate(resourceLayout, parent, false);

            TextView textView = (TextView) v.findViewById(R.id.todoTextView);
            String todoText = getItem(position).getText();
            if(todoText!=null && todoText.length()>TODO_TEXT_LENGTH){
                todoText = todoText.substring(0, TODO_TEXT_LENGTH-1)+ADDITIONAL_POINTS;
            }
            textView.setText(todoText);

            return v;
        }
    }
}
