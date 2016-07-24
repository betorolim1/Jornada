package br.com.betorolim.loja.modelo;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Livro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotEmpty(message="Titulo não deve ser vazio")
	private String titulo;
	private String capa;
	@NotEmpty(message="Autor não deve ser vazio")
	private String autor;
	@NotEmpty(message="Descricao não deve ser vazio")
	private String descricao;
	@NotNull
	private double precoEbook;
	@NotNull
	private double precoImpresso;
	@NotNull
	private double precoCombo;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public double getPrecoEbook() {
		return precoEbook;
	}
	public void setPrecoEbook(double precoEbook) {
		this.precoEbook = precoEbook;
	}
	public double getPrecoImpresso() {
		return precoImpresso;
	}
	public void setPrecoImpresso(double precoImpresso) {
		this.precoImpresso = precoImpresso;
	}
	public double getPrecoCombo() {
		return precoCombo;
	}
	public void setPrecoCombo(double precoCombo) {
		this.precoCombo = precoCombo;
	}
	public String getCapa() {
		return capa;
	}
	public void setCapa(String capa) {
		this.capa = capa;
	}
	
}
