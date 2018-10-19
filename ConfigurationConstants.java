package com.jnj.adf.dataservice.adfcoreignite;

abstract interface ConfigurationConstants
{
  public static final String GS_ENV = "gs.env";
  public static final String GS_HOME = "gs.home";
  public static final String GS_WORK = "gs.work";
  public static final String GS_BINDIP = "gs.bind.ip";
  public static final String GS_LOCATORS = "gs.locators";
  public static final String GS_VERSION = "gs.version";
  public static final String SYSTEM_NAME = "system.name";
  public static final String SYSTEM_GRIDNAME = "system.gridname";
  public static final String DB_TYPE = "gs.date.patterns";
  public static final String DB_URL = "db.url";
  public static final String DB_PORT = "db.port";
  public static final String DB_DRIVER = "db.driver";
  public static final String DB_DEFAULT_NAME = "db.default.name";
  public static final String DB_DIALECT = "db.dialect";
  public static final String DB_USERNAME = "db.username";
  public static final String DB_PASSWORD = "db.password";
  public static final String JMX_RMI_PORT = "jmx.port";
  public static final String JMX_HOST = "jmx.host";
  public static final String JMX_PORT = "jmx.port";
  public static final String HTML_PORT = "html.port";
  public static final String JMX_MANAGER_PORT = "jmx.manager.port";
  public static final String ZOOKEEPER_SERVERS = "zookeeper.servers";
  public static final String ADF_SESSION_TIMEOUT = "adf.session.timeout";
  public static final String SOLR_ZK_HOST = "solr.zk.host";
  public static final String SOLR_COLLECTION = "solr.collection";
  public static final String SOLR_HTTP_CONNECT_TIMEOUT = "solr.http.connect.timeout";
  public static final String SOLR_HTTP_SO_TIMEOUT = "solr.http.so.timeout";
  public static final String SOLR_COMMIT_MAXTIME = "solr.commit.max.time";
  public static final String SOLR_COMMIT_BATCH_SIZE = "solr.commit.batch.size";
  public static final String NAMING_SERVER = "naming.server";
  public static final String GRID_OPLOG_META = "grid.meta.oplog.max";
  public static final String GRID_OPLOG_QUEUE = "grid.queue.oplog.max";
  public static final String GRID_STATUS_THREAD_INTERVAL = "grid.status-thread.interval";
  public static final String GRID_CACHE_REALTIME = "grid.cache.realtime";
  public static final String CLIENT_NULTIPLE_USER_ENABLED = "client.multiple.user.enabled";
  public static final String LUCENE_DIRECTORY = "indexer.lucene.directory";
  public static final String LUCENE_DISK_STORE = "indexer.lucene.disk-store";
  public static final String LUCENE_AUTO_RECOVER = "indexer.lucene.auto-recover";
  public static final String LUCENE_INTERVAL = "indexer.lucene.time-interval";
  public static final String ENV_FILE_NAME = "env.file.name";
  public static final String LOGGER_FILE_NAME = "logger.file.name";
  public static final String LOG4J_DEFAULT_CONFILE = "log4j2-server.xml";
  public static final String DEFAULT_ENV_FILE = "config/com/jnj/adf/core/env-default.properties";

  /*     */
  /*     */
  /*     */
  /*     */
  /*     */
  /*     */
  /*     */
  /*     */
  /*     */
  /*     */
  /*     */
  /*     */
  /*     */
  /*     */
  /*     */
  /*     */
  /*     */  class ADFRuntime
  /*     */ {
  /*  24 */   private TYPES nodeType = TYPES.CLIENT;
  /*     */   private boolean jmxManagerNode;
  /*     */   private String nodeName;
  /*     */   private String workPath;
  /*     */   private String homePath;
  /*     */   private String masterGridName;
  /*  30 */   private static final ADFRuntime INST = new ADFRuntime();
  /*     */
  /*     */
  /*     */
  /*     */
  /*     */
  /*     */
  /*     */
  /*     */
  /*     */   public static final ADFRuntime getRuntime()
  /*     */   {
  /*  41 */     return INST;
  /*     */   }
  /*     */
  /*     */   public boolean isServerSide()
  /*     */   {
  /*  46 */     return (this.nodeType == TYPES.DATASTORE) || (this.nodeType == TYPES.LOCATOR);
  /*     */   }
  /*     */
  /*     */   public boolean isMasterLocator()
  /*     */   {
  /*  51 */     return (isLocatorSide()) && (ADFConfigHelper.getBoolean(ITEMS.AS_MASTER_GRID));
  /*     */   }
  /*     */
  /*     */   public boolean isMasterDataStore()
  /*     */   {
  /*  56 */     return (isDataStoreSide()) && (ADFConfigHelper.getBoolean(ITEMS.AS_MASTER_GRID));
  /*     */   }
  /*     */
  /*     */   public boolean isMaster() {
  /*  60 */     return (isMasterDataStore()) || (isMasterLocator());
  /*     */   }
  /*     */
  /*     */   public boolean isDataStoreSide()
  /*     */   {
  /*  65 */     return this.nodeType == TYPES.DATASTORE;
  /*     */   }
  /*     */
  /*     */   public boolean isLocatorSide()
  /*     */   {
  /*  70 */     return this.nodeType == TYPES.LOCATOR;
  /*     */   }
  /*     */
  /*     */   public TYPES getNodeType()
  /*     */   {
  /*  75 */     return this.nodeType;
  /*     */   }
  /*     */
  /*     */   public void setNodeType(TYPES nodeType)
  /*     */   {
  /*  80 */     this.nodeType = nodeType;
  /*     */   }
  /*     */
  /*     */   public boolean isJmxManagerNode()
  /*     */   {
  /*  85 */     return this.jmxManagerNode;
  /*     */   }
  /*     */
  /*     */   public void setJmxManagerNode(boolean jmxManagerNode)
  /*     */   {
  /*  90 */     this.jmxManagerNode = jmxManagerNode;
  /*     */   }
  /*     */
  /*     */   public String getNodeName()
  /*     */   {
  /*  95 */     return this.nodeName;
  /*     */   }
  /*     */
  /*     */   public void setNodeName(String nodeName)
  /*     */   {
  /* 100 */     this.nodeName = nodeName;
  /*     */   }
  /*     */
  /*     */   public String getWorkPath()
  /*     */   {
  /* 105 */     return this.workPath;
  /*     */   }
  /*     */
  /*     */   public void setWorkPath(String workPath)
  /*     */   {
  /* 110 */     this.workPath = workPath;
  /*     */   }
  /*     */
  /*     */   public String getHomePath()
  /*     */   {
  /* 115 */     return this.homePath;
  /*     */   }
  /*     */
  /*     */   public void setHomePath(String homePath)
  /*     */   {
  /* 120 */     this.homePath = homePath;
  /*     */   }
  /*     */
  /*     */   public String currentGridId()
  /*     */   {
  /* 125 */     return ADFConfigHelper.getConfig(ITEMS.SYSTEM_GRIDNAME);
  /*     */   }
  /*     */
  /*     */   public String getMasterGridName()
  /*     */   {
  /* 130 */     return this.masterGridName;
  /*     */   }
  /*     */
  /*     */   public boolean isClientSide()
  /*     */   {
  /* 135 */     return this.nodeType == TYPES.CLIENT;
  /*     */   }
  /*     */
  /*     */   public void setMasterGridName(String masterGridName)
  /*     */   {
  /* 140 */     this.masterGridName = masterGridName;
  /*     */   }
  /*     */ }
}


/* Location:              C:\Users\jliu315\repository\com\jnj\adf\adf-core\0.3.04\adf-core-0.3.04.jar!\com\jnj\adf\grid\support\system\ConfigurationConstants.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */