package com.ext.teamformation.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.ext.teamformation.Modal.ProjectModal;
import com.ext.teamformation.R;
import com.ext.teamformation.Utils.DatabaseHandler;

import java.util.ArrayList;
import java.util.List;

import androidx.core.view.GravityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;


public class DashboardActivity extends NavigationActivity {

    @BindView(R.id.imgMenu)
    ImageView imgMenu;

    DatabaseHandler db;
    List <ProjectModal> list = new ArrayList <>();
    @BindView(R.id.rv)
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        getLayoutInflater().inflate( R.layout.activity_dashboard, frame_main );
        ButterKnife.bind( this );

        this.memoryAllocation();
        this.setListeners();
    }

    private void memoryAllocation() {

        db = new DatabaseHandler( this );

        list = new ArrayList <>();
        list.addAll( db.getAllProject() );

        if (db.getAllProject().size() == 0) Toast.makeText( this, "No data available", Toast.LENGTH_SHORT ).show();

        AdapterDashboard adapterDashboard = new AdapterDashboard( getApplicationContext(), list );
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( this, LinearLayoutManager.VERTICAL, false );
        rv.setLayoutManager( linearLayoutManager );
        rv.setAdapter( adapterDashboard );
    }

    private void setListeners() {
        imgMenu.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer_layout.openDrawer( GravityCompat.START );
            }
        } );
    }

    @Override
    public void onBackPressed() {
        this.finishAffinity();
    }


}
