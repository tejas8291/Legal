package com.example.legalify.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.legalify.R;

public class RegisterActivity extends AppCompatActivity {

    private TextView haveAccount_tv;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_register );
        haveAccount_tv = (TextView) findViewById( R.id.haveAccount_tv );

        haveAccount_tv.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick ( View v ) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
               startActivity( intent );
            }
        } );

    }
}