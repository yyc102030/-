package com.tedu.cloudnote.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tedu.cloudnote.entity.NoteBook;

/**
 * 笔记本信息表
 * @author lenovo
 *
 */
@Repository("notebookdao")
public interface NoteBookDao {
	
	/**
	 * 根据登陆用过户的ID，查询他的笔记本
	 * @param id 用户ID
	 * @return 笔记本的集合
	 */
	public List<NoteBook> findByUserId(String id);
}
