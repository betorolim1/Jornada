package br.com.betorolim.loja.Bean;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.com.betorolim.loja.Dao.LivroDao;
import br.com.betorolim.loja.Modelo.Livro;

@Model
public class LivroBean {
	
	private Livro livro = new Livro();


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
			livro.setCapa(event.getFile().getContents());
			FacesMessage msg = new FacesMessage("O Arquivo ", arq.getFileName() + " salvo em banco de dados.");
			FacesContext.getCurrentInstance().addMessage("msgUpdate", msg);

			System.out.println(event.getFile().getContents());
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
}
