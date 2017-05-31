package com.tedu.cloudnote.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tedu.cloudnote.service.NoteService;
import com.tedu.cloudnote.util.NoteResult;

/**
 * 笔记控制器
 * @author lenovo
 *
 */
@Controller
@RequestMapping("/note")
public class NoteController {

	@Resource(name="noteservice")
	private NoteService noteservice;
	
	/**
	 * 查询每一个笔记本的所有笔记
	 * @return JSON
	 */
	@RequestMapping("/loadnotes.do")
	@ResponseBody
	public NoteResult loadNotes(String noteBookId){
		NoteResult result=
				noteservice.findNotes(noteBookId);
		return result;		
	}
	
	/**
	 * 处理查询笔记内容的请求
	 * @param noteId
	 * @return JSON
	 */
	@RequestMapping("/loadNoteBody.do")
	@ResponseBody
	public NoteResult loadNoteBody(String noteId){
		NoteResult result=
				noteservice.findNoteBody(noteId);
		return result;	
	}
	
	/**
	 * 保存笔记控制器
	 * @param noteId 笔记id
	 * @param noteTitle 笔记标题
	 * @param noteBody 笔记内容
	 * @return JSON
	 */
	@RequestMapping("/saveNote.do")
	@ResponseBody
	public NoteResult saveNote(String noteId, String noteTitle, String noteBody){
		NoteResult result=noteservice.saveNote(noteId, noteTitle, noteBody);
		return result;	
	}
	
	/**
	 * 添加笔记控制器
	 * @param noteBookId 笔记本id
	 * @param noteId 笔记id
	 * @param noteTitle 笔记标题
	 * @param userId 用户id
	 * @return JSON 返回JSON数据类型
	 */
	@RequestMapping("/addnote.do")
	@ResponseBody
	public NoteResult addNote(String noteBookId, String noteTitle, String userId){
		NoteResult result=noteservice.addNote(noteBookId, noteTitle, userId);
		return result;	
	};
	
	/**
	 * 
	 * 将笔记删除到回收站
	 * @param noteId 笔记Id
	 * @return JSON
	 */
	@RequestMapping("/rallbacknote.do")
	@ResponseBody
	public NoteResult rallBackNote(String noteId){
		System.out.println("11111111111111");
		NoteResult result=noteservice.rallBackNote(noteId);
		return result;	
	}
	
	/**
	 * 删除笔记控制器
	 * @param noteId
	 * @return JSON数据
	 */
	@RequestMapping("/deletenote.do")
	@ResponseBody
	public NoteResult deleteNote(String noteId){
		NoteResult result=noteservice.deleteNote(noteId);
		return result;		
	}
	
	/**
	 * 移动笔记的控制器
	 * @param noteId
	 * @param noteBookId
	 * @return JSON
	 */
	@RequestMapping("/movenote.do")
	@ResponseBody
	public NoteResult moveNote(String noteId,String noteBookId){
		NoteResult result=noteservice.moveNote(noteId, noteBookId);
		return result;
	}
	
	/**
	 * 分享笔记
	 * @param noteId
	 * @return JSON
	 */
	@RequestMapping("/sharenote.do")
	@ResponseBody
	public NoteResult shareNote(String noteId){
		NoteResult result=noteservice.shareNote(noteId);
		return result;	
	}
	
	/**
	 * 搜索笔记控制器
	 * @param inValue 搜索笔记的标题名
	 * @return json
	 */
	@RequestMapping("/searchnote.do")
	@ResponseBody
	public NoteResult searchNote(String inValue){
		System.out.println("inValue:"+inValue);
		NoteResult result=noteservice.searchNote(inValue);
		return result;		
	}
	
	/**
	 * 加载更多搜索结果的控制器
	 * @param inValue 搜索的值
	 * @param start 开始的页数
	 * @param pagesize 每页显示的数目
	 * @return JSON
	 */
	@RequestMapping("/searchloadmore.do")
	@ResponseBody
	public NoteResult loadMore(String inValue, int start, int pagesize){
		System.out.println("inValue:"+inValue+",start:"+start+",pagesize"+pagesize);
		NoteResult result=noteservice.loadMore(inValue, start, pagesize);
		return result;		
	}
	
	/**
	 * 点击搜索结果的li时，在预览笔记里面显示标题和内容
	 * @param cn_share_id 
	 * @return JSON
	 * 
	 */
	@RequestMapping("/loadsharenotebody.do")
	@ResponseBody
	public NoteResult loadShareNoteBody(String shareNoteId){
		NoteResult result=noteservice.loadShareNoteBody(shareNoteId);
		return result;		
	}
	
	/**
	 * 回收站笔记查询
	 * @return JSON
	 */
	@RequestMapping("/rallback.do")
	@ResponseBody
	public NoteResult loadRallBack(){
		NoteResult result=noteservice.loadRallBack();
		return result;		
	};
	
	/**
	 * 点击回收站笔记列表时，在预览笔记模块显示body和title
	 * 控制器
	 * @param noteId 笔记id
	 * @return JSON
	 */
	@RequestMapping("/loadrallbacknotebody.do")
	@ResponseBody
	public NoteResult LoadRallBackNoteBody(String noteId){
		NoteResult result=noteservice.LoadRallBackNoteBody(noteId);
		return result;		
	};
	
	/**
	 * 恢复回收站的笔记
	 * @param noteId
	 * @return JSON
	 */
	@RequestMapping("/replaynote.do")
	@ResponseBody
	public NoteResult replayNote(String noteId){
		NoteResult result=noteservice.replayNote(noteId);
		return result;		
	}
}
