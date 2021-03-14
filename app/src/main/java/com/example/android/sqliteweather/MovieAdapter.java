package com.example.android.sqliteweather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.sqliteweather.data.GenreData;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieItemViewHolder> {
    private ArrayList<GenreData> genreList;
    private OnMovieItemClickListener onMovieItemClickListener;

    public interface OnMovieItemClickListener {
        void onMovieItemClick(GenreData genreData);
    }

    public MovieAdapter(OnMovieItemClickListener onMovieItemClickListener) {
        this.onMovieItemClickListener = onMovieItemClickListener;
    }

    @NonNull
    @Override
    public MovieItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.genre_list_item, parent, false);
        return new MovieItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieItemViewHolder holder, int position) {
        holder.bind(this.genreList.get(position));
    }

    public void updateGenreData(ArrayList<GenreData> genreList) {
        this.genreList = genreList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (this.genreList == null) {
            return 0;
        } else {
            return this.genreList.size();
        }
    }

    class MovieItemViewHolder extends RecyclerView.ViewHolder {
        final private TextView genreTV;

        public MovieItemViewHolder(@NonNull View itemView) {
            super(itemView);
            genreTV = itemView.findViewById(R.id.tv_genre);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onMovieItemClickListener.onMovieItemClick(
                            genreList.get(getAdapterPosition())
                    );
                }
            });
        }

        public void bind(GenreData genreData) {
            Context ctx = this.itemView.getContext();

            genreTV.setText(genreData.getName());

        }

    }
}
