package br.edu.novaroma.appmototaxi.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface.OnClickListener;
import android.widget.Toast;

public class MensagemUtil {
	/*
	 * Metodo de criacao de mensagens rapidas do sistema
	 */
	public static void addMsg(Activity activity, String msg){
		Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
	}

	public  static void addMsgOk(Activity activity, String titulo, String msg, int icone) {
		
		AlertDialog.Builder builderDialog = new AlertDialog.Builder(activity);
		builderDialog.setTitle(titulo);
		builderDialog.setMessage(msg);
		builderDialog.setNeutralButton("OK", null);
		builderDialog.setIcon(icone);
		builderDialog.show();
		
	}
	
	public  static void addMsgConfirm(Activity activity, String titulo, String msg, int icone,
			OnClickListener listener){
		
		AlertDialog.Builder builderDialog = new AlertDialog.Builder(activity);
		builderDialog.setTitle(titulo);
		builderDialog.setMessage(msg);
		builderDialog.setPositiveButton("Sim", listener);
		builderDialog.setPositiveButton("Não", null);
		builderDialog.setIcon(icone);
		builderDialog.show();
		
	}
	
}
