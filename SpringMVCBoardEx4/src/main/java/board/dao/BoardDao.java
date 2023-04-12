package board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import board.dto.BoardDto;

@Repository
public class BoardDao {
	@Autowired
	private SqlSession session;
	
	String nameSpace = "board.dao.BoardDao.";
	
	public int getTotalCount() {
		return session.selectOne(nameSpace + "totalCountOfBoard");
	}
	
	public List<BoardDto> getPagingList(int start, int perpage) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", start);
		map.put("perpage", perpage);
		
		return session.selectList(nameSpace + "pagingListOfBoard", map);
	}
	
	public void insertBoard(BoardDto dto) {
		session.insert(nameSpace + "insertOfBoard", dto);
	}
	
	public void updateReadCount(int idx) {
		session.update(nameSpace + "updateReadCountOfBoard", idx);
	}
	
	public BoardDto selectOneBoard(int idx) {
		return session.selectOne(nameSpace + "selectOnBoardOfIdx", idx);
	}
	
	public void deleteBoard(int idx) {
		session.delete(nameSpace + "deleteBoardOfIdx", idx);
	}
	
	public void updateBoard(BoardDto dto) {
		session.update(nameSpace + "updateBoard", dto);
	}
}
