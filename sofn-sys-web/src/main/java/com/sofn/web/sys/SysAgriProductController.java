package com.sofn.web.sys;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.authorization.annotation.Authorization;
import com.sofn.core.base.BaseController;
import com.sofn.core.persistence.Page;
import com.sofn.core.util.StringUtils;
import com.sofn.model.generator.SysArgiProduct;
import com.sofn.service.sys.SysArgiProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Table;
import org.apache.poi.hwpf.usermodel.TableIterator;
import org.apache.poi.hwpf.usermodel.TableRow;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/11.
 */
@RestController
@Api(value = "农产品管理",description = "农产品管理")
@RequestMapping("/agriProduct")
public class SysAgriProductController extends BaseController {
    @Autowired
    private SysArgiProductService sysArgiProductService;

    @ApiOperation(value = "根据名字查询农产品信息")
    @PostMapping("/getByNameOrAlias")
    @SystemControllerLog(description = "根据名字查询农产品信息",operationType="查询")
    public Object getByNameOrAlias(@ApiParam(required = true, value = "行业代码") @RequestParam(value = "codeType", required = false) String codeType,
                                   @ApiParam(required = true, value = "名字") @RequestBody String keyword){

        List<Map<String, Object>> list = sysArgiProductService.getByNameOrAlias(codeType,keyword);

        return setSuccessModelMap(new ModelMap(), list);
    }

    @ApiOperation(value = "根据名字查询农产品信息(分页)")
    @PostMapping("/queryByNameOrAlias")
//    @SystemControllerLog(description = "根据名字查询农产品信息(分页)",operationType="查询")
    public Object queryByNameOrAlias(String productCode, String keyword ,int start, int length){

        PageInfo<Map<String, Object>> pageInfo = sysArgiProductService.queryByNameOrAlias(productCode,keyword,start,length);
        return setSuccessModelMap(new ModelMap(), pageInfo);
    }

    public List<Map<String, Object>> queryByProductCode(String productCode){

        return sysArgiProductService.queryByProductCode(productCode);
    }

    @ApiOperation(value = "根据产品代码查询屠宰后产品")
    @PostMapping("/queryProductForSlaughter")
    @SystemControllerLog(description = "根据产品代码查询屠宰后产品(分页)",operationType="查询")
    public Object queryProductForSlaughter(String productCode,String keyword ,int pageNum,int length){

        PageInfo<Map<String, Object>> pageInfo = sysArgiProductService.queryProductForSlaughter(productCode,keyword,pageNum,length);
        return setSuccessModelMap(new ModelMap(), pageInfo);
    }

    @ApiOperation(value = "畜牧业屠宰前产品查询(keyWord)")
    @PostMapping("/queryProductBeforeSlaughter")
    @SystemControllerLog(description = "畜牧业屠宰前产品查询(keyWord)",operationType="查询")
    public Object queryProductBeforeSlaughter(String keyword,long start, long length) {
        Page page=new Page();
        page.setLength(length);
        page.setStart(start/length);
        PageInfo<Map<String, Object>> pageInfo = sysArgiProductService.queryProductBeforeSlaughter(keyword,page);
        return setSuccessModelMap(new ModelMap(), pageInfo);
    }

    @Authorization
    @ApiOperation(value = "农产品分类查询(keyWord)")
    @PostMapping("/query")
    @SystemControllerLog(description = "农产品分类查询(keyWord)",operationType="查询")
    public Object query(SysArgiProduct sysArgiProduct,  String keyword,long start, long length) {
        Page page=new Page();
        page.setLength(length);
        page.setStart(start/length);
        PageInfo<Map<String, Object>> pageInfo = sysArgiProductService.queryByCondition(sysArgiProduct,keyword,page);
        return setSuccessModelMap(new ModelMap(), pageInfo);
    }


    @ApiOperation(value = "农产品分类新增")
    @PostMapping("/add")
    @SystemControllerLog(description = "农产品分类新增",operationType="添加")
    public Object add(@RequestBody SysArgiProduct sysArgiProduct) {
        if(null!=sysArgiProduct.getId() && !"".equals(sysArgiProduct)){
            return this.update(sysArgiProduct);
        }
        sysArgiProductService.add(sysArgiProduct);
        return setSuccessModelMap(new ModelMap());
    }

    /**
     * Logout object.
     *
     * @param sysArgiProduct the SysArgiProduct Class
     * @return the object
     */
    @ApiOperation(value = "农产品分类修改")
    @PostMapping("/update")
    @SystemControllerLog(description = "农产品分类修改",operationType="修改")
    public Object update(SysArgiProduct sysArgiProduct) {
        if(StringUtils.isEmpty(sysArgiProduct.getId() )){
            return setSuccessModelMap(new ModelMap(),"更新ID不能为空");
        }
        SysArgiProduct s=sysArgiProductService.queryById(sysArgiProduct.getId());
        if(StringUtils.isNotEmpty(sysArgiProduct.getParentId())){
            s.setParentId(sysArgiProduct.getParentId());
        }
        if(StringUtils.isNotEmpty(sysArgiProduct.getAlias())){
            s.setAlias(sysArgiProduct.getAlias());
        }
        if(StringUtils.isNotEmpty(sysArgiProduct.getDelFlag())){
            s.setDelFlag(sysArgiProduct.getDelFlag());
        }
//        if(StringUtils.isNotEmpty(sysArgiProduct.getDescription())){
            s.setDescription(sysArgiProduct.getDescription());
//        }
        if(StringUtils.isNotEmpty(sysArgiProduct.getEnname())){
            s.setEnname(sysArgiProduct.getEnname());
        }
//        if(StringUtils.isNotEmpty(sysArgiProduct.getGbCode())){
            s.setGbCode(sysArgiProduct.getGbCode());
//        }
        if(StringUtils.isNotEmpty(sysArgiProduct.getName())){
            s.setName(sysArgiProduct.getName());
        }
        if(StringUtils.isNotEmpty(sysArgiProduct.getProductCode())){
            s.setProductCode(sysArgiProduct.getProductCode());
        }
        if(StringUtils.isNotEmpty(sysArgiProduct.getRemark())){
            s.setRemark(sysArgiProduct.getRemark());
        }
        if(StringUtils.isNotEmpty(sysArgiProduct.getTypeId())){
            s.setTypeId(sysArgiProduct.getTypeId());
        }
        if(StringUtils.isNotEmpty(sysArgiProduct.getUseCode())){
            s.setUseCode(sysArgiProduct.getUseCode());
        }
        sysArgiProductService.update(s);
        return setSuccessModelMap(new ModelMap());
    }

    @ApiOperation(value = "农产品分类删除")
    @PostMapping("/delete")
    @SystemControllerLog(description = "农产品分类删除",operationType="删除")
    public Object delete(
            @ApiParam(required = false, value = "ids") String jsonIds
    ) {
        JSONArray ids = JSONArray.parseArray(jsonIds);
        sysArgiProductService.deleteArgiProduct(ids.toArray());
        return setSuccessModelMap(new ModelMap());
    }


    @PostMapping("/import")
    public Object importWord(HttpServletRequest request){
        List<SysArgiProduct> list=new ArrayList<SysArgiProduct>();

        String typeId=request.getParameter("typeId");
        if(typeId==null || typeId.equals("")) {
            logger.error("importWord:typeId is null.");
            return setFailModelMap(new ModelMap(),"所属行业类型不能为空！");
        };
        DiskFileItemFactory factory=new DiskFileItemFactory();
        factory.setSizeThreshold(4096);

        ServletFileUpload upload=new ServletFileUpload(factory);
        upload.setFileSizeMax(4191304);
        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            MultipartFile file = multipartRequest.getFile("file");
            String fileName = file.getOriginalFilename();
            InputStream input = file.getInputStream();
            testWord(input,fileName,typeId);
        }catch (Exception e){
            return setFailModelMap(new ModelMap(),e.getMessage());
        }
        return setSuccessModelMap(new ModelMap());
    }

    public void testWord(InputStream in, String filename, String typeId) throws  Exception{
        int cellSize = 6;
        if("02".equals(typeId)){
            cellSize = 7;
        }

        List<SysArgiProduct> list=new ArrayList<SysArgiProduct>();

            //如果是office2007  docx格式
            if(filename.toLowerCase().endsWith("docx")){
                //word 2007 图片不会被读取， 表格中的数据会被放在字符串的最后
                XWPFDocument xwpf = new XWPFDocument(in);//得到word文档的信息
                Iterator<XWPFTable> it = xwpf.getTablesIterator();//得到word中的表格
                while(it.hasNext()){
                    XWPFTable table = it.next();
                    List<XWPFTableRow> rows=table.getRows();
                    //读取每一行数据
                    for (int i = 0; i < rows.size(); i++) {
                        XWPFTableRow  row = rows.get(i);
                        //读取每一列数据
                        List<XWPFTableCell> cells = row.getTableCells();
                        int num=cells.size();
                        String first=cells.get(0).getText();
                        if(num!=cellSize){
                            int currentRowNum = i +1;
                            throw new Exception("表格第"+currentRowNum+"列数据列数不正确！");
                        }
                        if(first !=null && first.indexOf("产品代码")>-1){
                            continue;
                        }
                        String parentId=cells.get(0).getText();
                        SysArgiProduct sysArgiProduct;
                        //如果是畜牧业会多一列屠宰标识ID
                        if ("02".equals(typeId)){
                            sysArgiProduct = fillSysArgiProductField(parentId,typeId,getCellTextByIndex(cells,0),
                                    getCellTextByIndex(cells,1),getCellTextByIndex(cells,2),
                                    getCellTextByIndex(cells,3), getCellTextByIndex(cells,4),
                                    getCellTextByIndex(cells,5), getCellTextByIndex(cells,6));
                        }else{
                            sysArgiProduct = fillSysArgiProductField(parentId,typeId,getCellTextByIndex(cells,0),
                                    getCellTextByIndex(cells,1),"",getCellTextByIndex(cells,2),
                                    getCellTextByIndex(cells,3), getCellTextByIndex(cells,4),
                                    getCellTextByIndex(cells,5));
                        }

                        List<Map<String,Object>> parentSysArgiProduct = queryByProductCode(sysArgiProduct.getParentId());
                        if (parentSysArgiProduct.size()>0)
                        {
                            SysArgiProduct maptemp = (SysArgiProduct)parentSysArgiProduct.get(0);
                            if(null != maptemp){
                                sysArgiProduct.setParentName(maptemp.getName());
                            }
                        }

                        add(sysArgiProduct);
                    }

                }
            }else if (filename.toLowerCase().endsWith("doc")){
                //如果是office2003  doc格式
                POIFSFileSystem pfs = new POIFSFileSystem(in);
                HWPFDocument hwpf = new HWPFDocument(pfs);
                Range range = hwpf.getRange();//得到文档的读取范围
                TableIterator it = new TableIterator(range);
                //迭代文档中的表格
                while (it.hasNext()) {
                    Table tb = (Table) it.next();
                    //迭代行，默认从0开始
                    for (int i = 0; i < tb.numRows(); i++) {
                        TableRow tr = tb.getRow(i);

                        //去掉表头数据
                        int num=tr.numCells();
                        String first=tr.getCell(0).getParagraph(0).text();
                        if(num!=cellSize){
                            int currentRowNum = i +1;
                            throw new Exception("表格第"+currentRowNum+"列数据列数不正确！");
                        }
                        if(first !=null && first.indexOf("产品代码")>-1){
                            continue;
                        }

                        //迭代列，默认从0开始
                        String parentId=subStringTd( tr.getCell(0).getParagraph(0).text());
                        SysArgiProduct sysArgiProduct;
                        //如果是畜牧业会多一列屠宰标识ID
                        if ("02".equals(typeId)){
                            sysArgiProduct = fillSysArgiProductField(parentId,typeId,subStringTd(getParagraphTextByIndex(tr,0)),
                                    getParagraphTextByIndex(tr,1),subStringTd(getParagraphTextByIndex(tr,2)),
                                    getParagraphTextByIndex(tr,3), getParagraphTextByIndex(tr,4),
                                    getParagraphTextByIndex(tr,5), getParagraphTextByIndex(tr,6));
                        }else{
                            sysArgiProduct = fillSysArgiProductField(parentId,typeId,subStringTd(getParagraphTextByIndex(tr,0)),
                                    getParagraphTextByIndex(tr,1),"",getParagraphTextByIndex(tr,2),
                                    getParagraphTextByIndex(tr,3), getParagraphTextByIndex(tr,4),
                                    getParagraphTextByIndex(tr,5));
                        }

                        add(sysArgiProduct);
                    }
                }
            }


    }
    private String getCellTextByIndex(List<XWPFTableCell> cells,int index){
        return cells.get(index).getText();
    }

    private String getParagraphTextByIndex(TableRow tr,int index){
        return tr.getCell(index).getParagraph(0).text();
    }

    private SysArgiProduct fillSysArgiProductField(String parentId,String typeId,String productCode,
                            String name,String slaughterStatus,String alias,String enName,String GbCode,String description){
        if (parentId.length()>=2) {
            parentId = parentId.substring(0, parentId.length() - 2);
        }
        SysArgiProduct sysArgiProduct=new SysArgiProduct();
        sysArgiProduct.setParentId(parentId.replace(" ","").trim());
        sysArgiProduct.setTypeId(typeId.trim());
        sysArgiProduct.setProductCode(productCode.replace(" ","").trim() );
        sysArgiProduct.setName(name.trim());
        sysArgiProduct.setSlaughterStatus(slaughterStatus);
        sysArgiProduct.setAlias(alias.trim());
        sysArgiProduct.setEnname(enName.trim());
        sysArgiProduct.setGbCode(GbCode.trim());
        sysArgiProduct.setDescription(description.trim());
        List<Map<String,Object>> parentSysArgiProduct = queryByProductCode(sysArgiProduct.getParentId());
        if (parentSysArgiProduct.size()>0)
        {
            SysArgiProduct maptemp = (SysArgiProduct)parentSysArgiProduct.get(0);
            if(null != maptemp){
                sysArgiProduct.setParentName(maptemp.getName());
            }
        }
        return sysArgiProduct;
    }

    private String subStringTd(String s){
        String str=s.substring(0, s.length()-1);
        return str;
    }

}
