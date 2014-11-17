package info.androidhive.slidingmenu;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

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
	JSONArray arrayObj=null;
	private ImageButton btnShuffle;
	private int i=-1; 
	public FindPeopleFragment(){}
	MediaPlayer player = new MediaPlayer();
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_find_people, container, false);
        
		btnPlay = ((ImageButton)rootView.findViewById(R.id.btnPlay));
		btnPlay.setOnClickListener(this);
		btnForward = ((ImageButton)rootView.findViewById(R.id.btnForward));
		btnForward.setOnClickListener(this);
		btnBackward = ((ImageButton)rootView.findViewById(R.id.btnBackward));
		btnBackward.setOnClickListener(this);
		 i = this.getArguments().getInt("position");
		Play(i);
		System.out.println(i);
		System.out.println("iiiiiiiiiiiiiiiiiz");
		
        return rootView;
    }
	
	public int getelement(int i){
		System.out.println(i);
		return 5;
	}

	public void Play(int index) {
		System.out.println(index);
		System.out.println("indexxx");
		 try {
		    player.setAudioStreamType(AudioManager.STREAM_MUSIC);
		    JSONArray array_ojbect=Get_Json_Objects();
		    JSONObject obj =(JSONObject) array_ojbect.get(index);
		    Object url=obj.get("url");
	         Object title=obj.get("titre");
	         System.out.println(url);
	         System.out.println("urrrrrrrrrrrrr");
	         player.reset();
		    player.setDataSource(url.toString()
		            );
		    player.prepare();
		    
		    if(player.isPlaying()){
		    	System.out.println("isplayingg");
				if(player!=null){
					player.pause();
					// Changing button image to play button
					btnPlay.setImageResource(R.drawable.btn_play);
				}
			}else{
				// Resume song
				System.out.println("not playing");
				if(player!=null){
					Log.e("stat","not null");
					player.start();
					// Changing button image to pause buttonl
					btnPlay.setImageResource(R.drawable.btn_pause);
				}
			}
			    Log.e("start","start");
			} catch (Exception e) {
			    // TODO: handle exception
				System.out.println("messassge"+e.getMessage());
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
	public void forward(int index){
		player.release();
		player = new MediaPlayer();
		 try {
			    player.setAudioStreamType(AudioManager.STREAM_MUSIC);
			    JSONArray array_ojbect=Get_Json_Objects();
			    JSONObject obj =(JSONObject) array_ojbect.get(index);
			    Object url=obj.get("url");
		         Object title=obj.get("titre");
		         System.out.println(url);
		         System.out.println("urrrrrrrrrrrrr");
		         player.reset();
			    player.setDataSource(url.toString()
			            );
			    player.prepare();
			    player.start();
				// Changing button image to pause buttonl
				btnPlay.setImageResource(R.drawable.btn_pause);
		 } catch (Exception e) {
			    // TODO: handle exception
				System.out.println("messassge"+e.getMessage());
			}
	}
	
	@Override
	 public void onDestroy(){
	 super.onDestroy();
	    player.release();
	 }
	@Override
	public void onClick(View v) {
		int ele=v.getId();
		if (ele==R.id.btnPlay){
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
	  }else{
		  if(ele==R.id.btnForward){
			  System.out.println("forwarddddd");
			  System.out.println(i);
			  if(i!=-1){
				  System.out.println("forwarddddd");
				  //onDestroy();
				  
				  i=i+1;
			  forward(i);
			  }
			  
		  }else{
			  if(ele==R.id.btnBackward){
				  if(i!=-1){
					  if(i==0){
						  forward(i);
					  }else{
						  i=i-1;
						  forward(i);  
					  }
				  
				  }
				  
			  }
		  }
		  
	  }
		
	}

	public  JSONArray Get_Json_Objects(){
		JSONParser parser = new JSONParser();
        try {
        	
        	InputStream inputStream = getResources().openRawResource(R.raw.file);
        	ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int i;
            i = inputStream.read();
            while (i != -1)
             {
              byteArrayOutputStream.write(i);
              i = inputStream.read();
             }
             inputStream.close();
           //System.out.println(byteArrayOutputStream.toString());
           String text=byteArrayOutputStream.toString();
           String data="["+text+"]";
           Object object=null;
           
           JSONParser jsonParser=new JSONParser();
           object=jsonParser.parse(data);
           arrayObj=(JSONArray) object;
           //JSONObject obj =(JSONObject) arrayObj.get(1);
           //Object d=obj.get("url");
           
           //String d=node.getString(KK);
           //System.out.println("Json object :: "+d);
           
           //JSONObject jsonObj = new JSONObject(text);
           //System.out.println(jsonObj.len);
        } catch (Exception e) {
            e.printStackTrace();
         
        }
        return arrayObj;
	}

}
