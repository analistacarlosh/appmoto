package br.com.chfmr.appmotos.engagementApp.ui;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.chfmr.appmotos.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class EngagementActivityFragment extends Fragment {

    public EngagementActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_engagement, container, false);
    }
}
