package team7.hack.com.hackjmiapplication;

import android.animation.Animator;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.roger.psdloadingview.library.PsdLoadingView;
import com.roger.psdloadingview.library.animate.EatAnimate;

import java.util.Calendar;
import java.util.HashMap;

import team7.hack.com.hackjmiapplication.AESCrypt;
import team7.hack.com.hackjmiapplication.R;

public class LoginActivity extends AppCompatActivity {

    FirebaseDatabase database;;
    DatabaseReference myRef;
    private HashMap<String, User> loginCredentials;

    EditText fullNameET, phnoET, bgET, passwordET;

    EditText loginPh;

    private static TextView dob;
    private static Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginCredentials = new HashMap<>(10);
        User u1 = new User("\"-L8MvfiJMreSdSiOP0m-\"", "8860408118", "12345678", "Nihit Bansal", "24/3/1999", "A+" );
        User u2 = new User("-L8Mv9f0UFL2ZtNVEWdJ", "8130230868", "12345678", "Amit Mohan", "24/3/1998", "O+" );
        User u3 = new User("-L8MvUfUn90iz6c1s7Oq", "9711579373", "12345678", "Shubham Dwivedi", "24/3/1997", "B+" );

        loginCredentials.put("8860408118", u1);
        loginCredentials.put("8130230868",u2);
        loginCredentials.put("9711579373",u3);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("users");

        fullNameET = findViewById(R.id.userName);
        phnoET = findViewById(R.id.phone);
        bgET = findViewById(R.id.bloodGroup);
        passwordET = findViewById(R.id.passwordStore);

        loginPh = findViewById(R.id.phoneNumber);



        final PsdLoadingView psd = findViewById(R.id.password);
        psd.init(new EatAnimate());

        findViewById(R.id.buttonLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!psd.getText().toString().equals(""))
                    psd.startLoading();
            }
        });

        calendar = Calendar.getInstance();
        dob = findViewById(R.id.dob);
        setDate(calendar);

        final View login = findViewById(R.id.login);
        final View signUp = findViewById(R.id.signUp);

        final TextView toggle = findViewById(R.id.toggle);
        toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(login.getVisibility() == View.VISIBLE) {
                    crossFade(login, signUp);

                    toggle.setText("Login if you're already a Donor");
                } else {
                    crossFade(signUp, login);

                    toggle.setText("Sign Up if you're not a Donor");
                }
            }
        });

        findViewById(R.id.buttonLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String phoneNo = loginPh.getText().toString().trim();
                final String pass = psd.getTextDuringLoading().toString().trim();

                psd.startLoading();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        String password="";
                        if(!TextUtils.isEmpty(pass)) {
                            try {
                                password = AESCrypt.encrypt(pass);
                            } catch (Exception e) {
                            }
                        }

                        if(loginCredentials.containsKey(phoneNo))
                        {

                            if(pass.equals(loginCredentials.get(phoneNo).getUserPassword()))
                            {
                                Toast.makeText(LoginActivity.this, "You have been successfully logged in!", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(LoginActivity.this, "Incorrect Password", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(LoginActivity.this, "abc", Toast.LENGTH_SHORT).show();
                        }

                    }
                }, 3000);
                    }
                });

                psd.stopLoading();

        findViewById(R.id.buttonSignUp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = myRef.push().getKey();
                String fullName = fullNameET.getText().toString().trim();
                String phoneNo = phnoET.getText().toString().trim();
                String DOB = dob.getText().toString();
                String bg = bgET.getText().toString().trim();
                String p = passwordET.getText().toString().trim();
                String password="";
                if(!TextUtils.isEmpty(p)) {
                    try {
                        password = AESCrypt.encrypt(p);
                    } catch (Exception e) {
                    }
                }
                if(!TextUtils.isEmpty(fullName) && !TextUtils.isEmpty(phoneNo) && !TextUtils.isEmpty(bg) && !TextUtils.isEmpty(p)) {
                    User user = new User(id, phoneNo, password, fullName, DOB, bg);
                    myRef.child(phoneNo).setValue(user);
                }
                else{
                    Toast.makeText(LoginActivity.this, "Fields can't be empty!", Toast.LENGTH_SHORT).show();

                }


            }
        });

        findViewById(R.id.datePick).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerFragment().show(getFragmentManager(), "Date Picker");
            }
        });
    }

    private void crossFade(final View fadingView, final View appearingView) {
        fadingView.animate().alpha(0f).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                fadingView.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

        appearingView.animate().alpha(1f).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                appearingView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }

    private static void setDate(Calendar calendar) {
        dob.setText(
                calendar.get(Calendar.DAY_OF_MONTH) +"/"
                        +(calendar.get(Calendar.MONTH)+1)+"/"
                        +calendar.get(Calendar.YEAR));
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, day);

            setDate(calendar);
        }
    }
}