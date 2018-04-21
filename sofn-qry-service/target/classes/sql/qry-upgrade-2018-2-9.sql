-- 万继聪 2018-4-16 查询系统,新增菜单,部署后更新
-- 新增打印信息查询菜单
INSERT INTO SYS_MENU (
	ID,
	MENU_NAME,
	PARENT_ID,
	MENU_TYPE,
	MENU_VALUE,
	NUMBERS,
	URL,
	DESCRIBES,
	STATUS,
	CREATE_BY,
	CREATE_TIME,
	DEL_FLAG
)
VALUES
	(
		'878f02ef-1b23-40b4-8d8f-e1e7101cda10',
		'打印信息查询',
		'223c608a-771c-4e89-bf11-ad75602bdb92',
		'1',
		'QRY',
		'233',
		'index.content.qry/traceCodePrint/qrySubjEntPrint',
		'打印信息查询',
		'1',
		'05b92ff5112d4882bfc6c69cfb293cbc5f28e9002aa24b109afa70a974e5944e',
		TO_TIMESTAMP (
			' 2018-03-30 15:18:21:036000',
			'SYYYY-MM-DD HH24:MI:SS:FF6'
		),
		'N'
	);
-- 新增主体交易信息查询菜单
INSERT INTO SYS_MENU (
	ID,
	MENU_NAME,
	PARENT_ID,
	MENU_TYPE,
	MENU_VALUE,
	NUMBERS,
	URL,
	DESCRIBES,
	STATUS,
	CREATE_BY,
	CREATE_TIME,
	DEL_FLAG
)
VALUES
	(
		'2a8b6b30-e7c2-4f70-8de1-a0656a66fbf8',
		'主体交易信息查询',
		'223c608a-771c-4e89-bf11-ad75602bdb92',
		'1',
		'QRY',
		'232',
		'index.content.qry/subjEntTransaction/qrySubjEntTransaction',
		'主体交易信息查询',
		'1',
		'05b92ff5112d4882bfc6c69cfb293cbc5f28e9002aa24b109afa70a974e5944e',
		TO_TIMESTAMP (
			' 2018-03-30 15:14:44:010000',
			'SYYYY-MM-DD HH24:MI:SS:FF6'
		),
		'N'
	);
-- 主体追溯码查询菜单
INSERT INTO SYS_MENU(
	ID,
	MENU_NAME,
	PARENT_ID,
	MENU_TYPE,
	MENU_VALUE,
	NUMBERS,
	URL,
	DESCRIBES,
	STATUS,
	CREATE_BY,
	CREATE_TIME,
	DEL_FLAG
)
VALUES
	(
		'8ee6a02c-a67d-4d36-a87a-f93140eefec3',
		'追溯码查询',
		'223c608a-771c-4e89-bf11-ad75602bdb92',
		'1',
		'QRY',
		'231',
		'index.content.qry/traceCodeManage/qryBathList',
		'追溯码查询',
		'1',
		'181087eb9d0c4c46af3bb98da6eead9ca8b4d8a5b2294372a68636a092a6f75c',
		TO_TIMESTAMP (
			' 2018-03-29 16:05:46:418000',
			'SYYYY-MM-DD HH24:MI:SS:FF6'
		),
		'N'
	);