package space.abdilazov.for39.ui.profile;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import space.abdilazov.for39.R;
import space.abdilazov.for39.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private boolean change;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initImageView();
    }

    private void initImageView() {

        ActivityResultLauncher<String>getImageView = registerForActivityResult(new ActivityResultContracts.GetContent(),
                uri-> {

                    Glide.with(binding.circleIv).load(uri).circleCrop().into(binding.circleIv);
                    change = true;

        });

        binding.circleIv.setOnClickListener(view -> {
            if (change){
                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                builder.setNeutralButton("Заменить", ((dialog,which) -> getImageView.launch("image/*")));
                builder.setPositiveButton("Удалить",((dialog, which) -> {
                    binding.circleIv.setImageResource(R.drawable.neymar);
                }));
                @SuppressLint("InflateParams") ConstraintLayout constraintLayout  = (ConstraintLayout) getLayoutInflater().inflate(R.layout.item_alert_dialog,null);
                builder.setView(constraintLayout);
                AlertDialog dialog = builder.create();
                dialog.show();
                change = false;
            } else {
                getImageView.launch("image/*");
            }
        });
    }
}