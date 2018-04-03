package net.zaizheli.web.utils;



import net.zaizheli.constants.ApplicationConfig;
import net.zaizheli.constants.Gender;
import net.zaizheli.domains.Resource;
import net.zaizheli.vo.ActionVo;

import org.springframework.stereotype.Component;

@Component
public class DomainObjectUtil {
	
	public static String getGender(Gender gender){
		String sex = null;
		if(gender==null) return "保密";
		switch(gender){
			case FEMALE:
				sex = "美女";
				break;
			case MALE:
				sex = "帅哥";
				break;
			default:
				sex = "未知";
				break;
		}
		return sex;
	}
	
	public static String getThirdPerson(Gender gender){
		String sex = null;
		if(gender==null) return "他";
		switch(gender){
			case FEMALE:
				sex = "她";
				break;
			case MALE:
			default:
				sex = "他";
				break;
		}
		return sex;
	}
	
	public static String getAvatarUrl(Resource res,
			Gender gender){
		StringBuilder url = new StringBuilder();
		if(res!=null) {
			url.append(WebImageUtil.getAvatarUrl(res.getResId()));
		}else{
			if(gender==null){
				url.append(ApplicationConfig.base)
					.append(ApplicationConfig.staticRefer)
					.append("/img/avatar-unkown.jpg");
			}else{
				switch(gender){
					case FEMALE:
						url.append(ApplicationConfig.base)
							.append(ApplicationConfig.staticRefer)
							.append("/img/avatar-female.jpg");
						break;
					case MALE:
						url.append(ApplicationConfig.base)
							.append(ApplicationConfig.staticRefer)
							.append("/img/avatar-male.jpg");
						break;
					default:
						url.append(ApplicationConfig.base)
							.append(ApplicationConfig.staticRefer)
							.append("/img/avatar-unkown.jpg");
						break;
				}
			}
		}
		return url.toString();
	}
	
	public static String getActionHtml(ActionVo action){
		StringBuilder html = new StringBuilder();
		if(action==null||action.getType()==null) return html.toString();
		html.append("<a>").append(action.getOwner().getName()).append("</a>");
		switch(action.getType()){
			case ACTIVITY:
				html.append("&nbsp;建立了新活动&nbsp;")
					.append("<a>")
					.append(action.getTargetActivity().getTitle());
				if(action.getTargetActivity().getPlace()!=null){
					html.append("&nbsp;@&nbsp;")
						.append(action.getTargetActivity().getPlace().getFullAddr());
				}
					html.append("</a>");
				break;
			case TRACK:
				html.append("&nbsp;追踪了&nbsp;")
					.append("<a>")
					.append(action.getTargetActivity().getTitle());
				if(action.getTargetActivity().getPlace()!=null){
					html.append("&nbsp;@&nbsp;")
						.append(action.getTargetActivity().getPlace().getFullAddr());
				}
					html.append("</a>");
				break;
			case FORWARD:
				html.append("&nbsp;转发了&nbsp;")
					.append("<a>")
					.append(action.getTargetActivity().getTitle());
				if(action.getTargetActivity().getPlace()!=null){
					html.append("&nbsp;@&nbsp;")
						.append(action.getTargetActivity().getPlace().getFullAddr());
				}
					html.append("</a>");
				break;
			case COMMENT:
				if(action.getTargetUser()!=null){
				}
				else {
				html.append("&nbsp;评论了&nbsp;");
					if(action.getTargetActivity()!=null){
						html.append("<a>")
							.append(action.getTargetActivity().getTitle());
						if(action.getTargetActivity().getPlace()!=null){
							html.append("&nbsp;@&nbsp;")
								.append(action.getTargetActivity().getPlace().getFullAddr());
						}
						html.append("</a><br>");
					}
				}
				html.append("&nbsp;")
					.append(action.getContent());
				break;
			case FOLLOW:
				html.append("&nbsp;关注了&nbsp;")
					.append("<a>")
					.append(action.getTargetUser().getName())
					.append("</a>");
				break;
			case SETTING:
				html.append("&nbsp;更新了&nbsp;")
					.append("<a>")
					.append(action.getTargetActivity().getTitle());
				if(action.getTargetActivity().getPlace()!=null){
					html.append("&nbsp;@&nbsp;")
						.append(action.getTargetActivity().getPlace().getFullAddr());
				}
					html.append("</a>");
				break;
			case JOIN:
				html.append("&nbsp;加入了&nbsp;")
				.append("<a>")
				.append(action.getTargetActivity().getTitle());
				if(action.getTargetActivity().getPlace()!=null){
					html.append("&nbsp;@&nbsp;")
						.append(action.getTargetActivity().getPlace().getFullAddr());
				}
				html.append("</a>");
				break;
			case UPLOAD:
				html.append("&nbsp;上传了图片到&nbsp;")
				.append("<a>")
				.append(action.getTargetActivity().getTitle());
				if(action.getTargetActivity().getPlace()!=null){
					html.append("&nbsp;@&nbsp;")
						.append(action.getTargetActivity().getPlace().getFullAddr());
				}
				html.append("</a>");
				break;
			case APPLY:
				html.append("&nbsp;申请加入&nbsp;")
				.append("<a>")
				.append(action.getTargetActivity().getTitle());
				if(action.getTargetActivity().getPlace()!=null){
					html.append("&nbsp;@&nbsp;")
						.append(action.getTargetActivity().getPlace().getFullAddr());
				}
				html.append("</a>");	
				break;
			case QUIT:
				html.append("&nbsp;退出了&nbsp;")
				.append("<a>")
				.append(action.getTargetActivity().getTitle());
				if(action.getTargetActivity().getPlace()!=null){
					html.append("&nbsp;@&nbsp;")
						.append(action.getTargetActivity().getPlace().getFullAddr());
				}
				html.append("</a>");	
				break;
			default:
				break;
		}
		return html.toString();
	}
	
}
