package com.tedu.cloudnote.dao;

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
}
