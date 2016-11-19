package com.icode.util.gencode;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import com.icode.sys.domain.Attribute;
import com.icode.util.FreemarkerUtil;
import com.icode.util.PrimaryKeyFactory;

/**
 * Action代码产生器,根据Action类模板用FreeMarker产生Action代码.
 * <br>模板例子请参照config目录下的action.ftl
 * @author HuangYu , 2013.9 modify by liuchao
 */
public class GenMainWeb {

    //private Configuration cfg;
    // private String templateDir = "F:/workspace-64/frame/GenTemplate";
    private String webtmpl = "F:/workspace-64/frame/GenTemplate";
    //TODO 修改输出位置
    private String outputDir = "F:/workspace-64/frame/WebContent/download/code/";
    
    public String uuid;
    
    public GenMainWeb() throws IOException{
    }
    
    public GenMainWeb(String realPath) throws IOException{
    	uuid = PrimaryKeyFactory.generatePrimaryKey("");
    	outputDir = realPath+"download/code/"+uuid+"/";
    	//templateDir = realPath + "WEB-INF/classes/ftl";
    	webtmpl = realPath + "WEB-INF/classes/web-tmpl/WebContent";
    }

    public void setOutputDir(String outputDir) {
        this.outputDir = outputDir;
    }

    /*****
     * 根据Model类产生Struts2配置代码
     * @param className Model类的名字，如：User
     * @param packageName 模块包的名字，如：com.sys
     */
    public void generateSxml(String className,String packageName,String filename) throws IOException{
    	String classpath = packageName.replace(".", "/");
    	classpath = this.outputDir + "src/";

    	JavaModel model=new JavaModel();
    	model.setClassName(className);
    	model.setPackageName(packageName);
    	model.setWeb(true);
        // Build the data-model
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("model", model);
        
        FileUtils.forceMkdir(new File(classpath));
        FreemarkerUtil.getInstance().fprint(filename + ".ftl", data, classpath+filename);
    }
    /*****
     * 根据Model类产生Action Service Mapper类代码，如UserAction.java
     * @param className Model类的名字，如：User
     * @param packageName 模块包的名字，如：com.sys
     * @param type Model类的类型，如：Action Service ServiceImpl
     */
    public void generateJava(String className,String packageName,String type,List<Attribute> attrs) throws IOException{
    	String classpath = packageName.replace(".", "/");
    	if("Service".equals(type)){
    		classpath = this.outputDir + "src/" + classpath + "/service/";
    	}else if("ServiceImpl".equals(type)){
    		classpath = this.outputDir + "src/" + classpath + "/service/impl/";
    	}else if("Action".equals(type)){
    		classpath = this.outputDir + "src/" + classpath + "/action/";
    	}else if("Mapper".equals(type)){
    		classpath = this.outputDir + "src/" + classpath + "/persistence/";
    	}
    	JavaModel model=new JavaModel();
    	model.setClassName(className);
    	model.setPackageName(packageName);
    	model.setWeb(true);
    	model.setAttrs(attrs);
        // Build the data-model
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("model", model);
        
        FileUtils.forceMkdir(new File(classpath));
        FreemarkerUtil.getInstance().fprint(type + ".java.ftl", data,classpath + className + type +".java");
        
    }
    /*****
     * 根据Model类产生MapperXml代码，如UserAction.java
     * @param className Model类的名字，如：User
     * @param packageName 模块包的名字，如：com.sys
     * @throws ClassNotFoundException 
     * @throws SecurityException 
     */
    public void generateMapperXml(String className,String packageName,List<Attribute> attrs) throws Exception{
    	String classpath = packageName.replace(".", "/");
    	classpath = this.outputDir + "src/" + classpath + "/persistence/";
    	
    	JavaModel model=new JavaModel();
    	model.setClassName(className);
    	model.setPackageName(packageName);
    	model.setAttrs(attrs);
    	model.setWeb(true);
        // Build the data-model
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("model", model);
        
        FileUtils.forceMkdir(new File(classpath));
        FreemarkerUtil.getInstance().fprint("Mapper.xml.ftl", data, classpath+className +"Mapper.xml");
    }
    /*****
     * 根据Model类产生Action Service Mapper类代码，如UserAction.java
     * @param className Model类的名字，如：User
     * @param packageName 模块包的名字，如：com.sys
     * @param type Model类的类型，如：Action Service ServiceImpl
     */
    public void generateJsp(String className,String packageName,String type,List<Attribute> attrs) throws Exception{
    	String classpath  = "";
    	if(type.equals("welcome.jsp")){
    		classpath = this.outputDir + "WebContent/";
    	}else{
    		classpath = this.outputDir + "WebContent/" + StringUtils.lowerCase(className) + "/";
    	}
    	
    	JavaModel model=new JavaModel();
    	model.setClassName(className);
    	// get Class Attr
    	model.setAttrs(attrs);
    	model.setWeb(true);
    	//Map<String,String> pager = new HashMap<String,String>();
    	//pager.put("total", "${pager.total}");
    	//pager.put("offset", "${pager.offset}");
        // Build the data-model
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("model", model);
       // data.put("contextPath","${contextPath}");
       // data.put("pager", pager);
        //data.put("pageUrl", "${pageUrl}");
        //data.put("currentPageTest", "${currentPageNumber == pageNumber}");
       // data.put("pageNumber","${pageNumber}");
       // data.put("backurl", "${backurl}");
       // data.put("result", "${result}");
        data.put("el", "$");
        // Get the templat object
        FileUtils.forceMkdir(new File(classpath));
        String filename = "";
        if(type.equals("welcome.jsp")){
        	filename = "index.jsp";
        }else{
        	filename = type;
        }
        FreemarkerUtil.getInstance().fprint(type + ".ftl", data, classpath+filename);
    }
    /*****
     * 根据Model类产生Struts2配置代码
     * @param className Model类的名字，如：User
     * @param packageName 模块包的名字，如：com.sys
     */
    public void autoGenerate(String className,String packageName,List<Attribute> attrs) throws Exception {
    	
    	File source = new File(webtmpl);
        File destination = new File(outputDir);
        if (!destination.exists())
        {
            destination.mkdirs();
        }
        FileUtils.copyDirectoryToDirectory(source, destination);
    	
    	generateJavaClass(className,packageName,attrs);
    	generateJava(className,packageName,"Mapper",attrs);
    	generateJava(className,packageName,"Service",attrs);
        generateJava(className,packageName,"ServiceImpl",attrs);
        generateJava(className,packageName,"Action",attrs);
        generateMapperXml(className,packageName,attrs);
        System.out.println("Java代码已生成，位置:"+outputDir + "src/");
        generateJsp(className,packageName,"index.jsp",attrs);
        generateJsp(className,packageName,"add.jsp",attrs);
        generateJsp(className,packageName,"update.jsp",attrs);
        generateJsp(className,packageName,"result.jsp",attrs);
        generateJsp(className,packageName,"welcome.jsp",attrs);
        System.out.println("Jsp代码已生成，位置:"+outputDir + "WebContent/");
        generateSxml(className,packageName,"struts.xml");
        generateSxml(className,packageName,"app-bean.xml");
        generateSxml(className,packageName,"app-config.xml");
        generateSql(className,packageName,attrs);
    }
    
	private void generateSql(String className, String packageName,
			List<Attribute> attrs) throws Exception {
		String classpath = packageName.replace(".", "/");
		classpath = this.outputDir + "db/";
  
    	JavaModel model=new JavaModel();
    	model.setClassName(className);
    	model.setPackageName(packageName);
    	model.setAttrs(attrs);
    	model.setWeb(true);
        // Build the data-model
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("model", model);
        
        FileUtils.forceMkdir(new File(classpath));
        FreemarkerUtil.getInstance().fprint("sql.ftl", data, classpath + className +".sql");
	}

	public void generateJavaClass(String className, String packageName,
			List<Attribute> attrs) throws Exception {
		String classpath = packageName.replace(".", "/");
		classpath = this.outputDir + "src/" + classpath + "/domain/";
  
    	JavaModel model=new JavaModel();
    	model.setClassName(className);
    	model.setPackageName(packageName);
    	model.setAttrs(attrs);
    	model.setWeb(true);
        // Build the data-model
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("model", model);
        
        FileUtils.forceMkdir(new File(classpath));
        FreemarkerUtil.getInstance().fprint("Domain.java.ftl", data, classpath + className +".java");
	}
	
    /****
     * 测试，根据Model类生成相应的Action
     */
    public static void main(String[] args) throws Exception {
        //GenMain gen = new GenMain();
        //TODO 修改包名 类名
        //gen.autoGenerate("Project","com.icode.team");
    }

}
