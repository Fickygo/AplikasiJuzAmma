package com.example.AplikasiJuzAmma;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.pengenalantanamanobat.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<ModelData> listIbadah;
    SearchView searchView;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listIbadah = new ArrayList<>();
        recyclerView = findViewById(R.id.rvListIbadah);


        // Get data from local JSON
        try {
            JSONObject jsonObject = new JSONObject(JsonDataFromAsset());
            JSONArray jsonArray = jsonObject.getJSONArray("daftar_surat");
            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject dataIbadah = jsonArray.getJSONObject(i);
                listIbadah.add(new ModelData(
                        dataIbadah.getString("nama"),
                        dataIbadah.getString("image_url"),
                        dataIbadah.getString("deskripsi")
                ));
            }
        }catch (JSONException e){
            e.printStackTrace();
        }


        // add to recyclerView
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        AdapterData adapterData = new AdapterData(this, listIbadah);
        recyclerView.setAdapter(adapterData);
        adapterData.notifyDataSetChanged();

        filteredListData();


    }


    private String JsonDataFromAsset(){
        String json = null;
        try {
            InputStream inputStream = getAssets().open("AplikasiJuzAmma.json");
            int sizeOfFile = inputStream.available();
            byte[] bufferData = new byte[sizeOfFile];
            inputStream.read(bufferData);
            inputStream.close();
            json = new String(bufferData, "UTF-8");
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
        return json;
    }


    private void filteredListData(){
        searchView = findViewById(R.id.searchIbadah);
        searchView.clearFocus();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String searchText) {

                ArrayList<ModelData> filteredIbadah = new ArrayList<>();

                for (ModelData modelData : listIbadah){
                    if(modelData.getNama().toLowerCase().contains(searchText.toLowerCase())){
                        filteredIbadah.add(modelData);
                    }
                }

                AdapterData adapterFilteredData = new AdapterData(getApplicationContext(), filteredIbadah);
                recyclerView.setAdapter(adapterFilteredData);
                adapterFilteredData.notifyDataSetChanged();

                return true;
            }
        });
    }

}