package info.androidhive.slidingmenu;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FindPeopleFragment extends Fragment {
	
	public FindPeopleFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		int i = this.getArguments().getInt("position");
		System.out.println(i);
        View rootView = inflater.inflate(R.layout.fragment_find_people, container, false);
         
        return rootView;
    }
	public int getelement(int i){
		System.out.println(i);
		return 5;
	}
}
