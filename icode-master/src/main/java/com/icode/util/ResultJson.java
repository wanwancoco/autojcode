package com.icode.util;

import java.io.Serializable;

public class ResultJson implements Serializable {

	private static final long serialVersionUID = -1660528150122345907L;

	private int result;
	
	public ResultJson() {
		
	}
	
	public ResultJson(int result) {
		super();
		this.result = result;
	}
	

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}
	
}
