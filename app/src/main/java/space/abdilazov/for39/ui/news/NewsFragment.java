package space.abdilazov.for39.ui.news;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.io.Serializable;

import space.abdilazov.for39.R;
import space.abdilazov.for39.databinding.FragmentNewsBinding;
import space.abdilazov.for39.models.News;

public class NewsFragment extends Fragment {

    private FragmentNewsBinding binding;
    private News news;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNewsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (news != null)
            binding.editText.setText(news.getTitle());
        binding.btnSave.setOnClickListener(view1 -> save());

    }

    private void save() {
            String text = binding.editText.getText().toString().trim();
            Bundle bundle = new Bundle();
            if (news == null) {
                news = new News(text, System.currentTimeMillis());
                bundle.putSerializable("news", news);
                bundle.putString("text", text);
                getParentFragmentManager().setFragmentResult("rv_news", bundle);
            } else {
                news.setTitle(text);
                bundle.putSerializable("news", news);
                getParentFragmentManager().setFragmentResult("rk_news_update", bundle);
            }
            close();
        }

        private void close() {
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
            navController.navigate(R.id.navigation_home);
        }
    }
