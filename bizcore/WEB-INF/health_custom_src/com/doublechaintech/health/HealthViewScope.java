package com.doublechaintech.health;


import com.terapico.caf.viewpage.SerializeScope;

public class HealthViewScope extends HealthBaseViewScope{

	static {
		// 定制化本项目的序列化scope的代码放在这里
		System.out.println("**************************************************************\n定制序列化\n");
	}
	
	protected static HealthViewScope instance = null;
	public static HealthViewScope getInstance() {
		if (instance != null) {
			return instance;
		}
		synchronized (HealthViewScope.class) {
			instance = new HealthViewScope();
		}
		return instance;
	}
}







