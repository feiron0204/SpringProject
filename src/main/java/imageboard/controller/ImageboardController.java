package imageboard.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
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
import org.springframework.web.servlet.ModelAndView;

import imageboard.bean.ImageboardDTO;
import imageboard.bean.ImageboardPaging;
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
	/*
	// name="img"
	@PostMapping(value = "imageboardWrite")
	@ResponseBody
	public void imageboardWrite(@ModelAttribute ImageboardDTO imageboardDTO,
								@RequestParam MultipartFile img,
								HttpSession session) {//실제경로얻을라고
		//지금 img는 임시폴더에있음
//		가상폴더에올리고 실제폴더에는 복사해주기
//		//이클립스때는 가상폴더에 접근불가였는데 얜함
//		String filePath = "C:/Spring/workspace/SpringProject/src/main/webapp/storage";//가상폴더
//		String fileName = img.getOriginalFilename();//너의..이름은?
//		
//		//파일복사
//		File file = new File(filePath,fileName);//파일생성
//		try {
//			FileCopyUtils.copy(img.getInputStream(),new FileOutputStream(file));
////		} catch (FileNotFoundException e) {
////			e.printStackTrace();
//		} catch (IOException e) {//얘가 부모라서 위쪽 애도 들어올수있음
//			e.printStackTrace();
//		}
//		
//		
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
		
		System.out.println(imageboardDTO);
		//이제 DB가면됨
		imageboardService.imageboardWrite(imageboardDTO);
		
	}
	*/
	// name="img" 2개 이상인 경우
	/*
	@PostMapping(value = "imageboardWrite")
	@ResponseBody
	public void imageboardWrite(@ModelAttribute ImageboardDTO imageboardDTO,
								@RequestParam MultipartFile[] img,
								HttpSession session) {//실제경로얻을라고
		//지금 img는 임시폴더에있음
//		가상폴더에올리고 실제폴더에는 복사해주기
//		//이클립스때는 가상폴더에 접근불가였는데 얜함
//		String filePath = "C:/Spring/workspace/SpringProject/src/main/webapp/storage";//가상폴더
//		String fileName = img.getOriginalFilename();//너의..이름은?
//		
//		//파일복사
//		File file = new File(filePath,fileName);//파일생성
//		try {
//			FileCopyUtils.copy(img.getInputStream(),new FileOutputStream(file));
////		} catch (FileNotFoundException e) {
////			e.printStackTrace();
//		} catch (IOException e) {//얘가 부모라서 위쪽 애도 들어올수있음
//			e.printStackTrace();
//		}
//		
//		
		//아직 DTO에 파일이름못들어감...
		
		//이번엔 실제폴더에 직접올려보기
		String filePath = session.getServletContext().getRealPath("/storage");
		//String fileName = img.getOriginalFilename();
		String fileName;
		
		//File file = new File(filePath,fileName);
		File file;
		if(img[0]!=null) {
			fileName=img[0].getOriginalFilename();
			file = new File(filePath,fileName);
			
			try {
				img[0].transferTo(file);
	//		} catch (IllegalStateException e) {
	//			e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			imageboardDTO.setImage1(fileName);
		}else {
			imageboardDTO.setImage1("");
		}
		
		if(img[1]!=null) {
			fileName=img[1].getOriginalFilename();
			file = new File(filePath,fileName);
			
			try {
				img[1].transferTo(file);
	//		} catch (IllegalStateException e) {
	//			e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			imageboardDTO.setImage2(fileName);
		}else {
			imageboardDTO.setImage2("");
		}
		
		System.out.println(imageboardDTO);
		//이제 DB가면됨
		imageboardService.imageboardWrite(imageboardDTO);
		
	}
	*/
	//한번에 여러개의 파일올릴떄
	@PostMapping(value = "imageboardWrite")
	@ResponseBody
	public void imageboardWrite(@ModelAttribute ImageboardDTO imageboardDTO,
								@RequestParam("img[]") List<MultipartFile> list,
								HttpSession session) {
		String filePath = session.getServletContext().getRealPath("/storage");
		String fileName;
		File file;
		
		for(MultipartFile img:list) {
			fileName=img.getOriginalFilename();
			file = new File(filePath,fileName);
			
			try {
				img.transferTo(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
			imageboardDTO.setImage1(fileName);
			imageboardDTO.setImage2("");
			
			imageboardService.imageboardWrite(imageboardDTO);
		}
	}
	
	@GetMapping(value = "imageboardList")
	public String imageboardList(Model model,@RequestParam(required = false,defaultValue = "1") String pg) {
		model.addAttribute("display", "/imageboard/imageboardList.jsp");
		model.addAttribute("pg", pg);
		return "/index";
	}
	
//	
//	@PostMapping(value = "getImageboardList")
//	@ResponseBody
//	public Map<String,Object> getImageboardList(@RequestParam String pg){
//		return imageboardService.getImageboardList(pg);
//	}
//	
	@PostMapping(value = "getImageboardList")
	@ResponseBody
	public ModelAndView getImageboardList(@RequestParam String pg){
		//db
		List<ImageboardDTO> list = imageboardService.getImageboardList(pg);
		//페이징
		ImageboardPaging imageboardPaging = imageboardService.imageboardPaging(pg);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.addObject("imageboardPaging", imageboardPaging);
		mav.setViewName("jsonView");
		return mav;
	}
	
	@GetMapping(value = "imageboardView")
	public String imageboardView(@RequestParam String seq, @RequestParam String pg,Model model) {
		model.addAttribute("pg", pg);
		model.addAttribute("seq", seq);
		model.addAttribute("display", "/imageboard/imageboardView.jsp");
		return "/index";
	}
	
	@PostMapping(value = "getImageboardView")
	@ResponseBody
	public ImageboardDTO getImageboardView(@RequestParam String seq) {
		ImageboardDTO imageboardDTO = imageboardService.getImageboardView(seq);
		return imageboardDTO;
	}
}
