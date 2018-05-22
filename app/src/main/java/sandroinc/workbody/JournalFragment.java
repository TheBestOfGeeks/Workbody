package sandroinc.workbody;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class JournalFragment extends Fragment {


    public JournalFragment() {
        // Required empty public constructor
    }

    Button addNewProgram;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_journal, container, false);

        addNewProgram = (Button) view.findViewById(R.id.newprogram);
        addNewProgram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), NewprogrammActivity.class);
                startActivity(intent);
            }
        });


        return view;
    }

}
