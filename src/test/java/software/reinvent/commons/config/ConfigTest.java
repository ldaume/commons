package software.reinvent.commons.config;

import com.typesafe.config.Config;
import org.junit.Before;
import org.junit.Test;
import software.reinvent.test.ConfigTestUtils;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by lenny on 11.04.17.
 */
public class ConfigTest {

    private CachedConfig cachedConfig;
    private Config config;

    @Before
    public void setUp() throws Exception {
        this.config = ConfigLoader.load();
        cachedConfig = ConfigLoader.loadCached();
    }

    @Test
    public void load() throws Exception {
        assertThat(config).isNotNull();
    }

    @Test
    public void loadCached() throws Exception {
        assertThat(cachedConfig).isNotNull();
    }

    @Test
    public void manipulateConfig() throws Exception {
        final String uncachedPath = "config.uncached.test";
        final String expectedValue = "new value";
        assertThat(config.getString(uncachedPath)).isEqualTo("hello uncached config");
        ConfigTestUtils.addConfigEntry(uncachedPath, expectedValue, config);
        assertThat(config.getString(uncachedPath)).isEqualTo(expectedValue);


        final String cachedPath = "config.cached.test";
        assertThat(cachedConfig.getString(cachedPath)).isEqualTo("hello cached config");
        ConfigTestUtils.addConfigEntry(cachedPath, expectedValue, config);
        assertThat(config.getString(cachedPath)).isEqualTo(expectedValue);


    }
}