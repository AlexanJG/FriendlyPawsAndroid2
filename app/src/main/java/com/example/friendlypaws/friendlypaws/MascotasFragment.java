package com.example.friendlypaws.friendlypaws;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MascotasFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MascotasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MascotasFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mascotas, container, false);
    }
}
