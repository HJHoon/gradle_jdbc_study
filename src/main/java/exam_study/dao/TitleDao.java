package exam_study.dao;

import java.sql.SQLException;
import java.util.List;

import exam_study.dto.Title;

public interface TitleDao {
	List<Title> selectTitleByAll() throws SQLException;
	int insertTitle(Title title) throws SQLException;
	int updateTitle(Title title) throws SQLException;
	int deleteTitle(Title title) throws SQLException;
}
