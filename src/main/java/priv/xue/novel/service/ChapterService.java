package priv.xue.novel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import priv.xue.novel.core.FormatTxt;
import priv.xue.novel.dao.ChapterDao;
import priv.xue.novel.entity.Chapter;

@Service
public class ChapterService {
	private ChapterDao chapterDao = null;
	private FormatTxt formatTxt = null;

	@Autowired
	public void setFormatTxt(FormatTxt formatTxt) {
		this.formatTxt = formatTxt;
	}

	@Autowired
	public void setChapterDao(ChapterDao chapterDao) {
		this.chapterDao = chapterDao;
	}

	@Transactional
	public int saveChapter(Chapter chapter) {
		return chapterDao.saveChapter(chapter);
	}

	@Transactional
	public boolean uploadFromTxt(String filename) {
		return formatTxt.getChapterFromFile(filename);
	}

	public List getAllChapter(int novelId) {
		return chapterDao.getAllChapter(novelId);
	}

	public String[] getChapterById(int chapterId) {
		return chapterDao.getChapterById(chapterId);
	}

	public String[] getNextChapterByCurId(int novelId, int currentId) {
		return chapterDao.getNextChapterByCurId(novelId, currentId);
	}
}
