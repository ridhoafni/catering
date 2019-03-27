package ui.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anonymous.catering.R;
import com.example.anonymous.catering.utils.SessionManager;

import ui.activities.MainTabActivity;
import ui.activities.UpdateProfilActivity;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {


    private static final String KEY_ID_GURU             = "id";
    private static final String KEY_NAMA                = "nama";
    private static final String KEY_EMAIL               = "email";
    private static final String KEY_NO_HP               = "no_hp";
    private static final String KEY_PASSWORD            = "password";
    private static final String KEY_TGL_LAHIR           = "password";
    private static final String KEY_JK                  = "jk";
    private static final String KEY_PROVINSI_KTP        = "provinsi_ktp";
    private static final String KEY_KOTA_KTP            = "kota_ktp";
    private static final String KEY_KECAMATAN_KTP       = "kecamatan_ktp";
    private static final String KEY_ALAMAT_KTP          = "alamat_ktp";
    private static final String KEY_PROVINSI_DOMISILI   = "provinsi_domisili";
    private static final String KEY_KOTA_DOMISILI       = "kota_domisili";
    private static final String KEY_KECAMATAN_DOMISILI  = "kecamatan_domisili";
    private static final String KEY_ALAMAT_DOMISILI     = "alamat_domisili";
    private static final String KEY_BIODATA             = "biodata";
    private static final String KEY_PHOTO_PROFILE       = "photo_profile";

    private int SESSION_ID_MURID;
    private String SESSION_NAMA;
    private String SESSION_EMAIL;
    private String SESSION_NO_HP;
    private String SESSION_PASSWORD;
    private String SESSION_TGL_LAHIR;
    private String SESSION_JK;
    private String SESSION_PROVINSI_KTP;
    private String SESSION_KOTA_KTP;
    private String SESSION_KECAMATAN_KTP;
    private String SESSION_ALAMAT_KTP;
    private String SESSION_PROVINSI_DOMISILI;
    private String SESSION_KOTA_DOMISILI;
    private String SESSION_KECAMATAN_DOMISILI;
    private String SESSION_ALAMAT_DOMISILI;
    private String SESSION_BIODATA;
    private String SESSION_PHOTO_PROFILE;

    private Button btnEdit;
    private Context context;
    private TextView tvNama, tvNamaLengkap, tvEmail, tvEmail2, tvJk, tvHP, tvAgama, tvUsername;
    ImageView imgProfile;
    SessionManager sessionManager;

    public static ProfilFragment newInstance() {
        return new ProfilFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        btnEdit = (Button) view.findViewById(R.id.btn_edit);
        tvNama = (TextView) view.findViewById(R.id.tv_nama);
        tvEmail= (TextView) view.findViewById(R.id.tv_email);
        tvJk= (TextView) view.findViewById(R.id.tv_jk);
        tvHP   = (TextView) view.findViewById(R.id.tv_no_hp);
        tvAgama   = (TextView) view.findViewById(R.id.tv_agama);
        tvUsername   = (TextView) view.findViewById(R.id.tv_username);

        sessionManager = new SessionManager(getActivity());

        tvNama.setText(sessionManager.getMemberProfile().get("nama_lengkap"));
        tvEmail.setText(sessionManager.getMemberProfile().get("email"));
        tvHP.setText(sessionManager.getMemberProfile().get("no_hp"));
        tvJk.setText(sessionManager.getMemberProfile().get("jk"));
        tvUsername.setText(sessionManager.getMemberProfile().get("username"));
        tvAgama.setText(sessionManager.getMemberProfile().get("agama"));
        tvHP.setText(sessionManager.getMemberProfile().get("no_hp"));

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), MainTabActivity.class);
                Intent edit = new Intent(getApplicationContext(), UpdateProfilActivity.class);
//                edit.putExtra(ProfilFragment.KEY_ID_GURU,      SESSION_ID_MURID);
//                edit.putExtra(ProfilFragment.KEY_NAMA,         SESSION_NAMA);
//                edit.putExtra(ProfilFragment.KEY_NO_HP,        SESSION_NO_HP);
//                edit.putExtra(ProfilFragment.KEY_EMAIL,        SESSION_EMAIL);

                startActivity(edit);
            }
        });
        return view;

    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
//        inflater.inflate(R.menu.menu_settings, menu);
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item){
//        switch (item.getItemId()){
//            case R.id.action_setings:
//                Intent i;
//                i = new Intent(getActivity(), SettingsActivity.class);
//                startActivity(i);
//
//        }
//        return super.onOptionsItemSelected(item);
//    }

}
