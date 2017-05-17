package com.tedu.cloudnote.util;

import java.io.Serializable;
/**
 * 结果对象
 * @author lenovo
 *{"status":0,"msg":"成功","data":数据}
 */
public class NoteResult implements Serializable{
	
	private static final long serialVersionUID = 4145171032985843264L;
	private int status;//返回的状态 0代表成功，1代表失败
	private String msg;//返回的状态信息
	private Object data;//返回的数据
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
}
