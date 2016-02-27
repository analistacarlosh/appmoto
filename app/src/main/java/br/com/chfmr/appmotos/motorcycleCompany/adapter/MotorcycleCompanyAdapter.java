package br.com.chfmr.appmotos.motorcycleCompany.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.chfmr.appmotos.motorcycleCompany.model.MotorcyclerCompany;
import br.com.chfmr.appmotos.motorcycleCompany.ui.motocycleCompanyListFragment;
import br.com.chfmr.appmotos.R;

/**
 * Created by carlosfmr on 5/31/15.
 */
public class MotorcycleCompanyAdapter extends RecyclerView.Adapter<MotorcycleCompanyAdapter.MotocyclerCompanyViewHolder> {

    private Activity mContext;
    private List<MotorcyclerCompany> mMotocyclerCompany;
    private onClickInListener mListener;

    public MotorcycleCompanyAdapter(motocycleCompanyListFragment ctx, List<MotorcyclerCompany> motorcyclerCompanies){
        mContext    = ctx.getActivity();
        mMotocyclerCompany = motorcyclerCompanies;
    }

    public void setOnClickInAdvertiserListener(onClickInListener l){
        mListener = l;
    }

    @Override
    public MotocyclerCompanyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        View v = LayoutInflater.from(mContext).inflate(R.layout.card_list_motorcyclercompany, parent, false);
        MotocyclerCompanyViewHolder vh = new MotocyclerCompanyViewHolder(v);

        v.setTag(vh);
        v.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    MotocyclerCompanyViewHolder vh = (MotocyclerCompanyViewHolder) view.getTag();
                    int position = vh.getAdapterPosition();
                    mListener.onClickInListener(view, position, mMotocyclerCompany.get(position));
                }
            }
        });

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
        holder.txtAddress.setText(advertiser.address + " - " + advertiser.addressDistrict);
        holder.txtTelefone.setText(advertiser.phone);
    }

    public interface onClickInListener {
        void onClickInListener(View v, int position, MotorcyclerCompany motorcyclerCompany);
    }

    public static class MotocyclerCompanyViewHolder extends RecyclerView.ViewHolder {

       public TextView txtName;
       public TextView txtAddress;
       public TextView txtTelefone;

       // Setar os atributos da view que recebera das class
       public MotocyclerCompanyViewHolder(View parent){
           super(parent);
            // receber
           txtName = (TextView)parent.findViewById(R.id.txtName);
           txtAddress = (TextView)parent.findViewById(R.id.txtAddress);
           txtTelefone = (TextView)parent.findViewById(R.id.txtTelefoneAdvertiser);
       }
    }
}
