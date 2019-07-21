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
	 * 1.有@ModelAttribute 标记的方法，会在每个目标方法执行之前被 SpringMVC 调用
	 * 2.@ModelAttribute 注解也可以来修饰目标方法POJO 类型的入参，其value属性值有如下的作用：
	 * 1).SpringMVC 会使 value 属性值在 implicitModel 中查找对应的对象，若存在则会直接传入到目标方法的入参中。
	 * 2).SpringMVC 会 一 value为Key ，POJO类型的对象为 value，存入到request中
	 * @param id
	 * @param map
	 */
	@ModelAttribute
	public  void getUser(@RequestParam(value = "id",required =false ) Integer id,
						 Map<String,Object> map){

		System.out.println("ModelAttribute method");
		if(id!= null){
			User user = new User(1,"Tom","123456",12,"tom@163.com");
			System.out.println("从数据库中获取对象 user ："+user);
			map.put("abc",user);
		}

	}

	/**
	 * 运行流程：
	 * 1.执行 @ModelAttribute 注解修饰的方法：从数据库中取出对象，把对象放入到了Map 中，键为：user
	 * 2.SpringMVC 从Map中取出User对象，并把表单的请求参数赋给该User对象的对应属性
	 * 3.SpringMVC 把上述对象传入目标方法的参数
	 *
	 * 注意：在@ModelAttribute 修饰的方法中，放入到Map 时的键需要和目标方法入参类型的第一个字母小写的字符串一致！
	 *
	 *
	 *
	 * SpringMVC 确定目标方法 POJO类型入参的过程
	 * 1.确定一个key
	 * 1).若目标方法的POJO 类型的参数没有使用@ModelAttribute 作为修饰，则key为 POJO类名，第一个字母小写
	 * 2).若使用了@ModelAttribute 来修饰，则key为@ModelAttribute 注解的value 属性值
	 * 2.在implicitModel 中查找 key对应的对象，若存在，则作为入参传入
	 * 1).若在@ModelAttribute 标记的方法中在Map 中保存过，且 key和 1确定的key 一致，则会获取到。
	 * 3.若implicitModel 中不存在key 对应的对象，则检查当前的Handler 是否使用@SessionAttributes 注解修饰，
	 * 若使用了该注解，且@SessionAttributes 注解的value 属性值中包含了key，则会从HttpSession中来获取key所
	 * 对应的value值，若存在则直接传入到目标方法的入参中。若不存在则将抛出异常。
	 * 4.若Handler 没有标识@SessionAttributes 注解或者@SessionAttributes 注解的value值中不包含key，则
	 * 会通过反射来创建POJO 类型的参数，传入为目标方法的参数
	 * 5.SpringMVC 会把Key 和POJO 类型的对象保存到implicitModel 中，进而会保存到request中。
	 * @param user
	 * @return
	 */
	@RequestMapping("/testModelAttribute")
	public String testModelAttribute(User user){
		System.out.println("修改 ：" +user);
		return SUCCESS;
	}
	/**
	 * @SessionAttribute 除了可以通过属性名来指定需要放到会话中的属性外（）实际上使用的是Value属性值
	 * 还可以通过模型属性的对象类型指定哪些模型属性需要放到会话中（）实际上使用的是types属性值
	 *
	 * 注意：该注解只能放在类的上面，而不能修饰方法
	 */

	@RequestMapping("/testSessionAttribute")
	public String testSessionAttribute(Map<String ,Object> map){

		User user = new User("Tom","123456",12,"12@123.com");
		map.put("user",user);
		map.put("school","henanzhongyiyaodaxue");
		return SUCCESS;
	}
	/**
	 * 目标方法可以添加 Map 类型(实际上也可以是 Model 类型或者 ModelMap类型)的参数
	 *
	 */
	@RequestMapping("/testMap")
	public String testMap(Map<String ,Object> map){
		map.put("names", Arrays.asList("Tom","Jerry","Mike"));
		return SUCCESS;

	}

	/**
	 *目标方法的返回值可以是ModelAndView 类型
	 * 其中可以包含视图和模型信息
	 * SpringMVC 会把 ModelAndView 的 model中的数据放入到 request 域对象中；
	 */
	@RequestMapping("/testModelAndView")
	public ModelAndView testModelAndView(){

		String viewName=SUCCESS;
		ModelAndView modelAndView =new ModelAndView(viewName);
		modelAndView.addObject("time",new Date());
		return modelAndView;
	}

	/**
	 *可以使用servlet 原生的API作为目标方法的参数 具体支持以下类型
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
 *SpringMVC 会按照请求参数名和POJO属性名进行自动匹配，
 * 自动为该对象填充属性值，支持级联属性。
 *如address.city,address.province
 */
	@RequestMapping("/testPojo")
	public String testPojo(User user){
		System.out.println("testPojo " + user);
		return SUCCESS;
	}

	/**
	 * 了解;
	 * @CookieValue 映射一个 Cookie 的值,属性同 @RequestParam
	 * @param SessionId
	 * @return
	 */
	@RequestMapping("/testCookieValue")
	public String testCookieValue(@CookieValue(value ="JSESSIONID" ) String SessionId){
		System.out.println("test RequestHeader SessionId : "+SessionId);
		return SUCCESS;
	}


	/**
	 * 了解：@RequestHeader
	 * 映射请求头信息
	 * 用法同@RequestParam
	 */
	@RequestMapping("/testRequestHeader")
	public String testRequestParam(@RequestHeader(value = "Accept-Language") String language){
		System.out.println("test RequestHeader Accept-Language : "+language);
		return SUCCESS;
	}

	/**
	 * @RequestParam 来映射请求参数
	 * value 值即请求参数的参数名
	 * required 该参数是否必须。默认为true
	 * defaultValue 请求参数的默认值
	 */
	@RequestMapping("/testRequestParam")
	public String testRequestParam(@RequestParam(value = "username") String username,
								   @RequestParam(value = "age") Integer age){
		System.out.println("test RequestParam username: "+username+" , age: "+age);
		return SUCCESS;
		}

	/**
	 * Rest 风格的URL
	 * 以CRUD 为例
	 * 
	 * 新增：/order POST 
	 * 修改： /order/1 PUT  update?id =1
	 * 获取： /order/1 GET   get?id =1
	 * 删除： /order/1 DELETE  delete?id =1
	 * 
	 * 如何发送 PUT 请求和 DELETE 请求呢？
	 * 1.需要配置 HiddenHttpMethodFilter
	 * 2.需要发送POST 请求
	 * 3.需要在发送POST请求时携带一个name="_method"的隐藏域，值为DELETE 或 PUT
	 * 
	 * 在SpringMVC 的目标方法中如何得到id呢
	 * 使用@PathVariable 注解
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
	 * @PathVariable 可以用来映射 URL 中的占位符到目标方法的参数中
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
	 * 了解，可以使用params和headers来更加精确的映射请求，params和headers支持简单的表达式
	 * @return
	 */
	@RequestMapping(value="/testParamsAndHeaders",params= {"username","age!=10"},headers= {"Accept-Language=zh-CN,zh;q=0.9"})
	public String testParamsAndHeaders() {
		System.out.println("testParamsAndHeaders");
		return SUCCESS;
	}
	
	
	/**
	 	* 常用：使用method 屬性來指定請求方式
	 */
	@RequestMapping(value="/testMethod",method=RequestMethod.POST)
	public String testMethod() {
		System.out.println("testMehtod");
		return SUCCESS;
	}
	
	/**
	 * 1.@requestMapping 除了修饰方法，還可以修饰类
	 * 2.
	 * 1).类方法处：提供初步的请求映射信息，相对于web应用的根目录
	 * 2).方法处：提供进一步的细分映射信息
	 *相对于类定义处的URL，若类定义处未标注@RequestMapping,则方法处标记的URL 相对于WEB应用的根目录
	 */
	
	@RequestMapping("/testRequestMapping")
	public String testRequestMapping() {
		System.out.println("testRequestMapping");
		return SUCCESS;
	}
}
