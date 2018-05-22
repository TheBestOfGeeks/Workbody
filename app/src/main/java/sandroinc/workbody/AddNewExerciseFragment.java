package sandroinc.workbody;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

public class AddNewExerciseFragment extends DialogFragment {

    static AddNewExerciseFragment newInstance() {
        return new AddNewExerciseFragment();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_newexercise, null);

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle("Новое упражнение")
                .setPositiveButton(android.R.string.ok, null)
                .create();
    }


}
