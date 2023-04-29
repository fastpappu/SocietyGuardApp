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

public class CustomGuardUser extends AppCompatActivity {

    TextInputEditText edt_name,password_edt_sql;
    TextInputLayout input_edt_lay_1,password_lay_custom;
    Button super_btn;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custome_guard_user);

        edt_name = findViewById(R.id.edt_name);
        password_edt_sql = findViewById(R.id.password_edt_sql);
        input_edt_lay_1 = findViewById(R.id.input_edt_lay_1);
        password_lay_custom = findViewById(R.id.password_lay_custom);

        DB = new DBHelper(CustomGuardUser.this);


        super_btn = findViewById(R.id.super_btn);

        super_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String guardName = Objects.requireNonNull(edt_name.getText()).toString();
                String password = Objects.requireNonNull(password_edt_sql.getText()).toString();

                if (!guardName.isEmpty()){
                    input_edt_lay_1.setError(null);
                    input_edt_lay_1.setErrorEnabled(false);
                    if (!password.isEmpty()){
                        password_lay_custom.setError(null);
                        password_lay_custom.setErrorEnabled(false);

                        Boolean checkUser = DB.checkUserName(guardName);
                        if (!checkUser){
                            Boolean insert = DB.insertData(guardName,password);
                            if (insert){
                                Toast.makeText(CustomGuardUser.this, "Guard Registration Successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), GuardVerificationActivity.class);
                                startActivity(intent);
                                finish();
                            }else {
                                Toast.makeText(CustomGuardUser.this, "failed to create", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }else{
                        password_lay_custom.setError("Please Enter Password");
                    }
                }else {
                    input_edt_lay_1.setError("Please Enter Your Name");
                }
            }
        });


    }
}