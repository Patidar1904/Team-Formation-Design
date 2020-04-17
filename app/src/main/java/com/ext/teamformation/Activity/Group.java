package com.ext.teamformation.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;

import com.ext.teamformation.R;
import com.ext.teamformation.Utils.DatabaseHandler;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class Group extends AppCompatActivity implements SearchView.OnQueryTextListener {
    ListView list;
    LayoutInflater inflater;
    ListViewAdapter adapter;
    String[] nameList, langList, expList, rating;
    ArrayList <AnimalNames> arraylist = new ArrayList <AnimalNames>();
    DatabaseHandler db;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.lv)
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_group );
        ButterKnife.bind( this );

        db = new DatabaseHandler( this );
        nameList = new String[]{"Agnesh", "Satypal", "Nishtha", "Amiras"};
        langList = new String[]{"Developer", "Web Developer", "Designer", "Database Administrator"};
        expList = new String[]{"3 Year", "3 Year 5 Months", "3 Year", "2 Year 6 Months"};
//        rating = new String[]{"5 Star", "4.5 Star", "5 Star", "3.5 Star"};
        rating = new String[]{"5", "3", "2.5", "4.5"};
        // Locate the ListView in listview_main.xml
        list = (ListView) findViewById( R.id.lv );

        for (int i = 0; i < nameList.length; i++) {
            AnimalNames animalNames = new AnimalNames( nameList[i], langList[i], expList[i], rating[i] );
            // Binds all strings into an array
            arraylist.add( animalNames );
        }

        // Pass results to ListViewAdapter Class
        adapter = new ListViewAdapter( this, db.getAllPeople() );

        // Binds the Adapter to the ListView
        list.setAdapter( adapter );

        ivBack.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
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
