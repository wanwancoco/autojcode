package ${model.packageName}.action;

import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.icode.sys.action.BaseAction;
import com.icode.util.Page;
import ${model.packageName}.domain.${model.className};
import ${model.packageName}.service.${model.className}Service;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class ${model.className}Action extends BaseAction {

	private ${model.className} ${model.className?uncap_first};
	
	private Page pager;
	
	private int result;
	
	private String backurl;
	
	@Resource
	private ${model.className}Service ${model.className?uncap_first}Service;

	public String index(){
		pager = ${model.className?uncap_first}Service.search(pager);
		return "index";
	}
	
	public String add(){
		result = ${model.className?uncap_first}Service.insert(${model.className?uncap_first});
		backurl = "${model.className?uncap_first}/add.jsp";
		return "result-jsp";
	}
	
	public String delete(){
		result = ${model.className?uncap_first}Service.delete(${model.primaryMethIn});
		return "result-json";
	}
	
	public String intoUpdate(){
		${model.className?uncap_first} = ${model.className?uncap_first}Service.searchById(${model.primaryMethIn});
		return "intoUpdate";
	}
	
	public String update(){
		result = ${model.className?uncap_first}Service.update(${model.className?uncap_first});
		backurl = "front/${model.className?uncap_first}_intoUpdate.action${model.jspParam};
		return "result-jsp";
	}
	
	public ${model.className} get${model.className}(){
		return ${model.className?uncap_first};
	}
	public void set${model.className}(${model.className} ${model.className?uncap_first}){
		this.${model.className?uncap_first} = ${model.className?uncap_first};
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public Page getPager() {
		return pager;
	}
	public void setPager(Page pager) {
		this.pager = pager;
	}
	public String getBackurl() {
		return backurl;
	}
	public void setBackurl(String backurl) {
		this.backurl = backurl;
	}
}