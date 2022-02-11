package imageboard.controller;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import imageboard.bean.ImageboardDTO;
import imageboard.service.ImageboardService;

@Controller
@RequestMapping(value = "imageboard")
public class ImageboardController {
	@Autowired
	private ImageboardService imageboardService;

	@GetMapping(value = "imageboardWriteForm")
	public String imageboardWriteForm(Model model) {
		model.addAttribute("display", "/imageboard/imageboardWriteForm.jsp");
		return "/index";
	}
	
	// name="img"
	@PostMapping(value = "imageboardWrite")
	@ResponseBody
	public void imageboardWrite(@ModelAttribute ImageboardDTO imageboardDTO,
								@RequestParam MultipartFile img,
								HttpSession session) {//실제경로얻을라고
		//지금 img는 임시폴더에있음
		/*가상폴더에올리고 실제폴더에는 복사해주기
		//이클립스때는 가상폴더에 접근불가였는데 얜함
		String filePath = "C:/Spring/workspace/SpringProject/src/main/webapp/storage";//가상폴더
		String fileName = img.getOriginalFilename();//너의..이름은?
		
		//파일복사
		File file = new File(filePath,fileName);//파일생성
		try {
			FileCopyUtils.copy(img.getInputStream(),new FileOutputStream(file));
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
		} catch (IOException e) {//얘가 부모라서 위쪽 애도 들어올수있음
			e.printStackTrace();
		}
		
		*/
		//아직 DTO에 파일이름못들어감...
		
		//이번엔 실제폴더에 직접올려보기
		String filePath = session.getServletContext().getRealPath("/storage");
		String fileName = img.getOriginalFilename();
		
		File file = new File(filePath,fileName);
		
		try {
			img.transferTo(file);
//		} catch (IllegalStateException e) {
//			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		imageboardDTO.setImage1(fileName);
		
		//이제 DB가면됨
		imageboardService.imageboardWrite(imageboardDTO);
		
	}
	
	// name="img" 2개 이상인 경우
	
	@GetMapping(value = "imageboardList")
	public String imageboardList(Model model,@RequestParam(required = false,defaultValue = "1") String pg) {
		model.addAttribute("display", "/imageboard/imageboardList.jsp");
		model.addAttribute("pg", pg);
		return "/index";
	}
	
	
	@PostMapping(value = "getImageboardList")
	@ResponseBody
	public Map<String,Object> getImageboardList(@RequestParam String pg){
		return imageboardService.getImageboardList(pg);
	}
	
}
