package com.xiaoleilu.huludemo.interceptor;

import org.slf4j.Logger;

import com.xiaoleilu.hulu.ActionMethod;
import com.xiaoleilu.hulu.exception.ActionException;
import com.xiaoleilu.hulu.interceptor.Interceptor;
import com.xiaoleilu.hutool.Log;

/**
 * 日志过滤器
 * @author loolly
 *
 */
public class Log1Interceptor implements Interceptor{
	private final static Logger log = Log.get();

	@Override
	public void invoke(ActionMethod actionMethod) throws ActionException {
		log.info("过滤器 [{}] 在执行Action方法前做的事情", this.getClass().getName());
		
		actionMethod.invoke();
		
		log.info("过滤器 [{}] 在执行Action方法后做的事情", this.getClass().getName());
	}

}
