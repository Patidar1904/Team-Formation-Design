package com.ext.teamformation.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.ext.teamformation.Modal.ProjectModal;
import com.ext.teamformation.R;
import com.ext.teamformation.Utils.DatabaseHandler;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AddProjectActivity extends AppCompatActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.edt_project)
    EditText edtProject;
    @BindView(R.id.edt_project_des)
    EditText edtProjectDes;
    @BindView(R.id.edt_total_member)
    EditText edtTotalMember;
    @BindView(R.id.btn_add)
    Button btnAdd;


    DatabaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_add_project );
        ButterKnife.bind( this );


        db = new DatabaseHandler( this );
        this.setListner();
    }

    private void setListner() {

        ivBack.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        } );

        edtTotalMember.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent( AddProjectActivity.this,AddPeopleActivity.class);
                intent.putExtra( "from","project" );
                startActivity( intent );
            }
        } );


        btnAdd.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtProject.getText().toString().isEmpty()){
                    Toast.makeText( AddProjectActivity.this, "Project name cannot be empty", Toast.LENGTH_SHORT ).show();
                }else if (edtProjectDes.getText().toString().isEmpty()){
                    Toast.makeText( AddProjectActivity.this, "Project description cannot be empty", Toast.LENGTH_SHORT ).show();
                }else if (edtTotalMember.getText().toString().equalsIgnoreCase( "0" ) || edtTotalMember.getText().toString().equalsIgnoreCase( "00" )){
                    Toast.makeText( AddProjectActivity.this, "Please add team member", Toast.LENGTH_SHORT ).show();
                }
                else {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < AddPeopleActivity.listPeople.size()  ; i++) {
                        if (i == AddPeopleActivity.listPeople.size() -1 )sb.append(  AddPeopleActivity.listPeople.get( i ));
                        else sb.append(  AddPeopleActivity.listPeople.get( i ) +",");

                    }

                    String member = String.valueOf( sb );
                    Log.e("member",member);
                    ProjectModal projectModal = new ProjectModal( edtProject.getText().toString(),edtProjectDes.getText().toString(),member );
                    db.addProject( projectModal );
                    Toast.makeText( AddProjectActivity.this, "Project Add Succesfully", Toast.LENGTH_SHORT ).show();

                    AddPeopleActivity.listPeople = new ArrayList <>(  );
                    startActivity( new Intent( AddProjectActivity.this,DashboardActivity.class ) );
                    finish();

                }
            }
        } );
    }

    @Override
    protected void onResume() {
        super.onResume();
        edtTotalMember.setText( AddPeopleActivity.listPeople.size()+""  );
    }

    //    public void alert(View v) {
//        Toast.makeText(this, "You Adds Project Succesfully", Toast.LENGTH_SHORT).show();
//    }

}
