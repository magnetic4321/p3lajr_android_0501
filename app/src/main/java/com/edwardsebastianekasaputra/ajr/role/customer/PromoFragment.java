package com.edwardsebastianekasaputra.ajr.role.customer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.edwardsebastianekasaputra.ajr.R;
import com.edwardsebastianekasaputra.ajr.adapter.PromoAdapter;
import com.edwardsebastianekasaputra.ajr.auth.UserPreferences;
import com.edwardsebastianekasaputra.ajr.databinding.FragmentPromoBinding;
import com.edwardsebastianekasaputra.ajr.databinding.RvItemPromoBinding;
import com.edwardsebastianekasaputra.ajr.models.PromoResponse;
import com.edwardsebastianekasaputra.ajr.models.User;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class PromoFragment extends Fragment {

    private UserPreferences userPreferences;

    private PromoAdapter adapter;
    private RequestQueue queue;
    private FragmentPromoBinding binding;

    public PromoFragment() {
        // Required empty public constructor
    }
    private static final String URL_GET_PROMO = "http://192.168.1.8:8000/api/promos";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        queue = Volley.newRequestQueue(this.getContext());
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_promo, container, false);
        userPreferences = new UserPreferences(getContext());

        adapter = new PromoAdapter(new ArrayList<>(), this.getContext());
        binding.rvPromo.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));
        binding.rvPromo.setAdapter(adapter);
        getAllPromo();

//        binding.srPromo.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                getAllPromo();
//            }
//        });

        return binding.getRoot();
    }

//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        User user = userPreferences.getUserLogin();
//        binding.tvName.setText(user.getNama());
//    }

    private void getAllPromo() {
//        binding.srPromo.setRefreshing(true);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_GET_PROMO, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                PromoResponse promoResponse = gson.fromJson(response, PromoResponse.class);
                adapter.setPromoList(promoResponse.getPromoList());
                Toast.makeText(getContext(), promoResponse.getMessage(), Toast.LENGTH_SHORT).show();
//                binding.srPromo.setRefreshing(false);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try {
                    String responseBody = new String(error.networkResponse.data, StandardCharsets.UTF_8);
                    JSONObject errors = new JSONObject(responseBody);
                    Toast.makeText(getContext(), ("HALO?"), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                return headers;
            }
        };
        queue.add(stringRequest);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}