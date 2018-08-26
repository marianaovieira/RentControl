package br.iff.pooa20181.rentcontrol.activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.Task;

import br.iff.pooa20181.rentcontrol.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {

    SignInButton signInButton;
    private GoogleSignInClient googleSignInClient;
    private static final int RC_ENTRAR = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );


        signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener((View.OnClickListener) this);


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(this, gso);



    }

    private void signIn() {
        //intent para mostrar com qual conta o usuário deseja entrar
        Intent entrarIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(entrarIntent, RC_ENTRAR);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                signIn();
                break;
            // ...
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            // sucesso ao logar
            Intent intent = new Intent(MainActivity.this,Menu.class);
            startActivity(intent);
        } catch (ApiException e) {
            Toast.makeText(getApplicationContext(), "Algo de errado aconteceu", Toast.LENGTH_LONG);
        }
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_ENTRAR) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
    }

    protected void onStart() {
        super.onStart();
        //checa se o usuario já logou no app antes com a conta da google
        //retorna nulo se não logado
        GoogleSignInAccount conta = GoogleSignIn.getLastSignedInAccount(this);
        //se o usuario está logado, redireciona para a app
        if(conta != null)
        {
            Intent intent = new Intent(MainActivity.this,Menu.class);
            startActivity(intent);
        }
    }


}
