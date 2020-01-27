
-- BUILD WITH MODEL TIME 200126T1313
-- Turn off safe mode
SET SQL_SAFE_UPDATES = 0;


drop database  if exists health;
create database health;
-- alter  database health  character set = utf8mb4  collate = utf8mb4_unicode_ci; -- 支持表情符号
use health;
set SESSION sql_mode='';

drop table  if exists platform_data;
create table platform_data (
	id                            	varchar(48)          not null            comment 'ID',
	name                          	varchar(200)                             comment '名称',
	description                   	longtext                                 comment '描述',
	version                       	int                                      comment '版本'
	
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = "平台";
-- primary key will be created later for better import performance

drop table  if exists province_data;
create table province_data (
	id                            	varchar(48)          not null            comment 'ID',
	name                          	varchar(50)                              comment '名称',
	platform                      	varchar(48)                              comment '平台',
	create_time                   	datetime                                 comment '创建时间',
	version                       	int                                      comment '版本'
	
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = "省";
-- primary key will be created later for better import performance

drop table  if exists city_data;
create table city_data (
	id                            	varchar(48)          not null            comment 'ID',
	name                          	varchar(50)                              comment '名称',
	province                      	varchar(48)                              comment '省',
	platform                      	varchar(48)                              comment '平台',
	create_time                   	datetime                                 comment '创建时间',
	version                       	int                                      comment '版本'
	
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = "城市";
-- primary key will be created later for better import performance

drop table  if exists district_data;
create table district_data (
	id                            	varchar(48)          not null            comment 'ID',
	name                          	varchar(50)                              comment '名称',
	city                          	varchar(48)                              comment '城市',
	platform                      	varchar(48)                              comment '平台',
	create_time                   	datetime                                 comment '创建时间',
	version                       	int                                      comment '版本'
	
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = "区/县";
-- primary key will be created later for better import performance

drop table  if exists location_data;
create table location_data (
	id                            	varchar(48)          not null            comment 'ID',
	name                          	varchar(16)                              comment '名称',
	address                       	varchar(100)                             comment '地址',
	district                      	varchar(48)                              comment '区/县',
	province                      	varchar(48)                              comment '省',
	latitude                      	numeric(8,5)                             comment '纬度',
	longitude                     	numeric(9,5)                             comment '经度',
	version                       	int                                      comment '版本'
	
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = "位置";
-- primary key will be created later for better import performance

drop table  if exists school_class_data;
create table school_class_data (
	id                            	varchar(48)          not null            comment 'ID',
	name                          	varchar(99)                              comment '名称',
	class_teacher                 	varchar(48)                              comment '班主任',
	create_time                   	datetime                                 comment '创建时间',
	platform                      	varchar(48)                              comment '平台',
	schoole                       	varchar(16)                              comment 'Schoole',
	cq                            	varchar(48)                              comment 'Cq',
	version                       	int                                      comment '版本'
	
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = "学校类";
-- primary key will be created later for better import performance

drop table  if exists teacher_data;
create table teacher_data (
	id                            	varchar(48)          not null            comment 'ID',
	name                          	varchar(20)                              comment '名称',
	mobile                        	varchar(44)                              comment '手机号码',
	schoole                       	varchar(16)                              comment 'Schoole',
	create_time                   	datetime                                 comment '创建时间',
	platform                      	varchar(48)                              comment '平台',
	cq                            	varchar(48)                              comment 'Cq',
	version                       	int                                      comment '版本'
	
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = "老师";
-- primary key will be created later for better import performance

drop table  if exists guardian_data;
create table guardian_data (
	id                            	varchar(48)          not null            comment 'ID',
	name                          	varchar(20)                              comment '名称',
	mobile                        	varchar(44)                              comment '手机号码',
	address                       	varchar(48)                              comment '地址',
	wechat_user                   	varchar(48)                              comment '微信用户',
	create_time                   	datetime                                 comment '创建时间',
	platform                      	varchar(48)                              comment '平台',
	cq                            	varchar(48)                              comment 'Cq',
	version                       	int                                      comment '版本'
	
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = "《卫报》";
-- primary key will be created later for better import performance

drop table  if exists question_data;
create table question_data (
	id                            	varchar(48)          not null            comment 'ID',
	topic                         	varchar(50)                              comment '活动主题',
	question_type                 	varchar(48)                              comment '问题类型',
	option_a                      	varchar(99)                              comment 'A选项',
	option_b                      	varchar(99)                              comment 'B选项',
	option_c                      	varchar(99)                              comment 'C选项',
	option_d                      	varchar(99)                              comment 'D选项',
	platform                      	varchar(48)                              comment '平台',
	version                       	int                                      comment '版本'
	
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = "检查问题";
-- primary key will be created later for better import performance

drop table  if exists question_type_data;
create table question_type_data (
	id                            	varchar(48)          not null            comment 'ID',
	name                          	varchar(12)                              comment '名称',
	code                          	varchar(48)                              comment '编码',
	platform                      	varchar(48)                              comment '平台',
	version                       	int                                      comment '版本'
	
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = "问题类型";
-- primary key will be created later for better import performance

drop table  if exists question_source_data;
create table question_source_data (
	id                            	varchar(48)          not null            comment 'ID',
	name                          	varchar(24)                              comment '名称',
	code                          	varchar(24)                              comment '编码',
	platform                      	varchar(48)                              comment '平台',
	version                       	int                                      comment '版本'
	
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = "问题的来源";
-- primary key will be created later for better import performance

drop table  if exists class_question_data;
create table class_question_data (
	id                            	varchar(48)          not null            comment 'ID',
	topic                         	varchar(50)                              comment '活动主题',
	question_type                 	varchar(48)                              comment '问题类型',
	option_a                      	varchar(99)                              comment 'A选项',
	option_b                      	varchar(99)                              comment 'B选项',
	option_c                      	varchar(99)                              comment 'C选项',
	option_d                      	varchar(99)                              comment 'D选项',
	question_source               	varchar(48)                              comment '问题的来源',
	creator                       	varchar(48)                              comment '创建人名称',
	cq                            	varchar(48)                              comment 'Cq',
	version                       	int                                      comment '版本'
	
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = "类问题";
-- primary key will be created later for better import performance

drop table  if exists class_daily_health_survey_data;
create table class_daily_health_survey_data (
	id                            	varchar(48)          not null            comment 'ID',
	name                          	varchar(88)                              comment '名称',
	school_class                  	varchar(48)                              comment '学校类',
	survey_time                   	datetime                                 comment '调查的时间',
	creator                       	varchar(48)                              comment '创建人名称',
	cq                            	varchar(48)                              comment 'Cq',
	version                       	int                                      comment '版本'
	
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = "每日健康调查";
-- primary key will be created later for better import performance

drop table  if exists daily_survey_question_data;
create table daily_survey_question_data (
	id                            	varchar(48)          not null            comment 'ID',
	topic                         	varchar(50)                              comment '活动主题',
	question_type                 	varchar(48)                              comment '问题类型',
	option_a                      	varchar(99)                              comment 'A选项',
	option_b                      	varchar(99)                              comment 'B选项',
	option_c                      	varchar(99)                              comment 'C选项',
	option_d                      	varchar(99)                              comment 'D选项',
	class_daily_health_survey     	varchar(48)                              comment '每日健康调查',
	class_question                	varchar(48)                              comment '类问题',
	version                       	int                                      comment '版本'
	
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = "每日调查问题";
-- primary key will be created later for better import performance

drop table  if exists student_data;
create table student_data (
	id                            	varchar(48)          not null            comment 'ID',
	name                          	varchar(20)                              comment '名称',
	gender                        	varchar(24)                              comment '性别',
	guardian                      	varchar(48)                              comment '《卫报》',
	school_class                  	varchar(48)                              comment '学校类',
	student_id                    	varchar(3)                               comment '学生证',
	cq                            	varchar(48)                              comment 'Cq',
	version                       	int                                      comment '版本'
	
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = "学生";
-- primary key will be created later for better import performance

drop table  if exists student_health_survey_data;
create table student_health_survey_data (
	id                            	varchar(48)          not null            comment 'ID',
	student                       	varchar(48)                              comment '学生',
	answer_time                   	datetime                                 comment '回答时间',
	survey_status                 	varchar(48)                              comment '调查现状',
	school_class                  	varchar(48)                              comment '学校类',
	class_daily_health_survey     	varchar(48)                              comment '每日健康调查',
	create_time                   	datetime                                 comment '创建时间',
	last_update_time              	datetime                                 comment '最后更新时间',
	cq                            	varchar(48)                              comment 'Cq',
	version                       	int                                      comment '版本'
	
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = "学生健康调查";
-- primary key will be created later for better import performance

drop table  if exists student_daily_answer_data;
create table student_daily_answer_data (
	id                            	varchar(48)          not null            comment 'ID',
	student_health_survey         	varchar(48)                              comment '学生健康调查',
	question                      	varchar(48)                              comment '检查问题',
	answer                        	varchar(99)                              comment '回答',
	create_time                   	datetime                                 comment '创建时间',
	last_update_time              	datetime                                 comment '最后更新时间',
	cq                            	varchar(48)                              comment 'Cq',
	version                       	int                                      comment '版本'
	
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = "学生每天回答";
-- primary key will be created later for better import performance

drop table  if exists survey_status_data;
create table survey_status_data (
	id                            	varchar(48)          not null            comment 'ID',
	name                          	varchar(12)                              comment '名称',
	code                          	varchar(44)                              comment '编码',
	platform                      	varchar(48)                              comment '平台',
	version                       	int                                      comment '版本'
	
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = "调查现状";
-- primary key will be created later for better import performance

drop table  if exists wechat_user_data;
create table wechat_user_data (
	id                            	varchar(48)          not null            comment 'ID',
	name                          	varchar(200)                             comment '名称',
	avatar                        	varchar(512) CHARACTER SET ascii COLLATE ascii_general_ci                     comment '头像',
	address                       	varchar(48)                              comment '地址',
	user_type                     	varchar(48)                              comment '用户类型',
	create_time                   	datetime                                 comment '创建时间',
	platform                      	varchar(48)                              comment '平台',
	version                       	int                                      comment '版本'
	
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = "微信用户";
-- primary key will be created later for better import performance

drop table  if exists user_type_data;
create table user_type_data (
	id                            	varchar(48)          not null            comment 'ID',
	name                          	varchar(8)                               comment '名称',
	code                          	varchar(32)                              comment '编码',
	platform                      	varchar(48)                              comment '平台',
	version                       	int                                      comment '版本'
	
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = "用户类型";
-- primary key will be created later for better import performance

drop table  if exists wechat_login_info_data;
create table wechat_login_info_data (
	id                            	varchar(48)          not null            comment 'ID',
	wechat_user                   	varchar(48)                              comment '微信用户',
	app_id                        	varchar(100)                             comment '应用程序Id',
	open_id                       	varchar(100)                             comment '开放Id',
	session_key                   	varchar(200)                             comment '会话密钥',
	last_update_time              	datetime                                 comment '最后更新时间',
	version                       	int                                      comment '版本'
	
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = "微信登录信息";
-- primary key will be created later for better import performance

drop table  if exists change_request_data;
create table change_request_data (
	id                            	varchar(48)          not null            comment 'ID',
	name                          	varchar(50)                              comment '名称',
	create_time                   	datetime                                 comment '创建时间',
	remote_ip                     	varchar(40)                              comment '访问IP',
	request_type                  	varchar(48)                              comment '请求类型',
	platform                      	varchar(48)                              comment '平台',
	version                       	int                                      comment '版本'
	
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = "变更请求";
-- primary key will be created later for better import performance

drop table  if exists change_request_type_data;
create table change_request_type_data (
	id                            	varchar(48)          not null            comment 'ID',
	name                          	varchar(100)                             comment '名称',
	code                          	varchar(56)                              comment '编码',
	icon                          	varchar(16)                              comment '图标',
	display_order                 	int                                      comment '顺序',
	bind_types                    	longtext                                 comment '绑定类型',
	step_configuration            	longtext                                 comment '分步配置',
	platform                      	varchar(48)                              comment '平台',
	version                       	int                                      comment '版本'
	
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = "变更请求类型";
-- primary key will be created later for better import performance

drop table  if exists user_domain_data;
create table user_domain_data (
	id                            	varchar(48)          not null            comment 'ID',
	name                          	varchar(16)                              comment '名称',
	version                       	int                                      comment '版本'
	
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = "用户域";
-- primary key will be created later for better import performance

drop table  if exists user_white_list_data;
create table user_white_list_data (
	id                            	varchar(48)          not null            comment 'ID',
	user_identity                 	varchar(40)                              comment '用户身份',
	user_special_functions        	varchar(200)                             comment '用户特殊功能',
	domain                        	varchar(48)                              comment '域',
	version                       	int                                      comment '版本'
	
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = "用户白名单";
-- primary key will be created later for better import performance

drop table  if exists sec_user_data;
create table sec_user_data (
	id                            	varchar(48)          not null            comment 'ID',
	login                         	varchar(256)                             comment '登录',
	mobile                        	varchar(11)                              comment '手机号码',
	email                         	varchar(256)                             comment '电子邮件',
	pwd                           	varchar(64)                              comment '密码',
	weixin_openid                 	varchar(128)                             comment '微信openid',
	weixin_appid                  	varchar(128)                             comment '微信Appid',
	access_token                  	varchar(128)                             comment '访问令牌',
	verification_code             	int                                      comment '验证码',
	verification_code_expire      	datetime                                 comment '验证码过期',
	last_login_time               	datetime                                 comment '最后登录时间',
	domain                        	varchar(48)                              comment '域',
	version                       	int                                      comment '版本'
	
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = "安全用户";
-- primary key will be created later for better import performance

drop table  if exists user_app_data;
create table user_app_data (
	id                            	varchar(48)          not null            comment 'ID',
	title                         	varchar(300)                             comment '标题',
	sec_user                      	varchar(48)                              comment '安全用户',
	app_icon                      	varchar(36)                              comment '应用程序图标',
	full_access                   	tinyint                                  comment '完全访问',
	permission                    	varchar(16)                              comment '许可',
	object_type                   	varchar(100)                             comment '访问对象类型',
	object_id                     	varchar(40)                              comment '对象ID',
	location                      	varchar(48)                              comment '位置',
	version                       	int                                      comment '版本'
	
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = "用户应用程序";
-- primary key will be created later for better import performance

drop table  if exists quick_link_data;
create table quick_link_data (
	id                            	varchar(48)          not null            comment 'ID',
	name                          	varchar(200)                             comment '名称',
	icon                          	varchar(200)                             comment '图标',
	image_path                    	varchar(512) CHARACTER SET ascii COLLATE ascii_general_ci                     comment '图片路径',
	link_target                   	varchar(200)                             comment '链接的目标',
	create_time                   	datetime                                 comment '创建时间',
	app                           	varchar(48)                              comment '应用程序',
	version                       	int                                      comment '版本'
	
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = "快速链接";
-- primary key will be created later for better import performance

drop table  if exists list_access_data;
create table list_access_data (
	id                            	varchar(48)          not null            comment 'ID',
	name                          	varchar(200)                             comment '名称',
	internal_name                 	varchar(200)                             comment '内部名称',
	read_permission               	tinyint                                  comment '读权限',
	create_permission             	tinyint                                  comment '创建权限',
	delete_permission             	tinyint                                  comment '删除权限',
	update_permission             	tinyint                                  comment '更新权限',
	execution_permission          	tinyint                                  comment '执行权限',
	app                           	varchar(48)                              comment '应用程序',
	version                       	int                                      comment '版本'
	
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = "访问列表";
-- primary key will be created later for better import performance

drop table  if exists object_access_data;
create table object_access_data (
	id                            	varchar(48)          not null            comment 'ID',
	name                          	varchar(28)                              comment '名称',
	object_type                   	varchar(112)                             comment '访问对象类型',
	list1                         	varchar(80)                              comment '列表1',
	list2                         	varchar(80)                              comment '列表2',
	list3                         	varchar(80)                              comment '列表3',
	list4                         	varchar(80)                              comment '列表4',
	list5                         	varchar(80)                              comment '列表5',
	list6                         	varchar(80)                              comment '列表6',
	list7                         	varchar(80)                              comment '列表7',
	list8                         	varchar(80)                              comment '列表8',
	list9                         	varchar(80)                              comment '列表9',
	app                           	varchar(48)                              comment '应用程序',
	version                       	int                                      comment '版本'
	
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = "对象访问";
-- primary key will be created later for better import performance

drop table  if exists login_history_data;
create table login_history_data (
	id                            	varchar(48)          not null            comment 'ID',
	login_time                    	datetime                                 comment '登录时间',
	from_ip                       	varchar(44)                              comment '来自IP',
	description                   	varchar(16)                              comment '描述',
	sec_user                      	varchar(48)                              comment '安全用户',
	version                       	int                                      comment '版本'
	
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = "登录历史";
-- primary key will be created later for better import performance

drop table  if exists generic_form_data;
create table generic_form_data (
	id                            	varchar(48)          not null            comment 'ID',
	title                         	varchar(20)                              comment '标题',
	description                   	varchar(48)                              comment '描述',
	version                       	int                                      comment '版本'
	
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = "通用的形式";
-- primary key will be created later for better import performance

drop table  if exists form_message_data;
create table form_message_data (
	id                            	varchar(48)          not null            comment 'ID',
	title                         	varchar(24)                              comment '标题',
	form                          	varchar(48)                              comment '形式',
	level                         	varchar(28)                              comment '水平',
	version                       	int                                      comment '版本'
	
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = "表单信息";
-- primary key will be created later for better import performance

drop table  if exists form_field_message_data;
create table form_field_message_data (
	id                            	varchar(48)          not null            comment 'ID',
	title                         	varchar(16)                              comment '标题',
	parameter_name                	varchar(16)                              comment '参数名称',
	form                          	varchar(48)                              comment '形式',
	level                         	varchar(28)                              comment '水平',
	version                       	int                                      comment '版本'
	
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = "表单字段的信息";
-- primary key will be created later for better import performance

drop table  if exists form_field_data;
create table form_field_data (
	id                            	varchar(48)          not null            comment 'ID',
	label                         	varchar(12)                              comment '标签',
	locale_key                    	varchar(44)                              comment '语言环境的关键',
	parameter_name                	varchar(16)                              comment '参数名称',
	type                          	varchar(36)                              comment '类型',
	form                          	varchar(48)                              comment '形式',
	placeholder                   	varchar(48)                              comment '占位符',
	default_value                 	varchar(12)                              comment '默认值',
	description                   	varchar(48)                              comment '描述',
	field_group                   	varchar(16)                              comment '字段组',
	minimum_value                 	varchar(60)                              comment '最小值',
	maximum_value                 	varchar(72)                              comment '最大值',
	required                      	tinyint                                  comment '要求',
	disabled                      	tinyint                                  comment '是否冻结',
	custom_rendering              	tinyint                                  comment '自定义渲染',
	candidate_values              	varchar(12)                              comment '候选人的价值观',
	suggest_values                	varchar(12)                              comment '建议值',
	version                       	int                                      comment '版本'
	
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = "表单字段";
-- primary key will be created later for better import performance

drop table  if exists form_action_data;
create table form_action_data (
	id                            	varchar(48)          not null            comment 'ID',
	label                         	varchar(8)                               comment '标签',
	locale_key                    	varchar(16)                              comment '语言环境的关键',
	action_key                    	varchar(24)                              comment '行动的关键',
	level                         	varchar(28)                              comment '水平',
	url                           	varchar(168)                             comment 'url',
	form                          	varchar(48)                              comment '形式',
	version                       	int                                      comment '版本'
	
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = "表单动作";
-- primary key will be created later for better import performance

drop table  if exists candidate_container_data;
create table candidate_container_data (
	id                            	varchar(48)          not null            comment 'ID',
	name                          	varchar(28)                              comment '名称',
	version                       	int                                      comment '版本'
	
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = "候选人容器";
-- primary key will be created later for better import performance

drop table  if exists candidate_element_data;
create table candidate_element_data (
	id                            	varchar(48)          not null            comment 'ID',
	name                          	varchar(200)                             comment '名称',
	type                          	varchar(200)                             comment '类型',
	image                         	varchar(512) CHARACTER SET ascii COLLATE ascii_general_ci                     comment '图片',
	container                     	varchar(48)                              comment '容器',
	version                       	int                                      comment '版本'
	
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = "候选人元素";
-- primary key will be created later for better import performance

drop table  if exists wechat_workapp_identify_data;
create table wechat_workapp_identify_data (
	id                            	varchar(48)          not null            comment 'ID',
	corp_id                       	varchar(100)                             comment '公司标识',
	user_id                       	varchar(100)                             comment '用户Id',
	sec_user                      	varchar(48)                              comment '安全用户',
	create_time                   	datetime                                 comment '创建时间',
	last_login_time               	datetime                                 comment '最后登录时间',
	version                       	int                                      comment '版本'
	
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = "微信Workapp识别";
-- primary key will be created later for better import performance

drop table  if exists wechat_miniapp_identify_data;
create table wechat_miniapp_identify_data (
	id                            	varchar(48)          not null            comment 'ID',
	open_id                       	varchar(128)                             comment '开放Id',
	app_id                        	varchar(128)                             comment '应用程序Id',
	sec_user                      	varchar(48)                              comment '安全用户',
	create_time                   	datetime                                 comment '创建时间',
	last_login_time               	datetime                                 comment '最后登录时间',
	version                       	int                                      comment '版本'
	
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = "微信Miniapp识别";
-- primary key will be created later for better import performance




insert into platform_data values
	('P000001','健康状态调查平台','    一段样例文字。    一段样例文字。\n可以分段。可以分段。\n\n可以空行。可以空行。\n\n','1');

insert into province_data values
	('P000001','四川省','P000001','2020-01-20 13:11:01','1'),
	('P000002','四川省0002','P000001','2020-01-15 23:02:18','1');

insert into city_data values
	('C000001','成都市','P000001','P000001','2020-01-23 10:38:44','1'),
	('C000002','成都市0002','P000001','P000001','2020-01-11 21:43:02','1'),
	('C000003','成都市0003','P000002','P000001','2020-01-13 02:26:21','1'),
	('C000004','成都市0004','P000002','P000001','2020-01-25 04:24:50','1');

insert into district_data values
	('D000001','高新区','C000001','P000001','2020-01-18 18:45:40','1'),
	('D000002','高新区0002','C000001','P000001','2020-01-17 10:47:30','1'),
	('D000003','高新区0003','C000002','P000001','2020-01-11 04:41:33','1'),
	('D000004','高新区0004','C000002','P000001','2020-01-23 05:21:00','1'),
	('D000005','高新区0005','C000003','P000001','2020-01-22 03:48:57','1'),
	('D000006','高新区0006','C000003','P000001','2020-01-11 06:06:45','1'),
	('D000007','高新区0007','C000004','P000001','2020-01-07 18:22:30','1'),
	('D000008','高新区0008','C000004','P000001','2020-01-08 20:07:51','1');

insert into location_data values
	('L000001','公司地址','四川省成都市高新区南华路100号','D000001','P000001','30.132477359097713','103.0967496631097','1'),
	('L000002','家庭地址','四川省成都市高新区南华路100号0002','D000001','P000001','31.72738548665103','104.46662156884535','1'),
	('L000003','公司地址','四川省成都市高新区南华路100号0003','D000002','P000001','30.433942346127527','104.15872131674487','1'),
	('L000004','家庭地址','四川省成都市高新区南华路100号0004','D000002','P000001','29.519510637417618','104.09085222613245','1'),
	('L000005','公司地址','四川省成都市高新区南华路100号0005','D000003','P000001','30.116928147691','105.33273647247563','1'),
	('L000006','家庭地址','四川省成都市高新区南华路100号0006','D000003','P000001','29.467607828570788','103.26715165685641','1'),
	('L000007','公司地址','四川省成都市高新区南华路100号0007','D000004','P000001','29.726517791835644','105.44166292466566','1'),
	('L000008','家庭地址','四川省成都市高新区南华路100号0008','D000004','P000001','30.736829084148937','105.36607310066562','1'),
	('L000009','公司地址','四川省成都市高新区南华路100号0009','D000005','P000002','30.174218161985458','103.72089899424721','1'),
	('L000010','家庭地址','四川省成都市高新区南华路100号0010','D000005','P000002','31.60651303462783','105.73897619935383','1'),
	('L000011','公司地址','四川省成都市高新区南华路100号0011','D000006','P000002','31.972758973933274','105.61956664645815','1'),
	('L000012','家庭地址','四川省成都市高新区南华路100号0012','D000006','P000002','31.212900398603466','103.1178317765414','1'),
	('L000013','公司地址','四川省成都市高新区南华路100号0013','D000007','P000002','32.27806904086057','104.72985879570082','1'),
	('L000014','家庭地址','四川省成都市高新区南华路100号0014','D000007','P000002','30.82172924780653','103.14408056797168','1'),
	('L000015','公司地址','四川省成都市高新区南华路100号0015','D000008','P000002','30.860347652391372','103.75527597865779','1'),
	('L000016','家庭地址','四川省成都市高新区南华路100号0016','D000008','P000002','30.742373902671247','104.6106219948881','1');

insert into school_class_data values
	('SC000001','教科院一年级5班','T000001','2020-01-22 01:17:12','P000001','益州小学','CR000001','1'),
	('SC000002','教科院二年级3班','T000001','2020-01-21 06:03:16','P000001','大源中学','CR000001','1'),
	('SC000003','教科院一年级5班','T000002','2020-01-08 15:57:18','P000001','南山中学','CR000001','1'),
	('SC000004','教科院二年级3班','T000002','2020-01-19 09:16:20','P000001','益州小学','CR000001','1'),
	('SC000005','教科院一年级5班','T000003','2020-01-15 03:37:27','P000001','大源中学','CR000002','1'),
	('SC000006','教科院二年级3班','T000003','2020-01-13 08:05:48','P000001','南山中学','CR000002','1'),
	('SC000007','教科院一年级5班','T000004','2020-01-12 23:27:04','P000001','益州小学','CR000002','1'),
	('SC000008','教科院二年级3班','T000004','2020-01-10 21:47:44','P000001','大源中学','CR000002','1'),
	('SC000009','教科院一年级5班','T000005','2020-01-26 22:56:09','P000001','南山中学','CR000003','1'),
	('SC000010','教科院二年级3班','T000005','2020-01-10 12:24:59','P000001','益州小学','CR000003','1'),
	('SC000011','教科院一年级5班','T000006','2020-01-10 00:45:25','P000001','大源中学','CR000003','1'),
	('SC000012','教科院二年级3班','T000006','2020-01-06 08:13:05','P000001','南山中学','CR000003','1'),
	('SC000013','教科院一年级5班','T000007','2020-01-14 05:21:08','P000001','益州小学','CR000004','1'),
	('SC000014','教科院二年级3班','T000007','2020-01-18 21:31:14','P000001','大源中学','CR000004','1'),
	('SC000015','教科院一年级5班','T000008','2020-01-09 03:51:37','P000001','南山中学','CR000004','1'),
	('SC000016','教科院二年级3班','T000008','2020-01-16 15:25:03','P000001','益州小学','CR000004','1');

insert into teacher_data values
	('T000001','白山水','18012341234','益州小学','2020-01-19 12:10:25','P000001','CR000001','1'),
	('T000002','胡一刀','13900000002','大源中学','2020-01-21 08:44:45','P000001','CR000001','1'),
	('T000003','苗人凤','13900000003','南山中学','2020-01-12 10:46:58','P000001','CR000002','1'),
	('T000004','白山水','13900000004','益州小学','2020-01-23 03:02:25','P000001','CR000002','1'),
	('T000005','胡一刀','13900000005','大源中学','2020-01-16 15:19:18','P000001','CR000003','1'),
	('T000006','苗人凤','13900000006','南山中学','2020-01-18 14:11:59','P000001','CR000003','1'),
	('T000007','白山水','13900000007','益州小学','2020-01-18 02:09:14','P000001','CR000004','1'),
	('T000008','胡一刀','13900000008','大源中学','2020-01-26 09:24:59','P000001','CR000004','1');

insert into guardian_data values
	('G000001','刘备','18012341234','L000001','WU000001','2020-01-07 01:27:27','P000001','CR000001','1'),
	('G000002','刘玄德','13900000002','L000001','WU000001','2020-01-08 04:17:12','P000001','CR000001','1'),
	('G000003','张飞','13900000003','L000001','WU000002','2020-01-11 11:03:09','P000001','CR000001','1'),
	('G000004','张翼德','13900000004','L000001','WU000002','2020-01-20 05:43:20','P000001','CR000001','1'),
	('G000005','刘备','13900000005','L000002','WU000003','2020-01-18 14:32:14','P000001','CR000001','1'),
	('G000006','刘玄德','13900000006','L000002','WU000003','2020-01-15 01:50:29','P000001','CR000001','1'),
	('G000007','张飞','13900000007','L000002','WU000004','2020-01-14 06:10:41','P000001','CR000001','1'),
	('G000008','张翼德','13900000008','L000002','WU000004','2020-01-25 23:28:39','P000001','CR000001','1'),
	('G000009','刘备','13900000009','L000003','WU000005','2020-01-10 01:31:54','P000001','CR000001','1'),
	('G000010','刘玄德','13900000010','L000003','WU000005','2020-01-27 05:25:27','P000001','CR000001','1'),
	('G000011','张飞','13900000011','L000003','WU000006','2020-01-17 09:00:27','P000001','CR000001','1'),
	('G000012','张翼德','13900000012','L000003','WU000006','2020-01-06 04:31:10','P000001','CR000001','1'),
	('G000013','刘备','13900000013','L000004','WU000007','2020-01-10 00:25:44','P000001','CR000001','1'),
	('G000014','刘玄德','13900000014','L000004','WU000007','2020-01-23 03:31:04','P000001','CR000001','1'),
	('G000015','张飞','13900000015','L000004','WU000008','2020-01-19 20:22:27','P000001','CR000001','1'),
	('G000016','张翼德','13900000016','L000004','WU000008','2020-01-26 08:16:49','P000001','CR000001','1'),
	('G000017','刘备','13900000017','L000005','WU000009','2020-01-21 19:27:07','P000001','CR000002','1'),
	('G000018','刘玄德','13900000018','L000005','WU000009','2020-01-20 09:44:41','P000001','CR000002','1'),
	('G000019','张飞','13900000019','L000005','WU000010','2020-01-10 12:56:49','P000001','CR000002','1'),
	('G000020','张翼德','13900000020','L000005','WU000010','2020-01-17 00:54:07','P000001','CR000002','1'),
	('G000021','刘备','13900000021','L000006','WU000011','2020-01-23 20:03:24','P000001','CR000002','1'),
	('G000022','刘玄德','13900000022','L000006','WU000011','2020-01-13 20:27:14','P000001','CR000002','1'),
	('G000023','张飞','13900000023','L000006','WU000012','2020-01-06 18:55:55','P000001','CR000002','1'),
	('G000024','张翼德','13900000024','L000006','WU000012','2020-01-25 17:14:49','P000001','CR000002','1'),
	('G000025','刘备','13900000025','L000007','WU000013','2020-01-26 04:08:58','P000001','CR000002','1'),
	('G000026','刘玄德','13900000026','L000007','WU000013','2020-01-07 08:27:57','P000001','CR000002','1'),
	('G000027','张飞','13900000027','L000007','WU000014','2020-01-22 08:35:25','P000001','CR000002','1'),
	('G000028','张翼德','13900000028','L000007','WU000014','2020-01-22 06:14:10','P000001','CR000002','1'),
	('G000029','刘备','13900000029','L000008','WU000015','2020-01-12 12:26:19','P000001','CR000002','1'),
	('G000030','刘玄德','13900000030','L000008','WU000015','2020-01-21 07:38:09','P000001','CR000002','1'),
	('G000031','张飞','13900000031','L000008','WU000016','2020-01-17 07:40:53','P000001','CR000002','1'),
	('G000032','张翼德','13900000032','L000008','WU000016','2020-01-13 12:30:21','P000001','CR000002','1'),
	('G000033','刘备','13900000033','L000009','WU000017','2020-01-07 00:21:31','P000001','CR000003','1'),
	('G000034','刘玄德','13900000034','L000009','WU000017','2020-01-09 19:23:12','P000001','CR000003','1'),
	('G000035','张飞','13900000035','L000009','WU000018','2020-01-25 07:02:37','P000001','CR000003','1'),
	('G000036','张翼德','13900000036','L000009','WU000018','2020-01-12 00:24:19','P000001','CR000003','1'),
	('G000037','刘备','13900000037','L000010','WU000019','2020-01-13 12:56:11','P000001','CR000003','1'),
	('G000038','刘玄德','13900000038','L000010','WU000019','2020-01-23 14:53:27','P000001','CR000003','1'),
	('G000039','张飞','13900000039','L000010','WU000020','2020-01-13 20:54:11','P000001','CR000003','1'),
	('G000040','张翼德','13900000040','L000010','WU000020','2020-01-22 23:22:14','P000001','CR000003','1'),
	('G000041','刘备','13900000041','L000011','WU000021','2020-01-19 20:44:20','P000001','CR000003','1'),
	('G000042','刘玄德','13900000042','L000011','WU000021','2020-01-23 03:54:37','P000001','CR000003','1'),
	('G000043','张飞','13900000043','L000011','WU000022','2020-01-21 08:02:54','P000001','CR000003','1'),
	('G000044','张翼德','13900000044','L000011','WU000022','2020-01-12 17:10:30','P000001','CR000003','1'),
	('G000045','刘备','13900000045','L000012','WU000023','2020-01-20 07:13:18','P000001','CR000003','1'),
	('G000046','刘玄德','13900000046','L000012','WU000023','2020-01-24 20:41:33','P000001','CR000003','1'),
	('G000047','张飞','13900000047','L000012','WU000024','2020-01-25 15:26:04','P000001','CR000003','1'),
	('G000048','张翼德','13900000048','L000012','WU000024','2020-01-25 19:02:29','P000001','CR000003','1'),
	('G000049','刘备','13900000049','L000013','WU000025','2020-01-07 16:37:11','P000001','CR000004','1'),
	('G000050','刘玄德','13900000050','L000013','WU000025','2020-01-27 01:05:41','P000001','CR000004','1'),
	('G000051','张飞','13900000051','L000013','WU000026','2020-01-23 02:35:22','P000001','CR000004','1'),
	('G000052','张翼德','13900000052','L000013','WU000026','2020-01-24 02:06:08','P000001','CR000004','1'),
	('G000053','刘备','13900000053','L000014','WU000027','2020-01-09 12:57:17','P000001','CR000004','1'),
	('G000054','刘玄德','13900000054','L000014','WU000027','2020-01-26 10:56:43','P000001','CR000004','1'),
	('G000055','张飞','13900000055','L000014','WU000028','2020-01-26 00:39:03','P000001','CR000004','1'),
	('G000056','张翼德','13900000056','L000014','WU000028','2020-01-15 08:37:37','P000001','CR000004','1'),
	('G000057','刘备','13900000057','L000015','WU000029','2020-01-11 17:35:42','P000001','CR000004','1'),
	('G000058','刘玄德','13900000058','L000015','WU000029','2020-01-10 09:29:02','P000001','CR000004','1'),
	('G000059','张飞','13900000059','L000015','WU000030','2020-01-21 11:08:38','P000001','CR000004','1'),
	('G000060','张翼德','13900000060','L000015','WU000030','2020-01-07 21:47:54','P000001','CR000004','1'),
	('G000061','刘备','13900000061','L000016','WU000031','2020-01-12 09:06:05','P000001','CR000004','1'),
	('G000062','刘玄德','13900000062','L000016','WU000031','2020-01-19 02:23:59','P000001','CR000004','1'),
	('G000063','张飞','13900000063','L000016','WU000032','2020-01-09 17:29:52','P000001','CR000004','1'),
	('G000064','张翼德','13900000064','L000016','WU000032','2020-01-17 00:11:38','P000001','CR000004','1');

insert into question_data values
	('Q000001','节假日是否到过武汉','OptionSelect','没有','有','不确定','不知道','P000001','1'),
	('Q000002','家里是否有武汉的亲朋好友来访','OptionSelect','没有0002','有0002','不确定0002','不知道0002','P000001','1'),
	('Q000003','是否有发热、发烧症状','TextInput','没有0003','有0003','不确定0003','不知道0003','P000001','1'),
	('Q000004','节假日是否到过武汉','TextInput','没有0004','有0004','不确定0004','不知道0004','P000001','1');

insert into question_type_data values
	('OptionSelect','选择题','OptionSelect','P000001','1'),
	('TextInput','简答题','TextInput','P000001','1');

insert into question_source_data values
	('System','题库','System','P000001','1'),
	('UGC','用户自己录入','UGC','P000001','1');

insert into class_question_data values
	('CQ000001','节假日是否到过武汉','OptionSelect','没有','有','不确定','不知道','System','WU000001','CR000001','1'),
	('CQ000002','家里是否有武汉的亲朋好友来访','OptionSelect','没有0002','有0002','不确定0002','不知道0002','System','WU000001','CR000001','1'),
	('CQ000003','是否有发热、发烧症状','OptionSelect','没有0003','有0003','不确定0003','不知道0003','System','WU000002','CR000001','1'),
	('CQ000004','节假日是否到过武汉','OptionSelect','没有0004','有0004','不确定0004','不知道0004','System','WU000002','CR000001','1'),
	('CQ000005','家里是否有武汉的亲朋好友来访','OptionSelect','没有0005','有0005','不确定0005','不知道0005','System','WU000003','CR000001','1'),
	('CQ000006','是否有发热、发烧症状','OptionSelect','没有0006','有0006','不确定0006','不知道0006','System','WU000003','CR000001','1'),
	('CQ000007','节假日是否到过武汉','OptionSelect','没有0007','有0007','不确定0007','不知道0007','System','WU000004','CR000001','1'),
	('CQ000008','家里是否有武汉的亲朋好友来访','OptionSelect','没有0008','有0008','不确定0008','不知道0008','System','WU000004','CR000001','1'),
	('CQ000009','是否有发热、发烧症状','OptionSelect','没有0009','有0009','不确定0009','不知道0009','System','WU000005','CR000001','1'),
	('CQ000010','节假日是否到过武汉','OptionSelect','没有0010','有0010','不确定0010','不知道0010','System','WU000005','CR000001','1'),
	('CQ000011','家里是否有武汉的亲朋好友来访','OptionSelect','没有0011','有0011','不确定0011','不知道0011','System','WU000006','CR000001','1'),
	('CQ000012','是否有发热、发烧症状','OptionSelect','没有0012','有0012','不确定0012','不知道0012','System','WU000006','CR000001','1'),
	('CQ000013','节假日是否到过武汉','OptionSelect','没有0013','有0013','不确定0013','不知道0013','System','WU000007','CR000001','1'),
	('CQ000014','家里是否有武汉的亲朋好友来访','OptionSelect','没有0014','有0014','不确定0014','不知道0014','System','WU000007','CR000001','1'),
	('CQ000015','是否有发热、发烧症状','OptionSelect','没有0015','有0015','不确定0015','不知道0015','System','WU000008','CR000001','1'),
	('CQ000016','节假日是否到过武汉','OptionSelect','没有0016','有0016','不确定0016','不知道0016','System','WU000008','CR000001','1'),
	('CQ000017','家里是否有武汉的亲朋好友来访','OptionSelect','没有0017','有0017','不确定0017','不知道0017','System','WU000009','CR000002','1'),
	('CQ000018','是否有发热、发烧症状','OptionSelect','没有0018','有0018','不确定0018','不知道0018','System','WU000009','CR000002','1'),
	('CQ000019','节假日是否到过武汉','OptionSelect','没有0019','有0019','不确定0019','不知道0019','System','WU000010','CR000002','1'),
	('CQ000020','家里是否有武汉的亲朋好友来访','OptionSelect','没有0020','有0020','不确定0020','不知道0020','System','WU000010','CR000002','1'),
	('CQ000021','是否有发热、发烧症状','OptionSelect','没有0021','有0021','不确定0021','不知道0021','System','WU000011','CR000002','1'),
	('CQ000022','节假日是否到过武汉','OptionSelect','没有0022','有0022','不确定0022','不知道0022','System','WU000011','CR000002','1'),
	('CQ000023','家里是否有武汉的亲朋好友来访','OptionSelect','没有0023','有0023','不确定0023','不知道0023','System','WU000012','CR000002','1'),
	('CQ000024','是否有发热、发烧症状','OptionSelect','没有0024','有0024','不确定0024','不知道0024','System','WU000012','CR000002','1'),
	('CQ000025','节假日是否到过武汉','OptionSelect','没有0025','有0025','不确定0025','不知道0025','System','WU000013','CR000002','1'),
	('CQ000026','家里是否有武汉的亲朋好友来访','OptionSelect','没有0026','有0026','不确定0026','不知道0026','System','WU000013','CR000002','1'),
	('CQ000027','是否有发热、发烧症状','OptionSelect','没有0027','有0027','不确定0027','不知道0027','System','WU000014','CR000002','1'),
	('CQ000028','节假日是否到过武汉','OptionSelect','没有0028','有0028','不确定0028','不知道0028','System','WU000014','CR000002','1'),
	('CQ000029','家里是否有武汉的亲朋好友来访','OptionSelect','没有0029','有0029','不确定0029','不知道0029','System','WU000015','CR000002','1'),
	('CQ000030','是否有发热、发烧症状','OptionSelect','没有0030','有0030','不确定0030','不知道0030','System','WU000015','CR000002','1'),
	('CQ000031','节假日是否到过武汉','OptionSelect','没有0031','有0031','不确定0031','不知道0031','System','WU000016','CR000002','1'),
	('CQ000032','家里是否有武汉的亲朋好友来访','OptionSelect','没有0032','有0032','不确定0032','不知道0032','System','WU000016','CR000002','1'),
	('CQ000033','是否有发热、发烧症状','TextInput','没有0033','有0033','不确定0033','不知道0033','UGC','WU000017','CR000003','1'),
	('CQ000034','节假日是否到过武汉','TextInput','没有0034','有0034','不确定0034','不知道0034','UGC','WU000017','CR000003','1'),
	('CQ000035','家里是否有武汉的亲朋好友来访','TextInput','没有0035','有0035','不确定0035','不知道0035','UGC','WU000018','CR000003','1'),
	('CQ000036','是否有发热、发烧症状','TextInput','没有0036','有0036','不确定0036','不知道0036','UGC','WU000018','CR000003','1'),
	('CQ000037','节假日是否到过武汉','TextInput','没有0037','有0037','不确定0037','不知道0037','UGC','WU000019','CR000003','1'),
	('CQ000038','家里是否有武汉的亲朋好友来访','TextInput','没有0038','有0038','不确定0038','不知道0038','UGC','WU000019','CR000003','1'),
	('CQ000039','是否有发热、发烧症状','TextInput','没有0039','有0039','不确定0039','不知道0039','UGC','WU000020','CR000003','1'),
	('CQ000040','节假日是否到过武汉','TextInput','没有0040','有0040','不确定0040','不知道0040','UGC','WU000020','CR000003','1'),
	('CQ000041','家里是否有武汉的亲朋好友来访','TextInput','没有0041','有0041','不确定0041','不知道0041','UGC','WU000021','CR000003','1'),
	('CQ000042','是否有发热、发烧症状','TextInput','没有0042','有0042','不确定0042','不知道0042','UGC','WU000021','CR000003','1'),
	('CQ000043','节假日是否到过武汉','TextInput','没有0043','有0043','不确定0043','不知道0043','UGC','WU000022','CR000003','1'),
	('CQ000044','家里是否有武汉的亲朋好友来访','TextInput','没有0044','有0044','不确定0044','不知道0044','UGC','WU000022','CR000003','1'),
	('CQ000045','是否有发热、发烧症状','TextInput','没有0045','有0045','不确定0045','不知道0045','UGC','WU000023','CR000003','1'),
	('CQ000046','节假日是否到过武汉','TextInput','没有0046','有0046','不确定0046','不知道0046','UGC','WU000023','CR000003','1'),
	('CQ000047','家里是否有武汉的亲朋好友来访','TextInput','没有0047','有0047','不确定0047','不知道0047','UGC','WU000024','CR000003','1'),
	('CQ000048','是否有发热、发烧症状','TextInput','没有0048','有0048','不确定0048','不知道0048','UGC','WU000024','CR000003','1'),
	('CQ000049','节假日是否到过武汉','TextInput','没有0049','有0049','不确定0049','不知道0049','UGC','WU000025','CR000004','1'),
	('CQ000050','家里是否有武汉的亲朋好友来访','TextInput','没有0050','有0050','不确定0050','不知道0050','UGC','WU000025','CR000004','1'),
	('CQ000051','是否有发热、发烧症状','TextInput','没有0051','有0051','不确定0051','不知道0051','UGC','WU000026','CR000004','1'),
	('CQ000052','节假日是否到过武汉','TextInput','没有0052','有0052','不确定0052','不知道0052','UGC','WU000026','CR000004','1'),
	('CQ000053','家里是否有武汉的亲朋好友来访','TextInput','没有0053','有0053','不确定0053','不知道0053','UGC','WU000027','CR000004','1'),
	('CQ000054','是否有发热、发烧症状','TextInput','没有0054','有0054','不确定0054','不知道0054','UGC','WU000027','CR000004','1'),
	('CQ000055','节假日是否到过武汉','TextInput','没有0055','有0055','不确定0055','不知道0055','UGC','WU000028','CR000004','1'),
	('CQ000056','家里是否有武汉的亲朋好友来访','TextInput','没有0056','有0056','不确定0056','不知道0056','UGC','WU000028','CR000004','1'),
	('CQ000057','是否有发热、发烧症状','TextInput','没有0057','有0057','不确定0057','不知道0057','UGC','WU000029','CR000004','1'),
	('CQ000058','节假日是否到过武汉','TextInput','没有0058','有0058','不确定0058','不知道0058','UGC','WU000029','CR000004','1'),
	('CQ000059','家里是否有武汉的亲朋好友来访','TextInput','没有0059','有0059','不确定0059','不知道0059','UGC','WU000030','CR000004','1'),
	('CQ000060','是否有发热、发烧症状','TextInput','没有0060','有0060','不确定0060','不知道0060','UGC','WU000030','CR000004','1'),
	('CQ000061','节假日是否到过武汉','TextInput','没有0061','有0061','不确定0061','不知道0061','UGC','WU000031','CR000004','1'),
	('CQ000062','家里是否有武汉的亲朋好友来访','TextInput','没有0062','有0062','不确定0062','不知道0062','UGC','WU000031','CR000004','1'),
	('CQ000063','是否有发热、发烧症状','TextInput','没有0063','有0063','不确定0063','不知道0063','UGC','WU000032','CR000004','1'),
	('CQ000064','节假日是否到过武汉','TextInput','没有0064','有0064','不确定0064','不知道0064','UGC','WU000032','CR000004','1');

insert into class_daily_health_survey_data values
	('CDHS000001','2020年1月25日益州小学学生健康调查问卷','SC000001','2020-01-10 05:35:38','WU000001','CR000001','1'),
	('CDHS000002','2020年1月25日益州小学学生健康调查问卷0002','SC000001','2020-01-13 20:28:21','WU000001','CR000001','1'),
	('CDHS000003','2020年1月25日益州小学学生健康调查问卷0003','SC000001','2020-01-16 23:38:33','WU000002','CR000001','1'),
	('CDHS000004','2020年1月25日益州小学学生健康调查问卷0004','SC000001','2020-01-20 03:32:27','WU000002','CR000001','1'),
	('CDHS000005','2020年1月25日益州小学学生健康调查问卷0005','SC000002','2020-01-12 04:13:07','WU000003','CR000001','1'),
	('CDHS000006','2020年1月25日益州小学学生健康调查问卷0006','SC000002','2020-01-22 21:45:14','WU000003','CR000001','1'),
	('CDHS000007','2020年1月25日益州小学学生健康调查问卷0007','SC000002','2020-01-21 06:21:19','WU000004','CR000001','1'),
	('CDHS000008','2020年1月25日益州小学学生健康调查问卷0008','SC000002','2020-01-10 11:32:41','WU000004','CR000001','1'),
	('CDHS000009','2020年1月25日益州小学学生健康调查问卷0009','SC000003','2020-01-25 02:51:41','WU000005','CR000001','1'),
	('CDHS000010','2020年1月25日益州小学学生健康调查问卷0010','SC000003','2020-01-22 13:33:18','WU000005','CR000001','1'),
	('CDHS000011','2020年1月25日益州小学学生健康调查问卷0011','SC000003','2020-01-23 01:49:45','WU000006','CR000001','1'),
	('CDHS000012','2020年1月25日益州小学学生健康调查问卷0012','SC000003','2020-01-14 10:02:37','WU000006','CR000001','1'),
	('CDHS000013','2020年1月25日益州小学学生健康调查问卷0013','SC000004','2020-01-27 04:01:58','WU000007','CR000001','1'),
	('CDHS000014','2020年1月25日益州小学学生健康调查问卷0014','SC000004','2020-01-11 12:29:28','WU000007','CR000001','1'),
	('CDHS000015','2020年1月25日益州小学学生健康调查问卷0015','SC000004','2020-01-23 08:30:33','WU000008','CR000001','1'),
	('CDHS000016','2020年1月25日益州小学学生健康调查问卷0016','SC000004','2020-01-17 20:01:50','WU000008','CR000001','1'),
	('CDHS000017','2020年1月25日益州小学学生健康调查问卷0017','SC000005','2020-01-25 03:04:15','WU000009','CR000002','1'),
	('CDHS000018','2020年1月25日益州小学学生健康调查问卷0018','SC000005','2020-01-07 20:48:58','WU000009','CR000002','1'),
	('CDHS000019','2020年1月25日益州小学学生健康调查问卷0019','SC000005','2020-01-19 12:53:29','WU000010','CR000002','1'),
	('CDHS000020','2020年1月25日益州小学学生健康调查问卷0020','SC000005','2020-01-10 18:23:06','WU000010','CR000002','1'),
	('CDHS000021','2020年1月25日益州小学学生健康调查问卷0021','SC000006','2020-01-18 17:14:39','WU000011','CR000002','1'),
	('CDHS000022','2020年1月25日益州小学学生健康调查问卷0022','SC000006','2020-01-21 10:30:34','WU000011','CR000002','1'),
	('CDHS000023','2020年1月25日益州小学学生健康调查问卷0023','SC000006','2020-01-24 19:48:41','WU000012','CR000002','1'),
	('CDHS000024','2020年1月25日益州小学学生健康调查问卷0024','SC000006','2020-01-24 06:53:43','WU000012','CR000002','1'),
	('CDHS000025','2020年1月25日益州小学学生健康调查问卷0025','SC000007','2020-01-13 12:35:55','WU000013','CR000002','1'),
	('CDHS000026','2020年1月25日益州小学学生健康调查问卷0026','SC000007','2020-01-05 17:00:49','WU000013','CR000002','1'),
	('CDHS000027','2020年1月25日益州小学学生健康调查问卷0027','SC000007','2020-01-16 03:26:30','WU000014','CR000002','1'),
	('CDHS000028','2020年1月25日益州小学学生健康调查问卷0028','SC000007','2020-01-15 07:29:55','WU000014','CR000002','1'),
	('CDHS000029','2020年1月25日益州小学学生健康调查问卷0029','SC000008','2020-01-16 00:16:54','WU000015','CR000002','1'),
	('CDHS000030','2020年1月25日益州小学学生健康调查问卷0030','SC000008','2020-01-08 14:35:48','WU000015','CR000002','1'),
	('CDHS000031','2020年1月25日益州小学学生健康调查问卷0031','SC000008','2020-01-17 02:30:09','WU000016','CR000002','1'),
	('CDHS000032','2020年1月25日益州小学学生健康调查问卷0032','SC000008','2020-01-18 03:51:47','WU000016','CR000002','1'),
	('CDHS000033','2020年1月25日益州小学学生健康调查问卷0033','SC000009','2020-01-15 02:47:32','WU000017','CR000003','1'),
	('CDHS000034','2020年1月25日益州小学学生健康调查问卷0034','SC000009','2020-01-15 15:00:22','WU000017','CR000003','1'),
	('CDHS000035','2020年1月25日益州小学学生健康调查问卷0035','SC000009','2020-01-18 23:07:43','WU000018','CR000003','1'),
	('CDHS000036','2020年1月25日益州小学学生健康调查问卷0036','SC000009','2020-01-23 10:49:43','WU000018','CR000003','1'),
	('CDHS000037','2020年1月25日益州小学学生健康调查问卷0037','SC000010','2020-01-21 08:20:17','WU000019','CR000003','1'),
	('CDHS000038','2020年1月25日益州小学学生健康调查问卷0038','SC000010','2020-01-27 06:47:58','WU000019','CR000003','1'),
	('CDHS000039','2020年1月25日益州小学学生健康调查问卷0039','SC000010','2020-01-18 10:58:46','WU000020','CR000003','1'),
	('CDHS000040','2020年1月25日益州小学学生健康调查问卷0040','SC000010','2020-01-25 10:39:52','WU000020','CR000003','1'),
	('CDHS000041','2020年1月25日益州小学学生健康调查问卷0041','SC000011','2020-01-11 05:27:13','WU000021','CR000003','1'),
	('CDHS000042','2020年1月25日益州小学学生健康调查问卷0042','SC000011','2020-01-13 06:28:52','WU000021','CR000003','1'),
	('CDHS000043','2020年1月25日益州小学学生健康调查问卷0043','SC000011','2020-01-26 02:33:15','WU000022','CR000003','1'),
	('CDHS000044','2020年1月25日益州小学学生健康调查问卷0044','SC000011','2020-01-06 18:09:18','WU000022','CR000003','1'),
	('CDHS000045','2020年1月25日益州小学学生健康调查问卷0045','SC000012','2020-01-10 05:48:39','WU000023','CR000003','1'),
	('CDHS000046','2020年1月25日益州小学学生健康调查问卷0046','SC000012','2020-01-19 10:44:51','WU000023','CR000003','1'),
	('CDHS000047','2020年1月25日益州小学学生健康调查问卷0047','SC000012','2020-01-26 21:52:59','WU000024','CR000003','1'),
	('CDHS000048','2020年1月25日益州小学学生健康调查问卷0048','SC000012','2020-01-19 23:36:30','WU000024','CR000003','1'),
	('CDHS000049','2020年1月25日益州小学学生健康调查问卷0049','SC000013','2020-01-08 04:46:24','WU000025','CR000004','1'),
	('CDHS000050','2020年1月25日益州小学学生健康调查问卷0050','SC000013','2020-01-24 13:44:00','WU000025','CR000004','1'),
	('CDHS000051','2020年1月25日益州小学学生健康调查问卷0051','SC000013','2020-01-09 20:46:36','WU000026','CR000004','1'),
	('CDHS000052','2020年1月25日益州小学学生健康调查问卷0052','SC000013','2020-01-08 05:59:17','WU000026','CR000004','1'),
	('CDHS000053','2020年1月25日益州小学学生健康调查问卷0053','SC000014','2020-01-17 12:41:01','WU000027','CR000004','1'),
	('CDHS000054','2020年1月25日益州小学学生健康调查问卷0054','SC000014','2020-01-19 17:46:31','WU000027','CR000004','1'),
	('CDHS000055','2020年1月25日益州小学学生健康调查问卷0055','SC000014','2020-01-16 18:17:08','WU000028','CR000004','1'),
	('CDHS000056','2020年1月25日益州小学学生健康调查问卷0056','SC000014','2020-01-11 21:06:08','WU000028','CR000004','1'),
	('CDHS000057','2020年1月25日益州小学学生健康调查问卷0057','SC000015','2020-01-14 07:56:07','WU000029','CR000004','1'),
	('CDHS000058','2020年1月25日益州小学学生健康调查问卷0058','SC000015','2020-01-17 23:14:56','WU000029','CR000004','1'),
	('CDHS000059','2020年1月25日益州小学学生健康调查问卷0059','SC000015','2020-01-22 21:52:37','WU000030','CR000004','1'),
	('CDHS000060','2020年1月25日益州小学学生健康调查问卷0060','SC000015','2020-01-16 05:44:24','WU000030','CR000004','1'),
	('CDHS000061','2020年1月25日益州小学学生健康调查问卷0061','SC000016','2020-01-21 09:57:58','WU000031','CR000004','1'),
	('CDHS000062','2020年1月25日益州小学学生健康调查问卷0062','SC000016','2020-01-09 10:54:27','WU000031','CR000004','1'),
	('CDHS000063','2020年1月25日益州小学学生健康调查问卷0063','SC000016','2020-01-09 13:09:19','WU000032','CR000004','1'),
	('CDHS000064','2020年1月25日益州小学学生健康调查问卷0064','SC000016','2020-01-08 22:37:30','WU000032','CR000004','1');

insert into daily_survey_question_data values
	('DSQ000001','节假日是否到过武汉','OptionSelect','没有','有','不确定','不知道','CDHS000001','CQ000001','1'),
	('DSQ000002','家里是否有武汉的亲朋好友来访','OptionSelect','没有0002','有0002','不确定0002','不知道0002','CDHS000001','CQ000001','1'),
	('DSQ000003','是否有发热、发烧症状','OptionSelect','没有0003','有0003','不确定0003','不知道0003','CDHS000002','CQ000002','1'),
	('DSQ000004','节假日是否到过武汉','OptionSelect','没有0004','有0004','不确定0004','不知道0004','CDHS000002','CQ000002','1'),
	('DSQ000005','家里是否有武汉的亲朋好友来访','OptionSelect','没有0005','有0005','不确定0005','不知道0005','CDHS000003','CQ000003','1'),
	('DSQ000006','是否有发热、发烧症状','OptionSelect','没有0006','有0006','不确定0006','不知道0006','CDHS000003','CQ000003','1'),
	('DSQ000007','节假日是否到过武汉','OptionSelect','没有0007','有0007','不确定0007','不知道0007','CDHS000004','CQ000004','1'),
	('DSQ000008','家里是否有武汉的亲朋好友来访','OptionSelect','没有0008','有0008','不确定0008','不知道0008','CDHS000004','CQ000004','1'),
	('DSQ000009','是否有发热、发烧症状','OptionSelect','没有0009','有0009','不确定0009','不知道0009','CDHS000005','CQ000005','1'),
	('DSQ000010','节假日是否到过武汉','OptionSelect','没有0010','有0010','不确定0010','不知道0010','CDHS000005','CQ000005','1'),
	('DSQ000011','家里是否有武汉的亲朋好友来访','OptionSelect','没有0011','有0011','不确定0011','不知道0011','CDHS000006','CQ000006','1'),
	('DSQ000012','是否有发热、发烧症状','OptionSelect','没有0012','有0012','不确定0012','不知道0012','CDHS000006','CQ000006','1'),
	('DSQ000013','节假日是否到过武汉','OptionSelect','没有0013','有0013','不确定0013','不知道0013','CDHS000007','CQ000007','1'),
	('DSQ000014','家里是否有武汉的亲朋好友来访','OptionSelect','没有0014','有0014','不确定0014','不知道0014','CDHS000007','CQ000007','1'),
	('DSQ000015','是否有发热、发烧症状','OptionSelect','没有0015','有0015','不确定0015','不知道0015','CDHS000008','CQ000008','1'),
	('DSQ000016','节假日是否到过武汉','OptionSelect','没有0016','有0016','不确定0016','不知道0016','CDHS000008','CQ000008','1'),
	('DSQ000017','家里是否有武汉的亲朋好友来访','OptionSelect','没有0017','有0017','不确定0017','不知道0017','CDHS000009','CQ000009','1'),
	('DSQ000018','是否有发热、发烧症状','OptionSelect','没有0018','有0018','不确定0018','不知道0018','CDHS000009','CQ000009','1'),
	('DSQ000019','节假日是否到过武汉','OptionSelect','没有0019','有0019','不确定0019','不知道0019','CDHS000010','CQ000010','1'),
	('DSQ000020','家里是否有武汉的亲朋好友来访','OptionSelect','没有0020','有0020','不确定0020','不知道0020','CDHS000010','CQ000010','1'),
	('DSQ000021','是否有发热、发烧症状','OptionSelect','没有0021','有0021','不确定0021','不知道0021','CDHS000011','CQ000011','1'),
	('DSQ000022','节假日是否到过武汉','OptionSelect','没有0022','有0022','不确定0022','不知道0022','CDHS000011','CQ000011','1'),
	('DSQ000023','家里是否有武汉的亲朋好友来访','OptionSelect','没有0023','有0023','不确定0023','不知道0023','CDHS000012','CQ000012','1'),
	('DSQ000024','是否有发热、发烧症状','OptionSelect','没有0024','有0024','不确定0024','不知道0024','CDHS000012','CQ000012','1'),
	('DSQ000025','节假日是否到过武汉','OptionSelect','没有0025','有0025','不确定0025','不知道0025','CDHS000013','CQ000013','1'),
	('DSQ000026','家里是否有武汉的亲朋好友来访','OptionSelect','没有0026','有0026','不确定0026','不知道0026','CDHS000013','CQ000013','1'),
	('DSQ000027','是否有发热、发烧症状','OptionSelect','没有0027','有0027','不确定0027','不知道0027','CDHS000014','CQ000014','1'),
	('DSQ000028','节假日是否到过武汉','OptionSelect','没有0028','有0028','不确定0028','不知道0028','CDHS000014','CQ000014','1'),
	('DSQ000029','家里是否有武汉的亲朋好友来访','OptionSelect','没有0029','有0029','不确定0029','不知道0029','CDHS000015','CQ000015','1'),
	('DSQ000030','是否有发热、发烧症状','OptionSelect','没有0030','有0030','不确定0030','不知道0030','CDHS000015','CQ000015','1'),
	('DSQ000031','节假日是否到过武汉','OptionSelect','没有0031','有0031','不确定0031','不知道0031','CDHS000016','CQ000016','1'),
	('DSQ000032','家里是否有武汉的亲朋好友来访','OptionSelect','没有0032','有0032','不确定0032','不知道0032','CDHS000016','CQ000016','1'),
	('DSQ000033','是否有发热、发烧症状','OptionSelect','没有0033','有0033','不确定0033','不知道0033','CDHS000017','CQ000017','1'),
	('DSQ000034','节假日是否到过武汉','OptionSelect','没有0034','有0034','不确定0034','不知道0034','CDHS000017','CQ000017','1'),
	('DSQ000035','家里是否有武汉的亲朋好友来访','OptionSelect','没有0035','有0035','不确定0035','不知道0035','CDHS000018','CQ000018','1'),
	('DSQ000036','是否有发热、发烧症状','OptionSelect','没有0036','有0036','不确定0036','不知道0036','CDHS000018','CQ000018','1'),
	('DSQ000037','节假日是否到过武汉','OptionSelect','没有0037','有0037','不确定0037','不知道0037','CDHS000019','CQ000019','1'),
	('DSQ000038','家里是否有武汉的亲朋好友来访','OptionSelect','没有0038','有0038','不确定0038','不知道0038','CDHS000019','CQ000019','1'),
	('DSQ000039','是否有发热、发烧症状','OptionSelect','没有0039','有0039','不确定0039','不知道0039','CDHS000020','CQ000020','1'),
	('DSQ000040','节假日是否到过武汉','OptionSelect','没有0040','有0040','不确定0040','不知道0040','CDHS000020','CQ000020','1'),
	('DSQ000041','家里是否有武汉的亲朋好友来访','OptionSelect','没有0041','有0041','不确定0041','不知道0041','CDHS000021','CQ000021','1'),
	('DSQ000042','是否有发热、发烧症状','OptionSelect','没有0042','有0042','不确定0042','不知道0042','CDHS000021','CQ000021','1'),
	('DSQ000043','节假日是否到过武汉','OptionSelect','没有0043','有0043','不确定0043','不知道0043','CDHS000022','CQ000022','1'),
	('DSQ000044','家里是否有武汉的亲朋好友来访','OptionSelect','没有0044','有0044','不确定0044','不知道0044','CDHS000022','CQ000022','1'),
	('DSQ000045','是否有发热、发烧症状','OptionSelect','没有0045','有0045','不确定0045','不知道0045','CDHS000023','CQ000023','1'),
	('DSQ000046','节假日是否到过武汉','OptionSelect','没有0046','有0046','不确定0046','不知道0046','CDHS000023','CQ000023','1'),
	('DSQ000047','家里是否有武汉的亲朋好友来访','OptionSelect','没有0047','有0047','不确定0047','不知道0047','CDHS000024','CQ000024','1'),
	('DSQ000048','是否有发热、发烧症状','OptionSelect','没有0048','有0048','不确定0048','不知道0048','CDHS000024','CQ000024','1'),
	('DSQ000049','节假日是否到过武汉','OptionSelect','没有0049','有0049','不确定0049','不知道0049','CDHS000025','CQ000025','1'),
	('DSQ000050','家里是否有武汉的亲朋好友来访','OptionSelect','没有0050','有0050','不确定0050','不知道0050','CDHS000025','CQ000025','1'),
	('DSQ000051','是否有发热、发烧症状','OptionSelect','没有0051','有0051','不确定0051','不知道0051','CDHS000026','CQ000026','1'),
	('DSQ000052','节假日是否到过武汉','OptionSelect','没有0052','有0052','不确定0052','不知道0052','CDHS000026','CQ000026','1'),
	('DSQ000053','家里是否有武汉的亲朋好友来访','OptionSelect','没有0053','有0053','不确定0053','不知道0053','CDHS000027','CQ000027','1'),
	('DSQ000054','是否有发热、发烧症状','OptionSelect','没有0054','有0054','不确定0054','不知道0054','CDHS000027','CQ000027','1'),
	('DSQ000055','节假日是否到过武汉','OptionSelect','没有0055','有0055','不确定0055','不知道0055','CDHS000028','CQ000028','1'),
	('DSQ000056','家里是否有武汉的亲朋好友来访','OptionSelect','没有0056','有0056','不确定0056','不知道0056','CDHS000028','CQ000028','1'),
	('DSQ000057','是否有发热、发烧症状','OptionSelect','没有0057','有0057','不确定0057','不知道0057','CDHS000029','CQ000029','1'),
	('DSQ000058','节假日是否到过武汉','OptionSelect','没有0058','有0058','不确定0058','不知道0058','CDHS000029','CQ000029','1'),
	('DSQ000059','家里是否有武汉的亲朋好友来访','OptionSelect','没有0059','有0059','不确定0059','不知道0059','CDHS000030','CQ000030','1'),
	('DSQ000060','是否有发热、发烧症状','OptionSelect','没有0060','有0060','不确定0060','不知道0060','CDHS000030','CQ000030','1'),
	('DSQ000061','节假日是否到过武汉','OptionSelect','没有0061','有0061','不确定0061','不知道0061','CDHS000031','CQ000031','1'),
	('DSQ000062','家里是否有武汉的亲朋好友来访','OptionSelect','没有0062','有0062','不确定0062','不知道0062','CDHS000031','CQ000031','1'),
	('DSQ000063','是否有发热、发烧症状','OptionSelect','没有0063','有0063','不确定0063','不知道0063','CDHS000032','CQ000032','1'),
	('DSQ000064','节假日是否到过武汉','OptionSelect','没有0064','有0064','不确定0064','不知道0064','CDHS000032','CQ000032','1'),
	('DSQ000065','家里是否有武汉的亲朋好友来访','TextInput','没有0065','有0065','不确定0065','不知道0065','CDHS000033','CQ000033','1'),
	('DSQ000066','是否有发热、发烧症状','TextInput','没有0066','有0066','不确定0066','不知道0066','CDHS000033','CQ000033','1'),
	('DSQ000067','节假日是否到过武汉','TextInput','没有0067','有0067','不确定0067','不知道0067','CDHS000034','CQ000034','1'),
	('DSQ000068','家里是否有武汉的亲朋好友来访','TextInput','没有0068','有0068','不确定0068','不知道0068','CDHS000034','CQ000034','1'),
	('DSQ000069','是否有发热、发烧症状','TextInput','没有0069','有0069','不确定0069','不知道0069','CDHS000035','CQ000035','1'),
	('DSQ000070','节假日是否到过武汉','TextInput','没有0070','有0070','不确定0070','不知道0070','CDHS000035','CQ000035','1'),
	('DSQ000071','家里是否有武汉的亲朋好友来访','TextInput','没有0071','有0071','不确定0071','不知道0071','CDHS000036','CQ000036','1'),
	('DSQ000072','是否有发热、发烧症状','TextInput','没有0072','有0072','不确定0072','不知道0072','CDHS000036','CQ000036','1'),
	('DSQ000073','节假日是否到过武汉','TextInput','没有0073','有0073','不确定0073','不知道0073','CDHS000037','CQ000037','1'),
	('DSQ000074','家里是否有武汉的亲朋好友来访','TextInput','没有0074','有0074','不确定0074','不知道0074','CDHS000037','CQ000037','1'),
	('DSQ000075','是否有发热、发烧症状','TextInput','没有0075','有0075','不确定0075','不知道0075','CDHS000038','CQ000038','1'),
	('DSQ000076','节假日是否到过武汉','TextInput','没有0076','有0076','不确定0076','不知道0076','CDHS000038','CQ000038','1'),
	('DSQ000077','家里是否有武汉的亲朋好友来访','TextInput','没有0077','有0077','不确定0077','不知道0077','CDHS000039','CQ000039','1'),
	('DSQ000078','是否有发热、发烧症状','TextInput','没有0078','有0078','不确定0078','不知道0078','CDHS000039','CQ000039','1'),
	('DSQ000079','节假日是否到过武汉','TextInput','没有0079','有0079','不确定0079','不知道0079','CDHS000040','CQ000040','1'),
	('DSQ000080','家里是否有武汉的亲朋好友来访','TextInput','没有0080','有0080','不确定0080','不知道0080','CDHS000040','CQ000040','1'),
	('DSQ000081','是否有发热、发烧症状','TextInput','没有0081','有0081','不确定0081','不知道0081','CDHS000041','CQ000041','1'),
	('DSQ000082','节假日是否到过武汉','TextInput','没有0082','有0082','不确定0082','不知道0082','CDHS000041','CQ000041','1'),
	('DSQ000083','家里是否有武汉的亲朋好友来访','TextInput','没有0083','有0083','不确定0083','不知道0083','CDHS000042','CQ000042','1'),
	('DSQ000084','是否有发热、发烧症状','TextInput','没有0084','有0084','不确定0084','不知道0084','CDHS000042','CQ000042','1'),
	('DSQ000085','节假日是否到过武汉','TextInput','没有0085','有0085','不确定0085','不知道0085','CDHS000043','CQ000043','1'),
	('DSQ000086','家里是否有武汉的亲朋好友来访','TextInput','没有0086','有0086','不确定0086','不知道0086','CDHS000043','CQ000043','1'),
	('DSQ000087','是否有发热、发烧症状','TextInput','没有0087','有0087','不确定0087','不知道0087','CDHS000044','CQ000044','1'),
	('DSQ000088','节假日是否到过武汉','TextInput','没有0088','有0088','不确定0088','不知道0088','CDHS000044','CQ000044','1'),
	('DSQ000089','家里是否有武汉的亲朋好友来访','TextInput','没有0089','有0089','不确定0089','不知道0089','CDHS000045','CQ000045','1'),
	('DSQ000090','是否有发热、发烧症状','TextInput','没有0090','有0090','不确定0090','不知道0090','CDHS000045','CQ000045','1'),
	('DSQ000091','节假日是否到过武汉','TextInput','没有0091','有0091','不确定0091','不知道0091','CDHS000046','CQ000046','1'),
	('DSQ000092','家里是否有武汉的亲朋好友来访','TextInput','没有0092','有0092','不确定0092','不知道0092','CDHS000046','CQ000046','1'),
	('DSQ000093','是否有发热、发烧症状','TextInput','没有0093','有0093','不确定0093','不知道0093','CDHS000047','CQ000047','1'),
	('DSQ000094','节假日是否到过武汉','TextInput','没有0094','有0094','不确定0094','不知道0094','CDHS000047','CQ000047','1'),
	('DSQ000095','家里是否有武汉的亲朋好友来访','TextInput','没有0095','有0095','不确定0095','不知道0095','CDHS000048','CQ000048','1'),
	('DSQ000096','是否有发热、发烧症状','TextInput','没有0096','有0096','不确定0096','不知道0096','CDHS000048','CQ000048','1'),
	('DSQ000097','节假日是否到过武汉','TextInput','没有0097','有0097','不确定0097','不知道0097','CDHS000049','CQ000049','1'),
	('DSQ000098','家里是否有武汉的亲朋好友来访','TextInput','没有0098','有0098','不确定0098','不知道0098','CDHS000049','CQ000049','1'),
	('DSQ000099','是否有发热、发烧症状','TextInput','没有0099','有0099','不确定0099','不知道0099','CDHS000050','CQ000050','1'),
	('DSQ000100','节假日是否到过武汉','TextInput','没有0100','有0100','不确定0100','不知道0100','CDHS000050','CQ000050','1'),
	('DSQ000101','家里是否有武汉的亲朋好友来访','TextInput','没有0101','有0101','不确定0101','不知道0101','CDHS000051','CQ000051','1'),
	('DSQ000102','是否有发热、发烧症状','TextInput','没有0102','有0102','不确定0102','不知道0102','CDHS000051','CQ000051','1'),
	('DSQ000103','节假日是否到过武汉','TextInput','没有0103','有0103','不确定0103','不知道0103','CDHS000052','CQ000052','1'),
	('DSQ000104','家里是否有武汉的亲朋好友来访','TextInput','没有0104','有0104','不确定0104','不知道0104','CDHS000052','CQ000052','1'),
	('DSQ000105','是否有发热、发烧症状','TextInput','没有0105','有0105','不确定0105','不知道0105','CDHS000053','CQ000053','1'),
	('DSQ000106','节假日是否到过武汉','TextInput','没有0106','有0106','不确定0106','不知道0106','CDHS000053','CQ000053','1'),
	('DSQ000107','家里是否有武汉的亲朋好友来访','TextInput','没有0107','有0107','不确定0107','不知道0107','CDHS000054','CQ000054','1'),
	('DSQ000108','是否有发热、发烧症状','TextInput','没有0108','有0108','不确定0108','不知道0108','CDHS000054','CQ000054','1'),
	('DSQ000109','节假日是否到过武汉','TextInput','没有0109','有0109','不确定0109','不知道0109','CDHS000055','CQ000055','1'),
	('DSQ000110','家里是否有武汉的亲朋好友来访','TextInput','没有0110','有0110','不确定0110','不知道0110','CDHS000055','CQ000055','1'),
	('DSQ000111','是否有发热、发烧症状','TextInput','没有0111','有0111','不确定0111','不知道0111','CDHS000056','CQ000056','1'),
	('DSQ000112','节假日是否到过武汉','TextInput','没有0112','有0112','不确定0112','不知道0112','CDHS000056','CQ000056','1'),
	('DSQ000113','家里是否有武汉的亲朋好友来访','TextInput','没有0113','有0113','不确定0113','不知道0113','CDHS000057','CQ000057','1'),
	('DSQ000114','是否有发热、发烧症状','TextInput','没有0114','有0114','不确定0114','不知道0114','CDHS000057','CQ000057','1'),
	('DSQ000115','节假日是否到过武汉','TextInput','没有0115','有0115','不确定0115','不知道0115','CDHS000058','CQ000058','1'),
	('DSQ000116','家里是否有武汉的亲朋好友来访','TextInput','没有0116','有0116','不确定0116','不知道0116','CDHS000058','CQ000058','1'),
	('DSQ000117','是否有发热、发烧症状','TextInput','没有0117','有0117','不确定0117','不知道0117','CDHS000059','CQ000059','1'),
	('DSQ000118','节假日是否到过武汉','TextInput','没有0118','有0118','不确定0118','不知道0118','CDHS000059','CQ000059','1'),
	('DSQ000119','家里是否有武汉的亲朋好友来访','TextInput','没有0119','有0119','不确定0119','不知道0119','CDHS000060','CQ000060','1'),
	('DSQ000120','是否有发热、发烧症状','TextInput','没有0120','有0120','不确定0120','不知道0120','CDHS000060','CQ000060','1'),
	('DSQ000121','节假日是否到过武汉','TextInput','没有0121','有0121','不确定0121','不知道0121','CDHS000061','CQ000061','1'),
	('DSQ000122','家里是否有武汉的亲朋好友来访','TextInput','没有0122','有0122','不确定0122','不知道0122','CDHS000061','CQ000061','1'),
	('DSQ000123','是否有发热、发烧症状','TextInput','没有0123','有0123','不确定0123','不知道0123','CDHS000062','CQ000062','1'),
	('DSQ000124','节假日是否到过武汉','TextInput','没有0124','有0124','不确定0124','不知道0124','CDHS000062','CQ000062','1'),
	('DSQ000125','家里是否有武汉的亲朋好友来访','TextInput','没有0125','有0125','不确定0125','不知道0125','CDHS000063','CQ000063','1'),
	('DSQ000126','是否有发热、发烧症状','TextInput','没有0126','有0126','不确定0126','不知道0126','CDHS000063','CQ000063','1'),
	('DSQ000127','节假日是否到过武汉','TextInput','没有0127','有0127','不确定0127','不知道0127','CDHS000064','CQ000064','1'),
	('DSQ000128','家里是否有武汉的亲朋好友来访','TextInput','没有0128','有0128','不确定0128','不知道0128','CDHS000064','CQ000064','1');

insert into student_data values
	('S000001','刘婵','male','G000001','SC000001','A01','CR000001','1'),
	('S000002','刘阿斗','female','G000001','SC000001','A01','CR000001','1'),
	('S000003','李天一','male','G000002','SC000001','A01','CR000001','1'),
	('S000004','刘婵','female','G000002','SC000001','A01','CR000001','1'),
	('S000005','刘阿斗','male','G000003','SC000001','A01','CR000001','1'),
	('S000006','李天一','female','G000003','SC000001','A01','CR000001','1'),
	('S000007','刘婵','male','G000004','SC000001','A01','CR000001','1'),
	('S000008','刘阿斗','female','G000004','SC000001','A01','CR000001','1'),
	('S000009','李天一','male','G000005','SC000002','A01','CR000001','1'),
	('S000010','刘婵','female','G000005','SC000002','A01','CR000001','1'),
	('S000011','刘阿斗','male','G000006','SC000002','A01','CR000001','1'),
	('S000012','李天一','female','G000006','SC000002','A01','CR000001','1'),
	('S000013','刘婵','male','G000007','SC000002','A01','CR000001','1'),
	('S000014','刘阿斗','female','G000007','SC000002','A01','CR000001','1'),
	('S000015','李天一','male','G000008','SC000002','A01','CR000001','1'),
	('S000016','刘婵','female','G000008','SC000002','A01','CR000001','1'),
	('S000017','刘阿斗','male','G000009','SC000003','A01','CR000001','1'),
	('S000018','李天一','female','G000009','SC000003','A01','CR000001','1'),
	('S000019','刘婵','male','G000010','SC000003','A01','CR000001','1'),
	('S000020','刘阿斗','female','G000010','SC000003','A01','CR000001','1'),
	('S000021','李天一','male','G000011','SC000003','A01','CR000001','1'),
	('S000022','刘婵','female','G000011','SC000003','A01','CR000001','1'),
	('S000023','刘阿斗','male','G000012','SC000003','A01','CR000001','1'),
	('S000024','李天一','female','G000012','SC000003','A01','CR000001','1'),
	('S000025','刘婵','male','G000013','SC000004','A01','CR000001','1'),
	('S000026','刘阿斗','female','G000013','SC000004','A01','CR000001','1'),
	('S000027','李天一','male','G000014','SC000004','A01','CR000001','1'),
	('S000028','刘婵','female','G000014','SC000004','A01','CR000001','1'),
	('S000029','刘阿斗','male','G000015','SC000004','A01','CR000001','1'),
	('S000030','李天一','female','G000015','SC000004','A01','CR000001','1'),
	('S000031','刘婵','male','G000016','SC000004','A01','CR000001','1'),
	('S000032','刘阿斗','female','G000016','SC000004','A01','CR000001','1'),
	('S000033','李天一','male','G000017','SC000005','A01','CR000002','1'),
	('S000034','刘婵','female','G000017','SC000005','A01','CR000002','1'),
	('S000035','刘阿斗','male','G000018','SC000005','A01','CR000002','1'),
	('S000036','李天一','female','G000018','SC000005','A01','CR000002','1'),
	('S000037','刘婵','male','G000019','SC000005','A01','CR000002','1'),
	('S000038','刘阿斗','female','G000019','SC000005','A01','CR000002','1'),
	('S000039','李天一','male','G000020','SC000005','A01','CR000002','1'),
	('S000040','刘婵','female','G000020','SC000005','A01','CR000002','1'),
	('S000041','刘阿斗','male','G000021','SC000006','A01','CR000002','1'),
	('S000042','李天一','female','G000021','SC000006','A01','CR000002','1'),
	('S000043','刘婵','male','G000022','SC000006','A01','CR000002','1'),
	('S000044','刘阿斗','female','G000022','SC000006','A01','CR000002','1'),
	('S000045','李天一','male','G000023','SC000006','A01','CR000002','1'),
	('S000046','刘婵','female','G000023','SC000006','A01','CR000002','1'),
	('S000047','刘阿斗','male','G000024','SC000006','A01','CR000002','1'),
	('S000048','李天一','female','G000024','SC000006','A01','CR000002','1'),
	('S000049','刘婵','male','G000025','SC000007','A01','CR000002','1'),
	('S000050','刘阿斗','female','G000025','SC000007','A01','CR000002','1'),
	('S000051','李天一','male','G000026','SC000007','A01','CR000002','1'),
	('S000052','刘婵','female','G000026','SC000007','A01','CR000002','1'),
	('S000053','刘阿斗','male','G000027','SC000007','A01','CR000002','1'),
	('S000054','李天一','female','G000027','SC000007','A01','CR000002','1'),
	('S000055','刘婵','male','G000028','SC000007','A01','CR000002','1'),
	('S000056','刘阿斗','female','G000028','SC000007','A01','CR000002','1'),
	('S000057','李天一','male','G000029','SC000008','A01','CR000002','1'),
	('S000058','刘婵','female','G000029','SC000008','A01','CR000002','1'),
	('S000059','刘阿斗','male','G000030','SC000008','A01','CR000002','1'),
	('S000060','李天一','female','G000030','SC000008','A01','CR000002','1'),
	('S000061','刘婵','male','G000031','SC000008','A01','CR000002','1'),
	('S000062','刘阿斗','female','G000031','SC000008','A01','CR000002','1'),
	('S000063','李天一','male','G000032','SC000008','A01','CR000002','1'),
	('S000064','刘婵','female','G000032','SC000008','A01','CR000002','1'),
	('S000065','刘阿斗','male','G000033','SC000009','A01','CR000003','1'),
	('S000066','李天一','female','G000033','SC000009','A01','CR000003','1'),
	('S000067','刘婵','male','G000034','SC000009','A01','CR000003','1'),
	('S000068','刘阿斗','female','G000034','SC000009','A01','CR000003','1'),
	('S000069','李天一','male','G000035','SC000009','A01','CR000003','1'),
	('S000070','刘婵','female','G000035','SC000009','A01','CR000003','1'),
	('S000071','刘阿斗','male','G000036','SC000009','A01','CR000003','1'),
	('S000072','李天一','female','G000036','SC000009','A01','CR000003','1'),
	('S000073','刘婵','male','G000037','SC000010','A01','CR000003','1'),
	('S000074','刘阿斗','female','G000037','SC000010','A01','CR000003','1'),
	('S000075','李天一','male','G000038','SC000010','A01','CR000003','1'),
	('S000076','刘婵','female','G000038','SC000010','A01','CR000003','1'),
	('S000077','刘阿斗','male','G000039','SC000010','A01','CR000003','1'),
	('S000078','李天一','female','G000039','SC000010','A01','CR000003','1'),
	('S000079','刘婵','male','G000040','SC000010','A01','CR000003','1'),
	('S000080','刘阿斗','female','G000040','SC000010','A01','CR000003','1'),
	('S000081','李天一','male','G000041','SC000011','A01','CR000003','1'),
	('S000082','刘婵','female','G000041','SC000011','A01','CR000003','1'),
	('S000083','刘阿斗','male','G000042','SC000011','A01','CR000003','1'),
	('S000084','李天一','female','G000042','SC000011','A01','CR000003','1'),
	('S000085','刘婵','male','G000043','SC000011','A01','CR000003','1'),
	('S000086','刘阿斗','female','G000043','SC000011','A01','CR000003','1'),
	('S000087','李天一','male','G000044','SC000011','A01','CR000003','1'),
	('S000088','刘婵','female','G000044','SC000011','A01','CR000003','1'),
	('S000089','刘阿斗','male','G000045','SC000012','A01','CR000003','1'),
	('S000090','李天一','female','G000045','SC000012','A01','CR000003','1'),
	('S000091','刘婵','male','G000046','SC000012','A01','CR000003','1'),
	('S000092','刘阿斗','female','G000046','SC000012','A01','CR000003','1'),
	('S000093','李天一','male','G000047','SC000012','A01','CR000003','1'),
	('S000094','刘婵','female','G000047','SC000012','A01','CR000003','1'),
	('S000095','刘阿斗','male','G000048','SC000012','A01','CR000003','1'),
	('S000096','李天一','female','G000048','SC000012','A01','CR000003','1'),
	('S000097','刘婵','male','G000049','SC000013','A01','CR000004','1'),
	('S000098','刘阿斗','female','G000049','SC000013','A01','CR000004','1'),
	('S000099','李天一','male','G000050','SC000013','A01','CR000004','1'),
	('S000100','刘婵','female','G000050','SC000013','A01','CR000004','1'),
	('S000101','刘阿斗','male','G000051','SC000013','A01','CR000004','1'),
	('S000102','李天一','female','G000051','SC000013','A01','CR000004','1'),
	('S000103','刘婵','male','G000052','SC000013','A01','CR000004','1'),
	('S000104','刘阿斗','female','G000052','SC000013','A01','CR000004','1'),
	('S000105','李天一','male','G000053','SC000014','A01','CR000004','1'),
	('S000106','刘婵','female','G000053','SC000014','A01','CR000004','1'),
	('S000107','刘阿斗','male','G000054','SC000014','A01','CR000004','1'),
	('S000108','李天一','female','G000054','SC000014','A01','CR000004','1'),
	('S000109','刘婵','male','G000055','SC000014','A01','CR000004','1'),
	('S000110','刘阿斗','female','G000055','SC000014','A01','CR000004','1'),
	('S000111','李天一','male','G000056','SC000014','A01','CR000004','1'),
	('S000112','刘婵','female','G000056','SC000014','A01','CR000004','1'),
	('S000113','刘阿斗','male','G000057','SC000015','A01','CR000004','1'),
	('S000114','李天一','female','G000057','SC000015','A01','CR000004','1'),
	('S000115','刘婵','male','G000058','SC000015','A01','CR000004','1'),
	('S000116','刘阿斗','female','G000058','SC000015','A01','CR000004','1'),
	('S000117','李天一','male','G000059','SC000015','A01','CR000004','1'),
	('S000118','刘婵','female','G000059','SC000015','A01','CR000004','1'),
	('S000119','刘阿斗','male','G000060','SC000015','A01','CR000004','1'),
	('S000120','李天一','female','G000060','SC000015','A01','CR000004','1'),
	('S000121','刘婵','male','G000061','SC000016','A01','CR000004','1'),
	('S000122','刘阿斗','female','G000061','SC000016','A01','CR000004','1'),
	('S000123','李天一','male','G000062','SC000016','A01','CR000004','1'),
	('S000124','刘婵','female','G000062','SC000016','A01','CR000004','1'),
	('S000125','刘阿斗','male','G000063','SC000016','A01','CR000004','1'),
	('S000126','李天一','female','G000063','SC000016','A01','CR000004','1'),
	('S000127','刘婵','male','G000064','SC000016','A01','CR000004','1'),
	('S000128','刘阿斗','female','G000064','SC000016','A01','CR000004','1');

insert into student_health_survey_data values
	('SHS000001','S000001','2020-01-12 06:25:44','UnSubmitted','SC000001','CDHS000001','2020-01-11 17:38:57','2020-01-24 03:27:57','CR000001','1'),
	('SHS000002','S000001','2020-01-15 03:04:21','UnSubmitted','SC000001','CDHS000001','2020-01-21 20:19:49','2020-01-16 11:20:52','CR000001','1'),
	('SHS000003','S000002','2020-01-14 23:04:37','UnSubmitted','SC000001','CDHS000001','2020-01-11 10:39:03','2020-01-19 07:29:25','CR000001','1'),
	('SHS000004','S000002','2020-01-10 12:20:33','UnSubmitted','SC000001','CDHS000001','2020-01-10 19:24:46','2020-01-25 18:01:34','CR000001','1'),
	('SHS000005','S000003','2020-01-14 04:16:00','UnSubmitted','SC000001','CDHS000002','2020-01-12 06:44:04','2020-01-14 21:48:20','CR000001','1'),
	('SHS000006','S000003','2020-01-08 18:02:00','UnSubmitted','SC000001','CDHS000002','2020-01-27 00:06:08','2020-01-25 17:02:44','CR000001','1'),
	('SHS000007','S000004','2020-01-19 15:16:06','UnSubmitted','SC000001','CDHS000002','2020-01-18 23:44:01','2020-01-24 13:05:07','CR000001','1'),
	('SHS000008','S000004','2020-01-16 18:12:40','UnSubmitted','SC000001','CDHS000002','2020-01-16 03:25:51','2020-01-17 15:32:58','CR000001','1'),
	('SHS000009','S000005','2020-01-11 00:11:03','UnSubmitted','SC000001','CDHS000003','2020-01-23 19:19:00','2020-01-25 03:52:02','CR000001','1'),
	('SHS000010','S000005','2020-01-07 23:19:11','UnSubmitted','SC000001','CDHS000003','2020-01-06 12:17:59','2020-01-16 15:33:59','CR000001','1'),
	('SHS000011','S000006','2020-01-27 03:32:06','UnSubmitted','SC000001','CDHS000003','2020-01-10 11:18:26','2020-01-10 16:35:46','CR000001','1'),
	('SHS000012','S000006','2020-01-24 09:51:49','UnSubmitted','SC000001','CDHS000003','2020-01-09 17:34:55','2020-01-16 11:11:32','CR000001','1'),
	('SHS000013','S000007','2020-01-17 05:06:31','UnSubmitted','SC000001','CDHS000004','2020-01-09 20:17:46','2020-01-17 12:30:51','CR000001','1'),
	('SHS000014','S000007','2020-01-24 17:07:11','UnSubmitted','SC000001','CDHS000004','2020-01-20 11:47:08','2020-01-05 23:10:32','CR000001','1'),
	('SHS000015','S000008','2020-01-25 07:30:35','UnSubmitted','SC000001','CDHS000004','2020-01-20 17:07:33','2020-01-23 14:42:59','CR000001','1'),
	('SHS000016','S000008','2020-01-11 08:38:25','UnSubmitted','SC000001','CDHS000004','2020-01-11 05:02:56','2020-01-06 13:56:59','CR000001','1'),
	('SHS000017','S000009','2020-01-09 23:54:31','UnSubmitted','SC000002','CDHS000005','2020-01-27 00:49:43','2020-01-06 22:28:11','CR000001','1'),
	('SHS000018','S000009','2020-01-11 06:08:35','UnSubmitted','SC000002','CDHS000005','2020-01-08 23:36:40','2020-01-15 13:15:00','CR000001','1'),
	('SHS000019','S000010','2020-01-10 11:29:55','UnSubmitted','SC000002','CDHS000005','2020-01-19 14:19:37','2020-01-22 21:27:01','CR000001','1'),
	('SHS000020','S000010','2020-01-13 01:49:30','UnSubmitted','SC000002','CDHS000005','2020-01-22 23:17:19','2020-01-20 04:11:37','CR000001','1'),
	('SHS000021','S000011','2020-01-25 20:07:56','UnSubmitted','SC000002','CDHS000006','2020-01-24 05:32:05','2020-01-22 09:59:47','CR000001','1'),
	('SHS000022','S000011','2020-01-09 09:26:33','UnSubmitted','SC000002','CDHS000006','2020-01-25 15:58:17','2020-01-21 18:54:50','CR000001','1'),
	('SHS000023','S000012','2020-01-10 07:15:06','UnSubmitted','SC000002','CDHS000006','2020-01-08 10:14:18','2020-01-22 06:51:23','CR000001','1'),
	('SHS000024','S000012','2020-01-14 18:32:42','UnSubmitted','SC000002','CDHS000006','2020-01-27 05:25:40','2020-01-07 15:13:14','CR000001','1'),
	('SHS000025','S000013','2020-01-14 14:48:50','UnSubmitted','SC000002','CDHS000007','2020-01-09 20:02:56','2020-01-19 00:59:58','CR000001','1'),
	('SHS000026','S000013','2020-01-17 03:32:15','UnSubmitted','SC000002','CDHS000007','2020-01-18 19:07:43','2020-01-12 14:26:37','CR000001','1'),
	('SHS000027','S000014','2020-01-17 17:39:53','UnSubmitted','SC000002','CDHS000007','2020-01-20 23:04:33','2020-01-19 06:59:22','CR000001','1'),
	('SHS000028','S000014','2020-01-12 15:08:04','UnSubmitted','SC000002','CDHS000007','2020-01-25 19:32:52','2020-01-06 18:20:57','CR000001','1'),
	('SHS000029','S000015','2020-01-13 09:27:55','UnSubmitted','SC000002','CDHS000008','2020-01-21 17:36:58','2020-01-26 00:50:19','CR000001','1'),
	('SHS000030','S000015','2020-01-17 01:15:10','UnSubmitted','SC000002','CDHS000008','2020-01-18 02:04:55','2020-01-11 19:36:58','CR000001','1'),
	('SHS000031','S000016','2020-01-22 16:30:23','UnSubmitted','SC000002','CDHS000008','2020-01-17 17:38:19','2020-01-06 02:33:39','CR000001','1'),
	('SHS000032','S000016','2020-01-10 13:52:38','UnSubmitted','SC000002','CDHS000008','2020-01-14 19:37:36','2020-01-09 05:39:23','CR000001','1'),
	('SHS000033','S000017','2020-01-06 09:23:16','UnSubmitted','SC000003','CDHS000009','2020-01-18 21:03:57','2020-01-21 03:34:43','CR000001','1'),
	('SHS000034','S000017','2020-01-22 00:00:16','UnSubmitted','SC000003','CDHS000009','2020-01-13 10:37:24','2020-01-24 06:01:27','CR000001','1'),
	('SHS000035','S000018','2020-01-20 04:44:00','UnSubmitted','SC000003','CDHS000009','2020-01-05 16:52:27','2020-01-24 23:55:42','CR000001','1'),
	('SHS000036','S000018','2020-01-10 20:42:55','UnSubmitted','SC000003','CDHS000009','2020-01-10 05:37:00','2020-01-22 07:49:20','CR000001','1'),
	('SHS000037','S000019','2020-01-14 18:57:59','UnSubmitted','SC000003','CDHS000010','2020-01-18 08:11:24','2020-01-18 09:56:25','CR000001','1'),
	('SHS000038','S000019','2020-01-10 21:53:32','UnSubmitted','SC000003','CDHS000010','2020-01-05 20:33:24','2020-01-16 17:17:36','CR000001','1'),
	('SHS000039','S000020','2020-01-22 20:54:49','UnSubmitted','SC000003','CDHS000010','2020-01-20 11:12:34','2020-01-19 00:47:28','CR000001','1'),
	('SHS000040','S000020','2020-01-21 22:27:02','UnSubmitted','SC000003','CDHS000010','2020-01-23 15:42:28','2020-01-09 11:12:36','CR000001','1'),
	('SHS000041','S000021','2020-01-20 09:35:31','UnSubmitted','SC000003','CDHS000011','2020-01-23 01:30:59','2020-01-10 02:12:28','CR000001','1'),
	('SHS000042','S000021','2020-01-06 04:12:57','UnSubmitted','SC000003','CDHS000011','2020-01-07 03:28:36','2020-01-16 18:56:20','CR000001','1'),
	('SHS000043','S000022','2020-01-09 20:30:09','UnSubmitted','SC000003','CDHS000011','2020-01-21 12:39:24','2020-01-10 00:24:13','CR000001','1'),
	('SHS000044','S000022','2020-01-08 19:23:18','UnSubmitted','SC000003','CDHS000011','2020-01-16 14:52:39','2020-01-15 01:02:26','CR000001','1'),
	('SHS000045','S000023','2020-01-15 16:55:08','UnSubmitted','SC000003','CDHS000012','2020-01-20 00:07:03','2020-01-21 08:25:51','CR000001','1'),
	('SHS000046','S000023','2020-01-17 16:12:40','UnSubmitted','SC000003','CDHS000012','2020-01-10 23:34:51','2020-01-07 11:27:43','CR000001','1'),
	('SHS000047','S000024','2020-01-18 01:00:31','UnSubmitted','SC000003','CDHS000012','2020-01-23 22:40:56','2020-01-18 13:02:01','CR000001','1'),
	('SHS000048','S000024','2020-01-08 22:52:02','UnSubmitted','SC000003','CDHS000012','2020-01-10 12:21:22','2020-01-12 05:55:48','CR000001','1'),
	('SHS000049','S000025','2020-01-08 04:06:25','UnSubmitted','SC000004','CDHS000013','2020-01-13 20:12:49','2020-01-11 15:00:22','CR000001','1'),
	('SHS000050','S000025','2020-01-05 19:49:12','UnSubmitted','SC000004','CDHS000013','2020-01-13 19:21:48','2020-01-11 13:02:14','CR000001','1'),
	('SHS000051','S000026','2020-01-19 01:28:38','UnSubmitted','SC000004','CDHS000013','2020-01-24 12:05:10','2020-01-24 16:34:34','CR000001','1'),
	('SHS000052','S000026','2020-01-16 22:41:11','UnSubmitted','SC000004','CDHS000013','2020-01-16 15:02:00','2020-01-11 03:32:03','CR000001','1'),
	('SHS000053','S000027','2020-01-06 12:18:26','UnSubmitted','SC000004','CDHS000014','2020-01-11 12:55:09','2020-01-11 05:44:54','CR000001','1'),
	('SHS000054','S000027','2020-01-18 14:25:12','UnSubmitted','SC000004','CDHS000014','2020-01-08 20:04:18','2020-01-09 00:57:17','CR000001','1'),
	('SHS000055','S000028','2020-01-17 17:09:23','UnSubmitted','SC000004','CDHS000014','2020-01-17 09:16:00','2020-01-20 02:54:27','CR000001','1'),
	('SHS000056','S000028','2020-01-24 20:41:23','UnSubmitted','SC000004','CDHS000014','2020-01-08 10:30:50','2020-01-26 09:12:35','CR000001','1'),
	('SHS000057','S000029','2020-01-26 22:32:28','UnSubmitted','SC000004','CDHS000015','2020-01-22 12:45:41','2020-01-22 00:04:04','CR000001','1'),
	('SHS000058','S000029','2020-01-16 03:37:27','UnSubmitted','SC000004','CDHS000015','2020-01-13 08:19:05','2020-01-07 13:04:52','CR000001','1'),
	('SHS000059','S000030','2020-01-19 13:20:37','UnSubmitted','SC000004','CDHS000015','2020-01-27 09:51:34','2020-01-05 23:05:27','CR000001','1'),
	('SHS000060','S000030','2020-01-11 14:28:57','UnSubmitted','SC000004','CDHS000015','2020-01-11 10:52:20','2020-01-24 13:25:40','CR000001','1'),
	('SHS000061','S000031','2020-01-21 21:29:04','UnSubmitted','SC000004','CDHS000016','2020-01-06 00:19:41','2020-01-11 12:50:19','CR000001','1'),
	('SHS000062','S000031','2020-01-20 04:30:38','UnSubmitted','SC000004','CDHS000016','2020-01-27 04:01:59','2020-01-13 20:46:21','CR000001','1'),
	('SHS000063','S000032','2020-01-15 21:48:36','UnSubmitted','SC000004','CDHS000016','2020-01-19 14:12:50','2020-01-18 02:09:43','CR000001','1'),
	('SHS000064','S000032','2020-01-24 05:02:59','UnSubmitted','SC000004','CDHS000016','2020-01-16 15:23:28','2020-01-23 06:22:55','CR000001','1'),
	('SHS000065','S000033','2020-01-09 13:30:29','UnSubmitted','SC000005','CDHS000017','2020-01-25 11:02:30','2020-01-11 20:47:52','CR000002','1'),
	('SHS000066','S000033','2020-01-18 14:47:09','UnSubmitted','SC000005','CDHS000017','2020-01-16 03:00:11','2020-01-07 07:18:42','CR000002','1'),
	('SHS000067','S000034','2020-01-18 15:40:27','UnSubmitted','SC000005','CDHS000017','2020-01-13 03:08:08','2020-01-14 09:19:01','CR000002','1'),
	('SHS000068','S000034','2020-01-14 00:19:18','UnSubmitted','SC000005','CDHS000017','2020-01-14 14:03:47','2020-01-09 03:25:20','CR000002','1'),
	('SHS000069','S000035','2020-01-14 11:02:15','UnSubmitted','SC000005','CDHS000018','2020-01-18 17:31:53','2020-01-10 04:26:28','CR000002','1'),
	('SHS000070','S000035','2020-01-08 09:22:39','UnSubmitted','SC000005','CDHS000018','2020-01-20 07:35:01','2020-01-17 12:16:04','CR000002','1'),
	('SHS000071','S000036','2020-01-13 21:45:02','UnSubmitted','SC000005','CDHS000018','2020-01-05 18:20:43','2020-01-07 10:40:42','CR000002','1'),
	('SHS000072','S000036','2020-01-13 20:40:08','UnSubmitted','SC000005','CDHS000018','2020-01-05 15:48:41','2020-01-11 12:59:37','CR000002','1'),
	('SHS000073','S000037','2020-01-17 08:53:03','UnSubmitted','SC000005','CDHS000019','2020-01-19 20:15:28','2020-01-22 19:34:54','CR000002','1'),
	('SHS000074','S000037','2020-01-13 23:38:59','UnSubmitted','SC000005','CDHS000019','2020-01-19 00:58:39','2020-01-20 16:58:03','CR000002','1'),
	('SHS000075','S000038','2020-01-18 21:37:10','UnSubmitted','SC000005','CDHS000019','2020-01-06 20:37:26','2020-01-24 18:39:48','CR000002','1'),
	('SHS000076','S000038','2020-01-21 22:43:57','UnSubmitted','SC000005','CDHS000019','2020-01-09 14:14:29','2020-01-14 15:17:51','CR000002','1'),
	('SHS000077','S000039','2020-01-07 11:15:47','UnSubmitted','SC000005','CDHS000020','2020-01-10 11:53:10','2020-01-19 19:48:36','CR000002','1'),
	('SHS000078','S000039','2020-01-21 08:34:40','UnSubmitted','SC000005','CDHS000020','2020-01-15 17:22:22','2020-01-10 05:32:16','CR000002','1'),
	('SHS000079','S000040','2020-01-15 09:58:18','UnSubmitted','SC000005','CDHS000020','2020-01-22 08:22:56','2020-01-20 17:04:58','CR000002','1'),
	('SHS000080','S000040','2020-01-16 16:05:16','UnSubmitted','SC000005','CDHS000020','2020-01-21 12:51:05','2020-01-20 07:17:50','CR000002','1'),
	('SHS000081','S000041','2020-01-22 03:57:10','UnSubmitted','SC000006','CDHS000021','2020-01-11 06:37:04','2020-01-20 04:51:09','CR000002','1'),
	('SHS000082','S000041','2020-01-14 05:03:45','UnSubmitted','SC000006','CDHS000021','2020-01-24 15:23:05','2020-01-15 02:55:34','CR000002','1'),
	('SHS000083','S000042','2020-01-16 15:32:59','UnSubmitted','SC000006','CDHS000021','2020-01-27 11:39:50','2020-01-19 10:05:33','CR000002','1'),
	('SHS000084','S000042','2020-01-11 03:24:45','UnSubmitted','SC000006','CDHS000021','2020-01-22 20:14:33','2020-01-12 01:10:24','CR000002','1'),
	('SHS000085','S000043','2020-01-13 15:39:38','UnSubmitted','SC000006','CDHS000022','2020-01-25 15:05:35','2020-01-12 17:26:24','CR000002','1'),
	('SHS000086','S000043','2020-01-11 18:27:41','UnSubmitted','SC000006','CDHS000022','2020-01-06 01:17:27','2020-01-26 19:30:58','CR000002','1'),
	('SHS000087','S000044','2020-01-19 00:39:56','Submitted','SC000006','CDHS000022','2020-01-15 21:42:39','2020-01-11 17:34:59','CR000002','1'),
	('SHS000088','S000044','2020-01-23 23:28:25','Submitted','SC000006','CDHS000022','2020-01-06 01:56:02','2020-01-06 18:53:21','CR000002','1'),
	('SHS000089','S000045','2020-01-21 02:33:01','Submitted','SC000006','CDHS000023','2020-01-09 13:44:35','2020-01-25 06:24:46','CR000002','1'),
	('SHS000090','S000045','2020-01-17 09:12:12','Submitted','SC000006','CDHS000023','2020-01-15 09:03:47','2020-01-26 04:39:55','CR000002','1'),
	('SHS000091','S000046','2020-01-23 08:35:54','Submitted','SC000006','CDHS000023','2020-01-16 06:14:05','2020-01-21 10:43:46','CR000002','1'),
	('SHS000092','S000046','2020-01-10 04:33:43','Submitted','SC000006','CDHS000023','2020-01-18 11:44:41','2020-01-20 09:57:41','CR000002','1'),
	('SHS000093','S000047','2020-01-22 20:27:14','Submitted','SC000006','CDHS000024','2020-01-10 23:48:39','2020-01-15 10:44:59','CR000002','1'),
	('SHS000094','S000047','2020-01-07 12:36:27','Submitted','SC000006','CDHS000024','2020-01-07 08:34:58','2020-01-11 23:02:41','CR000002','1'),
	('SHS000095','S000048','2020-01-16 10:34:08','Submitted','SC000006','CDHS000024','2020-01-24 08:13:04','2020-01-15 01:14:01','CR000002','1'),
	('SHS000096','S000048','2020-01-09 00:48:19','Submitted','SC000006','CDHS000024','2020-01-06 20:44:01','2020-01-16 15:14:03','CR000002','1'),
	('SHS000097','S000049','2020-01-23 10:54:58','Submitted','SC000007','CDHS000025','2020-01-25 17:17:17','2020-01-25 22:04:15','CR000002','1'),
	('SHS000098','S000049','2020-01-17 07:57:30','Submitted','SC000007','CDHS000025','2020-01-21 12:05:32','2020-01-14 03:43:07','CR000002','1'),
	('SHS000099','S000050','2020-01-11 09:04:39','Submitted','SC000007','CDHS000025','2020-01-21 13:01:24','2020-01-17 22:17:14','CR000002','1'),
	('SHS000100','S000050','2020-01-17 03:27:30','Submitted','SC000007','CDHS000025','2020-01-07 10:34:39','2020-01-11 16:02:35','CR000002','1'),
	('SHS000101','S000051','2020-01-16 07:57:02','Submitted','SC000007','CDHS000026','2020-01-12 07:06:00','2020-01-18 12:41:06','CR000002','1'),
	('SHS000102','S000051','2020-01-11 21:29:56','Submitted','SC000007','CDHS000026','2020-01-08 10:40:37','2020-01-08 14:00:45','CR000002','1'),
	('SHS000103','S000052','2020-01-10 09:19:52','Submitted','SC000007','CDHS000026','2020-01-26 14:01:39','2020-01-07 15:38:30','CR000002','1'),
	('SHS000104','S000052','2020-01-13 05:48:25','Submitted','SC000007','CDHS000026','2020-01-10 17:03:24','2020-01-10 12:00:19','CR000002','1'),
	('SHS000105','S000053','2020-01-23 00:57:38','Submitted','SC000007','CDHS000027','2020-01-24 07:27:03','2020-01-25 00:27:01','CR000002','1'),
	('SHS000106','S000053','2020-01-17 07:52:50','Submitted','SC000007','CDHS000027','2020-01-10 11:46:46','2020-01-19 13:37:00','CR000002','1'),
	('SHS000107','S000054','2020-01-19 20:06:50','Submitted','SC000007','CDHS000027','2020-01-26 00:29:30','2020-01-12 12:12:26','CR000002','1'),
	('SHS000108','S000054','2020-01-13 08:57:07','Submitted','SC000007','CDHS000027','2020-01-22 21:57:35','2020-01-17 11:24:29','CR000002','1'),
	('SHS000109','S000055','2020-01-14 20:08:46','Submitted','SC000007','CDHS000028','2020-01-25 22:28:27','2020-01-19 00:36:04','CR000002','1'),
	('SHS000110','S000055','2020-01-09 12:58:26','Submitted','SC000007','CDHS000028','2020-01-23 20:38:13','2020-01-21 04:14:37','CR000002','1'),
	('SHS000111','S000056','2020-01-06 00:09:44','Submitted','SC000007','CDHS000028','2020-01-22 08:10:46','2020-01-22 04:07:28','CR000002','1'),
	('SHS000112','S000056','2020-01-16 00:01:32','Submitted','SC000007','CDHS000028','2020-01-19 15:02:56','2020-01-24 11:46:41','CR000002','1'),
	('SHS000113','S000057','2020-01-10 02:15:37','Submitted','SC000008','CDHS000029','2020-01-10 08:27:52','2020-01-21 02:38:03','CR000002','1'),
	('SHS000114','S000057','2020-01-22 20:09:54','Submitted','SC000008','CDHS000029','2020-01-10 05:06:11','2020-01-17 04:53:39','CR000002','1'),
	('SHS000115','S000058','2020-01-26 17:52:18','Submitted','SC000008','CDHS000029','2020-01-12 17:16:20','2020-01-14 19:49:55','CR000002','1'),
	('SHS000116','S000058','2020-01-07 03:58:03','Submitted','SC000008','CDHS000029','2020-01-14 20:07:21','2020-01-13 07:29:10','CR000002','1'),
	('SHS000117','S000059','2020-01-19 17:11:06','Submitted','SC000008','CDHS000030','2020-01-16 11:21:02','2020-01-15 11:22:57','CR000002','1'),
	('SHS000118','S000059','2020-01-12 06:23:01','Submitted','SC000008','CDHS000030','2020-01-08 10:56:38','2020-01-06 21:13:11','CR000002','1'),
	('SHS000119','S000060','2020-01-22 09:49:15','Submitted','SC000008','CDHS000030','2020-01-21 07:04:42','2020-01-14 23:55:23','CR000002','1'),
	('SHS000120','S000060','2020-01-27 01:37:46','Submitted','SC000008','CDHS000030','2020-01-08 01:29:55','2020-01-23 11:00:41','CR000002','1'),
	('SHS000121','S000061','2020-01-15 20:41:17','Submitted','SC000008','CDHS000031','2020-01-18 17:08:57','2020-01-06 17:30:00','CR000002','1'),
	('SHS000122','S000061','2020-01-15 15:04:28','Submitted','SC000008','CDHS000031','2020-01-07 15:00:56','2020-01-11 00:20:53','CR000002','1'),
	('SHS000123','S000062','2020-01-09 02:19:15','Submitted','SC000008','CDHS000031','2020-01-17 13:13:48','2020-01-20 08:54:58','CR000002','1'),
	('SHS000124','S000062','2020-01-23 03:53:49','Submitted','SC000008','CDHS000031','2020-01-27 10:34:41','2020-01-14 14:42:35','CR000002','1'),
	('SHS000125','S000063','2020-01-18 09:05:16','Submitted','SC000008','CDHS000032','2020-01-24 00:52:34','2020-01-21 05:45:32','CR000002','1'),
	('SHS000126','S000063','2020-01-18 03:59:42','Submitted','SC000008','CDHS000032','2020-01-10 05:31:24','2020-01-14 09:07:17','CR000002','1'),
	('SHS000127','S000064','2020-01-23 11:11:58','Submitted','SC000008','CDHS000032','2020-01-20 15:45:00','2020-01-27 09:41:12','CR000002','1'),
	('SHS000128','S000064','2020-01-13 16:06:53','Submitted','SC000008','CDHS000032','2020-01-19 02:11:15','2020-01-16 02:38:51','CR000002','1'),
	('SHS000129','S000065','2020-01-13 16:11:13','Submitted','SC000009','CDHS000033','2020-01-20 19:36:45','2020-01-09 04:12:32','CR000003','1'),
	('SHS000130','S000065','2020-01-26 11:38:58','Submitted','SC000009','CDHS000033','2020-01-24 05:49:02','2020-01-20 17:32:01','CR000003','1'),
	('SHS000131','S000066','2020-01-27 00:42:21','Submitted','SC000009','CDHS000033','2020-01-09 04:19:23','2020-01-17 23:35:04','CR000003','1'),
	('SHS000132','S000066','2020-01-24 20:57:57','Submitted','SC000009','CDHS000033','2020-01-22 11:05:35','2020-01-15 00:02:15','CR000003','1'),
	('SHS000133','S000067','2020-01-12 00:33:06','Submitted','SC000009','CDHS000034','2020-01-06 17:43:20','2020-01-14 08:58:50','CR000003','1'),
	('SHS000134','S000067','2020-01-26 01:46:07','Submitted','SC000009','CDHS000034','2020-01-23 18:07:19','2020-01-14 07:52:25','CR000003','1'),
	('SHS000135','S000068','2020-01-22 14:46:11','Submitted','SC000009','CDHS000034','2020-01-11 17:08:26','2020-01-22 17:02:02','CR000003','1'),
	('SHS000136','S000068','2020-01-24 15:54:18','Submitted','SC000009','CDHS000034','2020-01-27 11:15:08','2020-01-20 21:04:15','CR000003','1'),
	('SHS000137','S000069','2020-01-09 08:24:03','Submitted','SC000009','CDHS000035','2020-01-09 14:10:49','2020-01-07 00:18:16','CR000003','1'),
	('SHS000138','S000069','2020-01-13 06:32:29','Submitted','SC000009','CDHS000035','2020-01-08 16:12:51','2020-01-10 07:40:47','CR000003','1'),
	('SHS000139','S000070','2020-01-20 04:04:50','Submitted','SC000009','CDHS000035','2020-01-11 21:23:47','2020-01-23 21:02:54','CR000003','1'),
	('SHS000140','S000070','2020-01-25 19:15:42','Submitted','SC000009','CDHS000035','2020-01-07 23:13:45','2020-01-15 06:52:44','CR000003','1'),
	('SHS000141','S000071','2020-01-06 22:26:04','Submitted','SC000009','CDHS000036','2020-01-10 07:37:24','2020-01-10 09:36:25','CR000003','1'),
	('SHS000142','S000071','2020-01-11 19:27:01','Submitted','SC000009','CDHS000036','2020-01-19 10:08:38','2020-01-21 21:04:35','CR000003','1'),
	('SHS000143','S000072','2020-01-08 19:44:41','Submitted','SC000009','CDHS000036','2020-01-17 16:53:20','2020-01-14 06:00:03','CR000003','1'),
	('SHS000144','S000072','2020-01-25 08:49:38','Submitted','SC000009','CDHS000036','2020-01-13 07:06:53','2020-01-24 11:33:12','CR000003','1'),
	('SHS000145','S000073','2020-01-24 03:51:57','Submitted','SC000010','CDHS000037','2020-01-25 11:14:37','2020-01-20 22:52:56','CR000003','1'),
	('SHS000146','S000073','2020-01-22 21:11:43','Submitted','SC000010','CDHS000037','2020-01-24 04:51:46','2020-01-11 02:00:28','CR000003','1'),
	('SHS000147','S000074','2020-01-17 06:15:48','Submitted','SC000010','CDHS000037','2020-01-16 14:26:41','2020-01-15 04:11:20','CR000003','1'),
	('SHS000148','S000074','2020-01-15 02:06:14','Submitted','SC000010','CDHS000037','2020-01-21 08:06:12','2020-01-25 02:02:51','CR000003','1'),
	('SHS000149','S000075','2020-01-25 18:56:07','Submitted','SC000010','CDHS000038','2020-01-14 10:39:10','2020-01-11 21:34:21','CR000003','1'),
	('SHS000150','S000075','2020-01-19 02:54:28','Submitted','SC000010','CDHS000038','2020-01-15 23:39:34','2020-01-22 03:06:35','CR000003','1'),
	('SHS000151','S000076','2020-01-25 04:49:01','Submitted','SC000010','CDHS000038','2020-01-17 11:12:45','2020-01-09 11:35:09','CR000003','1'),
	('SHS000152','S000076','2020-01-13 11:38:50','Submitted','SC000010','CDHS000038','2020-01-16 14:20:31','2020-01-14 20:55:37','CR000003','1'),
	('SHS000153','S000077','2020-01-07 13:52:42','Submitted','SC000010','CDHS000039','2020-01-23 01:42:17','2020-01-26 05:41:27','CR000003','1'),
	('SHS000154','S000077','2020-01-26 06:01:22','Submitted','SC000010','CDHS000039','2020-01-10 06:37:01','2020-01-18 18:14:32','CR000003','1'),
	('SHS000155','S000078','2020-01-20 04:22:26','Submitted','SC000010','CDHS000039','2020-01-19 00:52:38','2020-01-13 15:42:57','CR000003','1'),
	('SHS000156','S000078','2020-01-07 22:26:31','Submitted','SC000010','CDHS000039','2020-01-24 04:14:04','2020-01-12 00:10:56','CR000003','1'),
	('SHS000157','S000079','2020-01-25 14:04:25','Submitted','SC000010','CDHS000040','2020-01-15 01:36:42','2020-01-10 08:29:12','CR000003','1'),
	('SHS000158','S000079','2020-01-19 22:14:57','Submitted','SC000010','CDHS000040','2020-01-25 13:29:29','2020-01-25 06:17:38','CR000003','1'),
	('SHS000159','S000080','2020-01-14 12:56:08','Submitted','SC000010','CDHS000040','2020-01-09 01:44:06','2020-01-23 16:48:03','CR000003','1'),
	('SHS000160','S000080','2020-01-06 08:15:14','Submitted','SC000010','CDHS000040','2020-01-14 06:59:31','2020-01-22 10:24:42','CR000003','1'),
	('SHS000161','S000081','2020-01-07 04:20:33','Submitted','SC000011','CDHS000041','2020-01-09 07:30:34','2020-01-18 00:01:18','CR000003','1'),
	('SHS000162','S000081','2020-01-25 11:35:58','Submitted','SC000011','CDHS000041','2020-01-22 23:28:55','2020-01-15 23:29:32','CR000003','1'),
	('SHS000163','S000082','2020-01-11 13:54:47','Submitted','SC000011','CDHS000041','2020-01-25 20:55:04','2020-01-06 13:24:48','CR000003','1'),
	('SHS000164','S000082','2020-01-10 23:58:08','Submitted','SC000011','CDHS000041','2020-01-13 06:19:57','2020-01-19 17:04:14','CR000003','1'),
	('SHS000165','S000083','2020-01-08 13:17:00','Submitted','SC000011','CDHS000042','2020-01-09 18:43:23','2020-01-24 02:07:42','CR000003','1'),
	('SHS000166','S000083','2020-01-14 07:51:16','Submitted','SC000011','CDHS000042','2020-01-12 08:05:23','2020-01-14 02:47:29','CR000003','1'),
	('SHS000167','S000084','2020-01-15 02:22:34','Submitted','SC000011','CDHS000042','2020-01-13 02:53:40','2020-01-06 04:38:16','CR000003','1'),
	('SHS000168','S000084','2020-01-20 21:52:11','Submitted','SC000011','CDHS000042','2020-01-14 00:24:34','2020-01-21 04:32:28','CR000003','1'),
	('SHS000169','S000085','2020-01-08 19:06:37','Submitted','SC000011','CDHS000043','2020-01-13 03:08:48','2020-01-19 02:38:49','CR000003','1'),
	('SHS000170','S000085','2020-01-08 09:06:56','Submitted','SC000011','CDHS000043','2020-01-25 07:17:19','2020-01-16 23:29:34','CR000003','1'),
	('SHS000171','S000086','2020-01-15 04:20:27','Submitted','SC000011','CDHS000043','2020-01-06 12:16:46','2020-01-09 22:10:40','CR000003','1'),
	('SHS000172','S000086','2020-01-26 20:01:40','Draft','SC000011','CDHS000043','2020-01-22 00:53:15','2020-01-24 14:42:16','CR000003','1'),
	('SHS000173','S000087','2020-01-26 12:07:43','Draft','SC000011','CDHS000044','2020-01-18 12:48:34','2020-01-08 07:17:33','CR000003','1'),
	('SHS000174','S000087','2020-01-14 01:03:26','Draft','SC000011','CDHS000044','2020-01-11 16:18:24','2020-01-12 08:55:49','CR000003','1'),
	('SHS000175','S000088','2020-01-25 06:58:06','Draft','SC000011','CDHS000044','2020-01-18 01:21:51','2020-01-08 23:57:31','CR000003','1'),
	('SHS000176','S000088','2020-01-20 02:52:34','Draft','SC000011','CDHS000044','2020-01-20 03:25:35','2020-01-07 03:06:35','CR000003','1'),
	('SHS000177','S000089','2020-01-26 02:31:27','Draft','SC000012','CDHS000045','2020-01-21 22:00:05','2020-01-22 11:52:02','CR000003','1'),
	('SHS000178','S000089','2020-01-09 23:19:19','Draft','SC000012','CDHS000045','2020-01-11 19:17:38','2020-01-15 01:59:14','CR000003','1'),
	('SHS000179','S000090','2020-01-11 22:54:56','Draft','SC000012','CDHS000045','2020-01-14 13:14:27','2020-01-16 00:21:54','CR000003','1'),
	('SHS000180','S000090','2020-01-08 00:48:07','Draft','SC000012','CDHS000045','2020-01-21 01:49:17','2020-01-26 07:30:27','CR000003','1'),
	('SHS000181','S000091','2020-01-25 13:08:52','Draft','SC000012','CDHS000046','2020-01-14 17:20:35','2020-01-26 04:33:12','CR000003','1'),
	('SHS000182','S000091','2020-01-18 09:12:39','Draft','SC000012','CDHS000046','2020-01-14 08:34:33','2020-01-25 03:50:36','CR000003','1'),
	('SHS000183','S000092','2020-01-22 00:32:11','Draft','SC000012','CDHS000046','2020-01-06 15:55:19','2020-01-14 00:31:14','CR000003','1'),
	('SHS000184','S000092','2020-01-23 13:14:24','Draft','SC000012','CDHS000046','2020-01-14 13:31:45','2020-01-06 20:53:12','CR000003','1'),
	('SHS000185','S000093','2020-01-17 00:40:10','Draft','SC000012','CDHS000047','2020-01-25 04:50:43','2020-01-18 19:19:33','CR000003','1'),
	('SHS000186','S000093','2020-01-20 14:11:57','Draft','SC000012','CDHS000047','2020-01-09 08:07:09','2020-01-25 11:50:12','CR000003','1'),
	('SHS000187','S000094','2020-01-24 10:45:05','Draft','SC000012','CDHS000047','2020-01-07 15:27:56','2020-01-09 21:20:41','CR000003','1'),
	('SHS000188','S000094','2020-01-05 14:40:15','Draft','SC000012','CDHS000047','2020-01-16 05:39:42','2020-01-18 04:09:59','CR000003','1'),
	('SHS000189','S000095','2020-01-09 01:38:29','Draft','SC000012','CDHS000048','2020-01-19 10:20:51','2020-01-24 09:05:43','CR000003','1'),
	('SHS000190','S000095','2020-01-09 15:52:15','Draft','SC000012','CDHS000048','2020-01-14 10:00:44','2020-01-25 12:05:41','CR000003','1'),
	('SHS000191','S000096','2020-01-09 19:12:46','Draft','SC000012','CDHS000048','2020-01-11 07:11:59','2020-01-22 10:08:40','CR000003','1'),
	('SHS000192','S000096','2020-01-16 14:19:08','Draft','SC000012','CDHS000048','2020-01-12 18:33:04','2020-01-16 04:49:24','CR000003','1'),
	('SHS000193','S000097','2020-01-19 23:26:41','Draft','SC000013','CDHS000049','2020-01-26 22:20:59','2020-01-24 19:02:57','CR000004','1'),
	('SHS000194','S000097','2020-01-11 01:23:17','Draft','SC000013','CDHS000049','2020-01-26 17:14:10','2020-01-27 06:02:24','CR000004','1'),
	('SHS000195','S000098','2020-01-08 04:51:18','Draft','SC000013','CDHS000049','2020-01-16 20:53:34','2020-01-26 14:35:03','CR000004','1'),
	('SHS000196','S000098','2020-01-17 02:36:53','Draft','SC000013','CDHS000049','2020-01-13 10:41:34','2020-01-15 13:17:55','CR000004','1'),
	('SHS000197','S000099','2020-01-16 22:36:23','Draft','SC000013','CDHS000050','2020-01-25 19:20:31','2020-01-06 20:46:41','CR000004','1'),
	('SHS000198','S000099','2020-01-22 09:49:53','Draft','SC000013','CDHS000050','2020-01-11 13:32:04','2020-01-12 04:11:16','CR000004','1'),
	('SHS000199','S000100','2020-01-26 21:53:18','Draft','SC000013','CDHS000050','2020-01-19 12:33:09','2020-01-08 04:50:10','CR000004','1'),
	('SHS000200','S000100','2020-01-20 09:02:11','Draft','SC000013','CDHS000050','2020-01-20 06:15:00','2020-01-26 21:05:34','CR000004','1'),
	('SHS000201','S000101','2020-01-10 15:21:58','Draft','SC000013','CDHS000051','2020-01-24 12:30:19','2020-01-11 08:58:37','CR000004','1'),
	('SHS000202','S000101','2020-01-22 12:48:52','Draft','SC000013','CDHS000051','2020-01-09 15:00:19','2020-01-07 21:03:02','CR000004','1'),
	('SHS000203','S000102','2020-01-26 20:06:02','Draft','SC000013','CDHS000051','2020-01-08 04:26:25','2020-01-07 01:57:38','CR000004','1'),
	('SHS000204','S000102','2020-01-08 22:21:17','Draft','SC000013','CDHS000051','2020-01-06 11:08:56','2020-01-18 02:11:45','CR000004','1'),
	('SHS000205','S000103','2020-01-15 15:37:55','Draft','SC000013','CDHS000052','2020-01-17 07:06:28','2020-01-27 07:11:37','CR000004','1'),
	('SHS000206','S000103','2020-01-17 16:08:51','Draft','SC000013','CDHS000052','2020-01-27 03:43:32','2020-01-16 17:02:12','CR000004','1'),
	('SHS000207','S000104','2020-01-18 14:51:05','Draft','SC000013','CDHS000052','2020-01-17 02:16:30','2020-01-22 07:40:06','CR000004','1'),
	('SHS000208','S000104','2020-01-11 07:12:34','Draft','SC000013','CDHS000052','2020-01-26 08:55:47','2020-01-20 18:53:29','CR000004','1'),
	('SHS000209','S000105','2020-01-14 17:36:18','Draft','SC000014','CDHS000053','2020-01-24 02:01:49','2020-01-08 02:50:02','CR000004','1'),
	('SHS000210','S000105','2020-01-11 07:08:36','Draft','SC000014','CDHS000053','2020-01-15 22:53:03','2020-01-13 01:46:13','CR000004','1'),
	('SHS000211','S000106','2020-01-10 06:06:03','Draft','SC000014','CDHS000053','2020-01-10 09:14:48','2020-01-26 07:36:58','CR000004','1'),
	('SHS000212','S000106','2020-01-23 12:42:41','Draft','SC000014','CDHS000053','2020-01-17 23:18:17','2020-01-06 21:16:18','CR000004','1'),
	('SHS000213','S000107','2020-01-18 11:57:43','Draft','SC000014','CDHS000054','2020-01-20 11:27:39','2020-01-16 08:04:18','CR000004','1'),
	('SHS000214','S000107','2020-01-21 20:28:44','Draft','SC000014','CDHS000054','2020-01-23 04:44:13','2020-01-08 12:40:53','CR000004','1'),
	('SHS000215','S000108','2020-01-18 08:28:53','Draft','SC000014','CDHS000054','2020-01-22 20:48:22','2020-01-16 10:50:14','CR000004','1'),
	('SHS000216','S000108','2020-01-19 16:36:28','Draft','SC000014','CDHS000054','2020-01-24 22:27:57','2020-01-13 03:44:38','CR000004','1'),
	('SHS000217','S000109','2020-01-10 05:34:43','Draft','SC000014','CDHS000055','2020-01-13 09:03:20','2020-01-09 09:36:31','CR000004','1'),
	('SHS000218','S000109','2020-01-20 03:05:44','Draft','SC000014','CDHS000055','2020-01-21 21:36:24','2020-01-21 18:36:37','CR000004','1'),
	('SHS000219','S000110','2020-01-21 11:44:09','Draft','SC000014','CDHS000055','2020-01-16 10:11:34','2020-01-05 23:51:53','CR000004','1'),
	('SHS000220','S000110','2020-01-24 16:52:01','Draft','SC000014','CDHS000055','2020-01-10 19:43:27','2020-01-17 05:27:10','CR000004','1'),
	('SHS000221','S000111','2020-01-15 22:56:12','Draft','SC000014','CDHS000056','2020-01-07 06:01:11','2020-01-13 16:47:45','CR000004','1'),
	('SHS000222','S000111','2020-01-09 13:29:21','Draft','SC000014','CDHS000056','2020-01-13 09:44:53','2020-01-24 20:08:15','CR000004','1'),
	('SHS000223','S000112','2020-01-10 06:51:07','Draft','SC000014','CDHS000056','2020-01-19 07:09:03','2020-01-12 03:29:47','CR000004','1'),
	('SHS000224','S000112','2020-01-19 16:56:22','Draft','SC000014','CDHS000056','2020-01-12 15:17:34','2020-01-13 00:26:18','CR000004','1'),
	('SHS000225','S000113','2020-01-12 04:11:40','Draft','SC000015','CDHS000057','2020-01-16 14:10:12','2020-01-13 16:29:53','CR000004','1'),
	('SHS000226','S000113','2020-01-27 02:27:47','Draft','SC000015','CDHS000057','2020-01-22 21:44:55','2020-01-19 18:23:55','CR000004','1'),
	('SHS000227','S000114','2020-01-14 03:05:50','Draft','SC000015','CDHS000057','2020-01-24 11:14:11','2020-01-21 16:39:36','CR000004','1'),
	('SHS000228','S000114','2020-01-15 18:36:50','Draft','SC000015','CDHS000057','2020-01-17 09:43:41','2020-01-26 08:44:39','CR000004','1'),
	('SHS000229','S000115','2020-01-24 09:13:11','Draft','SC000015','CDHS000058','2020-01-14 04:20:51','2020-01-22 06:59:18','CR000004','1'),
	('SHS000230','S000115','2020-01-22 19:02:04','Draft','SC000015','CDHS000058','2020-01-25 18:29:20','2020-01-25 07:51:32','CR000004','1'),
	('SHS000231','S000116','2020-01-12 19:18:24','Draft','SC000015','CDHS000058','2020-01-16 08:09:28','2020-01-20 05:47:03','CR000004','1'),
	('SHS000232','S000116','2020-01-16 14:56:10','Draft','SC000015','CDHS000058','2020-01-09 04:42:54','2020-01-15 10:43:53','CR000004','1'),
	('SHS000233','S000117','2020-01-15 21:27:48','Draft','SC000015','CDHS000059','2020-01-10 04:42:23','2020-01-07 08:10:26','CR000004','1'),
	('SHS000234','S000117','2020-01-10 15:27:19','Draft','SC000015','CDHS000059','2020-01-25 22:05:05','2020-01-15 11:55:14','CR000004','1'),
	('SHS000235','S000118','2020-01-16 18:24:31','Draft','SC000015','CDHS000059','2020-01-21 03:53:20','2020-01-21 01:31:50','CR000004','1'),
	('SHS000236','S000118','2020-01-07 06:40:37','Draft','SC000015','CDHS000059','2020-01-08 06:44:16','2020-01-14 12:24:22','CR000004','1'),
	('SHS000237','S000119','2020-01-19 07:37:27','Draft','SC000015','CDHS000060','2020-01-20 00:27:24','2020-01-21 03:31:31','CR000004','1'),
	('SHS000238','S000119','2020-01-16 19:40:39','Draft','SC000015','CDHS000060','2020-01-19 12:44:07','2020-01-06 08:54:09','CR000004','1'),
	('SHS000239','S000120','2020-01-08 03:15:48','Draft','SC000015','CDHS000060','2020-01-10 02:29:03','2020-01-18 18:26:51','CR000004','1'),
	('SHS000240','S000120','2020-01-20 09:19:47','Draft','SC000015','CDHS000060','2020-01-17 11:41:56','2020-01-14 04:45:16','CR000004','1'),
	('SHS000241','S000121','2020-01-05 14:59:19','Draft','SC000016','CDHS000061','2020-01-10 02:57:32','2020-01-16 09:01:57','CR000004','1'),
	('SHS000242','S000121','2020-01-08 16:24:48','Draft','SC000016','CDHS000061','2020-01-16 17:45:03','2020-01-09 23:28:41','CR000004','1'),
	('SHS000243','S000122','2020-01-22 01:16:48','Draft','SC000016','CDHS000061','2020-01-21 15:58:40','2020-01-08 11:29:53','CR000004','1'),
	('SHS000244','S000122','2020-01-07 12:47:22','Draft','SC000016','CDHS000061','2020-01-12 06:33:18','2020-01-15 06:34:32','CR000004','1'),
	('SHS000245','S000123','2020-01-06 18:56:32','Draft','SC000016','CDHS000062','2020-01-25 19:39:25','2020-01-22 12:44:14','CR000004','1'),
	('SHS000246','S000123','2020-01-23 16:27:33','Draft','SC000016','CDHS000062','2020-01-18 06:17:17','2020-01-17 02:40:36','CR000004','1'),
	('SHS000247','S000124','2020-01-05 18:54:33','Draft','SC000016','CDHS000062','2020-01-19 06:53:02','2020-01-18 08:04:43','CR000004','1'),
	('SHS000248','S000124','2020-01-15 20:18:11','Draft','SC000016','CDHS000062','2020-01-22 09:30:23','2020-01-06 22:51:27','CR000004','1'),
	('SHS000249','S000125','2020-01-21 21:14:26','Draft','SC000016','CDHS000063','2020-01-10 22:00:41','2020-01-26 14:29:22','CR000004','1'),
	('SHS000250','S000125','2020-01-20 22:07:24','Draft','SC000016','CDHS000063','2020-01-13 13:12:58','2020-01-25 17:38:53','CR000004','1'),
	('SHS000251','S000126','2020-01-24 01:51:25','Draft','SC000016','CDHS000063','2020-01-23 16:01:08','2020-01-27 03:28:54','CR000004','1'),
	('SHS000252','S000126','2020-01-17 22:12:47','Draft','SC000016','CDHS000063','2020-01-20 04:02:18','2020-01-20 19:45:53','CR000004','1'),
	('SHS000253','S000127','2020-01-09 20:23:26','Draft','SC000016','CDHS000064','2020-01-21 01:08:10','2020-01-20 02:45:31','CR000004','1'),
	('SHS000254','S000127','2020-01-25 03:15:49','Draft','SC000016','CDHS000064','2020-01-25 10:08:33','2020-01-09 21:21:29','CR000004','1'),
	('SHS000255','S000128','2020-01-07 09:26:35','Draft','SC000016','CDHS000064','2020-01-15 12:39:06','2020-01-08 13:46:01','CR000004','1'),
	('SHS000256','S000128','2020-01-23 15:47:48','Draft','SC000016','CDHS000064','2020-01-23 04:55:38','2020-01-14 15:13:29','CR000004','1');

insert into student_daily_answer_data values
	('SDA000001','SHS000001','DSQ000001','A','2020-01-23 17:43:33','2020-01-10 12:15:46','CR000001','1'),
	('SDA000002','SHS000001','DSQ000001','B','2020-01-16 00:46:20','2020-01-24 18:45:59','CR000001','1'),
	('SDA000003','SHS000002','DSQ000001','A','2020-01-16 16:04:39','2020-01-20 13:01:47','CR000001','1'),
	('SDA000004','SHS000002','DSQ000001','B','2020-01-18 04:25:22','2020-01-22 18:44:31','CR000001','1'),
	('SDA000005','SHS000003','DSQ000002','A','2020-01-19 15:30:04','2020-01-20 02:46:50','CR000001','1'),
	('SDA000006','SHS000003','DSQ000002','B','2020-01-19 22:14:01','2020-01-07 02:52:32','CR000001','1'),
	('SDA000007','SHS000004','DSQ000002','A','2020-01-15 16:52:57','2020-01-06 08:15:06','CR000001','1'),
	('SDA000008','SHS000004','DSQ000002','B','2020-01-09 04:23:03','2020-01-27 07:30:38','CR000001','1'),
	('SDA000009','SHS000005','DSQ000003','A','2020-01-16 21:45:37','2020-01-10 12:27:44','CR000001','1'),
	('SDA000010','SHS000005','DSQ000003','B','2020-01-18 07:07:40','2020-01-07 07:27:40','CR000001','1'),
	('SDA000011','SHS000006','DSQ000003','A','2020-01-22 16:22:45','2020-01-22 22:27:32','CR000001','1'),
	('SDA000012','SHS000006','DSQ000003','B','2020-01-12 03:52:28','2020-01-10 08:23:38','CR000001','1'),
	('SDA000013','SHS000007','DSQ000004','A','2020-01-10 04:21:41','2020-01-09 07:32:04','CR000001','1'),
	('SDA000014','SHS000007','DSQ000004','B','2020-01-20 10:04:46','2020-01-08 19:44:16','CR000001','1'),
	('SDA000015','SHS000008','DSQ000004','A','2020-01-20 08:41:08','2020-01-20 20:57:31','CR000001','1'),
	('SDA000016','SHS000008','DSQ000004','B','2020-01-23 17:00:53','2020-01-23 04:41:51','CR000001','1'),
	('SDA000017','SHS000009','DSQ000005','A','2020-01-25 17:35:17','2020-01-13 14:44:52','CR000001','1'),
	('SDA000018','SHS000009','DSQ000005','B','2020-01-13 02:37:15','2020-01-21 01:45:37','CR000001','1'),
	('SDA000019','SHS000010','DSQ000005','A','2020-01-21 23:07:52','2020-01-08 04:19:26','CR000001','1'),
	('SDA000020','SHS000010','DSQ000005','B','2020-01-24 08:35:23','2020-01-24 17:06:36','CR000001','1'),
	('SDA000021','SHS000011','DSQ000006','A','2020-01-25 21:57:02','2020-01-26 06:03:06','CR000001','1'),
	('SDA000022','SHS000011','DSQ000006','B','2020-01-15 19:12:35','2020-01-16 22:49:31','CR000001','1'),
	('SDA000023','SHS000012','DSQ000006','A','2020-01-15 03:04:45','2020-01-15 17:13:24','CR000001','1'),
	('SDA000024','SHS000012','DSQ000006','B','2020-01-14 10:53:42','2020-01-09 08:49:00','CR000001','1'),
	('SDA000025','SHS000013','DSQ000007','A','2020-01-16 07:21:44','2020-01-09 04:38:29','CR000001','1'),
	('SDA000026','SHS000013','DSQ000007','B','2020-01-26 21:45:18','2020-01-20 10:22:08','CR000001','1'),
	('SDA000027','SHS000014','DSQ000007','A','2020-01-17 04:24:39','2020-01-27 00:58:46','CR000001','1'),
	('SDA000028','SHS000014','DSQ000007','B','2020-01-20 09:12:40','2020-01-26 17:28:55','CR000001','1'),
	('SDA000029','SHS000015','DSQ000008','A','2020-01-20 21:54:46','2020-01-14 07:56:28','CR000001','1'),
	('SDA000030','SHS000015','DSQ000008','B','2020-01-20 23:43:56','2020-01-14 12:33:50','CR000001','1'),
	('SDA000031','SHS000016','DSQ000008','A','2020-01-19 09:35:51','2020-01-26 03:06:30','CR000001','1'),
	('SDA000032','SHS000016','DSQ000008','B','2020-01-14 10:40:49','2020-01-06 06:36:42','CR000001','1'),
	('SDA000033','SHS000017','DSQ000009','A','2020-01-12 10:16:22','2020-01-12 16:03:53','CR000001','1'),
	('SDA000034','SHS000017','DSQ000009','B','2020-01-08 11:09:51','2020-01-24 02:49:12','CR000001','1'),
	('SDA000035','SHS000018','DSQ000009','A','2020-01-19 11:05:26','2020-01-15 06:05:42','CR000001','1'),
	('SDA000036','SHS000018','DSQ000009','B','2020-01-25 14:45:45','2020-01-07 15:23:34','CR000001','1'),
	('SDA000037','SHS000019','DSQ000010','A','2020-01-21 10:45:04','2020-01-21 12:07:00','CR000001','1'),
	('SDA000038','SHS000019','DSQ000010','B','2020-01-21 17:56:54','2020-01-11 02:59:23','CR000001','1'),
	('SDA000039','SHS000020','DSQ000010','A','2020-01-18 09:09:28','2020-01-14 00:15:19','CR000001','1'),
	('SDA000040','SHS000020','DSQ000010','B','2020-01-23 03:39:01','2020-01-10 19:07:00','CR000001','1'),
	('SDA000041','SHS000021','DSQ000011','A','2020-01-18 21:02:21','2020-01-17 18:28:52','CR000001','1'),
	('SDA000042','SHS000021','DSQ000011','B','2020-01-06 16:24:20','2020-01-13 03:05:04','CR000001','1'),
	('SDA000043','SHS000022','DSQ000011','A','2020-01-16 19:53:29','2020-01-12 05:44:25','CR000001','1'),
	('SDA000044','SHS000022','DSQ000011','B','2020-01-14 12:49:17','2020-01-08 15:41:11','CR000001','1'),
	('SDA000045','SHS000023','DSQ000012','A','2020-01-25 08:28:04','2020-01-07 04:39:12','CR000001','1'),
	('SDA000046','SHS000023','DSQ000012','B','2020-01-19 17:43:40','2020-01-20 13:54:02','CR000001','1'),
	('SDA000047','SHS000024','DSQ000012','A','2020-01-07 13:19:06','2020-01-08 07:20:04','CR000001','1'),
	('SDA000048','SHS000024','DSQ000012','B','2020-01-16 07:42:19','2020-01-09 08:18:52','CR000001','1'),
	('SDA000049','SHS000025','DSQ000013','A','2020-01-09 02:24:11','2020-01-11 04:46:01','CR000001','1'),
	('SDA000050','SHS000025','DSQ000013','B','2020-01-19 22:48:32','2020-01-12 19:33:45','CR000001','1'),
	('SDA000051','SHS000026','DSQ000013','A','2020-01-09 20:08:27','2020-01-06 21:54:02','CR000001','1'),
	('SDA000052','SHS000026','DSQ000013','B','2020-01-21 21:26:34','2020-01-20 19:30:43','CR000001','1'),
	('SDA000053','SHS000027','DSQ000014','A','2020-01-15 01:48:45','2020-01-23 15:10:17','CR000001','1'),
	('SDA000054','SHS000027','DSQ000014','B','2020-01-17 10:31:47','2020-01-09 06:47:00','CR000001','1'),
	('SDA000055','SHS000028','DSQ000014','A','2020-01-22 04:14:06','2020-01-09 08:55:31','CR000001','1'),
	('SDA000056','SHS000028','DSQ000014','B','2020-01-07 21:43:01','2020-01-08 04:28:18','CR000001','1'),
	('SDA000057','SHS000029','DSQ000015','A','2020-01-10 04:01:38','2020-01-16 19:24:32','CR000001','1'),
	('SDA000058','SHS000029','DSQ000015','B','2020-01-09 20:22:40','2020-01-19 16:38:55','CR000001','1'),
	('SDA000059','SHS000030','DSQ000015','A','2020-01-15 19:02:01','2020-01-12 20:25:10','CR000001','1'),
	('SDA000060','SHS000030','DSQ000015','B','2020-01-26 18:02:02','2020-01-17 01:45:02','CR000001','1'),
	('SDA000061','SHS000031','DSQ000016','A','2020-01-07 18:31:47','2020-01-16 02:32:56','CR000001','1'),
	('SDA000062','SHS000031','DSQ000016','B','2020-01-19 13:40:59','2020-01-23 05:43:18','CR000001','1'),
	('SDA000063','SHS000032','DSQ000016','A','2020-01-09 10:55:33','2020-01-18 16:13:22','CR000001','1'),
	('SDA000064','SHS000032','DSQ000016','B','2020-01-21 17:49:50','2020-01-26 04:09:11','CR000001','1'),
	('SDA000065','SHS000033','DSQ000017','A','2020-01-11 05:47:42','2020-01-26 22:37:50','CR000001','1'),
	('SDA000066','SHS000033','DSQ000017','B','2020-01-24 10:44:32','2020-01-24 20:16:47','CR000001','1'),
	('SDA000067','SHS000034','DSQ000017','A','2020-01-08 16:59:45','2020-01-11 10:58:14','CR000001','1'),
	('SDA000068','SHS000034','DSQ000017','B','2020-01-14 12:47:12','2020-01-17 23:19:02','CR000001','1'),
	('SDA000069','SHS000035','DSQ000018','A','2020-01-21 23:48:44','2020-01-19 12:45:49','CR000001','1'),
	('SDA000070','SHS000035','DSQ000018','B','2020-01-18 04:30:55','2020-01-22 22:52:48','CR000001','1'),
	('SDA000071','SHS000036','DSQ000018','A','2020-01-14 06:14:07','2020-01-07 15:58:20','CR000001','1'),
	('SDA000072','SHS000036','DSQ000018','B','2020-01-05 20:07:23','2020-01-17 02:30:43','CR000001','1'),
	('SDA000073','SHS000037','DSQ000019','A','2020-01-13 02:42:56','2020-01-07 02:02:54','CR000001','1'),
	('SDA000074','SHS000037','DSQ000019','B','2020-01-26 00:34:01','2020-01-13 10:39:29','CR000001','1'),
	('SDA000075','SHS000038','DSQ000019','A','2020-01-24 23:57:06','2020-01-14 23:27:22','CR000001','1'),
	('SDA000076','SHS000038','DSQ000019','B','2020-01-09 01:27:33','2020-01-26 16:20:28','CR000001','1'),
	('SDA000077','SHS000039','DSQ000020','A','2020-01-15 16:57:17','2020-01-17 17:15:35','CR000001','1'),
	('SDA000078','SHS000039','DSQ000020','B','2020-01-16 04:20:14','2020-01-06 12:58:01','CR000001','1'),
	('SDA000079','SHS000040','DSQ000020','A','2020-01-10 09:42:14','2020-01-11 23:14:01','CR000001','1'),
	('SDA000080','SHS000040','DSQ000020','B','2020-01-06 18:48:42','2020-01-17 07:27:45','CR000001','1'),
	('SDA000081','SHS000041','DSQ000021','A','2020-01-10 18:13:43','2020-01-06 16:15:12','CR000001','1'),
	('SDA000082','SHS000041','DSQ000021','B','2020-01-25 11:24:44','2020-01-17 01:45:11','CR000001','1'),
	('SDA000083','SHS000042','DSQ000021','A','2020-01-07 19:17:36','2020-01-19 10:02:06','CR000001','1'),
	('SDA000084','SHS000042','DSQ000021','B','2020-01-19 19:30:42','2020-01-19 13:49:32','CR000001','1'),
	('SDA000085','SHS000043','DSQ000022','A','2020-01-25 15:00:51','2020-01-23 18:38:45','CR000001','1'),
	('SDA000086','SHS000043','DSQ000022','B','2020-01-12 12:40:48','2020-01-15 21:04:36','CR000001','1'),
	('SDA000087','SHS000044','DSQ000022','A','2020-01-23 13:32:16','2020-01-11 11:10:18','CR000001','1'),
	('SDA000088','SHS000044','DSQ000022','B','2020-01-23 23:37:00','2020-01-25 17:43:32','CR000001','1'),
	('SDA000089','SHS000045','DSQ000023','A','2020-01-07 11:07:00','2020-01-14 17:55:20','CR000001','1'),
	('SDA000090','SHS000045','DSQ000023','B','2020-01-08 18:26:44','2020-01-10 18:22:51','CR000001','1'),
	('SDA000091','SHS000046','DSQ000023','A','2020-01-14 03:09:18','2020-01-11 14:46:38','CR000001','1'),
	('SDA000092','SHS000046','DSQ000023','B','2020-01-24 20:23:50','2020-01-25 04:11:05','CR000001','1'),
	('SDA000093','SHS000047','DSQ000024','A','2020-01-15 01:28:12','2020-01-20 07:19:15','CR000001','1'),
	('SDA000094','SHS000047','DSQ000024','B','2020-01-25 23:23:03','2020-01-14 21:06:25','CR000001','1'),
	('SDA000095','SHS000048','DSQ000024','A','2020-01-09 06:29:12','2020-01-23 07:04:38','CR000001','1'),
	('SDA000096','SHS000048','DSQ000024','B','2020-01-14 12:40:38','2020-01-21 16:21:21','CR000001','1'),
	('SDA000097','SHS000049','DSQ000025','A','2020-01-05 18:00:58','2020-01-09 07:15:24','CR000001','1'),
	('SDA000098','SHS000049','DSQ000025','B','2020-01-16 18:24:39','2020-01-16 03:15:52','CR000001','1'),
	('SDA000099','SHS000050','DSQ000025','A','2020-01-07 08:11:20','2020-01-19 12:08:54','CR000001','1'),
	('SDA000100','SHS000050','DSQ000025','B','2020-01-22 06:24:10','2020-01-17 16:39:31','CR000001','1'),
	('SDA000101','SHS000051','DSQ000026','A','2020-01-10 16:17:21','2020-01-11 05:25:31','CR000001','1'),
	('SDA000102','SHS000051','DSQ000026','B','2020-01-14 18:08:40','2020-01-23 02:45:27','CR000001','1'),
	('SDA000103','SHS000052','DSQ000026','A','2020-01-22 12:49:07','2020-01-24 12:36:45','CR000001','1'),
	('SDA000104','SHS000052','DSQ000026','B','2020-01-15 20:37:16','2020-01-21 14:32:46','CR000001','1'),
	('SDA000105','SHS000053','DSQ000027','A','2020-01-07 23:56:06','2020-01-14 08:09:03','CR000001','1'),
	('SDA000106','SHS000053','DSQ000027','B','2020-01-17 02:26:45','2020-01-27 08:04:05','CR000001','1'),
	('SDA000107','SHS000054','DSQ000027','A','2020-01-12 10:38:38','2020-01-19 10:43:17','CR000001','1'),
	('SDA000108','SHS000054','DSQ000027','B','2020-01-07 19:38:43','2020-01-08 11:36:27','CR000001','1'),
	('SDA000109','SHS000055','DSQ000028','A','2020-01-13 11:47:38','2020-01-21 00:07:26','CR000001','1'),
	('SDA000110','SHS000055','DSQ000028','B','2020-01-09 22:42:16','2020-01-12 01:06:16','CR000001','1'),
	('SDA000111','SHS000056','DSQ000028','A','2020-01-11 03:36:14','2020-01-22 01:29:20','CR000001','1'),
	('SDA000112','SHS000056','DSQ000028','B','2020-01-24 21:23:07','2020-01-10 19:42:36','CR000001','1'),
	('SDA000113','SHS000057','DSQ000029','A','2020-01-15 17:58:19','2020-01-22 02:41:11','CR000001','1'),
	('SDA000114','SHS000057','DSQ000029','B','2020-01-17 00:33:22','2020-01-11 16:48:44','CR000001','1'),
	('SDA000115','SHS000058','DSQ000029','A','2020-01-10 01:09:01','2020-01-18 03:18:59','CR000001','1'),
	('SDA000116','SHS000058','DSQ000029','B','2020-01-21 01:37:13','2020-01-17 18:28:56','CR000001','1'),
	('SDA000117','SHS000059','DSQ000030','A','2020-01-23 13:08:00','2020-01-08 09:48:53','CR000001','1'),
	('SDA000118','SHS000059','DSQ000030','B','2020-01-13 10:54:25','2020-01-12 19:33:19','CR000001','1'),
	('SDA000119','SHS000060','DSQ000030','A','2020-01-10 03:05:48','2020-01-17 22:10:15','CR000001','1'),
	('SDA000120','SHS000060','DSQ000030','B','2020-01-16 19:47:35','2020-01-23 17:50:31','CR000001','1'),
	('SDA000121','SHS000061','DSQ000031','A','2020-01-16 19:26:17','2020-01-10 14:45:19','CR000001','1'),
	('SDA000122','SHS000061','DSQ000031','B','2020-01-12 02:08:30','2020-01-17 22:04:02','CR000001','1'),
	('SDA000123','SHS000062','DSQ000031','A','2020-01-17 22:00:12','2020-01-13 11:58:03','CR000001','1'),
	('SDA000124','SHS000062','DSQ000031','B','2020-01-08 04:54:13','2020-01-25 06:40:26','CR000001','1'),
	('SDA000125','SHS000063','DSQ000032','A','2020-01-14 01:21:22','2020-01-12 08:27:57','CR000001','1'),
	('SDA000126','SHS000063','DSQ000032','B','2020-01-22 00:59:48','2020-01-08 12:58:44','CR000001','1'),
	('SDA000127','SHS000064','DSQ000032','A','2020-01-25 12:13:29','2020-01-12 06:00:22','CR000001','1'),
	('SDA000128','SHS000064','DSQ000032','B','2020-01-27 00:16:50','2020-01-11 09:45:22','CR000001','1'),
	('SDA000129','SHS000065','DSQ000033','A','2020-01-14 20:22:59','2020-01-23 10:24:46','CR000002','1'),
	('SDA000130','SHS000065','DSQ000033','B','2020-01-22 12:58:27','2020-01-08 11:55:21','CR000002','1'),
	('SDA000131','SHS000066','DSQ000033','A','2020-01-20 16:09:11','2020-01-20 15:19:14','CR000002','1'),
	('SDA000132','SHS000066','DSQ000033','B','2020-01-16 04:36:23','2020-01-20 04:02:34','CR000002','1'),
	('SDA000133','SHS000067','DSQ000034','A','2020-01-19 05:05:19','2020-01-15 04:28:57','CR000002','1'),
	('SDA000134','SHS000067','DSQ000034','B','2020-01-13 02:10:30','2020-01-16 06:37:00','CR000002','1'),
	('SDA000135','SHS000068','DSQ000034','A','2020-01-12 16:46:40','2020-01-11 04:23:18','CR000002','1'),
	('SDA000136','SHS000068','DSQ000034','B','2020-01-22 02:46:46','2020-01-10 20:48:12','CR000002','1'),
	('SDA000137','SHS000069','DSQ000035','A','2020-01-24 20:55:26','2020-01-16 07:21:21','CR000002','1'),
	('SDA000138','SHS000069','DSQ000035','B','2020-01-09 07:25:51','2020-01-18 17:44:11','CR000002','1'),
	('SDA000139','SHS000070','DSQ000035','A','2020-01-21 09:53:49','2020-01-20 14:03:26','CR000002','1'),
	('SDA000140','SHS000070','DSQ000035','B','2020-01-06 11:35:00','2020-01-07 22:43:23','CR000002','1'),
	('SDA000141','SHS000071','DSQ000036','A','2020-01-18 03:36:56','2020-01-16 04:05:59','CR000002','1'),
	('SDA000142','SHS000071','DSQ000036','B','2020-01-12 14:12:37','2020-01-19 03:58:10','CR000002','1'),
	('SDA000143','SHS000072','DSQ000036','A','2020-01-25 23:54:10','2020-01-06 18:32:34','CR000002','1'),
	('SDA000144','SHS000072','DSQ000036','B','2020-01-09 12:33:04','2020-01-14 06:03:58','CR000002','1'),
	('SDA000145','SHS000073','DSQ000037','A','2020-01-23 14:34:37','2020-01-06 22:43:54','CR000002','1'),
	('SDA000146','SHS000073','DSQ000037','B','2020-01-09 02:46:48','2020-01-18 03:03:47','CR000002','1'),
	('SDA000147','SHS000074','DSQ000037','A','2020-01-07 04:04:26','2020-01-17 21:56:45','CR000002','1'),
	('SDA000148','SHS000074','DSQ000037','B','2020-01-21 22:59:36','2020-01-07 04:36:27','CR000002','1'),
	('SDA000149','SHS000075','DSQ000038','A','2020-01-20 04:57:44','2020-01-19 11:42:18','CR000002','1'),
	('SDA000150','SHS000075','DSQ000038','B','2020-01-15 09:17:53','2020-01-22 13:39:52','CR000002','1'),
	('SDA000151','SHS000076','DSQ000038','A','2020-01-24 15:17:57','2020-01-27 11:08:44','CR000002','1'),
	('SDA000152','SHS000076','DSQ000038','B','2020-01-23 15:34:28','2020-01-08 07:25:54','CR000002','1'),
	('SDA000153','SHS000077','DSQ000039','A','2020-01-19 01:35:33','2020-01-13 11:40:26','CR000002','1'),
	('SDA000154','SHS000077','DSQ000039','B','2020-01-17 19:07:03','2020-01-10 14:57:26','CR000002','1'),
	('SDA000155','SHS000078','DSQ000039','A','2020-01-14 09:21:48','2020-01-21 02:55:24','CR000002','1'),
	('SDA000156','SHS000078','DSQ000039','B','2020-01-17 05:48:49','2020-01-25 03:42:32','CR000002','1'),
	('SDA000157','SHS000079','DSQ000040','A','2020-01-12 00:10:55','2020-01-13 10:41:14','CR000002','1'),
	('SDA000158','SHS000079','DSQ000040','B','2020-01-09 12:34:39','2020-01-26 02:05:54','CR000002','1'),
	('SDA000159','SHS000080','DSQ000040','A','2020-01-25 05:31:13','2020-01-07 07:21:57','CR000002','1'),
	('SDA000160','SHS000080','DSQ000040','B','2020-01-21 23:53:11','2020-01-20 04:32:34','CR000002','1'),
	('SDA000161','SHS000081','DSQ000041','A','2020-01-09 07:29:34','2020-01-11 11:21:57','CR000002','1'),
	('SDA000162','SHS000081','DSQ000041','B','2020-01-27 00:33:40','2020-01-12 13:15:26','CR000002','1'),
	('SDA000163','SHS000082','DSQ000041','A','2020-01-20 15:41:09','2020-01-22 09:48:15','CR000002','1'),
	('SDA000164','SHS000082','DSQ000041','B','2020-01-20 08:06:29','2020-01-23 04:12:05','CR000002','1'),
	('SDA000165','SHS000083','DSQ000042','A','2020-01-18 06:14:43','2020-01-17 12:24:58','CR000002','1'),
	('SDA000166','SHS000083','DSQ000042','B','2020-01-06 04:05:56','2020-01-10 01:03:39','CR000002','1'),
	('SDA000167','SHS000084','DSQ000042','A','2020-01-20 04:58:28','2020-01-10 10:32:24','CR000002','1'),
	('SDA000168','SHS000084','DSQ000042','B','2020-01-08 12:04:34','2020-01-27 03:13:39','CR000002','1'),
	('SDA000169','SHS000085','DSQ000043','A','2020-01-16 14:24:49','2020-01-07 23:50:01','CR000002','1'),
	('SDA000170','SHS000085','DSQ000043','B','2020-01-06 08:29:35','2020-01-06 15:02:51','CR000002','1'),
	('SDA000171','SHS000086','DSQ000043','A','2020-01-24 00:06:31','2020-01-09 17:00:54','CR000002','1'),
	('SDA000172','SHS000086','DSQ000043','B','2020-01-23 08:02:51','2020-01-14 06:32:15','CR000002','1'),
	('SDA000173','SHS000087','DSQ000044','A','2020-01-24 09:08:00','2020-01-11 01:44:52','CR000002','1'),
	('SDA000174','SHS000087','DSQ000044','B','2020-01-20 08:14:42','2020-01-13 01:56:02','CR000002','1'),
	('SDA000175','SHS000088','DSQ000044','A','2020-01-23 16:01:57','2020-01-11 12:04:16','CR000002','1'),
	('SDA000176','SHS000088','DSQ000044','B','2020-01-22 20:31:57','2020-01-25 17:00:23','CR000002','1'),
	('SDA000177','SHS000089','DSQ000045','A','2020-01-10 17:38:58','2020-01-24 20:15:54','CR000002','1'),
	('SDA000178','SHS000089','DSQ000045','B','2020-01-16 18:27:33','2020-01-14 10:41:46','CR000002','1'),
	('SDA000179','SHS000090','DSQ000045','A','2020-01-14 05:39:09','2020-01-21 22:09:42','CR000002','1'),
	('SDA000180','SHS000090','DSQ000045','B','2020-01-07 08:16:56','2020-01-19 03:38:46','CR000002','1'),
	('SDA000181','SHS000091','DSQ000046','A','2020-01-12 19:32:00','2020-01-17 13:03:31','CR000002','1'),
	('SDA000182','SHS000091','DSQ000046','B','2020-01-13 09:24:22','2020-01-27 02:28:15','CR000002','1'),
	('SDA000183','SHS000092','DSQ000046','A','2020-01-14 15:26:13','2020-01-09 10:21:35','CR000002','1'),
	('SDA000184','SHS000092','DSQ000046','B','2020-01-06 16:14:39','2020-01-08 17:16:42','CR000002','1'),
	('SDA000185','SHS000093','DSQ000047','A','2020-01-17 06:59:55','2020-01-23 15:15:38','CR000002','1'),
	('SDA000186','SHS000093','DSQ000047','B','2020-01-06 17:55:09','2020-01-13 17:28:30','CR000002','1'),
	('SDA000187','SHS000094','DSQ000047','A','2020-01-22 06:36:09','2020-01-17 09:37:18','CR000002','1'),
	('SDA000188','SHS000094','DSQ000047','B','2020-01-25 00:04:13','2020-01-22 23:36:21','CR000002','1'),
	('SDA000189','SHS000095','DSQ000048','A','2020-01-14 11:56:15','2020-01-26 11:36:34','CR000002','1'),
	('SDA000190','SHS000095','DSQ000048','B','2020-01-27 04:42:23','2020-01-27 03:21:12','CR000002','1'),
	('SDA000191','SHS000096','DSQ000048','A','2020-01-07 17:48:47','2020-01-25 15:57:32','CR000002','1'),
	('SDA000192','SHS000096','DSQ000048','B','2020-01-17 11:33:06','2020-01-14 02:11:03','CR000002','1'),
	('SDA000193','SHS000097','DSQ000049','A','2020-01-27 10:54:58','2020-01-24 15:24:25','CR000002','1'),
	('SDA000194','SHS000097','DSQ000049','B','2020-01-24 12:27:05','2020-01-17 19:57:10','CR000002','1'),
	('SDA000195','SHS000098','DSQ000049','A','2020-01-17 17:31:21','2020-01-26 12:33:53','CR000002','1'),
	('SDA000196','SHS000098','DSQ000049','B','2020-01-22 23:57:25','2020-01-09 14:23:54','CR000002','1'),
	('SDA000197','SHS000099','DSQ000050','A','2020-01-11 05:08:44','2020-01-27 06:14:04','CR000002','1'),
	('SDA000198','SHS000099','DSQ000050','B','2020-01-17 07:47:10','2020-01-15 15:43:33','CR000002','1'),
	('SDA000199','SHS000100','DSQ000050','A','2020-01-21 05:29:10','2020-01-19 15:21:38','CR000002','1'),
	('SDA000200','SHS000100','DSQ000050','B','2020-01-12 22:03:22','2020-01-15 17:20:52','CR000002','1'),
	('SDA000201','SHS000101','DSQ000051','A','2020-01-27 00:40:01','2020-01-14 06:35:05','CR000002','1'),
	('SDA000202','SHS000101','DSQ000051','B','2020-01-25 06:24:34','2020-01-21 21:34:15','CR000002','1'),
	('SDA000203','SHS000102','DSQ000051','A','2020-01-15 22:22:18','2020-01-08 01:00:08','CR000002','1'),
	('SDA000204','SHS000102','DSQ000051','B','2020-01-06 21:00:23','2020-01-18 08:44:22','CR000002','1'),
	('SDA000205','SHS000103','DSQ000052','A','2020-01-06 09:22:12','2020-01-12 00:57:40','CR000002','1'),
	('SDA000206','SHS000103','DSQ000052','B','2020-01-09 07:14:01','2020-01-26 13:42:29','CR000002','1'),
	('SDA000207','SHS000104','DSQ000052','A','2020-01-19 04:11:52','2020-01-17 05:00:33','CR000002','1'),
	('SDA000208','SHS000104','DSQ000052','B','2020-01-26 13:40:36','2020-01-17 16:53:51','CR000002','1'),
	('SDA000209','SHS000105','DSQ000053','A','2020-01-24 03:40:54','2020-01-24 09:59:54','CR000002','1'),
	('SDA000210','SHS000105','DSQ000053','B','2020-01-06 21:37:59','2020-01-23 16:35:41','CR000002','1'),
	('SDA000211','SHS000106','DSQ000053','A','2020-01-11 05:43:29','2020-01-11 09:51:53','CR000002','1'),
	('SDA000212','SHS000106','DSQ000053','B','2020-01-11 13:23:26','2020-01-15 15:47:15','CR000002','1'),
	('SDA000213','SHS000107','DSQ000054','A','2020-01-12 11:32:39','2020-01-24 17:09:21','CR000002','1'),
	('SDA000214','SHS000107','DSQ000054','B','2020-01-19 12:52:29','2020-01-12 08:17:11','CR000002','1'),
	('SDA000215','SHS000108','DSQ000054','A','2020-01-13 20:19:49','2020-01-24 22:20:30','CR000002','1'),
	('SDA000216','SHS000108','DSQ000054','B','2020-01-23 12:51:54','2020-01-12 09:57:46','CR000002','1'),
	('SDA000217','SHS000109','DSQ000055','A','2020-01-21 00:07:05','2020-01-22 06:32:22','CR000002','1'),
	('SDA000218','SHS000109','DSQ000055','B','2020-01-25 11:24:40','2020-01-20 13:14:35','CR000002','1'),
	('SDA000219','SHS000110','DSQ000055','A','2020-01-10 08:05:43','2020-01-20 23:30:27','CR000002','1'),
	('SDA000220','SHS000110','DSQ000055','B','2020-01-19 22:43:32','2020-01-08 00:55:52','CR000002','1'),
	('SDA000221','SHS000111','DSQ000056','A','2020-01-09 16:50:04','2020-01-25 19:24:04','CR000002','1'),
	('SDA000222','SHS000111','DSQ000056','B','2020-01-14 15:54:35','2020-01-19 06:11:02','CR000002','1'),
	('SDA000223','SHS000112','DSQ000056','A','2020-01-10 18:25:45','2020-01-25 21:27:31','CR000002','1'),
	('SDA000224','SHS000112','DSQ000056','B','2020-01-16 04:22:11','2020-01-10 13:51:04','CR000002','1'),
	('SDA000225','SHS000113','DSQ000057','A','2020-01-06 02:13:25','2020-01-15 21:04:29','CR000002','1'),
	('SDA000226','SHS000113','DSQ000057','B','2020-01-13 14:05:57','2020-01-15 08:23:52','CR000002','1'),
	('SDA000227','SHS000114','DSQ000057','A','2020-01-23 04:57:43','2020-01-18 13:50:38','CR000002','1'),
	('SDA000228','SHS000114','DSQ000057','B','2020-01-15 17:45:58','2020-01-13 19:39:16','CR000002','1'),
	('SDA000229','SHS000115','DSQ000058','A','2020-01-18 10:45:14','2020-01-22 07:53:47','CR000002','1'),
	('SDA000230','SHS000115','DSQ000058','B','2020-01-07 20:06:35','2020-01-26 18:06:22','CR000002','1'),
	('SDA000231','SHS000116','DSQ000058','A','2020-01-26 09:49:57','2020-01-24 05:01:16','CR000002','1'),
	('SDA000232','SHS000116','DSQ000058','B','2020-01-12 23:21:23','2020-01-22 19:06:09','CR000002','1'),
	('SDA000233','SHS000117','DSQ000059','A','2020-01-21 22:30:03','2020-01-13 22:15:49','CR000002','1'),
	('SDA000234','SHS000117','DSQ000059','B','2020-01-25 21:59:17','2020-01-20 11:05:19','CR000002','1'),
	('SDA000235','SHS000118','DSQ000059','A','2020-01-22 16:17:55','2020-01-12 15:42:28','CR000002','1'),
	('SDA000236','SHS000118','DSQ000059','B','2020-01-06 05:27:00','2020-01-13 12:16:47','CR000002','1'),
	('SDA000237','SHS000119','DSQ000060','A','2020-01-11 15:52:53','2020-01-13 14:35:25','CR000002','1'),
	('SDA000238','SHS000119','DSQ000060','B','2020-01-18 06:03:38','2020-01-09 07:10:03','CR000002','1'),
	('SDA000239','SHS000120','DSQ000060','A','2020-01-14 06:24:04','2020-01-17 17:34:33','CR000002','1'),
	('SDA000240','SHS000120','DSQ000060','B','2020-01-08 11:04:44','2020-01-25 10:49:17','CR000002','1'),
	('SDA000241','SHS000121','DSQ000061','A','2020-01-12 11:04:41','2020-01-26 01:30:12','CR000002','1'),
	('SDA000242','SHS000121','DSQ000061','B','2020-01-24 22:37:38','2020-01-26 22:21:33','CR000002','1'),
	('SDA000243','SHS000122','DSQ000061','A','2020-01-14 23:00:21','2020-01-23 00:38:32','CR000002','1'),
	('SDA000244','SHS000122','DSQ000061','B','2020-01-26 00:05:52','2020-01-21 11:59:22','CR000002','1'),
	('SDA000245','SHS000123','DSQ000062','A','2020-01-06 13:58:54','2020-01-26 21:02:27','CR000002','1'),
	('SDA000246','SHS000123','DSQ000062','B','2020-01-14 07:15:47','2020-01-12 16:17:39','CR000002','1'),
	('SDA000247','SHS000124','DSQ000062','A','2020-01-16 20:33:14','2020-01-19 11:06:40','CR000002','1'),
	('SDA000248','SHS000124','DSQ000062','B','2020-01-25 08:54:18','2020-01-09 11:29:15','CR000002','1'),
	('SDA000249','SHS000125','DSQ000063','A','2020-01-08 13:04:53','2020-01-26 23:24:07','CR000002','1'),
	('SDA000250','SHS000125','DSQ000063','B','2020-01-06 05:00:05','2020-01-15 11:06:29','CR000002','1'),
	('SDA000251','SHS000126','DSQ000063','A','2020-01-13 01:02:53','2020-01-10 06:19:07','CR000002','1'),
	('SDA000252','SHS000126','DSQ000063','B','2020-01-23 01:33:46','2020-01-27 01:43:36','CR000002','1'),
	('SDA000253','SHS000127','DSQ000064','A','2020-01-18 08:19:46','2020-01-12 15:12:04','CR000002','1'),
	('SDA000254','SHS000127','DSQ000064','B','2020-01-26 13:41:10','2020-01-14 19:48:25','CR000002','1'),
	('SDA000255','SHS000128','DSQ000064','A','2020-01-07 02:24:13','2020-01-07 04:02:11','CR000002','1'),
	('SDA000256','SHS000128','DSQ000064','B','2020-01-22 15:05:41','2020-01-13 12:02:38','CR000002','1'),
	('SDA000257','SHS000129','DSQ000065','A','2020-01-05 16:48:35','2020-01-26 16:10:52','CR000003','1'),
	('SDA000258','SHS000129','DSQ000065','B','2020-01-14 03:45:32','2020-01-20 04:42:39','CR000003','1'),
	('SDA000259','SHS000130','DSQ000065','A','2020-01-11 14:29:29','2020-01-23 11:34:56','CR000003','1'),
	('SDA000260','SHS000130','DSQ000065','B','2020-01-10 12:20:17','2020-01-12 21:10:17','CR000003','1'),
	('SDA000261','SHS000131','DSQ000066','A','2020-01-17 18:38:11','2020-01-26 20:20:12','CR000003','1'),
	('SDA000262','SHS000131','DSQ000066','B','2020-01-25 11:10:18','2020-01-20 01:58:46','CR000003','1'),
	('SDA000263','SHS000132','DSQ000066','A','2020-01-21 07:59:57','2020-01-09 06:47:41','CR000003','1'),
	('SDA000264','SHS000132','DSQ000066','B','2020-01-08 19:07:54','2020-01-20 20:27:56','CR000003','1'),
	('SDA000265','SHS000133','DSQ000067','A','2020-01-26 14:42:51','2020-01-16 23:12:17','CR000003','1'),
	('SDA000266','SHS000133','DSQ000067','B','2020-01-15 17:05:36','2020-01-15 07:37:06','CR000003','1'),
	('SDA000267','SHS000134','DSQ000067','A','2020-01-18 01:26:45','2020-01-08 02:19:34','CR000003','1'),
	('SDA000268','SHS000134','DSQ000067','B','2020-01-13 01:12:48','2020-01-17 23:23:49','CR000003','1'),
	('SDA000269','SHS000135','DSQ000068','A','2020-01-13 19:22:57','2020-01-10 03:40:08','CR000003','1'),
	('SDA000270','SHS000135','DSQ000068','B','2020-01-12 11:29:38','2020-01-25 14:10:37','CR000003','1'),
	('SDA000271','SHS000136','DSQ000068','A','2020-01-25 11:52:03','2020-01-09 12:53:59','CR000003','1'),
	('SDA000272','SHS000136','DSQ000068','B','2020-01-21 10:48:29','2020-01-16 03:24:08','CR000003','1'),
	('SDA000273','SHS000137','DSQ000069','A','2020-01-07 04:46:47','2020-01-24 02:26:16','CR000003','1'),
	('SDA000274','SHS000137','DSQ000069','B','2020-01-14 20:16:52','2020-01-07 17:55:59','CR000003','1'),
	('SDA000275','SHS000138','DSQ000069','A','2020-01-19 10:06:40','2020-01-07 03:47:40','CR000003','1'),
	('SDA000276','SHS000138','DSQ000069','B','2020-01-16 06:41:07','2020-01-15 10:24:17','CR000003','1'),
	('SDA000277','SHS000139','DSQ000070','A','2020-01-08 17:08:53','2020-01-07 04:20:44','CR000003','1'),
	('SDA000278','SHS000139','DSQ000070','B','2020-01-07 11:52:15','2020-01-24 00:34:22','CR000003','1'),
	('SDA000279','SHS000140','DSQ000070','A','2020-01-07 05:51:40','2020-01-09 18:12:06','CR000003','1'),
	('SDA000280','SHS000140','DSQ000070','B','2020-01-27 06:03:41','2020-01-15 05:18:10','CR000003','1'),
	('SDA000281','SHS000141','DSQ000071','A','2020-01-20 19:54:39','2020-01-15 17:25:51','CR000003','1'),
	('SDA000282','SHS000141','DSQ000071','B','2020-01-24 20:52:03','2020-01-18 08:45:56','CR000003','1'),
	('SDA000283','SHS000142','DSQ000071','A','2020-01-27 05:19:27','2020-01-22 15:42:44','CR000003','1'),
	('SDA000284','SHS000142','DSQ000071','B','2020-01-17 09:19:28','2020-01-09 19:08:06','CR000003','1'),
	('SDA000285','SHS000143','DSQ000072','A','2020-01-12 06:33:05','2020-01-26 16:20:35','CR000003','1'),
	('SDA000286','SHS000143','DSQ000072','B','2020-01-08 19:31:49','2020-01-09 20:02:29','CR000003','1'),
	('SDA000287','SHS000144','DSQ000072','A','2020-01-20 06:18:28','2020-01-20 21:35:02','CR000003','1'),
	('SDA000288','SHS000144','DSQ000072','B','2020-01-16 11:33:59','2020-01-13 19:00:58','CR000003','1'),
	('SDA000289','SHS000145','DSQ000073','A','2020-01-19 11:19:05','2020-01-23 12:14:08','CR000003','1'),
	('SDA000290','SHS000145','DSQ000073','B','2020-01-18 22:18:35','2020-01-06 12:22:01','CR000003','1'),
	('SDA000291','SHS000146','DSQ000073','A','2020-01-14 10:00:39','2020-01-07 14:38:40','CR000003','1'),
	('SDA000292','SHS000146','DSQ000073','B','2020-01-12 23:05:24','2020-01-14 19:09:30','CR000003','1'),
	('SDA000293','SHS000147','DSQ000074','A','2020-01-16 07:35:14','2020-01-13 06:41:33','CR000003','1'),
	('SDA000294','SHS000147','DSQ000074','B','2020-01-24 10:31:34','2020-01-21 20:37:53','CR000003','1'),
	('SDA000295','SHS000148','DSQ000074','A','2020-01-16 11:27:50','2020-01-15 03:29:00','CR000003','1'),
	('SDA000296','SHS000148','DSQ000074','B','2020-01-18 06:04:43','2020-01-18 21:52:12','CR000003','1'),
	('SDA000297','SHS000149','DSQ000075','A','2020-01-22 11:54:37','2020-01-10 13:50:46','CR000003','1'),
	('SDA000298','SHS000149','DSQ000075','B','2020-01-22 22:04:17','2020-01-11 13:02:22','CR000003','1'),
	('SDA000299','SHS000150','DSQ000075','A','2020-01-17 23:32:24','2020-01-21 19:58:56','CR000003','1'),
	('SDA000300','SHS000150','DSQ000075','B','2020-01-22 10:48:04','2020-01-09 23:14:37','CR000003','1'),
	('SDA000301','SHS000151','DSQ000076','A','2020-01-06 07:58:18','2020-01-07 12:38:14','CR000003','1'),
	('SDA000302','SHS000151','DSQ000076','B','2020-01-10 08:48:43','2020-01-14 05:24:58','CR000003','1'),
	('SDA000303','SHS000152','DSQ000076','A','2020-01-24 16:04:11','2020-01-25 05:50:33','CR000003','1'),
	('SDA000304','SHS000152','DSQ000076','B','2020-01-25 11:43:53','2020-01-21 00:49:47','CR000003','1'),
	('SDA000305','SHS000153','DSQ000077','A','2020-01-19 12:05:23','2020-01-20 07:22:12','CR000003','1'),
	('SDA000306','SHS000153','DSQ000077','B','2020-01-11 09:22:14','2020-01-18 14:49:40','CR000003','1'),
	('SDA000307','SHS000154','DSQ000077','A','2020-01-23 22:58:56','2020-01-14 18:21:29','CR000003','1'),
	('SDA000308','SHS000154','DSQ000077','B','2020-01-26 20:57:41','2020-01-18 11:12:16','CR000003','1'),
	('SDA000309','SHS000155','DSQ000078','A','2020-01-21 01:17:07','2020-01-23 09:19:02','CR000003','1'),
	('SDA000310','SHS000155','DSQ000078','B','2020-01-11 13:09:51','2020-01-26 10:07:17','CR000003','1'),
	('SDA000311','SHS000156','DSQ000078','A','2020-01-07 12:12:33','2020-01-07 19:29:19','CR000003','1'),
	('SDA000312','SHS000156','DSQ000078','B','2020-01-15 18:53:10','2020-01-27 01:31:54','CR000003','1'),
	('SDA000313','SHS000157','DSQ000079','A','2020-01-14 05:08:51','2020-01-26 03:31:34','CR000003','1'),
	('SDA000314','SHS000157','DSQ000079','B','2020-01-11 14:10:47','2020-01-19 16:23:06','CR000003','1'),
	('SDA000315','SHS000158','DSQ000079','A','2020-01-18 05:16:10','2020-01-12 21:17:19','CR000003','1'),
	('SDA000316','SHS000158','DSQ000079','B','2020-01-08 21:32:07','2020-01-10 11:25:24','CR000003','1'),
	('SDA000317','SHS000159','DSQ000080','A','2020-01-08 04:31:28','2020-01-26 09:58:51','CR000003','1'),
	('SDA000318','SHS000159','DSQ000080','B','2020-01-12 17:57:34','2020-01-06 11:19:59','CR000003','1'),
	('SDA000319','SHS000160','DSQ000080','A','2020-01-21 04:04:20','2020-01-07 04:08:23','CR000003','1'),
	('SDA000320','SHS000160','DSQ000080','B','2020-01-13 10:32:08','2020-01-12 00:47:59','CR000003','1'),
	('SDA000321','SHS000161','DSQ000081','A','2020-01-23 06:40:00','2020-01-18 23:40:17','CR000003','1'),
	('SDA000322','SHS000161','DSQ000081','B','2020-01-10 11:52:53','2020-01-17 16:37:10','CR000003','1'),
	('SDA000323','SHS000162','DSQ000081','A','2020-01-16 18:39:34','2020-01-21 19:38:47','CR000003','1'),
	('SDA000324','SHS000162','DSQ000081','B','2020-01-17 15:04:37','2020-01-15 10:03:21','CR000003','1'),
	('SDA000325','SHS000163','DSQ000082','A','2020-01-12 20:15:10','2020-01-23 13:31:33','CR000003','1'),
	('SDA000326','SHS000163','DSQ000082','B','2020-01-13 23:24:22','2020-01-20 20:52:53','CR000003','1'),
	('SDA000327','SHS000164','DSQ000082','A','2020-01-09 13:49:12','2020-01-17 11:02:27','CR000003','1'),
	('SDA000328','SHS000164','DSQ000082','B','2020-01-06 10:39:39','2020-01-19 09:26:03','CR000003','1'),
	('SDA000329','SHS000165','DSQ000083','A','2020-01-19 01:07:26','2020-01-14 13:09:20','CR000003','1'),
	('SDA000330','SHS000165','DSQ000083','B','2020-01-26 03:58:28','2020-01-06 23:31:03','CR000003','1'),
	('SDA000331','SHS000166','DSQ000083','A','2020-01-20 21:30:08','2020-01-26 08:57:28','CR000003','1'),
	('SDA000332','SHS000166','DSQ000083','B','2020-01-06 06:48:52','2020-01-15 22:42:34','CR000003','1'),
	('SDA000333','SHS000167','DSQ000084','A','2020-01-08 18:57:20','2020-01-12 18:58:07','CR000003','1'),
	('SDA000334','SHS000167','DSQ000084','B','2020-01-12 22:01:39','2020-01-08 02:48:13','CR000003','1'),
	('SDA000335','SHS000168','DSQ000084','A','2020-01-10 17:21:12','2020-01-13 20:37:34','CR000003','1'),
	('SDA000336','SHS000168','DSQ000084','B','2020-01-14 04:08:24','2020-01-26 15:17:03','CR000003','1'),
	('SDA000337','SHS000169','DSQ000085','A','2020-01-24 08:53:49','2020-01-13 15:24:15','CR000003','1'),
	('SDA000338','SHS000169','DSQ000085','B','2020-01-25 21:38:04','2020-01-06 09:44:48','CR000003','1'),
	('SDA000339','SHS000170','DSQ000085','A','2020-01-17 11:06:42','2020-01-11 21:55:57','CR000003','1'),
	('SDA000340','SHS000170','DSQ000085','B','2020-01-23 02:05:09','2020-01-06 22:36:16','CR000003','1'),
	('SDA000341','SHS000171','DSQ000086','A','2020-01-18 14:48:00','2020-01-22 09:38:26','CR000003','1'),
	('SDA000342','SHS000171','DSQ000086','B','2020-01-14 20:11:57','2020-01-06 14:53:07','CR000003','1'),
	('SDA000343','SHS000172','DSQ000086','A','2020-01-19 12:47:05','2020-01-10 14:48:42','CR000003','1'),
	('SDA000344','SHS000172','DSQ000086','B','2020-01-10 00:36:41','2020-01-25 05:23:38','CR000003','1'),
	('SDA000345','SHS000173','DSQ000087','A','2020-01-19 22:52:32','2020-01-13 19:55:46','CR000003','1'),
	('SDA000346','SHS000173','DSQ000087','B','2020-01-08 17:19:41','2020-01-24 20:14:04','CR000003','1'),
	('SDA000347','SHS000174','DSQ000087','A','2020-01-13 15:56:12','2020-01-17 23:00:58','CR000003','1'),
	('SDA000348','SHS000174','DSQ000087','B','2020-01-21 17:31:40','2020-01-11 12:00:38','CR000003','1'),
	('SDA000349','SHS000175','DSQ000088','A','2020-01-08 03:11:42','2020-01-10 04:18:46','CR000003','1'),
	('SDA000350','SHS000175','DSQ000088','B','2020-01-15 04:44:17','2020-01-13 19:10:49','CR000003','1'),
	('SDA000351','SHS000176','DSQ000088','A','2020-01-21 21:12:49','2020-01-25 09:06:52','CR000003','1'),
	('SDA000352','SHS000176','DSQ000088','B','2020-01-16 15:29:23','2020-01-19 03:37:49','CR000003','1'),
	('SDA000353','SHS000177','DSQ000089','A','2020-01-25 15:04:23','2020-01-17 18:16:10','CR000003','1'),
	('SDA000354','SHS000177','DSQ000089','B','2020-01-22 05:40:42','2020-01-13 17:43:42','CR000003','1'),
	('SDA000355','SHS000178','DSQ000089','A','2020-01-07 09:50:21','2020-01-15 23:46:37','CR000003','1'),
	('SDA000356','SHS000178','DSQ000089','B','2020-01-25 01:37:08','2020-01-21 00:33:39','CR000003','1'),
	('SDA000357','SHS000179','DSQ000090','A','2020-01-25 20:33:23','2020-01-27 10:51:03','CR000003','1'),
	('SDA000358','SHS000179','DSQ000090','B','2020-01-09 16:32:52','2020-01-12 22:42:24','CR000003','1'),
	('SDA000359','SHS000180','DSQ000090','A','2020-01-13 16:12:10','2020-01-26 12:11:57','CR000003','1'),
	('SDA000360','SHS000180','DSQ000090','B','2020-01-12 07:44:05','2020-01-18 19:23:09','CR000003','1'),
	('SDA000361','SHS000181','DSQ000091','A','2020-01-16 02:26:40','2020-01-27 05:03:17','CR000003','1'),
	('SDA000362','SHS000181','DSQ000091','B','2020-01-15 08:23:08','2020-01-14 19:31:47','CR000003','1'),
	('SDA000363','SHS000182','DSQ000091','A','2020-01-13 23:16:23','2020-01-16 14:42:54','CR000003','1'),
	('SDA000364','SHS000182','DSQ000091','B','2020-01-17 20:58:26','2020-01-23 16:26:49','CR000003','1'),
	('SDA000365','SHS000183','DSQ000092','A','2020-01-07 05:52:32','2020-01-23 22:47:36','CR000003','1'),
	('SDA000366','SHS000183','DSQ000092','B','2020-01-20 20:30:44','2020-01-25 11:55:21','CR000003','1'),
	('SDA000367','SHS000184','DSQ000092','A','2020-01-15 20:45:42','2020-01-05 17:15:20','CR000003','1'),
	('SDA000368','SHS000184','DSQ000092','B','2020-01-09 22:46:23','2020-01-19 04:33:46','CR000003','1'),
	('SDA000369','SHS000185','DSQ000093','A','2020-01-10 14:42:11','2020-01-11 21:24:16','CR000003','1'),
	('SDA000370','SHS000185','DSQ000093','B','2020-01-19 13:11:59','2020-01-10 03:24:40','CR000003','1'),
	('SDA000371','SHS000186','DSQ000093','A','2020-01-06 10:58:02','2020-01-22 19:30:13','CR000003','1'),
	('SDA000372','SHS000186','DSQ000093','B','2020-01-17 15:11:39','2020-01-22 17:17:24','CR000003','1'),
	('SDA000373','SHS000187','DSQ000094','A','2020-01-21 02:53:21','2020-01-27 02:27:51','CR000003','1'),
	('SDA000374','SHS000187','DSQ000094','B','2020-01-20 16:50:50','2020-01-24 10:58:50','CR000003','1'),
	('SDA000375','SHS000188','DSQ000094','A','2020-01-08 20:13:41','2020-01-11 19:12:04','CR000003','1'),
	('SDA000376','SHS000188','DSQ000094','B','2020-01-12 20:58:25','2020-01-17 04:49:54','CR000003','1'),
	('SDA000377','SHS000189','DSQ000095','A','2020-01-18 22:56:47','2020-01-17 06:38:59','CR000003','1'),
	('SDA000378','SHS000189','DSQ000095','B','2020-01-12 17:44:46','2020-01-11 03:54:22','CR000003','1'),
	('SDA000379','SHS000190','DSQ000095','A','2020-01-22 00:30:36','2020-01-15 11:15:25','CR000003','1'),
	('SDA000380','SHS000190','DSQ000095','B','2020-01-05 19:04:04','2020-01-11 16:17:23','CR000003','1'),
	('SDA000381','SHS000191','DSQ000096','A','2020-01-05 15:15:15','2020-01-05 22:12:30','CR000003','1'),
	('SDA000382','SHS000191','DSQ000096','B','2020-01-23 03:54:07','2020-01-26 12:57:29','CR000003','1'),
	('SDA000383','SHS000192','DSQ000096','A','2020-01-16 01:51:29','2020-01-24 22:54:51','CR000003','1'),
	('SDA000384','SHS000192','DSQ000096','B','2020-01-11 21:40:36','2020-01-13 07:10:20','CR000003','1'),
	('SDA000385','SHS000193','DSQ000097','A','2020-01-08 03:04:44','2020-01-06 06:47:03','CR000004','1'),
	('SDA000386','SHS000193','DSQ000097','B','2020-01-24 01:00:19','2020-01-25 06:34:58','CR000004','1'),
	('SDA000387','SHS000194','DSQ000097','A','2020-01-06 16:38:25','2020-01-25 20:49:32','CR000004','1'),
	('SDA000388','SHS000194','DSQ000097','B','2020-01-15 03:43:29','2020-01-27 02:27:09','CR000004','1'),
	('SDA000389','SHS000195','DSQ000098','A','2020-01-12 03:07:28','2020-01-21 17:45:27','CR000004','1'),
	('SDA000390','SHS000195','DSQ000098','B','2020-01-06 21:36:54','2020-01-23 13:16:04','CR000004','1'),
	('SDA000391','SHS000196','DSQ000098','A','2020-01-19 11:54:51','2020-01-11 05:17:35','CR000004','1'),
	('SDA000392','SHS000196','DSQ000098','B','2020-01-12 11:58:25','2020-01-08 10:29:17','CR000004','1'),
	('SDA000393','SHS000197','DSQ000099','A','2020-01-10 00:50:32','2020-01-15 04:02:44','CR000004','1'),
	('SDA000394','SHS000197','DSQ000099','B','2020-01-08 18:02:41','2020-01-20 11:05:22','CR000004','1'),
	('SDA000395','SHS000198','DSQ000099','A','2020-01-23 00:50:15','2020-01-21 11:07:18','CR000004','1'),
	('SDA000396','SHS000198','DSQ000099','B','2020-01-12 00:20:47','2020-01-26 18:29:51','CR000004','1'),
	('SDA000397','SHS000199','DSQ000100','A','2020-01-26 03:07:50','2020-01-19 16:31:29','CR000004','1'),
	('SDA000398','SHS000199','DSQ000100','B','2020-01-07 22:25:21','2020-01-26 19:14:47','CR000004','1'),
	('SDA000399','SHS000200','DSQ000100','A','2020-01-24 03:27:37','2020-01-14 14:44:42','CR000004','1'),
	('SDA000400','SHS000200','DSQ000100','B','2020-01-15 12:38:37','2020-01-07 06:06:11','CR000004','1'),
	('SDA000401','SHS000201','DSQ000101','A','2020-01-08 22:02:47','2020-01-21 09:37:17','CR000004','1'),
	('SDA000402','SHS000201','DSQ000101','B','2020-01-08 01:36:17','2020-01-08 09:47:06','CR000004','1'),
	('SDA000403','SHS000202','DSQ000101','A','2020-01-21 05:49:04','2020-01-10 01:04:14','CR000004','1'),
	('SDA000404','SHS000202','DSQ000101','B','2020-01-22 01:13:30','2020-01-20 13:38:25','CR000004','1'),
	('SDA000405','SHS000203','DSQ000102','A','2020-01-11 12:57:52','2020-01-16 14:01:34','CR000004','1'),
	('SDA000406','SHS000203','DSQ000102','B','2020-01-20 17:13:18','2020-01-16 10:43:06','CR000004','1'),
	('SDA000407','SHS000204','DSQ000102','A','2020-01-20 06:27:30','2020-01-13 06:06:09','CR000004','1'),
	('SDA000408','SHS000204','DSQ000102','B','2020-01-06 07:14:22','2020-01-16 13:20:04','CR000004','1'),
	('SDA000409','SHS000205','DSQ000103','A','2020-01-13 15:36:20','2020-01-16 05:29:13','CR000004','1'),
	('SDA000410','SHS000205','DSQ000103','B','2020-01-13 06:08:09','2020-01-16 03:08:28','CR000004','1'),
	('SDA000411','SHS000206','DSQ000103','A','2020-01-16 20:31:55','2020-01-22 20:23:29','CR000004','1'),
	('SDA000412','SHS000206','DSQ000103','B','2020-01-07 04:10:42','2020-01-26 22:03:19','CR000004','1'),
	('SDA000413','SHS000207','DSQ000104','A','2020-01-13 18:33:57','2020-01-17 03:37:49','CR000004','1'),
	('SDA000414','SHS000207','DSQ000104','B','2020-01-25 05:35:03','2020-01-25 18:20:35','CR000004','1'),
	('SDA000415','SHS000208','DSQ000104','A','2020-01-07 02:10:02','2020-01-10 07:22:03','CR000004','1'),
	('SDA000416','SHS000208','DSQ000104','B','2020-01-06 16:19:48','2020-01-06 05:44:35','CR000004','1'),
	('SDA000417','SHS000209','DSQ000105','A','2020-01-19 05:46:30','2020-01-08 05:22:04','CR000004','1'),
	('SDA000418','SHS000209','DSQ000105','B','2020-01-27 00:17:30','2020-01-22 19:31:38','CR000004','1'),
	('SDA000419','SHS000210','DSQ000105','A','2020-01-07 02:52:01','2020-01-09 11:36:23','CR000004','1'),
	('SDA000420','SHS000210','DSQ000105','B','2020-01-07 23:55:04','2020-01-19 01:00:59','CR000004','1'),
	('SDA000421','SHS000211','DSQ000106','A','2020-01-26 04:18:36','2020-01-20 00:02:13','CR000004','1'),
	('SDA000422','SHS000211','DSQ000106','B','2020-01-19 15:25:30','2020-01-20 20:56:02','CR000004','1'),
	('SDA000423','SHS000212','DSQ000106','A','2020-01-23 11:50:49','2020-01-20 07:29:06','CR000004','1'),
	('SDA000424','SHS000212','DSQ000106','B','2020-01-25 22:53:35','2020-01-18 19:39:46','CR000004','1'),
	('SDA000425','SHS000213','DSQ000107','A','2020-01-21 19:35:36','2020-01-12 12:49:59','CR000004','1'),
	('SDA000426','SHS000213','DSQ000107','B','2020-01-26 08:16:23','2020-01-26 01:22:18','CR000004','1'),
	('SDA000427','SHS000214','DSQ000107','A','2020-01-09 06:03:02','2020-01-05 23:40:37','CR000004','1'),
	('SDA000428','SHS000214','DSQ000107','B','2020-01-15 09:10:06','2020-01-25 06:42:50','CR000004','1'),
	('SDA000429','SHS000215','DSQ000108','A','2020-01-24 21:48:12','2020-01-14 19:16:59','CR000004','1'),
	('SDA000430','SHS000215','DSQ000108','B','2020-01-25 15:43:27','2020-01-24 20:33:43','CR000004','1'),
	('SDA000431','SHS000216','DSQ000108','A','2020-01-21 09:45:48','2020-01-05 15:08:35','CR000004','1'),
	('SDA000432','SHS000216','DSQ000108','B','2020-01-09 02:03:46','2020-01-22 09:24:34','CR000004','1'),
	('SDA000433','SHS000217','DSQ000109','A','2020-01-10 09:55:36','2020-01-14 21:42:55','CR000004','1'),
	('SDA000434','SHS000217','DSQ000109','B','2020-01-13 03:37:34','2020-01-07 14:54:00','CR000004','1'),
	('SDA000435','SHS000218','DSQ000109','A','2020-01-12 13:33:31','2020-01-17 14:28:06','CR000004','1'),
	('SDA000436','SHS000218','DSQ000109','B','2020-01-20 00:20:32','2020-01-22 03:21:11','CR000004','1'),
	('SDA000437','SHS000219','DSQ000110','A','2020-01-07 23:16:39','2020-01-25 09:16:39','CR000004','1'),
	('SDA000438','SHS000219','DSQ000110','B','2020-01-11 20:18:02','2020-01-15 11:01:54','CR000004','1'),
	('SDA000439','SHS000220','DSQ000110','A','2020-01-08 21:41:56','2020-01-14 21:00:04','CR000004','1'),
	('SDA000440','SHS000220','DSQ000110','B','2020-01-12 06:01:47','2020-01-21 16:45:51','CR000004','1'),
	('SDA000441','SHS000221','DSQ000111','A','2020-01-12 05:42:56','2020-01-09 00:03:31','CR000004','1'),
	('SDA000442','SHS000221','DSQ000111','B','2020-01-10 03:05:36','2020-01-06 16:39:14','CR000004','1'),
	('SDA000443','SHS000222','DSQ000111','A','2020-01-08 16:31:34','2020-01-21 09:38:36','CR000004','1'),
	('SDA000444','SHS000222','DSQ000111','B','2020-01-10 00:57:57','2020-01-25 05:07:37','CR000004','1'),
	('SDA000445','SHS000223','DSQ000112','A','2020-01-05 15:11:18','2020-01-12 15:15:47','CR000004','1'),
	('SDA000446','SHS000223','DSQ000112','B','2020-01-08 16:38:22','2020-01-23 08:56:21','CR000004','1'),
	('SDA000447','SHS000224','DSQ000112','A','2020-01-14 02:09:48','2020-01-08 15:44:57','CR000004','1'),
	('SDA000448','SHS000224','DSQ000112','B','2020-01-25 07:41:43','2020-01-22 03:08:21','CR000004','1'),
	('SDA000449','SHS000225','DSQ000113','A','2020-01-27 03:25:34','2020-01-08 12:23:45','CR000004','1'),
	('SDA000450','SHS000225','DSQ000113','B','2020-01-12 23:19:01','2020-01-26 22:47:13','CR000004','1'),
	('SDA000451','SHS000226','DSQ000113','A','2020-01-23 06:03:39','2020-01-12 02:22:59','CR000004','1'),
	('SDA000452','SHS000226','DSQ000113','B','2020-01-15 08:03:36','2020-01-16 14:40:11','CR000004','1'),
	('SDA000453','SHS000227','DSQ000114','A','2020-01-07 10:01:10','2020-01-20 10:12:26','CR000004','1'),
	('SDA000454','SHS000227','DSQ000114','B','2020-01-10 04:10:25','2020-01-25 23:42:25','CR000004','1'),
	('SDA000455','SHS000228','DSQ000114','A','2020-01-12 02:42:39','2020-01-18 17:11:26','CR000004','1'),
	('SDA000456','SHS000228','DSQ000114','B','2020-01-21 15:28:34','2020-01-10 22:25:05','CR000004','1'),
	('SDA000457','SHS000229','DSQ000115','A','2020-01-15 13:42:15','2020-01-23 01:33:11','CR000004','1'),
	('SDA000458','SHS000229','DSQ000115','B','2020-01-19 07:11:52','2020-01-07 07:56:14','CR000004','1'),
	('SDA000459','SHS000230','DSQ000115','A','2020-01-06 16:03:40','2020-01-14 10:20:09','CR000004','1'),
	('SDA000460','SHS000230','DSQ000115','B','2020-01-12 05:52:18','2020-01-26 11:11:19','CR000004','1'),
	('SDA000461','SHS000231','DSQ000116','A','2020-01-13 17:29:10','2020-01-06 03:19:18','CR000004','1'),
	('SDA000462','SHS000231','DSQ000116','B','2020-01-23 06:25:03','2020-01-19 20:35:47','CR000004','1'),
	('SDA000463','SHS000232','DSQ000116','A','2020-01-23 08:33:25','2020-01-16 06:45:25','CR000004','1'),
	('SDA000464','SHS000232','DSQ000116','B','2020-01-27 02:03:56','2020-01-10 08:28:36','CR000004','1'),
	('SDA000465','SHS000233','DSQ000117','A','2020-01-16 14:49:49','2020-01-08 20:46:57','CR000004','1'),
	('SDA000466','SHS000233','DSQ000117','B','2020-01-13 21:06:12','2020-01-20 19:40:48','CR000004','1'),
	('SDA000467','SHS000234','DSQ000117','A','2020-01-11 01:44:06','2020-01-07 07:47:43','CR000004','1'),
	('SDA000468','SHS000234','DSQ000117','B','2020-01-06 08:09:44','2020-01-07 03:52:17','CR000004','1'),
	('SDA000469','SHS000235','DSQ000118','A','2020-01-25 23:32:40','2020-01-23 17:30:30','CR000004','1'),
	('SDA000470','SHS000235','DSQ000118','B','2020-01-13 12:33:09','2020-01-20 00:17:53','CR000004','1'),
	('SDA000471','SHS000236','DSQ000118','A','2020-01-21 11:06:06','2020-01-26 04:38:56','CR000004','1'),
	('SDA000472','SHS000236','DSQ000118','B','2020-01-22 03:32:44','2020-01-16 13:46:49','CR000004','1'),
	('SDA000473','SHS000237','DSQ000119','A','2020-01-12 22:58:57','2020-01-24 03:01:25','CR000004','1'),
	('SDA000474','SHS000237','DSQ000119','B','2020-01-08 16:08:01','2020-01-11 08:09:31','CR000004','1'),
	('SDA000475','SHS000238','DSQ000119','A','2020-01-07 08:37:24','2020-01-11 17:30:19','CR000004','1'),
	('SDA000476','SHS000238','DSQ000119','B','2020-01-19 11:02:41','2020-01-14 22:10:27','CR000004','1'),
	('SDA000477','SHS000239','DSQ000120','A','2020-01-08 01:36:09','2020-01-25 16:08:04','CR000004','1'),
	('SDA000478','SHS000239','DSQ000120','B','2020-01-20 10:28:10','2020-01-18 23:30:10','CR000004','1'),
	('SDA000479','SHS000240','DSQ000120','A','2020-01-12 12:45:12','2020-01-15 23:12:20','CR000004','1'),
	('SDA000480','SHS000240','DSQ000120','B','2020-01-16 15:39:11','2020-01-10 14:39:39','CR000004','1'),
	('SDA000481','SHS000241','DSQ000121','A','2020-01-13 11:41:50','2020-01-08 18:53:29','CR000004','1'),
	('SDA000482','SHS000241','DSQ000121','B','2020-01-17 06:38:11','2020-01-21 10:29:29','CR000004','1'),
	('SDA000483','SHS000242','DSQ000121','A','2020-01-10 06:55:40','2020-01-24 12:53:37','CR000004','1'),
	('SDA000484','SHS000242','DSQ000121','B','2020-01-13 20:46:39','2020-01-08 22:35:12','CR000004','1'),
	('SDA000485','SHS000243','DSQ000122','A','2020-01-27 05:08:08','2020-01-17 10:44:12','CR000004','1'),
	('SDA000486','SHS000243','DSQ000122','B','2020-01-15 14:03:34','2020-01-25 23:14:51','CR000004','1'),
	('SDA000487','SHS000244','DSQ000122','A','2020-01-11 18:03:25','2020-01-08 17:18:10','CR000004','1'),
	('SDA000488','SHS000244','DSQ000122','B','2020-01-23 05:11:51','2020-01-08 06:52:15','CR000004','1'),
	('SDA000489','SHS000245','DSQ000123','A','2020-01-14 05:44:10','2020-01-09 21:23:40','CR000004','1'),
	('SDA000490','SHS000245','DSQ000123','B','2020-01-18 11:58:38','2020-01-23 19:09:31','CR000004','1'),
	('SDA000491','SHS000246','DSQ000123','A','2020-01-18 21:08:19','2020-01-06 15:56:02','CR000004','1'),
	('SDA000492','SHS000246','DSQ000123','B','2020-01-27 06:30:33','2020-01-23 13:04:33','CR000004','1'),
	('SDA000493','SHS000247','DSQ000124','A','2020-01-15 18:54:13','2020-01-06 02:51:02','CR000004','1'),
	('SDA000494','SHS000247','DSQ000124','B','2020-01-18 04:21:42','2020-01-21 23:02:27','CR000004','1'),
	('SDA000495','SHS000248','DSQ000124','A','2020-01-16 00:19:31','2020-01-14 20:10:42','CR000004','1'),
	('SDA000496','SHS000248','DSQ000124','B','2020-01-06 04:14:07','2020-01-06 23:00:56','CR000004','1'),
	('SDA000497','SHS000249','DSQ000125','A','2020-01-23 16:57:22','2020-01-09 09:46:49','CR000004','1'),
	('SDA000498','SHS000249','DSQ000125','B','2020-01-07 14:45:45','2020-01-24 13:10:02','CR000004','1'),
	('SDA000499','SHS000250','DSQ000125','A','2020-01-23 23:22:06','2020-01-23 19:50:09','CR000004','1'),
	('SDA000500','SHS000250','DSQ000125','B','2020-01-25 23:58:40','2020-01-23 14:48:24','CR000004','1'),
	('SDA000501','SHS000251','DSQ000126','A','2020-01-19 01:58:18','2020-01-16 17:58:20','CR000004','1'),
	('SDA000502','SHS000251','DSQ000126','B','2020-01-19 01:45:45','2020-01-16 20:22:01','CR000004','1'),
	('SDA000503','SHS000252','DSQ000126','A','2020-01-27 05:46:23','2020-01-12 07:29:11','CR000004','1'),
	('SDA000504','SHS000252','DSQ000126','B','2020-01-14 00:11:33','2020-01-15 03:27:49','CR000004','1'),
	('SDA000505','SHS000253','DSQ000127','A','2020-01-10 05:46:50','2020-01-22 11:18:29','CR000004','1'),
	('SDA000506','SHS000253','DSQ000127','B','2020-01-14 20:42:55','2020-01-11 06:47:34','CR000004','1'),
	('SDA000507','SHS000254','DSQ000127','A','2020-01-06 06:47:13','2020-01-25 08:57:44','CR000004','1'),
	('SDA000508','SHS000254','DSQ000127','B','2020-01-12 19:21:42','2020-01-23 13:32:34','CR000004','1'),
	('SDA000509','SHS000255','DSQ000128','A','2020-01-20 06:08:05','2020-01-12 09:38:37','CR000004','1'),
	('SDA000510','SHS000255','DSQ000128','B','2020-01-08 20:50:53','2020-01-22 11:58:01','CR000004','1'),
	('SDA000511','SHS000256','DSQ000128','A','2020-01-06 00:21:15','2020-01-08 13:58:45','CR000004','1'),
	('SDA000512','SHS000256','DSQ000128','B','2020-01-24 09:32:43','2020-01-22 16:20:10','CR000004','1');

insert into survey_status_data values
	('UnSubmitted','未提交','UnSubmitted','P000001','1'),
	('Submitted','已提交','Submitted','P000001','1'),
	('Draft','草稿','Draft','P000001','1');

insert into wechat_user_data values
	('WU000001','张三','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000001','Teacher','2020-01-23 08:40:36','P000001','1'),
	('WU000002','李四','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000001','Teacher','2020-01-17 02:09:04','P000001','1'),
	('WU000003','王五','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000002','Teacher','2020-01-14 12:36:16','P000001','1'),
	('WU000004','张三','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000002','Teacher','2020-01-19 13:54:03','P000001','1'),
	('WU000005','李四','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000003','Teacher','2020-01-23 12:21:40','P000001','1'),
	('WU000006','王五','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000003','Teacher','2020-01-16 17:10:33','P000001','1'),
	('WU000007','张三','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000004','Teacher','2020-01-06 03:17:09','P000001','1'),
	('WU000008','李四','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000004','Teacher','2020-01-20 21:55:22','P000001','1'),
	('WU000009','王五','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000005','Teacher','2020-01-10 08:32:40','P000001','1'),
	('WU000010','张三','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000005','Teacher','2020-01-13 09:16:13','P000001','1'),
	('WU000011','李四','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000006','Teacher','2020-01-11 14:42:33','P000001','1'),
	('WU000012','王五','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000006','Guardian','2020-01-18 08:29:57','P000001','1'),
	('WU000013','张三','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000007','Guardian','2020-01-07 07:42:09','P000001','1'),
	('WU000014','李四','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000007','Guardian','2020-01-19 16:49:01','P000001','1'),
	('WU000015','王五','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000008','Guardian','2020-01-16 14:39:00','P000001','1'),
	('WU000016','张三','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000008','Guardian','2020-01-09 23:46:24','P000001','1'),
	('WU000017','李四','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000009','Guardian','2020-01-18 03:13:56','P000001','1'),
	('WU000018','王五','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000009','Guardian','2020-01-16 15:49:34','P000001','1'),
	('WU000019','张三','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000010','Guardian','2020-01-20 05:49:37','P000001','1'),
	('WU000020','李四','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000010','Guardian','2020-01-22 08:14:43','P000001','1'),
	('WU000021','王五','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000011','Guardian','2020-01-25 04:29:46','P000001','1'),
	('WU000022','张三','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000011','Guardian','2020-01-23 02:36:47','P000001','1'),
	('WU000023','李四','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000012','Student','2020-01-11 05:46:42','P000001','1'),
	('WU000024','王五','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000012','Student','2020-01-18 06:37:05','P000001','1'),
	('WU000025','张三','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000013','Student','2020-01-07 18:56:24','P000001','1'),
	('WU000026','李四','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000013','Student','2020-01-10 14:03:47','P000001','1'),
	('WU000027','王五','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000014','Student','2020-01-25 11:30:04','P000001','1'),
	('WU000028','张三','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000014','Student','2020-01-08 03:46:35','P000001','1'),
	('WU000029','李四','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000015','Student','2020-01-05 16:10:29','P000001','1'),
	('WU000030','王五','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000015','Student','2020-01-21 11:30:27','P000001','1'),
	('WU000031','张三','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000016','Student','2020-01-19 07:19:42','P000001','1'),
	('WU000032','李四','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000016','Student','2020-01-07 09:16:43','P000001','1');

insert into user_type_data values
	('Teacher','老师','Teacher','P000001','1'),
	('Guardian','家长','Guardian','P000001','1'),
	('Student','学生','Student','P000001','1');

insert into wechat_login_info_data values
	('WLI000001','WU000001','user123','user123','session123','2020-01-06 18:26:20','1'),
	('WLI000002','WU000001','user1230002','user1230002','session1230002','2020-01-23 22:45:02','1'),
	('WLI000003','WU000002','user1230003','user1230003','session1230003','2020-01-27 02:50:36','1'),
	('WLI000004','WU000002','user1230004','user1230004','session1230004','2020-01-25 05:44:45','1'),
	('WLI000005','WU000003','user1230005','user1230005','session1230005','2020-01-19 07:10:52','1'),
	('WLI000006','WU000003','user1230006','user1230006','session1230006','2020-01-09 00:53:15','1'),
	('WLI000007','WU000004','user1230007','user1230007','session1230007','2020-01-16 00:34:58','1'),
	('WLI000008','WU000004','user1230008','user1230008','session1230008','2020-01-13 05:33:54','1'),
	('WLI000009','WU000005','user1230009','user1230009','session1230009','2020-01-16 06:40:49','1'),
	('WLI000010','WU000005','user1230010','user1230010','session1230010','2020-01-12 07:54:42','1'),
	('WLI000011','WU000006','user1230011','user1230011','session1230011','2020-01-12 19:39:21','1'),
	('WLI000012','WU000006','user1230012','user1230012','session1230012','2020-01-25 10:10:57','1'),
	('WLI000013','WU000007','user1230013','user1230013','session1230013','2020-01-11 20:59:53','1'),
	('WLI000014','WU000007','user1230014','user1230014','session1230014','2020-01-15 00:52:14','1'),
	('WLI000015','WU000008','user1230015','user1230015','session1230015','2020-01-13 08:24:20','1'),
	('WLI000016','WU000008','user1230016','user1230016','session1230016','2020-01-18 06:32:37','1'),
	('WLI000017','WU000009','user1230017','user1230017','session1230017','2020-01-22 18:34:36','1'),
	('WLI000018','WU000009','user1230018','user1230018','session1230018','2020-01-18 09:19:47','1'),
	('WLI000019','WU000010','user1230019','user1230019','session1230019','2020-01-08 15:39:57','1'),
	('WLI000020','WU000010','user1230020','user1230020','session1230020','2020-01-06 20:20:34','1'),
	('WLI000021','WU000011','user1230021','user1230021','session1230021','2020-01-10 12:14:13','1'),
	('WLI000022','WU000011','user1230022','user1230022','session1230022','2020-01-26 22:43:08','1'),
	('WLI000023','WU000012','user1230023','user1230023','session1230023','2020-01-22 13:37:18','1'),
	('WLI000024','WU000012','user1230024','user1230024','session1230024','2020-01-10 21:16:42','1'),
	('WLI000025','WU000013','user1230025','user1230025','session1230025','2020-01-19 20:46:56','1'),
	('WLI000026','WU000013','user1230026','user1230026','session1230026','2020-01-16 17:59:02','1'),
	('WLI000027','WU000014','user1230027','user1230027','session1230027','2020-01-16 05:20:45','1'),
	('WLI000028','WU000014','user1230028','user1230028','session1230028','2020-01-11 00:39:58','1'),
	('WLI000029','WU000015','user1230029','user1230029','session1230029','2020-01-10 13:07:07','1'),
	('WLI000030','WU000015','user1230030','user1230030','session1230030','2020-01-20 13:26:04','1'),
	('WLI000031','WU000016','user1230031','user1230031','session1230031','2020-01-07 04:23:59','1'),
	('WLI000032','WU000016','user1230032','user1230032','session1230032','2020-01-14 06:56:51','1'),
	('WLI000033','WU000017','user1230033','user1230033','session1230033','2020-01-18 03:30:08','1'),
	('WLI000034','WU000017','user1230034','user1230034','session1230034','2020-01-15 17:46:12','1'),
	('WLI000035','WU000018','user1230035','user1230035','session1230035','2020-01-23 06:24:04','1'),
	('WLI000036','WU000018','user1230036','user1230036','session1230036','2020-01-21 02:37:49','1'),
	('WLI000037','WU000019','user1230037','user1230037','session1230037','2020-01-27 08:52:05','1'),
	('WLI000038','WU000019','user1230038','user1230038','session1230038','2020-01-14 23:35:33','1'),
	('WLI000039','WU000020','user1230039','user1230039','session1230039','2020-01-15 18:24:52','1'),
	('WLI000040','WU000020','user1230040','user1230040','session1230040','2020-01-26 05:50:26','1'),
	('WLI000041','WU000021','user1230041','user1230041','session1230041','2020-01-25 21:45:32','1'),
	('WLI000042','WU000021','user1230042','user1230042','session1230042','2020-01-08 04:15:15','1'),
	('WLI000043','WU000022','user1230043','user1230043','session1230043','2020-01-17 08:12:10','1'),
	('WLI000044','WU000022','user1230044','user1230044','session1230044','2020-01-11 20:06:49','1'),
	('WLI000045','WU000023','user1230045','user1230045','session1230045','2020-01-14 02:21:24','1'),
	('WLI000046','WU000023','user1230046','user1230046','session1230046','2020-01-10 06:03:27','1'),
	('WLI000047','WU000024','user1230047','user1230047','session1230047','2020-01-10 23:22:04','1'),
	('WLI000048','WU000024','user1230048','user1230048','session1230048','2020-01-13 08:32:13','1'),
	('WLI000049','WU000025','user1230049','user1230049','session1230049','2020-01-11 02:32:23','1'),
	('WLI000050','WU000025','user1230050','user1230050','session1230050','2020-01-25 20:37:16','1'),
	('WLI000051','WU000026','user1230051','user1230051','session1230051','2020-01-25 06:14:34','1'),
	('WLI000052','WU000026','user1230052','user1230052','session1230052','2020-01-13 02:28:02','1'),
	('WLI000053','WU000027','user1230053','user1230053','session1230053','2020-01-08 15:43:01','1'),
	('WLI000054','WU000027','user1230054','user1230054','session1230054','2020-01-21 04:14:46','1'),
	('WLI000055','WU000028','user1230055','user1230055','session1230055','2020-01-11 10:50:51','1'),
	('WLI000056','WU000028','user1230056','user1230056','session1230056','2020-01-21 15:34:15','1'),
	('WLI000057','WU000029','user1230057','user1230057','session1230057','2020-01-13 16:51:45','1'),
	('WLI000058','WU000029','user1230058','user1230058','session1230058','2020-01-10 10:21:33','1'),
	('WLI000059','WU000030','user1230059','user1230059','session1230059','2020-01-09 07:41:22','1'),
	('WLI000060','WU000030','user1230060','user1230060','session1230060','2020-01-10 13:41:54','1'),
	('WLI000061','WU000031','user1230061','user1230061','session1230061','2020-01-25 01:39:36','1'),
	('WLI000062','WU000031','user1230062','user1230062','session1230062','2020-01-10 09:34:44','1'),
	('WLI000063','WU000032','user1230063','user1230063','session1230063','2020-01-08 03:06:02','1'),
	('WLI000064','WU000032','user1230064','user1230064','session1230064','2020-01-10 15:41:02','1');

insert into change_request_data values
	('CR000001','答题','2020-01-13 13:34:33','8.8.8.8','AnswerQuestion','P000001','1'),
	('CR000002','答题0002','2020-01-07 03:14:48','8.8.8.8','AnswerQuestion','P000001','1'),
	('CR000003','答题0003','2020-01-26 06:07:20','8.8.8.8','AnswerQuestion','P000001','1'),
	('CR000004','答题0004','2020-01-16 02:22:05','8.8.8.8','AnswerQuestion','P000001','1');

insert into change_request_type_data values
	('AnswerQuestion','答题','AnswerQuestion','book','1','    一段样例文字。    一段样例文字。\n可以分段。可以分段。\n\n可以空行。可以空行。\n\n','    一段样例文字。    一段样例文字。\n可以分段。可以分段。\n\n可以空行。可以空行。\n\n','P000001','1');

insert into wechat_workapp_identify_data values
	('WWI000001','corporation123','user123','SU000001','2020-01-10 22:23:04','2020-01-19 17:17:55','1'),
	('WWI000002','corporation1230002','user1230002','SU000001','2020-01-09 06:10:17','2020-01-18 17:31:55','1'),
	('WWI000003','corporation1230003','user1230003','SU000002','2020-01-10 20:10:59','2020-01-09 02:07:25','1'),
	('WWI000004','corporation1230004','user1230004','SU000002','2020-01-10 23:48:37','2020-01-27 07:15:56','1');

insert into wechat_miniapp_identify_data values
	('WMI000001','wechat_open_id_1234567890','wechat_miniapp_id_1234567890','SU000001','2020-01-11 05:05:55','2020-01-22 16:52:30','1'),
	('WMI000002','wechat_open_id_12345678900002','wechat_miniapp_id_12345678900002','SU000001','2020-01-14 09:49:16','2020-01-12 01:21:21','1'),
	('WMI000003','wechat_open_id_12345678900003','wechat_miniapp_id_12345678900003','SU000002','2020-01-25 15:25:24','2020-01-26 18:36:24','1'),
	('WMI000004','wechat_open_id_12345678900004','wechat_miniapp_id_12345678900004','SU000002','2020-01-18 03:40:09','2020-01-20 23:40:33','1');







delete from list_access_data ;
delete from object_access_data ;
delete from user_app_data ;
delete from login_history_data ;
delete from sec_user_data ;
delete from user_domain_data ;
delete from wechat_miniapp_identify_data;
delete from wechat_workapp_identify_data;
insert into user_domain_data values ('UD000001','用户区域','1');



insert into sec_user_data values('SU000001','User000001','13900000001','1000001@qq.com','24327F1C00D22210298A18D0DB9AA6C4C22DEAC4BEAE7C02E616442CA7764246', 'weixin_openid_000001', 'weixin_appid_000001', 'jwt_token_000001' ,'9292993','2019-09-09 09:09:09','2019-09-09 09:09:09','UD000001',1);
insert into user_app_data values('UA000001','健康状态调查平台','SU000001','university',1,'MXWR','Platform','P000001','/link/to/app','1');
insert into user_app_data values('UA000002','我的账户','SU000001','lock',1,'MXWR','SecUser','SU000001','/link/to/app','1');
insert into user_app_data values('UA000003','用户管理','SU000001','users',1,'MXWR','UserDomain','UD000001','/link/to/app','1');

/* ------------------------------ generate users for all target od marked as user4all ------------------------------------------ */
insert into sec_user_data values('SU000002','User000002','13900000002','1000002@qq.com','BB5210DAE99659C7164D7DBCFC51FB2D167D0DA372D58EF26A9F8533EEA2967C', 'weixin_openid_000002', 'weixin_appid_000002', 'jwt_token_000002' ,'9292993','2019-09-09 09:09:09','2019-09-09 09:09:09','UD000001',1);
insert into user_app_data values('UA000004','微信用户: 张三','SU000002','store',1,'MXWR','WechatUser','WU000001','/link/to/app','1');
insert into user_app_data values('UA000005','我的账户','SU000002','lock',1,'MXWR','SecUser','SU000002','/link/to/app','1');
insert into sec_user_data values('SU000003','User000003','13900000003','1000003@qq.com','9D4104DF2774FDEAAE074CA35B052D8F664F4F99064C7BEAB0B589C2605C4EDA', 'weixin_openid_000003', 'weixin_appid_000003', 'jwt_token_000003' ,'9292993','2019-09-09 09:09:09','2019-09-09 09:09:09','UD000001',1);
insert into user_app_data values('UA000006','微信用户: 李四','SU000003','store',1,'MXWR','WechatUser','WU000002','/link/to/app','1');
insert into user_app_data values('UA000007','我的账户','SU000003','lock',1,'MXWR','SecUser','SU000003','/link/to/app','1');
insert into sec_user_data values('SU000004','User000004','13900000004','1000004@qq.com','9B223EBD008D7B544A3A640739EBE47459D3A4C5296DDA00F594FAF60FE88B28', 'weixin_openid_000004', 'weixin_appid_000004', 'jwt_token_000004' ,'9292993','2019-09-09 09:09:09','2019-09-09 09:09:09','UD000001',1);
insert into user_app_data values('UA000008','微信用户: 王五','SU000004','store',1,'MXWR','WechatUser','WU000003','/link/to/app','1');
insert into user_app_data values('UA000009','我的账户','SU000004','lock',1,'MXWR','SecUser','SU000004','/link/to/app','1');
insert into sec_user_data values('SU000005','User000005','13900000005','1000005@qq.com','AE5F93F319636A96963C06D035B97F004D18E61D80129EFEA331784A6E21DC5C', 'weixin_openid_000005', 'weixin_appid_000005', 'jwt_token_000005' ,'9292993','2019-09-09 09:09:09','2019-09-09 09:09:09','UD000001',1);
insert into user_app_data values('UA000010','微信用户: 张三','SU000005','store',1,'MXWR','WechatUser','WU000004','/link/to/app','1');
insert into user_app_data values('UA000011','我的账户','SU000005','lock',1,'MXWR','SecUser','SU000005','/link/to/app','1');
insert into sec_user_data values('SU000006','User000006','13900000006','1000006@qq.com','5FBBDBEAD9F84D599E8819CEEA167854CDA0FFD8D297D17D12E4619CE76F3B55', 'weixin_openid_000006', 'weixin_appid_000006', 'jwt_token_000006' ,'9292993','2019-09-09 09:09:09','2019-09-09 09:09:09','UD000001',1);
insert into user_app_data values('UA000012','微信用户: 李四','SU000006','store',1,'MXWR','WechatUser','WU000005','/link/to/app','1');
insert into user_app_data values('UA000013','我的账户','SU000006','lock',1,'MXWR','SecUser','SU000006','/link/to/app','1');
insert into sec_user_data values('SU000007','User000007','13900000007','1000007@qq.com','A9652F0D7C1ACCB421BAF55EB3E7286AFA8F591897F1AE4CEB6A76402CCBE803', 'weixin_openid_000007', 'weixin_appid_000007', 'jwt_token_000007' ,'9292993','2019-09-09 09:09:09','2019-09-09 09:09:09','UD000001',1);
insert into user_app_data values('UA000014','微信用户: 王五','SU000007','store',1,'MXWR','WechatUser','WU000006','/link/to/app','1');
insert into user_app_data values('UA000015','我的账户','SU000007','lock',1,'MXWR','SecUser','SU000007','/link/to/app','1');
insert into sec_user_data values('SU000008','User000008','13900000008','1000008@qq.com','A4B83C2652CD6BECE5C7909576555B313078D7EE50AA028F26B8F0245C191B4B', 'weixin_openid_000008', 'weixin_appid_000008', 'jwt_token_000008' ,'9292993','2019-09-09 09:09:09','2019-09-09 09:09:09','UD000001',1);
insert into user_app_data values('UA000016','微信用户: 张三','SU000008','store',1,'MXWR','WechatUser','WU000007','/link/to/app','1');
insert into user_app_data values('UA000017','我的账户','SU000008','lock',1,'MXWR','SecUser','SU000008','/link/to/app','1');
insert into sec_user_data values('SU000009','User000009','13900000009','1000009@qq.com','88F8AB5F153081C5AB21F5E5354B4EB14286EFB43CEA588ED1C73FE2B46B35C1', 'weixin_openid_000009', 'weixin_appid_000009', 'jwt_token_000009' ,'9292993','2019-09-09 09:09:09','2019-09-09 09:09:09','UD000001',1);
insert into user_app_data values('UA000018','微信用户: 李四','SU000009','store',1,'MXWR','WechatUser','WU000008','/link/to/app','1');
insert into user_app_data values('UA000019','我的账户','SU000009','lock',1,'MXWR','SecUser','SU000009','/link/to/app','1');
insert into sec_user_data values('SU000010','User000010','13900000010','1000010@qq.com','EF8232ABB97CC3858F271527A1AA1452A33715A3AC48312A44B0940D5C948600', 'weixin_openid_000010', 'weixin_appid_000010', 'jwt_token_000010' ,'9292993','2019-09-09 09:09:09','2019-09-09 09:09:09','UD000001',1);
insert into user_app_data values('UA000020','微信用户: 王五','SU000010','store',1,'MXWR','WechatUser','WU000009','/link/to/app','1');
insert into user_app_data values('UA000021','我的账户','SU000010','lock',1,'MXWR','SecUser','SU000010','/link/to/app','1');
insert into sec_user_data values('SU000011','User000011','13900000011','1000011@qq.com','FE7AF5D4F030CD575C117A73124FC39AB41528DFFC41D2CFBC1130E755694243', 'weixin_openid_000011', 'weixin_appid_000011', 'jwt_token_000011' ,'9292993','2019-09-09 09:09:09','2019-09-09 09:09:09','UD000001',1);
insert into user_app_data values('UA000022','微信用户: 张三','SU000011','store',1,'MXWR','WechatUser','WU000010','/link/to/app','1');
insert into user_app_data values('UA000023','我的账户','SU000011','lock',1,'MXWR','SecUser','SU000011','/link/to/app','1');
insert into sec_user_data values('SU000012','User000012','13900000012','1000012@qq.com','999DD89E35807C62458F2D191D4F55548B49245EEC6E186FE9497EC867C40088', 'weixin_openid_000012', 'weixin_appid_000012', 'jwt_token_000012' ,'9292993','2019-09-09 09:09:09','2019-09-09 09:09:09','UD000001',1);
insert into user_app_data values('UA000024','微信用户: 李四','SU000012','store',1,'MXWR','WechatUser','WU000011','/link/to/app','1');
insert into user_app_data values('UA000025','我的账户','SU000012','lock',1,'MXWR','SecUser','SU000012','/link/to/app','1');
insert into sec_user_data values('SU000013','User000013','13900000013','1000013@qq.com','0AE92E17166CBB59341836C218E92EF083058CC4E3108C5FD2FB904650013A69', 'weixin_openid_000013', 'weixin_appid_000013', 'jwt_token_000013' ,'9292993','2019-09-09 09:09:09','2019-09-09 09:09:09','UD000001',1);
insert into user_app_data values('UA000026','微信用户: 王五','SU000013','store',1,'MXWR','WechatUser','WU000012','/link/to/app','1');
insert into user_app_data values('UA000027','我的账户','SU000013','lock',1,'MXWR','SecUser','SU000013','/link/to/app','1');
insert into sec_user_data values('SU000014','User000014','13900000014','1000014@qq.com','E79E64241204EB0FCE03C4BA0E315F21ECDB11D22264BE7B1AAD41D04D77A6D0', 'weixin_openid_000014', 'weixin_appid_000014', 'jwt_token_000014' ,'9292993','2019-09-09 09:09:09','2019-09-09 09:09:09','UD000001',1);
insert into user_app_data values('UA000028','微信用户: 张三','SU000014','store',1,'MXWR','WechatUser','WU000013','/link/to/app','1');
insert into user_app_data values('UA000029','我的账户','SU000014','lock',1,'MXWR','SecUser','SU000014','/link/to/app','1');
insert into sec_user_data values('SU000015','User000015','13900000015','1000015@qq.com','1D858671B95062DAFE1D989C089188CC4EFDF3D5C45D8F24DD20BF3E352A3D9B', 'weixin_openid_000015', 'weixin_appid_000015', 'jwt_token_000015' ,'9292993','2019-09-09 09:09:09','2019-09-09 09:09:09','UD000001',1);
insert into user_app_data values('UA000030','微信用户: 李四','SU000015','store',1,'MXWR','WechatUser','WU000014','/link/to/app','1');
insert into user_app_data values('UA000031','我的账户','SU000015','lock',1,'MXWR','SecUser','SU000015','/link/to/app','1');
insert into sec_user_data values('SU000016','User000016','13900000016','1000016@qq.com','14B1F5E667F8B6697C8A2952C3619D9AD82F846E5B32FD9F258918786B3ED519', 'weixin_openid_000016', 'weixin_appid_000016', 'jwt_token_000016' ,'9292993','2019-09-09 09:09:09','2019-09-09 09:09:09','UD000001',1);
insert into user_app_data values('UA000032','微信用户: 王五','SU000016','store',1,'MXWR','WechatUser','WU000015','/link/to/app','1');
insert into user_app_data values('UA000033','我的账户','SU000016','lock',1,'MXWR','SecUser','SU000016','/link/to/app','1');
insert into sec_user_data values('SU000017','User000017','13900000017','1000017@qq.com','1A803C7096681FC2AA7C55C46A6A99D8089481B96997774EA5B1C785C8035010', 'weixin_openid_000017', 'weixin_appid_000017', 'jwt_token_000017' ,'9292993','2019-09-09 09:09:09','2019-09-09 09:09:09','UD000001',1);
insert into user_app_data values('UA000034','微信用户: 张三','SU000017','store',1,'MXWR','WechatUser','WU000016','/link/to/app','1');
insert into user_app_data values('UA000035','我的账户','SU000017','lock',1,'MXWR','SecUser','SU000017','/link/to/app','1');
insert into sec_user_data values('SU000018','User000018','13900000018','1000018@qq.com','FA485AC06A6BD6BBF7AC9F253FCC516227CB232598792232277A70386FD892ED', 'weixin_openid_000018', 'weixin_appid_000018', 'jwt_token_000018' ,'9292993','2019-09-09 09:09:09','2019-09-09 09:09:09','UD000001',1);
insert into user_app_data values('UA000036','微信用户: 李四','SU000018','store',1,'MXWR','WechatUser','WU000017','/link/to/app','1');
insert into user_app_data values('UA000037','我的账户','SU000018','lock',1,'MXWR','SecUser','SU000018','/link/to/app','1');
insert into sec_user_data values('SU000019','User000019','13900000019','1000019@qq.com','A5D9532EB6FC76A7D06764C14F751A4AFBC7C5BC49C215272A2EE42BBEA1A502', 'weixin_openid_000019', 'weixin_appid_000019', 'jwt_token_000019' ,'9292993','2019-09-09 09:09:09','2019-09-09 09:09:09','UD000001',1);
insert into user_app_data values('UA000038','微信用户: 王五','SU000019','store',1,'MXWR','WechatUser','WU000018','/link/to/app','1');
insert into user_app_data values('UA000039','我的账户','SU000019','lock',1,'MXWR','SecUser','SU000019','/link/to/app','1');
insert into sec_user_data values('SU000020','User000020','13900000020','1000020@qq.com','7CB0B35123A314B427FC1459C4083AA314D8F9E2505BB9187594B223BE5623A0', 'weixin_openid_000020', 'weixin_appid_000020', 'jwt_token_000020' ,'9292993','2019-09-09 09:09:09','2019-09-09 09:09:09','UD000001',1);
insert into user_app_data values('UA000040','微信用户: 张三','SU000020','store',1,'MXWR','WechatUser','WU000019','/link/to/app','1');
insert into user_app_data values('UA000041','我的账户','SU000020','lock',1,'MXWR','SecUser','SU000020','/link/to/app','1');
insert into sec_user_data values('SU000021','User000021','13900000021','1000021@qq.com','C21B3A395B3E337A4D06491AEC7B485523BB4E5790DE925000FECEC237F939F2', 'weixin_openid_000021', 'weixin_appid_000021', 'jwt_token_000021' ,'9292993','2019-09-09 09:09:09','2019-09-09 09:09:09','UD000001',1);
insert into user_app_data values('UA000042','微信用户: 李四','SU000021','store',1,'MXWR','WechatUser','WU000020','/link/to/app','1');
insert into user_app_data values('UA000043','我的账户','SU000021','lock',1,'MXWR','SecUser','SU000021','/link/to/app','1');
insert into sec_user_data values('SU000022','User000022','13900000022','1000022@qq.com','D6C0743E4B79BE93E8BDB4D0B55054EC3532F6B1AF8F69EDD542F0D22DD228C9', 'weixin_openid_000022', 'weixin_appid_000022', 'jwt_token_000022' ,'9292993','2019-09-09 09:09:09','2019-09-09 09:09:09','UD000001',1);
insert into user_app_data values('UA000044','微信用户: 王五','SU000022','store',1,'MXWR','WechatUser','WU000021','/link/to/app','1');
insert into user_app_data values('UA000045','我的账户','SU000022','lock',1,'MXWR','SecUser','SU000022','/link/to/app','1');
insert into sec_user_data values('SU000023','User000023','13900000023','1000023@qq.com','D5405F91AA444B65AE234F0AA39FF8A43A2F0CF28F238479A0AC08D9C292629E', 'weixin_openid_000023', 'weixin_appid_000023', 'jwt_token_000023' ,'9292993','2019-09-09 09:09:09','2019-09-09 09:09:09','UD000001',1);
insert into user_app_data values('UA000046','微信用户: 张三','SU000023','store',1,'MXWR','WechatUser','WU000022','/link/to/app','1');
insert into user_app_data values('UA000047','我的账户','SU000023','lock',1,'MXWR','SecUser','SU000023','/link/to/app','1');
insert into sec_user_data values('SU000024','User000024','13900000024','1000024@qq.com','663EE204DCB9B63399177CA2CF9E0206E286B7ECBF8E9A9874F50A9A863E9B02', 'weixin_openid_000024', 'weixin_appid_000024', 'jwt_token_000024' ,'9292993','2019-09-09 09:09:09','2019-09-09 09:09:09','UD000001',1);
insert into user_app_data values('UA000048','微信用户: 李四','SU000024','store',1,'MXWR','WechatUser','WU000023','/link/to/app','1');
insert into user_app_data values('UA000049','我的账户','SU000024','lock',1,'MXWR','SecUser','SU000024','/link/to/app','1');
insert into sec_user_data values('SU000025','User000025','13900000025','1000025@qq.com','E1D441F2F9DA5C7456A3D6F32097D0C29DEFF3FFCAB5CE40927FC12208CDABE0', 'weixin_openid_000025', 'weixin_appid_000025', 'jwt_token_000025' ,'9292993','2019-09-09 09:09:09','2019-09-09 09:09:09','UD000001',1);
insert into user_app_data values('UA000050','微信用户: 王五','SU000025','store',1,'MXWR','WechatUser','WU000024','/link/to/app','1');
insert into user_app_data values('UA000051','我的账户','SU000025','lock',1,'MXWR','SecUser','SU000025','/link/to/app','1');
insert into sec_user_data values('SU000026','User000026','13900000026','1000026@qq.com','21139DC63E2442E86BEE2231680F6F51E2ABC6C8A89190FD6565CAC0EAE1F4D7', 'weixin_openid_000026', 'weixin_appid_000026', 'jwt_token_000026' ,'9292993','2019-09-09 09:09:09','2019-09-09 09:09:09','UD000001',1);
insert into user_app_data values('UA000052','微信用户: 张三','SU000026','store',1,'MXWR','WechatUser','WU000025','/link/to/app','1');
insert into user_app_data values('UA000053','我的账户','SU000026','lock',1,'MXWR','SecUser','SU000026','/link/to/app','1');
insert into sec_user_data values('SU000027','User000027','13900000027','1000027@qq.com','E18E06C2B1A0C33B7445B28A6056263F174B1F7B471819FB2C047BB26F009897', 'weixin_openid_000027', 'weixin_appid_000027', 'jwt_token_000027' ,'9292993','2019-09-09 09:09:09','2019-09-09 09:09:09','UD000001',1);
insert into user_app_data values('UA000054','微信用户: 李四','SU000027','store',1,'MXWR','WechatUser','WU000026','/link/to/app','1');
insert into user_app_data values('UA000055','我的账户','SU000027','lock',1,'MXWR','SecUser','SU000027','/link/to/app','1');
insert into sec_user_data values('SU000028','User000028','13900000028','1000028@qq.com','CAF3CA28D098FF0C861BBA7A78B373A7468FE1F5581C2540B399D98A77A3C4F6', 'weixin_openid_000028', 'weixin_appid_000028', 'jwt_token_000028' ,'9292993','2019-09-09 09:09:09','2019-09-09 09:09:09','UD000001',1);
insert into user_app_data values('UA000056','微信用户: 王五','SU000028','store',1,'MXWR','WechatUser','WU000027','/link/to/app','1');
insert into user_app_data values('UA000057','我的账户','SU000028','lock',1,'MXWR','SecUser','SU000028','/link/to/app','1');
insert into sec_user_data values('SU000029','User000029','13900000029','1000029@qq.com','EA698F00DC96ED60831718D28FD8570EAEDA9E8A806CB645DA2EAB2854F0A88E', 'weixin_openid_000029', 'weixin_appid_000029', 'jwt_token_000029' ,'9292993','2019-09-09 09:09:09','2019-09-09 09:09:09','UD000001',1);
insert into user_app_data values('UA000058','微信用户: 张三','SU000029','store',1,'MXWR','WechatUser','WU000028','/link/to/app','1');
insert into user_app_data values('UA000059','我的账户','SU000029','lock',1,'MXWR','SecUser','SU000029','/link/to/app','1');
insert into sec_user_data values('SU000030','User000030','13900000030','1000030@qq.com','2AD02AB382A1850784152BBBF63FDE2F80504FCBD4FF76E314FA11F64F35B682', 'weixin_openid_000030', 'weixin_appid_000030', 'jwt_token_000030' ,'9292993','2019-09-09 09:09:09','2019-09-09 09:09:09','UD000001',1);
insert into user_app_data values('UA000060','微信用户: 李四','SU000030','store',1,'MXWR','WechatUser','WU000029','/link/to/app','1');
insert into user_app_data values('UA000061','我的账户','SU000030','lock',1,'MXWR','SecUser','SU000030','/link/to/app','1');
insert into sec_user_data values('SU000031','User000031','13900000031','1000031@qq.com','8F7AA9BA1C0A231BCF9BA1D48B091E21D630F744E05E89AD25E4C9FE9B2FF921', 'weixin_openid_000031', 'weixin_appid_000031', 'jwt_token_000031' ,'9292993','2019-09-09 09:09:09','2019-09-09 09:09:09','UD000001',1);
insert into user_app_data values('UA000062','微信用户: 王五','SU000031','store',1,'MXWR','WechatUser','WU000030','/link/to/app','1');
insert into user_app_data values('UA000063','我的账户','SU000031','lock',1,'MXWR','SecUser','SU000031','/link/to/app','1');
insert into sec_user_data values('SU000032','User000032','13900000032','1000032@qq.com','F801F74132C5952B0086E9EAB138914F70B6B08B885EFC250590D61A61C06886', 'weixin_openid_000032', 'weixin_appid_000032', 'jwt_token_000032' ,'9292993','2019-09-09 09:09:09','2019-09-09 09:09:09','UD000001',1);
insert into user_app_data values('UA000064','微信用户: 张三','SU000032','store',1,'MXWR','WechatUser','WU000031','/link/to/app','1');
insert into user_app_data values('UA000065','我的账户','SU000032','lock',1,'MXWR','SecUser','SU000032','/link/to/app','1');
insert into sec_user_data values('SU000033','User000033','13900000033','1000033@qq.com','C51F55D220A7181AD92382E935A9DC87592C1F769B727E655DE54E42D00DCB61', 'weixin_openid_000033', 'weixin_appid_000033', 'jwt_token_000033' ,'9292993','2019-09-09 09:09:09','2019-09-09 09:09:09','UD000001',1);
insert into user_app_data values('UA000066','微信用户: 李四','SU000033','store',1,'MXWR','WechatUser','WU000032','/link/to/app','1');
insert into user_app_data values('UA000067','我的账户','SU000033','lock',1,'MXWR','SecUser','SU000033','/link/to/app','1');


insert into change_request_type_data values
('CreateClass','CreateClass','CreateClass','user','2','Platform','{}','P000001',1),
('CreateQuestion','CreateQuestion','CreateQuestion','user','3','Platform','{}','P000001',1),
('RegisterStudent','RegisterStudent','RegisterStudent','user','4','Platform','{}','P000001',1),
('CreateSurvey','CreateSurvey','CreateSurvey','user','5','Platform','{}','P000001',1),
('Registerguardian','Registerguardian','Registerguardian','user','6','Platform','{}','P000001',1),
('AnswerSurvey','AnswerSurvey','AnswerSurvey','user','7','Platform','{}','P000001',1),
('AnswerQuestion','AnswerQuestion','AnswerQuestion','user','8','Platform','{}','P000001',1),
('RegisterTeacher','RegisterTeacher','RegisterTeacher','user','9','Platform','{}','P000001',1);

select mobile as `可用于登录的账号`, 'admin123' as `密码` from sec_user_data;

/*
| 角色        | 用户名           | 密码         |
| ------------- |:-------------:|:-------------------:|


*/



-- Mysql innodb's foreign key has index automatically 
create unique index idx4id_ver_of_platform on platform_data (id, version);

create unique index idx4id_ver_of_province on province_data (id, version);
create  index idx4create_time_of_province on province_data (create_time);

create unique index idx4id_ver_of_city on city_data (id, version);
create  index idx4create_time_of_city on city_data (create_time);

create unique index idx4id_ver_of_district on district_data (id, version);
create  index idx4create_time_of_district on district_data (create_time);

create unique index idx4id_ver_of_location on location_data (id, version);
create  index idx4latitude_of_location on location_data (latitude);
create  index idx4longitude_of_location on location_data (longitude);

create unique index idx4id_ver_of_school_class on school_class_data (id, version);
create  index idx4create_time_of_school_class on school_class_data (create_time);

create unique index idx4id_ver_of_teacher on teacher_data (id, version);
create  index idx4mobile_of_teacher on teacher_data (mobile);
create  index idx4create_time_of_teacher on teacher_data (create_time);

create unique index idx4id_ver_of_guardian on guardian_data (id, version);
create  index idx4mobile_of_guardian on guardian_data (mobile);
create  index idx4create_time_of_guardian on guardian_data (create_time);

create unique index idx4id_ver_of_question on question_data (id, version);

create unique index idx4id_ver_of_question_type on question_type_data (id, version);
create unique index idx4code_of_question_type on question_type_data (code);

create unique index idx4id_ver_of_question_source on question_source_data (id, version);
create unique index idx4code_of_question_source on question_source_data (code);

create unique index idx4id_ver_of_class_question on class_question_data (id, version);

create unique index idx4id_ver_of_class_daily_health_survey on class_daily_health_survey_data (id, version);
create  index idx4survey_time_of_class_daily_health_survey on class_daily_health_survey_data (survey_time);

create unique index idx4id_ver_of_daily_survey_question on daily_survey_question_data (id, version);

create unique index idx4id_ver_of_student on student_data (id, version);
create  index idx4student_id_of_student on student_data (student_id);

create unique index idx4id_ver_of_student_health_survey on student_health_survey_data (id, version);
create  index idx4answer_time_of_student_health_survey on student_health_survey_data (answer_time);
create  index idx4create_time_of_student_health_survey on student_health_survey_data (create_time);
create  index idx4last_update_time_of_student_health_survey on student_health_survey_data (last_update_time);

create unique index idx4id_ver_of_student_daily_answer on student_daily_answer_data (id, version);
create  index idx4create_time_of_student_daily_answer on student_daily_answer_data (create_time);
create  index idx4last_update_time_of_student_daily_answer on student_daily_answer_data (last_update_time);

create unique index idx4id_ver_of_survey_status on survey_status_data (id, version);
create unique index idx4code_of_survey_status on survey_status_data (code);

create unique index idx4id_ver_of_wechat_user on wechat_user_data (id, version);
create  index idx4create_time_of_wechat_user on wechat_user_data (create_time);

create unique index idx4id_ver_of_user_type on user_type_data (id, version);
create unique index idx4code_of_user_type on user_type_data (code);

create unique index idx4id_ver_of_wechat_login_info on wechat_login_info_data (id, version);
create  index idx4app_id_of_wechat_login_info on wechat_login_info_data (app_id);
create  index idx4open_id_of_wechat_login_info on wechat_login_info_data (open_id);
create  index idx4last_update_time_of_wechat_login_info on wechat_login_info_data (last_update_time);

create unique index idx4id_ver_of_change_request on change_request_data (id, version);
create  index idx4create_time_of_change_request on change_request_data (create_time);

create unique index idx4id_ver_of_change_request_type on change_request_type_data (id, version);
create unique index idx4code_of_change_request_type on change_request_type_data (code);
create  index idx4display_order_of_change_request_type on change_request_type_data (display_order);

create unique index idx4id_ver_of_user_domain on user_domain_data (id, version);

create unique index idx4id_ver_of_user_white_list on user_white_list_data (id, version);

create unique index idx4id_ver_of_sec_user on sec_user_data (id, version);
create unique index idx4login_of_sec_user on sec_user_data (login);
create unique index idx4email_of_sec_user on sec_user_data (email);
create unique index idx4mobile_of_sec_user on sec_user_data (mobile);
create  index idx4verification_code_of_sec_user on sec_user_data (verification_code);
create  index idx4verification_code_expire_of_sec_user on sec_user_data (verification_code_expire);
create  index idx4last_login_time_of_sec_user on sec_user_data (last_login_time);

create unique index idx4id_ver_of_user_app on user_app_data (id, version);
create  index idx4object_id_of_user_app on user_app_data (object_id);

create unique index idx4id_ver_of_quick_link on quick_link_data (id, version);
create  index idx4create_time_of_quick_link on quick_link_data (create_time);

create unique index idx4id_ver_of_list_access on list_access_data (id, version);

create unique index idx4id_ver_of_object_access on object_access_data (id, version);

create unique index idx4id_ver_of_login_history on login_history_data (id, version);
create  index idx4login_time_of_login_history on login_history_data (login_time);

create unique index idx4id_ver_of_generic_form on generic_form_data (id, version);

create unique index idx4id_ver_of_form_message on form_message_data (id, version);

create unique index idx4id_ver_of_form_field_message on form_field_message_data (id, version);

create unique index idx4id_ver_of_form_field on form_field_data (id, version);

create unique index idx4id_ver_of_form_action on form_action_data (id, version);

create unique index idx4id_ver_of_candidate_container on candidate_container_data (id, version);

create unique index idx4id_ver_of_candidate_element on candidate_element_data (id, version);

create unique index idx4id_ver_of_wechat_workapp_identify on wechat_workapp_identify_data (id, version);
create  index idx4corp_id_of_wechat_workapp_identify on wechat_workapp_identify_data (corp_id);
create  index idx4user_id_of_wechat_workapp_identify on wechat_workapp_identify_data (user_id);
create  index idx4create_time_of_wechat_workapp_identify on wechat_workapp_identify_data (create_time);
create  index idx4last_login_time_of_wechat_workapp_identify on wechat_workapp_identify_data (last_login_time);

create unique index idx4id_ver_of_wechat_miniapp_identify on wechat_miniapp_identify_data (id, version);
create  index idx4open_id_of_wechat_miniapp_identify on wechat_miniapp_identify_data (open_id);
create  index idx4app_id_of_wechat_miniapp_identify on wechat_miniapp_identify_data (app_id);
create  index idx4create_time_of_wechat_miniapp_identify on wechat_miniapp_identify_data (create_time);
create  index idx4last_login_time_of_wechat_miniapp_identify on wechat_miniapp_identify_data (last_login_time);
alter table platform_data add constraint pk4id_of_platform_data primary key (id);

alter table province_data add constraint pk4id_of_province_data primary key (id);
alter table province_data add constraint 
	fk4platform_of_province_data foreign key (platform) references platform_data(id) ON DELETE CASCADE ON UPDATE CASCADE;

alter table city_data add constraint pk4id_of_city_data primary key (id);
alter table city_data add constraint 
	fk4province_of_city_data foreign key (province) references province_data(id) ON DELETE CASCADE ON UPDATE CASCADE;
alter table city_data add constraint 
	fk4platform_of_city_data foreign key (platform) references platform_data(id) ON DELETE CASCADE ON UPDATE CASCADE;

alter table district_data add constraint pk4id_of_district_data primary key (id);
alter table district_data add constraint 
	fk4city_of_district_data foreign key (city) references city_data(id) ON DELETE CASCADE ON UPDATE CASCADE;
alter table district_data add constraint 
	fk4platform_of_district_data foreign key (platform) references platform_data(id) ON DELETE CASCADE ON UPDATE CASCADE;

alter table location_data add constraint pk4id_of_location_data primary key (id);
alter table location_data add constraint 
	fk4district_of_location_data foreign key (district) references district_data(id) ON DELETE CASCADE ON UPDATE CASCADE;
alter table location_data add constraint 
	fk4province_of_location_data foreign key (province) references province_data(id) ON DELETE CASCADE ON UPDATE CASCADE;

alter table school_class_data add constraint pk4id_of_school_class_data primary key (id);
alter table school_class_data add constraint 
	fk4class_teacher_of_school_class_data foreign key (class_teacher) references teacher_data(id) ON DELETE CASCADE ON UPDATE CASCADE;
alter table school_class_data add constraint 
	fk4platform_of_school_class_data foreign key (platform) references platform_data(id) ON DELETE CASCADE ON UPDATE CASCADE;
alter table school_class_data add constraint 
	fk4cq_of_school_class_data foreign key (cq) references change_request_data(id) ON DELETE CASCADE ON UPDATE CASCADE;

alter table teacher_data add constraint pk4id_of_teacher_data primary key (id);
alter table teacher_data add constraint 
	fk4platform_of_teacher_data foreign key (platform) references platform_data(id) ON DELETE CASCADE ON UPDATE CASCADE;
alter table teacher_data add constraint 
	fk4cq_of_teacher_data foreign key (cq) references change_request_data(id) ON DELETE CASCADE ON UPDATE CASCADE;

alter table guardian_data add constraint pk4id_of_guardian_data primary key (id);
alter table guardian_data add constraint 
	fk4address_of_guardian_data foreign key (address) references location_data(id) ON DELETE CASCADE ON UPDATE CASCADE;
alter table guardian_data add constraint 
	fk4wechat_user_of_guardian_data foreign key (wechat_user) references wechat_user_data(id) ON DELETE CASCADE ON UPDATE CASCADE;
alter table guardian_data add constraint 
	fk4platform_of_guardian_data foreign key (platform) references platform_data(id) ON DELETE CASCADE ON UPDATE CASCADE;
alter table guardian_data add constraint 
	fk4cq_of_guardian_data foreign key (cq) references change_request_data(id) ON DELETE CASCADE ON UPDATE CASCADE;

alter table question_data add constraint pk4id_of_question_data primary key (id);
alter table question_data add constraint 
	fk4question_type_of_question_data foreign key (question_type) references question_type_data(id) ON DELETE CASCADE ON UPDATE CASCADE;
alter table question_data add constraint 
	fk4platform_of_question_data foreign key (platform) references platform_data(id) ON DELETE CASCADE ON UPDATE CASCADE;

alter table question_type_data add constraint pk4id_of_question_type_data primary key (id);
alter table question_type_data add constraint 
	fk4platform_of_question_type_data foreign key (platform) references platform_data(id) ON DELETE CASCADE ON UPDATE CASCADE;

alter table question_source_data add constraint pk4id_of_question_source_data primary key (id);
alter table question_source_data add constraint 
	fk4platform_of_question_source_data foreign key (platform) references platform_data(id) ON DELETE CASCADE ON UPDATE CASCADE;

alter table class_question_data add constraint pk4id_of_class_question_data primary key (id);
alter table class_question_data add constraint 
	fk4question_type_of_class_question_data foreign key (question_type) references question_type_data(id) ON DELETE CASCADE ON UPDATE CASCADE;
alter table class_question_data add constraint 
	fk4question_source_of_class_question_data foreign key (question_source) references question_source_data(id) ON DELETE CASCADE ON UPDATE CASCADE;
alter table class_question_data add constraint 
	fk4creator_of_class_question_data foreign key (creator) references wechat_user_data(id) ON DELETE CASCADE ON UPDATE CASCADE;
alter table class_question_data add constraint 
	fk4cq_of_class_question_data foreign key (cq) references change_request_data(id) ON DELETE CASCADE ON UPDATE CASCADE;

alter table class_daily_health_survey_data add constraint pk4id_of_class_daily_health_survey_data primary key (id);
alter table class_daily_health_survey_data add constraint 
	fk4school_class_of_class_daily_health_survey_data foreign key (school_class) references school_class_data(id) ON DELETE CASCADE ON UPDATE CASCADE;
alter table class_daily_health_survey_data add constraint 
	fk4creator_of_class_daily_health_survey_data foreign key (creator) references wechat_user_data(id) ON DELETE CASCADE ON UPDATE CASCADE;
alter table class_daily_health_survey_data add constraint 
	fk4cq_of_class_daily_health_survey_data foreign key (cq) references change_request_data(id) ON DELETE CASCADE ON UPDATE CASCADE;

alter table daily_survey_question_data add constraint pk4id_of_daily_survey_question_data primary key (id);
alter table daily_survey_question_data add constraint 
	fk4question_type_of_daily_survey_question_data foreign key (question_type) references question_type_data(id) ON DELETE CASCADE ON UPDATE CASCADE;
alter table daily_survey_question_data add constraint 
	fk4class_daily_health_survey_of_daily_survey_question_data foreign key (class_daily_health_survey) references class_daily_health_survey_data(id) ON DELETE CASCADE ON UPDATE CASCADE;
alter table daily_survey_question_data add constraint 
	fk4class_question_of_daily_survey_question_data foreign key (class_question) references class_question_data(id) ON DELETE CASCADE ON UPDATE CASCADE;

alter table student_data add constraint pk4id_of_student_data primary key (id);
alter table student_data add constraint 
	fk4guardian_of_student_data foreign key (guardian) references guardian_data(id) ON DELETE CASCADE ON UPDATE CASCADE;
alter table student_data add constraint 
	fk4school_class_of_student_data foreign key (school_class) references school_class_data(id) ON DELETE CASCADE ON UPDATE CASCADE;
alter table student_data add constraint 
	fk4cq_of_student_data foreign key (cq) references change_request_data(id) ON DELETE CASCADE ON UPDATE CASCADE;

alter table student_health_survey_data add constraint pk4id_of_student_health_survey_data primary key (id);
alter table student_health_survey_data add constraint 
	fk4student_of_student_health_survey_data foreign key (student) references student_data(id) ON DELETE CASCADE ON UPDATE CASCADE;
alter table student_health_survey_data add constraint 
	fk4survey_status_of_student_health_survey_data foreign key (survey_status) references survey_status_data(id) ON DELETE CASCADE ON UPDATE CASCADE;
alter table student_health_survey_data add constraint 
	fk4school_class_of_student_health_survey_data foreign key (school_class) references school_class_data(id) ON DELETE CASCADE ON UPDATE CASCADE;
alter table student_health_survey_data add constraint 
	fk4class_daily_health_survey_of_student_health_survey_data foreign key (class_daily_health_survey) references class_daily_health_survey_data(id) ON DELETE CASCADE ON UPDATE CASCADE;
alter table student_health_survey_data add constraint 
	fk4cq_of_student_health_survey_data foreign key (cq) references change_request_data(id) ON DELETE CASCADE ON UPDATE CASCADE;

alter table student_daily_answer_data add constraint pk4id_of_student_daily_answer_data primary key (id);
alter table student_daily_answer_data add constraint 
	fk4student_health_survey_of_student_daily_answer_data foreign key (student_health_survey) references student_health_survey_data(id) ON DELETE CASCADE ON UPDATE CASCADE;
alter table student_daily_answer_data add constraint 
	fk4question_of_student_daily_answer_data foreign key (question) references daily_survey_question_data(id) ON DELETE CASCADE ON UPDATE CASCADE;
alter table student_daily_answer_data add constraint 
	fk4cq_of_student_daily_answer_data foreign key (cq) references change_request_data(id) ON DELETE CASCADE ON UPDATE CASCADE;

alter table survey_status_data add constraint pk4id_of_survey_status_data primary key (id);
alter table survey_status_data add constraint 
	fk4platform_of_survey_status_data foreign key (platform) references platform_data(id) ON DELETE CASCADE ON UPDATE CASCADE;

alter table wechat_user_data add constraint pk4id_of_wechat_user_data primary key (id);
alter table wechat_user_data add constraint 
	fk4address_of_wechat_user_data foreign key (address) references location_data(id) ON DELETE CASCADE ON UPDATE CASCADE;
alter table wechat_user_data add constraint 
	fk4user_type_of_wechat_user_data foreign key (user_type) references user_type_data(id) ON DELETE CASCADE ON UPDATE CASCADE;
alter table wechat_user_data add constraint 
	fk4platform_of_wechat_user_data foreign key (platform) references platform_data(id) ON DELETE CASCADE ON UPDATE CASCADE;

alter table user_type_data add constraint pk4id_of_user_type_data primary key (id);
alter table user_type_data add constraint 
	fk4platform_of_user_type_data foreign key (platform) references platform_data(id) ON DELETE CASCADE ON UPDATE CASCADE;

alter table wechat_login_info_data add constraint pk4id_of_wechat_login_info_data primary key (id);
alter table wechat_login_info_data add constraint 
	fk4wechat_user_of_wechat_login_info_data foreign key (wechat_user) references wechat_user_data(id) ON DELETE CASCADE ON UPDATE CASCADE;

alter table change_request_data add constraint pk4id_of_change_request_data primary key (id);
alter table change_request_data add constraint 
	fk4request_type_of_change_request_data foreign key (request_type) references change_request_type_data(id) ON DELETE CASCADE ON UPDATE CASCADE;
alter table change_request_data add constraint 
	fk4platform_of_change_request_data foreign key (platform) references platform_data(id) ON DELETE CASCADE ON UPDATE CASCADE;

alter table change_request_type_data add constraint pk4id_of_change_request_type_data primary key (id);
alter table change_request_type_data add constraint 
	fk4platform_of_change_request_type_data foreign key (platform) references platform_data(id) ON DELETE CASCADE ON UPDATE CASCADE;

alter table user_domain_data add constraint pk4id_of_user_domain_data primary key (id);

alter table user_white_list_data add constraint pk4id_of_user_white_list_data primary key (id);
alter table user_white_list_data add constraint 
	fk4domain_of_user_white_list_data foreign key (domain) references user_domain_data(id) ON DELETE CASCADE ON UPDATE CASCADE;

alter table sec_user_data add constraint pk4id_of_sec_user_data primary key (id);
alter table sec_user_data add constraint 
	fk4domain_of_sec_user_data foreign key (domain) references user_domain_data(id) ON DELETE CASCADE ON UPDATE CASCADE;

alter table user_app_data add constraint pk4id_of_user_app_data primary key (id);
alter table user_app_data add constraint 
	fk4sec_user_of_user_app_data foreign key (sec_user) references sec_user_data(id) ON DELETE CASCADE ON UPDATE CASCADE;

alter table quick_link_data add constraint pk4id_of_quick_link_data primary key (id);
alter table quick_link_data add constraint 
	fk4app_of_quick_link_data foreign key (app) references user_app_data(id) ON DELETE CASCADE ON UPDATE CASCADE;

alter table list_access_data add constraint pk4id_of_list_access_data primary key (id);
alter table list_access_data add constraint 
	fk4app_of_list_access_data foreign key (app) references user_app_data(id) ON DELETE CASCADE ON UPDATE CASCADE;

alter table object_access_data add constraint pk4id_of_object_access_data primary key (id);
alter table object_access_data add constraint 
	fk4app_of_object_access_data foreign key (app) references user_app_data(id) ON DELETE CASCADE ON UPDATE CASCADE;

alter table login_history_data add constraint pk4id_of_login_history_data primary key (id);
alter table login_history_data add constraint 
	fk4sec_user_of_login_history_data foreign key (sec_user) references sec_user_data(id) ON DELETE CASCADE ON UPDATE CASCADE;

alter table generic_form_data add constraint pk4id_of_generic_form_data primary key (id);

alter table form_message_data add constraint pk4id_of_form_message_data primary key (id);
alter table form_message_data add constraint 
	fk4form_of_form_message_data foreign key (form) references generic_form_data(id) ON DELETE CASCADE ON UPDATE CASCADE;

alter table form_field_message_data add constraint pk4id_of_form_field_message_data primary key (id);
alter table form_field_message_data add constraint 
	fk4form_of_form_field_message_data foreign key (form) references generic_form_data(id) ON DELETE CASCADE ON UPDATE CASCADE;

alter table form_field_data add constraint pk4id_of_form_field_data primary key (id);
alter table form_field_data add constraint 
	fk4form_of_form_field_data foreign key (form) references generic_form_data(id) ON DELETE CASCADE ON UPDATE CASCADE;

alter table form_action_data add constraint pk4id_of_form_action_data primary key (id);
alter table form_action_data add constraint 
	fk4form_of_form_action_data foreign key (form) references generic_form_data(id) ON DELETE CASCADE ON UPDATE CASCADE;

alter table candidate_container_data add constraint pk4id_of_candidate_container_data primary key (id);

alter table candidate_element_data add constraint pk4id_of_candidate_element_data primary key (id);
alter table candidate_element_data add constraint 
	fk4container_of_candidate_element_data foreign key (container) references candidate_container_data(id) ON DELETE CASCADE ON UPDATE CASCADE;

alter table wechat_workapp_identify_data add constraint pk4id_of_wechat_workapp_identify_data primary key (id);
alter table wechat_workapp_identify_data add constraint 
	fk4sec_user_of_wechat_workapp_identify_data foreign key (sec_user) references sec_user_data(id) ON DELETE CASCADE ON UPDATE CASCADE;

alter table wechat_miniapp_identify_data add constraint pk4id_of_wechat_miniapp_identify_data primary key (id);
alter table wechat_miniapp_identify_data add constraint 
	fk4sec_user_of_wechat_miniapp_identify_data foreign key (sec_user) references sec_user_data(id) ON DELETE CASCADE ON UPDATE CASCADE;
-- create extra index for time, number and mobile phone




create table info_lines(line varchar(400));

insert into info_lines values( '   SSSSSSSSSSSSSSS                                                                                                                  !!! ');
insert into info_lines values( ' SS:::::::::::::::S                                                                                                                !!:!!');
insert into info_lines values( 'S:::::SSSSSS::::::S                                                                                                                !:::!');
insert into info_lines values( 'S:::::S     SSSSSSS                                                                                                                !:::!');
insert into info_lines values( 'S:::::S            uuuuuu    uuuuuu      cccccccccccccccc    cccccccccccccccc    eeeeeeeeeeee        ssssssssss       ssssssssss   !:::!');
insert into info_lines values( 'S:::::S            u::::u    u::::u    cc:::::::::::::::c  cc:::::::::::::::c  ee::::::::::::ee    ss::::::::::s    ss::::::::::s  !:::!');
insert into info_lines values( ' S::::SSSS         u::::u    u::::u   c:::::::::::::::::c c:::::::::::::::::c e::::::eeeee:::::eess:::::::::::::s ss:::::::::::::s !:::!');
insert into info_lines values( '  SS::::::SSSSS    u::::u    u::::u  c:::::::cccccc:::::cc:::::::cccccc:::::ce::::::e     e:::::es::::::ssss:::::ss::::::ssss:::::s!:::!');
insert into info_lines values( '    SSS::::::::SS  u::::u    u::::u  c::::::c     cccccccc::::::c     ccccccce:::::::eeeee::::::e s:::::s  ssssss  s:::::s  ssssss !:::!');
insert into info_lines values( '       SSSSSS::::S u::::u    u::::u  c:::::c             c:::::c             e:::::::::::::::::e    s::::::s         s::::::s      !:::!');
insert into info_lines values( '            S:::::Su::::u    u::::u  c:::::c             c:::::c             e::::::eeeeeeeeeee        s::::::s         s::::::s   !!:!!');
insert into info_lines values( '            S:::::Su:::::uuuu:::::u  c::::::c     cccccccc::::::c     ccccccce:::::::e           ssssss   s:::::s ssssss   s:::::s  !!! ');
insert into info_lines values( 'SSSSSSS     S:::::Su:::::::::::::::uuc:::::::cccccc:::::cc:::::::cccccc:::::ce::::::::e          s:::::ssss::::::ss:::::ssss::::::s     ');
insert into info_lines values( 'S::::::SSSSSS:::::S u:::::::::::::::u c:::::::::::::::::c c:::::::::::::::::c e::::::::eeeeeeee  s::::::::::::::s s::::::::::::::s  !!! ');
insert into info_lines values( 'S:::::::::::::::SS   uu::::::::uu:::u  cc:::::::::::::::c  cc:::::::::::::::c  ee:::::::::::::e   s:::::::::::ss   s:::::::::::ss  !!:!!');
insert into info_lines values( ' SSSSSSSSSSSSSSS       uuuuuuuu  uuuu    cccccccccccccccc    cccccccccccccccc    eeeeeeeeeeeeee    sssssssssss      sssssssssss     !!! ');

select * from info_lines;
/* start with data patch */
/* The sql file is not found from: /home/philip/resin-3.1.12/webapps/sky/data-patch/health.sql */
update change_request_type_data set bind_types='Platform';
-- turn on safe mode
SET SQL_SAFE_UPDATES = 1;
-- change request type

/*
http://patorjk.com/software/taag/#p=testall&h=0&v=0&f=Graceful&t=Success!
   _____                                            _ 
  / ____|                                          | |
 | (___    _   _    ___    ___    ___   ___   ___  | |
  \\___   | | | |  / __|  / __|  / _  / __| / __| | |
  ____) | | |_| | | (__  | (__  |  __/ \\__  \\__  |_|
 |_____/   \\__,_|  \\___|  \\___|  \\___| |___/ |___/ (_)  
+----------+---------------------------------+---------------------+--------+
| Charset  | Description                     | Default collation   | Maxlen |
+----------+---------------------------------+---------------------+--------+
| gb2312   | GB2312 Simplified Chinese       | gb2312_chinese_ci   |      2 |
| gbk      | GBK Simplified Chinese          | gbk_chinese_ci      |      2 |
| utf8mb4  | UTF-8 Unicode                   | utf8mb4_general_ci  |      4 |
| utf32    | UTF-32 Unicode                  | utf32_general_ci    |      4 |
| gb18030  | China National Standard GB18030 | gb18030_chinese_ci  |      4 |
+----------+---------------------------------+---------------------+--------+

*/

