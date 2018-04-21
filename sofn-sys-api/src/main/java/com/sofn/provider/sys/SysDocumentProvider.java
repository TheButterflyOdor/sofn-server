package com.sofn.provider.sys;

import com.github.pagehelper.PageInfo;
import com.sofn.model.generator.SysDocument;
import com.sofn.model.sys.SysDocumentQueryParam;

/**
 * 文档数据接口
 * <p>
 *     可以保存前端富文本框生成的内容
 * </p>
 */
public interface SysDocumentProvider {
    /**
     * 根据条件分页查询文档列表
     *
     * @param param 查询参数
     * @return 文档列表
     */
    PageInfo<SysDocument> getDocumentList(SysDocumentQueryParam param);

    /**
     * 根据ID获取文档信息
     *
     * @param id 文档ID
     * @return 文档信息
     */
    SysDocument getDocument(String id);

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
