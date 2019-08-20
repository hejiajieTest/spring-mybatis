package cn.ffcs.tsp.controller.manual;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.jdi.Method;

import cn.ffcs.tsp.entity.ManualInfo;
import cn.ffcs.tsp.hbase.entity.User;
import cn.ffcs.tsp.hbase.service.IHbaseTestService;
import cn.ffcs.tsp.mongo.MsgInfo;
import cn.ffcs.tsp.service.manual.IManualService;
import cn.ffcs.tsp.util.Page;
@RequestMapping("/manualInfo")
@Controller
//@Path
public class ManualInfoController {

	@RequestMapping("/index.do")
	public String index(HttpServletRequest request,HttpSession session){
		session = request.getSession();
		session.setAttribute("loginTest", "loginTest");
		System.out.println("11111");
		return "index" ;
	}
	
	 @RequestMapping("/getObj.do")
	 @ResponseBody
	 public ManualInfo getObj(){
		 ManualInfo m = new ManualInfo() ;
		 m.setDowloadUrl("11111");
		 m.setManualName("");
		 System.out.println("11111");
		return m;
	 }
	@Resource
	private IManualService manualService ;
	@Resource
	private IHbaseTestService hbaseService ;
	
	/**
	 * �������
	 * @param manualInfo
	 */
	@RequestMapping("/save.do")
	public void save(ManualInfo manualInfo){
		manualService.save(manualInfo);
	}
	
	/**
	 * ���²���
	 * @param manualInfo
	 */
	public void update(ManualInfo manualInfo){
		manualService.update(manualInfo);
	}
	
	
	/**
	 * ��ѯ����
	 * @return
	 */
	@RequestMapping("/findAll.do")
	@ResponseBody
	public List<ManualInfo> findAll(){
		return manualService.findAll();
	}
	
	
	/**
	 * 
	 * ����id��ѯ
	 * @param id
	 * @return
	 */
	public ManualInfo findById(String id){
		return manualService.findById(id);
	}
	
	/**
	 * 
	 * ����ids��ѯ
	 */
	
	 public List<ManualInfo> findByIds(String[] ids){
		 return manualService.findByIds(ids);
	 }
	 
	/**
	 * 
	 * ����������ѯ
	 * @param map
	 * @return
	 */
	public List<ManualInfo> findByMap(Map<String,Object> map){
		return manualService.findByMap(map);
	}
	 
	/**
	 * 
	 * ��ҳ��ѯ
	 * @param page
	 * @param map
	 * @return
	 */
	public List<ManualInfo> findByPage(Page page,Map<String,Object> map){
		map.put("startRowNum", page.getStartRowNum());
		map.put("pageSize", page.getPageSize());
		return manualService.findByPage(page,map);
	}
	
	/**
	 * 
	 * ��������
	 * @param list
	 */
	public void saveList(List<ManualInfo> list){
		manualService.saveList(list);
	}
	
	public void deleteByIds(List<Long> list){
		manualService.deleteByIds(list);
	}
	
	/**
	 * ����ids����
	 */
	public void updateByIds(List<String> list){
		manualService.updateByIds(list);
	}
	
	@RequestMapping("/testMongo.do")
	@ResponseBody
	public List<MsgInfo> testMongo(){
		return manualService.operateMongo();
	}
	
	@RequestMapping("/testHbase.do")
	@ResponseBody
	public List<User> testHbase(){
		return hbaseService.testHbase();
	}
	
	@RequestMapping(value="/testHbaseSave.do",method=RequestMethod.POST)
	@ResponseBody
	public boolean testHbaseSave(@RequestBody User user){
		return hbaseService.testHbaseSave(user);
	}
}
