package imageboard.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import imageboard.bean.ImageboardDTO;

@Repository
@Transactional
public class ImageboardDAOImpl implements ImageboardDAO {
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void imageboardWrite(ImageboardDTO imageboardDTO) {
		System.out.println(imageboardDTO);
		sqlSession.insert("imageboardSQL.imageboardWrite", imageboardDTO);
	}

	@Override
	public List<ImageboardDTO> getImageboardList(Map<String, Integer> map) {
		return sqlSession.selectList("imageboardSQL.getImageboardList", map);
	}

	@Override
	public int getTotalA() {
		return sqlSession.selectOne("imageboardSQL.getTotalA");
	}

	@Override
	public ImageboardDTO getImageboardView(String seq) {
		return sqlSession.selectOne("imageboardSQL.getImageboardView", Integer.parseInt(seq));
	}

	@Override
	public void imageboardDelete(String seq) {
		sqlSession.delete("imageboardSQL.imageboardDelete",Integer.parseInt(seq));
	}

}
