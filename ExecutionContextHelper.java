//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

//package com.jnj.adf.grid.namespace;

//import com.jnj.adf.client.api.JsonObject;
import com.jnj.adf.dataservice.adfcoreignite.grid.namespace.ExecutionContextImpl;
//import com.jnj.adf.grid.common.GridPathNameUtil;
//import com.jnj.adf.grid.support.context.ADFRuntime;
//import com.jnj.adf.grid.support.system.ADFConfigHelper;
import com.jnj.adf.dataservice.adfcoreignite.ADFConfigHelper.ITEMS;
//import com.jnj.adf.grid.utils.LogUtil;
import org.apache.commons.lang3.StringUtils;

public final class ExecutionContextHelper
{
    public static final String QA = "QA";
    public static final String PRODUCTION = "PRD";
    public static final String DEV = "DEV";
    public static final String SEP = "/";
    private static final ThreadLocal<ExecutionContextImpl> tl_ecc = new InheritableThreadLocal<ExecutionContextImpl>() {
        protected ExecutionContextImpl initialValue() {
            ExecutionContextImpl ecc = new ExecutionContextImpl();
            return ecc;
        }
    };

    public ExecutionContextHelper() {
    }

    private static final ExecutionContextImpl changValue(String namespace, String version) {
        ExecutionContextImpl ecc = new ExecutionContextImpl(namespace, version);
        return ecc;
    }

    public static final ExecutionContext setNamespace(String namespace) {
        ExecutionContextImpl ecc = (ExecutionContextImpl)tl_ecc.get();
        ExecutionContextImpl newEcc = changValue(namespace, ecc.getVersion());
        tl_ecc.set(newEcc);
        return ecc;
    }

    public static final ExecutionContext setVersion(String version) {
        ExecutionContextImpl ecc = (ExecutionContextImpl)tl_ecc.get();
        ExecutionContextImpl newEcc = changValue(ecc.getNamespace(), version);
        tl_ecc.set(newEcc);
        return newEcc;
    }

    public static final void clearNamespace() {
        tl_ecc.remove();
    }

    public static final String currentVersion() {
        ExecutionContext ecc = currentExecutionContext();
        return ecc.getVersion();
    }

    public static final String currentNamespace() {
        return currentNamespace((String)null);
    }

    public static final String currentNamespace(String path) {
        ExecutionContext ecc = currentExecutionContext(path);
        return ecc.getNamespace();
    }

    public static final ExecutionContext currentExecutionContext() {
        return currentExecutionContext((String)null);
    }

    public static final ExecutionContext currentExecutionContext(String inputPath) {
        ExecutionContextImpl ecc = (ExecutionContextImpl)tl_ecc.get();
        if (ecc.getNamespace() == null) {
            String nm;
            if (ADFRuntime.getRuntime().isClientSide()) {
                nm = ADFConfigHelper.getProperty(ITEMS.PATH_NAMESPACE);
                ecc.setNamespace(nm);
            } else if (!StringUtils.isEmpty(inputPath)) {
                nm = NamespaceService.getInstance().findNamespace(inputPath);
                if (nm != null) {
                    ecc.setNamespace(nm);
                }
            }
        }

        return ecc;
    }

    public static final JsonObject genNamespaceParam(String path) {
        JsonObject jo = JsonObject.create();
        ExecutionContext ecc = currentExecutionContext(path);
        if (ecc != null) {
            jo.append("_NAMESPACE", ecc.getNamespace());
        }

        return jo;
    }

    public static final String processNamespace(String inputPath) {
        String path = inputPath;
        ExecutionContext ecc = currentExecutionContext(inputPath);
        if (ecc != null && !StringUtils.isEmpty(ecc.getNamespace())) {
            if (!inputPath.startsWith("/" + ecc.getNamespace())) {
                StringBuilder bd = new StringBuilder("/");
                path = GridPathNameUtil.escapePath(inputPath);
                if (!"PRD".equals(ecc.getNamespace())) {
                    bd.append(ecc.getNamespace()).append(path);
                    path = bd.toString();
                } else {
                    path = inputPath;
                }
            }

            LogUtil.getCoreLog().debug("Found namespace:{}, inputPath:{} to {}", new Object[]{ecc.getNamespace(), inputPath, path});
        }

        return path;
    }

    public static void main(String[] args) {
        String s = checkNamespace("/QA/prod1");
        System.out.println(s);
        s = checkNamespace("/INT/prod1");
        System.out.println(s);
        s = checkNamespace("/uto/prod1");
        System.out.println(s);
        s = checkNamespace("/utoprod1");
        System.out.println(s);
    }

    public static final String checkNamespace(String fullpath) {
        if (!StringUtils.isEmpty(fullpath)) {
            fullpath = GridPathNameUtil.escapePath(fullpath);
            String[] names = fullpath.split("/");
            if ("QA".equals(names[1])) {
                return "QA";
            }

            if ("INT".equals(names[1])) {
                return "INT";
            }
        }

        return null;
    }
}
