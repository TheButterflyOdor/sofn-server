-- 万继聪这是增加字段的的sql
ALTER TABLE ASMS_INSPECTION_TASK ADD (DETAIL_CONTENT VARCHAR2(1024));
alter table ASMS_INSPECTION_TASK modify(CREATE_ORG_NAME varchar2(128));


--徐权 2018-2-28 增加 消息队列 处理记录表

DROP TABLE MQ_INFO;

CREATE TABLE MQ_INFO (
"ID" CHAR(64 BYTE) NOT NULL ,
"PROVIDER" VARCHAR2(255 BYTE) NULL ,
"CONSUMER" VARCHAR2(255 BYTE) NULL ,
"TITLE" VARCHAR2(255 BYTE) NULL ,
"CONTENT" VARCHAR2(255 BYTE) NULL ,
"RECEIVE_FLAG" CHAR(1 BYTE) NULL ,
"REMARK" VARCHAR2(1000 BYTE) NULL ,
"CREATE_BY" CHAR(64 BYTE) NULL ,
"CREATE_TIME" DATE NULL ,
"UPDATE_BY" CHAR(64 BYTE) NULL ,
"UPDATE_TIME" DATE NULL ,
"DEL_FLAG" CHAR(1 BYTE) NULL
);

ALTER TABLE MQ_INFO ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE MQ_INFO ADD PRIMARY KEY ("ID");

-- 万继聪 2018-3-2 bps流程数据库增加字段
ALTER TABLE ASMS_SUBJ_SV_CHANGE ADD WORKITEMID VARCHAR2(100)

	-- 万继聪 2018-3-9 临时主体,增加组织机构字段
ALTER TABLE ASMS_SUBJ_ENT_TEMP ADD ORGANIZATION_CODE VARCHAR2(200);

-- 万继聪 2018-3-19 创建临时主体数据字典(正式环境未创建会导致临时主体的主体类型无法加载)
INSERT INTO SYS_DICT_TYPE (
	ID,
	CODE,
	PID,
	NAME,
	CREATE_BY,
	CREATE_TIME,
	UPDATE_BY,
	UPDATE_TIME,
	DEL_FLAG,
	REMARK,
	ENABLE
)
VALUES
	(
		'24ad41f84b944e93bab5d18ab1b0d00de36cad6c459e44eb90265b883f49598d',
		'linshizhutileixing',
		'bcf062df2aa94c308a55d6a1acec941eda6cb4b29bcc4615a9cb73c230a3c2a1',
		'临时主体类型',
		'admin',
		TO_DATE (
			'2018-02-26 11:46:21',
			'SYYYY-MM-DD HH24:MI:SS'
		),
		'admin',
		TO_DATE (
			'2018-02-26 11:46:21',
			'SYYYY-MM-DD HH24:MI:SS'
		),
		'N',
		'临时主体类型',
		'Y'
	);
	-- 生产主体
	INSERT INTO SYS_DICT_DATA (
	ID,
	TYPE_ID,
	DICT_CODE,
	DICT_NAME,
	DICT_VALUE,
	DEL_FLAG,
	ENABLE,
	REMARK,
	CREATE_BY,
	CREATE_TIME,
	UPDATE_BY,
	UPDATE_TIME
)
VALUES
	(
		'4acca593f08d472291363a9f62bd2b9ce18ac1f1fd3646ba87058a7322857819',
		'24ad41f84b944e93bab5d18ab1b0d00de36cad6c459e44eb90265b883f49598d',
		'shengchanzhuti',
		'生产主体',
		'0',
		'N',
		'Y',
		'测试数据',
		'admin',
		TO_DATE (
			'2018-02-26 14:03:30',
			'SYYYY-MM-DD HH24:MI:SS'
		),
		'admin',
		TO_DATE (
			'2018-02-28 14:42:35',
			'SYYYY-MM-DD HH24:MI:SS'
		)
	);
	-- 经营主体
	INSERT INTO SYS_DICT_DATA (
	ID,
	TYPE_ID,
	DICT_CODE,
	DICT_NAME,
	DICT_VALUE,
	DEL_FLAG,
	ENABLE,
	REMARK,
	CREATE_BY,
	CREATE_TIME,
	UPDATE_BY,
	UPDATE_TIME
)
VALUES
	(
		'9c2b8c8698454f96a0c034f1059409368030ce166ab14290b3cc3a9523d02251',
		'24ad41f84b944e93bab5d18ab1b0d00de36cad6c459e44eb90265b883f49598d',
		'jingyingzhuti',
		'经营主体',
		'1',
		'N',
		'Y',
		'测试数据',
		'admin',
		TO_DATE (
			'2018-02-26 14:06:00',
			'SYYYY-MM-DD HH24:MI:SS'
		),
		'admin',
		TO_DATE (
			'2018-02-26 14:06:00',
			'SYYYY-MM-DD HH24:MI:SS'
		)
	);
	-- 生产经营主体
	INSERT INTO SYS_DICT_DATA (
	ID,
	TYPE_ID,
	DICT_CODE,
	DICT_NAME,
	DICT_VALUE,
	DEL_FLAG,
	ENABLE,
	REMARK,
	CREATE_BY,
	CREATE_TIME,
	UPDATE_BY,
	UPDATE_TIME
)
VALUES
	(
		'be52e2b50b1d4f96956f079f2f29a1e4e4e0a1ed784f4c7091f8e5f9628ac07c',
		'24ad41f84b944e93bab5d18ab1b0d00de36cad6c459e44eb90265b883f49598d',
		'shengchanjingyingzhuti',
		'生产经营主体',
		'2',
		'N',
		'Y',
		'测试数据',
		'admin',
		TO_DATE (
			'2018-02-26 14:06:00',
			'SYYYY-MM-DD HH24:MI:SS'
		),
		'admin',
		TO_DATE (
			'2018-02-26 14:06:00',
			'SYYYY-MM-DD HH24:MI:SS'
		)
	);
-- 农资主体
INSERT INTO SYS_DICT_DATA (
	ID,
	TYPE_ID,
	DICT_CODE,
	DICT_NAME,
	DICT_VALUE,
	DEL_FLAG,
	ENABLE,
	REMARK,
	CREATE_BY,
	CREATE_TIME,
	UPDATE_BY,
	UPDATE_TIME
)
VALUES
	(
		'a208f1073afd4df480171c292e06c37a6af158789d254d81962d3fa781f288fc',
		'24ad41f84b944e93bab5d18ab1b0d00de36cad6c459e44eb90265b883f49598d',
		'nongzizhuti',
		'农资主体',
		'3',
		'N',
		'Y',
		'测试数据',
		'admin',
		TO_DATE (
			'2018-02-26 14:06:00',
			'SYYYY-MM-DD HH24:MI:SS'
		),
		'admin',
		TO_DATE (
			'2018-02-26 14:06:00',
			'SYYYY-MM-DD HH24:MI:SS'
		)
	);

-- 万继聪 2018-3-19 临时主体,数据字典修改,原有数据修正(需在代码部署和临时主体数据字典创建成功之后执行)
UPDATE ASMS_SUBJ_ENT_TEMP
SET ENTITY_TYPE_ID = (
	SELECT
		D . ID
	FROM
		SYS_DICT_TYPE T
	LEFT JOIN SYS_DICT_DATA D ON T . ID = D .TYPE_ID
	WHERE
		T .CODE = 'linshizhutileixing'
	AND D .DICT_NAME = '生产主体'
)
WHERE
	ENTITY_TYPE = '生产主体';
UPDATE ASMS_SUBJ_ENT_TEMP
SET ENTITY_TYPE_ID = (
	SELECT
		D . ID
	FROM
		SYS_DICT_TYPE T
	LEFT JOIN SYS_DICT_DATA D ON T . ID = D .TYPE_ID
	WHERE
		T .CODE = 'linshizhutileixing'
	AND D .DICT_NAME = '经营主体'
)
WHERE
	ENTITY_TYPE = '经营主体';
UPDATE ASMS_SUBJ_ENT_TEMP
SET ENTITY_TYPE_ID = (
	SELECT
		D . ID
	FROM
		SYS_DICT_TYPE T
	LEFT JOIN SYS_DICT_DATA D ON T . ID = D .TYPE_ID
	WHERE
		T .CODE = 'linshizhutileixing'
	AND D .DICT_NAME = '生产经营主体'
)
WHERE
	ENTITY_TYPE = '生产经营主体';
UPDATE ASMS_SUBJ_ENT_TEMP
SET ENTITY_TYPE_ID = (
	SELECT
		D . ID
	FROM
		SYS_DICT_TYPE T
	LEFT JOIN SYS_DICT_DATA D ON T . ID = D .TYPE_ID
	WHERE
		T .CODE = 'linshizhutileixing'
	AND D .DICT_NAME = '农资主体'
)
WHERE
	ENTITY_TYPE = '农资主体';

-- 万继聪 2018-3-19 巡查结果数据字段修正(数据库实际存值与巡查结果字典有误,未修改数据库会导致添加的数据出错)
UPDATE SYS_DICT_DATA SET  DICT_VALUE = '1' WHERE id = 'f7a32d9a8ca64684aad21b0cdfe1ff9421aaa4ba9f68409083c98f5bf8e76a0d';
UPDATE SYS_DICT_DATA SET  DICT_VALUE = '2' WHERE id = '7be5e55ac26f47ecb9d0ffcd2f80c177ba7263c9f9b04b26a7c1de6c510a0265';
UPDATE SYS_DICT_DATA SET  DICT_VALUE = '3' WHERE id = '1cf56b534aac45efad58ebc14d96bb778783c99e65424511bb55f65ca93e1c8c';
-- 万继聪 2018-4-17 将检测系统任务状态同步到监管系统(解决无法改变状态的历史任务状态与监管同步)
update  ASMS_ROUTINE_MONITOR SET RM_STATE = '3' where id in (select a.SUP_ID from ADS_MONITOR_TASK a where a.PUBLISH_STATUS = '2');
update  ASMS_ROUTINE_MONITOR SET RM_STATE = '5' where id in (select a.SUP_ID from ADS_MONITOR_TASK a where a.PUBLISH_STATUS = '3');
update  ASMS_ROUTINE_MONITOR SET RM_STATE = '4' where id in (select a.SUP_ID from ADS_MONITOR_TASK a where a.PUBLISH_STATUS = '4');
update  ASMS_SPECIAL_MONITOR SET SM_STATE = '3' where id in (select a.SUP_ID from ADS_MONITOR_TASK a where a.PUBLISH_STATUS = '2');
update  ASMS_SPECIAL_MONITOR SET SM_STATE = '5' where id in (select a.SUP_ID from ADS_MONITOR_TASK a where a.PUBLISH_STATUS = '3');
update  ASMS_SPECIAL_MONITOR SET SM_STATE = '4' where id in (select a.SUP_ID from ADS_MONITOR_TASK a where a.PUBLISH_STATUS = '4');
update  ASMS_CHECK_TASK SET STATE = '3' where id in (select a.SUP_ID from ADS_MONITOR_TASK a where a.PUBLISH_STATUS = '2');
update  ASMS_CHECK_TASK SET STATE = '5' where id in (select a.SUP_ID from ADS_MONITOR_TASK a where a.PUBLISH_STATUS = '3');
update  ASMS_CHECK_TASK SET STATE = '4' where id in (select a.SUP_ID from ADS_MONITOR_TASK a where a.PUBLISH_STATUS = '4');
update  ASMS_RECHECK_TASK SET STATE = '3' where id in (select a.id from ADS_MONITOR_TASK a where a.PUBLISH_STATUS = '2');
update  ASMS_RECHECK_TASK SET STATE = '5' where id in (select a.id from ADS_MONITOR_TASK a where a.PUBLISH_STATUS = '3');
update  ASMS_RECHECK_TASK SET STATE = '4' where id in (select a.id from ADS_MONITOR_TASK a where a.PUBLISH_STATUS = '4');
