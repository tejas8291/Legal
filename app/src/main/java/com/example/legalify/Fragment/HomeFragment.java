package com.example.legalify.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.legalify.R;
public class HomeFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private static final String ARG_PARAM1="param1";
    private static final String ARG_PARAM2="param2";
    private String mParam1;
    private String mParam2;
    String[] courses = { "Filing Case", "Existing",
            "Send Notice", "RTI"};

    public HomeFragment () {

    }
    public static HomeFragment newInstance ( String param1, String param2 ) {
        HomeFragment fragment=new HomeFragment();
        Bundle args=new Bundle();
        args.putString( ARG_PARAM1, param1 );
        args.putString( ARG_PARAM2, param2 );
        fragment.setArguments( args );
        return fragment;
    }

    @Override
    public void onCreate ( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        if (getArguments() != null) {
            mParam1=getArguments().getString( ARG_PARAM1 );
            mParam2=getArguments().getString( ARG_PARAM2 );
        }
    }

    @Override
    public View onCreateView ( LayoutInflater inflater, ViewGroup container,
                               Bundle savedInstanceState ) {

        View view =  inflater.inflate( R.layout.fragment_home, container, false );

        Spinner spin = view.findViewById(R.id.caseSpinner);
        spin.setOnItemSelectedListener(this);

        ArrayAdapter ad= new ArrayAdapter( getActivity(), android.R.layout.simple_spinner_item,courses);
        ad.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(ad);



      return view;
    }

    @Override
    public void onItemSelected ( AdapterView < ? > parent, View view, int position, long id ) {


    }

    @Override
    public void onNothingSelected ( AdapterView < ? > parent ) {

    }
}