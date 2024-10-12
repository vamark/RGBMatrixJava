package dev.vagvolgyi.rgbmatrix.jni.model;

public class RuntimeOptions {
    private Integer gpioSlowdown;
    private Integer daemon;
    private Integer dropPrivileges;
    private Boolean doGpioInit;
    private String dropPrivUser;
    private String dropPrivGroup;

    public Integer getGpioSlowdown() {
        return gpioSlowdown;
    }

    public RuntimeOptions gpioSlowdown(Integer gpioSlowdown) {
        this.gpioSlowdown = gpioSlowdown;
        return this;
    }

    public Integer getDaemon() {
        return daemon;
    }

    public RuntimeOptions daemon(Integer daemon) {
        this.daemon = daemon;
        return this;
    }

    public Integer getDropPrivileges() {
        return dropPrivileges;
    }

    public RuntimeOptions dropPrivileges(Integer dropPrivileges) {
        this.dropPrivileges = dropPrivileges;
        return this;
    }

    public Boolean isDoGpioInit() {
        return doGpioInit;
    }

    public RuntimeOptions doGpioInit(Boolean doGpioInit) {
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
