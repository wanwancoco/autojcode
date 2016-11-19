package com.icode.sys.service;
import java.util.List;

import com.icode.sys.domain.Attribute;

public interface CodeService {

	String generateCode(String realPath ,String packageName, String className,
			List<Attribute> attrs);
	
}