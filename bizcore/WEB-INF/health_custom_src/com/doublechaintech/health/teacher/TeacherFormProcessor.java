package com.doublechaintech.health.teacher;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.terapico.caf.form.ImageInfo;

import com.doublechaintech.health.BaseHealthFormProcessor;
import com.doublechaintech.health.HealthException;
import com.doublechaintech.health.HealthUserContext;

public class TeacherFormProcessor extends BaseHealthFormProcessor{
	protected String name;
	protected String school;
	protected String schoolClass;
	protected Integer classSize;
	protected String mobile;
	public String getName(){
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSchool(){
		return this.school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getSchoolClass(){
		return this.schoolClass;
	}
	public void setSchoolClass(String schoolClass) {
		this.schoolClass = schoolClass;
	}
	public Integer getClassSize(){
		return this.classSize;
	}
	public void setClassSize(Integer classSize) {
		this.classSize = classSize;
	}
	public String getMobile(){
		return this.mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public TeacherFormProcessor initByRequest(HealthUserContext userContext, String requestBody) throws Exception {
		setUserContext(userContext);
		loadRequestBody(requestBody);
		setName(pickStringParams("name", null));
		setSchool(pickStringParams("school", null));
		setSchoolClass(pickStringParams("schoolClass", null));
		setClassSize(pickIntegerParams("classSize", null));
		setMobile(pickStringParams("mobile"));
		return this;
	}
	public TeacherFormProcessor() {
		super();
	}
	
	public Map<String, Object> mapToUiForm(HealthUserContext userContext) {
		userContext.forceResponseXClassHeader("com.doublechaintech.health.teacher.TeacherForm");
		Map<String, Object> result = new HashMap<>();
		result.put("title", "teacher form");
		Map<String, Object> fields = new HashMap<>();
		result.put("fields", fields);
		fields.put("sceneCode", mapStringFieldIntoUiForm(true, "sceneCode", sceneCode));
		fields.put("name", mapStringFieldIntoUiForm(false, "name", getName() ));	
		fields.put("school", mapStringFieldIntoUiForm(false, "school", getSchool() ));	
		fields.put("schoolClass", mapStringFieldIntoUiForm(false, "schoolClass", getSchoolClass() ));	
		fields.put("classSize", mapIntegerFieldIntoUiForm(false, "classSize", getClassSize() ));	
		fields.put("mobile", mapStringFieldIntoUiForm(false, "mobile", getMobile() ));	
		mappingFormActions(result);
		return result;
	}

	public void verifyInputs() throws HealthException {
		HealthException e = new HealthException();
		boolean pass = true;
		pass = verifyNameInput(e) && pass;
		pass = verifySchoolInput(e) && pass;
		pass = verifySchoolClassInput(e) && pass;
		pass = verifyClassSizeInput(e) && pass;
		pass = verifyMobileInput(e) && pass;
		if (!pass){
			throw e;
		}
	}
	protected boolean canNameBeNull(){
		return false;
	}
	protected boolean verifyNameInput(HealthException e){
		if (isEmpty(getName())) {
			if (canNameBeNull()){
				return true;
			}
			addMessageToException(e, "请输入noname");
			return false;
		}
		if (!verifyStringInput(getName(), 1, 20)) {
			addMessageToException(e, "noname的范围是1~20");
			return false;
		}
		return true;
	}
	
	protected boolean canSchoolBeNull(){
		return false;
	}
	protected boolean verifySchoolInput(HealthException e){
		if (isEmpty(getSchool())) {
			if (canSchoolBeNull()){
				return true;
			}
			addMessageToException(e, "请输入noname");
			return false;
		}
		if (!verifyStringInput(getSchool(), 1, 99)) {
			addMessageToException(e, "noname的范围是1~99");
			return false;
		}
		return true;
	}
	
	protected boolean canSchoolClassBeNull(){
		return false;
	}
	protected boolean verifySchoolClassInput(HealthException e){
		if (isEmpty(getSchoolClass())) {
			if (canSchoolClassBeNull()){
				return true;
			}
			addMessageToException(e, "请输入noname");
			return false;
		}
		if (!verifyStringInput(getSchoolClass(), 1, 99)) {
			addMessageToException(e, "noname的范围是1~99");
			return false;
		}
		return true;
	}
	
	protected boolean canClassSizeBeNull(){
		return false;
	}
	protected boolean verifyClassSizeInput(HealthException e){
		if (isEmpty(getClassSize())) {
			if (canClassSizeBeNull()){
				return true;
			}
			addMessageToException(e, "请输入noname");
			return false;
		}
		if (!verifyIntegerInput(getClassSize(), 1, 999)) {
			addMessageToException(e, "noname的范围是1~999");
			return false;
		}
		return true;
	}
	
	protected boolean canMobileBeNull(){
		return true;
	}
	protected boolean verifyMobileInput(HealthException e){
		if (isEmpty(getMobile())) {
			if (canMobileBeNull()){
				return true;
			}
			addMessageToException(e, "请输入noname");
			return false;
		}
		if (!verifyStringInput(getMobile(), null, null)) {
			addMessageToException(e, "noname的值不正确");
			return false;
		}
		return true;
	}
	
	protected String unWrapperListImageInfoDataMember(List<ImageInfo> image) {
		if(image==null||image.isEmpty()) {
			return null;
		}
		return image.get(0).getImageUrl();
	}

}











