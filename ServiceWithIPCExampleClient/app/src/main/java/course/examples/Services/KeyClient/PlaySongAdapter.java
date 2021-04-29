package course.examples.Services.KeyClient;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PlaySongAdapter extends RecyclerView.Adapter<PlaySongAdapter.MyViewHolder> {

    List<String> localSongNameList = new ArrayList<>();
    List<String> localSongArtistList = new ArrayList<>();
    Intent musicServiceIntent;
    public PlaySongAdapter(List<String> localSongNameList, List<String> localSongArtistList){
        this.localSongNameList = localSongNameList;
        this.localSongArtistList = localSongArtistList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView playSongTitle ;
        private TextView playSongArtist;
        private Button playButton;
        private Button pauseButton;

        public MyViewHolder(final View view){
            super(view);
            playSongTitle = view.findViewById(R.id.playSongtitle);
            playSongArtist = view.findViewById(R.id.playSongArtist);
            playButton = view.findViewById(R.id.playSongBtn);
            pauseButton = view.findViewById(R.id.pauseSongBtn);

        }
    }

    @NonNull
    @Override
    public PlaySongAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.play_song_list_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final PlaySongAdapter.MyViewHolder myViewHolder, final int position) {

        final String playSongName = localSongNameList.get(position);
        String playSongArtist = localSongArtistList.get(position);

        myViewHolder.playSongTitle.setText(playSongName);
        myViewHolder.playSongArtist.setText(playSongArtist);


        // If Play Button is clicked
        myViewHolder.playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myViewHolder.playSongTitle.setTextColor(Color.parseColor("#CF1B1B"));
                musicServiceIntent = new Intent(v.getContext(), MusicService.class);
                // Pass arguments to service
                musicServiceIntent.putExtra("songIndex",myViewHolder.getAdapterPosition());

                // Start the service
                v.getContext().startService(musicServiceIntent);

                Log.i("PlaySongAdapter", "Play Button #"+myViewHolder.getAdapterPosition()+" was pressed");
            }
        });

        // If Pause Button is clicked
        myViewHolder.pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewHolder.playSongTitle.setTextColor(Color.parseColor("#ffffff"));
                v.getContext().stopService(musicServiceIntent);
                Log.i("PlaySongAdapter", "Pause Button #"+myViewHolder.getAdapterPosition()+". Stopped Service");
            }
        });
    }

    @Override
    public int getItemCount() {
        return localSongArtistList.size();
    }
}
