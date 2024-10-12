package dev.vagvolgyi.rgbmatrix.wrapper;

import dev.vagvolgyi.rgbmatrix.jni.model.Options;
import dev.vagvolgyi.rgbmatrix.jni.model.RuntimeOptions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class MatrixConfigParserTest {
    @Test
    void parseOptions_emptyProperties_emptyOptionsReturned() {
        MatrixConfigParser parser = new MatrixConfigParser(new Properties());

        Options options = parser.parseOptions();

        assertNull(options.getHardwareMapping());
        assertNull(options.getRows());
        assertNull(options.getCols());
        assertNull(options.getChainLength());
        assertNull(options.getParallel());
        assertNull(options.getPwmBits());
        assertNull(options.getPwmLsbNanoseconds());
        assertNull(options.getPwmDitherBits());
        assertNull(options.getBrightness());
        assertNull(options.getScanMode());
        assertNull(options.getRowAddressType());
        assertNull(options.getMultiplexing());
        assertNull(options.getDisableHardwarePulsing());
        assertNull(options.getShowRefreshRate());
        assertNull(options.getInverseColors());
        assertNull(options.getLedRgbSequence());
        assertNull(options.getPixelMapperConfig());
        assertNull(options.getPanelType());
        assertNull(options.getLimitRefreshRateHz());
    }

    @Test
    void parseOptions_allPropertiesSet_allOptionsReturned() {
        MatrixConfigParser parser = new MatrixConfigParser(createOptionsProperties());

        Options options = parser.parseOptions();

        assertEquals("adafruit-hat-pwm", options.getHardwareMapping());
        assertEquals(64, options.getRows());
        assertEquals(64, options.getCols());
        assertEquals(1, options.getChainLength());
        assertEquals(1, options.getParallel());
        assertEquals(11, options.getPwmBits());
        assertEquals(130, options.getPwmLsbNanoseconds());
        assertEquals(0, options.getPwmDitherBits());
        assertEquals(100, options.getBrightness());
        assertEquals(0, options.getScanMode());
        assertEquals(0, options.getRowAddressType());
        assertEquals(0, options.getMultiplexing());
        assertTrue(options.getDisableHardwarePulsing());
        assertFalse(options.getShowRefreshRate());
        assertTrue(options.getInverseColors());
        assertEquals("BGR", options.getLedRgbSequence());
        assertEquals("Rotate:90", options.getPixelMapperConfig());
        assertEquals("FM6126A", options.getPanelType());
        assertEquals(60, options.getLimitRefreshRateHz());
    }

    @ParameterizedTest
    @ValueSource(strings = {"rows", "cols", "chain.length", "parallel", "pwm.bits", "pwm.lsb.nanoseconds", "pwm.dither.bits",
                            "brightness", "scan.mode", "row.address.type", "multiplexing", "limit.refresh.rate.hz"})
    void parseOptions_wrongTypeForIntProperty_exceptionThrown(String property) {
        Properties properties = createOptionsProperties();
        properties.setProperty(property, "not a number");
        MatrixConfigParser parser = new MatrixConfigParser(properties);

        assertThrows(IllegalArgumentException.class, parser::parseOptions);
    }

    @ParameterizedTest
    @ValueSource(strings = {"disable.hardware.pulsing", "show.refresh.rate", "inverse.colors"})
    void parseOptions_wrongTypeForBooleanProperty_exceptionThrown(String property) {
        Properties properties = createOptionsProperties();
        properties.setProperty(property, "not a boolean");
        MatrixConfigParser parser = new MatrixConfigParser(properties);

        assertThrows(IllegalArgumentException.class, parser::parseOptions);
    }

    @Test
    void parseRuntimeOptions_emptyProperties_emptyRuntimeOptionsReturned() {
        MatrixConfigParser parser = new MatrixConfigParser(new Properties());

        RuntimeOptions runtimeOptions = parser.parseRuntimeOptions();

        assertNull(runtimeOptions.getGpioSlowdown());
        assertNull(runtimeOptions.getDaemon());
        assertNull(runtimeOptions.getDropPrivileges());
        assertNull(runtimeOptions.isDoGpioInit());
        assertNull(runtimeOptions.getDropPrivUser());
        assertNull(runtimeOptions.getDropPrivGroup());
    }

    @Test
    void parseRuntimeOptions_allPropertiesSet_allRuntimeOptionsReturned() {
        MatrixConfigParser parser = new MatrixConfigParser(createRuntimeOptionsProperties());

        RuntimeOptions runtimeOptions = parser.parseRuntimeOptions();

        assertEquals(1, runtimeOptions.getGpioSlowdown());
        assertEquals(1, runtimeOptions.getDaemon());
        assertEquals(1, runtimeOptions.getDropPrivileges());
        assertTrue(runtimeOptions.isDoGpioInit());
        assertEquals("user", runtimeOptions.getDropPrivUser());
        assertEquals("group", runtimeOptions.getDropPrivGroup());
    }

    @ParameterizedTest
    @ValueSource(strings = {"gpio.slowdown", "daemon", "drop.privileges", "do.gpio.init"})
    void parseRuntimeOptions_wrongTypeForIntProperty_exceptionThrown(String property) {
        Properties properties = createRuntimeOptionsProperties();
        properties.setProperty(property, "not a number");
        MatrixConfigParser parser = new MatrixConfigParser(properties);

        assertThrows(IllegalArgumentException.class, parser::parseRuntimeOptions);
    }

    @Test
    void parseRuntimeOptions_wrongTypeForBooleanProperty_exceptionThrown() {
        Properties properties = createRuntimeOptionsProperties();
        properties.setProperty("do.gpio.init", "not a boolean");
        MatrixConfigParser parser = new MatrixConfigParser(properties);

        assertThrows(IllegalArgumentException.class, parser::parseRuntimeOptions);
    }

    private Properties createRuntimeOptionsProperties() {
        Properties properties = new Properties();
        properties.setProperty("gpio.slowdown", "1");
        properties.setProperty("daemon", "1");
        properties.setProperty("drop.privileges", "1");
        properties.setProperty("do.gpio.init", "true");
        properties.setProperty("drop.priv.user", "user");
        properties.setProperty("drop.priv.group", "group");
        return properties;
    }

    private static Properties createOptionsProperties() {
        Properties properties = new Properties();
        properties.setProperty("hardware.mapping", "adafruit-hat-pwm");
        properties.setProperty("rows", "64");
        properties.setProperty("cols", "64");
        properties.setProperty("chain.length", "1");
        properties.setProperty("parallel", "1");
        properties.setProperty("pwm.bits", "11");
        properties.setProperty("pwm.lsb.nanoseconds", "130");
        properties.setProperty("pwm.dither.bits", "0");
        properties.setProperty("brightness", "100");
        properties.setProperty("scan.mode", "0");
        properties.setProperty("row.address.type", "0");
        properties.setProperty("multiplexing", "0");
        properties.setProperty("disable.hardware.pulsing", "true");
        properties.setProperty("show.refresh.rate", "false");
        properties.setProperty("inverse.colors", "true");
        properties.setProperty("led.rgb.sequence", "BGR");
        properties.setProperty("pixel.mapper.config", "Rotate:90");
        properties.setProperty("panel.type", "FM6126A");
        properties.setProperty("limit.refresh.rate.hz", "60");
        return properties;
    }
}