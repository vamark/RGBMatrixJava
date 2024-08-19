package io.vagvolgyi.rgbmatrix.jni.model;

public class Options {
    private String hardwareMapping;
    private Integer rows;
    private Integer cols;
    private Integer chainLength;
    private Integer parallel;
    private Integer pwmBits;
    private Integer pwmLsbNanoseconds;
    private Integer pwmDitherBits;
    private Integer brightness;
    private Integer scanMode;
    private Integer rowAddressType;
    private Integer multiplexing;
    private Boolean disableHardwarePulsing;
    private Boolean showRefreshRate;
    private Boolean inverseColors;
    private String ledRgbSequence;
    private String pixelMapperConfig;
    private String panelType;
    private Integer limitRefreshRateHz;

    public String getHardwareMapping() {
        return hardwareMapping;
    }

    public Options hardwareMapping(String hardwareMapping) {
        this.hardwareMapping = hardwareMapping;
        return this;
    }

    public Integer getRows() {
        return rows;
    }

    public Options rows(Integer rows) {
        this.rows = rows;
        return this;
    }

    public Integer getCols() {
        return cols;
    }

    public Options cols(Integer cols) {
        this.cols = cols;
        return this;
    }

    public Integer getChainLength() {
        return chainLength;
    }

    public Options chainLength(Integer chainLength) {
        this.chainLength = chainLength;
        return this;
    }

    public Integer getParallel() {
        return parallel;
    }

    public Options parallel(Integer parallel) {
        this.parallel = parallel;
        return this;
    }

    public Integer getPwmBits() {
        return pwmBits;
    }

    public Options pwmBits(Integer pwmBits) {
        this.pwmBits = pwmBits;
        return this;
    }

    public Integer getPwmLsbNanoseconds() {
        return pwmLsbNanoseconds;
    }

    public Options pwmLsbNanoseconds(Integer pwmLsbNanoseconds) {
        this.pwmLsbNanoseconds = pwmLsbNanoseconds;
        return this;
    }

    public Integer getPwmDitherBits() {
        return pwmDitherBits;
    }

    public Options pwmDitherBits(Integer pwmDitherBits) {
        this.pwmDitherBits = pwmDitherBits;
        return this;
    }

    public Integer getBrightness() {
        return brightness;
    }

    public Options brightness(Integer brightness) {
        this.brightness = brightness;
        return this;
    }

    public Integer getScanMode() {
        return scanMode;
    }

    public Options scanMode(Integer scanMode) {
        this.scanMode = scanMode;
        return this;
    }

    public Integer getRowAddressType() {
        return rowAddressType;
    }

    public Options rowAddressType(Integer rowAddressType) {
        this.rowAddressType = rowAddressType;
        return this;
    }

    public Integer getMultiplexing() {
        return multiplexing;
    }

    public Options multiplexing(Integer multiplexing) {
        this.multiplexing = multiplexing;
        return this;
    }

    public Boolean getDisableHardwarePulsing() {
        return disableHardwarePulsing;
    }

    public Options disableHardwarePulsing(Boolean disableHardwarePulsing) {
        this.disableHardwarePulsing = disableHardwarePulsing;
        return this;
    }

    public Boolean getShowRefreshRate() {
        return showRefreshRate;
    }

    public Options showRefreshRate(Boolean showRefreshRate) {
        this.showRefreshRate = showRefreshRate;
        return this;
    }

    public Boolean getInverseColors() {
        return inverseColors;
    }

    public Options inverseColors(Boolean inverseColors) {
        this.inverseColors = inverseColors;
        return this;
    }

    public String getLedRgbSequence() {
        return ledRgbSequence;
    }

    public Options ledRgbSequence(String ledRgbSequence) {
        this.ledRgbSequence = ledRgbSequence;
        return this;
    }

    public String getPixelMapperConfig() {
        return pixelMapperConfig;
    }

    public Options pixelMapperConfig(String pixelMapperConfig) {
        this.pixelMapperConfig = pixelMapperConfig;
        return this;
    }

    public String getPanelType() {
        return panelType;
    }

    public Options panelType(String panelType) {
        this.panelType = panelType;
        return this;
    }

    public Integer getLimitRefreshRateHz() {
        return limitRefreshRateHz;
    }

    public Options limitRefreshRateHz(Integer limitRefreshRateHz) {
        this.limitRefreshRateHz = limitRefreshRateHz;
        return this;
    }
}
