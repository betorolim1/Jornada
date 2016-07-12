package br.com.betorolim.loja.Modelo;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Livro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotNull
	private String Titulo;
	private Byte[] Capa;
	@NotNull
	private String Autor;
	@NotNull
	private String Descricao;
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
		return Titulo;
	}
	public void setTitulo(String titulo) {
		Titulo = titulo;
	}
	public String getAutor() {
		return Autor;
	}
	public void setAutor(String autor) {
		Autor = autor;
	}
	public String getDescricao() {
		return Descricao;
	}
	public void setDescricao(String descricao) {
		Descricao = descricao;
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
	public Byte[] getCapa() {
		return Capa;
	}
	public void setCapa(Byte[] capa) {
		Capa = capa;
	}
	
}
