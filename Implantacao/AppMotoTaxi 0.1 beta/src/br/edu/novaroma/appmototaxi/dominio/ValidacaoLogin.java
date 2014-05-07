package br.edu.novaroma.appmototaxi.dominio;
//criando um objeto para emcapsular tudo nele
public class ValidacaoLogin {
	
	private boolean valido;
	
	private String mensagem;

	public boolean isValido() {
		return valido;
	}

	public void setValido(boolean valido) {
		this.valido = valido;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
