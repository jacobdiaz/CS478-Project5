package course.examples.Services.KeyClient;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class PlaySongAdapter extends RecyclerView.Adapter<PlaySongAdapter.MyViewHolder> {

    List<String> songNameList;
    List<String> songArtistList;
    List<String> songUrlList;

    public PlaySongAdapter(List<String> songNameList, List<String> songArtistList, List<String> songUrlList){
        this.songNameList = songNameList;
        this.songArtistList = songArtistList;
        this.songUrlList = songUrlList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView song_title;
        private TextView song_artist;
        public MyViewHolder(final View view){
            super(view);
            song_title = view.findViewById(R.id.song_title);
            song_artist = view.findViewById(R.id.artist);
        }
    }

    @NonNull
    @Override
    public PlaySongAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        // Inflate our view
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.play_song_list_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaySongAdapter.MyViewHolder myViewHolder, int position) {
        String songName = songNameList.get(position);
        String songArtist = songArtistList.get(position);

        myViewHolder.song_title.setText(songName);
        myViewHolder.song_artist.setText(songArtist);
    }

    @Override
    public int getItemCount() {
        return songNameList.size();
    }
}
