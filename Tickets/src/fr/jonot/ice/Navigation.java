package fr.jonot.ice;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.servlet.annotation.ServletSecurity;
//ToDO : objet page avec url, titre, emplacement, autorisations, meta données... 

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;


/*
 * Cette classe a pour but de scanner les fichiers xhtml 
 * à la recherche d'annotations spécifiques
 * pour les organiser et leur associer autorisations et métadonnées
 * 
 * Elle fournira ensuite un menu
 * ,avec la page actuelle sélectionnée,
 * en fonction du compte connecté
 * 
 * puis d'autres fonctions comme la page parente, les pages soeurs, un fil d'ariane...
 * La redirection vers une page quand  le compte n'est pas autorisés
 */

@ManagedBean
@ApplicationScoped
public class Navigation {

	private List<Page> pages;
	private static Pattern pTitre;
	private static Pattern pAutorisations;
	private static Pattern pParent;

	private static String racine="Tickets";

	@PostConstruct 
	public void init() {
		//parse des fichiers xhtml 
		//pour lire les annotations
		//et créer l'arborescence des pages

		pTitre=Pattern.compile("@Titre\\((.*)\\)");
		pAutorisations=Pattern.compile("@Autorisations\\((.*)\\)");
		pParent=Pattern.compile("@Parent\\((.*)\\)");

		pages=new ArrayList<Page>();
		try{
			String path = this.getClass().getClassLoader().getResource("").getPath();
			String fullPath = URLDecoder.decode(path, "UTF-8");
			String pathArr[] = fullPath.split("/WEB-INF/classes/");
			File repertoire=new File(pathArr[0]);
			parcourir(repertoire);
		}catch ( UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public void parcourir(File repertoire){

		if("WEB-INF".equals(repertoire.getName()) || "META-INF".equals(repertoire.getName()) || "resources".equals(repertoire.getName())) return;
		if (repertoire.exists() ) { 
			if( repertoire.isDirectory()){
				File[] fichiers = repertoire.listFiles(); 
				if (fichiers != null) { 
					for (File fichier : fichiers) { 
						// Appel récursif sur les sous-répertoires 
						parcourir(fichier); 
					}  
				} 
			}
			else {

				pages.add(parseXHTML(repertoire));
			}
		}  
	}
	public Page parseXHTML(File fichier){
		if(fichier== null) return null;
		try{
			Scanner sc = new Scanner(fichier);
			Page page=null;
			while (sc.hasNextLine()) {
				String s=sc.nextLine();
				Matcher ma=pAutorisations.matcher(s); 
				if(ma.find()){
					if(page==null) page=getPage(fichier);

					String autorisations=ma.group(1);;
					if(autorisations!=null && !autorisations.isEmpty()){
						String[] autos=autorisations.split(",");
						for(String a :autos) {
							page.addAutorisation(a);
						}
					}

				}
				Matcher mt=pTitre.matcher(s); 
				if(mt.find()){
					if(page==null) page=getPage(fichier);
					String titre=mt.group(1);
					page.setTitre(titre);
				}

				Matcher mp=pParent.matcher(s); 
				if(mp.find()){
					if(page==null) page=getPage(fichier);
					String parent=mp.group(1);
					page.setParent(parent);
				}
			} 
			sc.close();
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		return null;
	}


	public MenuModel  getMenu(){
		
		//ToDo : creer le menu en fonction de l'organisation des pages de l'arborescenece
		// et des autorisations du compe
		//prévoir affichage selectionné de la page actuelle 
		
		// ?face-redirect=true ???
		
		MenuModel model = new DefaultMenuModel();

		//First submenu
		DefaultSubMenu firstSubmenu = new DefaultSubMenu("Tickets");

		DefaultMenuItem item = new DefaultMenuItem("Consultation");
		item.setUrl("tickets.xhtml");
		item.setIcon("pi pi-home");
		firstSubmenu.addElement(item);

		model.addElement(firstSubmenu);

		//Second submenu
		DefaultSubMenu secondSubmenu = new DefaultSubMenu("Comptes");

		item = new DefaultMenuItem("Save");
		item.setIcon("pi pi-save");
		item.setCommand("#{navigation.save}");
		item.setUpdate("messages");
		secondSubmenu.addElement(item);

		item = new DefaultMenuItem("Delete");
		item.setIcon("pi pi-times");
		item.setCommand("#{navigation.delete}");
		item.setAjax(false);
		secondSubmenu.addElement(item);

		item = new DefaultMenuItem("Redirect");
		item.setIcon("pi pi-search");
		item.setCommand("#{navigation.redirect}");
		secondSubmenu.addElement(item);

		model.addElement(secondSubmenu);

		return model;

	}
	private Page getPage(File fichier){
		if(fichier==null) return null;
		Page page=new Page(fichier.getName());
		String path = fichier.getPath();

		//String fullPath = URLDecoder.decode(path, "UTF-8");
		//System.out.println(fullPath);
		String pathArr[] = path.split(File.pathSeparator);
		String relativePath="";
		for(int i=pathArr.length-1; i>=0; i-- ){
			if(racine.equals(pathArr[i])) break;
			relativePath=pathArr[i]+"//"+relativePath;

		}
		page.setUrl(relativePath);


		return page;
	}

}
