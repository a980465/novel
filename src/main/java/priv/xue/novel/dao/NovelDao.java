package priv.xue.novel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.sun.org.apache.regexp.internal.recompile;

@Repository
public class NovelDao {

	private final static String SAVE_CHAPTER = "insert into novel(name,auth,ctime,utime) values(?,?,?,?)";
	private final static String QUERY_BY_PAGE = "select id,name,auth from novel limit ?,?";
	private JdbcTemplate template = null;

	@Autowired
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	public int saveNovel(String novelName, String novelAuth) {
		String time = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
		KeyHolder keyHolder = new GeneratedKeyHolder();
		int res = template.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(SAVE_CHAPTER, new String[] { "id" });
				ps.setString(1, novelName);
				ps.setString(2, novelAuth);
				ps.setString(3, time);
				ps.setString(4, time);
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().intValue();
	}

	public List<String[]> getNovelList(int page, int pageSize) {
		List list = new ArrayList<>();
		String[] novel = new String[4];
		Object[] objects = { new Integer(page), new Integer(pageSize) };
		list = template.query(QUERY_BY_PAGE, objects, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				novel[0] = rs.getString("id");
				novel[1] = rs.getString("name");
				novel[2] = rs.getString("auth");
				return novel;
			}
		});
		return list;
	}
}
