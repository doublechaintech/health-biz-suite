
-- BUILD WITH MODEL TIME 200211T2125
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
	download_url                  	varchar(99999)                           comment '下载网址',
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
	('P000001','四川省','P000001','2020-02-01 13:39:59','1'),
	('P000002','四川省0002','P000001','2020-02-07 21:13:10','1');

insert into city_data values
	('C000001','成都市','P000001','P000001','2020-02-09 06:45:14','1'),
	('C000002','成都市0002','P000001','P000001','2020-02-11 14:27:52','1'),
	('C000003','成都市0003','P000002','P000001','2020-02-02 18:08:27','1'),
	('C000004','成都市0004','P000002','P000001','2020-01-22 05:30:51','1');

insert into district_data values
	('D000001','高新区','C000001','P000001','2020-02-09 21:12:41','1'),
	('D000002','高新区0002','C000001','P000001','2020-02-01 08:13:16','1'),
	('D000003','高新区0003','C000002','P000001','2020-01-24 02:50:45','1'),
	('D000004','高新区0004','C000002','P000001','2020-01-28 08:36:18','1'),
	('D000005','高新区0005','C000003','P000001','2020-01-27 05:43:40','1'),
	('D000006','高新区0006','C000003','P000001','2020-02-10 12:43:03','1'),
	('D000007','高新区0007','C000004','P000001','2020-02-01 00:47:37','1'),
	('D000008','高新区0008','C000004','P000001','2020-01-24 08:06:19','1');

insert into location_data values
	('L000001','公司地址','四川省成都市高新区南华路100号','D000001','P000001','31.75040120422491','104.59132833238559','1'),
	('L000002','家庭地址','四川省成都市高新区南华路100号0002','D000001','P000001','29.41363415140238','103.64896007914687','1'),
	('L000003','公司地址','四川省成都市高新区南华路100号0003','D000002','P000001','31.53027894097253','105.68127424189464','1'),
	('L000004','家庭地址','四川省成都市高新区南华路100号0004','D000002','P000001','32.20269559304274','104.89438628626289','1'),
	('L000005','公司地址','四川省成都市高新区南华路100号0005','D000003','P000001','30.033144215671598','104.8362000189353','1'),
	('L000006','家庭地址','四川省成都市高新区南华路100号0006','D000003','P000001','32.094091702088775','104.452513763602','1'),
	('L000007','公司地址','四川省成都市高新区南华路100号0007','D000004','P000001','29.344561076985038','103.37881222570263','1'),
	('L000008','家庭地址','四川省成都市高新区南华路100号0008','D000004','P000001','29.92866826547099','104.29307660786411','1'),
	('L000009','公司地址','四川省成都市高新区南华路100号0009','D000005','P000002','30.369742738272894','104.53001909231551','1'),
	('L000010','家庭地址','四川省成都市高新区南华路100号0010','D000005','P000002','32.241584684771205','103.29607498601723','1'),
	('L000011','公司地址','四川省成都市高新区南华路100号0011','D000006','P000002','31.140092233011625','103.90207279974233','1'),
	('L000012','家庭地址','四川省成都市高新区南华路100号0012','D000006','P000002','31.54818785389656','104.66842970163128','1'),
	('L000013','公司地址','四川省成都市高新区南华路100号0013','D000007','P000002','30.261049556959673','104.57453892346376','1'),
	('L000014','家庭地址','四川省成都市高新区南华路100号0014','D000007','P000002','30.865355293435428','104.88516132149351','1'),
	('L000015','公司地址','四川省成都市高新区南华路100号0015','D000008','P000002','32.21569348852364','103.75440577665879','1'),
	('L000016','家庭地址','四川省成都市高新区南华路100号0016','D000008','P000002','29.965468499169702','103.09146273890407','1');

insert into teacher_data values
	('T000001','白山水','18012341234','益州小学','教科院一年级5班','1','2020-01-31 13:35:54','P000001','U000001','CR000001','1'),
	('T000002','胡一刀','13900000002','大源中学','教科院二年级3班','1','2020-01-27 09:15:20','P000001','U000001','CR000001','1'),
	('T000003','苗人凤','13900000003','南山中学','教科院一年级5班','1','2020-01-21 13:56:21','P000001','U000001','CR000002','1'),
	('T000004','白山水','13900000004','益州小学','教科院二年级3班','1','2020-01-27 09:07:13','P000001','U000001','CR000002','1'),
	('T000005','胡一刀','13900000005','大源中学','教科院一年级5班','1','2020-02-08 08:35:52','P000001','U000002','CR000003','1'),
	('T000006','苗人凤','13900000006','南山中学','教科院二年级3班','1','2020-02-02 18:29:15','P000001','U000002','CR000003','1'),
	('T000007','白山水','13900000007','益州小学','教科院一年级5班','1','2020-02-11 15:15:51','P000001','U000002','CR000004','1'),
	('T000008','胡一刀','13900000008','大源中学','教科院二年级3班','1','2020-01-29 05:31:44','P000001','U000002','CR000004','1');

insert into student_data values
	('S000001','刘婵','A01','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','刘备','18012341234','L000001','U000001','2020-02-06 08:49:01','P000001','1'),
	('S000002','刘阿斗','A010002','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','刘玄德','13900000002','L000001','U000001','2020-02-04 22:37:47','P000001','1'),
	('S000003','李天一','A010003','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','张飞','13900000003','L000002','U000001','2020-01-30 11:38:48','P000001','1'),
	('S000004','刘婵','A010004','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','张翼德','13900000004','L000002','U000001','2020-02-07 22:25:58','P000001','1'),
	('S000005','刘阿斗','A010005','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','刘备','13900000005','L000003','U000001','2020-02-06 11:42:37','P000001','1'),
	('S000006','李天一','A010006','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','刘玄德','13900000006','L000003','U000001','2020-02-08 21:30:28','P000001','1'),
	('S000007','刘婵','A010007','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','张飞','13900000007','L000004','U000001','2020-01-23 13:38:29','P000001','1'),
	('S000008','刘阿斗','A010008','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','张翼德','13900000008','L000004','U000001','2020-01-28 18:43:49','P000001','1'),
	('S000009','李天一','A010009','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','刘备','13900000009','L000005','U000001','2020-01-22 13:17:53','P000001','1'),
	('S000010','刘婵','A010010','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','刘玄德','13900000010','L000005','U000001','2020-01-21 04:07:38','P000001','1'),
	('S000011','刘阿斗','A010011','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','张飞','13900000011','L000006','U000001','2020-01-30 11:33:25','P000001','1'),
	('S000012','李天一','A010012','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','张翼德','13900000012','L000006','U000001','2020-02-01 10:42:18','P000001','1'),
	('S000013','刘婵','A010013','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','刘备','13900000013','L000007','U000001','2020-02-04 15:11:45','P000001','1'),
	('S000014','刘阿斗','A010014','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','刘玄德','13900000014','L000007','U000001','2020-01-26 01:20:01','P000001','1'),
	('S000015','李天一','A010015','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','张飞','13900000015','L000008','U000001','2020-01-29 20:17:56','P000001','1'),
	('S000016','刘婵','A010016','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','张翼德','13900000016','L000008','U000001','2020-01-22 10:56:56','P000001','1'),
	('S000017','刘阿斗','A010017','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','刘备','13900000017','L000009','U000002','2020-01-22 02:19:01','P000001','1'),
	('S000018','李天一','A010018','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','刘玄德','13900000018','L000009','U000002','2020-01-31 23:32:14','P000001','1'),
	('S000019','刘婵','A010019','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','张飞','13900000019','L000010','U000002','2020-01-31 04:31:24','P000001','1'),
	('S000020','刘阿斗','A010020','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','张翼德','13900000020','L000010','U000002','2020-01-31 10:01:56','P000001','1'),
	('S000021','李天一','A010021','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','刘备','13900000021','L000011','U000002','2020-01-23 21:25:35','P000001','1'),
	('S000022','刘婵','A010022','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','刘玄德','13900000022','L000011','U000002','2020-02-08 05:34:10','P000001','1'),
	('S000023','刘阿斗','A010023','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','张飞','13900000023','L000012','U000002','2020-01-28 21:46:38','P000001','1'),
	('S000024','李天一','A010024','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','张翼德','13900000024','L000012','U000002','2020-01-22 21:36:07','P000001','1'),
	('S000025','刘婵','A010025','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','刘备','13900000025','L000013','U000002','2020-01-26 17:47:45','P000001','1'),
	('S000026','刘阿斗','A010026','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','刘玄德','13900000026','L000013','U000002','2020-02-02 14:33:33','P000001','1'),
	('S000027','李天一','A010027','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','张飞','13900000027','L000014','U000002','2020-01-24 00:26:13','P000001','1'),
	('S000028','刘婵','A010028','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','张翼德','13900000028','L000014','U000002','2020-02-05 15:22:35','P000001','1'),
	('S000029','刘阿斗','A010029','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','刘备','13900000029','L000015','U000002','2020-01-25 18:24:49','P000001','1'),
	('S000030','李天一','A010030','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','刘玄德','13900000030','L000015','U000002','2020-02-01 21:55:25','P000001','1'),
	('S000031','刘婵','A010031','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','张飞','13900000031','L000016','U000002','2020-01-28 08:59:18','P000001','1'),
	('S000032','刘阿斗','A010032','https://demo.doublechaintech.com/demodata/imageManager/genImage/student00/400/200/grey/','张翼德','13900000032','L000016','U000002','2020-01-28 21:30:12','P000001','1');

insert into question_data values
	('Q000001','节假日是否到过武汉','SINGLE_SELECT','没有','有','不确定','不知道','P000001','U000001','CR000001','1'),
	('Q000002','家里是否有武汉的亲朋好友来访','SINGLE_SELECT','没有0002','有0002','不确定0002','不知道0002','P000001','U000001','CR000001','1'),
	('Q000003','是否有发热、发烧症状','SINGLE_SELECT','没有0003','有0003','不确定0003','不知道0003','P000001','U000001','CR000002','1'),
	('Q000004','节假日是否到过武汉','SINGLE_SELECT','没有0004','有0004','不确定0004','不知道0004','P000001','U000001','CR000002','1'),
	('Q000005','家里是否有武汉的亲朋好友来访','TEXT_INPUT','没有0005','有0005','不确定0005','不知道0005','P000001','U000002','CR000003','1'),
	('Q000006','是否有发热、发烧症状','TEXT_INPUT','没有0006','有0006','不确定0006','不知道0006','P000001','U000002','CR000003','1'),
	('Q000007','节假日是否到过武汉','TEXT_INPUT','没有0007','有0007','不确定0007','不知道0007','P000001','U000002','CR000004','1'),
	('Q000008','家里是否有武汉的亲朋好友来访','TEXT_INPUT','没有0008','有0008','不确定0008','不知道0008','P000001','U000002','CR000004','1');

insert into question_type_data values
	('SINGLE_SELECT','单选题','SINGLE_SELECT','P000001','1'),
	('TEXT_INPUT','简答题','TEXT_INPUT','P000001','1');

insert into class_daily_health_survey_data values
	('CDHS000001','2020年1月25日益州小学学生健康调查问卷','T000001','2020-02-10 17:10:28','U000001','https://oss','CR000001','1'),
	('CDHS000002','2020年1月25日益州小学学生健康调查问卷0002','T000001','2020-02-01 22:21:23','U000001','https://oss','CR000001','1'),
	('CDHS000003','2020年1月25日益州小学学生健康调查问卷0003','T000002','2020-01-28 04:15:41','U000001','https://oss','CR000001','1'),
	('CDHS000004','2020年1月25日益州小学学生健康调查问卷0004','T000002','2020-02-10 11:21:05','U000001','https://oss','CR000001','1'),
	('CDHS000005','2020年1月25日益州小学学生健康调查问卷0005','T000003','2020-02-07 09:40:01','U000001','https://oss','CR000002','1'),
	('CDHS000006','2020年1月25日益州小学学生健康调查问卷0006','T000003','2020-01-21 22:44:09','U000001','https://oss','CR000002','1'),
	('CDHS000007','2020年1月25日益州小学学生健康调查问卷0007','T000004','2020-01-23 01:21:05','U000001','https://oss','CR000002','1'),
	('CDHS000008','2020年1月25日益州小学学生健康调查问卷0008','T000004','2020-01-22 10:27:07','U000001','https://oss','CR000002','1'),
	('CDHS000009','2020年1月25日益州小学学生健康调查问卷0009','T000005','2020-02-11 15:24:12','U000002','https://oss','CR000003','1'),
	('CDHS000010','2020年1月25日益州小学学生健康调查问卷0010','T000005','2020-01-23 06:06:54','U000002','https://oss','CR000003','1'),
	('CDHS000011','2020年1月25日益州小学学生健康调查问卷0011','T000006','2020-02-04 19:04:48','U000002','https://oss','CR000003','1'),
	('CDHS000012','2020年1月25日益州小学学生健康调查问卷0012','T000006','2020-01-21 17:21:54','U000002','https://oss','CR000003','1'),
	('CDHS000013','2020年1月25日益州小学学生健康调查问卷0013','T000007','2020-01-24 03:58:03','U000002','https://oss','CR000004','1'),
	('CDHS000014','2020年1月25日益州小学学生健康调查问卷0014','T000007','2020-01-30 22:02:23','U000002','https://oss','CR000004','1'),
	('CDHS000015','2020年1月25日益州小学学生健康调查问卷0015','T000008','2020-02-10 00:24:59','U000002','https://oss','CR000004','1'),
	('CDHS000016','2020年1月25日益州小学学生健康调查问卷0016','T000008','2020-02-02 09:20:05','U000002','https://oss','CR000004','1');

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
	('DSQ000017','家里是否有武汉的亲朋好友来访','TEXT_INPUT','没有0017','有0017','不确定0017','不知道0017','CDHS000009','Q000005','1'),
	('DSQ000018','是否有发热、发烧症状','TEXT_INPUT','没有0018','有0018','不确定0018','不知道0018','CDHS000009','Q000005','1'),
	('DSQ000019','节假日是否到过武汉','TEXT_INPUT','没有0019','有0019','不确定0019','不知道0019','CDHS000010','Q000005','1'),
	('DSQ000020','家里是否有武汉的亲朋好友来访','TEXT_INPUT','没有0020','有0020','不确定0020','不知道0020','CDHS000010','Q000005','1'),
	('DSQ000021','是否有发热、发烧症状','TEXT_INPUT','没有0021','有0021','不确定0021','不知道0021','CDHS000011','Q000006','1'),
	('DSQ000022','节假日是否到过武汉','TEXT_INPUT','没有0022','有0022','不确定0022','不知道0022','CDHS000011','Q000006','1'),
	('DSQ000023','家里是否有武汉的亲朋好友来访','TEXT_INPUT','没有0023','有0023','不确定0023','不知道0023','CDHS000012','Q000006','1'),
	('DSQ000024','是否有发热、发烧症状','TEXT_INPUT','没有0024','有0024','不确定0024','不知道0024','CDHS000012','Q000006','1'),
	('DSQ000025','节假日是否到过武汉','TEXT_INPUT','没有0025','有0025','不确定0025','不知道0025','CDHS000013','Q000007','1'),
	('DSQ000026','家里是否有武汉的亲朋好友来访','TEXT_INPUT','没有0026','有0026','不确定0026','不知道0026','CDHS000013','Q000007','1'),
	('DSQ000027','是否有发热、发烧症状','TEXT_INPUT','没有0027','有0027','不确定0027','不知道0027','CDHS000014','Q000007','1'),
	('DSQ000028','节假日是否到过武汉','TEXT_INPUT','没有0028','有0028','不确定0028','不知道0028','CDHS000014','Q000007','1'),
	('DSQ000029','家里是否有武汉的亲朋好友来访','TEXT_INPUT','没有0029','有0029','不确定0029','不知道0029','CDHS000015','Q000008','1'),
	('DSQ000030','是否有发热、发烧症状','TEXT_INPUT','没有0030','有0030','不确定0030','不知道0030','CDHS000015','Q000008','1'),
	('DSQ000031','节假日是否到过武汉','TEXT_INPUT','没有0031','有0031','不确定0031','不知道0031','CDHS000016','Q000008','1'),
	('DSQ000032','家里是否有武汉的亲朋好友来访','TEXT_INPUT','没有0032','有0032','不确定0032','不知道0032','CDHS000016','Q000008','1');

insert into student_health_survey_data values
	('SHS000001','S000001','2020-02-05 07:50:30','UN_SUBMITTED','T000001','CDHS000001','2020-02-01 06:43:57','2020-01-23 02:26:54','CR000001','1'),
	('SHS000002','S000001','2020-02-06 07:42:50','UN_SUBMITTED','T000001','CDHS000001','2020-01-25 14:33:12','2020-01-31 20:01:10','CR000001','1'),
	('SHS000003','S000002','2020-02-03 00:07:04','UN_SUBMITTED','T000001','CDHS000001','2020-02-02 00:28:15','2020-02-03 21:06:53','CR000001','1'),
	('SHS000004','S000002','2020-01-24 23:24:35','UN_SUBMITTED','T000001','CDHS000001','2020-02-03 14:20:48','2020-02-03 13:15:39','CR000001','1'),
	('SHS000005','S000003','2020-02-08 19:39:49','UN_SUBMITTED','T000001','CDHS000002','2020-02-10 14:44:02','2020-02-07 00:03:21','CR000001','1'),
	('SHS000006','S000003','2020-02-05 06:07:43','UN_SUBMITTED','T000001','CDHS000002','2020-02-10 19:11:09','2020-01-31 15:07:39','CR000001','1'),
	('SHS000007','S000004','2020-01-24 02:26:38','UN_SUBMITTED','T000001','CDHS000002','2020-02-07 02:43:18','2020-02-07 06:11:58','CR000001','1'),
	('SHS000008','S000004','2020-01-29 17:59:23','UN_SUBMITTED','T000001','CDHS000002','2020-01-27 18:00:56','2020-01-31 14:32:17','CR000001','1'),
	('SHS000009','S000005','2020-01-22 17:41:31','UN_SUBMITTED','T000002','CDHS000003','2020-01-22 19:59:07','2020-02-08 23:22:10','CR000001','1'),
	('SHS000010','S000005','2020-01-26 02:35:13','UN_SUBMITTED','T000002','CDHS000003','2020-02-10 18:27:04','2020-02-05 14:02:23','CR000001','1'),
	('SHS000011','S000006','2020-02-02 08:31:08','UN_SUBMITTED','T000002','CDHS000003','2020-02-10 23:43:58','2020-02-05 05:24:05','CR000001','1'),
	('SHS000012','S000006','2020-01-22 03:10:43','UN_SUBMITTED','T000002','CDHS000003','2020-02-02 06:05:58','2020-02-03 21:18:48','CR000001','1'),
	('SHS000013','S000007','2020-02-03 15:38:46','UN_SUBMITTED','T000002','CDHS000004','2020-02-01 03:11:05','2020-02-10 08:49:16','CR000001','1'),
	('SHS000014','S000007','2020-02-02 21:44:39','UN_SUBMITTED','T000002','CDHS000004','2020-01-26 05:26:30','2020-02-01 20:35:48','CR000001','1'),
	('SHS000015','S000008','2020-02-09 06:09:42','UN_SUBMITTED','T000002','CDHS000004','2020-02-07 17:12:21','2020-02-01 15:52:15','CR000001','1'),
	('SHS000016','S000008','2020-02-05 07:45:31','UN_SUBMITTED','T000002','CDHS000004','2020-02-09 09:56:51','2020-02-01 23:28:46','CR000001','1'),
	('SHS000017','S000009','2020-02-01 03:33:10','UN_SUBMITTED','T000003','CDHS000005','2020-02-08 22:00:47','2020-01-29 07:33:23','CR000002','1'),
	('SHS000018','S000009','2020-01-30 16:49:24','UN_SUBMITTED','T000003','CDHS000005','2020-02-03 00:07:14','2020-02-10 13:22:24','CR000002','1'),
	('SHS000019','S000010','2020-01-29 09:46:42','UN_SUBMITTED','T000003','CDHS000005','2020-02-11 20:00:44','2020-01-22 12:25:16','CR000002','1'),
	('SHS000020','S000010','2020-01-24 09:15:09','UN_SUBMITTED','T000003','CDHS000005','2020-02-11 18:18:20','2020-02-05 02:55:59','CR000002','1'),
	('SHS000021','S000011','2020-01-25 21:37:42','UN_SUBMITTED','T000003','CDHS000006','2020-01-28 12:06:02','2020-01-30 20:34:58','CR000002','1'),
	('SHS000022','S000011','2020-01-26 19:24:32','UN_SUBMITTED','T000003','CDHS000006','2020-01-31 07:44:32','2020-01-21 18:30:40','CR000002','1'),
	('SHS000023','S000012','2020-01-22 09:43:21','SUBMITTE','T000003','CDHS000006','2020-02-04 16:02:42','2020-01-27 14:37:36','CR000002','1'),
	('SHS000024','S000012','2020-01-21 04:04:23','SUBMITTE','T000003','CDHS000006','2020-01-25 21:12:22','2020-02-11 04:49:32','CR000002','1'),
	('SHS000025','S000013','2020-02-02 03:52:16','SUBMITTE','T000004','CDHS000007','2020-02-10 07:24:18','2020-01-26 04:54:59','CR000002','1'),
	('SHS000026','S000013','2020-01-22 10:33:02','SUBMITTE','T000004','CDHS000007','2020-01-24 22:29:52','2020-01-23 05:22:36','CR000002','1'),
	('SHS000027','S000014','2020-02-08 13:54:44','SUBMITTE','T000004','CDHS000007','2020-01-30 23:47:53','2020-01-28 03:30:31','CR000002','1'),
	('SHS000028','S000014','2020-02-10 09:55:41','SUBMITTE','T000004','CDHS000007','2020-02-07 07:01:32','2020-01-27 21:05:51','CR000002','1'),
	('SHS000029','S000015','2020-01-28 23:51:40','SUBMITTE','T000004','CDHS000008','2020-02-02 11:07:08','2020-02-10 11:41:08','CR000002','1'),
	('SHS000030','S000015','2020-01-24 17:15:24','SUBMITTE','T000004','CDHS000008','2020-01-24 13:40:46','2020-01-30 12:17:51','CR000002','1'),
	('SHS000031','S000016','2020-01-25 06:41:14','SUBMITTE','T000004','CDHS000008','2020-01-22 23:01:32','2020-02-06 12:57:15','CR000002','1'),
	('SHS000032','S000016','2020-02-06 03:32:44','SUBMITTE','T000004','CDHS000008','2020-02-01 04:23:44','2020-02-06 11:23:30','CR000002','1'),
	('SHS000033','S000017','2020-01-28 22:03:13','SUBMITTE','T000005','CDHS000009','2020-02-08 10:03:50','2020-01-21 07:38:49','CR000003','1'),
	('SHS000034','S000017','2020-01-22 12:29:40','SUBMITTE','T000005','CDHS000009','2020-02-02 03:39:34','2020-01-28 17:41:13','CR000003','1'),
	('SHS000035','S000018','2020-02-04 11:37:29','SUBMITTE','T000005','CDHS000009','2020-01-21 10:53:59','2020-01-26 21:28:04','CR000003','1'),
	('SHS000036','S000018','2020-01-23 12:39:45','SUBMITTE','T000005','CDHS000009','2020-01-29 20:48:32','2020-01-25 08:07:48','CR000003','1'),
	('SHS000037','S000019','2020-01-26 12:19:51','SUBMITTE','T000005','CDHS000010','2020-02-08 10:00:46','2020-01-27 19:46:03','CR000003','1'),
	('SHS000038','S000019','2020-01-24 01:56:12','SUBMITTE','T000005','CDHS000010','2020-02-01 04:17:38','2020-01-21 08:11:23','CR000003','1'),
	('SHS000039','S000020','2020-02-08 05:13:52','SUBMITTE','T000005','CDHS000010','2020-02-10 04:13:00','2020-01-27 04:22:11','CR000003','1'),
	('SHS000040','S000020','2020-02-07 18:58:10','SUBMITTE','T000005','CDHS000010','2020-02-07 22:47:58','2020-02-03 04:30:27','CR000003','1'),
	('SHS000041','S000021','2020-01-22 08:30:47','SUBMITTE','T000006','CDHS000011','2020-01-25 08:34:24','2020-02-07 07:34:12','CR000003','1'),
	('SHS000042','S000021','2020-01-23 12:51:58','SUBMITTE','T000006','CDHS000011','2020-01-26 19:18:31','2020-01-23 13:41:02','CR000003','1'),
	('SHS000043','S000022','2020-01-28 11:07:14','SUBMITTE','T000006','CDHS000011','2020-02-08 12:37:09','2020-01-24 20:44:44','CR000003','1'),
	('SHS000044','S000022','2020-02-03 04:19:33','DRAFT','T000006','CDHS000011','2020-02-03 06:52:29','2020-02-05 00:47:16','CR000003','1'),
	('SHS000045','S000023','2020-02-10 09:21:10','DRAFT','T000006','CDHS000012','2020-02-11 04:54:19','2020-01-24 04:16:36','CR000003','1'),
	('SHS000046','S000023','2020-02-03 22:19:41','DRAFT','T000006','CDHS000012','2020-01-30 01:41:28','2020-02-06 12:38:56','CR000003','1'),
	('SHS000047','S000024','2020-02-03 19:50:10','DRAFT','T000006','CDHS000012','2020-01-22 03:17:33','2020-01-23 20:07:52','CR000003','1'),
	('SHS000048','S000024','2020-02-10 05:16:11','DRAFT','T000006','CDHS000012','2020-01-28 06:45:47','2020-02-01 13:36:16','CR000003','1'),
	('SHS000049','S000025','2020-01-27 03:37:55','DRAFT','T000007','CDHS000013','2020-02-10 15:40:25','2020-01-29 17:47:37','CR000004','1'),
	('SHS000050','S000025','2020-02-11 18:21:11','DRAFT','T000007','CDHS000013','2020-02-01 19:24:50','2020-02-06 18:09:38','CR000004','1'),
	('SHS000051','S000026','2020-02-03 22:18:38','DRAFT','T000007','CDHS000013','2020-01-21 15:20:56','2020-01-26 01:03:06','CR000004','1'),
	('SHS000052','S000026','2020-01-29 05:00:44','DRAFT','T000007','CDHS000013','2020-02-02 07:48:12','2020-02-02 22:09:18','CR000004','1'),
	('SHS000053','S000027','2020-02-11 01:20:51','DRAFT','T000007','CDHS000014','2020-02-02 05:50:42','2020-01-26 14:15:33','CR000004','1'),
	('SHS000054','S000027','2020-01-27 12:40:55','DRAFT','T000007','CDHS000014','2020-01-21 17:13:26','2020-02-06 16:59:57','CR000004','1'),
	('SHS000055','S000028','2020-02-04 17:28:04','DRAFT','T000007','CDHS000014','2020-01-25 12:33:40','2020-01-28 04:21:33','CR000004','1'),
	('SHS000056','S000028','2020-01-30 07:45:55','DRAFT','T000007','CDHS000014','2020-02-09 23:01:19','2020-01-31 14:14:28','CR000004','1'),
	('SHS000057','S000029','2020-02-06 17:49:17','DRAFT','T000008','CDHS000015','2020-02-11 13:36:04','2020-01-23 13:02:04','CR000004','1'),
	('SHS000058','S000029','2020-02-06 19:55:13','DRAFT','T000008','CDHS000015','2020-02-07 11:29:10','2020-02-07 20:03:40','CR000004','1'),
	('SHS000059','S000030','2020-02-02 07:50:36','DRAFT','T000008','CDHS000015','2020-01-22 01:24:37','2020-02-10 04:24:17','CR000004','1'),
	('SHS000060','S000030','2020-02-05 22:34:18','DRAFT','T000008','CDHS000015','2020-02-07 05:15:50','2020-02-07 04:50:56','CR000004','1'),
	('SHS000061','S000031','2020-02-02 15:40:54','DRAFT','T000008','CDHS000016','2020-01-27 19:36:07','2020-01-31 11:27:06','CR000004','1'),
	('SHS000062','S000031','2020-02-04 18:24:29','DRAFT','T000008','CDHS000016','2020-01-30 00:50:01','2020-01-27 04:13:58','CR000004','1'),
	('SHS000063','S000032','2020-01-27 08:58:11','DRAFT','T000008','CDHS000016','2020-02-08 15:36:42','2020-01-28 08:51:09','CR000004','1'),
	('SHS000064','S000032','2020-01-26 20:48:35','DRAFT','T000008','CDHS000016','2020-02-03 04:48:53','2020-02-03 03:58:05','CR000004','1');

insert into student_daily_answer_data values
	('SDA000001','SHS000001','DSQ000001','A','2020-01-30 11:27:57','2020-02-09 10:51:17','1'),
	('SDA000002','SHS000001','DSQ000001','B','2020-02-06 23:08:13','2020-02-03 00:19:48','1'),
	('SDA000003','SHS000002','DSQ000001','A','2020-01-28 17:34:35','2020-02-07 04:56:44','1'),
	('SDA000004','SHS000002','DSQ000001','B','2020-01-29 23:47:41','2020-01-31 18:20:08','1'),
	('SDA000005','SHS000003','DSQ000002','A','2020-01-31 10:11:12','2020-01-27 16:01:16','1'),
	('SDA000006','SHS000003','DSQ000002','B','2020-02-04 13:07:04','2020-01-27 10:14:57','1'),
	('SDA000007','SHS000004','DSQ000002','A','2020-02-06 19:51:34','2020-02-08 09:44:33','1'),
	('SDA000008','SHS000004','DSQ000002','B','2020-02-05 01:42:39','2020-01-24 18:42:26','1'),
	('SDA000009','SHS000005','DSQ000003','A','2020-02-11 13:55:44','2020-01-31 21:56:56','1'),
	('SDA000010','SHS000005','DSQ000003','B','2020-02-09 22:12:36','2020-02-04 03:17:10','1'),
	('SDA000011','SHS000006','DSQ000003','A','2020-02-02 13:28:52','2020-02-02 18:39:38','1'),
	('SDA000012','SHS000006','DSQ000003','B','2020-02-10 11:36:40','2020-02-08 15:38:54','1'),
	('SDA000013','SHS000007','DSQ000004','A','2020-01-29 17:43:55','2020-02-03 17:44:50','1'),
	('SDA000014','SHS000007','DSQ000004','B','2020-02-01 16:07:23','2020-01-29 14:05:34','1'),
	('SDA000015','SHS000008','DSQ000004','A','2020-01-25 16:16:55','2020-01-23 06:02:31','1'),
	('SDA000016','SHS000008','DSQ000004','B','2020-01-28 04:15:20','2020-02-04 07:12:29','1'),
	('SDA000017','SHS000009','DSQ000005','A','2020-02-02 23:47:47','2020-02-05 16:11:24','1'),
	('SDA000018','SHS000009','DSQ000005','B','2020-02-02 01:37:49','2020-02-07 06:39:36','1'),
	('SDA000019','SHS000010','DSQ000005','A','2020-02-10 10:56:53','2020-01-24 23:26:48','1'),
	('SDA000020','SHS000010','DSQ000005','B','2020-01-30 11:12:56','2020-02-02 13:15:59','1'),
	('SDA000021','SHS000011','DSQ000006','A','2020-02-11 09:59:13','2020-02-05 02:22:59','1'),
	('SDA000022','SHS000011','DSQ000006','B','2020-02-03 00:06:50','2020-01-23 23:17:16','1'),
	('SDA000023','SHS000012','DSQ000006','A','2020-01-26 11:50:33','2020-02-08 07:50:41','1'),
	('SDA000024','SHS000012','DSQ000006','B','2020-01-23 13:05:24','2020-01-25 11:14:20','1'),
	('SDA000025','SHS000013','DSQ000007','A','2020-02-06 09:01:54','2020-02-04 20:41:58','1'),
	('SDA000026','SHS000013','DSQ000007','B','2020-02-07 00:54:22','2020-02-08 09:33:18','1'),
	('SDA000027','SHS000014','DSQ000007','A','2020-01-24 18:02:29','2020-02-04 18:56:30','1'),
	('SDA000028','SHS000014','DSQ000007','B','2020-01-28 11:26:43','2020-02-07 22:27:58','1'),
	('SDA000029','SHS000015','DSQ000008','A','2020-01-21 22:40:48','2020-02-06 08:58:05','1'),
	('SDA000030','SHS000015','DSQ000008','B','2020-02-04 09:07:34','2020-02-06 04:42:48','1'),
	('SDA000031','SHS000016','DSQ000008','A','2020-01-22 00:07:22','2020-01-28 16:19:15','1'),
	('SDA000032','SHS000016','DSQ000008','B','2020-02-07 04:52:40','2020-02-04 17:13:07','1'),
	('SDA000033','SHS000017','DSQ000009','A','2020-01-21 12:51:51','2020-01-31 03:29:11','1'),
	('SDA000034','SHS000017','DSQ000009','B','2020-01-27 18:55:12','2020-02-04 06:20:03','1'),
	('SDA000035','SHS000018','DSQ000009','A','2020-01-24 22:03:53','2020-02-04 09:40:27','1'),
	('SDA000036','SHS000018','DSQ000009','B','2020-01-31 13:00:20','2020-02-03 06:26:37','1'),
	('SDA000037','SHS000019','DSQ000010','A','2020-01-23 12:07:14','2020-02-11 01:02:24','1'),
	('SDA000038','SHS000019','DSQ000010','B','2020-02-09 13:36:20','2020-01-29 05:26:11','1'),
	('SDA000039','SHS000020','DSQ000010','A','2020-02-02 15:48:18','2020-01-22 01:12:14','1'),
	('SDA000040','SHS000020','DSQ000010','B','2020-02-10 01:11:24','2020-01-27 22:58:54','1'),
	('SDA000041','SHS000021','DSQ000011','A','2020-02-09 22:55:34','2020-01-30 04:18:20','1'),
	('SDA000042','SHS000021','DSQ000011','B','2020-01-25 14:25:16','2020-02-03 15:43:29','1'),
	('SDA000043','SHS000022','DSQ000011','A','2020-01-31 07:00:53','2020-02-03 05:04:17','1'),
	('SDA000044','SHS000022','DSQ000011','B','2020-01-27 02:11:36','2020-02-09 06:22:58','1'),
	('SDA000045','SHS000023','DSQ000012','A','2020-02-01 13:23:53','2020-01-23 06:00:58','1'),
	('SDA000046','SHS000023','DSQ000012','B','2020-01-23 16:35:23','2020-02-02 23:09:54','1'),
	('SDA000047','SHS000024','DSQ000012','A','2020-01-31 03:38:25','2020-02-06 10:34:25','1'),
	('SDA000048','SHS000024','DSQ000012','B','2020-02-07 15:10:09','2020-01-28 22:26:50','1'),
	('SDA000049','SHS000025','DSQ000013','A','2020-01-28 21:00:56','2020-01-29 09:51:55','1'),
	('SDA000050','SHS000025','DSQ000013','B','2020-02-09 03:35:36','2020-01-24 11:55:29','1'),
	('SDA000051','SHS000026','DSQ000013','A','2020-01-31 15:45:53','2020-01-26 14:19:13','1'),
	('SDA000052','SHS000026','DSQ000013','B','2020-01-29 20:11:58','2020-02-04 09:37:28','1'),
	('SDA000053','SHS000027','DSQ000014','A','2020-02-01 17:48:37','2020-01-29 19:55:51','1'),
	('SDA000054','SHS000027','DSQ000014','B','2020-02-03 18:45:58','2020-02-10 06:06:29','1'),
	('SDA000055','SHS000028','DSQ000014','A','2020-02-09 05:08:43','2020-01-27 01:17:43','1'),
	('SDA000056','SHS000028','DSQ000014','B','2020-01-21 01:58:07','2020-02-08 14:59:27','1'),
	('SDA000057','SHS000029','DSQ000015','A','2020-02-02 05:53:14','2020-01-27 01:47:33','1'),
	('SDA000058','SHS000029','DSQ000015','B','2020-02-07 15:54:22','2020-02-02 06:06:46','1'),
	('SDA000059','SHS000030','DSQ000015','A','2020-01-26 15:46:32','2020-02-01 08:28:44','1'),
	('SDA000060','SHS000030','DSQ000015','B','2020-01-28 02:47:21','2020-02-06 02:44:27','1'),
	('SDA000061','SHS000031','DSQ000016','A','2020-02-09 07:31:06','2020-01-21 09:55:31','1'),
	('SDA000062','SHS000031','DSQ000016','B','2020-01-21 09:12:38','2020-01-25 09:50:11','1'),
	('SDA000063','SHS000032','DSQ000016','A','2020-01-22 13:49:43','2020-02-06 20:06:24','1'),
	('SDA000064','SHS000032','DSQ000016','B','2020-01-21 01:22:23','2020-01-28 04:43:05','1'),
	('SDA000065','SHS000033','DSQ000017','A','2020-01-30 11:52:22','2020-02-02 12:56:46','1'),
	('SDA000066','SHS000033','DSQ000017','B','2020-02-04 19:50:17','2020-02-08 17:57:41','1'),
	('SDA000067','SHS000034','DSQ000017','A','2020-02-09 20:44:04','2020-01-28 16:38:45','1'),
	('SDA000068','SHS000034','DSQ000017','B','2020-01-26 17:57:33','2020-01-31 04:18:38','1'),
	('SDA000069','SHS000035','DSQ000018','A','2020-01-28 11:49:46','2020-02-03 20:29:18','1'),
	('SDA000070','SHS000035','DSQ000018','B','2020-02-03 04:36:01','2020-01-21 17:56:49','1'),
	('SDA000071','SHS000036','DSQ000018','A','2020-02-11 18:32:31','2020-01-27 04:33:52','1'),
	('SDA000072','SHS000036','DSQ000018','B','2020-02-02 20:11:41','2020-02-08 10:02:40','1'),
	('SDA000073','SHS000037','DSQ000019','A','2020-01-28 21:19:40','2020-01-24 06:05:15','1'),
	('SDA000074','SHS000037','DSQ000019','B','2020-01-24 16:29:00','2020-01-23 00:45:13','1'),
	('SDA000075','SHS000038','DSQ000019','A','2020-01-31 22:55:19','2020-01-24 16:34:39','1'),
	('SDA000076','SHS000038','DSQ000019','B','2020-01-22 07:34:32','2020-01-22 23:23:56','1'),
	('SDA000077','SHS000039','DSQ000020','A','2020-02-06 08:42:36','2020-02-04 22:15:10','1'),
	('SDA000078','SHS000039','DSQ000020','B','2020-02-08 06:06:34','2020-01-21 03:43:54','1'),
	('SDA000079','SHS000040','DSQ000020','A','2020-01-31 22:33:45','2020-01-23 16:14:17','1'),
	('SDA000080','SHS000040','DSQ000020','B','2020-01-28 13:14:59','2020-02-06 05:34:28','1'),
	('SDA000081','SHS000041','DSQ000021','A','2020-02-03 09:42:29','2020-01-21 10:30:08','1'),
	('SDA000082','SHS000041','DSQ000021','B','2020-01-23 07:01:58','2020-02-01 21:18:04','1'),
	('SDA000083','SHS000042','DSQ000021','A','2020-02-06 11:06:32','2020-01-26 07:21:45','1'),
	('SDA000084','SHS000042','DSQ000021','B','2020-01-31 03:47:11','2020-01-24 12:08:25','1'),
	('SDA000085','SHS000043','DSQ000022','A','2020-02-11 06:19:32','2020-02-05 15:44:39','1'),
	('SDA000086','SHS000043','DSQ000022','B','2020-01-26 14:22:48','2020-01-22 03:38:52','1'),
	('SDA000087','SHS000044','DSQ000022','A','2020-02-04 13:12:11','2020-01-30 12:09:36','1'),
	('SDA000088','SHS000044','DSQ000022','B','2020-02-08 12:06:42','2020-02-07 23:05:16','1'),
	('SDA000089','SHS000045','DSQ000023','A','2020-01-21 11:40:26','2020-01-30 18:34:31','1'),
	('SDA000090','SHS000045','DSQ000023','B','2020-01-29 00:56:04','2020-01-29 02:16:07','1'),
	('SDA000091','SHS000046','DSQ000023','A','2020-01-22 12:46:38','2020-02-03 08:32:51','1'),
	('SDA000092','SHS000046','DSQ000023','B','2020-01-27 19:49:00','2020-01-31 22:25:27','1'),
	('SDA000093','SHS000047','DSQ000024','A','2020-01-29 15:24:27','2020-01-29 07:16:58','1'),
	('SDA000094','SHS000047','DSQ000024','B','2020-02-01 00:34:13','2020-02-01 09:23:30','1'),
	('SDA000095','SHS000048','DSQ000024','A','2020-02-05 05:32:53','2020-02-02 02:55:44','1'),
	('SDA000096','SHS000048','DSQ000024','B','2020-02-11 02:02:31','2020-01-26 12:01:30','1'),
	('SDA000097','SHS000049','DSQ000025','A','2020-01-29 03:01:14','2020-02-10 03:31:10','1'),
	('SDA000098','SHS000049','DSQ000025','B','2020-01-23 01:08:02','2020-01-26 00:05:52','1'),
	('SDA000099','SHS000050','DSQ000025','A','2020-01-30 22:40:25','2020-02-11 13:18:55','1'),
	('SDA000100','SHS000050','DSQ000025','B','2020-01-23 21:56:12','2020-01-26 11:05:02','1'),
	('SDA000101','SHS000051','DSQ000026','A','2020-01-28 12:02:59','2020-02-01 16:38:16','1'),
	('SDA000102','SHS000051','DSQ000026','B','2020-02-08 05:05:03','2020-01-30 13:22:34','1'),
	('SDA000103','SHS000052','DSQ000026','A','2020-02-10 12:43:10','2020-02-03 05:51:52','1'),
	('SDA000104','SHS000052','DSQ000026','B','2020-02-09 04:18:04','2020-02-08 15:04:43','1'),
	('SDA000105','SHS000053','DSQ000027','A','2020-02-11 19:38:27','2020-02-01 16:15:23','1'),
	('SDA000106','SHS000053','DSQ000027','B','2020-02-04 13:11:18','2020-01-27 18:56:11','1'),
	('SDA000107','SHS000054','DSQ000027','A','2020-02-06 03:41:20','2020-01-24 16:55:06','1'),
	('SDA000108','SHS000054','DSQ000027','B','2020-02-04 10:12:44','2020-01-31 09:59:20','1'),
	('SDA000109','SHS000055','DSQ000028','A','2020-02-11 16:55:59','2020-02-11 19:14:11','1'),
	('SDA000110','SHS000055','DSQ000028','B','2020-02-04 21:58:37','2020-01-31 00:00:40','1'),
	('SDA000111','SHS000056','DSQ000028','A','2020-01-31 11:04:19','2020-02-08 17:36:59','1'),
	('SDA000112','SHS000056','DSQ000028','B','2020-01-25 17:02:10','2020-01-24 09:41:14','1'),
	('SDA000113','SHS000057','DSQ000029','A','2020-01-23 17:54:40','2020-01-25 21:51:06','1'),
	('SDA000114','SHS000057','DSQ000029','B','2020-02-11 07:27:53','2020-01-28 02:34:16','1'),
	('SDA000115','SHS000058','DSQ000029','A','2020-02-04 21:15:54','2020-01-31 11:09:48','1'),
	('SDA000116','SHS000058','DSQ000029','B','2020-02-06 05:13:53','2020-02-08 09:26:20','1'),
	('SDA000117','SHS000059','DSQ000030','A','2020-01-26 11:22:42','2020-01-29 23:19:23','1'),
	('SDA000118','SHS000059','DSQ000030','B','2020-02-07 01:26:15','2020-01-21 03:59:14','1'),
	('SDA000119','SHS000060','DSQ000030','A','2020-02-08 14:18:47','2020-01-28 02:37:38','1'),
	('SDA000120','SHS000060','DSQ000030','B','2020-02-09 05:32:46','2020-02-07 21:25:52','1'),
	('SDA000121','SHS000061','DSQ000031','A','2020-01-30 10:30:33','2020-01-29 19:06:09','1'),
	('SDA000122','SHS000061','DSQ000031','B','2020-01-31 13:12:35','2020-02-10 07:57:41','1'),
	('SDA000123','SHS000062','DSQ000031','A','2020-01-29 20:31:53','2020-02-04 08:33:00','1'),
	('SDA000124','SHS000062','DSQ000031','B','2020-01-24 23:36:35','2020-02-08 09:39:50','1'),
	('SDA000125','SHS000063','DSQ000032','A','2020-01-23 13:01:32','2020-02-06 12:04:36','1'),
	('SDA000126','SHS000063','DSQ000032','B','2020-01-27 00:21:11','2020-01-23 15:57:40','1'),
	('SDA000127','SHS000064','DSQ000032','A','2020-01-28 05:45:16','2020-01-24 04:14:44','1'),
	('SDA000128','SHS000064','DSQ000032','B','2020-01-21 00:43:38','2020-02-07 18:31:57','1');

insert into survey_status_data values
	('UN_SUBMITTED','未提交','UN_SUBMITTED','P000001','1'),
	('SUBMITTE','已提交','SUBMITTE','P000001','1'),
	('DRAFT','草稿','DRAFT','P000001','1');

insert into health_survey_report_data values
	('HSR000001','2020年1月25日益州小学学生健康调查问卷','2020-01-27 18:03:30','张三','益州小学','教科院一年级5班','刘婵','A01','刘备','18012341234','S000001','T000001','CDHS000001','1'),
	('HSR000002','2020年1月25日益州小学学生健康调查问卷0002','2020-01-30 19:40:42','张三0002','大源中学','教科院二年级3班','刘阿斗','A01','刘玄德','13900000002','S000001','T000001','CDHS000001','1'),
	('HSR000003','2020年1月25日益州小学学生健康调查问卷0003','2020-02-02 07:18:12','张三0003','南山中学','教科院一年级5班','李天一','A01','张飞','13900000003','S000002','T000001','CDHS000001','1'),
	('HSR000004','2020年1月25日益州小学学生健康调查问卷0004','2020-01-22 23:58:40','张三0004','益州小学','教科院二年级3班','刘婵','A01','张翼德','13900000004','S000002','T000001','CDHS000001','1'),
	('HSR000005','2020年1月25日益州小学学生健康调查问卷0005','2020-02-05 00:58:12','张三0005','大源中学','教科院一年级5班','刘阿斗','A01','刘备','13900000005','S000003','T000001','CDHS000002','1'),
	('HSR000006','2020年1月25日益州小学学生健康调查问卷0006','2020-02-07 15:39:46','张三0006','南山中学','教科院二年级3班','李天一','A01','刘玄德','13900000006','S000003','T000001','CDHS000002','1'),
	('HSR000007','2020年1月25日益州小学学生健康调查问卷0007','2020-02-11 09:52:23','张三0007','益州小学','教科院一年级5班','刘婵','A01','张飞','13900000007','S000004','T000001','CDHS000002','1'),
	('HSR000008','2020年1月25日益州小学学生健康调查问卷0008','2020-01-23 13:37:26','张三0008','大源中学','教科院二年级3班','刘阿斗','A01','张翼德','13900000008','S000004','T000001','CDHS000002','1'),
	('HSR000009','2020年1月25日益州小学学生健康调查问卷0009','2020-01-21 00:20:34','张三0009','南山中学','教科院一年级5班','李天一','A01','刘备','13900000009','S000005','T000002','CDHS000003','1'),
	('HSR000010','2020年1月25日益州小学学生健康调查问卷0010','2020-02-11 03:52:29','张三0010','益州小学','教科院二年级3班','刘婵','A01','刘玄德','13900000010','S000005','T000002','CDHS000003','1'),
	('HSR000011','2020年1月25日益州小学学生健康调查问卷0011','2020-01-29 23:44:49','张三0011','大源中学','教科院一年级5班','刘阿斗','A01','张飞','13900000011','S000006','T000002','CDHS000003','1'),
	('HSR000012','2020年1月25日益州小学学生健康调查问卷0012','2020-01-31 03:14:46','张三0012','南山中学','教科院二年级3班','李天一','A01','张翼德','13900000012','S000006','T000002','CDHS000003','1'),
	('HSR000013','2020年1月25日益州小学学生健康调查问卷0013','2020-01-28 13:05:21','张三0013','益州小学','教科院一年级5班','刘婵','A01','刘备','13900000013','S000007','T000002','CDHS000004','1'),
	('HSR000014','2020年1月25日益州小学学生健康调查问卷0014','2020-01-24 22:05:46','张三0014','大源中学','教科院二年级3班','刘阿斗','A01','刘玄德','13900000014','S000007','T000002','CDHS000004','1'),
	('HSR000015','2020年1月25日益州小学学生健康调查问卷0015','2020-02-02 23:41:39','张三0015','南山中学','教科院一年级5班','李天一','A01','张飞','13900000015','S000008','T000002','CDHS000004','1'),
	('HSR000016','2020年1月25日益州小学学生健康调查问卷0016','2020-02-11 03:51:35','张三0016','益州小学','教科院二年级3班','刘婵','A01','张翼德','13900000016','S000008','T000002','CDHS000004','1'),
	('HSR000017','2020年1月25日益州小学学生健康调查问卷0017','2020-01-29 05:48:00','张三0017','大源中学','教科院一年级5班','刘阿斗','A01','刘备','13900000017','S000009','T000003','CDHS000005','1'),
	('HSR000018','2020年1月25日益州小学学生健康调查问卷0018','2020-02-03 05:36:55','张三0018','南山中学','教科院二年级3班','李天一','A01','刘玄德','13900000018','S000009','T000003','CDHS000005','1'),
	('HSR000019','2020年1月25日益州小学学生健康调查问卷0019','2020-02-03 14:05:14','张三0019','益州小学','教科院一年级5班','刘婵','A01','张飞','13900000019','S000010','T000003','CDHS000005','1'),
	('HSR000020','2020年1月25日益州小学学生健康调查问卷0020','2020-02-09 19:44:37','张三0020','大源中学','教科院二年级3班','刘阿斗','A01','张翼德','13900000020','S000010','T000003','CDHS000005','1'),
	('HSR000021','2020年1月25日益州小学学生健康调查问卷0021','2020-02-02 21:00:21','张三0021','南山中学','教科院一年级5班','李天一','A01','刘备','13900000021','S000011','T000003','CDHS000006','1'),
	('HSR000022','2020年1月25日益州小学学生健康调查问卷0022','2020-02-08 00:12:56','张三0022','益州小学','教科院二年级3班','刘婵','A01','刘玄德','13900000022','S000011','T000003','CDHS000006','1'),
	('HSR000023','2020年1月25日益州小学学生健康调查问卷0023','2020-01-31 04:19:09','张三0023','大源中学','教科院一年级5班','刘阿斗','A01','张飞','13900000023','S000012','T000003','CDHS000006','1'),
	('HSR000024','2020年1月25日益州小学学生健康调查问卷0024','2020-01-28 03:06:50','张三0024','南山中学','教科院二年级3班','李天一','A01','张翼德','13900000024','S000012','T000003','CDHS000006','1'),
	('HSR000025','2020年1月25日益州小学学生健康调查问卷0025','2020-02-01 13:33:49','张三0025','益州小学','教科院一年级5班','刘婵','A01','刘备','13900000025','S000013','T000004','CDHS000007','1'),
	('HSR000026','2020年1月25日益州小学学生健康调查问卷0026','2020-01-25 13:31:00','张三0026','大源中学','教科院二年级3班','刘阿斗','A01','刘玄德','13900000026','S000013','T000004','CDHS000007','1'),
	('HSR000027','2020年1月25日益州小学学生健康调查问卷0027','2020-01-25 23:53:57','张三0027','南山中学','教科院一年级5班','李天一','A01','张飞','13900000027','S000014','T000004','CDHS000007','1'),
	('HSR000028','2020年1月25日益州小学学生健康调查问卷0028','2020-01-21 22:42:20','张三0028','益州小学','教科院二年级3班','刘婵','A01','张翼德','13900000028','S000014','T000004','CDHS000007','1'),
	('HSR000029','2020年1月25日益州小学学生健康调查问卷0029','2020-01-30 22:05:55','张三0029','大源中学','教科院一年级5班','刘阿斗','A01','刘备','13900000029','S000015','T000004','CDHS000008','1'),
	('HSR000030','2020年1月25日益州小学学生健康调查问卷0030','2020-01-29 12:06:01','张三0030','南山中学','教科院二年级3班','李天一','A01','刘玄德','13900000030','S000015','T000004','CDHS000008','1'),
	('HSR000031','2020年1月25日益州小学学生健康调查问卷0031','2020-01-28 03:22:53','张三0031','益州小学','教科院一年级5班','刘婵','A01','张飞','13900000031','S000016','T000004','CDHS000008','1'),
	('HSR000032','2020年1月25日益州小学学生健康调查问卷0032','2020-01-27 15:13:51','张三0032','大源中学','教科院二年级3班','刘阿斗','A01','张翼德','13900000032','S000016','T000004','CDHS000008','1'),
	('HSR000033','2020年1月25日益州小学学生健康调查问卷0033','2020-02-05 08:05:42','张三0033','南山中学','教科院一年级5班','李天一','A01','刘备','13900000033','S000017','T000005','CDHS000009','1'),
	('HSR000034','2020年1月25日益州小学学生健康调查问卷0034','2020-02-05 22:21:44','张三0034','益州小学','教科院二年级3班','刘婵','A01','刘玄德','13900000034','S000017','T000005','CDHS000009','1'),
	('HSR000035','2020年1月25日益州小学学生健康调查问卷0035','2020-02-01 04:40:17','张三0035','大源中学','教科院一年级5班','刘阿斗','A01','张飞','13900000035','S000018','T000005','CDHS000009','1'),
	('HSR000036','2020年1月25日益州小学学生健康调查问卷0036','2020-01-28 07:46:45','张三0036','南山中学','教科院二年级3班','李天一','A01','张翼德','13900000036','S000018','T000005','CDHS000009','1'),
	('HSR000037','2020年1月25日益州小学学生健康调查问卷0037','2020-02-10 12:07:43','张三0037','益州小学','教科院一年级5班','刘婵','A01','刘备','13900000037','S000019','T000005','CDHS000010','1'),
	('HSR000038','2020年1月25日益州小学学生健康调查问卷0038','2020-02-07 23:20:57','张三0038','大源中学','教科院二年级3班','刘阿斗','A01','刘玄德','13900000038','S000019','T000005','CDHS000010','1'),
	('HSR000039','2020年1月25日益州小学学生健康调查问卷0039','2020-02-09 08:03:25','张三0039','南山中学','教科院一年级5班','李天一','A01','张飞','13900000039','S000020','T000005','CDHS000010','1'),
	('HSR000040','2020年1月25日益州小学学生健康调查问卷0040','2020-01-22 14:34:21','张三0040','益州小学','教科院二年级3班','刘婵','A01','张翼德','13900000040','S000020','T000005','CDHS000010','1'),
	('HSR000041','2020年1月25日益州小学学生健康调查问卷0041','2020-01-27 18:03:06','张三0041','大源中学','教科院一年级5班','刘阿斗','A01','刘备','13900000041','S000021','T000006','CDHS000011','1'),
	('HSR000042','2020年1月25日益州小学学生健康调查问卷0042','2020-01-26 11:35:44','张三0042','南山中学','教科院二年级3班','李天一','A01','刘玄德','13900000042','S000021','T000006','CDHS000011','1'),
	('HSR000043','2020年1月25日益州小学学生健康调查问卷0043','2020-02-07 17:32:28','张三0043','益州小学','教科院一年级5班','刘婵','A01','张飞','13900000043','S000022','T000006','CDHS000011','1'),
	('HSR000044','2020年1月25日益州小学学生健康调查问卷0044','2020-01-23 21:26:50','张三0044','大源中学','教科院二年级3班','刘阿斗','A01','张翼德','13900000044','S000022','T000006','CDHS000011','1'),
	('HSR000045','2020年1月25日益州小学学生健康调查问卷0045','2020-01-30 20:53:11','张三0045','南山中学','教科院一年级5班','李天一','A01','刘备','13900000045','S000023','T000006','CDHS000012','1'),
	('HSR000046','2020年1月25日益州小学学生健康调查问卷0046','2020-01-31 00:45:01','张三0046','益州小学','教科院二年级3班','刘婵','A01','刘玄德','13900000046','S000023','T000006','CDHS000012','1'),
	('HSR000047','2020年1月25日益州小学学生健康调查问卷0047','2020-02-11 17:07:15','张三0047','大源中学','教科院一年级5班','刘阿斗','A01','张飞','13900000047','S000024','T000006','CDHS000012','1'),
	('HSR000048','2020年1月25日益州小学学生健康调查问卷0048','2020-02-05 06:25:30','张三0048','南山中学','教科院二年级3班','李天一','A01','张翼德','13900000048','S000024','T000006','CDHS000012','1'),
	('HSR000049','2020年1月25日益州小学学生健康调查问卷0049','2020-01-26 05:09:05','张三0049','益州小学','教科院一年级5班','刘婵','A01','刘备','13900000049','S000025','T000007','CDHS000013','1'),
	('HSR000050','2020年1月25日益州小学学生健康调查问卷0050','2020-01-30 12:34:57','张三0050','大源中学','教科院二年级3班','刘阿斗','A01','刘玄德','13900000050','S000025','T000007','CDHS000013','1'),
	('HSR000051','2020年1月25日益州小学学生健康调查问卷0051','2020-02-06 00:07:18','张三0051','南山中学','教科院一年级5班','李天一','A01','张飞','13900000051','S000026','T000007','CDHS000013','1'),
	('HSR000052','2020年1月25日益州小学学生健康调查问卷0052','2020-01-29 22:20:54','张三0052','益州小学','教科院二年级3班','刘婵','A01','张翼德','13900000052','S000026','T000007','CDHS000013','1'),
	('HSR000053','2020年1月25日益州小学学生健康调查问卷0053','2020-01-24 10:56:26','张三0053','大源中学','教科院一年级5班','刘阿斗','A01','刘备','13900000053','S000027','T000007','CDHS000014','1'),
	('HSR000054','2020年1月25日益州小学学生健康调查问卷0054','2020-01-22 17:34:59','张三0054','南山中学','教科院二年级3班','李天一','A01','刘玄德','13900000054','S000027','T000007','CDHS000014','1'),
	('HSR000055','2020年1月25日益州小学学生健康调查问卷0055','2020-02-08 12:01:28','张三0055','益州小学','教科院一年级5班','刘婵','A01','张飞','13900000055','S000028','T000007','CDHS000014','1'),
	('HSR000056','2020年1月25日益州小学学生健康调查问卷0056','2020-01-30 08:35:28','张三0056','大源中学','教科院二年级3班','刘阿斗','A01','张翼德','13900000056','S000028','T000007','CDHS000014','1'),
	('HSR000057','2020年1月25日益州小学学生健康调查问卷0057','2020-01-24 23:17:36','张三0057','南山中学','教科院一年级5班','李天一','A01','刘备','13900000057','S000029','T000008','CDHS000015','1'),
	('HSR000058','2020年1月25日益州小学学生健康调查问卷0058','2020-01-31 17:37:34','张三0058','益州小学','教科院二年级3班','刘婵','A01','刘玄德','13900000058','S000029','T000008','CDHS000015','1'),
	('HSR000059','2020年1月25日益州小学学生健康调查问卷0059','2020-02-02 03:07:54','张三0059','大源中学','教科院一年级5班','刘阿斗','A01','张飞','13900000059','S000030','T000008','CDHS000015','1'),
	('HSR000060','2020年1月25日益州小学学生健康调查问卷0060','2020-02-08 17:43:23','张三0060','南山中学','教科院二年级3班','李天一','A01','张翼德','13900000060','S000030','T000008','CDHS000015','1'),
	('HSR000061','2020年1月25日益州小学学生健康调查问卷0061','2020-01-22 05:26:29','张三0061','益州小学','教科院一年级5班','刘婵','A01','刘备','13900000061','S000031','T000008','CDHS000016','1'),
	('HSR000062','2020年1月25日益州小学学生健康调查问卷0062','2020-01-24 19:38:13','张三0062','大源中学','教科院二年级3班','刘阿斗','A01','刘玄德','13900000062','S000031','T000008','CDHS000016','1'),
	('HSR000063','2020年1月25日益州小学学生健康调查问卷0063','2020-01-23 10:35:56','张三0063','南山中学','教科院一年级5班','李天一','A01','张飞','13900000063','S000032','T000008','CDHS000016','1'),
	('HSR000064','2020年1月25日益州小学学生健康调查问卷0064','2020-01-28 18:05:39','张三0064','益州小学','教科院二年级3班','刘婵','A01','张翼德','13900000064','S000032','T000008','CDHS000016','1');

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
	('SA000044','HSR000011','SDA000022','节假日是否到过武汉0044','B','1'),
	('SA000045','HSR000012','SDA000023','节假日是否到过武汉0045','A','1'),
	('SA000046','HSR000012','SDA000023','节假日是否到过武汉0046','B','1'),
	('SA000047','HSR000012','SDA000024','节假日是否到过武汉0047','A','1'),
	('SA000048','HSR000012','SDA000024','节假日是否到过武汉0048','B','1'),
	('SA000049','HSR000013','SDA000025','节假日是否到过武汉0049','A','1'),
	('SA000050','HSR000013','SDA000025','节假日是否到过武汉0050','B','1'),
	('SA000051','HSR000013','SDA000026','节假日是否到过武汉0051','A','1'),
	('SA000052','HSR000013','SDA000026','节假日是否到过武汉0052','B','1'),
	('SA000053','HSR000014','SDA000027','节假日是否到过武汉0053','A','1'),
	('SA000054','HSR000014','SDA000027','节假日是否到过武汉0054','B','1'),
	('SA000055','HSR000014','SDA000028','节假日是否到过武汉0055','A','1'),
	('SA000056','HSR000014','SDA000028','节假日是否到过武汉0056','B','1'),
	('SA000057','HSR000015','SDA000029','节假日是否到过武汉0057','A','1'),
	('SA000058','HSR000015','SDA000029','节假日是否到过武汉0058','B','1'),
	('SA000059','HSR000015','SDA000030','节假日是否到过武汉0059','A','1'),
	('SA000060','HSR000015','SDA000030','节假日是否到过武汉0060','B','1'),
	('SA000061','HSR000016','SDA000031','节假日是否到过武汉0061','A','1'),
	('SA000062','HSR000016','SDA000031','节假日是否到过武汉0062','B','1'),
	('SA000063','HSR000016','SDA000032','节假日是否到过武汉0063','A','1'),
	('SA000064','HSR000016','SDA000032','节假日是否到过武汉0064','B','1'),
	('SA000065','HSR000017','SDA000033','节假日是否到过武汉0065','A','1'),
	('SA000066','HSR000017','SDA000033','节假日是否到过武汉0066','B','1'),
	('SA000067','HSR000017','SDA000034','节假日是否到过武汉0067','A','1'),
	('SA000068','HSR000017','SDA000034','节假日是否到过武汉0068','B','1'),
	('SA000069','HSR000018','SDA000035','节假日是否到过武汉0069','A','1'),
	('SA000070','HSR000018','SDA000035','节假日是否到过武汉0070','B','1'),
	('SA000071','HSR000018','SDA000036','节假日是否到过武汉0071','A','1'),
	('SA000072','HSR000018','SDA000036','节假日是否到过武汉0072','B','1'),
	('SA000073','HSR000019','SDA000037','节假日是否到过武汉0073','A','1'),
	('SA000074','HSR000019','SDA000037','节假日是否到过武汉0074','B','1'),
	('SA000075','HSR000019','SDA000038','节假日是否到过武汉0075','A','1'),
	('SA000076','HSR000019','SDA000038','节假日是否到过武汉0076','B','1'),
	('SA000077','HSR000020','SDA000039','节假日是否到过武汉0077','A','1'),
	('SA000078','HSR000020','SDA000039','节假日是否到过武汉0078','B','1'),
	('SA000079','HSR000020','SDA000040','节假日是否到过武汉0079','A','1'),
	('SA000080','HSR000020','SDA000040','节假日是否到过武汉0080','B','1'),
	('SA000081','HSR000021','SDA000041','节假日是否到过武汉0081','A','1'),
	('SA000082','HSR000021','SDA000041','节假日是否到过武汉0082','B','1'),
	('SA000083','HSR000021','SDA000042','节假日是否到过武汉0083','A','1'),
	('SA000084','HSR000021','SDA000042','节假日是否到过武汉0084','B','1'),
	('SA000085','HSR000022','SDA000043','节假日是否到过武汉0085','A','1'),
	('SA000086','HSR000022','SDA000043','节假日是否到过武汉0086','B','1'),
	('SA000087','HSR000022','SDA000044','节假日是否到过武汉0087','A','1'),
	('SA000088','HSR000022','SDA000044','节假日是否到过武汉0088','B','1'),
	('SA000089','HSR000023','SDA000045','节假日是否到过武汉0089','A','1'),
	('SA000090','HSR000023','SDA000045','节假日是否到过武汉0090','B','1'),
	('SA000091','HSR000023','SDA000046','节假日是否到过武汉0091','A','1'),
	('SA000092','HSR000023','SDA000046','节假日是否到过武汉0092','B','1'),
	('SA000093','HSR000024','SDA000047','节假日是否到过武汉0093','A','1'),
	('SA000094','HSR000024','SDA000047','节假日是否到过武汉0094','B','1'),
	('SA000095','HSR000024','SDA000048','节假日是否到过武汉0095','A','1'),
	('SA000096','HSR000024','SDA000048','节假日是否到过武汉0096','B','1'),
	('SA000097','HSR000025','SDA000049','节假日是否到过武汉0097','A','1'),
	('SA000098','HSR000025','SDA000049','节假日是否到过武汉0098','B','1'),
	('SA000099','HSR000025','SDA000050','节假日是否到过武汉0099','A','1'),
	('SA000100','HSR000025','SDA000050','节假日是否到过武汉0100','B','1'),
	('SA000101','HSR000026','SDA000051','节假日是否到过武汉0101','A','1'),
	('SA000102','HSR000026','SDA000051','节假日是否到过武汉0102','B','1'),
	('SA000103','HSR000026','SDA000052','节假日是否到过武汉0103','A','1'),
	('SA000104','HSR000026','SDA000052','节假日是否到过武汉0104','B','1'),
	('SA000105','HSR000027','SDA000053','节假日是否到过武汉0105','A','1'),
	('SA000106','HSR000027','SDA000053','节假日是否到过武汉0106','B','1'),
	('SA000107','HSR000027','SDA000054','节假日是否到过武汉0107','A','1'),
	('SA000108','HSR000027','SDA000054','节假日是否到过武汉0108','B','1'),
	('SA000109','HSR000028','SDA000055','节假日是否到过武汉0109','A','1'),
	('SA000110','HSR000028','SDA000055','节假日是否到过武汉0110','B','1'),
	('SA000111','HSR000028','SDA000056','节假日是否到过武汉0111','A','1'),
	('SA000112','HSR000028','SDA000056','节假日是否到过武汉0112','B','1'),
	('SA000113','HSR000029','SDA000057','节假日是否到过武汉0113','A','1'),
	('SA000114','HSR000029','SDA000057','节假日是否到过武汉0114','B','1'),
	('SA000115','HSR000029','SDA000058','节假日是否到过武汉0115','A','1'),
	('SA000116','HSR000029','SDA000058','节假日是否到过武汉0116','B','1'),
	('SA000117','HSR000030','SDA000059','节假日是否到过武汉0117','A','1'),
	('SA000118','HSR000030','SDA000059','节假日是否到过武汉0118','B','1'),
	('SA000119','HSR000030','SDA000060','节假日是否到过武汉0119','A','1'),
	('SA000120','HSR000030','SDA000060','节假日是否到过武汉0120','B','1'),
	('SA000121','HSR000031','SDA000061','节假日是否到过武汉0121','A','1'),
	('SA000122','HSR000031','SDA000061','节假日是否到过武汉0122','B','1'),
	('SA000123','HSR000031','SDA000062','节假日是否到过武汉0123','A','1'),
	('SA000124','HSR000031','SDA000062','节假日是否到过武汉0124','B','1'),
	('SA000125','HSR000032','SDA000063','节假日是否到过武汉0125','A','1'),
	('SA000126','HSR000032','SDA000063','节假日是否到过武汉0126','B','1'),
	('SA000127','HSR000032','SDA000064','节假日是否到过武汉0127','A','1'),
	('SA000128','HSR000032','SDA000064','节假日是否到过武汉0128','B','1'),
	('SA000129','HSR000033','SDA000065','节假日是否到过武汉0129','A','1'),
	('SA000130','HSR000033','SDA000065','节假日是否到过武汉0130','B','1'),
	('SA000131','HSR000033','SDA000066','节假日是否到过武汉0131','A','1'),
	('SA000132','HSR000033','SDA000066','节假日是否到过武汉0132','B','1'),
	('SA000133','HSR000034','SDA000067','节假日是否到过武汉0133','A','1'),
	('SA000134','HSR000034','SDA000067','节假日是否到过武汉0134','B','1'),
	('SA000135','HSR000034','SDA000068','节假日是否到过武汉0135','A','1'),
	('SA000136','HSR000034','SDA000068','节假日是否到过武汉0136','B','1'),
	('SA000137','HSR000035','SDA000069','节假日是否到过武汉0137','A','1'),
	('SA000138','HSR000035','SDA000069','节假日是否到过武汉0138','B','1'),
	('SA000139','HSR000035','SDA000070','节假日是否到过武汉0139','A','1'),
	('SA000140','HSR000035','SDA000070','节假日是否到过武汉0140','B','1'),
	('SA000141','HSR000036','SDA000071','节假日是否到过武汉0141','A','1'),
	('SA000142','HSR000036','SDA000071','节假日是否到过武汉0142','B','1'),
	('SA000143','HSR000036','SDA000072','节假日是否到过武汉0143','A','1'),
	('SA000144','HSR000036','SDA000072','节假日是否到过武汉0144','B','1'),
	('SA000145','HSR000037','SDA000073','节假日是否到过武汉0145','A','1'),
	('SA000146','HSR000037','SDA000073','节假日是否到过武汉0146','B','1'),
	('SA000147','HSR000037','SDA000074','节假日是否到过武汉0147','A','1'),
	('SA000148','HSR000037','SDA000074','节假日是否到过武汉0148','B','1'),
	('SA000149','HSR000038','SDA000075','节假日是否到过武汉0149','A','1'),
	('SA000150','HSR000038','SDA000075','节假日是否到过武汉0150','B','1'),
	('SA000151','HSR000038','SDA000076','节假日是否到过武汉0151','A','1'),
	('SA000152','HSR000038','SDA000076','节假日是否到过武汉0152','B','1'),
	('SA000153','HSR000039','SDA000077','节假日是否到过武汉0153','A','1'),
	('SA000154','HSR000039','SDA000077','节假日是否到过武汉0154','B','1'),
	('SA000155','HSR000039','SDA000078','节假日是否到过武汉0155','A','1'),
	('SA000156','HSR000039','SDA000078','节假日是否到过武汉0156','B','1'),
	('SA000157','HSR000040','SDA000079','节假日是否到过武汉0157','A','1'),
	('SA000158','HSR000040','SDA000079','节假日是否到过武汉0158','B','1'),
	('SA000159','HSR000040','SDA000080','节假日是否到过武汉0159','A','1'),
	('SA000160','HSR000040','SDA000080','节假日是否到过武汉0160','B','1'),
	('SA000161','HSR000041','SDA000081','节假日是否到过武汉0161','A','1'),
	('SA000162','HSR000041','SDA000081','节假日是否到过武汉0162','B','1'),
	('SA000163','HSR000041','SDA000082','节假日是否到过武汉0163','A','1'),
	('SA000164','HSR000041','SDA000082','节假日是否到过武汉0164','B','1'),
	('SA000165','HSR000042','SDA000083','节假日是否到过武汉0165','A','1'),
	('SA000166','HSR000042','SDA000083','节假日是否到过武汉0166','B','1'),
	('SA000167','HSR000042','SDA000084','节假日是否到过武汉0167','A','1'),
	('SA000168','HSR000042','SDA000084','节假日是否到过武汉0168','B','1'),
	('SA000169','HSR000043','SDA000085','节假日是否到过武汉0169','A','1'),
	('SA000170','HSR000043','SDA000085','节假日是否到过武汉0170','B','1'),
	('SA000171','HSR000043','SDA000086','节假日是否到过武汉0171','A','1'),
	('SA000172','HSR000043','SDA000086','节假日是否到过武汉0172','B','1'),
	('SA000173','HSR000044','SDA000087','节假日是否到过武汉0173','A','1'),
	('SA000174','HSR000044','SDA000087','节假日是否到过武汉0174','B','1'),
	('SA000175','HSR000044','SDA000088','节假日是否到过武汉0175','A','1'),
	('SA000176','HSR000044','SDA000088','节假日是否到过武汉0176','B','1'),
	('SA000177','HSR000045','SDA000089','节假日是否到过武汉0177','A','1'),
	('SA000178','HSR000045','SDA000089','节假日是否到过武汉0178','B','1'),
	('SA000179','HSR000045','SDA000090','节假日是否到过武汉0179','A','1'),
	('SA000180','HSR000045','SDA000090','节假日是否到过武汉0180','B','1'),
	('SA000181','HSR000046','SDA000091','节假日是否到过武汉0181','A','1'),
	('SA000182','HSR000046','SDA000091','节假日是否到过武汉0182','B','1'),
	('SA000183','HSR000046','SDA000092','节假日是否到过武汉0183','A','1'),
	('SA000184','HSR000046','SDA000092','节假日是否到过武汉0184','B','1'),
	('SA000185','HSR000047','SDA000093','节假日是否到过武汉0185','A','1'),
	('SA000186','HSR000047','SDA000093','节假日是否到过武汉0186','B','1'),
	('SA000187','HSR000047','SDA000094','节假日是否到过武汉0187','A','1'),
	('SA000188','HSR000047','SDA000094','节假日是否到过武汉0188','B','1'),
	('SA000189','HSR000048','SDA000095','节假日是否到过武汉0189','A','1'),
	('SA000190','HSR000048','SDA000095','节假日是否到过武汉0190','B','1'),
	('SA000191','HSR000048','SDA000096','节假日是否到过武汉0191','A','1'),
	('SA000192','HSR000048','SDA000096','节假日是否到过武汉0192','B','1'),
	('SA000193','HSR000049','SDA000097','节假日是否到过武汉0193','A','1'),
	('SA000194','HSR000049','SDA000097','节假日是否到过武汉0194','B','1'),
	('SA000195','HSR000049','SDA000098','节假日是否到过武汉0195','A','1'),
	('SA000196','HSR000049','SDA000098','节假日是否到过武汉0196','B','1'),
	('SA000197','HSR000050','SDA000099','节假日是否到过武汉0197','A','1'),
	('SA000198','HSR000050','SDA000099','节假日是否到过武汉0198','B','1'),
	('SA000199','HSR000050','SDA000100','节假日是否到过武汉0199','A','1'),
	('SA000200','HSR000050','SDA000100','节假日是否到过武汉0200','B','1'),
	('SA000201','HSR000051','SDA000101','节假日是否到过武汉0201','A','1'),
	('SA000202','HSR000051','SDA000101','节假日是否到过武汉0202','B','1'),
	('SA000203','HSR000051','SDA000102','节假日是否到过武汉0203','A','1'),
	('SA000204','HSR000051','SDA000102','节假日是否到过武汉0204','B','1'),
	('SA000205','HSR000052','SDA000103','节假日是否到过武汉0205','A','1'),
	('SA000206','HSR000052','SDA000103','节假日是否到过武汉0206','B','1'),
	('SA000207','HSR000052','SDA000104','节假日是否到过武汉0207','A','1'),
	('SA000208','HSR000052','SDA000104','节假日是否到过武汉0208','B','1'),
	('SA000209','HSR000053','SDA000105','节假日是否到过武汉0209','A','1'),
	('SA000210','HSR000053','SDA000105','节假日是否到过武汉0210','B','1'),
	('SA000211','HSR000053','SDA000106','节假日是否到过武汉0211','A','1'),
	('SA000212','HSR000053','SDA000106','节假日是否到过武汉0212','B','1'),
	('SA000213','HSR000054','SDA000107','节假日是否到过武汉0213','A','1'),
	('SA000214','HSR000054','SDA000107','节假日是否到过武汉0214','B','1'),
	('SA000215','HSR000054','SDA000108','节假日是否到过武汉0215','A','1'),
	('SA000216','HSR000054','SDA000108','节假日是否到过武汉0216','B','1'),
	('SA000217','HSR000055','SDA000109','节假日是否到过武汉0217','A','1'),
	('SA000218','HSR000055','SDA000109','节假日是否到过武汉0218','B','1'),
	('SA000219','HSR000055','SDA000110','节假日是否到过武汉0219','A','1'),
	('SA000220','HSR000055','SDA000110','节假日是否到过武汉0220','B','1'),
	('SA000221','HSR000056','SDA000111','节假日是否到过武汉0221','A','1'),
	('SA000222','HSR000056','SDA000111','节假日是否到过武汉0222','B','1'),
	('SA000223','HSR000056','SDA000112','节假日是否到过武汉0223','A','1'),
	('SA000224','HSR000056','SDA000112','节假日是否到过武汉0224','B','1'),
	('SA000225','HSR000057','SDA000113','节假日是否到过武汉0225','A','1'),
	('SA000226','HSR000057','SDA000113','节假日是否到过武汉0226','B','1'),
	('SA000227','HSR000057','SDA000114','节假日是否到过武汉0227','A','1'),
	('SA000228','HSR000057','SDA000114','节假日是否到过武汉0228','B','1'),
	('SA000229','HSR000058','SDA000115','节假日是否到过武汉0229','A','1'),
	('SA000230','HSR000058','SDA000115','节假日是否到过武汉0230','B','1'),
	('SA000231','HSR000058','SDA000116','节假日是否到过武汉0231','A','1'),
	('SA000232','HSR000058','SDA000116','节假日是否到过武汉0232','B','1'),
	('SA000233','HSR000059','SDA000117','节假日是否到过武汉0233','A','1'),
	('SA000234','HSR000059','SDA000117','节假日是否到过武汉0234','B','1'),
	('SA000235','HSR000059','SDA000118','节假日是否到过武汉0235','A','1'),
	('SA000236','HSR000059','SDA000118','节假日是否到过武汉0236','B','1'),
	('SA000237','HSR000060','SDA000119','节假日是否到过武汉0237','A','1'),
	('SA000238','HSR000060','SDA000119','节假日是否到过武汉0238','B','1'),
	('SA000239','HSR000060','SDA000120','节假日是否到过武汉0239','A','1'),
	('SA000240','HSR000060','SDA000120','节假日是否到过武汉0240','B','1'),
	('SA000241','HSR000061','SDA000121','节假日是否到过武汉0241','A','1'),
	('SA000242','HSR000061','SDA000121','节假日是否到过武汉0242','B','1'),
	('SA000243','HSR000061','SDA000122','节假日是否到过武汉0243','A','1'),
	('SA000244','HSR000061','SDA000122','节假日是否到过武汉0244','B','1'),
	('SA000245','HSR000062','SDA000123','节假日是否到过武汉0245','A','1'),
	('SA000246','HSR000062','SDA000123','节假日是否到过武汉0246','B','1'),
	('SA000247','HSR000062','SDA000124','节假日是否到过武汉0247','A','1'),
	('SA000248','HSR000062','SDA000124','节假日是否到过武汉0248','B','1'),
	('SA000249','HSR000063','SDA000125','节假日是否到过武汉0249','A','1'),
	('SA000250','HSR000063','SDA000125','节假日是否到过武汉0250','B','1'),
	('SA000251','HSR000063','SDA000126','节假日是否到过武汉0251','A','1'),
	('SA000252','HSR000063','SDA000126','节假日是否到过武汉0252','B','1'),
	('SA000253','HSR000064','SDA000127','节假日是否到过武汉0253','A','1'),
	('SA000254','HSR000064','SDA000127','节假日是否到过武汉0254','B','1'),
	('SA000255','HSR000064','SDA000128','节假日是否到过武汉0255','A','1'),
	('SA000256','HSR000064','SDA000128','节假日是否到过武汉0256','B','1');

insert into user_data values
	('U000001','张三','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','2020-01-21 04:22:59','P000001','1'),
	('U000002','李四','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','2020-01-23 15:54:21','P000001','1');

insert into wechat_login_info_data values
	('WLI000001','U000001','user123','user123','session123','2020-02-09 17:19:18','1'),
	('WLI000002','U000001','user1230002','user1230002','session1230002','2020-01-28 16:36:41','1'),
	('WLI000003','U000002','user1230003','user1230003','session1230003','2020-02-10 20:44:00','1'),
	('WLI000004','U000002','user1230004','user1230004','session1230004','2020-02-11 10:49:07','1');

insert into change_request_data values
	('CR000001','答题','2020-01-25 18:23:05','8.8.8.8','ADD_CLASS','P000001','1'),
	('CR000002','答题0002','2020-01-31 14:19:45','8.8.8.8','ADD_STUDENT','P000001','1'),
	('CR000003','答题0003','2020-01-30 12:19:09','8.8.8.8','PUBLISH_SURVEY','P000001','1'),
	('CR000004','答题0004','2020-01-23 04:07:26','8.8.8.8','FILL_SURVEY','P000001','1');

insert into change_request_type_data values
	('ADD_CLASS','添加班级','ADD_CLASS','book','1','    一段样例文字。    一段样例文字。\n可以分段。可以分段。\n\n可以空行。可以空行。\n\n','    一段样例文字。    一段样例文字。\n可以分段。可以分段。\n\n可以空行。可以空行。\n\n','P000001','1'),
	('ADD_STUDENT','添加学生','ADD_STUDENT','add','2','    一段样例文字。    一段样例文字。\n可以分段。可以分段。\n\n可以空行。可以空行。\n\n','    一段样例文字。    一段样例文字。\n可以分段。可以分段。\n\n可以空行。可以空行。\n\n','P000001','1'),
	('PUBLISH_SURVEY','发布问卷','PUBLISH_SURVEY','add','3','    一段样例文字。    一段样例文字。\n可以分段。可以分段。\n\n可以空行。可以空行。\n\n','    一段样例文字。    一段样例文字。\n可以分段。可以分段。\n\n可以空行。可以空行。\n\n','P000001','1'),
	('FILL_SURVEY','填写问卷','FILL_SURVEY','swap','4','    一段样例文字。    一段样例文字。\n可以分段。可以分段。\n\n可以空行。可以空行。\n\n','    一段样例文字。    一段样例文字。\n可以分段。可以分段。\n\n可以空行。可以空行。\n\n','P000001','1');

insert into wechat_workapp_identify_data values
	('WWI000001','corporation123','user123','SU000001','2020-02-11 15:24:32','2020-02-11 15:13:57','1'),
	('WWI000002','corporation1230002','user1230002','SU000001','2020-02-05 07:43:41','2020-01-22 05:25:50','1'),
	('WWI000003','corporation1230003','user1230003','SU000002','2020-01-25 15:59:32','2020-02-03 03:46:41','1'),
	('WWI000004','corporation1230004','user1230004','SU000002','2020-01-31 13:11:36','2020-01-28 20:38:07','1');

insert into wechat_miniapp_identify_data values
	('WMI000001','wechat_open_id_1234567890','wechat_miniapp_id_1234567890','SU000001','2020-01-21 17:06:56','2020-01-24 13:22:33','1'),
	('WMI000002','wechat_open_id_12345678900002','wechat_miniapp_id_12345678900002','SU000001','2020-01-28 00:35:33','2020-01-30 22:23:50','1'),
	('WMI000003','wechat_open_id_12345678900003','wechat_miniapp_id_12345678900003','SU000002','2020-02-10 00:17:55','2020-02-01 02:49:49','1'),
	('WMI000004','wechat_open_id_12345678900004','wechat_miniapp_id_12345678900004','SU000002','2020-01-31 18:45:12','2020-02-07 11:14:19','1');







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


insert into change_request_type_data values
('ANSWER_SURVEY','ANSWER_SURVEY','ANSWER_SURVEY','user','5','Platform','{}','P000001',1),
('REGISTER_TEACHER','REGISTER_TEACHER','REGISTER_TEACHER','user','6','Platform','{}','P000001',1),
('CREATE_SURVEY','CREATE_SURVEY','CREATE_SURVEY','user','7','Platform','{}','P000001',1),
('CREATE_QUESTION','CREATE_QUESTION','CREATE_QUESTION','user','8','Platform','{}','P000001',1);

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

