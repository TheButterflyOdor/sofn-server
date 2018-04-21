package com.sofn.dao.sys;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseMapper;
import com.sofn.model.generator.SysArgiProduct;
import com.sofn.model.generator.SysPesticideResidueModel;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/11/21/021.
 */
@MyBatisDao
public interface SysPesticideResidueExpandMapper extends BaseMapper<SysPesticideResidueModel> {
    List<SysPesticideResidueModel> getPesticideResidues(@Param("standardCode") String standardCode, @Param("itemName") String itemName, @Param("testObjectName") String testObjectName);
    SysPesticideResidueModel getPesticideResidue(@Param("id") String id);
    int addPesticideResidue(SysPesticideResidueModel model);
    int updatePesticideResidue(SysPesticideResidueModel model);
    int deletePesticideResidues(@Param("idList") List<String> idList, @Param("account") String account, @Param("operateTime") Date operateTime);
    int isExistPesticideResidue(@Param("testItemId") String testItemId, @Param("testObjectId") String testObjectId);
    List<SysArgiProduct> getArgiProducts(@Param("typeId") String typeId, @Param("nameKeyword") String nameKeyword);
}
