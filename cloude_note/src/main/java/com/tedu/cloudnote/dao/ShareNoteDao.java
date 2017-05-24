package com.tedu.cloudnote.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tedu.cloudnote.entity.ShareNote;

/**
 * 分享笔记表cn_share的持久层
 * @author lenovo
 *
 */
@Repository("sharenotedao")
public interface ShareNoteDao {
	
	/**
	 * 当用户分享笔记时，将其添加到cn_share中
	 * @param sNote 分享的笔记
	 * @return 处理的结果
	 */
	public int saveNote(ShareNote sNote);
	
	/**
	 * 搜索分享笔记功能
	 * @param inValue 分享内容的
	 * @return ShareNote 集合
	 */
	public List<ShareNote> searchNote(Map<String,Object> map);
}
