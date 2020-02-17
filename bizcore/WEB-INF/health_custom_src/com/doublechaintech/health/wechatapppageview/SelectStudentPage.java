package com.doublechaintech.health.wechatapppageview;

import com.terapico.caf.viewpage.SerializeScope;
import com.terapico.utils.MapUtil;

import java.util.HashMap;
import java.util.Map;

import com.doublechaintech.health.BaseViewPage;
import com.doublechaintech.health.HealthUserContext;
import com.doublechaintech.health.CustomHealthUserContextImpl;
import com.doublechaintech.health.HealthViewScope;
import com.doublechaintech.health.SmartList;
import com.doublechaintech.health.student.Student;
import com.doublechaintech.health.student.StudentTokens;
import com.doublechaintech.health.wechatapp.WechatAppViewBizService;

public class SelectStudentPage extends BaseViewPage{
	private static final long serialVersionUID = 1L;
	private static HealthViewScope ViewScope = HealthViewScope.getInstance();
	protected static final SerializeScope SCOPE = SerializeScope.INCLUDE()
			.field("title")
			.field("popup")
			.field("toast", SerializeScope.EXCLUDE())
			.field("refreshAction")
			.field("actions")
			.field("actionList")
			.field("displayMode")
			.field("emptyMessage")
			.field("list")
			.field("tabs")
			;
	@Override
	protected SerializeScope getSerializeScope() {
		return SCOPE;
	}

	public String getPageTitle() {
		if (pageTitle == null) {
			return "select student";
		}
		return pageTitle;
	}
	@Override
	protected void beforeDoRendering() {
		super.beforeDoRendering();
		this.set("displayMode", "student");
	}
	@Override
	protected void afterDoRendering() {
		super.afterDoRendering();
		forceResponseAsListOfPage();
	}
	@Override
	public void assemblerContent(HealthUserContext userContext, String requestName)throws Exception {
		CustomHealthUserContextImpl ctx = (CustomHealthUserContextImpl)userContext;
		SmartList<Student> studentList = ctx.getDAOGroup().getStudentDAO().findStudentByUser(ctx.getCurrentUserInfo().getId(), StudentTokens.all());
		if(studentList!=null) {
			studentList.forEach(s->s.addItemToValueMap("code", s.getId()));
		}
		set("list",studentList);
		set("emptyMessage","目前没有添加孩子信息");

		Map<String, Object> actions = new HashMap<>();
		actions
				.put("addStudent",
						MapUtil
								.put("code", "addStudent")
									.put("title", "添加孩子")
									.put("icon", "add")
									.put("linkToUrl", "")
									.into_map());
		set("actions", actions);
		setPageTitle("选择孩子");
	}
}
