package ui.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anonymous.catering.R;
import com.example.anonymous.catering.config.ServerConfig;
import com.example.anonymous.catering.models.Pembayaran;
import com.example.anonymous.catering.response.ResponseGambar;
import com.example.anonymous.catering.response.ResponsePembayaran;
import com.example.anonymous.catering.response.ResponsePemesananJoin;
import com.example.anonymous.catering.rests.ApiClient;
import com.example.anonymous.catering.rests.ApiInterface;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PembayaranActivity extends AppCompatActivity {
    @BindView(R.id.tvNama)
    TextView tvNama;
    @BindView(R.id.tvNamaPesanan)
    TextView tvNamaPesanan;
    @BindView(R.id.tvJumlahPesanan)
    TextView tvJumlahPesanan;
    @BindView(R.id.tvTglPesan)
    TextView tvTglPesan;
    @BindView(R.id.etJumlahTransfer)
    EditText etJumlahTransfer;
    @BindView(R.id.btnUpload)
    Button btnUpload;
    @BindView(R.id.imggambar)
    ImageView imgView;
    @BindView(R.id.btnBayar)

    Button btnBayar;
    String partimage;
    ProgressDialog pd;
    final int REQUEST_GALLERY = 9544;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Pembayaran");
        pd = new ProgressDialog(this);
        pd.setMessage("loading ...");

//        String nama,namapesanan,jumlahpesanan,tglPesan,jumlahtransfer,total,alamat;
//        nama = getIntent().getStringExtra("id_pemesanan");
//        namapesanan = getIntent().getStringExtra("nama_paket");
//        jumlahpesanan = getIntent().getStringExtra("jumlah_pesanan");
//        jumlahtransfer = getIntent().getStringExtra("jumlah_transfer");
//        alamat = getIntent().getStringExtra("alamat");
//        total = getIntent().getStringExtra("total");
//        tvNama.setText(""+nama);
//        tvNamaPesanan.setText(""+namapesanan);
//        tvJumlahPesanan.setText(""+jumlahpesanan);
//        etJumlahTransfer.setText(""+jumlahtransfer);
//        tvTglPesan.setText(""+total);
//        final HashMap<String, String> map = sessionManager.getMemberProfile();
        apiInterface = ApiClient.getClient(ServerConfig.SERVER_URL2).create(ApiInterface.class);

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"open gallery"),REQUEST_GALLERY);
            }
        });

//        apiInterface.pemesananJoin(getIntent().getStringExtra("id_pemesanan")).enqueue(new Callback<ResponsePemesananJoin>() {
//            @Override
//            public void onResponse(Call<ResponsePemesananJoin> call, Response<ResponsePemesananJoin> response) {
//                if (response.isSuccessful()){
//                    Log.d("kantau","");
//                }
//                else{
//                    Log.d("kantau2","");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponsePemesananJoin> call, Throwable t) {
//                Log.d("kantau2",""+t.toString());
//            }
//        });
        btnBayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                HashMap<String, RequestBody> map = new HashMap<>();
//                map.put("pemesanan_id", createPartFromString("24"));
//                map.put("jumlah_transfer", createPartFromString("24000"));
//                map.put("total", createPartFromString(getIntent().getStringExtra("29000")));
//                pd.show();
                File imageFile = new File(partimage);
                RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-file"),imageFile);
                MultipartBody.Part partGambar = MultipartBody.Part.createFormData("bukti_transfer",imageFile.getName(),requestBody);
                RequestBody pemesanan_id = RequestBody.create(
                        MediaType.parse("text/plain"),
                        ""+getIntent().getStringExtra("id_pemesanan"));
                RequestBody total = RequestBody.create(
                        MediaType.parse("text/plain"),
//                        ""+getIntent().getStringExtra("total"));
                        ""+"9");
                RequestBody jumlah_transfer = RequestBody.create(
                        MediaType.parse("text/plain"),
                        ""+etJumlahTransfer.getText().toString());
                apiInterface.uploadImage(partGambar,pemesanan_id,jumlah_transfer,total).enqueue(new Callback<ResponseGambar>() {
                    @Override
                    public void onResponse(Call<ResponseGambar> call, Response<ResponseGambar> response) {
                        System.out.println("UUU :"+response);
                        pd.dismiss();
                        Log.d("Kambing",""+response.body().toString());
                        if (response.body().getKode().equals("1")){
                            Toast.makeText(getApplicationContext(), ""+response.body().getPesan(), Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Gagal", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseGambar> call, Throwable t) {
                        pd.dismiss();
                        Log.d("Kambing",""+t.toString());
                    }
                });
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            if (requestCode ==REQUEST_GALLERY){

                Uri dataImage = data.getData();
                String[] imageprojection = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(dataImage,imageprojection,null,null,null);
                if (cursor !=null ){
                    cursor.moveToFirst();
                    int indexImage = cursor.getColumnIndex(imageprojection[0]);
                    partimage = cursor.getString(indexImage);
                    cursor.close();
                    if (partimage != null){
                        File image = new File(partimage);
//                        Bitmap bitmap1 = BitmapFactory.decodeStream();
//                        img.setImageBitmap(bitmap1);
                        imgView.setImageBitmap(BitmapFactory.decodeFile(partimage));
//                        Picasso.get()
//                                .load(dataImage)
//                                .resize(6000,2000)
//                                .onlyScaleDown()
//                                .centerInside()
//                                .into((ImageView) findViewById(R.id.imggambar));
                    }
                }
            }

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent i = new Intent(getApplicationContext(), MainTabActivity.class);
                startActivity(i);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @NonNull
    private RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(
                MultipartBody.FORM, descriptionString);
    }
}
