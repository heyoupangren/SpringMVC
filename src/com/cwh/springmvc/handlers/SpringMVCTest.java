package com.cwh.springmvc.handlers;

import com.cwh.springmvc.entites.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

@SessionAttributes(value = "user",types = String.class)
@RequestMapping("/springmvc")
@Controller
public class SpringMVCTest {

	private static final String SUCCESS ="success";

    @RequestMapping("/testForward")
    public String testForward(){
        System.out.println("testForward");
        return "forward:/index.jsp";
    }

	@RequestMapping("/testRedirect")
	public String testRedirect(){
        System.out.println("testRedirect");
        return "redirect:/index.jsp";
    }

	@RequestMapping("/testView")
	public String testView(){
        System.out.println("testView");
        return "helloView";
    }

	@RequestMapping("/testViewAndViewResolver")
	public String testViewAndViewResolver(){
		System.out.println("testViewAndViewResolver");
		return SUCCESS;
	}

	/**
	 * 1.��@ModelAttribute ��ǵķ���������ÿ��Ŀ�귽��ִ��֮ǰ�� SpringMVC ����
	 * 2.@ModelAttribute ע��Ҳ����������Ŀ�귽��POJO ���͵���Σ���value����ֵ�����µ����ã�
	 * 1).SpringMVC ��ʹ value ����ֵ�� implicitModel �в��Ҷ�Ӧ�Ķ������������ֱ�Ӵ��뵽Ŀ�귽��������С�
	 * 2).SpringMVC �� һ valueΪKey ��POJO���͵Ķ���Ϊ value�����뵽request��
	 * @param id
	 * @param map
	 */
	@ModelAttribute
	public  void getUser(@RequestParam(value = "id",required =false ) Integer id,
						 Map<String,Object> map){

		System.out.println("ModelAttribute method");
		if(id!= null){
			User user = new User(1,"Tom","123456",12,"tom@163.com");
			System.out.println("�����ݿ��л�ȡ���� user ��"+user);
			map.put("abc",user);
		}

	}

	/**
	 * �������̣�
	 * 1.ִ�� @ModelAttribute ע�����εķ����������ݿ���ȡ�����󣬰Ѷ�����뵽��Map �У���Ϊ��user
	 * 2.SpringMVC ��Map��ȡ��User���󣬲��ѱ����������������User����Ķ�Ӧ����
	 * 3.SpringMVC ������������Ŀ�귽���Ĳ���
	 *
	 * ע�⣺��@ModelAttribute ���εķ����У����뵽Map ʱ�ļ���Ҫ��Ŀ�귽��������͵ĵ�һ����ĸСд���ַ���һ�£�
	 *
	 *
	 *
	 * SpringMVC ȷ��Ŀ�귽�� POJO������εĹ���
	 * 1.ȷ��һ��key
	 * 1).��Ŀ�귽����POJO ���͵Ĳ���û��ʹ��@ModelAttribute ��Ϊ���Σ���keyΪ POJO��������һ����ĸСд
	 * 2).��ʹ����@ModelAttribute �����Σ���keyΪ@ModelAttribute ע���value ����ֵ
	 * 2.��implicitModel �в��� key��Ӧ�Ķ��������ڣ�����Ϊ��δ���
	 * 1).����@ModelAttribute ��ǵķ�������Map �б�������� key�� 1ȷ����key һ�£�����ȡ����
	 * 3.��implicitModel �в�����key ��Ӧ�Ķ������鵱ǰ��Handler �Ƿ�ʹ��@SessionAttributes ע�����Σ�
	 * ��ʹ���˸�ע�⣬��@SessionAttributes ע���value ����ֵ�а�����key������HttpSession������ȡkey��
	 * ��Ӧ��valueֵ����������ֱ�Ӵ��뵽Ŀ�귽��������С������������׳��쳣��
	 * 4.��Handler û�б�ʶ@SessionAttributes ע�����@SessionAttributes ע���valueֵ�в�����key����
	 * ��ͨ������������POJO ���͵Ĳ���������ΪĿ�귽���Ĳ���
	 * 5.SpringMVC ���Key ��POJO ���͵Ķ��󱣴浽implicitModel �У������ᱣ�浽request�С�
	 * @param user
	 * @return
	 */
	@RequestMapping("/testModelAttribute")
	public String testModelAttribute(User user){
		System.out.println("�޸� ��" +user);
		return SUCCESS;
	}
	/**
	 * @SessionAttribute ���˿���ͨ����������ָ����Ҫ�ŵ��Ự�е������⣨��ʵ����ʹ�õ���Value����ֵ
	 * ������ͨ��ģ�����ԵĶ�������ָ����Щģ��������Ҫ�ŵ��Ự�У���ʵ����ʹ�õ���types����ֵ
	 *
	 * ע�⣺��ע��ֻ�ܷ���������棬���������η���
	 */

	@RequestMapping("/testSessionAttribute")
	public String testSessionAttribute(Map<String ,Object> map){

		User user = new User("Tom","123456",12,"12@123.com");
		map.put("user",user);
		map.put("school","henanzhongyiyaodaxue");
		return SUCCESS;
	}
	/**
	 * Ŀ�귽��������� Map ����(ʵ����Ҳ������ Model ���ͻ��� ModelMap����)�Ĳ���
	 *
	 */
	@RequestMapping("/testMap")
	public String testMap(Map<String ,Object> map){
		map.put("names", Arrays.asList("Tom","Jerry","Mike"));
		return SUCCESS;

	}

	/**
	 *Ŀ�귽���ķ���ֵ������ModelAndView ����
	 * ���п��԰�����ͼ��ģ����Ϣ
	 * SpringMVC ��� ModelAndView �� model�е����ݷ��뵽 request ������У�
	 */
	@RequestMapping("/testModelAndView")
	public ModelAndView testModelAndView(){

		String viewName=SUCCESS;
		ModelAndView modelAndView =new ModelAndView(viewName);
		modelAndView.addObject("time",new Date());
		return modelAndView;
	}

	/**
	 *����ʹ��servlet ԭ����API��ΪĿ�귽���Ĳ��� ����֧����������
	 *
	 * HttpServletRequest
	 * HttpServletResponse
	 * HttpSession
	 * java.security.Principal
	 * Locale
	 * InputStream
	 * OutputStream
	 * Reader
	 * Writer
	 */
	@RequestMapping("/testServletAPI")
	public  void testServletAPI(HttpServletRequest request, HttpServletResponse response, Writer writer) throws IOException {
		System.out.println("Test ServletAPI " +request+" , "+response);
		writer.write("Test ServletAPI");

		/*return SUCCESS;*/
	}

/**
 *SpringMVC �ᰴ�������������POJO�����������Զ�ƥ�䣬
 * �Զ�Ϊ�ö����������ֵ��֧�ּ������ԡ�
 *��address.city,address.province
 */
	@RequestMapping("/testPojo")
	public String testPojo(User user){
		System.out.println("testPojo " + user);
		return SUCCESS;
	}

	/**
	 * �˽�;
	 * @CookieValue ӳ��һ�� Cookie ��ֵ,����ͬ @RequestParam
	 * @param SessionId
	 * @return
	 */
	@RequestMapping("/testCookieValue")
	public String testCookieValue(@CookieValue(value ="JSESSIONID" ) String SessionId){
		System.out.println("test RequestHeader SessionId : "+SessionId);
		return SUCCESS;
	}


	/**
	 * �˽⣺@RequestHeader
	 * ӳ������ͷ��Ϣ
	 * �÷�ͬ@RequestParam
	 */
	@RequestMapping("/testRequestHeader")
	public String testRequestParam(@RequestHeader(value = "Accept-Language") String language){
		System.out.println("test RequestHeader Accept-Language : "+language);
		return SUCCESS;
	}

	/**
	 * @RequestParam ��ӳ���������
	 * value ֵ����������Ĳ�����
	 * required �ò����Ƿ���롣Ĭ��Ϊtrue
	 * defaultValue ���������Ĭ��ֵ
	 */
	@RequestMapping("/testRequestParam")
	public String testRequestParam(@RequestParam(value = "username") String username,
								   @RequestParam(value = "age") Integer age){
		System.out.println("test RequestParam username: "+username+" , age: "+age);
		return SUCCESS;
		}

	/**
	 * Rest ����URL
	 * ��CRUD Ϊ��
	 * 
	 * ������/order POST 
	 * �޸ģ� /order/1 PUT  update?id =1
	 * ��ȡ�� /order/1 GET   get?id =1
	 * ɾ���� /order/1 DELETE  delete?id =1
	 * 
	 * ��η��� PUT ����� DELETE �����أ�
	 * 1.��Ҫ���� HiddenHttpMethodFilter
	 * 2.��Ҫ����POST ����
	 * 3.��Ҫ�ڷ���POST����ʱЯ��һ��name="_method"��������ֵΪDELETE �� PUT
	 * 
	 * ��SpringMVC ��Ŀ�귽������εõ�id��
	 * ʹ��@PathVariable ע��
	 * 
	 */
	@RequestMapping(value="/testRest/{id}",method=RequestMethod.PUT)
	public String testRestPut(@PathVariable Integer id) {
		System.out.println("testRest PUT :"+id);
		
		return SUCCESS;
	}
	
	@RequestMapping(value="/testRest/{id}",method=RequestMethod.DELETE)
	public String testRestDelete(@PathVariable Integer id) {
		System.out.println("testRest DELETE :"+id);
		
		return SUCCESS;
	}
	
	@RequestMapping(value="/testRest",method=RequestMethod.POST)
	public String testRestPost() {
		System.out.println("testRest POST");
		
		return SUCCESS;
	}
	
	@RequestMapping(value="/testRest/{id}",method=RequestMethod.GET)
	public String testRestGet(@PathVariable Integer id) {
		System.out.println("testRest GET :"+id);
		
		return SUCCESS;
	}
	
	/**
	 * @PathVariable ��������ӳ�� URL �е�ռλ����Ŀ�귽���Ĳ�����
	 */
	@RequestMapping("/testPathVariable/{id}")
	public String testPathVariable(@PathVariable("id") Integer id) {
		System.out.println("testPathVariable :"+id);
		return SUCCESS;
	}
	
	@RequestMapping("/testAntPath/*/xyz")
	public String testAntPath() {
		System.out.println("testAntPath");
		return SUCCESS;
	}
	
	/**
	 * �˽⣬����ʹ��params��headers�����Ӿ�ȷ��ӳ������params��headers֧�ּ򵥵ı��ʽ
	 * @return
	 */
	@RequestMapping(value="/testParamsAndHeaders",params= {"username","age!=10"},headers= {"Accept-Language=zh-CN,zh;q=0.9"})
	public String testParamsAndHeaders() {
		System.out.println("testParamsAndHeaders");
		return SUCCESS;
	}
	
	
	/**
	 	* ���ã�ʹ��method ���ԁ�ָ��Ո��ʽ
	 */
	@RequestMapping(value="/testMethod",method=RequestMethod.POST)
	public String testMethod() {
		System.out.println("testMehtod");
		return SUCCESS;
	}
	
	/**
	 * 1.@requestMapping �������η�����߀����������
	 * 2.
	 * 1).�෽�������ṩ����������ӳ����Ϣ�������webӦ�õĸ�Ŀ¼
	 * 2).���������ṩ��һ����ϸ��ӳ����Ϣ
	 *������ඨ�崦��URL�����ඨ�崦δ��ע@RequestMapping,�򷽷�����ǵ�URL �����WEBӦ�õĸ�Ŀ¼
	 */
	
	@RequestMapping("/testRequestMapping")
	public String testRequestMapping() {
		System.out.println("testRequestMapping");
		return SUCCESS;
	}
}
