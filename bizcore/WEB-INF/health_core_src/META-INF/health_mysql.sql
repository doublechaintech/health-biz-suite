
-- BUILD WITH MODEL TIME 200203T1452
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

drop table  if exists teacher_data;
create table teacher_data (
	id                            	varchar(48)          not null            comment 'ID',
	name                          	varchar(20)                              comment '名称',
	mobile                        	varchar(44)                              comment '手机号码',
	school                        	varchar(99)                              comment '学校',
	school_class                  	varchar(99)                              comment '学校类',
	class_size                    	int                                      comment '班级规模',
	create_time                   	datetime                                 comment '创建时间',
	platform                      	varchar(48)                              comment '平台',
	user                          	varchar(48)                              comment '用户',
	change_request                	varchar(48)                              comment '变更请求',
	version                       	int                                      comment '版本'
	
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = "老师";
-- primary key will be created later for better import performance

drop table  if exists student_data;
create table student_data (
	id                            	varchar(48)          not null            comment 'ID',
	student_name                  	varchar(20)                              comment '学生的名字',
	student_number                	varchar(99)                              comment '学生数量',
	student_avatar                	varchar(512) CHARACTER SET ascii COLLATE ascii_general_ci                     comment '学生阿凡达',
	guardian_name                 	varchar(20)                              comment '监护人姓名',
	guardian_mobile               	varchar(44)                              comment '监护人手机',
	address                       	varchar(48)                              comment '地址',
	user                          	varchar(48)                              comment '用户',
	create_time                   	datetime                                 comment '创建时间',
	platform                      	varchar(48)                              comment '平台',
	change_request                	varchar(48)                              comment '变更请求',
	version                       	int                                      comment '版本'
	
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = "学生";
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
	creator                       	varchar(48)                              comment '创建人名称',
	cq                            	varchar(48)                              comment 'Cq',
	version                       	int                                      comment '版本'
	
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = "检查问题";
-- primary key will be created later for better import performance

drop table  if exists question_type_data;
create table question_type_data (
	id                            	varchar(48)          not null            comment 'ID',
	name                          	varchar(12)                              comment '名称',
	code                          	varchar(52)                              comment '编码',
	platform                      	varchar(48)                              comment '平台',
	version                       	int                                      comment '版本'
	
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = "问题类型";
-- primary key will be created later for better import performance

drop table  if exists class_daily_health_survey_data;
create table class_daily_health_survey_data (
	id                            	varchar(48)          not null            comment 'ID',
	name                          	varchar(88)                              comment '名称',
	teacher                       	varchar(48)                              comment '老师',
	survey_time                   	datetime                                 comment '调查的时间',
	creator                       	varchar(48)                              comment '创建人名称',
	change_request                	varchar(48)                              comment '变更请求',
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
	survey_question               	varchar(48)                              comment '调查的问题',
	version                       	int                                      comment '版本'
	
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = "每日调查问题";
-- primary key will be created later for better import performance

drop table  if exists student_health_survey_data;
create table student_health_survey_data (
	id                            	varchar(48)          not null            comment 'ID',
	student                       	varchar(48)                              comment '学生',
	answer_time                   	datetime                                 comment '回答时间',
	survey_status                 	varchar(48)                              comment '调查现状',
	teacher                       	varchar(48)                              comment '老师',
	class_daily_health_survey     	varchar(48)                              comment '每日健康调查',
	create_time                   	datetime                                 comment '创建时间',
	last_update_time              	datetime                                 comment '最后更新时间',
	change_request                	varchar(48)                              comment '变更请求',
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
	change_request                	varchar(48)                              comment '变更请求',
	version                       	int                                      comment '版本'
	
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = "学生每天回答";
-- primary key will be created later for better import performance

drop table  if exists survey_status_data;
create table survey_status_data (
	id                            	varchar(48)          not null            comment 'ID',
	name                          	varchar(12)                              comment '名称',
	code                          	varchar(48)                              comment '编码',
	platform                      	varchar(48)                              comment '平台',
	version                       	int                                      comment '版本'
	
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = "调查现状";
-- primary key will be created later for better import performance

drop table  if exists health_survey_report_data;
create table health_survey_report_data (
	id                            	varchar(48)          not null            comment 'ID',
	survey_name                   	varchar(88)                              comment '调查的名字',
	survey_time                   	datetime                                 comment '调查的时间',
	teacher_name                  	varchar(20)                              comment '老师的名字',
	school                        	varchar(16)                              comment '学校',
	school_class                  	varchar(99)                              comment '学校类',
	student_name                  	varchar(20)                              comment '学生的名字',
	student_number                	varchar(3)                               comment '学生数量',
	guardian_name                 	varchar(20)                              comment '监护人姓名',
	guardian_mobile               	varchar(44)                              comment '监护人手机',
	student                       	varchar(48)                              comment '学生',
	teacher                       	varchar(48)                              comment '老师',
	survey                        	varchar(48)                              comment '调查',
	version                       	int                                      comment '版本'
	
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = "健康调查报告";
-- primary key will be created later for better import performance

drop table  if exists student_answer_data;
create table student_answer_data (
	id                            	varchar(48)          not null            comment 'ID',
	health_survey_report          	varchar(48)                              comment '健康调查报告',
	daily_answer                  	varchar(48)                              comment '每天回答',
	question_topic                	varchar(99)                              comment '问题的话题',
	answer                        	varchar(99)                              comment '回答',
	version                       	int                                      comment '版本'
	
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = "学生回答";
-- primary key will be created later for better import performance

drop table  if exists user_data;
create table user_data (
	id                            	varchar(48)          not null            comment 'ID',
	name                          	varchar(200)                             comment '名称',
	avatar                        	varchar(512) CHARACTER SET ascii COLLATE ascii_general_ci                     comment '头像',
	address                       	varchar(48)                              comment '地址',
	create_time                   	datetime                                 comment '创建时间',
	platform                      	varchar(48)                              comment '平台',
	version                       	int                                      comment '版本'
	
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = "用户";
-- primary key will be created later for better import performance

drop table  if exists wechat_login_info_data;
create table wechat_login_info_data (
	id                            	varchar(48)          not null            comment 'ID',
	user                          	varchar(48)                              comment '用户',
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
	name                          	varchar(16)                              comment '名称',
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
	('P000001','四川省','P000001','2020-01-17 14:07:45','1'),
	('P000002','四川省0002','P000001','2020-01-20 06:01:35','1');

insert into city_data values
	('C000001','成都市','P000001','P000001','2020-01-17 04:04:41','1'),
	('C000002','成都市0002','P000001','P000001','2020-02-02 16:46:47','1'),
	('C000003','成都市0003','P000002','P000001','2020-01-28 23:31:08','1'),
	('C000004','成都市0004','P000002','P000001','2020-01-31 13:04:09','1');

insert into district_data values
	('D000001','高新区','C000001','P000001','2020-01-25 09:21:42','1'),
	('D000002','高新区0002','C000001','P000001','2020-01-30 04:27:26','1'),
	('D000003','高新区0003','C000002','P000001','2020-01-28 11:51:53','1'),
	('D000004','高新区0004','C000002','P000001','2020-01-29 15:08:24','1'),
	('D000005','高新区0005','C000003','P000001','2020-01-21 23:38:09','1'),
	('D000006','高新区0006','C000003','P000001','2020-01-15 01:06:22','1'),
	('D000007','高新区0007','C000004','P000001','2020-01-14 23:51:29','1'),
	('D000008','高新区0008','C000004','P000001','2020-01-21 03:06:50','1');

insert into location_data values
	('L000001','公司地址','四川省成都市高新区南华路100号','D000001','P000001','29.98266293860159','104.67665551040031','1'),
	('L000002','家庭地址','四川省成都市高新区南华路100号0002','D000001','P000001','30.578343615765355','105.98723151789997','1'),
	('L000003','公司地址','四川省成都市高新区南华路100号0003','D000002','P000001','30.02060698586895','105.40921441395211','1'),
	('L000004','家庭地址','四川省成都市高新区南华路100号0004','D000002','P000001','30.73135994032577','104.50829985389623','1'),
	('L000005','公司地址','四川省成都市高新区南华路100号0005','D000003','P000001','30.156793376097422','104.88924703679662','1'),
	('L000006','家庭地址','四川省成都市高新区南华路100号0006','D000003','P000001','30.709037830798074','104.45125374298067','1'),
	('L000007','公司地址','四川省成都市高新区南华路100号0007','D000004','P000001','29.475672587162105','104.38165666463959','1'),
	('L000008','家庭地址','四川省成都市高新区南华路100号0008','D000004','P000001','30.222206066842904','105.8213729206691','1'),
	('L000009','公司地址','四川省成都市高新区南华路100号0009','D000005','P000002','30.741506949548757','104.5628946254354','1'),
	('L000010','家庭地址','四川省成都市高新区南华路100号0010','D000005','P000002','29.611489744144627','103.65052254617376','1'),
	('L000011','公司地址','四川省成都市高新区南华路100号0011','D000006','P000002','29.579028781031568','103.28722329709892','1'),
	('L000012','家庭地址','四川省成都市高新区南华路100号0012','D000006','P000002','31.749376907125516','103.31671443236846','1'),
	('L000013','公司地址','四川省成都市高新区南华路100号0013','D000007','P000002','31.915938267615967','105.15622280959957','1'),
	('L000014','家庭地址','四川省成都市高新区南华路100号0014','D000007','P000002','29.589119039000437','104.67727330136624','1'),
	('L000015','公司地址','四川省成都市高新区南华路100号0015','D000008','P000002','29.683548615421987','105.05069223633721','1'),
	('L000016','家庭地址','四川省成都市高新区南华路100号0016','D000008','P000002','29.49300464087095','104.22895681511028','1');

insert into teacher_data values
	('T000001','白山水','18012341234','益州小学','教科院一年级5班','1','2020-02-02 13:33:09','P000001','U000001','CR000001','1'),
	('T000002','胡一刀','13900000002','大源中学','教科院二年级3班','1','2020-01-15 22:12:35','P000001','U000001','CR000001','1'),
	('T000003','苗人凤','13900000003','南山中学','教科院一年级5班','1','2020-01-22 19:18:47','P000001','U000002','CR000001','1'),
	('T000004','白山水','13900000004','益州小学','教科院二年级3班','1','2020-01-16 13:18:02','P000001','U000002','CR000001','1'),
	('T000005','胡一刀','13900000005','大源中学','教科院一年级5班','1','2020-01-26 16:38:08','P000001','U000003','CR000001','1'),
	('T000006','苗人凤','13900000006','南山中学','教科院二年级3班','1','2020-01-29 02:26:40','P000001','U000003','CR000001','1'),
	('T000007','白山水','13900000007','益州小学','教科院一年级5班','1','2020-01-22 13:06:08','P000001','U000004','CR000001','1'),
	('T000008','胡一刀','13900000008','大源中学','教科院二年级3班','1','2020-01-28 08:17:05','P000001','U000004','CR000001','1'),
	('T000009','苗人凤','13900000009','南山中学','教科院一年级5班','1','2020-01-18 14:25:07','P000001','U000005','CR000001','1'),
	('T000010','白山水','13900000010','益州小学','教科院二年级3班','1','2020-01-16 12:00:22','P000001','U000005','CR000001','1'),
	('T000011','胡一刀','13900000011','大源中学','教科院一年级5班','1','2020-01-25 12:01:56','P000001','U000006','CR000001','1'),
	('T000012','苗人凤','13900000012','南山中学','教科院二年级3班','1','2020-01-18 15:23:31','P000001','U000006','CR000001','1'),
	('T000013','白山水','13900000013','益州小学','教科院一年级5班','1','2020-01-14 05:22:31','P000001','U000007','CR000001','1'),
	('T000014','胡一刀','13900000014','大源中学','教科院二年级3班','1','2020-01-31 18:14:15','P000001','U000007','CR000001','1'),
	('T000015','苗人凤','13900000015','南山中学','教科院一年级5班','1','2020-01-21 16:42:02','P000001','U000008','CR000001','1'),
	('T000016','白山水','13900000016','益州小学','教科院二年级3班','1','2020-01-15 04:40:51','P000001','U000008','CR000001','1'),
	('T000017','胡一刀','13900000017','大源中学','教科院一年级5班','1','2020-01-16 18:17:34','P000001','U000009','CR000002','1'),
	('T000018','苗人凤','13900000018','南山中学','教科院二年级3班','1','2020-01-29 22:47:50','P000001','U000009','CR000002','1'),
	('T000019','白山水','13900000019','益州小学','教科院一年级5班','1','2020-01-17 11:47:31','P000001','U000010','CR000002','1'),
	('T000020','胡一刀','13900000020','大源中学','教科院二年级3班','1','2020-02-02 11:06:29','P000001','U000010','CR000002','1'),
	('T000021','苗人凤','13900000021','南山中学','教科院一年级5班','1','2020-01-14 20:38:09','P000001','U000011','CR000002','1'),
	('T000022','白山水','13900000022','益州小学','教科院二年级3班','1','2020-01-23 17:29:57','P000001','U000011','CR000002','1'),
	('T000023','胡一刀','13900000023','大源中学','教科院一年级5班','1','2020-02-02 12:49:40','P000001','U000012','CR000002','1'),
	('T000024','苗人凤','13900000024','南山中学','教科院二年级3班','1','2020-01-19 08:47:53','P000001','U000012','CR000002','1'),
	('T000025','白山水','13900000025','益州小学','教科院一年级5班','1','2020-01-16 01:33:09','P000001','U000013','CR000002','1'),
	('T000026','胡一刀','13900000026','大源中学','教科院二年级3班','1','2020-01-30 18:56:30','P000001','U000013','CR000002','1'),
	('T000027','苗人凤','13900000027','南山中学','教科院一年级5班','1','2020-01-21 05:13:19','P000001','U000014','CR000002','1'),
	('T000028','白山水','13900000028','益州小学','教科院二年级3班','1','2020-01-28 09:29:17','P000001','U000014','CR000002','1'),
	('T000029','胡一刀','13900000029','大源中学','教科院一年级5班','1','2020-01-20 05:29:32','P000001','U000015','CR000002','1'),
	('T000030','苗人凤','13900000030','南山中学','教科院二年级3班','1','2020-01-19 16:32:42','P000001','U000015','CR000002','1'),
	('T000031','白山水','13900000031','益州小学','教科院一年级5班','1','2020-01-21 02:30:06','P000001','U000016','CR000002','1'),
	('T000032','胡一刀','13900000032','大源中学','教科院二年级3班','1','2020-01-29 15:27:44','P000001','U000016','CR000002','1'),
	('T000033','苗人凤','13900000033','南山中学','教科院一年级5班','1','2020-01-29 02:11:49','P000001','U000017','CR000003','1'),
	('T000034','白山水','13900000034','益州小学','教科院二年级3班','1','2020-01-22 20:48:43','P000001','U000017','CR000003','1'),
	('T000035','胡一刀','13900000035','大源中学','教科院一年级5班','1','2020-01-16 05:57:42','P000001','U000018','CR000003','1'),
	('T000036','苗人凤','13900000036','南山中学','教科院二年级3班','1','2020-01-20 23:05:32','P000001','U000018','CR000003','1'),
	('T000037','白山水','13900000037','益州小学','教科院一年级5班','1','2020-01-18 13:26:12','P000001','U000019','CR000003','1'),
	('T000038','胡一刀','13900000038','大源中学','教科院二年级3班','1','2020-01-21 16:25:41','P000001','U000019','CR000003','1'),
	('T000039','苗人凤','13900000039','南山中学','教科院一年级5班','1','2020-02-02 19:25:11','P000001','U000020','CR000003','1'),
	('T000040','白山水','13900000040','益州小学','教科院二年级3班','1','2020-01-29 22:54:00','P000001','U000020','CR000003','1'),
	('T000041','胡一刀','13900000041','大源中学','教科院一年级5班','1','2020-01-24 16:14:58','P000001','U000021','CR000003','1'),
	('T000042','苗人凤','13900000042','南山中学','教科院二年级3班','1','2020-01-24 03:24:59','P000001','U000021','CR000003','1'),
	('T000043','白山水','13900000043','益州小学','教科院一年级5班','1','2020-01-24 12:56:05','P000001','U000022','CR000003','1'),
	('T000044','胡一刀','13900000044','大源中学','教科院二年级3班','1','2020-01-19 01:27:31','P000001','U000022','CR000003','1'),
	('T000045','苗人凤','13900000045','南山中学','教科院一年级5班','1','2020-01-17 22:39:15','P000001','U000023','CR000003','1'),
	('T000046','白山水','13900000046','益州小学','教科院二年级3班','1','2020-01-16 18:33:38','P000001','U000023','CR000003','1'),
	('T000047','胡一刀','13900000047','大源中学','教科院一年级5班','1','2020-01-18 19:09:20','P000001','U000024','CR000003','1'),
	('T000048','苗人凤','13900000048','南山中学','教科院二年级3班','1','2020-01-25 00:14:37','P000001','U000024','CR000003','1'),
	('T000049','白山水','13900000049','益州小学','教科院一年级5班','1','2020-02-03 12:48:09','P000001','U000025','CR000004','1'),
	('T000050','胡一刀','13900000050','大源中学','教科院二年级3班','1','2020-01-25 04:42:58','P000001','U000025','CR000004','1'),
	('T000051','苗人凤','13900000051','南山中学','教科院一年级5班','1','2020-02-02 22:09:59','P000001','U000026','CR000004','1'),
	('T000052','白山水','13900000052','益州小学','教科院二年级3班','1','2020-01-14 07:33:18','P000001','U000026','CR000004','1'),
	('T000053','胡一刀','13900000053','大源中学','教科院一年级5班','1','2020-01-31 00:20:02','P000001','U000027','CR000004','1'),
	('T000054','苗人凤','13900000054','南山中学','教科院二年级3班','1','2020-01-19 14:13:17','P000001','U000027','CR000004','1'),
	('T000055','白山水','13900000055','益州小学','教科院一年级5班','1','2020-01-23 13:17:40','P000001','U000028','CR000004','1'),
	('T000056','胡一刀','13900000056','大源中学','教科院二年级3班','1','2020-01-15 01:25:26','P000001','U000028','CR000004','1'),
	('T000057','苗人凤','13900000057','南山中学','教科院一年级5班','1','2020-01-29 16:06:03','P000001','U000029','CR000004','1'),
	('T000058','白山水','13900000058','益州小学','教科院二年级3班','1','2020-01-20 00:49:39','P000001','U000029','CR000004','1'),
	('T000059','胡一刀','13900000059','大源中学','教科院一年级5班','1','2020-02-02 14:08:29','P000001','U000030','CR000004','1'),
	('T000060','苗人凤','13900000060','南山中学','教科院二年级3班','1','2020-01-17 14:25:02','P000001','U000030','CR000004','1'),
	('T000061','白山水','13900000061','益州小学','教科院一年级5班','1','2020-01-31 23:49:38','P000001','U000031','CR000004','1'),
	('T000062','胡一刀','13900000062','大源中学','教科院二年级3班','1','2020-01-17 11:33:30','P000001','U000031','CR000004','1'),
	('T000063','苗人凤','13900000063','南山中学','教科院一年级5班','1','2020-02-03 03:18:44','P000001','U000032','CR000004','1'),
	('T000064','白山水','13900000064','益州小学','教科院二年级3班','1','2020-01-18 06:39:32','P000001','U000032','CR000004','1');

insert into student_data values
	('S000001','刘婵','A01','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','刘备','18012341234','L000001','U000001','2020-01-24 15:05:52','P000001','CR000001','1'),
	('S000002','刘阿斗','A010002','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','刘玄德','13900000002','L000001','U000001','2020-01-15 13:28:07','P000001','CR000001','1'),
	('S000003','李天一','A010003','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','张飞','13900000003','L000001','U000002','2020-01-24 00:24:10','P000001','CR000001','1'),
	('S000004','刘婵','A010004','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','张翼德','13900000004','L000001','U000002','2020-02-01 12:31:15','P000001','CR000001','1'),
	('S000005','刘阿斗','A010005','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','刘备','13900000005','L000002','U000003','2020-01-14 11:37:20','P000001','CR000001','1'),
	('S000006','李天一','A010006','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','刘玄德','13900000006','L000002','U000003','2020-01-19 04:17:58','P000001','CR000001','1'),
	('S000007','刘婵','A010007','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','张飞','13900000007','L000002','U000004','2020-01-19 08:39:03','P000001','CR000001','1'),
	('S000008','刘阿斗','A010008','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','张翼德','13900000008','L000002','U000004','2020-01-16 07:10:55','P000001','CR000001','1'),
	('S000009','李天一','A010009','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','刘备','13900000009','L000003','U000005','2020-01-12 20:48:22','P000001','CR000001','1'),
	('S000010','刘婵','A010010','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','刘玄德','13900000010','L000003','U000005','2020-01-28 11:02:23','P000001','CR000001','1'),
	('S000011','刘阿斗','A010011','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','张飞','13900000011','L000003','U000006','2020-01-15 19:21:54','P000001','CR000001','1'),
	('S000012','李天一','A010012','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','张翼德','13900000012','L000003','U000006','2020-01-26 05:16:43','P000001','CR000001','1'),
	('S000013','刘婵','A010013','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','刘备','13900000013','L000004','U000007','2020-01-16 22:50:42','P000001','CR000001','1'),
	('S000014','刘阿斗','A010014','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','刘玄德','13900000014','L000004','U000007','2020-01-29 10:35:33','P000001','CR000001','1'),
	('S000015','李天一','A010015','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','张飞','13900000015','L000004','U000008','2020-01-23 15:40:20','P000001','CR000001','1'),
	('S000016','刘婵','A010016','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','张翼德','13900000016','L000004','U000008','2020-01-20 08:43:40','P000001','CR000001','1'),
	('S000017','刘阿斗','A010017','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','刘备','13900000017','L000005','U000009','2020-01-27 14:11:50','P000001','CR000002','1'),
	('S000018','李天一','A010018','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','刘玄德','13900000018','L000005','U000009','2020-01-28 11:52:21','P000001','CR000002','1'),
	('S000019','刘婵','A010019','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','张飞','13900000019','L000005','U000010','2020-01-28 07:59:07','P000001','CR000002','1'),
	('S000020','刘阿斗','A010020','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','张翼德','13900000020','L000005','U000010','2020-01-18 00:12:17','P000001','CR000002','1'),
	('S000021','李天一','A010021','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','刘备','13900000021','L000006','U000011','2020-01-31 12:37:56','P000001','CR000002','1'),
	('S000022','刘婵','A010022','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','刘玄德','13900000022','L000006','U000011','2020-01-22 06:40:35','P000001','CR000002','1'),
	('S000023','刘阿斗','A010023','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','张飞','13900000023','L000006','U000012','2020-01-23 18:29:39','P000001','CR000002','1'),
	('S000024','李天一','A010024','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','张翼德','13900000024','L000006','U000012','2020-01-17 12:30:25','P000001','CR000002','1'),
	('S000025','刘婵','A010025','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','刘备','13900000025','L000007','U000013','2020-01-24 00:52:22','P000001','CR000002','1'),
	('S000026','刘阿斗','A010026','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','刘玄德','13900000026','L000007','U000013','2020-01-17 21:16:14','P000001','CR000002','1'),
	('S000027','李天一','A010027','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','张飞','13900000027','L000007','U000014','2020-02-01 11:57:26','P000001','CR000002','1'),
	('S000028','刘婵','A010028','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','张翼德','13900000028','L000007','U000014','2020-01-26 10:13:49','P000001','CR000002','1'),
	('S000029','刘阿斗','A010029','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','刘备','13900000029','L000008','U000015','2020-02-02 09:32:28','P000001','CR000002','1'),
	('S000030','李天一','A010030','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','刘玄德','13900000030','L000008','U000015','2020-01-21 11:47:09','P000001','CR000002','1'),
	('S000031','刘婵','A010031','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','张飞','13900000031','L000008','U000016','2020-02-02 19:51:53','P000001','CR000002','1'),
	('S000032','刘阿斗','A010032','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','张翼德','13900000032','L000008','U000016','2020-01-15 14:02:20','P000001','CR000002','1'),
	('S000033','李天一','A010033','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','刘备','13900000033','L000009','U000017','2020-01-30 14:13:55','P000001','CR000003','1'),
	('S000034','刘婵','A010034','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','刘玄德','13900000034','L000009','U000017','2020-01-22 04:19:49','P000001','CR000003','1'),
	('S000035','刘阿斗','A010035','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','张飞','13900000035','L000009','U000018','2020-02-02 21:37:58','P000001','CR000003','1'),
	('S000036','李天一','A010036','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','张翼德','13900000036','L000009','U000018','2020-01-23 09:24:13','P000001','CR000003','1'),
	('S000037','刘婵','A010037','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','刘备','13900000037','L000010','U000019','2020-01-31 11:10:51','P000001','CR000003','1'),
	('S000038','刘阿斗','A010038','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','刘玄德','13900000038','L000010','U000019','2020-01-21 22:16:52','P000001','CR000003','1'),
	('S000039','李天一','A010039','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','张飞','13900000039','L000010','U000020','2020-01-22 13:23:17','P000001','CR000003','1'),
	('S000040','刘婵','A010040','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','张翼德','13900000040','L000010','U000020','2020-01-13 01:23:03','P000001','CR000003','1'),
	('S000041','刘阿斗','A010041','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','刘备','13900000041','L000011','U000021','2020-01-25 04:21:45','P000001','CR000003','1'),
	('S000042','李天一','A010042','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','刘玄德','13900000042','L000011','U000021','2020-01-15 02:05:13','P000001','CR000003','1'),
	('S000043','刘婵','A010043','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','张飞','13900000043','L000011','U000022','2020-01-23 16:07:47','P000001','CR000003','1'),
	('S000044','刘阿斗','A010044','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','张翼德','13900000044','L000011','U000022','2020-02-01 11:16:30','P000001','CR000003','1'),
	('S000045','李天一','A010045','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','刘备','13900000045','L000012','U000023','2020-01-23 09:32:29','P000001','CR000003','1'),
	('S000046','刘婵','A010046','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','刘玄德','13900000046','L000012','U000023','2020-01-27 01:30:32','P000001','CR000003','1'),
	('S000047','刘阿斗','A010047','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','张飞','13900000047','L000012','U000024','2020-01-18 08:12:48','P000001','CR000003','1'),
	('S000048','李天一','A010048','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','张翼德','13900000048','L000012','U000024','2020-01-18 19:41:13','P000001','CR000003','1'),
	('S000049','刘婵','A010049','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','刘备','13900000049','L000013','U000025','2020-01-22 07:25:13','P000001','CR000004','1'),
	('S000050','刘阿斗','A010050','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','刘玄德','13900000050','L000013','U000025','2020-01-27 20:09:26','P000001','CR000004','1'),
	('S000051','李天一','A010051','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','张飞','13900000051','L000013','U000026','2020-01-21 16:58:37','P000001','CR000004','1'),
	('S000052','刘婵','A010052','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','张翼德','13900000052','L000013','U000026','2020-01-25 06:19:33','P000001','CR000004','1'),
	('S000053','刘阿斗','A010053','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','刘备','13900000053','L000014','U000027','2020-01-19 13:45:47','P000001','CR000004','1'),
	('S000054','李天一','A010054','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','刘玄德','13900000054','L000014','U000027','2020-02-02 19:49:00','P000001','CR000004','1'),
	('S000055','刘婵','A010055','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','张飞','13900000055','L000014','U000028','2020-01-31 09:20:00','P000001','CR000004','1'),
	('S000056','刘阿斗','A010056','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','张翼德','13900000056','L000014','U000028','2020-01-30 07:20:31','P000001','CR000004','1'),
	('S000057','李天一','A010057','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','刘备','13900000057','L000015','U000029','2020-01-28 08:50:51','P000001','CR000004','1'),
	('S000058','刘婵','A010058','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','刘玄德','13900000058','L000015','U000029','2020-01-30 03:26:30','P000001','CR000004','1'),
	('S000059','刘阿斗','A010059','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','张飞','13900000059','L000015','U000030','2020-01-19 01:17:10','P000001','CR000004','1'),
	('S000060','李天一','A010060','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','张翼德','13900000060','L000015','U000030','2020-01-20 03:27:06','P000001','CR000004','1'),
	('S000061','刘婵','A010061','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','刘备','13900000061','L000016','U000031','2020-01-26 04:11:06','P000001','CR000004','1'),
	('S000062','刘阿斗','A010062','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','刘玄德','13900000062','L000016','U000031','2020-01-25 08:23:03','P000001','CR000004','1'),
	('S000063','李天一','A010063','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','张飞','13900000063','L000016','U000032','2020-01-29 14:22:30','P000001','CR000004','1'),
	('S000064','刘婵','A010064','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','张翼德','13900000064','L000016','U000032','2020-01-15 18:06:05','P000001','CR000004','1');

insert into question_data values
	('Q000001','节假日是否到过武汉','SINGLE_SELECT','没有','有','不确定','不知道','P000001','U000001','CR000001','1'),
	('Q000002','家里是否有武汉的亲朋好友来访','SINGLE_SELECT','没有0002','有0002','不确定0002','不知道0002','P000001','U000001','CR000001','1'),
	('Q000003','是否有发热、发烧症状','SINGLE_SELECT','没有0003','有0003','不确定0003','不知道0003','P000001','U000002','CR000001','1'),
	('Q000004','节假日是否到过武汉','SINGLE_SELECT','没有0004','有0004','不确定0004','不知道0004','P000001','U000002','CR000001','1'),
	('Q000005','家里是否有武汉的亲朋好友来访','SINGLE_SELECT','没有0005','有0005','不确定0005','不知道0005','P000001','U000003','CR000001','1'),
	('Q000006','是否有发热、发烧症状','SINGLE_SELECT','没有0006','有0006','不确定0006','不知道0006','P000001','U000003','CR000001','1'),
	('Q000007','节假日是否到过武汉','SINGLE_SELECT','没有0007','有0007','不确定0007','不知道0007','P000001','U000004','CR000001','1'),
	('Q000008','家里是否有武汉的亲朋好友来访','SINGLE_SELECT','没有0008','有0008','不确定0008','不知道0008','P000001','U000004','CR000001','1'),
	('Q000009','是否有发热、发烧症状','SINGLE_SELECT','没有0009','有0009','不确定0009','不知道0009','P000001','U000005','CR000001','1'),
	('Q000010','节假日是否到过武汉','SINGLE_SELECT','没有0010','有0010','不确定0010','不知道0010','P000001','U000005','CR000001','1'),
	('Q000011','家里是否有武汉的亲朋好友来访','SINGLE_SELECT','没有0011','有0011','不确定0011','不知道0011','P000001','U000006','CR000001','1'),
	('Q000012','是否有发热、发烧症状','SINGLE_SELECT','没有0012','有0012','不确定0012','不知道0012','P000001','U000006','CR000001','1'),
	('Q000013','节假日是否到过武汉','SINGLE_SELECT','没有0013','有0013','不确定0013','不知道0013','P000001','U000007','CR000001','1'),
	('Q000014','家里是否有武汉的亲朋好友来访','SINGLE_SELECT','没有0014','有0014','不确定0014','不知道0014','P000001','U000007','CR000001','1'),
	('Q000015','是否有发热、发烧症状','SINGLE_SELECT','没有0015','有0015','不确定0015','不知道0015','P000001','U000008','CR000001','1'),
	('Q000016','节假日是否到过武汉','SINGLE_SELECT','没有0016','有0016','不确定0016','不知道0016','P000001','U000008','CR000001','1'),
	('Q000017','家里是否有武汉的亲朋好友来访','SINGLE_SELECT','没有0017','有0017','不确定0017','不知道0017','P000001','U000009','CR000002','1'),
	('Q000018','是否有发热、发烧症状','SINGLE_SELECT','没有0018','有0018','不确定0018','不知道0018','P000001','U000009','CR000002','1'),
	('Q000019','节假日是否到过武汉','SINGLE_SELECT','没有0019','有0019','不确定0019','不知道0019','P000001','U000010','CR000002','1'),
	('Q000020','家里是否有武汉的亲朋好友来访','SINGLE_SELECT','没有0020','有0020','不确定0020','不知道0020','P000001','U000010','CR000002','1'),
	('Q000021','是否有发热、发烧症状','SINGLE_SELECT','没有0021','有0021','不确定0021','不知道0021','P000001','U000011','CR000002','1'),
	('Q000022','节假日是否到过武汉','SINGLE_SELECT','没有0022','有0022','不确定0022','不知道0022','P000001','U000011','CR000002','1'),
	('Q000023','家里是否有武汉的亲朋好友来访','SINGLE_SELECT','没有0023','有0023','不确定0023','不知道0023','P000001','U000012','CR000002','1'),
	('Q000024','是否有发热、发烧症状','SINGLE_SELECT','没有0024','有0024','不确定0024','不知道0024','P000001','U000012','CR000002','1'),
	('Q000025','节假日是否到过武汉','SINGLE_SELECT','没有0025','有0025','不确定0025','不知道0025','P000001','U000013','CR000002','1'),
	('Q000026','家里是否有武汉的亲朋好友来访','SINGLE_SELECT','没有0026','有0026','不确定0026','不知道0026','P000001','U000013','CR000002','1'),
	('Q000027','是否有发热、发烧症状','SINGLE_SELECT','没有0027','有0027','不确定0027','不知道0027','P000001','U000014','CR000002','1'),
	('Q000028','节假日是否到过武汉','SINGLE_SELECT','没有0028','有0028','不确定0028','不知道0028','P000001','U000014','CR000002','1'),
	('Q000029','家里是否有武汉的亲朋好友来访','SINGLE_SELECT','没有0029','有0029','不确定0029','不知道0029','P000001','U000015','CR000002','1'),
	('Q000030','是否有发热、发烧症状','SINGLE_SELECT','没有0030','有0030','不确定0030','不知道0030','P000001','U000015','CR000002','1'),
	('Q000031','节假日是否到过武汉','SINGLE_SELECT','没有0031','有0031','不确定0031','不知道0031','P000001','U000016','CR000002','1'),
	('Q000032','家里是否有武汉的亲朋好友来访','SINGLE_SELECT','没有0032','有0032','不确定0032','不知道0032','P000001','U000016','CR000002','1'),
	('Q000033','是否有发热、发烧症状','TEXT_INPUT','没有0033','有0033','不确定0033','不知道0033','P000001','U000017','CR000003','1'),
	('Q000034','节假日是否到过武汉','TEXT_INPUT','没有0034','有0034','不确定0034','不知道0034','P000001','U000017','CR000003','1'),
	('Q000035','家里是否有武汉的亲朋好友来访','TEXT_INPUT','没有0035','有0035','不确定0035','不知道0035','P000001','U000018','CR000003','1'),
	('Q000036','是否有发热、发烧症状','TEXT_INPUT','没有0036','有0036','不确定0036','不知道0036','P000001','U000018','CR000003','1'),
	('Q000037','节假日是否到过武汉','TEXT_INPUT','没有0037','有0037','不确定0037','不知道0037','P000001','U000019','CR000003','1'),
	('Q000038','家里是否有武汉的亲朋好友来访','TEXT_INPUT','没有0038','有0038','不确定0038','不知道0038','P000001','U000019','CR000003','1'),
	('Q000039','是否有发热、发烧症状','TEXT_INPUT','没有0039','有0039','不确定0039','不知道0039','P000001','U000020','CR000003','1'),
	('Q000040','节假日是否到过武汉','TEXT_INPUT','没有0040','有0040','不确定0040','不知道0040','P000001','U000020','CR000003','1'),
	('Q000041','家里是否有武汉的亲朋好友来访','TEXT_INPUT','没有0041','有0041','不确定0041','不知道0041','P000001','U000021','CR000003','1'),
	('Q000042','是否有发热、发烧症状','TEXT_INPUT','没有0042','有0042','不确定0042','不知道0042','P000001','U000021','CR000003','1'),
	('Q000043','节假日是否到过武汉','TEXT_INPUT','没有0043','有0043','不确定0043','不知道0043','P000001','U000022','CR000003','1'),
	('Q000044','家里是否有武汉的亲朋好友来访','TEXT_INPUT','没有0044','有0044','不确定0044','不知道0044','P000001','U000022','CR000003','1'),
	('Q000045','是否有发热、发烧症状','TEXT_INPUT','没有0045','有0045','不确定0045','不知道0045','P000001','U000023','CR000003','1'),
	('Q000046','节假日是否到过武汉','TEXT_INPUT','没有0046','有0046','不确定0046','不知道0046','P000001','U000023','CR000003','1'),
	('Q000047','家里是否有武汉的亲朋好友来访','TEXT_INPUT','没有0047','有0047','不确定0047','不知道0047','P000001','U000024','CR000003','1'),
	('Q000048','是否有发热、发烧症状','TEXT_INPUT','没有0048','有0048','不确定0048','不知道0048','P000001','U000024','CR000003','1'),
	('Q000049','节假日是否到过武汉','TEXT_INPUT','没有0049','有0049','不确定0049','不知道0049','P000001','U000025','CR000004','1'),
	('Q000050','家里是否有武汉的亲朋好友来访','TEXT_INPUT','没有0050','有0050','不确定0050','不知道0050','P000001','U000025','CR000004','1'),
	('Q000051','是否有发热、发烧症状','TEXT_INPUT','没有0051','有0051','不确定0051','不知道0051','P000001','U000026','CR000004','1'),
	('Q000052','节假日是否到过武汉','TEXT_INPUT','没有0052','有0052','不确定0052','不知道0052','P000001','U000026','CR000004','1'),
	('Q000053','家里是否有武汉的亲朋好友来访','TEXT_INPUT','没有0053','有0053','不确定0053','不知道0053','P000001','U000027','CR000004','1'),
	('Q000054','是否有发热、发烧症状','TEXT_INPUT','没有0054','有0054','不确定0054','不知道0054','P000001','U000027','CR000004','1'),
	('Q000055','节假日是否到过武汉','TEXT_INPUT','没有0055','有0055','不确定0055','不知道0055','P000001','U000028','CR000004','1'),
	('Q000056','家里是否有武汉的亲朋好友来访','TEXT_INPUT','没有0056','有0056','不确定0056','不知道0056','P000001','U000028','CR000004','1'),
	('Q000057','是否有发热、发烧症状','TEXT_INPUT','没有0057','有0057','不确定0057','不知道0057','P000001','U000029','CR000004','1'),
	('Q000058','节假日是否到过武汉','TEXT_INPUT','没有0058','有0058','不确定0058','不知道0058','P000001','U000029','CR000004','1'),
	('Q000059','家里是否有武汉的亲朋好友来访','TEXT_INPUT','没有0059','有0059','不确定0059','不知道0059','P000001','U000030','CR000004','1'),
	('Q000060','是否有发热、发烧症状','TEXT_INPUT','没有0060','有0060','不确定0060','不知道0060','P000001','U000030','CR000004','1'),
	('Q000061','节假日是否到过武汉','TEXT_INPUT','没有0061','有0061','不确定0061','不知道0061','P000001','U000031','CR000004','1'),
	('Q000062','家里是否有武汉的亲朋好友来访','TEXT_INPUT','没有0062','有0062','不确定0062','不知道0062','P000001','U000031','CR000004','1'),
	('Q000063','是否有发热、发烧症状','TEXT_INPUT','没有0063','有0063','不确定0063','不知道0063','P000001','U000032','CR000004','1'),
	('Q000064','节假日是否到过武汉','TEXT_INPUT','没有0064','有0064','不确定0064','不知道0064','P000001','U000032','CR000004','1');

insert into question_type_data values
	('SINGLE_SELECT','单选题','SINGLE_SELECT','P000001','1'),
	('TEXT_INPUT','简答题','TEXT_INPUT','P000001','1');

insert into class_daily_health_survey_data values
	('CDHS000001','2020年1月25日益州小学学生健康调查问卷','T000001','2020-01-31 21:57:50','U000001','CR000001','1'),
	('CDHS000002','2020年1月25日益州小学学生健康调查问卷0002','T000001','2020-01-29 08:15:10','U000001','CR000001','1'),
	('CDHS000003','2020年1月25日益州小学学生健康调查问卷0003','T000002','2020-01-25 20:38:16','U000001','CR000001','1'),
	('CDHS000004','2020年1月25日益州小学学生健康调查问卷0004','T000002','2020-01-26 11:55:21','U000001','CR000001','1'),
	('CDHS000005','2020年1月25日益州小学学生健康调查问卷0005','T000003','2020-01-14 12:30:59','U000002','CR000001','1'),
	('CDHS000006','2020年1月25日益州小学学生健康调查问卷0006','T000003','2020-01-15 20:20:21','U000002','CR000001','1'),
	('CDHS000007','2020年1月25日益州小学学生健康调查问卷0007','T000004','2020-01-13 00:46:50','U000002','CR000001','1'),
	('CDHS000008','2020年1月25日益州小学学生健康调查问卷0008','T000004','2020-01-17 07:38:21','U000002','CR000001','1'),
	('CDHS000009','2020年1月25日益州小学学生健康调查问卷0009','T000005','2020-01-25 22:04:26','U000003','CR000001','1'),
	('CDHS000010','2020年1月25日益州小学学生健康调查问卷0010','T000005','2020-01-29 15:25:57','U000003','CR000001','1'),
	('CDHS000011','2020年1月25日益州小学学生健康调查问卷0011','T000006','2020-01-20 16:52:35','U000003','CR000001','1'),
	('CDHS000012','2020年1月25日益州小学学生健康调查问卷0012','T000006','2020-01-30 15:37:43','U000003','CR000001','1'),
	('CDHS000013','2020年1月25日益州小学学生健康调查问卷0013','T000007','2020-01-18 18:21:56','U000004','CR000001','1'),
	('CDHS000014','2020年1月25日益州小学学生健康调查问卷0014','T000007','2020-01-22 03:13:16','U000004','CR000001','1'),
	('CDHS000015','2020年1月25日益州小学学生健康调查问卷0015','T000008','2020-01-18 08:39:51','U000004','CR000001','1'),
	('CDHS000016','2020年1月25日益州小学学生健康调查问卷0016','T000008','2020-01-15 11:17:07','U000004','CR000001','1'),
	('CDHS000017','2020年1月25日益州小学学生健康调查问卷0017','T000009','2020-01-20 16:25:58','U000005','CR000001','1'),
	('CDHS000018','2020年1月25日益州小学学生健康调查问卷0018','T000009','2020-01-24 05:49:47','U000005','CR000001','1'),
	('CDHS000019','2020年1月25日益州小学学生健康调查问卷0019','T000010','2020-01-18 15:48:12','U000005','CR000001','1'),
	('CDHS000020','2020年1月25日益州小学学生健康调查问卷0020','T000010','2020-01-28 00:26:04','U000005','CR000001','1'),
	('CDHS000021','2020年1月25日益州小学学生健康调查问卷0021','T000011','2020-01-27 16:34:29','U000006','CR000001','1'),
	('CDHS000022','2020年1月25日益州小学学生健康调查问卷0022','T000011','2020-01-16 23:03:37','U000006','CR000001','1'),
	('CDHS000023','2020年1月25日益州小学学生健康调查问卷0023','T000012','2020-01-21 13:38:15','U000006','CR000001','1'),
	('CDHS000024','2020年1月25日益州小学学生健康调查问卷0024','T000012','2020-02-01 19:50:56','U000006','CR000001','1'),
	('CDHS000025','2020年1月25日益州小学学生健康调查问卷0025','T000013','2020-02-02 11:24:20','U000007','CR000001','1'),
	('CDHS000026','2020年1月25日益州小学学生健康调查问卷0026','T000013','2020-01-23 14:05:55','U000007','CR000001','1'),
	('CDHS000027','2020年1月25日益州小学学生健康调查问卷0027','T000014','2020-01-21 01:33:24','U000007','CR000001','1'),
	('CDHS000028','2020年1月25日益州小学学生健康调查问卷0028','T000014','2020-01-23 20:26:57','U000007','CR000001','1'),
	('CDHS000029','2020年1月25日益州小学学生健康调查问卷0029','T000015','2020-01-20 18:47:34','U000008','CR000001','1'),
	('CDHS000030','2020年1月25日益州小学学生健康调查问卷0030','T000015','2020-02-03 07:44:13','U000008','CR000001','1'),
	('CDHS000031','2020年1月25日益州小学学生健康调查问卷0031','T000016','2020-01-19 05:41:36','U000008','CR000001','1'),
	('CDHS000032','2020年1月25日益州小学学生健康调查问卷0032','T000016','2020-01-19 07:02:38','U000008','CR000001','1'),
	('CDHS000033','2020年1月25日益州小学学生健康调查问卷0033','T000017','2020-01-24 11:58:03','U000009','CR000002','1'),
	('CDHS000034','2020年1月25日益州小学学生健康调查问卷0034','T000017','2020-01-18 20:11:01','U000009','CR000002','1'),
	('CDHS000035','2020年1月25日益州小学学生健康调查问卷0035','T000018','2020-01-31 12:16:23','U000009','CR000002','1'),
	('CDHS000036','2020年1月25日益州小学学生健康调查问卷0036','T000018','2020-01-18 02:50:04','U000009','CR000002','1'),
	('CDHS000037','2020年1月25日益州小学学生健康调查问卷0037','T000019','2020-01-22 08:56:07','U000010','CR000002','1'),
	('CDHS000038','2020年1月25日益州小学学生健康调查问卷0038','T000019','2020-02-01 23:05:13','U000010','CR000002','1'),
	('CDHS000039','2020年1月25日益州小学学生健康调查问卷0039','T000020','2020-01-26 17:38:28','U000010','CR000002','1'),
	('CDHS000040','2020年1月25日益州小学学生健康调查问卷0040','T000020','2020-01-29 11:49:58','U000010','CR000002','1'),
	('CDHS000041','2020年1月25日益州小学学生健康调查问卷0041','T000021','2020-01-17 03:16:56','U000011','CR000002','1'),
	('CDHS000042','2020年1月25日益州小学学生健康调查问卷0042','T000021','2020-01-26 02:51:42','U000011','CR000002','1'),
	('CDHS000043','2020年1月25日益州小学学生健康调查问卷0043','T000022','2020-01-21 10:36:59','U000011','CR000002','1'),
	('CDHS000044','2020年1月25日益州小学学生健康调查问卷0044','T000022','2020-01-17 07:45:56','U000011','CR000002','1'),
	('CDHS000045','2020年1月25日益州小学学生健康调查问卷0045','T000023','2020-01-31 05:57:58','U000012','CR000002','1'),
	('CDHS000046','2020年1月25日益州小学学生健康调查问卷0046','T000023','2020-01-27 16:13:06','U000012','CR000002','1'),
	('CDHS000047','2020年1月25日益州小学学生健康调查问卷0047','T000024','2020-01-21 18:46:42','U000012','CR000002','1'),
	('CDHS000048','2020年1月25日益州小学学生健康调查问卷0048','T000024','2020-01-26 08:00:21','U000012','CR000002','1'),
	('CDHS000049','2020年1月25日益州小学学生健康调查问卷0049','T000025','2020-02-01 14:41:46','U000013','CR000002','1'),
	('CDHS000050','2020年1月25日益州小学学生健康调查问卷0050','T000025','2020-02-02 23:06:03','U000013','CR000002','1'),
	('CDHS000051','2020年1月25日益州小学学生健康调查问卷0051','T000026','2020-01-18 22:58:07','U000013','CR000002','1'),
	('CDHS000052','2020年1月25日益州小学学生健康调查问卷0052','T000026','2020-02-03 01:09:20','U000013','CR000002','1'),
	('CDHS000053','2020年1月25日益州小学学生健康调查问卷0053','T000027','2020-01-17 02:50:35','U000014','CR000002','1'),
	('CDHS000054','2020年1月25日益州小学学生健康调查问卷0054','T000027','2020-01-17 23:54:18','U000014','CR000002','1'),
	('CDHS000055','2020年1月25日益州小学学生健康调查问卷0055','T000028','2020-01-20 16:05:50','U000014','CR000002','1'),
	('CDHS000056','2020年1月25日益州小学学生健康调查问卷0056','T000028','2020-01-29 17:30:29','U000014','CR000002','1'),
	('CDHS000057','2020年1月25日益州小学学生健康调查问卷0057','T000029','2020-01-22 05:53:47','U000015','CR000002','1'),
	('CDHS000058','2020年1月25日益州小学学生健康调查问卷0058','T000029','2020-02-01 10:00:17','U000015','CR000002','1'),
	('CDHS000059','2020年1月25日益州小学学生健康调查问卷0059','T000030','2020-01-18 01:16:15','U000015','CR000002','1'),
	('CDHS000060','2020年1月25日益州小学学生健康调查问卷0060','T000030','2020-01-18 11:33:55','U000015','CR000002','1'),
	('CDHS000061','2020年1月25日益州小学学生健康调查问卷0061','T000031','2020-01-27 10:40:08','U000016','CR000002','1'),
	('CDHS000062','2020年1月25日益州小学学生健康调查问卷0062','T000031','2020-01-15 19:19:28','U000016','CR000002','1'),
	('CDHS000063','2020年1月25日益州小学学生健康调查问卷0063','T000032','2020-01-19 23:06:35','U000016','CR000002','1'),
	('CDHS000064','2020年1月25日益州小学学生健康调查问卷0064','T000032','2020-01-20 23:26:01','U000016','CR000002','1'),
	('CDHS000065','2020年1月25日益州小学学生健康调查问卷0065','T000033','2020-01-29 17:33:59','U000017','CR000003','1'),
	('CDHS000066','2020年1月25日益州小学学生健康调查问卷0066','T000033','2020-01-22 20:42:33','U000017','CR000003','1'),
	('CDHS000067','2020年1月25日益州小学学生健康调查问卷0067','T000034','2020-01-24 05:42:27','U000017','CR000003','1'),
	('CDHS000068','2020年1月25日益州小学学生健康调查问卷0068','T000034','2020-01-22 03:42:42','U000017','CR000003','1'),
	('CDHS000069','2020年1月25日益州小学学生健康调查问卷0069','T000035','2020-01-15 16:29:19','U000018','CR000003','1'),
	('CDHS000070','2020年1月25日益州小学学生健康调查问卷0070','T000035','2020-01-19 00:12:06','U000018','CR000003','1'),
	('CDHS000071','2020年1月25日益州小学学生健康调查问卷0071','T000036','2020-01-23 18:59:06','U000018','CR000003','1'),
	('CDHS000072','2020年1月25日益州小学学生健康调查问卷0072','T000036','2020-01-26 03:36:03','U000018','CR000003','1'),
	('CDHS000073','2020年1月25日益州小学学生健康调查问卷0073','T000037','2020-02-01 22:40:58','U000019','CR000003','1'),
	('CDHS000074','2020年1月25日益州小学学生健康调查问卷0074','T000037','2020-01-22 16:59:34','U000019','CR000003','1'),
	('CDHS000075','2020年1月25日益州小学学生健康调查问卷0075','T000038','2020-02-01 07:25:12','U000019','CR000003','1'),
	('CDHS000076','2020年1月25日益州小学学生健康调查问卷0076','T000038','2020-01-23 12:23:13','U000019','CR000003','1'),
	('CDHS000077','2020年1月25日益州小学学生健康调查问卷0077','T000039','2020-01-14 20:10:20','U000020','CR000003','1'),
	('CDHS000078','2020年1月25日益州小学学生健康调查问卷0078','T000039','2020-01-24 18:25:47','U000020','CR000003','1'),
	('CDHS000079','2020年1月25日益州小学学生健康调查问卷0079','T000040','2020-01-31 22:29:25','U000020','CR000003','1'),
	('CDHS000080','2020年1月25日益州小学学生健康调查问卷0080','T000040','2020-01-18 23:25:25','U000020','CR000003','1'),
	('CDHS000081','2020年1月25日益州小学学生健康调查问卷0081','T000041','2020-01-15 05:59:41','U000021','CR000003','1'),
	('CDHS000082','2020年1月25日益州小学学生健康调查问卷0082','T000041','2020-01-18 19:58:11','U000021','CR000003','1'),
	('CDHS000083','2020年1月25日益州小学学生健康调查问卷0083','T000042','2020-01-22 06:38:45','U000021','CR000003','1'),
	('CDHS000084','2020年1月25日益州小学学生健康调查问卷0084','T000042','2020-02-02 02:24:40','U000021','CR000003','1'),
	('CDHS000085','2020年1月25日益州小学学生健康调查问卷0085','T000043','2020-01-15 10:25:27','U000022','CR000003','1'),
	('CDHS000086','2020年1月25日益州小学学生健康调查问卷0086','T000043','2020-01-27 23:25:41','U000022','CR000003','1'),
	('CDHS000087','2020年1月25日益州小学学生健康调查问卷0087','T000044','2020-01-22 15:49:30','U000022','CR000003','1'),
	('CDHS000088','2020年1月25日益州小学学生健康调查问卷0088','T000044','2020-02-02 16:35:42','U000022','CR000003','1'),
	('CDHS000089','2020年1月25日益州小学学生健康调查问卷0089','T000045','2020-02-02 09:01:27','U000023','CR000003','1'),
	('CDHS000090','2020年1月25日益州小学学生健康调查问卷0090','T000045','2020-01-26 06:01:59','U000023','CR000003','1'),
	('CDHS000091','2020年1月25日益州小学学生健康调查问卷0091','T000046','2020-01-21 01:52:14','U000023','CR000003','1'),
	('CDHS000092','2020年1月25日益州小学学生健康调查问卷0092','T000046','2020-01-28 09:13:36','U000023','CR000003','1'),
	('CDHS000093','2020年1月25日益州小学学生健康调查问卷0093','T000047','2020-01-29 12:57:37','U000024','CR000003','1'),
	('CDHS000094','2020年1月25日益州小学学生健康调查问卷0094','T000047','2020-01-21 19:42:27','U000024','CR000003','1'),
	('CDHS000095','2020年1月25日益州小学学生健康调查问卷0095','T000048','2020-01-13 12:02:15','U000024','CR000003','1'),
	('CDHS000096','2020年1月25日益州小学学生健康调查问卷0096','T000048','2020-01-29 19:06:00','U000024','CR000003','1'),
	('CDHS000097','2020年1月25日益州小学学生健康调查问卷0097','T000049','2020-02-01 11:43:47','U000025','CR000004','1'),
	('CDHS000098','2020年1月25日益州小学学生健康调查问卷0098','T000049','2020-01-19 17:48:24','U000025','CR000004','1'),
	('CDHS000099','2020年1月25日益州小学学生健康调查问卷0099','T000050','2020-01-20 09:46:59','U000025','CR000004','1'),
	('CDHS000100','2020年1月25日益州小学学生健康调查问卷0100','T000050','2020-01-18 12:33:24','U000025','CR000004','1'),
	('CDHS000101','2020年1月25日益州小学学生健康调查问卷0101','T000051','2020-01-17 12:05:36','U000026','CR000004','1'),
	('CDHS000102','2020年1月25日益州小学学生健康调查问卷0102','T000051','2020-01-22 18:30:17','U000026','CR000004','1'),
	('CDHS000103','2020年1月25日益州小学学生健康调查问卷0103','T000052','2020-01-25 23:55:01','U000026','CR000004','1'),
	('CDHS000104','2020年1月25日益州小学学生健康调查问卷0104','T000052','2020-01-19 15:52:09','U000026','CR000004','1'),
	('CDHS000105','2020年1月25日益州小学学生健康调查问卷0105','T000053','2020-01-18 15:46:26','U000027','CR000004','1'),
	('CDHS000106','2020年1月25日益州小学学生健康调查问卷0106','T000053','2020-01-15 08:04:44','U000027','CR000004','1'),
	('CDHS000107','2020年1月25日益州小学学生健康调查问卷0107','T000054','2020-01-28 06:25:37','U000027','CR000004','1'),
	('CDHS000108','2020年1月25日益州小学学生健康调查问卷0108','T000054','2020-01-19 00:32:10','U000027','CR000004','1'),
	('CDHS000109','2020年1月25日益州小学学生健康调查问卷0109','T000055','2020-01-16 14:22:00','U000028','CR000004','1'),
	('CDHS000110','2020年1月25日益州小学学生健康调查问卷0110','T000055','2020-01-16 09:09:16','U000028','CR000004','1'),
	('CDHS000111','2020年1月25日益州小学学生健康调查问卷0111','T000056','2020-01-26 13:15:04','U000028','CR000004','1'),
	('CDHS000112','2020年1月25日益州小学学生健康调查问卷0112','T000056','2020-02-02 17:59:43','U000028','CR000004','1'),
	('CDHS000113','2020年1月25日益州小学学生健康调查问卷0113','T000057','2020-01-21 19:19:58','U000029','CR000004','1'),
	('CDHS000114','2020年1月25日益州小学学生健康调查问卷0114','T000057','2020-01-14 16:28:48','U000029','CR000004','1'),
	('CDHS000115','2020年1月25日益州小学学生健康调查问卷0115','T000058','2020-02-02 14:32:09','U000029','CR000004','1'),
	('CDHS000116','2020年1月25日益州小学学生健康调查问卷0116','T000058','2020-01-28 11:32:19','U000029','CR000004','1'),
	('CDHS000117','2020年1月25日益州小学学生健康调查问卷0117','T000059','2020-01-27 20:40:38','U000030','CR000004','1'),
	('CDHS000118','2020年1月25日益州小学学生健康调查问卷0118','T000059','2020-02-01 09:39:52','U000030','CR000004','1'),
	('CDHS000119','2020年1月25日益州小学学生健康调查问卷0119','T000060','2020-01-30 18:44:19','U000030','CR000004','1'),
	('CDHS000120','2020年1月25日益州小学学生健康调查问卷0120','T000060','2020-01-29 06:13:21','U000030','CR000004','1'),
	('CDHS000121','2020年1月25日益州小学学生健康调查问卷0121','T000061','2020-02-01 07:11:34','U000031','CR000004','1'),
	('CDHS000122','2020年1月25日益州小学学生健康调查问卷0122','T000061','2020-02-03 10:52:10','U000031','CR000004','1'),
	('CDHS000123','2020年1月25日益州小学学生健康调查问卷0123','T000062','2020-01-19 07:02:46','U000031','CR000004','1'),
	('CDHS000124','2020年1月25日益州小学学生健康调查问卷0124','T000062','2020-01-17 09:54:33','U000031','CR000004','1'),
	('CDHS000125','2020年1月25日益州小学学生健康调查问卷0125','T000063','2020-02-02 03:55:44','U000032','CR000004','1'),
	('CDHS000126','2020年1月25日益州小学学生健康调查问卷0126','T000063','2020-01-15 15:21:21','U000032','CR000004','1'),
	('CDHS000127','2020年1月25日益州小学学生健康调查问卷0127','T000064','2020-01-16 12:14:54','U000032','CR000004','1'),
	('CDHS000128','2020年1月25日益州小学学生健康调查问卷0128','T000064','2020-01-28 16:37:32','U000032','CR000004','1');

insert into daily_survey_question_data values
	('DSQ000001','节假日是否到过武汉','SINGLE_SELECT','没有','有','不确定','不知道','CDHS000001','Q000001','1'),
	('DSQ000002','家里是否有武汉的亲朋好友来访','SINGLE_SELECT','没有0002','有0002','不确定0002','不知道0002','CDHS000001','Q000001','1'),
	('DSQ000003','是否有发热、发烧症状','SINGLE_SELECT','没有0003','有0003','不确定0003','不知道0003','CDHS000002','Q000001','1'),
	('DSQ000004','节假日是否到过武汉','SINGLE_SELECT','没有0004','有0004','不确定0004','不知道0004','CDHS000002','Q000001','1'),
	('DSQ000005','家里是否有武汉的亲朋好友来访','SINGLE_SELECT','没有0005','有0005','不确定0005','不知道0005','CDHS000003','Q000002','1'),
	('DSQ000006','是否有发热、发烧症状','SINGLE_SELECT','没有0006','有0006','不确定0006','不知道0006','CDHS000003','Q000002','1'),
	('DSQ000007','节假日是否到过武汉','SINGLE_SELECT','没有0007','有0007','不确定0007','不知道0007','CDHS000004','Q000002','1'),
	('DSQ000008','家里是否有武汉的亲朋好友来访','SINGLE_SELECT','没有0008','有0008','不确定0008','不知道0008','CDHS000004','Q000002','1'),
	('DSQ000009','是否有发热、发烧症状','SINGLE_SELECT','没有0009','有0009','不确定0009','不知道0009','CDHS000005','Q000003','1'),
	('DSQ000010','节假日是否到过武汉','SINGLE_SELECT','没有0010','有0010','不确定0010','不知道0010','CDHS000005','Q000003','1'),
	('DSQ000011','家里是否有武汉的亲朋好友来访','SINGLE_SELECT','没有0011','有0011','不确定0011','不知道0011','CDHS000006','Q000003','1'),
	('DSQ000012','是否有发热、发烧症状','SINGLE_SELECT','没有0012','有0012','不确定0012','不知道0012','CDHS000006','Q000003','1'),
	('DSQ000013','节假日是否到过武汉','SINGLE_SELECT','没有0013','有0013','不确定0013','不知道0013','CDHS000007','Q000004','1'),
	('DSQ000014','家里是否有武汉的亲朋好友来访','SINGLE_SELECT','没有0014','有0014','不确定0014','不知道0014','CDHS000007','Q000004','1'),
	('DSQ000015','是否有发热、发烧症状','SINGLE_SELECT','没有0015','有0015','不确定0015','不知道0015','CDHS000008','Q000004','1'),
	('DSQ000016','节假日是否到过武汉','SINGLE_SELECT','没有0016','有0016','不确定0016','不知道0016','CDHS000008','Q000004','1'),
	('DSQ000017','家里是否有武汉的亲朋好友来访','SINGLE_SELECT','没有0017','有0017','不确定0017','不知道0017','CDHS000009','Q000005','1'),
	('DSQ000018','是否有发热、发烧症状','SINGLE_SELECT','没有0018','有0018','不确定0018','不知道0018','CDHS000009','Q000005','1'),
	('DSQ000019','节假日是否到过武汉','SINGLE_SELECT','没有0019','有0019','不确定0019','不知道0019','CDHS000010','Q000005','1'),
	('DSQ000020','家里是否有武汉的亲朋好友来访','SINGLE_SELECT','没有0020','有0020','不确定0020','不知道0020','CDHS000010','Q000005','1'),
	('DSQ000021','是否有发热、发烧症状','SINGLE_SELECT','没有0021','有0021','不确定0021','不知道0021','CDHS000011','Q000006','1'),
	('DSQ000022','节假日是否到过武汉','SINGLE_SELECT','没有0022','有0022','不确定0022','不知道0022','CDHS000011','Q000006','1'),
	('DSQ000023','家里是否有武汉的亲朋好友来访','SINGLE_SELECT','没有0023','有0023','不确定0023','不知道0023','CDHS000012','Q000006','1'),
	('DSQ000024','是否有发热、发烧症状','SINGLE_SELECT','没有0024','有0024','不确定0024','不知道0024','CDHS000012','Q000006','1'),
	('DSQ000025','节假日是否到过武汉','SINGLE_SELECT','没有0025','有0025','不确定0025','不知道0025','CDHS000013','Q000007','1'),
	('DSQ000026','家里是否有武汉的亲朋好友来访','SINGLE_SELECT','没有0026','有0026','不确定0026','不知道0026','CDHS000013','Q000007','1'),
	('DSQ000027','是否有发热、发烧症状','SINGLE_SELECT','没有0027','有0027','不确定0027','不知道0027','CDHS000014','Q000007','1'),
	('DSQ000028','节假日是否到过武汉','SINGLE_SELECT','没有0028','有0028','不确定0028','不知道0028','CDHS000014','Q000007','1'),
	('DSQ000029','家里是否有武汉的亲朋好友来访','SINGLE_SELECT','没有0029','有0029','不确定0029','不知道0029','CDHS000015','Q000008','1'),
	('DSQ000030','是否有发热、发烧症状','SINGLE_SELECT','没有0030','有0030','不确定0030','不知道0030','CDHS000015','Q000008','1'),
	('DSQ000031','节假日是否到过武汉','SINGLE_SELECT','没有0031','有0031','不确定0031','不知道0031','CDHS000016','Q000008','1'),
	('DSQ000032','家里是否有武汉的亲朋好友来访','SINGLE_SELECT','没有0032','有0032','不确定0032','不知道0032','CDHS000016','Q000008','1'),
	('DSQ000033','是否有发热、发烧症状','SINGLE_SELECT','没有0033','有0033','不确定0033','不知道0033','CDHS000017','Q000009','1'),
	('DSQ000034','节假日是否到过武汉','SINGLE_SELECT','没有0034','有0034','不确定0034','不知道0034','CDHS000017','Q000009','1'),
	('DSQ000035','家里是否有武汉的亲朋好友来访','SINGLE_SELECT','没有0035','有0035','不确定0035','不知道0035','CDHS000018','Q000009','1'),
	('DSQ000036','是否有发热、发烧症状','SINGLE_SELECT','没有0036','有0036','不确定0036','不知道0036','CDHS000018','Q000009','1'),
	('DSQ000037','节假日是否到过武汉','SINGLE_SELECT','没有0037','有0037','不确定0037','不知道0037','CDHS000019','Q000010','1'),
	('DSQ000038','家里是否有武汉的亲朋好友来访','SINGLE_SELECT','没有0038','有0038','不确定0038','不知道0038','CDHS000019','Q000010','1'),
	('DSQ000039','是否有发热、发烧症状','SINGLE_SELECT','没有0039','有0039','不确定0039','不知道0039','CDHS000020','Q000010','1'),
	('DSQ000040','节假日是否到过武汉','SINGLE_SELECT','没有0040','有0040','不确定0040','不知道0040','CDHS000020','Q000010','1'),
	('DSQ000041','家里是否有武汉的亲朋好友来访','SINGLE_SELECT','没有0041','有0041','不确定0041','不知道0041','CDHS000021','Q000011','1'),
	('DSQ000042','是否有发热、发烧症状','SINGLE_SELECT','没有0042','有0042','不确定0042','不知道0042','CDHS000021','Q000011','1'),
	('DSQ000043','节假日是否到过武汉','SINGLE_SELECT','没有0043','有0043','不确定0043','不知道0043','CDHS000022','Q000011','1'),
	('DSQ000044','家里是否有武汉的亲朋好友来访','SINGLE_SELECT','没有0044','有0044','不确定0044','不知道0044','CDHS000022','Q000011','1'),
	('DSQ000045','是否有发热、发烧症状','SINGLE_SELECT','没有0045','有0045','不确定0045','不知道0045','CDHS000023','Q000012','1'),
	('DSQ000046','节假日是否到过武汉','SINGLE_SELECT','没有0046','有0046','不确定0046','不知道0046','CDHS000023','Q000012','1'),
	('DSQ000047','家里是否有武汉的亲朋好友来访','SINGLE_SELECT','没有0047','有0047','不确定0047','不知道0047','CDHS000024','Q000012','1'),
	('DSQ000048','是否有发热、发烧症状','SINGLE_SELECT','没有0048','有0048','不确定0048','不知道0048','CDHS000024','Q000012','1'),
	('DSQ000049','节假日是否到过武汉','SINGLE_SELECT','没有0049','有0049','不确定0049','不知道0049','CDHS000025','Q000013','1'),
	('DSQ000050','家里是否有武汉的亲朋好友来访','SINGLE_SELECT','没有0050','有0050','不确定0050','不知道0050','CDHS000025','Q000013','1'),
	('DSQ000051','是否有发热、发烧症状','SINGLE_SELECT','没有0051','有0051','不确定0051','不知道0051','CDHS000026','Q000013','1'),
	('DSQ000052','节假日是否到过武汉','SINGLE_SELECT','没有0052','有0052','不确定0052','不知道0052','CDHS000026','Q000013','1'),
	('DSQ000053','家里是否有武汉的亲朋好友来访','SINGLE_SELECT','没有0053','有0053','不确定0053','不知道0053','CDHS000027','Q000014','1'),
	('DSQ000054','是否有发热、发烧症状','SINGLE_SELECT','没有0054','有0054','不确定0054','不知道0054','CDHS000027','Q000014','1'),
	('DSQ000055','节假日是否到过武汉','SINGLE_SELECT','没有0055','有0055','不确定0055','不知道0055','CDHS000028','Q000014','1'),
	('DSQ000056','家里是否有武汉的亲朋好友来访','SINGLE_SELECT','没有0056','有0056','不确定0056','不知道0056','CDHS000028','Q000014','1'),
	('DSQ000057','是否有发热、发烧症状','SINGLE_SELECT','没有0057','有0057','不确定0057','不知道0057','CDHS000029','Q000015','1'),
	('DSQ000058','节假日是否到过武汉','SINGLE_SELECT','没有0058','有0058','不确定0058','不知道0058','CDHS000029','Q000015','1'),
	('DSQ000059','家里是否有武汉的亲朋好友来访','SINGLE_SELECT','没有0059','有0059','不确定0059','不知道0059','CDHS000030','Q000015','1'),
	('DSQ000060','是否有发热、发烧症状','SINGLE_SELECT','没有0060','有0060','不确定0060','不知道0060','CDHS000030','Q000015','1'),
	('DSQ000061','节假日是否到过武汉','SINGLE_SELECT','没有0061','有0061','不确定0061','不知道0061','CDHS000031','Q000016','1'),
	('DSQ000062','家里是否有武汉的亲朋好友来访','SINGLE_SELECT','没有0062','有0062','不确定0062','不知道0062','CDHS000031','Q000016','1'),
	('DSQ000063','是否有发热、发烧症状','SINGLE_SELECT','没有0063','有0063','不确定0063','不知道0063','CDHS000032','Q000016','1'),
	('DSQ000064','节假日是否到过武汉','SINGLE_SELECT','没有0064','有0064','不确定0064','不知道0064','CDHS000032','Q000016','1'),
	('DSQ000065','家里是否有武汉的亲朋好友来访','SINGLE_SELECT','没有0065','有0065','不确定0065','不知道0065','CDHS000033','Q000017','1'),
	('DSQ000066','是否有发热、发烧症状','SINGLE_SELECT','没有0066','有0066','不确定0066','不知道0066','CDHS000033','Q000017','1'),
	('DSQ000067','节假日是否到过武汉','SINGLE_SELECT','没有0067','有0067','不确定0067','不知道0067','CDHS000034','Q000017','1'),
	('DSQ000068','家里是否有武汉的亲朋好友来访','SINGLE_SELECT','没有0068','有0068','不确定0068','不知道0068','CDHS000034','Q000017','1'),
	('DSQ000069','是否有发热、发烧症状','SINGLE_SELECT','没有0069','有0069','不确定0069','不知道0069','CDHS000035','Q000018','1'),
	('DSQ000070','节假日是否到过武汉','SINGLE_SELECT','没有0070','有0070','不确定0070','不知道0070','CDHS000035','Q000018','1'),
	('DSQ000071','家里是否有武汉的亲朋好友来访','SINGLE_SELECT','没有0071','有0071','不确定0071','不知道0071','CDHS000036','Q000018','1'),
	('DSQ000072','是否有发热、发烧症状','SINGLE_SELECT','没有0072','有0072','不确定0072','不知道0072','CDHS000036','Q000018','1'),
	('DSQ000073','节假日是否到过武汉','SINGLE_SELECT','没有0073','有0073','不确定0073','不知道0073','CDHS000037','Q000019','1'),
	('DSQ000074','家里是否有武汉的亲朋好友来访','SINGLE_SELECT','没有0074','有0074','不确定0074','不知道0074','CDHS000037','Q000019','1'),
	('DSQ000075','是否有发热、发烧症状','SINGLE_SELECT','没有0075','有0075','不确定0075','不知道0075','CDHS000038','Q000019','1'),
	('DSQ000076','节假日是否到过武汉','SINGLE_SELECT','没有0076','有0076','不确定0076','不知道0076','CDHS000038','Q000019','1'),
	('DSQ000077','家里是否有武汉的亲朋好友来访','SINGLE_SELECT','没有0077','有0077','不确定0077','不知道0077','CDHS000039','Q000020','1'),
	('DSQ000078','是否有发热、发烧症状','SINGLE_SELECT','没有0078','有0078','不确定0078','不知道0078','CDHS000039','Q000020','1'),
	('DSQ000079','节假日是否到过武汉','SINGLE_SELECT','没有0079','有0079','不确定0079','不知道0079','CDHS000040','Q000020','1'),
	('DSQ000080','家里是否有武汉的亲朋好友来访','SINGLE_SELECT','没有0080','有0080','不确定0080','不知道0080','CDHS000040','Q000020','1'),
	('DSQ000081','是否有发热、发烧症状','SINGLE_SELECT','没有0081','有0081','不确定0081','不知道0081','CDHS000041','Q000021','1'),
	('DSQ000082','节假日是否到过武汉','SINGLE_SELECT','没有0082','有0082','不确定0082','不知道0082','CDHS000041','Q000021','1'),
	('DSQ000083','家里是否有武汉的亲朋好友来访','SINGLE_SELECT','没有0083','有0083','不确定0083','不知道0083','CDHS000042','Q000021','1'),
	('DSQ000084','是否有发热、发烧症状','SINGLE_SELECT','没有0084','有0084','不确定0084','不知道0084','CDHS000042','Q000021','1'),
	('DSQ000085','节假日是否到过武汉','SINGLE_SELECT','没有0085','有0085','不确定0085','不知道0085','CDHS000043','Q000022','1'),
	('DSQ000086','家里是否有武汉的亲朋好友来访','SINGLE_SELECT','没有0086','有0086','不确定0086','不知道0086','CDHS000043','Q000022','1'),
	('DSQ000087','是否有发热、发烧症状','SINGLE_SELECT','没有0087','有0087','不确定0087','不知道0087','CDHS000044','Q000022','1'),
	('DSQ000088','节假日是否到过武汉','SINGLE_SELECT','没有0088','有0088','不确定0088','不知道0088','CDHS000044','Q000022','1'),
	('DSQ000089','家里是否有武汉的亲朋好友来访','SINGLE_SELECT','没有0089','有0089','不确定0089','不知道0089','CDHS000045','Q000023','1'),
	('DSQ000090','是否有发热、发烧症状','SINGLE_SELECT','没有0090','有0090','不确定0090','不知道0090','CDHS000045','Q000023','1'),
	('DSQ000091','节假日是否到过武汉','SINGLE_SELECT','没有0091','有0091','不确定0091','不知道0091','CDHS000046','Q000023','1'),
	('DSQ000092','家里是否有武汉的亲朋好友来访','SINGLE_SELECT','没有0092','有0092','不确定0092','不知道0092','CDHS000046','Q000023','1'),
	('DSQ000093','是否有发热、发烧症状','SINGLE_SELECT','没有0093','有0093','不确定0093','不知道0093','CDHS000047','Q000024','1'),
	('DSQ000094','节假日是否到过武汉','SINGLE_SELECT','没有0094','有0094','不确定0094','不知道0094','CDHS000047','Q000024','1'),
	('DSQ000095','家里是否有武汉的亲朋好友来访','SINGLE_SELECT','没有0095','有0095','不确定0095','不知道0095','CDHS000048','Q000024','1'),
	('DSQ000096','是否有发热、发烧症状','SINGLE_SELECT','没有0096','有0096','不确定0096','不知道0096','CDHS000048','Q000024','1'),
	('DSQ000097','节假日是否到过武汉','SINGLE_SELECT','没有0097','有0097','不确定0097','不知道0097','CDHS000049','Q000025','1'),
	('DSQ000098','家里是否有武汉的亲朋好友来访','SINGLE_SELECT','没有0098','有0098','不确定0098','不知道0098','CDHS000049','Q000025','1'),
	('DSQ000099','是否有发热、发烧症状','SINGLE_SELECT','没有0099','有0099','不确定0099','不知道0099','CDHS000050','Q000025','1'),
	('DSQ000100','节假日是否到过武汉','SINGLE_SELECT','没有0100','有0100','不确定0100','不知道0100','CDHS000050','Q000025','1'),
	('DSQ000101','家里是否有武汉的亲朋好友来访','SINGLE_SELECT','没有0101','有0101','不确定0101','不知道0101','CDHS000051','Q000026','1'),
	('DSQ000102','是否有发热、发烧症状','SINGLE_SELECT','没有0102','有0102','不确定0102','不知道0102','CDHS000051','Q000026','1'),
	('DSQ000103','节假日是否到过武汉','SINGLE_SELECT','没有0103','有0103','不确定0103','不知道0103','CDHS000052','Q000026','1'),
	('DSQ000104','家里是否有武汉的亲朋好友来访','SINGLE_SELECT','没有0104','有0104','不确定0104','不知道0104','CDHS000052','Q000026','1'),
	('DSQ000105','是否有发热、发烧症状','SINGLE_SELECT','没有0105','有0105','不确定0105','不知道0105','CDHS000053','Q000027','1'),
	('DSQ000106','节假日是否到过武汉','SINGLE_SELECT','没有0106','有0106','不确定0106','不知道0106','CDHS000053','Q000027','1'),
	('DSQ000107','家里是否有武汉的亲朋好友来访','SINGLE_SELECT','没有0107','有0107','不确定0107','不知道0107','CDHS000054','Q000027','1'),
	('DSQ000108','是否有发热、发烧症状','SINGLE_SELECT','没有0108','有0108','不确定0108','不知道0108','CDHS000054','Q000027','1'),
	('DSQ000109','节假日是否到过武汉','SINGLE_SELECT','没有0109','有0109','不确定0109','不知道0109','CDHS000055','Q000028','1'),
	('DSQ000110','家里是否有武汉的亲朋好友来访','SINGLE_SELECT','没有0110','有0110','不确定0110','不知道0110','CDHS000055','Q000028','1'),
	('DSQ000111','是否有发热、发烧症状','SINGLE_SELECT','没有0111','有0111','不确定0111','不知道0111','CDHS000056','Q000028','1'),
	('DSQ000112','节假日是否到过武汉','SINGLE_SELECT','没有0112','有0112','不确定0112','不知道0112','CDHS000056','Q000028','1'),
	('DSQ000113','家里是否有武汉的亲朋好友来访','SINGLE_SELECT','没有0113','有0113','不确定0113','不知道0113','CDHS000057','Q000029','1'),
	('DSQ000114','是否有发热、发烧症状','SINGLE_SELECT','没有0114','有0114','不确定0114','不知道0114','CDHS000057','Q000029','1'),
	('DSQ000115','节假日是否到过武汉','SINGLE_SELECT','没有0115','有0115','不确定0115','不知道0115','CDHS000058','Q000029','1'),
	('DSQ000116','家里是否有武汉的亲朋好友来访','SINGLE_SELECT','没有0116','有0116','不确定0116','不知道0116','CDHS000058','Q000029','1'),
	('DSQ000117','是否有发热、发烧症状','SINGLE_SELECT','没有0117','有0117','不确定0117','不知道0117','CDHS000059','Q000030','1'),
	('DSQ000118','节假日是否到过武汉','SINGLE_SELECT','没有0118','有0118','不确定0118','不知道0118','CDHS000059','Q000030','1'),
	('DSQ000119','家里是否有武汉的亲朋好友来访','SINGLE_SELECT','没有0119','有0119','不确定0119','不知道0119','CDHS000060','Q000030','1'),
	('DSQ000120','是否有发热、发烧症状','SINGLE_SELECT','没有0120','有0120','不确定0120','不知道0120','CDHS000060','Q000030','1'),
	('DSQ000121','节假日是否到过武汉','SINGLE_SELECT','没有0121','有0121','不确定0121','不知道0121','CDHS000061','Q000031','1'),
	('DSQ000122','家里是否有武汉的亲朋好友来访','SINGLE_SELECT','没有0122','有0122','不确定0122','不知道0122','CDHS000061','Q000031','1'),
	('DSQ000123','是否有发热、发烧症状','SINGLE_SELECT','没有0123','有0123','不确定0123','不知道0123','CDHS000062','Q000031','1'),
	('DSQ000124','节假日是否到过武汉','SINGLE_SELECT','没有0124','有0124','不确定0124','不知道0124','CDHS000062','Q000031','1'),
	('DSQ000125','家里是否有武汉的亲朋好友来访','SINGLE_SELECT','没有0125','有0125','不确定0125','不知道0125','CDHS000063','Q000032','1'),
	('DSQ000126','是否有发热、发烧症状','SINGLE_SELECT','没有0126','有0126','不确定0126','不知道0126','CDHS000063','Q000032','1'),
	('DSQ000127','节假日是否到过武汉','SINGLE_SELECT','没有0127','有0127','不确定0127','不知道0127','CDHS000064','Q000032','1'),
	('DSQ000128','家里是否有武汉的亲朋好友来访','SINGLE_SELECT','没有0128','有0128','不确定0128','不知道0128','CDHS000064','Q000032','1'),
	('DSQ000129','是否有发热、发烧症状','TEXT_INPUT','没有0129','有0129','不确定0129','不知道0129','CDHS000065','Q000033','1'),
	('DSQ000130','节假日是否到过武汉','TEXT_INPUT','没有0130','有0130','不确定0130','不知道0130','CDHS000065','Q000033','1'),
	('DSQ000131','家里是否有武汉的亲朋好友来访','TEXT_INPUT','没有0131','有0131','不确定0131','不知道0131','CDHS000066','Q000033','1'),
	('DSQ000132','是否有发热、发烧症状','TEXT_INPUT','没有0132','有0132','不确定0132','不知道0132','CDHS000066','Q000033','1'),
	('DSQ000133','节假日是否到过武汉','TEXT_INPUT','没有0133','有0133','不确定0133','不知道0133','CDHS000067','Q000034','1'),
	('DSQ000134','家里是否有武汉的亲朋好友来访','TEXT_INPUT','没有0134','有0134','不确定0134','不知道0134','CDHS000067','Q000034','1'),
	('DSQ000135','是否有发热、发烧症状','TEXT_INPUT','没有0135','有0135','不确定0135','不知道0135','CDHS000068','Q000034','1'),
	('DSQ000136','节假日是否到过武汉','TEXT_INPUT','没有0136','有0136','不确定0136','不知道0136','CDHS000068','Q000034','1'),
	('DSQ000137','家里是否有武汉的亲朋好友来访','TEXT_INPUT','没有0137','有0137','不确定0137','不知道0137','CDHS000069','Q000035','1'),
	('DSQ000138','是否有发热、发烧症状','TEXT_INPUT','没有0138','有0138','不确定0138','不知道0138','CDHS000069','Q000035','1'),
	('DSQ000139','节假日是否到过武汉','TEXT_INPUT','没有0139','有0139','不确定0139','不知道0139','CDHS000070','Q000035','1'),
	('DSQ000140','家里是否有武汉的亲朋好友来访','TEXT_INPUT','没有0140','有0140','不确定0140','不知道0140','CDHS000070','Q000035','1'),
	('DSQ000141','是否有发热、发烧症状','TEXT_INPUT','没有0141','有0141','不确定0141','不知道0141','CDHS000071','Q000036','1'),
	('DSQ000142','节假日是否到过武汉','TEXT_INPUT','没有0142','有0142','不确定0142','不知道0142','CDHS000071','Q000036','1'),
	('DSQ000143','家里是否有武汉的亲朋好友来访','TEXT_INPUT','没有0143','有0143','不确定0143','不知道0143','CDHS000072','Q000036','1'),
	('DSQ000144','是否有发热、发烧症状','TEXT_INPUT','没有0144','有0144','不确定0144','不知道0144','CDHS000072','Q000036','1'),
	('DSQ000145','节假日是否到过武汉','TEXT_INPUT','没有0145','有0145','不确定0145','不知道0145','CDHS000073','Q000037','1'),
	('DSQ000146','家里是否有武汉的亲朋好友来访','TEXT_INPUT','没有0146','有0146','不确定0146','不知道0146','CDHS000073','Q000037','1'),
	('DSQ000147','是否有发热、发烧症状','TEXT_INPUT','没有0147','有0147','不确定0147','不知道0147','CDHS000074','Q000037','1'),
	('DSQ000148','节假日是否到过武汉','TEXT_INPUT','没有0148','有0148','不确定0148','不知道0148','CDHS000074','Q000037','1'),
	('DSQ000149','家里是否有武汉的亲朋好友来访','TEXT_INPUT','没有0149','有0149','不确定0149','不知道0149','CDHS000075','Q000038','1'),
	('DSQ000150','是否有发热、发烧症状','TEXT_INPUT','没有0150','有0150','不确定0150','不知道0150','CDHS000075','Q000038','1'),
	('DSQ000151','节假日是否到过武汉','TEXT_INPUT','没有0151','有0151','不确定0151','不知道0151','CDHS000076','Q000038','1'),
	('DSQ000152','家里是否有武汉的亲朋好友来访','TEXT_INPUT','没有0152','有0152','不确定0152','不知道0152','CDHS000076','Q000038','1'),
	('DSQ000153','是否有发热、发烧症状','TEXT_INPUT','没有0153','有0153','不确定0153','不知道0153','CDHS000077','Q000039','1'),
	('DSQ000154','节假日是否到过武汉','TEXT_INPUT','没有0154','有0154','不确定0154','不知道0154','CDHS000077','Q000039','1'),
	('DSQ000155','家里是否有武汉的亲朋好友来访','TEXT_INPUT','没有0155','有0155','不确定0155','不知道0155','CDHS000078','Q000039','1'),
	('DSQ000156','是否有发热、发烧症状','TEXT_INPUT','没有0156','有0156','不确定0156','不知道0156','CDHS000078','Q000039','1'),
	('DSQ000157','节假日是否到过武汉','TEXT_INPUT','没有0157','有0157','不确定0157','不知道0157','CDHS000079','Q000040','1'),
	('DSQ000158','家里是否有武汉的亲朋好友来访','TEXT_INPUT','没有0158','有0158','不确定0158','不知道0158','CDHS000079','Q000040','1'),
	('DSQ000159','是否有发热、发烧症状','TEXT_INPUT','没有0159','有0159','不确定0159','不知道0159','CDHS000080','Q000040','1'),
	('DSQ000160','节假日是否到过武汉','TEXT_INPUT','没有0160','有0160','不确定0160','不知道0160','CDHS000080','Q000040','1'),
	('DSQ000161','家里是否有武汉的亲朋好友来访','TEXT_INPUT','没有0161','有0161','不确定0161','不知道0161','CDHS000081','Q000041','1'),
	('DSQ000162','是否有发热、发烧症状','TEXT_INPUT','没有0162','有0162','不确定0162','不知道0162','CDHS000081','Q000041','1'),
	('DSQ000163','节假日是否到过武汉','TEXT_INPUT','没有0163','有0163','不确定0163','不知道0163','CDHS000082','Q000041','1'),
	('DSQ000164','家里是否有武汉的亲朋好友来访','TEXT_INPUT','没有0164','有0164','不确定0164','不知道0164','CDHS000082','Q000041','1'),
	('DSQ000165','是否有发热、发烧症状','TEXT_INPUT','没有0165','有0165','不确定0165','不知道0165','CDHS000083','Q000042','1'),
	('DSQ000166','节假日是否到过武汉','TEXT_INPUT','没有0166','有0166','不确定0166','不知道0166','CDHS000083','Q000042','1'),
	('DSQ000167','家里是否有武汉的亲朋好友来访','TEXT_INPUT','没有0167','有0167','不确定0167','不知道0167','CDHS000084','Q000042','1'),
	('DSQ000168','是否有发热、发烧症状','TEXT_INPUT','没有0168','有0168','不确定0168','不知道0168','CDHS000084','Q000042','1'),
	('DSQ000169','节假日是否到过武汉','TEXT_INPUT','没有0169','有0169','不确定0169','不知道0169','CDHS000085','Q000043','1'),
	('DSQ000170','家里是否有武汉的亲朋好友来访','TEXT_INPUT','没有0170','有0170','不确定0170','不知道0170','CDHS000085','Q000043','1'),
	('DSQ000171','是否有发热、发烧症状','TEXT_INPUT','没有0171','有0171','不确定0171','不知道0171','CDHS000086','Q000043','1'),
	('DSQ000172','节假日是否到过武汉','TEXT_INPUT','没有0172','有0172','不确定0172','不知道0172','CDHS000086','Q000043','1'),
	('DSQ000173','家里是否有武汉的亲朋好友来访','TEXT_INPUT','没有0173','有0173','不确定0173','不知道0173','CDHS000087','Q000044','1'),
	('DSQ000174','是否有发热、发烧症状','TEXT_INPUT','没有0174','有0174','不确定0174','不知道0174','CDHS000087','Q000044','1'),
	('DSQ000175','节假日是否到过武汉','TEXT_INPUT','没有0175','有0175','不确定0175','不知道0175','CDHS000088','Q000044','1'),
	('DSQ000176','家里是否有武汉的亲朋好友来访','TEXT_INPUT','没有0176','有0176','不确定0176','不知道0176','CDHS000088','Q000044','1'),
	('DSQ000177','是否有发热、发烧症状','TEXT_INPUT','没有0177','有0177','不确定0177','不知道0177','CDHS000089','Q000045','1'),
	('DSQ000178','节假日是否到过武汉','TEXT_INPUT','没有0178','有0178','不确定0178','不知道0178','CDHS000089','Q000045','1'),
	('DSQ000179','家里是否有武汉的亲朋好友来访','TEXT_INPUT','没有0179','有0179','不确定0179','不知道0179','CDHS000090','Q000045','1'),
	('DSQ000180','是否有发热、发烧症状','TEXT_INPUT','没有0180','有0180','不确定0180','不知道0180','CDHS000090','Q000045','1'),
	('DSQ000181','节假日是否到过武汉','TEXT_INPUT','没有0181','有0181','不确定0181','不知道0181','CDHS000091','Q000046','1'),
	('DSQ000182','家里是否有武汉的亲朋好友来访','TEXT_INPUT','没有0182','有0182','不确定0182','不知道0182','CDHS000091','Q000046','1'),
	('DSQ000183','是否有发热、发烧症状','TEXT_INPUT','没有0183','有0183','不确定0183','不知道0183','CDHS000092','Q000046','1'),
	('DSQ000184','节假日是否到过武汉','TEXT_INPUT','没有0184','有0184','不确定0184','不知道0184','CDHS000092','Q000046','1'),
	('DSQ000185','家里是否有武汉的亲朋好友来访','TEXT_INPUT','没有0185','有0185','不确定0185','不知道0185','CDHS000093','Q000047','1'),
	('DSQ000186','是否有发热、发烧症状','TEXT_INPUT','没有0186','有0186','不确定0186','不知道0186','CDHS000093','Q000047','1'),
	('DSQ000187','节假日是否到过武汉','TEXT_INPUT','没有0187','有0187','不确定0187','不知道0187','CDHS000094','Q000047','1'),
	('DSQ000188','家里是否有武汉的亲朋好友来访','TEXT_INPUT','没有0188','有0188','不确定0188','不知道0188','CDHS000094','Q000047','1'),
	('DSQ000189','是否有发热、发烧症状','TEXT_INPUT','没有0189','有0189','不确定0189','不知道0189','CDHS000095','Q000048','1'),
	('DSQ000190','节假日是否到过武汉','TEXT_INPUT','没有0190','有0190','不确定0190','不知道0190','CDHS000095','Q000048','1'),
	('DSQ000191','家里是否有武汉的亲朋好友来访','TEXT_INPUT','没有0191','有0191','不确定0191','不知道0191','CDHS000096','Q000048','1'),
	('DSQ000192','是否有发热、发烧症状','TEXT_INPUT','没有0192','有0192','不确定0192','不知道0192','CDHS000096','Q000048','1'),
	('DSQ000193','节假日是否到过武汉','TEXT_INPUT','没有0193','有0193','不确定0193','不知道0193','CDHS000097','Q000049','1'),
	('DSQ000194','家里是否有武汉的亲朋好友来访','TEXT_INPUT','没有0194','有0194','不确定0194','不知道0194','CDHS000097','Q000049','1'),
	('DSQ000195','是否有发热、发烧症状','TEXT_INPUT','没有0195','有0195','不确定0195','不知道0195','CDHS000098','Q000049','1'),
	('DSQ000196','节假日是否到过武汉','TEXT_INPUT','没有0196','有0196','不确定0196','不知道0196','CDHS000098','Q000049','1'),
	('DSQ000197','家里是否有武汉的亲朋好友来访','TEXT_INPUT','没有0197','有0197','不确定0197','不知道0197','CDHS000099','Q000050','1'),
	('DSQ000198','是否有发热、发烧症状','TEXT_INPUT','没有0198','有0198','不确定0198','不知道0198','CDHS000099','Q000050','1'),
	('DSQ000199','节假日是否到过武汉','TEXT_INPUT','没有0199','有0199','不确定0199','不知道0199','CDHS000100','Q000050','1'),
	('DSQ000200','家里是否有武汉的亲朋好友来访','TEXT_INPUT','没有0200','有0200','不确定0200','不知道0200','CDHS000100','Q000050','1'),
	('DSQ000201','是否有发热、发烧症状','TEXT_INPUT','没有0201','有0201','不确定0201','不知道0201','CDHS000101','Q000051','1'),
	('DSQ000202','节假日是否到过武汉','TEXT_INPUT','没有0202','有0202','不确定0202','不知道0202','CDHS000101','Q000051','1'),
	('DSQ000203','家里是否有武汉的亲朋好友来访','TEXT_INPUT','没有0203','有0203','不确定0203','不知道0203','CDHS000102','Q000051','1'),
	('DSQ000204','是否有发热、发烧症状','TEXT_INPUT','没有0204','有0204','不确定0204','不知道0204','CDHS000102','Q000051','1'),
	('DSQ000205','节假日是否到过武汉','TEXT_INPUT','没有0205','有0205','不确定0205','不知道0205','CDHS000103','Q000052','1'),
	('DSQ000206','家里是否有武汉的亲朋好友来访','TEXT_INPUT','没有0206','有0206','不确定0206','不知道0206','CDHS000103','Q000052','1'),
	('DSQ000207','是否有发热、发烧症状','TEXT_INPUT','没有0207','有0207','不确定0207','不知道0207','CDHS000104','Q000052','1'),
	('DSQ000208','节假日是否到过武汉','TEXT_INPUT','没有0208','有0208','不确定0208','不知道0208','CDHS000104','Q000052','1'),
	('DSQ000209','家里是否有武汉的亲朋好友来访','TEXT_INPUT','没有0209','有0209','不确定0209','不知道0209','CDHS000105','Q000053','1'),
	('DSQ000210','是否有发热、发烧症状','TEXT_INPUT','没有0210','有0210','不确定0210','不知道0210','CDHS000105','Q000053','1'),
	('DSQ000211','节假日是否到过武汉','TEXT_INPUT','没有0211','有0211','不确定0211','不知道0211','CDHS000106','Q000053','1'),
	('DSQ000212','家里是否有武汉的亲朋好友来访','TEXT_INPUT','没有0212','有0212','不确定0212','不知道0212','CDHS000106','Q000053','1'),
	('DSQ000213','是否有发热、发烧症状','TEXT_INPUT','没有0213','有0213','不确定0213','不知道0213','CDHS000107','Q000054','1'),
	('DSQ000214','节假日是否到过武汉','TEXT_INPUT','没有0214','有0214','不确定0214','不知道0214','CDHS000107','Q000054','1'),
	('DSQ000215','家里是否有武汉的亲朋好友来访','TEXT_INPUT','没有0215','有0215','不确定0215','不知道0215','CDHS000108','Q000054','1'),
	('DSQ000216','是否有发热、发烧症状','TEXT_INPUT','没有0216','有0216','不确定0216','不知道0216','CDHS000108','Q000054','1'),
	('DSQ000217','节假日是否到过武汉','TEXT_INPUT','没有0217','有0217','不确定0217','不知道0217','CDHS000109','Q000055','1'),
	('DSQ000218','家里是否有武汉的亲朋好友来访','TEXT_INPUT','没有0218','有0218','不确定0218','不知道0218','CDHS000109','Q000055','1'),
	('DSQ000219','是否有发热、发烧症状','TEXT_INPUT','没有0219','有0219','不确定0219','不知道0219','CDHS000110','Q000055','1'),
	('DSQ000220','节假日是否到过武汉','TEXT_INPUT','没有0220','有0220','不确定0220','不知道0220','CDHS000110','Q000055','1'),
	('DSQ000221','家里是否有武汉的亲朋好友来访','TEXT_INPUT','没有0221','有0221','不确定0221','不知道0221','CDHS000111','Q000056','1'),
	('DSQ000222','是否有发热、发烧症状','TEXT_INPUT','没有0222','有0222','不确定0222','不知道0222','CDHS000111','Q000056','1'),
	('DSQ000223','节假日是否到过武汉','TEXT_INPUT','没有0223','有0223','不确定0223','不知道0223','CDHS000112','Q000056','1'),
	('DSQ000224','家里是否有武汉的亲朋好友来访','TEXT_INPUT','没有0224','有0224','不确定0224','不知道0224','CDHS000112','Q000056','1'),
	('DSQ000225','是否有发热、发烧症状','TEXT_INPUT','没有0225','有0225','不确定0225','不知道0225','CDHS000113','Q000057','1'),
	('DSQ000226','节假日是否到过武汉','TEXT_INPUT','没有0226','有0226','不确定0226','不知道0226','CDHS000113','Q000057','1'),
	('DSQ000227','家里是否有武汉的亲朋好友来访','TEXT_INPUT','没有0227','有0227','不确定0227','不知道0227','CDHS000114','Q000057','1'),
	('DSQ000228','是否有发热、发烧症状','TEXT_INPUT','没有0228','有0228','不确定0228','不知道0228','CDHS000114','Q000057','1'),
	('DSQ000229','节假日是否到过武汉','TEXT_INPUT','没有0229','有0229','不确定0229','不知道0229','CDHS000115','Q000058','1'),
	('DSQ000230','家里是否有武汉的亲朋好友来访','TEXT_INPUT','没有0230','有0230','不确定0230','不知道0230','CDHS000115','Q000058','1'),
	('DSQ000231','是否有发热、发烧症状','TEXT_INPUT','没有0231','有0231','不确定0231','不知道0231','CDHS000116','Q000058','1'),
	('DSQ000232','节假日是否到过武汉','TEXT_INPUT','没有0232','有0232','不确定0232','不知道0232','CDHS000116','Q000058','1'),
	('DSQ000233','家里是否有武汉的亲朋好友来访','TEXT_INPUT','没有0233','有0233','不确定0233','不知道0233','CDHS000117','Q000059','1'),
	('DSQ000234','是否有发热、发烧症状','TEXT_INPUT','没有0234','有0234','不确定0234','不知道0234','CDHS000117','Q000059','1'),
	('DSQ000235','节假日是否到过武汉','TEXT_INPUT','没有0235','有0235','不确定0235','不知道0235','CDHS000118','Q000059','1'),
	('DSQ000236','家里是否有武汉的亲朋好友来访','TEXT_INPUT','没有0236','有0236','不确定0236','不知道0236','CDHS000118','Q000059','1'),
	('DSQ000237','是否有发热、发烧症状','TEXT_INPUT','没有0237','有0237','不确定0237','不知道0237','CDHS000119','Q000060','1'),
	('DSQ000238','节假日是否到过武汉','TEXT_INPUT','没有0238','有0238','不确定0238','不知道0238','CDHS000119','Q000060','1'),
	('DSQ000239','家里是否有武汉的亲朋好友来访','TEXT_INPUT','没有0239','有0239','不确定0239','不知道0239','CDHS000120','Q000060','1'),
	('DSQ000240','是否有发热、发烧症状','TEXT_INPUT','没有0240','有0240','不确定0240','不知道0240','CDHS000120','Q000060','1'),
	('DSQ000241','节假日是否到过武汉','TEXT_INPUT','没有0241','有0241','不确定0241','不知道0241','CDHS000121','Q000061','1'),
	('DSQ000242','家里是否有武汉的亲朋好友来访','TEXT_INPUT','没有0242','有0242','不确定0242','不知道0242','CDHS000121','Q000061','1'),
	('DSQ000243','是否有发热、发烧症状','TEXT_INPUT','没有0243','有0243','不确定0243','不知道0243','CDHS000122','Q000061','1'),
	('DSQ000244','节假日是否到过武汉','TEXT_INPUT','没有0244','有0244','不确定0244','不知道0244','CDHS000122','Q000061','1'),
	('DSQ000245','家里是否有武汉的亲朋好友来访','TEXT_INPUT','没有0245','有0245','不确定0245','不知道0245','CDHS000123','Q000062','1'),
	('DSQ000246','是否有发热、发烧症状','TEXT_INPUT','没有0246','有0246','不确定0246','不知道0246','CDHS000123','Q000062','1'),
	('DSQ000247','节假日是否到过武汉','TEXT_INPUT','没有0247','有0247','不确定0247','不知道0247','CDHS000124','Q000062','1'),
	('DSQ000248','家里是否有武汉的亲朋好友来访','TEXT_INPUT','没有0248','有0248','不确定0248','不知道0248','CDHS000124','Q000062','1'),
	('DSQ000249','是否有发热、发烧症状','TEXT_INPUT','没有0249','有0249','不确定0249','不知道0249','CDHS000125','Q000063','1'),
	('DSQ000250','节假日是否到过武汉','TEXT_INPUT','没有0250','有0250','不确定0250','不知道0250','CDHS000125','Q000063','1'),
	('DSQ000251','家里是否有武汉的亲朋好友来访','TEXT_INPUT','没有0251','有0251','不确定0251','不知道0251','CDHS000126','Q000063','1'),
	('DSQ000252','是否有发热、发烧症状','TEXT_INPUT','没有0252','有0252','不确定0252','不知道0252','CDHS000126','Q000063','1'),
	('DSQ000253','节假日是否到过武汉','TEXT_INPUT','没有0253','有0253','不确定0253','不知道0253','CDHS000127','Q000064','1'),
	('DSQ000254','家里是否有武汉的亲朋好友来访','TEXT_INPUT','没有0254','有0254','不确定0254','不知道0254','CDHS000127','Q000064','1'),
	('DSQ000255','是否有发热、发烧症状','TEXT_INPUT','没有0255','有0255','不确定0255','不知道0255','CDHS000128','Q000064','1'),
	('DSQ000256','节假日是否到过武汉','TEXT_INPUT','没有0256','有0256','不确定0256','不知道0256','CDHS000128','Q000064','1');

insert into student_health_survey_data values
	('SHS000001','S000001','2020-02-03 02:28:17','UN_SUBMITTED','T000001','CDHS000001','2020-02-01 06:23:54','2020-02-01 18:34:30','CR000001','1'),
	('SHS000002','S000001','2020-02-01 05:37:10','UN_SUBMITTED','T000001','CDHS000001','2020-01-16 07:51:21','2020-01-18 14:14:22','CR000001','1'),
	('SHS000003','S000001','2020-01-18 21:18:53','UN_SUBMITTED','T000001','CDHS000002','2020-01-13 19:47:47','2020-01-22 00:06:11','CR000001','1'),
	('SHS000004','S000001','2020-01-28 01:48:52','UN_SUBMITTED','T000001','CDHS000002','2020-01-22 13:39:26','2020-01-19 06:27:47','CR000001','1'),
	('SHS000005','S000002','2020-01-20 21:15:28','UN_SUBMITTED','T000002','CDHS000003','2020-01-20 23:35:25','2020-01-19 23:09:45','CR000001','1'),
	('SHS000006','S000002','2020-01-25 19:27:09','UN_SUBMITTED','T000002','CDHS000003','2020-01-27 07:13:57','2020-01-24 08:46:19','CR000001','1'),
	('SHS000007','S000002','2020-01-30 07:55:44','UN_SUBMITTED','T000002','CDHS000004','2020-01-30 07:36:21','2020-01-19 18:05:10','CR000001','1'),
	('SHS000008','S000002','2020-01-22 15:11:09','UN_SUBMITTED','T000002','CDHS000004','2020-01-17 02:56:51','2020-01-29 18:10:51','CR000001','1'),
	('SHS000009','S000003','2020-01-28 11:51:20','UN_SUBMITTED','T000003','CDHS000005','2020-01-13 02:55:00','2020-01-19 13:50:56','CR000001','1'),
	('SHS000010','S000003','2020-01-31 09:06:17','UN_SUBMITTED','T000003','CDHS000005','2020-02-03 13:13:23','2020-02-01 08:52:36','CR000001','1'),
	('SHS000011','S000003','2020-01-25 01:54:30','UN_SUBMITTED','T000003','CDHS000006','2020-01-27 08:38:17','2020-01-23 05:14:48','CR000001','1'),
	('SHS000012','S000003','2020-02-03 08:54:22','UN_SUBMITTED','T000003','CDHS000006','2020-01-29 08:21:09','2020-01-30 20:12:52','CR000001','1'),
	('SHS000013','S000004','2020-01-27 23:59:39','UN_SUBMITTED','T000004','CDHS000007','2020-01-19 01:33:03','2020-02-03 11:42:57','CR000001','1'),
	('SHS000014','S000004','2020-01-30 15:54:56','UN_SUBMITTED','T000004','CDHS000007','2020-01-17 12:42:55','2020-01-15 17:20:12','CR000001','1'),
	('SHS000015','S000004','2020-02-01 11:13:14','UN_SUBMITTED','T000004','CDHS000008','2020-01-22 11:05:35','2020-01-18 02:03:12','CR000001','1'),
	('SHS000016','S000004','2020-01-27 19:33:40','UN_SUBMITTED','T000004','CDHS000008','2020-01-21 07:30:47','2020-01-19 04:59:28','CR000001','1'),
	('SHS000017','S000005','2020-01-22 14:37:54','UN_SUBMITTED','T000005','CDHS000009','2020-01-25 16:32:13','2020-01-22 09:33:46','CR000001','1'),
	('SHS000018','S000005','2020-02-01 09:31:37','UN_SUBMITTED','T000005','CDHS000009','2020-01-14 01:17:01','2020-01-12 18:04:47','CR000001','1'),
	('SHS000019','S000005','2020-01-19 01:08:16','UN_SUBMITTED','T000005','CDHS000010','2020-01-30 04:10:48','2020-01-23 01:27:41','CR000001','1'),
	('SHS000020','S000005','2020-01-24 03:59:53','UN_SUBMITTED','T000005','CDHS000010','2020-01-25 22:23:59','2020-01-17 11:41:15','CR000001','1'),
	('SHS000021','S000006','2020-01-14 21:09:09','UN_SUBMITTED','T000006','CDHS000011','2020-01-16 12:38:39','2020-01-30 09:48:45','CR000001','1'),
	('SHS000022','S000006','2020-02-01 22:06:03','UN_SUBMITTED','T000006','CDHS000011','2020-01-17 19:33:01','2020-01-16 14:24:17','CR000001','1'),
	('SHS000023','S000006','2020-01-29 03:32:17','UN_SUBMITTED','T000006','CDHS000012','2020-01-24 11:20:09','2020-01-31 15:33:39','CR000001','1'),
	('SHS000024','S000006','2020-01-17 23:33:49','UN_SUBMITTED','T000006','CDHS000012','2020-01-20 12:36:03','2020-02-02 04:40:26','CR000001','1'),
	('SHS000025','S000007','2020-01-21 03:48:05','UN_SUBMITTED','T000007','CDHS000013','2020-01-12 18:13:54','2020-01-28 12:19:34','CR000001','1'),
	('SHS000026','S000007','2020-01-17 21:41:29','UN_SUBMITTED','T000007','CDHS000013','2020-01-13 02:13:54','2020-01-23 08:26:54','CR000001','1'),
	('SHS000027','S000007','2020-01-20 02:18:47','UN_SUBMITTED','T000007','CDHS000014','2020-01-25 13:15:53','2020-01-26 08:46:55','CR000001','1'),
	('SHS000028','S000007','2020-01-28 17:04:18','UN_SUBMITTED','T000007','CDHS000014','2020-01-30 08:29:05','2020-01-16 09:10:51','CR000001','1'),
	('SHS000029','S000008','2020-01-19 00:40:23','UN_SUBMITTED','T000008','CDHS000015','2020-01-18 03:07:41','2020-01-22 02:21:23','CR000001','1'),
	('SHS000030','S000008','2020-01-31 05:17:25','UN_SUBMITTED','T000008','CDHS000015','2020-01-27 11:36:13','2020-01-25 15:39:45','CR000001','1'),
	('SHS000031','S000008','2020-01-24 08:59:57','UN_SUBMITTED','T000008','CDHS000016','2020-01-21 12:52:53','2020-01-28 03:45:29','CR000001','1'),
	('SHS000032','S000008','2020-01-15 11:25:40','UN_SUBMITTED','T000008','CDHS000016','2020-01-18 01:25:11','2020-01-26 02:00:17','CR000001','1'),
	('SHS000033','S000009','2020-01-25 02:30:51','UN_SUBMITTED','T000009','CDHS000017','2020-01-13 05:58:34','2020-01-28 05:16:05','CR000001','1'),
	('SHS000034','S000009','2020-01-18 00:28:31','UN_SUBMITTED','T000009','CDHS000017','2020-01-20 14:33:55','2020-01-26 09:15:43','CR000001','1'),
	('SHS000035','S000009','2020-01-21 17:28:49','UN_SUBMITTED','T000009','CDHS000018','2020-01-28 21:10:33','2020-01-13 03:20:38','CR000001','1'),
	('SHS000036','S000009','2020-01-24 23:44:11','UN_SUBMITTED','T000009','CDHS000018','2020-01-20 17:17:37','2020-01-21 16:31:52','CR000001','1'),
	('SHS000037','S000010','2020-01-17 09:06:57','UN_SUBMITTED','T000010','CDHS000019','2020-01-23 05:31:02','2020-01-21 00:25:45','CR000001','1'),
	('SHS000038','S000010','2020-02-02 20:34:29','UN_SUBMITTED','T000010','CDHS000019','2020-01-14 18:08:42','2020-01-28 22:43:54','CR000001','1'),
	('SHS000039','S000010','2020-01-25 01:06:38','UN_SUBMITTED','T000010','CDHS000020','2020-01-27 04:43:31','2020-01-27 04:15:03','CR000001','1'),
	('SHS000040','S000010','2020-01-17 12:47:12','UN_SUBMITTED','T000010','CDHS000020','2020-01-23 13:59:54','2020-01-25 01:35:07','CR000001','1'),
	('SHS000041','S000011','2020-01-22 03:46:01','UN_SUBMITTED','T000011','CDHS000021','2020-01-18 00:29:14','2020-01-17 01:50:19','CR000001','1'),
	('SHS000042','S000011','2020-01-18 07:25:27','UN_SUBMITTED','T000011','CDHS000021','2020-01-31 18:05:14','2020-01-27 22:49:18','CR000001','1'),
	('SHS000043','S000011','2020-01-16 11:29:18','UN_SUBMITTED','T000011','CDHS000022','2020-01-30 08:51:33','2020-01-28 19:30:18','CR000001','1'),
	('SHS000044','S000011','2020-01-25 11:27:47','UN_SUBMITTED','T000011','CDHS000022','2020-01-18 17:45:12','2020-02-03 12:38:12','CR000001','1'),
	('SHS000045','S000012','2020-01-15 00:17:50','UN_SUBMITTED','T000012','CDHS000023','2020-01-26 11:22:03','2020-01-16 04:46:44','CR000001','1'),
	('SHS000046','S000012','2020-01-25 06:07:25','UN_SUBMITTED','T000012','CDHS000023','2020-01-19 18:21:58','2020-01-14 05:44:39','CR000001','1'),
	('SHS000047','S000012','2020-01-18 19:21:49','UN_SUBMITTED','T000012','CDHS000024','2020-01-30 06:33:28','2020-01-24 15:14:43','CR000001','1'),
	('SHS000048','S000012','2020-01-22 01:14:52','UN_SUBMITTED','T000012','CDHS000024','2020-01-18 14:19:12','2020-01-27 18:03:43','CR000001','1'),
	('SHS000049','S000013','2020-01-31 23:29:30','UN_SUBMITTED','T000013','CDHS000025','2020-01-31 18:35:08','2020-01-26 02:53:09','CR000001','1'),
	('SHS000050','S000013','2020-01-12 23:42:54','UN_SUBMITTED','T000013','CDHS000025','2020-01-19 14:49:47','2020-01-30 05:33:49','CR000001','1'),
	('SHS000051','S000013','2020-01-14 00:47:23','UN_SUBMITTED','T000013','CDHS000026','2020-01-30 08:22:53','2020-01-29 21:28:32','CR000001','1'),
	('SHS000052','S000013','2020-01-20 13:58:54','UN_SUBMITTED','T000013','CDHS000026','2020-01-18 04:54:34','2020-02-01 02:56:12','CR000001','1'),
	('SHS000053','S000014','2020-01-29 01:15:54','UN_SUBMITTED','T000014','CDHS000027','2020-02-02 05:52:25','2020-01-19 14:30:42','CR000001','1'),
	('SHS000054','S000014','2020-02-02 12:23:05','UN_SUBMITTED','T000014','CDHS000027','2020-01-25 17:19:19','2020-01-15 14:25:23','CR000001','1'),
	('SHS000055','S000014','2020-01-13 14:02:20','UN_SUBMITTED','T000014','CDHS000028','2020-02-03 12:45:15','2020-01-14 04:14:53','CR000001','1'),
	('SHS000056','S000014','2020-01-18 14:01:10','UN_SUBMITTED','T000014','CDHS000028','2020-01-20 01:19:15','2020-01-12 21:45:32','CR000001','1'),
	('SHS000057','S000015','2020-01-25 08:46:48','UN_SUBMITTED','T000015','CDHS000029','2020-01-19 22:48:26','2020-01-19 19:30:22','CR000001','1'),
	('SHS000058','S000015','2020-01-23 07:39:28','UN_SUBMITTED','T000015','CDHS000029','2020-01-13 18:44:54','2020-01-14 08:51:45','CR000001','1'),
	('SHS000059','S000015','2020-01-21 01:22:18','UN_SUBMITTED','T000015','CDHS000030','2020-01-30 01:53:55','2020-01-21 11:35:55','CR000001','1'),
	('SHS000060','S000015','2020-01-16 14:40:06','UN_SUBMITTED','T000015','CDHS000030','2020-01-30 18:32:42','2020-01-20 23:13:02','CR000001','1'),
	('SHS000061','S000016','2020-01-19 17:51:40','UN_SUBMITTED','T000016','CDHS000031','2020-01-23 08:59:53','2020-01-29 19:58:26','CR000001','1'),
	('SHS000062','S000016','2020-01-23 03:52:51','UN_SUBMITTED','T000016','CDHS000031','2020-01-19 17:02:24','2020-01-31 14:17:32','CR000001','1'),
	('SHS000063','S000016','2020-02-02 07:42:50','UN_SUBMITTED','T000016','CDHS000032','2020-01-21 23:05:13','2020-01-20 10:34:00','CR000001','1'),
	('SHS000064','S000016','2020-01-25 21:06:48','UN_SUBMITTED','T000016','CDHS000032','2020-01-24 10:33:06','2020-01-23 20:36:11','CR000001','1'),
	('SHS000065','S000017','2020-01-23 21:35:37','UN_SUBMITTED','T000017','CDHS000033','2020-01-16 08:28:57','2020-02-01 13:57:48','CR000002','1'),
	('SHS000066','S000017','2020-01-16 02:18:27','UN_SUBMITTED','T000017','CDHS000033','2020-01-20 03:14:06','2020-01-29 20:39:09','CR000002','1'),
	('SHS000067','S000017','2020-01-22 08:24:54','UN_SUBMITTED','T000017','CDHS000034','2020-01-19 16:00:14','2020-01-21 04:13:56','CR000002','1'),
	('SHS000068','S000017','2020-01-23 10:20:31','UN_SUBMITTED','T000017','CDHS000034','2020-01-23 02:13:57','2020-01-15 19:31:24','CR000002','1'),
	('SHS000069','S000018','2020-01-23 03:54:58','UN_SUBMITTED','T000018','CDHS000035','2020-01-21 03:15:21','2020-01-13 23:49:56','CR000002','1'),
	('SHS000070','S000018','2020-01-21 13:16:44','UN_SUBMITTED','T000018','CDHS000035','2020-01-13 01:56:47','2020-01-16 01:38:11','CR000002','1'),
	('SHS000071','S000018','2020-01-13 16:34:17','UN_SUBMITTED','T000018','CDHS000036','2020-01-26 10:07:43','2020-01-20 23:03:29','CR000002','1'),
	('SHS000072','S000018','2020-01-21 22:26:24','UN_SUBMITTED','T000018','CDHS000036','2020-01-14 05:29:46','2020-01-26 19:24:42','CR000002','1'),
	('SHS000073','S000019','2020-01-13 04:06:20','UN_SUBMITTED','T000019','CDHS000037','2020-01-15 16:54:22','2020-01-22 08:46:29','CR000002','1'),
	('SHS000074','S000019','2020-01-12 23:45:28','UN_SUBMITTED','T000019','CDHS000037','2020-01-25 11:41:29','2020-02-02 20:22:55','CR000002','1'),
	('SHS000075','S000019','2020-01-30 07:16:34','UN_SUBMITTED','T000019','CDHS000038','2020-02-01 04:19:45','2020-01-24 11:58:39','CR000002','1'),
	('SHS000076','S000019','2020-01-26 15:05:26','UN_SUBMITTED','T000019','CDHS000038','2020-01-28 12:43:15','2020-02-01 16:56:19','CR000002','1'),
	('SHS000077','S000020','2020-01-22 20:43:20','UN_SUBMITTED','T000020','CDHS000039','2020-01-18 21:16:32','2020-01-13 01:44:24','CR000002','1'),
	('SHS000078','S000020','2020-01-16 22:48:12','UN_SUBMITTED','T000020','CDHS000039','2020-01-15 21:09:12','2020-01-14 16:37:53','CR000002','1'),
	('SHS000079','S000020','2020-01-28 21:12:18','UN_SUBMITTED','T000020','CDHS000040','2020-01-28 01:31:10','2020-02-02 02:46:25','CR000002','1'),
	('SHS000080','S000020','2020-02-03 00:55:21','UN_SUBMITTED','T000020','CDHS000040','2020-01-17 02:32:48','2020-01-18 05:00:41','CR000002','1'),
	('SHS000081','S000021','2020-01-30 20:01:04','UN_SUBMITTED','T000021','CDHS000041','2020-01-25 09:01:11','2020-01-20 01:27:33','CR000002','1'),
	('SHS000082','S000021','2020-01-30 00:05:27','UN_SUBMITTED','T000021','CDHS000041','2020-01-29 09:25:03','2020-01-27 00:49:40','CR000002','1'),
	('SHS000083','S000021','2020-02-03 05:11:25','UN_SUBMITTED','T000021','CDHS000042','2020-01-31 20:16:13','2020-01-21 00:48:01','CR000002','1'),
	('SHS000084','S000021','2020-01-28 06:21:14','UN_SUBMITTED','T000021','CDHS000042','2020-01-21 09:44:44','2020-01-14 12:36:48','CR000002','1'),
	('SHS000085','S000022','2020-01-23 00:02:03','UN_SUBMITTED','T000022','CDHS000043','2020-02-01 20:03:38','2020-01-21 15:47:31','CR000002','1'),
	('SHS000086','S000022','2020-01-25 17:51:43','UN_SUBMITTED','T000022','CDHS000043','2020-01-30 22:21:18','2020-01-22 04:40:22','CR000002','1'),
	('SHS000087','S000022','2020-01-15 19:13:09','SUBMITTE','T000022','CDHS000044','2020-01-19 13:04:06','2020-01-12 21:08:42','CR000002','1'),
	('SHS000088','S000022','2020-01-26 16:45:07','SUBMITTE','T000022','CDHS000044','2020-01-16 14:37:46','2020-01-28 16:15:56','CR000002','1'),
	('SHS000089','S000023','2020-01-29 17:36:19','SUBMITTE','T000023','CDHS000045','2020-01-20 23:01:50','2020-01-20 10:38:46','CR000002','1'),
	('SHS000090','S000023','2020-01-17 08:45:00','SUBMITTE','T000023','CDHS000045','2020-01-29 04:51:14','2020-01-31 00:05:16','CR000002','1'),
	('SHS000091','S000023','2020-01-15 00:29:36','SUBMITTE','T000023','CDHS000046','2020-01-22 21:54:33','2020-01-28 07:35:37','CR000002','1'),
	('SHS000092','S000023','2020-01-26 18:52:40','SUBMITTE','T000023','CDHS000046','2020-01-23 03:46:16','2020-01-29 02:12:28','CR000002','1'),
	('SHS000093','S000024','2020-01-24 11:14:34','SUBMITTE','T000024','CDHS000047','2020-01-27 08:16:06','2020-01-21 02:09:40','CR000002','1'),
	('SHS000094','S000024','2020-01-31 01:57:44','SUBMITTE','T000024','CDHS000047','2020-01-29 03:28:52','2020-01-16 15:18:54','CR000002','1'),
	('SHS000095','S000024','2020-01-28 23:19:16','SUBMITTE','T000024','CDHS000048','2020-01-31 02:27:50','2020-01-27 22:57:30','CR000002','1'),
	('SHS000096','S000024','2020-01-19 04:53:59','SUBMITTE','T000024','CDHS000048','2020-01-22 02:40:54','2020-01-26 19:04:38','CR000002','1'),
	('SHS000097','S000025','2020-01-19 17:13:54','SUBMITTE','T000025','CDHS000049','2020-01-15 16:23:06','2020-01-29 05:02:31','CR000002','1'),
	('SHS000098','S000025','2020-01-19 10:42:47','SUBMITTE','T000025','CDHS000049','2020-01-21 05:13:51','2020-01-26 16:04:38','CR000002','1'),
	('SHS000099','S000025','2020-01-21 04:45:27','SUBMITTE','T000025','CDHS000050','2020-01-26 16:12:13','2020-01-28 10:43:40','CR000002','1'),
	('SHS000100','S000025','2020-01-31 03:34:09','SUBMITTE','T000025','CDHS000050','2020-01-17 08:57:01','2020-02-03 03:31:28','CR000002','1'),
	('SHS000101','S000026','2020-01-27 03:10:08','SUBMITTE','T000026','CDHS000051','2020-01-19 01:50:03','2020-01-24 23:19:04','CR000002','1'),
	('SHS000102','S000026','2020-01-26 03:46:57','SUBMITTE','T000026','CDHS000051','2020-01-26 10:51:09','2020-01-19 10:51:07','CR000002','1'),
	('SHS000103','S000026','2020-01-28 07:12:01','SUBMITTE','T000026','CDHS000052','2020-01-16 12:45:43','2020-02-03 11:34:02','CR000002','1'),
	('SHS000104','S000026','2020-01-15 23:00:57','SUBMITTE','T000026','CDHS000052','2020-01-20 04:22:22','2020-01-16 06:38:37','CR000002','1'),
	('SHS000105','S000027','2020-02-02 15:04:59','SUBMITTE','T000027','CDHS000053','2020-01-21 03:55:15','2020-01-29 06:08:51','CR000002','1'),
	('SHS000106','S000027','2020-01-13 07:14:06','SUBMITTE','T000027','CDHS000053','2020-01-20 14:16:08','2020-01-22 18:26:50','CR000002','1'),
	('SHS000107','S000027','2020-01-22 11:50:18','SUBMITTE','T000027','CDHS000054','2020-01-17 12:55:03','2020-01-26 07:31:59','CR000002','1'),
	('SHS000108','S000027','2020-02-03 10:52:04','SUBMITTE','T000027','CDHS000054','2020-01-22 16:00:25','2020-01-20 02:54:01','CR000002','1'),
	('SHS000109','S000028','2020-02-02 11:07:26','SUBMITTE','T000028','CDHS000055','2020-01-20 18:39:28','2020-01-23 08:13:23','CR000002','1'),
	('SHS000110','S000028','2020-01-28 15:28:39','SUBMITTE','T000028','CDHS000055','2020-01-15 20:47:15','2020-01-13 10:21:23','CR000002','1'),
	('SHS000111','S000028','2020-01-24 21:41:34','SUBMITTE','T000028','CDHS000056','2020-02-02 04:37:29','2020-01-15 13:11:04','CR000002','1'),
	('SHS000112','S000028','2020-01-17 01:49:56','SUBMITTE','T000028','CDHS000056','2020-01-16 16:04:06','2020-02-03 05:25:45','CR000002','1'),
	('SHS000113','S000029','2020-01-15 22:16:02','SUBMITTE','T000029','CDHS000057','2020-02-01 21:01:12','2020-01-28 23:49:48','CR000002','1'),
	('SHS000114','S000029','2020-01-28 15:31:48','SUBMITTE','T000029','CDHS000057','2020-01-20 12:34:32','2020-01-12 17:50:59','CR000002','1'),
	('SHS000115','S000029','2020-01-29 23:13:51','SUBMITTE','T000029','CDHS000058','2020-01-24 02:09:48','2020-01-15 20:03:59','CR000002','1'),
	('SHS000116','S000029','2020-01-31 04:51:32','SUBMITTE','T000029','CDHS000058','2020-01-20 19:07:54','2020-02-02 01:19:52','CR000002','1'),
	('SHS000117','S000030','2020-02-03 07:17:03','SUBMITTE','T000030','CDHS000059','2020-01-15 12:52:41','2020-01-23 21:41:58','CR000002','1'),
	('SHS000118','S000030','2020-01-25 19:05:03','SUBMITTE','T000030','CDHS000059','2020-01-17 09:48:13','2020-01-16 07:59:59','CR000002','1'),
	('SHS000119','S000030','2020-01-14 04:43:39','SUBMITTE','T000030','CDHS000060','2020-01-15 20:29:08','2020-01-18 18:54:21','CR000002','1'),
	('SHS000120','S000030','2020-01-31 19:59:28','SUBMITTE','T000030','CDHS000060','2020-02-02 21:50:09','2020-01-26 09:56:04','CR000002','1'),
	('SHS000121','S000031','2020-01-22 14:37:31','SUBMITTE','T000031','CDHS000061','2020-01-25 20:47:02','2020-02-01 11:14:24','CR000002','1'),
	('SHS000122','S000031','2020-01-18 19:58:14','SUBMITTE','T000031','CDHS000061','2020-01-13 19:11:26','2020-01-21 19:52:08','CR000002','1'),
	('SHS000123','S000031','2020-01-12 20:07:54','SUBMITTE','T000031','CDHS000062','2020-01-15 05:37:38','2020-01-18 09:06:08','CR000002','1'),
	('SHS000124','S000031','2020-01-14 10:32:59','SUBMITTE','T000031','CDHS000062','2020-02-03 05:35:37','2020-01-23 12:42:02','CR000002','1'),
	('SHS000125','S000032','2020-01-28 16:37:39','SUBMITTE','T000032','CDHS000063','2020-01-26 11:03:15','2020-01-21 20:22:13','CR000002','1'),
	('SHS000126','S000032','2020-02-03 05:18:10','SUBMITTE','T000032','CDHS000063','2020-02-01 06:45:49','2020-01-21 20:40:36','CR000002','1'),
	('SHS000127','S000032','2020-01-28 17:33:09','SUBMITTE','T000032','CDHS000064','2020-01-26 10:24:12','2020-01-29 23:18:35','CR000002','1'),
	('SHS000128','S000032','2020-01-23 00:37:25','SUBMITTE','T000032','CDHS000064','2020-01-20 13:05:02','2020-01-19 00:43:43','CR000002','1'),
	('SHS000129','S000033','2020-01-31 12:00:12','SUBMITTE','T000033','CDHS000065','2020-01-17 10:27:06','2020-01-17 10:34:20','CR000003','1'),
	('SHS000130','S000033','2020-01-30 21:10:07','SUBMITTE','T000033','CDHS000065','2020-01-30 03:19:11','2020-01-13 14:20:37','CR000003','1'),
	('SHS000131','S000033','2020-01-24 00:00:04','SUBMITTE','T000033','CDHS000066','2020-01-29 03:45:06','2020-02-03 11:42:10','CR000003','1'),
	('SHS000132','S000033','2020-01-25 21:43:50','SUBMITTE','T000033','CDHS000066','2020-01-17 17:45:37','2020-01-20 09:53:10','CR000003','1'),
	('SHS000133','S000034','2020-01-25 16:04:33','SUBMITTE','T000034','CDHS000067','2020-01-23 15:58:35','2020-01-20 00:46:03','CR000003','1'),
	('SHS000134','S000034','2020-01-25 20:19:28','SUBMITTE','T000034','CDHS000067','2020-01-21 15:42:46','2020-01-29 00:09:14','CR000003','1'),
	('SHS000135','S000034','2020-01-13 16:44:06','SUBMITTE','T000034','CDHS000068','2020-01-27 14:23:22','2020-01-19 06:40:49','CR000003','1'),
	('SHS000136','S000034','2020-01-22 08:47:52','SUBMITTE','T000034','CDHS000068','2020-01-31 02:38:17','2020-01-26 07:37:10','CR000003','1'),
	('SHS000137','S000035','2020-01-25 08:19:43','SUBMITTE','T000035','CDHS000069','2020-01-18 02:05:07','2020-01-13 00:34:14','CR000003','1'),
	('SHS000138','S000035','2020-01-23 16:43:00','SUBMITTE','T000035','CDHS000069','2020-01-16 17:06:37','2020-01-22 01:23:55','CR000003','1'),
	('SHS000139','S000035','2020-01-31 19:37:35','SUBMITTE','T000035','CDHS000070','2020-01-22 20:32:39','2020-01-18 08:18:20','CR000003','1'),
	('SHS000140','S000035','2020-01-31 05:50:26','SUBMITTE','T000035','CDHS000070','2020-01-22 10:13:30','2020-01-16 20:18:37','CR000003','1'),
	('SHS000141','S000036','2020-01-25 05:05:33','SUBMITTE','T000036','CDHS000071','2020-01-29 04:01:48','2020-01-29 14:12:18','CR000003','1'),
	('SHS000142','S000036','2020-01-24 11:46:34','SUBMITTE','T000036','CDHS000071','2020-01-13 23:57:07','2020-01-13 21:09:59','CR000003','1'),
	('SHS000143','S000036','2020-01-21 20:26:03','SUBMITTE','T000036','CDHS000072','2020-01-29 10:34:03','2020-01-20 12:05:56','CR000003','1'),
	('SHS000144','S000036','2020-01-27 09:57:37','SUBMITTE','T000036','CDHS000072','2020-01-30 14:50:37','2020-01-14 23:13:55','CR000003','1'),
	('SHS000145','S000037','2020-01-30 01:14:27','SUBMITTE','T000037','CDHS000073','2020-01-25 23:56:31','2020-01-23 14:28:05','CR000003','1'),
	('SHS000146','S000037','2020-01-24 20:21:22','SUBMITTE','T000037','CDHS000073','2020-01-29 22:06:37','2020-01-26 16:30:39','CR000003','1'),
	('SHS000147','S000037','2020-02-03 08:28:49','SUBMITTE','T000037','CDHS000074','2020-01-20 11:05:11','2020-02-02 15:52:58','CR000003','1'),
	('SHS000148','S000037','2020-02-01 20:03:51','SUBMITTE','T000037','CDHS000074','2020-01-23 01:44:17','2020-02-03 10:18:50','CR000003','1'),
	('SHS000149','S000038','2020-01-24 18:34:56','SUBMITTE','T000038','CDHS000075','2020-01-19 05:25:57','2020-01-30 09:36:43','CR000003','1'),
	('SHS000150','S000038','2020-01-26 00:15:24','SUBMITTE','T000038','CDHS000075','2020-02-03 06:53:45','2020-02-01 04:17:00','CR000003','1'),
	('SHS000151','S000038','2020-01-25 14:50:44','SUBMITTE','T000038','CDHS000076','2020-01-18 12:20:56','2020-01-21 09:39:24','CR000003','1'),
	('SHS000152','S000038','2020-01-18 17:41:30','SUBMITTE','T000038','CDHS000076','2020-01-20 18:17:58','2020-02-01 15:40:02','CR000003','1'),
	('SHS000153','S000039','2020-01-14 21:25:15','SUBMITTE','T000039','CDHS000077','2020-01-28 15:57:20','2020-02-01 23:39:47','CR000003','1'),
	('SHS000154','S000039','2020-01-29 19:20:52','SUBMITTE','T000039','CDHS000077','2020-01-29 07:03:44','2020-01-13 03:31:49','CR000003','1'),
	('SHS000155','S000039','2020-01-30 05:58:17','SUBMITTE','T000039','CDHS000078','2020-01-27 02:36:49','2020-02-01 10:18:50','CR000003','1'),
	('SHS000156','S000039','2020-01-28 01:04:35','SUBMITTE','T000039','CDHS000078','2020-01-31 10:38:16','2020-01-26 17:55:14','CR000003','1'),
	('SHS000157','S000040','2020-01-15 17:56:16','SUBMITTE','T000040','CDHS000079','2020-01-17 13:39:58','2020-01-29 12:07:34','CR000003','1'),
	('SHS000158','S000040','2020-01-13 15:08:04','SUBMITTE','T000040','CDHS000079','2020-01-13 18:15:47','2020-02-01 14:57:49','CR000003','1'),
	('SHS000159','S000040','2020-01-29 06:54:32','SUBMITTE','T000040','CDHS000080','2020-01-25 17:59:07','2020-01-18 05:01:34','CR000003','1'),
	('SHS000160','S000040','2020-01-25 08:05:48','SUBMITTE','T000040','CDHS000080','2020-01-29 02:24:43','2020-01-22 05:06:59','CR000003','1'),
	('SHS000161','S000041','2020-01-26 22:46:05','SUBMITTE','T000041','CDHS000081','2020-01-24 15:21:03','2020-01-21 15:25:44','CR000003','1'),
	('SHS000162','S000041','2020-01-16 11:36:55','SUBMITTE','T000041','CDHS000081','2020-01-15 07:40:28','2020-02-01 17:34:34','CR000003','1'),
	('SHS000163','S000041','2020-01-17 06:47:14','SUBMITTE','T000041','CDHS000082','2020-01-12 20:14:19','2020-01-17 12:31:23','CR000003','1'),
	('SHS000164','S000041','2020-01-17 09:27:39','SUBMITTE','T000041','CDHS000082','2020-01-28 17:52:53','2020-02-02 11:21:51','CR000003','1'),
	('SHS000165','S000042','2020-01-27 05:53:01','SUBMITTE','T000042','CDHS000083','2020-02-02 19:25:37','2020-02-01 16:38:28','CR000003','1'),
	('SHS000166','S000042','2020-01-17 00:40:44','SUBMITTE','T000042','CDHS000083','2020-01-19 14:20:30','2020-01-18 12:25:31','CR000003','1'),
	('SHS000167','S000042','2020-01-30 21:34:49','SUBMITTE','T000042','CDHS000084','2020-01-16 05:40:50','2020-01-23 11:26:51','CR000003','1'),
	('SHS000168','S000042','2020-01-18 14:21:20','SUBMITTE','T000042','CDHS000084','2020-02-02 16:46:26','2020-01-31 03:05:38','CR000003','1'),
	('SHS000169','S000043','2020-01-17 14:46:13','SUBMITTE','T000043','CDHS000085','2020-01-18 01:00:37','2020-01-22 22:44:58','CR000003','1'),
	('SHS000170','S000043','2020-01-15 11:37:55','SUBMITTE','T000043','CDHS000085','2020-02-01 00:49:15','2020-01-13 10:20:02','CR000003','1'),
	('SHS000171','S000043','2020-01-19 21:07:09','SUBMITTE','T000043','CDHS000086','2020-01-13 16:35:22','2020-01-19 14:43:22','CR000003','1'),
	('SHS000172','S000043','2020-01-31 19:37:17','DRAFT','T000043','CDHS000086','2020-01-22 09:10:00','2020-01-16 06:09:05','CR000003','1'),
	('SHS000173','S000044','2020-01-17 06:57:34','DRAFT','T000044','CDHS000087','2020-02-01 10:12:57','2020-02-03 11:42:28','CR000003','1'),
	('SHS000174','S000044','2020-01-15 22:47:50','DRAFT','T000044','CDHS000087','2020-02-01 10:53:00','2020-01-17 06:56:54','CR000003','1'),
	('SHS000175','S000044','2020-01-23 23:45:54','DRAFT','T000044','CDHS000088','2020-02-03 01:13:26','2020-01-18 21:58:11','CR000003','1'),
	('SHS000176','S000044','2020-02-02 15:09:10','DRAFT','T000044','CDHS000088','2020-02-01 13:21:24','2020-01-20 05:58:25','CR000003','1'),
	('SHS000177','S000045','2020-01-29 01:48:54','DRAFT','T000045','CDHS000089','2020-01-31 15:00:58','2020-01-30 19:46:55','CR000003','1'),
	('SHS000178','S000045','2020-01-28 14:13:31','DRAFT','T000045','CDHS000089','2020-01-20 14:36:44','2020-01-22 14:28:18','CR000003','1'),
	('SHS000179','S000045','2020-01-20 22:22:30','DRAFT','T000045','CDHS000090','2020-01-30 04:50:08','2020-01-25 05:43:00','CR000003','1'),
	('SHS000180','S000045','2020-01-22 03:48:35','DRAFT','T000045','CDHS000090','2020-02-02 22:14:55','2020-01-17 12:50:02','CR000003','1'),
	('SHS000181','S000046','2020-01-30 03:41:39','DRAFT','T000046','CDHS000091','2020-01-13 16:05:15','2020-01-27 20:34:56','CR000003','1'),
	('SHS000182','S000046','2020-01-20 04:26:57','DRAFT','T000046','CDHS000091','2020-01-16 08:57:53','2020-01-24 06:50:09','CR000003','1'),
	('SHS000183','S000046','2020-01-15 20:24:21','DRAFT','T000046','CDHS000092','2020-02-02 06:55:31','2020-01-25 14:59:58','CR000003','1'),
	('SHS000184','S000046','2020-01-24 12:30:26','DRAFT','T000046','CDHS000092','2020-01-12 17:19:20','2020-01-14 05:53:15','CR000003','1'),
	('SHS000185','S000047','2020-02-03 08:47:13','DRAFT','T000047','CDHS000093','2020-01-14 10:23:23','2020-01-14 15:50:33','CR000003','1'),
	('SHS000186','S000047','2020-01-31 12:52:18','DRAFT','T000047','CDHS000093','2020-01-19 07:59:19','2020-01-22 09:41:55','CR000003','1'),
	('SHS000187','S000047','2020-01-29 03:58:14','DRAFT','T000047','CDHS000094','2020-01-22 10:46:17','2020-01-20 03:12:09','CR000003','1'),
	('SHS000188','S000047','2020-01-28 23:53:26','DRAFT','T000047','CDHS000094','2020-01-27 19:20:27','2020-01-18 18:15:19','CR000003','1'),
	('SHS000189','S000048','2020-01-14 18:42:55','DRAFT','T000048','CDHS000095','2020-01-30 10:17:03','2020-01-18 12:01:15','CR000003','1'),
	('SHS000190','S000048','2020-01-28 02:48:44','DRAFT','T000048','CDHS000095','2020-01-23 15:58:20','2020-01-26 18:51:04','CR000003','1'),
	('SHS000191','S000048','2020-01-22 12:01:37','DRAFT','T000048','CDHS000096','2020-01-14 14:24:58','2020-01-31 13:35:48','CR000003','1'),
	('SHS000192','S000048','2020-01-16 14:43:47','DRAFT','T000048','CDHS000096','2020-01-17 11:38:30','2020-01-27 17:28:04','CR000003','1'),
	('SHS000193','S000049','2020-01-22 06:06:03','DRAFT','T000049','CDHS000097','2020-01-29 06:14:59','2020-01-18 03:24:37','CR000004','1'),
	('SHS000194','S000049','2020-01-17 19:38:50','DRAFT','T000049','CDHS000097','2020-01-28 06:28:44','2020-01-26 00:23:20','CR000004','1'),
	('SHS000195','S000049','2020-01-15 01:23:50','DRAFT','T000049','CDHS000098','2020-01-21 06:13:52','2020-02-01 19:37:38','CR000004','1'),
	('SHS000196','S000049','2020-01-27 00:40:03','DRAFT','T000049','CDHS000098','2020-01-14 15:54:18','2020-01-18 17:39:17','CR000004','1'),
	('SHS000197','S000050','2020-01-18 16:26:29','DRAFT','T000050','CDHS000099','2020-01-14 08:04:31','2020-02-03 00:49:12','CR000004','1'),
	('SHS000198','S000050','2020-01-31 10:15:55','DRAFT','T000050','CDHS000099','2020-01-17 02:26:54','2020-01-22 14:57:26','CR000004','1'),
	('SHS000199','S000050','2020-01-28 00:01:34','DRAFT','T000050','CDHS000100','2020-01-18 20:53:46','2020-01-25 07:10:26','CR000004','1'),
	('SHS000200','S000050','2020-01-29 11:37:14','DRAFT','T000050','CDHS000100','2020-02-02 16:55:34','2020-01-16 17:29:59','CR000004','1'),
	('SHS000201','S000051','2020-01-25 17:06:52','DRAFT','T000051','CDHS000101','2020-01-15 15:00:39','2020-01-17 12:38:55','CR000004','1'),
	('SHS000202','S000051','2020-01-23 23:48:56','DRAFT','T000051','CDHS000101','2020-01-23 00:58:27','2020-01-13 08:29:27','CR000004','1'),
	('SHS000203','S000051','2020-01-23 04:45:53','DRAFT','T000051','CDHS000102','2020-01-30 11:54:24','2020-01-25 00:20:15','CR000004','1'),
	('SHS000204','S000051','2020-01-26 08:23:19','DRAFT','T000051','CDHS000102','2020-01-21 08:31:11','2020-01-26 01:35:15','CR000004','1'),
	('SHS000205','S000052','2020-01-31 19:48:10','DRAFT','T000052','CDHS000103','2020-02-03 12:55:17','2020-01-14 09:29:31','CR000004','1'),
	('SHS000206','S000052','2020-01-20 00:40:08','DRAFT','T000052','CDHS000103','2020-01-17 02:09:07','2020-01-18 22:22:40','CR000004','1'),
	('SHS000207','S000052','2020-02-01 13:00:58','DRAFT','T000052','CDHS000104','2020-01-16 22:59:24','2020-01-18 04:26:11','CR000004','1'),
	('SHS000208','S000052','2020-01-16 04:26:26','DRAFT','T000052','CDHS000104','2020-01-18 02:27:40','2020-01-23 03:33:31','CR000004','1'),
	('SHS000209','S000053','2020-01-17 06:16:14','DRAFT','T000053','CDHS000105','2020-01-28 02:26:49','2020-01-30 15:32:19','CR000004','1'),
	('SHS000210','S000053','2020-01-16 12:36:57','DRAFT','T000053','CDHS000105','2020-01-16 08:33:37','2020-01-21 13:38:39','CR000004','1'),
	('SHS000211','S000053','2020-01-29 20:00:54','DRAFT','T000053','CDHS000106','2020-01-13 21:50:21','2020-01-28 12:10:27','CR000004','1'),
	('SHS000212','S000053','2020-02-01 21:27:25','DRAFT','T000053','CDHS000106','2020-01-19 04:43:47','2020-01-15 14:38:45','CR000004','1'),
	('SHS000213','S000054','2020-01-30 13:38:25','DRAFT','T000054','CDHS000107','2020-01-19 05:41:18','2020-01-22 17:52:49','CR000004','1'),
	('SHS000214','S000054','2020-02-03 08:12:33','DRAFT','T000054','CDHS000107','2020-01-26 05:16:42','2020-01-23 23:00:05','CR000004','1'),
	('SHS000215','S000054','2020-02-03 14:06:38','DRAFT','T000054','CDHS000108','2020-01-24 14:35:30','2020-01-30 08:13:03','CR000004','1'),
	('SHS000216','S000054','2020-01-26 01:21:55','DRAFT','T000054','CDHS000108','2020-01-14 12:38:33','2020-01-28 05:06:08','CR000004','1'),
	('SHS000217','S000055','2020-01-16 00:06:43','DRAFT','T000055','CDHS000109','2020-01-15 18:26:09','2020-01-31 17:19:22','CR000004','1'),
	('SHS000218','S000055','2020-01-29 14:01:50','DRAFT','T000055','CDHS000109','2020-01-27 14:39:40','2020-01-20 20:37:42','CR000004','1'),
	('SHS000219','S000055','2020-01-17 18:00:14','DRAFT','T000055','CDHS000110','2020-01-24 15:39:27','2020-01-13 16:51:27','CR000004','1'),
	('SHS000220','S000055','2020-01-23 07:45:55','DRAFT','T000055','CDHS000110','2020-01-17 01:10:14','2020-01-13 18:20:30','CR000004','1'),
	('SHS000221','S000056','2020-02-01 19:25:33','DRAFT','T000056','CDHS000111','2020-02-03 06:25:59','2020-01-17 02:54:23','CR000004','1'),
	('SHS000222','S000056','2020-02-01 20:40:39','DRAFT','T000056','CDHS000111','2020-01-21 06:59:04','2020-01-28 09:57:06','CR000004','1'),
	('SHS000223','S000056','2020-01-16 18:26:58','DRAFT','T000056','CDHS000112','2020-02-01 12:03:56','2020-01-21 02:14:57','CR000004','1'),
	('SHS000224','S000056','2020-01-15 10:57:21','DRAFT','T000056','CDHS000112','2020-01-13 02:00:51','2020-01-21 23:53:30','CR000004','1'),
	('SHS000225','S000057','2020-02-02 23:55:18','DRAFT','T000057','CDHS000113','2020-01-31 16:24:52','2020-01-20 21:57:21','CR000004','1'),
	('SHS000226','S000057','2020-01-28 17:29:50','DRAFT','T000057','CDHS000113','2020-01-23 09:32:04','2020-01-23 18:27:40','CR000004','1'),
	('SHS000227','S000057','2020-01-23 09:14:47','DRAFT','T000057','CDHS000114','2020-01-15 09:38:04','2020-01-31 19:42:32','CR000004','1'),
	('SHS000228','S000057','2020-01-29 09:40:42','DRAFT','T000057','CDHS000114','2020-01-27 14:51:50','2020-01-23 00:19:54','CR000004','1'),
	('SHS000229','S000058','2020-01-24 12:33:16','DRAFT','T000058','CDHS000115','2020-01-28 09:40:44','2020-01-27 06:50:57','CR000004','1'),
	('SHS000230','S000058','2020-01-27 06:03:38','DRAFT','T000058','CDHS000115','2020-01-23 15:58:02','2020-01-22 03:44:50','CR000004','1'),
	('SHS000231','S000058','2020-02-01 15:15:57','DRAFT','T000058','CDHS000116','2020-01-25 19:10:33','2020-01-12 22:31:24','CR000004','1'),
	('SHS000232','S000058','2020-01-28 07:57:33','DRAFT','T000058','CDHS000116','2020-01-17 18:41:55','2020-01-13 02:24:34','CR000004','1'),
	('SHS000233','S000059','2020-01-20 14:31:37','DRAFT','T000059','CDHS000117','2020-01-14 18:57:27','2020-02-02 16:03:38','CR000004','1'),
	('SHS000234','S000059','2020-02-01 04:44:37','DRAFT','T000059','CDHS000117','2020-01-28 17:10:06','2020-01-27 12:28:16','CR000004','1'),
	('SHS000235','S000059','2020-01-26 01:31:29','DRAFT','T000059','CDHS000118','2020-02-02 05:50:02','2020-01-17 10:51:30','CR000004','1'),
	('SHS000236','S000059','2020-01-16 06:41:06','DRAFT','T000059','CDHS000118','2020-01-15 00:47:10','2020-01-27 05:57:01','CR000004','1'),
	('SHS000237','S000060','2020-01-17 17:39:10','DRAFT','T000060','CDHS000119','2020-01-19 03:54:26','2020-01-31 18:20:57','CR000004','1'),
	('SHS000238','S000060','2020-01-22 23:30:42','DRAFT','T000060','CDHS000119','2020-01-23 09:38:34','2020-01-31 17:04:05','CR000004','1'),
	('SHS000239','S000060','2020-01-15 00:58:58','DRAFT','T000060','CDHS000120','2020-01-22 13:55:03','2020-01-28 10:52:49','CR000004','1'),
	('SHS000240','S000060','2020-01-28 15:33:55','DRAFT','T000060','CDHS000120','2020-01-28 07:25:18','2020-01-14 16:45:09','CR000004','1'),
	('SHS000241','S000061','2020-01-12 20:25:25','DRAFT','T000061','CDHS000121','2020-01-22 04:42:41','2020-02-02 14:28:50','CR000004','1'),
	('SHS000242','S000061','2020-01-19 12:42:43','DRAFT','T000061','CDHS000121','2020-01-22 18:49:43','2020-01-29 12:28:04','CR000004','1'),
	('SHS000243','S000061','2020-01-14 14:33:16','DRAFT','T000061','CDHS000122','2020-01-19 17:13:07','2020-01-23 08:16:52','CR000004','1'),
	('SHS000244','S000061','2020-02-01 22:44:18','DRAFT','T000061','CDHS000122','2020-01-24 18:35:53','2020-01-18 05:01:35','CR000004','1'),
	('SHS000245','S000062','2020-01-23 13:33:02','DRAFT','T000062','CDHS000123','2020-02-02 23:47:11','2020-01-18 04:08:14','CR000004','1'),
	('SHS000246','S000062','2020-01-31 13:58:19','DRAFT','T000062','CDHS000123','2020-01-19 21:04:12','2020-01-16 09:21:21','CR000004','1'),
	('SHS000247','S000062','2020-01-27 09:14:52','DRAFT','T000062','CDHS000124','2020-01-22 07:37:18','2020-02-03 09:28:55','CR000004','1'),
	('SHS000248','S000062','2020-01-29 22:50:23','DRAFT','T000062','CDHS000124','2020-01-19 09:55:39','2020-01-26 16:15:55','CR000004','1'),
	('SHS000249','S000063','2020-01-26 06:09:31','DRAFT','T000063','CDHS000125','2020-01-21 09:41:51','2020-02-01 10:44:07','CR000004','1'),
	('SHS000250','S000063','2020-01-16 20:20:52','DRAFT','T000063','CDHS000125','2020-01-27 06:29:56','2020-02-02 03:31:31','CR000004','1'),
	('SHS000251','S000063','2020-01-24 13:18:20','DRAFT','T000063','CDHS000126','2020-01-18 17:11:37','2020-01-22 07:24:51','CR000004','1'),
	('SHS000252','S000063','2020-01-23 16:42:47','DRAFT','T000063','CDHS000126','2020-01-23 17:14:34','2020-02-01 17:33:10','CR000004','1'),
	('SHS000253','S000064','2020-01-14 03:45:55','DRAFT','T000064','CDHS000127','2020-01-25 00:39:17','2020-01-14 13:41:14','CR000004','1'),
	('SHS000254','S000064','2020-02-01 00:19:10','DRAFT','T000064','CDHS000127','2020-01-30 23:46:16','2020-01-13 15:51:59','CR000004','1'),
	('SHS000255','S000064','2020-01-20 01:35:28','DRAFT','T000064','CDHS000128','2020-01-23 17:18:09','2020-01-27 01:24:27','CR000004','1'),
	('SHS000256','S000064','2020-02-03 00:15:21','DRAFT','T000064','CDHS000128','2020-01-28 13:34:47','2020-01-17 05:59:19','CR000004','1');

insert into student_daily_answer_data values
	('SDA000001','SHS000001','DSQ000001','A','2020-01-14 14:55:32','2020-01-21 21:00:30','CR000001','1'),
	('SDA000002','SHS000001','DSQ000001','B','2020-01-19 11:03:55','2020-01-30 21:37:25','CR000001','1'),
	('SDA000003','SHS000002','DSQ000002','A','2020-02-02 16:51:51','2020-02-01 02:06:37','CR000001','1'),
	('SDA000004','SHS000002','DSQ000002','B','2020-02-01 07:22:36','2020-01-23 14:15:09','CR000001','1'),
	('SDA000005','SHS000003','DSQ000003','A','2020-01-15 06:17:46','2020-01-30 15:23:35','CR000001','1'),
	('SDA000006','SHS000003','DSQ000003','B','2020-02-03 14:18:01','2020-01-20 16:22:59','CR000001','1'),
	('SDA000007','SHS000004','DSQ000004','A','2020-01-18 12:32:29','2020-01-24 23:23:17','CR000001','1'),
	('SDA000008','SHS000004','DSQ000004','B','2020-01-16 11:58:53','2020-01-26 17:30:42','CR000001','1'),
	('SDA000009','SHS000005','DSQ000005','A','2020-01-16 02:23:20','2020-02-01 01:35:55','CR000001','1'),
	('SDA000010','SHS000005','DSQ000005','B','2020-01-21 09:48:46','2020-01-14 00:25:26','CR000001','1'),
	('SDA000011','SHS000006','DSQ000006','A','2020-01-26 02:49:29','2020-01-18 16:26:50','CR000001','1'),
	('SDA000012','SHS000006','DSQ000006','B','2020-01-30 06:20:42','2020-01-22 07:23:51','CR000001','1'),
	('SDA000013','SHS000007','DSQ000007','A','2020-01-13 14:42:46','2020-01-16 20:19:25','CR000001','1'),
	('SDA000014','SHS000007','DSQ000007','B','2020-02-01 20:34:20','2020-01-13 03:22:19','CR000001','1'),
	('SDA000015','SHS000008','DSQ000008','A','2020-01-13 15:04:55','2020-02-01 16:27:56','CR000001','1'),
	('SDA000016','SHS000008','DSQ000008','B','2020-01-14 01:37:02','2020-01-14 07:48:19','CR000001','1'),
	('SDA000017','SHS000009','DSQ000009','A','2020-01-29 05:46:47','2020-02-02 19:01:04','CR000001','1'),
	('SDA000018','SHS000009','DSQ000009','B','2020-01-15 23:12:01','2020-01-26 00:31:34','CR000001','1'),
	('SDA000019','SHS000010','DSQ000010','A','2020-01-20 22:41:49','2020-01-28 15:33:57','CR000001','1'),
	('SDA000020','SHS000010','DSQ000010','B','2020-01-24 11:47:40','2020-01-15 04:35:54','CR000001','1'),
	('SDA000021','SHS000011','DSQ000011','A','2020-01-13 18:08:07','2020-01-27 14:43:08','CR000001','1'),
	('SDA000022','SHS000011','DSQ000011','B','2020-02-02 10:59:30','2020-02-03 07:03:09','CR000001','1'),
	('SDA000023','SHS000012','DSQ000012','A','2020-02-03 08:22:48','2020-01-23 12:18:11','CR000001','1'),
	('SDA000024','SHS000012','DSQ000012','B','2020-01-25 06:09:31','2020-01-26 10:41:40','CR000001','1'),
	('SDA000025','SHS000013','DSQ000013','A','2020-01-19 03:09:25','2020-01-22 01:15:34','CR000001','1'),
	('SDA000026','SHS000013','DSQ000013','B','2020-02-01 07:44:37','2020-01-18 16:17:35','CR000001','1'),
	('SDA000027','SHS000014','DSQ000014','A','2020-01-19 15:23:30','2020-01-25 22:22:55','CR000001','1'),
	('SDA000028','SHS000014','DSQ000014','B','2020-01-23 00:59:45','2020-01-18 07:51:06','CR000001','1'),
	('SDA000029','SHS000015','DSQ000015','A','2020-02-01 15:45:46','2020-01-16 10:24:25','CR000001','1'),
	('SDA000030','SHS000015','DSQ000015','B','2020-01-17 07:11:15','2020-01-30 00:48:33','CR000001','1'),
	('SDA000031','SHS000016','DSQ000016','A','2020-01-15 00:31:13','2020-01-31 23:03:45','CR000001','1'),
	('SDA000032','SHS000016','DSQ000016','B','2020-01-18 03:29:17','2020-01-19 12:16:22','CR000001','1'),
	('SDA000033','SHS000017','DSQ000017','A','2020-02-03 05:22:12','2020-01-20 08:42:18','CR000001','1'),
	('SDA000034','SHS000017','DSQ000017','B','2020-01-25 09:00:41','2020-01-14 21:22:21','CR000001','1'),
	('SDA000035','SHS000018','DSQ000018','A','2020-01-26 02:51:23','2020-01-14 16:43:37','CR000001','1'),
	('SDA000036','SHS000018','DSQ000018','B','2020-02-02 21:39:57','2020-01-17 16:08:12','CR000001','1'),
	('SDA000037','SHS000019','DSQ000019','A','2020-02-01 21:38:43','2020-01-21 16:46:51','CR000001','1'),
	('SDA000038','SHS000019','DSQ000019','B','2020-01-25 14:39:56','2020-01-29 15:43:09','CR000001','1'),
	('SDA000039','SHS000020','DSQ000020','A','2020-01-18 13:48:13','2020-01-23 07:16:16','CR000001','1'),
	('SDA000040','SHS000020','DSQ000020','B','2020-02-03 08:06:45','2020-02-03 00:34:45','CR000001','1'),
	('SDA000041','SHS000021','DSQ000021','A','2020-01-19 13:47:01','2020-01-16 21:17:09','CR000001','1'),
	('SDA000042','SHS000021','DSQ000021','B','2020-01-27 08:44:24','2020-01-15 19:05:05','CR000001','1'),
	('SDA000043','SHS000022','DSQ000022','A','2020-01-24 08:21:51','2020-01-30 11:11:01','CR000001','1'),
	('SDA000044','SHS000022','DSQ000022','B','2020-01-26 19:42:48','2020-01-17 18:14:11','CR000001','1'),
	('SDA000045','SHS000023','DSQ000023','A','2020-01-18 19:03:53','2020-01-19 05:01:55','CR000001','1'),
	('SDA000046','SHS000023','DSQ000023','B','2020-01-28 11:06:25','2020-01-22 00:43:59','CR000001','1'),
	('SDA000047','SHS000024','DSQ000024','A','2020-02-01 23:32:42','2020-01-26 22:58:54','CR000001','1'),
	('SDA000048','SHS000024','DSQ000024','B','2020-01-16 20:44:20','2020-01-22 02:03:49','CR000001','1'),
	('SDA000049','SHS000025','DSQ000025','A','2020-02-03 07:00:55','2020-01-31 17:05:33','CR000001','1'),
	('SDA000050','SHS000025','DSQ000025','B','2020-01-31 15:36:56','2020-01-13 00:05:52','CR000001','1'),
	('SDA000051','SHS000026','DSQ000026','A','2020-01-23 10:43:09','2020-01-13 07:40:03','CR000001','1'),
	('SDA000052','SHS000026','DSQ000026','B','2020-01-13 06:20:57','2020-02-03 00:33:04','CR000001','1'),
	('SDA000053','SHS000027','DSQ000027','A','2020-01-16 12:55:54','2020-01-20 19:13:47','CR000001','1'),
	('SDA000054','SHS000027','DSQ000027','B','2020-02-03 14:31:59','2020-01-29 23:56:52','CR000001','1'),
	('SDA000055','SHS000028','DSQ000028','A','2020-01-15 20:59:15','2020-01-25 23:28:14','CR000001','1'),
	('SDA000056','SHS000028','DSQ000028','B','2020-01-24 11:49:08','2020-01-18 05:14:45','CR000001','1'),
	('SDA000057','SHS000029','DSQ000029','A','2020-01-30 01:05:25','2020-01-19 14:49:13','CR000001','1'),
	('SDA000058','SHS000029','DSQ000029','B','2020-01-13 19:19:14','2020-01-29 07:47:49','CR000001','1'),
	('SDA000059','SHS000030','DSQ000030','A','2020-01-27 21:58:41','2020-02-02 23:08:40','CR000001','1'),
	('SDA000060','SHS000030','DSQ000030','B','2020-01-14 13:45:44','2020-01-17 19:06:03','CR000001','1'),
	('SDA000061','SHS000031','DSQ000031','A','2020-01-15 03:47:52','2020-01-13 22:38:12','CR000001','1'),
	('SDA000062','SHS000031','DSQ000031','B','2020-01-15 14:08:05','2020-01-21 14:34:12','CR000001','1'),
	('SDA000063','SHS000032','DSQ000032','A','2020-01-23 02:19:43','2020-01-29 16:40:40','CR000001','1'),
	('SDA000064','SHS000032','DSQ000032','B','2020-02-02 02:39:30','2020-01-29 07:57:46','CR000001','1'),
	('SDA000065','SHS000033','DSQ000033','A','2020-02-02 18:36:45','2020-01-13 11:39:26','CR000001','1'),
	('SDA000066','SHS000033','DSQ000033','B','2020-01-13 08:14:49','2020-01-23 17:20:33','CR000001','1'),
	('SDA000067','SHS000034','DSQ000034','A','2020-01-31 13:53:09','2020-01-29 12:53:33','CR000001','1'),
	('SDA000068','SHS000034','DSQ000034','B','2020-01-16 20:33:40','2020-01-31 12:55:05','CR000001','1'),
	('SDA000069','SHS000035','DSQ000035','A','2020-02-01 09:29:03','2020-01-16 01:16:00','CR000001','1'),
	('SDA000070','SHS000035','DSQ000035','B','2020-01-27 09:46:07','2020-01-19 13:58:10','CR000001','1'),
	('SDA000071','SHS000036','DSQ000036','A','2020-01-16 00:16:05','2020-02-02 20:47:18','CR000001','1'),
	('SDA000072','SHS000036','DSQ000036','B','2020-01-14 00:29:22','2020-01-27 02:12:28','CR000001','1'),
	('SDA000073','SHS000037','DSQ000037','A','2020-01-22 20:56:58','2020-01-15 14:36:45','CR000001','1'),
	('SDA000074','SHS000037','DSQ000037','B','2020-01-24 06:28:32','2020-01-21 20:49:06','CR000001','1'),
	('SDA000075','SHS000038','DSQ000038','A','2020-01-24 06:13:44','2020-01-23 16:30:09','CR000001','1'),
	('SDA000076','SHS000038','DSQ000038','B','2020-02-02 01:46:56','2020-01-28 05:37:57','CR000001','1'),
	('SDA000077','SHS000039','DSQ000039','A','2020-01-31 20:19:28','2020-01-13 13:53:06','CR000001','1'),
	('SDA000078','SHS000039','DSQ000039','B','2020-01-15 00:31:07','2020-01-25 17:49:22','CR000001','1'),
	('SDA000079','SHS000040','DSQ000040','A','2020-01-15 12:51:53','2020-02-01 13:57:07','CR000001','1'),
	('SDA000080','SHS000040','DSQ000040','B','2020-01-15 09:30:50','2020-01-31 17:45:34','CR000001','1'),
	('SDA000081','SHS000041','DSQ000041','A','2020-01-29 08:38:05','2020-01-20 03:17:05','CR000001','1'),
	('SDA000082','SHS000041','DSQ000041','B','2020-02-02 20:49:05','2020-01-23 07:40:17','CR000001','1'),
	('SDA000083','SHS000042','DSQ000042','A','2020-01-22 03:54:09','2020-01-31 14:13:07','CR000001','1'),
	('SDA000084','SHS000042','DSQ000042','B','2020-01-20 11:03:41','2020-02-03 07:38:46','CR000001','1'),
	('SDA000085','SHS000043','DSQ000043','A','2020-01-30 18:49:28','2020-01-15 19:14:43','CR000001','1'),
	('SDA000086','SHS000043','DSQ000043','B','2020-02-02 09:55:19','2020-02-03 03:20:57','CR000001','1'),
	('SDA000087','SHS000044','DSQ000044','A','2020-01-22 13:18:53','2020-01-27 04:08:50','CR000001','1'),
	('SDA000088','SHS000044','DSQ000044','B','2020-01-16 23:06:23','2020-01-13 14:36:09','CR000001','1'),
	('SDA000089','SHS000045','DSQ000045','A','2020-01-24 03:11:03','2020-01-24 07:40:21','CR000001','1'),
	('SDA000090','SHS000045','DSQ000045','B','2020-01-23 14:19:44','2020-01-24 19:14:36','CR000001','1'),
	('SDA000091','SHS000046','DSQ000046','A','2020-01-23 09:29:41','2020-02-02 03:37:17','CR000001','1'),
	('SDA000092','SHS000046','DSQ000046','B','2020-01-14 15:22:26','2020-01-24 08:53:52','CR000001','1'),
	('SDA000093','SHS000047','DSQ000047','A','2020-01-15 09:44:15','2020-01-15 07:22:17','CR000001','1'),
	('SDA000094','SHS000047','DSQ000047','B','2020-01-20 02:39:42','2020-02-03 08:26:07','CR000001','1'),
	('SDA000095','SHS000048','DSQ000048','A','2020-01-22 05:50:28','2020-01-21 04:22:53','CR000001','1'),
	('SDA000096','SHS000048','DSQ000048','B','2020-01-28 17:35:13','2020-01-17 11:44:15','CR000001','1'),
	('SDA000097','SHS000049','DSQ000049','A','2020-01-30 05:07:45','2020-01-17 14:13:23','CR000001','1'),
	('SDA000098','SHS000049','DSQ000049','B','2020-01-30 15:28:45','2020-01-17 04:59:20','CR000001','1'),
	('SDA000099','SHS000050','DSQ000050','A','2020-01-30 23:47:05','2020-01-25 07:27:02','CR000001','1'),
	('SDA000100','SHS000050','DSQ000050','B','2020-01-31 11:32:16','2020-01-24 03:25:09','CR000001','1'),
	('SDA000101','SHS000051','DSQ000051','A','2020-01-24 20:57:13','2020-01-19 08:52:36','CR000001','1'),
	('SDA000102','SHS000051','DSQ000051','B','2020-01-28 01:07:33','2020-01-15 21:27:08','CR000001','1'),
	('SDA000103','SHS000052','DSQ000052','A','2020-01-17 13:42:08','2020-02-03 05:38:09','CR000001','1'),
	('SDA000104','SHS000052','DSQ000052','B','2020-01-28 15:57:29','2020-01-17 02:23:01','CR000001','1'),
	('SDA000105','SHS000053','DSQ000053','A','2020-01-16 01:27:53','2020-01-25 03:43:29','CR000001','1'),
	('SDA000106','SHS000053','DSQ000053','B','2020-01-18 06:35:53','2020-02-01 23:59:23','CR000001','1'),
	('SDA000107','SHS000054','DSQ000054','A','2020-01-22 23:21:30','2020-02-02 01:46:06','CR000001','1'),
	('SDA000108','SHS000054','DSQ000054','B','2020-02-01 08:05:03','2020-01-14 20:28:57','CR000001','1'),
	('SDA000109','SHS000055','DSQ000055','A','2020-01-20 21:12:28','2020-01-31 19:31:31','CR000001','1'),
	('SDA000110','SHS000055','DSQ000055','B','2020-01-31 19:01:49','2020-01-28 11:45:38','CR000001','1'),
	('SDA000111','SHS000056','DSQ000056','A','2020-01-15 07:42:48','2020-01-14 08:44:54','CR000001','1'),
	('SDA000112','SHS000056','DSQ000056','B','2020-01-12 23:36:24','2020-01-14 07:10:08','CR000001','1'),
	('SDA000113','SHS000057','DSQ000057','A','2020-01-17 21:18:51','2020-01-19 21:26:12','CR000001','1'),
	('SDA000114','SHS000057','DSQ000057','B','2020-01-25 19:58:45','2020-01-14 00:57:19','CR000001','1'),
	('SDA000115','SHS000058','DSQ000058','A','2020-02-02 17:20:20','2020-01-24 10:27:46','CR000001','1'),
	('SDA000116','SHS000058','DSQ000058','B','2020-01-29 08:05:32','2020-01-22 00:20:11','CR000001','1'),
	('SDA000117','SHS000059','DSQ000059','A','2020-01-13 14:59:59','2020-01-18 04:26:00','CR000001','1'),
	('SDA000118','SHS000059','DSQ000059','B','2020-01-18 22:06:30','2020-01-24 10:54:36','CR000001','1'),
	('SDA000119','SHS000060','DSQ000060','A','2020-01-19 09:17:04','2020-02-01 01:14:16','CR000001','1'),
	('SDA000120','SHS000060','DSQ000060','B','2020-01-16 19:34:24','2020-01-21 16:54:48','CR000001','1'),
	('SDA000121','SHS000061','DSQ000061','A','2020-01-20 03:24:27','2020-01-20 04:39:44','CR000001','1'),
	('SDA000122','SHS000061','DSQ000061','B','2020-01-18 01:15:12','2020-01-14 12:18:53','CR000001','1'),
	('SDA000123','SHS000062','DSQ000062','A','2020-01-29 02:01:53','2020-01-24 21:41:37','CR000001','1'),
	('SDA000124','SHS000062','DSQ000062','B','2020-01-13 03:47:28','2020-01-27 10:08:05','CR000001','1'),
	('SDA000125','SHS000063','DSQ000063','A','2020-01-17 01:31:58','2020-01-18 11:54:45','CR000001','1'),
	('SDA000126','SHS000063','DSQ000063','B','2020-01-27 01:03:58','2020-01-19 17:43:32','CR000001','1'),
	('SDA000127','SHS000064','DSQ000064','A','2020-01-20 09:10:33','2020-01-22 07:28:08','CR000001','1'),
	('SDA000128','SHS000064','DSQ000064','B','2020-01-28 11:47:09','2020-01-27 06:12:10','CR000001','1'),
	('SDA000129','SHS000065','DSQ000065','A','2020-01-21 04:34:38','2020-02-03 04:57:37','CR000002','1'),
	('SDA000130','SHS000065','DSQ000065','B','2020-01-14 00:25:30','2020-01-27 04:24:10','CR000002','1'),
	('SDA000131','SHS000066','DSQ000066','A','2020-01-24 04:17:09','2020-01-26 10:40:35','CR000002','1'),
	('SDA000132','SHS000066','DSQ000066','B','2020-01-20 20:07:31','2020-01-21 21:15:17','CR000002','1'),
	('SDA000133','SHS000067','DSQ000067','A','2020-01-19 17:15:57','2020-01-24 06:57:08','CR000002','1'),
	('SDA000134','SHS000067','DSQ000067','B','2020-01-27 23:36:08','2020-01-15 04:29:14','CR000002','1'),
	('SDA000135','SHS000068','DSQ000068','A','2020-01-14 10:21:57','2020-01-27 11:45:32','CR000002','1'),
	('SDA000136','SHS000068','DSQ000068','B','2020-01-27 11:03:30','2020-01-17 09:21:06','CR000002','1'),
	('SDA000137','SHS000069','DSQ000069','A','2020-02-01 08:52:14','2020-01-14 18:59:59','CR000002','1'),
	('SDA000138','SHS000069','DSQ000069','B','2020-01-24 22:10:41','2020-01-13 08:07:51','CR000002','1'),
	('SDA000139','SHS000070','DSQ000070','A','2020-01-16 20:22:28','2020-01-30 07:36:34','CR000002','1'),
	('SDA000140','SHS000070','DSQ000070','B','2020-01-26 02:05:20','2020-02-03 10:00:21','CR000002','1'),
	('SDA000141','SHS000071','DSQ000071','A','2020-01-25 16:04:58','2020-01-25 03:56:33','CR000002','1'),
	('SDA000142','SHS000071','DSQ000071','B','2020-01-20 09:53:44','2020-01-17 10:59:55','CR000002','1'),
	('SDA000143','SHS000072','DSQ000072','A','2020-01-18 06:38:10','2020-01-31 22:01:20','CR000002','1'),
	('SDA000144','SHS000072','DSQ000072','B','2020-01-25 09:03:25','2020-01-25 22:21:31','CR000002','1'),
	('SDA000145','SHS000073','DSQ000073','A','2020-01-21 08:04:23','2020-02-02 05:15:09','CR000002','1'),
	('SDA000146','SHS000073','DSQ000073','B','2020-01-30 14:14:33','2020-01-15 23:21:13','CR000002','1'),
	('SDA000147','SHS000074','DSQ000074','A','2020-02-01 09:59:01','2020-01-15 17:32:21','CR000002','1'),
	('SDA000148','SHS000074','DSQ000074','B','2020-01-13 10:13:17','2020-01-17 10:55:47','CR000002','1'),
	('SDA000149','SHS000075','DSQ000075','A','2020-01-23 10:40:37','2020-01-16 17:26:23','CR000002','1'),
	('SDA000150','SHS000075','DSQ000075','B','2020-01-13 10:21:19','2020-01-17 06:08:50','CR000002','1'),
	('SDA000151','SHS000076','DSQ000076','A','2020-01-25 22:52:27','2020-02-03 11:09:30','CR000002','1'),
	('SDA000152','SHS000076','DSQ000076','B','2020-02-02 16:37:48','2020-01-22 15:14:59','CR000002','1'),
	('SDA000153','SHS000077','DSQ000077','A','2020-01-29 16:27:12','2020-01-25 13:19:43','CR000002','1'),
	('SDA000154','SHS000077','DSQ000077','B','2020-01-27 14:13:50','2020-01-16 18:07:22','CR000002','1'),
	('SDA000155','SHS000078','DSQ000078','A','2020-01-27 20:55:21','2020-01-20 11:46:07','CR000002','1'),
	('SDA000156','SHS000078','DSQ000078','B','2020-01-28 03:04:23','2020-01-15 03:53:28','CR000002','1'),
	('SDA000157','SHS000079','DSQ000079','A','2020-01-25 03:33:00','2020-01-24 16:58:11','CR000002','1'),
	('SDA000158','SHS000079','DSQ000079','B','2020-01-21 12:26:24','2020-01-23 10:09:18','CR000002','1'),
	('SDA000159','SHS000080','DSQ000080','A','2020-01-27 11:53:12','2020-01-19 13:22:44','CR000002','1'),
	('SDA000160','SHS000080','DSQ000080','B','2020-02-03 10:06:20','2020-01-24 12:48:59','CR000002','1'),
	('SDA000161','SHS000081','DSQ000081','A','2020-01-15 20:55:12','2020-01-20 14:21:03','CR000002','1'),
	('SDA000162','SHS000081','DSQ000081','B','2020-01-28 12:08:10','2020-01-29 12:50:04','CR000002','1'),
	('SDA000163','SHS000082','DSQ000082','A','2020-01-16 20:56:55','2020-01-16 01:36:14','CR000002','1'),
	('SDA000164','SHS000082','DSQ000082','B','2020-01-15 19:02:01','2020-02-03 10:33:27','CR000002','1'),
	('SDA000165','SHS000083','DSQ000083','A','2020-02-02 11:13:44','2020-01-24 19:04:29','CR000002','1'),
	('SDA000166','SHS000083','DSQ000083','B','2020-01-16 02:32:23','2020-02-01 10:43:10','CR000002','1'),
	('SDA000167','SHS000084','DSQ000084','A','2020-01-13 00:08:03','2020-01-12 19:04:32','CR000002','1'),
	('SDA000168','SHS000084','DSQ000084','B','2020-01-30 17:09:08','2020-01-25 00:08:24','CR000002','1'),
	('SDA000169','SHS000085','DSQ000085','A','2020-01-25 16:35:38','2020-01-28 02:22:13','CR000002','1'),
	('SDA000170','SHS000085','DSQ000085','B','2020-01-23 07:18:06','2020-01-25 05:39:51','CR000002','1'),
	('SDA000171','SHS000086','DSQ000086','A','2020-02-01 06:42:21','2020-01-26 01:14:36','CR000002','1'),
	('SDA000172','SHS000086','DSQ000086','B','2020-01-31 21:55:36','2020-01-23 19:52:20','CR000002','1'),
	('SDA000173','SHS000087','DSQ000087','A','2020-01-13 05:17:43','2020-01-31 22:35:55','CR000002','1'),
	('SDA000174','SHS000087','DSQ000087','B','2020-01-19 22:31:32','2020-01-31 22:14:41','CR000002','1'),
	('SDA000175','SHS000088','DSQ000088','A','2020-01-16 20:22:37','2020-01-13 03:01:47','CR000002','1'),
	('SDA000176','SHS000088','DSQ000088','B','2020-01-13 00:12:26','2020-01-14 06:54:34','CR000002','1'),
	('SDA000177','SHS000089','DSQ000089','A','2020-01-22 08:59:56','2020-01-14 08:01:26','CR000002','1'),
	('SDA000178','SHS000089','DSQ000089','B','2020-01-23 06:38:56','2020-01-27 14:05:39','CR000002','1'),
	('SDA000179','SHS000090','DSQ000090','A','2020-01-24 18:58:51','2020-01-17 03:24:25','CR000002','1'),
	('SDA000180','SHS000090','DSQ000090','B','2020-01-22 01:58:12','2020-01-15 00:30:55','CR000002','1'),
	('SDA000181','SHS000091','DSQ000091','A','2020-01-31 16:14:04','2020-01-19 16:24:48','CR000002','1'),
	('SDA000182','SHS000091','DSQ000091','B','2020-01-26 15:45:49','2020-01-27 01:21:54','CR000002','1'),
	('SDA000183','SHS000092','DSQ000092','A','2020-01-15 19:38:22','2020-01-31 12:45:38','CR000002','1'),
	('SDA000184','SHS000092','DSQ000092','B','2020-01-26 21:04:13','2020-01-14 18:55:42','CR000002','1'),
	('SDA000185','SHS000093','DSQ000093','A','2020-01-22 08:03:55','2020-01-19 18:25:49','CR000002','1'),
	('SDA000186','SHS000093','DSQ000093','B','2020-01-23 06:10:36','2020-01-18 08:07:58','CR000002','1'),
	('SDA000187','SHS000094','DSQ000094','A','2020-01-15 05:28:23','2020-02-02 12:06:18','CR000002','1'),
	('SDA000188','SHS000094','DSQ000094','B','2020-01-15 23:42:12','2020-01-21 20:45:27','CR000002','1'),
	('SDA000189','SHS000095','DSQ000095','A','2020-01-31 02:53:45','2020-01-30 19:59:14','CR000002','1'),
	('SDA000190','SHS000095','DSQ000095','B','2020-01-19 08:35:39','2020-01-13 11:09:27','CR000002','1'),
	('SDA000191','SHS000096','DSQ000096','A','2020-01-20 17:17:16','2020-01-28 03:48:58','CR000002','1'),
	('SDA000192','SHS000096','DSQ000096','B','2020-01-25 01:27:41','2020-02-01 15:36:51','CR000002','1'),
	('SDA000193','SHS000097','DSQ000097','A','2020-01-21 22:21:02','2020-01-13 15:43:38','CR000002','1'),
	('SDA000194','SHS000097','DSQ000097','B','2020-01-24 12:07:24','2020-01-13 07:37:11','CR000002','1'),
	('SDA000195','SHS000098','DSQ000098','A','2020-01-29 14:41:00','2020-01-16 01:44:55','CR000002','1'),
	('SDA000196','SHS000098','DSQ000098','B','2020-01-23 10:52:39','2020-01-14 08:54:12','CR000002','1'),
	('SDA000197','SHS000099','DSQ000099','A','2020-02-01 23:58:20','2020-01-30 13:59:31','CR000002','1'),
	('SDA000198','SHS000099','DSQ000099','B','2020-02-02 09:16:46','2020-01-16 20:50:40','CR000002','1'),
	('SDA000199','SHS000100','DSQ000100','A','2020-01-21 22:25:31','2020-01-21 16:00:08','CR000002','1'),
	('SDA000200','SHS000100','DSQ000100','B','2020-01-18 07:08:48','2020-01-24 02:41:07','CR000002','1'),
	('SDA000201','SHS000101','DSQ000101','A','2020-02-01 18:40:29','2020-01-22 03:27:04','CR000002','1'),
	('SDA000202','SHS000101','DSQ000101','B','2020-01-19 16:35:42','2020-01-13 16:01:42','CR000002','1'),
	('SDA000203','SHS000102','DSQ000102','A','2020-01-25 02:06:51','2020-01-18 12:36:06','CR000002','1'),
	('SDA000204','SHS000102','DSQ000102','B','2020-01-15 18:18:52','2020-01-22 12:19:29','CR000002','1'),
	('SDA000205','SHS000103','DSQ000103','A','2020-01-18 02:17:36','2020-01-16 16:33:05','CR000002','1'),
	('SDA000206','SHS000103','DSQ000103','B','2020-02-02 09:36:46','2020-01-13 09:23:23','CR000002','1'),
	('SDA000207','SHS000104','DSQ000104','A','2020-01-18 21:24:07','2020-01-31 07:45:24','CR000002','1'),
	('SDA000208','SHS000104','DSQ000104','B','2020-02-01 05:37:37','2020-01-19 00:26:32','CR000002','1'),
	('SDA000209','SHS000105','DSQ000105','A','2020-01-17 13:58:22','2020-01-15 05:09:19','CR000002','1'),
	('SDA000210','SHS000105','DSQ000105','B','2020-01-16 15:15:39','2020-01-18 10:21:08','CR000002','1'),
	('SDA000211','SHS000106','DSQ000106','A','2020-01-14 17:59:17','2020-01-20 14:10:14','CR000002','1'),
	('SDA000212','SHS000106','DSQ000106','B','2020-01-25 03:07:40','2020-01-30 08:17:11','CR000002','1'),
	('SDA000213','SHS000107','DSQ000107','A','2020-01-19 07:08:03','2020-01-25 08:43:53','CR000002','1'),
	('SDA000214','SHS000107','DSQ000107','B','2020-01-31 18:23:47','2020-01-26 04:34:55','CR000002','1'),
	('SDA000215','SHS000108','DSQ000108','A','2020-01-13 00:38:27','2020-01-16 02:39:26','CR000002','1'),
	('SDA000216','SHS000108','DSQ000108','B','2020-01-12 17:47:44','2020-01-18 05:38:13','CR000002','1'),
	('SDA000217','SHS000109','DSQ000109','A','2020-01-22 02:57:38','2020-01-23 07:40:06','CR000002','1'),
	('SDA000218','SHS000109','DSQ000109','B','2020-01-24 03:30:38','2020-01-21 19:29:17','CR000002','1'),
	('SDA000219','SHS000110','DSQ000110','A','2020-01-30 00:04:56','2020-01-28 12:56:16','CR000002','1'),
	('SDA000220','SHS000110','DSQ000110','B','2020-01-22 20:30:47','2020-01-21 18:57:40','CR000002','1'),
	('SDA000221','SHS000111','DSQ000111','A','2020-01-21 18:56:29','2020-01-21 22:28:07','CR000002','1'),
	('SDA000222','SHS000111','DSQ000111','B','2020-01-21 05:35:02','2020-01-15 03:36:39','CR000002','1'),
	('SDA000223','SHS000112','DSQ000112','A','2020-01-18 22:46:43','2020-01-18 21:16:40','CR000002','1'),
	('SDA000224','SHS000112','DSQ000112','B','2020-01-19 02:21:27','2020-01-26 07:19:58','CR000002','1'),
	('SDA000225','SHS000113','DSQ000113','A','2020-02-01 13:13:07','2020-01-12 20:35:47','CR000002','1'),
	('SDA000226','SHS000113','DSQ000113','B','2020-02-01 19:28:56','2020-01-27 23:30:15','CR000002','1'),
	('SDA000227','SHS000114','DSQ000114','A','2020-01-19 00:52:34','2020-01-13 21:38:05','CR000002','1'),
	('SDA000228','SHS000114','DSQ000114','B','2020-01-25 02:54:06','2020-01-13 20:38:07','CR000002','1'),
	('SDA000229','SHS000115','DSQ000115','A','2020-02-01 00:52:21','2020-01-15 17:44:29','CR000002','1'),
	('SDA000230','SHS000115','DSQ000115','B','2020-01-17 12:41:18','2020-01-30 11:32:01','CR000002','1'),
	('SDA000231','SHS000116','DSQ000116','A','2020-01-17 04:31:23','2020-01-22 20:22:44','CR000002','1'),
	('SDA000232','SHS000116','DSQ000116','B','2020-01-15 04:37:07','2020-02-02 05:09:26','CR000002','1'),
	('SDA000233','SHS000117','DSQ000117','A','2020-01-28 20:05:23','2020-01-13 06:03:45','CR000002','1'),
	('SDA000234','SHS000117','DSQ000117','B','2020-01-23 14:21:05','2020-01-28 15:48:13','CR000002','1'),
	('SDA000235','SHS000118','DSQ000118','A','2020-01-20 19:49:03','2020-01-29 20:51:14','CR000002','1'),
	('SDA000236','SHS000118','DSQ000118','B','2020-01-21 06:52:48','2020-01-22 13:06:21','CR000002','1'),
	('SDA000237','SHS000119','DSQ000119','A','2020-01-19 21:33:10','2020-01-15 22:58:52','CR000002','1'),
	('SDA000238','SHS000119','DSQ000119','B','2020-01-25 17:51:21','2020-01-27 23:37:50','CR000002','1'),
	('SDA000239','SHS000120','DSQ000120','A','2020-01-12 18:13:30','2020-01-13 01:26:33','CR000002','1'),
	('SDA000240','SHS000120','DSQ000120','B','2020-01-14 06:50:12','2020-01-21 21:32:33','CR000002','1'),
	('SDA000241','SHS000121','DSQ000121','A','2020-01-26 08:11:37','2020-01-24 18:17:15','CR000002','1'),
	('SDA000242','SHS000121','DSQ000121','B','2020-01-15 04:41:18','2020-01-20 03:46:31','CR000002','1'),
	('SDA000243','SHS000122','DSQ000122','A','2020-02-03 08:18:06','2020-02-03 12:36:14','CR000002','1'),
	('SDA000244','SHS000122','DSQ000122','B','2020-01-20 07:28:32','2020-01-16 03:16:03','CR000002','1'),
	('SDA000245','SHS000123','DSQ000123','A','2020-01-29 12:38:51','2020-01-14 14:19:25','CR000002','1'),
	('SDA000246','SHS000123','DSQ000123','B','2020-01-29 00:48:18','2020-01-13 22:27:12','CR000002','1'),
	('SDA000247','SHS000124','DSQ000124','A','2020-01-13 09:34:04','2020-01-13 01:44:47','CR000002','1'),
	('SDA000248','SHS000124','DSQ000124','B','2020-01-18 21:20:24','2020-02-03 08:21:32','CR000002','1'),
	('SDA000249','SHS000125','DSQ000125','A','2020-01-28 19:57:21','2020-01-24 14:12:49','CR000002','1'),
	('SDA000250','SHS000125','DSQ000125','B','2020-01-29 16:04:48','2020-01-26 23:32:13','CR000002','1'),
	('SDA000251','SHS000126','DSQ000126','A','2020-01-31 22:10:08','2020-01-23 03:48:14','CR000002','1'),
	('SDA000252','SHS000126','DSQ000126','B','2020-01-29 04:12:28','2020-01-20 04:53:26','CR000002','1'),
	('SDA000253','SHS000127','DSQ000127','A','2020-02-01 00:02:54','2020-01-29 10:06:05','CR000002','1'),
	('SDA000254','SHS000127','DSQ000127','B','2020-02-01 12:01:57','2020-01-26 19:33:48','CR000002','1'),
	('SDA000255','SHS000128','DSQ000128','A','2020-01-26 00:34:10','2020-01-20 00:22:43','CR000002','1'),
	('SDA000256','SHS000128','DSQ000128','B','2020-01-29 12:24:45','2020-01-22 13:56:15','CR000002','1'),
	('SDA000257','SHS000129','DSQ000129','A','2020-01-13 09:57:14','2020-01-16 13:09:57','CR000003','1'),
	('SDA000258','SHS000129','DSQ000129','B','2020-01-31 04:01:08','2020-02-02 11:34:07','CR000003','1'),
	('SDA000259','SHS000130','DSQ000130','A','2020-01-18 04:30:52','2020-01-20 16:55:07','CR000003','1'),
	('SDA000260','SHS000130','DSQ000130','B','2020-01-18 00:54:19','2020-01-12 22:11:22','CR000003','1'),
	('SDA000261','SHS000131','DSQ000131','A','2020-02-03 08:16:35','2020-01-20 16:58:35','CR000003','1'),
	('SDA000262','SHS000131','DSQ000131','B','2020-01-19 21:52:58','2020-02-01 13:33:33','CR000003','1'),
	('SDA000263','SHS000132','DSQ000132','A','2020-01-16 15:54:13','2020-01-24 06:47:36','CR000003','1'),
	('SDA000264','SHS000132','DSQ000132','B','2020-01-23 17:44:30','2020-01-20 23:41:05','CR000003','1'),
	('SDA000265','SHS000133','DSQ000133','A','2020-01-24 00:40:37','2020-01-25 16:21:20','CR000003','1'),
	('SDA000266','SHS000133','DSQ000133','B','2020-01-23 19:39:15','2020-01-31 14:26:12','CR000003','1'),
	('SDA000267','SHS000134','DSQ000134','A','2020-01-14 04:01:01','2020-02-01 20:56:05','CR000003','1'),
	('SDA000268','SHS000134','DSQ000134','B','2020-01-14 11:41:14','2020-01-15 20:46:55','CR000003','1'),
	('SDA000269','SHS000135','DSQ000135','A','2020-01-17 20:54:09','2020-01-21 06:30:51','CR000003','1'),
	('SDA000270','SHS000135','DSQ000135','B','2020-01-31 06:41:05','2020-01-28 23:59:48','CR000003','1'),
	('SDA000271','SHS000136','DSQ000136','A','2020-01-20 00:08:00','2020-01-15 06:42:28','CR000003','1'),
	('SDA000272','SHS000136','DSQ000136','B','2020-01-26 00:14:02','2020-01-15 22:41:07','CR000003','1'),
	('SDA000273','SHS000137','DSQ000137','A','2020-02-03 00:54:12','2020-01-26 23:25:29','CR000003','1'),
	('SDA000274','SHS000137','DSQ000137','B','2020-01-13 04:48:49','2020-01-20 10:36:29','CR000003','1'),
	('SDA000275','SHS000138','DSQ000138','A','2020-01-21 06:46:53','2020-02-01 14:16:38','CR000003','1'),
	('SDA000276','SHS000138','DSQ000138','B','2020-01-31 05:30:45','2020-01-18 21:38:17','CR000003','1'),
	('SDA000277','SHS000139','DSQ000139','A','2020-01-23 10:10:45','2020-01-30 19:23:52','CR000003','1'),
	('SDA000278','SHS000139','DSQ000139','B','2020-01-21 21:09:12','2020-01-19 22:23:56','CR000003','1'),
	('SDA000279','SHS000140','DSQ000140','A','2020-01-15 07:51:49','2020-01-27 23:20:14','CR000003','1'),
	('SDA000280','SHS000140','DSQ000140','B','2020-01-16 09:47:50','2020-01-27 07:53:57','CR000003','1'),
	('SDA000281','SHS000141','DSQ000141','A','2020-01-26 21:29:33','2020-01-16 03:13:47','CR000003','1'),
	('SDA000282','SHS000141','DSQ000141','B','2020-01-17 11:53:09','2020-02-03 07:57:20','CR000003','1'),
	('SDA000283','SHS000142','DSQ000142','A','2020-01-19 23:41:49','2020-01-14 16:03:57','CR000003','1'),
	('SDA000284','SHS000142','DSQ000142','B','2020-01-13 08:53:30','2020-01-13 00:01:18','CR000003','1'),
	('SDA000285','SHS000143','DSQ000143','A','2020-01-22 03:29:35','2020-01-14 00:04:57','CR000003','1'),
	('SDA000286','SHS000143','DSQ000143','B','2020-01-17 08:39:30','2020-01-23 06:21:23','CR000003','1'),
	('SDA000287','SHS000144','DSQ000144','A','2020-01-17 17:36:19','2020-01-22 04:46:29','CR000003','1'),
	('SDA000288','SHS000144','DSQ000144','B','2020-01-17 17:53:05','2020-01-18 15:56:43','CR000003','1'),
	('SDA000289','SHS000145','DSQ000145','A','2020-01-21 02:35:32','2020-01-14 09:40:01','CR000003','1'),
	('SDA000290','SHS000145','DSQ000145','B','2020-01-29 19:22:21','2020-01-13 05:21:13','CR000003','1'),
	('SDA000291','SHS000146','DSQ000146','A','2020-01-14 00:06:40','2020-01-20 00:30:52','CR000003','1'),
	('SDA000292','SHS000146','DSQ000146','B','2020-01-13 07:58:06','2020-02-01 08:13:14','CR000003','1'),
	('SDA000293','SHS000147','DSQ000147','A','2020-01-20 17:25:41','2020-01-13 08:41:26','CR000003','1'),
	('SDA000294','SHS000147','DSQ000147','B','2020-01-28 08:09:19','2020-01-14 11:31:06','CR000003','1'),
	('SDA000295','SHS000148','DSQ000148','A','2020-01-23 08:57:33','2020-01-21 18:42:26','CR000003','1'),
	('SDA000296','SHS000148','DSQ000148','B','2020-01-21 14:00:32','2020-01-19 04:28:46','CR000003','1'),
	('SDA000297','SHS000149','DSQ000149','A','2020-01-28 13:27:43','2020-01-18 09:20:18','CR000003','1'),
	('SDA000298','SHS000149','DSQ000149','B','2020-01-30 04:18:10','2020-01-30 01:57:55','CR000003','1'),
	('SDA000299','SHS000150','DSQ000150','A','2020-01-21 14:37:14','2020-01-31 01:16:02','CR000003','1'),
	('SDA000300','SHS000150','DSQ000150','B','2020-01-13 01:33:43','2020-01-19 14:28:07','CR000003','1'),
	('SDA000301','SHS000151','DSQ000151','A','2020-01-17 21:26:52','2020-01-15 09:44:57','CR000003','1'),
	('SDA000302','SHS000151','DSQ000151','B','2020-01-20 05:23:11','2020-01-13 14:40:26','CR000003','1'),
	('SDA000303','SHS000152','DSQ000152','A','2020-01-16 16:18:44','2020-01-20 16:28:33','CR000003','1'),
	('SDA000304','SHS000152','DSQ000152','B','2020-01-26 22:13:07','2020-01-30 17:57:38','CR000003','1'),
	('SDA000305','SHS000153','DSQ000153','A','2020-01-22 20:30:47','2020-01-15 07:51:29','CR000003','1'),
	('SDA000306','SHS000153','DSQ000153','B','2020-01-14 11:06:56','2020-02-01 02:37:07','CR000003','1'),
	('SDA000307','SHS000154','DSQ000154','A','2020-01-24 21:46:30','2020-02-02 17:16:48','CR000003','1'),
	('SDA000308','SHS000154','DSQ000154','B','2020-01-17 16:46:47','2020-01-29 01:39:28','CR000003','1'),
	('SDA000309','SHS000155','DSQ000155','A','2020-01-20 04:23:35','2020-01-19 04:33:54','CR000003','1'),
	('SDA000310','SHS000155','DSQ000155','B','2020-01-14 19:56:35','2020-01-20 00:19:33','CR000003','1'),
	('SDA000311','SHS000156','DSQ000156','A','2020-01-28 16:15:05','2020-01-17 14:52:28','CR000003','1'),
	('SDA000312','SHS000156','DSQ000156','B','2020-01-27 07:32:32','2020-01-15 05:24:28','CR000003','1'),
	('SDA000313','SHS000157','DSQ000157','A','2020-01-16 04:45:41','2020-01-30 22:23:57','CR000003','1'),
	('SDA000314','SHS000157','DSQ000157','B','2020-01-29 02:12:26','2020-01-26 13:16:27','CR000003','1'),
	('SDA000315','SHS000158','DSQ000158','A','2020-01-19 11:05:59','2020-01-16 09:26:18','CR000003','1'),
	('SDA000316','SHS000158','DSQ000158','B','2020-01-15 11:35:32','2020-02-02 22:14:27','CR000003','1'),
	('SDA000317','SHS000159','DSQ000159','A','2020-01-18 10:35:33','2020-02-02 09:14:34','CR000003','1'),
	('SDA000318','SHS000159','DSQ000159','B','2020-01-28 04:35:53','2020-01-24 21:33:06','CR000003','1'),
	('SDA000319','SHS000160','DSQ000160','A','2020-01-16 09:53:33','2020-01-14 17:41:21','CR000003','1'),
	('SDA000320','SHS000160','DSQ000160','B','2020-01-22 23:59:45','2020-01-17 04:08:59','CR000003','1'),
	('SDA000321','SHS000161','DSQ000161','A','2020-01-14 21:54:00','2020-01-23 16:52:53','CR000003','1'),
	('SDA000322','SHS000161','DSQ000161','B','2020-01-25 14:32:35','2020-01-21 06:11:06','CR000003','1'),
	('SDA000323','SHS000162','DSQ000162','A','2020-01-21 22:01:51','2020-01-25 22:43:07','CR000003','1'),
	('SDA000324','SHS000162','DSQ000162','B','2020-02-02 23:10:29','2020-01-27 13:43:29','CR000003','1'),
	('SDA000325','SHS000163','DSQ000163','A','2020-01-23 14:47:31','2020-01-12 21:08:36','CR000003','1'),
	('SDA000326','SHS000163','DSQ000163','B','2020-01-30 16:29:54','2020-01-24 23:33:34','CR000003','1'),
	('SDA000327','SHS000164','DSQ000164','A','2020-02-03 08:31:27','2020-01-13 09:22:48','CR000003','1'),
	('SDA000328','SHS000164','DSQ000164','B','2020-01-25 07:45:36','2020-01-16 13:57:43','CR000003','1'),
	('SDA000329','SHS000165','DSQ000165','A','2020-01-17 18:58:56','2020-01-30 19:23:57','CR000003','1'),
	('SDA000330','SHS000165','DSQ000165','B','2020-01-27 16:20:14','2020-01-15 14:20:36','CR000003','1'),
	('SDA000331','SHS000166','DSQ000166','A','2020-01-24 15:58:07','2020-01-29 05:29:17','CR000003','1'),
	('SDA000332','SHS000166','DSQ000166','B','2020-01-18 16:00:54','2020-01-13 19:09:20','CR000003','1'),
	('SDA000333','SHS000167','DSQ000167','A','2020-01-19 11:29:53','2020-01-30 13:22:51','CR000003','1'),
	('SDA000334','SHS000167','DSQ000167','B','2020-01-28 01:25:45','2020-01-26 07:07:49','CR000003','1'),
	('SDA000335','SHS000168','DSQ000168','A','2020-01-29 19:46:08','2020-01-17 19:42:04','CR000003','1'),
	('SDA000336','SHS000168','DSQ000168','B','2020-01-20 11:07:01','2020-01-26 09:11:51','CR000003','1'),
	('SDA000337','SHS000169','DSQ000169','A','2020-01-24 11:19:33','2020-01-29 00:41:27','CR000003','1'),
	('SDA000338','SHS000169','DSQ000169','B','2020-01-30 07:23:44','2020-01-30 20:25:17','CR000003','1'),
	('SDA000339','SHS000170','DSQ000170','A','2020-01-18 00:14:41','2020-01-28 20:44:51','CR000003','1'),
	('SDA000340','SHS000170','DSQ000170','B','2020-01-26 23:47:11','2020-01-28 04:59:32','CR000003','1'),
	('SDA000341','SHS000171','DSQ000171','A','2020-01-18 10:20:11','2020-01-16 06:33:49','CR000003','1'),
	('SDA000342','SHS000171','DSQ000171','B','2020-02-02 08:20:19','2020-01-27 05:09:15','CR000003','1'),
	('SDA000343','SHS000172','DSQ000172','A','2020-01-17 23:27:27','2020-01-13 22:51:50','CR000003','1'),
	('SDA000344','SHS000172','DSQ000172','B','2020-01-12 23:57:32','2020-01-29 13:18:24','CR000003','1'),
	('SDA000345','SHS000173','DSQ000173','A','2020-01-20 23:13:41','2020-01-25 09:14:53','CR000003','1'),
	('SDA000346','SHS000173','DSQ000173','B','2020-01-14 09:07:35','2020-01-15 18:12:07','CR000003','1'),
	('SDA000347','SHS000174','DSQ000174','A','2020-01-25 02:00:21','2020-01-28 05:42:44','CR000003','1'),
	('SDA000348','SHS000174','DSQ000174','B','2020-01-25 14:59:10','2020-01-26 14:22:19','CR000003','1'),
	('SDA000349','SHS000175','DSQ000175','A','2020-02-03 05:49:43','2020-01-15 17:22:23','CR000003','1'),
	('SDA000350','SHS000175','DSQ000175','B','2020-01-28 07:19:56','2020-01-13 12:39:17','CR000003','1'),
	('SDA000351','SHS000176','DSQ000176','A','2020-02-03 03:13:18','2020-01-21 20:26:15','CR000003','1'),
	('SDA000352','SHS000176','DSQ000176','B','2020-01-28 20:03:29','2020-01-29 13:44:50','CR000003','1'),
	('SDA000353','SHS000177','DSQ000177','A','2020-01-19 00:05:58','2020-01-16 02:39:57','CR000003','1'),
	('SDA000354','SHS000177','DSQ000177','B','2020-01-21 22:12:53','2020-02-01 10:05:54','CR000003','1'),
	('SDA000355','SHS000178','DSQ000178','A','2020-01-29 01:12:50','2020-01-31 19:20:09','CR000003','1'),
	('SDA000356','SHS000178','DSQ000178','B','2020-01-25 01:56:21','2020-01-23 13:46:02','CR000003','1'),
	('SDA000357','SHS000179','DSQ000179','A','2020-01-26 11:34:48','2020-01-16 12:23:03','CR000003','1'),
	('SDA000358','SHS000179','DSQ000179','B','2020-01-12 20:19:46','2020-01-20 08:18:24','CR000003','1'),
	('SDA000359','SHS000180','DSQ000180','A','2020-01-27 15:47:58','2020-01-13 18:47:40','CR000003','1'),
	('SDA000360','SHS000180','DSQ000180','B','2020-01-22 18:06:15','2020-01-28 01:41:09','CR000003','1'),
	('SDA000361','SHS000181','DSQ000181','A','2020-01-28 17:12:44','2020-01-22 00:25:59','CR000003','1'),
	('SDA000362','SHS000181','DSQ000181','B','2020-01-18 05:57:50','2020-02-01 16:21:57','CR000003','1'),
	('SDA000363','SHS000182','DSQ000182','A','2020-01-22 13:33:31','2020-01-25 23:46:24','CR000003','1'),
	('SDA000364','SHS000182','DSQ000182','B','2020-01-14 19:58:10','2020-02-01 10:13:53','CR000003','1'),
	('SDA000365','SHS000183','DSQ000183','A','2020-01-24 16:05:20','2020-01-31 07:31:35','CR000003','1'),
	('SDA000366','SHS000183','DSQ000183','B','2020-01-22 01:35:15','2020-01-30 20:38:26','CR000003','1'),
	('SDA000367','SHS000184','DSQ000184','A','2020-01-14 23:15:35','2020-01-15 10:17:30','CR000003','1'),
	('SDA000368','SHS000184','DSQ000184','B','2020-01-30 00:18:25','2020-01-28 05:41:27','CR000003','1'),
	('SDA000369','SHS000185','DSQ000185','A','2020-01-19 11:03:50','2020-01-14 02:33:51','CR000003','1'),
	('SDA000370','SHS000185','DSQ000185','B','2020-02-02 17:18:08','2020-01-24 08:24:16','CR000003','1'),
	('SDA000371','SHS000186','DSQ000186','A','2020-01-15 05:17:25','2020-01-17 07:23:04','CR000003','1'),
	('SDA000372','SHS000186','DSQ000186','B','2020-01-16 13:56:01','2020-01-14 08:26:11','CR000003','1'),
	('SDA000373','SHS000187','DSQ000187','A','2020-01-27 00:33:58','2020-02-01 21:35:10','CR000003','1'),
	('SDA000374','SHS000187','DSQ000187','B','2020-02-03 04:29:32','2020-01-29 12:08:34','CR000003','1'),
	('SDA000375','SHS000188','DSQ000188','A','2020-01-20 19:44:19','2020-01-28 21:47:48','CR000003','1'),
	('SDA000376','SHS000188','DSQ000188','B','2020-01-25 11:21:52','2020-01-31 03:00:48','CR000003','1'),
	('SDA000377','SHS000189','DSQ000189','A','2020-02-01 23:21:24','2020-01-25 01:40:17','CR000003','1'),
	('SDA000378','SHS000189','DSQ000189','B','2020-01-26 18:39:17','2020-01-25 00:09:05','CR000003','1'),
	('SDA000379','SHS000190','DSQ000190','A','2020-01-31 20:21:49','2020-01-29 04:04:46','CR000003','1'),
	('SDA000380','SHS000190','DSQ000190','B','2020-01-25 17:19:23','2020-01-23 03:06:00','CR000003','1'),
	('SDA000381','SHS000191','DSQ000191','A','2020-01-13 05:34:22','2020-01-14 21:44:52','CR000003','1'),
	('SDA000382','SHS000191','DSQ000191','B','2020-01-21 07:00:41','2020-01-23 09:38:27','CR000003','1'),
	('SDA000383','SHS000192','DSQ000192','A','2020-01-15 21:53:40','2020-01-29 10:57:09','CR000003','1'),
	('SDA000384','SHS000192','DSQ000192','B','2020-01-26 18:59:49','2020-01-13 21:39:41','CR000003','1'),
	('SDA000385','SHS000193','DSQ000193','A','2020-01-21 20:23:37','2020-01-19 15:16:36','CR000004','1'),
	('SDA000386','SHS000193','DSQ000193','B','2020-01-26 13:28:53','2020-02-02 08:21:51','CR000004','1'),
	('SDA000387','SHS000194','DSQ000194','A','2020-01-25 00:18:06','2020-01-24 12:36:12','CR000004','1'),
	('SDA000388','SHS000194','DSQ000194','B','2020-01-27 03:31:51','2020-01-30 15:12:19','CR000004','1'),
	('SDA000389','SHS000195','DSQ000195','A','2020-01-16 12:28:29','2020-01-13 23:10:42','CR000004','1'),
	('SDA000390','SHS000195','DSQ000195','B','2020-01-22 09:00:59','2020-01-21 02:11:02','CR000004','1'),
	('SDA000391','SHS000196','DSQ000196','A','2020-01-15 01:04:38','2020-01-24 19:22:55','CR000004','1'),
	('SDA000392','SHS000196','DSQ000196','B','2020-01-14 14:50:49','2020-02-01 09:39:50','CR000004','1'),
	('SDA000393','SHS000197','DSQ000197','A','2020-01-15 13:53:18','2020-01-16 12:21:56','CR000004','1'),
	('SDA000394','SHS000197','DSQ000197','B','2020-01-18 16:16:48','2020-01-29 00:35:40','CR000004','1'),
	('SDA000395','SHS000198','DSQ000198','A','2020-01-19 05:58:15','2020-01-28 01:45:46','CR000004','1'),
	('SDA000396','SHS000198','DSQ000198','B','2020-01-30 15:17:01','2020-01-13 12:24:32','CR000004','1'),
	('SDA000397','SHS000199','DSQ000199','A','2020-01-25 00:54:34','2020-01-16 15:02:57','CR000004','1'),
	('SDA000398','SHS000199','DSQ000199','B','2020-01-30 16:16:05','2020-01-23 08:24:42','CR000004','1'),
	('SDA000399','SHS000200','DSQ000200','A','2020-01-18 08:00:46','2020-01-13 00:50:06','CR000004','1'),
	('SDA000400','SHS000200','DSQ000200','B','2020-01-31 04:08:37','2020-01-20 22:19:27','CR000004','1'),
	('SDA000401','SHS000201','DSQ000201','A','2020-02-02 18:47:32','2020-01-12 23:22:52','CR000004','1'),
	('SDA000402','SHS000201','DSQ000201','B','2020-01-22 23:51:22','2020-02-02 19:15:10','CR000004','1'),
	('SDA000403','SHS000202','DSQ000202','A','2020-01-16 04:52:32','2020-01-21 00:23:29','CR000004','1'),
	('SDA000404','SHS000202','DSQ000202','B','2020-01-27 07:02:23','2020-01-27 07:38:29','CR000004','1'),
	('SDA000405','SHS000203','DSQ000203','A','2020-01-30 03:28:18','2020-01-24 19:00:06','CR000004','1'),
	('SDA000406','SHS000203','DSQ000203','B','2020-01-12 20:16:06','2020-01-15 02:36:48','CR000004','1'),
	('SDA000407','SHS000204','DSQ000204','A','2020-01-25 08:48:15','2020-01-24 23:52:32','CR000004','1'),
	('SDA000408','SHS000204','DSQ000204','B','2020-01-30 02:56:00','2020-01-22 23:03:56','CR000004','1'),
	('SDA000409','SHS000205','DSQ000205','A','2020-01-20 10:08:13','2020-01-29 14:51:25','CR000004','1'),
	('SDA000410','SHS000205','DSQ000205','B','2020-01-24 01:03:24','2020-01-26 00:33:51','CR000004','1'),
	('SDA000411','SHS000206','DSQ000206','A','2020-01-22 00:21:56','2020-01-13 11:39:31','CR000004','1'),
	('SDA000412','SHS000206','DSQ000206','B','2020-01-31 06:21:08','2020-01-16 03:29:48','CR000004','1'),
	('SDA000413','SHS000207','DSQ000207','A','2020-01-13 12:24:45','2020-01-27 19:39:02','CR000004','1'),
	('SDA000414','SHS000207','DSQ000207','B','2020-01-27 01:34:44','2020-01-13 05:28:46','CR000004','1'),
	('SDA000415','SHS000208','DSQ000208','A','2020-01-23 19:02:54','2020-01-21 10:18:30','CR000004','1'),
	('SDA000416','SHS000208','DSQ000208','B','2020-01-14 08:04:41','2020-01-31 11:52:04','CR000004','1'),
	('SDA000417','SHS000209','DSQ000209','A','2020-01-28 21:41:27','2020-01-23 12:17:43','CR000004','1'),
	('SDA000418','SHS000209','DSQ000209','B','2020-01-27 18:58:17','2020-01-25 21:36:48','CR000004','1'),
	('SDA000419','SHS000210','DSQ000210','A','2020-02-01 16:45:41','2020-01-20 21:42:56','CR000004','1'),
	('SDA000420','SHS000210','DSQ000210','B','2020-01-20 14:35:57','2020-01-25 21:12:52','CR000004','1'),
	('SDA000421','SHS000211','DSQ000211','A','2020-01-15 10:45:18','2020-01-23 15:31:53','CR000004','1'),
	('SDA000422','SHS000211','DSQ000211','B','2020-01-13 03:24:09','2020-01-27 19:08:31','CR000004','1'),
	('SDA000423','SHS000212','DSQ000212','A','2020-01-28 23:40:01','2020-01-27 23:20:49','CR000004','1'),
	('SDA000424','SHS000212','DSQ000212','B','2020-02-03 10:48:51','2020-01-19 09:41:28','CR000004','1'),
	('SDA000425','SHS000213','DSQ000213','A','2020-02-02 22:50:00','2020-01-30 23:40:45','CR000004','1'),
	('SDA000426','SHS000213','DSQ000213','B','2020-01-14 18:47:53','2020-02-03 02:16:54','CR000004','1'),
	('SDA000427','SHS000214','DSQ000214','A','2020-02-01 06:23:31','2020-01-20 22:17:05','CR000004','1'),
	('SDA000428','SHS000214','DSQ000214','B','2020-01-29 03:28:41','2020-01-21 13:51:00','CR000004','1'),
	('SDA000429','SHS000215','DSQ000215','A','2020-02-01 11:25:34','2020-02-03 04:29:05','CR000004','1'),
	('SDA000430','SHS000215','DSQ000215','B','2020-01-31 22:13:59','2020-01-13 14:06:24','CR000004','1'),
	('SDA000431','SHS000216','DSQ000216','A','2020-02-01 10:03:19','2020-01-15 07:31:26','CR000004','1'),
	('SDA000432','SHS000216','DSQ000216','B','2020-01-15 09:29:01','2020-01-17 10:29:46','CR000004','1'),
	('SDA000433','SHS000217','DSQ000217','A','2020-01-16 06:50:40','2020-01-25 12:01:55','CR000004','1'),
	('SDA000434','SHS000217','DSQ000217','B','2020-01-19 16:10:28','2020-01-31 15:16:26','CR000004','1'),
	('SDA000435','SHS000218','DSQ000218','A','2020-01-14 13:21:10','2020-02-02 07:35:20','CR000004','1'),
	('SDA000436','SHS000218','DSQ000218','B','2020-01-27 22:51:57','2020-01-20 17:38:18','CR000004','1'),
	('SDA000437','SHS000219','DSQ000219','A','2020-01-18 02:24:10','2020-01-16 10:16:59','CR000004','1'),
	('SDA000438','SHS000219','DSQ000219','B','2020-01-23 09:34:12','2020-01-19 17:16:32','CR000004','1'),
	('SDA000439','SHS000220','DSQ000220','A','2020-01-23 00:32:39','2020-01-17 06:20:18','CR000004','1'),
	('SDA000440','SHS000220','DSQ000220','B','2020-01-13 03:25:27','2020-01-31 19:38:54','CR000004','1'),
	('SDA000441','SHS000221','DSQ000221','A','2020-01-24 11:00:26','2020-01-27 02:12:44','CR000004','1'),
	('SDA000442','SHS000221','DSQ000221','B','2020-01-19 12:46:28','2020-01-30 05:26:07','CR000004','1'),
	('SDA000443','SHS000222','DSQ000222','A','2020-01-17 19:10:29','2020-01-27 02:01:12','CR000004','1'),
	('SDA000444','SHS000222','DSQ000222','B','2020-01-23 13:14:45','2020-01-27 21:56:43','CR000004','1'),
	('SDA000445','SHS000223','DSQ000223','A','2020-01-19 10:16:28','2020-01-30 14:53:59','CR000004','1'),
	('SDA000446','SHS000223','DSQ000223','B','2020-01-14 02:41:52','2020-01-22 21:27:33','CR000004','1'),
	('SDA000447','SHS000224','DSQ000224','A','2020-01-26 18:10:49','2020-01-15 21:14:47','CR000004','1'),
	('SDA000448','SHS000224','DSQ000224','B','2020-01-14 17:13:49','2020-01-31 09:31:09','CR000004','1'),
	('SDA000449','SHS000225','DSQ000225','A','2020-01-27 16:55:49','2020-01-14 11:09:09','CR000004','1'),
	('SDA000450','SHS000225','DSQ000225','B','2020-01-14 05:10:22','2020-01-30 13:57:43','CR000004','1'),
	('SDA000451','SHS000226','DSQ000226','A','2020-01-29 14:00:41','2020-01-16 06:57:01','CR000004','1'),
	('SDA000452','SHS000226','DSQ000226','B','2020-02-02 07:53:53','2020-01-22 10:12:45','CR000004','1'),
	('SDA000453','SHS000227','DSQ000227','A','2020-01-22 17:01:17','2020-01-19 11:12:53','CR000004','1'),
	('SDA000454','SHS000227','DSQ000227','B','2020-01-18 11:19:51','2020-01-24 12:46:52','CR000004','1'),
	('SDA000455','SHS000228','DSQ000228','A','2020-01-22 01:25:09','2020-01-20 17:11:33','CR000004','1'),
	('SDA000456','SHS000228','DSQ000228','B','2020-01-15 02:58:38','2020-01-15 07:04:21','CR000004','1'),
	('SDA000457','SHS000229','DSQ000229','A','2020-01-30 15:28:59','2020-01-22 23:32:12','CR000004','1'),
	('SDA000458','SHS000229','DSQ000229','B','2020-01-25 00:58:20','2020-01-31 03:53:50','CR000004','1'),
	('SDA000459','SHS000230','DSQ000230','A','2020-01-14 01:44:31','2020-01-26 21:28:57','CR000004','1'),
	('SDA000460','SHS000230','DSQ000230','B','2020-01-14 20:27:31','2020-02-01 00:17:25','CR000004','1'),
	('SDA000461','SHS000231','DSQ000231','A','2020-01-22 09:36:28','2020-01-19 14:09:53','CR000004','1'),
	('SDA000462','SHS000231','DSQ000231','B','2020-01-17 02:07:38','2020-01-28 13:58:18','CR000004','1'),
	('SDA000463','SHS000232','DSQ000232','A','2020-01-26 19:20:10','2020-01-24 13:28:49','CR000004','1'),
	('SDA000464','SHS000232','DSQ000232','B','2020-01-31 17:36:37','2020-01-18 17:02:42','CR000004','1'),
	('SDA000465','SHS000233','DSQ000233','A','2020-01-22 19:08:47','2020-01-23 09:19:12','CR000004','1'),
	('SDA000466','SHS000233','DSQ000233','B','2020-01-22 16:59:49','2020-02-01 12:55:22','CR000004','1'),
	('SDA000467','SHS000234','DSQ000234','A','2020-01-28 14:35:17','2020-02-02 06:20:33','CR000004','1'),
	('SDA000468','SHS000234','DSQ000234','B','2020-01-22 19:50:27','2020-01-18 18:09:48','CR000004','1'),
	('SDA000469','SHS000235','DSQ000235','A','2020-01-18 02:47:32','2020-01-27 19:31:53','CR000004','1'),
	('SDA000470','SHS000235','DSQ000235','B','2020-01-23 03:51:45','2020-01-20 10:21:09','CR000004','1'),
	('SDA000471','SHS000236','DSQ000236','A','2020-01-17 08:33:06','2020-01-21 23:35:01','CR000004','1'),
	('SDA000472','SHS000236','DSQ000236','B','2020-01-23 07:06:08','2020-01-24 00:19:00','CR000004','1'),
	('SDA000473','SHS000237','DSQ000237','A','2020-01-14 18:10:50','2020-01-30 05:53:15','CR000004','1'),
	('SDA000474','SHS000237','DSQ000237','B','2020-01-14 10:21:16','2020-01-26 10:38:21','CR000004','1'),
	('SDA000475','SHS000238','DSQ000238','A','2020-01-24 13:38:54','2020-01-28 12:54:36','CR000004','1'),
	('SDA000476','SHS000238','DSQ000238','B','2020-01-13 08:07:30','2020-01-26 03:51:56','CR000004','1'),
	('SDA000477','SHS000239','DSQ000239','A','2020-01-16 02:45:04','2020-01-15 00:22:20','CR000004','1'),
	('SDA000478','SHS000239','DSQ000239','B','2020-01-28 20:04:29','2020-01-23 14:06:50','CR000004','1'),
	('SDA000479','SHS000240','DSQ000240','A','2020-01-19 02:40:23','2020-01-13 20:18:01','CR000004','1'),
	('SDA000480','SHS000240','DSQ000240','B','2020-01-31 02:34:23','2020-01-28 23:52:14','CR000004','1'),
	('SDA000481','SHS000241','DSQ000241','A','2020-01-18 16:12:27','2020-01-12 23:39:16','CR000004','1'),
	('SDA000482','SHS000241','DSQ000241','B','2020-01-16 12:21:31','2020-01-24 06:20:07','CR000004','1'),
	('SDA000483','SHS000242','DSQ000242','A','2020-01-22 07:44:47','2020-01-20 20:11:32','CR000004','1'),
	('SDA000484','SHS000242','DSQ000242','B','2020-01-29 01:00:42','2020-01-28 22:47:08','CR000004','1'),
	('SDA000485','SHS000243','DSQ000243','A','2020-01-24 08:43:13','2020-02-01 19:05:23','CR000004','1'),
	('SDA000486','SHS000243','DSQ000243','B','2020-01-17 16:46:14','2020-01-26 01:40:41','CR000004','1'),
	('SDA000487','SHS000244','DSQ000244','A','2020-01-29 03:27:04','2020-01-30 18:38:40','CR000004','1'),
	('SDA000488','SHS000244','DSQ000244','B','2020-01-27 16:46:40','2020-01-20 09:56:42','CR000004','1'),
	('SDA000489','SHS000245','DSQ000245','A','2020-01-27 19:12:35','2020-01-14 18:42:27','CR000004','1'),
	('SDA000490','SHS000245','DSQ000245','B','2020-02-01 20:07:18','2020-01-26 10:03:02','CR000004','1'),
	('SDA000491','SHS000246','DSQ000246','A','2020-01-14 05:23:50','2020-01-31 23:20:14','CR000004','1'),
	('SDA000492','SHS000246','DSQ000246','B','2020-01-31 15:14:33','2020-01-22 03:33:54','CR000004','1'),
	('SDA000493','SHS000247','DSQ000247','A','2020-01-30 05:51:04','2020-01-29 17:08:35','CR000004','1'),
	('SDA000494','SHS000247','DSQ000247','B','2020-02-01 10:24:03','2020-01-28 08:33:30','CR000004','1'),
	('SDA000495','SHS000248','DSQ000248','A','2020-01-22 18:03:10','2020-02-02 04:04:48','CR000004','1'),
	('SDA000496','SHS000248','DSQ000248','B','2020-01-31 11:45:34','2020-01-17 02:22:26','CR000004','1'),
	('SDA000497','SHS000249','DSQ000249','A','2020-01-27 22:20:37','2020-01-30 05:41:07','CR000004','1'),
	('SDA000498','SHS000249','DSQ000249','B','2020-01-28 07:00:40','2020-01-31 04:07:39','CR000004','1'),
	('SDA000499','SHS000250','DSQ000250','A','2020-01-22 17:05:08','2020-01-21 22:05:17','CR000004','1'),
	('SDA000500','SHS000250','DSQ000250','B','2020-01-27 16:11:07','2020-01-31 21:23:01','CR000004','1'),
	('SDA000501','SHS000251','DSQ000251','A','2020-01-15 15:16:53','2020-01-23 03:36:01','CR000004','1'),
	('SDA000502','SHS000251','DSQ000251','B','2020-01-23 22:05:56','2020-01-26 18:06:19','CR000004','1'),
	('SDA000503','SHS000252','DSQ000252','A','2020-02-01 23:38:58','2020-01-27 04:53:46','CR000004','1'),
	('SDA000504','SHS000252','DSQ000252','B','2020-01-16 13:16:01','2020-01-18 08:07:18','CR000004','1'),
	('SDA000505','SHS000253','DSQ000253','A','2020-01-29 19:46:11','2020-01-25 04:54:06','CR000004','1'),
	('SDA000506','SHS000253','DSQ000253','B','2020-01-17 14:46:16','2020-01-28 03:15:12','CR000004','1'),
	('SDA000507','SHS000254','DSQ000254','A','2020-01-17 20:15:49','2020-01-12 23:10:35','CR000004','1'),
	('SDA000508','SHS000254','DSQ000254','B','2020-01-16 21:11:04','2020-01-15 02:14:19','CR000004','1'),
	('SDA000509','SHS000255','DSQ000255','A','2020-01-20 08:13:28','2020-01-24 12:31:47','CR000004','1'),
	('SDA000510','SHS000255','DSQ000255','B','2020-01-18 07:23:08','2020-01-30 17:20:44','CR000004','1'),
	('SDA000511','SHS000256','DSQ000256','A','2020-01-29 09:19:13','2020-01-13 14:43:15','CR000004','1'),
	('SDA000512','SHS000256','DSQ000256','B','2020-01-22 15:32:20','2020-01-21 20:00:17','CR000004','1');

insert into survey_status_data values
	('UN_SUBMITTED','未提交','UN_SUBMITTED','P000001','1'),
	('SUBMITTE','已提交','SUBMITTE','P000001','1'),
	('DRAFT','草稿','DRAFT','P000001','1');

insert into health_survey_report_data values
	('HSR000001','2020年1月25日益州小学学生健康调查问卷','2020-01-24 00:17:52','张三','益州小学','教科院一年级5班','刘婵','A01','刘备','18012341234','S000001','T000001','CDHS000001','1'),
	('HSR000002','2020年1月25日益州小学学生健康调查问卷0002','2020-01-22 00:06:25','张三0002','大源中学','教科院二年级3班','刘阿斗','A01','刘玄德','13900000002','S000001','T000001','CDHS000001','1'),
	('HSR000003','2020年1月25日益州小学学生健康调查问卷0003','2020-01-17 18:54:38','张三0003','南山中学','教科院一年级5班','李天一','A01','张飞','13900000003','S000001','T000001','CDHS000002','1'),
	('HSR000004','2020年1月25日益州小学学生健康调查问卷0004','2020-01-31 04:59:39','张三0004','益州小学','教科院二年级3班','刘婵','A01','张翼德','13900000004','S000001','T000001','CDHS000002','1'),
	('HSR000005','2020年1月25日益州小学学生健康调查问卷0005','2020-02-02 17:51:43','张三0005','大源中学','教科院一年级5班','刘阿斗','A01','刘备','13900000005','S000002','T000002','CDHS000003','1'),
	('HSR000006','2020年1月25日益州小学学生健康调查问卷0006','2020-01-30 02:39:34','张三0006','南山中学','教科院二年级3班','李天一','A01','刘玄德','13900000006','S000002','T000002','CDHS000003','1'),
	('HSR000007','2020年1月25日益州小学学生健康调查问卷0007','2020-01-14 19:04:44','张三0007','益州小学','教科院一年级5班','刘婵','A01','张飞','13900000007','S000002','T000002','CDHS000004','1'),
	('HSR000008','2020年1月25日益州小学学生健康调查问卷0008','2020-01-31 06:12:46','张三0008','大源中学','教科院二年级3班','刘阿斗','A01','张翼德','13900000008','S000002','T000002','CDHS000004','1'),
	('HSR000009','2020年1月25日益州小学学生健康调查问卷0009','2020-01-23 21:16:12','张三0009','南山中学','教科院一年级5班','李天一','A01','刘备','13900000009','S000003','T000003','CDHS000005','1'),
	('HSR000010','2020年1月25日益州小学学生健康调查问卷0010','2020-01-13 12:50:00','张三0010','益州小学','教科院二年级3班','刘婵','A01','刘玄德','13900000010','S000003','T000003','CDHS000005','1'),
	('HSR000011','2020年1月25日益州小学学生健康调查问卷0011','2020-01-21 16:56:24','张三0011','大源中学','教科院一年级5班','刘阿斗','A01','张飞','13900000011','S000003','T000003','CDHS000006','1'),
	('HSR000012','2020年1月25日益州小学学生健康调查问卷0012','2020-01-15 14:42:34','张三0012','南山中学','教科院二年级3班','李天一','A01','张翼德','13900000012','S000003','T000003','CDHS000006','1'),
	('HSR000013','2020年1月25日益州小学学生健康调查问卷0013','2020-01-23 00:28:21','张三0013','益州小学','教科院一年级5班','刘婵','A01','刘备','13900000013','S000004','T000004','CDHS000007','1'),
	('HSR000014','2020年1月25日益州小学学生健康调查问卷0014','2020-01-21 01:34:38','张三0014','大源中学','教科院二年级3班','刘阿斗','A01','刘玄德','13900000014','S000004','T000004','CDHS000007','1'),
	('HSR000015','2020年1月25日益州小学学生健康调查问卷0015','2020-01-16 13:39:13','张三0015','南山中学','教科院一年级5班','李天一','A01','张飞','13900000015','S000004','T000004','CDHS000008','1'),
	('HSR000016','2020年1月25日益州小学学生健康调查问卷0016','2020-02-03 13:00:18','张三0016','益州小学','教科院二年级3班','刘婵','A01','张翼德','13900000016','S000004','T000004','CDHS000008','1'),
	('HSR000017','2020年1月25日益州小学学生健康调查问卷0017','2020-01-22 06:50:49','张三0017','大源中学','教科院一年级5班','刘阿斗','A01','刘备','13900000017','S000005','T000005','CDHS000009','1'),
	('HSR000018','2020年1月25日益州小学学生健康调查问卷0018','2020-01-20 04:05:30','张三0018','南山中学','教科院二年级3班','李天一','A01','刘玄德','13900000018','S000005','T000005','CDHS000009','1'),
	('HSR000019','2020年1月25日益州小学学生健康调查问卷0019','2020-01-28 05:21:26','张三0019','益州小学','教科院一年级5班','刘婵','A01','张飞','13900000019','S000005','T000005','CDHS000010','1'),
	('HSR000020','2020年1月25日益州小学学生健康调查问卷0020','2020-01-30 12:01:07','张三0020','大源中学','教科院二年级3班','刘阿斗','A01','张翼德','13900000020','S000005','T000005','CDHS000010','1'),
	('HSR000021','2020年1月25日益州小学学生健康调查问卷0021','2020-01-13 01:55:00','张三0021','南山中学','教科院一年级5班','李天一','A01','刘备','13900000021','S000006','T000006','CDHS000011','1'),
	('HSR000022','2020年1月25日益州小学学生健康调查问卷0022','2020-01-22 02:11:40','张三0022','益州小学','教科院二年级3班','刘婵','A01','刘玄德','13900000022','S000006','T000006','CDHS000011','1'),
	('HSR000023','2020年1月25日益州小学学生健康调查问卷0023','2020-01-29 07:05:01','张三0023','大源中学','教科院一年级5班','刘阿斗','A01','张飞','13900000023','S000006','T000006','CDHS000012','1'),
	('HSR000024','2020年1月25日益州小学学生健康调查问卷0024','2020-01-18 10:55:59','张三0024','南山中学','教科院二年级3班','李天一','A01','张翼德','13900000024','S000006','T000006','CDHS000012','1'),
	('HSR000025','2020年1月25日益州小学学生健康调查问卷0025','2020-01-28 22:54:18','张三0025','益州小学','教科院一年级5班','刘婵','A01','刘备','13900000025','S000007','T000007','CDHS000013','1'),
	('HSR000026','2020年1月25日益州小学学生健康调查问卷0026','2020-01-16 04:31:14','张三0026','大源中学','教科院二年级3班','刘阿斗','A01','刘玄德','13900000026','S000007','T000007','CDHS000013','1'),
	('HSR000027','2020年1月25日益州小学学生健康调查问卷0027','2020-01-23 20:50:11','张三0027','南山中学','教科院一年级5班','李天一','A01','张飞','13900000027','S000007','T000007','CDHS000014','1'),
	('HSR000028','2020年1月25日益州小学学生健康调查问卷0028','2020-01-18 20:51:05','张三0028','益州小学','教科院二年级3班','刘婵','A01','张翼德','13900000028','S000007','T000007','CDHS000014','1'),
	('HSR000029','2020年1月25日益州小学学生健康调查问卷0029','2020-01-14 11:46:33','张三0029','大源中学','教科院一年级5班','刘阿斗','A01','刘备','13900000029','S000008','T000008','CDHS000015','1'),
	('HSR000030','2020年1月25日益州小学学生健康调查问卷0030','2020-01-16 02:16:48','张三0030','南山中学','教科院二年级3班','李天一','A01','刘玄德','13900000030','S000008','T000008','CDHS000015','1'),
	('HSR000031','2020年1月25日益州小学学生健康调查问卷0031','2020-01-19 11:02:35','张三0031','益州小学','教科院一年级5班','刘婵','A01','张飞','13900000031','S000008','T000008','CDHS000016','1'),
	('HSR000032','2020年1月25日益州小学学生健康调查问卷0032','2020-01-21 16:10:33','张三0032','大源中学','教科院二年级3班','刘阿斗','A01','张翼德','13900000032','S000008','T000008','CDHS000016','1'),
	('HSR000033','2020年1月25日益州小学学生健康调查问卷0033','2020-01-21 13:43:25','张三0033','南山中学','教科院一年级5班','李天一','A01','刘备','13900000033','S000009','T000009','CDHS000017','1'),
	('HSR000034','2020年1月25日益州小学学生健康调查问卷0034','2020-01-26 10:07:50','张三0034','益州小学','教科院二年级3班','刘婵','A01','刘玄德','13900000034','S000009','T000009','CDHS000017','1'),
	('HSR000035','2020年1月25日益州小学学生健康调查问卷0035','2020-01-27 19:34:31','张三0035','大源中学','教科院一年级5班','刘阿斗','A01','张飞','13900000035','S000009','T000009','CDHS000018','1'),
	('HSR000036','2020年1月25日益州小学学生健康调查问卷0036','2020-01-27 12:00:46','张三0036','南山中学','教科院二年级3班','李天一','A01','张翼德','13900000036','S000009','T000009','CDHS000018','1'),
	('HSR000037','2020年1月25日益州小学学生健康调查问卷0037','2020-01-25 15:30:47','张三0037','益州小学','教科院一年级5班','刘婵','A01','刘备','13900000037','S000010','T000010','CDHS000019','1'),
	('HSR000038','2020年1月25日益州小学学生健康调查问卷0038','2020-01-14 19:39:23','张三0038','大源中学','教科院二年级3班','刘阿斗','A01','刘玄德','13900000038','S000010','T000010','CDHS000019','1'),
	('HSR000039','2020年1月25日益州小学学生健康调查问卷0039','2020-01-23 21:26:16','张三0039','南山中学','教科院一年级5班','李天一','A01','张飞','13900000039','S000010','T000010','CDHS000020','1'),
	('HSR000040','2020年1月25日益州小学学生健康调查问卷0040','2020-01-16 14:49:16','张三0040','益州小学','教科院二年级3班','刘婵','A01','张翼德','13900000040','S000010','T000010','CDHS000020','1'),
	('HSR000041','2020年1月25日益州小学学生健康调查问卷0041','2020-01-28 22:02:47','张三0041','大源中学','教科院一年级5班','刘阿斗','A01','刘备','13900000041','S000011','T000011','CDHS000021','1'),
	('HSR000042','2020年1月25日益州小学学生健康调查问卷0042','2020-01-12 19:25:17','张三0042','南山中学','教科院二年级3班','李天一','A01','刘玄德','13900000042','S000011','T000011','CDHS000021','1'),
	('HSR000043','2020年1月25日益州小学学生健康调查问卷0043','2020-01-24 04:38:42','张三0043','益州小学','教科院一年级5班','刘婵','A01','张飞','13900000043','S000011','T000011','CDHS000022','1'),
	('HSR000044','2020年1月25日益州小学学生健康调查问卷0044','2020-01-18 08:09:30','张三0044','大源中学','教科院二年级3班','刘阿斗','A01','张翼德','13900000044','S000011','T000011','CDHS000022','1'),
	('HSR000045','2020年1月25日益州小学学生健康调查问卷0045','2020-01-14 23:24:51','张三0045','南山中学','教科院一年级5班','李天一','A01','刘备','13900000045','S000012','T000012','CDHS000023','1'),
	('HSR000046','2020年1月25日益州小学学生健康调查问卷0046','2020-01-29 00:58:18','张三0046','益州小学','教科院二年级3班','刘婵','A01','刘玄德','13900000046','S000012','T000012','CDHS000023','1'),
	('HSR000047','2020年1月25日益州小学学生健康调查问卷0047','2020-01-29 05:46:32','张三0047','大源中学','教科院一年级5班','刘阿斗','A01','张飞','13900000047','S000012','T000012','CDHS000024','1'),
	('HSR000048','2020年1月25日益州小学学生健康调查问卷0048','2020-01-23 18:56:09','张三0048','南山中学','教科院二年级3班','李天一','A01','张翼德','13900000048','S000012','T000012','CDHS000024','1'),
	('HSR000049','2020年1月25日益州小学学生健康调查问卷0049','2020-02-01 14:06:42','张三0049','益州小学','教科院一年级5班','刘婵','A01','刘备','13900000049','S000013','T000013','CDHS000025','1'),
	('HSR000050','2020年1月25日益州小学学生健康调查问卷0050','2020-01-29 15:16:04','张三0050','大源中学','教科院二年级3班','刘阿斗','A01','刘玄德','13900000050','S000013','T000013','CDHS000025','1'),
	('HSR000051','2020年1月25日益州小学学生健康调查问卷0051','2020-01-21 16:33:07','张三0051','南山中学','教科院一年级5班','李天一','A01','张飞','13900000051','S000013','T000013','CDHS000026','1'),
	('HSR000052','2020年1月25日益州小学学生健康调查问卷0052','2020-02-02 00:29:03','张三0052','益州小学','教科院二年级3班','刘婵','A01','张翼德','13900000052','S000013','T000013','CDHS000026','1'),
	('HSR000053','2020年1月25日益州小学学生健康调查问卷0053','2020-01-17 21:26:34','张三0053','大源中学','教科院一年级5班','刘阿斗','A01','刘备','13900000053','S000014','T000014','CDHS000027','1'),
	('HSR000054','2020年1月25日益州小学学生健康调查问卷0054','2020-01-26 09:41:37','张三0054','南山中学','教科院二年级3班','李天一','A01','刘玄德','13900000054','S000014','T000014','CDHS000027','1'),
	('HSR000055','2020年1月25日益州小学学生健康调查问卷0055','2020-01-31 23:49:05','张三0055','益州小学','教科院一年级5班','刘婵','A01','张飞','13900000055','S000014','T000014','CDHS000028','1'),
	('HSR000056','2020年1月25日益州小学学生健康调查问卷0056','2020-01-16 22:07:59','张三0056','大源中学','教科院二年级3班','刘阿斗','A01','张翼德','13900000056','S000014','T000014','CDHS000028','1'),
	('HSR000057','2020年1月25日益州小学学生健康调查问卷0057','2020-01-23 16:35:31','张三0057','南山中学','教科院一年级5班','李天一','A01','刘备','13900000057','S000015','T000015','CDHS000029','1'),
	('HSR000058','2020年1月25日益州小学学生健康调查问卷0058','2020-01-15 13:08:35','张三0058','益州小学','教科院二年级3班','刘婵','A01','刘玄德','13900000058','S000015','T000015','CDHS000029','1'),
	('HSR000059','2020年1月25日益州小学学生健康调查问卷0059','2020-01-25 07:24:01','张三0059','大源中学','教科院一年级5班','刘阿斗','A01','张飞','13900000059','S000015','T000015','CDHS000030','1'),
	('HSR000060','2020年1月25日益州小学学生健康调查问卷0060','2020-01-19 18:09:07','张三0060','南山中学','教科院二年级3班','李天一','A01','张翼德','13900000060','S000015','T000015','CDHS000030','1'),
	('HSR000061','2020年1月25日益州小学学生健康调查问卷0061','2020-01-19 17:51:00','张三0061','益州小学','教科院一年级5班','刘婵','A01','刘备','13900000061','S000016','T000016','CDHS000031','1'),
	('HSR000062','2020年1月25日益州小学学生健康调查问卷0062','2020-01-25 03:24:01','张三0062','大源中学','教科院二年级3班','刘阿斗','A01','刘玄德','13900000062','S000016','T000016','CDHS000031','1'),
	('HSR000063','2020年1月25日益州小学学生健康调查问卷0063','2020-02-02 19:12:03','张三0063','南山中学','教科院一年级5班','李天一','A01','张飞','13900000063','S000016','T000016','CDHS000032','1'),
	('HSR000064','2020年1月25日益州小学学生健康调查问卷0064','2020-01-14 22:33:19','张三0064','益州小学','教科院二年级3班','刘婵','A01','张翼德','13900000064','S000016','T000016','CDHS000032','1'),
	('HSR000065','2020年1月25日益州小学学生健康调查问卷0065','2020-01-30 21:22:57','张三0065','大源中学','教科院一年级5班','刘阿斗','A01','刘备','13900000065','S000017','T000017','CDHS000033','1'),
	('HSR000066','2020年1月25日益州小学学生健康调查问卷0066','2020-01-19 08:38:26','张三0066','南山中学','教科院二年级3班','李天一','A01','刘玄德','13900000066','S000017','T000017','CDHS000033','1'),
	('HSR000067','2020年1月25日益州小学学生健康调查问卷0067','2020-01-17 04:50:46','张三0067','益州小学','教科院一年级5班','刘婵','A01','张飞','13900000067','S000017','T000017','CDHS000034','1'),
	('HSR000068','2020年1月25日益州小学学生健康调查问卷0068','2020-01-16 23:39:34','张三0068','大源中学','教科院二年级3班','刘阿斗','A01','张翼德','13900000068','S000017','T000017','CDHS000034','1'),
	('HSR000069','2020年1月25日益州小学学生健康调查问卷0069','2020-01-24 03:34:45','张三0069','南山中学','教科院一年级5班','李天一','A01','刘备','13900000069','S000018','T000018','CDHS000035','1'),
	('HSR000070','2020年1月25日益州小学学生健康调查问卷0070','2020-01-17 12:13:38','张三0070','益州小学','教科院二年级3班','刘婵','A01','刘玄德','13900000070','S000018','T000018','CDHS000035','1'),
	('HSR000071','2020年1月25日益州小学学生健康调查问卷0071','2020-01-16 23:55:09','张三0071','大源中学','教科院一年级5班','刘阿斗','A01','张飞','13900000071','S000018','T000018','CDHS000036','1'),
	('HSR000072','2020年1月25日益州小学学生健康调查问卷0072','2020-01-17 14:11:26','张三0072','南山中学','教科院二年级3班','李天一','A01','张翼德','13900000072','S000018','T000018','CDHS000036','1'),
	('HSR000073','2020年1月25日益州小学学生健康调查问卷0073','2020-01-29 03:59:06','张三0073','益州小学','教科院一年级5班','刘婵','A01','刘备','13900000073','S000019','T000019','CDHS000037','1'),
	('HSR000074','2020年1月25日益州小学学生健康调查问卷0074','2020-01-19 16:39:49','张三0074','大源中学','教科院二年级3班','刘阿斗','A01','刘玄德','13900000074','S000019','T000019','CDHS000037','1'),
	('HSR000075','2020年1月25日益州小学学生健康调查问卷0075','2020-01-27 01:50:01','张三0075','南山中学','教科院一年级5班','李天一','A01','张飞','13900000075','S000019','T000019','CDHS000038','1'),
	('HSR000076','2020年1月25日益州小学学生健康调查问卷0076','2020-01-13 21:58:43','张三0076','益州小学','教科院二年级3班','刘婵','A01','张翼德','13900000076','S000019','T000019','CDHS000038','1'),
	('HSR000077','2020年1月25日益州小学学生健康调查问卷0077','2020-02-01 18:18:30','张三0077','大源中学','教科院一年级5班','刘阿斗','A01','刘备','13900000077','S000020','T000020','CDHS000039','1'),
	('HSR000078','2020年1月25日益州小学学生健康调查问卷0078','2020-01-13 12:56:18','张三0078','南山中学','教科院二年级3班','李天一','A01','刘玄德','13900000078','S000020','T000020','CDHS000039','1'),
	('HSR000079','2020年1月25日益州小学学生健康调查问卷0079','2020-01-16 09:45:45','张三0079','益州小学','教科院一年级5班','刘婵','A01','张飞','13900000079','S000020','T000020','CDHS000040','1'),
	('HSR000080','2020年1月25日益州小学学生健康调查问卷0080','2020-01-16 12:25:55','张三0080','大源中学','教科院二年级3班','刘阿斗','A01','张翼德','13900000080','S000020','T000020','CDHS000040','1'),
	('HSR000081','2020年1月25日益州小学学生健康调查问卷0081','2020-01-16 11:48:59','张三0081','南山中学','教科院一年级5班','李天一','A01','刘备','13900000081','S000021','T000021','CDHS000041','1'),
	('HSR000082','2020年1月25日益州小学学生健康调查问卷0082','2020-01-15 00:26:44','张三0082','益州小学','教科院二年级3班','刘婵','A01','刘玄德','13900000082','S000021','T000021','CDHS000041','1'),
	('HSR000083','2020年1月25日益州小学学生健康调查问卷0083','2020-01-27 17:54:18','张三0083','大源中学','教科院一年级5班','刘阿斗','A01','张飞','13900000083','S000021','T000021','CDHS000042','1'),
	('HSR000084','2020年1月25日益州小学学生健康调查问卷0084','2020-01-18 02:40:10','张三0084','南山中学','教科院二年级3班','李天一','A01','张翼德','13900000084','S000021','T000021','CDHS000042','1'),
	('HSR000085','2020年1月25日益州小学学生健康调查问卷0085','2020-01-18 06:58:01','张三0085','益州小学','教科院一年级5班','刘婵','A01','刘备','13900000085','S000022','T000022','CDHS000043','1'),
	('HSR000086','2020年1月25日益州小学学生健康调查问卷0086','2020-01-19 09:17:19','张三0086','大源中学','教科院二年级3班','刘阿斗','A01','刘玄德','13900000086','S000022','T000022','CDHS000043','1'),
	('HSR000087','2020年1月25日益州小学学生健康调查问卷0087','2020-01-14 19:23:47','张三0087','南山中学','教科院一年级5班','李天一','A01','张飞','13900000087','S000022','T000022','CDHS000044','1'),
	('HSR000088','2020年1月25日益州小学学生健康调查问卷0088','2020-02-03 06:24:10','张三0088','益州小学','教科院二年级3班','刘婵','A01','张翼德','13900000088','S000022','T000022','CDHS000044','1'),
	('HSR000089','2020年1月25日益州小学学生健康调查问卷0089','2020-01-25 11:47:47','张三0089','大源中学','教科院一年级5班','刘阿斗','A01','刘备','13900000089','S000023','T000023','CDHS000045','1'),
	('HSR000090','2020年1月25日益州小学学生健康调查问卷0090','2020-01-14 23:10:08','张三0090','南山中学','教科院二年级3班','李天一','A01','刘玄德','13900000090','S000023','T000023','CDHS000045','1'),
	('HSR000091','2020年1月25日益州小学学生健康调查问卷0091','2020-01-31 23:40:21','张三0091','益州小学','教科院一年级5班','刘婵','A01','张飞','13900000091','S000023','T000023','CDHS000046','1'),
	('HSR000092','2020年1月25日益州小学学生健康调查问卷0092','2020-02-02 12:47:30','张三0092','大源中学','教科院二年级3班','刘阿斗','A01','张翼德','13900000092','S000023','T000023','CDHS000046','1'),
	('HSR000093','2020年1月25日益州小学学生健康调查问卷0093','2020-01-21 22:10:24','张三0093','南山中学','教科院一年级5班','李天一','A01','刘备','13900000093','S000024','T000024','CDHS000047','1'),
	('HSR000094','2020年1月25日益州小学学生健康调查问卷0094','2020-02-01 19:56:24','张三0094','益州小学','教科院二年级3班','刘婵','A01','刘玄德','13900000094','S000024','T000024','CDHS000047','1'),
	('HSR000095','2020年1月25日益州小学学生健康调查问卷0095','2020-01-29 23:34:30','张三0095','大源中学','教科院一年级5班','刘阿斗','A01','张飞','13900000095','S000024','T000024','CDHS000048','1'),
	('HSR000096','2020年1月25日益州小学学生健康调查问卷0096','2020-01-17 20:21:27','张三0096','南山中学','教科院二年级3班','李天一','A01','张翼德','13900000096','S000024','T000024','CDHS000048','1'),
	('HSR000097','2020年1月25日益州小学学生健康调查问卷0097','2020-01-17 18:44:33','张三0097','益州小学','教科院一年级5班','刘婵','A01','刘备','13900000097','S000025','T000025','CDHS000049','1'),
	('HSR000098','2020年1月25日益州小学学生健康调查问卷0098','2020-01-29 03:56:45','张三0098','大源中学','教科院二年级3班','刘阿斗','A01','刘玄德','13900000098','S000025','T000025','CDHS000049','1'),
	('HSR000099','2020年1月25日益州小学学生健康调查问卷0099','2020-01-27 17:02:46','张三0099','南山中学','教科院一年级5班','李天一','A01','张飞','13900000099','S000025','T000025','CDHS000050','1'),
	('HSR000100','2020年1月25日益州小学学生健康调查问卷0100','2020-01-30 05:01:35','张三0100','益州小学','教科院二年级3班','刘婵','A01','张翼德','13900000100','S000025','T000025','CDHS000050','1'),
	('HSR000101','2020年1月25日益州小学学生健康调查问卷0101','2020-01-30 00:13:37','张三0101','大源中学','教科院一年级5班','刘阿斗','A01','刘备','13900000101','S000026','T000026','CDHS000051','1'),
	('HSR000102','2020年1月25日益州小学学生健康调查问卷0102','2020-02-03 10:35:55','张三0102','南山中学','教科院二年级3班','李天一','A01','刘玄德','13900000102','S000026','T000026','CDHS000051','1'),
	('HSR000103','2020年1月25日益州小学学生健康调查问卷0103','2020-01-22 03:58:43','张三0103','益州小学','教科院一年级5班','刘婵','A01','张飞','13900000103','S000026','T000026','CDHS000052','1'),
	('HSR000104','2020年1月25日益州小学学生健康调查问卷0104','2020-02-01 02:40:41','张三0104','大源中学','教科院二年级3班','刘阿斗','A01','张翼德','13900000104','S000026','T000026','CDHS000052','1'),
	('HSR000105','2020年1月25日益州小学学生健康调查问卷0105','2020-01-30 07:55:17','张三0105','南山中学','教科院一年级5班','李天一','A01','刘备','13900000105','S000027','T000027','CDHS000053','1'),
	('HSR000106','2020年1月25日益州小学学生健康调查问卷0106','2020-01-23 21:43:45','张三0106','益州小学','教科院二年级3班','刘婵','A01','刘玄德','13900000106','S000027','T000027','CDHS000053','1'),
	('HSR000107','2020年1月25日益州小学学生健康调查问卷0107','2020-01-29 01:36:17','张三0107','大源中学','教科院一年级5班','刘阿斗','A01','张飞','13900000107','S000027','T000027','CDHS000054','1'),
	('HSR000108','2020年1月25日益州小学学生健康调查问卷0108','2020-01-14 02:25:24','张三0108','南山中学','教科院二年级3班','李天一','A01','张翼德','13900000108','S000027','T000027','CDHS000054','1'),
	('HSR000109','2020年1月25日益州小学学生健康调查问卷0109','2020-01-12 22:30:06','张三0109','益州小学','教科院一年级5班','刘婵','A01','刘备','13900000109','S000028','T000028','CDHS000055','1'),
	('HSR000110','2020年1月25日益州小学学生健康调查问卷0110','2020-01-24 03:14:27','张三0110','大源中学','教科院二年级3班','刘阿斗','A01','刘玄德','13900000110','S000028','T000028','CDHS000055','1'),
	('HSR000111','2020年1月25日益州小学学生健康调查问卷0111','2020-01-22 16:34:06','张三0111','南山中学','教科院一年级5班','李天一','A01','张飞','13900000111','S000028','T000028','CDHS000056','1'),
	('HSR000112','2020年1月25日益州小学学生健康调查问卷0112','2020-01-21 01:58:37','张三0112','益州小学','教科院二年级3班','刘婵','A01','张翼德','13900000112','S000028','T000028','CDHS000056','1'),
	('HSR000113','2020年1月25日益州小学学生健康调查问卷0113','2020-01-28 19:17:35','张三0113','大源中学','教科院一年级5班','刘阿斗','A01','刘备','13900000113','S000029','T000029','CDHS000057','1'),
	('HSR000114','2020年1月25日益州小学学生健康调查问卷0114','2020-01-19 12:31:02','张三0114','南山中学','教科院二年级3班','李天一','A01','刘玄德','13900000114','S000029','T000029','CDHS000057','1'),
	('HSR000115','2020年1月25日益州小学学生健康调查问卷0115','2020-01-22 20:06:13','张三0115','益州小学','教科院一年级5班','刘婵','A01','张飞','13900000115','S000029','T000029','CDHS000058','1'),
	('HSR000116','2020年1月25日益州小学学生健康调查问卷0116','2020-01-26 11:47:32','张三0116','大源中学','教科院二年级3班','刘阿斗','A01','张翼德','13900000116','S000029','T000029','CDHS000058','1'),
	('HSR000117','2020年1月25日益州小学学生健康调查问卷0117','2020-02-01 22:16:39','张三0117','南山中学','教科院一年级5班','李天一','A01','刘备','13900000117','S000030','T000030','CDHS000059','1'),
	('HSR000118','2020年1月25日益州小学学生健康调查问卷0118','2020-01-26 21:05:14','张三0118','益州小学','教科院二年级3班','刘婵','A01','刘玄德','13900000118','S000030','T000030','CDHS000059','1'),
	('HSR000119','2020年1月25日益州小学学生健康调查问卷0119','2020-01-14 01:44:34','张三0119','大源中学','教科院一年级5班','刘阿斗','A01','张飞','13900000119','S000030','T000030','CDHS000060','1'),
	('HSR000120','2020年1月25日益州小学学生健康调查问卷0120','2020-01-31 11:37:40','张三0120','南山中学','教科院二年级3班','李天一','A01','张翼德','13900000120','S000030','T000030','CDHS000060','1'),
	('HSR000121','2020年1月25日益州小学学生健康调查问卷0121','2020-01-23 08:23:27','张三0121','益州小学','教科院一年级5班','刘婵','A01','刘备','13900000121','S000031','T000031','CDHS000061','1'),
	('HSR000122','2020年1月25日益州小学学生健康调查问卷0122','2020-01-25 01:25:01','张三0122','大源中学','教科院二年级3班','刘阿斗','A01','刘玄德','13900000122','S000031','T000031','CDHS000061','1'),
	('HSR000123','2020年1月25日益州小学学生健康调查问卷0123','2020-02-01 23:30:28','张三0123','南山中学','教科院一年级5班','李天一','A01','张飞','13900000123','S000031','T000031','CDHS000062','1'),
	('HSR000124','2020年1月25日益州小学学生健康调查问卷0124','2020-01-17 05:08:56','张三0124','益州小学','教科院二年级3班','刘婵','A01','张翼德','13900000124','S000031','T000031','CDHS000062','1'),
	('HSR000125','2020年1月25日益州小学学生健康调查问卷0125','2020-01-26 06:03:05','张三0125','大源中学','教科院一年级5班','刘阿斗','A01','刘备','13900000125','S000032','T000032','CDHS000063','1'),
	('HSR000126','2020年1月25日益州小学学生健康调查问卷0126','2020-01-18 16:59:56','张三0126','南山中学','教科院二年级3班','李天一','A01','刘玄德','13900000126','S000032','T000032','CDHS000063','1'),
	('HSR000127','2020年1月25日益州小学学生健康调查问卷0127','2020-01-17 02:11:44','张三0127','益州小学','教科院一年级5班','刘婵','A01','张飞','13900000127','S000032','T000032','CDHS000064','1'),
	('HSR000128','2020年1月25日益州小学学生健康调查问卷0128','2020-01-24 05:50:04','张三0128','大源中学','教科院二年级3班','刘阿斗','A01','张翼德','13900000128','S000032','T000032','CDHS000064','1'),
	('HSR000129','2020年1月25日益州小学学生健康调查问卷0129','2020-01-22 06:10:27','张三0129','南山中学','教科院一年级5班','李天一','A01','刘备','13900000129','S000033','T000033','CDHS000065','1'),
	('HSR000130','2020年1月25日益州小学学生健康调查问卷0130','2020-01-15 23:20:09','张三0130','益州小学','教科院二年级3班','刘婵','A01','刘玄德','13900000130','S000033','T000033','CDHS000065','1'),
	('HSR000131','2020年1月25日益州小学学生健康调查问卷0131','2020-01-27 22:32:30','张三0131','大源中学','教科院一年级5班','刘阿斗','A01','张飞','13900000131','S000033','T000033','CDHS000066','1'),
	('HSR000132','2020年1月25日益州小学学生健康调查问卷0132','2020-01-23 07:14:13','张三0132','南山中学','教科院二年级3班','李天一','A01','张翼德','13900000132','S000033','T000033','CDHS000066','1'),
	('HSR000133','2020年1月25日益州小学学生健康调查问卷0133','2020-01-31 14:34:15','张三0133','益州小学','教科院一年级5班','刘婵','A01','刘备','13900000133','S000034','T000034','CDHS000067','1'),
	('HSR000134','2020年1月25日益州小学学生健康调查问卷0134','2020-01-14 09:09:14','张三0134','大源中学','教科院二年级3班','刘阿斗','A01','刘玄德','13900000134','S000034','T000034','CDHS000067','1'),
	('HSR000135','2020年1月25日益州小学学生健康调查问卷0135','2020-01-13 15:57:20','张三0135','南山中学','教科院一年级5班','李天一','A01','张飞','13900000135','S000034','T000034','CDHS000068','1'),
	('HSR000136','2020年1月25日益州小学学生健康调查问卷0136','2020-01-25 20:09:29','张三0136','益州小学','教科院二年级3班','刘婵','A01','张翼德','13900000136','S000034','T000034','CDHS000068','1'),
	('HSR000137','2020年1月25日益州小学学生健康调查问卷0137','2020-01-17 14:24:02','张三0137','大源中学','教科院一年级5班','刘阿斗','A01','刘备','13900000137','S000035','T000035','CDHS000069','1'),
	('HSR000138','2020年1月25日益州小学学生健康调查问卷0138','2020-01-12 18:15:46','张三0138','南山中学','教科院二年级3班','李天一','A01','刘玄德','13900000138','S000035','T000035','CDHS000069','1'),
	('HSR000139','2020年1月25日益州小学学生健康调查问卷0139','2020-01-14 00:56:45','张三0139','益州小学','教科院一年级5班','刘婵','A01','张飞','13900000139','S000035','T000035','CDHS000070','1'),
	('HSR000140','2020年1月25日益州小学学生健康调查问卷0140','2020-01-17 00:08:18','张三0140','大源中学','教科院二年级3班','刘阿斗','A01','张翼德','13900000140','S000035','T000035','CDHS000070','1'),
	('HSR000141','2020年1月25日益州小学学生健康调查问卷0141','2020-01-21 05:49:35','张三0141','南山中学','教科院一年级5班','李天一','A01','刘备','13900000141','S000036','T000036','CDHS000071','1'),
	('HSR000142','2020年1月25日益州小学学生健康调查问卷0142','2020-01-15 23:16:27','张三0142','益州小学','教科院二年级3班','刘婵','A01','刘玄德','13900000142','S000036','T000036','CDHS000071','1'),
	('HSR000143','2020年1月25日益州小学学生健康调查问卷0143','2020-01-18 17:06:29','张三0143','大源中学','教科院一年级5班','刘阿斗','A01','张飞','13900000143','S000036','T000036','CDHS000072','1'),
	('HSR000144','2020年1月25日益州小学学生健康调查问卷0144','2020-01-19 01:36:49','张三0144','南山中学','教科院二年级3班','李天一','A01','张翼德','13900000144','S000036','T000036','CDHS000072','1'),
	('HSR000145','2020年1月25日益州小学学生健康调查问卷0145','2020-01-27 05:39:04','张三0145','益州小学','教科院一年级5班','刘婵','A01','刘备','13900000145','S000037','T000037','CDHS000073','1'),
	('HSR000146','2020年1月25日益州小学学生健康调查问卷0146','2020-01-17 08:06:25','张三0146','大源中学','教科院二年级3班','刘阿斗','A01','刘玄德','13900000146','S000037','T000037','CDHS000073','1'),
	('HSR000147','2020年1月25日益州小学学生健康调查问卷0147','2020-01-28 14:37:57','张三0147','南山中学','教科院一年级5班','李天一','A01','张飞','13900000147','S000037','T000037','CDHS000074','1'),
	('HSR000148','2020年1月25日益州小学学生健康调查问卷0148','2020-01-19 00:59:11','张三0148','益州小学','教科院二年级3班','刘婵','A01','张翼德','13900000148','S000037','T000037','CDHS000074','1'),
	('HSR000149','2020年1月25日益州小学学生健康调查问卷0149','2020-01-29 21:38:47','张三0149','大源中学','教科院一年级5班','刘阿斗','A01','刘备','13900000149','S000038','T000038','CDHS000075','1'),
	('HSR000150','2020年1月25日益州小学学生健康调查问卷0150','2020-01-30 20:52:05','张三0150','南山中学','教科院二年级3班','李天一','A01','刘玄德','13900000150','S000038','T000038','CDHS000075','1'),
	('HSR000151','2020年1月25日益州小学学生健康调查问卷0151','2020-01-28 08:52:55','张三0151','益州小学','教科院一年级5班','刘婵','A01','张飞','13900000151','S000038','T000038','CDHS000076','1'),
	('HSR000152','2020年1月25日益州小学学生健康调查问卷0152','2020-01-29 09:39:21','张三0152','大源中学','教科院二年级3班','刘阿斗','A01','张翼德','13900000152','S000038','T000038','CDHS000076','1'),
	('HSR000153','2020年1月25日益州小学学生健康调查问卷0153','2020-01-25 22:09:17','张三0153','南山中学','教科院一年级5班','李天一','A01','刘备','13900000153','S000039','T000039','CDHS000077','1'),
	('HSR000154','2020年1月25日益州小学学生健康调查问卷0154','2020-01-29 07:03:26','张三0154','益州小学','教科院二年级3班','刘婵','A01','刘玄德','13900000154','S000039','T000039','CDHS000077','1'),
	('HSR000155','2020年1月25日益州小学学生健康调查问卷0155','2020-01-14 19:31:46','张三0155','大源中学','教科院一年级5班','刘阿斗','A01','张飞','13900000155','S000039','T000039','CDHS000078','1'),
	('HSR000156','2020年1月25日益州小学学生健康调查问卷0156','2020-01-27 03:24:08','张三0156','南山中学','教科院二年级3班','李天一','A01','张翼德','13900000156','S000039','T000039','CDHS000078','1'),
	('HSR000157','2020年1月25日益州小学学生健康调查问卷0157','2020-02-02 19:44:10','张三0157','益州小学','教科院一年级5班','刘婵','A01','刘备','13900000157','S000040','T000040','CDHS000079','1'),
	('HSR000158','2020年1月25日益州小学学生健康调查问卷0158','2020-01-22 18:45:01','张三0158','大源中学','教科院二年级3班','刘阿斗','A01','刘玄德','13900000158','S000040','T000040','CDHS000079','1'),
	('HSR000159','2020年1月25日益州小学学生健康调查问卷0159','2020-01-15 09:11:46','张三0159','南山中学','教科院一年级5班','李天一','A01','张飞','13900000159','S000040','T000040','CDHS000080','1'),
	('HSR000160','2020年1月25日益州小学学生健康调查问卷0160','2020-01-14 14:22:00','张三0160','益州小学','教科院二年级3班','刘婵','A01','张翼德','13900000160','S000040','T000040','CDHS000080','1'),
	('HSR000161','2020年1月25日益州小学学生健康调查问卷0161','2020-01-19 21:21:00','张三0161','大源中学','教科院一年级5班','刘阿斗','A01','刘备','13900000161','S000041','T000041','CDHS000081','1'),
	('HSR000162','2020年1月25日益州小学学生健康调查问卷0162','2020-01-19 07:13:59','张三0162','南山中学','教科院二年级3班','李天一','A01','刘玄德','13900000162','S000041','T000041','CDHS000081','1'),
	('HSR000163','2020年1月25日益州小学学生健康调查问卷0163','2020-01-25 11:54:06','张三0163','益州小学','教科院一年级5班','刘婵','A01','张飞','13900000163','S000041','T000041','CDHS000082','1'),
	('HSR000164','2020年1月25日益州小学学生健康调查问卷0164','2020-01-16 07:51:48','张三0164','大源中学','教科院二年级3班','刘阿斗','A01','张翼德','13900000164','S000041','T000041','CDHS000082','1'),
	('HSR000165','2020年1月25日益州小学学生健康调查问卷0165','2020-01-23 00:23:50','张三0165','南山中学','教科院一年级5班','李天一','A01','刘备','13900000165','S000042','T000042','CDHS000083','1'),
	('HSR000166','2020年1月25日益州小学学生健康调查问卷0166','2020-01-15 02:06:41','张三0166','益州小学','教科院二年级3班','刘婵','A01','刘玄德','13900000166','S000042','T000042','CDHS000083','1'),
	('HSR000167','2020年1月25日益州小学学生健康调查问卷0167','2020-01-16 14:33:24','张三0167','大源中学','教科院一年级5班','刘阿斗','A01','张飞','13900000167','S000042','T000042','CDHS000084','1'),
	('HSR000168','2020年1月25日益州小学学生健康调查问卷0168','2020-01-24 15:57:45','张三0168','南山中学','教科院二年级3班','李天一','A01','张翼德','13900000168','S000042','T000042','CDHS000084','1'),
	('HSR000169','2020年1月25日益州小学学生健康调查问卷0169','2020-01-20 22:43:28','张三0169','益州小学','教科院一年级5班','刘婵','A01','刘备','13900000169','S000043','T000043','CDHS000085','1'),
	('HSR000170','2020年1月25日益州小学学生健康调查问卷0170','2020-01-27 01:35:12','张三0170','大源中学','教科院二年级3班','刘阿斗','A01','刘玄德','13900000170','S000043','T000043','CDHS000085','1'),
	('HSR000171','2020年1月25日益州小学学生健康调查问卷0171','2020-01-26 05:56:38','张三0171','南山中学','教科院一年级5班','李天一','A01','张飞','13900000171','S000043','T000043','CDHS000086','1'),
	('HSR000172','2020年1月25日益州小学学生健康调查问卷0172','2020-01-31 20:01:36','张三0172','益州小学','教科院二年级3班','刘婵','A01','张翼德','13900000172','S000043','T000043','CDHS000086','1'),
	('HSR000173','2020年1月25日益州小学学生健康调查问卷0173','2020-01-14 17:22:20','张三0173','大源中学','教科院一年级5班','刘阿斗','A01','刘备','13900000173','S000044','T000044','CDHS000087','1'),
	('HSR000174','2020年1月25日益州小学学生健康调查问卷0174','2020-01-24 00:47:45','张三0174','南山中学','教科院二年级3班','李天一','A01','刘玄德','13900000174','S000044','T000044','CDHS000087','1'),
	('HSR000175','2020年1月25日益州小学学生健康调查问卷0175','2020-02-03 11:35:35','张三0175','益州小学','教科院一年级5班','刘婵','A01','张飞','13900000175','S000044','T000044','CDHS000088','1'),
	('HSR000176','2020年1月25日益州小学学生健康调查问卷0176','2020-01-31 18:44:03','张三0176','大源中学','教科院二年级3班','刘阿斗','A01','张翼德','13900000176','S000044','T000044','CDHS000088','1'),
	('HSR000177','2020年1月25日益州小学学生健康调查问卷0177','2020-02-01 03:28:29','张三0177','南山中学','教科院一年级5班','李天一','A01','刘备','13900000177','S000045','T000045','CDHS000089','1'),
	('HSR000178','2020年1月25日益州小学学生健康调查问卷0178','2020-01-31 03:01:28','张三0178','益州小学','教科院二年级3班','刘婵','A01','刘玄德','13900000178','S000045','T000045','CDHS000089','1'),
	('HSR000179','2020年1月25日益州小学学生健康调查问卷0179','2020-01-18 18:26:07','张三0179','大源中学','教科院一年级5班','刘阿斗','A01','张飞','13900000179','S000045','T000045','CDHS000090','1'),
	('HSR000180','2020年1月25日益州小学学生健康调查问卷0180','2020-01-28 20:13:00','张三0180','南山中学','教科院二年级3班','李天一','A01','张翼德','13900000180','S000045','T000045','CDHS000090','1'),
	('HSR000181','2020年1月25日益州小学学生健康调查问卷0181','2020-01-24 04:51:15','张三0181','益州小学','教科院一年级5班','刘婵','A01','刘备','13900000181','S000046','T000046','CDHS000091','1'),
	('HSR000182','2020年1月25日益州小学学生健康调查问卷0182','2020-01-30 04:15:34','张三0182','大源中学','教科院二年级3班','刘阿斗','A01','刘玄德','13900000182','S000046','T000046','CDHS000091','1'),
	('HSR000183','2020年1月25日益州小学学生健康调查问卷0183','2020-01-27 08:55:28','张三0183','南山中学','教科院一年级5班','李天一','A01','张飞','13900000183','S000046','T000046','CDHS000092','1'),
	('HSR000184','2020年1月25日益州小学学生健康调查问卷0184','2020-01-17 18:20:21','张三0184','益州小学','教科院二年级3班','刘婵','A01','张翼德','13900000184','S000046','T000046','CDHS000092','1'),
	('HSR000185','2020年1月25日益州小学学生健康调查问卷0185','2020-01-31 00:19:26','张三0185','大源中学','教科院一年级5班','刘阿斗','A01','刘备','13900000185','S000047','T000047','CDHS000093','1'),
	('HSR000186','2020年1月25日益州小学学生健康调查问卷0186','2020-01-31 15:42:56','张三0186','南山中学','教科院二年级3班','李天一','A01','刘玄德','13900000186','S000047','T000047','CDHS000093','1'),
	('HSR000187','2020年1月25日益州小学学生健康调查问卷0187','2020-01-28 11:14:27','张三0187','益州小学','教科院一年级5班','刘婵','A01','张飞','13900000187','S000047','T000047','CDHS000094','1'),
	('HSR000188','2020年1月25日益州小学学生健康调查问卷0188','2020-01-16 12:17:51','张三0188','大源中学','教科院二年级3班','刘阿斗','A01','张翼德','13900000188','S000047','T000047','CDHS000094','1'),
	('HSR000189','2020年1月25日益州小学学生健康调查问卷0189','2020-01-14 12:05:57','张三0189','南山中学','教科院一年级5班','李天一','A01','刘备','13900000189','S000048','T000048','CDHS000095','1'),
	('HSR000190','2020年1月25日益州小学学生健康调查问卷0190','2020-01-26 21:16:04','张三0190','益州小学','教科院二年级3班','刘婵','A01','刘玄德','13900000190','S000048','T000048','CDHS000095','1'),
	('HSR000191','2020年1月25日益州小学学生健康调查问卷0191','2020-01-28 03:50:55','张三0191','大源中学','教科院一年级5班','刘阿斗','A01','张飞','13900000191','S000048','T000048','CDHS000096','1'),
	('HSR000192','2020年1月25日益州小学学生健康调查问卷0192','2020-01-14 07:09:21','张三0192','南山中学','教科院二年级3班','李天一','A01','张翼德','13900000192','S000048','T000048','CDHS000096','1'),
	('HSR000193','2020年1月25日益州小学学生健康调查问卷0193','2020-01-29 12:38:26','张三0193','益州小学','教科院一年级5班','刘婵','A01','刘备','13900000193','S000049','T000049','CDHS000097','1'),
	('HSR000194','2020年1月25日益州小学学生健康调查问卷0194','2020-01-26 09:56:53','张三0194','大源中学','教科院二年级3班','刘阿斗','A01','刘玄德','13900000194','S000049','T000049','CDHS000097','1'),
	('HSR000195','2020年1月25日益州小学学生健康调查问卷0195','2020-01-31 08:15:29','张三0195','南山中学','教科院一年级5班','李天一','A01','张飞','13900000195','S000049','T000049','CDHS000098','1'),
	('HSR000196','2020年1月25日益州小学学生健康调查问卷0196','2020-01-18 02:44:32','张三0196','益州小学','教科院二年级3班','刘婵','A01','张翼德','13900000196','S000049','T000049','CDHS000098','1'),
	('HSR000197','2020年1月25日益州小学学生健康调查问卷0197','2020-01-27 13:17:08','张三0197','大源中学','教科院一年级5班','刘阿斗','A01','刘备','13900000197','S000050','T000050','CDHS000099','1'),
	('HSR000198','2020年1月25日益州小学学生健康调查问卷0198','2020-01-14 21:00:17','张三0198','南山中学','教科院二年级3班','李天一','A01','刘玄德','13900000198','S000050','T000050','CDHS000099','1'),
	('HSR000199','2020年1月25日益州小学学生健康调查问卷0199','2020-01-19 23:20:33','张三0199','益州小学','教科院一年级5班','刘婵','A01','张飞','13900000199','S000050','T000050','CDHS000100','1'),
	('HSR000200','2020年1月25日益州小学学生健康调查问卷0200','2020-01-15 13:49:23','张三0200','大源中学','教科院二年级3班','刘阿斗','A01','张翼德','13900000200','S000050','T000050','CDHS000100','1'),
	('HSR000201','2020年1月25日益州小学学生健康调查问卷0201','2020-02-01 14:11:57','张三0201','南山中学','教科院一年级5班','李天一','A01','刘备','13900000201','S000051','T000051','CDHS000101','1'),
	('HSR000202','2020年1月25日益州小学学生健康调查问卷0202','2020-01-25 08:54:19','张三0202','益州小学','教科院二年级3班','刘婵','A01','刘玄德','13900000202','S000051','T000051','CDHS000101','1'),
	('HSR000203','2020年1月25日益州小学学生健康调查问卷0203','2020-02-01 00:23:45','张三0203','大源中学','教科院一年级5班','刘阿斗','A01','张飞','13900000203','S000051','T000051','CDHS000102','1'),
	('HSR000204','2020年1月25日益州小学学生健康调查问卷0204','2020-01-21 12:07:03','张三0204','南山中学','教科院二年级3班','李天一','A01','张翼德','13900000204','S000051','T000051','CDHS000102','1'),
	('HSR000205','2020年1月25日益州小学学生健康调查问卷0205','2020-01-25 10:03:38','张三0205','益州小学','教科院一年级5班','刘婵','A01','刘备','13900000205','S000052','T000052','CDHS000103','1'),
	('HSR000206','2020年1月25日益州小学学生健康调查问卷0206','2020-01-14 10:03:13','张三0206','大源中学','教科院二年级3班','刘阿斗','A01','刘玄德','13900000206','S000052','T000052','CDHS000103','1'),
	('HSR000207','2020年1月25日益州小学学生健康调查问卷0207','2020-01-13 13:27:53','张三0207','南山中学','教科院一年级5班','李天一','A01','张飞','13900000207','S000052','T000052','CDHS000104','1'),
	('HSR000208','2020年1月25日益州小学学生健康调查问卷0208','2020-01-17 00:28:19','张三0208','益州小学','教科院二年级3班','刘婵','A01','张翼德','13900000208','S000052','T000052','CDHS000104','1'),
	('HSR000209','2020年1月25日益州小学学生健康调查问卷0209','2020-01-28 22:43:16','张三0209','大源中学','教科院一年级5班','刘阿斗','A01','刘备','13900000209','S000053','T000053','CDHS000105','1'),
	('HSR000210','2020年1月25日益州小学学生健康调查问卷0210','2020-01-14 01:31:09','张三0210','南山中学','教科院二年级3班','李天一','A01','刘玄德','13900000210','S000053','T000053','CDHS000105','1'),
	('HSR000211','2020年1月25日益州小学学生健康调查问卷0211','2020-01-28 12:22:22','张三0211','益州小学','教科院一年级5班','刘婵','A01','张飞','13900000211','S000053','T000053','CDHS000106','1'),
	('HSR000212','2020年1月25日益州小学学生健康调查问卷0212','2020-02-02 23:47:43','张三0212','大源中学','教科院二年级3班','刘阿斗','A01','张翼德','13900000212','S000053','T000053','CDHS000106','1'),
	('HSR000213','2020年1月25日益州小学学生健康调查问卷0213','2020-01-27 08:24:39','张三0213','南山中学','教科院一年级5班','李天一','A01','刘备','13900000213','S000054','T000054','CDHS000107','1'),
	('HSR000214','2020年1月25日益州小学学生健康调查问卷0214','2020-01-18 23:55:11','张三0214','益州小学','教科院二年级3班','刘婵','A01','刘玄德','13900000214','S000054','T000054','CDHS000107','1'),
	('HSR000215','2020年1月25日益州小学学生健康调查问卷0215','2020-01-31 20:15:07','张三0215','大源中学','教科院一年级5班','刘阿斗','A01','张飞','13900000215','S000054','T000054','CDHS000108','1'),
	('HSR000216','2020年1月25日益州小学学生健康调查问卷0216','2020-01-23 02:10:27','张三0216','南山中学','教科院二年级3班','李天一','A01','张翼德','13900000216','S000054','T000054','CDHS000108','1'),
	('HSR000217','2020年1月25日益州小学学生健康调查问卷0217','2020-01-22 10:08:12','张三0217','益州小学','教科院一年级5班','刘婵','A01','刘备','13900000217','S000055','T000055','CDHS000109','1'),
	('HSR000218','2020年1月25日益州小学学生健康调查问卷0218','2020-01-22 04:34:35','张三0218','大源中学','教科院二年级3班','刘阿斗','A01','刘玄德','13900000218','S000055','T000055','CDHS000109','1'),
	('HSR000219','2020年1月25日益州小学学生健康调查问卷0219','2020-01-20 22:56:02','张三0219','南山中学','教科院一年级5班','李天一','A01','张飞','13900000219','S000055','T000055','CDHS000110','1'),
	('HSR000220','2020年1月25日益州小学学生健康调查问卷0220','2020-02-01 14:06:47','张三0220','益州小学','教科院二年级3班','刘婵','A01','张翼德','13900000220','S000055','T000055','CDHS000110','1'),
	('HSR000221','2020年1月25日益州小学学生健康调查问卷0221','2020-01-16 17:18:12','张三0221','大源中学','教科院一年级5班','刘阿斗','A01','刘备','13900000221','S000056','T000056','CDHS000111','1'),
	('HSR000222','2020年1月25日益州小学学生健康调查问卷0222','2020-01-16 17:33:20','张三0222','南山中学','教科院二年级3班','李天一','A01','刘玄德','13900000222','S000056','T000056','CDHS000111','1'),
	('HSR000223','2020年1月25日益州小学学生健康调查问卷0223','2020-01-19 02:59:56','张三0223','益州小学','教科院一年级5班','刘婵','A01','张飞','13900000223','S000056','T000056','CDHS000112','1'),
	('HSR000224','2020年1月25日益州小学学生健康调查问卷0224','2020-01-25 23:42:54','张三0224','大源中学','教科院二年级3班','刘阿斗','A01','张翼德','13900000224','S000056','T000056','CDHS000112','1'),
	('HSR000225','2020年1月25日益州小学学生健康调查问卷0225','2020-01-29 09:30:48','张三0225','南山中学','教科院一年级5班','李天一','A01','刘备','13900000225','S000057','T000057','CDHS000113','1'),
	('HSR000226','2020年1月25日益州小学学生健康调查问卷0226','2020-01-21 08:37:57','张三0226','益州小学','教科院二年级3班','刘婵','A01','刘玄德','13900000226','S000057','T000057','CDHS000113','1'),
	('HSR000227','2020年1月25日益州小学学生健康调查问卷0227','2020-01-29 15:04:01','张三0227','大源中学','教科院一年级5班','刘阿斗','A01','张飞','13900000227','S000057','T000057','CDHS000114','1'),
	('HSR000228','2020年1月25日益州小学学生健康调查问卷0228','2020-01-22 05:59:30','张三0228','南山中学','教科院二年级3班','李天一','A01','张翼德','13900000228','S000057','T000057','CDHS000114','1'),
	('HSR000229','2020年1月25日益州小学学生健康调查问卷0229','2020-01-17 07:07:56','张三0229','益州小学','教科院一年级5班','刘婵','A01','刘备','13900000229','S000058','T000058','CDHS000115','1'),
	('HSR000230','2020年1月25日益州小学学生健康调查问卷0230','2020-01-14 08:21:39','张三0230','大源中学','教科院二年级3班','刘阿斗','A01','刘玄德','13900000230','S000058','T000058','CDHS000115','1'),
	('HSR000231','2020年1月25日益州小学学生健康调查问卷0231','2020-01-26 12:12:44','张三0231','南山中学','教科院一年级5班','李天一','A01','张飞','13900000231','S000058','T000058','CDHS000116','1'),
	('HSR000232','2020年1月25日益州小学学生健康调查问卷0232','2020-01-28 05:00:44','张三0232','益州小学','教科院二年级3班','刘婵','A01','张翼德','13900000232','S000058','T000058','CDHS000116','1'),
	('HSR000233','2020年1月25日益州小学学生健康调查问卷0233','2020-01-17 13:10:59','张三0233','大源中学','教科院一年级5班','刘阿斗','A01','刘备','13900000233','S000059','T000059','CDHS000117','1'),
	('HSR000234','2020年1月25日益州小学学生健康调查问卷0234','2020-01-16 12:06:33','张三0234','南山中学','教科院二年级3班','李天一','A01','刘玄德','13900000234','S000059','T000059','CDHS000117','1'),
	('HSR000235','2020年1月25日益州小学学生健康调查问卷0235','2020-01-27 19:25:02','张三0235','益州小学','教科院一年级5班','刘婵','A01','张飞','13900000235','S000059','T000059','CDHS000118','1'),
	('HSR000236','2020年1月25日益州小学学生健康调查问卷0236','2020-01-25 22:37:53','张三0236','大源中学','教科院二年级3班','刘阿斗','A01','张翼德','13900000236','S000059','T000059','CDHS000118','1'),
	('HSR000237','2020年1月25日益州小学学生健康调查问卷0237','2020-01-21 00:36:55','张三0237','南山中学','教科院一年级5班','李天一','A01','刘备','13900000237','S000060','T000060','CDHS000119','1'),
	('HSR000238','2020年1月25日益州小学学生健康调查问卷0238','2020-01-25 18:06:53','张三0238','益州小学','教科院二年级3班','刘婵','A01','刘玄德','13900000238','S000060','T000060','CDHS000119','1'),
	('HSR000239','2020年1月25日益州小学学生健康调查问卷0239','2020-01-23 02:19:53','张三0239','大源中学','教科院一年级5班','刘阿斗','A01','张飞','13900000239','S000060','T000060','CDHS000120','1'),
	('HSR000240','2020年1月25日益州小学学生健康调查问卷0240','2020-02-03 02:41:34','张三0240','南山中学','教科院二年级3班','李天一','A01','张翼德','13900000240','S000060','T000060','CDHS000120','1'),
	('HSR000241','2020年1月25日益州小学学生健康调查问卷0241','2020-01-29 07:13:01','张三0241','益州小学','教科院一年级5班','刘婵','A01','刘备','13900000241','S000061','T000061','CDHS000121','1'),
	('HSR000242','2020年1月25日益州小学学生健康调查问卷0242','2020-01-22 01:44:01','张三0242','大源中学','教科院二年级3班','刘阿斗','A01','刘玄德','13900000242','S000061','T000061','CDHS000121','1'),
	('HSR000243','2020年1月25日益州小学学生健康调查问卷0243','2020-01-28 13:29:06','张三0243','南山中学','教科院一年级5班','李天一','A01','张飞','13900000243','S000061','T000061','CDHS000122','1'),
	('HSR000244','2020年1月25日益州小学学生健康调查问卷0244','2020-02-01 23:42:00','张三0244','益州小学','教科院二年级3班','刘婵','A01','张翼德','13900000244','S000061','T000061','CDHS000122','1'),
	('HSR000245','2020年1月25日益州小学学生健康调查问卷0245','2020-01-24 09:46:46','张三0245','大源中学','教科院一年级5班','刘阿斗','A01','刘备','13900000245','S000062','T000062','CDHS000123','1'),
	('HSR000246','2020年1月25日益州小学学生健康调查问卷0246','2020-01-14 17:18:50','张三0246','南山中学','教科院二年级3班','李天一','A01','刘玄德','13900000246','S000062','T000062','CDHS000123','1'),
	('HSR000247','2020年1月25日益州小学学生健康调查问卷0247','2020-01-23 22:18:09','张三0247','益州小学','教科院一年级5班','刘婵','A01','张飞','13900000247','S000062','T000062','CDHS000124','1'),
	('HSR000248','2020年1月25日益州小学学生健康调查问卷0248','2020-01-28 17:08:41','张三0248','大源中学','教科院二年级3班','刘阿斗','A01','张翼德','13900000248','S000062','T000062','CDHS000124','1'),
	('HSR000249','2020年1月25日益州小学学生健康调查问卷0249','2020-01-16 01:59:00','张三0249','南山中学','教科院一年级5班','李天一','A01','刘备','13900000249','S000063','T000063','CDHS000125','1'),
	('HSR000250','2020年1月25日益州小学学生健康调查问卷0250','2020-01-13 17:41:14','张三0250','益州小学','教科院二年级3班','刘婵','A01','刘玄德','13900000250','S000063','T000063','CDHS000125','1'),
	('HSR000251','2020年1月25日益州小学学生健康调查问卷0251','2020-01-22 18:36:03','张三0251','大源中学','教科院一年级5班','刘阿斗','A01','张飞','13900000251','S000063','T000063','CDHS000126','1'),
	('HSR000252','2020年1月25日益州小学学生健康调查问卷0252','2020-01-25 04:13:40','张三0252','南山中学','教科院二年级3班','李天一','A01','张翼德','13900000252','S000063','T000063','CDHS000126','1'),
	('HSR000253','2020年1月25日益州小学学生健康调查问卷0253','2020-01-19 21:40:39','张三0253','益州小学','教科院一年级5班','刘婵','A01','刘备','13900000253','S000064','T000064','CDHS000127','1'),
	('HSR000254','2020年1月25日益州小学学生健康调查问卷0254','2020-01-27 12:22:34','张三0254','大源中学','教科院二年级3班','刘阿斗','A01','刘玄德','13900000254','S000064','T000064','CDHS000127','1'),
	('HSR000255','2020年1月25日益州小学学生健康调查问卷0255','2020-01-26 14:30:03','张三0255','南山中学','教科院一年级5班','李天一','A01','张飞','13900000255','S000064','T000064','CDHS000128','1'),
	('HSR000256','2020年1月25日益州小学学生健康调查问卷0256','2020-02-02 02:33:38','张三0256','益州小学','教科院二年级3班','刘婵','A01','张翼德','13900000256','S000064','T000064','CDHS000128','1');

insert into student_answer_data values
	('SA000001','HSR000001','SDA000001','节假日是否到过武汉','A','1'),
	('SA000002','HSR000001','SDA000001','节假日是否到过武汉0002','B','1'),
	('SA000003','HSR000001','SDA000002','节假日是否到过武汉0003','A','1'),
	('SA000004','HSR000001','SDA000002','节假日是否到过武汉0004','B','1'),
	('SA000005','HSR000002','SDA000003','节假日是否到过武汉0005','A','1'),
	('SA000006','HSR000002','SDA000003','节假日是否到过武汉0006','B','1'),
	('SA000007','HSR000002','SDA000004','节假日是否到过武汉0007','A','1'),
	('SA000008','HSR000002','SDA000004','节假日是否到过武汉0008','B','1'),
	('SA000009','HSR000003','SDA000005','节假日是否到过武汉0009','A','1'),
	('SA000010','HSR000003','SDA000005','节假日是否到过武汉0010','B','1'),
	('SA000011','HSR000003','SDA000006','节假日是否到过武汉0011','A','1'),
	('SA000012','HSR000003','SDA000006','节假日是否到过武汉0012','B','1'),
	('SA000013','HSR000004','SDA000007','节假日是否到过武汉0013','A','1'),
	('SA000014','HSR000004','SDA000007','节假日是否到过武汉0014','B','1'),
	('SA000015','HSR000004','SDA000008','节假日是否到过武汉0015','A','1'),
	('SA000016','HSR000004','SDA000008','节假日是否到过武汉0016','B','1'),
	('SA000017','HSR000005','SDA000009','节假日是否到过武汉0017','A','1'),
	('SA000018','HSR000005','SDA000009','节假日是否到过武汉0018','B','1'),
	('SA000019','HSR000005','SDA000010','节假日是否到过武汉0019','A','1'),
	('SA000020','HSR000005','SDA000010','节假日是否到过武汉0020','B','1'),
	('SA000021','HSR000006','SDA000011','节假日是否到过武汉0021','A','1'),
	('SA000022','HSR000006','SDA000011','节假日是否到过武汉0022','B','1'),
	('SA000023','HSR000006','SDA000012','节假日是否到过武汉0023','A','1'),
	('SA000024','HSR000006','SDA000012','节假日是否到过武汉0024','B','1'),
	('SA000025','HSR000007','SDA000013','节假日是否到过武汉0025','A','1'),
	('SA000026','HSR000007','SDA000013','节假日是否到过武汉0026','B','1'),
	('SA000027','HSR000007','SDA000014','节假日是否到过武汉0027','A','1'),
	('SA000028','HSR000007','SDA000014','节假日是否到过武汉0028','B','1'),
	('SA000029','HSR000008','SDA000015','节假日是否到过武汉0029','A','1'),
	('SA000030','HSR000008','SDA000015','节假日是否到过武汉0030','B','1'),
	('SA000031','HSR000008','SDA000016','节假日是否到过武汉0031','A','1'),
	('SA000032','HSR000008','SDA000016','节假日是否到过武汉0032','B','1'),
	('SA000033','HSR000009','SDA000017','节假日是否到过武汉0033','A','1'),
	('SA000034','HSR000009','SDA000017','节假日是否到过武汉0034','B','1'),
	('SA000035','HSR000009','SDA000018','节假日是否到过武汉0035','A','1'),
	('SA000036','HSR000009','SDA000018','节假日是否到过武汉0036','B','1'),
	('SA000037','HSR000010','SDA000019','节假日是否到过武汉0037','A','1'),
	('SA000038','HSR000010','SDA000019','节假日是否到过武汉0038','B','1'),
	('SA000039','HSR000010','SDA000020','节假日是否到过武汉0039','A','1'),
	('SA000040','HSR000010','SDA000020','节假日是否到过武汉0040','B','1'),
	('SA000041','HSR000011','SDA000021','节假日是否到过武汉0041','A','1'),
	('SA000042','HSR000011','SDA000021','节假日是否到过武汉0042','B','1'),
	('SA000043','HSR000011','SDA000022','节假日是否到过武汉0043','A','1'),
	('SA000044','HSR000012','SDA000023','节假日是否到过武汉0044','B','1'),
	('SA000045','HSR000012','SDA000023','节假日是否到过武汉0045','A','1'),
	('SA000046','HSR000012','SDA000024','节假日是否到过武汉0046','B','1'),
	('SA000047','HSR000012','SDA000024','节假日是否到过武汉0047','A','1'),
	('SA000048','HSR000013','SDA000025','节假日是否到过武汉0048','B','1'),
	('SA000049','HSR000013','SDA000025','节假日是否到过武汉0049','A','1'),
	('SA000050','HSR000013','SDA000026','节假日是否到过武汉0050','B','1'),
	('SA000051','HSR000013','SDA000026','节假日是否到过武汉0051','A','1'),
	('SA000052','HSR000014','SDA000027','节假日是否到过武汉0052','B','1'),
	('SA000053','HSR000014','SDA000027','节假日是否到过武汉0053','A','1'),
	('SA000054','HSR000014','SDA000028','节假日是否到过武汉0054','B','1'),
	('SA000055','HSR000014','SDA000028','节假日是否到过武汉0055','A','1'),
	('SA000056','HSR000015','SDA000029','节假日是否到过武汉0056','B','1'),
	('SA000057','HSR000015','SDA000029','节假日是否到过武汉0057','A','1'),
	('SA000058','HSR000015','SDA000030','节假日是否到过武汉0058','B','1'),
	('SA000059','HSR000015','SDA000030','节假日是否到过武汉0059','A','1'),
	('SA000060','HSR000016','SDA000031','节假日是否到过武汉0060','B','1'),
	('SA000061','HSR000016','SDA000031','节假日是否到过武汉0061','A','1'),
	('SA000062','HSR000016','SDA000032','节假日是否到过武汉0062','B','1'),
	('SA000063','HSR000016','SDA000032','节假日是否到过武汉0063','A','1'),
	('SA000064','HSR000017','SDA000033','节假日是否到过武汉0064','B','1'),
	('SA000065','HSR000017','SDA000033','节假日是否到过武汉0065','A','1'),
	('SA000066','HSR000017','SDA000034','节假日是否到过武汉0066','B','1'),
	('SA000067','HSR000017','SDA000034','节假日是否到过武汉0067','A','1'),
	('SA000068','HSR000018','SDA000035','节假日是否到过武汉0068','B','1'),
	('SA000069','HSR000018','SDA000035','节假日是否到过武汉0069','A','1'),
	('SA000070','HSR000018','SDA000036','节假日是否到过武汉0070','B','1'),
	('SA000071','HSR000018','SDA000036','节假日是否到过武汉0071','A','1'),
	('SA000072','HSR000019','SDA000037','节假日是否到过武汉0072','B','1'),
	('SA000073','HSR000019','SDA000037','节假日是否到过武汉0073','A','1'),
	('SA000074','HSR000019','SDA000038','节假日是否到过武汉0074','B','1'),
	('SA000075','HSR000019','SDA000038','节假日是否到过武汉0075','A','1'),
	('SA000076','HSR000020','SDA000039','节假日是否到过武汉0076','B','1'),
	('SA000077','HSR000020','SDA000039','节假日是否到过武汉0077','A','1'),
	('SA000078','HSR000020','SDA000040','节假日是否到过武汉0078','B','1'),
	('SA000079','HSR000020','SDA000040','节假日是否到过武汉0079','A','1'),
	('SA000080','HSR000021','SDA000041','节假日是否到过武汉0080','B','1'),
	('SA000081','HSR000021','SDA000041','节假日是否到过武汉0081','A','1'),
	('SA000082','HSR000021','SDA000042','节假日是否到过武汉0082','B','1'),
	('SA000083','HSR000021','SDA000042','节假日是否到过武汉0083','A','1'),
	('SA000084','HSR000022','SDA000043','节假日是否到过武汉0084','B','1'),
	('SA000085','HSR000022','SDA000044','节假日是否到过武汉0085','A','1'),
	('SA000086','HSR000022','SDA000044','节假日是否到过武汉0086','B','1'),
	('SA000087','HSR000023','SDA000045','节假日是否到过武汉0087','A','1'),
	('SA000088','HSR000023','SDA000045','节假日是否到过武汉0088','B','1'),
	('SA000089','HSR000023','SDA000046','节假日是否到过武汉0089','A','1'),
	('SA000090','HSR000023','SDA000046','节假日是否到过武汉0090','B','1'),
	('SA000091','HSR000024','SDA000047','节假日是否到过武汉0091','A','1'),
	('SA000092','HSR000024','SDA000047','节假日是否到过武汉0092','B','1'),
	('SA000093','HSR000024','SDA000048','节假日是否到过武汉0093','A','1'),
	('SA000094','HSR000024','SDA000048','节假日是否到过武汉0094','B','1'),
	('SA000095','HSR000025','SDA000049','节假日是否到过武汉0095','A','1'),
	('SA000096','HSR000025','SDA000049','节假日是否到过武汉0096','B','1'),
	('SA000097','HSR000025','SDA000050','节假日是否到过武汉0097','A','1'),
	('SA000098','HSR000025','SDA000050','节假日是否到过武汉0098','B','1'),
	('SA000099','HSR000026','SDA000051','节假日是否到过武汉0099','A','1'),
	('SA000100','HSR000026','SDA000051','节假日是否到过武汉0100','B','1'),
	('SA000101','HSR000026','SDA000052','节假日是否到过武汉0101','A','1'),
	('SA000102','HSR000026','SDA000052','节假日是否到过武汉0102','B','1'),
	('SA000103','HSR000027','SDA000053','节假日是否到过武汉0103','A','1'),
	('SA000104','HSR000027','SDA000053','节假日是否到过武汉0104','B','1'),
	('SA000105','HSR000027','SDA000054','节假日是否到过武汉0105','A','1'),
	('SA000106','HSR000027','SDA000054','节假日是否到过武汉0106','B','1'),
	('SA000107','HSR000028','SDA000055','节假日是否到过武汉0107','A','1'),
	('SA000108','HSR000028','SDA000055','节假日是否到过武汉0108','B','1'),
	('SA000109','HSR000028','SDA000056','节假日是否到过武汉0109','A','1'),
	('SA000110','HSR000028','SDA000056','节假日是否到过武汉0110','B','1'),
	('SA000111','HSR000029','SDA000057','节假日是否到过武汉0111','A','1'),
	('SA000112','HSR000029','SDA000057','节假日是否到过武汉0112','B','1'),
	('SA000113','HSR000029','SDA000058','节假日是否到过武汉0113','A','1'),
	('SA000114','HSR000029','SDA000058','节假日是否到过武汉0114','B','1'),
	('SA000115','HSR000030','SDA000059','节假日是否到过武汉0115','A','1'),
	('SA000116','HSR000030','SDA000059','节假日是否到过武汉0116','B','1'),
	('SA000117','HSR000030','SDA000060','节假日是否到过武汉0117','A','1'),
	('SA000118','HSR000030','SDA000060','节假日是否到过武汉0118','B','1'),
	('SA000119','HSR000031','SDA000061','节假日是否到过武汉0119','A','1'),
	('SA000120','HSR000031','SDA000061','节假日是否到过武汉0120','B','1'),
	('SA000121','HSR000031','SDA000062','节假日是否到过武汉0121','A','1'),
	('SA000122','HSR000031','SDA000062','节假日是否到过武汉0122','B','1'),
	('SA000123','HSR000032','SDA000063','节假日是否到过武汉0123','A','1'),
	('SA000124','HSR000032','SDA000063','节假日是否到过武汉0124','B','1'),
	('SA000125','HSR000032','SDA000064','节假日是否到过武汉0125','A','1'),
	('SA000126','HSR000033','SDA000065','节假日是否到过武汉0126','B','1'),
	('SA000127','HSR000033','SDA000065','节假日是否到过武汉0127','A','1'),
	('SA000128','HSR000033','SDA000066','节假日是否到过武汉0128','B','1'),
	('SA000129','HSR000033','SDA000066','节假日是否到过武汉0129','A','1'),
	('SA000130','HSR000034','SDA000067','节假日是否到过武汉0130','B','1'),
	('SA000131','HSR000034','SDA000067','节假日是否到过武汉0131','A','1'),
	('SA000132','HSR000034','SDA000068','节假日是否到过武汉0132','B','1'),
	('SA000133','HSR000034','SDA000068','节假日是否到过武汉0133','A','1'),
	('SA000134','HSR000035','SDA000069','节假日是否到过武汉0134','B','1'),
	('SA000135','HSR000035','SDA000069','节假日是否到过武汉0135','A','1'),
	('SA000136','HSR000035','SDA000070','节假日是否到过武汉0136','B','1'),
	('SA000137','HSR000035','SDA000070','节假日是否到过武汉0137','A','1'),
	('SA000138','HSR000036','SDA000071','节假日是否到过武汉0138','B','1'),
	('SA000139','HSR000036','SDA000071','节假日是否到过武汉0139','A','1'),
	('SA000140','HSR000036','SDA000072','节假日是否到过武汉0140','B','1'),
	('SA000141','HSR000036','SDA000072','节假日是否到过武汉0141','A','1'),
	('SA000142','HSR000037','SDA000073','节假日是否到过武汉0142','B','1'),
	('SA000143','HSR000037','SDA000073','节假日是否到过武汉0143','A','1'),
	('SA000144','HSR000037','SDA000074','节假日是否到过武汉0144','B','1'),
	('SA000145','HSR000037','SDA000074','节假日是否到过武汉0145','A','1'),
	('SA000146','HSR000038','SDA000075','节假日是否到过武汉0146','B','1'),
	('SA000147','HSR000038','SDA000075','节假日是否到过武汉0147','A','1'),
	('SA000148','HSR000038','SDA000076','节假日是否到过武汉0148','B','1'),
	('SA000149','HSR000038','SDA000076','节假日是否到过武汉0149','A','1'),
	('SA000150','HSR000039','SDA000077','节假日是否到过武汉0150','B','1'),
	('SA000151','HSR000039','SDA000077','节假日是否到过武汉0151','A','1'),
	('SA000152','HSR000039','SDA000078','节假日是否到过武汉0152','B','1'),
	('SA000153','HSR000039','SDA000078','节假日是否到过武汉0153','A','1'),
	('SA000154','HSR000040','SDA000079','节假日是否到过武汉0154','B','1'),
	('SA000155','HSR000040','SDA000079','节假日是否到过武汉0155','A','1'),
	('SA000156','HSR000040','SDA000080','节假日是否到过武汉0156','B','1'),
	('SA000157','HSR000040','SDA000080','节假日是否到过武汉0157','A','1'),
	('SA000158','HSR000041','SDA000081','节假日是否到过武汉0158','B','1'),
	('SA000159','HSR000041','SDA000081','节假日是否到过武汉0159','A','1'),
	('SA000160','HSR000041','SDA000082','节假日是否到过武汉0160','B','1'),
	('SA000161','HSR000041','SDA000082','节假日是否到过武汉0161','A','1'),
	('SA000162','HSR000042','SDA000083','节假日是否到过武汉0162','B','1'),
	('SA000163','HSR000042','SDA000083','节假日是否到过武汉0163','A','1'),
	('SA000164','HSR000042','SDA000084','节假日是否到过武汉0164','B','1'),
	('SA000165','HSR000042','SDA000084','节假日是否到过武汉0165','A','1'),
	('SA000166','HSR000043','SDA000085','节假日是否到过武汉0166','B','1'),
	('SA000167','HSR000043','SDA000085','节假日是否到过武汉0167','A','1'),
	('SA000168','HSR000043','SDA000086','节假日是否到过武汉0168','B','1'),
	('SA000169','HSR000044','SDA000087','节假日是否到过武汉0169','A','1'),
	('SA000170','HSR000044','SDA000087','节假日是否到过武汉0170','B','1'),
	('SA000171','HSR000044','SDA000088','节假日是否到过武汉0171','A','1'),
	('SA000172','HSR000044','SDA000088','节假日是否到过武汉0172','B','1'),
	('SA000173','HSR000045','SDA000089','节假日是否到过武汉0173','A','1'),
	('SA000174','HSR000045','SDA000089','节假日是否到过武汉0174','B','1'),
	('SA000175','HSR000045','SDA000090','节假日是否到过武汉0175','A','1'),
	('SA000176','HSR000045','SDA000090','节假日是否到过武汉0176','B','1'),
	('SA000177','HSR000046','SDA000091','节假日是否到过武汉0177','A','1'),
	('SA000178','HSR000046','SDA000091','节假日是否到过武汉0178','B','1'),
	('SA000179','HSR000046','SDA000092','节假日是否到过武汉0179','A','1'),
	('SA000180','HSR000046','SDA000092','节假日是否到过武汉0180','B','1'),
	('SA000181','HSR000047','SDA000093','节假日是否到过武汉0181','A','1'),
	('SA000182','HSR000047','SDA000093','节假日是否到过武汉0182','B','1'),
	('SA000183','HSR000047','SDA000094','节假日是否到过武汉0183','A','1'),
	('SA000184','HSR000047','SDA000094','节假日是否到过武汉0184','B','1'),
	('SA000185','HSR000048','SDA000095','节假日是否到过武汉0185','A','1'),
	('SA000186','HSR000048','SDA000095','节假日是否到过武汉0186','B','1'),
	('SA000187','HSR000048','SDA000096','节假日是否到过武汉0187','A','1'),
	('SA000188','HSR000048','SDA000096','节假日是否到过武汉0188','B','1'),
	('SA000189','HSR000049','SDA000097','节假日是否到过武汉0189','A','1'),
	('SA000190','HSR000049','SDA000097','节假日是否到过武汉0190','B','1'),
	('SA000191','HSR000049','SDA000098','节假日是否到过武汉0191','A','1'),
	('SA000192','HSR000049','SDA000098','节假日是否到过武汉0192','B','1'),
	('SA000193','HSR000050','SDA000099','节假日是否到过武汉0193','A','1'),
	('SA000194','HSR000050','SDA000099','节假日是否到过武汉0194','B','1'),
	('SA000195','HSR000050','SDA000100','节假日是否到过武汉0195','A','1'),
	('SA000196','HSR000050','SDA000100','节假日是否到过武汉0196','B','1'),
	('SA000197','HSR000051','SDA000101','节假日是否到过武汉0197','A','1'),
	('SA000198','HSR000051','SDA000101','节假日是否到过武汉0198','B','1'),
	('SA000199','HSR000051','SDA000102','节假日是否到过武汉0199','A','1'),
	('SA000200','HSR000051','SDA000102','节假日是否到过武汉0200','B','1'),
	('SA000201','HSR000052','SDA000103','节假日是否到过武汉0201','A','1'),
	('SA000202','HSR000052','SDA000103','节假日是否到过武汉0202','B','1'),
	('SA000203','HSR000052','SDA000104','节假日是否到过武汉0203','A','1'),
	('SA000204','HSR000052','SDA000104','节假日是否到过武汉0204','B','1'),
	('SA000205','HSR000053','SDA000105','节假日是否到过武汉0205','A','1'),
	('SA000206','HSR000053','SDA000105','节假日是否到过武汉0206','B','1'),
	('SA000207','HSR000053','SDA000106','节假日是否到过武汉0207','A','1'),
	('SA000208','HSR000053','SDA000106','节假日是否到过武汉0208','B','1'),
	('SA000209','HSR000054','SDA000107','节假日是否到过武汉0209','A','1'),
	('SA000210','HSR000054','SDA000108','节假日是否到过武汉0210','B','1'),
	('SA000211','HSR000054','SDA000108','节假日是否到过武汉0211','A','1'),
	('SA000212','HSR000055','SDA000109','节假日是否到过武汉0212','B','1'),
	('SA000213','HSR000055','SDA000109','节假日是否到过武汉0213','A','1'),
	('SA000214','HSR000055','SDA000110','节假日是否到过武汉0214','B','1'),
	('SA000215','HSR000055','SDA000110','节假日是否到过武汉0215','A','1'),
	('SA000216','HSR000056','SDA000111','节假日是否到过武汉0216','B','1'),
	('SA000217','HSR000056','SDA000111','节假日是否到过武汉0217','A','1'),
	('SA000218','HSR000056','SDA000112','节假日是否到过武汉0218','B','1'),
	('SA000219','HSR000056','SDA000112','节假日是否到过武汉0219','A','1'),
	('SA000220','HSR000057','SDA000113','节假日是否到过武汉0220','B','1'),
	('SA000221','HSR000057','SDA000113','节假日是否到过武汉0221','A','1'),
	('SA000222','HSR000057','SDA000114','节假日是否到过武汉0222','B','1'),
	('SA000223','HSR000057','SDA000114','节假日是否到过武汉0223','A','1'),
	('SA000224','HSR000058','SDA000115','节假日是否到过武汉0224','B','1'),
	('SA000225','HSR000058','SDA000115','节假日是否到过武汉0225','A','1'),
	('SA000226','HSR000058','SDA000116','节假日是否到过武汉0226','B','1'),
	('SA000227','HSR000058','SDA000116','节假日是否到过武汉0227','A','1'),
	('SA000228','HSR000059','SDA000117','节假日是否到过武汉0228','B','1'),
	('SA000229','HSR000059','SDA000117','节假日是否到过武汉0229','A','1'),
	('SA000230','HSR000059','SDA000118','节假日是否到过武汉0230','B','1'),
	('SA000231','HSR000059','SDA000118','节假日是否到过武汉0231','A','1'),
	('SA000232','HSR000060','SDA000119','节假日是否到过武汉0232','B','1'),
	('SA000233','HSR000060','SDA000119','节假日是否到过武汉0233','A','1'),
	('SA000234','HSR000060','SDA000120','节假日是否到过武汉0234','B','1'),
	('SA000235','HSR000060','SDA000120','节假日是否到过武汉0235','A','1'),
	('SA000236','HSR000061','SDA000121','节假日是否到过武汉0236','B','1'),
	('SA000237','HSR000061','SDA000121','节假日是否到过武汉0237','A','1'),
	('SA000238','HSR000061','SDA000122','节假日是否到过武汉0238','B','1'),
	('SA000239','HSR000061','SDA000122','节假日是否到过武汉0239','A','1'),
	('SA000240','HSR000062','SDA000123','节假日是否到过武汉0240','B','1'),
	('SA000241','HSR000062','SDA000123','节假日是否到过武汉0241','A','1'),
	('SA000242','HSR000062','SDA000124','节假日是否到过武汉0242','B','1'),
	('SA000243','HSR000062','SDA000124','节假日是否到过武汉0243','A','1'),
	('SA000244','HSR000063','SDA000125','节假日是否到过武汉0244','B','1'),
	('SA000245','HSR000063','SDA000125','节假日是否到过武汉0245','A','1'),
	('SA000246','HSR000063','SDA000126','节假日是否到过武汉0246','B','1'),
	('SA000247','HSR000063','SDA000126','节假日是否到过武汉0247','A','1'),
	('SA000248','HSR000064','SDA000127','节假日是否到过武汉0248','B','1'),
	('SA000249','HSR000064','SDA000127','节假日是否到过武汉0249','A','1'),
	('SA000250','HSR000064','SDA000128','节假日是否到过武汉0250','B','1'),
	('SA000251','HSR000065','SDA000129','节假日是否到过武汉0251','A','1'),
	('SA000252','HSR000065','SDA000129','节假日是否到过武汉0252','B','1'),
	('SA000253','HSR000065','SDA000130','节假日是否到过武汉0253','A','1'),
	('SA000254','HSR000065','SDA000130','节假日是否到过武汉0254','B','1'),
	('SA000255','HSR000066','SDA000131','节假日是否到过武汉0255','A','1'),
	('SA000256','HSR000066','SDA000131','节假日是否到过武汉0256','B','1'),
	('SA000257','HSR000066','SDA000132','节假日是否到过武汉0257','A','1'),
	('SA000258','HSR000066','SDA000132','节假日是否到过武汉0258','B','1'),
	('SA000259','HSR000067','SDA000133','节假日是否到过武汉0259','A','1'),
	('SA000260','HSR000067','SDA000133','节假日是否到过武汉0260','B','1'),
	('SA000261','HSR000067','SDA000134','节假日是否到过武汉0261','A','1'),
	('SA000262','HSR000067','SDA000134','节假日是否到过武汉0262','B','1'),
	('SA000263','HSR000068','SDA000135','节假日是否到过武汉0263','A','1'),
	('SA000264','HSR000068','SDA000135','节假日是否到过武汉0264','B','1'),
	('SA000265','HSR000068','SDA000136','节假日是否到过武汉0265','A','1'),
	('SA000266','HSR000068','SDA000136','节假日是否到过武汉0266','B','1'),
	('SA000267','HSR000069','SDA000137','节假日是否到过武汉0267','A','1'),
	('SA000268','HSR000069','SDA000137','节假日是否到过武汉0268','B','1'),
	('SA000269','HSR000069','SDA000138','节假日是否到过武汉0269','A','1'),
	('SA000270','HSR000069','SDA000138','节假日是否到过武汉0270','B','1'),
	('SA000271','HSR000070','SDA000139','节假日是否到过武汉0271','A','1'),
	('SA000272','HSR000070','SDA000139','节假日是否到过武汉0272','B','1'),
	('SA000273','HSR000070','SDA000140','节假日是否到过武汉0273','A','1'),
	('SA000274','HSR000070','SDA000140','节假日是否到过武汉0274','B','1'),
	('SA000275','HSR000071','SDA000141','节假日是否到过武汉0275','A','1'),
	('SA000276','HSR000071','SDA000141','节假日是否到过武汉0276','B','1'),
	('SA000277','HSR000071','SDA000142','节假日是否到过武汉0277','A','1'),
	('SA000278','HSR000071','SDA000142','节假日是否到过武汉0278','B','1'),
	('SA000279','HSR000072','SDA000143','节假日是否到过武汉0279','A','1'),
	('SA000280','HSR000072','SDA000143','节假日是否到过武汉0280','B','1'),
	('SA000281','HSR000072','SDA000144','节假日是否到过武汉0281','A','1'),
	('SA000282','HSR000072','SDA000144','节假日是否到过武汉0282','B','1'),
	('SA000283','HSR000073','SDA000145','节假日是否到过武汉0283','A','1'),
	('SA000284','HSR000073','SDA000145','节假日是否到过武汉0284','B','1'),
	('SA000285','HSR000073','SDA000146','节假日是否到过武汉0285','A','1'),
	('SA000286','HSR000073','SDA000146','节假日是否到过武汉0286','B','1'),
	('SA000287','HSR000074','SDA000147','节假日是否到过武汉0287','A','1'),
	('SA000288','HSR000074','SDA000147','节假日是否到过武汉0288','B','1'),
	('SA000289','HSR000074','SDA000148','节假日是否到过武汉0289','A','1'),
	('SA000290','HSR000074','SDA000148','节假日是否到过武汉0290','B','1'),
	('SA000291','HSR000075','SDA000149','节假日是否到过武汉0291','A','1'),
	('SA000292','HSR000075','SDA000149','节假日是否到过武汉0292','B','1'),
	('SA000293','HSR000075','SDA000150','节假日是否到过武汉0293','A','1'),
	('SA000294','HSR000076','SDA000151','节假日是否到过武汉0294','B','1'),
	('SA000295','HSR000076','SDA000151','节假日是否到过武汉0295','A','1'),
	('SA000296','HSR000076','SDA000152','节假日是否到过武汉0296','B','1'),
	('SA000297','HSR000076','SDA000152','节假日是否到过武汉0297','A','1'),
	('SA000298','HSR000077','SDA000153','节假日是否到过武汉0298','B','1'),
	('SA000299','HSR000077','SDA000153','节假日是否到过武汉0299','A','1'),
	('SA000300','HSR000077','SDA000154','节假日是否到过武汉0300','B','1'),
	('SA000301','HSR000077','SDA000154','节假日是否到过武汉0301','A','1'),
	('SA000302','HSR000078','SDA000155','节假日是否到过武汉0302','B','1'),
	('SA000303','HSR000078','SDA000155','节假日是否到过武汉0303','A','1'),
	('SA000304','HSR000078','SDA000156','节假日是否到过武汉0304','B','1'),
	('SA000305','HSR000078','SDA000156','节假日是否到过武汉0305','A','1'),
	('SA000306','HSR000079','SDA000157','节假日是否到过武汉0306','B','1'),
	('SA000307','HSR000079','SDA000157','节假日是否到过武汉0307','A','1'),
	('SA000308','HSR000079','SDA000158','节假日是否到过武汉0308','B','1'),
	('SA000309','HSR000079','SDA000158','节假日是否到过武汉0309','A','1'),
	('SA000310','HSR000080','SDA000159','节假日是否到过武汉0310','B','1'),
	('SA000311','HSR000080','SDA000159','节假日是否到过武汉0311','A','1'),
	('SA000312','HSR000080','SDA000160','节假日是否到过武汉0312','B','1'),
	('SA000313','HSR000080','SDA000160','节假日是否到过武汉0313','A','1'),
	('SA000314','HSR000081','SDA000161','节假日是否到过武汉0314','B','1'),
	('SA000315','HSR000081','SDA000161','节假日是否到过武汉0315','A','1'),
	('SA000316','HSR000081','SDA000162','节假日是否到过武汉0316','B','1'),
	('SA000317','HSR000081','SDA000162','节假日是否到过武汉0317','A','1'),
	('SA000318','HSR000082','SDA000163','节假日是否到过武汉0318','B','1'),
	('SA000319','HSR000082','SDA000163','节假日是否到过武汉0319','A','1'),
	('SA000320','HSR000082','SDA000164','节假日是否到过武汉0320','B','1'),
	('SA000321','HSR000082','SDA000164','节假日是否到过武汉0321','A','1'),
	('SA000322','HSR000083','SDA000165','节假日是否到过武汉0322','B','1'),
	('SA000323','HSR000083','SDA000165','节假日是否到过武汉0323','A','1'),
	('SA000324','HSR000083','SDA000166','节假日是否到过武汉0324','B','1'),
	('SA000325','HSR000083','SDA000166','节假日是否到过武汉0325','A','1'),
	('SA000326','HSR000084','SDA000167','节假日是否到过武汉0326','B','1'),
	('SA000327','HSR000084','SDA000167','节假日是否到过武汉0327','A','1'),
	('SA000328','HSR000084','SDA000168','节假日是否到过武汉0328','B','1'),
	('SA000329','HSR000084','SDA000168','节假日是否到过武汉0329','A','1'),
	('SA000330','HSR000085','SDA000169','节假日是否到过武汉0330','B','1'),
	('SA000331','HSR000085','SDA000169','节假日是否到过武汉0331','A','1'),
	('SA000332','HSR000085','SDA000170','节假日是否到过武汉0332','B','1'),
	('SA000333','HSR000085','SDA000170','节假日是否到过武汉0333','A','1'),
	('SA000334','HSR000086','SDA000171','节假日是否到过武汉0334','B','1'),
	('SA000335','HSR000086','SDA000172','节假日是否到过武汉0335','A','1'),
	('SA000336','HSR000086','SDA000172','节假日是否到过武汉0336','B','1'),
	('SA000337','HSR000087','SDA000173','节假日是否到过武汉0337','A','1'),
	('SA000338','HSR000087','SDA000173','节假日是否到过武汉0338','B','1'),
	('SA000339','HSR000087','SDA000174','节假日是否到过武汉0339','A','1'),
	('SA000340','HSR000087','SDA000174','节假日是否到过武汉0340','B','1'),
	('SA000341','HSR000088','SDA000175','节假日是否到过武汉0341','A','1'),
	('SA000342','HSR000088','SDA000175','节假日是否到过武汉0342','B','1'),
	('SA000343','HSR000088','SDA000176','节假日是否到过武汉0343','A','1'),
	('SA000344','HSR000088','SDA000176','节假日是否到过武汉0344','B','1'),
	('SA000345','HSR000089','SDA000177','节假日是否到过武汉0345','A','1'),
	('SA000346','HSR000089','SDA000177','节假日是否到过武汉0346','B','1'),
	('SA000347','HSR000089','SDA000178','节假日是否到过武汉0347','A','1'),
	('SA000348','HSR000089','SDA000178','节假日是否到过武汉0348','B','1'),
	('SA000349','HSR000090','SDA000179','节假日是否到过武汉0349','A','1'),
	('SA000350','HSR000090','SDA000179','节假日是否到过武汉0350','B','1'),
	('SA000351','HSR000090','SDA000180','节假日是否到过武汉0351','A','1'),
	('SA000352','HSR000090','SDA000180','节假日是否到过武汉0352','B','1'),
	('SA000353','HSR000091','SDA000181','节假日是否到过武汉0353','A','1'),
	('SA000354','HSR000091','SDA000181','节假日是否到过武汉0354','B','1'),
	('SA000355','HSR000091','SDA000182','节假日是否到过武汉0355','A','1'),
	('SA000356','HSR000091','SDA000182','节假日是否到过武汉0356','B','1'),
	('SA000357','HSR000092','SDA000183','节假日是否到过武汉0357','A','1'),
	('SA000358','HSR000092','SDA000183','节假日是否到过武汉0358','B','1'),
	('SA000359','HSR000092','SDA000184','节假日是否到过武汉0359','A','1'),
	('SA000360','HSR000092','SDA000184','节假日是否到过武汉0360','B','1'),
	('SA000361','HSR000093','SDA000185','节假日是否到过武汉0361','A','1'),
	('SA000362','HSR000093','SDA000185','节假日是否到过武汉0362','B','1'),
	('SA000363','HSR000093','SDA000186','节假日是否到过武汉0363','A','1'),
	('SA000364','HSR000093','SDA000186','节假日是否到过武汉0364','B','1'),
	('SA000365','HSR000094','SDA000187','节假日是否到过武汉0365','A','1'),
	('SA000366','HSR000094','SDA000187','节假日是否到过武汉0366','B','1'),
	('SA000367','HSR000094','SDA000188','节假日是否到过武汉0367','A','1'),
	('SA000368','HSR000094','SDA000188','节假日是否到过武汉0368','B','1'),
	('SA000369','HSR000095','SDA000189','节假日是否到过武汉0369','A','1'),
	('SA000370','HSR000095','SDA000189','节假日是否到过武汉0370','B','1'),
	('SA000371','HSR000095','SDA000190','节假日是否到过武汉0371','A','1'),
	('SA000372','HSR000095','SDA000190','节假日是否到过武汉0372','B','1'),
	('SA000373','HSR000096','SDA000191','节假日是否到过武汉0373','A','1'),
	('SA000374','HSR000096','SDA000191','节假日是否到过武汉0374','B','1'),
	('SA000375','HSR000096','SDA000192','节假日是否到过武汉0375','A','1'),
	('SA000376','HSR000097','SDA000193','节假日是否到过武汉0376','B','1'),
	('SA000377','HSR000097','SDA000193','节假日是否到过武汉0377','A','1'),
	('SA000378','HSR000097','SDA000194','节假日是否到过武汉0378','B','1'),
	('SA000379','HSR000097','SDA000194','节假日是否到过武汉0379','A','1'),
	('SA000380','HSR000098','SDA000195','节假日是否到过武汉0380','B','1'),
	('SA000381','HSR000098','SDA000195','节假日是否到过武汉0381','A','1'),
	('SA000382','HSR000098','SDA000196','节假日是否到过武汉0382','B','1'),
	('SA000383','HSR000098','SDA000196','节假日是否到过武汉0383','A','1'),
	('SA000384','HSR000099','SDA000197','节假日是否到过武汉0384','B','1'),
	('SA000385','HSR000099','SDA000197','节假日是否到过武汉0385','A','1'),
	('SA000386','HSR000099','SDA000198','节假日是否到过武汉0386','B','1'),
	('SA000387','HSR000099','SDA000198','节假日是否到过武汉0387','A','1'),
	('SA000388','HSR000100','SDA000199','节假日是否到过武汉0388','B','1'),
	('SA000389','HSR000100','SDA000199','节假日是否到过武汉0389','A','1'),
	('SA000390','HSR000100','SDA000200','节假日是否到过武汉0390','B','1'),
	('SA000391','HSR000100','SDA000200','节假日是否到过武汉0391','A','1'),
	('SA000392','HSR000101','SDA000201','节假日是否到过武汉0392','B','1'),
	('SA000393','HSR000101','SDA000201','节假日是否到过武汉0393','A','1'),
	('SA000394','HSR000101','SDA000202','节假日是否到过武汉0394','B','1'),
	('SA000395','HSR000101','SDA000202','节假日是否到过武汉0395','A','1'),
	('SA000396','HSR000102','SDA000203','节假日是否到过武汉0396','B','1'),
	('SA000397','HSR000102','SDA000203','节假日是否到过武汉0397','A','1'),
	('SA000398','HSR000102','SDA000204','节假日是否到过武汉0398','B','1'),
	('SA000399','HSR000102','SDA000204','节假日是否到过武汉0399','A','1'),
	('SA000400','HSR000103','SDA000205','节假日是否到过武汉0400','B','1'),
	('SA000401','HSR000103','SDA000205','节假日是否到过武汉0401','A','1'),
	('SA000402','HSR000103','SDA000206','节假日是否到过武汉0402','B','1'),
	('SA000403','HSR000103','SDA000206','节假日是否到过武汉0403','A','1'),
	('SA000404','HSR000104','SDA000207','节假日是否到过武汉0404','B','1'),
	('SA000405','HSR000104','SDA000207','节假日是否到过武汉0405','A','1'),
	('SA000406','HSR000104','SDA000208','节假日是否到过武汉0406','B','1'),
	('SA000407','HSR000104','SDA000208','节假日是否到过武汉0407','A','1'),
	('SA000408','HSR000105','SDA000209','节假日是否到过武汉0408','B','1'),
	('SA000409','HSR000105','SDA000209','节假日是否到过武汉0409','A','1'),
	('SA000410','HSR000105','SDA000210','节假日是否到过武汉0410','B','1'),
	('SA000411','HSR000105','SDA000210','节假日是否到过武汉0411','A','1'),
	('SA000412','HSR000106','SDA000211','节假日是否到过武汉0412','B','1'),
	('SA000413','HSR000106','SDA000211','节假日是否到过武汉0413','A','1'),
	('SA000414','HSR000106','SDA000212','节假日是否到过武汉0414','B','1'),
	('SA000415','HSR000106','SDA000212','节假日是否到过武汉0415','A','1'),
	('SA000416','HSR000107','SDA000213','节假日是否到过武汉0416','B','1'),
	('SA000417','HSR000107','SDA000213','节假日是否到过武汉0417','A','1'),
	('SA000418','HSR000107','SDA000214','节假日是否到过武汉0418','B','1'),
	('SA000419','HSR000108','SDA000215','节假日是否到过武汉0419','A','1'),
	('SA000420','HSR000108','SDA000215','节假日是否到过武汉0420','B','1'),
	('SA000421','HSR000108','SDA000216','节假日是否到过武汉0421','A','1'),
	('SA000422','HSR000108','SDA000216','节假日是否到过武汉0422','B','1'),
	('SA000423','HSR000109','SDA000217','节假日是否到过武汉0423','A','1'),
	('SA000424','HSR000109','SDA000217','节假日是否到过武汉0424','B','1'),
	('SA000425','HSR000109','SDA000218','节假日是否到过武汉0425','A','1'),
	('SA000426','HSR000109','SDA000218','节假日是否到过武汉0426','B','1'),
	('SA000427','HSR000110','SDA000219','节假日是否到过武汉0427','A','1'),
	('SA000428','HSR000110','SDA000219','节假日是否到过武汉0428','B','1'),
	('SA000429','HSR000110','SDA000220','节假日是否到过武汉0429','A','1'),
	('SA000430','HSR000110','SDA000220','节假日是否到过武汉0430','B','1'),
	('SA000431','HSR000111','SDA000221','节假日是否到过武汉0431','A','1'),
	('SA000432','HSR000111','SDA000221','节假日是否到过武汉0432','B','1'),
	('SA000433','HSR000111','SDA000222','节假日是否到过武汉0433','A','1'),
	('SA000434','HSR000111','SDA000222','节假日是否到过武汉0434','B','1'),
	('SA000435','HSR000112','SDA000223','节假日是否到过武汉0435','A','1'),
	('SA000436','HSR000112','SDA000223','节假日是否到过武汉0436','B','1'),
	('SA000437','HSR000112','SDA000224','节假日是否到过武汉0437','A','1'),
	('SA000438','HSR000112','SDA000224','节假日是否到过武汉0438','B','1'),
	('SA000439','HSR000113','SDA000225','节假日是否到过武汉0439','A','1'),
	('SA000440','HSR000113','SDA000225','节假日是否到过武汉0440','B','1'),
	('SA000441','HSR000113','SDA000226','节假日是否到过武汉0441','A','1'),
	('SA000442','HSR000113','SDA000226','节假日是否到过武汉0442','B','1'),
	('SA000443','HSR000114','SDA000227','节假日是否到过武汉0443','A','1'),
	('SA000444','HSR000114','SDA000227','节假日是否到过武汉0444','B','1'),
	('SA000445','HSR000114','SDA000228','节假日是否到过武汉0445','A','1'),
	('SA000446','HSR000114','SDA000228','节假日是否到过武汉0446','B','1'),
	('SA000447','HSR000115','SDA000229','节假日是否到过武汉0447','A','1'),
	('SA000448','HSR000115','SDA000229','节假日是否到过武汉0448','B','1'),
	('SA000449','HSR000115','SDA000230','节假日是否到过武汉0449','A','1'),
	('SA000450','HSR000115','SDA000230','节假日是否到过武汉0450','B','1'),
	('SA000451','HSR000116','SDA000231','节假日是否到过武汉0451','A','1'),
	('SA000452','HSR000116','SDA000231','节假日是否到过武汉0452','B','1'),
	('SA000453','HSR000116','SDA000232','节假日是否到过武汉0453','A','1'),
	('SA000454','HSR000116','SDA000232','节假日是否到过武汉0454','B','1'),
	('SA000455','HSR000117','SDA000233','节假日是否到过武汉0455','A','1'),
	('SA000456','HSR000117','SDA000233','节假日是否到过武汉0456','B','1'),
	('SA000457','HSR000117','SDA000234','节假日是否到过武汉0457','A','1'),
	('SA000458','HSR000117','SDA000234','节假日是否到过武汉0458','B','1'),
	('SA000459','HSR000118','SDA000235','节假日是否到过武汉0459','A','1'),
	('SA000460','HSR000118','SDA000236','节假日是否到过武汉0460','B','1'),
	('SA000461','HSR000118','SDA000236','节假日是否到过武汉0461','A','1'),
	('SA000462','HSR000119','SDA000237','节假日是否到过武汉0462','B','1'),
	('SA000463','HSR000119','SDA000237','节假日是否到过武汉0463','A','1'),
	('SA000464','HSR000119','SDA000238','节假日是否到过武汉0464','B','1'),
	('SA000465','HSR000119','SDA000238','节假日是否到过武汉0465','A','1'),
	('SA000466','HSR000120','SDA000239','节假日是否到过武汉0466','B','1'),
	('SA000467','HSR000120','SDA000239','节假日是否到过武汉0467','A','1'),
	('SA000468','HSR000120','SDA000240','节假日是否到过武汉0468','B','1'),
	('SA000469','HSR000120','SDA000240','节假日是否到过武汉0469','A','1'),
	('SA000470','HSR000121','SDA000241','节假日是否到过武汉0470','B','1'),
	('SA000471','HSR000121','SDA000241','节假日是否到过武汉0471','A','1'),
	('SA000472','HSR000121','SDA000242','节假日是否到过武汉0472','B','1'),
	('SA000473','HSR000121','SDA000242','节假日是否到过武汉0473','A','1'),
	('SA000474','HSR000122','SDA000243','节假日是否到过武汉0474','B','1'),
	('SA000475','HSR000122','SDA000243','节假日是否到过武汉0475','A','1'),
	('SA000476','HSR000122','SDA000244','节假日是否到过武汉0476','B','1'),
	('SA000477','HSR000122','SDA000244','节假日是否到过武汉0477','A','1'),
	('SA000478','HSR000123','SDA000245','节假日是否到过武汉0478','B','1'),
	('SA000479','HSR000123','SDA000245','节假日是否到过武汉0479','A','1'),
	('SA000480','HSR000123','SDA000246','节假日是否到过武汉0480','B','1'),
	('SA000481','HSR000123','SDA000246','节假日是否到过武汉0481','A','1'),
	('SA000482','HSR000124','SDA000247','节假日是否到过武汉0482','B','1'),
	('SA000483','HSR000124','SDA000247','节假日是否到过武汉0483','A','1'),
	('SA000484','HSR000124','SDA000248','节假日是否到过武汉0484','B','1'),
	('SA000485','HSR000124','SDA000248','节假日是否到过武汉0485','A','1'),
	('SA000486','HSR000125','SDA000249','节假日是否到过武汉0486','B','1'),
	('SA000487','HSR000125','SDA000249','节假日是否到过武汉0487','A','1'),
	('SA000488','HSR000125','SDA000250','节假日是否到过武汉0488','B','1'),
	('SA000489','HSR000125','SDA000250','节假日是否到过武汉0489','A','1'),
	('SA000490','HSR000126','SDA000251','节假日是否到过武汉0490','B','1'),
	('SA000491','HSR000126','SDA000251','节假日是否到过武汉0491','A','1'),
	('SA000492','HSR000126','SDA000252','节假日是否到过武汉0492','B','1'),
	('SA000493','HSR000126','SDA000252','节假日是否到过武汉0493','A','1'),
	('SA000494','HSR000127','SDA000253','节假日是否到过武汉0494','B','1'),
	('SA000495','HSR000127','SDA000253','节假日是否到过武汉0495','A','1'),
	('SA000496','HSR000127','SDA000254','节假日是否到过武汉0496','B','1'),
	('SA000497','HSR000127','SDA000254','节假日是否到过武汉0497','A','1'),
	('SA000498','HSR000128','SDA000255','节假日是否到过武汉0498','B','1'),
	('SA000499','HSR000128','SDA000255','节假日是否到过武汉0499','A','1'),
	('SA000500','HSR000128','SDA000256','节假日是否到过武汉0500','B','1'),
	('SA000501','HSR000129','SDA000257','节假日是否到过武汉0501','A','1'),
	('SA000502','HSR000129','SDA000257','节假日是否到过武汉0502','B','1'),
	('SA000503','HSR000129','SDA000258','节假日是否到过武汉0503','A','1'),
	('SA000504','HSR000129','SDA000258','节假日是否到过武汉0504','B','1'),
	('SA000505','HSR000130','SDA000259','节假日是否到过武汉0505','A','1'),
	('SA000506','HSR000130','SDA000259','节假日是否到过武汉0506','B','1'),
	('SA000507','HSR000130','SDA000260','节假日是否到过武汉0507','A','1'),
	('SA000508','HSR000130','SDA000260','节假日是否到过武汉0508','B','1'),
	('SA000509','HSR000131','SDA000261','节假日是否到过武汉0509','A','1'),
	('SA000510','HSR000131','SDA000261','节假日是否到过武汉0510','B','1'),
	('SA000511','HSR000131','SDA000262','节假日是否到过武汉0511','A','1'),
	('SA000512','HSR000131','SDA000262','节假日是否到过武汉0512','B','1'),
	('SA000513','HSR000132','SDA000263','节假日是否到过武汉0513','A','1'),
	('SA000514','HSR000132','SDA000263','节假日是否到过武汉0514','B','1'),
	('SA000515','HSR000132','SDA000264','节假日是否到过武汉0515','A','1'),
	('SA000516','HSR000132','SDA000264','节假日是否到过武汉0516','B','1'),
	('SA000517','HSR000133','SDA000265','节假日是否到过武汉0517','A','1'),
	('SA000518','HSR000133','SDA000265','节假日是否到过武汉0518','B','1'),
	('SA000519','HSR000133','SDA000266','节假日是否到过武汉0519','A','1'),
	('SA000520','HSR000133','SDA000266','节假日是否到过武汉0520','B','1'),
	('SA000521','HSR000134','SDA000267','节假日是否到过武汉0521','A','1'),
	('SA000522','HSR000134','SDA000267','节假日是否到过武汉0522','B','1'),
	('SA000523','HSR000134','SDA000268','节假日是否到过武汉0523','A','1'),
	('SA000524','HSR000134','SDA000268','节假日是否到过武汉0524','B','1'),
	('SA000525','HSR000135','SDA000269','节假日是否到过武汉0525','A','1'),
	('SA000526','HSR000135','SDA000269','节假日是否到过武汉0526','B','1'),
	('SA000527','HSR000135','SDA000270','节假日是否到过武汉0527','A','1'),
	('SA000528','HSR000135','SDA000270','节假日是否到过武汉0528','B','1'),
	('SA000529','HSR000136','SDA000271','节假日是否到过武汉0529','A','1'),
	('SA000530','HSR000136','SDA000271','节假日是否到过武汉0530','B','1'),
	('SA000531','HSR000136','SDA000272','节假日是否到过武汉0531','A','1'),
	('SA000532','HSR000136','SDA000272','节假日是否到过武汉0532','B','1'),
	('SA000533','HSR000137','SDA000273','节假日是否到过武汉0533','A','1'),
	('SA000534','HSR000137','SDA000273','节假日是否到过武汉0534','B','1'),
	('SA000535','HSR000137','SDA000274','节假日是否到过武汉0535','A','1'),
	('SA000536','HSR000137','SDA000274','节假日是否到过武汉0536','B','1'),
	('SA000537','HSR000138','SDA000275','节假日是否到过武汉0537','A','1'),
	('SA000538','HSR000138','SDA000275','节假日是否到过武汉0538','B','1'),
	('SA000539','HSR000138','SDA000276','节假日是否到过武汉0539','A','1'),
	('SA000540','HSR000138','SDA000276','节假日是否到过武汉0540','B','1'),
	('SA000541','HSR000139','SDA000277','节假日是否到过武汉0541','A','1'),
	('SA000542','HSR000139','SDA000277','节假日是否到过武汉0542','B','1'),
	('SA000543','HSR000139','SDA000278','节假日是否到过武汉0543','A','1'),
	('SA000544','HSR000140','SDA000279','节假日是否到过武汉0544','B','1'),
	('SA000545','HSR000140','SDA000279','节假日是否到过武汉0545','A','1'),
	('SA000546','HSR000140','SDA000280','节假日是否到过武汉0546','B','1'),
	('SA000547','HSR000140','SDA000280','节假日是否到过武汉0547','A','1'),
	('SA000548','HSR000141','SDA000281','节假日是否到过武汉0548','B','1'),
	('SA000549','HSR000141','SDA000281','节假日是否到过武汉0549','A','1'),
	('SA000550','HSR000141','SDA000282','节假日是否到过武汉0550','B','1'),
	('SA000551','HSR000141','SDA000282','节假日是否到过武汉0551','A','1'),
	('SA000552','HSR000142','SDA000283','节假日是否到过武汉0552','B','1'),
	('SA000553','HSR000142','SDA000283','节假日是否到过武汉0553','A','1'),
	('SA000554','HSR000142','SDA000284','节假日是否到过武汉0554','B','1'),
	('SA000555','HSR000142','SDA000284','节假日是否到过武汉0555','A','1'),
	('SA000556','HSR000143','SDA000285','节假日是否到过武汉0556','B','1'),
	('SA000557','HSR000143','SDA000285','节假日是否到过武汉0557','A','1'),
	('SA000558','HSR000143','SDA000286','节假日是否到过武汉0558','B','1'),
	('SA000559','HSR000143','SDA000286','节假日是否到过武汉0559','A','1'),
	('SA000560','HSR000144','SDA000287','节假日是否到过武汉0560','B','1'),
	('SA000561','HSR000144','SDA000287','节假日是否到过武汉0561','A','1'),
	('SA000562','HSR000144','SDA000288','节假日是否到过武汉0562','B','1'),
	('SA000563','HSR000144','SDA000288','节假日是否到过武汉0563','A','1'),
	('SA000564','HSR000145','SDA000289','节假日是否到过武汉0564','B','1'),
	('SA000565','HSR000145','SDA000289','节假日是否到过武汉0565','A','1'),
	('SA000566','HSR000145','SDA000290','节假日是否到过武汉0566','B','1'),
	('SA000567','HSR000145','SDA000290','节假日是否到过武汉0567','A','1'),
	('SA000568','HSR000146','SDA000291','节假日是否到过武汉0568','B','1'),
	('SA000569','HSR000146','SDA000291','节假日是否到过武汉0569','A','1'),
	('SA000570','HSR000146','SDA000292','节假日是否到过武汉0570','B','1'),
	('SA000571','HSR000146','SDA000292','节假日是否到过武汉0571','A','1'),
	('SA000572','HSR000147','SDA000293','节假日是否到过武汉0572','B','1'),
	('SA000573','HSR000147','SDA000293','节假日是否到过武汉0573','A','1'),
	('SA000574','HSR000147','SDA000294','节假日是否到过武汉0574','B','1'),
	('SA000575','HSR000147','SDA000294','节假日是否到过武汉0575','A','1'),
	('SA000576','HSR000148','SDA000295','节假日是否到过武汉0576','B','1'),
	('SA000577','HSR000148','SDA000295','节假日是否到过武汉0577','A','1'),
	('SA000578','HSR000148','SDA000296','节假日是否到过武汉0578','B','1'),
	('SA000579','HSR000148','SDA000296','节假日是否到过武汉0579','A','1'),
	('SA000580','HSR000149','SDA000297','节假日是否到过武汉0580','B','1'),
	('SA000581','HSR000149','SDA000297','节假日是否到过武汉0581','A','1'),
	('SA000582','HSR000149','SDA000298','节假日是否到过武汉0582','B','1'),
	('SA000583','HSR000149','SDA000298','节假日是否到过武汉0583','A','1'),
	('SA000584','HSR000150','SDA000299','节假日是否到过武汉0584','B','1'),
	('SA000585','HSR000150','SDA000300','节假日是否到过武汉0585','A','1'),
	('SA000586','HSR000150','SDA000300','节假日是否到过武汉0586','B','1'),
	('SA000587','HSR000151','SDA000301','节假日是否到过武汉0587','A','1'),
	('SA000588','HSR000151','SDA000301','节假日是否到过武汉0588','B','1'),
	('SA000589','HSR000151','SDA000302','节假日是否到过武汉0589','A','1'),
	('SA000590','HSR000151','SDA000302','节假日是否到过武汉0590','B','1'),
	('SA000591','HSR000152','SDA000303','节假日是否到过武汉0591','A','1'),
	('SA000592','HSR000152','SDA000303','节假日是否到过武汉0592','B','1'),
	('SA000593','HSR000152','SDA000304','节假日是否到过武汉0593','A','1'),
	('SA000594','HSR000152','SDA000304','节假日是否到过武汉0594','B','1'),
	('SA000595','HSR000153','SDA000305','节假日是否到过武汉0595','A','1'),
	('SA000596','HSR000153','SDA000305','节假日是否到过武汉0596','B','1'),
	('SA000597','HSR000153','SDA000306','节假日是否到过武汉0597','A','1'),
	('SA000598','HSR000153','SDA000306','节假日是否到过武汉0598','B','1'),
	('SA000599','HSR000154','SDA000307','节假日是否到过武汉0599','A','1'),
	('SA000600','HSR000154','SDA000307','节假日是否到过武汉0600','B','1'),
	('SA000601','HSR000154','SDA000308','节假日是否到过武汉0601','A','1'),
	('SA000602','HSR000154','SDA000308','节假日是否到过武汉0602','B','1'),
	('SA000603','HSR000155','SDA000309','节假日是否到过武汉0603','A','1'),
	('SA000604','HSR000155','SDA000309','节假日是否到过武汉0604','B','1'),
	('SA000605','HSR000155','SDA000310','节假日是否到过武汉0605','A','1'),
	('SA000606','HSR000155','SDA000310','节假日是否到过武汉0606','B','1'),
	('SA000607','HSR000156','SDA000311','节假日是否到过武汉0607','A','1'),
	('SA000608','HSR000156','SDA000311','节假日是否到过武汉0608','B','1'),
	('SA000609','HSR000156','SDA000312','节假日是否到过武汉0609','A','1'),
	('SA000610','HSR000156','SDA000312','节假日是否到过武汉0610','B','1'),
	('SA000611','HSR000157','SDA000313','节假日是否到过武汉0611','A','1'),
	('SA000612','HSR000157','SDA000313','节假日是否到过武汉0612','B','1'),
	('SA000613','HSR000157','SDA000314','节假日是否到过武汉0613','A','1'),
	('SA000614','HSR000157','SDA000314','节假日是否到过武汉0614','B','1'),
	('SA000615','HSR000158','SDA000315','节假日是否到过武汉0615','A','1'),
	('SA000616','HSR000158','SDA000315','节假日是否到过武汉0616','B','1'),
	('SA000617','HSR000158','SDA000316','节假日是否到过武汉0617','A','1'),
	('SA000618','HSR000158','SDA000316','节假日是否到过武汉0618','B','1'),
	('SA000619','HSR000159','SDA000317','节假日是否到过武汉0619','A','1'),
	('SA000620','HSR000159','SDA000317','节假日是否到过武汉0620','B','1'),
	('SA000621','HSR000159','SDA000318','节假日是否到过武汉0621','A','1'),
	('SA000622','HSR000159','SDA000318','节假日是否到过武汉0622','B','1'),
	('SA000623','HSR000160','SDA000319','节假日是否到过武汉0623','A','1'),
	('SA000624','HSR000160','SDA000319','节假日是否到过武汉0624','B','1'),
	('SA000625','HSR000160','SDA000320','节假日是否到过武汉0625','A','1'),
	('SA000626','HSR000161','SDA000321','节假日是否到过武汉0626','B','1'),
	('SA000627','HSR000161','SDA000321','节假日是否到过武汉0627','A','1'),
	('SA000628','HSR000161','SDA000322','节假日是否到过武汉0628','B','1'),
	('SA000629','HSR000161','SDA000322','节假日是否到过武汉0629','A','1'),
	('SA000630','HSR000162','SDA000323','节假日是否到过武汉0630','B','1'),
	('SA000631','HSR000162','SDA000323','节假日是否到过武汉0631','A','1'),
	('SA000632','HSR000162','SDA000324','节假日是否到过武汉0632','B','1'),
	('SA000633','HSR000162','SDA000324','节假日是否到过武汉0633','A','1'),
	('SA000634','HSR000163','SDA000325','节假日是否到过武汉0634','B','1'),
	('SA000635','HSR000163','SDA000325','节假日是否到过武汉0635','A','1'),
	('SA000636','HSR000163','SDA000326','节假日是否到过武汉0636','B','1'),
	('SA000637','HSR000163','SDA000326','节假日是否到过武汉0637','A','1'),
	('SA000638','HSR000164','SDA000327','节假日是否到过武汉0638','B','1'),
	('SA000639','HSR000164','SDA000327','节假日是否到过武汉0639','A','1'),
	('SA000640','HSR000164','SDA000328','节假日是否到过武汉0640','B','1'),
	('SA000641','HSR000164','SDA000328','节假日是否到过武汉0641','A','1'),
	('SA000642','HSR000165','SDA000329','节假日是否到过武汉0642','B','1'),
	('SA000643','HSR000165','SDA000329','节假日是否到过武汉0643','A','1'),
	('SA000644','HSR000165','SDA000330','节假日是否到过武汉0644','B','1'),
	('SA000645','HSR000165','SDA000330','节假日是否到过武汉0645','A','1'),
	('SA000646','HSR000166','SDA000331','节假日是否到过武汉0646','B','1'),
	('SA000647','HSR000166','SDA000331','节假日是否到过武汉0647','A','1'),
	('SA000648','HSR000166','SDA000332','节假日是否到过武汉0648','B','1'),
	('SA000649','HSR000166','SDA000332','节假日是否到过武汉0649','A','1'),
	('SA000650','HSR000167','SDA000333','节假日是否到过武汉0650','B','1'),
	('SA000651','HSR000167','SDA000333','节假日是否到过武汉0651','A','1'),
	('SA000652','HSR000167','SDA000334','节假日是否到过武汉0652','B','1'),
	('SA000653','HSR000167','SDA000334','节假日是否到过武汉0653','A','1'),
	('SA000654','HSR000168','SDA000335','节假日是否到过武汉0654','B','1'),
	('SA000655','HSR000168','SDA000335','节假日是否到过武汉0655','A','1'),
	('SA000656','HSR000168','SDA000336','节假日是否到过武汉0656','B','1'),
	('SA000657','HSR000168','SDA000336','节假日是否到过武汉0657','A','1'),
	('SA000658','HSR000169','SDA000337','节假日是否到过武汉0658','B','1'),
	('SA000659','HSR000169','SDA000337','节假日是否到过武汉0659','A','1'),
	('SA000660','HSR000169','SDA000338','节假日是否到过武汉0660','B','1'),
	('SA000661','HSR000169','SDA000338','节假日是否到过武汉0661','A','1'),
	('SA000662','HSR000170','SDA000339','节假日是否到过武汉0662','B','1'),
	('SA000663','HSR000170','SDA000339','节假日是否到过武汉0663','A','1'),
	('SA000664','HSR000170','SDA000340','节假日是否到过武汉0664','B','1'),
	('SA000665','HSR000170','SDA000340','节假日是否到过武汉0665','A','1'),
	('SA000666','HSR000171','SDA000341','节假日是否到过武汉0666','B','1'),
	('SA000667','HSR000171','SDA000341','节假日是否到过武汉0667','A','1'),
	('SA000668','HSR000171','SDA000342','节假日是否到过武汉0668','B','1'),
	('SA000669','HSR000172','SDA000343','节假日是否到过武汉0669','A','1'),
	('SA000670','HSR000172','SDA000343','节假日是否到过武汉0670','B','1'),
	('SA000671','HSR000172','SDA000344','节假日是否到过武汉0671','A','1'),
	('SA000672','HSR000172','SDA000344','节假日是否到过武汉0672','B','1'),
	('SA000673','HSR000173','SDA000345','节假日是否到过武汉0673','A','1'),
	('SA000674','HSR000173','SDA000345','节假日是否到过武汉0674','B','1'),
	('SA000675','HSR000173','SDA000346','节假日是否到过武汉0675','A','1'),
	('SA000676','HSR000173','SDA000346','节假日是否到过武汉0676','B','1'),
	('SA000677','HSR000174','SDA000347','节假日是否到过武汉0677','A','1'),
	('SA000678','HSR000174','SDA000347','节假日是否到过武汉0678','B','1'),
	('SA000679','HSR000174','SDA000348','节假日是否到过武汉0679','A','1'),
	('SA000680','HSR000174','SDA000348','节假日是否到过武汉0680','B','1'),
	('SA000681','HSR000175','SDA000349','节假日是否到过武汉0681','A','1'),
	('SA000682','HSR000175','SDA000349','节假日是否到过武汉0682','B','1'),
	('SA000683','HSR000175','SDA000350','节假日是否到过武汉0683','A','1'),
	('SA000684','HSR000175','SDA000350','节假日是否到过武汉0684','B','1'),
	('SA000685','HSR000176','SDA000351','节假日是否到过武汉0685','A','1'),
	('SA000686','HSR000176','SDA000351','节假日是否到过武汉0686','B','1'),
	('SA000687','HSR000176','SDA000352','节假日是否到过武汉0687','A','1'),
	('SA000688','HSR000176','SDA000352','节假日是否到过武汉0688','B','1'),
	('SA000689','HSR000177','SDA000353','节假日是否到过武汉0689','A','1'),
	('SA000690','HSR000177','SDA000353','节假日是否到过武汉0690','B','1'),
	('SA000691','HSR000177','SDA000354','节假日是否到过武汉0691','A','1'),
	('SA000692','HSR000177','SDA000354','节假日是否到过武汉0692','B','1'),
	('SA000693','HSR000178','SDA000355','节假日是否到过武汉0693','A','1'),
	('SA000694','HSR000178','SDA000355','节假日是否到过武汉0694','B','1'),
	('SA000695','HSR000178','SDA000356','节假日是否到过武汉0695','A','1'),
	('SA000696','HSR000178','SDA000356','节假日是否到过武汉0696','B','1'),
	('SA000697','HSR000179','SDA000357','节假日是否到过武汉0697','A','1'),
	('SA000698','HSR000179','SDA000357','节假日是否到过武汉0698','B','1'),
	('SA000699','HSR000179','SDA000358','节假日是否到过武汉0699','A','1'),
	('SA000700','HSR000179','SDA000358','节假日是否到过武汉0700','B','1'),
	('SA000701','HSR000180','SDA000359','节假日是否到过武汉0701','A','1'),
	('SA000702','HSR000180','SDA000359','节假日是否到过武汉0702','B','1'),
	('SA000703','HSR000180','SDA000360','节假日是否到过武汉0703','A','1'),
	('SA000704','HSR000180','SDA000360','节假日是否到过武汉0704','B','1'),
	('SA000705','HSR000181','SDA000361','节假日是否到过武汉0705','A','1'),
	('SA000706','HSR000181','SDA000361','节假日是否到过武汉0706','B','1'),
	('SA000707','HSR000181','SDA000362','节假日是否到过武汉0707','A','1'),
	('SA000708','HSR000181','SDA000362','节假日是否到过武汉0708','B','1'),
	('SA000709','HSR000182','SDA000363','节假日是否到过武汉0709','A','1'),
	('SA000710','HSR000182','SDA000364','节假日是否到过武汉0710','B','1'),
	('SA000711','HSR000182','SDA000364','节假日是否到过武汉0711','A','1'),
	('SA000712','HSR000183','SDA000365','节假日是否到过武汉0712','B','1'),
	('SA000713','HSR000183','SDA000365','节假日是否到过武汉0713','A','1'),
	('SA000714','HSR000183','SDA000366','节假日是否到过武汉0714','B','1'),
	('SA000715','HSR000183','SDA000366','节假日是否到过武汉0715','A','1'),
	('SA000716','HSR000184','SDA000367','节假日是否到过武汉0716','B','1'),
	('SA000717','HSR000184','SDA000367','节假日是否到过武汉0717','A','1'),
	('SA000718','HSR000184','SDA000368','节假日是否到过武汉0718','B','1'),
	('SA000719','HSR000184','SDA000368','节假日是否到过武汉0719','A','1'),
	('SA000720','HSR000185','SDA000369','节假日是否到过武汉0720','B','1'),
	('SA000721','HSR000185','SDA000369','节假日是否到过武汉0721','A','1'),
	('SA000722','HSR000185','SDA000370','节假日是否到过武汉0722','B','1'),
	('SA000723','HSR000185','SDA000370','节假日是否到过武汉0723','A','1'),
	('SA000724','HSR000186','SDA000371','节假日是否到过武汉0724','B','1'),
	('SA000725','HSR000186','SDA000371','节假日是否到过武汉0725','A','1'),
	('SA000726','HSR000186','SDA000372','节假日是否到过武汉0726','B','1'),
	('SA000727','HSR000186','SDA000372','节假日是否到过武汉0727','A','1'),
	('SA000728','HSR000187','SDA000373','节假日是否到过武汉0728','B','1'),
	('SA000729','HSR000187','SDA000373','节假日是否到过武汉0729','A','1'),
	('SA000730','HSR000187','SDA000374','节假日是否到过武汉0730','B','1'),
	('SA000731','HSR000187','SDA000374','节假日是否到过武汉0731','A','1'),
	('SA000732','HSR000188','SDA000375','节假日是否到过武汉0732','B','1'),
	('SA000733','HSR000188','SDA000375','节假日是否到过武汉0733','A','1'),
	('SA000734','HSR000188','SDA000376','节假日是否到过武汉0734','B','1'),
	('SA000735','HSR000188','SDA000376','节假日是否到过武汉0735','A','1'),
	('SA000736','HSR000189','SDA000377','节假日是否到过武汉0736','B','1'),
	('SA000737','HSR000189','SDA000377','节假日是否到过武汉0737','A','1'),
	('SA000738','HSR000189','SDA000378','节假日是否到过武汉0738','B','1'),
	('SA000739','HSR000189','SDA000378','节假日是否到过武汉0739','A','1'),
	('SA000740','HSR000190','SDA000379','节假日是否到过武汉0740','B','1'),
	('SA000741','HSR000190','SDA000379','节假日是否到过武汉0741','A','1'),
	('SA000742','HSR000190','SDA000380','节假日是否到过武汉0742','B','1'),
	('SA000743','HSR000190','SDA000380','节假日是否到过武汉0743','A','1'),
	('SA000744','HSR000191','SDA000381','节假日是否到过武汉0744','B','1'),
	('SA000745','HSR000191','SDA000381','节假日是否到过武汉0745','A','1'),
	('SA000746','HSR000191','SDA000382','节假日是否到过武汉0746','B','1'),
	('SA000747','HSR000191','SDA000382','节假日是否到过武汉0747','A','1'),
	('SA000748','HSR000192','SDA000383','节假日是否到过武汉0748','B','1'),
	('SA000749','HSR000192','SDA000383','节假日是否到过武汉0749','A','1'),
	('SA000750','HSR000192','SDA000384','节假日是否到过武汉0750','B','1'),
	('SA000751','HSR000193','SDA000385','节假日是否到过武汉0751','A','1'),
	('SA000752','HSR000193','SDA000385','节假日是否到过武汉0752','B','1'),
	('SA000753','HSR000193','SDA000386','节假日是否到过武汉0753','A','1'),
	('SA000754','HSR000193','SDA000386','节假日是否到过武汉0754','B','1'),
	('SA000755','HSR000194','SDA000387','节假日是否到过武汉0755','A','1'),
	('SA000756','HSR000194','SDA000387','节假日是否到过武汉0756','B','1'),
	('SA000757','HSR000194','SDA000388','节假日是否到过武汉0757','A','1'),
	('SA000758','HSR000194','SDA000388','节假日是否到过武汉0758','B','1'),
	('SA000759','HSR000195','SDA000389','节假日是否到过武汉0759','A','1'),
	('SA000760','HSR000195','SDA000389','节假日是否到过武汉0760','B','1'),
	('SA000761','HSR000195','SDA000390','节假日是否到过武汉0761','A','1'),
	('SA000762','HSR000195','SDA000390','节假日是否到过武汉0762','B','1'),
	('SA000763','HSR000196','SDA000391','节假日是否到过武汉0763','A','1'),
	('SA000764','HSR000196','SDA000391','节假日是否到过武汉0764','B','1'),
	('SA000765','HSR000196','SDA000392','节假日是否到过武汉0765','A','1'),
	('SA000766','HSR000196','SDA000392','节假日是否到过武汉0766','B','1'),
	('SA000767','HSR000197','SDA000393','节假日是否到过武汉0767','A','1'),
	('SA000768','HSR000197','SDA000393','节假日是否到过武汉0768','B','1'),
	('SA000769','HSR000197','SDA000394','节假日是否到过武汉0769','A','1'),
	('SA000770','HSR000197','SDA000394','节假日是否到过武汉0770','B','1'),
	('SA000771','HSR000198','SDA000395','节假日是否到过武汉0771','A','1'),
	('SA000772','HSR000198','SDA000395','节假日是否到过武汉0772','B','1'),
	('SA000773','HSR000198','SDA000396','节假日是否到过武汉0773','A','1'),
	('SA000774','HSR000198','SDA000396','节假日是否到过武汉0774','B','1'),
	('SA000775','HSR000199','SDA000397','节假日是否到过武汉0775','A','1'),
	('SA000776','HSR000199','SDA000397','节假日是否到过武汉0776','B','1'),
	('SA000777','HSR000199','SDA000398','节假日是否到过武汉0777','A','1'),
	('SA000778','HSR000199','SDA000398','节假日是否到过武汉0778','B','1'),
	('SA000779','HSR000200','SDA000399','节假日是否到过武汉0779','A','1'),
	('SA000780','HSR000200','SDA000399','节假日是否到过武汉0780','B','1'),
	('SA000781','HSR000200','SDA000400','节假日是否到过武汉0781','A','1'),
	('SA000782','HSR000200','SDA000400','节假日是否到过武汉0782','B','1'),
	('SA000783','HSR000201','SDA000401','节假日是否到过武汉0783','A','1'),
	('SA000784','HSR000201','SDA000401','节假日是否到过武汉0784','B','1'),
	('SA000785','HSR000201','SDA000402','节假日是否到过武汉0785','A','1'),
	('SA000786','HSR000201','SDA000402','节假日是否到过武汉0786','B','1'),
	('SA000787','HSR000202','SDA000403','节假日是否到过武汉0787','A','1'),
	('SA000788','HSR000202','SDA000403','节假日是否到过武汉0788','B','1'),
	('SA000789','HSR000202','SDA000404','节假日是否到过武汉0789','A','1'),
	('SA000790','HSR000202','SDA000404','节假日是否到过武汉0790','B','1'),
	('SA000791','HSR000203','SDA000405','节假日是否到过武汉0791','A','1'),
	('SA000792','HSR000203','SDA000405','节假日是否到过武汉0792','B','1'),
	('SA000793','HSR000203','SDA000406','节假日是否到过武汉0793','A','1'),
	('SA000794','HSR000204','SDA000407','节假日是否到过武汉0794','B','1'),
	('SA000795','HSR000204','SDA000407','节假日是否到过武汉0795','A','1'),
	('SA000796','HSR000204','SDA000408','节假日是否到过武汉0796','B','1'),
	('SA000797','HSR000204','SDA000408','节假日是否到过武汉0797','A','1'),
	('SA000798','HSR000205','SDA000409','节假日是否到过武汉0798','B','1'),
	('SA000799','HSR000205','SDA000409','节假日是否到过武汉0799','A','1'),
	('SA000800','HSR000205','SDA000410','节假日是否到过武汉0800','B','1'),
	('SA000801','HSR000205','SDA000410','节假日是否到过武汉0801','A','1'),
	('SA000802','HSR000206','SDA000411','节假日是否到过武汉0802','B','1'),
	('SA000803','HSR000206','SDA000411','节假日是否到过武汉0803','A','1'),
	('SA000804','HSR000206','SDA000412','节假日是否到过武汉0804','B','1'),
	('SA000805','HSR000206','SDA000412','节假日是否到过武汉0805','A','1'),
	('SA000806','HSR000207','SDA000413','节假日是否到过武汉0806','B','1'),
	('SA000807','HSR000207','SDA000413','节假日是否到过武汉0807','A','1'),
	('SA000808','HSR000207','SDA000414','节假日是否到过武汉0808','B','1'),
	('SA000809','HSR000207','SDA000414','节假日是否到过武汉0809','A','1'),
	('SA000810','HSR000208','SDA000415','节假日是否到过武汉0810','B','1'),
	('SA000811','HSR000208','SDA000415','节假日是否到过武汉0811','A','1'),
	('SA000812','HSR000208','SDA000416','节假日是否到过武汉0812','B','1'),
	('SA000813','HSR000208','SDA000416','节假日是否到过武汉0813','A','1'),
	('SA000814','HSR000209','SDA000417','节假日是否到过武汉0814','B','1'),
	('SA000815','HSR000209','SDA000417','节假日是否到过武汉0815','A','1'),
	('SA000816','HSR000209','SDA000418','节假日是否到过武汉0816','B','1'),
	('SA000817','HSR000209','SDA000418','节假日是否到过武汉0817','A','1'),
	('SA000818','HSR000210','SDA000419','节假日是否到过武汉0818','B','1'),
	('SA000819','HSR000210','SDA000419','节假日是否到过武汉0819','A','1'),
	('SA000820','HSR000210','SDA000420','节假日是否到过武汉0820','B','1'),
	('SA000821','HSR000210','SDA000420','节假日是否到过武汉0821','A','1'),
	('SA000822','HSR000211','SDA000421','节假日是否到过武汉0822','B','1'),
	('SA000823','HSR000211','SDA000421','节假日是否到过武汉0823','A','1'),
	('SA000824','HSR000211','SDA000422','节假日是否到过武汉0824','B','1'),
	('SA000825','HSR000211','SDA000422','节假日是否到过武汉0825','A','1'),
	('SA000826','HSR000212','SDA000423','节假日是否到过武汉0826','B','1'),
	('SA000827','HSR000212','SDA000423','节假日是否到过武汉0827','A','1'),
	('SA000828','HSR000212','SDA000424','节假日是否到过武汉0828','B','1'),
	('SA000829','HSR000212','SDA000424','节假日是否到过武汉0829','A','1'),
	('SA000830','HSR000213','SDA000425','节假日是否到过武汉0830','B','1'),
	('SA000831','HSR000213','SDA000425','节假日是否到过武汉0831','A','1'),
	('SA000832','HSR000213','SDA000426','节假日是否到过武汉0832','B','1'),
	('SA000833','HSR000213','SDA000426','节假日是否到过武汉0833','A','1'),
	('SA000834','HSR000214','SDA000427','节假日是否到过武汉0834','B','1'),
	('SA000835','HSR000214','SDA000428','节假日是否到过武汉0835','A','1'),
	('SA000836','HSR000214','SDA000428','节假日是否到过武汉0836','B','1'),
	('SA000837','HSR000215','SDA000429','节假日是否到过武汉0837','A','1'),
	('SA000838','HSR000215','SDA000429','节假日是否到过武汉0838','B','1'),
	('SA000839','HSR000215','SDA000430','节假日是否到过武汉0839','A','1'),
	('SA000840','HSR000215','SDA000430','节假日是否到过武汉0840','B','1'),
	('SA000841','HSR000216','SDA000431','节假日是否到过武汉0841','A','1'),
	('SA000842','HSR000216','SDA000431','节假日是否到过武汉0842','B','1'),
	('SA000843','HSR000216','SDA000432','节假日是否到过武汉0843','A','1'),
	('SA000844','HSR000216','SDA000432','节假日是否到过武汉0844','B','1'),
	('SA000845','HSR000217','SDA000433','节假日是否到过武汉0845','A','1'),
	('SA000846','HSR000217','SDA000433','节假日是否到过武汉0846','B','1'),
	('SA000847','HSR000217','SDA000434','节假日是否到过武汉0847','A','1'),
	('SA000848','HSR000217','SDA000434','节假日是否到过武汉0848','B','1'),
	('SA000849','HSR000218','SDA000435','节假日是否到过武汉0849','A','1'),
	('SA000850','HSR000218','SDA000435','节假日是否到过武汉0850','B','1'),
	('SA000851','HSR000218','SDA000436','节假日是否到过武汉0851','A','1'),
	('SA000852','HSR000218','SDA000436','节假日是否到过武汉0852','B','1'),
	('SA000853','HSR000219','SDA000437','节假日是否到过武汉0853','A','1'),
	('SA000854','HSR000219','SDA000437','节假日是否到过武汉0854','B','1'),
	('SA000855','HSR000219','SDA000438','节假日是否到过武汉0855','A','1'),
	('SA000856','HSR000219','SDA000438','节假日是否到过武汉0856','B','1'),
	('SA000857','HSR000220','SDA000439','节假日是否到过武汉0857','A','1'),
	('SA000858','HSR000220','SDA000439','节假日是否到过武汉0858','B','1'),
	('SA000859','HSR000220','SDA000440','节假日是否到过武汉0859','A','1'),
	('SA000860','HSR000220','SDA000440','节假日是否到过武汉0860','B','1'),
	('SA000861','HSR000221','SDA000441','节假日是否到过武汉0861','A','1'),
	('SA000862','HSR000221','SDA000441','节假日是否到过武汉0862','B','1'),
	('SA000863','HSR000221','SDA000442','节假日是否到过武汉0863','A','1'),
	('SA000864','HSR000221','SDA000442','节假日是否到过武汉0864','B','1'),
	('SA000865','HSR000222','SDA000443','节假日是否到过武汉0865','A','1'),
	('SA000866','HSR000222','SDA000443','节假日是否到过武汉0866','B','1'),
	('SA000867','HSR000222','SDA000444','节假日是否到过武汉0867','A','1'),
	('SA000868','HSR000222','SDA000444','节假日是否到过武汉0868','B','1'),
	('SA000869','HSR000223','SDA000445','节假日是否到过武汉0869','A','1'),
	('SA000870','HSR000223','SDA000445','节假日是否到过武汉0870','B','1'),
	('SA000871','HSR000223','SDA000446','节假日是否到过武汉0871','A','1'),
	('SA000872','HSR000223','SDA000446','节假日是否到过武汉0872','B','1'),
	('SA000873','HSR000224','SDA000447','节假日是否到过武汉0873','A','1'),
	('SA000874','HSR000224','SDA000447','节假日是否到过武汉0874','B','1'),
	('SA000875','HSR000224','SDA000448','节假日是否到过武汉0875','A','1'),
	('SA000876','HSR000225','SDA000449','节假日是否到过武汉0876','B','1'),
	('SA000877','HSR000225','SDA000449','节假日是否到过武汉0877','A','1'),
	('SA000878','HSR000225','SDA000450','节假日是否到过武汉0878','B','1'),
	('SA000879','HSR000225','SDA000450','节假日是否到过武汉0879','A','1'),
	('SA000880','HSR000226','SDA000451','节假日是否到过武汉0880','B','1'),
	('SA000881','HSR000226','SDA000451','节假日是否到过武汉0881','A','1'),
	('SA000882','HSR000226','SDA000452','节假日是否到过武汉0882','B','1'),
	('SA000883','HSR000226','SDA000452','节假日是否到过武汉0883','A','1'),
	('SA000884','HSR000227','SDA000453','节假日是否到过武汉0884','B','1'),
	('SA000885','HSR000227','SDA000453','节假日是否到过武汉0885','A','1'),
	('SA000886','HSR000227','SDA000454','节假日是否到过武汉0886','B','1'),
	('SA000887','HSR000227','SDA000454','节假日是否到过武汉0887','A','1'),
	('SA000888','HSR000228','SDA000455','节假日是否到过武汉0888','B','1'),
	('SA000889','HSR000228','SDA000455','节假日是否到过武汉0889','A','1'),
	('SA000890','HSR000228','SDA000456','节假日是否到过武汉0890','B','1'),
	('SA000891','HSR000228','SDA000456','节假日是否到过武汉0891','A','1'),
	('SA000892','HSR000229','SDA000457','节假日是否到过武汉0892','B','1'),
	('SA000893','HSR000229','SDA000457','节假日是否到过武汉0893','A','1'),
	('SA000894','HSR000229','SDA000458','节假日是否到过武汉0894','B','1'),
	('SA000895','HSR000229','SDA000458','节假日是否到过武汉0895','A','1'),
	('SA000896','HSR000230','SDA000459','节假日是否到过武汉0896','B','1'),
	('SA000897','HSR000230','SDA000459','节假日是否到过武汉0897','A','1'),
	('SA000898','HSR000230','SDA000460','节假日是否到过武汉0898','B','1'),
	('SA000899','HSR000230','SDA000460','节假日是否到过武汉0899','A','1'),
	('SA000900','HSR000231','SDA000461','节假日是否到过武汉0900','B','1'),
	('SA000901','HSR000231','SDA000461','节假日是否到过武汉0901','A','1'),
	('SA000902','HSR000231','SDA000462','节假日是否到过武汉0902','B','1'),
	('SA000903','HSR000231','SDA000462','节假日是否到过武汉0903','A','1'),
	('SA000904','HSR000232','SDA000463','节假日是否到过武汉0904','B','1'),
	('SA000905','HSR000232','SDA000463','节假日是否到过武汉0905','A','1'),
	('SA000906','HSR000232','SDA000464','节假日是否到过武汉0906','B','1'),
	('SA000907','HSR000232','SDA000464','节假日是否到过武汉0907','A','1'),
	('SA000908','HSR000233','SDA000465','节假日是否到过武汉0908','B','1'),
	('SA000909','HSR000233','SDA000465','节假日是否到过武汉0909','A','1'),
	('SA000910','HSR000233','SDA000466','节假日是否到过武汉0910','B','1'),
	('SA000911','HSR000233','SDA000466','节假日是否到过武汉0911','A','1'),
	('SA000912','HSR000234','SDA000467','节假日是否到过武汉0912','B','1'),
	('SA000913','HSR000234','SDA000467','节假日是否到过武汉0913','A','1'),
	('SA000914','HSR000234','SDA000468','节假日是否到过武汉0914','B','1'),
	('SA000915','HSR000234','SDA000468','节假日是否到过武汉0915','A','1'),
	('SA000916','HSR000235','SDA000469','节假日是否到过武汉0916','B','1'),
	('SA000917','HSR000235','SDA000469','节假日是否到过武汉0917','A','1'),
	('SA000918','HSR000235','SDA000470','节假日是否到过武汉0918','B','1'),
	('SA000919','HSR000236','SDA000471','节假日是否到过武汉0919','A','1'),
	('SA000920','HSR000236','SDA000471','节假日是否到过武汉0920','B','1'),
	('SA000921','HSR000236','SDA000472','节假日是否到过武汉0921','A','1'),
	('SA000922','HSR000236','SDA000472','节假日是否到过武汉0922','B','1'),
	('SA000923','HSR000237','SDA000473','节假日是否到过武汉0923','A','1'),
	('SA000924','HSR000237','SDA000473','节假日是否到过武汉0924','B','1'),
	('SA000925','HSR000237','SDA000474','节假日是否到过武汉0925','A','1'),
	('SA000926','HSR000237','SDA000474','节假日是否到过武汉0926','B','1'),
	('SA000927','HSR000238','SDA000475','节假日是否到过武汉0927','A','1'),
	('SA000928','HSR000238','SDA000475','节假日是否到过武汉0928','B','1'),
	('SA000929','HSR000238','SDA000476','节假日是否到过武汉0929','A','1'),
	('SA000930','HSR000238','SDA000476','节假日是否到过武汉0930','B','1'),
	('SA000931','HSR000239','SDA000477','节假日是否到过武汉0931','A','1'),
	('SA000932','HSR000239','SDA000477','节假日是否到过武汉0932','B','1'),
	('SA000933','HSR000239','SDA000478','节假日是否到过武汉0933','A','1'),
	('SA000934','HSR000239','SDA000478','节假日是否到过武汉0934','B','1'),
	('SA000935','HSR000240','SDA000479','节假日是否到过武汉0935','A','1'),
	('SA000936','HSR000240','SDA000479','节假日是否到过武汉0936','B','1'),
	('SA000937','HSR000240','SDA000480','节假日是否到过武汉0937','A','1'),
	('SA000938','HSR000240','SDA000480','节假日是否到过武汉0938','B','1'),
	('SA000939','HSR000241','SDA000481','节假日是否到过武汉0939','A','1'),
	('SA000940','HSR000241','SDA000481','节假日是否到过武汉0940','B','1'),
	('SA000941','HSR000241','SDA000482','节假日是否到过武汉0941','A','1'),
	('SA000942','HSR000241','SDA000482','节假日是否到过武汉0942','B','1'),
	('SA000943','HSR000242','SDA000483','节假日是否到过武汉0943','A','1'),
	('SA000944','HSR000242','SDA000483','节假日是否到过武汉0944','B','1'),
	('SA000945','HSR000242','SDA000484','节假日是否到过武汉0945','A','1'),
	('SA000946','HSR000242','SDA000484','节假日是否到过武汉0946','B','1'),
	('SA000947','HSR000243','SDA000485','节假日是否到过武汉0947','A','1'),
	('SA000948','HSR000243','SDA000485','节假日是否到过武汉0948','B','1'),
	('SA000949','HSR000243','SDA000486','节假日是否到过武汉0949','A','1'),
	('SA000950','HSR000243','SDA000486','节假日是否到过武汉0950','B','1'),
	('SA000951','HSR000244','SDA000487','节假日是否到过武汉0951','A','1'),
	('SA000952','HSR000244','SDA000487','节假日是否到过武汉0952','B','1'),
	('SA000953','HSR000244','SDA000488','节假日是否到过武汉0953','A','1'),
	('SA000954','HSR000244','SDA000488','节假日是否到过武汉0954','B','1'),
	('SA000955','HSR000245','SDA000489','节假日是否到过武汉0955','A','1'),
	('SA000956','HSR000245','SDA000489','节假日是否到过武汉0956','B','1'),
	('SA000957','HSR000245','SDA000490','节假日是否到过武汉0957','A','1'),
	('SA000958','HSR000245','SDA000490','节假日是否到过武汉0958','B','1'),
	('SA000959','HSR000246','SDA000491','节假日是否到过武汉0959','A','1'),
	('SA000960','HSR000246','SDA000492','节假日是否到过武汉0960','B','1'),
	('SA000961','HSR000246','SDA000492','节假日是否到过武汉0961','A','1'),
	('SA000962','HSR000247','SDA000493','节假日是否到过武汉0962','B','1'),
	('SA000963','HSR000247','SDA000493','节假日是否到过武汉0963','A','1'),
	('SA000964','HSR000247','SDA000494','节假日是否到过武汉0964','B','1'),
	('SA000965','HSR000247','SDA000494','节假日是否到过武汉0965','A','1'),
	('SA000966','HSR000248','SDA000495','节假日是否到过武汉0966','B','1'),
	('SA000967','HSR000248','SDA000495','节假日是否到过武汉0967','A','1'),
	('SA000968','HSR000248','SDA000496','节假日是否到过武汉0968','B','1'),
	('SA000969','HSR000248','SDA000496','节假日是否到过武汉0969','A','1'),
	('SA000970','HSR000249','SDA000497','节假日是否到过武汉0970','B','1'),
	('SA000971','HSR000249','SDA000497','节假日是否到过武汉0971','A','1'),
	('SA000972','HSR000249','SDA000498','节假日是否到过武汉0972','B','1'),
	('SA000973','HSR000249','SDA000498','节假日是否到过武汉0973','A','1'),
	('SA000974','HSR000250','SDA000499','节假日是否到过武汉0974','B','1'),
	('SA000975','HSR000250','SDA000499','节假日是否到过武汉0975','A','1'),
	('SA000976','HSR000250','SDA000500','节假日是否到过武汉0976','B','1'),
	('SA000977','HSR000250','SDA000500','节假日是否到过武汉0977','A','1'),
	('SA000978','HSR000251','SDA000501','节假日是否到过武汉0978','B','1'),
	('SA000979','HSR000251','SDA000501','节假日是否到过武汉0979','A','1'),
	('SA000980','HSR000251','SDA000502','节假日是否到过武汉0980','B','1'),
	('SA000981','HSR000251','SDA000502','节假日是否到过武汉0981','A','1'),
	('SA000982','HSR000252','SDA000503','节假日是否到过武汉0982','B','1'),
	('SA000983','HSR000252','SDA000503','节假日是否到过武汉0983','A','1'),
	('SA000984','HSR000252','SDA000504','节假日是否到过武汉0984','B','1'),
	('SA000985','HSR000252','SDA000504','节假日是否到过武汉0985','A','1'),
	('SA000986','HSR000253','SDA000505','节假日是否到过武汉0986','B','1'),
	('SA000987','HSR000253','SDA000505','节假日是否到过武汉0987','A','1'),
	('SA000988','HSR000253','SDA000506','节假日是否到过武汉0988','B','1'),
	('SA000989','HSR000253','SDA000506','节假日是否到过武汉0989','A','1'),
	('SA000990','HSR000254','SDA000507','节假日是否到过武汉0990','B','1'),
	('SA000991','HSR000254','SDA000507','节假日是否到过武汉0991','A','1'),
	('SA000992','HSR000254','SDA000508','节假日是否到过武汉0992','B','1'),
	('SA000993','HSR000254','SDA000508','节假日是否到过武汉0993','A','1'),
	('SA000994','HSR000255','SDA000509','节假日是否到过武汉0994','B','1'),
	('SA000995','HSR000255','SDA000509','节假日是否到过武汉0995','A','1'),
	('SA000996','HSR000255','SDA000510','节假日是否到过武汉0996','B','1'),
	('SA000997','HSR000255','SDA000510','节假日是否到过武汉0997','A','1'),
	('SA000998','HSR000256','SDA000511','节假日是否到过武汉0998','B','1'),
	('SA000999','HSR000256','SDA000511','节假日是否到过武汉0999','A','1'),
	('SA001000','HSR000256','SDA000512','节假日是否到过武汉1000','B','1');

insert into user_data values
	('U000001','张三','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000001','2020-01-31 23:31:48','P000001','1'),
	('U000002','李四','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000001','2020-01-17 14:01:58','P000001','1'),
	('U000003','王五','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000002','2020-01-18 16:04:00','P000001','1'),
	('U000004','张三','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000002','2020-01-30 11:01:35','P000001','1'),
	('U000005','李四','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000003','2020-01-13 06:52:22','P000001','1'),
	('U000006','王五','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000003','2020-02-03 02:46:43','P000001','1'),
	('U000007','张三','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000004','2020-01-20 11:53:19','P000001','1'),
	('U000008','李四','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000004','2020-01-24 18:29:07','P000001','1'),
	('U000009','王五','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000005','2020-01-17 05:56:03','P000001','1'),
	('U000010','张三','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000005','2020-01-22 09:10:46','P000001','1'),
	('U000011','李四','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000006','2020-01-22 08:51:13','P000001','1'),
	('U000012','王五','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000006','2020-01-28 01:14:46','P000001','1'),
	('U000013','张三','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000007','2020-02-02 20:08:11','P000001','1'),
	('U000014','李四','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000007','2020-01-31 09:47:40','P000001','1'),
	('U000015','王五','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000008','2020-01-31 08:18:08','P000001','1'),
	('U000016','张三','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000008','2020-01-20 05:35:11','P000001','1'),
	('U000017','李四','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000009','2020-01-28 06:33:29','P000001','1'),
	('U000018','王五','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000009','2020-01-19 05:21:26','P000001','1'),
	('U000019','张三','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000010','2020-01-23 14:38:59','P000001','1'),
	('U000020','李四','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000010','2020-01-16 02:17:16','P000001','1'),
	('U000021','王五','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000011','2020-01-22 03:47:55','P000001','1'),
	('U000022','张三','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000011','2020-01-25 13:05:41','P000001','1'),
	('U000023','李四','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000012','2020-01-16 04:57:19','P000001','1'),
	('U000024','王五','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000012','2020-01-31 16:33:45','P000001','1'),
	('U000025','张三','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000013','2020-01-24 01:33:07','P000001','1'),
	('U000026','李四','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000013','2020-01-18 09:51:48','P000001','1'),
	('U000027','王五','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000014','2020-02-03 05:50:38','P000001','1'),
	('U000028','张三','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000014','2020-02-01 21:17:09','P000001','1'),
	('U000029','李四','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000015','2020-02-02 07:35:13','P000001','1'),
	('U000030','王五','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000015','2020-01-19 19:40:48','P000001','1'),
	('U000031','张三','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000016','2020-01-20 10:51:10','P000001','1'),
	('U000032','李四','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000016','2020-01-30 18:54:14','P000001','1');

insert into wechat_login_info_data values
	('WLI000001','U000001','user123','user123','session123','2020-02-01 23:08:37','1'),
	('WLI000002','U000001','user1230002','user1230002','session1230002','2020-01-30 07:50:44','1'),
	('WLI000003','U000002','user1230003','user1230003','session1230003','2020-01-13 15:26:23','1'),
	('WLI000004','U000002','user1230004','user1230004','session1230004','2020-01-15 18:10:34','1'),
	('WLI000005','U000003','user1230005','user1230005','session1230005','2020-01-15 23:48:14','1'),
	('WLI000006','U000003','user1230006','user1230006','session1230006','2020-01-30 09:00:31','1'),
	('WLI000007','U000004','user1230007','user1230007','session1230007','2020-01-28 14:12:13','1'),
	('WLI000008','U000004','user1230008','user1230008','session1230008','2020-01-14 10:25:15','1'),
	('WLI000009','U000005','user1230009','user1230009','session1230009','2020-01-17 17:15:28','1'),
	('WLI000010','U000005','user1230010','user1230010','session1230010','2020-01-24 09:23:26','1'),
	('WLI000011','U000006','user1230011','user1230011','session1230011','2020-01-24 08:05:28','1'),
	('WLI000012','U000006','user1230012','user1230012','session1230012','2020-01-22 06:09:51','1'),
	('WLI000013','U000007','user1230013','user1230013','session1230013','2020-02-01 08:52:25','1'),
	('WLI000014','U000007','user1230014','user1230014','session1230014','2020-01-26 03:59:09','1'),
	('WLI000015','U000008','user1230015','user1230015','session1230015','2020-01-19 03:43:22','1'),
	('WLI000016','U000008','user1230016','user1230016','session1230016','2020-02-01 02:15:30','1'),
	('WLI000017','U000009','user1230017','user1230017','session1230017','2020-02-01 16:12:18','1'),
	('WLI000018','U000009','user1230018','user1230018','session1230018','2020-01-17 02:40:53','1'),
	('WLI000019','U000010','user1230019','user1230019','session1230019','2020-01-24 22:02:39','1'),
	('WLI000020','U000010','user1230020','user1230020','session1230020','2020-01-29 18:48:50','1'),
	('WLI000021','U000011','user1230021','user1230021','session1230021','2020-01-15 04:06:16','1'),
	('WLI000022','U000011','user1230022','user1230022','session1230022','2020-01-23 12:52:49','1'),
	('WLI000023','U000012','user1230023','user1230023','session1230023','2020-01-16 17:18:56','1'),
	('WLI000024','U000012','user1230024','user1230024','session1230024','2020-01-17 13:54:32','1'),
	('WLI000025','U000013','user1230025','user1230025','session1230025','2020-01-24 01:22:38','1'),
	('WLI000026','U000013','user1230026','user1230026','session1230026','2020-01-29 13:59:24','1'),
	('WLI000027','U000014','user1230027','user1230027','session1230027','2020-01-26 19:11:03','1'),
	('WLI000028','U000014','user1230028','user1230028','session1230028','2020-01-24 11:09:52','1'),
	('WLI000029','U000015','user1230029','user1230029','session1230029','2020-01-25 03:54:26','1'),
	('WLI000030','U000015','user1230030','user1230030','session1230030','2020-01-23 18:38:04','1'),
	('WLI000031','U000016','user1230031','user1230031','session1230031','2020-01-27 05:25:54','1'),
	('WLI000032','U000016','user1230032','user1230032','session1230032','2020-01-18 10:32:31','1'),
	('WLI000033','U000017','user1230033','user1230033','session1230033','2020-01-31 08:41:49','1'),
	('WLI000034','U000017','user1230034','user1230034','session1230034','2020-02-01 13:52:11','1'),
	('WLI000035','U000018','user1230035','user1230035','session1230035','2020-02-01 19:46:21','1'),
	('WLI000036','U000018','user1230036','user1230036','session1230036','2020-01-22 11:18:00','1'),
	('WLI000037','U000019','user1230037','user1230037','session1230037','2020-01-28 20:42:28','1'),
	('WLI000038','U000019','user1230038','user1230038','session1230038','2020-01-13 01:49:53','1'),
	('WLI000039','U000020','user1230039','user1230039','session1230039','2020-02-01 20:34:57','1'),
	('WLI000040','U000020','user1230040','user1230040','session1230040','2020-01-19 21:57:46','1'),
	('WLI000041','U000021','user1230041','user1230041','session1230041','2020-01-20 08:57:18','1'),
	('WLI000042','U000021','user1230042','user1230042','session1230042','2020-01-24 17:54:54','1'),
	('WLI000043','U000022','user1230043','user1230043','session1230043','2020-01-13 10:39:52','1'),
	('WLI000044','U000022','user1230044','user1230044','session1230044','2020-01-22 19:46:44','1'),
	('WLI000045','U000023','user1230045','user1230045','session1230045','2020-01-17 10:23:48','1'),
	('WLI000046','U000023','user1230046','user1230046','session1230046','2020-01-30 18:59:15','1'),
	('WLI000047','U000024','user1230047','user1230047','session1230047','2020-01-31 05:30:38','1'),
	('WLI000048','U000024','user1230048','user1230048','session1230048','2020-02-02 05:07:13','1'),
	('WLI000049','U000025','user1230049','user1230049','session1230049','2020-01-29 04:10:54','1'),
	('WLI000050','U000025','user1230050','user1230050','session1230050','2020-01-22 14:39:07','1'),
	('WLI000051','U000026','user1230051','user1230051','session1230051','2020-01-28 08:00:12','1'),
	('WLI000052','U000026','user1230052','user1230052','session1230052','2020-01-27 15:20:47','1'),
	('WLI000053','U000027','user1230053','user1230053','session1230053','2020-01-23 00:45:17','1'),
	('WLI000054','U000027','user1230054','user1230054','session1230054','2020-02-01 16:26:01','1'),
	('WLI000055','U000028','user1230055','user1230055','session1230055','2020-01-21 09:30:03','1'),
	('WLI000056','U000028','user1230056','user1230056','session1230056','2020-01-28 07:28:33','1'),
	('WLI000057','U000029','user1230057','user1230057','session1230057','2020-01-21 22:11:15','1'),
	('WLI000058','U000029','user1230058','user1230058','session1230058','2020-01-31 05:19:40','1'),
	('WLI000059','U000030','user1230059','user1230059','session1230059','2020-01-15 11:34:27','1'),
	('WLI000060','U000030','user1230060','user1230060','session1230060','2020-01-30 07:08:22','1'),
	('WLI000061','U000031','user1230061','user1230061','session1230061','2020-02-01 08:41:47','1'),
	('WLI000062','U000031','user1230062','user1230062','session1230062','2020-01-25 21:51:09','1'),
	('WLI000063','U000032','user1230063','user1230063','session1230063','2020-01-19 22:31:44','1'),
	('WLI000064','U000032','user1230064','user1230064','session1230064','2020-01-19 16:44:58','1');

insert into change_request_data values
	('CR000001','答题','2020-02-02 05:13:10','8.8.8.8','ADD_CLASS','P000001','1'),
	('CR000002','答题0002','2020-01-26 00:33:43','8.8.8.8','ADD_STUDENT','P000001','1'),
	('CR000003','答题0003','2020-01-23 14:36:53','8.8.8.8','PUBLISH_SURVEY','P000001','1'),
	('CR000004','答题0004','2020-01-30 20:12:16','8.8.8.8','FILL_SURVEY','P000001','1');

insert into change_request_type_data values
	('ADD_CLASS','添加班级','ADD_CLASS','book','1','    一段样例文字。    一段样例文字。\n可以分段。可以分段。\n\n可以空行。可以空行。\n\n','    一段样例文字。    一段样例文字。\n可以分段。可以分段。\n\n可以空行。可以空行。\n\n','P000001','1'),
	('ADD_STUDENT','添加学生','ADD_STUDENT','add','2','    一段样例文字。    一段样例文字。\n可以分段。可以分段。\n\n可以空行。可以空行。\n\n','    一段样例文字。    一段样例文字。\n可以分段。可以分段。\n\n可以空行。可以空行。\n\n','P000001','1'),
	('PUBLISH_SURVEY','发布问卷','PUBLISH_SURVEY','add','3','    一段样例文字。    一段样例文字。\n可以分段。可以分段。\n\n可以空行。可以空行。\n\n','    一段样例文字。    一段样例文字。\n可以分段。可以分段。\n\n可以空行。可以空行。\n\n','P000001','1'),
	('FILL_SURVEY','填写问卷','FILL_SURVEY','swap','4','    一段样例文字。    一段样例文字。\n可以分段。可以分段。\n\n可以空行。可以空行。\n\n','    一段样例文字。    一段样例文字。\n可以分段。可以分段。\n\n可以空行。可以空行。\n\n','P000001','1');

insert into wechat_workapp_identify_data values
	('WWI000001','corporation123','user123','SU000001','2020-01-21 15:34:22','2020-01-16 06:08:12','1'),
	('WWI000002','corporation1230002','user1230002','SU000001','2020-01-20 15:31:51','2020-01-27 14:45:42','1'),
	('WWI000003','corporation1230003','user1230003','SU000002','2020-01-30 15:16:08','2020-01-25 17:25:38','1'),
	('WWI000004','corporation1230004','user1230004','SU000002','2020-01-26 16:16:12','2020-02-02 20:32:25','1');

insert into wechat_miniapp_identify_data values
	('WMI000001','wechat_open_id_1234567890','wechat_miniapp_id_1234567890','SU000001','2020-01-14 05:10:12','2020-01-14 10:50:01','1'),
	('WMI000002','wechat_open_id_12345678900002','wechat_miniapp_id_12345678900002','SU000001','2020-01-13 20:53:22','2020-01-15 03:40:58','1'),
	('WMI000003','wechat_open_id_12345678900003','wechat_miniapp_id_12345678900003','SU000002','2020-01-23 13:58:22','2020-01-12 23:55:26','1'),
	('WMI000004','wechat_open_id_12345678900004','wechat_miniapp_id_12345678900004','SU000002','2020-01-19 16:52:55','2020-02-02 08:29:01','1');







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
insert into user_app_data values('UA000004','用户: 张三','SU000002','store',1,'MXWR','User','U000001','/link/to/app','1');
insert into user_app_data values('UA000005','我的账户','SU000002','lock',1,'MXWR','SecUser','SU000002','/link/to/app','1');
insert into sec_user_data values('SU000003','User000003','13900000003','1000003@qq.com','9D4104DF2774FDEAAE074CA35B052D8F664F4F99064C7BEAB0B589C2605C4EDA', 'weixin_openid_000003', 'weixin_appid_000003', 'jwt_token_000003' ,'9292993','2019-09-09 09:09:09','2019-09-09 09:09:09','UD000001',1);
insert into user_app_data values('UA000006','用户: 李四','SU000003','store',1,'MXWR','User','U000002','/link/to/app','1');
insert into user_app_data values('UA000007','我的账户','SU000003','lock',1,'MXWR','SecUser','SU000003','/link/to/app','1');
insert into sec_user_data values('SU000004','User000004','13900000004','1000004@qq.com','9B223EBD008D7B544A3A640739EBE47459D3A4C5296DDA00F594FAF60FE88B28', 'weixin_openid_000004', 'weixin_appid_000004', 'jwt_token_000004' ,'9292993','2019-09-09 09:09:09','2019-09-09 09:09:09','UD000001',1);
insert into user_app_data values('UA000008','用户: 王五','SU000004','store',1,'MXWR','User','U000003','/link/to/app','1');
insert into user_app_data values('UA000009','我的账户','SU000004','lock',1,'MXWR','SecUser','SU000004','/link/to/app','1');
insert into sec_user_data values('SU000005','User000005','13900000005','1000005@qq.com','AE5F93F319636A96963C06D035B97F004D18E61D80129EFEA331784A6E21DC5C', 'weixin_openid_000005', 'weixin_appid_000005', 'jwt_token_000005' ,'9292993','2019-09-09 09:09:09','2019-09-09 09:09:09','UD000001',1);
insert into user_app_data values('UA000010','用户: 张三','SU000005','store',1,'MXWR','User','U000004','/link/to/app','1');
insert into user_app_data values('UA000011','我的账户','SU000005','lock',1,'MXWR','SecUser','SU000005','/link/to/app','1');
insert into sec_user_data values('SU000006','User000006','13900000006','1000006@qq.com','5FBBDBEAD9F84D599E8819CEEA167854CDA0FFD8D297D17D12E4619CE76F3B55', 'weixin_openid_000006', 'weixin_appid_000006', 'jwt_token_000006' ,'9292993','2019-09-09 09:09:09','2019-09-09 09:09:09','UD000001',1);
insert into user_app_data values('UA000012','用户: 李四','SU000006','store',1,'MXWR','User','U000005','/link/to/app','1');
insert into user_app_data values('UA000013','我的账户','SU000006','lock',1,'MXWR','SecUser','SU000006','/link/to/app','1');
insert into sec_user_data values('SU000007','User000007','13900000007','1000007@qq.com','A9652F0D7C1ACCB421BAF55EB3E7286AFA8F591897F1AE4CEB6A76402CCBE803', 'weixin_openid_000007', 'weixin_appid_000007', 'jwt_token_000007' ,'9292993','2019-09-09 09:09:09','2019-09-09 09:09:09','UD000001',1);
insert into user_app_data values('UA000014','用户: 王五','SU000007','store',1,'MXWR','User','U000006','/link/to/app','1');
insert into user_app_data values('UA000015','我的账户','SU000007','lock',1,'MXWR','SecUser','SU000007','/link/to/app','1');
insert into sec_user_data values('SU000008','User000008','13900000008','1000008@qq.com','A4B83C2652CD6BECE5C7909576555B313078D7EE50AA028F26B8F0245C191B4B', 'weixin_openid_000008', 'weixin_appid_000008', 'jwt_token_000008' ,'9292993','2019-09-09 09:09:09','2019-09-09 09:09:09','UD000001',1);
insert into user_app_data values('UA000016','用户: 张三','SU000008','store',1,'MXWR','User','U000007','/link/to/app','1');
insert into user_app_data values('UA000017','我的账户','SU000008','lock',1,'MXWR','SecUser','SU000008','/link/to/app','1');
insert into sec_user_data values('SU000009','User000009','13900000009','1000009@qq.com','88F8AB5F153081C5AB21F5E5354B4EB14286EFB43CEA588ED1C73FE2B46B35C1', 'weixin_openid_000009', 'weixin_appid_000009', 'jwt_token_000009' ,'9292993','2019-09-09 09:09:09','2019-09-09 09:09:09','UD000001',1);
insert into user_app_data values('UA000018','用户: 李四','SU000009','store',1,'MXWR','User','U000008','/link/to/app','1');
insert into user_app_data values('UA000019','我的账户','SU000009','lock',1,'MXWR','SecUser','SU000009','/link/to/app','1');
insert into sec_user_data values('SU000010','User000010','13900000010','1000010@qq.com','EF8232ABB97CC3858F271527A1AA1452A33715A3AC48312A44B0940D5C948600', 'weixin_openid_000010', 'weixin_appid_000010', 'jwt_token_000010' ,'9292993','2019-09-09 09:09:09','2019-09-09 09:09:09','UD000001',1);
insert into user_app_data values('UA000020','用户: 王五','SU000010','store',1,'MXWR','User','U000009','/link/to/app','1');
insert into user_app_data values('UA000021','我的账户','SU000010','lock',1,'MXWR','SecUser','SU000010','/link/to/app','1');
insert into sec_user_data values('SU000011','User000011','13900000011','1000011@qq.com','FE7AF5D4F030CD575C117A73124FC39AB41528DFFC41D2CFBC1130E755694243', 'weixin_openid_000011', 'weixin_appid_000011', 'jwt_token_000011' ,'9292993','2019-09-09 09:09:09','2019-09-09 09:09:09','UD000001',1);
insert into user_app_data values('UA000022','用户: 张三','SU000011','store',1,'MXWR','User','U000010','/link/to/app','1');
insert into user_app_data values('UA000023','我的账户','SU000011','lock',1,'MXWR','SecUser','SU000011','/link/to/app','1');
insert into sec_user_data values('SU000012','User000012','13900000012','1000012@qq.com','999DD89E35807C62458F2D191D4F55548B49245EEC6E186FE9497EC867C40088', 'weixin_openid_000012', 'weixin_appid_000012', 'jwt_token_000012' ,'9292993','2019-09-09 09:09:09','2019-09-09 09:09:09','UD000001',1);
insert into user_app_data values('UA000024','用户: 李四','SU000012','store',1,'MXWR','User','U000011','/link/to/app','1');
insert into user_app_data values('UA000025','我的账户','SU000012','lock',1,'MXWR','SecUser','SU000012','/link/to/app','1');
insert into sec_user_data values('SU000013','User000013','13900000013','1000013@qq.com','0AE92E17166CBB59341836C218E92EF083058CC4E3108C5FD2FB904650013A69', 'weixin_openid_000013', 'weixin_appid_000013', 'jwt_token_000013' ,'9292993','2019-09-09 09:09:09','2019-09-09 09:09:09','UD000001',1);
insert into user_app_data values('UA000026','用户: 王五','SU000013','store',1,'MXWR','User','U000012','/link/to/app','1');
insert into user_app_data values('UA000027','我的账户','SU000013','lock',1,'MXWR','SecUser','SU000013','/link/to/app','1');
insert into sec_user_data values('SU000014','User000014','13900000014','1000014@qq.com','E79E64241204EB0FCE03C4BA0E315F21ECDB11D22264BE7B1AAD41D04D77A6D0', 'weixin_openid_000014', 'weixin_appid_000014', 'jwt_token_000014' ,'9292993','2019-09-09 09:09:09','2019-09-09 09:09:09','UD000001',1);
insert into user_app_data values('UA000028','用户: 张三','SU000014','store',1,'MXWR','User','U000013','/link/to/app','1');
insert into user_app_data values('UA000029','我的账户','SU000014','lock',1,'MXWR','SecUser','SU000014','/link/to/app','1');
insert into sec_user_data values('SU000015','User000015','13900000015','1000015@qq.com','1D858671B95062DAFE1D989C089188CC4EFDF3D5C45D8F24DD20BF3E352A3D9B', 'weixin_openid_000015', 'weixin_appid_000015', 'jwt_token_000015' ,'9292993','2019-09-09 09:09:09','2019-09-09 09:09:09','UD000001',1);
insert into user_app_data values('UA000030','用户: 李四','SU000015','store',1,'MXWR','User','U000014','/link/to/app','1');
insert into user_app_data values('UA000031','我的账户','SU000015','lock',1,'MXWR','SecUser','SU000015','/link/to/app','1');
insert into sec_user_data values('SU000016','User000016','13900000016','1000016@qq.com','14B1F5E667F8B6697C8A2952C3619D9AD82F846E5B32FD9F258918786B3ED519', 'weixin_openid_000016', 'weixin_appid_000016', 'jwt_token_000016' ,'9292993','2019-09-09 09:09:09','2019-09-09 09:09:09','UD000001',1);
insert into user_app_data values('UA000032','用户: 王五','SU000016','store',1,'MXWR','User','U000015','/link/to/app','1');
insert into user_app_data values('UA000033','我的账户','SU000016','lock',1,'MXWR','SecUser','SU000016','/link/to/app','1');
insert into sec_user_data values('SU000017','User000017','13900000017','1000017@qq.com','1A803C7096681FC2AA7C55C46A6A99D8089481B96997774EA5B1C785C8035010', 'weixin_openid_000017', 'weixin_appid_000017', 'jwt_token_000017' ,'9292993','2019-09-09 09:09:09','2019-09-09 09:09:09','UD000001',1);
insert into user_app_data values('UA000034','用户: 张三','SU000017','store',1,'MXWR','User','U000016','/link/to/app','1');
insert into user_app_data values('UA000035','我的账户','SU000017','lock',1,'MXWR','SecUser','SU000017','/link/to/app','1');
insert into sec_user_data values('SU000018','User000018','13900000018','1000018@qq.com','FA485AC06A6BD6BBF7AC9F253FCC516227CB232598792232277A70386FD892ED', 'weixin_openid_000018', 'weixin_appid_000018', 'jwt_token_000018' ,'9292993','2019-09-09 09:09:09','2019-09-09 09:09:09','UD000001',1);
insert into user_app_data values('UA000036','用户: 李四','SU000018','store',1,'MXWR','User','U000017','/link/to/app','1');
insert into user_app_data values('UA000037','我的账户','SU000018','lock',1,'MXWR','SecUser','SU000018','/link/to/app','1');
insert into sec_user_data values('SU000019','User000019','13900000019','1000019@qq.com','A5D9532EB6FC76A7D06764C14F751A4AFBC7C5BC49C215272A2EE42BBEA1A502', 'weixin_openid_000019', 'weixin_appid_000019', 'jwt_token_000019' ,'9292993','2019-09-09 09:09:09','2019-09-09 09:09:09','UD000001',1);
insert into user_app_data values('UA000038','用户: 王五','SU000019','store',1,'MXWR','User','U000018','/link/to/app','1');
insert into user_app_data values('UA000039','我的账户','SU000019','lock',1,'MXWR','SecUser','SU000019','/link/to/app','1');
insert into sec_user_data values('SU000020','User000020','13900000020','1000020@qq.com','7CB0B35123A314B427FC1459C4083AA314D8F9E2505BB9187594B223BE5623A0', 'weixin_openid_000020', 'weixin_appid_000020', 'jwt_token_000020' ,'9292993','2019-09-09 09:09:09','2019-09-09 09:09:09','UD000001',1);
insert into user_app_data values('UA000040','用户: 张三','SU000020','store',1,'MXWR','User','U000019','/link/to/app','1');
insert into user_app_data values('UA000041','我的账户','SU000020','lock',1,'MXWR','SecUser','SU000020','/link/to/app','1');
insert into sec_user_data values('SU000021','User000021','13900000021','1000021@qq.com','C21B3A395B3E337A4D06491AEC7B485523BB4E5790DE925000FECEC237F939F2', 'weixin_openid_000021', 'weixin_appid_000021', 'jwt_token_000021' ,'9292993','2019-09-09 09:09:09','2019-09-09 09:09:09','UD000001',1);
insert into user_app_data values('UA000042','用户: 李四','SU000021','store',1,'MXWR','User','U000020','/link/to/app','1');
insert into user_app_data values('UA000043','我的账户','SU000021','lock',1,'MXWR','SecUser','SU000021','/link/to/app','1');
insert into sec_user_data values('SU000022','User000022','13900000022','1000022@qq.com','D6C0743E4B79BE93E8BDB4D0B55054EC3532F6B1AF8F69EDD542F0D22DD228C9', 'weixin_openid_000022', 'weixin_appid_000022', 'jwt_token_000022' ,'9292993','2019-09-09 09:09:09','2019-09-09 09:09:09','UD000001',1);
insert into user_app_data values('UA000044','用户: 王五','SU000022','store',1,'MXWR','User','U000021','/link/to/app','1');
insert into user_app_data values('UA000045','我的账户','SU000022','lock',1,'MXWR','SecUser','SU000022','/link/to/app','1');
insert into sec_user_data values('SU000023','User000023','13900000023','1000023@qq.com','D5405F91AA444B65AE234F0AA39FF8A43A2F0CF28F238479A0AC08D9C292629E', 'weixin_openid_000023', 'weixin_appid_000023', 'jwt_token_000023' ,'9292993','2019-09-09 09:09:09','2019-09-09 09:09:09','UD000001',1);
insert into user_app_data values('UA000046','用户: 张三','SU000023','store',1,'MXWR','User','U000022','/link/to/app','1');
insert into user_app_data values('UA000047','我的账户','SU000023','lock',1,'MXWR','SecUser','SU000023','/link/to/app','1');
insert into sec_user_data values('SU000024','User000024','13900000024','1000024@qq.com','663EE204DCB9B63399177CA2CF9E0206E286B7ECBF8E9A9874F50A9A863E9B02', 'weixin_openid_000024', 'weixin_appid_000024', 'jwt_token_000024' ,'9292993','2019-09-09 09:09:09','2019-09-09 09:09:09','UD000001',1);
insert into user_app_data values('UA000048','用户: 李四','SU000024','store',1,'MXWR','User','U000023','/link/to/app','1');
insert into user_app_data values('UA000049','我的账户','SU000024','lock',1,'MXWR','SecUser','SU000024','/link/to/app','1');
insert into sec_user_data values('SU000025','User000025','13900000025','1000025@qq.com','E1D441F2F9DA5C7456A3D6F32097D0C29DEFF3FFCAB5CE40927FC12208CDABE0', 'weixin_openid_000025', 'weixin_appid_000025', 'jwt_token_000025' ,'9292993','2019-09-09 09:09:09','2019-09-09 09:09:09','UD000001',1);
insert into user_app_data values('UA000050','用户: 王五','SU000025','store',1,'MXWR','User','U000024','/link/to/app','1');
insert into user_app_data values('UA000051','我的账户','SU000025','lock',1,'MXWR','SecUser','SU000025','/link/to/app','1');
insert into sec_user_data values('SU000026','User000026','13900000026','1000026@qq.com','21139DC63E2442E86BEE2231680F6F51E2ABC6C8A89190FD6565CAC0EAE1F4D7', 'weixin_openid_000026', 'weixin_appid_000026', 'jwt_token_000026' ,'9292993','2019-09-09 09:09:09','2019-09-09 09:09:09','UD000001',1);
insert into user_app_data values('UA000052','用户: 张三','SU000026','store',1,'MXWR','User','U000025','/link/to/app','1');
insert into user_app_data values('UA000053','我的账户','SU000026','lock',1,'MXWR','SecUser','SU000026','/link/to/app','1');
insert into sec_user_data values('SU000027','User000027','13900000027','1000027@qq.com','E18E06C2B1A0C33B7445B28A6056263F174B1F7B471819FB2C047BB26F009897', 'weixin_openid_000027', 'weixin_appid_000027', 'jwt_token_000027' ,'9292993','2019-09-09 09:09:09','2019-09-09 09:09:09','UD000001',1);
insert into user_app_data values('UA000054','用户: 李四','SU000027','store',1,'MXWR','User','U000026','/link/to/app','1');
insert into user_app_data values('UA000055','我的账户','SU000027','lock',1,'MXWR','SecUser','SU000027','/link/to/app','1');
insert into sec_user_data values('SU000028','User000028','13900000028','1000028@qq.com','CAF3CA28D098FF0C861BBA7A78B373A7468FE1F5581C2540B399D98A77A3C4F6', 'weixin_openid_000028', 'weixin_appid_000028', 'jwt_token_000028' ,'9292993','2019-09-09 09:09:09','2019-09-09 09:09:09','UD000001',1);
insert into user_app_data values('UA000056','用户: 王五','SU000028','store',1,'MXWR','User','U000027','/link/to/app','1');
insert into user_app_data values('UA000057','我的账户','SU000028','lock',1,'MXWR','SecUser','SU000028','/link/to/app','1');
insert into sec_user_data values('SU000029','User000029','13900000029','1000029@qq.com','EA698F00DC96ED60831718D28FD8570EAEDA9E8A806CB645DA2EAB2854F0A88E', 'weixin_openid_000029', 'weixin_appid_000029', 'jwt_token_000029' ,'9292993','2019-09-09 09:09:09','2019-09-09 09:09:09','UD000001',1);
insert into user_app_data values('UA000058','用户: 张三','SU000029','store',1,'MXWR','User','U000028','/link/to/app','1');
insert into user_app_data values('UA000059','我的账户','SU000029','lock',1,'MXWR','SecUser','SU000029','/link/to/app','1');
insert into sec_user_data values('SU000030','User000030','13900000030','1000030@qq.com','2AD02AB382A1850784152BBBF63FDE2F80504FCBD4FF76E314FA11F64F35B682', 'weixin_openid_000030', 'weixin_appid_000030', 'jwt_token_000030' ,'9292993','2019-09-09 09:09:09','2019-09-09 09:09:09','UD000001',1);
insert into user_app_data values('UA000060','用户: 李四','SU000030','store',1,'MXWR','User','U000029','/link/to/app','1');
insert into user_app_data values('UA000061','我的账户','SU000030','lock',1,'MXWR','SecUser','SU000030','/link/to/app','1');
insert into sec_user_data values('SU000031','User000031','13900000031','1000031@qq.com','8F7AA9BA1C0A231BCF9BA1D48B091E21D630F744E05E89AD25E4C9FE9B2FF921', 'weixin_openid_000031', 'weixin_appid_000031', 'jwt_token_000031' ,'9292993','2019-09-09 09:09:09','2019-09-09 09:09:09','UD000001',1);
insert into user_app_data values('UA000062','用户: 王五','SU000031','store',1,'MXWR','User','U000030','/link/to/app','1');
insert into user_app_data values('UA000063','我的账户','SU000031','lock',1,'MXWR','SecUser','SU000031','/link/to/app','1');
insert into sec_user_data values('SU000032','User000032','13900000032','1000032@qq.com','F801F74132C5952B0086E9EAB138914F70B6B08B885EFC250590D61A61C06886', 'weixin_openid_000032', 'weixin_appid_000032', 'jwt_token_000032' ,'9292993','2019-09-09 09:09:09','2019-09-09 09:09:09','UD000001',1);
insert into user_app_data values('UA000064','用户: 张三','SU000032','store',1,'MXWR','User','U000031','/link/to/app','1');
insert into user_app_data values('UA000065','我的账户','SU000032','lock',1,'MXWR','SecUser','SU000032','/link/to/app','1');
insert into sec_user_data values('SU000033','User000033','13900000033','1000033@qq.com','C51F55D220A7181AD92382E935A9DC87592C1F769B727E655DE54E42D00DCB61', 'weixin_openid_000033', 'weixin_appid_000033', 'jwt_token_000033' ,'9292993','2019-09-09 09:09:09','2019-09-09 09:09:09','UD000001',1);
insert into user_app_data values('UA000066','用户: 李四','SU000033','store',1,'MXWR','User','U000032','/link/to/app','1');
insert into user_app_data values('UA000067','我的账户','SU000033','lock',1,'MXWR','SecUser','SU000033','/link/to/app','1');


insert into change_request_type_data values
('ANSWER_SURVEY','ANSWER_SURVEY','ANSWER_SURVEY','user','5','Platform','{}','P000001',1),
('REGISTER_TEACHER','REGISTER_TEACHER','REGISTER_TEACHER','user','6','Platform','{}','P000001',1),
('CREATE_SURVEY','CREATE_SURVEY','CREATE_SURVEY','user','7','Platform','{}','P000001',1),
('ANSWER_QUESTION','ANSWER_QUESTION','ANSWER_QUESTION','user','8','Platform','{}','P000001',1),
('CREATE_QUESTION','CREATE_QUESTION','CREATE_QUESTION','user','9','Platform','{}','P000001',1),
('REGISTER_STUDENT','REGISTER_STUDENT','REGISTER_STUDENT','user','10','Platform','{}','P000001',1);

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

create unique index idx4id_ver_of_teacher on teacher_data (id, version);
create  index idx4mobile_of_teacher on teacher_data (mobile);
create  index idx4class_size_of_teacher on teacher_data (class_size);
create  index idx4create_time_of_teacher on teacher_data (create_time);

create unique index idx4id_ver_of_student on student_data (id, version);
create  index idx4guardian_mobile_of_student on student_data (guardian_mobile);
create  index idx4create_time_of_student on student_data (create_time);

create unique index idx4id_ver_of_question on question_data (id, version);

create unique index idx4id_ver_of_question_type on question_type_data (id, version);
create unique index idx4code_of_question_type on question_type_data (code);

create unique index idx4id_ver_of_class_daily_health_survey on class_daily_health_survey_data (id, version);
create  index idx4survey_time_of_class_daily_health_survey on class_daily_health_survey_data (survey_time);

create unique index idx4id_ver_of_daily_survey_question on daily_survey_question_data (id, version);

create unique index idx4id_ver_of_student_health_survey on student_health_survey_data (id, version);
create  index idx4answer_time_of_student_health_survey on student_health_survey_data (answer_time);
create  index idx4create_time_of_student_health_survey on student_health_survey_data (create_time);
create  index idx4last_update_time_of_student_health_survey on student_health_survey_data (last_update_time);

create unique index idx4id_ver_of_student_daily_answer on student_daily_answer_data (id, version);
create  index idx4create_time_of_student_daily_answer on student_daily_answer_data (create_time);
create  index idx4last_update_time_of_student_daily_answer on student_daily_answer_data (last_update_time);

create unique index idx4id_ver_of_survey_status on survey_status_data (id, version);
create unique index idx4code_of_survey_status on survey_status_data (code);

create unique index idx4id_ver_of_health_survey_report on health_survey_report_data (id, version);
create  index idx4survey_time_of_health_survey_report on health_survey_report_data (survey_time);
create  index idx4guardian_mobile_of_health_survey_report on health_survey_report_data (guardian_mobile);

create unique index idx4id_ver_of_student_answer on student_answer_data (id, version);

create unique index idx4id_ver_of_user on user_data (id, version);
create  index idx4create_time_of_user on user_data (create_time);

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

alter table teacher_data add constraint pk4id_of_teacher_data primary key (id);
alter table teacher_data add constraint 
	fk4platform_of_teacher_data foreign key (platform) references platform_data(id) ON DELETE CASCADE ON UPDATE CASCADE;
alter table teacher_data add constraint 
	fk4user_of_teacher_data foreign key (user) references user_data(id) ON DELETE CASCADE ON UPDATE CASCADE;
alter table teacher_data add constraint 
	fk4change_request_of_teacher_data foreign key (change_request) references change_request_data(id) ON DELETE CASCADE ON UPDATE CASCADE;

alter table student_data add constraint pk4id_of_student_data primary key (id);
alter table student_data add constraint 
	fk4address_of_student_data foreign key (address) references location_data(id) ON DELETE CASCADE ON UPDATE CASCADE;
alter table student_data add constraint 
	fk4user_of_student_data foreign key (user) references user_data(id) ON DELETE CASCADE ON UPDATE CASCADE;
alter table student_data add constraint 
	fk4platform_of_student_data foreign key (platform) references platform_data(id) ON DELETE CASCADE ON UPDATE CASCADE;
alter table student_data add constraint 
	fk4change_request_of_student_data foreign key (change_request) references change_request_data(id) ON DELETE CASCADE ON UPDATE CASCADE;

alter table question_data add constraint pk4id_of_question_data primary key (id);
alter table question_data add constraint 
	fk4question_type_of_question_data foreign key (question_type) references question_type_data(id) ON DELETE CASCADE ON UPDATE CASCADE;
alter table question_data add constraint 
	fk4platform_of_question_data foreign key (platform) references platform_data(id) ON DELETE CASCADE ON UPDATE CASCADE;
alter table question_data add constraint 
	fk4creator_of_question_data foreign key (creator) references user_data(id) ON DELETE CASCADE ON UPDATE CASCADE;
alter table question_data add constraint 
	fk4cq_of_question_data foreign key (cq) references change_request_data(id) ON DELETE CASCADE ON UPDATE CASCADE;

alter table question_type_data add constraint pk4id_of_question_type_data primary key (id);
alter table question_type_data add constraint 
	fk4platform_of_question_type_data foreign key (platform) references platform_data(id) ON DELETE CASCADE ON UPDATE CASCADE;

alter table class_daily_health_survey_data add constraint pk4id_of_class_daily_health_survey_data primary key (id);
alter table class_daily_health_survey_data add constraint 
	fk4teacher_of_class_daily_health_survey_data foreign key (teacher) references teacher_data(id) ON DELETE CASCADE ON UPDATE CASCADE;
alter table class_daily_health_survey_data add constraint 
	fk4creator_of_class_daily_health_survey_data foreign key (creator) references user_data(id) ON DELETE CASCADE ON UPDATE CASCADE;
alter table class_daily_health_survey_data add constraint 
	fk4change_request_of_class_daily_health_survey_data foreign key (change_request) references change_request_data(id) ON DELETE CASCADE ON UPDATE CASCADE;

alter table daily_survey_question_data add constraint pk4id_of_daily_survey_question_data primary key (id);
alter table daily_survey_question_data add constraint 
	fk4question_type_of_daily_survey_question_data foreign key (question_type) references question_type_data(id) ON DELETE CASCADE ON UPDATE CASCADE;
alter table daily_survey_question_data add constraint 
	fk4class_daily_health_survey_of_daily_survey_question_data foreign key (class_daily_health_survey) references class_daily_health_survey_data(id) ON DELETE CASCADE ON UPDATE CASCADE;
alter table daily_survey_question_data add constraint 
	fk4survey_question_of_daily_survey_question_data foreign key (survey_question) references question_data(id) ON DELETE CASCADE ON UPDATE CASCADE;

alter table student_health_survey_data add constraint pk4id_of_student_health_survey_data primary key (id);
alter table student_health_survey_data add constraint 
	fk4student_of_student_health_survey_data foreign key (student) references student_data(id) ON DELETE CASCADE ON UPDATE CASCADE;
alter table student_health_survey_data add constraint 
	fk4survey_status_of_student_health_survey_data foreign key (survey_status) references survey_status_data(id) ON DELETE CASCADE ON UPDATE CASCADE;
alter table student_health_survey_data add constraint 
	fk4teacher_of_student_health_survey_data foreign key (teacher) references teacher_data(id) ON DELETE CASCADE ON UPDATE CASCADE;
alter table student_health_survey_data add constraint 
	fk4class_daily_health_survey_of_student_health_survey_data foreign key (class_daily_health_survey) references class_daily_health_survey_data(id) ON DELETE CASCADE ON UPDATE CASCADE;
alter table student_health_survey_data add constraint 
	fk4change_request_of_student_health_survey_data foreign key (change_request) references change_request_data(id) ON DELETE CASCADE ON UPDATE CASCADE;

alter table student_daily_answer_data add constraint pk4id_of_student_daily_answer_data primary key (id);
alter table student_daily_answer_data add constraint 
	fk4student_health_survey_of_student_daily_answer_data foreign key (student_health_survey) references student_health_survey_data(id) ON DELETE CASCADE ON UPDATE CASCADE;
alter table student_daily_answer_data add constraint 
	fk4question_of_student_daily_answer_data foreign key (question) references daily_survey_question_data(id) ON DELETE CASCADE ON UPDATE CASCADE;
alter table student_daily_answer_data add constraint 
	fk4change_request_of_student_daily_answer_data foreign key (change_request) references change_request_data(id) ON DELETE CASCADE ON UPDATE CASCADE;

alter table survey_status_data add constraint pk4id_of_survey_status_data primary key (id);
alter table survey_status_data add constraint 
	fk4platform_of_survey_status_data foreign key (platform) references platform_data(id) ON DELETE CASCADE ON UPDATE CASCADE;

alter table health_survey_report_data add constraint pk4id_of_health_survey_report_data primary key (id);
alter table health_survey_report_data add constraint 
	fk4student_of_health_survey_report_data foreign key (student) references student_data(id) ON DELETE CASCADE ON UPDATE CASCADE;
alter table health_survey_report_data add constraint 
	fk4teacher_of_health_survey_report_data foreign key (teacher) references teacher_data(id) ON DELETE CASCADE ON UPDATE CASCADE;
alter table health_survey_report_data add constraint 
	fk4survey_of_health_survey_report_data foreign key (survey) references class_daily_health_survey_data(id) ON DELETE CASCADE ON UPDATE CASCADE;

alter table student_answer_data add constraint pk4id_of_student_answer_data primary key (id);
alter table student_answer_data add constraint 
	fk4health_survey_report_of_student_answer_data foreign key (health_survey_report) references health_survey_report_data(id) ON DELETE CASCADE ON UPDATE CASCADE;
alter table student_answer_data add constraint 
	fk4daily_answer_of_student_answer_data foreign key (daily_answer) references student_daily_answer_data(id) ON DELETE CASCADE ON UPDATE CASCADE;

alter table user_data add constraint pk4id_of_user_data primary key (id);
alter table user_data add constraint 
	fk4address_of_user_data foreign key (address) references location_data(id) ON DELETE CASCADE ON UPDATE CASCADE;
alter table user_data add constraint 
	fk4platform_of_user_data foreign key (platform) references platform_data(id) ON DELETE CASCADE ON UPDATE CASCADE;

alter table wechat_login_info_data add constraint pk4id_of_wechat_login_info_data primary key (id);
alter table wechat_login_info_data add constraint 
	fk4user_of_wechat_login_info_data foreign key (user) references user_data(id) ON DELETE CASCADE ON UPDATE CASCADE;

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
/* The sql file is not found from: /Users/jarryzhou/githome/web-code-generator/sky/data-patch/health.sql */
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

