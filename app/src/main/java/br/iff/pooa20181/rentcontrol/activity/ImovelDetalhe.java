package br.iff.pooa20181.rentcontrol.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.iff.pooa20181.rentcontrol.MapsActivity;
import br.iff.pooa20181.rentcontrol.R;
import br.iff.pooa20181.rentcontrol.model.Contratos;
import br.iff.pooa20181.rentcontrol.model.Imovel;
import io.realm.Realm;

public class ImovelDetalhe extends AppCompatActivity {

    EditText edNomeP, edTelP, edEmailP, edRuaI, edCidadeI, edBairroI, edNumI, edCepI, edValor;
    Button btnSalvar, btnAlterar, btnVerMapa, btnExcluir;

    br.iff.pooa20181.rentcontrol.model.Imovel imoveis;
    int id;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imovel_detalhe);


        edNomeP = (EditText) findViewById(R.id.edNomeProp);
        edTelP = (EditText) findViewById(R.id.edTelefoneProp);
        edEmailP = (EditText) findViewById(R.id.edEmailProp);
        edRuaI = (EditText) findViewById(R.id.edRuaImovel);
        edCidadeI = (EditText) findViewById(R.id.edCidadeImovel);
        edBairroI = (EditText) findViewById(R.id.edBairroImovel);
        edNumI = (EditText) findViewById(R.id.edNumeroImovel);
        edCepI = (EditText) findViewById(R.id.edCepImovel);
        edValor = (EditText) findViewById(R.id.edValorImovel);


        btnSalvar = (Button) findViewById( R.id.btnSalvar );
        btnAlterar = (Button) findViewById( R.id.btnAlterar );
        btnExcluir = (Button) findViewById( R.id.btnExcluir );
        btnVerMapa = (Button) findViewById( R.id.btnVerMapa );


        Intent intent = getIntent();
        id = (int) intent.getSerializableExtra("id");
        realm = Realm.getDefaultInstance();

        btnVerMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MapsActivity.class);
                startActivityForResult(intent, id);

            }
        });

        if(id != 0)
        {

            btnSalvar.setEnabled(false);
            btnSalvar.setClickable(false);
            btnSalvar.setVisibility( View.INVISIBLE);

            btnAlterar.setEnabled(true);
            btnAlterar.setClickable(true);
            btnAlterar.setVisibility(View.VISIBLE);

            btnVerMapa.setEnabled(true);
            btnVerMapa.setClickable(true);
            btnVerMapa.setVisibility(View.VISIBLE);


            imoveis = realm.where(Imovel.class).equalTo("id",id).findFirst();

            edNomeP.setText(imoveis.getNomeProp());
            edTelP.setText(imoveis.getTelefoneProp());
            edEmailP.setText(imoveis.getEmailProp());
            edRuaI.setText(imoveis.getRua());
            edCidadeI.setText(imoveis.getCidade());
            edNumI.setText(imoveis.getNumero());
            edNumI.setText( imoveis.getRua() );
            edCepI.setText( imoveis.getCep());
            edValor.setText( imoveis.getValor());


        } else
        {

            btnAlterar.setEnabled(false);
            btnAlterar.setClickable(false);
            btnAlterar.setVisibility(View.INVISIBLE);

            btnExcluir.setEnabled(false);
            btnExcluir.setClickable(false);
            btnExcluir.setVisibility(View.INVISIBLE);

            btnVerMapa.setEnabled(false);
            btnVerMapa.setClickable(false);
            btnVerMapa.setVisibility(View.INVISIBLE);
        }

        btnSalvar.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                salvar();
            }
        });
        btnAlterar.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                alterar();
            }
        });
        btnExcluir.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                deletar();
            }
        });

    }

    public void deletar(){
        realm.beginTransaction();
        imoveis.deleteFromRealm();
        realm.commitTransaction();
        realm.close();

        Toast.makeText(this," Imóvel deletado com sucesso!",Toast.LENGTH_LONG).show();
        this.finish();

    }

    public void salvar() {


        int proximoID = 1;
        if(realm.where(Imovel.class).max("id") !=null)
            proximoID = realm.where(Imovel.class).max("id").intValue()+1;

        realm.beginTransaction();
        Imovel imovel = new Imovel();
        imovel.setId(proximoID);
        setEGrava(imovel);

        realm.copyToRealm(imovel);
        realm.commitTransaction();
        realm.close();

        Toast.makeText(this,"Imóvel cadastrado com sucesso!",Toast.LENGTH_LONG).show();
        this.finish();

    }

    private void setEGrava(Imovel imoveis){

        imoveis.setNomeProp(edNomeP.getText().toString());
        imoveis.setEmailProp(edEmailP.getText().toString());
        imoveis.setTelefoneProp(edTelP.getText().toString());
        imoveis.setRua(edRuaI.getText().toString());
        imoveis.setCep(edCepI.getText().toString());
        imoveis.setNumero(edNumI.getText().toString());
        imoveis.setCidade(edCidadeI.getText().toString());
        imoveis.setValor(edValor.getText().toString());


    }

    public void alterar() {

        realm.beginTransaction();

        setEGrava(imoveis);

        realm.copyToRealm(imoveis);
        realm.commitTransaction();
        realm.close();

        Toast.makeText(this,"Imóvel alterado!",Toast.LENGTH_LONG).show();
        this.finish();

    }

    private Context getContext(){
        return this;
    }
}
