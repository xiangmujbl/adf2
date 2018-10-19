//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jnj.adf.dataservice.adfcoreignite;

//import com.jnj.adf.grid.support.context.ADFRuntime;
import com.jnj.adf.dataservice.adfcoreignite.ADFConfigHelper.ITEMS;
import com.jnj.adf.dataservice.adfcoreignite.ADFConfigHelper.TYPES;
//import com.jnj.adf.grid.utils.LogUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

public class WorkPathHelper {
    private static FileLock lock;

    public WorkPathHelper() {
    }

    public void unlock(FileLock lock) {
        if (lock != null) {
            try {
                lock.release();
            } catch (IOException var3) {
                throw new RuntimeException(var3);
            }

            lock = null;
        }

    }

    public static File verifyWorkPath(String path) {
        String home = ADFConfigHelper.getConfig(ITEMS.GS_WORK);
        return verifyPath(home, path, false);
    }

    public static File verifyPath(String path) {
        return verifyPath(path, false);
    }

    public static File verifyPath(String path, boolean clean) {
        String home = ADFConfigHelper.getConfig(ITEMS.GS_HOME);
        return verifyPath(home, path, clean);
    }

    public static File verifyPath(String home, String path, boolean clean) {
        StringBuilder builder = new StringBuilder();
        builder.append(home).append("/");
        String parent = builder.toString();
        File dir = new File(parent, path);
        if (clean && dir.exists()) {
            FileUtils.deleteQuietly(dir);
        }

        if (!dir.exists()) {
            boolean bl = dir.mkdirs();
            if (!bl) {
                System.err.println("Directory create failure:" + dir.getAbsolutePath());
                return null;
            }
        }

        return dir;
    }

    public static String verifyServerName(ITEMS item, String type, String name) {
        String home = ADFConfigHelper.getConfig(item);
        StringBuilder builder = new StringBuilder();
        builder.append(home).append("/").append(type);
        String parent = builder.toString();
        if (StringUtils.isBlank(name)) {
            return randomServerName(parent);
        } else {
            File f = findDirectory(parent, name);
            if (f != null && lock(f)) {
                System.out.println("Check directory ok:" + f);
                return name;
            } else {
                throw new RuntimeException("Error create work path:" + parent + "/" + name);
            }
        }
    }

    public static String randomServerName(String parent) {
        for(int i = 0; i < 100; ++i) {
            File f = findDirectory(parent, "node" + i);
            if (f != null && lock(f)) {
                return "node" + i;
            }
        }

        return null;
    }

    public static synchronized File findDirectory(String parent, String serverName) {
        if (serverName != null) {
            File dir = new File(parent, serverName);
            boolean bl;
            if (dir.exists()) {
                if (!ADFConfigHelper.getBoolean(ITEMS.ADF_NODE_RESET)) {
                    return dir;
                }

                bl = FileUtils.deleteQuietly(dir);
                LogUtil.getCoreLog().info("Work path {} exists. Clean all:{}.", new Object[]{dir, bl});
            }

            bl = dir.mkdirs();
            LogUtil.getCoreLog().info("Work path {} created.", new Object[]{dir});
            if (!bl) {
                System.err.println("Directory create failure:" + dir.getAbsolutePath());
                return null;
            } else {
                return dir;
            }
        } else {
            return null;
        }
    }

    private static boolean lock(File directory) {
        String name = "node.lock";
        File lockfile = new File(directory, name);
        lockfile.deleteOnExit();

        try {
            FileChannel fc = (FileChannel)Channels.newChannel(new FileOutputStream(lockfile));
            lock = fc.tryLock();
            if (lock != null) {
                return true;
            }
        } catch (IOException var4) {
            System.err.println(var4.toString());
        } catch (OverlappingFileLockException var5) {
            System.err.println(var5.toString());
        }

        return false;
    }

    public static String diskStore(String diskStoreName) {
        return diskStore((String)null, diskStoreName);
    }

    public static String diskStore(String category, String diskStoreName) {
        String parent = ADFConfigHelper.getConfig(ITEMS.GS_WORK);
        String serverName = ADFConfigHelper.getProperty(ITEMS.NODE_NAME);
        String nodeTypePath = "server";
        if (ADFRuntime.getRuntime().getNodeType() == TYPES.LOCATOR) {
            nodeTypePath = "locator";
        }

        StringBuilder bui = new StringBuilder();
        bui.append(parent).append("/").append(nodeTypePath).append("/").append(serverName);
        if (!StringUtils.isEmpty(category)) {
            bui.append("/").append(category);
        }

        File diskStore = findDirectory(bui.toString(), diskStoreName);
        return diskStore.getAbsolutePath();
    }
}
