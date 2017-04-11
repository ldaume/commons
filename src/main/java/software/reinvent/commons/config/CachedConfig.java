package software.reinvent.commons.config;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigList;
import com.typesafe.config.ConfigMemorySize;
import com.typesafe.config.ConfigMergeable;
import com.typesafe.config.ConfigObject;
import com.typesafe.config.ConfigOrigin;
import com.typesafe.config.ConfigResolveOptions;
import com.typesafe.config.ConfigValue;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created on 11.04.17.
 *
 * @author <a href="mailto:lenny@reinvent.software">Leonard Daume</a>
 */
public class CachedConfig implements Config {
    private final Config config;
    private final LoadingCache<String, String> stringCache = CacheBuilder.newBuilder()
                                                                         .build(
                                                                                 new CacheLoader<String, String>() {
                                                                                     public String load(String key) throws Exception {
                                                                                         return config.getString(key);
                                                                                     }
                                                                                 });
    private final LoadingCache<String, List<String>> stringListCache = CacheBuilder.newBuilder()
                                                                                   .build(
                                                                                           new CacheLoader<String, List<String>>() {
                                                                                               public List<String> load(String key) throws Exception {
                                                                                                   return config.getStringList(key);
                                                                                               }
                                                                                           });
    private final LoadingCache<String, Boolean> hasPathCache = CacheBuilder.newBuilder()
                                                                           .build(
                                                                                   new CacheLoader<String, Boolean>() {
                                                                                       public Boolean load(String key) throws Exception {
                                                                                           return config.hasPath(key);
                                                                                       }
                                                                                   });
    private final LoadingCache<String, Boolean> booleanCache = CacheBuilder.newBuilder()
                                                                           .build(
                                                                                   new CacheLoader<String, Boolean>() {
                                                                                       public Boolean load(String key) throws Exception {
                                                                                           return config.getBoolean(key);
                                                                                       }
                                                                                   });
    private final LoadingCache<String, Integer> intCache = CacheBuilder.newBuilder()
                                                                       .build(
                                                                               new CacheLoader<String, Integer>() {
                                                                                   public Integer load(String key) throws Exception {
                                                                                       return config.getInt(key);
                                                                                   }
                                                                               });
    private final LoadingCache<String, Long> longCache = CacheBuilder.newBuilder()
                                                                     .build(
                                                                             new CacheLoader<String, Long>() {
                                                                                 public Long load(String key) throws Exception {
                                                                                     return config.getLong(key);
                                                                                 }
                                                                             });

    public CachedConfig(Config config) {
        this.config = config;
    }

    public String getString(String path) {
        try {
            return stringCache.get(path);
        } catch (ExecutionException e) {
            return config.getString(path);
        }
    }

    @Override
    public <T extends Enum<T>> T getEnum(Class<T> enumClass, String path) {
        return config.getEnum(enumClass, path);
    }

    @Override
    public ConfigObject getObject(String path) {
        return config.getObject(path);
    }

    @Override
    public Config getConfig(String path) {
        return config.getConfig(path);
    }

    @Override
    public Object getAnyRef(String path) {
        return config.getAnyRef(path);
    }

    @Override
    public ConfigValue getValue(String path) {
        return config.getValue(path);
    }

    @Override
    public Long getBytes(String path) {
        return config.getBytes(path);
    }

    @Override
    public ConfigMemorySize getMemorySize(String path) {
        return config.getMemorySize(path);
    }

    @Override
    public Long getMilliseconds(String path) {
        return config.getMilliseconds(path);
    }

    @Override
    public Long getNanoseconds(String path) {
        return config.getNanoseconds(path);
    }

    @Override
    public long getDuration(String path, TimeUnit unit) {
        return config.getDuration(path, unit);
    }

    @Override
    public Duration getDuration(String path) {
        return config.getDuration(path);
    }

    @Override
    public ConfigList getList(String path) {
        return config.getList(path);
    }

    @Override
    public List<Boolean> getBooleanList(String path) {
        return config.getBooleanList(path);
    }

    @Override
    public List<Number> getNumberList(String path) {
        return config.getNumberList(path);
    }

    @Override
    public List<Integer> getIntList(String path) {
        return config.getIntList(path);
    }

    @Override
    public List<Long> getLongList(String path) {
        return config.getLongList(path);
    }

    @Override
    public List<Double> getDoubleList(String path) {
        return config.getDoubleList(path);
    }

    @Override
    public List<String> getStringList(String path) {
        return config.getStringList(path);
    }

    @Override
    public <T extends Enum<T>> List<T> getEnumList(Class<T> enumClass, String path) {
        return config.getEnumList(enumClass, path);
    }

    @Override
    public List<? extends ConfigObject> getObjectList(String path) {
        return config.getObjectList(path);
    }

    @Override
    public List<? extends Config> getConfigList(String path) {
        return config.getConfigList(path);
    }

    @Override
    public List<? extends Object> getAnyRefList(String path) {
        return config.getAnyRefList(path);
    }

    @Override
    public List<Long> getBytesList(String path) {
        return config.getBytesList(path);
    }

    @Override
    public List<ConfigMemorySize> getMemorySizeList(String path) {
        return config.getMemorySizeList(path);
    }

    @Override
    public List<Long> getMillisecondsList(String path) {
        return config.getMillisecondsList(path);
    }

    @Override
    public List<Long> getNanosecondsList(String path) {
        return config.getNanosecondsList(path);
    }

    @Override
    public List<Long> getDurationList(String path, TimeUnit unit) {
        return config.getDurationList(path, unit);
    }

    @Override
    public List<Duration> getDurationList(String path) {
        return config.getDurationList(path);
    }

    @Override
    public Config withOnlyPath(String path) {
        return config.withOnlyPath(path);
    }

    @Override
    public Config withoutPath(String path) {
        return config.withoutPath(path);
    }

    @Override
    public Config atPath(String path) {
        return config.atPath(path);
    }

    @Override
    public Config atKey(String key) {
        return config.atKey(key);
    }

    @Override
    public Config withValue(String path, ConfigValue value) {
        return config.withValue(path, value);
    }

    @Override
    public ConfigObject root() {
        return config.root();
    }

    @Override
    public ConfigOrigin origin() {
        return config.origin();
    }

    @Override
    public Config withFallback(ConfigMergeable other) {
        return config.withFallback(other);
    }

    @Override
    public Config resolve() {
        return config.resolve();
    }

    @Override
    public Config resolve(ConfigResolveOptions options) {
        return config.resolve(options);
    }

    @Override
    public boolean isResolved() {
        return config.isResolved();
    }

    @Override
    public Config resolveWith(Config source) {
        return config.resolveWith(source);
    }

    @Override
    public Config resolveWith(Config source, ConfigResolveOptions options) {
        return config.resolveWith(source, options);
    }

    @Override
    public void checkValid(Config reference, String... restrictToPaths) {
        config.checkValid(reference, restrictToPaths);
    }

    public boolean hasPath(String path) {
        try {
            return hasPathCache.get(path);
        } catch (ExecutionException e) {
            return config.hasPath(path);
        }
    }

    @Override
    public boolean hasPathOrNull(String path) {
        return config.hasPathOrNull(path);
    }

    @Override
    public boolean isEmpty() {
        return config.isEmpty();
    }

    @Override
    public Set<Map.Entry<String, ConfigValue>> entrySet() {
        return config.entrySet();
    }

    @Override
    public boolean getIsNull(String path) {
        return config.getIsNull(path);
    }

    public boolean getBoolean(String path) {
        try {
            return booleanCache.get(path);
        } catch (ExecutionException e) {
            return config.getBoolean(path);
        }
    }

    @Override
    public Number getNumber(String path) {
        return config.getNumber(path);
    }

    public int getInt(String path) {
        try {
            return intCache.get(path);
        } catch (ExecutionException e) {
            return config.getInt(path);
        }
    }

    public long getLong(String path) {
        try {
            return longCache.get(path);
        } catch (ExecutionException e) {
            return config.getLong(path);
        }
    }

    @Override
    public double getDouble(String path) {
        return config.getDouble(path);
    }
}
