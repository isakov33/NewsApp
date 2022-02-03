package space.abdilazov.for39.ui.home;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import space.abdilazov.for39.R;
import space.abdilazov.for39.databinding.FragmentHomeBinding;
import space.abdilazov.for39.interfaces.OnItemClickListener;
import space.abdilazov.for39.models.News;

public class HomeFragment extends Fragment {

    private AdapterRv adapter;
    private FragmentHomeBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new AdapterRv();
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(int a) {}

            @Override
            public void onLongClick(int a) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setNeutralButton("Отмена", ((dialog, which) -> {
                }));
                builder.setPositiveButton("Удалить", (dialog, which) -> {
                    News news = adapter.getItem(a);
                    adapter.removeItem(news, a);
                });
            }
        });
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.fab.setOnClickListener(view -> openFragment(null));
        getParentFragmentManager().setFragmentResultListener("rv_news",getViewLifecycleOwner(),(requestKey, result) -> {
            News news = (News)result.getSerializable("news");
            Log.e("Home","text:" + news.getTitle());
            adapter.addItem(news);
        });
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initList();
    }

        private void initList() {
        binding.recyclerView.setAdapter(adapter);
}
    private void openFragment(News news) {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        navController.navigate(R.id.navigation_news);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
