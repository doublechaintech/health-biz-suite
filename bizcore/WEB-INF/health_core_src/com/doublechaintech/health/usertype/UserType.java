
package com.doublechaintech.health.usertype;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.math.BigDecimal;
import com.terapico.caf.DateTime;
import com.doublechaintech.health.BaseEntity;
import com.doublechaintech.health.SmartList;
import com.doublechaintech.health.KeyValuePair;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.doublechaintech.health.platform.Platform;
import com.doublechaintech.health.wechatuser.WechatUser;

@JsonSerialize(using = UserTypeSerializer.class)
public class UserType extends BaseEntity implements  java.io.Serializable{

	public static final String TEACHER = "Teacher";	// 老师
	public static final String GUARDIAN = "Guardian";	// 家长
	public static final String STUDENT = "Student";	// 学生
	public static List<KeyValuePair> CODE_NAME_LIST;
	static {
		CODE_NAME_LIST = new ArrayList<>();

		CODE_NAME_LIST.add(new KeyValuePair(TEACHER, "老师"));
		CODE_NAME_LIST.add(new KeyValuePair(GUARDIAN, "家长"));
		CODE_NAME_LIST.add(new KeyValuePair(STUDENT, "学生"));
	}
	
	public static final String ID_PROPERTY                    = "id"                ;
	public static final String NAME_PROPERTY                  = "name"              ;
	public static final String CODE_PROPERTY                  = "code"              ;
	public static final String PLATFORM_PROPERTY              = "platform"          ;
	public static final String VERSION_PROPERTY               = "version"           ;

	public static final String WECHAT_USER_LIST                         = "wechatUserList"    ;

	public static final String INTERNAL_TYPE="UserType";
	public String getInternalType(){
		return INTERNAL_TYPE;
	}
	
	public String getDisplayName(){
	
		String displayName = getName();
		if(displayName!=null){
			return displayName;
		}
		
		return super.getDisplayName();
		
	}

	private static final long serialVersionUID = 1L;
	

	protected		String              	mId                 ;
	protected		String              	mName               ;
	protected		String              	mCode               ;
	protected		Platform            	mPlatform           ;
	protected		int                 	mVersion            ;
	
	
	protected		SmartList<WechatUser>	mWechatUserList     ;
	
		
	public 	UserType(){
		// lazy load for all the properties
	}
	public 	static UserType withId(String id){
		UserType userType = new UserType();
		userType.setId(id);
		userType.setVersion(Integer.MAX_VALUE);
		return userType;
	}
	public 	static UserType refById(String id){
		return withId(id);
	}
	
	// disconnect from all, 中文就是一了百了，跟所有一切尘世断绝往来藏身于茫茫数据海洋
	public 	void clearFromAll(){
		setPlatform( null );

		this.changed = true;
	}
	
	
	//Support for changing the property
	
	public void changeProperty(String property, String newValueExpr) {
     	
		if(NAME_PROPERTY.equals(property)){
			changeNameProperty(newValueExpr);
		}
		if(CODE_PROPERTY.equals(property)){
			changeCodeProperty(newValueExpr);
		}

      
	}
    
    
	protected void changeNameProperty(String newValueExpr){
		String oldValue = getName();
		String newValue = parseString(newValueExpr);
		if(equalsString(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateName(newValue);
		this.onChangeProperty(NAME_PROPERTY, oldValue, newValue);
		return;
  
	}
			
			
			
	protected void changeCodeProperty(String newValueExpr){
		String oldValue = getCode();
		String newValue = parseString(newValueExpr);
		if(equalsString(oldValue , newValue)){
			return;//they can be both null, or exact the same object, this is much faster than equals function
		}
		//they are surely different each other
		updateCode(newValue);
		this.onChangeProperty(CODE_PROPERTY, oldValue, newValue);
		return;
  
	}
			
			
			


	
	public Object propertyOf(String property) {
     	
		if(NAME_PROPERTY.equals(property)){
			return getName();
		}
		if(CODE_PROPERTY.equals(property)){
			return getCode();
		}
		if(PLATFORM_PROPERTY.equals(property)){
			return getPlatform();
		}
		if(WECHAT_USER_LIST.equals(property)){
			List<BaseEntity> list = getWechatUserList().stream().map(item->item).collect(Collectors.toList());
			return list;
		}

    		//other property not include here
		return super.propertyOf(property);
	}
    
    


	
	
	
	public void setId(String id){
		this.mId = trimString(id);;
	}
	public String getId(){
		return this.mId;
	}
	public UserType updateId(String id){
		this.mId = trimString(id);;
		this.changed = true;
		return this;
	}
	public void mergeId(String id){
		if(id != null) { setId(id);}
	}
	
	
	public void setName(String name){
		this.mName = trimString(name);;
	}
	public String getName(){
		return this.mName;
	}
	public UserType updateName(String name){
		this.mName = trimString(name);;
		this.changed = true;
		return this;
	}
	public void mergeName(String name){
		if(name != null) { setName(name);}
	}
	
	
	public void setCode(String code){
		this.mCode = trimString(code);;
	}
	public String getCode(){
		return this.mCode;
	}
	public UserType updateCode(String code){
		this.mCode = trimString(code);;
		this.changed = true;
		return this;
	}
	public void mergeCode(String code){
		if(code != null) { setCode(code);}
	}
	
	
	public void setPlatform(Platform platform){
		this.mPlatform = platform;;
	}
	public Platform getPlatform(){
		return this.mPlatform;
	}
	public UserType updatePlatform(Platform platform){
		this.mPlatform = platform;;
		this.changed = true;
		return this;
	}
	public void mergePlatform(Platform platform){
		if(platform != null) { setPlatform(platform);}
	}
	
	
	public void clearPlatform(){
		setPlatform ( null );
		this.changed = true;
	}
	
	public void setVersion(int version){
		this.mVersion = version;;
	}
	public int getVersion(){
		return this.mVersion;
	}
	public UserType updateVersion(int version){
		this.mVersion = version;;
		this.changed = true;
		return this;
	}
	public void mergeVersion(int version){
		setVersion(version);
	}
	
	

	public  SmartList<WechatUser> getWechatUserList(){
		if(this.mWechatUserList == null){
			this.mWechatUserList = new SmartList<WechatUser>();
			this.mWechatUserList.setListInternalName (WECHAT_USER_LIST );
			//有名字，便于做权限控制
		}
		
		return this.mWechatUserList;	
	}
	public  void setWechatUserList(SmartList<WechatUser> wechatUserList){
		for( WechatUser wechatUser:wechatUserList){
			wechatUser.setUserType(this);
		}

		this.mWechatUserList = wechatUserList;
		this.mWechatUserList.setListInternalName (WECHAT_USER_LIST );
		
	}
	
	public  void addWechatUser(WechatUser wechatUser){
		wechatUser.setUserType(this);
		getWechatUserList().add(wechatUser);
	}
	public  void addWechatUserList(SmartList<WechatUser> wechatUserList){
		for( WechatUser wechatUser:wechatUserList){
			wechatUser.setUserType(this);
		}
		getWechatUserList().addAll(wechatUserList);
	}
	public  void mergeWechatUserList(SmartList<WechatUser> wechatUserList){
		if(wechatUserList==null){
			return;
		}
		if(wechatUserList.isEmpty()){
			return;
		}
		addWechatUserList( wechatUserList );
		
	}
	public  WechatUser removeWechatUser(WechatUser wechatUserIndex){
		
		int index = getWechatUserList().indexOf(wechatUserIndex);
        if(index < 0){
        	String message = "WechatUser("+wechatUserIndex.getId()+") with version='"+wechatUserIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        WechatUser wechatUser = getWechatUserList().get(index);        
        // wechatUser.clearUserType(); //disconnect with UserType
        wechatUser.clearFromAll(); //disconnect with UserType
		
		boolean result = getWechatUserList().planToRemove(wechatUser);
        if(!result){
        	String message = "WechatUser("+wechatUserIndex.getId()+") with version='"+wechatUserIndex.getVersion()+"' NOT found!";
            throw new IllegalStateException(message);
        }
        return wechatUser;
        
	
	}
	//断舍离
	public  void breakWithWechatUser(WechatUser wechatUser){
		
		if(wechatUser == null){
			return;
		}
		wechatUser.setUserType(null);
		//getWechatUserList().remove();
	
	}
	
	public  boolean hasWechatUser(WechatUser wechatUser){
	
		return getWechatUserList().contains(wechatUser);
  
	}
	
	public void copyWechatUserFrom(WechatUser wechatUser) {

		WechatUser wechatUserInList = findTheWechatUser(wechatUser);
		WechatUser newWechatUser = new WechatUser();
		wechatUserInList.copyTo(newWechatUser);
		newWechatUser.setVersion(0);//will trigger copy
		getWechatUserList().add(newWechatUser);
		addItemToFlexiableObject(COPIED_CHILD, newWechatUser);
	}
	
	public  WechatUser findTheWechatUser(WechatUser wechatUser){
		
		int index =  getWechatUserList().indexOf(wechatUser);
		//The input parameter must have the same id and version number.
		if(index < 0){
 			String message = "WechatUser("+wechatUser.getId()+") with version='"+wechatUser.getVersion()+"' NOT found!";
			throw new IllegalStateException(message);
		}
		
		return  getWechatUserList().get(index);
		//Performance issue when using LinkedList, but it is almost an ArrayList for sure!
	}
	
	public  void cleanUpWechatUserList(){
		getWechatUserList().clear();
	}
	
	
	


	public void collectRefercences(BaseEntity owner, List<BaseEntity> entityList, String internalType){

		addToEntityList(this, entityList, getPlatform(), internalType);

		
	}
	
	public List<BaseEntity>  collectRefercencesFromLists(String internalType){
		
		List<BaseEntity> entityList = new ArrayList<BaseEntity>();
		collectFromList(this, entityList, getWechatUserList(), internalType);

		return entityList;
	}
	
	public  List<SmartList<?>> getAllRelatedLists() {
		List<SmartList<?>> listOfList = new ArrayList<SmartList<?>>();
		
		listOfList.add( getWechatUserList());
			

		return listOfList;
	}

	
	public List<KeyValuePair> keyValuePairOf(){
		List<KeyValuePair> result =  super.keyValuePairOf();

		appendKeyValuePair(result, ID_PROPERTY, getId());
		appendKeyValuePair(result, NAME_PROPERTY, getName());
		appendKeyValuePair(result, CODE_PROPERTY, getCode());
		appendKeyValuePair(result, PLATFORM_PROPERTY, getPlatform());
		appendKeyValuePair(result, VERSION_PROPERTY, getVersion());
		appendKeyValuePair(result, WECHAT_USER_LIST, getWechatUserList());
		if(!getWechatUserList().isEmpty()){
			appendKeyValuePair(result, "wechatUserCount", getWechatUserList().getTotalCount());
			appendKeyValuePair(result, "wechatUserCurrentPageNumber", getWechatUserList().getCurrentPageNumber());
		}

		
		return result;
	}
	
	
	public BaseEntity copyTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof UserType){
		
		
			UserType dest =(UserType)baseDest;
		
			dest.setId(getId());
			dest.setName(getName());
			dest.setCode(getCode());
			dest.setPlatform(getPlatform());
			dest.setVersion(getVersion());
			dest.setWechatUserList(getWechatUserList());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	public BaseEntity mergeDataTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof UserType){
		
			
			UserType dest =(UserType)baseDest;
		
			dest.mergeId(getId());
			dest.mergeName(getName());
			dest.mergeCode(getCode());
			dest.mergePlatform(getPlatform());
			dest.mergeVersion(getVersion());
			dest.mergeWechatUserList(getWechatUserList());

		}
		super.copyTo(baseDest);
		return baseDest;
	}
	
	public BaseEntity mergePrimitiveDataTo(BaseEntity baseDest){
		
		
		if(baseDest instanceof UserType){
		
			
			UserType dest =(UserType)baseDest;
		
			dest.mergeId(getId());
			dest.mergeName(getName());
			dest.mergeCode(getCode());
			dest.mergeVersion(getVersion());

		}
		return baseDest;
	}
	public Object[] toFlatArray(){
		return new Object[]{getId(), getName(), getCode(), getPlatform(), getVersion()};
	}
	public String toString(){
		StringBuilder stringBuilder=new StringBuilder(128);

		stringBuilder.append("UserType{");
		stringBuilder.append("\tid='"+getId()+"';");
		stringBuilder.append("\tname='"+getName()+"';");
		stringBuilder.append("\tcode='"+getCode()+"';");
		if(getPlatform() != null ){
 			stringBuilder.append("\tplatform='Platform("+getPlatform().getId()+")';");
 		}
		stringBuilder.append("\tversion='"+getVersion()+"';");
		stringBuilder.append("}");

		return stringBuilder.toString();
	}
	
	//provide number calculation function
	

}

