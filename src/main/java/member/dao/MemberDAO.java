package member.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import member.bean.MemberDTO;

@Repository
@Transactional
public class MemberDAO {
	@Autowired
	private SqlSession sqlSession;

	public Object login(Map<String, String> map) {
		return sqlSession.selectOne("memberSQL.login", map);
		
	}

}
