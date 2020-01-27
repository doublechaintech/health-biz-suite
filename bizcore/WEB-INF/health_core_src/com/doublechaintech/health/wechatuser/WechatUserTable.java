
package com.doublechaintech.health.wechatuser;
import com.doublechaintech.health.AccessKey;


public class WechatUserTable{


	public static AccessKey withId(Object value){
		AccessKey accessKey = new AccessKey();
		accessKey.setColumnName(COLUMN_ID);
		accessKey.setValue(value);
		return accessKey;
	}
	//Add extra identifiers
	

	//only this package can use this, so the scope is default, not public, not private either nor protected
	public static final String TABLE_NAME="wechat_user_data";
	static final String COLUMN_ID = "id";
	static final String COLUMN_NAME = "name";
	static final String COLUMN_AVATAR = "avatar";
	static final String COLUMN_ADDRESS = "address";
	static final String COLUMN_USER_TYPE = "user_type";
	static final String COLUMN_CREATE_TIME = "create_time";
	static final String COLUMN_PLATFORM = "platform";
	static final String COLUMN_VERSION = "version";
 
	public static final String []ALL_CLOUMNS = {COLUMN_ID,COLUMN_NAME,COLUMN_AVATAR,COLUMN_ADDRESS,COLUMN_USER_TYPE,COLUMN_CREATE_TIME,COLUMN_PLATFORM,COLUMN_VERSION};
	public static final String []NORMAL_CLOUMNS = {COLUMN_NAME,COLUMN_AVATAR,COLUMN_ADDRESS,COLUMN_USER_TYPE,COLUMN_CREATE_TIME,COLUMN_PLATFORM};
	
	
}

