package com.sofn.ws;

import javax.jws.WebService;

/**
 * Created by weiqiang on 2016/11/15.
 */
@WebService(name = "hello")
public interface Hello {
     void hello(String username);
}
