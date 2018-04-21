--李江 2018-2-9
UPDATE SYS_CODE_RULE_FIELD_NEW
SET length = '5'
WHERE
	id = (
SELECT
	t2.id
FROM
	SYS_CODE_RULE_NEW t1
	JOIN SYS_CODE_RULE_FIELD_NEW t2 ON t1.id = t2.RULE_ID
WHERE
	t1.rule_name = '主体身份码'
	AND t2.name = '顺序码'
	);


-- 2018-3-8 新增建表和插入数据的SQL
INSERT INTO "SYS_DICT_TYPE"("ID", "CODE", "PID", "NAME", "CREATE_BY", "CREATE_TIME", "UPDATE_BY", "UPDATE_TIME", "DEL_FLAG", "RESERVED_FIELD1", "RESERVED_FIELD2", "REMARK", "ENABLE", "MARK") VALUES ('fb429a7e7b23475d8eb1d322950116a0567ac8194a7444e2b566903dde112ad8', 'documenttype', 'bcf062df2aa94c308a55d6a1acec941eda6cb4b29bcc4615a9cb73c230a3c2a1', '文档类别', 'admin', TO_DATE('2018-02-28 14:48:43', 'SYYYY-MM-DD HH24:MI:SS'), 'admin', TO_DATE('2018-02-28 14:48:43', 'SYYYY-MM-DD HH24:MI:SS'), 'N', NULL, NULL, '在线编辑功能新增文档时使用该字典', 'Y', NULL);
INSERT INTO "SYS_DICT_DATA"("ID", "TYPE_ID", "CATEGORIE_ID", "DICT_CODE", "DICT_NAME", "SPELL_NAME", "DICT_VALUE", "FIXED", "DEL_FLAG", "ENABLE", "REMARK", "CREATE_BY", "CREATE_TIME", "UPDATE_BY", "UPDATE_TIME", "RESERVED_FIELD1", "RESERVED_FIELD2", "SIMPLE_NAME") VALUES ('7dd3a26aec5242d58f93801937bd9f21e33131bde148416e9b1258e95cd76625', 'fb429a7e7b23475d8eb1d322950116a0567ac8194a7444e2b566903dde112ad8', NULL, 'doctype_qt', '其他', 'qt', '其他', NULL, 'N', 'Y', NULL, 'admin', TO_DATE('2018-03-02 11:45:37', 'SYYYY-MM-DD HH24:MI:SS'), 'admin', TO_DATE('2018-03-02 11:45:37', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL, NULL);
INSERT INTO "SYS_DICT_DATA"("ID", "TYPE_ID", "CATEGORIE_ID", "DICT_CODE", "DICT_NAME", "SPELL_NAME", "DICT_VALUE", "FIXED", "DEL_FLAG", "ENABLE", "REMARK", "CREATE_BY", "CREATE_TIME", "UPDATE_BY", "UPDATE_TIME", "RESERVED_FIELD1", "RESERVED_FIELD2", "SIMPLE_NAME") VALUES ('6315cadf74a44f65af0a60e134638aa40677181f2d94429e901bb83f83e6c24b', 'fb429a7e7b23475d8eb1d322950116a0567ac8194a7444e2b566903dde112ad8', NULL, 'doctype_cjwt', '常见问题', 'cjwt', '常见问题', NULL, 'N', 'Y', NULL, 'admin', TO_DATE('2018-03-02 11:45:04', 'SYYYY-MM-DD HH24:MI:SS'), 'admin', TO_DATE('2018-03-02 11:45:04', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL, NULL);
INSERT INTO "SYS_DICT_DATA"("ID", "TYPE_ID", "CATEGORIE_ID", "DICT_CODE", "DICT_NAME", "SPELL_NAME", "DICT_VALUE", "FIXED", "DEL_FLAG", "ENABLE", "REMARK", "CREATE_BY", "CREATE_TIME", "UPDATE_BY", "UPDATE_TIME", "RESERVED_FIELD1", "RESERVED_FIELD2", "SIMPLE_NAME") VALUES ('b9667739133e423d84883526ab84a1d22b0cd3b4ed354a3c8c0d02670c75539a', 'fb429a7e7b23475d8eb1d322950116a0567ac8194a7444e2b566903dde112ad8', NULL, 'doctype_czsc', '操作手册', 'czsc', '操作手册', NULL, 'N', 'Y', NULL, 'admin', TO_DATE('2018-03-02 11:44:17', 'SYYYY-MM-DD HH24:MI:SS'), 'admin', TO_DATE('2018-03-02 11:44:17', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL, NULL);

/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.21.249_1521_gjzs
 Source Server Type    : Oracle
 Source Server Version : 110200
 Source Host           : 192.168.21.249:1521
 Source Schema         : GJZSPT

 Target Server Type    : Oracle
 Target Server Version : 110200
 File Encoding         : 65001

 Date: 06/02/2018 17:45:47
*/


-- ----------------------------
-- Table structure for SYS_DOCUMENT
-- ----------------------------
DROP TABLE "GJZSPT"."SYS_DOCUMENT";
CREATE TABLE "GJZSPT"."SYS_DOCUMENT" (
  "ID" VARCHAR2(64 BYTE) NOT NULL ,
  "TITLE" VARCHAR2(500 CHAR) DEFAULT '' NOT NULL ,
  "CONTENT" CLOB DEFAULT '' NOT NULL ,
  "TYPE" VARCHAR2(100 CHAR) ,
  "APPLY_TO" VARCHAR2(100 CHAR) ,
  "ENABLE_FLAG" VARCHAR2(1 CHAR) DEFAULT 'Y'  NOT NULL ,
  "CREATE_BY" VARCHAR2(64 CHAR) ,
  "CREATE_TIME" DATE DEFAULT sysdate  ,
  "UPDATE_BY" VARCHAR2(64 CHAR) ,
  "UPDATE_TIME" DATE ,
  "DEL_FLAG" VARCHAR2(1 CHAR) DEFAULT 'N'  NOT NULL
)
TABLESPACE "USERS"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536
  NEXT 1048576
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "GJZSPT"."SYS_DOCUMENT"."ID" IS 'ID';
COMMENT ON COLUMN "GJZSPT"."SYS_DOCUMENT"."TITLE" IS '标题';
COMMENT ON COLUMN "GJZSPT"."SYS_DOCUMENT"."CONTENT" IS '内容（存放前端富文本框生成的html）';
COMMENT ON COLUMN "GJZSPT"."SYS_DOCUMENT"."TYPE" IS '类别';
COMMENT ON COLUMN "GJZSPT"."SYS_DOCUMENT"."APPLY_TO" IS '适用系统';
COMMENT ON COLUMN "GJZSPT"."SYS_DOCUMENT"."ENABLE_FLAG" IS '启用标记（Y：启用，N：禁用）';
COMMENT ON COLUMN "GJZSPT"."SYS_DOCUMENT"."CREATE_BY" IS '创建人';
COMMENT ON COLUMN "GJZSPT"."SYS_DOCUMENT"."CREATE_TIME" IS '创建时间';
COMMENT ON COLUMN "GJZSPT"."SYS_DOCUMENT"."UPDATE_BY" IS '更新人';
COMMENT ON COLUMN "GJZSPT"."SYS_DOCUMENT"."UPDATE_TIME" IS '更新时间';
COMMENT ON COLUMN "GJZSPT"."SYS_DOCUMENT"."DEL_FLAG" IS '删除标记（Y：已删除，N：未删除）';
COMMENT ON TABLE "GJZSPT"."SYS_DOCUMENT" IS '文档表';

-- ----------------------------
-- Primary Key structure for table SYS_DOCUMENT
-- ----------------------------
ALTER TABLE "GJZSPT"."SYS_DOCUMENT" ADD CONSTRAINT "SYS_C0021870" PRIMARY KEY ("ID");

-- ----------------------------
-- Checks structure for table SYS_DOCUMENT
-- ----------------------------
ALTER TABLE "GJZSPT"."SYS_DOCUMENT" ADD CONSTRAINT "SYS_C0021863" CHECK ("ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "GJZSPT"."SYS_DOCUMENT" ADD CONSTRAINT "SYS_C0021864" CHECK ("TITLE" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "GJZSPT"."SYS_DOCUMENT" ADD CONSTRAINT "SYS_C0021865" CHECK ("CONTENT" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "GJZSPT"."SYS_DOCUMENT" ADD CONSTRAINT "SYS_C0021866" CHECK ("ENABLE_FLAG" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "GJZSPT"."SYS_DOCUMENT" ADD CONSTRAINT "SYS_C0021869" CHECK ("DEL_FLAG" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- 增加东莞市下一级的行政区划数据 2018/4/12
INSERT INTO SYS_REGION ( ID, PARENT_ID, REGION_NAME, REGION_CODE, DEL_FLAG )
VALUES
	( '441900003000', '441900', '东城街道办事处', '441900003000', 'N' );
INSERT INTO SYS_REGION ( ID, PARENT_ID, REGION_NAME, REGION_CODE, DEL_FLAG )
VALUES
	( '441900004000', '441900', '南城街道办事处', '441900004000', 'N' );
INSERT INTO SYS_REGION ( ID, PARENT_ID, REGION_NAME, REGION_CODE, DEL_FLAG )
VALUES
	( '441900005000', '441900', '万江街道办事处', '441900005000', 'N' );
INSERT INTO SYS_REGION ( ID, PARENT_ID, REGION_NAME, REGION_CODE, DEL_FLAG )
VALUES
	( '441900006000', '441900', '莞城街道办事处', '441900006000', 'N' );

INSERT INTO SYS_REGION ( ID, PARENT_ID, REGION_NAME, REGION_CODE, DEL_FLAG )
VALUES
  ( '441900101000', '441900', '石碣镇', '441900101000', 'N' );
INSERT INTO SYS_REGION ( ID, PARENT_ID, REGION_NAME, REGION_CODE, DEL_FLAG )
VALUES
  ( '441900102000', '441900', '石龙镇', '441900102000', 'N' );
INSERT INTO SYS_REGION ( ID, PARENT_ID, REGION_NAME, REGION_CODE, DEL_FLAG )
VALUES
  ( '441900103000', '441900', '茶山镇', '441900103000', 'N' );
INSERT INTO SYS_REGION ( ID, PARENT_ID, REGION_NAME, REGION_CODE, DEL_FLAG )
VALUES
  ( '441900104000', '441900', '石排镇', '441900104000', 'N' );
INSERT INTO SYS_REGION ( ID, PARENT_ID, REGION_NAME, REGION_CODE, DEL_FLAG )
VALUES
  ( '441900105000', '441900', '企石镇', '441900105000', 'N' );
INSERT INTO SYS_REGION ( ID, PARENT_ID, REGION_NAME, REGION_CODE, DEL_FLAG )
VALUES
  ( '441900106000', '441900', '横沥镇', '441900106000', 'N' );
INSERT INTO SYS_REGION ( ID, PARENT_ID, REGION_NAME, REGION_CODE, DEL_FLAG )
VALUES
  ( '441900107000', '441900', '桥头镇', '441900107000', 'N' );
INSERT INTO SYS_REGION ( ID, PARENT_ID, REGION_NAME, REGION_CODE, DEL_FLAG )
VALUES
  ( '441900108000', '441900', '谢岗镇', '441900108000', 'N' );
INSERT INTO SYS_REGION ( ID, PARENT_ID, REGION_NAME, REGION_CODE, DEL_FLAG )
VALUES
  ( '441900109000', '441900', '东坑镇', '441900109000', 'N' );
INSERT INTO SYS_REGION ( ID, PARENT_ID, REGION_NAME, REGION_CODE, DEL_FLAG )
VALUES
  ( '441900110000', '441900', '常平镇', '441900110000', 'N' );
INSERT INTO SYS_REGION ( ID, PARENT_ID, REGION_NAME, REGION_CODE, DEL_FLAG )
VALUES
  ( '441900111000', '441900', '寮步镇', '441900111000', 'N' );
INSERT INTO SYS_REGION ( ID, PARENT_ID, REGION_NAME, REGION_CODE, DEL_FLAG )
VALUES
  ( '441900112000', '441900', '樟木头镇', '441900112000', 'N' );
INSERT INTO SYS_REGION ( ID, PARENT_ID, REGION_NAME, REGION_CODE, DEL_FLAG )
VALUES
  ( '441900113000', '441900', '大朗镇', '441900113000', 'N' );
INSERT INTO SYS_REGION ( ID, PARENT_ID, REGION_NAME, REGION_CODE, DEL_FLAG )
VALUES
  ( '441900114000', '441900', '黄江镇', '441900114000', 'N' );
INSERT INTO SYS_REGION ( ID, PARENT_ID, REGION_NAME, REGION_CODE, DEL_FLAG )
VALUES
  ( '441900115000', '441900', '清溪镇', '441900115000', 'N' );
INSERT INTO SYS_REGION ( ID, PARENT_ID, REGION_NAME, REGION_CODE, DEL_FLAG )
VALUES
  ( '441900116000', '441900', '塘厦镇', '441900116000', 'N' );
INSERT INTO SYS_REGION ( ID, PARENT_ID, REGION_NAME, REGION_CODE, DEL_FLAG )
VALUES
  ( '441900117000', '441900', '凤岗镇', '441900117000', 'N' );
INSERT INTO SYS_REGION ( ID, PARENT_ID, REGION_NAME, REGION_CODE, DEL_FLAG )
VALUES
  ( '441900118000', '441900', '大岭山镇', '441900118000', 'N' );
INSERT INTO SYS_REGION ( ID, PARENT_ID, REGION_NAME, REGION_CODE, DEL_FLAG )
VALUES
  ( '441900119000', '441900', '长安镇', '441900119000', 'N' );
INSERT INTO SYS_REGION ( ID, PARENT_ID, REGION_NAME, REGION_CODE, DEL_FLAG )
VALUES
  ( '441900121000', '441900', '虎门镇', '441900121000', 'N' );
INSERT INTO SYS_REGION ( ID, PARENT_ID, REGION_NAME, REGION_CODE, DEL_FLAG )
VALUES
  ( '441900122000', '441900', '厚街镇', '441900122000', 'N' );
INSERT INTO SYS_REGION ( ID, PARENT_ID, REGION_NAME, REGION_CODE, DEL_FLAG )
VALUES
  ( '441900123000', '441900', '沙田镇', '441900123000', 'N' );
INSERT INTO SYS_REGION ( ID, PARENT_ID, REGION_NAME, REGION_CODE, DEL_FLAG )
VALUES
  ( '441900124000', '441900', '道滘镇', '441900124000', 'N' );
INSERT INTO SYS_REGION ( ID, PARENT_ID, REGION_NAME, REGION_CODE, DEL_FLAG )
VALUES
  ( '441900125000', '441900', '洪梅镇', '441900125000', 'N' );
INSERT INTO SYS_REGION ( ID, PARENT_ID, REGION_NAME, REGION_CODE, DEL_FLAG )
VALUES
  ( '441900126000', '441900', '麻涌镇', '441900126000', 'N' );
INSERT INTO SYS_REGION ( ID, PARENT_ID, REGION_NAME, REGION_CODE, DEL_FLAG )
VALUES
  ( '441900127000', '441900', '望牛墩镇', '441900127000', 'N' );
INSERT INTO SYS_REGION ( ID, PARENT_ID, REGION_NAME, REGION_CODE, DEL_FLAG )
VALUES
  ( '441900128000', '441900', '中堂镇', '441900128000', 'N' );
INSERT INTO SYS_REGION ( ID, PARENT_ID, REGION_NAME, REGION_CODE, DEL_FLAG )
VALUES
  ( '441900129000', '441900', '高埗镇', '441900129000', 'N' );
INSERT INTO SYS_REGION ( ID, PARENT_ID, REGION_NAME, REGION_CODE, DEL_FLAG )
VALUES
  ( '441900401000', '441900', '松山湖管委会', '441900401000', 'N' );
INSERT INTO SYS_REGION ( ID, PARENT_ID, REGION_NAME, REGION_CODE, DEL_FLAG )
VALUES
  ( '441900402000', '441900', '虎门港管委会', '441900402000', 'N' );
INSERT INTO SYS_REGION ( ID, PARENT_ID, REGION_NAME, REGION_CODE, DEL_FLAG )
VALUES
  ( '441900403000', '441900', '东莞生态园', '441900403000', 'N' );