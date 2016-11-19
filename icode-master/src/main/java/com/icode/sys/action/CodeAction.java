package com.icode.sys.action;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.icode.sys.domain.Attribute;
import com.icode.sys.service.CodeService;

/***
 * 生成代码核心Action
 * @author liuchao
 */
@SuppressWarnings("serial")
@Controller("codeAction")
@Scope("prototype")
public class CodeAction extends BaseAction {

	private String packageName;
	
	private String className;
	
	private String[] attrName;
	
	private String[] attrType;
	
	private String[] attrPrimary;
	
	private String[] attrComment;
	
	private String[] attrLength;
	
	private String[] attrNull;
	
	private String folder;
	
	@Resource
	private CodeService codeService;
	
	private int result;
	
	private String filecode;
	
	private String fileName;
	
	private String content;
	
	private Attribute attr;
	
	private String backurl;
	
	public String generate(){
		String realPath = this.httpServletRequest.getSession().getServletContext().getRealPath("/");;
		realPath = StringUtils.replace(realPath, "\\", "/");
		List<Attribute> attrs = new ArrayList<Attribute>();
		for(int i = 0 ; i < attrName.length; i++){
			Attribute attr = new Attribute();
			attr.setAttrName(attrName[i]);
			attr.setAttrPrimary(attrPrimary[i]);
			attr.setAttrType(attrType[i]);
			attr.setAttrComment(attrComment[i]);
			attrs.add(attr);
		}
		this.folder = StringUtils.lowerCase(className);
		filecode = codeService.generateCode(realPath,packageName,className,attrs);
		String directory = StringUtils.replace(packageName, ".", "/");
		content = getFileContent(realPath+"download/code/"+filecode+"/src/"+directory+"/domain/"+className+".java");
		backurl = "/WEB-INF/jsp/code/export.jsp";
		return "result-jsp";
	}
	
	public String searchFileContent(){
		String realPath = this.httpServletRequest.getSession().getServletContext().getRealPath("/");;
		realPath = StringUtils.replace(realPath, "\\", "/");
		String directory = StringUtils.replace(packageName, ".", "/");
		if(fileName.endsWith(".java") || fileName.endsWith(".xml")){
			if(fileName.equals("struts.xml" ) || fileName.equals("app-bean.xml" ) || fileName.equals("app-config.xml" )){
				content = getFileContent(realPath+"download/code/"+filecode+"/src/"+fileName);
			}else{
				String type = "domain";
				if(fileName.endsWith("Action.java")){
					type = "action";
				}else if(fileName.endsWith("Mapper.java")){
					type = "persistence";
				}else if(fileName.endsWith("Service.java")){
					type = "service";
				}else if(fileName.endsWith("ServiceImpl.java")){
					type = "service/impl";
				}else if(fileName.endsWith("Mapper.xml")){
					type = "persistence";
				} 
				content = getFileContent(realPath+"download/code/"+filecode+"/src/"+directory+"/"+type+"/"+fileName);
			}
		}else if(fileName.endsWith(".sql")){
			content = getFileContent(realPath+"download/code/"+filecode+"/db/"+fileName);
		}else{
			content = getFileContent(realPath+"download/code/"+filecode+"/WebContent/"+StringUtils.lowerCase(className)+"/"+fileName);
		}
		backurl = "/WEB-INF/jsp/code/export.jsp";
		return "result-jsp";
	}
	
	public String getFileContent(String _fileName){
	        StringBuffer content = new StringBuffer();
	        try {
	            FileInputStream fis = new FileInputStream(_fileName);
	            InputStreamReader isr = new InputStreamReader(fis,"UTF-8");
	            BufferedReader br = new BufferedReader(isr);
	            String line = null;
	            while ((line = br.readLine()) != null) {
	            	content.append(line);
	            	String nextLine = System.getProperty("line.separator");
	            	content.append(nextLine); // 补上换行符
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return content.toString();
	}
	
	public String intoUpdate(){
		backurl = "/code/attr-update.jsp";
		return "result-jsp";
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String[] getAttrName() {
		return attrName;
	}

	public void setAttrName(String[] attrName) {
		this.attrName = attrName;
	}

	public String[] getAttrType() {
		return attrType;
	}

	public void setAttrType(String[] attrType) {
		this.attrType = attrType;
	}

	public String[] getAttrPrimary() {
		return attrPrimary;
	}

	public void setAttrPrimary(String[] attrPrimary) {
		this.attrPrimary = attrPrimary;
	}

	public String[] getAttrComment() {
		return attrComment;
	}

	public void setAttrComment(String[] attrComment) {
		this.attrComment = attrComment;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

	public String getFilecode() {
		return filecode;
	}

	public void setFilecode(String filecode) {
		this.filecode = filecode;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Attribute getAttr() {
		return attr;
	}

	public void setAttr(Attribute attr) {
		this.attr = attr;
	}

	public String getBackurl() {
		return backurl;
	}

	public void setBackurl(String backurl) {
		this.backurl = backurl;
	}

	public String[] getAttrLength() {
		return attrLength;
	}

	public void setAttrLength(String[] attrLength) {
		this.attrLength = attrLength;
	}

	public String[] getAttrNull() {
		return attrNull;
	}

	public void setAttrNull(String[] attrNull) {
		this.attrNull = attrNull;
	}
	
}