#include "options_converter.h"

OptionsConverter::OptionsConverter(JNIEnv *env) {
    this->env = env;
}

rgb_matrix::RGBMatrix::Options OptionsConverter::convert(jobject options) {
    FieldReader optionsReader(env, options);
    rgb_matrix::RGBMatrix::Options opts;

    std::optional<std::string> hardwareMappingOpt = optionsReader.readString("hardwareMapping");
    if (hardwareMappingOpt.has_value()) {
        hardwareMapping = hardwareMappingOpt.value();
        opts.hardware_mapping = hardwareMapping.c_str();
    }

    std::optional<int> rowsOpt = optionsReader.readInt("rows");
    if (rowsOpt.has_value()) {
        opts.rows = rowsOpt.value();
    }

    std::optional<int> colsOpt = optionsReader.readInt("cols");
    if (colsOpt.has_value()) {
        opts.cols = colsOpt.value();
    }

    std::optional<int> chainLengthOpt = optionsReader.readInt("chainLength");
    if (chainLengthOpt.has_value()) {
        opts.chain_length = chainLengthOpt.value();
    }

    std::optional<int> parallelOpt = optionsReader.readInt("parallel");
    if (parallelOpt.has_value()) {
        opts.parallel = parallelOpt.value();
    }

    std::optional<int> pwmBitsOpt = optionsReader.readInt("pwmBits");
    if (pwmBitsOpt.has_value()) {
        opts.pwm_bits = pwmBitsOpt.value();
    }

    std::optional<int> pwmLsbNanosecondsOpt = optionsReader.readInt("pwmLsbNanoseconds");
    if (pwmLsbNanosecondsOpt.has_value()) {
        opts.pwm_lsb_nanoseconds = pwmLsbNanosecondsOpt.value();
    }

    std::optional<int> pwmDitherBitsOpt = optionsReader.readInt("pwmDitherBits");
    if (pwmDitherBitsOpt.has_value()) {
        opts.pwm_dither_bits = pwmDitherBitsOpt.value();
    }

    std::optional<int> brightnessOpt = optionsReader.readInt("brightness");
    if (brightnessOpt.has_value()) {
        opts.brightness = brightnessOpt.value();
    }

    std::optional<int> scanModeOpt = optionsReader.readInt("scanMode");
    if (scanModeOpt.has_value()) {
        opts.scan_mode = scanModeOpt.value();
    }

    std::optional<int> rowAddressTypeOpt = optionsReader.readInt("rowAddressType");
    if (rowAddressTypeOpt.has_value()) {
        opts.row_address_type = rowAddressTypeOpt.value();
    }

    std::optional<int> multiplexingOpt = optionsReader.readInt("multiplexing");
    if (multiplexingOpt.has_value()) {
        opts.multiplexing = multiplexingOpt.value();
    }

    std::optional<bool> disableHardwarePulsingOpt = optionsReader.readBoolean("disableHardwarePulsing");
    if (disableHardwarePulsingOpt.has_value()) {
        opts.disable_hardware_pulsing = disableHardwarePulsingOpt.value();
    }

    std::optional<bool> showRefreshRateOpt = optionsReader.readBoolean("showRefreshRate");
    if (showRefreshRateOpt.has_value()) {
        opts.show_refresh_rate = showRefreshRateOpt.value();
    }

    std::optional<bool> inverseColorsOpt = optionsReader.readBoolean("inverseColors");
    if (inverseColorsOpt.has_value()) {
        opts.inverse_colors = inverseColorsOpt.value();
    }

    std::optional<std::string> ledRgbSequenceOpt = optionsReader.readString("ledRgbSequence");
    if (ledRgbSequenceOpt.has_value()) {
        ledRgbSequence = ledRgbSequenceOpt.value();
        opts.led_rgb_sequence = ledRgbSequence.c_str();
    }

    std::optional<std::string> pixelMapperConfigOpt = optionsReader.readString("pixelMapperConfig");
    if (pixelMapperConfigOpt.has_value()) {
        pixelMapperConfig = pixelMapperConfigOpt.value();
        opts.pixel_mapper_config = pixelMapperConfig.c_str();
    }

    std::optional<std::string> panelTypeOpt = optionsReader.readString("panelType");
    if (panelTypeOpt.has_value()) {
        panelType = panelTypeOpt.value();
        opts.panel_type = panelType.c_str();
    }

    std::optional<int> limitRefreshRateHzOpt = optionsReader.readInt("limitRefreshRateHz");
    if (limitRefreshRateHzOpt.has_value()) {
        opts.limit_refresh_rate_hz = limitRefreshRateHzOpt.value();
    }

    return opts;
}