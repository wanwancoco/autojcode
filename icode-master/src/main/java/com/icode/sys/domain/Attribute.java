package com.icode.sys.domain;

import org.apache.commons.lang.StringUtils;

public class Attribute {
	
	private String attrName;
	
	private String attrType;
	
	private String attrPrimary;
	
	private String attrComment;
	
	private String trid;
	
	private String sql;

	public String getAttrName() {
		return attrName;
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	public String getAttrType() {
		return attrType;
	}

	public void setAttrType(String attrType) {
		this.attrType = attrType;
	}

	public String getAttrPrimary() {
		return attrPrimary;
	}

	public void setAttrPrimary(String attrPrimary) {
		this.attrPrimary = attrPrimary;
	}

	public String getAttrComment() {
		return attrComment;
	}

	public void setAttrComment(String attrComment) {
		this.attrComment = attrComment;
	}

	public String getSql() {
		StringBuffer sb = new StringBuffer("");
		sb.append("`").append(StringUtils.lowerCase(attrName)).append("`");
		if("String".equals(attrType)){
			sb.append(" varchar(100)");
		}else if("Integer".equals(attrType)){
			sb.append(" int(11)");
		}else if("Long".equals(attrType)){
			sb.append(" bigint(20)");
		}else if("Byte".equals(attrType)){
			sb.append(" tinyint(4)");
		}else if("Float".equals(attrType)){
			sb.append(" float");
		}else if("Timestamp".equals(attrType)){
			sb.append(" timestamp NULL default CURRENT_TIMESTAMP ");
			if(StringUtils.isNotEmpty(attrComment)){
				sb.append("COMMENT '").append(attrComment).append("'");
			}
			sb.append(",");
		}
		if("1".equals(this.getAttrPrimary()) && !attrType.equals("Date")){
			sb.append(" NOT NULL");
			if("Long".equals(attrType) || "Integer".equals(attrType)){
				sb.append(" auto_increment ");
			}
			if(StringUtils.isNotEmpty(attrComment)){
				sb.append("COMMENT '").append(attrComment).append("'");
			}
			sb.append(",");
		}else{
			if(!attrType.equals("Date")){
				sb.append(" default NULL ");
				if(StringUtils.isNotEmpty(attrComment)){
					sb.append("COMMENT '").append(attrComment).append("'");
				}
				sb.append(",");
			}
		}
		sql = sb.toString();
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String getTrid() {
		return trid;
	}

	public void setTrid(String trid) {
		this.trid = trid;
	}

}
