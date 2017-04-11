package software.reinvent.commons.config;

import com.google.inject.Provider;

/**
 * Created on 11.01.2017.
 *
 * @author <a href="mailto:lenny@reinvent.software">Leonard Daume</a>
 */
public class CachedConfigProvider implements Provider<CachedConfig> {
    @Override
    public CachedConfig get() {
        return ConfigLoader.loadCached();
    }
}

