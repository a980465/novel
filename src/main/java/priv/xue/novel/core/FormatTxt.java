package priv.xue.novel.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.print.attribute.standard.MediaName;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import priv.xue.novel.entity.Chapter;
import priv.xue.novel.service.ChapterService;
import priv.xue.novel.service.NovelService;

@Repository
public class FormatTxt {
	ChapterService chapterService = null;
	NovelService novelService = null;

	@Autowired
	public void setNovelService(NovelService novelService) {
		this.novelService = novelService;
	}

	@Autowired
	public void setChapterService(ChapterService chapterService) {
		this.chapterService = chapterService;
	}

	private boolean isTitile(String content) {
		String regEx = "^[\\u0391-\\uFFE5]{0,}[\\s]{0,}第[\\u0391-\\uFFE5]+章[\\s]{0,}[\\u0391-\\uFFE5]{0,}[\\s]{0,}";
		String regEx1 = "^[\\u0391-\\uFFE5]+(.*?)";
		Pattern pattern = Pattern.compile(regEx1);
		Matcher matcher = pattern.matcher(content);
		return matcher.matches();
	}

	public boolean getChapterFromFile(String fileName) {
		EncodingDetect detect = new EncodingDetect();
		String encoding = detect.detect(fileName);
		Chapter chapter = new Chapter();
		String lastChapterTitle = "";
		String content = "";
		FileInputStream in = null;
		InputStreamReader reader = null;
		BufferedReader bufferedReader = null;
		try {
			in = new FileInputStream(fileName);
			reader = new InputStreamReader(in, encoding);
			bufferedReader = new BufferedReader(reader);
			String str = "";
			int i = 0;
			String novelName = bufferedReader.readLine();
			int novelId = saveNovel(novelName);
			if(novelId <= 0) return false;
			while ((str = bufferedReader.readLine()) != null) {
				boolean isTile = false;
				if (isTitile(str) && false == content.equals("")) {
					i++;
					chapter.setTitle(lastChapterTitle);
					chapter.setContent(content);
					chapter.setNovel(novelId);
					saveChapter(chapter);
					lastChapterTitle = str;
					isTile = true;
					content = "";
				}
				if (false == isTile) {
					content += str;
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("找不到指定文件");
		} catch (IOException e) {
			System.out.println("读取文件失败");
		} finally {
			try {
				if (bufferedReader instanceof BufferedReader)
					bufferedReader.close();
				if (reader instanceof InputStreamReader)
					reader.close();
				if (in instanceof FileInputStream)
					in.close();
			} catch (Exception e) {
				System.err.println("关闭流失败");
			}
		}
		return true;
	}

	private int saveChapter(Chapter chapter) {
		return chapterService.saveChapter(chapter);
	}

	private int saveNovel(String novelName) {
		return novelService.saveNovel(novelName, "");
	}

	public static void main(String[] args) {
		String string = "定时发送 定时发  送";
		FormatTxt formatTxt = new FormatTxt();

		System.out.println(formatTxt.isTitile(string));
	}
}
