package com.example.yard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;
import com.parse.ParseUser;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {
    private Context context;
    private List<PostCreation> posts;

    public PostsAdapter(Context context, List<PostCreation> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PostCreation post = posts.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvUsername;
        private ImageView ivUserImage;
        private TextView tvCaption;
        private TextView tvLocation;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            ivUserImage = itemView.findViewById(R.id.ivUserImage);
            tvCaption = itemView.findViewById(R.id.tvCaption);
            tvLocation = itemView.findViewById(R.id.tvLocation);
        }

        public void bind(PostCreation post) {
            tvCaption.setText(post.getKeyDescription());
            if (post.getKeyUser() == null){
                Toast.makeText(context, "User doesn't exist", Toast.LENGTH_SHORT).show();
                return;
            }
            else {
                tvUsername.setText(post.getKeyUser().getUsername());
            }
            tvLocation.setText(post.getKeyLocation());
            ParseFile image = post.getImage();
            if (image != null){
                Glide.with(context).load(image.getUrl()).into(ivUserImage) ;
            }
            else{
                ivUserImage.setVisibility(View.GONE);
            }
            if (tvLocation.getText().toString() == ""){
                tvLocation.setVisibility(View.GONE);
            }
        }
    }


}
