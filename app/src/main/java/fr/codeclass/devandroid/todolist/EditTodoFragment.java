package fr.codeclass.devandroid.todolist;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditTodoFragment extends Fragment {

    private TextSubmitListener textSubmitListener;
    private EditText editText;
    private Button submitBtn;
    private TodoDao todoDao = TodoDao.getInstance();

    public EditTodoFragment() {
        // Required empty public constructor
    }

    public interface TextSubmitListener {
        void onTextSubmitted();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            textSubmitListener = (TextSubmitListener)getActivity();
        }catch (ClassCastException e){
            throw new ClassCastException(getActivity().toString() +" must implement TextSubmitListener");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_todo, container, false);
        editText = (EditText) view.findViewById(R.id.editTodo);
        submitBtn = (Button) view.findViewById(R.id.submitButton);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = editText.getText().toString();
                if (s != null && !"".equals(s)) {
                    editText.getText().clear();
                     if (selectedTodoItem == null)
                        todoDao.putTodoItem(new TodoItem(s));
                    else {
                        selectedTodoItem.setText(s);
                    }
                    textSubmitListener.onTextSubmitted();

                }
            }
        });

        return view;
    }
    private TodoItem selectedTodoItem;
    public void updateSelectedTodoItem(TodoItem todoItem){
        this.selectedTodoItem = todoItem;
        editText.setText(todoItem.getText());
    }


}
