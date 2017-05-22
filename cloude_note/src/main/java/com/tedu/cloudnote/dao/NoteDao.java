package com.tedu.cloudnote.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tedu.cloudnote.entity.Note;

/**
 * 笔记持久层
 * @author lenovo
 *
 */
@Repository("notedao")
public interface NoteDao {
	
	/**
	 * 根据笔记本的id，来查询笔记，查询的，只是status=1的
	 * @param id
	 * @return 每一个笔记本下面的所有笔记
	 */
	public List<Map<String,Object>> findByNoteBookId(String id);
	
	/**
	 * 根据笔记的id查询笔记的内容
	 * @param noteId
	 * @return 返回的Map中存放着note_body
	 */
	public Note findNoteBody(String noteId);
	
	/**
	 * 保存笔记
	 * @param note 笔记类型
	 * @return 保存成功返回1
	 */
	public int saveNote(Note note);
	
	/**
	 * 增添加笔记
	 * @param note 
	 * @return 返回处理的结果
	 */
	public int addNote(Note note);
	
	/**
	 * 删除笔记，将其让如回收站
	 * 将note_status_id改为2
	 * @param noteId 
	 * @return 影响的记录条数
	 */
	public int rallbackNote(String noteId);
	
	/**
	 * 删除笔记
	 * @param noteId 想要删除的笔记的id
	 * @return 被影响的记录
	 */
	public int deleteNote(String noteId);
	
	/**
	 * 移动笔记
	 * @param noteId 
	 * @param noteBookId
	 * @return 移动的结果
	 */
	public int moveNote(Note note);
	
	/**
	 * 根据笔记的id,查询该笔记的所有内容
	 * @param noteId
	 * @return Note 笔记
	 */
	public Note findAllByNoteId(String noteId);
	
	/**
	 * 分享笔记时，需要将note_type
	 * @param noteId
	 * @return 处理的结果 1代表成功
	 */
	public int updateTypeId(String noteId);
}
