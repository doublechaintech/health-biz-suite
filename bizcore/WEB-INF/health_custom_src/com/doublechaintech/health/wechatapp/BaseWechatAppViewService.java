package com.doublechaintech.health.wechatapp;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.Cookie;
import com.auth0.jwt.interfaces.DecodedJWT;

import com.doublechaintech.health.HealthUserContext;
import com.doublechaintech.health.CustomHealthUserContextImpl;
import com.doublechaintech.health.FootprintProducer;
import com.doublechaintech.health.MultipleAccessKey;
import com.doublechaintech.health.HealthBaseConstants;
import com.doublechaintech.health.HealthBaseUtils;
import com.doublechaintech.health.SmartList;
import com.doublechaintech.health.secuser.SecUser;
import com.doublechaintech.health.userapp.UserApp;
import com.doublechaintech.health.userapp.UserAppTokens;
import com.doublechaintech.health.HealthException;
import com.doublechaintech.health.user.User;
import com.doublechaintech.health.wechatlogininfo.WechatLoginInfo;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;

import com.doublechaintech.health.wechatapp.BasicWxappService;
import com.doublechaintech.health.wechatapppageview.*;
import com.terapico.utils.DateTimeUtil;
import com.terapico.utils.JWTUtil;
import com.terapico.utils.MapUtil;
import com.terapico.utils.RandomUtil;
import com.terapico.utils.TextUtil;

import com.terapico.uccaf.BaseUserContext;
import com.terapico.caf.baseelement.LoginParam;

/**
 * 此类负责：声明所有WechatAppViewService中所使用的方法和常量。 单独列出的目的是便于维护。
 * @author clariones
 *
 */
public abstract class BaseWechatAppViewService extends BasicWxappService implements FootprintProducer{
	protected static interface BaseLoginHandler {
		String DEBUG = "debug";  // 调试时使用的简单登录接口，生产环境不可用
		String WECHAT_WORK_APP = "wechat_work_app";  // 前端使用小程序，用企业微信登录
		String WECHAT_APP = "wechat_app";  // 前端使用小程序, 做微信登录
		String MOBILE_AND_VCODE = "mobile_vcode";	// 前端使用手机号和验证码登录

		/**
		 * 用输入的信息，做登录的动作。
		 * @return 如果输入的信息错误，抛异常；如果输入的信息，不能登陆，抛异常；如果输入的信息允许登录，但是找不到对应的'登录目标'， 不抛异常，返回null；如果登录正常，返回登录成功的对象。
		 */
		public User doLogin(CustomHealthUserContextImpl ctx, LoginParam loginParam) throws Exception;
		
		/** 
		 * 获取处理后的登录相关信息
		 */
		public Map<String, Object> getProcessedLoginInfo(CustomHealthUserContextImpl ctx);
		
		/**
		 * 新用户登录后,创建与之关联的登录信息
		 */
		public void createLoginInfoForNewTarget(CustomHealthUserContextImpl ctx, User loginTarget);
	}
	public static final int $PRC_RESULT_OBJECT_WAS_SET = -1;
	public static final int PRC_BY_DEFAULT = 0;
	public static final int PRC_SWITCHTOTEACHER = 1;
	public static final int PRC_EMPTY_CLASS = 2;
	public static final int PRC_SUBMITTED = 3;
	protected boolean returnRightNow(int resultCode) {
		return $PRC_RESULT_OBJECT_WAS_SET == resultCode;
	}
	protected abstract void commonLog(CustomHealthUserContextImpl ctx, String eventCode, String title, 
			String key1, String key2, String key3, Object detailInfo);
			
	protected boolean hasFormResubmitFlag(CustomHealthUserContextImpl  ctx) {
		Object flag = ctx.getFromContextLocalStorage(HealthBaseConstants.KEY_RE_SUBMIT);
		if (flag == null) {
			return false;
		}
		if (flag instanceof Boolean) {
			return ((Boolean) flag).booleanValue();
		}
		return false;
	}
	protected Map<String, Object> makeToast(String content, int duration, String type) {
		HashMap<String, Object> toast = new HashMap<String, Object>();
		toast.put("text", content);
		toast.put("duration", duration * 1000);
		toast.put("icon", type);
		toast.put("position", "center");
		return toast;
	}
	protected static String makeUrl(String methodName, Object ... params) {
		return makeUrlF(methodName, true, params);
	}
	protected static String makeUrlF(String methodName, boolean encode, Object ... params) {
		StringBuilder sb = new StringBuilder(methodName).append("/");
		if (params != null) {
			for(Object param : params) {
				if (param == null || ((param instanceof String) && TextUtil.isBlank((String) param))) {
					sb.append('+').append('/');
					continue;
				}
				if (param instanceof Date) {
					sb.append(DateTimeUtil.formatDate((Date)param, "yyyy-MM-dd'T'HH:mm:ss")).append('/');
					continue;
				}
				boolean isVariable = false;
				if (param instanceof String && ((String) param).length() > 0) {
					isVariable = ((String) param).charAt(0) == ':';
				}
				if (encode && !isVariable) {
					try {
						sb.append(URLEncoder.encode(String.valueOf(param), "utf-8"));
					} catch (UnsupportedEncodingException e) {
						sb.append(URLEncoder.encode(String.valueOf(param)));
					}
				} else {
					sb.append(String.valueOf(param));
				}
				sb.append('/');
			}
		}
		return sb.toString();
	}
	public Object sendVerifyCode(CustomHealthUserContextImpl ctx, String mobile) throws Exception {
		String vCode = RandomUtil.randomNum(6);
		mobile = HealthBaseUtils.formatChinaMobile(mobile);
		cacheVerifyCode(ctx, mobile, vCode);
		ctx.sendMessage(mobile, getSmsSign(ctx), getSmsVCodeTemplate(ctx), MapUtil.put("code", vCode).into_map(String.class));
		if (ctx.isProductEnvironment()) {
			return makeToast("短信已经发送到"+TextUtil.shrink(mobile, 3, 2, "***")+",请注意查收", 10, "info");
		}else {
			return makeToast("验证码"+vCode+"已经发送到"+TextUtil.shrink(mobile, 3, 2, "***")+",请注意查收", 10, "info");
		}
	}
    
    protected String getSmsVCodeTemplate(CustomHealthUserContextImpl ctx) {
		return TextUtil.getExtVariable("SMS_VERIFY_CODE", "");
	}
	protected String getSmsSign(CustomHealthUserContextImpl ctx) {
		return TextUtil.getExtVariable("SMS_SIGN", "");
	}    
    protected User processClientLogin(CustomHealthUserContextImpl ctx, LoginParam loginParam) throws Exception {
        // 先根据输入参数，判断应该用哪个 loginHandler
        BaseLoginHandler loginHandler = findLoginHandler(ctx, loginParam);
        if (loginHandler == null) {
        	throwsExceptionWithMessage(ctx, "不支持"+loginParam.getLoginMethod()+"方式的登录");
        }
        // loginHandler 首先找到登录的目标用户。 如果登录失败，会抛出异常。 如果允许登录，
        User loginTarget = loginHandler.doLogin(ctx, loginParam);
        if (loginTarget == null) {
            // 如果没有抛异常，返回null，说明是个 '新建用户'。 触发'onNewLogin'方法
            loginTarget = onNewLogin(ctx, loginParam, loginHandler);
            loginHandler.createLoginInfoForNewTarget(ctx, loginTarget);
        }
        // 找到登录目标对应的 secUser 和 userApp
        SecUser secUser = findSecUserByLoginTarget(ctx, loginTarget);
        if (secUser == null) {
            secUser = whenUserNotRelatedWithSecUser(ctx, loginHandler, loginTarget);
        }
        UserApp userApp = ctx.getUserAppByBindedEntity(ctx, loginTarget);
		// 缓存
        String loginInfoCacheToken = createLoginInfoCacheToken(ctx, loginTarget);
        ctx.setTokenId(loginInfoCacheToken);
        cacheLoginedUser(ctx, loginInfoCacheToken, secUser, userApp, loginTarget);
        String jwtToken = makeClientToken(ctx, loginInfoCacheToken, loginTarget);
        ctx.setCurrentUserInfo(loginTarget);
        saveTokenInResponse(ctx, jwtToken);
        afterLogined(ctx, loginTarget);
        return loginTarget;
    }

	protected void afterLogined(CustomHealthUserContextImpl ctx, User loginTarget) {
    	// 默认不用再做什么了
	}

	protected void saveTokenInResponse(CustomHealthUserContextImpl ctx, String jwtToken) {
    	ctx.setResponseHeader(JWTUtil.HEADER_NAME, jwtToken);
	}

	protected String makeClientToken(CustomHealthUserContextImpl ctx, String loginInfoCacheToken, User loginTarget) {
		String jwtTokenString = JWTUtil.getJwtToken(loginInfoCacheToken,
				HealthBaseUtils.getOssUploadFolderName(User.INTERNAL_TYPE, loginTarget.getId(), ctx.isProductEnvironment()),
				ctx.isProductEnvironment()?"product":"test", ctx.tokenId(),ctx.now());
		return jwtTokenString;
	}

	protected int getLoginInfoCachePeriod(CustomHealthUserContextImpl ctx) {
		return (int) (7*DateTimeUtil.DAY_IN_MS/DateTimeUtil.SECOND_IN_MS);
	}
	protected void cacheLoginedUser(CustomHealthUserContextImpl ctx, String loginInfoCacheToken, SecUser secUser, UserApp userApp, User loginTarget) {
		Map<String, Object> data = MapUtil.putIf(secUser!=null, "secUserId", ()->secUser.getId())
				.putIf(userApp!=null, "userAppId", ()->userApp.getId())
				.put("loginAs", loginTarget.getId())
				.putIf(ctx.getFromContextLocalStorage("wechatLoginSessionKey")!=null, "wechatLoginSessionKey", ctx.getFromContextLocalStorage("wechatLoginSessionKey"))
				.put("fromTime", ctx.now())
				.into_map();
		// 一周没有活动，就重新登录。 超过5天则更新
		int cachePeriod = getLoginInfoCachePeriod(ctx);
		ctx.putToCache("login_"+loginInfoCacheToken, data, cachePeriod);

		// 保证 this.currentApp(ctx) 能够拿到 userApp
		ctx.putToCache(getCurrentAppKey(ctx), userApp, cachePeriod);
		ctx.setSecUser(secUser);
	}

	// 为当前登陆的用户创建一个 tokenKey，用于cache，并将其生成jwtToken
	protected String createLoginInfoCacheToken(CustomHealthUserContextImpl ctx, User loginTarget) {
    	// 随机生成一个字符串
    	return HealthBaseUtils.hashWithSHA256(loginTarget.getId(), RandomUtil.randomChars(8));
	}

	// 如果对应的secUser不存在，一般来说是代码或者数据异常，需要人工干预。 也可以不调查原因，直接创建secUser和userApp。 默认的实现是 抛异常。
    protected SecUser whenUserNotRelatedWithSecUser(CustomHealthUserContextImpl ctx, BaseLoginHandler loginHandler, User loginTarget) throws Exception {
        throwsExceptionWithMessage(ctx, String.format("该用户(%s)没有关联系统账户，请联系客户处理账号异常。", loginTarget.getId()));
        return null;
    }

    protected void throwsExceptionWithMessage(CustomHealthUserContextImpl ctx, String message) throws HealthException {
    	throw new HealthException(message);
    }

    protected SecUser findSecUserByLoginTarget(CustomHealthUserContextImpl ctx, User loginTarget) {
    	String sql = "select SU.* from sec_user_data SU left join user_app_data UA on UA.sec_user = SU.id " + 
    			"	where UA.object_type=? and UA.object_id=? and SU.domain is not null";
    	SmartList<SecUser> suList = secUserDaoOf(ctx).queryList(sql, loginTarget.getInternalType(), loginTarget.getId());
        return suList.first();
    }

    
    // 默认新用户登录，自动创建账户。 具体的字段需要业务实现。所以此处为 abstract
    protected abstract User onNewLogin(CustomHealthUserContextImpl ctx, LoginParam loginParam, BaseLoginHandler loginHandler) throws Exception;

    protected BaseLoginHandler findLoginHandler(CustomHealthUserContextImpl ctx, LoginParam loginParam) {
		switch (loginParam.getLoginMethod()) {
			case BaseLoginHandler.DEBUG: {
				return new BaseLoginHandler() {
					String did;
					@Override
                    public User doLogin(CustomHealthUserContextImpl ctx, LoginParam loginParam) throws Exception{
                    	if (ctx.isProductEnvironment()){
                    		throw new Exception("不能在生产环境使用此方式登录");
                    	}
                    	String id = loginParam.getId();
						return userDaoOf(ctx).load(id, EO);
                    }
                    @Override
					public Map<String, Object> getProcessedLoginInfo(CustomHealthUserContextImpl ctx) {
						return MapUtil.put("loginMethod", BaseLoginHandler.DEBUG)
								.put("id", did).into_map();
					}
					@Override
					public void createLoginInfoForNewTarget(CustomHealthUserContextImpl ctx, User loginTarget) {
						// 调试登录直接使用ID,无需额外操作
					}
				};
			}
            case BaseLoginHandler.WECHAT_APP:{
                return new BaseLoginHandler() {
                	String wxOpenId;
                	String wxSessionKey;
                    @Override
                    public User doLogin(CustomHealthUserContextImpl ctx, LoginParam loginParam) throws Exception{
                        WxMaService wxService = getWxMaService();
                        String code = loginParam.getCode();
                        WxMaJscode2SessionResult sessionInfo = wxService.jsCode2SessionInfo(code);
                        String openId = sessionInfo.getOpenid();
                        String userSessionKey = sessionInfo.getSessionKey();
                        wxOpenId = openId;
						wxSessionKey = userSessionKey;
                        ctx.putIntoContextLocalStorage("wechatLoginSessionKey", userSessionKey);
                        String sql = "select * from wechat_login_info_data where open_id=? and user is not null";
						SmartList<WechatLoginInfo> infoList = wechatLoginInfoDaoOf(ctx).queryList(sql, openId);
						if (infoList == null || infoList.isEmpty()) {
							return null;
						}
						WechatLoginInfo logInfo = infoList.first();
						logInfo.updateSessionKey(userSessionKey);
						wechatLoginInfoManagerOf(ctx).internalSaveWechatLoginInfo(ctx, logInfo, EO);
						try{
							return userDaoOf(ctx).load(logInfo.getUser().getId(), EO);
						}catch(Exception e){
							// 找不到不要紧 后面会处理
							return null;
						}
                    }
                    @Override
					public Map<String, Object> getProcessedLoginInfo(CustomHealthUserContextImpl ctx) {
						return MapUtil.put("loginMethod", BaseLoginHandler.WECHAT_APP)
								.put("openId", wxOpenId)
								.put("sessionKey", wxSessionKey).into_map();
					}
					@Override
					public void createLoginInfoForNewTarget(CustomHealthUserContextImpl ctx, User loginTarget) {
						try {
							WxMaService wxService = getWxMaService();
							wechatLoginInfoManagerOf(ctx).createWechatLoginInfo(ctx, loginTarget.getId(),
									wxService.getWxMaConfig().getAppid(), wxOpenId, wxSessionKey);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
                };
            }
            default:
        }
        return null;
    }
    @Override
    public void onAccess(BaseUserContext baseUserContext, String methodName, Object[] parameters) {
        super.onAccess(baseUserContext, methodName, parameters);
        // 当前调用的信息保存在（类似于）session里
        CustomHealthUserContextImpl ctx = (CustomHealthUserContextImpl) baseUserContext;
        ctx.putToCache(HealthBaseUtils.getCacheAccessKey(ctx), Boolean.TRUE, (int)(1*DateTimeUtil.HOUR_IN_MS/DateTimeUtil.SECOND_IN_MS)); // 1个小时
        ctx.putIntoContextLocalStorage(HealthBaseConstants.CACHE_KEY_ACCESS_METHOD, methodName);
        ctx.putIntoContextLocalStorage(HealthBaseConstants.CACHE_KEY_ACCESS_PARAMS, parameters);

        ctx.saveAccessInfo(this.getBeanName(), methodName, parameters);
    }

    @Override
    public Object checkAccess(BaseUserContext baseUserContext, String methodName, Object[] parameters)
            throws IllegalAccessException {
        CustomHealthUserContextImpl ctx = (CustomHealthUserContextImpl) baseUserContext;
        try {
			User logined = tryToLoadUserFromCache(ctx);
			boolean needLogin = isMethodNeedLogin(ctx, methodName, parameters);
			boolean shouldRefreshToken = shouldRefreshToken(ctx);
			String jwtToken = ctx.getInputJwtToken();
			if (logined != null) {
				if (shouldRefreshToken) {
					String loginInfoCacheToken = createLoginInfoCacheToken(ctx, logined);
					ctx.setJwtKeyId(loginInfoCacheToken);
					ctx.setTokenId(loginInfoCacheToken);
					cacheLoginedUser(ctx, ctx.getJwtKeyId(), ctx.getSecUser(), this.currentApp(ctx), logined );
					jwtToken = makeClientToken(ctx, loginInfoCacheToken, logined);
				}
				ctx.setCurrentUserInfo(logined);
				saveTokenInResponse(ctx, jwtToken);
				return accessOK();
			}
			// 没有登录
			if (needLogin) {
				ctx.forceResponseXClassHeader("com.terapico.appview.LoginForm");
				return accessFail("请登录后再继续使用。");
			}
			// 无需登录，那就生成一个临时的token
			if (shouldRefreshToken) {
				logined = User.withId("anonymuse");
				String loginInfoCacheToken = createLoginInfoCacheToken(ctx, logined);
		        ctx.setTokenId(loginInfoCacheToken);
		        cacheLoginedUser(ctx, loginInfoCacheToken, null, null, logined);
		        jwtToken = makeClientToken(ctx, loginInfoCacheToken, logined);
		        ctx.setCurrentUserInfo(null);
		        saveTokenInResponse(ctx, jwtToken);
			}
	        return accessOK();
		} catch (Exception e) {
			e.printStackTrace();
			throw new IllegalAccessException(e.getMessage());
		}
        
    }
    
    protected boolean shouldRefreshToken(CustomHealthUserContextImpl ctx) {
    	Map<String, Object> data = (Map<String, Object>) ctx.getFromContextLocalStorage("cached_login_data");
    	if (data == null || !data.containsKey("fromTime")) {
    		return true;
    	}
    	Date date = DateTimeUtil.parseInputDateTime(String.valueOf(data.get("fromTime")));
    	if (ctx.now().getTime() - date.getTime() > (5*DateTimeUtil.DAY_IN_MS)) {
    		return true;
    	}
		return false;
	}
	protected boolean isMethodNeedLogin(CustomHealthUserContextImpl ctx, String methodName, Object[] parameters) {
		// 默认用名字前缀来判断
		return methodName.startsWith("customer");
	}
	protected User tryToLoadUserFromCache(CustomHealthUserContextImpl ctx) throws Exception {
		DecodedJWT token = getTokenFromClientRequest(ctx);
		if (token == null) {
			return null;
		}
		String jwtKeyId = token.getKeyId();
		ctx.setJwtKeyId(jwtKeyId);
		ctx.setTokenId(jwtKeyId);
		// 从 cache中取得
		Map<String, Object> data = ctx.getCachedObject("login_"+jwtKeyId, Map.class);
		if (data == null) {
			// 没有缓存，或者缓存过期了。
			return null;
		}
		ctx.putIntoContextLocalStorage("cached_login_data", data);
		String userAppId = (String) data.get("userAppId");
		if (userAppId == null) {
			return null;
		}
		String loginedId = (String) data.get("loginAs");
		try{
			User loginTarget = userDaoOf(ctx).load(loginedId, EO);
			UserApp userApp = userAppDaoOf(ctx).load(userAppId, UserAppTokens.start().withSecUser().done());
			ctx.setCurrentUserInfo(loginTarget);
			int cachePeriod = getLoginInfoCachePeriod(ctx);
			ctx.putToCache(getCurrentAppKey(ctx), userApp, cachePeriod);
			ctx.setSecUser(userApp.getSecUser());
			
			return loginTarget;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
    
    protected DecodedJWT getTokenFromClientRequest(CustomHealthUserContextImpl ctx) {
		// 先从Header取
    	String jwtToken = ctx.getRequestHeader(JWTUtil.HEADER_NAME);
    	if (TextUtil.isBlank(jwtToken)) {
    		// 试试从cookie取
    		jwtToken = getJwtTokenFromCookie(ctx);
    	}
    	if (!ctx.isProductEnvironment()) {
			ctx.log("Got jwt token: " + TextUtil.shrink(jwtToken, 20, 6,  "..."));
		}
		if (TextUtil.isBlank(jwtToken)) {
			ctx.log("令牌无效:请求中没有JWT token");
			return null;
		}
		jwtToken = JWTUtil.trimJwtToken(jwtToken);
		ctx.setInputJwtToken(jwtToken);
		
		DecodedJWT jwt = JWTUtil.decodeToken(jwtToken);
		if (jwt == null) {
			ctx.log("令牌无效:解析失败");
			return null;
		}
		String tokenEnvType = jwt.getClaim("envType").asString();
		String id = jwt.getKeyId();
		ctx.log(String.format("令牌(%s):%s",tokenEnvType,id));
		if (ctx.isProductEnvironment()) {
			if (!"product".equals(tokenEnvType)) {
				ctx.log("令牌无效:非生产环境token");
				return null;
			}
		} else {
			if (!"test".equals(tokenEnvType)) {
				ctx.log("令牌无效:非测试环境token");
				return null;
			}
		}
		return jwt;
	}
    protected String getJwtTokenFromCookie(CustomHealthUserContextImpl ctx) {
		if (ctx.isProductEnvironment()) {
			return null;
		}
		Cookie[] cookies = ctx.getCookies();
		if (cookies == null || cookies.length == 0) {
			return null;
		}
		for (Cookie cookie: cookies) {
			if (cookie.getName().equalsIgnoreCase(JWTUtil.HEADER_NAME)) {
				return cookie.getValue();
			}
		}
		return null;
	}
	

	// 处理请求：查看首页
	public static String makeViewHomepageUrl(CustomHealthUserContextImpl ctx){
		return makeUrl("viewHomepage");
	}
	// 处理请求：默认的客户端登录接口
	public static String makeClientLoginUrl(CustomHealthUserContextImpl ctx, com.terapico.caf.baseelement.LoginParam loginParam){
		return makeUrl("clientLogin", loginParam);
	}
	// 处理请求：这个程序员很懒,什么也没留下
	public static String makeUpdateProfileUrl(CustomHealthUserContextImpl ctx, String name , String avatar , String userType){
		return makeUrl("customerUpdateProfile", name , avatar , userType);
	}
	// 处理请求：这个程序员很懒,什么也没留下
	public static String makeSwitchToTeacherUrl(CustomHealthUserContextImpl ctx){
		return makeUrl("customerSwitchToTeacher");
	}
	// 处理请求：这个程序员很懒,什么也没留下
	public static String makeAddClassUrl(CustomHealthUserContextImpl ctx){
		return makeUrl("customerAddClass");
	}
	// 处理请求：创建班级
	public static String makeSubmitClassUrl(CustomHealthUserContextImpl ctx){
		return makeUrl("customerSubmitClass", "formData");
	}
	// 处理请求：这个程序员很懒,什么也没留下
	public static String makeViewClassDetailUrl(CustomHealthUserContextImpl ctx, String teacherId){
		return makeUrl("customerViewClassDetail", teacherId);
	}
	// 处理请求：问卷详情
	public static String makeViewSurveyDetailUrl(CustomHealthUserContextImpl ctx, String surveyId){
		return makeUrl("customerViewSurveyDetail", surveyId);
	}
	// 处理请求：添加问卷
	public static String makeAddSurveyUrl(CustomHealthUserContextImpl ctx, String teacherId){
		return makeUrl("customerAddSurvey", teacherId);
	}
	// 处理请求：发布问卷
	public static String makePublishSurveyUrl(CustomHealthUserContextImpl ctx, String teacherId , String surveyDate){
		return makeUrl("customerPublishSurvey", teacherId , surveyDate);
	}
	// 处理请求：这个程序员很懒,什么也没留下
	public static String makeSwitchToStudentUrl(CustomHealthUserContextImpl ctx){
		return makeUrl("customerSwitchToStudent");
	}
	// 处理请求：学生登录后查看问卷
	public static String makeStudentViewSurveyUrl(CustomHealthUserContextImpl ctx, String surveyId){
		return makeUrl("customerStudentViewSurvey", surveyId);
	}
	// 处理请求：这个程序员很懒,什么也没留下
	public static String makeViewStudentSurveyDetailUrl(CustomHealthUserContextImpl ctx, String studentSurveyId){
		return makeUrl("customerViewStudentSurveyDetail", studentSurveyId);
	}
	// 处理请求：这个程序员很懒,什么也没留下
	public static String makeSubmitStudentSurveyUrl(CustomHealthUserContextImpl ctx, String surveyId , String answer , String studentName){
		return makeUrl("customerSubmitStudentSurvey", surveyId , answer , studentName);
	}

	/** 处理请求：查看首页. 返回值：PRC_BY_DEFAULT: ;  */
	protected abstract int processRequestViewHomepage(CustomHealthUserContextImpl ctx) throws Exception;
	/** 处理请求：默认的客户端登录接口. 返回值：PRC_BY_DEFAULT: ;  */
	protected abstract int processRequestClientLogin(CustomHealthUserContextImpl ctx) throws Exception;
	/** 处理请求：这个程序员很懒,什么也没留下. 返回值：PRC_SWITCHTOTEACHER: ; PRC_BY_DEFAULT: ;  */
	protected abstract int processRequestCustomerUpdateProfile(CustomHealthUserContextImpl ctx) throws Exception;
	/** 处理请求：这个程序员很懒,什么也没留下. 返回值：PRC_EMPTY_CLASS: ; PRC_BY_DEFAULT: ;  */
	protected abstract int processRequestCustomerSwitchToTeacher(CustomHealthUserContextImpl ctx) throws Exception;
	/** 处理请求：这个程序员很懒,什么也没留下. 返回值：PRC_BY_DEFAULT: ;  */
	protected abstract int processRequestCustomerAddClass(CustomHealthUserContextImpl ctx) throws Exception;
	/** 处理请求：创建班级. 返回值：PRC_BY_DEFAULT: ;  */
	protected abstract int processRequestCustomerSubmitClass(CustomHealthUserContextImpl ctx) throws Exception;
	/** 处理请求：这个程序员很懒,什么也没留下. 返回值：PRC_BY_DEFAULT: ;  */
	protected abstract int processRequestCustomerViewClassDetail(CustomHealthUserContextImpl ctx) throws Exception;
	/** 处理请求：问卷详情. 返回值：PRC_BY_DEFAULT: ;  */
	protected abstract int processRequestCustomerViewSurveyDetail(CustomHealthUserContextImpl ctx) throws Exception;
	/** 处理请求：添加问卷. 返回值：PRC_BY_DEFAULT: ;  */
	protected abstract int processRequestCustomerAddSurvey(CustomHealthUserContextImpl ctx) throws Exception;
	/** 处理请求：发布问卷. 返回值：PRC_BY_DEFAULT: ;  */
	protected abstract int processRequestCustomerPublishSurvey(CustomHealthUserContextImpl ctx) throws Exception;
	/** 处理请求：这个程序员很懒,什么也没留下. 返回值：PRC_BY_DEFAULT: ;  */
	protected abstract int processRequestCustomerSwitchToStudent(CustomHealthUserContextImpl ctx) throws Exception;
	/** 处理请求：学生登录后查看问卷. 返回值：PRC_SUBMITTED: ; PRC_BY_DEFAULT: ;  */
	protected abstract int processRequestCustomerStudentViewSurvey(CustomHealthUserContextImpl ctx) throws Exception;
	/** 处理请求：这个程序员很懒,什么也没留下. 返回值：PRC_BY_DEFAULT: ;  */
	protected abstract int processRequestCustomerViewStudentSurveyDetail(CustomHealthUserContextImpl ctx) throws Exception;
	/** 处理请求：这个程序员很懒,什么也没留下. 返回值：PRC_BY_DEFAULT: ;  */
	protected abstract int processRequestCustomerSubmitStudentSurvey(CustomHealthUserContextImpl ctx) throws Exception;

	protected SurveyListPage assemblerSurveyListPage(CustomHealthUserContextImpl ctx, String requestName)throws Exception {
		SurveyListPage page = new SurveyListPage();
		page.assemblerContent(ctx, requestName);
		return page;
	}
	protected StudentSurveyFormPage assemblerStudentSurveyFormPage(CustomHealthUserContextImpl ctx, String requestName)throws Exception {
		StudentSurveyFormPage page = new StudentSurveyFormPage();
		page.assemblerContent(ctx, requestName);
		return page;
	}
	protected StudentSurveyListPage assemblerStudentSurveyListPage(CustomHealthUserContextImpl ctx, String requestName)throws Exception {
		StudentSurveyListPage page = new StudentSurveyListPage();
		page.assemblerContent(ctx, requestName);
		return page;
	}
	protected AddSurveyPage assemblerAddSurveyPage(CustomHealthUserContextImpl ctx, String requestName)throws Exception {
		AddSurveyPage page = new AddSurveyPage();
		page.assemblerContent(ctx, requestName);
		return page;
	}
	protected MePage assemblerMePage(CustomHealthUserContextImpl ctx, String requestName)throws Exception {
		MePage page = new MePage();
		page.assemblerContent(ctx, requestName);
		return page;
	}
	protected StudentSurveyDetailPage assemblerStudentSurveyDetailPage(CustomHealthUserContextImpl ctx, String requestName)throws Exception {
		StudentSurveyDetailPage page = new StudentSurveyDetailPage();
		page.assemblerContent(ctx, requestName);
		return page;
	}
	protected AddClassPage assemblerAddClassPage(CustomHealthUserContextImpl ctx, String requestName)throws Exception {
		AddClassPage page = new AddClassPage();
		page.assemblerContent(ctx, requestName);
		return page;
	}
	protected SurveyDetailPage assemblerSurveyDetailPage(CustomHealthUserContextImpl ctx, String requestName)throws Exception {
		SurveyDetailPage page = new SurveyDetailPage();
		page.assemblerContent(ctx, requestName);
		return page;
	}
	protected HomePage assemblerHomePage(CustomHealthUserContextImpl ctx, String requestName)throws Exception {
		HomePage page = new HomePage();
		page.assemblerContent(ctx, requestName);
		return page;
	}

	protected abstract void getCurrentUserInfo(CustomHealthUserContextImpl ctx);
	protected abstract void ensureCurrentUserInfo(CustomHealthUserContextImpl ctx) throws Exception;
}
