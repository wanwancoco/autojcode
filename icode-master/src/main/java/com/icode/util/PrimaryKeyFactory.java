package com.icode.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自动生成主键
 * @author lch
 *
 */
public class PrimaryKeyFactory {

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
	private static AtomicInteger uniqueId = new AtomicInteger(0);
	/**
	 * 主键开头
	 * @param head
	 * @return
	 */
	public static String generatePrimaryKey(String head){
		 Date d = new Date();
		 if(uniqueId.get() == 10000) uniqueId.set(0);
		 return head+sdf.format(d)+uniqueId.incrementAndGet();
	}
	
	public static long generatePrimaryKey(){
		 return Long.parseLong(sdf.format(new Date()) + "" + uniqueId.incrementAndGet());
	}

}
