package com.example.anonymous.catering.adapters;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anonymous.catering.R;
import com.example.anonymous.catering.models.Pemesanan;

import java.util.List;

import ui.activities.HistoryPembayaranActivity;

public class PemesananAdapter extends RecyclerView.Adapter<PemesananAdapter.ViewHolder> {
    private List<Pemesanan> Pemesanans;
    Context context;
    public PemesananAdapter(Context context,List<Pemesanan> Pemesanans){
        this.context = context;
        this.Pemesanans = Pemesanans;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.list_verifikasi,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder   ;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Pemesanan Pemesanan = Pemesanans.get(position);
        holder.tvTanggal.setText(Pemesanan.getTglPesanan());
        holder.tvStatus.setText(Pemesanan.getStatus());
        holder.tvTotal.setText(Pemesanan.getTotal().toString());
        holder.tvNamaPaket.setText(Pemesanan.getNamaPaket());
        holder.tvJumlah.setText(Pemesanan.getJumlahPesanan().toString());
        holder.cvCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, ""+Pemesanan.getIdPemesanan(), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(context, HistoryPembayaranActivity.class);
                i.putExtra("id_pemesanan",""+Pemesanan.getIdPemesanan());
                i.putExtra("nama_pesanan",""+Pemesanan.getNamaPaket());
                i.putExtra("total",""+Pemesanan.getTotal());
                context.startActivity(i);
            }
        });
    }
    @Override
    public int getItemCount() {
        return Pemesanans.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvNamaPaket,tvJumlah,tvTotal,tvStatus,tvTanggal;
        CardView cvCard;
        public ViewHolder(View itemView) {
            super(itemView);
            tvNamaPaket = itemView.findViewById(R.id.tvPaket);
            tvJumlah = itemView.findViewById(R.id.tvJumlah);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            tvTotal = itemView.findViewById(R.id.tvTotal);
            tvTanggal = itemView.findViewById(R.id.tvTanggal);
            cvCard = itemView.findViewById(R.id.cvCard);
        }
    }
}