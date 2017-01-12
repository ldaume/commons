package software.reinvent.log;

import com.google.inject.AbstractModule;

import static com.google.inject.matcher.Matchers.any;

/**
 * Created by lenny on 12.01.17.
 */
public class LoggerModule extends AbstractModule {

    @Override
    protected void configure() {
        bindListener(any(), new Slf4jTypeListener());
    }
}

