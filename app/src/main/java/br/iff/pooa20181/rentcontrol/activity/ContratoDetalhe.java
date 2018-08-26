package br.iff.pooa20181.rentcontrol.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import br.iff.pooa20181.rentcontrol.MapsActivity;
import br.iff.pooa20181.rentcontrol.R;
import br.iff.pooa20181.rentcontrol.model.Contratos;
import io.realm.Realm;

public class ContratoDetalhe extends AppCompatActivity {

    EditText edNomeLocador, edEmailLocador, edTelefoneLocador, edNomeLocatario, edEmailLocatario, edTelefoneLocatario, edRua, edNumero, edCep, edCidade, edValor;
    Button btnSalvar, btnAlterar, btnExcluir, btnVerMapa;

    int id;
    Contratos contratos;
    private Realm realm;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_contrato_detalhe );

        edNomeLocador = (EditText) findViewById( R.id.txtNomeLocador );
        edEmailLocador = (EditText) findViewById( R.id.txtEmailLocador );
        edTelefoneLocador = (EditText) findViewById( R.id.txtTelefoneLocador );
        edNomeLocatario = (EditText) findViewById( R.id.txtNomeLocatario );
        edEmailLocatario = (EditText) findViewById( R.id.txtEmailLocatario );
        edTelefoneLocatario = (EditText) findViewById( R.id.txtTelefoneLocatario );
        edRua = (EditText) findViewById( R.id.txtRuaImovel );
        edNumero = (EditText) findViewById( R.id.txtNumeroImovel );
        edCep = (EditText) findViewById( R.id.txtCepImovel );
        edCidade = (EditText) findViewById( R.id.txtCidade );
        edValor = (EditText) findViewById( R.id.txtValor ) ;

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
                startActivity(intent);

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


            contratos = realm.where(Contratos.class).equalTo("id",id).findFirst();

            edNomeLocador.setText(contratos.getNome_locador());
            edEmailLocador.setText(contratos.getEmail_locador());
            edTelefoneLocador.setText(contratos.getTelefone_locador());
            edNomeLocatario.setText(contratos.getNome_locatario());
            edEmailLocatario.setText(contratos.getEmail_locatario());
            edTelefoneLocatario.setText(contratos.getTelefone_locatario());
            edRua.setText( contratos.getRua() );
            edCep.setText( contratos.getCep());
            edNumero.setText( contratos.getNumero() );
            edValor.setText( contratos.getValor_aluguel() );
            edCidade.setText(contratos.getCidade());

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



    }public void deletar(){
        realm.beginTransaction();
        contratos.deleteFromRealm();
        realm.commitTransaction();
        realm.close();

        Toast.makeText(this," Contrato deletado com sucesso!",Toast.LENGTH_LONG).show();
        this.finish();

    }

    public void salvar() {


        int proximoID = 1;
        if(realm.where(Contratos.class).max("id") !=null)
            proximoID = realm.where(Contratos.class).max("id").intValue()+1;

        realm.beginTransaction();
        Contratos contratos = new Contratos();
        contratos.setId(proximoID);
        setEGrava(contratos);

        realm.copyToRealm(contratos);
        realm.commitTransaction();
        realm.close();

        Toast.makeText(this,"Contrato cadastrado com sucesso!",Toast.LENGTH_LONG).show();
        this.finish();

    }

    private void setEGrava(Contratos contratos){

        contratos.setNome_locador(edNomeLocador.getText().toString());
        contratos.setEmail_locador(edEmailLocador.getText().toString());
        contratos.setTelefone_locador(edTelefoneLocador.getText().toString());
        contratos.setNome_locatario(edNomeLocatario.getText().toString());
        contratos.setEmail_locatario(edEmailLocatario.getText().toString());
        contratos.setRua(edRua.getText().toString());
        contratos.setCep(edCep.getText().toString());
        contratos.setNumero(edNumero.getText().toString());
        contratos.setCidade(edCidade.getText().toString());
        contratos.setValor_aluguel(edValor.getText().toString());


    }

    public void alterar() {

        realm.beginTransaction();

        setEGrava(contratos);

        realm.copyToRealm(contratos);
        realm.commitTransaction();
        realm.close();

        Toast.makeText(this,"Contrato de aluguel alterado!",Toast.LENGTH_LONG).show();
        this.finish();

    }

    private Context getContext(){
        return this;
    }
}






