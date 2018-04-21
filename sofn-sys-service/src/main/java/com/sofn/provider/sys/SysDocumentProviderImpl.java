package com.sofn.provider.sys;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.sys.SysDocumentExpandMapper;
import com.sofn.model.generator.SysDocument;
import com.sofn.model.sys.SysDocumentQueryParam;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 文档数据接口实现类
 */
@DubboService(interfaceClass = SysDocumentProvider.class)
public class SysDocumentProviderImpl implements SysDocumentProvider {
    @Autowired
    private SysDocumentExpandMapper sysDocumentExpandMapper;

    /**
     * 根据条件分页查询文档列表
     *
     * @param param 查询参数
     * @return 文档列表
     */
    @Override
    public PageInfo<SysDocument> getDocumentList(SysDocumentQueryParam param) {
        PageHelper.startPage(param.getPageNum(), param.getPageSize());
        List<SysDocument> list = sysDocumentExpandMapper.getDocumentList(param);
        PageInfo<SysDocument> pageInfo = new PageInfo<>(list);

        return pageInfo;
    }

    /**
     * 根据ID获取文档信息
     *
     * @param id 文档ID
     * @return 文档信息
     */
    @Override
    public SysDocument getDocument(String id) {
        return sysDocumentExpandMapper.getDocument(id);
    }

    /**
     * 新增文档信息
     *
     * @param document 文档信息
     * @return 新增记录数
     */
    @Override
    public int addDocument(SysDocument document) {
        return sysDocumentExpandMapper.addDocument(document);
    }

    /**
     * 更新文档信息
     *
     * @param document 文档信息
     * @return 更新记录数
     */
    @Override
    public int updateDocument(SysDocument document) {
        return sysDocumentExpandMapper.updateDocument(document);
    }
}
