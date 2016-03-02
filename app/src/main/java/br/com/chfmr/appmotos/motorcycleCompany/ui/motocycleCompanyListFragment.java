package br.com.chfmr.appmotos.motorcycleCompany.ui;


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
import br.com.chfmr.appmotos.motorcycleCompany.adapter.MotorcycleCompanyAdapter;
import br.com.chfmr.appmotos.motorcycleCompany.model.MotorcyclerCompany;

/**
 * A simple {@link Fragment} subclass.
 */
public class motocycleCompanyListFragment extends Fragment
        implements MotorcycleCompanyAdapter.onClickInListener,
        MotorcycleCompanyAdapter.onClickInListenerBtnPhone{

    private String TAG = motocycleCompanyListFragment.class.getSimpleName();
    RecyclerView mRecyclerMotocyclerCompanyView;
    private MotorcycleCompanyAdapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;

    List<MotorcyclerCompany> listMotorcyclerCompany;
    private  ListMotorcyclerCompanyTask mListMotorcyclerCompanyTask;
    boolean mIsRunning;

    TextView mTextMessage;
    ProgressBar mProgressBar;

    public motocycleCompanyListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_motocycle_company_list, container, false);

        mTextMessage = (TextView)layout.findViewById(android.R.id.empty);
        mProgressBar = (ProgressBar)layout.findViewById(R.id.progressBar);

        mRecyclerMotocyclerCompanyView = (RecyclerView) layout.findViewById(R.id.recyclerViewListAdvertiser);
        mRecyclerMotocyclerCompanyView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this.getActivity());
        //mRecyclerMotocyclerCompanyView.addOnChildAttachStateChangeListener();

        /*recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(context, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
        */
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

        /*
            Intent intent  = getActivity().getIntent();
            friendlyUrl = intent.getStringExtra("friendlyUrl");
            friendlyUrl = "/advertiser";
        */

        /*
        if(friendlyUrl.isEmpty()){
            Toast.makeText(this.getActivity(), "Não foi possível obter a listagem", Toast.LENGTH_LONG).show();
        } else
        */

        if (AppHttp.hasConect(this.getActivity()) == false) {
            Toast.makeText(this.getActivity(), "Sem conexão com internet", Toast.LENGTH_LONG).show();
        } else {

            mRecyclerMotocyclerCompanyView.setLayoutManager(mLayoutManager);
            mRecyclerMotocyclerCompanyView.setItemAnimator(new DefaultItemAnimator());

            if(mListMotorcyclerCompanyTask == null  || (mListMotorcyclerCompanyTask.getStatus() != AsyncTask.Status.RUNNING && mListMotorcyclerCompanyTask.getStatus() != AsyncTask.Status.PENDING)){
                startingDownload();
            } else if (mListMotorcyclerCompanyTask.getStatus() == AsyncTask.Status.RUNNING){
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

        if(mListMotorcyclerCompanyTask == null || mListMotorcyclerCompanyTask.getStatus() != AsyncTask.Status.RUNNING){
            mListMotorcyclerCompanyTask = new ListMotorcyclerCompanyTask();
            mListMotorcyclerCompanyTask.execute();
        }
    }

    @Override
    public void onClickInListener(View v, int position, MotorcyclerCompany motorcyclerCompany) {

        Log.i(TAG, "onClickInCategory - position:" + position);

        /*
        friendlyUrl = listCategory.get(position).friendlyUrl;
        Intent intent = new Intent(this.getActivity(), ListAdvertiserActivity.class);
        intent.putExtra("friendlyUrl", friendlyUrl);
        startActivity(intent);
        */

        Toast.makeText(motocycleCompanyListFragment.this.getActivity(), "onClickInListener-" + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClickInListenerBtnPhone(View v, int position, MotorcyclerCompany motorcyclerCompany) {

        //Log.i(TAG, "onClickInListenerBtnPhone - position:" + position);
        Toast.makeText(motocycleCompanyListFragment.this.getActivity(), "onClickInListenerBtnPhone-" + motorcyclerCompany.phone, Toast.LENGTH_LONG).show();
    }

    class ListMotorcyclerCompanyTask extends AsyncTask<String, Void, List<MotorcyclerCompany>> {

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            showProgress(true);
        }

        @Override
        protected List<MotorcyclerCompany> doInBackground(String... arg){
            return listMotorcyclerCompany = MotorcyclerCompany.searchMotorcyclerCompanyJson();
        }

        @Override
        protected void onPostExecute(List<MotorcyclerCompany> listMotorcyclerCompany){

            super.onPostExecute(listMotorcyclerCompany);
            Log.i("APPGUIA", "categoryList - " + listMotorcyclerCompany);
            if(listMotorcyclerCompany != null){
                mAdapter = new MotorcycleCompanyAdapter(motocycleCompanyListFragment.this, listMotorcyclerCompany);
                mAdapter.setOnClickInListenerMotocycleCompany(motocycleCompanyListFragment.this);
                mAdapter.setOnClickInListenerMotocycleCompanyBtnPhone(motocycleCompanyListFragment.this);

                mRecyclerMotocyclerCompanyView.setAdapter(mAdapter);
            } else {
                Toast.makeText(motocycleCompanyListFragment.this.getActivity(), "Não foi possível objter a lista de Moto-táxi", Toast.LENGTH_LONG).show();
            }
            showProgress(false);
        }
    }
}
