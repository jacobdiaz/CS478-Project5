package course.examples.Services.KeyService;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;

import java.util.ArrayList;
import java.util.List;

import course.examples.Services.KeyCommon.KeyGenerator;

public class MainActivity extends AppCompatActivity {
    RecyclerView allSongAdapter;

    public List<String> initList(String type){
        // List of Songs
        Song s1 = new Song("I Follow Rivers", "Lykke Li","https://soundcloud.com/cbsimg/lykke-li-i-follow-rivers-the");
        Song s2 = new Song("Funny Thing","Thundercat","https://soundcloud.com/thundercat-official/funny-thing");
        Song s3 = new Song("Blue World","Mac Miller","https://soundcloud.com/larryfisherman/mac-miller-blue-world");
        Song s4 = new Song("American Boy","Kanye West, Estelle","https://soundcloud.com/atlanticrecords/estelle-american-boy-feat");
        Song s5 = new Song("Devuelveme a mi chica","Hombres G","https://soundcloud.com/warnermusicspain/devuelveme-a-mi-chica");
        List<String> songNames = new ArrayList<String>();
        List<String> artists = new ArrayList<String>();
        List<String> songUrls = new ArrayList<String>();

        switch (type){
            case "songNames":
                songNames.add(s1.getSongName());
                songNames.add(s2.getSongName());
                songNames.add(s3.getSongName());
                songNames.add(s4.getSongName());
                songNames.add(s5.getSongName());
                return songNames;
            case "artists" :
                artists.add(s1.getArtist());
                artists.add(s2.getArtist());
                artists.add(s3.getArtist());
                artists.add(s4.getArtist());
                artists.add(s5.getArtist());
                return artists;
            case "songUrls":
                songUrls.add(s1.getSongUrl());
                songUrls.add(s2.getSongUrl());
                songUrls.add(s3.getSongUrl());
                songUrls.add(s4.getSongUrl());
                songUrls.add(s5.getSongUrl());
                return songUrls;
            default:
                break;
        }
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestWindowFeature(Window.FEATURE_NO_TITLE); // Remove the title bar
        Intent service = new Intent(this, KeyGeneratorImpl.class);
        startService(service);


        allSongAdapter = findViewById(R.id.allSongRV);
        setAllSongsAdapter();

    }

    private void setAllSongsAdapter() {
        List<String> songNames = initList("songNames");
        List<String> artists = initList("artists");
        List<String> songUrls = initList("songUrls");

        AllSongAdapter allAdapter = new AllSongAdapter(songNames,artists,songUrls);
        RecyclerView.LayoutManager allLayoutManager = new LinearLayoutManager(getApplicationContext());
        allSongAdapter.setLayoutManager(allLayoutManager);
        allSongAdapter.setItemAnimator(new DefaultItemAnimator());
        allSongAdapter.setAdapter(allAdapter);
    }
}