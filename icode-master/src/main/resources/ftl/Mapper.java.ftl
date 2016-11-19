package ${model.packageName}.persistence;

import java.util.List;
import com.icode.util.Page;
import org.apache.ibatis.annotations.Param;
import ${model.packageName}.domain.${model.className};

public interface ${model.className}Mapper {
	
    int deleteByPrimaryKey(${model.mapperParam});

    int insert(${model.className} ${model.className?uncap_first});

    ${model.className} selectByPrimaryKey(${model.mapperParam});

    int updateByPrimaryKey(${model.className} ${model.className?uncap_first});

	List<${model.className}> search(Page pager);
	
}