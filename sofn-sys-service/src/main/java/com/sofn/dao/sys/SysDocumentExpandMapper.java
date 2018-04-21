package com.sofn.dao.sys;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.model.generator.SysDocument;
import com.sofn.model.sys.SysDocumentQueryParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 文档信息映射接口
 */
@MyBatisDao
public interface SysDocumentExpandMapper {
    /**
     * 根据条件分页查询文档列表
     *
     * @param param 查询参数
     * @return 文档列表
     */
    List<SysDocument> getDocumentList(SysDocumentQueryParam param);

    /**
     * 根据ID获取文档信息
     *
     * @param id 文档ID
     * @return 文档信息
     */
    SysDocument getDocument(@Param("id") String id);

    /**
     * 新增文档信息
     *
     * @param document 文档信息
     * @return 新增记录数
     */
    int addDocument(SysDocument document);

    /**
     * 更新文档信息
     *
     * @param document 文档信息
     * @return 更新记录数
     */
    int updateDocument(SysDocument document);
}
