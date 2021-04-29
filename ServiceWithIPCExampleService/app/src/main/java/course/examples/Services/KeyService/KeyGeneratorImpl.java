package course.examples.Services.KeyService;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import course.examples.Services.KeyCommon.KeyGenerator;

public class KeyGeneratorImpl extends Service {

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


	private final KeyGenerator.Stub mBinder = new KeyGenerator.Stub() {
		public List<String> getSongNames() {

			checkCallingPermission("course.examples.Services.KeyService.GEN_ID") ;
			List<String> songNames = initList("songNames");
			return songNames;
		}
		public List<String> getArtists() {

			checkCallingPermission("course.examples.Services.KeyService.GEN_ID") ;
			List<String> artists = initList("artists");
			return artists;
		}

		public List<String> getSongUrls() {
			checkCallingPermission("course.examples.Services.KeyService.GEN_ID") ;
			List<String> songUrls = initList("songUrls");
			return songUrls;
		}
	};

	@Override
	public IBinder onBind(Intent intent) {
		return mBinder;
	}
}
