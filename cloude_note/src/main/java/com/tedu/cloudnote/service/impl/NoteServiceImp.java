package com.tedu.cloudnote.service.impl;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tedu.cloudnote.dao.NoteDao;
import com.tedu.cloudnote.entity.Note;
import com.tedu.cloudnote.service.NoteService;
import com.tedu.cloudnote.util.NoteResult;
/**
 * 笔记服务层的实现类
 * @author lenovo
 *
 */
@Service("noteservice")
public class NoteServiceImp implements NoteService{
	
	@Resource(name="notedao")
	private NoteDao notedao;
	
	
	/**
	 * 根据笔记本id查询所有的笔记
	 * @param notebook_id
	 * @return JSON
	 */
	public NoteResult findNotes(String notebook_id) {
		List<Map<String,Object>> list=
				notedao.findByNoteBookId(notebook_id);
		System.out.println(list);
		NoteResult result=new NoteResult();
		if(list.isEmpty()){
			//查询失败
			result.setStatus(1);
			result.setMsg("该笔记本还未添加笔记");
			return result;
		}else{
			//查询成功
			result.setStatus(0);
			result.setMsg("查询成功");
			result.setData(list);
			return result;
		}
	}
	
	/**
	 * 根据笔记的id查询笔记的内容
	 * @param noteId
	 * @return JSON
	 */
	public NoteResult findNoteBody(String noteId) {
		Note note=
				notedao.findNoteBody(noteId);
		NoteResult result=new NoteResult();
		if(note==null){
			//如果内容为空
			result.setStatus(1);
			result.setMsg("该笔记还没有添加任何内容");
			return result;
		}else{
			//笔记有内容
			result.setStatus(0);
			result.setMsg("查询成功");
			result.setData(note);
			return result;
		}
	}

	/**
	 * 保存笔记实现类
	 * @param noteId 笔记的id
	 * @param noteTitle 要保存笔记的标题
	 * @param noteBody 要保存笔记的内容
	 * @return 受影响的记录
	 * 
	 */
	public NoteResult saveNote(String noteId, String noteTitle, String noteBody) {
		NoteResult result=new NoteResult();
		Note note=new Note();
		note.setCn_note_id(noteId);
		note.setCn_note_title(noteTitle);
		note.setCn_note_body(noteBody);
		note.setCn_note_last_modify_time(System.currentTimeMillis());
		System.out.println(note);
		int i=notedao.saveNote(note);
		if(i==0){//保存失败
			result.setStatus(1);
			result.setMsg("系统繁忙");
			return result;
		}else{
			result.setStatus(0);
			result.setMsg("保存成功");
			return result;
		}
	}

	/**
	 * 添加笔记
	 * @param noteBookId
	 * @param noteId
	 * @param noteTitle
	 * @param userId
	 * @return JSON
	 */
	public NoteResult addNote(String noteBookId,String noteTitle, String userId) {
		NoteResult result=new NoteResult();
		Note note=new Note();
		note.setCn_note_body("");
		note.setCn_note_create_time(System.currentTimeMillis());
		note.setCn_note_id(UUID.randomUUID().toString());
		note.setCn_note_last_modify_time(System.currentTimeMillis());
		note.setCn_note_status_id("1");
		note.setCn_note_title(noteTitle);
		note.setCn_note_type_id("");
		note.setCn_notebook_id(noteBookId);
		note.setCn_user_id(userId);
		int rows=notedao.addNote(note);
		if(rows==0){
			result.setStatus(1);
			result.setMsg("添加笔记失败");
		}else{
			result.setStatus(0);
			result.setMsg("添加笔记成功");
			result.setData(note);
		}
		return result;
	}

}
