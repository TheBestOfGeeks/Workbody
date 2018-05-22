package sandroinc.workbody;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class Main extends AppCompatActivity {

    private TextView mTextMessage;


    // Метод для создания транцакций фрагментов
    public void fragmentTransaction (Fragment fragment){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container, fragment);
        ft.commit();
    }
    //.........

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    JournalFragment journalFragment = new JournalFragment();
                    fragmentTransaction(journalFragment);
                    return true;
                case R.id.navigation_dashboard:
                    TrainingFragment trainingFragment = new TrainingFragment();
                    fragmentTransaction(trainingFragment);
                    return true;
                case R.id.navigation_notifications:
                    SettingsFragment settingsFragment = new SettingsFragment();
                    fragmentTransaction(settingsFragment);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
