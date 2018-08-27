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
import br.iff.pooa20181.rentcontrol.adapter.ImovelAdapter;
import br.iff.pooa20181.rentcontrol.model.Imovel;
import io.realm.Realm;

public class ListaImoveis extends AppCompatActivity implements ClickRecyclerViewListener {

    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_contratos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        realm = Realm.getDefaultInstance();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListaImoveis.this, ImovelDetalhe.class);
                intent.putExtra("id", 0);
                startActivity(intent);
            }
        });


    }

    protected void onResume() {

        super.onResume();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.lista_imoveis);
        //recyclerView.setAdapter(new ImovelAdapter(getImoveis(),this,this));
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    public List<Imovel> getImoveis(){

        return (List) realm.where(Imovel.class).findAll();

    }

    @Override
    public void onClick(Object object) {
        br.iff.pooa20181.rentcontrol.model.Imovel imoveis = (br.iff.pooa20181.rentcontrol.model.Imovel) object;
        Intent intent = new Intent(ListaImoveis.this,ImovelDetalhe.class);
        intent.putExtra("id",imoveis.getId());
        startActivity(intent);
    }



    public void finish(){
        super.finish();
        realm.close();
    }

}