package model;

import java.util.Map;
import java.util.logging.Logger;

public class GameDataBean {

    private static Logger log = Logger.getLogger(GameDataBean.class.getName());
    private String enemy = null;
    private Map<String, String> monitorMap = null;

    public GameDataBean() {
    }

    public String getEnemy() {
        return enemy;
    }

    public void setEnemy(String enemy) {
        this.enemy = enemy;
    }

    public Map<String, String> getMonitorMap() {
        return monitorMap;
    }

    public void setMonitorMap(Map<String, String> monitorMap) {
        this.monitorMap = monitorMap;
    }
}