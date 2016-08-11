package br.com.betorolim.loja.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class FinalizaCompra implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Integer numeroDoCartao;

	private Integer cvv;

	private Date DataValidade;

	private String endereco;
	
	@ManyToOne
	private Usuario usuario;

	public Integer getNumeroDoCartao() {
		return numeroDoCartao;
	}

	public void setNumeroDoCartao(Integer numeroDoCartao) {
		this.numeroDoCartao = numeroDoCartao;
	}

	public Integer getCvv() {
		return cvv;
	}

	public void setCvv(Integer cvv) {
		this.cvv = cvv;
	}

	public Date getDataValidade() {
		return DataValidade;
	}

	public void setDataValidade(Date dataValidade) {
		DataValidade = dataValidade;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

}
