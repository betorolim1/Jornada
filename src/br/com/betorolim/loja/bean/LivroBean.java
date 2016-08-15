package br.com.betorolim.loja.bean;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.UploadedFile;

import br.com.betorolim.loja.dao.LivroDao;
import br.com.betorolim.loja.modelo.Livro;

@Named
@ViewScoped
public class LivroBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Livro livro = new Livro();

	private List<Livro> livros;

	private Livro livroSelecionado;

	private String tituloPesquisa;

	private Livro livroEncontrado;

	@Inject
	private LivroDao dao;

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public void cadastrar() throws IOException {
		Livro livroCadastrado = dao.buscaLivroPorTitulo(livro.getTitulo());
		if (livroCadastrado != null && !livroCadastrado.equals(livro)) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Titulo ja existe!", null));
		} else {
			dao.adiciona(livro);
			FacesContext.getCurrentInstance().getExternalContext().redirect("admin.xhtml");
		}
	}

	public void fileUpload(FileUploadEvent event) {
		try {

			UploadedFile arq = event.getFile();

			InputStream in = new BufferedInputStream(arq.getInputstream());
			File file = new File(
					"C:\\Users\\Adalberto\\workspace\\Livraria\\WebContent\\resources\\img\\" + arq.getFileName());
			FileOutputStream fout = new FileOutputStream(file);
			while (in.available() != 0) {
				fout.write(in.read());
			}
			fout.close();

			this.livro.setCapa(arq.getFileName());
			System.out.println(livro.getCapa());

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public List<Livro> getLivros() {
		if (livros == null) {
			livros = dao.listaTodos();
		}
		return livros;
	}

	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}

	public void editaLinha(RowEditEvent event) throws IOException {
		livro = (Livro) event.getObject();
		
		Livro livroCadastrado = dao.buscaLivroPorTitulo(livro.getTitulo());
		if (livroCadastrado != null && !livroCadastrado.equals(livro)) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Titulo ja existe!", null));
			
		} else {
			FacesMessage msg = new FacesMessage("Livro atualizado", null);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			dao.atualiza(livro);
			
		}
		livros = dao.listaTodos();
	}

	public void cancelaEdicao(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edição cancelada", null);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void remove(Livro livro) {
		dao.remove(livro);
		livros = dao.listaTodos();
	}

	public Livro getLivroSelecionado() {
		return livroSelecionado;
	}

	public void setLivroSelecionado(Livro livroSelecionado) {
		this.livroSelecionado = livroSelecionado;
	}

	public void buscaLivroPorTitulo() {
		livroEncontrado = dao.buscaLivroPorTitulo(tituloPesquisa);
	}

	public String getTituloPesquisa() {
		return tituloPesquisa;
	}

	public void setTituloPesquisa(String tituloPesquisa) {
		this.tituloPesquisa = tituloPesquisa;
	}

	public Livro getLivroEncontrado() {
		return livroEncontrado;
	}

	public String limparLivroEncontrado() {
		livroEncontrado = null;
		return "principal?faces-redirect=true";
	}

}
