package io.vagvolgyi.ledmatrix.jni.model;

public class RuntimeOptions {
    private Integer gpioSlowdown;
    private Integer daemon;
    private Integer dropPrivileges;
    private Boolean doGpioInit;
    private String dropPrivUser;
    private String dropPrivGroup;

    public int getGpioSlowdown() {
        return gpioSlowdown;
    }

    public RuntimeOptions gpioSlowdown(int gpioSlowdown) {
        this.gpioSlowdown = gpioSlowdown;
        return this;
    }

    public int getDaemon() {
        return daemon;
    }

    public RuntimeOptions daemon(int daemon) {
        this.daemon = daemon;
        return this;
    }

    public int getDropPrivileges() {
        return dropPrivileges;
    }

    public RuntimeOptions dropPrivileges(int dropPrivileges) {
        this.dropPrivileges = dropPrivileges;
        return this;
    }

    public boolean isDoGpioInit() {
        return doGpioInit;
    }

    public RuntimeOptions doGpioInit(boolean doGpioInit) {
        this.doGpioInit = doGpioInit;
        return this;
    }

    public String getDropPrivUser() {
        return dropPrivUser;
    }

    public RuntimeOptions dropPrivUser(String dropPrivUser) {
        this.dropPrivUser = dropPrivUser;
        return this;
    }

    public String getDropPrivGroup() {
        return dropPrivGroup;
    }

    public RuntimeOptions dropPrivGroup(String dropPrivGroup) {
        this.dropPrivGroup = dropPrivGroup;
        return this;
    }
}
