package com.ext.teamformation.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ext.teamformation.R;
import com.ext.teamformation.Utils.PrefManager;
import com.google.android.material.navigation.NavigationView;

public class NavigationActivity extends AppCompatActivity {

    public FrameLayout frame_main;
    public DrawerLayout drawer_layout;
    public NavigationView nav_view;
    public LinearLayout lnLogout;
    public LinearLayout lnaddpeople;
    public LinearLayout lnAddProject;
    public LinearLayout lnGroup;
    public LinearLayout lnDashboard;
    public LinearLayout lnRequest;
    public TextView tvanme;
    public TextView tvEmail;
    PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        prefManager = new PrefManager( this );
        frame_main = findViewById(R.id.frame_main);
        drawer_layout = findViewById(R.id.drawer_layout);
        nav_view = findViewById(R.id.nav_view);
        lnLogout = findViewById(R.id.linear_logout);
        lnaddpeople = findViewById(R.id.ln_people);
        lnAddProject = findViewById(R.id.ln_project);
        lnDashboard = findViewById(R.id.ln_profile);
        lnRequest = findViewById(R.id.ln_request);
        lnGroup = findViewById(R.id.ln_group);
        tvanme = findViewById(R.id.tv_name);
        tvEmail = findViewById(R.id.tv_email);

        int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.75);
        DrawerLayout.LayoutParams params = (DrawerLayout.LayoutParams) nav_view.getLayoutParams();
        params.width = width;
        nav_view.setLayoutParams(params);

        tvanme.setText( prefManager.getPrefValue( PrefManager.USER_NAME ) );
        tvEmail.setText( prefManager.getPrefValue( PrefManager.EMAIL ) );


        lnaddpeople.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent( NavigationActivity.this,AddPeopleActivity.class ) );
            }
        } );

        lnAddProject.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent( NavigationActivity.this,AddProjectActivity.class ) );
            }
        } );

        lnGroup.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent( NavigationActivity.this,Group.class ) );
            }
        } );

        lnDashboard.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent( NavigationActivity.this,DashboardActivity.class ) );
            }
        } );


        lnRequest.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent( NavigationActivity.this,RequestActivity.class ) );
            }
        } );


        lnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prefManager.deletePrefData();

                Intent i=new Intent(NavigationActivity.this,StartUpActivity.class);
                startActivity(i);
                finishAffinity();
            }
        });
    }
}
