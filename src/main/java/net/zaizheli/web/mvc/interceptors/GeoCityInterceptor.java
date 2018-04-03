package net.zaizheli.web.mvc.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.zaizheli.constants.ApplicationConfig;
import net.zaizheli.constants.ApplicationConstants;
import net.zaizheli.domains.CityMeta;
import net.zaizheli.repositories.CityMetaRepository;
import net.zaizheli.web.utils.SessionUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class GeoCityInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	CityMetaRepository cityMetaRepository;
	@Autowired
	private SessionUtil sessionUtil;
	
	public boolean preHandle(HttpServletRequest request, 
			HttpServletResponse response, Object handler)
		    throws Exception {
		HttpSession session = request.getSession();
		
		CityMeta cityMeta = sessionUtil.getGeoCityMeta(session);
		if(cityMeta == null){
			String city = request.getHeader(ApplicationConstants.HEADER_CITY);
			if(city == null){
				city = ApplicationConfig.defaultCityPinyin;
			}
			cityMeta = cityMetaRepository.getByPinyin(city);
			session.setAttribute(ApplicationConstants.SESSION_SELECTED_CITY_META, 
					cityMeta);
		}
		return true;
	}
	
}
