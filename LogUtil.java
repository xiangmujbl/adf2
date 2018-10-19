package com.jnj.adf.dataservice.adfcoreignite;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//



//import com.jnj.adf.grid.support.system.ADFConfigHelper;
import java.net.URISyntaxException;
import java.util.Map;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.LoggerConfig;

public class LogUtil {
    private static Logger appLog = LogManager.getLogger("com.jnj.adf.appLog");
    private static Logger mqSyncLog = LogManager.getLogger("com.jnj.adf.coreLog");
    private static Logger auditLog = LogManager.getLogger("com.jnj.adf.auditLog");
    private static Logger checkLog = LogManager.getLogger("com.jnj.adf.coreLog");
    private static Logger coreLog = LogManager.getLogger("com.jnj.adf.coreLog");
    private static final LogUtil.Watch localWatch = new LogUtil.Watch();

    public LogUtil() {
    }

    public static Logger getCoreLog() {
        return coreLog;
    }

    public static Logger getMqSyncLog() {
        return mqSyncLog;
    }

    public static Logger getAuditLog() {
        return auditLog;
    }

    public static Logger getAppLog() {
        return appLog;
    }

    public static Logger getLogicLog() {
        return appLog;
    }

    public static Logger getLogger() {
        return appLog;
    }

    public static Logger getCheckLog() {
        return checkLog;
    }

    public static void modifyLevel(Logger logger, Level level) {
        LoggerContext context = (LoggerContext)LogManager.getContext(false);
        LoggerConfig conf = context.getConfiguration().getLoggerConfig(logger.getName());
        conf.setLevel(level);
        context.updateLoggers();
    }

    public static Map<String, LoggerConfig> getAllLevel() throws URISyntaxException {
        LoggerContext context = (LoggerContext)LogManager.getContext((ClassLoader)null, true, ADFConfigHelper.class.getClassLoader().getResource(ADFConfigHelper.getLoggerFileName()).toURI());
        return context.getConfiguration().getLoggers();
    }

    public static void modifyLevel(String name, Level level) throws URISyntaxException {
        LoggerContext context = (LoggerContext)LogManager.getContext((ClassLoader)null, true, ADFConfigHelper.class.getClassLoader().getResource(ADFConfigHelper.getLoggerFileName()).toURI());
        LoggerConfig conf = context.getConfiguration().getLoggerConfig(name);
        conf.setLevel(level);
        context.updateLoggers();
    }

    public static final void startTimer() {
        localWatch.rest();
    }

    public static final long cost() {
        return localWatch.cost();
    }

    public long longCost() {
        return localWatch.longCost();
    }

    public static final LogUtil.Watch newWatch() {
        return new LogUtil.Watch();
    }

    public static class Watch {
        private final ThreadLocal<Long> TL_COSTUSE = new ThreadLocal();
        private final ThreadLocal<Long> TL_START = new ThreadLocal();

        public Watch() {
        }

        public void rest() {
            long l = System.currentTimeMillis();
            this.TL_COSTUSE.set(l);
            this.TL_START.set(l);
        }

        public long cost() {
            Long end = (Long)this.TL_COSTUSE.get();
            long t1 = end != null ? end : System.currentTimeMillis();
            long t2 = System.currentTimeMillis();
            long d = t2 - t1;
            LogUtil.startTimer();
            return d;
        }

        public long longCost() {
            Long end = (Long)this.TL_START.get();
            long t1 = end != null ? end : System.currentTimeMillis();
            long t2 = System.currentTimeMillis();
            long d = t2 - t1;
            LogUtil.startTimer();
            return d;
        }
    }
}
