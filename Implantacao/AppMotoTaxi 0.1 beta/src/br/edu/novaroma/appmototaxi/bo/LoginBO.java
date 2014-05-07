package br.edu.novaroma.appmototaxi.bo;

import android.content.Context;
import br.edu.novaroma.appmototaxi.R;
import br.edu.novaroma.appmototaxi.dominio.ValidacaoLogin;
import br.edu.novaroma.appmototaxi.sqlite.LoginOpenHelper;

public class LoginBO {

	private Context context;
	
	private LoginOpenHelper loginOpenHelper;
	
	public LoginBO(Context context){
		this.context = context;
		loginOpenHelper = new LoginOpenHelper(context);
	}
	
	public ValidacaoLogin validarLogin(String login, String senha){
		ValidacaoLogin retorno = new ValidacaoLogin();
		if (login == null || login.equals("")) {
			retorno.setValido(false);
			retorno.setMensagem(context.getString(R.string.msg_login_obg));
		} else if (senha == null || senha.equals("")) {
			retorno.setValido(false);
			retorno.setMensagem(context.getString(R.string.msg_senha_obg));
		} else if(loginOpenHelper.validarLogin(login, senha)){
			retorno.setValido(true);
			retorno.setMensagem(context.getString(R.string.msg_login_sucesso));
		}  else {
			retorno.setValido(false);
			retorno.setMensagem(context.getString(R.string.msg_login_invalido));
	      }	
		return retorno;
		
	}
}
