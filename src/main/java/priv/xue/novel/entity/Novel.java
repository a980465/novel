package priv.xue.novel.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Repository;

@Repository
public class Novel {
	private String name="";
	private String auth="";
	private String ctime =new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
	private String utime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	public String getCtime() {
		return ctime;
	}
	public void setCtime(String ctime) {
		this.ctime = ctime;
	}
	public String getUtime() {
		return utime;
	}
	public void setUtime(String utime) {
		this.utime = utime;
	}
}
