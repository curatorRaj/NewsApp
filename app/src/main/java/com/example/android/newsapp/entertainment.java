package com.example.android.newsapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class entertainment extends Fragment {
    private RequestQueue mRequestQueue;
    ArrayList<News> screen;
    ImageView imageView;
    EditText editText;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.entertainment, container, false);

        screen = new ArrayList<>();
        imageView = rootView.findViewById(R.id.search_image);

        Uri.Builder builder2 = new Uri.Builder();


        mRequestQueue = Volley.newRequestQueue(getActivity());

        builder2.scheme("https")
                .authority("newsapi.org")
                .appendPath("v2")
                .appendPath("top-headlines")
                .appendQueryParameter("category","entertainment")
                .appendQueryParameter("country", "in")
                .appendQueryParameter("apiKey", "fadbe6eafd154620ad0d17519a81e195");
        String myUrl = builder2.build().toString();
        jsonparse(rootView, myUrl);


        return rootView;


    }

    public void jsonparse(final View root, String url) {


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new
                Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray array = response.getJSONArray("articles");
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject article = array.getJSONObject(i);
                                String name = article.getString("title");
                                String image = article.getString("urlToImage");
                                String url = article.getString("url");
                                String content = article.getString("content");
                                String discription = article.getString("description");
                                discription = discription + content;
                                screen.add(new News(name, image, discription, url));


                            }
                            CoustumAdapter adapter = new CoustumAdapter(getActivity(), 0, screen);
                            ListView listView = (ListView) root.findViewById(R.id.entertainmentList);
                            listView.setAdapter(adapter);

                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    News x = screen.get(position);
                                    Intent intent = new Intent(getActivity(), newsDetail.class);
                                    intent.putExtra("image_url", x.getImage());
                                    intent.putExtra("detail", x.getNews());
                                    intent.putExtra("url", x.getNewsUrl());
                                    startActivity(intent);

                                }
                            });


                        } catch (JSONException e) {
                            Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT);

                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT);

            }
        });
        mRequestQueue.add(request);

    }
}

