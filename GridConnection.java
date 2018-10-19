//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jnj.adf.dataservice.adfcoreignite;

//import com.gemstone.gemfire.cache.client.Pool;
import com.jnj.adf.dataservice.domain.grid.GridInfoData;
//import com.jnj.adf.grid.connect.attri.ADFPoolAttributes;
//import com.jnj.adf.grid.connect.domain.GridInfo;

public interface GridConnection {
    boolean connect();

    boolean isConnected();

    void close();

    String getPoolName();

//    Pool getPool();

    GridInfoData fetchGridInfo();

    void setGridInfo(GridInfoData var1);

    void setPoolAttributes(ADFPoolAttributes var1);
}
