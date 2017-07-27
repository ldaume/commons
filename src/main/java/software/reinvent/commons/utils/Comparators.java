package software.reinvent.commons.utils;

import com.google.common.base.Splitter;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.Comparator;
import java.util.List;

import static org.apache.commons.collections4.CollectionUtils.size;
import static org.apache.commons.lang3.StringUtils.defaultString;
import static org.apache.commons.lang3.StringUtils.removeStartIgnoreCase;
import static org.apache.commons.lang3.StringUtils.substringBefore;

/**
 * Some reusable {@link Comparator}s.
 *
 * Created by lenny on 04.04.17.
 */
public class Comparators {
    private static final String WHITESPACE = " ";

    private Comparators() {
        // Just a util class with static access.
    }

    /**
     * Null friendly version string comparator for dot-separated tuple like v2.1.10 or 0.1.12.5
     *
     * @return the comparator.
     */
    public static Comparator<String> versionStringComparator() {
        return (o1, o2) -> {
            int result = 0;
            final List<String> v1 = Splitter.on(".")
                                            .trimResults()
                                            .omitEmptyStrings()
                                            .splitToList(substringBefore(removeStartIgnoreCase(defaultString(o1), "v"),
                                                                         WHITESPACE));
            final List<String> v2 = Splitter.on(".")
                                            .trimResults()
                                            .omitEmptyStrings()
                                            .splitToList(substringBefore(removeStartIgnoreCase(defaultString(o2), "v"),
                                                                         WHITESPACE));
            for (int i = 0; i < v1.size(); i++) {
                if (v2.size() > i) {
                    final String versionTuple1 = v1.get(i);
                    final String versionTuple2 = v2.get(i);
                    result = NumberUtils.compare(NumberUtils.toInt(versionTuple1), NumberUtils.toInt(versionTuple2));
                    if (result != 0) {
                        break;
                    }
                }
            }
            if (result == 0) {
                result = NumberUtils.compare(size(v1), size(v2));
            }
            return result;
        };
    }

}
