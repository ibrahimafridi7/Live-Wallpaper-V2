package apps.sami.wallpaperv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    AdapterLiveWallpaper adapterRVWallpaper;
    List<ModelLiveWallpaper> wallpaperModelList;
    int pageNumber = 1;
    Boolean isScrolling = false;
    int currentItems,totalItems,scrollOutItems;


    String url = "https://api.pexels.com/videos/popular?per_page=80/";
    private Object ModelLiveWallpaper;
    String link1;
    String file_type;
    int id1;
    String quality;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        wallpaperModelList =new ArrayList<>();
        adapterRVWallpaper = new AdapterLiveWallpaper(this,wallpaperModelList);
        recyclerView.setAdapter(adapterRVWallpaper);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);










        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                currentItems =gridLayoutManager.getChildCount();
                totalItems = gridLayoutManager.getItemCount();
                scrollOutItems =gridLayoutManager.findFirstVisibleItemPosition();

                if(isScrolling && (currentItems+scrollOutItems==totalItems)){
                    isScrolling = false;
                    fetchWallpaper();
                }
            }
        });

        fetchWallpaper();
    }





    public void fetchWallpaper(){
        StringRequest request = new StringRequest(Request.Method.GET,url
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {


                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("videos");
                    int lenght = jsonArray.length();

                    for (int c = 0; c < jsonArray.length(); c++) {
                        JSONObject object = jsonArray.getJSONObject(c);
                        int id = object.getInt("id");
                        String pictures = object.getString("image");

                        JSONArray images = object.getJSONArray("video_files");
                        int lenght1 = jsonArray.length();

                        if (images.length() > 2) {
                            JSONObject json_obj = images.getJSONObject(2);   //get the 3rd item
                            link1 = json_obj.getString("link");


//                        for (int l = 0; l < images.length(); l++) {
//                            JSONObject link = images.getJSONObject(l);
//                            int id1 = link.getInt("id");
//                            quality = link.getString("quality");
//                            file_type = link.getString("file_type");
//                            link1 = link.getString("link");
                            Log.e("lnkk",link1 );


                            }

                        ModelLiveWallpaper modelLiveWallpaper = new ModelLiveWallpaper(id, pictures, id1, quality, file_type, link1);
                        wallpaperModelList.add(modelLiveWallpaper);
                          }


                        adapterRVWallpaper.notifyDataSetChanged();

                    }catch(JSONException e){

                    }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> para = new HashMap<>();
                para.put( "Authorization","563492ad6f91700001000001d359b5bf46ae4b6a92dde8df391e839b");
                return para;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);

    }
}