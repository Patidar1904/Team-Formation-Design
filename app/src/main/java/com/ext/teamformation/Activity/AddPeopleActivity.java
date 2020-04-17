package com.ext.teamformation.Activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;

import com.ext.teamformation.Modal.PeopleModal;
import com.ext.teamformation.R;
import com.ext.teamformation.Utils.DatabaseHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AddPeopleActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    ListView list;
    ListViewAdapter adapter;
    SearchView editsearch;
    String[] nameList, langList, expList, rating;

    DatabaseHandler db;
    public static ArrayList <String> listPeople = new ArrayList <>();

    ArrayList <PeopleModal> arraylist = new ArrayList <PeopleModal>();
    String from = "";
    @BindView(R.id.btn_continue)
    Button btnContinue;

    @BindView(R.id.lv)
    ListView lv;
    @BindView(R.id.iv_back)
    ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_add_people );
        ButterKnife.bind( this );
        // Generate sample data

        db = new DatabaseHandler( this );
        listPeople = new ArrayList <>(  );


        try {
             from= getIntent().getStringExtra( "from" );
            if (from.equalsIgnoreCase( "project" )) {
                btnContinue.setVisibility( View.VISIBLE );
            }
        } catch (Exception e) {
            from = "";
        }

        memoryAllocation();
        setListner();
    }

    void memoryAllocation(){

        // Locate the EditText in listview_main.xml
        editsearch = (SearchView) findViewById( R.id.search );
        editsearch.setOnQueryTextListener( this );


        if (db.getAllPeople().size() == 0) {
            db.addPeople( new PeopleModal( "Siddhi", "ANDROID,JAVA", "2 Year", "1.5" ) );
            db.addPeople( new PeopleModal( "Meet", "IONIC", "2 Year", "3" ) );
            db.addPeople( new PeopleModal( "Agnesh", "C#,C,C++", "2 Year", "4.5" ) );
            db.addPeople( new PeopleModal( "Satypal", "PHP,HTML,MYSQL", "2 Year", "5" ) );
            db.addPeople( new PeopleModal( "Anjali", "REACT,ANGULAR", "2 Year", "1" ) );
            db.addPeople( new PeopleModal( "Ravi", "JAVASCRIPT", "2 Year", "2.5" ) );
        }

        listPeople = new ArrayList <>();
        List <String> items = new ArrayList <>(  );
        if (from.equalsIgnoreCase( "dashboard" )){
            try{
                items = Arrays.asList(getIntent().getStringExtra( "project" ).split(","));

            }catch (Exception e){

            }
            for (int i = 0; i < db.getAllPeople().size(); i++) {
                for (int j = 0; j <items.size() ; j++) {
                    if (db.getAllPeople().get( i ).getPeopleName().equalsIgnoreCase( items.get( j ) )){

                        arraylist.add( db.getAllPeople().get( i ) );
                    }

                }

                Log.e( "size", arraylist.size() + "" );
            }
        }else {
            for (int i = 0; i < db.getAllPeople().size(); i++) {
                arraylist.add( db.getAllPeople().get( i ) );
            }
            Log.e( "size", arraylist.size() + "" );

        }
        Log.e( "size", db.getAllPeople().size() + "" );



        // Pass results to ListViewAdapter Class
        adapter = new ListViewAdapter( this, arraylist );

        // Binds the Adapter to the ListView
        lv.setAdapter( adapter );

    }

    void setListner(){

        btnContinue.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        } );

        ivBack.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        } );

        lv.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onItemClick(AdapterView <?> parent, View view, int position, long id) {
                // view.setBackgroundColor(getColor(R.color. colorPrimaryDark )) ;
                if (from.equalsIgnoreCase( "project" )) {
                    Boolean flag = false;
                    Boolean flag1 = false;


                    for (int i = 0; i < listPeople.size(); i++) {
                        if (listPeople.get( i ).equalsIgnoreCase( arraylist.get( position ).getPeopleName() )) {
                            flag = true;
                            listPeople.remove( i );
                            view.setBackground( getResources().getDrawable( R.drawable.listview_border ) );

                        } else {
                            flag1 = true;
                        }
                    }

                    if (flag1 && !flag) {
                        view.setBackgroundColor( Color.parseColor( "#ffc900" ) );
                        listPeople.add( arraylist.get( position ).getPeopleName() );

                    } else if (listPeople.size() == 0) {
                        view.setBackgroundColor( Color.parseColor( "#ffc900" ) );
                        listPeople.add( arraylist.get( position ).getPeopleName() );
                    }
                    for (int i = 0; i < listPeople.size(); i++) {

                        Log.e( "name", listPeople.get( i ) );
                    }

                }
            }

        } );
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        adapter.filter( text );
        return false;
    }
}