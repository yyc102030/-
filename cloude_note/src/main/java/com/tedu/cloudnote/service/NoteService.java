package com.tedu.cloudnote.service;

import com.tedu.cloudnote.util.NoteResult;

/**
 * 笔记的持久层
 * @author lenovo
 *
 */
public interface NoteService {
	/**
	 * 根据笔记本id查询所有的笔记
	 * @param notebook_id
	 * @return JSON
	 */
	public NoteResult findNotes(String notebook_id);
	
	/**
	 * 根据笔记的id查询笔记的内容
	 * @param noteId
	 * @return JSON
	 */
	public NoteResult findNoteBody(String noteId);
	
	/**
	 * 保存笔记
	 * @param noteId 笔记的id
	 * @param noteTitle 要保存笔记的标题
	 * @param noteBody 要保存笔记的内容
	 * @return JSON
	 * 
	 */
	public NoteResult saveNote(String noteId,String noteTitle,String noteBody);
	
	/**
	 * 添加笔记
	 * @param noteBookId
	 * @param noteId
	 * @param noteTitle
	 * @param userId
	 * @return JSON
	 */
	public NoteResult addNote(String noteBookId,String noteTitle,String userId);
	
	/**
	 * 将笔记删除到回收站
	 * @param noteId 笔记Id
	 * @return JSON
	 */
	public NoteResult rallBackNote(String noteId);
	
	
	/**
	 * 从回收站删除笔记
	 * @param noteId 笔记id
	 * @return
	 */
	public NoteResult deleteNote(String noteId);
	
	/**
	 * 移动笔记
	 * @param noteId
	 * @param noteBookId
	 * @return JSON
	 */
	public NoteResult moveNote(String noteId,String noteBookId);
	
	/**
	 * 分享笔记
	 * @param noteId
	 * @return JSON
	 */
	public NoteResult shareNote(String noteId);
	
	/**
	 * 
	 * @param map 参数map，map里面是包括：分享笔记的title
	 * @return JSON
	 */
	public NoteResult searchNote(String inValue);
	
	/**
	 * 加载更过笔记
	 * @param inValue 搜索的值
	 * @param start 其实页
	 * @param pagesize 每页显示的数目
	 * @return JSON
	 */
	public NoteResult loadMore(String inValue, int start, int pagesize);
	
	/**
	 * 显示搜索列表笔记的标题和内容
	 * @param cn_share_id
	 * @return JSON
	 */
	public NoteResult loadShareNoteBody(String cn_share_id);
	
	/**
	 * 查询回收站笔记
	 * @return note笔记集合
	 */
	public NoteResult loadRallBack();
	
	/**
	 * 在预览笔记模块显示回收站笔记的body和id
	 * @param noteId
	 * @return JSON
	 */
	public NoteResult LoadRallBackNoteBody(String noteId);
	
	/**
	 * 恢复回收站笔记
	 * @param noteId
	 * @return JSON
	 */
	public NoteResult replayNote(String noteId);
}
