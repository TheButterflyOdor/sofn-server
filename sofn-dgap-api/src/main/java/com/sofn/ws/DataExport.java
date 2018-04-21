package com.sofn.ws;

import com.sofn.model.dgap.DataExportResult;
import com.sofn.model.dgap.ErrorSupport;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Created by helong on 16-11-22.
 */
@WebService(name="dataexport")
public interface DataExport extends HeartService {

    DataExportResult getData(@WebParam(name="accessToken")String accessToken,
                             @WebParam(name="start")int start, @WebParam(name="end")int end,
                             @WebParam(name="fields")String[] fields,
                             @WebParam(name="condition")String condition);
}
