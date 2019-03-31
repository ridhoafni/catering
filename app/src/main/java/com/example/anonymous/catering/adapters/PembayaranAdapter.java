package com.example.anonymous.catering.adapters;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anonymous.catering.R;
import com.example.anonymous.catering.config.ServerConfig;
import com.example.anonymous.catering.models.Pembayaran;
import com.squareup.picasso.Picasso;

import java.util.List;

import ui.activities.HistoryPembayaranActivity;
import ui.activities.PembayaranActivity;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class PembayaranAdapter extends RecyclerView.Adapter<PembayaranAdapter.ViewHolder> {
    private List<Pembayaran> Pembayarans;
    Context context;
    public int total;
    public int bayar;
    public String namapesanan;
    public PembayaranAdapter(Context context, List<Pembayaran> Pembayarans,String namapesanan){
        this.context = context;
        this.namapesanan = namapesanan;
        this.Pembayarans = Pembayarans;
        //this.bayar = this.bayar+Pembayaran.getTotal();

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
        holder.tvNamaPaket.setText(""+namapesanan);
        holder.tvJumlah.setText(Pembayaran.getJumlahTransfer().toString());
        holder.cvCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
                Display display = wm.getDefaultDisplay();
                Point size = new Point();
                display.getSize(size);
                int width = size.x;
                int height = size.y;
                LayoutInflater inflater = (LayoutInflater)
                        context.getSystemService(LAYOUT_INFLATER_SERVICE);
                final View popupView = inflater.inflate(R.layout.detail_pembayaran, null);
                final PopupWindow popupWindow = new PopupWindow(popupView);
                popupWindow.setWidth(width);
                popupWindow.setHeight(height-200);
                popupView.setFocusable(false);
                popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
                ImageView close = (ImageView)popupView.findViewById(R.id.btnClose);
                TextView nama_paket = (TextView)popupView.findViewById(R.id.tvNamaPaket);
                TextView tgl_pesanan = (TextView)popupView.findViewById(R.id.tvTglPesan);
                TextView sisa = (TextView)popupView.findViewById(R.id.tvSisa);
                TextView transfer = (TextView)popupView.findViewById(R.id.tvTransfer);
                Button tutup = (Button)popupView.findViewById(R.id.btnTutup);
                ImageView imgBukti = (ImageView) popupView.findViewById(R.id.ivBuktiTransfer);

                nama_paket.setText(namapesanan);
                tgl_pesanan.setText(""+Pembayaran.getTanggalUpload());
                sisa.setText(""+Pembayaran.getTotal());
                transfer.setText(""+Pembayaran.getJumlahTransfer());

                ServerConfig serverConfig = new ServerConfig();
                Picasso.get()
                        .load(serverConfig.BUKTI_PEMBAYARAN+Pembayaran.getBuktiTransfer())
                        .resize(6000,2000)
                        .onlyScaleDown()
                        .centerInside()
                        .into((ImageView) popupView.findViewById(R.id.ivBuktiTransfer));
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
                tutup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
            }
        });
    }
    @Override
    public int getItemCount() {
        return Pembayarans.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvNamaPaket,tvJumlah,tvTotal,tvStatus,tvTanggal;
        CardView cvCard;
        public ViewHolder(View itemView) {
            super(itemView);
            tvNamaPaket = itemView.findViewById(R.id.tvPaket);
            tvJumlah = itemView.findViewById(R.id.tvJumlah);
            tvTotal = itemView.findViewById(R.id.tvTotal);
            tvTanggal = itemView.findViewById(R.id.tvTanggal);
            cvCard = itemView.findViewById(R.id.cvCard);
        }
    }

    public int getTotal() {
        int bayar =0;
        for (int i = 0;i<Pembayarans.size();i++){
            Pembayaran pembayaran = Pembayarans.get(i);
            bayar = bayar+pembayaran.getJumlahTransfer();
        }
        this.bayar = bayar;
        return this.bayar;
    }
}