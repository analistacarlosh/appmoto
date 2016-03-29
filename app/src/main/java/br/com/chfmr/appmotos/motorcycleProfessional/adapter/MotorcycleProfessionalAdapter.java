package br.com.chfmr.appmotos.motorcycleProfessional.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import br.com.chfmr.appmotos.R;
import br.com.chfmr.appmotos.motorcycleProfessional.model.MotorcyclerProfessional;
import br.com.chfmr.appmotos.motorcycleProfessional.ui.MotocycleProfessionalListFragment;

/**
 * Created by carlosfmr on 5/31/15.
 */
public class MotorcycleProfessionalAdapter extends RecyclerView.Adapter<MotorcycleProfessionalAdapter.MotocyclerProfessionalViewHolder> {

    private Activity mContext;
    public List<MotorcyclerProfessional> mMotocyclerProfessional;
    private onClickInListener mListener;
    private onClickInListenerBtnPhone mListenerBtnPhone;
    //private onClickInListenerBtnLocationMaps mListenerBtnLocationMaps;

    public MotorcycleProfessionalAdapter(MotocycleProfessionalListFragment ctx, List<MotorcyclerProfessional> motorcyclerProfessional){
        mContext    = ctx.getActivity();
        mMotocyclerProfessional = motorcyclerProfessional;
    }

    public void setOnClickInListenerMotocyclerProfessional(onClickInListener l){
        mListener = l;
    }

    public void setOnClickInListenerMotocyclerProfessionalBtnPhone(onClickInListenerBtnPhone l){
        mListenerBtnPhone = l;
    }

    @Override
    public MotocyclerProfessionalViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        View v = LayoutInflater.from(mContext).inflate(R.layout.card_list_motorcyclerprofessional, parent, false);
        MotocyclerProfessionalViewHolder vh = new MotocyclerProfessionalViewHolder(v);

        v.setTag(vh);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    MotocyclerProfessionalViewHolder vh = (MotocyclerProfessionalViewHolder) view.getTag();
                    int position = vh.getAdapterPosition();
                    mListener.onClickInListener(view, position, mMotocyclerProfessional.get(position));
                }
            }
        });

        Button btnCallTelPhoneMotorcyclerCompany = (Button) v.findViewById(R.id.btnCallTelPhoneMotorcyclerCompany);
        btnCallTelPhoneMotorcyclerCompany.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  if (mListenerBtnPhone != null) {
                       int positionV = (int) view.getTag();
                      mListenerBtnPhone.onClickInListenerBtnPhone(view, positionV, mMotocyclerProfessional.get(positionV));
                  }
              }
          });

        return vh;
    }

    @Override
    public int getItemCount(){
        return mMotocyclerProfessional != null ? mMotocyclerProfessional.size() : 0;
    }

    @Override
    public void onBindViewHolder(MotocyclerProfessionalViewHolder holder, int position){

        MotorcyclerProfessional advertiser = mMotocyclerProfessional.get(position);
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
        void onClickInListener(View v, int position, MotorcyclerProfessional motorcyclerProfessional);
    }

    public interface onClickInListenerBtnPhone {
        void onClickInListenerBtnPhone(View v, int position, MotorcyclerProfessional motorcyclerProfessional);
    }

    public static class MotocyclerProfessionalViewHolder extends RecyclerView.ViewHolder
    {
       public TextView txtName;
       public TextView txtAddress;
        public Button btnCallTelPhoneMotorcyclerCompany;

       // Setar os atributos da view que recebera das class
       public MotocyclerProfessionalViewHolder(View parent){
           super(parent);
            // receber
           txtName = (TextView)parent.findViewById(R.id.txtName);
           txtAddress = (TextView)parent.findViewById(R.id.txtAddress);
           btnCallTelPhoneMotorcyclerCompany = (Button)parent.findViewById(R.id.btnCallTelPhoneMotorcyclerCompany);
       }
    }
}
