package br.edu.novaroma.appmototaxi.sqlite;

import java.util.ResourceBundle;

import br.edu.novaroma.appmototaxi.comum.Constantes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ToggleButton;

public class LoginOpenHelper extends SQLiteOpenHelper {

	private static ResourceBundle config = 
			ResourceBundle.getBundle(Constantes.DB_CONFIG_PROPS);
	
	public LoginOpenHelper(Context context) {
		super(context, config.getString("Constantes.DB_CONFIG_NOME"), null,
				Integer.parseInt(config.getString(Constantes.DB_CONFIG_VERSAO)));
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		StringBuilder sql = new StringBuilder();
		sql.append("CREATE TABLE TB_USUARIO");
		sql.append("ID_USUARIO INTEGER PRIMARY KEY AUTOINCREMENT,");
		sql.append("LOGIN TEXT NOT NULL,");
		sql.append("SENHA TEXT NOT NULL)");

		db.execSQL(sql.toString());
		mockPopulaUsuarios(db);
	}
	
	private void mockPopulaUsuarios (SQLiteDatabase db){
		
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO TB_USUARIO(LOGIN, SENHA) VALUES('josimar', '123')");
		db.execSQL(sql.toString());
		ContentValues values = new ContentValues();
		values.put("LOGIN", "admin");
		values.put("SENHA", "admin");
		db.insert("TB_USUARIO", null, values);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

	//fazendo a consulta de login e usuario existem na base de dados:
	public boolean validarLogin(String usuario, String senha){
		
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor = db.query( "TB USUARIO", null, "LOGIN = ? AND SENHA = ?",
				new String[] {usuario, senha}, null, null, null);
		
		//metodo que movimenta o curso exatamente para a primeira linha, se ele logar direto ele retorna true
		//se não ele passa dierto e retorna false
				if (cursor.moveToFirst()){
					return true;
				}
		return false;
		
	}
	
}
