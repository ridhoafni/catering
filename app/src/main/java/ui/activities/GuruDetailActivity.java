package ui.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anonymous.catering.R;
import com.example.anonymous.catering.config.ServerConfig;
import com.example.anonymous.catering.models.Guru;
import com.example.anonymous.catering.response.ResponGuruDetail;
import com.example.anonymous.catering.rests.ApiClient;
import com.example.anonymous.catering.rests.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GuruDetailActivity extends AppCompatActivity {

    private static final String KEY_ID_GURU     = "key_id_guru";

    public static final String URL = ServerConfig.API_ENDPOINT;
    TextView tv_nama, tv_pen, tv_bio;
    ImageView iv_foto, iv_foto2;
    Toolbar toolbarDetail;
    int id;
    CollapsingToolbarLayout collapsingToolbar;
    ApiInterface apiService;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guru_detail);
        Intent i = getIntent();
        id = i.getIntExtra(KEY_ID_GURU, 0);

        System.out.println("ID ANDA: "+id);

        apiService  = ApiClient.getClient(URL).create(ApiInterface.class);
        iv_foto     = findViewById(R.id.img_item_photo_detail);
        tv_pen      = findViewById(R.id.tv_item_pendidikan_detail);
        tv_bio      = findViewById(R.id.tv_item_bio_detail);

        collapsingToolbar = findViewById(R.id.toolbar_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setTitleTextColor(Color.BLUE);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        apiService.guruFindById(id).enqueue(new Callback<ResponGuruDetail>() {
            @Override
            public void onResponse(Call<ResponGuruDetail> call, Response<ResponGuruDetail> response) {
                System.out.println(response.toString());

                if (response.isSuccessful()){
                        System.out.println(response.body().toString());
                        System.out.println(id);
                        ArrayList<Guru> gurus = new ArrayList<>();
                        gurus.add(response.body().getMaster());
                        Guru guru = gurus.get(0);
                        collapsingToolbar.setTitle(guru.getNama());
//                      tv_pendidikan.setText(guru.getRiwayat_pendidikan());
                        tv_pen.setText(guru.getRiwayatPendidikan());
                        tv_bio.setText(guru.getBiodata());
                }
            }

            @Override
            public void onFailure(Call<ResponGuruDetail> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }
}
