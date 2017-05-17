package com.tedu.cloudnote.service;

import com.tedu.cloudnote.util.NoteResult;

/**
 * 笔记本列表的服务层
 * @author lenovo
 *
 */
public interface NoteBookService {

	/**
	 * 根据用户的id来查询属于他的笔记本列表
	 * @param user_id
	 * @return 笔记本列表
	 */
	public NoteResult loadUserNoteBooks(String user_id);
}
