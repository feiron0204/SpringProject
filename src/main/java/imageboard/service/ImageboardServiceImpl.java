package imageboard.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imageboard.bean.ImageboardDTO;
import imageboard.bean.ImageboardPaging;
import imageboard.dao.ImageboardDAO;

@Service
public class ImageboardServiceImpl implements ImageboardService{
	@Autowired
	private ImageboardDAO imageboardDAO;
	@Autowired
	private ImageboardPaging imageboardPaging;
	
	@Override
	public void imageboardWrite(ImageboardDTO imageboardDTO) {
		imageboardDAO.imageboardWrite(imageboardDTO);
	}


//	@Override
//	public Map<String, Object> getImageboardList(String pg) {
//		Map<String,Object> map= new HashMap<String, Object>();
//		
//		Map<String,Integer> temp = new HashMap<String, Integer>();
//		temp.put("endNum", Integer.parseInt(pg)*3);
//		temp.put("startNum", temp.get("endNum")-2);
//		
//		map.put("list", imageboardDAO.getImageboardList(temp));
//		
//		imageboardPaging.setCurrentPage(Integer.parseInt(pg));
//		imageboardPaging.setPageBlock(3);
//		imageboardPaging.setPageSize(3);
//		imageboardPaging.setTotalA(imageboardDAO.getTotalA());
//		
//		imageboardPaging.makePagingHTML();
//		
//		
//		map.put("imageboardPaging", imageboardPaging.getPagingHTML().toString());
//		return map;
//	}
	@Override
	public List<ImageboardDTO> getImageboardList(String pg) {
		int endNum = Integer.parseInt(pg)*3;
		int startNum = endNum-2;
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("startNum", startNum);
		map.put("endNum", endNum);
		
		return imageboardDAO.getImageboardList(map);
	}


	@Override
	public ImageboardDTO getImageboardView(String seq) {
		return imageboardDAO.getImageboardView(seq);
	}
	
	@Override
	public ImageboardPaging imageboardPaging(String pg) {
		int totalA = imageboardDAO.getTotalA();
		
		imageboardPaging.setCurrentPage(Integer.parseInt(pg));
		imageboardPaging.setPageBlock(3);
		imageboardPaging.setPageSize(3);
		imageboardPaging.setTotalA(totalA);
		imageboardPaging.makePagingHTML();
		
		return imageboardPaging;
	}


	@Override
	public void imageboardDelete(String seq) {
		imageboardDAO.imageboardDelete(seq);
	}
	
	@Override
	public void imageboardDelete2(String[] seq) {
		Map<String, String[]> map = new HashMap<String, String[]>();
		map.put("seqArr", seq);
		imageboardDAO.imageboardDelete2(map);
	}
}
