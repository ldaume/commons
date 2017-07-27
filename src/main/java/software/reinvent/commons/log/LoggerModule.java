package software.reinvent.commons.log;

import com.google.inject.AbstractModule;

import static com.google.inject.matcher.Matchers.any;

/**
 * Created on 11.01.2017.
 *
 * @author <a href="mailto:lenny@reinvent.software">Leonard Daume</a>
 */
public class LoggerModule extends AbstractModule {

    @Override
    protected void configure() {
        bindListener(any(), new Slf4jTypeListener());
    }
}

