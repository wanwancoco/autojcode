
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `${model.className?lower_case}`
-- ----------------------------
DROP TABLE IF EXISTS `${model.className?lower_case}`;
CREATE TABLE `${model.className?lower_case}` (
<#list model.attrs as attr>
  ${attr.sql}
</#list>
  PRIMARY KEY  (${model.primaryKey})
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
