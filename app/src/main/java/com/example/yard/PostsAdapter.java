package com.example.yard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
<<<<<<< HEAD
import android.widget.Toast;
=======
>>>>>>> eb5cd50 (fragments, posts, email verification for only HBCUs in Tennessee, Delaware, and Virginia)

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;
<<<<<<< HEAD
import com.parse.ParseUser;
=======
>>>>>>> eb5cd50 (fragments, posts, email verification for only HBCUs in Tennessee, Delaware, and Virginia)

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {
<<<<<<< HEAD
=======

>>>>>>> eb5cd50 (fragments, posts, email verification for only HBCUs in Tennessee, Delaware, and Virginia)
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
<<<<<<< HEAD
        private TextView tvUsername;
        private ImageView ivUserImage;
        private TextView tvCaption;
        private TextView tvLocation;
=======

        private TextView tvUsername;
        private ImageView ivUserImage;
        private TextView tvCaption;

>>>>>>> eb5cd50 (fragments, posts, email verification for only HBCUs in Tennessee, Delaware, and Virginia)

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            ivUserImage = itemView.findViewById(R.id.ivUserImage);
            tvCaption = itemView.findViewById(R.id.tvCaption);
<<<<<<< HEAD
            tvLocation = itemView.findViewById(R.id.tvLocation);
=======
>>>>>>> eb5cd50 (fragments, posts, email verification for only HBCUs in Tennessee, Delaware, and Virginia)
        }

        public void bind(PostCreation post) {
            tvCaption.setText(post.getKeyDescription());
<<<<<<< HEAD
            if (post.getKeyUser() == null){
                Toast.makeText(context, "User doesn't exist", Toast.LENGTH_SHORT).show();
                return;
            }
            else {
                tvUsername.setText(post.getKeyUser().getUsername());
            }
            tvLocation.setText(post.getKeyLocation());
=======
            tvUsername.setText(post.getKeyUser().getUsername());
>>>>>>> eb5cd50 (fragments, posts, email verification for only HBCUs in Tennessee, Delaware, and Virginia)
            ParseFile image = post.getImage();
            if (image != null){
                Glide.with(context).load(image.getUrl()).into(ivUserImage) ;
            }
            else{
                ivUserImage.setVisibility(View.GONE);
            }
<<<<<<< HEAD
            if (tvLocation.getText().toString() == ""){
                tvLocation.setVisibility(View.GONE);
            }
=======
>>>>>>> eb5cd50 (fragments, posts, email verification for only HBCUs in Tennessee, Delaware, and Virginia)
        }
    }


}
