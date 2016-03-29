package br.com.chfmr.appmotos.motorcycleProfessional.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.com.chfmr.appmotos.R;
import br.com.chfmr.appmotos.commonApp.network.AppHttp;
import br.com.chfmr.appmotos.motorcycleProfessional.adapter.MotorcycleProfessionalAdapter;
import br.com.chfmr.appmotos.motorcycleProfessional.model.MotorcyclerProfessional;

/**
 * A simple {@link Fragment} subclass.
 */
public class MotocycleProfessionalListFragment extends Fragment
    implements MotorcycleProfessionalAdapter.onClickInListener,
        MotorcycleProfessionalAdapter.onClickInListenerBtnPhone {

    private String TAG = MotocycleProfessionalListFragment.class.getSimpleName();
    RecyclerView mRecyclerMotocyclerProfessionalView;
    private MotorcycleProfessionalAdapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;

    List<MotorcyclerProfessional> listMotorcyclerProfessional;
    private  ListMotorcyclerProfessionalTask mListMotorcyclerProfessionalTask;
    boolean mIsRunning;

    TextView mTextMessage;
    ProgressBar mProgressBar;

    public MotocycleProfessionalListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_motocycle_professional_list, container, false);

        mTextMessage = (TextView)layout.findViewById(android.R.id.empty);
        mProgressBar = (ProgressBar)layout.findViewById(R.id.progressBar);

        mRecyclerMotocyclerProfessionalView = (RecyclerView) layout.findViewById(R.id.recyclerViewListAdvertiser);
        mRecyclerMotocyclerProfessionalView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this.getActivity());

        return layout;
    }

    @Override
    public void onActivityCreated(Bundle saveInstanceState){
        super.onActivityCreated(saveInstanceState);
        initView();
        Log.i(TAG, "onResume");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.i(TAG, "onResume");
        initView();
    }

    private void initView(){

        if (AppHttp.hasConect(this.getActivity()) == false) {
            Toast.makeText(this.getActivity(), "Sem conexão com internet", Toast.LENGTH_LONG).show();
        } else {

            mRecyclerMotocyclerProfessionalView.setLayoutManager(mLayoutManager);
            mRecyclerMotocyclerProfessionalView.setItemAnimator(new DefaultItemAnimator());

            if(mListMotorcyclerProfessionalTask == null  || (mListMotorcyclerProfessionalTask.getStatus() != AsyncTask.Status.RUNNING && mListMotorcyclerProfessionalTask.getStatus() != AsyncTask.Status.PENDING)){
                startingDownload();
            } else if (mListMotorcyclerProfessionalTask.getStatus() == AsyncTask.Status.RUNNING){
                showProgress(true);
            }
        }

    }

    private void showProgress(boolean show){

        if(show == true){
            mTextMessage.setText("Baixando informações...");
        }

        mTextMessage.setVisibility(show ? View.VISIBLE : View.GONE);
        mProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    public void startingDownload(){

        if(mListMotorcyclerProfessionalTask == null || mListMotorcyclerProfessionalTask.getStatus() != AsyncTask.Status.RUNNING){
            mListMotorcyclerProfessionalTask = new ListMotorcyclerProfessionalTask();
            mListMotorcyclerProfessionalTask.execute();
        }
    }

    @Override
    public void onClickInListener(View v, int position, MotorcyclerProfessional motorcyclerProfessional) {

        Log.i(TAG, "onClickInCategory - position:" + position);
        Toast.makeText(MotocycleProfessionalListFragment.this.getActivity(), "onClickInListener-" + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClickInListenerBtnPhone(View v, int position, MotorcyclerProfessional motorcyclerProfessional) {

        Uri uri = Uri.parse("tel:" + motorcyclerProfessional.phone);
        Intent intent = new Intent(Intent.ACTION_DIAL, uri);
        startActivity(intent);
    }


    class ListMotorcyclerProfessionalTask extends AsyncTask<String, Void, List<MotorcyclerProfessional>> {

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            showProgress(true);
        }

        @Override
        protected List<MotorcyclerProfessional> doInBackground(String... arg){
            return listMotorcyclerProfessional = MotorcyclerProfessional.searchMotorcyclerProfessionalJson();
        }

        @Override
        protected void onPostExecute(List<MotorcyclerProfessional> listMotorcyclerProfessional){

            super.onPostExecute(listMotorcyclerProfessional);
            Log.i(TAG, "listMotorcyclerProfessional - " + listMotorcyclerProfessional);
            if(listMotorcyclerProfessional != null){
                mAdapter = new MotorcycleProfessionalAdapter(MotocycleProfessionalListFragment.this, listMotorcyclerProfessional);
                mAdapter.setOnClickInListenerMotocyclerProfessional(MotocycleProfessionalListFragment.this);
                mAdapter.setOnClickInListenerMotocyclerProfessionalBtnPhone(MotocycleProfessionalListFragment.this);
                //mAdapter.setOnClickInListenerBtnLocationMaps(motocycleCompanyListFragment.this);

                mRecyclerMotocyclerProfessionalView.setAdapter(mAdapter);
            } else {
                Toast.makeText(MotocycleProfessionalListFragment.this.getActivity(), "Não foi possível objter a lista de Moto-táxi", Toast.LENGTH_LONG).show();
            }
            showProgress(false);
        }
    }
}
