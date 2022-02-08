package board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import board.bean.BoardDTO;

@Repository
public class BoardDAOmybatis implements BoardDAO {
	@Autowired
	SqlSession sqlSession;
	@Override
	public void boardWrite(Map<String, String> map) {
		sqlSession.insert("boardSQL.boardWrite", map);
	}
	@Override
	public List<BoardDTO> getBoardList(Map<String, Integer> map) {
		return sqlSession.selectList("boardSQL.getBoardList", map);
	}
	@Override
	public int getTotalA() {
		return sqlSession.selectOne("boardSQL.getTotalA");
	}
	@Override
	public BoardDTO getBoardView(String seq) {
		return sqlSession.selectOne("boardSQL.getBoardView", seq);
	}

}
