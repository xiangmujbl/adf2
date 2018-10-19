package com.jnj.adf.dataservice.adfcoreignite;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

//package com.jnj.adf.grid.support.context;

//import com.jnj.adf.grid.support.system.ADFConfigHelper;
import com.jnj.adf.dataservice.adfcoreignite.ADFConfigHelper.ITEMS;
import com.jnj.adf.dataservice.adfcoreignite.ADFConfigHelper.TYPES;

public class ADFRuntime {
    private TYPES nodeType;
    private boolean jmxManagerNode;
    private String nodeName;
    private String workPath;
    private String homePath;
    private String masterGridName;
    private static final ADFRuntime INST = new ADFRuntime();

    private ADFRuntime() {
        this.nodeType = TYPES.CLIENT;
    }

    public static final ADFRuntime getRuntime() {
        return INST;
    }

    public boolean isServerSide() {
        return this.nodeType == TYPES.DATASTORE || this.nodeType == TYPES.LOCATOR;
    }

    public boolean isMasterLocator() {
        return this.isLocatorSide() && ADFConfigHelper.getBoolean(ITEMS.AS_MASTER_GRID);
    }

    public boolean isMasterDataStore() {
        return this.isDataStoreSide() && ADFConfigHelper.getBoolean(ITEMS.AS_MASTER_GRID);
    }

    public boolean isMaster() {
        return this.isMasterDataStore() || this.isMasterLocator();
    }

    public boolean isDataStoreSide() {
        return this.nodeType == TYPES.DATASTORE;
    }

    public boolean isLocatorSide() {
        return this.nodeType == TYPES.LOCATOR;
    }

    public TYPES getNodeType() {
        return this.nodeType;
    }

    public void setNodeType(TYPES nodeType) {
        this.nodeType = nodeType;
    }

    public boolean isJmxManagerNode() {
        return this.jmxManagerNode;
    }

    public void setJmxManagerNode(boolean jmxManagerNode) {
        this.jmxManagerNode = jmxManagerNode;
    }

    public String getNodeName() {
        return this.nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getWorkPath() {
        return this.workPath;
    }

    public void setWorkPath(String workPath) {
        this.workPath = workPath;
    }

    public String getHomePath() {
        return this.homePath;
    }

    public void setHomePath(String homePath) {
        this.homePath = homePath;
    }

    public String currentGridId() {
        return ADFConfigHelper.getConfig(ITEMS.SYSTEM_GRIDNAME);
    }

    public String getMasterGridName() {
        return this.masterGridName;
    }

    public boolean isClientSide() {
        return this.nodeType == TYPES.CLIENT;
    }

    public void setMasterGridName(String masterGridName) {
        this.masterGridName = masterGridName;
    }
}
