package br.com.chfmr.appmotos.motorcycleCompany.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.chfmr.appmotos.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class motocycleCompanyMapsFragment extends Fragment {


    public motocycleCompanyMapsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        textView.setText(R.string.hello_blank_fragment);
        return textView;
    }


}
