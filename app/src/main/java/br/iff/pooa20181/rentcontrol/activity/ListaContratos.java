package br.iff.pooa20181.rentcontrol.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;


import java.util.List;

import br.iff.pooa20181.rentcontrol.R;
import br.iff.pooa20181.rentcontrol.adapter.ClickRecyclerViewListener;
import br.iff.pooa20181.rentcontrol.adapter.ContratosAdapter;
import br.iff.pooa20181.rentcontrol.model.Contratos;
import io.realm.Realm;

public class ListaContratos extends AppCompatActivity implements ClickRecyclerViewListener {

    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_lista_contratos );
        Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );

        realm = Realm.getDefaultInstance();

        FloatingActionButton fab = (FloatingActionButton) findViewById( R.id.fab );
        fab.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListaContratos.this, ContratoDetalhe.class);
                intent.putExtra("id",0);
                startActivity(intent);
            }
        } );



    }

    protected void onResume() {

        super.onResume();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.lista_contratos);

        recyclerView.setAdapter(new ContratosAdapter(getContratos(),this,this));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public List<Contratos> getContratos(){

        return (List) realm.where(Contratos.class).findAll();

    }

    @Override
    public void onClick(Object object) {
        Contratos contratos = (Contratos) object;
        Intent intent = new Intent(ListaContratos.this,ContratoDetalhe.class);
        intent.putExtra("id",contratos.getId());
        startActivity(intent);
    }



    public void finish(){
        super.finish();
        realm.close();
    }


}
