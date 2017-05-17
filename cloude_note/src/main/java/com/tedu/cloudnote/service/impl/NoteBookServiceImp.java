package com.tedu.cloudnote.service.impl;

import java.util.ArrayList;
import java.util.List;

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

}
