package com.example.legalify.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.legalify.R;

public class LoginActivity extends AppCompatActivity {
    private TextView create_acc_tv;
    private AppCompatButton signIn_btn;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );
        create_acc_tv = (TextView)findViewById( R.id.create_acc_tv );

        signIn_btn = (AppCompatButton)findViewById( R.id.signIn_btn );

        create_acc_tv.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick ( View v ) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity( intent );
            }
        } );
        signIn_btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick ( View v ) {
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity( intent );
            }
        } );
    }
}