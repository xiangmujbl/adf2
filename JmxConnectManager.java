package com.jnj.adf.dataservice.adfcoreignite;
//import com.jnj.adf.client.connect.GridUtils;
//import com.jnj.adf.grid.runtime.GridInfo;
//import com.jnj.adf.grid.support.system.ADFConfigHelper;
//import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.adf.dataservice.domain.grid.GridInfoData;
import com.jnj.adf.dataservice.service.DataViewService;
import org.apache.commons.lang3.StringUtils;
import org.apache.geode.management.DistributedRegionMXBean;
import org.apache.geode.management.internal.cli.shell.JmxOperationInvoker;
import org.springframework.beans.factory.annotation.Autowired;

//MemoryMetricsMXBean;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.log4j.Logger;
public class JmxConnectManager {
    @Autowired
    DataViewService dataViewService;
    public static class JmxInstance {
        private JmxOperationInvoker operationInvoker;
        private Map<String, DistributedRegionMXBean> distributedRegionMBeanMap = new ConcurrentHashMap<>();


        public JmxInstance(JmxOperationInvoker operationInvoker) {
            this.operationInvoker = operationInvoker;
        }

        public JmxOperationInvoker getOperationInvoker() {
            return operationInvoker;
        }


        public synchronized DistributedRegionMXBean getRegioinBean(String path) {
            DistributedRegionMXBean mbean = distributedRegionMBeanMap.get(path);
            if (mbean == null) {
                ObjectName objectName = null;
                try {
                    objectName = new ObjectName("GemFire:service=Region,name=" + path + ",type=Distributed");
                } catch (MalformedObjectNameException e) {
                    throw new RuntimeException("", e);
                }
                mbean = operationInvoker.getMBeanProxy(objectName, DistributedRegionMXBean.class);
                distributedRegionMBeanMap.put(path, mbean);
            }
            return mbean;
        }
    }


    private final static Map<String, JmxInstance> jmxInstanceMap = new ConcurrentHashMap<>();


    public final static synchronized JmxInstance getJmxInstance(String gridId) {
        JmxInstance inst=jmxInstanceMap.get(gridId);
        if(inst==null){
            GridInfoData info=GridUtils.getGridInfo(gridId);
            inst=connect(info);
        }
        return inst;
    }

    /***
     * TODO: ssl
     */
    public final synchronized static JmxInstance connect(GridInfoData info) {
        try {
            JmxInstance inst = jmxInstanceMap.get(info.getGridId());
            if (inst != null)
                return inst;

            Properties prop = new Properties();
            String locators = info.getLocators();
            List<String> hostNames = processLocators(locators);
            for (String host : hostNames) {
                JmxOperationInvoker operationInvoker = new JmxOperationInvoker(host, info.getJmxPort(), prop);
                inst = new JmxInstance(operationInvoker);
                jmxInstanceMap.put(info.getGridId(), inst);
                break;
            }
            return inst;
        } catch (Exception e) {
            Logger.getLogger("JmxConnnectManager").error("", e);
        }
        return null;
    }

    private static List<String> processLocators(String locators) {
        List<String> list = new ArrayList<>();
        if (!StringUtils.isEmpty(locators)) {
            String[] grp = locators.split(",");
            for (int i = 0; i < grp.length; i++) {
                String[] sub = grp[i].split("\\[");
                String ip = sub[0];
                list.add(ip);
            }
        }
        return list;
    }

    public static void main(String[] args) {

        try {
            ADFConfigHelper.initConfig();
            ADFConfigHelper.initLog4j();
            GridUtils.connect();
//            List<GridInfo> list = GridUtils.listGrids();
            List<GridInfoData> gridList = gridInfoService.getGridList();
            list.forEach(g -> {
                JmxInstance inst = JmxConnectManager.connect(g);
                String[] x = (String[]) inst.getOperationInvoker().invoke(
                        "GemFire:service=System,type=Distributed", "listMembers", null, null);
                System.out.print(Arrays.asList(x));
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
