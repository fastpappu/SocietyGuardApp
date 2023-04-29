package abidahsoftware.co.in.societyguardapp;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


import java.util.Objects;

public class GuardVerificationActivity extends AppCompatActivity {

    TextInputLayout Guard_Name, password_lay;
    TextInputEditText edt_name, password_edt;
    Button login_start_btn;

    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guard_verification);

        Guard_Name = findViewById(R.id.Guard_Name);
        password_lay = findViewById(R.id.password_lay);

        edt_name = findViewById(R.id.edt_name);
        password_edt = findViewById(R.id.password_edt);

        DB = new DBHelper(this);

        login_start_btn = findViewById(R.id.login_start_btn);


        login_start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userName = Objects.requireNonNull(Guard_Name.getEditText()).getText().toString();
                String Password = Objects.requireNonNull(password_lay.getEditText()).getText().toString();

                if (!userName.isEmpty()) {
                    Guard_Name.setError(null);
                    Guard_Name.setErrorEnabled(false);
                    if (!Password.isEmpty()) {
                        password_lay.setError(null);
                        password_lay.setErrorEnabled(false);

                        Boolean checkUserPass = DB.checkUserNamePassword(userName, Password);
                        if (checkUserPass){
                            Toast.makeText(GuardVerificationActivity.this, "Welcome! Starting your Shift", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), ShiftActivityGuard.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(GuardVerificationActivity.this, "Username Or Password is invalid", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        password_lay.setError("Password Can not be Empty");
                    }
                } else {
                    Guard_Name.setError("Please Enter Full Name");
                }
            }
        });


    }
}