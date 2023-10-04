package com.example.legalify.Fragment;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.legalify.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class AddFragment extends Fragment {

    private RelativeLayout filingCase_rl,civil_rl;
    private LinearLayout case_ll;

    private static final String ARG_PARAM1="param1";
    private static final String ARG_PARAM2="param2";

    private String mParam1;
    private String mParam2;

    public AddFragment () {
    }

    public static AddFragment newInstance ( String param1, String param2 ) {
        AddFragment fragment=new AddFragment();
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

        View view  = inflater.inflate( R.layout.fragment_add, container, false );
        filingCase_rl = view.findViewById( R.id.filingCase_rl );
        case_ll = view.findViewById( R.id.case_ll );
        civil_rl = view.findViewById( R.id.civil_rl );

        filingCase_rl.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if ( case_ll.getVisibility() == View.VISIBLE){
                    case_ll.setVisibility(View.GONE);
                } else {
                    case_ll.setVisibility(View.VISIBLE);
                }
            }
        });
        civil_rl.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick ( View v ) {
                BottomSheetDialog bottomSheetDialog=new BottomSheetDialog( getActivity() );
                bottomSheetDialog.setContentView( R.layout.civil_case_bottom_sheet );
                bottomSheetDialog.show();

            }
        } );

        return view;
    }
}