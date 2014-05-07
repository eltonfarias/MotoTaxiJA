package br.edu.novaroma.appmototaxi;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import br.edu.novaroma.appmototaxi.bo.LoginBO;
import br.edu.novaroma.appmototaxi.dominio.ValidacaoLogin;
import br.edu.novaroma.appmototaxi.util.MensagemUtil;

public class LoginActivity extends Activity {
	
	private LoginBO loginBO;

	private EditText edtLogin;

	private EditText edtSenha;

	//private Button btnEntrar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		edtLogin = (EditText) findViewById(R.id.edt_login);
		edtSenha = (EditText) findViewById(R.id.edt_senha);
	}
	public void entrar(View view){
		new LoadingAsync().execute();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// CRiando um menu
		 //menu.add(0, 1, 1, "Sair");
		// menu.add(0, 2, 2, "info");
		//inflater carrega os menus dentro do meu xml 
		getMenuInflater().inflate(R.menu.login, menu);
		
		return true;
	}
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		int idMenuItem = item.getItemId();
		switch (idMenuItem) {
		case R.id.menusair:
			MensagemUtil.addMsgConfirm(LoginActivity.this, getString(R.string.lbl_sair),
					getString(R.string.msg_logout), R.drawable.logout, new OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							
						}
					});
			finish();
			break;
		case R.id.menusobre:
			MensagemUtil.addMsgOk(LoginActivity.this, getString(R.string.lbl_sobre)
					, getString(R.string.msg_sobre), R.drawable.about);
			
		}
		
		return true;
	}
	
	private class LoadingAsync extends AsyncTask<Void, Void, ValidacaoLogin> {

		ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
		
		@Override
		protected void onPreExecute() {
			// abertura do logon 
			
			progressDialog.setTitle("Carregando...");
			progressDialog.show();
			
		}
		
		@Override
		protected ValidacaoLogin doInBackground(Void... params) {
			//vai executar algo em um plano de fundo thread principall do logar mototaxi
			
			String login = edtLogin.getText().toString();
			String senha = edtSenha.getText().toString();

			loginBO = new LoginBO(LoginActivity.this);
			
			return loginBO.validarLogin(login, senha);
			
		}
		
		@Override
		protected void onPostExecute(ValidacaoLogin validacao) {
			// executar apos a execucao principal finalizacao do logon e exibicao 
			//da mensagem de erro ou transferir o mototaxi para a proxima tela 
			
			progressDialog.dismiss();
			
			if (validacao.isValido()) {
				Intent i = new Intent(LoginActivity.this, DashBoardActivity.class);
				i.putExtra("msg", validacao.getMensagem());
				startActivity(i);
				finish();
			}else {
				MensagemUtil.addMsg(LoginActivity.this, validacao.getMensagem());
			}
			
		}
		
	}
	
}

