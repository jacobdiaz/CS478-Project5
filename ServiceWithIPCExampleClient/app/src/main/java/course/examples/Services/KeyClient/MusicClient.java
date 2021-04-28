package course.examples.Services.KeyClient;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import course.examples.Services.KeyCommon.KeyGenerator;

public class MusicClient extends Activity {

	protected static final String TAG = "SongServiceUser";
	protected static final int PERMISSION_REQUEST = 0;
	private KeyGenerator mKeyGeneratorService;
	private boolean mIsBound = false;

	List<String> songNameList;
	List<String> songArtistList;
	List<String> songUrlList;

	TextView bindStatusText;
	Button bindServiceButton;
	RecyclerView allSongAdapter;
	RecyclerView playSongAdapter;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		requestWindowFeature(Window.FEATURE_NO_TITLE); // Remove the title bar
		setContentView(R.layout.main);

		bindServiceButton = (Button) findViewById(R.id.bindServiceButton);
		bindStatusText = findViewById(R.id.bindStatusText);
		allSongAdapter = findViewById(R.id.allSongRV);
		allSongAdapter.setNestedScrollingEnabled(false);

		playSongAdapter = findViewById(R.id.playSongRv);


		try {
			setPlaySongAdapter();
		} catch(Exception e){
			Log.i(TAG, "Could not set play song adapter");
			e.printStackTrace();
		}
		bindServiceButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				try {
					// Bind service
					if (mIsBound) {
						// Set Bind Status Text
						bindStatusText.setText("Bound");
						bindStatusText.setTextColor(Color.parseColor("#CF1B1B"));

						// Set bind service button text
						bindServiceButton.setText("UnBind Service");

						// Retrieve all lists
						songNameList = mKeyGeneratorService.getSongNames();
						songArtistList = mKeyGeneratorService.getArtists();
						songUrlList = mKeyGeneratorService.getSongUrls();

						// TODO Set Recycler View
						setAllSongsAdapter();
						Log.i(TAG, "Service was bound!");
					}
					else {
						Log.i(TAG, "Service was not bound");
					}
				} catch (RemoteException e) { Log.e(TAG, e.toString()); }
			}
		});
	}

	List<String> localSongNameList = new ArrayList<>();
	List<String> localSongArtistList = new ArrayList<>();
	private List<String> initSongNamesList(){
		localSongNameList.add("I Follow Rivers");
		localSongNameList.add("Funny Thing");
		localSongNameList.add("Blue World");
		localSongNameList.add("American Boy");
		localSongNameList.add("Devuelveme a mi chica");
		return localSongNameList;
	}

	private List<String> initSongArtistList(){
		localSongArtistList.add("Lykke Li");
		localSongArtistList.add("Thundercat");
		localSongArtistList.add("Mac Miller");
		localSongArtistList.add("Kanye West,Est...");
		localSongArtistList.add("Homb...");
		return localSongArtistList;
	}


	private void setAllSongsAdapter() {
		AllSongAdapter allAdapter = new AllSongAdapter(songNameList,songArtistList,songUrlList);
		RecyclerView.LayoutManager allLayoutManager = new LinearLayoutManager(getApplicationContext());
		allSongAdapter.setLayoutManager(allLayoutManager);
		allSongAdapter.setItemAnimator(new DefaultItemAnimator());
		allSongAdapter.setAdapter(allAdapter);
	}
	private void setPlaySongAdapter(){
		localSongNameList = initSongNamesList();
		localSongArtistList = initSongArtistList();
		PlaySongAdapter playAdapter = new PlaySongAdapter(localSongNameList,localSongArtistList);
		RecyclerView.LayoutManager playLayoutManager = new LinearLayoutManager(getApplicationContext());
		playSongAdapter.setLayoutManager(playLayoutManager);
		playSongAdapter.setItemAnimator(new DefaultItemAnimator());
		playSongAdapter.setAdapter(playAdapter);
	}

	@Override
	protected void onStart() {
		super.onStart();

		if (checkSelfPermission("course.examples.Services.KeyService.GEN_ID")
			!= PackageManager.PERMISSION_GRANTED) {
			ActivityCompat.requestPermissions(this,
					new String[]{"course.examples.Services.KeyService.GEN_ID"},
					PERMISSION_REQUEST);
		}
		else {
			checkBindingAndBind();
		}
	}

	protected void checkBindingAndBind() {
		if (!mIsBound) {

			boolean b = false;
			Intent i = new Intent(KeyGenerator.class.getName());
			ResolveInfo info = getPackageManager().resolveService(i, 0);
			i.setComponent(new ComponentName(info.serviceInfo.packageName, info.serviceInfo.name));

			b = bindService(i, this.mConnection, Context.BIND_AUTO_CREATE);

			if (b) {
				Log.i(TAG, "bindService successful!");
			} else {
				Log.i(TAG, "bindService unsuccessful!");
			}
		}
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		switch (requestCode) {
			case PERMISSION_REQUEST: {
				if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
					checkBindingAndBind();
				}
				else {
					Toast.makeText(this, "BUMMER: No Permission :-(", Toast.LENGTH_LONG).show() ;
				}
			}
			default: {
			}
		}
	}
	// Unbind from KeyGenerator Service
	protected void onStop() {
		super.onStop();
		super.onPause();
		if (mIsBound) {
			unbindService(this.mConnection);
		}
	}

	private final ServiceConnection mConnection = new ServiceConnection() {
		public void onServiceConnected(ComponentName className, IBinder iservice) {
			mKeyGeneratorService = KeyGenerator.Stub.asInterface(iservice);
			mIsBound = true;
		}

		public void onServiceDisconnected(ComponentName className) {
			mKeyGeneratorService = null;
			mIsBound = false;
		}
	};

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
	}
}
