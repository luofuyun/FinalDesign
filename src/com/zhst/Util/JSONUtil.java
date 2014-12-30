package com.zhst.Util;

import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

public class JSONUtil {

	/**
	 * 过滤JSON属性,解决JSON数据解析中的死循环问题
	 * @param property 过滤属性
	 * @return
	 */
	public static JsonConfig filterPropertyInJsonData(final String property){
	 JsonConfig config = new JsonConfig();
	 config.setJsonPropertyFilter(new PropertyFilter() {
	  @Override
	  public boolean apply(Object arg0, String arg1, Object arg2) {
	 if (property.equals(arg1)) {
	       return true;
	       } else {
	   return false;
	 }
	 }
	 });
	 return config;
	}
}
