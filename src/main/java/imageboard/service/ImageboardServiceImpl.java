package imageboard.service;

import java.util.HashMap;
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

	@Override
	public Map<String, Object> getImageboardList(String pg) {
		Map<String,Object> map= new HashMap<String, Object>();
		
		Map<String,Integer> temp = new HashMap<String, Integer>();
		temp.put("endNum", Integer.parseInt(pg)*3);
		temp.put("startNum", temp.get("endNum")-2);
		
		map.put("list", imageboardDAO.getImageboardList(temp));
		
		imageboardPaging.setCurrentPage(Integer.parseInt(pg));
		imageboardPaging.setPageBlock(3);
		imageboardPaging.setPageSize(3);
		imageboardPaging.setTotalA(imageboardDAO.getTotalA());
		
		imageboardPaging.makePagingHTML();
		
		
		map.put("imageboardPaging", imageboardPaging.getPagingHTML().toString());
		return map;
	}
}
