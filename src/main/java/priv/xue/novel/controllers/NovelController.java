package priv.xue.novel.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mysql.jdbc.ResultSetInternalMethods;

import priv.xue.novel.service.ChapterService;
import priv.xue.novel.service.NovelService;

@Controller
@RequestMapping(value = "novel")
public class NovelController {
	NovelService novelService = null;
	ChapterService chapterSvc = null;

	@Autowired
	public void setNovelService(NovelService novelService) {
		this.novelService = novelService;
	}

	@Autowired
	public void setChapterSvc(ChapterService chapterSvc) {
		this.chapterSvc = chapterSvc;
	}

	@RequestMapping(value = "novelList.html")
	public ModelAndView novelList(@RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "10") int pageSize) {
		List list = novelService.getNovelList(pageNo, pageSize);
		return new ModelAndView("novel/novellist", "novelList", list);
	}

	@RequestMapping(value = "chapterList.html")
	public ModelAndView chapterList(@RequestParam int novelId) {
		List list = chapterSvc.getAllChapter(novelId);
		return new ModelAndView("novel/chapterlist", "chapterlist", list);
	}
	
	@RequestMapping(value="content.html")
	public ModelAndView content(@RequestParam int chapterId) {
		String[] content = chapterSvc.getChapterById(chapterId);
		String[] contentList = formatContent(content[2]);
		ModelAndView model = new ModelAndView();
		model.addObject("content", content);
		model.addObject("contentList", contentList);
		model.setViewName("novel/content");
		return model;
	}
	
	private String[] formatContent(String content) {
		String[] res = content.split("\\s+");
		return res;
	}
	
	@RequestMapping(value="nextChapter.html")
	public ModelAndView nextChapter(@RequestParam int novelId, @RequestParam int currentId) {
		String[] content = chapterSvc.getNextChapterByCurId(novelId, currentId);
		String[] contentList = formatContent(content[2]);
		ModelAndView model = new ModelAndView();
		model.addObject("content", content);
		model.addObject("contentList", contentList);
		model.setViewName("novel/content");
		return model;
	}
}
