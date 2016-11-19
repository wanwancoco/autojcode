package com.icode.util.gencode;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;

import com.icode.sys.domain.Attribute;

public class JavaModel {
	
	private String packageName;
	
	private String className;
	
	private String implementsName;
	
	private List<Attribute> attrs;//前台赋值属性
	
	private boolean isWeb;
	
	private List<String> inputpackageList;
	
	private Field[] fields;
	
	private Field[] normalColumn;
	
	private String fieldColumn;
	
	private String insertColumn;
	
	private String insertParam;
	
	private String updateParam;
	
	private String primaryParam = "tbid = #{tbid}";
	
	private String primaryKey;
	
	private String primaryMethIn;
	
	private String primaryDef;
	
	private String primaryParamIn;
	
	private String mapperParam;
	
	private String jspParam;
	
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

	public List<String> getInputpackageList() {
		return inputpackageList;
	}

	public void setInputpackageList(List<String> inputpackageList) {
		this.inputpackageList = inputpackageList;
	}

	public String getImplementsName() {
		return implementsName;
	}

	public void setImplementsName(String implementsName) {
		this.implementsName = implementsName;
	}

	public Field[] getFields() {
		return fields;
	}

	public void setFields(Field[] fields) {
		this.fields = fields;
	}

	public String getFieldColumn() {
		StringBuffer column = new StringBuffer();
		if(isWeb){
			for(int i = 0 ; i < attrs.size(); i++){
				Attribute attr = attrs.get(i);
				if((i + 1) == attrs.size()){
					column.append("`").append(attr.getAttrName()).append("` ");
				}else{
					column.append("`").append(attr.getAttrName()).append("` ,");
				}
			}
		}else{
			for(int i = 0 ; i < fields.length ; i++){
				Field field = fields[i];
				if((i + 1) == fields.length){
					column.append("`").append(field.getName()).append("` ");
				}else{
					column.append("`").append(field.getName()).append("` ,");
				}
			}
		}
		this.fieldColumn = column.toString();
		return fieldColumn;
	}

	public void setFieldColumn(String fieldColumn) {
		this.fieldColumn = fieldColumn;
	}

	public String getInsertColumn() {
		StringBuffer column = new StringBuffer();
		if(isWeb){
			for(int i = 0 ; i < attrs.size(); i++){
				Attribute attr = attrs.get(i);
				if(attr.getAttrPrimary().equals("1") && ( attr.getAttrType().equals("Long") || attr.getAttrType().equals("Integer"))){
					continue;
				}
				if((i + 1) == attrs.size()){
					column.append("`").append(attr.getAttrName()).append("` ");
				}else{
					column.append("`").append(attr.getAttrName()).append("` ,");
				}
			}
		}else{
			for(int i = 0 ; i < fields.length ; i++){
				Field field = fields[i];
				if(!"tbid".equals(field.getName())){
					if((i + 1) == fields.length){
						column.append("`").append(field.getName()).append("` ");
					}else{
						column.append("`").append(field.getName()).append("` ,");
					}
				}
			}
		}
		this.insertColumn = column.toString();
		return insertColumn;
	}

	public void setInsertColumn(String insertColumn) {
		this.insertColumn = insertColumn;
	}

	public String getInsertParam() {
		StringBuffer param = new StringBuffer();
		if(isWeb){
			for(int i = 0 ; i < attrs.size(); i++){
				Attribute attr = attrs.get(i);
				if(attr.getAttrPrimary().equals("1") && ( attr.getAttrType().equals("Long") || attr.getAttrType().equals("Integer"))){
					continue;
				}
				if((i + 1) == attrs.size()){
					param.append("#{").append(attr.getAttrName()).append("}");
				}else{
					param.append("#{").append(attr.getAttrName()).append("},");
				}
			}
		}else{
			for(int i = 0 ; i < fields.length ; i++){
				Field field = fields[i];
				if(!"tbid".equals(field.getName())){
					if((i + 1) == fields.length){
						param.append("#{").append(field.getName()).append("}");
					}else{
						param.append("#{").append(field.getName()).append("},");
					}
				}
			}
		}
		this.insertParam = param.toString();
		return insertParam;
	}

	public void setInsertParam(String insertParam) {
		this.insertParam = insertParam;
	}

	public String getPrimaryParam() {
		if(isWeb){
			StringBuffer param = new StringBuffer();
			for(int i = 0 ; i < attrs.size(); i++){
				Attribute attr = attrs.get(i);
				if("1".equals(attr.getAttrPrimary())){
					if(i != 0){
						param.append(" and ");
					}
					param.append(" `").append(attr.getAttrName()).append("` = #{").append(attr.getAttrName()).append("}");
				}
			}
			this.primaryParam = param.toString();
		}
		return primaryParam;
	}

	public void setPrimaryParam(String primaryParam) {
		this.primaryParam = primaryParam;
	}

	public String getUpdateParam() {
		StringBuffer param = new StringBuffer();
		if(isWeb){
			for(int i = 0 ; i < attrs.size(); i++){
				Attribute attr = attrs.get(i);
				if(attr.getAttrPrimary().equals("1")){
					continue;
				}
				if((i + 1) == attrs.size()){
					param.append("`").append(attr.getAttrName()).append("` = #{").append(attr.getAttrName()).append("}");
				}else{
					param.append("`").append(attr.getAttrName()).append("` = #{").append(attr.getAttrName()).append("},");
				}
			}
		}else{
			for(int i = 0 ; i < fields.length ; i++){
				Field field = fields[i];
				if(!"tbid".equals(field.getName())){
					if((i + 1) == fields.length){
						param.append("`").append(field.getName()).append("` = #{").append(field.getName()).append("}");
					}else{
						param.append("`").append(field.getName()).append("` = #{").append(field.getName()).append("},");
					}
				}
			}
		}
		this.updateParam = param.toString();
		return updateParam;
	}

	public void setUpdateParam(String updateParam) {
		this.updateParam = updateParam;
	}

	public Field[] getNormalColumn() {
		List<Field> tempList = new ArrayList<Field>();
		for(int i = 0 ; i < fields.length ; i++){
			Field field = fields[i];
			if(!"tbid".equals(field.getName())){
				tempList.add(field);
			}
		}
		normalColumn = new Field[tempList.size()];
		for(int j = 0 ; j < tempList.size(); j++){
			normalColumn[j] = tempList.get(j);
		}
		for(Field f : normalColumn){
			System.out.println(f.getName());
		}
		return normalColumn;
	}

	public void setNormalColumn(Field[] normalColumn) {
		this.normalColumn = normalColumn;
	}

	public List<Attribute> getAttrs() {
		return attrs;
	}

	public void setAttrs(List<Attribute> attrs) {
		this.attrs = attrs;
	}

	public boolean isWeb() {
		return isWeb;
	}

	public void setWeb(boolean isWeb) {
		this.isWeb = isWeb;
	}

	public String getPrimaryKey() {
		if(isWeb){
			StringBuffer param = new StringBuffer();
			for(int i = 0 ; i < attrs.size(); i++){
				Attribute attr = attrs.get(i);
				if("1".equals(attr.getAttrPrimary())){
					if(param.length() > 0){
						param.append(" ,`").append(attr.getAttrName()).append("`");
					}else{
						param.append(" `").append(attr.getAttrName()).append("`");
					}
				}
			}
			this.primaryKey = param.toString();
		}
		return primaryKey;
	}

	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

	public String getPrimaryMethIn() {
		StringBuffer param = new StringBuffer();
		for(int i = 0 ; i < attrs.size(); i++){
			Attribute attr = attrs.get(i);
			if("1".equals(attr.getAttrPrimary())){
				if(param.length() > 0){
					param.append(" ,").append(StringUtils.uncapitalize(className)).append(".get").append(StringUtils.capitalize(attr.getAttrName())).append("()");
				}else{
					param.append(StringUtils.uncapitalize(className)).append(".get").append(StringUtils.capitalize(attr.getAttrName())).append("()");
				}
			}
		}
		this.primaryMethIn = param.toString();
		return primaryMethIn;
	}

	public void setPrimaryMethIn(String primaryMethIn) {
		this.primaryMethIn = primaryMethIn;
	}

	public String getPrimaryDef() {
		StringBuffer param = new StringBuffer();
		for(int i = 0 ; i < attrs.size(); i++){
			Attribute attr = attrs.get(i);
			if("1".equals(attr.getAttrPrimary())){
				if(param.length() > 0){
					param.append(" ,").append(attr.getAttrType()).append(" ").append(attr.getAttrName());
				}else{
					param.append(attr.getAttrType()).append(" ").append(attr.getAttrName());
				}
			}
		}
		this.primaryDef = param.toString();
		return primaryDef;
	}

	public void setPrimaryDef(String primaryDef) {
		this.primaryDef = primaryDef;
	}

	public String getPrimaryParamIn() {
		StringBuffer param = new StringBuffer();
		for(int i = 0 ; i < attrs.size(); i++){
			Attribute attr = attrs.get(i);
			if("1".equals(attr.getAttrPrimary())){
				if(param.length() > 0){
					param.append(" ,").append(attr.getAttrName());
				}else{
					param.append(attr.getAttrName());
				}
			}
		}
		this.primaryParamIn = param.toString();
		return primaryParamIn;
	}

	public void setPrimaryParamIn(String primaryParamIn) {
		this.primaryParamIn = primaryParamIn;
	}

	public String getMapperParam() {
		StringBuffer param = new StringBuffer();
		for(int i = 0 ; i < attrs.size(); i++){
			Attribute attr = attrs.get(i);
			if("1".equals(attr.getAttrPrimary())){
				if(param.length() > 0){
					param.append(",@Param(value=\"").append(attr.getAttrName())
					.append("\")").append(attr.getAttrType()).append(" ").append(attr.getAttrName());
				}else{
					param.append("@Param(value=\"").append(attr.getAttrName())
						.append("\")").append(attr.getAttrType()).append(" ").append(attr.getAttrName());
				}
			}
		}
		this.mapperParam = param.toString();
		return mapperParam;
	}

	public void setMapperParam(String mapperParam) {
		this.mapperParam = mapperParam;
	}

	public String getJspParam() {
		StringBuffer param = new StringBuffer();
		for(int i = 0 ; i < attrs.size(); i++){
			Attribute attr = attrs.get(i);
			if("1".equals(attr.getAttrPrimary())){
				if(param.length() > 0){
					param.append("+\"&").append(StringUtils.uncapitalize(this.className)).append(".").
					append(attr.getAttrName()).append("=\"+").append(StringUtils.uncapitalize(this.className)).append(".get")
					.append(StringUtils.capitalize(attr.getAttrName())).append("()");
				}else{
					param.append("?").append(StringUtils.uncapitalize(this.className)).append(".").
					append(attr.getAttrName()).append("=\"+").append(StringUtils.uncapitalize(this.className)).append(".get")
					.append(StringUtils.capitalize(attr.getAttrName())).append("()");
				}
			}
		}
		this.jspParam = param.toString();
		return jspParam;
	}

	public void setJspParam(String jspParam) {
		this.jspParam = jspParam;
	}
	
}