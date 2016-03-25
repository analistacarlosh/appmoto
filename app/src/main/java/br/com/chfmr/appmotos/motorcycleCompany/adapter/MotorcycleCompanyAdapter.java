package br.com.chfmr.appmotos.motorcycleCompany.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import br.com.chfmr.appmotos.R;
import br.com.chfmr.appmotos.motorcycleCompany.model.MotorcyclerCompany;
import br.com.chfmr.appmotos.motorcycleCompany.ui.motocycleCompanyListFragment;

/**
 * Created by carlosfmr on 5/31/15.
 */
public class MotorcycleCompanyAdapter extends RecyclerView.Adapter<MotorcycleCompanyAdapter.MotocyclerCompanyViewHolder> {

    private Activity mContext;
    public List<MotorcyclerCompany> mMotocyclerCompany;
    private onClickInListener mListener;
    private onClickInListenerBtnPhone mListenerBtnPhone;
    //private onClickInListenerBtnLocationMaps mListenerBtnLocationMaps;

    public MotorcycleCompanyAdapter(motocycleCompanyListFragment ctx, List<MotorcyclerCompany> motorcyclerCompanies){
        mContext    = ctx.getActivity();
        mMotocyclerCompany = motorcyclerCompanies;
    }

    public void setOnClickInListenerMotocycleCompany(onClickInListener l){
        mListener = l;
    }

    public void setOnClickInListenerMotocycleCompanyBtnPhone(onClickInListenerBtnPhone l){
        mListenerBtnPhone = l;
    }

    /*public void setOnClickInListenerBtnLocationMaps(onClickInListenerBtnLocationMaps l){
        mListenerBtnLocationMaps = l;
    }*/

    @Override
    public MotocyclerCompanyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        View v = LayoutInflater.from(mContext).inflate(R.layout.card_list_motorcyclercompany, parent, false);
        MotocyclerCompanyViewHolder vh = new MotocyclerCompanyViewHolder(v);

        v.setTag(vh);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    MotocyclerCompanyViewHolder vh = (MotocyclerCompanyViewHolder) view.getTag();
                    int position = vh.getAdapterPosition();
                    mListener.onClickInListener(view, position, mMotocyclerCompany.get(position));
                }
            }
        });

        Button btnCallTelPhoneMotorcyclerCompany = (Button) v.findViewById(R.id.btnCallTelPhoneMotorcyclerCompany);
        btnCallTelPhoneMotorcyclerCompany.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  if (mListenerBtnPhone != null) {
                       int positionV = (int) view.getTag();
                      mListenerBtnPhone.onClickInListenerBtnPhone(view, positionV, mMotocyclerCompany.get(positionV));
                  }
              }
          });

        /*Button btnOpenMapsLocationMotoCyclerCompany = (Button) v.findViewById(R.id.btnMapsLocationMotoCyclerCompany);
        btnOpenMapsLocationMotoCyclerCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListenerBtnLocationMaps != null) {
                    int positionV = (int) view.getTag();
                    mListenerBtnLocationMaps.onClickInListenerBtnLocationMaps(view, positionV, mMotocyclerCompany.get(positionV));
                }
            }
        });
        */

        return vh;
    }

    @Override
    public int getItemCount(){
        return mMotocyclerCompany != null ? mMotocyclerCompany.size() : 0;
    }

    @Override
    public void onBindViewHolder(MotocyclerCompanyViewHolder holder, int position){

        MotorcyclerCompany advertiser = mMotocyclerCompany.get(position);
        // Picasso.with(mContext).load(category.img).into(holder.imgCategory);
        holder.txtName.setText(advertiser.nameMotoCompany);
        holder.txtAddress.setText(advertiser.addressDistrict + " - " + advertiser.address);
        //holder.txtTelefone.setText(advertiser.phone);
        holder.btnCallTelPhoneMotorcyclerCompany.setTag(position);
//        holder.btnMapsLocationMotoCyclerCompany.setTag(position);
        //holder.btnNxt.setTag(position)

       // holder.imageButtonMotocycleCompany.setOnClickListener(this);
        /*holder.imageButtonMotocycleCompany.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                /// button click event
                Log.i("APP", "onClickInCategory - position:");
            }
        });*/
    }

    public interface onClickInListener {
        void onClickInListener(View v, int position, MotorcyclerCompany motorcyclerCompany);
    }

    public interface onClickInListenerBtnPhone {
        void onClickInListenerBtnPhone(View v, int position, MotorcyclerCompany motorcyclerCompany);
    }

    /*public interface onClickInListenerBtnLocationMaps {
        void onClickInListenerBtnLocationMaps(View v, int position, MotorcyclerCompany motorcyclerCompany);
    }*/

    public static class MotocyclerCompanyViewHolder extends RecyclerView.ViewHolder
    {
       public TextView txtName;
       public TextView txtAddress;
        public Button btnCallTelPhoneMotorcyclerCompany;
        public Button btnMapsLocationMotoCyclerCompany;

       // Setar os atributos da view que recebera das class
       public MotocyclerCompanyViewHolder(View parent){
           super(parent);
            // receber
           txtName = (TextView)parent.findViewById(R.id.txtName);
           txtAddress = (TextView)parent.findViewById(R.id.txtAddress);
           btnCallTelPhoneMotorcyclerCompany = (Button)parent.findViewById(R.id.btnCallTelPhoneMotorcyclerCompany);
          // btnMapsLocationMotoCyclerCompany = (Button)parent.findViewById(R.id.btnMapsLocationMotoCyclerCompany);
       }
    }
}
