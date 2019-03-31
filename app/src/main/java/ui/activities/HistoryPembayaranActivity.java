package ui.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anonymous.catering.R;
import com.example.anonymous.catering.adapters.PembayaranAdapter;
import com.example.anonymous.catering.config.ServerConfig;
import com.example.anonymous.catering.models.Pembayaran;
import com.example.anonymous.catering.models.Pemesanan;
import com.example.anonymous.catering.response.ResponsePembayaran;
import com.example.anonymous.catering.rests.ApiClient;
import com.example.anonymous.catering.rests.ApiInterface;
import com.example.anonymous.catering.utils.SessionManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryPembayaranActivity extends AppCompatActivity {
    SessionManager sessionManager;
    @BindView(R.id.rvHistory)
    RecyclerView rvVerifikasi;
    @BindView(R.id.tvJumlahSeluruh)
    TextView tvJumlahSeluruh;
    @BindView(R.id.tvTotal)
    TextView tvTotal;
    @BindView(R.id.btnPembayaran)
    ImageView btnPembayaran;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ProgressDialog progressDialog;
    ApiInterface apiInterface;
    public List<Pembayaran> pembayaran = new ArrayList<>();
    int bayar = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_pembayaran);
        ButterKnife.bind(HistoryPembayaranActivity.this);
        progressDialog = new ProgressDialog(HistoryPembayaranActivity.this);
        layoutManager = new LinearLayoutManager(HistoryPembayaranActivity.this, LinearLayoutManager.VERTICAL, false);
        rvVerifikasi.setLayoutManager(layoutManager);
        sessionManager = new SessionManager(HistoryPembayaranActivity.this);
        final HashMap<String, String> map = sessionManager.getMemberProfile();
        apiInterface = ApiClient.getClient(ServerConfig.API_ENDPOINT).create(ApiInterface.class);
        getLayout();

        btnPembayaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id_pemesanan = getIntent().getStringExtra("id_pemesanan");
                getLayoutPembayaran(id_pemesanan);
            }
        });
        progressDialog.setMessage("Loading ...");
        progressDialog.show();
    }

    private void getLayoutPembayaran(String id_pemesanan) {
        Intent i = new Intent(HistoryPembayaranActivity.this,PembayaranActivity.class);
        i.putExtra("id_pemesanan",id_pemesanan);
        i.putExtra("total",getIntent().getStringExtra("total"));
        i.putExtra("bayar",getIntent().getStringExtra("total"));
        i.putExtra("bayar",""+this.bayar);
        startActivity(i);
    }
    private void getLayout() {
        String id_pemesanan = getIntent().getStringExtra("id_pemesanan");
        int total = Integer.parseInt(getIntent().getStringExtra("total"));
        tvJumlahSeluruh.setText(""+total);
        apiInterface.pembayaranFindByPemesanan(id_pemesanan).enqueue(new Callback<ResponsePembayaran>() {
            @Override
            public void onResponse(Call<ResponsePembayaran> call, Response<ResponsePembayaran> response) {
                progressDialog.hide();
                if (response.isSuccessful()){
                    if (response.body().getMaster().size()>0){
                        pembayaran = response.body().getMaster();
                        adapter = new PembayaranAdapter(HistoryPembayaranActivity.this,pembayaran,getIntent().getStringExtra("nama_pesanan"));
                        int bayar = total-((PembayaranAdapter) adapter).getTotal();
                        tvTotal.setText(""+bayar);
                        setBayar(bayar);
                        rvVerifikasi.setAdapter(adapter);
                    }
                }
                else{
                    Toast.makeText(HistoryPembayaranActivity.this, "Tidak ada data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponsePembayaran> call, Throwable t) {

            }
        });
    }

    private int setBayar(int bayar) {
        return this.bayar = bayar;
    }
}
