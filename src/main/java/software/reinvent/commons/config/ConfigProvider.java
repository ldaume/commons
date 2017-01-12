package software.reinvent.commons.config;

import com.google.inject.Provider;

import com.typesafe.config.Config;

/**
 * Created on 11.01.2017.
 *
 * @author <a href="mailto:lenny@reinvent.software">Leonard Daume</a>
 */
public class ConfigProvider implements Provider<Config> {
    @Override
    public Config get() {
        return ConfigLoader.load();
    }
}

