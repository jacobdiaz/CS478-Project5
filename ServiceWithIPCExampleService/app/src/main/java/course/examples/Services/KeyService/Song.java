package course.examples.Services.KeyService;

public class Song {
    private String songName;
    private String artist;
    private String songUrl;

    // com.jdiaz88.musiccentralapi.Song constructor
    Song(String songName, String artist, String songUrl) {
        this.songName = songName;
        this.artist = artist;
        this.songUrl = songUrl;
    }

    // Getters
    public String getSongName() {
        return songName;
    }
    public String getArtist() {
        return artist;
    }
    public String getSongUrl() {
        return songUrl;
    }

    // Setters
    public void setSongName(String songName) {
        this.songName = songName;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setSongUrl(String songUrl) {
        this.songUrl = songUrl;
    }
}