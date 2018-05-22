package sandroinc.workbody;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Authentication extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private static final String TAG = "EmailPassword";
    EditText etEmail, etPassword;
    Button btnLogin, btnRegistration;
    String email, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        etEmail = findViewById(R.id.email);
        etPassword = findViewById(R.id.password);
        btnLogin =  findViewById(R.id.login);
        btnRegistration = findViewById(R.id.registration);

        mAuth = FirebaseAuth.getInstance();

        etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                etPassword.setEnabled(true);
            }
        });


        isFieldNotEmpty(etPassword, btnLogin);
        isFieldNotEmpty(etPassword, btnRegistration);


        // Кнопка регистрации
        btnRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = etEmail.getText().toString();
                password = etPassword.getText().toString();
                if (email.isEmpty()) {
                    Toast.makeText(Authentication.this, "Заполните поле email",
                            Toast.LENGTH_SHORT).show();
                }    else {
                    registration(email, password);;
                }
            }
        });

        // Кнопка авторизации
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = etEmail.getText().toString();
                password = etPassword.getText().toString();
                if (email.isEmpty()) {
                    Toast.makeText(Authentication.this, "Заполните поле email",
                            Toast.LENGTH_SHORT).show();
                }    else {
                    singin(email, password);
                }
            }
        });


    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    public void registration (String emailParameter, String passwordParameter) {
        mAuth.createUserWithEmailAndPassword(emailParameter, passwordParameter)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "Регистрация прошла успешно");
                            FirebaseUser user = mAuth.getCurrentUser();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(Authentication.this, "Некорректные данные",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

    public void singin (String emailParameter, String passwordParameter) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "Вы вошли");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(getBaseContext(), Main.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(Authentication.this, "Неверный логин и/или пароль",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }
    // Enable Send button when there's text to send
    public void isFieldNotEmpty (final TextView textView, final Button button) {

        textView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().length() > 0) {
                    button.setEnabled(true);
                } else {
                    button.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

}
