package com.doublechaintech.health.wechatapppageview;

import com.terapico.caf.viewpage.SerializeScope;
import com.terapico.utils.MapUtil;

import java.util.HashMap;
import java.util.Map;

import com.doublechaintech.health.BaseViewPage;
import com.doublechaintech.health.HealthUserContext;
import com.doublechaintech.health.CustomHealthUserContextImpl;
import com.doublechaintech.health.HealthCustomConstants;
import com.doublechaintech.health.HealthViewScope;
import com.doublechaintech.health.wechatapp.WechatAppViewBizService;

public class SelectRolePage extends BaseViewPage{
	private static final long serialVersionUID = 1L;
	private static HealthViewScope ViewScope = HealthViewScope.getInstance();
	protected static final SerializeScope SCOPE = SerializeScope.INCLUDE()
			.field("title")
			.field("popup")
			.field("toast", SerializeScope.EXCLUDE())
			.field("refreshAction")
			.field("actions", SerializeScope.INCLUDE().field("switchToTeacher", HealthCustomConstants.ACTION_SCOPE).field("switchToStudent", HealthCustomConstants.ACTION_SCOPE))
			.field("actionList")
			;
	@Override
	protected SerializeScope getSerializeScope() {
		return SCOPE;
	}

	public String getPageTitle() {
		if (pageTitle == null) {
			return "select role";
		}
		return pageTitle;
	}
	@Override
	public void assemblerContent(HealthUserContext userContext, String requestName)throws Exception {
		CustomHealthUserContextImpl ctx = (CustomHealthUserContextImpl)userContext;
		setPageTitle("选择身份");
		Map<String, Object> actions = new HashMap<>();
		actions
				.put("switchToTeacher",
						MapUtil
								.put("code", "switchToTeacher")
									.put("title", "我是老师")
									.put("icon", "add")
									.put("linkToUrl", WechatAppViewBizService.makeSwitchToTeacherUrl(ctx))
									.into_map());
		actions
		.put("switchToStudent",
				MapUtil
						.put("code", "switchToStudent")
							.put("title", "我是家长")
							.put("icon", "add")
							.put("linkToUrl", WechatAppViewBizService.makeSwitchToStudentUrl(ctx))
							.into_map());
		set("actions", actions);
	}
}
