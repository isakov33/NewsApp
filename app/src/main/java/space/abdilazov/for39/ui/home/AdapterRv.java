package space.abdilazov.for39.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import space.abdilazov.for39.databinding.ItemRvBinding;
import space.abdilazov.for39.interfaces.OnItemClickListener;
import space.abdilazov.for39.models.News;

public class AdapterRv extends RecyclerView.Adapter<AdapterRv.ViewHolder> {

   private ArrayList<News> list;
    private OnItemClickListener onItemClickListener;

    public AdapterRv(){
        list = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemRvBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(list.get(position),onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public void addItem(News news){
        list.add(0,news);
        notifyItemInserted(0);
    }

    public void updateItem(News news) {
        int index = list.indexOf(news);
        list.set(index,news);
        notifyItemChanged(index);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public News getItem(int a) {
        return list.get(a);
    }
    public void removeItem(News news, int a) {
        this.list.remove(news);
        notifyItemRemoved(a);
    }

        public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemRvBinding binding;

        public ViewHolder(@NonNull ItemRvBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        binding.getRoot().setOnClickListener(view -> {
                onItemClickListener.onClick(getAdapterPosition());
        });
        binding.getRoot().setOnLongClickListener(view -> {
            onItemClickListener.onLongClick(getAdapterPosition());
            return true;
        });
        }

        public void onBind(News news, OnItemClickListener onItemClickListener) {
            binding.tvTitle.setText(news.getTitle());
        }
    }
}
