package id.nesd.ovqat.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import id.nesd.ovqat.LoginActivity;
import id.nesd.ovqat.R;
import id.nesd.ovqat.TentangActivity;
import id.nesd.ovqat.data.SPData;
import id.nesd.ovqat.model.UserModel;
import id.nesd.ovqat.model.app.SingletonModel;

public class AccountFragment extends Fragment {
    private UserModel user;
    private TextView tvName;
    private TextView tvEmail;
    private TextView tvUsername;
    private TextView tvTentang;
    private TextView tvLogout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_account, container, false);
        user = SingletonModel.getInstance().getUser();
        tvName = v.findViewById(R.id.tvName);
        tvEmail = v.findViewById(R.id.tvEmail);
        tvUsername = v.findViewById(R.id.tvUsername);
        tvTentang = v.findViewById(R.id.tvTentang);
        tvLogout = v.findViewById(R.id.tvLogout);
        initData();
        return v;
    }

    private void initData() {
        tvName.setText(user.getDisplayName());
        tvEmail.setText(user.getEmail());
        tvUsername.setText(user.getUsername());
        tvTentang.setOnClickListener(view -> startActivity(new Intent(getContext(), TentangActivity.class)));
        tvLogout.setOnClickListener(view -> logout());
    }

    private void logout() {
        SingletonModel.getInstance().clear();
        SPData.getInstance(getContext()).clear();
        startActivity(new Intent(getContext(), LoginActivity.class));
        requireActivity().finish();
    }
}
