package priv.xue.novel.controllers;

import java.io.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import priv.xue.novel.service.ChapterService;
import priv.xue.novel.service.NovelService;

@Controller
@RequestMapping(value="uploadchapter")
public class UploadChapterController {
	ChapterService chapterService = null;
	
	@Autowired
	public void setNovelService(NovelService novelService) {
		this.novelService = novelService;
	}

	NovelService novelService = null;
	
	@Autowired
	public void setChapterService(ChapterService chapterService) {
		this.chapterService = chapterService;
	}
	
	@RequestMapping(value = "uploadFromTxt.html")
	public ModelAndView uploadFromTxt(@RequestParam String filename) {
		File file = new File("D:\\"+filename);
		ModelAndView modelAndView = null;
		if(false == file.exists())
		{
			System.out.println(file.getAbsolutePath());
			modelAndView = new ModelAndView("uploadchapter/uploadfromtxt", "info", "文件不存在");
			return modelAndView;
		}
		chapterService.uploadFromTxt("D:\\\\将夜.txt");
		new ModelAndView("uploadchapter/uploadfromtxt", "info", "成功");
		return modelAndView;
	}
}
