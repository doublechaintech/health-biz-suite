package com.doublechaintech.health.wechatapp;

import com.doublechaintech.health.CustomHealthCheckerManager;
import com.doublechaintech.health.CustomHealthUserContextImpl;
import com.doublechaintech.health.HealthBaseConstants;
import com.terapico.uccaf.BaseUserContext;
import com.terapico.utils.TextUtil;

import cn.binarywang.wx.miniapp.api.WxMaService;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.WxCpMaJsCode2SessionResult;

public abstract class BasicWxappService extends CustomHealthCheckerManager {
    protected WxCpService wxCpService;
    protected WxMaService wxMaService;

    public WxCpService getWxCpService() {
        return wxCpService;
    }

    public void setWxCpService(WxCpService wxCpService) {
        this.wxCpService = wxCpService;
    }

//    @Override
//    public void onAccess(BaseUserContext baseUserContext, String methodName, Object[] parameters) {
//        // TODO Auto-generated method stub
//        super.onAccess(baseUserContext, methodName, parameters);
//        CustomHealthUserContextImpl ctx = (CustomHealthUserContextImpl) baseUserContext;
//        ctx.putToCache(MiscUtils.getCacheAccessKey(ctx), true, 1 * 60 * 60); // 1个小时
//        ctx.putIntoContextLocalStorage(HealthBaseConstants.CACHE_KEY_ACCESS_METHOD, methodName);
//        ctx.putIntoContextLocalStorage(HealthBaseConstants.CACHE_KEY_ACCESS_PARAMS, parameters);
//
//        ctx.saveAccessInfo(this.getBeanName(), methodName, parameters);
//    }

//    @Override
//    public Object checkAccess(BaseUserContext baseUserContext, String methodName, Object[] parameters)
//            throws IllegalAccessException {
//        CustomHealthUserContextImpl ctx = (CustomHealthUserContextImpl) baseUserContext;
//        {
//            // 仅用于DEMO期间
//            healtheloper currentUserInfo = healtheloper.withId("SD000001");
//            ctx.setCurrentUserInfo(currentUserInfo);
//        }
//        return accessOK();
//    }

    public Object login(CustomHealthUserContextImpl ctx, String code) throws Exception {
        WxCpService svc = this.getWxCpService();
        if (TextUtil.isBlank(code)) {
            code = "test1234";
        }

//		WxCpOauth2UserInfo userInfo = svc.getOauth2Service().getUserInfo(code);
//		System.out.println(userInfo);

        WxCpMaJsCode2SessionResult sessionInfo = svc.jsCode2Session(code);
        System.out.println(sessionInfo);
        return null;
    }

	public WxMaService getWxMaService() {
		return wxMaService;
	}

	public void setWxMaService(WxMaService wxMaService) {
		this.wxMaService = wxMaService;
	}


}
