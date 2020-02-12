
-- BUILD WITH MODEL TIME 200211T1216
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
	('P000001','四川省','P000001','2020-01-27 03:51:54','1'),
	('P000002','四川省0002','P000001','2020-02-11 09:51:27','1');

insert into city_data values
	('C000001','成都市','P000001','P000001','2020-01-30 02:34:04','1'),
	('C000002','成都市0002','P000001','P000001','2020-01-26 03:01:57','1'),
	('C000003','成都市0003','P000002','P000001','2020-02-07 19:58:08','1'),
	('C000004','成都市0004','P000002','P000001','2020-02-06 17:40:14','1');

insert into district_data values
	('D000001','高新区','C000001','P000001','2020-01-22 21:36:05','1'),
	('D000002','高新区0002','C000001','P000001','2020-02-05 01:50:03','1'),
	('D000003','高新区0003','C000002','P000001','2020-02-06 00:55:11','1'),
	('D000004','高新区0004','C000002','P000001','2020-01-20 19:54:45','1'),
	('D000005','高新区0005','C000003','P000001','2020-02-10 16:45:05','1'),
	('D000006','高新区0006','C000003','P000001','2020-02-04 06:07:31','1'),
	('D000007','高新区0007','C000004','P000001','2020-02-07 23:08:43','1'),
	('D000008','高新区0008','C000004','P000001','2020-01-31 17:37:33','1');

insert into location_data values
	('L000001','公司地址','四川省成都市高新区南华路100号','D000001','P000001','30.878248146764637','104.17361176225624','1'),
	('L000002','家庭地址','四川省成都市高新区南华路100号0002','D000001','P000001','29.72728032357483','103.73222200084716','1'),
	('L000003','公司地址','四川省成都市高新区南华路100号0003','D000002','P000001','30.835602211418383','104.67794128745841','1'),
	('L000004','家庭地址','四川省成都市高新区南华路100号0004','D000002','P000001','29.400519193744774','104.3967954347809','1'),
	('L000005','公司地址','四川省成都市高新区南华路100号0005','D000003','P000001','32.194945122493024','104.54309284149372','1'),
	('L000006','家庭地址','四川省成都市高新区南华路100号0006','D000003','P000001','30.494671926433597','104.14321700489658','1'),
	('L000007','公司地址','四川省成都市高新区南华路100号0007','D000004','P000001','31.508150439925092','105.3006867759589','1'),
	('L000008','家庭地址','四川省成都市高新区南华路100号0008','D000004','P000001','30.840255198460305','105.37273644642892','1'),
	('L000009','公司地址','四川省成都市高新区南华路100号0009','D000005','P000002','29.712600236145875','104.76286431660941','1'),
	('L000010','家庭地址','四川省成都市高新区南华路100号0010','D000005','P000002','29.396980207575012','104.5970559070688','1'),
	('L000011','公司地址','四川省成都市高新区南华路100号0011','D000006','P000002','31.797569680577304','103.24929024842803','1'),
	('L000012','家庭地址','四川省成都市高新区南华路100号0012','D000006','P000002','31.028157691257007','103.53944641178587','1'),
	('L000013','公司地址','四川省成都市高新区南华路100号0013','D000007','P000002','30.093809583593494','103.63818228638306','1'),
	('L000014','家庭地址','四川省成都市高新区南华路100号0014','D000007','P000002','32.30722126457327','105.83246010345283','1'),
	('L000015','公司地址','四川省成都市高新区南华路100号0015','D000008','P000002','29.488299572187092','103.99524400238329','1'),
	('L000016','家庭地址','四川省成都市高新区南华路100号0016','D000008','P000002','30.435196695461755','105.3859368318842','1');


insert into question_data values
	('Q000001','节假日是否到过武汉','SINGLE_SELECT','是','否',null,null,'P000001',null,null,'1'),
	('Q000002','家里是否有武汉的亲朋好友来访','SINGLE_SELECT','有','没有',null,null,'P000001',null,null,'1'),
	('Q000003','是否有发热、发烧症状','SINGLE_SELECT','没有','有',null,null,'P000001',null,null,'1'),
	('Q000004','今日体温','TEXT_INPUT',null,null,null,null,'P000001',null,null,'1');

insert into question_type_data values
	('SINGLE_SELECT','单选题','SINGLE_SELECT','P000001','1'),
	('TEXT_INPUT','简答题','TEXT_INPUT','P000001','1');



insert into survey_status_data values
	('UN_SUBMITTED','未提交','UN_SUBMITTED','P000001','1'),
	('SUBMITTE','已提交','SUBMITTE','P000001','1'),
	('DRAFT','草稿','DRAFT','P000001','1');


insert into user_data values
	('U000001','张三','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','2020-01-31 20:33:35','P000001','1'),
	('U000002','李四','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','2020-01-25 05:27:02','P000001','1');

insert into wechat_login_info_data values
	('WLI000001','U000001','user123','user123','session123','2020-02-04 02:26:19','1'),
	('WLI000002','U000001','user1230002','user1230002','session1230002','2020-01-26 01:46:04','1'),
	('WLI000003','U000002','user1230003','user1230003','session1230003','2020-02-05 12:18:34','1'),
	('WLI000004','U000002','user1230004','user1230004','session1230004','2020-02-05 16:57:28','1');



insert into change_request_type_data values
	('ADD_CLASS','添加班级','ADD_CLASS','book','1','    一段样例文字。    一段样例文字。\n可以分段。可以分段。\n\n可以空行。可以空行。\n\n','    一段样例文字。    一段样例文字。\n可以分段。可以分段。\n\n可以空行。可以空行。\n\n','P000001','1'),
	('ADD_STUDENT','添加学生','ADD_STUDENT','add','2','    一段样例文字。    一段样例文字。\n可以分段。可以分段。\n\n可以空行。可以空行。\n\n','    一段样例文字。    一段样例文字。\n可以分段。可以分段。\n\n可以空行。可以空行。\n\n','P000001','1'),
	('PUBLISH_SURVEY','发布问卷','PUBLISH_SURVEY','add','3','    一段样例文字。    一段样例文字。\n可以分段。可以分段。\n\n可以空行。可以空行。\n\n','    一段样例文字。    一段样例文字。\n可以分段。可以分段。\n\n可以空行。可以空行。\n\n','P000001','1'),
	('FILL_SURVEY','填写问卷','FILL_SURVEY','swap','4','    一段样例文字。    一段样例文字。\n可以分段。可以分段。\n\n可以空行。可以空行。\n\n','    一段样例文字。    一段样例文字。\n可以分段。可以分段。\n\n可以空行。可以空行。\n\n','P000001','1');







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

