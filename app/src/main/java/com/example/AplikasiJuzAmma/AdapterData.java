package com.example.AplikasiJuzAmma;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pengenalantanamanobat.R;

import java.util.ArrayList;

public class AdapterData extends RecyclerView.Adapter<AdapterData.HolderData> {

    ArrayList<ModelData> listData;
    LayoutInflater inflater;

    public AdapterData(Context context, ArrayList<ModelData> listData) {
        this.listData = listData;
        this.inflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public AdapterData.HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item_main, parent, false);
        return new HolderData(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterData.HolderData holder, int position) {
        holder.tvNamaIbadah.setText(listData.get(position).getNama());
        holder.cvListIbadah.setOnClickListener(view -> {
            Intent intent = new Intent(inflater.getContext(), DetailActivity.class);
            intent.putExtra("nama",  listData.get(position).getNama());
            intent.putExtra("image_url",  listData.get(position).getImage_url());
            intent.putExtra("deskripsi",  listData.get(position).getDeskripsi());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            inflater.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class HolderData extends RecyclerView.ViewHolder{
        CardView cvListIbadah;
        TextView tvNamaIbadah;
        public HolderData(@NonNull View itemView) {
            super(itemView);
            tvNamaIbadah = itemView.findViewById(R.id.tvNamaIbadah);
            cvListIbadah = itemView.findViewById(R.id.cvListIbadah);
        }
    }
}
