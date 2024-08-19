#include "runtime_options_converter.h"

RuntimeOptionsConverter::RuntimeOptionsConverter(JNIEnv *env) {
    this->env = env;
}

rgb_matrix::RuntimeOptions RuntimeOptionsConverter::convert(jobject runtimeOptions) {
    FieldReader runtimeOptionsReader(env, runtimeOptions);
    rgb_matrix::RuntimeOptions runtime_defaults;

    std::optional<int> gpioSlowdownOpt = runtimeOptionsReader.readInt("gpioSlowdown");
    if (gpioSlowdownOpt.has_value()) {
        runtime_defaults.gpio_slowdown = gpioSlowdownOpt.value();
    }

    std::optional<int> daemonOpt = runtimeOptionsReader.readInt("daemon");
    if (daemonOpt.has_value()) {
        runtime_defaults.daemon = daemonOpt.value();
    }

    std::optional<int> dropPrivilegesOpt = runtimeOptionsReader.readInt("dropPrivileges");
    if (dropPrivilegesOpt.has_value()) {
        runtime_defaults.drop_privileges = dropPrivilegesOpt.value();
    }

    std::optional<bool> doGpioInitOpt = runtimeOptionsReader.readBoolean("doGpioInit");
    if (doGpioInitOpt.has_value()) {
        runtime_defaults.do_gpio_init = doGpioInitOpt.value();
    }

    std::optional<const char*> dropPrivUserOpt = runtimeOptionsReader.readString("dropPrivUser");
    if (dropPrivUserOpt.has_value()) {
        runtime_defaults.drop_priv_user = dropPrivUserOpt.value();
    }

    std::optional<const char*> dropPrivGroupOpt = runtimeOptionsReader.readString("dropPrivGroup");
    if (dropPrivGroupOpt.has_value()) {
        runtime_defaults.drop_priv_group = dropPrivGroupOpt.value();
    }

    return runtime_defaults;
}