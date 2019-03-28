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
import com.example.anonymous.catering.models.Pembayaran;

import java.util.List;

import ui.activities.PembayaranActivity;

public class PembayaranAdapter extends RecyclerView.Adapter<PembayaranAdapter.ViewHolder> {
    private List<Pembayaran> Pembayarans;
    Context context;
    public int total;
    int bayar;
    public PembayaranAdapter(Context context, List<Pembayaran> Pembayarans,int total){
        this.context = context;
        this.Pembayarans = Pembayarans;
        this.total = total;
        //this.bayar = this.bayar+Pembayaran.getTotal();
        for (int i = 0 ; i<Pembayarans.size();i++){
            this.bayar = this.bayar+Pembayarans.get(i).getTotal();
        }
    }
    public int getTotal (){
        return this.total-this.bayar;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.list_pembayaran,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder   ;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Pembayaran Pembayaran = Pembayarans.get(position);
        holder.tvTanggal.setText(Pembayaran.getTanggalUpload());
        //holder.tvStatus.setText(Pembayaran.getStatus());
        holder.tvTotal.setText(Pembayaran.getTotal().toString());
        holder.tvNamaPaket.setText(Pembayaran.getPemesananId().toString());
        holder.tvJumlah.setText(Pembayaran.getJumlahTransfer().toString());
//        holder.cvCard.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(context, PembayaranActivity.class);
//                context.startActivity(i);
//            }
//        });
    }
    @Override
    public int getItemCount() {
        return Pembayarans.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvNamaPaket,tvJumlah,tvTotal,tvStatus,tvTanggal;
//        CardView cvCard;
        public ViewHolder(View itemView) {
            super(itemView);
            tvNamaPaket = itemView.findViewById(R.id.tvPaket);
            tvJumlah = itemView.findViewById(R.id.tvJumlah);
            tvTotal = itemView.findViewById(R.id.tvTotal);
            tvTanggal = itemView.findViewById(R.id.tvTanggal);
//            cvCard = itemView.findViewById(R.id.cvCard);
        }
    }
}