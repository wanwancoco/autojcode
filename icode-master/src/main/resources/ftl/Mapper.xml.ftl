<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "mybatis-3-mapper.dtd" >
<mapper namespace="${model.packageName}.persistence.${model.className}Mapper" >

  <select id="selectByPrimaryKey" resultType="${model.className}" >
    select ${model.fieldColumn} from ${model.className?uncap_first} where ${model.primaryParam}
  </select>
  
  <select id="search" resultType="${model.className}">
    select ${model.fieldColumn} from ${model.className?uncap_first}
  </select>
  
  <insert id="insert" parameterType="${model.packageName}.domain.${model.className}" useGeneratedKeys="true" >
    insert into ${model.className?uncap_first} (${model.insertColumn}) values (${model.insertParam})
  </insert>
  
  <delete id="deleteByPrimaryKey">
    delete from ${model.className?uncap_first} where ${model.primaryParam}
  </delete>
  
  <update id="updateByPrimaryKey" parameterType="${model.packageName}.domain.${model.className}" >
    update ${model.className?uncap_first} set ${model.updateParam} where ${model.primaryParam}
  </update>
  
</mapper>