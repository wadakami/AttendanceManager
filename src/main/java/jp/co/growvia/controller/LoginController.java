package jp.co.growvia.controller;

import java.io.File;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {


	@RequestMapping(value="/login", method=RequestMethod.GET)
	private ModelAndView viewControll(ModelAndView mav) {

		mav.setViewName("login");

		MultipartFile multipartFile = null;
		File file = null;
		try {
		      // pathにフルパス設定
		file = new File("path ");
		//DiskFileItem fileItem = new DiskFileItem("file", "text/plain", false, file.getName(), (int) file.length(), file.getParentFile());
		// 多分↓。ファイルの種類が↓かと思う。
		DiskFileItem fileItem = new DiskFileItem("file", "text/csv", false, file.getName(), (int) file.length(), file.getParentFile());
		// ↓不要な気がする。
		fileItem.getOutputStream();
		// MultipartFileに変換
		multipartFile = new CommonsMultipartFile(fileItem);

		}catch(Exception e) {
		e.printStackTrace();
		}

		return mav;

	}

}
