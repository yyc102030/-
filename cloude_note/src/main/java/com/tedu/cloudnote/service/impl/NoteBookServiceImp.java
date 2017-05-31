package com.tedu.cloudnote.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tedu.cloudnote.dao.NoteBookDao;
import com.tedu.cloudnote.entity.NoteBook;
import com.tedu.cloudnote.service.NoteBookService;
import com.tedu.cloudnote.util.NoteResult;

/**
 * 笔记本服务层的实现类
 * @author lenovo
 *
 */
@Service("notebookservice")
public class NoteBookServiceImp implements NoteBookService{

	//将NoteBookDao注入进来
	@Resource(name="notebookdao")
	private NoteBookDao notebookdao;
	
	/**
	 * 根据用户的id来查询属于他的笔记本列表
	 * @param user_id
	 * @return 笔记本列表
	 */
	public NoteResult loadUserNoteBooks(String user_id) {
		List<NoteBook> list=new ArrayList<NoteBook>();
		list=notebookdao.findByUserId(user_id);
		NoteResult result=new NoteResult();
		if(list.isEmpty()){
			result.setStatus(1);
			result.setMsg("该用户还没有笔记本");
			return result;
		}else{
			result.setStatus(0);
			result.setMsg("查询成功");
			result.setData(list);
			return result;
		}
	}

	/**
	 * 添加笔记本的实现类
	 * @param noteBookName 笔记本名字
	 * @param user_id 用户id
	 * @return
	 */
	public NoteResult addNoteBook(String noteBookName, String user_id) {
		NoteBook noteBook=new NoteBook();
		NoteResult result=new NoteResult();
		noteBook.setCn_notebook_id(UUID.randomUUID().toString());
		noteBook.setCn_notebook_name(noteBookName);
		noteBook.setCn_user_id(user_id);
		noteBook.setCn_notebook_createtime(new Timestamp(System.currentTimeMillis()));
		noteBook.setCn_notebook_desc("");
		noteBook.setCn_notebook_type_id("1");
		int rows=notebookdao.addNoteBook(noteBook);
		if(rows==1){
			//添加成功
			result.setStatus(0);
			result.setMsg("添加笔记本成功");
			result.setData(noteBook);
		}else{
			result.setStatus(1);
			result.setMsg("添加笔记本失败");
		}
		return result;
	}

	/**
	 * 删除笔记本
	 * @param noteBookId
	 * @return JSON
	 */
	public NoteResult deleteBook(String noteBookId) {
		System.out.println("noteBookId111:"+noteBookId);
		NoteResult result=new NoteResult();
		int rows=notebookdao.deleteBook(noteBookId);
		if(rows!=0){
			//删除成功
			result.setStatus(0);
			result.setMsg("删除成功");
		}else{
			result.setStatus(1);
			result.setMsg("删除失败");
		}
		return result;
	}

	/**
	 * 重命名笔记本
	 * @param noteBookId
	 * @return JSON
	 */
	public NoteResult renameBook(String noteBookId,String bookName) {
		NoteResult result=new NoteResult();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("noteBookId", noteBookId);
		map.put("bookName", bookName);
		int rows=notebookdao.renameBook(map);
		if(rows!=0){
			//改名成功
			result.setStatus(0);
			result.setMsg("改名成功");
		}else{
			result.setStatus(1);
			result.setMsg("改名失败");
		}
		return result;
	}

}
