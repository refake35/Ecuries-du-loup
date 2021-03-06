package mvc.edl_code;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import service.photo.MediaManager;
import donnees.photo.Album;

@Controller
public class ChoixImageEdlCodeController{
	@Autowired
	@Qualifier("mediaManager")
	private MediaManager mediaManager;

	public void setAlbumPhotoManager(MediaManager mediaManager) {
		this.mediaManager = mediaManager;
	}

	@RequestMapping("/eldCode/choixImage.do")
	public ModelAndView show() throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();

		List<Album> albums = this.mediaManager.recupererTousLesAlbums();
		model.put("listesAlbums", albums);

		return new ModelAndView("edlCode/choixAjoutImage", model);
	}

}
