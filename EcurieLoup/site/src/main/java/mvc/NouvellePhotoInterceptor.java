package mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import service.UtilisateurManager;
import service.photo.AlbumPhotoManager;
import donnees.User;

public class NouvellePhotoInterceptor extends HandlerInterceptorAdapter {
	private UtilisateurManager utilisateurManager;
	private AlbumPhotoManager albumPhotoManager;

	public void setUtilisateurManager(UtilisateurManager utilisateurManager) {
		this.utilisateurManager = utilisateurManager;
	}

	public void setAlbumPhotoManager(AlbumPhotoManager albumPhotoManager) {
		this.albumPhotoManager = albumPhotoManager;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		User utilistateurCourant = this.utilisateurManager
				.getUtilisateurCourant();
		if (utilistateurCourant != null) {
			boolean hasNouvellesPhotos = this.albumPhotoManager
					.hasNouvellesPhotos();
			if(modelAndView != null){
				modelAndView.addObject("hasNouvellesPhotos", hasNouvellesPhotos);
				int nombreNouvellesPhotos = this.albumPhotoManager
						.getNombreNouvellesPhotos();
				modelAndView.addObject("nombreNouvellesPhotos",
						nombreNouvellesPhotos);
			}
		}
	}
}