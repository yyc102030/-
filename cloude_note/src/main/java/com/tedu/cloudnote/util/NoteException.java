package com.tedu.cloudnote.util;

/**
 * 密码加密异常
 * @author lenovo
 *
 */
public class NoteException extends RuntimeException{

	private static final long serialVersionUID = -9123011676814150967L;

	public NoteException(
			String msg,Throwable t){
		super(msg,t);
	}
}
