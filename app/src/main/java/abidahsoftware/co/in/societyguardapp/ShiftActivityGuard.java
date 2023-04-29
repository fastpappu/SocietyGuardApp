package abidahsoftware.co.in.societyguardapp;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;


import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Objects;

public class ShiftActivityGuard extends AppCompatActivity {

    private final Calendar myCalendar = Calendar.getInstance();
    TextInputLayout input_edt_lay_1, input_edt_lay_2, input_lay_auto_3, input_lay_auto_4,
            input_lay_auto_5, input_edt_lay_6, input_edt_lay_7,input_lay_auto_8;
    TextInputEditText edt_name, edt_name_security, shift_date, shift_time;
    AutoCompleteTextView Auto_Complete_society, Auto_Complete_gate, Auto_Complete_City, Auto_Complete_state;
    AppCompatButton start_btn;
    ArrayAdapter<CharSequence> societyNameAdapter;
    ArrayAdapter<CharSequence> gateIdAdapter;
    ArrayAdapter<CharSequence> indianStateAdapter;
    ArrayAdapter<CharSequence> indianCityAdapter;

    StorageReference storageReference = null;

    DatabaseReference databaseReference = null;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custum_signup);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("GuardData").child("GuardName");
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();

        input_edt_lay_1 = findViewById(R.id.input_edt_lay_1);
        input_edt_lay_2 = findViewById(R.id.input_edt_lay_2);
        input_lay_auto_3 = findViewById(R.id.input_lay_auto_3);
        input_lay_auto_4 = findViewById(R.id.input_lay_auto_4);
        input_lay_auto_5 = findViewById(R.id.input_lay_auto_5);
        input_edt_lay_6 = findViewById(R.id.input_edt_lay_6);
        input_edt_lay_7 = findViewById(R.id.input_edt_lay_7);
        input_lay_auto_8 = findViewById(R.id.input_lay_auto_8);


        edt_name = findViewById(R.id.edt_name);
        edt_name_security = findViewById(R.id.edt_name_security);
        Auto_Complete_state = findViewById(R.id.Auto_Complete_state);
        Auto_Complete_City = findViewById(R.id.Auto_Complete_City);
        Auto_Complete_society = findViewById(R.id.Auto_Complete_society);
        shift_date = findViewById(R.id.shift_date);
        shift_time = findViewById(R.id.shift_time);
        Auto_Complete_gate = findViewById(R.id.Auto_Complete_gate);

        shift_date.setOnClickListener(v -> shiftDate());
        shift_time.setOnClickListener(v -> shiftTime());

        start_btn = findViewById(R.id.start_btn);
        start_btn.setOnClickListener(this::onClick);

        societyNameAdapter = ArrayAdapter.createFromResource(ShiftActivityGuard.this, R.array.Society_chooser,
                R.layout.dropdown_item_society_chooser);
        societyNameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Auto_Complete_society.setAdapter(societyNameAdapter);

        gateIdAdapter = ArrayAdapter.createFromResource(ShiftActivityGuard.this, R.array.GateSelector,
                R.layout.dropdown_item_society_chooser);
        gateIdAdapter.setDropDownViewResource(android.R.layout.simple_selectable_list_item);
        Auto_Complete_gate.setAdapter(gateIdAdapter);

        indianStateAdapter = ArrayAdapter.createFromResource(ShiftActivityGuard.this, R.array.array_indian_states,
                R.layout.dropdown_item_society_chooser);
        indianStateAdapter.setDropDownViewResource(android.R.layout.simple_selectable_list_item);
        Auto_Complete_state.setAdapter(indianStateAdapter);

        indianCityAdapter = ArrayAdapter.createFromResource(ShiftActivityGuard.this, R.array.array_maharashtra_districts,
                R.layout.dropdown_item_society_chooser);
        indianCityAdapter.setDropDownViewResource(android.R.layout.simple_selectable_list_item);
        Auto_Complete_City.setAdapter(indianCityAdapter);

    }




    private void shiftTime() {
        Calendar currentTime = Calendar.getInstance();
        int hour = currentTime.get(Calendar.HOUR_OF_DAY);
        int minute = currentTime.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog;
        timePickerDialog = new TimePickerDialog
                (ShiftActivityGuard.this, (view, hourOfDay, minute1) -> {
                    currentTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    currentTime.set(Calendar.MINUTE, minute1);

                    String myFormat = "HH:MM:ss";
                    SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
                    shift_time.setText(dateFormat.format(currentTime.getTime()));
                }, hour, minute, true);
        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }

    private void shiftDate() {
        DatePickerDialog.OnDateSetListener date = (view, year, month, dayOfMonth) -> {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            shift_date.setText(updateDate());
        };
        shift_date.setOnClickListener(v -> new DatePickerDialog
                (ShiftActivityGuard.this, date, myCalendar.get(Calendar.YEAR), myCalendar.
                        get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show());
    }

    private String updateDate() {
        String myFormat = "yyyy-MM-dd";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
        return dateFormat.format(myCalendar.getTime());
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, (arg0, arg1) -> ShiftActivityGuard.super.onBackPressed()).create().show();
    }

    private void onClick(View v) {


        String guardName = Objects.requireNonNull(edt_name.getText()).toString();
        String securityName = Objects.requireNonNull(edt_name_security.getText()).toString();
        String stateName = Objects.requireNonNull(Auto_Complete_state.getText()).toString();
        String cityName = Objects.requireNonNull(Auto_Complete_City.getText()).toString();
        String societyName = Objects.requireNonNull(Auto_Complete_society.getText()).toString();
        String shiftDate = Objects.requireNonNull(shift_date.getText()).toString();
        String shiftTime = Objects.requireNonNull(shift_time.getText()).toString();
        String gateID = Objects.requireNonNull(Auto_Complete_gate.getText()).toString();


        if (!guardName.isEmpty()) {
            input_edt_lay_1.setError(null);
            input_edt_lay_1.setErrorEnabled(false);
            if (!securityName.isEmpty()) {
                input_edt_lay_2.setError(null);
                input_edt_lay_2.setErrorEnabled(false);
                if (!stateName.isEmpty()) {
                    input_lay_auto_3.setError(null);
                    input_lay_auto_3.setErrorEnabled(false);
                    if (!cityName.isEmpty()) {
                        input_lay_auto_4.setError(null);
                        input_lay_auto_4.setErrorEnabled(false);
                        if (!societyName.isEmpty()) {
                            input_lay_auto_5.setError(null);
                            input_lay_auto_5.setErrorEnabled(false);
                            if (!shiftDate.isEmpty()) {
                                input_edt_lay_6.setError(null);
                                input_edt_lay_6.setErrorEnabled(false);
                                if (!shiftTime.isEmpty()) {
                                    input_edt_lay_7.setError(null);
                                    input_edt_lay_7.setErrorEnabled(false);
                                    if (!gateID.isEmpty()) {
                                        input_lay_auto_8.setError(null);
                                        input_lay_auto_8.setErrorEnabled(false);

                                        final String guardName_f = Objects.requireNonNull(edt_name.getText()).toString();
                                        final String securityName_f = Objects.requireNonNull(edt_name_security.getText()).toString();
                                        final String stateName_f = Objects.requireNonNull(Auto_Complete_state.getText()).toString();
                                        final String cityName_f = Objects.requireNonNull(Auto_Complete_City.getText()).toString();
                                        final String societyName_f = Objects.requireNonNull(Auto_Complete_society.getText()).toString();
                                        final String shiftDate_f = Objects.requireNonNull(shift_date.getText()).toString();
                                        final String shiftTime_f = Objects.requireNonNull(shift_time.getText()).toString();
                                        final String gateID_f = Objects.requireNonNull(Auto_Complete_gate.getText()).toString();

                                        DatabaseReference guardUser = FirebaseDatabase.getInstance().getReference("GuardData");
                                        GuardUserData guardUserData = new GuardUserData
                                                (guardName_f,securityName_f,stateName_f, cityName_f,societyName_f,
                                                        shiftDate_f, shiftTime_f, gateID_f);
                                        String guardId = guardUser.push().getKey();
                                        assert guardId != null;
                                        guardUser.child("shiftData").setValue(guardUserData);
                                        Toast.makeText(ShiftActivityGuard.this, "Staring your shift", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(ShiftActivityGuard.this, GateVisitorActivity.class));
                                        finish();

                                    } else {
                                        input_lay_auto_8.setError("Please Select Gate ID");
                                    }

                                } else {
                                    input_edt_lay_7.setError("Please Select Time");
                                }

                            } else {
                                input_edt_lay_6.setError("Please Select Date");
                            }
                        } else {
                            input_lay_auto_5.setError("Please Select Your Society");
                        }

                    } else {
                        input_lay_auto_4.setError("Please Select Your City");
                    }
                } else {
                    input_lay_auto_3.setError("Please Select Your State");
                }

            } else {
                input_edt_lay_2.setError("Please Select Your Security Name");
            }

        } else {
            input_edt_lay_1.setError("Please Enter Your Full Name");
        }
    }
}