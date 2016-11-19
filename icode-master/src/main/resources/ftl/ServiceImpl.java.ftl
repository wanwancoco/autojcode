package ${model.packageName}.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.icode.util.Page;
import ${model.packageName}.domain.${model.className};
import ${model.packageName}.persistence.${model.className}Mapper;
import ${model.packageName}.service.${model.className}Service;

@Service("${model.className?uncap_first}Service")
public class ${model.className}ServiceImpl implements ${model.className}Service {

	@Resource
	private ${model.className}Mapper ${model.className?uncap_first}Mapper;
	
	public ${model.className} searchById(${model.primaryDef}){
		return ${model.className?uncap_first}Mapper.selectByPrimaryKey(${model.primaryParamIn});
	}
	
	@Transactional
	public int insert(${model.className} ${model.className?uncap_first}) {
		return ${model.className?uncap_first}Mapper.insert(${model.className?uncap_first});
	}
	
	@Transactional
	public int update(${model.className} ${model.className?uncap_first}) {
		return ${model.className?uncap_first}Mapper.updateByPrimaryKey(${model.className?uncap_first});
	}
	
	@Transactional
	public int delete(${model.primaryDef}){
		return ${model.className?uncap_first}Mapper.deleteByPrimaryKey(${model.primaryParamIn});
	}
	
	public Page search(Page pager) {
		if(pager == null){
		  pager = new Page();
		}
		List<${model.className}> items = ${model.className?uncap_first}Mapper.search(pager);
		pager.setDatas(items);	  
		return pager;
	}

}