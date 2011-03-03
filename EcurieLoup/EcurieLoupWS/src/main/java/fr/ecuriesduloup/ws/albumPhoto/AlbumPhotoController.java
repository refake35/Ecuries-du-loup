package fr.ecuriesduloup.ws.albumPhoto;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import donnees.User;
import donnees.photo.Album;
import donnees.photo.Photo;
import fr.ecuriesduloup.ws.WsStatus;
import fr.ecuriesduloup.ws.user.UserService;

@Controller
public class AlbumPhotoController {
	@Autowired
	private AlbumPhotoService albumPhotoService;
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/albumPhoto/albums",method=RequestMethod.GET)
	public ModelAndView listAlbum() {
		List<Album> albums = this.albumPhotoService.getAlbums();
		List<AlbumWs> albumWs = new ArrayList<AlbumWs>();
		for(Album album : albums){
			albumWs.add(new AlbumWs(album));
		}
		ModelAndView mav = new ModelAndView("statusXmlView", BindingResult.MODEL_KEY_PREFIX + "albums", albumWs);
		return mav;
	}

	@RequestMapping(value = "/albumPhoto/album/{name}",method=RequestMethod.PUT)
	public ModelAndView createAlbum(@PathVariable String name) {
		long idAlbumPhoto = this.albumPhotoService.createAlbumPhoto(name);
		Id id = new Id();
		id.setValue(idAlbumPhoto);
		ModelAndView mav = new ModelAndView("statusXmlView", BindingResult.MODEL_KEY_PREFIX + "albumPhoto", id);
		return mav;
	}

	@RequestMapping(value = "/albumPhoto/photo/{albumId}",method=RequestMethod.POST)
	
	public ModelAndView upload(@PathVariable long albumId, @RequestParam("file") MultipartFile multipartFile, MultipartHttpServletRequest request){
		

		User posteur = this.userService.getCurrentUser();
		Album album = this.albumPhotoService.getAlbum(albumId);
		
		Photo photo = new Photo();
		photo.setAlbum(album);
		photo.setDatePostage(new Date().getTime());
		photo.setPosteur(posteur);
		photo.setDescription("");
		photo.setTypeAdding("web_service");


		if (multipartFile.isEmpty()) {


		}

		if(!this.isSupportedPicture(multipartFile)){

		}
		
		File temporaire = this.createTemp(multipartFile, posteur);
		
		String pathPhoto = request.getSession().getServletContext().getRealPath("/");

		this.albumPhotoService.createPhoto(photo, temporaire, pathPhoto);

		temporaire.delete();
		WsStatus wsStatus = new WsStatus();
		wsStatus.setStatus("OK");
		ModelAndView mav = new ModelAndView("statusXmlView", BindingResult.MODEL_KEY_PREFIX + "Status", wsStatus);
		return mav;
		
	}
	
	private File createTemp(MultipartFile multipartFile, User posteur){
		String chemin = "tmp";
		chemin += posteur.getLogin();
		chemin += "_" + new Date().getTime();
		chemin += (int) (Math.random() * 10000);

		File temporaire = new File(chemin);
		try {
			multipartFile.transferTo(temporaire);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return temporaire;
	}


	

	private boolean isSupportedPicture(MultipartFile multipartFile) {
		// TODO Auto-generated method stub
		return false;
	}
}