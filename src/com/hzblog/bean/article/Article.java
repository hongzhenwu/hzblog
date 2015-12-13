package com.hzblog.bean.article;

import java.sql.Timestamp;

public class Article {

	/**主键id*/
	private int id;
	/**文章标题*/
	private String title;
	/** */
	private String content;
	/** */
	private Timestamp createDate;
	/** */
	private Timestamp modified;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public Timestamp getModified() {
		return modified;
	}

	public void setModified(Timestamp modified) {
		this.modified = modified;
	}
	
}
