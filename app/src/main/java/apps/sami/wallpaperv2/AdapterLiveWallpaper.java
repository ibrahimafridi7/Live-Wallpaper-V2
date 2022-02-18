package apps.sami.wallpaperv2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class AdapterLiveWallpaper extends RecyclerView.Adapter<WallLiveHolder> {

    private Context context;
    private List<ModelLiveWallpaper> wallModelList;

    public AdapterLiveWallpaper(Context context, List<ModelLiveWallpaper> wallModelList) {
        this.context = context;
        this.wallModelList = wallModelList;
    }

    @NonNull
    @Override
    public WallLiveHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.items , parent , false);
        return new WallLiveHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WallLiveHolder holder, int position) {
        
        Glide.with(context).load(wallModelList.get(position).getPictures()).into(holder.imageView1);
        holder.imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, FullScreen.class)
//                        .putExtra("picture",wallModelList.get(position).getPictures())
                .putExtra("link",wallModelList.get(position).getLink1()));

            }
        });
    }


    @Override
    public int getItemCount() {
        return  wallModelList.size();
    }
}
    class WallLiveHolder extends RecyclerView.ViewHolder{

    ImageView imageView1;

    public WallLiveHolder(@NonNull View itemView){
    super(itemView);
    imageView1 = itemView.findViewById(R.id.imageViewItems1);
                                                    }

}