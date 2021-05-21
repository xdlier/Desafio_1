package Compasso.Teste.Gabriel.Catalogo.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import Compasso.Teste.Gabriel.Catalogo.Model.Erro;

@RestControllerAdvice
public class ErroHandler {

	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<Erro> hanlder(Exception e, WebRequest request) {
		if (e.getMessage().equals("No value present")) {
			var erro = new Erro(404);
			return ResponseEntity.status(404).body(erro);
		} else {
			var erro = new Erro(400);
			return ResponseEntity.badRequest().body(erro);
		}
	}
}
