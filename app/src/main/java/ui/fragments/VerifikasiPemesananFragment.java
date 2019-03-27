package ui.fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anonymous.catering.R;
import com.example.anonymous.catering.adapters.GuruAdapter;
import com.example.anonymous.catering.adapters.PemesananAdapter;
import com.example.anonymous.catering.config.ServerConfig;
import com.example.anonymous.catering.models.Pembayaran;
import com.example.anonymous.catering.models.Pemesanan;
import com.example.anonymous.catering.response.ResponseGuru;
import com.example.anonymous.catering.response.ResponsePemesanan;
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
import ui.activities.GuruActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class VerifikasiPemesananFragment extends Fragment {
    SessionManager sessionManager;
    @BindView(R.id.rvVerifikasi)
    RecyclerView rvVerifikasi;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ProgressDialog progressDialog;
    int idnya;
    ApiInterface apiInterface;
    List<Pemesanan> pembayaran = new ArrayList<>();
    public VerifikasiPemesananFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_verifikasi_pemesanan, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressDialog = new ProgressDialog(getActivity());
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvVerifikasi.setLayoutManager(layoutManager);
        sessionManager = new SessionManager(getActivity());
        final HashMap<String, String> map = sessionManager.getMemberProfile();

        idnya = Integer.parseInt(sessionManager.getMemberProfile().get("id_member"));


        apiInterface = ApiClient.getClient(ServerConfig.API_ENDPOINT).create(ApiInterface.class);
        getLayout(map.get(sessionManager.ID_MEMBER));
        progressDialog.setMessage("Loading ...");
        progressDialog.show();
    }
    private void getLayout(String id_member) {


        apiInterface.pemesananFindByMember(String.valueOf(idnya)).enqueue(new Callback<ResponsePemesanan>() {

            @Override
            public void onResponse(Call<ResponsePemesanan> call, Response<ResponsePemesanan> response) {

                Log.d("TAGkampret2 :", ""+idnya);

                progressDialog.hide();
//                System.out.println("Pemesanan Berhasil"+response);
                Log.d("TAGNYA :", response.body().toString());
                if (response.isSuccessful()){
                    if (response.body().getMaster().size()>0){
                        pembayaran = response.body().getMaster();
                        adapter = new PemesananAdapter(getActivity(), pembayaran);
                        rvVerifikasi.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponsePemesanan> call, Throwable t) {
                Log.d("Error", t.toString());
            }
        });
    }

}
