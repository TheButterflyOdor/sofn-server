package com.sofn.service.sys;

import com.github.pagehelper.PageInfo;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.core.util.UUIDUtil;
import com.sofn.model.generator.SysDocument;
import com.sofn.model.sys.PagingAdapterDTO;
import com.sofn.model.sys.SysDocumentQueryParam;
import com.sofn.provider.sys.SysDocumentProvider;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Administrator on 2018/2/9/009.
 */
@Service
public class SysDocumentService {
    @DubboReference
    private SysDocumentProvider sysDocumentProvider;

    /**
     * 根据条件分页查询文档列表
     *
     * @param param 查询参数
     * @param start 前端datatable插件数据起始位置
     * @param length 前端datatable插件的pageSize
     * @return 文档列表
     */
    public PageInfo<SysDocument> getDocumentList(SysDocumentQueryParam param, int start, int length) {
        PagingAdapterDTO pagingAdapterDTO = new PagingAdapterDTO();
        pagingAdapterDTO.setStart(start);
        pagingAdapterDTO.setLength(length);
        param.setPageNum(pagingAdapterDTO.getPageNum());
        param.setPageSize(pagingAdapterDTO.getPageSize());

        return sysDocumentProvider.getDocumentList(param);
    }

    /**
     * 根据ID获取文档信息
     *
     * @param id 文档ID
     * @return 文档信息
     */
    public SysDocument getDocument(String id) {
        return sysDocumentProvider.getDocument(id);
    }

    /**
     * 新增文档信息
     *
     * @param document 文档信息
     * @return 新增记录数
     */
    public int addDocument(SysDocument document) {
        document.setId(UUIDUtil.getUUID());
        document.setCreateTime(new Date());
        return sysDocumentProvider.addDocument(document);
    }

    /**
     * 更新文档信息
     *
     * @param document 文档信息
     * @return 更新记录数
     */
    public int updateDocument(SysDocument document) {
        document.setUpdateTime(new Date());
        return sysDocumentProvider.updateDocument(document);
    }
}
