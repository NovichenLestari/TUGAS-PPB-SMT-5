package id.nesd.ovqat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import id.nesd.ovqat.fragment.AccountFragment;
import id.nesd.ovqat.fragment.HomeFragment;
import id.nesd.ovqat.fragment.OrderFragment;
import id.nesd.ovqat.tool.Helper;

public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {
    private Helper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = new Helper();
        BottomNavigationView bnvMain = findViewById(R.id.bnvMain);
        bnvMain.setOnItemSelectedListener(this);
        loadFragment(new HomeFragment(), "Beranda");
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        String title = null;
        int id = item.getItemId();
        if (id == R.id.itemHome) {
            fragment = new HomeFragment();
            title = "Beranda";
        } else if (id == R.id.itemOrder) {
            fragment = new OrderFragment();
            title = "Riwayat Pesanan";
        } else if (id == R.id.itemAccount) {
            fragment = new AccountFragment();
            title = "Akun";
        }

        return loadFragment(fragment, title);
    }

    private boolean loadFragment(Fragment fragment, String title) {
        if (fragment != null && title != null) {
            helper.setTitleAppBar(this, title);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.flMain, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}