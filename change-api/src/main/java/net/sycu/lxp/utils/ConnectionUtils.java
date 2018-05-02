package net.sycu.lxp.utils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 操作集合类工具
 * @author lxp
 *
 */
public class ConnectionUtils {
	
	/**
	 * 通过集合对象集合获取对象id集合
	 * @return
	 * @throws ClassNotFoundException 
	 */
	public List<? extends Object> getIdList(List<? extends Object> objList, String methodName, Class<?>... parameterTypes){
		List<Object> idList = new ArrayList<Object>();
		for (Object o: objList) {
			try {
				Class<?> classObj = o.getClass();
				Method method = classObj.getMethod(methodName, parameterTypes);
				String id = (String) method.invoke(o, parameterTypes);
				idList.add(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return idList;
	}

}
