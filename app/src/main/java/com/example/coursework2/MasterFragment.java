package com.example.coursework2;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class MasterFragment extends Fragment {

    private Callbacks callbacks;

    public static MasterFragment newInstance() {
        return new MasterFragment();
    }

    interface Callbacks {
        void onMasterItemClicked(int masterItemId);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (!(context instanceof Callbacks)) {
            throw new RuntimeException("Context must implement callbacks");
        }
        callbacks = (Callbacks) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_master, container, false);

        TextView textView1 = (TextView) view.findViewById(R.id.appo);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callbacks.onMasterItemClicked(1);
            }
        });

        TextView textView2 = (TextView) view.findViewById(R.id.about);
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callbacks.onMasterItemClicked(2);
            }
        });


        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callbacks = null;
    }
}