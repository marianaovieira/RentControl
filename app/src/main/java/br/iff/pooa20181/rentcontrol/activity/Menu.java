package br.iff.pooa20181.rentcontrol.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.iff.pooa20181.rentcontrol.R;

public class Menu extends AppCompatActivity {

    Button btnContratos, btnImovel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnContratos =(Button) findViewById(R.id.btnContratos);
        btnImovel =(Button) findViewById(R.id.btnImovel);

        btnContratos.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListaContratos.class);
                startActivity(intent);
            }
        });

        btnImovel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ListaImoveis.class);
                startActivity(intent);
            }
        });

    }
}
