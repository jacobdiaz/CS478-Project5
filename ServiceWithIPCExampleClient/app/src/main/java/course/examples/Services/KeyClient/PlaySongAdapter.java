package course.examples.Services.KeyClient;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PlaySongAdapter extends RecyclerView.Adapter<PlaySongAdapter.MyViewHolder> {

    List<String> localSongNameList = new ArrayList<>();
    List<String> localSongArtistList = new ArrayList<>();

    public PlaySongAdapter(List<String> localSongNameList, List<String> localSongArtistList){
        this.localSongNameList = localSongNameList;
        this.localSongArtistList = localSongArtistList;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView playSongTitle ;
        private TextView playSongArtist;

        public MyViewHolder(final View view){
            super(view);
            playSongTitle = view.findViewById(R.id.playSongtitle);
            playSongArtist = view.findViewById(R.id.playSongArtist);
        }
    }



    @NonNull
    @Override
    public PlaySongAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.play_song_list_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaySongAdapter.MyViewHolder myViewHolder, int position) {

        String playSongName = localSongNameList.get(position);
        String playSongArtist = localSongArtistList.get(position);

        myViewHolder.playSongTitle.setText(playSongName);
        myViewHolder.playSongArtist.setText(playSongArtist);
    }

    @Override
    public int getItemCount() {
        return localSongArtistList.size();
    }
}
