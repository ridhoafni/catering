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
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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

    public static final String SERVER_URL = "http://192.168.1.12/yii2/catering/api/v1/";

    SessionManager sessionManager;
    @BindView(R.id.rvHistory)
    RecyclerView rvVerifikasi;
    @BindView(R.id.tvTotal)
    TextView tvTotal;
    @BindView(R.id.btnPembayaran)
    Button btnPembayaran;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ProgressDialog progressDialog;
    ApiInterface apiInterface;
    List<Pembayaran> pembayaran = new ArrayList<>();
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
        apiInterface = ApiClient.getClient(SERVER_URL).create(ApiInterface.class);
        getLayout();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Histori Pembayaran");

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
        startActivity(i);
    }
    private void getLayout() {
        String id_pemesanan = getIntent().getStringExtra("id_pemesanan");
        String total = getIntent().getStringExtra("total");
        apiInterface.pembayaranFindByPemesanan(id_pemesanan).enqueue(new Callback<ResponsePembayaran>() {
            @Override
            public void onResponse(Call<ResponsePembayaran> call, Response<ResponsePembayaran> response) {
                progressDialog.hide();
                if (response.isSuccessful()){
                    if (response.body().getMaster().size()>0){
                        pembayaran = response.body().getMaster();
                        adapter = new PembayaranAdapter(HistoryPembayaranActivity.this,pembayaran,Integer.parseInt(total));
                        int bayar = ((PembayaranAdapter) adapter).getTotal();
                        tvTotal.setText(""+bayar);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Context context = null;
//                ((MainTabActivity)context).navigateFragment(4);
                Intent i = new Intent(getApplicationContext(), MainTabActivity.class);
                startActivity(i);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
