package ui.activities;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import ui.fragments.CaraPemesananFragment;
import ui.fragments.OneFragment;
import ui.fragments.ProfilFragment;
import ui.fragments.PaketMenuFragment;
import ui.fragments.VerifikasiPemesananFragment;

import com.example.anonymous.catering.R;
import com.example.anonymous.catering.utils.SessionManager;
import com.google.firebase.auth.FirebaseAuth;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class MainTabActivity extends AppCompatActivity {
    SessionManager sessionManager;
    private static final String TAG = "MainTabActivity";
    private Toolbar toolbar;
    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;
    private TabLayout tabLayout;
    private ViewPager viewPager;
//    private int[] tabIcons = {
//            R.drawable.ic_home_black_24dp,
//            R.drawable.ic_search_black_24dp,
//            R.drawable.ic_store_mall_directory_black_24dp,
//            R.drawable.ic_mail_outline_black_24dp
//    };

    OneFragment oneFragment;
    Fragment searchFragment;
    PaketMenuFragment paketMenuFragment;
    CaraPemesananFragment caraPemesananFragment;
    VerifikasiPemesananFragment VPFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tab);

        // add deferent menu opptions

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String hashKey = new String(Base64.encode(md.digest(), 0));
                Log.i(TAG, "printHashKey() Hash Key: " + hashKey);
            }
        } catch (NoSuchAlgorithmException e) {
            Log.e(TAG, "printHashKey()", e);
        } catch (Exception e) {
            Log.e(TAG, "printHashKey()", e);
        }

    }

    public void navigateFragment(int position){
        viewPager.setCurrentItem(position, true);

    }
    // end OnCreate

//    private void setupTabIcons() {
//        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
//        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
//        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
//        tabLayout.getTabAt(3).setIcon(tabIcons[3]);
//    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        oneFragment     = new OneFragment();
        searchFragment  = new ProfilFragment();
        paketMenuFragment = new PaketMenuFragment();
        caraPemesananFragment = new CaraPemesananFragment();
        VPFragment      = new VerifikasiPemesananFragment();

        adapter.addFragment(oneFragment, "Home");
        adapter.addFragment(searchFragment, "Profil");
        adapter.addFragment(paketMenuFragment, "Menu Paket");
        adapter.addFragment(caraPemesananFragment, "Cara Pembayaran");
        adapter.addFragment(VPFragment, "Verifikasi Pemesanan");
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_leave) {
            //Toast.makeText(MainTabActivity.this, "Action clicked", Toast.LENGTH_LONG).show();
            Logout();
            return true;
        }

        else if (id == R.id.action_refresh){
            Log.i(TAG, "Refresh menu item selected");
            Toast.makeText(MainTabActivity.this, "Action clicked refresh", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainTabActivity.this, UploadImage.class);
            return true;

        }
        else if (id == R.id.action_logout){
            Intent intent = new Intent(MainTabActivity.this, LoginActivity.class);

            // redirect to login page
            sessionManager.logoutMember();
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
            finish();
            return true;

        }
        else if (id == R.id.action_settings){
            Log.i(TAG, "Refresh menu item selected");
            Toast.makeText(MainTabActivity.this, "Action clicked settings", Toast.LENGTH_LONG).show();
            startActivity(new Intent(MainTabActivity.this, ProfileActivity.class));
            return true;

        }

        return super.onOptionsItemSelected(item);
    }

    private void Logout() {
        auth.signOut();
        startActivity(new Intent(MainTabActivity.this, LoginActivity.class));
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
//            viewPager.setCurrentItem(position, true);
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
//            return null;
            return mFragmentTitleList.get(position);
        }

    }

}