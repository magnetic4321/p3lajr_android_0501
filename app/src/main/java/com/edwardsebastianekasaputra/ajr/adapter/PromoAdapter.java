package com.edwardsebastianekasaputra.ajr.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.edwardsebastianekasaputra.ajr.R;
import com.edwardsebastianekasaputra.ajr.databinding.RvItemPromoBinding;
import com.edwardsebastianekasaputra.ajr.models.Promo;

import java.util.ArrayList;
import java.util.List;

public class PromoAdapter extends RecyclerView.Adapter<PromoAdapter.ViewHolder> implements Filterable {

    private List<Promo> promoList, filteredPromoList;
    private final Context context;

    public PromoAdapter(List<Promo> promoList, Context context) {
        this.promoList = promoList;
        filteredPromoList = new ArrayList<>(promoList);
//        this.promoList = new ArrayList<>(promoList);
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        RvItemPromoBinding binding;

        public ViewHolder(@NonNull RvItemPromoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            this.binding.cvPromo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e("TEST", "onClick: " +getAdapterPosition());
                }
            });
        }
        public void bindView(Promo promo) {
            binding.setPromo(promo);
        }
    }

    @NonNull
    @Override
    public PromoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RvItemPromoBinding rvItemPromoBinding = RvItemPromoBinding.inflate(inflater, parent, false);

        return new ViewHolder(rvItemPromoBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull PromoAdapter.ViewHolder holder, int position) {
        Promo promo = filteredPromoList.get(position);
        holder.binding.cvPromo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        holder.binding.tvKode.setText(promo.getKode());
        holder.binding.tvKeterangan.setText(promo.getKeterangan());
        holder.bindView(filteredPromoList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return filteredPromoList.size();
    }

    public void setPromoList(List<Promo> promoList) {
        this.promoList = promoList;
        filteredPromoList = new ArrayList<>(promoList);
//        this.promoList = new ArrayList<>(promoList);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charSequenceString = charSequence.toString();
                List<Promo> filtered = new ArrayList<>();

                if (charSequenceString.isEmpty()) {
                    filtered.addAll(promoList);
                } else {
                    for (Promo promo : promoList) {
                        if (promo.getKode().toLowerCase()
                                .contains(charSequenceString.toLowerCase()))
                            filtered.add(promo);
                    }
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filtered;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredPromoList.clear();
                filteredPromoList.addAll((List<Promo>) filterResults.values);
                notifyDataSetChanged();
            }
        };
    }
}
