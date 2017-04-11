package software.reinvent.commons.config;

import com.typesafe.config.Config;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by lenny on 11.04.17.
 */
public class ConfigLoaderTest {
    @Test
    public void load() throws Exception {
        final Config config = ConfigLoader.load();
        assertThat(config).isNotNull();
    }

    @Test
    public void loadCached() throws Exception {
        final CachedConfig config = ConfigLoader.loadCached();
        assertThat(config).isNotNull();
    }

}