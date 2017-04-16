package software.reinvent.test;

import com.google.common.collect.ImmutableMap;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigObject;
import com.typesafe.config.ConfigValue;
import software.reinvent.commons.config.CachedConfig;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Created on 16.04.2017.
 *
 * @author <a href="mailto:lenny@reinvent.software">Leonard Daume</a>
 */
public class ConfigTestUtils {

    private ConfigTestUtils() {
    }

    /**
     * Adds a new value to a specific config. If the value is null, the path will be removed.
     *
     * @param path         the path as key
     * @param value        the value to set or unset
     * @param cachedConfig the config to manipulate
     */
    public static void addConfigEntry(String path,
                                      Object value,
                                      CachedConfig cachedConfig) throws NoSuchFieldException, IllegalAccessException {
        addConfigEntry(path, value, cachedConfig.getConfig());
    }

    /**
     * Adds a new value to a specific config. If the value is null, the path will be removed.
     *
     * @param path               the path as key
     * @param value              the value to set or unset
     * @param configToManipulate the config to manipulate
     */
    public static void addConfigEntry(String path,
                                      Object value,
                                      Config configToManipulate) throws NoSuchFieldException, IllegalAccessException {
        if (value == null) {
            replaceConfigEntries(configToManipulate, configToManipulate.withoutPath(path));
        } else {
            addConfigEntry(ImmutableMap.of(path, value), configToManipulate);
        }
    }

    public static void addConfigEntry(Map<String, Object> valueMapToAdd,
                                      Config configToManipulate) throws NoSuchFieldException, IllegalAccessException {
        replaceConfigEntries(configToManipulate, ConfigFactory.parseMap(valueMapToAdd).withFallback(configToManipulate));
    }

    private static void replaceConfigEntries(Config configToManipulate,
                                             Config newConfig) throws NoSuchFieldException, IllegalAccessException {
        getConfigValueMap(configToManipulate).clear();
        getConfigValueMap(configToManipulate).putAll(getConfigValueMap(newConfig));
    }

    private static Map<String, ConfigValue> getConfigValueMap(Config config) throws NoSuchFieldException, IllegalAccessException {
        final ConfigObject root = config.root();
        Field field = config.root().getClass().getDeclaredField("value");
        field.setAccessible(true);
        return (Map<String, ConfigValue>) field.get(root);
    }
}