package com.doublechaintech.health.wechatapppageview;

import com.terapico.caf.viewpage.SerializeScope;
import com.doublechaintech.health.BaseHealthFormProcessor;
import com.doublechaintech.health.BaseViewPage;
import com.doublechaintech.health.HealthUserContext;
import com.doublechaintech.health.CustomHealthUserContextImpl;
import com.doublechaintech.health.HealthViewScope;
import com.doublechaintech.health.teacher.TeacherFormProcessor;
import com.doublechaintech.health.wechatapp.WechatAppViewBizService;

public class AddClassPage extends BaseViewPage{
	private static final long serialVersionUID = 1L;
	private static HealthViewScope ViewScope = HealthViewScope.getInstance();
	protected static final SerializeScope SCOPE = SerializeScope.INCLUDE()
			.field("title")
			.field("popup")
			.field("toast", SerializeScope.EXCLUDE())
			.field("refreshAction")
			.field("actions", SerializeScope.EXCLUDE())
			.field("actionList")
			.field("form")
			;
	@Override
	protected SerializeScope getSerializeScope() {
		return SCOPE;
	}

	public String getPageTitle() {
		if (pageTitle == null) {
			return "add class";
		}
		return pageTitle;
	}
	@Override
	public void assemblerContent(HealthUserContext userContext, String requestName)throws Exception {
		CustomHealthUserContextImpl ctx = (CustomHealthUserContextImpl)userContext;
		TeacherFormProcessor form = new TeacherFormProcessor();
		form.addAction("保存", "SAVE", WechatAppViewBizService.makeSubmitClassUrl(ctx));
		set("form",form);
		
		setPageTitle("添加班级");
	}
}
