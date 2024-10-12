package dev.vagvolgyi.rgbmatrix.wrapper;

import dev.vagvolgyi.rgbmatrix.jni.model.Options;
import dev.vagvolgyi.rgbmatrix.jni.model.RuntimeOptions;

import java.util.Properties;

import static java.util.Optional.ofNullable;

public class MatrixConfigParser {
    private final Properties properties;

    public MatrixConfigParser(Properties properties) {
        this.properties = properties;
    }

    public Options parseOptions() {
        return new Options().hardwareMapping(parseString("hardware.mapping"))
                            .rows(parseInteger("rows"))
                            .cols(parseInteger("cols"))
                            .chainLength(parseInteger("chain.length"))
                            .parallel(parseInteger("parallel"))
                            .pwmBits(parseInteger("pwm.bits"))
                            .pwmLsbNanoseconds(parseInteger("pwm.lsb.nanoseconds"))
                            .pwmDitherBits(parseInteger("pwm.dither.bits"))
                            .brightness(parseInteger("brightness"))
                            .scanMode(parseInteger("scan.mode"))
                            .rowAddressType(parseInteger("row.address.type"))
                            .multiplexing(parseInteger("multiplexing"))
                            .disableHardwarePulsing(parseBoolean("disable.hardware.pulsing"))
                            .showRefreshRate(parseBoolean("show.refresh.rate"))
                            .inverseColors(parseBoolean("inverse.colors"))
                            .ledRgbSequence(parseString("led.rgb.sequence"))
                            .pixelMapperConfig(parseString("pixel.mapper.config"))
                            .panelType(parseString("panel.type"))
                            .limitRefreshRateHz(parseInteger("limit.refresh.rate.hz"));
    }

    public RuntimeOptions parseRuntimeOptions() {
        return new RuntimeOptions().gpioSlowdown(parseInteger("gpio.slowdown"))
                                   .daemon(parseInteger("daemon"))
                                   .dropPrivileges(parseInteger("drop.privileges"))
                                   .doGpioInit(parseBoolean("do.gpio.init"))
                                   .dropPrivUser(parseString("drop.priv.user"))
                                   .dropPrivGroup(parseString("drop.priv.group"));
    }

    private String parseString(String key) {
        return properties.getProperty(key);
    }

    private Integer parseInteger(String key) {
        return ofNullable(properties.getProperty(key)).map(Integer::parseInt).orElse(null);
    }

    private Boolean parseBoolean(String key) {
        return ofNullable(properties.getProperty(key)).map(value -> {
            if(value.equalsIgnoreCase("true")) {
                return true;
            }
            else if(value.equalsIgnoreCase("false")) {
                return false;
            }
            throw new IllegalArgumentException("Invalid boolean value: " + value);
        }).orElse(null);
    }
}
