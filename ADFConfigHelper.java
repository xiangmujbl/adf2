package com.jnj.adf.dataservice.adfcoreignite;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

//package com.jnj.adf.grid.support.system;

//import com.gemstone.gemfire.admin.internal.InetAddressUtil;
import com.jnj.adf.dataservice.adfcoreignite.ConfigurationConstants;
//import com.jnj.adf.grid.support.context.ADFRuntime;
//import com.jnj.adf.grid.utils.LogUtil;
//import com.jnj.adf.grid.utils.VersionUtils;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiConsumer;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.ThreadContext;
import org.apache.logging.log4j.core.LoggerContext;
import org.springframework.util.PropertyPlaceholderHelper;

public class ADFConfigHelper implements ConfigurationConstants {
    private static final AtomicBoolean initlized = new AtomicBoolean(false);
    private static final AtomicBoolean initlizing = new AtomicBoolean(false);
    private static final AtomicBoolean initlized_log = new AtomicBoolean(false);
    private static int locatorCount = 0;
    static Properties serverConfig = new Properties();
    static Properties defaultConfig = new Properties();
    static PropertyPlaceholderHelper helper = new PropertyPlaceholderHelper("${", "}");
    private static final String locatorFlag = "-l-";
    private static final String serverFlag = "-s-";

    public ADFConfigHelper() {
    }

    public static void main(String[] args) {
        System.out.println(">>" + getVersion());
    }

    public static String getVersion() {
        String version = "";
        InputStream in = ADFConfigHelper.class.getClassLoader().getResourceAsStream("application.properties");
        Properties prop = new Properties();

        try {
            prop.load(in);
            version = prop.getProperty("version");
        } catch (IOException var4) {
            ;
        }

        return version;
    }

    public static String getConfig(ADFConfigHelper.ITEMS item) {
        return serverConfig != null && serverConfig.containsKey(item.name()) ? serverConfig.getProperty(item.getProKey()) : System.getProperty(item.getProKey());
    }

    private static ADFConfigHelper.ITEMS stringToITEMS(String name) {
        ADFConfigHelper.ITEMS item = (ADFConfigHelper.ITEMS)ADFConfigHelper.ITEMS.nameMap.get(name);
        if (item == null) {
            item = (ADFConfigHelper.ITEMS)ADFConfigHelper.ITEMS.proKeyMap.get(name);
        }

        return item;
    }

    public static void setProperty(String name, Object value) {
        if (value != null) {
            ADFConfigHelper.ITEMS item = stringToITEMS(name);
            if (item != null) {
                setProperty(item, value);
            } else {
                if (initlized.get()) {
                    Object old = serverConfig.get(name);
                    if (value.equals(old)) {
                        return;
                    }

                    LogUtil.getCoreLog().info("Set {}={}", new Object[]{name, value});
                }

                serverConfig.put(name, value);
            }

        }
    }

    public static void setProperty(ADFConfigHelper.ITEMS item, Object value) {
        if (initlized.get()) {
            Object old = serverConfig.get(item.proKey);
            if (value.equals(old)) {
                return;
            }

            LogUtil.getCoreLog().info("Set {}={}\t//{}", new Object[]{item.proKey, value, item.name()});
        }

        serverConfig.put(item.proKey, value);
        serverConfig.put(item.name(), value);
    }

    public static void putAll(Properties pro) {
        if (initlized.get()) {
            LogUtil.getCoreLog().info("ADF config Helper putAll additinal properties= {}.", new Object[]{pro});
        }

        if (pro != null) {
            Iterator entryIt = pro.entrySet().iterator();

            while(entryIt.hasNext()) {
                Entry<Object, Object> entry = (Entry)entryIt.next();
                setProperty((String)((String)entry.getKey()), (String)entry.getValue());
            }
        }

    }

    public static void initLog4j() {
        initLog4j(getLoggerFileName());
    }

    public static void initLog4j(String configFile) {
        if (!initlized_log.get()) {
            initlized_log.set(true);
            System.setProperty("Log4jContextSelector", "org.apache.logging.log4j.core.async.AsyncLoggerContextSelector");
            if (getProperty(ADFConfigHelper.ITEMS.NODE_TYPE.name()) == null) {
                setProperty((String)ADFConfigHelper.ITEMS.NODE_TYPE.name(), "");
            }

            if (getProperty(ADFConfigHelper.ITEMS.NODE_NAME.name()) == null) {
                setProperty((String)ADFConfigHelper.ITEMS.NODE_NAME.name(), "");
            }

            try {
                URL url = ADFConfigHelper.class.getClassLoader().getResource(configFile);
                LoggerContext context = (LoggerContext)LogManager.getContext(false);
                ThreadContext.put(ADFConfigHelper.ITEMS.GS_HOME.proKey, getConfig(ADFConfigHelper.ITEMS.GS_HOME));
                ThreadContext.put(ADFConfigHelper.ITEMS.NODE_TYPE.proKey, getConfig(ADFConfigHelper.ITEMS.NODE_TYPE));
                ThreadContext.put(ADFConfigHelper.ITEMS.NODE_NAME.proKey, getConfig(ADFConfigHelper.ITEMS.NODE_NAME));
                LogUtil.getCoreLog().info("Log4j config file loading from {}.", new Object[]{url});
                context.setConfigLocation(url.toURI());
            } catch (URISyntaxException var3) {
                System.err.print(var3.getMessage());
                initlized_log.set(false);
            }

            initlized_log.set(true);
        }
    }

    public static String getLoggerFileName() {
        return System.getProperty("logger.file.name", "log4j2-server.xml");
    }

    public static void dump() {
        try {
            StringBuilder bu = new StringBuilder("\n=======================ADF config==============================\n");
            final List list = new ArrayList(serverConfig.keySet());
            Collections.sort(list);
            final List priorList = new ArrayList();
            defaultConfig.forEach(new BiConsumer<Object, Object>() {
                public void accept(Object t, Object u) {
                    if (list.contains(t)) {
                        Object x = ADFConfigHelper.serverConfig.getProperty(t.toString());
                        if (x != null && !x.equals(u)) {
                            priorList.add(t);
                        }
                    }

                }
            });
            Collections.sort(priorList);
            Iterator var3 = priorList.iterator();

            Object key;
            ADFConfigHelper.ITEMS item;
            String value;
            while(var3.hasNext()) {
                key = var3.next();
                if (!ADFConfigHelper.ITEMS.nameMap.containsKey(key)) {
                    item = (ADFConfigHelper.ITEMS)ADFConfigHelper.ITEMS.proKeyMap.get(key);
                    value = serverConfig.getProperty(key.toString());
                    if (item != null) {
                        bu.append(key).append("=").append(value).append("\t//").append(item.name()).append("\n");
                    } else {
                        bu.append(key).append("=").append(value).append("\n");
                    }
                }
            }

            bu.append("===============================================================\n");
            var3 = list.iterator();

            while(var3.hasNext()) {
                key = var3.next();
                if (!priorList.contains(key) && !ADFConfigHelper.ITEMS.nameMap.containsKey(key)) {
                    item = (ADFConfigHelper.ITEMS)ADFConfigHelper.ITEMS.proKeyMap.get(key);
                    value = serverConfig.getProperty(key.toString());
                    if (item != null) {
                        bu.append(key).append("=").append(value).append("\t//").append(item.name()).append("\n");
                    } else {
                        bu.append(key).append("=").append(value).append("\n");
                    }
                }
            }

            bu.append("===============================================================");
            LogUtil.getCoreLog().info(bu.toString());
        } catch (Exception var7) {
            var7.printStackTrace();
        }

    }

    public static void initConfig() {
        initConfig(getEnvFile());
//        VersionUtils.findVersion(ADFConfigHelper.class.getClassLoader());
    }

    public static String getEnvFile() {
        return System.getProperty("env.file.name", "env.properties");
    }

    public static void initConfig(String envFile) {
        if (!initlized.get()) {
            try {
                Enumeration urls = ADFConfigHelper.class.getClassLoader().getResources(envFile);

                while(urls.hasMoreElements()) {
                    System.out.println("Found env url:" + urls.nextElement());
                }

                URL envFileURL = ADFConfigHelper.class.getClassLoader().getResource(envFile);
                initConfig("config/com/jnj/adf/core/env-default.properties", envFileURL, (Properties)null);
            } catch (Exception var3) {
                var3.printStackTrace();
                System.exit(-10);
            }

        }
    }

    public static void initConfig(URL envFileURL) {
        initConfig("config/com/jnj/adf/core/env-default.properties", envFileURL, (Properties)null);
    }

    public static void initConfig(Properties envProperties) {
        initConfig("config/com/jnj/adf/core/env-default.properties", (URL)null, envProperties);
    }

    private static void initConfig(String defaultEnvFile, URL envFileURL, Properties envProperties) {
        if (!initlizing.get() && !initlized.get()) {
            initlizing.set(true);

            try {
                beforeConfigInitial();
                initConfig0(defaultEnvFile, envFileURL, envProperties);
                afterConfigInitial();
            } catch (IOException var4) {
                initlized.set(false);
                var4.printStackTrace();
                System.exit(-1);
            }

            ADFRuntime.getRuntime().setHomePath(getHomePath());
            ADFRuntime.getRuntime().setWorkPath(getWorkPath());
            ADFRuntime.getRuntime().setNodeName(getNodeName());
            initlized.set(true);
        }
    }

    private static void beforeConfigInitial() {
    }

    private static void afterConfigInitial() {
        WorkPathHelper.verifyPath("log");
    }

    private static synchronized void initConfig0(String defaultEnvFile, URL envFileURL, Properties envProperties) throws IOException {
        URL url = ADFConfigHelper.class.getClassLoader().getResource(defaultEnvFile);
        Properties prop;
        if (url != null) {
            prop = new Properties();
            prop.load(url.openStream());
            putAll(prop);
            defaultConfig.load(url.openStream());
        }

        System.out.println("Load default env from:" + url);
        if (envProperties != null) {
            putAll(envProperties);
            System.out.println("Load env from given properties, properties items:" + envProperties.size());
        } else if (envFileURL != null) {
            prop = new Properties();
            prop.load(envFileURL.openStream());
            putAll(prop);
            System.out.println("Load env from:" + envFileURL);
        }

        Iterator it = serverConfig.keySet().iterator();

        String bindip;
        while(it.hasNext()) {
            bindip = it.next().toString();
            processSystemEnv(bindip);
        }

        System.setProperty("gemfire.ALLOW_PERSISTENT_TRANSACTIONS", "true");
        bindip = getConfig(ADFConfigHelper.ITEMS.BINDIP);
        processBindIp(bindip);
        String locators = getProperty(ADFConfigHelper.ITEMS.LOCATORS);
        processLocators(locators);
        String namingServers = getProperty(ADFConfigHelper.ITEMS.NAMING_SERVER);
        namingServers = processLocalhost(namingServers);
        setProperty((ADFConfigHelper.ITEMS)ADFConfigHelper.ITEMS.NAMING_SERVER, namingServers);
        String remoteLocators = getProperty(ADFConfigHelper.ITEMS.CLUSTER_REMOTE_LOCATORS);
        remoteLocators = processLocalhost(remoteLocators);
        setProperty((ADFConfigHelper.ITEMS)ADFConfigHelper.ITEMS.CLUSTER_REMOTE_LOCATORS, remoteLocators);
        System.setProperty(ADFConfigHelper.ITEMS.GS_ENV.name(), getConfig(ADFConfigHelper.ITEMS.GS_ENV));
        System.setProperty(ADFConfigHelper.ITEMS.GS_ENV.proKey, getConfig(ADFConfigHelper.ITEMS.GS_ENV));
        replacePlaceholders();
    }

    private static void replacePlaceholders() {
        Iterator entries = serverConfig.entrySet().iterator();

        while(entries.hasNext()) {
            Entry e = (Entry)entries.next();
            Object value = e.getValue();
            String replaced = helper.replacePlaceholders(value.toString(), serverConfig);
            if (replaced != null && !replaced.equals(value)) {
                setProperty((String)e.getKey().toString(), replaced);
            }
        }

    }

    public static void processLocators(String locators) {
        if (!StringUtils.isEmpty(locators)) {
            String[] grp = locators.split(",");
            locatorCount = grp.length;

            for(int i = 0; i < grp.length; ++i) {
                String[] sub = grp[i].split("\\[");
                String ip = sub[0];
                String port = sub[1].substring(0, sub[1].length() - 1);
                String key1 = ADFConfigHelper.ITEMS.LOCATOR.name() + (i + 1);
                String key2 = ADFConfigHelper.ITEMS.PORT.name() + (i + 1);
                setProperty((String)key1, ip);
                setProperty((String)key2, Integer.valueOf(port));
            }
        }

    }

    public static String processLocalhost(String locators) {
        if (!StringUtils.isEmpty(locators) && locators.indexOf("localhost") != -1) {
            String ip = localhostBindIp((String)null);
            locators = locators.replaceAll("localhost", ip);
        }

        return locators;
    }

    public static String localhostBindIp(String bindIp) {
        boolean isLocalhost = bindIp == null || "localhost".equalsIgnoreCase(bindIp);
        if (isLocalhost) {
            try {
                InetAddress addr = InetAddress.getLocalHost();
                bindIp = addr.getHostAddress();
            } catch (UnknownHostException var3) {
                initlized.set(false);
                System.err.println("Unkonw host!");
                return null;
            }
        }

        return bindIp;
    }

    private static void processBindIp(String bindIp) {
        bindIp = localhostBindIp(bindIp);
        if (!StringUtils.isEmpty(bindIp)) {
            setProperty((ADFConfigHelper.ITEMS)ADFConfigHelper.ITEMS.BINDIP, bindIp);
            String locators = getConfig(ADFConfigHelper.ITEMS.LOCATORS);
            locators = locators.replaceAll("localhost", bindIp);
            setProperty((ADFConfigHelper.ITEMS)ADFConfigHelper.ITEMS.LOCATORS, locators);
        }

    }

    private static void processSystemEnv(String name) {
        String value = System.getProperty(name);
        if (StringUtils.isEmpty(value)) {
            value = System.getenv(name);
        }

        if (!StringUtils.isEmpty(value)) {
            setProperty((String)name, value);
        }

    }

//    public static void setMemberName(String serverName) {
//        String hostName = InetAddressUtil.createLocalHost().getHostName();
//        setProperty((String)ADFConfigHelper.ITEMS.HOST_NAME.name(), hostName);
//        setProperty((String)ADFConfigHelper.ITEMS.NODE_NAME.name(), serverName);
//        StringBuilder bui = new StringBuilder();
//        if (ADFRuntime.getRuntime().getNodeType() == ADFConfigHelper.TYPES.LOCATOR) {
//            bui.append(hostName).append("-l-").append(serverName);
//        } else {
//            bui.append(hostName).append("-s-").append(serverName);
//        }
//
//        setProperty((ADFConfigHelper.ITEMS)ADFConfigHelper.ITEMS.MEMBER_NAME, bui.toString());
//    }

    public static String getMemberName() {
        return getConfig(ADFConfigHelper.ITEMS.MEMBER_NAME);
    }

    public static String getMemberId() {
        return getConfig(ADFConfigHelper.ITEMS.MEMBER_ID);
    }

    public static String getNodeName() {
        return getConfig(ADFConfigHelper.ITEMS.NODE_NAME);
    }

    public static String getWorkPath() {
        return getConfig(ADFConfigHelper.ITEMS.GS_WORK);
    }

    public static String getHomePath() {
        return getConfig(ADFConfigHelper.ITEMS.GS_HOME);
    }

    public static String getProperty(ADFConfigHelper.ITEMS item) {
        return getProperty(item, "");
    }

    public static String getProperty(ADFConfigHelper.ITEMS item, String defaultValue) {
        String value = serverConfig.getProperty(item.name());
        if (value == null) {
            value = serverConfig.getProperty(item.proKey);
        }

        if (value == null) {
            value = defaultValue;
        }

        return value;
    }

    public static String getDefaultLocatorAdddress() {
        return locatorCount == 0 ? null : getProperty(ADFConfigHelper.ITEMS.LOCATOR.name() + "1");
    }

    public static Integer getDefaultLocatorPort() {
        if (locatorCount == 0) {
            return null;
        } else {
            Object port = getInteger(ADFConfigHelper.ITEMS.PORT.name() + "1");
            return port instanceof Integer ? (Integer)port : NumberUtils.toInt(port.toString());
        }
    }

    public static String getProperty(String name) {
        ADFConfigHelper.ITEMS item = stringToITEMS(name);
        return item != null ? getProperty(item) : serverConfig.getProperty(name);
    }

    public static String getProperty(String name, String defaultValue) {
        String value = getProperty(name);
        return value == null ? defaultValue : value;
    }

    public static Integer getInteger(String name) {
        Object val = getProperty(name);
        if (val instanceof Integer) {
            return (Integer)val;
        } else {
            return val != null ? NumberUtils.toInt(val.toString()) : 0;
        }
    }

    public static Long getLong(String name) {
        Object val = getProperty(name);
        if (val instanceof Long) {
            return (Long)val;
        } else {
            return val != null ? NumberUtils.toLong(val.toString()) : 0L;
        }
    }

    public static Long getLong(ADFConfigHelper.ITEMS item) {
        Object val = getProperty(item);
        if (val instanceof Long) {
            return (Long)val;
        } else {
            return val != null ? NumberUtils.toLong(val.toString()) : 0L;
        }
    }

    public static Integer getInteger(ADFConfigHelper.ITEMS item) {
        Object val = getProperty(item);
        if (val instanceof Integer) {
            return (Integer)val;
        } else {
            return val != null ? NumberUtils.toInt(val.toString()) : 0;
        }
    }

    public static boolean getBoolean(ADFConfigHelper.ITEMS item) {
        return getBoolean(item.name());
    }

    public static boolean getBoolean(String name) {
        Object v = serverConfig.get(name);
        if (v != null && v instanceof Boolean) {
            return (Boolean)v;
        } else {
            return v != null ? BooleanUtils.toBoolean(v.toString()) : false;
        }
    }

    public static int getLocatorCount() {
        return locatorCount;
    }

    public static boolean isDev() {
        String env = getConfig(ADFConfigHelper.ITEMS.GS_ENV);
        return StringUtils.equalsIgnoreCase("dev", env);
    }

    public static enum ITEMS {
        GS_ENV("gs.env"),
        GS_HOME("gs.home"),
        GS_WORK("gs.work"),
        GS_VERSION("gs.version"),
        SYSTEM_NAME("system.name"),
        SYSTEM_GRIDNAME("system.gridname"),
        LOCATORS("gs.locators"),
        BINDIP("gs.bind.ip"),
        SYSTEM_WAN_AREA("system.wan.area"),
        HTTP_PLUGIN_BIND_ADDRESS("http.plugin.bind.address"),
        HTTP_PLUGIN_PORT("http.plugin.port"),
        SOLR_SERVERS("solr.zk.host"),
        ZOOKEEPER_SERVERS("zookeeper.servers"),
        ADF_SESSION_TIMEOUT("adf.session.timeout"),
        GS_LOGIC_JAR_DIR("gs.logicJarDir"),
        DEPLOY_WORKING_DIR("gs.deployWorkingDir"),
        NODE_NAME("gs.nodeName"),
        NODE_WORK_DIR("gs.node-workDir"),
        HOST_NAME("gs.hostName"),
        MEMBER_NAME("gs.memberName"),
        LOCATOR("gs.locator"),
        PORT("gs.port"),
        NODE_TYPE("gs.nodeType"),
        MEMBER_ID("gs.memberId"),
        DB_TYPE("gs.date.patterns"),
        DB_NAME("db.default.name"),
        DB_URL("db.url"),
        DB_PORT("db.port"),
        DB_DIRVER("db.driver"),
        DB_USERNAME("db.username"),
        DB_PASSWORD("db.password"),
        DB_DIALECT("db.dialect"),
        JMX_HOST("jmx.host"),
        JMX_PORT("jmx.port"),
        HTML_PORT("html.port"),
        JMX_RMI_PORT("jmx.port"),
        JMX_MANAGER_PORT("jmx.manager.port"),
        NAMING_SERVER("naming.server"),
        GRID_OPLOG_META("grid.meta.oplog.max"),
        GRID_OPLOG_QUEUE("grid.queue.oplog.max"),
        GRID_META_VISIBLE("grid.meta.visible"),
        GRID_STATUS_THREAD_INTERVAL("grid.status-thread.interval"),
        CACHE_REAL_TIME("grid.cache.realtime"),
        CLINET_MULTIPLE_USER("client.multiple.user.enabled"),
        STATISTICS_ENABLED("gs.statistics.enabled"),
        TEMPORAL_UNIT("gs.temporal.unit"),
        GRID_AUTHENTICATION_ENABLED("security.grid.authenticate.enabled"),
        AUTH_MODE("security.auth.mode"),
        CLUSTER_REMOTE_LOCATORS("cluster.remote.locators"),
        CLUSTER_DISTRIBUTE_SYSTEM_ID("cluster.distributed.system.id"),
        ASYNC_QUEUE_BATCH_SIZE("gs.queue.batch-size"),
        ASYNC_QUEUE_INTERVAL("gs.queue.time-interval"),
        ASYNC_QUEUE_BATH_CONFLATION("gs.queue.batch-conflation"),
        ASYNC_QUEUE_DISP_THREADS("gs.queue.dispatcher-threads"),
        MASTER_SERVICE_URL("master.service.url"),
        ASYNC_BQUEUE_SIZE("gs.block-queue.size"),
        ASYNC_THREAD_SIZE("gs.block-thread.size"),
        SECURITY_SYNC_RATE_HOURS("security.sync.rate.hours"),
        SECURITY_SYNC_SINGLE_INTERVAL("security.sync.single.interval"),
        MASTER_GRID_NAME("master.grid.name"),
        AS_MASTER_GRID("gs.grid.as.master"),
        ADF_REGISTER_TYPE("gs.grid.register.type"),
        CLIENT_POOL_SINGLEHOP("client.pool.pr-singlehop"),
        CLIENT_SENDING_SERVER_MODE("client.sending.server.mode"),
        CLIENT_POOL_TIMEOUT("client.pool.timeout"),
        CLIENT_POOL_RETRY("client.pool.retry-attemps"),
        CLIENT_POOL_MIN("client.pool.min-connections"),
        CLIENT_POOL_MAX("client.pool.max-connections"),
        CLIENT_FREE_TIMEOUT("client.pool.free-connection-timeout"),
        SOLR_COMMIT_MAXTIME("solr.commit.max.time"),
        SOLR_COMMIT_BATCH_SIZE("solr.commit.batch.size"),
        INDEXER_SOLR("gs.indexer.solr.enabled"),
        LUCENE_DIRECTORY("indexer.lucene.directory"),
        LUCENE_DISK_STORE("indexer.lucene.disk-store"),
        LUCENE_AUTO_RECOVER("indexer.lucene.auto-recover"),
        LUCENE_INTERVAL("indexer.lucene.time-interval"),
        LUCENE_BATCHSIZE("indexer.lucene.batch-size"),
        LUCENE_PAGESIZE("indexer.lucene.page-size"),
        LUCENE_PREFETCH_COUNT("indexer.lucene.cache.prefetch-count"),
        LUCENE_OPLOG("indexer.lucene.oplog-size"),
        LUCENE_SENDING_WINDOW_SIZE("indexer.lucene.windowedsending.window_size"),
        LUCENE_SENDING_BUFFER_SIZE("indexer.lucene.windowedsending.buffer_size"),
        LUCENE_STATISTIC("indexer.lucene.statistic"),
        MASTER_CLINET_MULTIPLE_USER("master.client.multiple.user.enabled"),
        MASTER_CLIENT_POOL_TIMEOUT("master.client.pool.timeout"),
        MASTER_CLIENT_POOL_RETRY("master.client.pool.retry-attemps"),
        MASTER_CLIENT_POOL_MIN("master.client.pool.min-connections"),
        MASTER_CLIENT_POOL_MAX("master.client.pool.max-connections"),
        MASTER_CLIENT_FREE_TIMEOUT("master.client.pool.free-connection-timeout"),
        ADF_NODE_RESET("adf.node.reset"),
        PATH_NAMESPACE("adf.path-namespace"),
        ADF_VERSION_NO("adf.core.version"),
        ADF_PARTITION_LISTENER("adf.partition.listener");

        private String proKey = null;
        private static Map<String, ADFConfigHelper.ITEMS> nameMap = new HashMap();
        private static Map<String, ADFConfigHelper.ITEMS> proKeyMap = new HashMap();

        private ITEMS(String name) {
            this.proKey = name;
        }

        public String getProKey() {
            return this.proKey;
        }

        static {
            ADFConfigHelper.ITEMS[] var0 = values();
            int var1 = var0.length;

            for(int var2 = 0; var2 < var1; ++var2) {
                ADFConfigHelper.ITEMS item = var0[var2];
                nameMap.put(item.name(), item);
                proKeyMap.put(item.proKey, item);
            }

        }
    }

    public static enum TYPES {
        DATASTORE,
        LOCATOR,
        JMX,
        MQ,
        CLIENT;

        private TYPES() {
        }
    }
}
