package info.androidhive.slidingmenu;

import android.app.Fragment;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class FindPeopleFragment extends Fragment implements OnClickListener {
	private ImageButton btnPlay;
	private ImageButton btnForward;
	private ImageButton btnBackward;
	private ImageButton btnNext;
	private ImageButton btnPrevious;
	private ImageButton btnPlaylist;
	private ImageButton btnRepeat;
	private ImageButton btnShuffle;
	public FindPeopleFragment(){}
	MediaPlayer player = new MediaPlayer();
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_find_people, container, false);
        
		btnPlay = ((ImageButton)rootView.findViewById(R.id.btnPlay));
		btnPlay.setOnClickListener(this);
		int i = this.getArguments().getInt("position");
		Play(i);
		
		
        return rootView;
    }
	public int getelement(int i){
		System.out.println(i);
		return 5;
	}

	public void Play(int index) {
		 try {
		    player.setAudioStreamType(AudioManager.STREAM_MUSIC);
		    player.setDataSource("http://www.archive.org/download/sira_nabawya_toumiat/03.mp3"
		            );
		    player.prepare();
		    
		    if(player.isPlaying()){
				if(player!=null){
					player.pause();
					// Changing button image to play button
					btnPlay.setImageResource(R.drawable.btn_play);
				}
			}else{
				// Resume song
				Log.e("ll","lm");
				if(player!=null){
					Log.e("stat","not null");
					player.start();
					// Changing button image to pause button
					btnPlay.setImageResource(R.drawable.btn_pause);
				}
			}
			    Log.e("start","start");
			} catch (Exception e) {
			    // TODO: handle exception
			}
		 
		Log.e("command","Play");
		// check for already playing
	/*	if(mp.isPlaying()){
			if(mp!=null){
				mp.pause();
				// Changing button image to play button
				btnPlay.setImageResource(R.drawable.btn_play);
			}
		}else{
			// Resume song
			if(mp!=null){
				mp.start();
				// Changing button image to pause button
				btnPlay.setImageResource(R.drawable.btn_pause);
			}
		*/
	
	}

	@Override
	public void onClick(View v) {
		int i=v.getId();
		if (i==R.id.btnPlay){
			if(player.isPlaying()){
				if(player!=null){
					player.pause();
					// Changing button image to play button
					btnPlay.setImageResource(R.drawable.btn_play);
				}			
		}else{
			// Resume song
			Log.e("ll","lm");
			if(player!=null){
				Log.e("stat","not null");
				player.start();
				// Changing button image to pause button
				btnPlay.setImageResource(R.drawable.btn_pause);
			}
		}
	  }
	}
}
