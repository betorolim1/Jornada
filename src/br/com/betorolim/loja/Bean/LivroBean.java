package br.com.betorolim.loja.Bean;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.com.betorolim.loja.Dao.LivroDao;
import br.com.betorolim.loja.Modelo.Livro;

@ManagedBean
@ViewScoped
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
	
}
