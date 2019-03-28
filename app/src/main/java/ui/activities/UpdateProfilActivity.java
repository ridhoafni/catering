package ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.anonymous.catering.R;

public class UpdateProfilActivity extends AppCompatActivity {

    public static final String KEY_ID_MEMBER = "id_member";
    public static final String KEY_EMAIL= "email";
    public static final String KEY_NAMA= "nama_lengkap";
    public static final String KEY_NO_HP= "no_hp";
    public static final String KEY_AGAMA= "agama";
    public static final String KEY_USERNAME= "username";
    public static final String KEY_JK= "jk";

    private TextView tvNama, tvNamaLengkap, tvEmail, tvEmail2, tvJk, tvHP, tvAgama, tvUsername;
    private String nama, email, jk, hp, agama, username;
    private int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profil);

        tvNama = (TextView) findViewById(R.id.etFormNama);
        tvEmail= (TextView) findViewById(R.id.etFormEmail);
        tvJk= (TextView) findViewById(R.id.etFormJk);
        tvHP   = (TextView) findViewById(R.id.etFormNoHp);
        tvAgama   = (TextView) findViewById(R.id.etFormAgama);
        tvUsername   = (TextView) findViewById(R.id.etFormUsername);

        Intent i = getIntent();
        id = i.getIntExtra(KEY_ID_MEMBER, 0);
        nama = i.getStringExtra(KEY_NAMA);
        jk = i.getStringExtra(KEY_JK);
        username = i.getStringExtra(KEY_USERNAME);
        agama = i.getStringExtra(KEY_AGAMA);
        hp = i.getStringExtra(KEY_NO_HP);
        email = i.getStringExtra(KEY_EMAIL);

        tvNama.setText(nama);
        tvJk.setText(jk);
        tvUsername.setText(username);
        tvHP.setText(hp);
        tvEmail.setText(email);
        tvAgama.setText(agama);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Edit Profile");

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Intent i = new Intent(getApplicationContext(), MainTabActivity.class);
                startActivity(i);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
