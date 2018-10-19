package com.jnj.adf.dataservice.adfcoreignite;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

//package com.jnj.adf.grid.connect.attri;

//import com.jnj.adf.grid.support.system.ADFConfigHelper;
import com.jnj.adf.dataservice.adfcoreignite.ADFConfigHelper.ITEMS;

public class ADFPoolAttributes {
    private int freeConnectionTimeout = 10000;
    private int readTimeout = 10000;
    private int minConnections = 1;
    private int maxConnections = -1;
    private int retryAttempts = -1;
    private boolean subscriptionEnabled = false;
    private boolean prSingleHopEnabled = true;
    private boolean gateway = false;
    private boolean multiuserAuthentication = false;
    private boolean pdxReadSerialized = false;
    private boolean gateWay = false;

    public ADFPoolAttributes() {
    }

    public static ADFPoolAttributes getDefaultAttributes() {
        ADFPoolAttributes attributes = new ADFPoolAttributes();
        boolean isMultipleUser = ADFConfigHelper.getBoolean(ITEMS.CLINET_MULTIPLE_USER);
        attributes.setPrSingleHopEnabled(ADFConfigHelper.getBoolean(ITEMS.CLIENT_POOL_SINGLEHOP));
        attributes.setMultiuserAuthentication(isMultipleUser);
        attributes.setReadTimeout(ADFConfigHelper.getInteger(ITEMS.CLIENT_POOL_TIMEOUT));
        attributes.setRetryAttempts(ADFConfigHelper.getInteger(ITEMS.CLIENT_POOL_RETRY));
        attributes.setRetryAttempts(ADFConfigHelper.getInteger(ITEMS.CLIENT_POOL_RETRY));
        attributes.setMaxConnections(ADFConfigHelper.getInteger(ITEMS.CLIENT_POOL_MAX));
        attributes.setMinConnections(ADFConfigHelper.getInteger(ITEMS.CLIENT_POOL_MIN));
        attributes.setFreeConnectionTimeout(ADFConfigHelper.getInteger(ITEMS.CLIENT_FREE_TIMEOUT));
        attributes.setSubscriptionEnabled(true);
        attributes.setPdxReadSerialized(false);
        return attributes;
    }

    public static ADFPoolAttributes getDefaultMasterAttributes() {
        ADFPoolAttributes attributes = new ADFPoolAttributes();
        boolean isMultipleUser = ADFConfigHelper.getBoolean(ITEMS.MASTER_CLINET_MULTIPLE_USER);
        attributes.setMultiuserAuthentication(isMultipleUser);
        attributes.setReadTimeout(ADFConfigHelper.getInteger(ITEMS.MASTER_CLIENT_POOL_TIMEOUT));
        attributes.setRetryAttempts(ADFConfigHelper.getInteger(ITEMS.MASTER_CLIENT_POOL_RETRY));
        attributes.setRetryAttempts(ADFConfigHelper.getInteger(ITEMS.MASTER_CLIENT_POOL_RETRY));
        attributes.setMaxConnections(ADFConfigHelper.getInteger(ITEMS.MASTER_CLIENT_POOL_MAX));
        attributes.setMinConnections(ADFConfigHelper.getInteger(ITEMS.MASTER_CLIENT_POOL_MIN));
        attributes.setFreeConnectionTimeout(ADFConfigHelper.getInteger(ITEMS.MASTER_CLIENT_FREE_TIMEOUT));
        attributes.setSubscriptionEnabled(true);
        attributes.setPdxReadSerialized(false);
        return attributes;
    }

    public int getFreeConnectionTimeout() {
        return this.freeConnectionTimeout;
    }

    public void setFreeConnectionTimeout(int freeConnectionTimeout) {
        this.freeConnectionTimeout = freeConnectionTimeout;
    }

    public int getReadTimeout() {
        return this.readTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }

    public int getMinConnections() {
        return this.minConnections;
    }

    public void setMinConnections(int minConnections) {
        this.minConnections = minConnections;
    }

    public int getMaxConnections() {
        return this.maxConnections;
    }

    public void setMaxConnections(int maxConnections) {
        this.maxConnections = maxConnections;
    }

    public int getRetryAttempts() {
        return this.retryAttempts;
    }

    public void setRetryAttempts(int retryAttempts) {
        this.retryAttempts = retryAttempts;
    }

    public boolean isSubscriptionEnabled() {
        return this.subscriptionEnabled;
    }

    public void setSubscriptionEnabled(boolean subscriptionEnabled) {
        this.subscriptionEnabled = subscriptionEnabled;
    }

    public boolean isPrSingleHopEnabled() {
        return this.prSingleHopEnabled;
    }

    public void setPrSingleHopEnabled(boolean prSingleHopEnabled) {
        this.prSingleHopEnabled = prSingleHopEnabled;
    }

    public boolean isGateway() {
        return this.gateway;
    }

    public void setGateway(boolean gateway) {
        this.gateway = gateway;
    }

    public boolean isMultiuserAuthentication() {
        return this.multiuserAuthentication;
    }

    public void setMultiuserAuthentication(boolean multiuserAuthentication) {
        this.multiuserAuthentication = multiuserAuthentication;
    }

    public boolean isPdxReadSerialized() {
        return this.pdxReadSerialized;
    }

    public void setPdxReadSerialized(boolean pdxReadSerialized) {
        this.pdxReadSerialized = pdxReadSerialized;
    }

    public boolean isGateWay() {
        return this.gateWay;
    }

    public void setGateWay(boolean gateWay) {
        this.gateWay = gateWay;
    }
}
