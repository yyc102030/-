package com.tedu.cloudnote.dao;

import java.util.List;
import java.util.Map;

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
	
	/**
	 * 增加笔记本
	 * @param noteBookName 笔记本名字
	 * @return 返回增加的结果 1 表示成功
	 */
	public int addNoteBook(NoteBook noteBook);
	
	/**
	 * 删除笔记本
	 * @param noteBookId
	 * @return 处理结果集
	 */
	public int deleteBook(String noteBookId);
	
	/**
	 * 更改笔记本的名字
	 * @param noteBookId
	 * @return 处理结果集
	 */
	public int renameBook(Map<String,Object> map);
}
