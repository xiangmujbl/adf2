//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jnj.adf.dataservice.adfcoreignite;

import com.gemstone.gemfire.cache.client.Pool;
//import com.jnj.adf.config.annotations.Path;
//import com.jnj.adf.config.annotations.RemoteMethod;
import com.jnj.adf.config.annotations.RemoteServiceApi;
import com.jnj.adf.config.annotations.RemoteMethod.InvokeTypes;
import java.util.List;

@RemoteServiceApi("adf.system.listRegion")
public interface IListRegionService {
    String ID = "adf.system.listRegion";

    @RemoteMethod(
            type = InvokeTypes.ON_SERVER
    )
    List<?> listNormalRegions(@Path(true) Pool var1);

    @RemoteMethod(
            type = InvokeTypes.ON_SERVER
    )
    List<?> listHostRegions(@Path(true) Pool var1);
}
