package Compasso.Teste.Gabriel.Catalogo.Model;

import lombok.Getter;

@Getter
public class Erro {

	private Integer status_code;
	private String message;
	
	public Erro(Integer status) {
		this.status_code = status;
		if(status==400) {
			message="Erro na requisição, tente novamente!";
		} else if (status == 404) {
			message="Produto não encontrado, tente novamente!";
		}
	}

}
