package priv.xue.novel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import priv.xue.novel.dao.NovelDao;
import priv.xue.novel.entity.Novel;

@Service
public class NovelService {
	NovelDao novelDao = null;

	@Autowired
	public void setNovelDao(NovelDao novelDao) {
		this.novelDao = novelDao;
	}
	
	@Transactional
	public int saveNovel(String novelName,String novelAuth) {
		return novelDao.saveNovel(novelName, novelAuth);
	}
	
	@Transactional
	public List<String[]> getNovelList(int page, int pageSize){
		return novelDao.getNovelList(page, pageSize);
	}
}
