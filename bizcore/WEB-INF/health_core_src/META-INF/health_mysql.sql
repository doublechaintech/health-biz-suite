
-- BUILD WITH MODEL TIME 200126T1427
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
	school                        	varchar(16)                              comment '学校',
	school_class                  	varchar(99)                              comment '学校类',
	create_time                   	datetime                                 comment '创建时间',
	platform                      	varchar(48)                              comment '平台',
	cq                            	varchar(48)                              comment 'Cq',
	version                       	int                                      comment '版本'
	
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = "老师";
-- primary key will be created later for better import performance

drop table  if exists student_data;
create table student_data (
	id                            	varchar(48)          not null            comment 'ID',
	student_name                  	varchar(20)                              comment '学生的名字',
	student_id                    	varchar(3)                               comment '学生证',
	guardian_name                 	varchar(20)                              comment '监护人姓名',
	guardian_mobile               	varchar(44)                              comment '监护人手机',
	address                       	varchar(48)                              comment '地址',
	user                          	varchar(48)                              comment '用户',
	create_time                   	datetime                                 comment '创建时间',
	platform                      	varchar(48)                              comment '平台',
	cq                            	varchar(48)                              comment 'Cq',
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
	code                          	varchar(48)                              comment '编码',
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
	name                          	varchar(100)                             comment '名称',
	code                          	varchar(20)                              comment '编码',
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
	('P000001','四川省','P000001','2020-01-06 21:12:18','1'),
	('P000002','四川省0002','P000001','2020-01-10 00:55:26','1');

insert into city_data values
	('C000001','成都市','P000001','P000001','2020-01-19 12:27:18','1'),
	('C000002','成都市0002','P000001','P000001','2020-01-12 20:18:23','1'),
	('C000003','成都市0003','P000002','P000001','2020-01-17 22:22:48','1'),
	('C000004','成都市0004','P000002','P000001','2020-01-11 22:12:15','1');

insert into district_data values
	('D000001','高新区','C000001','P000001','2020-01-05 09:15:21','1'),
	('D000002','高新区0002','C000001','P000001','2020-01-26 17:22:32','1'),
	('D000003','高新区0003','C000002','P000001','2020-01-25 21:38:25','1'),
	('D000004','高新区0004','C000002','P000001','2020-01-26 17:41:59','1'),
	('D000005','高新区0005','C000003','P000001','2020-01-21 05:48:30','1'),
	('D000006','高新区0006','C000003','P000001','2020-01-18 14:35:43','1'),
	('D000007','高新区0007','C000004','P000001','2020-01-23 11:35:39','1'),
	('D000008','高新区0008','C000004','P000001','2020-01-15 16:50:44','1');

insert into location_data values
	('L000001','公司地址','四川省成都市高新区南华路100号','D000001','P000001','30.77619268994743','105.80858149719822','1'),
	('L000002','家庭地址','四川省成都市高新区南华路100号0002','D000001','P000001','31.763880799518645','103.36822172552546','1'),
	('L000003','公司地址','四川省成都市高新区南华路100号0003','D000002','P000001','31.14271230666521','104.88418800962619','1'),
	('L000004','家庭地址','四川省成都市高新区南华路100号0004','D000002','P000001','29.502202457310567','105.42792090768813','1'),
	('L000005','公司地址','四川省成都市高新区南华路100号0005','D000003','P000001','30.223383450678','103.99944652761549','1'),
	('L000006','家庭地址','四川省成都市高新区南华路100号0006','D000003','P000001','29.486984969633852','103.62791717106938','1'),
	('L000007','公司地址','四川省成都市高新区南华路100号0007','D000004','P000001','30.591373771664262','103.26035864028321','1'),
	('L000008','家庭地址','四川省成都市高新区南华路100号0008','D000004','P000001','31.37207310213195','104.14223209037604','1'),
	('L000009','公司地址','四川省成都市高新区南华路100号0009','D000005','P000002','29.338913325357787','104.96688646246814','1'),
	('L000010','家庭地址','四川省成都市高新区南华路100号0010','D000005','P000002','29.4370994902869','104.54355270261544','1'),
	('L000011','公司地址','四川省成都市高新区南华路100号0011','D000006','P000002','31.753896313225752','104.81908386723894','1'),
	('L000012','家庭地址','四川省成都市高新区南华路100号0012','D000006','P000002','31.489288530793917','105.17291555937477','1'),
	('L000013','公司地址','四川省成都市高新区南华路100号0013','D000007','P000002','31.089793201154254','105.79532666355243','1'),
	('L000014','家庭地址','四川省成都市高新区南华路100号0014','D000007','P000002','30.9093976967204','103.6355147915878','1'),
	('L000015','公司地址','四川省成都市高新区南华路100号0015','D000008','P000002','31.45704098028365','105.7696278990407','1'),
	('L000016','家庭地址','四川省成都市高新区南华路100号0016','D000008','P000002','31.359709636124553','105.66296062989778','1');

insert into teacher_data values
	('T000001','白山水','18012341234','益州小学','教科院一年级5班','2020-01-19 09:55:58','P000001','CR000001','1'),
	('T000002','胡一刀','13900000002','大源中学','教科院二年级3班','2020-01-24 05:21:37','P000001','CR000001','1'),
	('T000003','苗人凤','13900000003','南山中学','教科院一年级5班','2020-01-12 21:05:28','P000001','CR000002','1'),
	('T000004','白山水','13900000004','益州小学','教科院二年级3班','2020-01-22 21:40:40','P000001','CR000002','1'),
	('T000005','胡一刀','13900000005','大源中学','教科院一年级5班','2020-01-15 07:41:57','P000001','CR000003','1'),
	('T000006','苗人凤','13900000006','南山中学','教科院二年级3班','2020-01-11 05:26:56','P000001','CR000003','1'),
	('T000007','白山水','13900000007','益州小学','教科院一年级5班','2020-01-09 06:06:29','P000001','CR000004','1'),
	('T000008','胡一刀','13900000008','大源中学','教科院二年级3班','2020-01-22 18:06:12','P000001','CR000004','1');

insert into student_data values
	('S000001','刘婵','A01','刘备','18012341234','L000001','U000001','2020-01-17 21:36:50','P000001','CR000001','1'),
	('S000002','刘阿斗','A01','刘玄德','13900000002','L000001','U000001','2020-01-22 03:01:38','P000001','CR000001','1'),
	('S000003','李天一','A01','张飞','13900000003','L000001','U000002','2020-01-12 20:09:19','P000001','CR000001','1'),
	('S000004','刘婵','A01','张翼德','13900000004','L000001','U000002','2020-01-16 02:37:31','P000001','CR000001','1'),
	('S000005','刘阿斗','A01','刘备','13900000005','L000002','U000003','2020-01-20 23:55:48','P000001','CR000001','1'),
	('S000006','李天一','A01','刘玄德','13900000006','L000002','U000003','2020-01-21 04:55:41','P000001','CR000001','1'),
	('S000007','刘婵','A01','张飞','13900000007','L000002','U000004','2020-01-17 10:22:45','P000001','CR000001','1'),
	('S000008','刘阿斗','A01','张翼德','13900000008','L000002','U000004','2020-01-21 18:42:20','P000001','CR000001','1'),
	('S000009','李天一','A01','刘备','13900000009','L000003','U000005','2020-01-12 15:05:54','P000001','CR000001','1'),
	('S000010','刘婵','A01','刘玄德','13900000010','L000003','U000005','2020-01-22 13:03:38','P000001','CR000001','1'),
	('S000011','刘阿斗','A01','张飞','13900000011','L000003','U000006','2020-01-22 05:40:25','P000001','CR000001','1'),
	('S000012','李天一','A01','张翼德','13900000012','L000003','U000006','2020-01-05 21:47:40','P000001','CR000001','1'),
	('S000013','刘婵','A01','刘备','13900000013','L000004','U000007','2020-01-25 15:21:50','P000001','CR000001','1'),
	('S000014','刘阿斗','A01','刘玄德','13900000014','L000004','U000007','2020-01-07 20:59:17','P000001','CR000001','1'),
	('S000015','李天一','A01','张飞','13900000015','L000004','U000008','2020-01-16 14:10:13','P000001','CR000001','1'),
	('S000016','刘婵','A01','张翼德','13900000016','L000004','U000008','2020-01-20 19:57:44','P000001','CR000001','1'),
	('S000017','刘阿斗','A01','刘备','13900000017','L000005','U000009','2020-01-05 02:57:51','P000001','CR000002','1'),
	('S000018','李天一','A01','刘玄德','13900000018','L000005','U000009','2020-01-07 09:11:38','P000001','CR000002','1'),
	('S000019','刘婵','A01','张飞','13900000019','L000005','U000010','2020-01-24 10:12:45','P000001','CR000002','1'),
	('S000020','刘阿斗','A01','张翼德','13900000020','L000005','U000010','2020-01-22 13:15:54','P000001','CR000002','1'),
	('S000021','李天一','A01','刘备','13900000021','L000006','U000011','2020-01-19 12:16:01','P000001','CR000002','1'),
	('S000022','刘婵','A01','刘玄德','13900000022','L000006','U000011','2020-01-12 10:13:32','P000001','CR000002','1'),
	('S000023','刘阿斗','A01','张飞','13900000023','L000006','U000012','2020-01-26 06:03:23','P000001','CR000002','1'),
	('S000024','李天一','A01','张翼德','13900000024','L000006','U000012','2020-01-10 16:42:25','P000001','CR000002','1'),
	('S000025','刘婵','A01','刘备','13900000025','L000007','U000013','2020-01-07 16:57:17','P000001','CR000002','1'),
	('S000026','刘阿斗','A01','刘玄德','13900000026','L000007','U000013','2020-01-05 22:27:12','P000001','CR000002','1'),
	('S000027','李天一','A01','张飞','13900000027','L000007','U000014','2020-01-10 17:43:12','P000001','CR000002','1'),
	('S000028','刘婵','A01','张翼德','13900000028','L000007','U000014','2020-01-08 04:30:57','P000001','CR000002','1'),
	('S000029','刘阿斗','A01','刘备','13900000029','L000008','U000015','2020-01-25 03:20:48','P000001','CR000002','1'),
	('S000030','李天一','A01','刘玄德','13900000030','L000008','U000015','2020-01-06 09:33:01','P000001','CR000002','1'),
	('S000031','刘婵','A01','张飞','13900000031','L000008','U000016','2020-01-16 03:36:03','P000001','CR000002','1'),
	('S000032','刘阿斗','A01','张翼德','13900000032','L000008','U000016','2020-01-12 23:56:03','P000001','CR000002','1'),
	('S000033','李天一','A01','刘备','13900000033','L000009','U000017','2020-01-16 05:33:56','P000001','CR000003','1'),
	('S000034','刘婵','A01','刘玄德','13900000034','L000009','U000017','2020-01-26 06:23:52','P000001','CR000003','1'),
	('S000035','刘阿斗','A01','张飞','13900000035','L000009','U000018','2020-01-08 14:42:44','P000001','CR000003','1'),
	('S000036','李天一','A01','张翼德','13900000036','L000009','U000018','2020-01-24 00:26:02','P000001','CR000003','1'),
	('S000037','刘婵','A01','刘备','13900000037','L000010','U000019','2020-01-11 04:17:32','P000001','CR000003','1'),
	('S000038','刘阿斗','A01','刘玄德','13900000038','L000010','U000019','2020-01-26 12:55:41','P000001','CR000003','1'),
	('S000039','李天一','A01','张飞','13900000039','L000010','U000020','2020-01-06 09:01:51','P000001','CR000003','1'),
	('S000040','刘婵','A01','张翼德','13900000040','L000010','U000020','2020-01-05 13:11:23','P000001','CR000003','1'),
	('S000041','刘阿斗','A01','刘备','13900000041','L000011','U000021','2020-01-06 13:55:06','P000001','CR000003','1'),
	('S000042','李天一','A01','刘玄德','13900000042','L000011','U000021','2020-01-21 06:40:49','P000001','CR000003','1'),
	('S000043','刘婵','A01','张飞','13900000043','L000011','U000022','2020-01-26 01:32:07','P000001','CR000003','1'),
	('S000044','刘阿斗','A01','张翼德','13900000044','L000011','U000022','2020-01-20 03:00:53','P000001','CR000003','1'),
	('S000045','李天一','A01','刘备','13900000045','L000012','U000023','2020-01-15 04:35:23','P000001','CR000003','1'),
	('S000046','刘婵','A01','刘玄德','13900000046','L000012','U000023','2020-01-11 02:29:40','P000001','CR000003','1'),
	('S000047','刘阿斗','A01','张飞','13900000047','L000012','U000024','2020-01-23 08:24:16','P000001','CR000003','1'),
	('S000048','李天一','A01','张翼德','13900000048','L000012','U000024','2020-01-19 11:55:42','P000001','CR000003','1'),
	('S000049','刘婵','A01','刘备','13900000049','L000013','U000025','2020-01-20 00:21:08','P000001','CR000004','1'),
	('S000050','刘阿斗','A01','刘玄德','13900000050','L000013','U000025','2020-01-23 16:16:39','P000001','CR000004','1'),
	('S000051','李天一','A01','张飞','13900000051','L000013','U000026','2020-01-11 19:22:49','P000001','CR000004','1'),
	('S000052','刘婵','A01','张翼德','13900000052','L000013','U000026','2020-01-26 05:45:04','P000001','CR000004','1'),
	('S000053','刘阿斗','A01','刘备','13900000053','L000014','U000027','2020-01-17 14:20:42','P000001','CR000004','1'),
	('S000054','李天一','A01','刘玄德','13900000054','L000014','U000027','2020-01-22 01:05:25','P000001','CR000004','1'),
	('S000055','刘婵','A01','张飞','13900000055','L000014','U000028','2020-01-13 15:38:39','P000001','CR000004','1'),
	('S000056','刘阿斗','A01','张翼德','13900000056','L000014','U000028','2020-01-20 23:20:09','P000001','CR000004','1'),
	('S000057','李天一','A01','刘备','13900000057','L000015','U000029','2020-01-13 18:45:24','P000001','CR000004','1'),
	('S000058','刘婵','A01','刘玄德','13900000058','L000015','U000029','2020-01-20 03:32:37','P000001','CR000004','1'),
	('S000059','刘阿斗','A01','张飞','13900000059','L000015','U000030','2020-01-23 10:31:56','P000001','CR000004','1'),
	('S000060','李天一','A01','张翼德','13900000060','L000015','U000030','2020-01-06 14:42:56','P000001','CR000004','1'),
	('S000061','刘婵','A01','刘备','13900000061','L000016','U000031','2020-01-12 09:50:10','P000001','CR000004','1'),
	('S000062','刘阿斗','A01','刘玄德','13900000062','L000016','U000031','2020-01-10 00:40:21','P000001','CR000004','1'),
	('S000063','李天一','A01','张飞','13900000063','L000016','U000032','2020-01-20 08:37:09','P000001','CR000004','1'),
	('S000064','刘婵','A01','张翼德','13900000064','L000016','U000032','2020-01-05 18:43:19','P000001','CR000004','1');

insert into question_data values
	('Q000001','节假日是否到过武汉','OptionSelect','没有','有','不确定','不知道','P000001','U000001','CR000001','1'),
	('Q000002','家里是否有武汉的亲朋好友来访','OptionSelect','没有0002','有0002','不确定0002','不知道0002','P000001','U000001','CR000001','1'),
	('Q000003','是否有发热、发烧症状','OptionSelect','没有0003','有0003','不确定0003','不知道0003','P000001','U000002','CR000001','1'),
	('Q000004','节假日是否到过武汉','OptionSelect','没有0004','有0004','不确定0004','不知道0004','P000001','U000002','CR000001','1'),
	('Q000005','家里是否有武汉的亲朋好友来访','OptionSelect','没有0005','有0005','不确定0005','不知道0005','P000001','U000003','CR000001','1'),
	('Q000006','是否有发热、发烧症状','OptionSelect','没有0006','有0006','不确定0006','不知道0006','P000001','U000003','CR000001','1'),
	('Q000007','节假日是否到过武汉','OptionSelect','没有0007','有0007','不确定0007','不知道0007','P000001','U000004','CR000001','1'),
	('Q000008','家里是否有武汉的亲朋好友来访','OptionSelect','没有0008','有0008','不确定0008','不知道0008','P000001','U000004','CR000001','1'),
	('Q000009','是否有发热、发烧症状','OptionSelect','没有0009','有0009','不确定0009','不知道0009','P000001','U000005','CR000001','1'),
	('Q000010','节假日是否到过武汉','OptionSelect','没有0010','有0010','不确定0010','不知道0010','P000001','U000005','CR000001','1'),
	('Q000011','家里是否有武汉的亲朋好友来访','OptionSelect','没有0011','有0011','不确定0011','不知道0011','P000001','U000006','CR000001','1'),
	('Q000012','是否有发热、发烧症状','OptionSelect','没有0012','有0012','不确定0012','不知道0012','P000001','U000006','CR000001','1'),
	('Q000013','节假日是否到过武汉','OptionSelect','没有0013','有0013','不确定0013','不知道0013','P000001','U000007','CR000001','1'),
	('Q000014','家里是否有武汉的亲朋好友来访','OptionSelect','没有0014','有0014','不确定0014','不知道0014','P000001','U000007','CR000001','1'),
	('Q000015','是否有发热、发烧症状','OptionSelect','没有0015','有0015','不确定0015','不知道0015','P000001','U000008','CR000001','1'),
	('Q000016','节假日是否到过武汉','OptionSelect','没有0016','有0016','不确定0016','不知道0016','P000001','U000008','CR000001','1'),
	('Q000017','家里是否有武汉的亲朋好友来访','OptionSelect','没有0017','有0017','不确定0017','不知道0017','P000001','U000009','CR000002','1'),
	('Q000018','是否有发热、发烧症状','OptionSelect','没有0018','有0018','不确定0018','不知道0018','P000001','U000009','CR000002','1'),
	('Q000019','节假日是否到过武汉','OptionSelect','没有0019','有0019','不确定0019','不知道0019','P000001','U000010','CR000002','1'),
	('Q000020','家里是否有武汉的亲朋好友来访','OptionSelect','没有0020','有0020','不确定0020','不知道0020','P000001','U000010','CR000002','1'),
	('Q000021','是否有发热、发烧症状','OptionSelect','没有0021','有0021','不确定0021','不知道0021','P000001','U000011','CR000002','1'),
	('Q000022','节假日是否到过武汉','OptionSelect','没有0022','有0022','不确定0022','不知道0022','P000001','U000011','CR000002','1'),
	('Q000023','家里是否有武汉的亲朋好友来访','OptionSelect','没有0023','有0023','不确定0023','不知道0023','P000001','U000012','CR000002','1'),
	('Q000024','是否有发热、发烧症状','OptionSelect','没有0024','有0024','不确定0024','不知道0024','P000001','U000012','CR000002','1'),
	('Q000025','节假日是否到过武汉','OptionSelect','没有0025','有0025','不确定0025','不知道0025','P000001','U000013','CR000002','1'),
	('Q000026','家里是否有武汉的亲朋好友来访','OptionSelect','没有0026','有0026','不确定0026','不知道0026','P000001','U000013','CR000002','1'),
	('Q000027','是否有发热、发烧症状','OptionSelect','没有0027','有0027','不确定0027','不知道0027','P000001','U000014','CR000002','1'),
	('Q000028','节假日是否到过武汉','OptionSelect','没有0028','有0028','不确定0028','不知道0028','P000001','U000014','CR000002','1'),
	('Q000029','家里是否有武汉的亲朋好友来访','OptionSelect','没有0029','有0029','不确定0029','不知道0029','P000001','U000015','CR000002','1'),
	('Q000030','是否有发热、发烧症状','OptionSelect','没有0030','有0030','不确定0030','不知道0030','P000001','U000015','CR000002','1'),
	('Q000031','节假日是否到过武汉','OptionSelect','没有0031','有0031','不确定0031','不知道0031','P000001','U000016','CR000002','1'),
	('Q000032','家里是否有武汉的亲朋好友来访','OptionSelect','没有0032','有0032','不确定0032','不知道0032','P000001','U000016','CR000002','1'),
	('Q000033','是否有发热、发烧症状','TextInput','没有0033','有0033','不确定0033','不知道0033','P000001','U000017','CR000003','1'),
	('Q000034','节假日是否到过武汉','TextInput','没有0034','有0034','不确定0034','不知道0034','P000001','U000017','CR000003','1'),
	('Q000035','家里是否有武汉的亲朋好友来访','TextInput','没有0035','有0035','不确定0035','不知道0035','P000001','U000018','CR000003','1'),
	('Q000036','是否有发热、发烧症状','TextInput','没有0036','有0036','不确定0036','不知道0036','P000001','U000018','CR000003','1'),
	('Q000037','节假日是否到过武汉','TextInput','没有0037','有0037','不确定0037','不知道0037','P000001','U000019','CR000003','1'),
	('Q000038','家里是否有武汉的亲朋好友来访','TextInput','没有0038','有0038','不确定0038','不知道0038','P000001','U000019','CR000003','1'),
	('Q000039','是否有发热、发烧症状','TextInput','没有0039','有0039','不确定0039','不知道0039','P000001','U000020','CR000003','1'),
	('Q000040','节假日是否到过武汉','TextInput','没有0040','有0040','不确定0040','不知道0040','P000001','U000020','CR000003','1'),
	('Q000041','家里是否有武汉的亲朋好友来访','TextInput','没有0041','有0041','不确定0041','不知道0041','P000001','U000021','CR000003','1'),
	('Q000042','是否有发热、发烧症状','TextInput','没有0042','有0042','不确定0042','不知道0042','P000001','U000021','CR000003','1'),
	('Q000043','节假日是否到过武汉','TextInput','没有0043','有0043','不确定0043','不知道0043','P000001','U000022','CR000003','1'),
	('Q000044','家里是否有武汉的亲朋好友来访','TextInput','没有0044','有0044','不确定0044','不知道0044','P000001','U000022','CR000003','1'),
	('Q000045','是否有发热、发烧症状','TextInput','没有0045','有0045','不确定0045','不知道0045','P000001','U000023','CR000003','1'),
	('Q000046','节假日是否到过武汉','TextInput','没有0046','有0046','不确定0046','不知道0046','P000001','U000023','CR000003','1'),
	('Q000047','家里是否有武汉的亲朋好友来访','TextInput','没有0047','有0047','不确定0047','不知道0047','P000001','U000024','CR000003','1'),
	('Q000048','是否有发热、发烧症状','TextInput','没有0048','有0048','不确定0048','不知道0048','P000001','U000024','CR000003','1'),
	('Q000049','节假日是否到过武汉','TextInput','没有0049','有0049','不确定0049','不知道0049','P000001','U000025','CR000004','1'),
	('Q000050','家里是否有武汉的亲朋好友来访','TextInput','没有0050','有0050','不确定0050','不知道0050','P000001','U000025','CR000004','1'),
	('Q000051','是否有发热、发烧症状','TextInput','没有0051','有0051','不确定0051','不知道0051','P000001','U000026','CR000004','1'),
	('Q000052','节假日是否到过武汉','TextInput','没有0052','有0052','不确定0052','不知道0052','P000001','U000026','CR000004','1'),
	('Q000053','家里是否有武汉的亲朋好友来访','TextInput','没有0053','有0053','不确定0053','不知道0053','P000001','U000027','CR000004','1'),
	('Q000054','是否有发热、发烧症状','TextInput','没有0054','有0054','不确定0054','不知道0054','P000001','U000027','CR000004','1'),
	('Q000055','节假日是否到过武汉','TextInput','没有0055','有0055','不确定0055','不知道0055','P000001','U000028','CR000004','1'),
	('Q000056','家里是否有武汉的亲朋好友来访','TextInput','没有0056','有0056','不确定0056','不知道0056','P000001','U000028','CR000004','1'),
	('Q000057','是否有发热、发烧症状','TextInput','没有0057','有0057','不确定0057','不知道0057','P000001','U000029','CR000004','1'),
	('Q000058','节假日是否到过武汉','TextInput','没有0058','有0058','不确定0058','不知道0058','P000001','U000029','CR000004','1'),
	('Q000059','家里是否有武汉的亲朋好友来访','TextInput','没有0059','有0059','不确定0059','不知道0059','P000001','U000030','CR000004','1'),
	('Q000060','是否有发热、发烧症状','TextInput','没有0060','有0060','不确定0060','不知道0060','P000001','U000030','CR000004','1'),
	('Q000061','节假日是否到过武汉','TextInput','没有0061','有0061','不确定0061','不知道0061','P000001','U000031','CR000004','1'),
	('Q000062','家里是否有武汉的亲朋好友来访','TextInput','没有0062','有0062','不确定0062','不知道0062','P000001','U000031','CR000004','1'),
	('Q000063','是否有发热、发烧症状','TextInput','没有0063','有0063','不确定0063','不知道0063','P000001','U000032','CR000004','1'),
	('Q000064','节假日是否到过武汉','TextInput','没有0064','有0064','不确定0064','不知道0064','P000001','U000032','CR000004','1');

insert into question_type_data values
	('OptionSelect','选择题','OptionSelect','P000001','1'),
	('TextInput','简答题','TextInput','P000001','1');

insert into class_daily_health_survey_data values
	('CDHS000001','2020年1月25日益州小学学生健康调查问卷','T000001','2020-01-12 21:28:42','U000001','CR000001','1'),
	('CDHS000002','2020年1月25日益州小学学生健康调查问卷0002','T000001','2020-01-16 11:38:28','U000001','CR000001','1'),
	('CDHS000003','2020年1月25日益州小学学生健康调查问卷0003','T000001','2020-01-13 03:07:34','U000002','CR000001','1'),
	('CDHS000004','2020年1月25日益州小学学生健康调查问卷0004','T000001','2020-01-07 08:32:21','U000002','CR000001','1'),
	('CDHS000005','2020年1月25日益州小学学生健康调查问卷0005','T000001','2020-01-25 00:43:43','U000003','CR000001','1'),
	('CDHS000006','2020年1月25日益州小学学生健康调查问卷0006','T000001','2020-01-14 18:51:17','U000003','CR000001','1'),
	('CDHS000007','2020年1月25日益州小学学生健康调查问卷0007','T000001','2020-01-12 04:38:55','U000004','CR000001','1'),
	('CDHS000008','2020年1月25日益州小学学生健康调查问卷0008','T000001','2020-01-18 09:11:12','U000004','CR000001','1'),
	('CDHS000009','2020年1月25日益州小学学生健康调查问卷0009','T000002','2020-01-09 13:01:33','U000005','CR000001','1'),
	('CDHS000010','2020年1月25日益州小学学生健康调查问卷0010','T000002','2020-01-06 08:54:13','U000005','CR000001','1'),
	('CDHS000011','2020年1月25日益州小学学生健康调查问卷0011','T000002','2020-01-25 11:53:44','U000006','CR000001','1'),
	('CDHS000012','2020年1月25日益州小学学生健康调查问卷0012','T000002','2020-01-25 12:53:23','U000006','CR000001','1'),
	('CDHS000013','2020年1月25日益州小学学生健康调查问卷0013','T000002','2020-01-08 02:04:48','U000007','CR000001','1'),
	('CDHS000014','2020年1月25日益州小学学生健康调查问卷0014','T000002','2020-01-12 12:01:35','U000007','CR000001','1'),
	('CDHS000015','2020年1月25日益州小学学生健康调查问卷0015','T000002','2020-01-09 13:24:34','U000008','CR000001','1'),
	('CDHS000016','2020年1月25日益州小学学生健康调查问卷0016','T000002','2020-01-09 01:03:43','U000008','CR000001','1'),
	('CDHS000017','2020年1月25日益州小学学生健康调查问卷0017','T000003','2020-01-25 14:36:20','U000009','CR000002','1'),
	('CDHS000018','2020年1月25日益州小学学生健康调查问卷0018','T000003','2020-01-21 18:40:43','U000009','CR000002','1'),
	('CDHS000019','2020年1月25日益州小学学生健康调查问卷0019','T000003','2020-01-09 00:59:00','U000010','CR000002','1'),
	('CDHS000020','2020年1月25日益州小学学生健康调查问卷0020','T000003','2020-01-20 08:27:15','U000010','CR000002','1'),
	('CDHS000021','2020年1月25日益州小学学生健康调查问卷0021','T000003','2020-01-11 15:15:38','U000011','CR000002','1'),
	('CDHS000022','2020年1月25日益州小学学生健康调查问卷0022','T000003','2020-01-07 01:42:40','U000011','CR000002','1'),
	('CDHS000023','2020年1月25日益州小学学生健康调查问卷0023','T000003','2020-01-18 05:25:28','U000012','CR000002','1'),
	('CDHS000024','2020年1月25日益州小学学生健康调查问卷0024','T000003','2020-01-17 20:37:59','U000012','CR000002','1'),
	('CDHS000025','2020年1月25日益州小学学生健康调查问卷0025','T000004','2020-01-14 16:30:37','U000013','CR000002','1'),
	('CDHS000026','2020年1月25日益州小学学生健康调查问卷0026','T000004','2020-01-10 12:36:34','U000013','CR000002','1'),
	('CDHS000027','2020年1月25日益州小学学生健康调查问卷0027','T000004','2020-01-24 21:30:03','U000014','CR000002','1'),
	('CDHS000028','2020年1月25日益州小学学生健康调查问卷0028','T000004','2020-01-10 07:37:33','U000014','CR000002','1'),
	('CDHS000029','2020年1月25日益州小学学生健康调查问卷0029','T000004','2020-01-22 03:43:12','U000015','CR000002','1'),
	('CDHS000030','2020年1月25日益州小学学生健康调查问卷0030','T000004','2020-01-25 21:24:33','U000015','CR000002','1'),
	('CDHS000031','2020年1月25日益州小学学生健康调查问卷0031','T000004','2020-01-09 14:30:28','U000016','CR000002','1'),
	('CDHS000032','2020年1月25日益州小学学生健康调查问卷0032','T000004','2020-01-19 04:14:37','U000016','CR000002','1'),
	('CDHS000033','2020年1月25日益州小学学生健康调查问卷0033','T000005','2020-01-08 03:35:43','U000017','CR000003','1'),
	('CDHS000034','2020年1月25日益州小学学生健康调查问卷0034','T000005','2020-01-14 15:30:18','U000017','CR000003','1'),
	('CDHS000035','2020年1月25日益州小学学生健康调查问卷0035','T000005','2020-01-21 09:30:22','U000018','CR000003','1'),
	('CDHS000036','2020年1月25日益州小学学生健康调查问卷0036','T000005','2020-01-08 20:06:00','U000018','CR000003','1'),
	('CDHS000037','2020年1月25日益州小学学生健康调查问卷0037','T000005','2020-01-09 00:07:39','U000019','CR000003','1'),
	('CDHS000038','2020年1月25日益州小学学生健康调查问卷0038','T000005','2020-01-14 18:34:58','U000019','CR000003','1'),
	('CDHS000039','2020年1月25日益州小学学生健康调查问卷0039','T000005','2020-01-16 19:08:59','U000020','CR000003','1'),
	('CDHS000040','2020年1月25日益州小学学生健康调查问卷0040','T000005','2020-01-13 16:29:00','U000020','CR000003','1'),
	('CDHS000041','2020年1月25日益州小学学生健康调查问卷0041','T000006','2020-01-17 08:51:53','U000021','CR000003','1'),
	('CDHS000042','2020年1月25日益州小学学生健康调查问卷0042','T000006','2020-01-11 19:45:13','U000021','CR000003','1'),
	('CDHS000043','2020年1月25日益州小学学生健康调查问卷0043','T000006','2020-01-12 23:05:09','U000022','CR000003','1'),
	('CDHS000044','2020年1月25日益州小学学生健康调查问卷0044','T000006','2020-01-15 20:34:59','U000022','CR000003','1'),
	('CDHS000045','2020年1月25日益州小学学生健康调查问卷0045','T000006','2020-01-21 17:36:50','U000023','CR000003','1'),
	('CDHS000046','2020年1月25日益州小学学生健康调查问卷0046','T000006','2020-01-26 06:38:10','U000023','CR000003','1'),
	('CDHS000047','2020年1月25日益州小学学生健康调查问卷0047','T000006','2020-01-05 00:48:48','U000024','CR000003','1'),
	('CDHS000048','2020年1月25日益州小学学生健康调查问卷0048','T000006','2020-01-19 05:46:07','U000024','CR000003','1'),
	('CDHS000049','2020年1月25日益州小学学生健康调查问卷0049','T000007','2020-01-20 00:48:04','U000025','CR000004','1'),
	('CDHS000050','2020年1月25日益州小学学生健康调查问卷0050','T000007','2020-01-14 00:13:50','U000025','CR000004','1'),
	('CDHS000051','2020年1月25日益州小学学生健康调查问卷0051','T000007','2020-01-17 23:48:54','U000026','CR000004','1'),
	('CDHS000052','2020年1月25日益州小学学生健康调查问卷0052','T000007','2020-01-26 00:00:57','U000026','CR000004','1'),
	('CDHS000053','2020年1月25日益州小学学生健康调查问卷0053','T000007','2020-01-11 20:26:35','U000027','CR000004','1'),
	('CDHS000054','2020年1月25日益州小学学生健康调查问卷0054','T000007','2020-01-06 05:08:27','U000027','CR000004','1'),
	('CDHS000055','2020年1月25日益州小学学生健康调查问卷0055','T000007','2020-01-12 17:19:33','U000028','CR000004','1'),
	('CDHS000056','2020年1月25日益州小学学生健康调查问卷0056','T000007','2020-01-11 02:48:38','U000028','CR000004','1'),
	('CDHS000057','2020年1月25日益州小学学生健康调查问卷0057','T000008','2020-01-13 11:51:38','U000029','CR000004','1'),
	('CDHS000058','2020年1月25日益州小学学生健康调查问卷0058','T000008','2020-01-15 10:27:25','U000029','CR000004','1'),
	('CDHS000059','2020年1月25日益州小学学生健康调查问卷0059','T000008','2020-01-13 20:52:27','U000030','CR000004','1'),
	('CDHS000060','2020年1月25日益州小学学生健康调查问卷0060','T000008','2020-01-19 01:07:01','U000030','CR000004','1'),
	('CDHS000061','2020年1月25日益州小学学生健康调查问卷0061','T000008','2020-01-21 19:14:24','U000031','CR000004','1'),
	('CDHS000062','2020年1月25日益州小学学生健康调查问卷0062','T000008','2020-01-21 13:57:51','U000031','CR000004','1'),
	('CDHS000063','2020年1月25日益州小学学生健康调查问卷0063','T000008','2020-01-13 16:08:29','U000032','CR000004','1'),
	('CDHS000064','2020年1月25日益州小学学生健康调查问卷0064','T000008','2020-01-21 06:04:52','U000032','CR000004','1');

insert into daily_survey_question_data values
	('DSQ000001','节假日是否到过武汉','OptionSelect','没有','有','不确定','不知道','CDHS000001','Q000001','1'),
	('DSQ000002','家里是否有武汉的亲朋好友来访','OptionSelect','没有0002','有0002','不确定0002','不知道0002','CDHS000001','Q000001','1'),
	('DSQ000003','是否有发热、发烧症状','OptionSelect','没有0003','有0003','不确定0003','不知道0003','CDHS000002','Q000002','1'),
	('DSQ000004','节假日是否到过武汉','OptionSelect','没有0004','有0004','不确定0004','不知道0004','CDHS000002','Q000002','1'),
	('DSQ000005','家里是否有武汉的亲朋好友来访','OptionSelect','没有0005','有0005','不确定0005','不知道0005','CDHS000003','Q000003','1'),
	('DSQ000006','是否有发热、发烧症状','OptionSelect','没有0006','有0006','不确定0006','不知道0006','CDHS000003','Q000003','1'),
	('DSQ000007','节假日是否到过武汉','OptionSelect','没有0007','有0007','不确定0007','不知道0007','CDHS000004','Q000004','1'),
	('DSQ000008','家里是否有武汉的亲朋好友来访','OptionSelect','没有0008','有0008','不确定0008','不知道0008','CDHS000004','Q000004','1'),
	('DSQ000009','是否有发热、发烧症状','OptionSelect','没有0009','有0009','不确定0009','不知道0009','CDHS000005','Q000005','1'),
	('DSQ000010','节假日是否到过武汉','OptionSelect','没有0010','有0010','不确定0010','不知道0010','CDHS000005','Q000005','1'),
	('DSQ000011','家里是否有武汉的亲朋好友来访','OptionSelect','没有0011','有0011','不确定0011','不知道0011','CDHS000006','Q000006','1'),
	('DSQ000012','是否有发热、发烧症状','OptionSelect','没有0012','有0012','不确定0012','不知道0012','CDHS000006','Q000006','1'),
	('DSQ000013','节假日是否到过武汉','OptionSelect','没有0013','有0013','不确定0013','不知道0013','CDHS000007','Q000007','1'),
	('DSQ000014','家里是否有武汉的亲朋好友来访','OptionSelect','没有0014','有0014','不确定0014','不知道0014','CDHS000007','Q000007','1'),
	('DSQ000015','是否有发热、发烧症状','OptionSelect','没有0015','有0015','不确定0015','不知道0015','CDHS000008','Q000008','1'),
	('DSQ000016','节假日是否到过武汉','OptionSelect','没有0016','有0016','不确定0016','不知道0016','CDHS000008','Q000008','1'),
	('DSQ000017','家里是否有武汉的亲朋好友来访','OptionSelect','没有0017','有0017','不确定0017','不知道0017','CDHS000009','Q000009','1'),
	('DSQ000018','是否有发热、发烧症状','OptionSelect','没有0018','有0018','不确定0018','不知道0018','CDHS000009','Q000009','1'),
	('DSQ000019','节假日是否到过武汉','OptionSelect','没有0019','有0019','不确定0019','不知道0019','CDHS000010','Q000010','1'),
	('DSQ000020','家里是否有武汉的亲朋好友来访','OptionSelect','没有0020','有0020','不确定0020','不知道0020','CDHS000010','Q000010','1'),
	('DSQ000021','是否有发热、发烧症状','OptionSelect','没有0021','有0021','不确定0021','不知道0021','CDHS000011','Q000011','1'),
	('DSQ000022','节假日是否到过武汉','OptionSelect','没有0022','有0022','不确定0022','不知道0022','CDHS000011','Q000011','1'),
	('DSQ000023','家里是否有武汉的亲朋好友来访','OptionSelect','没有0023','有0023','不确定0023','不知道0023','CDHS000012','Q000012','1'),
	('DSQ000024','是否有发热、发烧症状','OptionSelect','没有0024','有0024','不确定0024','不知道0024','CDHS000012','Q000012','1'),
	('DSQ000025','节假日是否到过武汉','OptionSelect','没有0025','有0025','不确定0025','不知道0025','CDHS000013','Q000013','1'),
	('DSQ000026','家里是否有武汉的亲朋好友来访','OptionSelect','没有0026','有0026','不确定0026','不知道0026','CDHS000013','Q000013','1'),
	('DSQ000027','是否有发热、发烧症状','OptionSelect','没有0027','有0027','不确定0027','不知道0027','CDHS000014','Q000014','1'),
	('DSQ000028','节假日是否到过武汉','OptionSelect','没有0028','有0028','不确定0028','不知道0028','CDHS000014','Q000014','1'),
	('DSQ000029','家里是否有武汉的亲朋好友来访','OptionSelect','没有0029','有0029','不确定0029','不知道0029','CDHS000015','Q000015','1'),
	('DSQ000030','是否有发热、发烧症状','OptionSelect','没有0030','有0030','不确定0030','不知道0030','CDHS000015','Q000015','1'),
	('DSQ000031','节假日是否到过武汉','OptionSelect','没有0031','有0031','不确定0031','不知道0031','CDHS000016','Q000016','1'),
	('DSQ000032','家里是否有武汉的亲朋好友来访','OptionSelect','没有0032','有0032','不确定0032','不知道0032','CDHS000016','Q000016','1'),
	('DSQ000033','是否有发热、发烧症状','OptionSelect','没有0033','有0033','不确定0033','不知道0033','CDHS000017','Q000017','1'),
	('DSQ000034','节假日是否到过武汉','OptionSelect','没有0034','有0034','不确定0034','不知道0034','CDHS000017','Q000017','1'),
	('DSQ000035','家里是否有武汉的亲朋好友来访','OptionSelect','没有0035','有0035','不确定0035','不知道0035','CDHS000018','Q000018','1'),
	('DSQ000036','是否有发热、发烧症状','OptionSelect','没有0036','有0036','不确定0036','不知道0036','CDHS000018','Q000018','1'),
	('DSQ000037','节假日是否到过武汉','OptionSelect','没有0037','有0037','不确定0037','不知道0037','CDHS000019','Q000019','1'),
	('DSQ000038','家里是否有武汉的亲朋好友来访','OptionSelect','没有0038','有0038','不确定0038','不知道0038','CDHS000019','Q000019','1'),
	('DSQ000039','是否有发热、发烧症状','OptionSelect','没有0039','有0039','不确定0039','不知道0039','CDHS000020','Q000020','1'),
	('DSQ000040','节假日是否到过武汉','OptionSelect','没有0040','有0040','不确定0040','不知道0040','CDHS000020','Q000020','1'),
	('DSQ000041','家里是否有武汉的亲朋好友来访','OptionSelect','没有0041','有0041','不确定0041','不知道0041','CDHS000021','Q000021','1'),
	('DSQ000042','是否有发热、发烧症状','OptionSelect','没有0042','有0042','不确定0042','不知道0042','CDHS000021','Q000021','1'),
	('DSQ000043','节假日是否到过武汉','OptionSelect','没有0043','有0043','不确定0043','不知道0043','CDHS000022','Q000022','1'),
	('DSQ000044','家里是否有武汉的亲朋好友来访','OptionSelect','没有0044','有0044','不确定0044','不知道0044','CDHS000022','Q000022','1'),
	('DSQ000045','是否有发热、发烧症状','OptionSelect','没有0045','有0045','不确定0045','不知道0045','CDHS000023','Q000023','1'),
	('DSQ000046','节假日是否到过武汉','OptionSelect','没有0046','有0046','不确定0046','不知道0046','CDHS000023','Q000023','1'),
	('DSQ000047','家里是否有武汉的亲朋好友来访','OptionSelect','没有0047','有0047','不确定0047','不知道0047','CDHS000024','Q000024','1'),
	('DSQ000048','是否有发热、发烧症状','OptionSelect','没有0048','有0048','不确定0048','不知道0048','CDHS000024','Q000024','1'),
	('DSQ000049','节假日是否到过武汉','OptionSelect','没有0049','有0049','不确定0049','不知道0049','CDHS000025','Q000025','1'),
	('DSQ000050','家里是否有武汉的亲朋好友来访','OptionSelect','没有0050','有0050','不确定0050','不知道0050','CDHS000025','Q000025','1'),
	('DSQ000051','是否有发热、发烧症状','OptionSelect','没有0051','有0051','不确定0051','不知道0051','CDHS000026','Q000026','1'),
	('DSQ000052','节假日是否到过武汉','OptionSelect','没有0052','有0052','不确定0052','不知道0052','CDHS000026','Q000026','1'),
	('DSQ000053','家里是否有武汉的亲朋好友来访','OptionSelect','没有0053','有0053','不确定0053','不知道0053','CDHS000027','Q000027','1'),
	('DSQ000054','是否有发热、发烧症状','OptionSelect','没有0054','有0054','不确定0054','不知道0054','CDHS000027','Q000027','1'),
	('DSQ000055','节假日是否到过武汉','OptionSelect','没有0055','有0055','不确定0055','不知道0055','CDHS000028','Q000028','1'),
	('DSQ000056','家里是否有武汉的亲朋好友来访','OptionSelect','没有0056','有0056','不确定0056','不知道0056','CDHS000028','Q000028','1'),
	('DSQ000057','是否有发热、发烧症状','OptionSelect','没有0057','有0057','不确定0057','不知道0057','CDHS000029','Q000029','1'),
	('DSQ000058','节假日是否到过武汉','OptionSelect','没有0058','有0058','不确定0058','不知道0058','CDHS000029','Q000029','1'),
	('DSQ000059','家里是否有武汉的亲朋好友来访','OptionSelect','没有0059','有0059','不确定0059','不知道0059','CDHS000030','Q000030','1'),
	('DSQ000060','是否有发热、发烧症状','OptionSelect','没有0060','有0060','不确定0060','不知道0060','CDHS000030','Q000030','1'),
	('DSQ000061','节假日是否到过武汉','OptionSelect','没有0061','有0061','不确定0061','不知道0061','CDHS000031','Q000031','1'),
	('DSQ000062','家里是否有武汉的亲朋好友来访','OptionSelect','没有0062','有0062','不确定0062','不知道0062','CDHS000031','Q000031','1'),
	('DSQ000063','是否有发热、发烧症状','OptionSelect','没有0063','有0063','不确定0063','不知道0063','CDHS000032','Q000032','1'),
	('DSQ000064','节假日是否到过武汉','OptionSelect','没有0064','有0064','不确定0064','不知道0064','CDHS000032','Q000032','1'),
	('DSQ000065','家里是否有武汉的亲朋好友来访','TextInput','没有0065','有0065','不确定0065','不知道0065','CDHS000033','Q000033','1'),
	('DSQ000066','是否有发热、发烧症状','TextInput','没有0066','有0066','不确定0066','不知道0066','CDHS000033','Q000033','1'),
	('DSQ000067','节假日是否到过武汉','TextInput','没有0067','有0067','不确定0067','不知道0067','CDHS000034','Q000034','1'),
	('DSQ000068','家里是否有武汉的亲朋好友来访','TextInput','没有0068','有0068','不确定0068','不知道0068','CDHS000034','Q000034','1'),
	('DSQ000069','是否有发热、发烧症状','TextInput','没有0069','有0069','不确定0069','不知道0069','CDHS000035','Q000035','1'),
	('DSQ000070','节假日是否到过武汉','TextInput','没有0070','有0070','不确定0070','不知道0070','CDHS000035','Q000035','1'),
	('DSQ000071','家里是否有武汉的亲朋好友来访','TextInput','没有0071','有0071','不确定0071','不知道0071','CDHS000036','Q000036','1'),
	('DSQ000072','是否有发热、发烧症状','TextInput','没有0072','有0072','不确定0072','不知道0072','CDHS000036','Q000036','1'),
	('DSQ000073','节假日是否到过武汉','TextInput','没有0073','有0073','不确定0073','不知道0073','CDHS000037','Q000037','1'),
	('DSQ000074','家里是否有武汉的亲朋好友来访','TextInput','没有0074','有0074','不确定0074','不知道0074','CDHS000037','Q000037','1'),
	('DSQ000075','是否有发热、发烧症状','TextInput','没有0075','有0075','不确定0075','不知道0075','CDHS000038','Q000038','1'),
	('DSQ000076','节假日是否到过武汉','TextInput','没有0076','有0076','不确定0076','不知道0076','CDHS000038','Q000038','1'),
	('DSQ000077','家里是否有武汉的亲朋好友来访','TextInput','没有0077','有0077','不确定0077','不知道0077','CDHS000039','Q000039','1'),
	('DSQ000078','是否有发热、发烧症状','TextInput','没有0078','有0078','不确定0078','不知道0078','CDHS000039','Q000039','1'),
	('DSQ000079','节假日是否到过武汉','TextInput','没有0079','有0079','不确定0079','不知道0079','CDHS000040','Q000040','1'),
	('DSQ000080','家里是否有武汉的亲朋好友来访','TextInput','没有0080','有0080','不确定0080','不知道0080','CDHS000040','Q000040','1'),
	('DSQ000081','是否有发热、发烧症状','TextInput','没有0081','有0081','不确定0081','不知道0081','CDHS000041','Q000041','1'),
	('DSQ000082','节假日是否到过武汉','TextInput','没有0082','有0082','不确定0082','不知道0082','CDHS000041','Q000041','1'),
	('DSQ000083','家里是否有武汉的亲朋好友来访','TextInput','没有0083','有0083','不确定0083','不知道0083','CDHS000042','Q000042','1'),
	('DSQ000084','是否有发热、发烧症状','TextInput','没有0084','有0084','不确定0084','不知道0084','CDHS000042','Q000042','1'),
	('DSQ000085','节假日是否到过武汉','TextInput','没有0085','有0085','不确定0085','不知道0085','CDHS000043','Q000043','1'),
	('DSQ000086','家里是否有武汉的亲朋好友来访','TextInput','没有0086','有0086','不确定0086','不知道0086','CDHS000043','Q000043','1'),
	('DSQ000087','是否有发热、发烧症状','TextInput','没有0087','有0087','不确定0087','不知道0087','CDHS000044','Q000044','1'),
	('DSQ000088','节假日是否到过武汉','TextInput','没有0088','有0088','不确定0088','不知道0088','CDHS000044','Q000044','1'),
	('DSQ000089','家里是否有武汉的亲朋好友来访','TextInput','没有0089','有0089','不确定0089','不知道0089','CDHS000045','Q000045','1'),
	('DSQ000090','是否有发热、发烧症状','TextInput','没有0090','有0090','不确定0090','不知道0090','CDHS000045','Q000045','1'),
	('DSQ000091','节假日是否到过武汉','TextInput','没有0091','有0091','不确定0091','不知道0091','CDHS000046','Q000046','1'),
	('DSQ000092','家里是否有武汉的亲朋好友来访','TextInput','没有0092','有0092','不确定0092','不知道0092','CDHS000046','Q000046','1'),
	('DSQ000093','是否有发热、发烧症状','TextInput','没有0093','有0093','不确定0093','不知道0093','CDHS000047','Q000047','1'),
	('DSQ000094','节假日是否到过武汉','TextInput','没有0094','有0094','不确定0094','不知道0094','CDHS000047','Q000047','1'),
	('DSQ000095','家里是否有武汉的亲朋好友来访','TextInput','没有0095','有0095','不确定0095','不知道0095','CDHS000048','Q000048','1'),
	('DSQ000096','是否有发热、发烧症状','TextInput','没有0096','有0096','不确定0096','不知道0096','CDHS000048','Q000048','1'),
	('DSQ000097','节假日是否到过武汉','TextInput','没有0097','有0097','不确定0097','不知道0097','CDHS000049','Q000049','1'),
	('DSQ000098','家里是否有武汉的亲朋好友来访','TextInput','没有0098','有0098','不确定0098','不知道0098','CDHS000049','Q000049','1'),
	('DSQ000099','是否有发热、发烧症状','TextInput','没有0099','有0099','不确定0099','不知道0099','CDHS000050','Q000050','1'),
	('DSQ000100','节假日是否到过武汉','TextInput','没有0100','有0100','不确定0100','不知道0100','CDHS000050','Q000050','1'),
	('DSQ000101','家里是否有武汉的亲朋好友来访','TextInput','没有0101','有0101','不确定0101','不知道0101','CDHS000051','Q000051','1'),
	('DSQ000102','是否有发热、发烧症状','TextInput','没有0102','有0102','不确定0102','不知道0102','CDHS000051','Q000051','1'),
	('DSQ000103','节假日是否到过武汉','TextInput','没有0103','有0103','不确定0103','不知道0103','CDHS000052','Q000052','1'),
	('DSQ000104','家里是否有武汉的亲朋好友来访','TextInput','没有0104','有0104','不确定0104','不知道0104','CDHS000052','Q000052','1'),
	('DSQ000105','是否有发热、发烧症状','TextInput','没有0105','有0105','不确定0105','不知道0105','CDHS000053','Q000053','1'),
	('DSQ000106','节假日是否到过武汉','TextInput','没有0106','有0106','不确定0106','不知道0106','CDHS000053','Q000053','1'),
	('DSQ000107','家里是否有武汉的亲朋好友来访','TextInput','没有0107','有0107','不确定0107','不知道0107','CDHS000054','Q000054','1'),
	('DSQ000108','是否有发热、发烧症状','TextInput','没有0108','有0108','不确定0108','不知道0108','CDHS000054','Q000054','1'),
	('DSQ000109','节假日是否到过武汉','TextInput','没有0109','有0109','不确定0109','不知道0109','CDHS000055','Q000055','1'),
	('DSQ000110','家里是否有武汉的亲朋好友来访','TextInput','没有0110','有0110','不确定0110','不知道0110','CDHS000055','Q000055','1'),
	('DSQ000111','是否有发热、发烧症状','TextInput','没有0111','有0111','不确定0111','不知道0111','CDHS000056','Q000056','1'),
	('DSQ000112','节假日是否到过武汉','TextInput','没有0112','有0112','不确定0112','不知道0112','CDHS000056','Q000056','1'),
	('DSQ000113','家里是否有武汉的亲朋好友来访','TextInput','没有0113','有0113','不确定0113','不知道0113','CDHS000057','Q000057','1'),
	('DSQ000114','是否有发热、发烧症状','TextInput','没有0114','有0114','不确定0114','不知道0114','CDHS000057','Q000057','1'),
	('DSQ000115','节假日是否到过武汉','TextInput','没有0115','有0115','不确定0115','不知道0115','CDHS000058','Q000058','1'),
	('DSQ000116','家里是否有武汉的亲朋好友来访','TextInput','没有0116','有0116','不确定0116','不知道0116','CDHS000058','Q000058','1'),
	('DSQ000117','是否有发热、发烧症状','TextInput','没有0117','有0117','不确定0117','不知道0117','CDHS000059','Q000059','1'),
	('DSQ000118','节假日是否到过武汉','TextInput','没有0118','有0118','不确定0118','不知道0118','CDHS000059','Q000059','1'),
	('DSQ000119','家里是否有武汉的亲朋好友来访','TextInput','没有0119','有0119','不确定0119','不知道0119','CDHS000060','Q000060','1'),
	('DSQ000120','是否有发热、发烧症状','TextInput','没有0120','有0120','不确定0120','不知道0120','CDHS000060','Q000060','1'),
	('DSQ000121','节假日是否到过武汉','TextInput','没有0121','有0121','不确定0121','不知道0121','CDHS000061','Q000061','1'),
	('DSQ000122','家里是否有武汉的亲朋好友来访','TextInput','没有0122','有0122','不确定0122','不知道0122','CDHS000061','Q000061','1'),
	('DSQ000123','是否有发热、发烧症状','TextInput','没有0123','有0123','不确定0123','不知道0123','CDHS000062','Q000062','1'),
	('DSQ000124','节假日是否到过武汉','TextInput','没有0124','有0124','不确定0124','不知道0124','CDHS000062','Q000062','1'),
	('DSQ000125','家里是否有武汉的亲朋好友来访','TextInput','没有0125','有0125','不确定0125','不知道0125','CDHS000063','Q000063','1'),
	('DSQ000126','是否有发热、发烧症状','TextInput','没有0126','有0126','不确定0126','不知道0126','CDHS000063','Q000063','1'),
	('DSQ000127','节假日是否到过武汉','TextInput','没有0127','有0127','不确定0127','不知道0127','CDHS000064','Q000064','1'),
	('DSQ000128','家里是否有武汉的亲朋好友来访','TextInput','没有0128','有0128','不确定0128','不知道0128','CDHS000064','Q000064','1');

insert into student_health_survey_data values
	('SHS000001','S000001','2020-01-14 02:58:05','UnSubmitted','T000001','CDHS000001','2020-01-22 18:33:31','2020-01-19 08:18:01','CR000001','1'),
	('SHS000002','S000001','2020-01-13 14:26:40','UnSubmitted','T000001','CDHS000001','2020-01-09 14:30:29','2020-01-26 14:20:53','CR000001','1'),
	('SHS000003','S000002','2020-01-07 16:27:07','UnSubmitted','T000001','CDHS000002','2020-01-20 02:34:34','2020-01-18 11:50:08','CR000001','1'),
	('SHS000004','S000002','2020-01-11 12:10:52','UnSubmitted','T000001','CDHS000002','2020-01-25 20:27:12','2020-01-07 11:23:14','CR000001','1'),
	('SHS000005','S000003','2020-01-06 15:33:25','UnSubmitted','T000001','CDHS000003','2020-01-21 01:23:54','2020-01-19 23:43:37','CR000001','1'),
	('SHS000006','S000003','2020-01-20 14:46:47','UnSubmitted','T000001','CDHS000003','2020-01-13 09:08:00','2020-01-08 10:30:47','CR000001','1'),
	('SHS000007','S000004','2020-01-08 10:58:25','UnSubmitted','T000001','CDHS000004','2020-01-06 01:04:41','2020-01-19 10:58:31','CR000001','1'),
	('SHS000008','S000004','2020-01-15 06:13:47','UnSubmitted','T000001','CDHS000004','2020-01-23 23:39:25','2020-01-16 23:20:00','CR000001','1'),
	('SHS000009','S000005','2020-01-17 13:56:40','UnSubmitted','T000001','CDHS000005','2020-01-23 21:46:11','2020-01-11 18:18:22','CR000001','1'),
	('SHS000010','S000005','2020-01-16 21:38:36','UnSubmitted','T000001','CDHS000005','2020-01-11 13:22:18','2020-01-09 15:01:39','CR000001','1'),
	('SHS000011','S000006','2020-01-26 05:52:54','UnSubmitted','T000001','CDHS000006','2020-01-11 12:42:41','2020-01-12 13:43:40','CR000001','1'),
	('SHS000012','S000006','2020-01-22 17:29:33','UnSubmitted','T000001','CDHS000006','2020-01-04 21:52:38','2020-01-25 21:10:55','CR000001','1'),
	('SHS000013','S000007','2020-01-22 07:42:57','UnSubmitted','T000001','CDHS000007','2020-01-05 13:16:21','2020-01-10 15:16:50','CR000001','1'),
	('SHS000014','S000007','2020-01-16 07:24:31','UnSubmitted','T000001','CDHS000007','2020-01-18 18:12:59','2020-01-18 18:24:57','CR000001','1'),
	('SHS000015','S000008','2020-01-15 15:35:34','UnSubmitted','T000001','CDHS000008','2020-01-18 22:29:08','2020-01-25 06:24:21','CR000001','1'),
	('SHS000016','S000008','2020-01-25 15:35:14','UnSubmitted','T000001','CDHS000008','2020-01-13 09:36:08','2020-01-08 15:56:24','CR000001','1'),
	('SHS000017','S000009','2020-01-10 02:29:08','UnSubmitted','T000002','CDHS000009','2020-01-11 06:51:13','2020-01-07 15:56:36','CR000001','1'),
	('SHS000018','S000009','2020-01-10 04:53:47','UnSubmitted','T000002','CDHS000009','2020-01-09 17:35:22','2020-01-17 13:03:09','CR000001','1'),
	('SHS000019','S000010','2020-01-15 02:24:42','UnSubmitted','T000002','CDHS000010','2020-01-23 05:45:32','2020-01-22 09:48:40','CR000001','1'),
	('SHS000020','S000010','2020-01-12 06:21:39','UnSubmitted','T000002','CDHS000010','2020-01-22 02:11:22','2020-01-20 06:30:30','CR000001','1'),
	('SHS000021','S000011','2020-01-08 20:40:09','UnSubmitted','T000002','CDHS000011','2020-01-24 14:03:05','2020-01-19 19:20:04','CR000001','1'),
	('SHS000022','S000011','2020-01-20 07:35:40','UnSubmitted','T000002','CDHS000011','2020-01-05 17:09:08','2020-01-25 04:01:17','CR000001','1'),
	('SHS000023','S000012','2020-01-14 13:30:30','UnSubmitted','T000002','CDHS000012','2020-01-19 18:07:01','2020-01-21 06:19:04','CR000001','1'),
	('SHS000024','S000012','2020-01-26 18:05:24','UnSubmitted','T000002','CDHS000012','2020-01-18 09:22:32','2020-01-08 07:54:39','CR000001','1'),
	('SHS000025','S000013','2020-01-06 00:40:56','UnSubmitted','T000002','CDHS000013','2020-01-16 06:55:01','2020-01-07 05:46:40','CR000001','1'),
	('SHS000026','S000013','2020-01-12 02:55:01','UnSubmitted','T000002','CDHS000013','2020-01-20 03:19:59','2020-01-21 06:15:06','CR000001','1'),
	('SHS000027','S000014','2020-01-20 03:41:18','UnSubmitted','T000002','CDHS000014','2020-01-05 21:59:44','2020-01-18 18:59:56','CR000001','1'),
	('SHS000028','S000014','2020-01-12 22:07:42','UnSubmitted','T000002','CDHS000014','2020-01-20 01:16:42','2020-01-19 00:57:10','CR000001','1'),
	('SHS000029','S000015','2020-01-08 04:27:38','UnSubmitted','T000002','CDHS000015','2020-01-19 21:44:02','2020-01-13 21:34:39','CR000001','1'),
	('SHS000030','S000015','2020-01-25 14:46:28','UnSubmitted','T000002','CDHS000015','2020-01-22 15:50:27','2020-01-08 03:25:23','CR000001','1'),
	('SHS000031','S000016','2020-01-17 07:59:16','UnSubmitted','T000002','CDHS000016','2020-01-21 17:31:35','2020-01-16 09:54:34','CR000001','1'),
	('SHS000032','S000016','2020-01-18 00:31:31','UnSubmitted','T000002','CDHS000016','2020-01-09 19:42:26','2020-01-25 22:15:36','CR000001','1'),
	('SHS000033','S000017','2020-01-06 20:20:16','UnSubmitted','T000003','CDHS000017','2020-01-14 11:10:57','2020-01-23 22:09:00','CR000002','1'),
	('SHS000034','S000017','2020-01-26 18:09:46','UnSubmitted','T000003','CDHS000017','2020-01-16 03:29:43','2020-01-17 05:16:21','CR000002','1'),
	('SHS000035','S000018','2020-01-12 09:29:53','UnSubmitted','T000003','CDHS000018','2020-01-24 19:32:13','2020-01-07 20:37:26','CR000002','1'),
	('SHS000036','S000018','2020-01-22 20:19:25','UnSubmitted','T000003','CDHS000018','2020-01-22 19:11:39','2020-01-14 01:44:14','CR000002','1'),
	('SHS000037','S000019','2020-01-25 14:07:52','UnSubmitted','T000003','CDHS000019','2020-01-14 09:59:28','2020-01-20 20:41:21','CR000002','1'),
	('SHS000038','S000019','2020-01-25 16:34:59','UnSubmitted','T000003','CDHS000019','2020-01-23 14:50:31','2020-01-23 03:09:43','CR000002','1'),
	('SHS000039','S000020','2020-01-18 05:28:58','UnSubmitted','T000003','CDHS000020','2020-01-12 09:58:12','2020-01-18 21:12:01','CR000002','1'),
	('SHS000040','S000020','2020-01-06 12:16:27','UnSubmitted','T000003','CDHS000020','2020-01-06 01:14:30','2020-01-25 15:26:07','CR000002','1'),
	('SHS000041','S000021','2020-01-06 22:57:54','UnSubmitted','T000003','CDHS000021','2020-01-06 15:58:46','2020-01-18 20:21:59','CR000002','1'),
	('SHS000042','S000021','2020-01-05 02:53:15','UnSubmitted','T000003','CDHS000021','2020-01-17 13:44:12','2020-01-21 17:51:09','CR000002','1'),
	('SHS000043','S000022','2020-01-14 21:33:51','UnSubmitted','T000003','CDHS000022','2020-01-08 18:23:54','2020-01-14 12:40:02','CR000002','1'),
	('SHS000044','S000022','2020-01-08 15:02:19','Submitted','T000003','CDHS000022','2020-01-07 03:49:30','2020-01-09 23:39:17','CR000002','1'),
	('SHS000045','S000023','2020-01-05 06:20:24','Submitted','T000003','CDHS000023','2020-01-19 00:57:19','2020-01-11 22:26:18','CR000002','1'),
	('SHS000046','S000023','2020-01-08 05:12:14','Submitted','T000003','CDHS000023','2020-01-23 06:16:05','2020-01-12 05:36:20','CR000002','1'),
	('SHS000047','S000024','2020-01-11 03:34:14','Submitted','T000003','CDHS000024','2020-01-25 07:35:06','2020-01-12 13:21:26','CR000002','1'),
	('SHS000048','S000024','2020-01-05 20:07:52','Submitted','T000003','CDHS000024','2020-01-14 06:13:13','2020-01-19 10:01:03','CR000002','1'),
	('SHS000049','S000025','2020-01-05 01:34:45','Submitted','T000004','CDHS000025','2020-01-24 06:07:46','2020-01-05 02:05:37','CR000002','1'),
	('SHS000050','S000025','2020-01-21 12:43:27','Submitted','T000004','CDHS000025','2020-01-23 23:03:13','2020-01-07 15:46:32','CR000002','1'),
	('SHS000051','S000026','2020-01-08 19:53:55','Submitted','T000004','CDHS000026','2020-01-12 06:12:14','2020-01-23 18:05:07','CR000002','1'),
	('SHS000052','S000026','2020-01-23 12:20:06','Submitted','T000004','CDHS000026','2020-01-07 01:55:33','2020-01-09 21:37:05','CR000002','1'),
	('SHS000053','S000027','2020-01-21 00:54:25','Submitted','T000004','CDHS000027','2020-01-09 22:13:31','2020-01-22 09:59:10','CR000002','1'),
	('SHS000054','S000027','2020-01-10 21:20:47','Submitted','T000004','CDHS000027','2020-01-14 03:16:50','2020-01-15 03:46:37','CR000002','1'),
	('SHS000055','S000028','2020-01-23 18:00:19','Submitted','T000004','CDHS000028','2020-01-06 11:15:46','2020-01-21 12:56:52','CR000002','1'),
	('SHS000056','S000028','2020-01-20 00:15:49','Submitted','T000004','CDHS000028','2020-01-19 05:25:19','2020-01-18 02:01:43','CR000002','1'),
	('SHS000057','S000029','2020-01-25 14:59:47','Submitted','T000004','CDHS000029','2020-01-12 20:24:53','2020-01-23 11:48:44','CR000002','1'),
	('SHS000058','S000029','2020-01-21 06:02:33','Submitted','T000004','CDHS000029','2020-01-23 14:40:39','2020-01-08 14:29:58','CR000002','1'),
	('SHS000059','S000030','2020-01-07 11:15:56','Submitted','T000004','CDHS000030','2020-01-26 12:52:49','2020-01-09 22:08:34','CR000002','1'),
	('SHS000060','S000030','2020-01-17 09:45:22','Submitted','T000004','CDHS000030','2020-01-15 17:21:46','2020-01-11 05:27:29','CR000002','1'),
	('SHS000061','S000031','2020-01-10 12:05:29','Submitted','T000004','CDHS000031','2020-01-20 17:52:03','2020-01-18 22:33:26','CR000002','1'),
	('SHS000062','S000031','2020-01-14 12:11:15','Submitted','T000004','CDHS000031','2020-01-11 19:17:36','2020-01-22 01:19:34','CR000002','1'),
	('SHS000063','S000032','2020-01-12 10:08:00','Submitted','T000004','CDHS000032','2020-01-25 18:38:51','2020-01-23 09:32:01','CR000002','1'),
	('SHS000064','S000032','2020-01-07 14:30:03','Submitted','T000004','CDHS000032','2020-01-25 07:55:30','2020-01-25 20:10:37','CR000002','1'),
	('SHS000065','S000033','2020-01-06 19:32:34','Submitted','T000005','CDHS000033','2020-01-14 07:48:40','2020-01-26 00:35:36','CR000003','1'),
	('SHS000066','S000033','2020-01-12 05:06:21','Submitted','T000005','CDHS000033','2020-01-08 20:06:49','2020-01-08 18:02:14','CR000003','1'),
	('SHS000067','S000034','2020-01-13 19:07:02','Submitted','T000005','CDHS000034','2020-01-23 17:04:06','2020-01-14 22:15:56','CR000003','1'),
	('SHS000068','S000034','2020-01-15 16:26:08','Submitted','T000005','CDHS000034','2020-01-22 07:49:51','2020-01-24 17:12:02','CR000003','1'),
	('SHS000069','S000035','2020-01-06 20:12:46','Submitted','T000005','CDHS000035','2020-01-06 01:48:09','2020-01-11 18:02:06','CR000003','1'),
	('SHS000070','S000035','2020-01-23 06:36:28','Submitted','T000005','CDHS000035','2020-01-07 18:11:59','2020-01-08 11:07:08','CR000003','1'),
	('SHS000071','S000036','2020-01-15 07:20:23','Submitted','T000005','CDHS000036','2020-01-08 16:17:38','2020-01-24 19:24:21','CR000003','1'),
	('SHS000072','S000036','2020-01-24 07:55:55','Submitted','T000005','CDHS000036','2020-01-09 15:26:49','2020-01-06 10:34:37','CR000003','1'),
	('SHS000073','S000037','2020-01-08 09:41:26','Submitted','T000005','CDHS000037','2020-01-21 03:46:20','2020-01-14 11:15:14','CR000003','1'),
	('SHS000074','S000037','2020-01-24 04:52:07','Submitted','T000005','CDHS000037','2020-01-20 22:55:48','2020-01-07 13:04:39','CR000003','1'),
	('SHS000075','S000038','2020-01-20 15:45:46','Submitted','T000005','CDHS000038','2020-01-25 08:47:13','2020-01-21 23:18:58','CR000003','1'),
	('SHS000076','S000038','2020-01-13 14:04:54','Submitted','T000005','CDHS000038','2020-01-22 13:54:18','2020-01-26 00:27:49','CR000003','1'),
	('SHS000077','S000039','2020-01-10 08:49:33','Submitted','T000005','CDHS000039','2020-01-13 09:21:27','2020-01-18 00:50:33','CR000003','1'),
	('SHS000078','S000039','2020-01-08 20:51:23','Submitted','T000005','CDHS000039','2020-01-09 17:07:35','2020-01-19 03:44:07','CR000003','1'),
	('SHS000079','S000040','2020-01-24 07:46:44','Submitted','T000005','CDHS000040','2020-01-08 19:49:14','2020-01-05 14:52:53','CR000003','1'),
	('SHS000080','S000040','2020-01-24 19:23:57','Submitted','T000005','CDHS000040','2020-01-21 19:37:16','2020-01-17 19:04:46','CR000003','1'),
	('SHS000081','S000041','2020-01-15 16:07:23','Submitted','T000006','CDHS000041','2020-01-21 10:26:49','2020-01-21 10:21:06','CR000003','1'),
	('SHS000082','S000041','2020-01-18 13:02:53','Submitted','T000006','CDHS000041','2020-01-13 02:42:18','2020-01-19 13:19:50','CR000003','1'),
	('SHS000083','S000042','2020-01-20 22:31:34','Submitted','T000006','CDHS000042','2020-01-14 13:53:01','2020-01-12 17:00:27','CR000003','1'),
	('SHS000084','S000042','2020-01-11 08:55:38','Submitted','T000006','CDHS000042','2020-01-21 22:29:04','2020-01-17 06:12:10','CR000003','1'),
	('SHS000085','S000043','2020-01-05 03:15:20','Submitted','T000006','CDHS000043','2020-01-23 23:46:12','2020-01-13 10:16:01','CR000003','1'),
	('SHS000086','S000043','2020-01-13 11:59:09','Submitted','T000006','CDHS000043','2020-01-25 18:37:37','2020-01-19 21:21:24','CR000003','1'),
	('SHS000087','S000044','2020-01-06 06:27:03','Draft','T000006','CDHS000044','2020-01-11 04:29:29','2020-01-12 03:33:37','CR000003','1'),
	('SHS000088','S000044','2020-01-14 07:52:20','Draft','T000006','CDHS000044','2020-01-14 21:58:20','2020-01-16 07:32:34','CR000003','1'),
	('SHS000089','S000045','2020-01-22 15:32:38','Draft','T000006','CDHS000045','2020-01-13 17:42:58','2020-01-07 19:04:46','CR000003','1'),
	('SHS000090','S000045','2020-01-24 06:53:56','Draft','T000006','CDHS000045','2020-01-25 08:08:41','2020-01-15 20:04:44','CR000003','1'),
	('SHS000091','S000046','2020-01-22 12:39:22','Draft','T000006','CDHS000046','2020-01-19 10:00:23','2020-01-09 12:21:37','CR000003','1'),
	('SHS000092','S000046','2020-01-07 19:19:21','Draft','T000006','CDHS000046','2020-01-12 22:53:15','2020-01-09 07:14:35','CR000003','1'),
	('SHS000093','S000047','2020-01-18 04:54:24','Draft','T000006','CDHS000047','2020-01-11 08:13:45','2020-01-14 07:47:16','CR000003','1'),
	('SHS000094','S000047','2020-01-11 20:34:47','Draft','T000006','CDHS000047','2020-01-07 19:49:22','2020-01-07 23:55:41','CR000003','1'),
	('SHS000095','S000048','2020-01-26 15:53:00','Draft','T000006','CDHS000048','2020-01-12 12:03:43','2020-01-07 03:45:36','CR000003','1'),
	('SHS000096','S000048','2020-01-20 11:29:08','Draft','T000006','CDHS000048','2020-01-26 02:56:55','2020-01-20 10:11:00','CR000003','1'),
	('SHS000097','S000049','2020-01-10 07:32:10','Draft','T000007','CDHS000049','2020-01-17 10:55:56','2020-01-19 01:43:04','CR000004','1'),
	('SHS000098','S000049','2020-01-15 23:31:58','Draft','T000007','CDHS000049','2020-01-10 22:58:32','2020-01-15 18:43:20','CR000004','1'),
	('SHS000099','S000050','2020-01-24 11:56:43','Draft','T000007','CDHS000050','2020-01-22 13:37:52','2020-01-19 15:30:56','CR000004','1'),
	('SHS000100','S000050','2020-01-15 20:39:50','Draft','T000007','CDHS000050','2020-01-22 17:15:36','2020-01-25 14:13:46','CR000004','1'),
	('SHS000101','S000051','2020-01-15 03:09:04','Draft','T000007','CDHS000051','2020-01-26 03:02:07','2020-01-22 22:58:08','CR000004','1'),
	('SHS000102','S000051','2020-01-13 07:43:11','Draft','T000007','CDHS000051','2020-01-15 19:00:14','2020-01-24 03:28:53','CR000004','1'),
	('SHS000103','S000052','2020-01-19 11:40:44','Draft','T000007','CDHS000052','2020-01-14 13:31:17','2020-01-09 04:56:02','CR000004','1'),
	('SHS000104','S000052','2020-01-16 23:25:28','Draft','T000007','CDHS000052','2020-01-17 17:46:05','2020-01-14 03:32:06','CR000004','1'),
	('SHS000105','S000053','2020-01-10 06:47:02','Draft','T000007','CDHS000053','2020-01-22 00:14:53','2020-01-06 20:54:17','CR000004','1'),
	('SHS000106','S000053','2020-01-17 02:24:46','Draft','T000007','CDHS000053','2020-01-16 18:49:38','2020-01-18 08:05:57','CR000004','1'),
	('SHS000107','S000054','2020-01-25 23:25:08','Draft','T000007','CDHS000054','2020-01-14 05:04:42','2020-01-22 12:36:36','CR000004','1'),
	('SHS000108','S000054','2020-01-16 17:32:04','Draft','T000007','CDHS000054','2020-01-25 07:57:10','2020-01-14 21:01:56','CR000004','1'),
	('SHS000109','S000055','2020-01-22 01:12:28','Draft','T000007','CDHS000055','2020-01-13 06:51:57','2020-01-10 16:52:15','CR000004','1'),
	('SHS000110','S000055','2020-01-15 06:31:01','Draft','T000007','CDHS000055','2020-01-15 21:11:34','2020-01-19 16:18:05','CR000004','1'),
	('SHS000111','S000056','2020-01-21 13:23:02','Draft','T000007','CDHS000056','2020-01-09 06:46:03','2020-01-08 08:28:54','CR000004','1'),
	('SHS000112','S000056','2020-01-25 02:38:39','Draft','T000007','CDHS000056','2020-01-08 08:14:34','2020-01-06 10:09:29','CR000004','1'),
	('SHS000113','S000057','2020-01-11 22:21:36','Draft','T000008','CDHS000057','2020-01-21 00:12:51','2020-01-19 06:46:58','CR000004','1'),
	('SHS000114','S000057','2020-01-20 20:27:30','Draft','T000008','CDHS000057','2020-01-23 10:18:31','2020-01-14 10:46:07','CR000004','1'),
	('SHS000115','S000058','2020-01-13 04:15:05','Draft','T000008','CDHS000058','2020-01-07 01:03:20','2020-01-15 11:13:30','CR000004','1'),
	('SHS000116','S000058','2020-01-14 13:56:46','Draft','T000008','CDHS000058','2020-01-23 12:43:44','2020-01-25 12:38:24','CR000004','1'),
	('SHS000117','S000059','2020-01-16 01:25:14','Draft','T000008','CDHS000059','2020-01-13 13:19:49','2020-01-11 01:20:49','CR000004','1'),
	('SHS000118','S000059','2020-01-15 03:08:49','Draft','T000008','CDHS000059','2020-01-23 09:59:14','2020-01-21 05:13:07','CR000004','1'),
	('SHS000119','S000060','2020-01-09 11:20:35','Draft','T000008','CDHS000060','2020-01-18 02:02:58','2020-01-26 15:45:49','CR000004','1'),
	('SHS000120','S000060','2020-01-10 12:00:23','Draft','T000008','CDHS000060','2020-01-12 13:32:10','2020-01-10 07:37:35','CR000004','1'),
	('SHS000121','S000061','2020-01-23 23:11:55','Draft','T000008','CDHS000061','2020-01-20 00:32:11','2020-01-24 00:52:54','CR000004','1'),
	('SHS000122','S000061','2020-01-26 06:47:33','Draft','T000008','CDHS000061','2020-01-05 18:05:35','2020-01-21 09:26:13','CR000004','1'),
	('SHS000123','S000062','2020-01-07 00:04:17','Draft','T000008','CDHS000062','2020-01-13 00:27:23','2020-01-07 05:43:07','CR000004','1'),
	('SHS000124','S000062','2020-01-09 14:22:00','Draft','T000008','CDHS000062','2020-01-17 18:51:45','2020-01-14 03:58:34','CR000004','1'),
	('SHS000125','S000063','2020-01-07 02:44:49','Draft','T000008','CDHS000063','2020-01-05 18:59:48','2020-01-04 23:10:45','CR000004','1'),
	('SHS000126','S000063','2020-01-09 12:55:21','Draft','T000008','CDHS000063','2020-01-14 05:59:18','2020-01-10 08:31:04','CR000004','1'),
	('SHS000127','S000064','2020-01-15 13:35:42','Draft','T000008','CDHS000064','2020-01-12 17:48:30','2020-01-18 11:55:36','CR000004','1'),
	('SHS000128','S000064','2020-01-08 15:01:03','Draft','T000008','CDHS000064','2020-01-14 05:24:55','2020-01-18 08:07:49','CR000004','1');

insert into student_daily_answer_data values
	('SDA000001','SHS000001','DSQ000001','A','2020-01-15 13:03:03','2020-01-21 16:16:16','CR000001','1'),
	('SDA000002','SHS000001','DSQ000001','B','2020-01-13 11:47:57','2020-01-11 08:22:20','CR000001','1'),
	('SDA000003','SHS000002','DSQ000002','A','2020-01-12 13:13:00','2020-01-19 13:19:00','CR000001','1'),
	('SDA000004','SHS000002','DSQ000002','B','2020-01-18 03:36:49','2020-01-08 18:09:16','CR000001','1'),
	('SDA000005','SHS000003','DSQ000003','A','2020-01-07 14:20:55','2020-01-18 22:00:54','CR000001','1'),
	('SDA000006','SHS000003','DSQ000003','B','2020-01-24 14:35:03','2020-01-24 12:40:17','CR000001','1'),
	('SDA000007','SHS000004','DSQ000004','A','2020-01-17 09:51:49','2020-01-09 17:40:45','CR000001','1'),
	('SDA000008','SHS000004','DSQ000004','B','2020-01-09 10:12:57','2020-01-24 00:20:05','CR000001','1'),
	('SDA000009','SHS000005','DSQ000005','A','2020-01-19 19:31:21','2020-01-14 21:44:27','CR000001','1'),
	('SDA000010','SHS000005','DSQ000005','B','2020-01-22 03:52:16','2020-01-10 02:33:22','CR000001','1'),
	('SDA000011','SHS000006','DSQ000006','A','2020-01-12 00:45:10','2020-01-20 04:09:03','CR000001','1'),
	('SDA000012','SHS000006','DSQ000006','B','2020-01-05 22:33:23','2020-01-11 18:13:45','CR000001','1'),
	('SDA000013','SHS000007','DSQ000007','A','2020-01-15 01:38:32','2020-01-19 02:44:49','CR000001','1'),
	('SDA000014','SHS000007','DSQ000007','B','2020-01-16 15:56:00','2020-01-20 10:43:40','CR000001','1'),
	('SDA000015','SHS000008','DSQ000008','A','2020-01-26 04:00:04','2020-01-06 01:35:43','CR000001','1'),
	('SDA000016','SHS000008','DSQ000008','B','2020-01-24 23:16:43','2020-01-09 15:19:26','CR000001','1'),
	('SDA000017','SHS000009','DSQ000009','A','2020-01-22 16:17:46','2020-01-17 17:06:35','CR000001','1'),
	('SDA000018','SHS000009','DSQ000009','B','2020-01-26 14:56:13','2020-01-10 17:01:46','CR000001','1'),
	('SDA000019','SHS000010','DSQ000010','A','2020-01-20 02:18:01','2020-01-23 02:21:53','CR000001','1'),
	('SDA000020','SHS000010','DSQ000010','B','2020-01-19 04:04:00','2020-01-24 21:00:13','CR000001','1'),
	('SDA000021','SHS000011','DSQ000011','A','2020-01-15 21:34:10','2020-01-15 16:28:15','CR000001','1'),
	('SDA000022','SHS000011','DSQ000011','B','2020-01-14 21:09:14','2020-01-25 13:01:20','CR000001','1'),
	('SDA000023','SHS000012','DSQ000012','A','2020-01-22 13:08:11','2020-01-11 02:44:35','CR000001','1'),
	('SDA000024','SHS000012','DSQ000012','B','2020-01-24 05:51:48','2020-01-25 10:05:09','CR000001','1'),
	('SDA000025','SHS000013','DSQ000013','A','2020-01-15 03:44:14','2020-01-12 10:17:43','CR000001','1'),
	('SDA000026','SHS000013','DSQ000013','B','2020-01-21 07:07:57','2020-01-23 19:03:12','CR000001','1'),
	('SDA000027','SHS000014','DSQ000014','A','2020-01-25 06:24:49','2020-01-12 23:24:26','CR000001','1'),
	('SDA000028','SHS000014','DSQ000014','B','2020-01-07 04:09:27','2020-01-05 19:01:15','CR000001','1'),
	('SDA000029','SHS000015','DSQ000015','A','2020-01-05 19:18:07','2020-01-06 17:09:16','CR000001','1'),
	('SDA000030','SHS000015','DSQ000015','B','2020-01-05 05:41:48','2020-01-07 21:48:56','CR000001','1'),
	('SDA000031','SHS000016','DSQ000016','A','2020-01-14 03:24:42','2020-01-07 09:58:09','CR000001','1'),
	('SDA000032','SHS000016','DSQ000016','B','2020-01-26 04:55:12','2020-01-08 14:51:53','CR000001','1'),
	('SDA000033','SHS000017','DSQ000017','A','2020-01-09 05:48:04','2020-01-22 21:59:04','CR000001','1'),
	('SDA000034','SHS000017','DSQ000017','B','2020-01-11 21:03:31','2020-01-13 06:12:17','CR000001','1'),
	('SDA000035','SHS000018','DSQ000018','A','2020-01-18 13:36:15','2020-01-07 12:14:28','CR000001','1'),
	('SDA000036','SHS000018','DSQ000018','B','2020-01-05 08:09:32','2020-01-10 06:07:16','CR000001','1'),
	('SDA000037','SHS000019','DSQ000019','A','2020-01-16 14:49:08','2020-01-24 10:04:34','CR000001','1'),
	('SDA000038','SHS000019','DSQ000019','B','2020-01-11 12:55:01','2020-01-21 19:28:38','CR000001','1'),
	('SDA000039','SHS000020','DSQ000020','A','2020-01-22 10:03:54','2020-01-08 17:53:10','CR000001','1'),
	('SDA000040','SHS000020','DSQ000020','B','2020-01-08 06:40:54','2020-01-22 07:57:47','CR000001','1'),
	('SDA000041','SHS000021','DSQ000021','A','2020-01-25 10:25:13','2020-01-23 15:56:50','CR000001','1'),
	('SDA000042','SHS000021','DSQ000021','B','2020-01-15 02:01:25','2020-01-23 08:18:31','CR000001','1'),
	('SDA000043','SHS000022','DSQ000022','A','2020-01-23 11:35:36','2020-01-11 05:54:05','CR000001','1'),
	('SDA000044','SHS000022','DSQ000022','B','2020-01-18 21:26:08','2020-01-21 23:19:56','CR000001','1'),
	('SDA000045','SHS000023','DSQ000023','A','2020-01-08 17:14:51','2020-01-24 19:03:07','CR000001','1'),
	('SDA000046','SHS000023','DSQ000023','B','2020-01-22 16:17:34','2020-01-14 20:45:33','CR000001','1'),
	('SDA000047','SHS000024','DSQ000024','A','2020-01-08 00:41:00','2020-01-17 16:47:32','CR000001','1'),
	('SDA000048','SHS000024','DSQ000024','B','2020-01-14 15:48:26','2020-01-07 18:25:13','CR000001','1'),
	('SDA000049','SHS000025','DSQ000025','A','2020-01-24 05:05:45','2020-01-09 19:16:44','CR000001','1'),
	('SDA000050','SHS000025','DSQ000025','B','2020-01-25 15:56:11','2020-01-19 01:20:59','CR000001','1'),
	('SDA000051','SHS000026','DSQ000026','A','2020-01-06 22:39:59','2020-01-23 11:40:58','CR000001','1'),
	('SDA000052','SHS000026','DSQ000026','B','2020-01-08 01:03:31','2020-01-24 04:50:16','CR000001','1'),
	('SDA000053','SHS000027','DSQ000027','A','2020-01-19 12:12:19','2020-01-09 07:47:35','CR000001','1'),
	('SDA000054','SHS000027','DSQ000027','B','2020-01-24 00:17:45','2020-01-11 00:26:45','CR000001','1'),
	('SDA000055','SHS000028','DSQ000028','A','2020-01-11 01:57:27','2020-01-21 00:01:31','CR000001','1'),
	('SDA000056','SHS000028','DSQ000028','B','2020-01-21 01:16:43','2020-01-24 13:46:12','CR000001','1'),
	('SDA000057','SHS000029','DSQ000029','A','2020-01-11 10:29:29','2020-01-19 23:22:17','CR000001','1'),
	('SDA000058','SHS000029','DSQ000029','B','2020-01-05 08:51:23','2020-01-20 05:45:43','CR000001','1'),
	('SDA000059','SHS000030','DSQ000030','A','2020-01-10 17:55:45','2020-01-23 17:21:13','CR000001','1'),
	('SDA000060','SHS000030','DSQ000030','B','2020-01-07 08:49:38','2020-01-17 08:25:30','CR000001','1'),
	('SDA000061','SHS000031','DSQ000031','A','2020-01-09 11:20:43','2020-01-17 10:59:23','CR000001','1'),
	('SDA000062','SHS000031','DSQ000031','B','2020-01-12 09:07:00','2020-01-23 07:10:53','CR000001','1'),
	('SDA000063','SHS000032','DSQ000032','A','2020-01-13 15:56:35','2020-01-11 13:56:22','CR000001','1'),
	('SDA000064','SHS000032','DSQ000032','B','2020-01-26 17:27:05','2020-01-26 13:16:36','CR000001','1'),
	('SDA000065','SHS000033','DSQ000033','A','2020-01-22 00:25:52','2020-01-13 12:24:36','CR000002','1'),
	('SDA000066','SHS000033','DSQ000033','B','2020-01-14 08:59:36','2020-01-14 17:29:48','CR000002','1'),
	('SDA000067','SHS000034','DSQ000034','A','2020-01-10 16:28:12','2020-01-11 05:00:00','CR000002','1'),
	('SDA000068','SHS000034','DSQ000034','B','2020-01-18 01:14:52','2020-01-15 10:48:25','CR000002','1'),
	('SDA000069','SHS000035','DSQ000035','A','2020-01-08 02:06:25','2020-01-22 15:31:25','CR000002','1'),
	('SDA000070','SHS000035','DSQ000035','B','2020-01-05 18:02:27','2020-01-13 20:40:42','CR000002','1'),
	('SDA000071','SHS000036','DSQ000036','A','2020-01-17 07:49:16','2020-01-26 11:42:49','CR000002','1'),
	('SDA000072','SHS000036','DSQ000036','B','2020-01-12 11:51:27','2020-01-15 22:14:52','CR000002','1'),
	('SDA000073','SHS000037','DSQ000037','A','2020-01-14 16:42:02','2020-01-07 03:00:07','CR000002','1'),
	('SDA000074','SHS000037','DSQ000037','B','2020-01-14 06:45:39','2020-01-11 20:16:02','CR000002','1'),
	('SDA000075','SHS000038','DSQ000038','A','2020-01-18 01:01:24','2020-01-22 00:53:44','CR000002','1'),
	('SDA000076','SHS000038','DSQ000038','B','2020-01-12 11:15:41','2020-01-10 13:01:39','CR000002','1'),
	('SDA000077','SHS000039','DSQ000039','A','2020-01-23 00:27:58','2020-01-05 17:48:33','CR000002','1'),
	('SDA000078','SHS000039','DSQ000039','B','2020-01-14 15:04:53','2020-01-21 19:06:04','CR000002','1'),
	('SDA000079','SHS000040','DSQ000040','A','2020-01-17 23:57:52','2020-01-14 11:23:08','CR000002','1'),
	('SDA000080','SHS000040','DSQ000040','B','2020-01-16 12:09:45','2020-01-17 16:37:36','CR000002','1'),
	('SDA000081','SHS000041','DSQ000041','A','2020-01-17 09:26:58','2020-01-22 00:07:46','CR000002','1'),
	('SDA000082','SHS000041','DSQ000041','B','2020-01-10 15:41:07','2020-01-06 08:10:24','CR000002','1'),
	('SDA000083','SHS000042','DSQ000042','A','2020-01-18 06:32:11','2020-01-25 04:12:07','CR000002','1'),
	('SDA000084','SHS000042','DSQ000042','B','2020-01-18 05:19:43','2020-01-25 10:44:29','CR000002','1'),
	('SDA000085','SHS000043','DSQ000043','A','2020-01-08 06:17:31','2020-01-19 13:39:41','CR000002','1'),
	('SDA000086','SHS000043','DSQ000043','B','2020-01-08 01:15:43','2020-01-09 17:49:45','CR000002','1'),
	('SDA000087','SHS000044','DSQ000044','A','2020-01-20 16:42:22','2020-01-26 00:06:56','CR000002','1'),
	('SDA000088','SHS000044','DSQ000044','B','2020-01-10 07:04:29','2020-01-16 10:15:46','CR000002','1'),
	('SDA000089','SHS000045','DSQ000045','A','2020-01-08 11:35:58','2020-01-05 19:26:01','CR000002','1'),
	('SDA000090','SHS000045','DSQ000045','B','2020-01-20 10:33:54','2020-01-17 00:42:12','CR000002','1'),
	('SDA000091','SHS000046','DSQ000046','A','2020-01-20 15:38:23','2020-01-14 23:41:42','CR000002','1'),
	('SDA000092','SHS000046','DSQ000046','B','2020-01-07 21:51:53','2020-01-24 22:34:22','CR000002','1'),
	('SDA000093','SHS000047','DSQ000047','A','2020-01-11 16:06:17','2020-01-14 17:53:36','CR000002','1'),
	('SDA000094','SHS000047','DSQ000047','B','2020-01-16 13:57:08','2020-01-09 15:59:55','CR000002','1'),
	('SDA000095','SHS000048','DSQ000048','A','2020-01-17 08:12:41','2020-01-13 22:40:42','CR000002','1'),
	('SDA000096','SHS000048','DSQ000048','B','2020-01-24 00:00:28','2020-01-19 18:51:31','CR000002','1'),
	('SDA000097','SHS000049','DSQ000049','A','2020-01-26 17:15:12','2020-01-24 23:25:28','CR000002','1'),
	('SDA000098','SHS000049','DSQ000049','B','2020-01-06 15:17:03','2020-01-13 22:30:51','CR000002','1'),
	('SDA000099','SHS000050','DSQ000050','A','2020-01-10 19:49:14','2020-01-26 12:31:19','CR000002','1'),
	('SDA000100','SHS000050','DSQ000050','B','2020-01-21 16:13:47','2020-01-24 19:21:10','CR000002','1'),
	('SDA000101','SHS000051','DSQ000051','A','2020-01-09 03:14:15','2020-01-07 02:02:29','CR000002','1'),
	('SDA000102','SHS000051','DSQ000051','B','2020-01-10 20:51:40','2020-01-09 08:01:13','CR000002','1'),
	('SDA000103','SHS000052','DSQ000052','A','2020-01-26 06:39:06','2020-01-22 08:58:57','CR000002','1'),
	('SDA000104','SHS000052','DSQ000052','B','2020-01-06 03:53:48','2020-01-18 17:47:49','CR000002','1'),
	('SDA000105','SHS000053','DSQ000053','A','2020-01-17 02:06:33','2020-01-15 17:37:16','CR000002','1'),
	('SDA000106','SHS000053','DSQ000053','B','2020-01-10 03:04:44','2020-01-07 17:59:21','CR000002','1'),
	('SDA000107','SHS000054','DSQ000054','A','2020-01-14 07:29:54','2020-01-18 18:58:59','CR000002','1'),
	('SDA000108','SHS000054','DSQ000054','B','2020-01-07 20:40:12','2020-01-15 12:14:08','CR000002','1'),
	('SDA000109','SHS000055','DSQ000055','A','2020-01-05 17:48:02','2020-01-06 05:49:03','CR000002','1'),
	('SDA000110','SHS000055','DSQ000055','B','2020-01-26 17:04:36','2020-01-06 13:16:42','CR000002','1'),
	('SDA000111','SHS000056','DSQ000056','A','2020-01-07 07:05:01','2020-01-07 23:25:02','CR000002','1'),
	('SDA000112','SHS000056','DSQ000056','B','2020-01-20 07:10:02','2020-01-08 19:50:38','CR000002','1'),
	('SDA000113','SHS000057','DSQ000057','A','2020-01-14 19:52:33','2020-01-24 12:37:06','CR000002','1'),
	('SDA000114','SHS000057','DSQ000057','B','2020-01-07 20:51:30','2020-01-05 20:58:31','CR000002','1'),
	('SDA000115','SHS000058','DSQ000058','A','2020-01-19 02:36:18','2020-01-09 07:20:40','CR000002','1'),
	('SDA000116','SHS000058','DSQ000058','B','2020-01-05 22:48:51','2020-01-18 00:53:23','CR000002','1'),
	('SDA000117','SHS000059','DSQ000059','A','2020-01-20 16:26:57','2020-01-23 03:29:30','CR000002','1'),
	('SDA000118','SHS000059','DSQ000059','B','2020-01-21 21:33:53','2020-01-23 02:48:14','CR000002','1'),
	('SDA000119','SHS000060','DSQ000060','A','2020-01-13 13:25:45','2020-01-05 18:01:45','CR000002','1'),
	('SDA000120','SHS000060','DSQ000060','B','2020-01-22 17:39:26','2020-01-17 14:44:00','CR000002','1'),
	('SDA000121','SHS000061','DSQ000061','A','2020-01-21 13:29:30','2020-01-07 02:04:53','CR000002','1'),
	('SDA000122','SHS000061','DSQ000061','B','2020-01-12 01:10:33','2020-01-21 07:17:46','CR000002','1'),
	('SDA000123','SHS000062','DSQ000062','A','2020-01-09 06:04:04','2020-01-10 21:52:41','CR000002','1'),
	('SDA000124','SHS000062','DSQ000062','B','2020-01-25 17:31:20','2020-01-20 01:22:19','CR000002','1'),
	('SDA000125','SHS000063','DSQ000063','A','2020-01-10 15:57:20','2020-01-13 20:48:00','CR000002','1'),
	('SDA000126','SHS000063','DSQ000063','B','2020-01-23 07:55:03','2020-01-23 16:55:52','CR000002','1'),
	('SDA000127','SHS000064','DSQ000064','A','2020-01-21 22:45:39','2020-01-14 21:55:34','CR000002','1'),
	('SDA000128','SHS000064','DSQ000064','B','2020-01-21 00:53:32','2020-01-17 23:49:33','CR000002','1'),
	('SDA000129','SHS000065','DSQ000065','A','2020-01-22 11:56:56','2020-01-07 10:40:17','CR000003','1'),
	('SDA000130','SHS000065','DSQ000065','B','2020-01-05 02:15:22','2020-01-13 19:14:48','CR000003','1'),
	('SDA000131','SHS000066','DSQ000066','A','2020-01-10 18:59:41','2020-01-22 18:31:45','CR000003','1'),
	('SDA000132','SHS000066','DSQ000066','B','2020-01-05 22:05:10','2020-01-22 08:28:31','CR000003','1'),
	('SDA000133','SHS000067','DSQ000067','A','2020-01-05 12:34:53','2020-01-11 18:46:59','CR000003','1'),
	('SDA000134','SHS000067','DSQ000067','B','2020-01-05 22:33:19','2020-01-13 11:55:34','CR000003','1'),
	('SDA000135','SHS000068','DSQ000068','A','2020-01-22 04:17:15','2020-01-10 02:30:33','CR000003','1'),
	('SDA000136','SHS000068','DSQ000068','B','2020-01-10 18:42:50','2020-01-22 21:26:51','CR000003','1'),
	('SDA000137','SHS000069','DSQ000069','A','2020-01-24 05:55:02','2020-01-05 01:53:38','CR000003','1'),
	('SDA000138','SHS000069','DSQ000069','B','2020-01-06 03:40:26','2020-01-15 06:27:42','CR000003','1'),
	('SDA000139','SHS000070','DSQ000070','A','2020-01-07 19:09:46','2020-01-20 18:12:47','CR000003','1'),
	('SDA000140','SHS000070','DSQ000070','B','2020-01-16 12:47:11','2020-01-09 08:57:26','CR000003','1'),
	('SDA000141','SHS000071','DSQ000071','A','2020-01-07 04:27:20','2020-01-20 23:21:08','CR000003','1'),
	('SDA000142','SHS000071','DSQ000071','B','2020-01-18 01:59:03','2020-01-18 19:59:56','CR000003','1'),
	('SDA000143','SHS000072','DSQ000072','A','2020-01-25 06:14:48','2020-01-23 04:31:55','CR000003','1'),
	('SDA000144','SHS000072','DSQ000072','B','2020-01-08 05:27:40','2020-01-22 00:06:27','CR000003','1'),
	('SDA000145','SHS000073','DSQ000073','A','2020-01-14 16:58:26','2020-01-10 14:16:54','CR000003','1'),
	('SDA000146','SHS000073','DSQ000073','B','2020-01-17 05:17:12','2020-01-12 05:57:59','CR000003','1'),
	('SDA000147','SHS000074','DSQ000074','A','2020-01-24 02:24:41','2020-01-23 21:20:18','CR000003','1'),
	('SDA000148','SHS000074','DSQ000074','B','2020-01-15 02:23:54','2020-01-08 03:38:46','CR000003','1'),
	('SDA000149','SHS000075','DSQ000075','A','2020-01-13 05:21:19','2020-01-10 07:22:54','CR000003','1'),
	('SDA000150','SHS000075','DSQ000075','B','2020-01-16 19:54:10','2020-01-05 16:41:25','CR000003','1'),
	('SDA000151','SHS000076','DSQ000076','A','2020-01-24 03:05:48','2020-01-21 20:59:26','CR000003','1'),
	('SDA000152','SHS000076','DSQ000076','B','2020-01-14 15:42:20','2020-01-12 07:59:33','CR000003','1'),
	('SDA000153','SHS000077','DSQ000077','A','2020-01-09 06:26:07','2020-01-26 15:54:02','CR000003','1'),
	('SDA000154','SHS000077','DSQ000077','B','2020-01-13 03:25:09','2020-01-12 14:36:08','CR000003','1'),
	('SDA000155','SHS000078','DSQ000078','A','2020-01-09 08:32:10','2020-01-06 20:45:39','CR000003','1'),
	('SDA000156','SHS000078','DSQ000078','B','2020-01-21 19:51:15','2020-01-05 15:37:28','CR000003','1'),
	('SDA000157','SHS000079','DSQ000079','A','2020-01-22 17:29:18','2020-01-19 04:07:54','CR000003','1'),
	('SDA000158','SHS000079','DSQ000079','B','2020-01-15 10:09:57','2020-01-09 17:38:16','CR000003','1'),
	('SDA000159','SHS000080','DSQ000080','A','2020-01-05 00:12:52','2020-01-12 05:02:19','CR000003','1'),
	('SDA000160','SHS000080','DSQ000080','B','2020-01-10 20:44:16','2020-01-24 01:32:25','CR000003','1'),
	('SDA000161','SHS000081','DSQ000081','A','2020-01-19 01:47:28','2020-01-16 13:35:11','CR000003','1'),
	('SDA000162','SHS000081','DSQ000081','B','2020-01-08 08:42:10','2020-01-22 15:49:59','CR000003','1'),
	('SDA000163','SHS000082','DSQ000082','A','2020-01-22 05:41:17','2020-01-20 01:33:44','CR000003','1'),
	('SDA000164','SHS000082','DSQ000082','B','2020-01-23 19:39:27','2020-01-20 04:05:27','CR000003','1'),
	('SDA000165','SHS000083','DSQ000083','A','2020-01-13 12:28:49','2020-01-13 11:35:52','CR000003','1'),
	('SDA000166','SHS000083','DSQ000083','B','2020-01-06 08:23:52','2020-01-07 00:19:13','CR000003','1'),
	('SDA000167','SHS000084','DSQ000084','A','2020-01-20 10:53:38','2020-01-25 12:07:56','CR000003','1'),
	('SDA000168','SHS000084','DSQ000084','B','2020-01-14 18:12:33','2020-01-21 06:17:23','CR000003','1'),
	('SDA000169','SHS000085','DSQ000085','A','2020-01-18 00:20:37','2020-01-22 17:18:29','CR000003','1'),
	('SDA000170','SHS000085','DSQ000085','B','2020-01-14 05:58:00','2020-01-11 08:31:37','CR000003','1'),
	('SDA000171','SHS000086','DSQ000086','A','2020-01-20 20:52:28','2020-01-24 05:39:35','CR000003','1'),
	('SDA000172','SHS000086','DSQ000086','B','2020-01-13 12:39:01','2020-01-09 06:42:05','CR000003','1'),
	('SDA000173','SHS000087','DSQ000087','A','2020-01-11 03:21:50','2020-01-06 15:23:24','CR000003','1'),
	('SDA000174','SHS000087','DSQ000087','B','2020-01-21 08:39:44','2020-01-18 04:46:48','CR000003','1'),
	('SDA000175','SHS000088','DSQ000088','A','2020-01-06 12:27:01','2020-01-22 12:48:10','CR000003','1'),
	('SDA000176','SHS000088','DSQ000088','B','2020-01-15 19:19:35','2020-01-16 20:48:44','CR000003','1'),
	('SDA000177','SHS000089','DSQ000089','A','2020-01-06 23:14:55','2020-01-08 02:27:39','CR000003','1'),
	('SDA000178','SHS000089','DSQ000089','B','2020-01-07 10:58:58','2020-01-25 19:43:09','CR000003','1'),
	('SDA000179','SHS000090','DSQ000090','A','2020-01-16 01:45:50','2020-01-20 05:38:36','CR000003','1'),
	('SDA000180','SHS000090','DSQ000090','B','2020-01-19 01:44:02','2020-01-19 09:59:14','CR000003','1'),
	('SDA000181','SHS000091','DSQ000091','A','2020-01-19 16:46:20','2020-01-18 08:56:16','CR000003','1'),
	('SDA000182','SHS000091','DSQ000091','B','2020-01-10 21:58:58','2020-01-19 04:07:24','CR000003','1'),
	('SDA000183','SHS000092','DSQ000092','A','2020-01-12 00:13:34','2020-01-10 15:39:31','CR000003','1'),
	('SDA000184','SHS000092','DSQ000092','B','2020-01-16 08:23:50','2020-01-18 17:42:59','CR000003','1'),
	('SDA000185','SHS000093','DSQ000093','A','2020-01-11 01:57:31','2020-01-22 20:05:13','CR000003','1'),
	('SDA000186','SHS000093','DSQ000093','B','2020-01-07 21:15:47','2020-01-09 06:33:02','CR000003','1'),
	('SDA000187','SHS000094','DSQ000094','A','2020-01-07 01:23:35','2020-01-24 21:02:22','CR000003','1'),
	('SDA000188','SHS000094','DSQ000094','B','2020-01-05 23:44:29','2020-01-18 17:03:04','CR000003','1'),
	('SDA000189','SHS000095','DSQ000095','A','2020-01-22 14:32:01','2020-01-13 19:25:31','CR000003','1'),
	('SDA000190','SHS000095','DSQ000095','B','2020-01-13 21:07:01','2020-01-19 09:11:56','CR000003','1'),
	('SDA000191','SHS000096','DSQ000096','A','2020-01-18 13:29:58','2020-01-12 12:12:41','CR000003','1'),
	('SDA000192','SHS000096','DSQ000096','B','2020-01-15 12:43:53','2020-01-14 18:56:11','CR000003','1'),
	('SDA000193','SHS000097','DSQ000097','A','2020-01-20 23:52:20','2020-01-06 02:37:19','CR000004','1'),
	('SDA000194','SHS000097','DSQ000097','B','2020-01-14 08:30:43','2020-01-11 03:47:08','CR000004','1'),
	('SDA000195','SHS000098','DSQ000098','A','2020-01-17 02:58:42','2020-01-13 07:48:18','CR000004','1'),
	('SDA000196','SHS000098','DSQ000098','B','2020-01-11 17:51:58','2020-01-15 06:32:30','CR000004','1'),
	('SDA000197','SHS000099','DSQ000099','A','2020-01-20 08:16:22','2020-01-08 23:03:15','CR000004','1'),
	('SDA000198','SHS000099','DSQ000099','B','2020-01-15 05:35:05','2020-01-12 07:51:15','CR000004','1'),
	('SDA000199','SHS000100','DSQ000100','A','2020-01-19 12:05:48','2020-01-06 18:20:11','CR000004','1'),
	('SDA000200','SHS000100','DSQ000100','B','2020-01-12 10:29:39','2020-01-08 04:05:35','CR000004','1'),
	('SDA000201','SHS000101','DSQ000101','A','2020-01-20 09:35:00','2020-01-22 17:34:25','CR000004','1'),
	('SDA000202','SHS000101','DSQ000101','B','2020-01-06 01:55:42','2020-01-12 02:06:27','CR000004','1'),
	('SDA000203','SHS000102','DSQ000102','A','2020-01-21 07:37:46','2020-01-14 04:38:49','CR000004','1'),
	('SDA000204','SHS000102','DSQ000102','B','2020-01-20 06:38:26','2020-01-12 00:06:28','CR000004','1'),
	('SDA000205','SHS000103','DSQ000103','A','2020-01-06 17:48:37','2020-01-25 00:25:59','CR000004','1'),
	('SDA000206','SHS000103','DSQ000103','B','2020-01-06 10:32:54','2020-01-06 03:58:16','CR000004','1'),
	('SDA000207','SHS000104','DSQ000104','A','2020-01-18 00:54:30','2020-01-05 02:21:19','CR000004','1'),
	('SDA000208','SHS000104','DSQ000104','B','2020-01-17 13:42:44','2020-01-25 20:20:18','CR000004','1'),
	('SDA000209','SHS000105','DSQ000105','A','2020-01-23 06:08:07','2020-01-25 12:43:34','CR000004','1'),
	('SDA000210','SHS000105','DSQ000105','B','2020-01-20 21:09:55','2020-01-09 14:23:34','CR000004','1'),
	('SDA000211','SHS000106','DSQ000106','A','2020-01-17 21:21:57','2020-01-17 21:22:27','CR000004','1'),
	('SDA000212','SHS000106','DSQ000106','B','2020-01-25 12:16:10','2020-01-05 23:31:38','CR000004','1'),
	('SDA000213','SHS000107','DSQ000107','A','2020-01-26 01:46:12','2020-01-11 22:53:51','CR000004','1'),
	('SDA000214','SHS000107','DSQ000107','B','2020-01-18 12:44:47','2020-01-14 01:28:39','CR000004','1'),
	('SDA000215','SHS000108','DSQ000108','A','2020-01-21 09:54:28','2020-01-13 15:21:01','CR000004','1'),
	('SDA000216','SHS000108','DSQ000108','B','2020-01-17 20:26:01','2020-01-23 22:04:23','CR000004','1'),
	('SDA000217','SHS000109','DSQ000109','A','2020-01-22 03:04:40','2020-01-08 14:04:17','CR000004','1'),
	('SDA000218','SHS000109','DSQ000109','B','2020-01-14 11:20:17','2020-01-25 07:09:48','CR000004','1'),
	('SDA000219','SHS000110','DSQ000110','A','2020-01-13 14:38:18','2020-01-15 18:28:20','CR000004','1'),
	('SDA000220','SHS000110','DSQ000110','B','2020-01-10 02:18:59','2020-01-21 10:43:01','CR000004','1'),
	('SDA000221','SHS000111','DSQ000111','A','2020-01-15 08:47:12','2020-01-15 03:45:21','CR000004','1'),
	('SDA000222','SHS000111','DSQ000111','B','2020-01-07 10:40:54','2020-01-15 04:08:24','CR000004','1'),
	('SDA000223','SHS000112','DSQ000112','A','2020-01-20 02:52:18','2020-01-21 04:06:22','CR000004','1'),
	('SDA000224','SHS000112','DSQ000112','B','2020-01-15 14:50:28','2020-01-07 05:44:46','CR000004','1'),
	('SDA000225','SHS000113','DSQ000113','A','2020-01-11 21:19:38','2020-01-14 19:16:20','CR000004','1'),
	('SDA000226','SHS000113','DSQ000113','B','2020-01-15 11:41:22','2020-01-22 10:24:57','CR000004','1'),
	('SDA000227','SHS000114','DSQ000114','A','2020-01-21 11:33:02','2020-01-05 19:54:35','CR000004','1'),
	('SDA000228','SHS000114','DSQ000114','B','2020-01-25 12:20:33','2020-01-12 04:37:15','CR000004','1'),
	('SDA000229','SHS000115','DSQ000115','A','2020-01-12 05:43:03','2020-01-26 15:39:57','CR000004','1'),
	('SDA000230','SHS000115','DSQ000115','B','2020-01-18 16:15:34','2020-01-06 17:55:55','CR000004','1'),
	('SDA000231','SHS000116','DSQ000116','A','2020-01-06 20:48:24','2020-01-25 01:28:42','CR000004','1'),
	('SDA000232','SHS000116','DSQ000116','B','2020-01-21 04:30:58','2020-01-23 20:55:48','CR000004','1'),
	('SDA000233','SHS000117','DSQ000117','A','2020-01-12 03:13:44','2020-01-08 17:26:59','CR000004','1'),
	('SDA000234','SHS000117','DSQ000117','B','2020-01-17 19:06:19','2020-01-07 15:49:50','CR000004','1'),
	('SDA000235','SHS000118','DSQ000118','A','2020-01-16 02:47:12','2020-01-19 17:10:24','CR000004','1'),
	('SDA000236','SHS000118','DSQ000118','B','2020-01-12 05:05:57','2020-01-16 12:58:23','CR000004','1'),
	('SDA000237','SHS000119','DSQ000119','A','2020-01-16 12:51:24','2020-01-13 16:08:08','CR000004','1'),
	('SDA000238','SHS000119','DSQ000119','B','2020-01-07 02:16:55','2020-01-04 23:34:48','CR000004','1'),
	('SDA000239','SHS000120','DSQ000120','A','2020-01-24 09:16:32','2020-01-18 06:35:32','CR000004','1'),
	('SDA000240','SHS000120','DSQ000120','B','2020-01-09 21:39:36','2020-01-23 10:52:34','CR000004','1'),
	('SDA000241','SHS000121','DSQ000121','A','2020-01-11 16:13:28','2020-01-19 18:54:06','CR000004','1'),
	('SDA000242','SHS000121','DSQ000121','B','2020-01-15 06:05:04','2020-01-21 07:45:26','CR000004','1'),
	('SDA000243','SHS000122','DSQ000122','A','2020-01-11 00:14:26','2020-01-06 18:31:37','CR000004','1'),
	('SDA000244','SHS000122','DSQ000122','B','2020-01-05 19:28:26','2020-01-06 12:08:35','CR000004','1'),
	('SDA000245','SHS000123','DSQ000123','A','2020-01-20 09:45:12','2020-01-25 23:57:24','CR000004','1'),
	('SDA000246','SHS000123','DSQ000123','B','2020-01-23 15:47:42','2020-01-05 14:05:31','CR000004','1'),
	('SDA000247','SHS000124','DSQ000124','A','2020-01-12 02:49:46','2020-01-14 02:28:04','CR000004','1'),
	('SDA000248','SHS000124','DSQ000124','B','2020-01-07 22:15:53','2020-01-06 11:44:46','CR000004','1'),
	('SDA000249','SHS000125','DSQ000125','A','2020-01-21 05:43:11','2020-01-19 09:32:48','CR000004','1'),
	('SDA000250','SHS000125','DSQ000125','B','2020-01-14 15:09:44','2020-01-05 08:10:47','CR000004','1'),
	('SDA000251','SHS000126','DSQ000126','A','2020-01-05 20:56:51','2020-01-24 02:18:13','CR000004','1'),
	('SDA000252','SHS000126','DSQ000126','B','2020-01-17 15:11:18','2020-01-11 11:27:25','CR000004','1'),
	('SDA000253','SHS000127','DSQ000127','A','2020-01-20 16:01:58','2020-01-20 18:09:45','CR000004','1'),
	('SDA000254','SHS000127','DSQ000127','B','2020-01-13 12:05:08','2020-01-24 12:03:29','CR000004','1'),
	('SDA000255','SHS000128','DSQ000128','A','2020-01-10 05:20:30','2020-01-10 01:52:26','CR000004','1'),
	('SDA000256','SHS000128','DSQ000128','B','2020-01-11 03:38:17','2020-01-15 15:43:24','CR000004','1');

insert into survey_status_data values
	('UnSubmitted','未提交','UnSubmitted','P000001','1'),
	('Submitted','已提交','Submitted','P000001','1'),
	('Draft','草稿','Draft','P000001','1');

insert into user_data values
	('U000001','张三','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000001','2020-01-26 07:57:32','P000001','1'),
	('U000002','李四','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000001','2020-01-24 16:05:55','P000001','1'),
	('U000003','王五','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000002','2020-01-15 18:59:09','P000001','1'),
	('U000004','张三','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000002','2020-01-19 12:03:33','P000001','1'),
	('U000005','李四','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000003','2020-01-12 01:31:19','P000001','1'),
	('U000006','王五','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000003','2020-01-20 20:13:22','P000001','1'),
	('U000007','张三','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000004','2020-01-16 14:27:00','P000001','1'),
	('U000008','李四','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000004','2020-01-23 15:59:45','P000001','1'),
	('U000009','王五','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000005','2020-01-24 10:01:13','P000001','1'),
	('U000010','张三','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000005','2020-01-06 21:31:00','P000001','1'),
	('U000011','李四','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000006','2020-01-24 17:44:13','P000001','1'),
	('U000012','王五','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000006','2020-01-10 17:32:01','P000001','1'),
	('U000013','张三','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000007','2020-01-09 04:14:43','P000001','1'),
	('U000014','李四','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000007','2020-01-11 12:12:39','P000001','1'),
	('U000015','王五','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000008','2020-01-10 22:17:00','P000001','1'),
	('U000016','张三','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000008','2020-01-23 20:00:04','P000001','1'),
	('U000017','李四','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000009','2020-01-26 10:29:54','P000001','1'),
	('U000018','王五','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000009','2020-01-23 14:22:05','P000001','1'),
	('U000019','张三','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000010','2020-01-13 20:02:36','P000001','1'),
	('U000020','李四','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000010','2020-01-06 20:39:14','P000001','1'),
	('U000021','王五','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000011','2020-01-13 18:41:40','P000001','1'),
	('U000022','张三','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000011','2020-01-16 03:08:11','P000001','1'),
	('U000023','李四','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000012','2020-01-23 11:21:30','P000001','1'),
	('U000024','王五','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000012','2020-01-24 18:40:31','P000001','1'),
	('U000025','张三','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000013','2020-01-15 00:46:03','P000001','1'),
	('U000026','李四','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000013','2020-01-16 14:31:39','P000001','1'),
	('U000027','王五','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000014','2020-01-06 16:43:55','P000001','1'),
	('U000028','张三','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000014','2020-01-14 12:29:55','P000001','1'),
	('U000029','李四','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000015','2020-01-25 07:04:24','P000001','1'),
	('U000030','王五','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000015','2020-01-16 02:13:11','P000001','1'),
	('U000031','张三','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000016','2020-01-06 05:19:43','P000001','1'),
	('U000032','李四','https://demo.doublechaintech.com/demodata/imageManager/genImage/wechat00/400/200/grey/','L000016','2020-01-14 13:14:58','P000001','1');

insert into wechat_login_info_data values
	('WLI000001','U000001','user123','user123','session123','2020-01-18 03:53:15','1'),
	('WLI000002','U000001','user1230002','user1230002','session1230002','2020-01-17 08:17:29','1'),
	('WLI000003','U000002','user1230003','user1230003','session1230003','2020-01-12 07:14:47','1'),
	('WLI000004','U000002','user1230004','user1230004','session1230004','2020-01-17 01:13:08','1'),
	('WLI000005','U000003','user1230005','user1230005','session1230005','2020-01-07 08:21:17','1'),
	('WLI000006','U000003','user1230006','user1230006','session1230006','2020-01-24 12:00:12','1'),
	('WLI000007','U000004','user1230007','user1230007','session1230007','2020-01-21 17:27:41','1'),
	('WLI000008','U000004','user1230008','user1230008','session1230008','2020-01-13 10:48:01','1'),
	('WLI000009','U000005','user1230009','user1230009','session1230009','2020-01-20 21:53:05','1'),
	('WLI000010','U000005','user1230010','user1230010','session1230010','2020-01-24 02:47:36','1'),
	('WLI000011','U000006','user1230011','user1230011','session1230011','2020-01-18 21:36:49','1'),
	('WLI000012','U000006','user1230012','user1230012','session1230012','2020-01-08 15:53:43','1'),
	('WLI000013','U000007','user1230013','user1230013','session1230013','2020-01-18 22:18:07','1'),
	('WLI000014','U000007','user1230014','user1230014','session1230014','2020-01-14 08:31:16','1'),
	('WLI000015','U000008','user1230015','user1230015','session1230015','2020-01-13 02:18:47','1'),
	('WLI000016','U000008','user1230016','user1230016','session1230016','2020-01-24 20:45:37','1'),
	('WLI000017','U000009','user1230017','user1230017','session1230017','2020-01-20 21:44:55','1'),
	('WLI000018','U000009','user1230018','user1230018','session1230018','2020-01-15 09:41:23','1'),
	('WLI000019','U000010','user1230019','user1230019','session1230019','2020-01-14 12:57:57','1'),
	('WLI000020','U000010','user1230020','user1230020','session1230020','2020-01-22 11:52:55','1'),
	('WLI000021','U000011','user1230021','user1230021','session1230021','2020-01-20 01:53:47','1'),
	('WLI000022','U000011','user1230022','user1230022','session1230022','2020-01-24 13:41:28','1'),
	('WLI000023','U000012','user1230023','user1230023','session1230023','2020-01-24 14:16:04','1'),
	('WLI000024','U000012','user1230024','user1230024','session1230024','2020-01-22 12:17:56','1'),
	('WLI000025','U000013','user1230025','user1230025','session1230025','2020-01-13 05:10:19','1'),
	('WLI000026','U000013','user1230026','user1230026','session1230026','2020-01-19 22:52:21','1'),
	('WLI000027','U000014','user1230027','user1230027','session1230027','2020-01-07 14:05:45','1'),
	('WLI000028','U000014','user1230028','user1230028','session1230028','2020-01-08 22:26:43','1'),
	('WLI000029','U000015','user1230029','user1230029','session1230029','2020-01-16 16:53:53','1'),
	('WLI000030','U000015','user1230030','user1230030','session1230030','2020-01-16 20:46:55','1'),
	('WLI000031','U000016','user1230031','user1230031','session1230031','2020-01-16 19:19:29','1'),
	('WLI000032','U000016','user1230032','user1230032','session1230032','2020-01-08 07:52:12','1'),
	('WLI000033','U000017','user1230033','user1230033','session1230033','2020-01-24 14:27:47','1'),
	('WLI000034','U000017','user1230034','user1230034','session1230034','2020-01-16 11:09:13','1'),
	('WLI000035','U000018','user1230035','user1230035','session1230035','2020-01-26 02:55:10','1'),
	('WLI000036','U000018','user1230036','user1230036','session1230036','2020-01-17 08:05:59','1'),
	('WLI000037','U000019','user1230037','user1230037','session1230037','2020-01-10 02:39:58','1'),
	('WLI000038','U000019','user1230038','user1230038','session1230038','2020-01-25 15:36:29','1'),
	('WLI000039','U000020','user1230039','user1230039','session1230039','2020-01-24 10:42:37','1'),
	('WLI000040','U000020','user1230040','user1230040','session1230040','2020-01-05 14:50:08','1'),
	('WLI000041','U000021','user1230041','user1230041','session1230041','2020-01-15 20:03:26','1'),
	('WLI000042','U000021','user1230042','user1230042','session1230042','2020-01-10 15:53:07','1'),
	('WLI000043','U000022','user1230043','user1230043','session1230043','2020-01-14 20:19:46','1'),
	('WLI000044','U000022','user1230044','user1230044','session1230044','2020-01-22 20:50:33','1'),
	('WLI000045','U000023','user1230045','user1230045','session1230045','2020-01-10 17:22:08','1'),
	('WLI000046','U000023','user1230046','user1230046','session1230046','2020-01-09 16:18:31','1'),
	('WLI000047','U000024','user1230047','user1230047','session1230047','2020-01-08 19:53:24','1'),
	('WLI000048','U000024','user1230048','user1230048','session1230048','2020-01-10 19:39:43','1'),
	('WLI000049','U000025','user1230049','user1230049','session1230049','2020-01-23 18:27:31','1'),
	('WLI000050','U000025','user1230050','user1230050','session1230050','2020-01-07 20:27:29','1'),
	('WLI000051','U000026','user1230051','user1230051','session1230051','2020-01-13 12:54:49','1'),
	('WLI000052','U000026','user1230052','user1230052','session1230052','2020-01-15 20:41:26','1'),
	('WLI000053','U000027','user1230053','user1230053','session1230053','2020-01-08 14:54:47','1'),
	('WLI000054','U000027','user1230054','user1230054','session1230054','2020-01-24 05:57:33','1'),
	('WLI000055','U000028','user1230055','user1230055','session1230055','2020-01-10 17:09:50','1'),
	('WLI000056','U000028','user1230056','user1230056','session1230056','2020-01-20 07:00:00','1'),
	('WLI000057','U000029','user1230057','user1230057','session1230057','2020-01-22 11:14:07','1'),
	('WLI000058','U000029','user1230058','user1230058','session1230058','2020-01-20 16:26:26','1'),
	('WLI000059','U000030','user1230059','user1230059','session1230059','2020-01-21 00:25:10','1'),
	('WLI000060','U000030','user1230060','user1230060','session1230060','2020-01-12 22:55:33','1'),
	('WLI000061','U000031','user1230061','user1230061','session1230061','2020-01-18 00:18:52','1'),
	('WLI000062','U000031','user1230062','user1230062','session1230062','2020-01-24 03:40:19','1'),
	('WLI000063','U000032','user1230063','user1230063','session1230063','2020-01-10 07:25:07','1'),
	('WLI000064','U000032','user1230064','user1230064','session1230064','2020-01-13 03:44:37','1');

insert into change_request_data values
	('CR000001','答题','2020-01-17 10:18:05','8.8.8.8','LOGIN','P000001','1'),
	('CR000002','答题0002','2020-01-16 08:54:14','8.8.8.8','LOGIN','P000001','1'),
	('CR000003','答题0003','2020-01-05 08:02:20','8.8.8.8','LOGIN','P000001','1'),
	('CR000004','答题0004','2020-01-05 03:26:45','8.8.8.8','LOGIN','P000001','1');

insert into change_request_type_data values
	('LOGIN','登录','LOGIN','book','1','    一段样例文字。    一段样例文字。\n可以分段。可以分段。\n\n可以空行。可以空行。\n\n','    一段样例文字。    一段样例文字。\n可以分段。可以分段。\n\n可以空行。可以空行。\n\n','P000001','1');

insert into wechat_workapp_identify_data values
	('WWI000001','corporation123','user123','SU000001','2020-01-14 17:38:10','2020-01-23 11:37:00','1'),
	('WWI000002','corporation1230002','user1230002','SU000001','2020-01-17 13:10:36','2020-01-16 10:06:39','1'),
	('WWI000003','corporation1230003','user1230003','SU000002','2020-01-19 17:17:35','2020-01-22 14:11:50','1'),
	('WWI000004','corporation1230004','user1230004','SU000002','2020-01-16 16:13:49','2020-01-26 10:29:01','1');

insert into wechat_miniapp_identify_data values
	('WMI000001','wechat_open_id_1234567890','wechat_miniapp_id_1234567890','SU000001','2020-01-25 11:45:20','2020-01-05 05:36:03','1'),
	('WMI000002','wechat_open_id_12345678900002','wechat_miniapp_id_12345678900002','SU000001','2020-01-20 20:30:23','2020-01-07 17:59:25','1'),
	('WMI000003','wechat_open_id_12345678900003','wechat_miniapp_id_12345678900003','SU000002','2020-01-24 09:59:22','2020-01-18 03:07:03','1'),
	('WMI000004','wechat_open_id_12345678900004','wechat_miniapp_id_12345678900004','SU000002','2020-01-16 04:19:10','2020-01-26 14:11:15','1');







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
('ANSWER_SURVEY','ANSWER_SURVEY','ANSWER_SURVEY','user','2','Platform','{}','P000001',1),
('REGISTER_TEACHER','REGISTER_TEACHER','REGISTER_TEACHER','user','3','Platform','{}','P000001',1),
('CREATE_SURVEY','CREATE_SURVEY','CREATE_SURVEY','user','4','Platform','{}','P000001',1),
('ANSWER_QUESTION','ANSWER_QUESTION','ANSWER_QUESTION','user','5','Platform','{}','P000001',1),
('CREATE_QUESTION','CREATE_QUESTION','CREATE_QUESTION','user','6','Platform','{}','P000001',1),
('REGISTER_STUDENT','REGISTER_STUDENT','REGISTER_STUDENT','user','7','Platform','{}','P000001',1);

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
create  index idx4create_time_of_teacher on teacher_data (create_time);

create unique index idx4id_ver_of_student on student_data (id, version);
create  index idx4student_id_of_student on student_data (student_id);
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
	fk4cq_of_teacher_data foreign key (cq) references change_request_data(id) ON DELETE CASCADE ON UPDATE CASCADE;

alter table student_data add constraint pk4id_of_student_data primary key (id);
alter table student_data add constraint 
	fk4address_of_student_data foreign key (address) references location_data(id) ON DELETE CASCADE ON UPDATE CASCADE;
alter table student_data add constraint 
	fk4user_of_student_data foreign key (user) references user_data(id) ON DELETE CASCADE ON UPDATE CASCADE;
alter table student_data add constraint 
	fk4platform_of_student_data foreign key (platform) references platform_data(id) ON DELETE CASCADE ON UPDATE CASCADE;
alter table student_data add constraint 
	fk4cq_of_student_data foreign key (cq) references change_request_data(id) ON DELETE CASCADE ON UPDATE CASCADE;

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
	fk4cq_of_class_daily_health_survey_data foreign key (cq) references change_request_data(id) ON DELETE CASCADE ON UPDATE CASCADE;

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

