package com.ext.teamformation.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.ext.teamformation.Modal.RequestModal;
import com.ext.teamformation.R;
import com.ext.teamformation.Utils.DatabaseHandler;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RequestActivity extends AppCompatActivity {

    DatabaseHandler db;
    public static ArrayList <String> listPeople = new ArrayList <>();

    List <RequestModal> arrayList = new ArrayList <>();
    String from = "";
    @BindView(R.id.iv_back)
    ImageView ivBack;

    String project = "";
    @BindView(R.id.lv)
    RecyclerView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_request );
        ButterKnife.bind( this );

        this.MemoryAllocation();
        this.setListner();
    }

    private void setListner() {
        ivBack.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        } );
    }

    private void MemoryAllocation() {
        db = new DatabaseHandler( this );

        if (db.getAllRequest().size() == 0) {
            db.addRequest( new RequestModal( "Team Formation", "Android,Java", "" ) );
            db.addRequest( new RequestModal( "Project 1", "Android,Java", "" ) );
            db.addRequest( new RequestModal( "Project 2", "C,C++", "" ) );
            db.addRequest( new RequestModal( "Project 3", "React", "" ) );
            db.addRequest( new RequestModal( "Project 4", "IONIC", "" ) );




        }

        for (int i = 0; i < db.getAllRequest().size(); i++) {
            Log.e("name",db.getAllRequest().get( i ).getAccept());
            if (db.getAllRequest().get( i ).getAccept().isEmpty()) {
                arrayList.add( db.getAllRequest().get( i ) );
            }
        }
        AdapterRequest adapterDashboard = new AdapterRequest( getApplicationContext(), arrayList );
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( this, LinearLayoutManager.VERTICAL, false );
        lv.setLayoutManager( linearLayoutManager );
        lv.setAdapter( adapterDashboard );
    }
}
