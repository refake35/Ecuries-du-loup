package fiche_chevaux.mvc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import service.photo.MediaManager;
import donnees.photo.Album;

@Controller
public class ChoixPhotoController{
	@Autowired
	@Qualifier("mediaManager")
	private MediaManager mediaManager;

	public void setAlbumPhotoManager(MediaManager mediaManager) {
		this.mediaManager = mediaManager;
	}

	@RequestMapping("/ficheChevaux/administration/choixPhoto.do")
	public ModelAndView handleRequest(@RequestParam("champs") String champ, @RequestParam("valeur")String valeurParametre) {
	

		int valeur = 0;
		if (valeurParametre != null) {
			valeur = Integer.parseInt(valeurParametre);
		}

		List<Album> albums = this.mediaManager.recupererTousLesAlbums();
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("listesAlbums", albums);
		model.put("idChamps", champ);
		model.put("valeurChamps", valeur);

		return new ModelAndView("ficheChevaux/choixPhotoFicheChevaux", model);
	}

}
