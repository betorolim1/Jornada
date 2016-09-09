package br.com.betorolim.loja.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
public class Compra implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany(cascade={CascadeType.ALL})
	private List<Item> itens = new ArrayList<Item>();

	private Long numeroDoCartao;

	private int cvv;

	private Date DataValidade;

	private String endereco;

	public Long getNumeroDoCartao() {
		return numeroDoCartao;
	}

	public void setNumeroDoCartao(Long numeroDoCartao) {
		this.numeroDoCartao = numeroDoCartao;
	}

	public int getCvv() {
		return cvv;
	}

	public void setCvv(int cvv) {
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

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

}
