package com.tedu.cloudnote.service.impl;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tedu.cloudnote.dao.NoteDao;
import com.tedu.cloudnote.dao.ShareNoteDao;
import com.tedu.cloudnote.entity.Note;
import com.tedu.cloudnote.entity.ShareNote;
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
	
	@Resource(name="sharenotedao")
	private ShareNoteDao sharenotedao;
	
	/**
	 * 根据笔记本id查询所有的笔记
	 * @param notebook_id
	 * @return JSON
	 */
	public NoteResult findNotes(String notebook_id) {
		List<Map<String,Object>> list=
				notedao.findByNoteBookId(notebook_id);
		System.out.println("list:"+list);
		for(int i=0;i<list.size();i++){
			if(list.get(i).get("cn_note_type_id")==null){
				list.get(i).replace("cn_note_type_id", "");
			}
		}
		System.out.println("list:"+list);
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

	
	/**
	 * 删除笔记实现类
	 * @param noteId 笔记id
	 * @return
	 */
	public NoteResult deleteNote(String noteId) {
		NoteResult reult=new NoteResult();
		int rows=notedao.deleteNote(noteId);
		if(rows==0){
			//删除成功
			reult.setStatus(0);
			reult.setMsg("删除笔记成功!");
		}else{
			reult.setStatus(1);
			reult.setMsg("删除笔记失败!");
		}
		return reult;
	}

	
	/**
	 * 将笔记删除到回收站的实现类
	 * @param noteId 笔记Id
	 * @return JSON
	 */
	public NoteResult rallBackNote(String noteId) {
		System.out.println("222222222");
		NoteResult result=new NoteResult();
		int rows=notedao.rallbackNote(noteId);
		System.out.println("rows:"+rows);
		if(rows==0){//删除失败
			result.setStatus(1);
			result.setMsg("删除失败!");
		}else{
			result.setStatus(0);
			result.setMsg("删除成功！!");
		}
		return result;
	}

	/**
	 * 移动笔记
	 * @param noteId
	 * @param noteBookId
	 * @return JSON
	 */
	public NoteResult moveNote(String noteId, String noteBookId) {
		NoteResult result=new NoteResult();
		Note note=new Note();
		note.setCn_note_id(noteId);
		note.setCn_notebook_id(noteBookId);
		int rows=notedao.moveNote(note);
		if(rows==0){
			//移动笔记失败
			result.setStatus(1);
			result.setMsg("移动笔记失败！");
		}else{
			//移动笔记成功
			result.setStatus(0);
			result.setMsg("移动笔记成功！");	
		}
		return result;
	}

	/**
	 * 分享笔记
	 * @param noteId
	 * @return JSON
	 */
	public NoteResult shareNote(String noteId) {
		NoteResult result=new NoteResult();
		//根据noteId查询出笔记的全部内容
		Note note=notedao.findAllByNoteId(noteId);
		System.out.println("note:"+note);
		ShareNote sNote=new ShareNote();
		sNote.setCn_note_id(note.getCn_note_id());
		sNote.setCn_share_body(note.getCn_note_body());
		sNote.setCn_share_id(UUID.randomUUID().toString());
		sNote.setCn_share_title(note.getCn_note_title());
		System.out.println("sNote:"+sNote);
		int rows =sharenotedao.saveNote(sNote);
		System.out.println("!!!!!!!!!!!!!!!!!!!rows:"+rows);
		if(rows!=0){
			//保存成功,更新cn_note表中的typeId
			int rowNote=notedao.updateTypeId(noteId);
			System.out.println("rowNote:"+rowNote);
			if(rowNote!=0){
				//更新成功，也就意味着分享成功
				result.setStatus(0);
				result.setMsg("分享成功！");
			}else{
				result.setStatus(1);
				result.setMsg("分享失败！");
			}
		}
		
		return result;
	}

}
