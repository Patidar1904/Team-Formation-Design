package com.ext.teamformation.Activity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.borjabravo.readmoretextview.ReadMoreTextView;
import com.ext.teamformation.Modal.ProjectModal;
import com.ext.teamformation.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterDashboard extends RecyclerView.Adapter <AdapterDashboard.MyViewHolder> {


    List <ProjectModal> arrayList = new ArrayList <>();
    Context context;



    public AdapterDashboard(Context context, List <ProjectModal> arrayList) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from( viewGroup.getContext() ).inflate( R.layout.raw_dashboard, viewGroup, false );
        return new MyViewHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        String member = "";
        try{
            List<String> items = Arrays.asList(arrayList.get( position ).getProjectMember().toString().split(","));
            member = items.size()+"";
        }catch (Exception e){
            member = "4";
        }

        myViewHolder.tvProjectname.setText( arrayList.get( position ).getProjectName() );
        myViewHolder.tvProjectdescription.setText( arrayList.get( position ).getProjectDescription() );
        myViewHolder.tvMember.setText( member+"\nMember" );


        myViewHolder.tvMember.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent( context,AddPeopleActivity.class );
                intent.putExtra( "from","dashboard" );
                intent.putExtra( "project",arrayList.get( position ).getProjectMember() );
                context.startActivity( intent );
            }
        } );

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_projectname)
        TextView tvProjectname;
        @BindView(R.id.tv_projectdescription)
        ReadMoreTextView tvProjectdescription;
        @BindView(R.id.tv_member)
        TextView tvMember;

        public MyViewHolder(@NonNull View itemView) {

            super( itemView );
            ButterKnife.bind( this, itemView );
        }
    }
}
