package imageboard.service;

import java.util.List;
import java.util.Map;

import imageboard.bean.ImageboardDTO;

public interface ImageboardService {

	public void imageboardWrite(ImageboardDTO imageboardDTO);

//	public Map<String, Object> getImageboardList(String pg);
	public List<ImageboardDTO> getImageboardList(String pg);
}
