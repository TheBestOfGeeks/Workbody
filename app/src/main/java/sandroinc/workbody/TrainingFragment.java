package sandroinc.workbody;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;



/**
 * A simple {@link Fragment} subclass.
 */
public class TrainingFragment extends Fragment {


    private RecyclerView mProgramRecyclerView;
    private NewprogramAdapter mAdapter;
    private List<String> mPrograms;


    public TrainingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_training, container, false);

        mProgramRecyclerView = (RecyclerView) view.findViewById(R.id.program_recycler_view);
        mProgramRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new NewprogramAdapter(mPrograms);
        mProgramRecyclerView.setAdapter(mAdapter);



        return view;
    }



    private class ProgramHolder extends RecyclerView.ViewHolder {

        public TextView mProgramName;

        public ProgramHolder(View itemView) {
            super(itemView);
            mProgramName = (TextView) itemView.findViewById(R.id.program_name);

        }

    }

    // Адаптер - для построения списка (которому передается объект класса Холдер)
    private class  NewprogramAdapter extends RecyclerView.Adapter<ProgramHolder> {

        public NewprogramAdapter(List<String> programs) {
            mPrograms = programs;
        }

        @Override
        public ProgramHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.list_item_programs, parent, false);
            return new ProgramHolder(view);
        }

        @Override
        public void onBindViewHolder(ProgramHolder holder, int position) {
    
        }

        @Override
        public int getItemCount() {
            return 10;
        }
    }



}
