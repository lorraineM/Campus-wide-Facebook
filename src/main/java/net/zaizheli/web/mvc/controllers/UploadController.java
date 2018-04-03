package net.zaizheli.web.mvc.controllers;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import net.zaizheli.constants.ActionType;
import net.zaizheli.constants.AjaxResultCode;
import net.zaizheli.constants.ApplicationConfig;
import net.zaizheli.domains.Action;
import net.zaizheli.repositories.ActionRepository;
import net.zaizheli.repositories.ActivityRepository;
import net.zaizheli.repositories.ResourceRepository;
import net.zaizheli.services.ImageService;
import net.zaizheli.vo.AjaxResult;
import net.zaizheli.web.utils.SessionUtil;
import net.zaizheli.web.utils.WebImageUtil;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;




@Controller
public class UploadController implements ApplicationContextAware {
	
	@Autowired
	ImageService imageService;
	@Autowired
	WebImageUtil webImageUtil;
	@Autowired
	ResourceRepository resourceRepository;
	@Autowired
	ActivityRepository activityRepository;
	@Autowired
	ActionRepository actionRepository;
	@Autowired 
	SessionUtil sessionUtil;
	
	private ApplicationContext ac;

	@ModelAttribute("tempRepositories")
	public String getImageRepositoriesPath() {
		return ApplicationConfig.uploadTempRepository;
	}

	@RequestMapping(value = "/spots/upload", method = RequestMethod.POST)
	public @ResponseBody
	String uploadSpot(@RequestParam MultipartFile imageFile,
			@ModelAttribute("tempRepositories") String tempRepositories,
			Model model) throws IOException {
		String orgName = imageFile.getOriginalFilename();
		String newName = new StringBuilder().append(System.currentTimeMillis())
				.append(orgName.substring(orgName.lastIndexOf('.'))).toString();
		Resource res = ac.getResource(tempRepositories);
		File file = res.getFile();
		if (file.isDirectory()) {
			file = new File(file.getPath() + File.separator + newName);
		}
		imageFile.transferTo(file);
		return ApplicationConfig.base + ApplicationConfig.uploadTempRefer + "/"
				+ newName;
	}

	@RequestMapping(value = "/avatar/upload", method = RequestMethod.POST)
	public @ResponseBody
	AjaxResult uploadAvatar(@RequestParam MultipartFile imageFile,
			@ModelAttribute("tempRepositories") String tempRepositories,
			Model model, HttpServletResponse response) throws IOException {
		String orgName = imageFile.getOriginalFilename();
		String newName = new StringBuilder().append(System.currentTimeMillis())
				.append(orgName.substring(orgName.lastIndexOf('.'))).toString();
		String ext = orgName.substring(orgName.lastIndexOf('.'));
		Resource res = ac.getResource(tempRepositories);
		File file = res.getFile();
		if (file.isDirectory()) {
			file = new File(file.getPath() + File.separator + newName);
		}
		imageFile.transferTo(file);
		BufferedImage orgImg = ImageIO.read(file);
		float h = orgImg.getHeight();
		float w = orgImg.getWidth();
		AjaxResult result = new AjaxResult(AjaxResultCode.SUCCESS);
		if (h < 200 || w < 200) {
			result.setResultCode(AjaxResultCode.EXCEPTION);
			result.setExceptionMsg("头像尺寸不小于200x200");
		} else {
			if (w > 350) {
				w = 350;
				h = h / (orgImg.getWidth() / w);
				int rw = Math.round(w);
				int rh = Math.round(h);
				BufferedImage newImg = new BufferedImage(rw, rh,
						BufferedImage.TYPE_INT_RGB);
				// 创建Graphics2D对象，用于在BufferedImage对象上绘图。
				Graphics2D g = newImg.createGraphics();
				// 设置图形上下文的当前颜色为白色。
				g.setColor(Color.WHITE);
				// 用图形上下文的当前颜色填充指定的矩形区域。
				g.fillRect(0, 0, rw, rh);
				// 按照缩放的大小在BufferedImage对象上绘制原始图像。
				g.drawImage(orgImg, 0, 0, rw, rh, null);
				// 释放图形上下文使用的系统资源。
				g.dispose();
				// 刷新此 Image 对象正在使用的所有可重构的资源.
				orgImg.flush();
				if(file!=null) file.delete();
				newName = new StringBuilder()
						.append(System.currentTimeMillis()).append(ext)
						.toString();
				file = res.getFile();
				if (file.isDirectory()) {
					file = new File(file.getPath() + File.separator + newName);
				}
				ImageIO.write(newImg, ext.substring(1), file);
			}
			result.setResultData(ApplicationConfig.base+
					 ApplicationConfig.uploadTempRefer + "/" + newName);
		}
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		return result;
	}
	
    @RequestMapping(value = "/gallery/upload", method = RequestMethod.POST)
    public 
    String executeMultiple( HttpServletRequest request, @RequestParam("actId") String id,
    		@RequestParam("userId") String uid,
			@ModelAttribute("tempRepositories") String tempRepositories,
            HttpServletResponse response, ModelMap model, HttpSession session) throws Exception
    {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet())
        {
            // 获取MulipartFile对象
            MultipartFile imageFile = entity.getValue();
            String orgName = imageFile.getOriginalFilename();
    		String newName = new StringBuilder().append(System.currentTimeMillis())
    				.append(orgName.substring(orgName.lastIndexOf('.'))).toString();
    		String ext = orgName.substring(orgName.lastIndexOf('.'));
    		Resource res = ac.getResource(tempRepositories);
    		File file = res.getFile();
    		if (file.isDirectory()) {
    			file = new File(file.getPath() + File.separator + newName);
    		}
    		imageFile.transferTo(file);
    		
    		BufferedImage orgImg = ImageIO.read(file);
    		String resId = imageService.put(file);
    		net.zaizheli.domains.Resource re = new net.zaizheli.domains.Resource();
    		re.setResId(resId);
    		re.setOrgSize(new Integer[]{ orgImg.getHeight(), orgImg.getWidth() });
    		re.setExt(ext);
    		re.setActId(id);
    		re.setTmpUrl(ApplicationConfig.uploadTempRepository + "/"+file.getName());
    		resourceRepository.save(re); 
    		activityRepository.inc(id, "galleryCount", 1);
    		activityRepository.inc(id, "hot", 0.1);
    		Action action = new Action();
            action.setOwner(uid);
    		action.setCreatedAt(new Date());
    		action.setTargetActivity(id);
    		action.setType(ActionType.UPLOAD);
    		action.setBy(sessionUtil.getBy(session));
    		action.setPicId(resId);
    		actionRepository.save(action);
        }      
        return "activity/gallery";
    }


//	@Override
	public void setApplicationContext(ApplicationContext ac)
			throws BeansException {
		this.ac = ac;
	}
}
