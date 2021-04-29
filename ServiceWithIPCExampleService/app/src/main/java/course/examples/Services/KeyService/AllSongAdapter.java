package course.examples.Services.KeyService;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AllSongAdapter extends RecyclerView.Adapter<AllSongAdapter.MyViewHolder> {

    private List<String> songNameList;
    private List<String> songArtistList;
    private List<String> songUrlList;

    private int[] images = {R.drawable.ifollowrivers, R.drawable.funnything, R.drawable.blueworld, R.drawable.american, R.drawable.devuelveme};


    public AllSongAdapter(List<String> songNameList, List<String> songArtistList, List<String> songUrlList){
        this.songNameList = songNameList;
        this.songArtistList = songArtistList;
        this.songUrlList = songUrlList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView song_title;
        private TextView song_artist;
        private TextView song_url;
        private ImageView albumCovers;

        public MyViewHolder(final View view){
            super(view);
            song_title = view.findViewById(R.id.song_title);
            song_artist = view.findViewById(R.id.artist);
            song_url = view.findViewById(R.id.url);
            albumCovers = view.findViewById(R.id.album_cover);
        }
    }

    @NonNull
    @Override
    public AllSongAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        // Inflate our view
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_song_list_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AllSongAdapter.MyViewHolder myViewHolder, int position) {
        // Set the text based on position
        String songName = songNameList.get(position);
        String songArtist = songArtistList.get(position);
        String songUrl = songUrlList.get(position);
        int imageId = images[position];

        // Set the text
        myViewHolder.song_title.setText(songName);
        myViewHolder.song_artist.setText(songArtist);
        myViewHolder.song_url.setText(songUrl);

        // Set the image cover
        myViewHolder.albumCovers.setImageResource(imageId);
    }

    @Override
    public int getItemCount() {
        return songNameList.size();
    }
}
