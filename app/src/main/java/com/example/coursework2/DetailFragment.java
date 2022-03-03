package com.example.coursework2;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Calendar;

public class DetailFragment extends Fragment {

    private static final String TAG = DetailFragment.class.getSimpleName();
    private View tv;
    private View sv1;
    private View sv2;
    private Button btnM;
    private Button btnW;
    private Button btnC;
    private EditText name;
    private RadioGroup gender;
    private RadioButton m;
    private RadioButton f;
    private EditText age;
    private EditText IC;
    private Spinner dep;
    private EditText date;
    private Spinner time;


    public static DetailFragment newInstance() {
        return new DetailFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        tv=(TextView) view.findViewById(R.id.tvWel);
        sv1=(ScrollView) view.findViewById(R.id.svAppo);
        sv2=(ScrollView) view.findViewById(R.id.svAbout);

        btnM = (Button) view.findViewById(R.id.btnMap);
        btnW = (Button) view.findViewById(R.id.btnWeb);

        name = (EditText) view.findViewById(R.id.txtName);
        gender= (RadioGroup) view.findViewById(R.id.rgGender);
        m=(RadioButton) view.findViewById(R.id.rbM);
        f=(RadioButton) view.findViewById(R.id.rbF);
        age = (EditText) view.findViewById(R.id.txtAge);
        IC = (EditText) view.findViewById(R.id.txtIC);
        dep=(Spinner) view.findViewById(R.id.spDep);
        date = (EditText) view.findViewById(R.id.txtDate);
        time=(Spinner) view.findViewById(R.id.spTime);



        btnC=(Button) view.findViewById(R.id.btnConfirm);
        btnC.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v1) {
                String sName="";
                String sSex = "";
                String sAge="";
                String sIC="";
                String sDep = "";
                String sDate="";
                String sTime="";

                sName=name.getText().toString().trim();
                sAge=age.getText().toString().trim();
                sIC=IC.getText().toString().trim();
                sDep=dep.getSelectedItem().toString();
                sDate=date.getText().toString().trim();
                sTime=time.getSelectedItem().toString();

                for (int i = 0; i < gender.getChildCount(); i++) {
                    RadioButton rd = (RadioButton) gender.getChildAt(i);
                    if (rd.isChecked()) {
                        sSex=rd.getText().toString().trim();
                        break;
                    }
                }

                String message="Name: "+sName
                        +"\nGender: "+ sSex
                        +"\nAge: "+sAge
                        +"\nIC/Passport NO.: "+sIC
                        +"\nAppointment department: "+ sDep
                        +"\nAppointment date: "+sDate
                        +"\nAppointment time: "+sTime;

                Intent intent=new Intent();
                intent.setClass(getActivity(), ResultActivity.class);
                intent.putExtra("info", message);
                startActivity(intent);
            }
        });


        date.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    showDatePickDlg();
                    return true;
                }
                return false;
            }
        });
        date.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    showDatePickDlg();
                }
            }
        });

        btnM.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v1) {

                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.addCategory("android.intent.category.DEFAULT");
                intent.addCategory("android.intent.category.BROWSABLE");
                intent.setType("https");
                intent.setData(Uri.parse("https://www.google.com/maps/place/Nilai+Medical+Centre/@2.8118159,101.7709427,17z/data=!3m1!4b1!4m5!3m4!1s0x31cdc6c73a59f9d1:0xdfae3428f6e944f1!8m2!3d2.8118159!4d101.7731314"));
                startActivity(intent);
            }

        });

        btnW.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v2) {

                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.addCategory("android.intent.category.DEFAULT");
                intent.addCategory("android.intent.category.BROWSABLE");
                intent.setType("https");
                intent.setData(Uri.parse("https://www.nilaimc.com/"));
                startActivity(intent);
            }

        });

        return view;
    }



    protected void showDatePickDlg() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                DetailFragment.this.date.setText(year + "-" + monthOfYear + "-" + dayOfMonth);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();



    }


    public void onMasterItemClicked(int masterId) {

        switch (masterId) {
            case 1:
                tv.setVisibility(View.GONE);
                sv1.setVisibility(View.VISIBLE);
                sv2.setVisibility(View.GONE);

                break;

            case 2:
                tv.setVisibility(View.GONE);
                sv1.setVisibility(View.GONE);
                sv2.setVisibility(View.VISIBLE);
                break;


            default:
                tv.setVisibility(View.VISIBLE);
                sv1.setVisibility(View.GONE);
                sv2.setVisibility(View.GONE);

        }
    }
}
