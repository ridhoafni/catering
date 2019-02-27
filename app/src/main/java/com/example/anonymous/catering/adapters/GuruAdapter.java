package com.example.anonymous.catering.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anonymous.catering.R;
import com.example.anonymous.catering.models.Guru;
import com.example.anonymous.catering.utils.CustomOnItemClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ui.activities.GuruDetailActivity;

public class GuruAdapter extends RecyclerView.Adapter<GuruAdapter.GuruViewHolder>{

    private static final String KEY_ID_GURU = "key_id_guru";
    private Context context;
    private List<Guru> getAllDataGuru;

    public GuruAdapter(Context context, List<Guru> getAllDataGuru){
        this.context        = context;
        this.getAllDataGuru = getAllDataGuru;
    }

    @NonNull
    @Override
    public GuruViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.guru_item, parent, false);
        return new GuruViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull GuruViewHolder guruViewHolder, int i) {
        Guru guru = getAllDataGuru.get(i);
//        Glide.with(context)
//                .load(ServerConfig.GURU_PATH+guru.getPhoto_profile())
//                .apply(new RequestOptions().override(100, 100))
//                .into(guruViewHolder.imgViewPhoto);
        guruViewHolder.textViewNama.setText(guru.getNama());
        guruViewHolder.textViewPendidikan.setText(guru.getRiwayatPendidikan());
        guruViewHolder.textViewBiodata.setText(guru.getBiodata());
        guruViewHolder.cardView.setOnClickListener(new CustomOnItemClickListener(i, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                int id          = getAllDataGuru.get(position).getIdGuru();
                String nama     = getAllDataGuru.get(position).getNama();
                String pen      = getAllDataGuru.get(position).getRiwayatPendidikan();
                String bio      = getAllDataGuru.get(position).getBiodata();
//              String photo    = getAllDataGuru.get(position).getPhoto_profile();
                Intent i = new Intent(view.getContext(), GuruDetailActivity.class);
                i.putExtra(GuruAdapter.KEY_ID_GURU, id);
                context.startActivity(i);
                Toast.makeText(context, "You are click " +id, Toast.LENGTH_SHORT).show();
            }
        }));
    }

    @Override
    public int getItemCount() {
        return getAllDataGuru.size();
    }

    public class GuruViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_item_photo) ImageView imgViewPhoto;
        @BindView(R.id.tv_item_name) TextView textViewNama;
        @BindView(R.id.tv_item_pendidikan) TextView textViewPendidikan;
        @BindView(R.id.tv_item_bio) TextView textViewBiodata;
        @BindView(R.id.card_view) CardView cardView;
        public GuruViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
