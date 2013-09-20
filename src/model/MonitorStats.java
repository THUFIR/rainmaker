package model;

import java.util.Map;
import java.util.logging.Logger;

public class MonitorStats {

    private static Logger log = Logger.getLogger(MonitorStats.class.getName());
    private Map<String, String> monitorMap = null;

    public MonitorStats() {
    }

    public Map<String, String> getMonitorMap() {
        return monitorMap;
    }

    public void setMonitorMap(Map<String, String> monitorMap) {
        this.monitorMap = monitorMap;
    }
}