package org.example.module1.app;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddFragment extends Fragment {

    private Interface mainActivity;

    private Button save;

    private EditText url;
    private EditText description;
    private Spinner category;

    public AddFragment() {
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(getString(R.string.add));
        save = (Button) view.findViewById(R.id.save);
        url = (EditText) view.findViewById(R.id.url);
        description = (EditText) view.findViewById(R.id.description);
        category = (Spinner) view.findViewById(R.id.category);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Hyperlink h = new Hyperlink();
                h.URL = url.getText().toString();
                h.Description = description.getText().toString();
                h.Category = category.getSelectedItemPosition();
                if (mainActivity.onAddHyperlink(h)) {
                    url.setText("");
                    description.setText("");
                    category.setSelection(0);
                    Toast.makeText(getActivity(), getString(R.string.save_successful), Toast.LENGTH_SHORT).show();
                } else Toast.makeText(getActivity(), getString(R.string.save_unsuccessful), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add, container, false);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mainActivity = (Interface) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mainActivity = null;
    }

    public interface Interface {
        Boolean onAddHyperlink(Hyperlink h);
    }
}
