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
	 * 根据笔记本的id，来查询笔记
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
}
