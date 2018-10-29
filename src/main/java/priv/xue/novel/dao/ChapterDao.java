package priv.xue.novel.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sun.org.apache.regexp.internal.recompile;

import priv.xue.novel.entity.Chapter;

@Repository
public class ChapterDao {
	private JdbcTemplate template = null;

	private final static String SAVE_CHAPTER = "insert into chapter(title,content,novel,ctime,utime) values(?,?,?,?,?)";
	private final static String QUERY_BY_NOVELID = "select id,title from chapter where novel=?";
	private final static String QUERY_BY_CHAPTERID = "select id,title,content,novel from chapter where id=?";
	private final static String QUERY_NEXTCHAPTER = "select id,title,content,novel from chapter where novel=? and id >? limit 1";

	@Autowired
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	public int saveChapter(Chapter chapter) {
		try {
			Object[] objects = { chapter.getTitle(), chapter.getContent(), chapter.getNovel(), chapter.getCtime(),chapter.getUtime()};
			return template.update(SAVE_CHAPTER, objects);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public List getAllChapter(int novelId) {
		List list = new ArrayList<String[]>();
		list = template.query(QUERY_BY_NOVELID, new Object[]{novelId}, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				String[] chapter = new String[2];
				chapter[0] = rs.getString("id");
				chapter[1] = rs.getString("title");
				return chapter;
			}
		});
		return list;
	}
	
	public String[] getChapterById(int chapterId) {
		String[] chapter = new String[4];
		template.query(QUERY_BY_CHAPTERID, new Object[]{chapterId}, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				chapter[0] = rs.getString("id");
				chapter[1] = rs.getString("title");
				chapter[2] = rs.getString("content");
				chapter[3] = rs.getString("novel");
			}
		});
		return chapter;
	}
	
	public String[] getNextChapterByCurId(int novelId, int currentId) {
		String[] chapter = new String[4];
		template.query(QUERY_NEXTCHAPTER, new Object[]{novelId, currentId}, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				chapter[0] = rs.getString("id");
				chapter[1] = rs.getString("title");
				chapter[2] = rs.getString("content");
				chapter[3] = rs.getString("novel");
			}
		});
		return chapter;
	}
}
