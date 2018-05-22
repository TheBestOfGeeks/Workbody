package sandroinc.workbody;

import android.app.FragmentTransaction;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewprogrammActivity extends AppCompatActivity {

    private RecyclerView mGymnasticRecyclerView;
    private NewgimnasticAdapter mAdapter;
    private List<String> mGymnastics;
    EditText nameOfProgram;
    Button safeProgram, newExercise;

    private FirebaseFirestore mFirebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newprogram);

        mFirebaseFirestore = FirebaseFirestore.getInstance();

        mGymnasticRecyclerView = (RecyclerView) findViewById(R.id.gymnastic_recycler_view);
        mGymnasticRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        nameOfProgram = (EditText) findViewById(R.id.programName);
        safeProgram = (Button) findViewById(R.id.safeProgram);
        newExercise = (Button) findViewById(R.id.addExercise);
        mAdapter = new NewgimnasticAdapter(mGymnastics);
        mGymnasticRecyclerView.setAdapter(mAdapter);

         // обработчик кнопки добавить упражнение
        newExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getSupportFragmentManager();
                AddNewExerciseFragment addNewExerciseFragment = new AddNewExerciseFragment();
                addNewExerciseFragment.show(manager, "dialog");
            }
        });

        //  Обработчик кнопки сохранить
        safeProgram.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String name = nameOfProgram.getText().toString();
                if (name.length() == 0) {
                    Toast toast = Toast.makeText(getApplicationContext(),"Придумай название программы, пожалуйста.", Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    Map<String, String> programmMap = new HashMap<>();
                    programmMap.put("name", name);
                    mFirebaseFirestore.collection("programms").add(programmMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                                   Toast.makeText ( NewprogrammActivity.this, "Программа создана", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText ( NewprogrammActivity.this, "Ошибка", Toast.LENGTH_SHORT).show();
                        }
                    }) ;

                }
            }
        });
    }




    private class GymnasticHolder extends RecyclerView.ViewHolder {
        public TextView mGymnasticSets;
        public TextView mGymnasticName;

        public GymnasticHolder(View itemView) {

            super(itemView);
            mGymnasticSets = (TextView) itemView.findViewById(R.id.gymnastic_sets);
            mGymnasticName = (TextView) itemView.findViewById(R.id.gymnastic_name);
        }

        }

    // Адаптер - для построения списка (которому передается объект класса Холдер)
    private class  NewgimnasticAdapter extends RecyclerView.Adapter<GymnasticHolder> {

        public NewgimnasticAdapter(List<String> programs) {
            mGymnastics = programs;
        }

        @Override
        public GymnasticHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.list_item_gymnastics, parent, false);
            return new GymnasticHolder(view);
        }

        @Override
        public void onBindViewHolder(GymnasticHolder holder, int position) {
            holder.mGymnasticSets.setText("Подходыыыы");
            holder.mGymnasticName.setText("Имяяяя");
        }

        @Override
        public int getItemCount() {
            return 10;
        }
    }
    }




