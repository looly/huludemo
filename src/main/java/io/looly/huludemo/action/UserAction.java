package io.looly.huludemo.action;

import java.util.Collection;

import org.slf4j.Logger;

import looly.github.hulu.Request;
import looly.github.hulu.annotation.Route;
import looly.github.hulu.exception.DaoException;
import looly.github.hulu.render.ErrorRender;
import looly.github.hulu.render.Render;
import looly.github.hutool.Log;
import looly.github.hutool.StrUtil;
import io.looly.huludemo.dao.VirtualDao;
import io.looly.huludemo.po.User;

import com.alibaba.fastjson.JSON;

/**
 * 用户相操作的Action<br>
 * 在hulu.setting中设置被扫描的包，这个包下所有的*Action类会被当作Action对象，里面的无返回值的Public方法会被当作一个Action<br>
 * 
 * 在访问的时候规则是这样的，你的UserAction类下有个create方法，那么访问路径就是/user/create<br>
 * 假如你用了@Route注解，那么注解的值就是访问的路径
 * @author loolly
 *
 */
public class UserAction {
	/** 这是我自己封装的Log，直接get，不需要传递类名了，很省事儿*/
	private final static Logger log = Log.get();
	
	/**
	 * 创建用户Action
	 */
	public void create() {
		//这是Request类中提供的一个静态方法，通过反射获取参数，调用POJO对象的set方法填充参数
		//例如：你的参数名称为name，那么user.setName方法会被调用。当第二个参数为true时，你的参数名必须是user.name
		final User user = Request.fillVo(new User(), false);
		
		//我爱死Slf4J这种字符串模板了，根本停不下来
		log.debug("Create user {}", user);
		
		try {
			VirtualDao.getInstance().saveUser(user);
		} catch (DaoException e) {
			//ErrorRender这个类用于返回给客户端（浏览器）一个错误信息，例如render500是返回一个500页面。内容是堆栈信息。
			//其实这个try catch不是必须的，可以在create方法上抛出其，当hulu.setting中的DevMode打开（为true）的时候，会自动返回一个带堆栈信息的500页面。
			ErrorRender.render500(e);
			return;
		}
		
		//Render这个类用于返回正常内容，例如HTML、JSP、JSON、JSONP等，在此返回一个普通文本。
		Render.renderText(StrUtil.format("Create user [{}] OK!", user.getName()));
	}
	
	/**
	 * 列出所有用户
	 */
	@Route("/listUser")		//这个注解用于指定Action方法的名字
	public void list() {
		final Collection<User> users = VirtualDao.getInstance().listUsers();
		Render.renderJson(JSON.toJSONString(users));
	}
}
