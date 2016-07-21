package br.com.betorolim.loja.bean;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
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


	@Inject
	private LivroDao dao;
	
	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	
	public String cadastrar() {
		dao.adiciona(livro);
		return "principal?faces-redirect=true";
	}
	
	public void fileUpload(FileUploadEvent event){
		try{
			
			UploadedFile arq = event.getFile();
			
			 InputStream in = new BufferedInputStream(arq.getInputstream());
			 File file = new File("C:\\var\\" + arq.getFileName());
			 String caminho = file.getAbsolutePath();
			 FileOutputStream fout = new FileOutputStream(file);
			 while(in.available() != 0)
			 {
			 fout.write(in.read());
			 }
			 fout.close();
			 
			 this.livro.setCapa(caminho);
			 System.out.println(livro.getCapa());
			
		}catch(Exception ex){
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

		FacesMessage msg = new FacesMessage("Livro atualizado", null);
        FacesContext.getCurrentInstance().addMessage(null, msg);
		dao.atualiza(livro);
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
	
}
