package com.ext.teamformation.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ext.teamformation.R;
import com.ext.teamformation.Utils.DatabaseHandler;
import com.ext.teamformation.Utils.PrefManager;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ext.teamformation.Activity.RegisterActivity.isValidEmail;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.lnLogin)
    LinearLayout lnLogin;
    @BindView(R.id.lnSignUp)
    LinearLayout lnSignUp;
    @BindView(R.id.edt_email)
    EditText edtEmail;
    @BindView(R.id.edt_password)
    EditText edtPassword;

    DatabaseHandler db;
    PrefManager prefManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );
        ButterKnife.bind( this );

        db = new DatabaseHandler( this );
        prefManager = new PrefManager( this );
        this.setListeners();
    }

    private void setListeners() {
        lnLogin.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e( "D", edtEmail.getText().toString() );
                Log.e( "D", edtPassword.getText().toString() );
                Log.e( "D", db.getAllUser().size() + "" );

                Boolean email = true;
                Boolean password = true;
                if (edtEmail.getText().toString().isEmpty()) {
                    Toast.makeText( LoginActivity.this, "Email cannot be Empty", Toast.LENGTH_SHORT ).show();
                } else if (!isValidEmail( edtEmail.getText().toString() )) {
                    Toast.makeText( LoginActivity.this, "Invalid Email", Toast.LENGTH_SHORT ).show();
                } else if (edtPassword.getText().toString().isEmpty()) {
                    Toast.makeText( LoginActivity.this, "Passsword cannot be Empty", Toast.LENGTH_SHORT ).show();
                } else {
                    if (db.getAllUser().size() != 0) {
                        for (int i = 0; i < db.getAllUser().size(); i++) {
                            Log.e( "DB", i + "-" );
                            Log.e( "DB", db.getAllUser().get( i ).getEmail() );
                            Log.e( "DB", db.getAllUser().get( i ).getFirtName() );
                            if (edtEmail.getText().toString().equalsIgnoreCase( db.getAllUser().get( i ).getEmail() )) {
                                email = true;
                                if (edtPassword.getText().toString().equalsIgnoreCase( db.getAllUser().get( i ).getFirtName() + "123" )) {

                                    password = true;

                                    prefManager.createLoginSession();
                                    prefManager.savePrefValue( PrefManager.REGISTERID, String.valueOf( db.getAllUser().get( i ).getUserId() ) );
                                    prefManager.savePrefValue( PrefManager.EMAIL, db.getAllUser().get( i ).getEmail() );
                                    prefManager.savePrefValue( PrefManager.USER_NAME, db.getAllUser().get( i ).getFirtName() );
                                    prefManager.savePrefValue( PrefManager.UserImage, "" );

                                    Intent intent = new Intent( LoginActivity.this, DashboardActivity.class );
                                    startActivity( intent );
                                    finish();
                                } else password = false;
                            } else email = false;
                        }

                        if (!email || !password )
                            Toast.makeText( LoginActivity.this, "Email or Password is Incorrect", Toast.LENGTH_SHORT ).show();
                     } else
                        Toast.makeText( LoginActivity.this, "Data not exist", Toast.LENGTH_SHORT ).show();

                }
            }


        } );

        lnSignUp.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( LoginActivity.this, RegisterActivity.class );
                startActivity( i );
            }
        } );
    }

    @Override
    public void onBackPressed() {
        this.finish();
    }
}
