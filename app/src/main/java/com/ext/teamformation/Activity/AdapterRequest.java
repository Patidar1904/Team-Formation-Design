package com.ext.teamformation.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.borjabravo.readmoretextview.ReadMoreTextView;
import com.ext.teamformation.Modal.RequestModal;
import com.ext.teamformation.R;
import com.ext.teamformation.Utils.DatabaseHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterRequest extends RecyclerView.Adapter <AdapterRequest.MyViewHolder> {


    List <RequestModal> arrayList = new ArrayList <>();
    Context context;
    DatabaseHandler db;



    public AdapterRequest(Context context, List <RequestModal> arrayList) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from( viewGroup.getContext() ).inflate( R.layout.pattern, viewGroup, false );
        return new MyViewHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {

        db = new DatabaseHandler( context );
        myViewHolder.text.setText( arrayList.get( position ).getProject() );
        myViewHolder.text1.setText( arrayList.get( position ).getLaguage() );


        myViewHolder.bt.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RequestModal requestModal = new RequestModal( arrayList.get( position ).getId(),arrayList.get( position ).getProject(),arrayList.get( position ).getLaguage(),"Accept" );

                db.updateRequest( requestModal );


                Intent intent = new Intent( context, DashboardActivity.class );
                context.startActivity( intent );

            }
        } );

        myViewHolder.bt1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RequestModal requestModal = new RequestModal(  arrayList.get( position ).getId(),arrayList.get( position ).getProject(),arrayList.get( position ).getLaguage(),"Reject" );

                db.updateRequest( requestModal );
                Intent intent = new Intent( context, DashboardActivity.class );
                context.startActivity( intent );

            }
        } );

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text)
        TextView text;
        @BindView(R.id.text1)
        TextView text1;
        @BindView(R.id.bt)
        Button bt;
        @BindView(R.id.bt1)
        Button bt1;
        public MyViewHolder(@NonNull View itemView) {

            super( itemView );
            ButterKnife.bind( this, itemView );
        }
    }
}
