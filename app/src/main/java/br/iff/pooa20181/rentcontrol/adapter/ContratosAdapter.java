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
import br.iff.pooa20181.rentcontrol.model.Contratos;

public class ContratosAdapter extends RecyclerView.Adapter {

    private List<Contratos> contratos;
    private Context context;
    static ClickRecyclerViewListener clickRecyclerViewListener;


    public ContratosAdapter(List<Contratos> contratos, Context context, ClickRecyclerViewListener clickRecyclerViewListener) {

        this.contratos = contratos;
        this.context = context;
        ContratosAdapter.clickRecyclerViewListener = clickRecyclerViewListener;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from( context )
                .inflate( R.layout.activity_item_contratos, parent, false );
        ContratosViewHolder contratosViewHolder = new ContratosViewHolder( view );
        return contratosViewHolder;


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        ContratosViewHolder contratosHolder = (ContratosViewHolder) viewHolder;
        Contratos contratos = this.contratos.get( position );


        contratosHolder.nomeLocador.setText( contratos.getNome_locador() );
        contratosHolder.nomeLocatario.setText( contratos.getNome_locatario() );
        contratosHolder.valorAluguel.setText( contratos.getValor_aluguel() );
        contratosHolder.cepImovel.setText( contratos.getCep() );


    }

    @Override
    public int getItemCount() {
        return contratos.size();
    }

    public class ContratosViewHolder extends RecyclerView.ViewHolder {

        private final TextView nomeLocador;
        private final TextView nomeLocatario;
        private final TextView valorAluguel;
        private final TextView cepImovel;

        public ContratosViewHolder(View itemView) {

            super( itemView );

            nomeLocador = (TextView) itemView.findViewById( R.id.tvNomeProprietário);
            nomeLocatario = (TextView) itemView.findViewById( R.id.tvNomeLocatario);
            valorAluguel = (TextView) itemView.findViewById( R.id.tvNomeLocatario);
            cepImovel = (TextView) itemView.findViewById( R.id.tvCepImovel );

            itemView.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickRecyclerViewListener.onClick( contratos.get( getLayoutPosition() ) );

                }
            } );

        }
    }
}
