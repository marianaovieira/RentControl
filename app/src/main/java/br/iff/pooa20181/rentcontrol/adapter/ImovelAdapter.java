package br.iff.pooa20181.rentcontrol.adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.iff.pooa20181.rentcontrol.R;
import br.iff.pooa20181.rentcontrol.model.Imovel;

public class ImovelAdapter extends RecyclerView.Adapter {

    private List<Imovel> imoveis;
    private Context context;
    static ClickRecyclerViewListener clickRecyclerViewListener;


    public ImovelAdapter(List<Imovel> imoveis, Context context, ClickRecyclerViewListener clickRecyclerViewListener) {

        this.imoveis = imoveis;
        this.context = context;
        ImovelAdapter.clickRecyclerViewListener = clickRecyclerViewListener;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from( context )
                .inflate( R.layout.activity_item_imovel, parent, false );
        ImoveisViewHolder imoveisViewHolder = new ImoveisViewHolder( view );
        return imoveisViewHolder;


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        ImoveisViewHolder imoveisHolder = (ImoveisViewHolder) viewHolder;
        Imovel imoveis = this.imoveis.get( position );


        imoveisHolder.nomeProprietario.setText( imoveis.getNomeProp() );
        imoveisHolder.valorAluguel.setText( imoveis.getValor() );
        imoveisHolder.cepImovel.setText( imoveis.getCep() );
    }

    @Override
    public int getItemCount() {
        return imoveis.size();
    }

    public class ImoveisViewHolder extends RecyclerView.ViewHolder {

        private final TextView nomeProprietario;
        private final TextView valorAluguel;
        private final TextView cepImovel;

        public ImoveisViewHolder(View itemView) {

            super( itemView );

            nomeProprietario = (TextView) itemView.findViewById( R.id.tvNomeProprietário);
            valorAluguel = (TextView) itemView.findViewById( R.id.tvNomeLocatario);
            cepImovel = (TextView) itemView.findViewById( R.id.tvCepImovel );

            itemView.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickRecyclerViewListener.onClick( imoveis.get( getLayoutPosition() ) );

                }
            } );

        }
    }

}
