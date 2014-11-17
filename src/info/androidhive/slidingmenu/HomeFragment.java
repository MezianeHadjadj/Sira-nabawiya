package info.androidhive.slidingmenu;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class HomeFragment extends Fragment {
	
	static final String KK = "titre";
	JSONArray arrayObj=null;
	ListView list;
    LazyAdapter adapter;
     
	public HomeFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        //getcode();
        //adapt listview
        ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();
        JSONArray array_ojbect=Get_Json_Objects();
        System.out.println(array_ojbect.size());
        Log.e("lll","llllllllll");
		for (int i = 0; i <array_ojbect.size() ; i++) {
			
			JSONObject obj =(JSONObject) array_ojbect.get(i);
	         Object url=obj.get("url");
	         Object title=obj.get("titre");
			// creating new HashMap
			HashMap<String, String> map = new HashMap<String, String>();	
			// adding each child node to HashMap key => value
			map.put(Model.KEY_ID, Model.KEY_ID);
			map.put(Model.KEY_TITLE, title.toString());
			map.put(Model.KEY_ARTIST, "lll");
			map.put(Model.KEY_DURATION, Model.KEY_DURATION);
			map.put(Model.KEY_THUMB_URL,url.toString());

			// adding HashList to ArrayList
			songsList.add(map);
		}
		
		Log.e("dddddd","dddddddddddd");
		//View V = inflater.inflate(R.layout.fragment_home, container, false);
		list=(ListView)rootView.findViewById(R.id.list);
		// Getting adapter by passing xml data ArrayList
        adapter=new LazyAdapter(getActivity(), songsList);        
        list.setAdapter(adapter);
        

        // Click event for single list row
        list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
							System.out.println("zzzzzzzzzzzzzzzz");
							start(position);

			}

			
		});	
		

        return rootView;
    }
	public void start(int position){
		Fragment fragment = null;
		
		fragment = new FindPeopleFragment();
		FragmentManager fragmentManager = getFragmentManager();
		//fragmentManager.beginTransaction().replace(R.id.frame_container, fragment);
		android.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
		//pass params to fragment
		 Bundle bundle = new Bundle();
	        bundle.putInt("position", position);
	        //FragmentClass fragInfo = new FragmentClass();
	        fragment.setArguments(bundle);
	        
		transaction.replace(R.id.frame_container, fragment);
		transaction.addToBackStack(null);
		//fragment.getActivity().getelement(5);
		
		// Commit the transactionddd
		transaction.commit();
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
