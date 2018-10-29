package priv.xue.novel.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Chapter {
	private String title = "";
	private String content = "";
	private int novel = 0;
	private String ctime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
	private String utime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getNovel() {
		return novel;
	}
	public void setNovel(int novel) {
		this.novel = novel;
	}
	public String getUtime() {
		return utime;
	}
	public void setUtime(String utime) {
		this.utime = utime;
	}
	public String getCtime() {
		return ctime;
	}
	public void setCtime(String ctime) {
		this.ctime = ctime;
	}
}
