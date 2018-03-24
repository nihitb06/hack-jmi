package team7.hack.com.hackjmiapplication;

import android.animation.Animator;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.roger.psdloadingview.library.PsdLoadingView;
import com.roger.psdloadingview.library.animate.EatAnimate;

import java.util.Calendar;

public class LoginActivity extends AppCompatActivity {

    private static TextView dob;
    private static Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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

        findViewById(R.id.buttonSignUp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //To Do
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
