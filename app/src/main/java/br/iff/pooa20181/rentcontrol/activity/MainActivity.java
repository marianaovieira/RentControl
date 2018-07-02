package br.iff.pooa20181.rentcontrol.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.iff.pooa20181.rentcontrol.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        Button btnContrato = (Button) findViewById( R.id.btnEntrarLogin );
        Button btnCadastro = (Button) findViewById( R.id.btnFazerCadastro );

        btnContrato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Login.class);
                startActivity(intent);

            }
        });

        btnCadastro.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getContext(), CadastroUsuario.class );
                startActivity( intent );
            }
        } );


    }
    private Context getContext(){
        return this;
    }
}
