package software.reinvent.utils;

import com.google.common.collect.ImmutableList;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * CUT of {@link Comparators}.
 *
 * Created by lenny on 04.04.17.
 */
public class ComparatorsTest {
    @Test
    public void versionStringComparator() throws Exception {
        final List<String> unordered = ImmutableList.of("v0.1 foo",
                                                        "2.15.2",
                                                        "v3.0.12.5",
                                                        "v0.0 bar",
                                                        "3.0.11.4",
                                                        "v3.0.1",
                                                        "2.15.1",
                                                        "2.15.0.1",
                                                        "v2.1.0.4.5");
        final List<String> ordered = ImmutableList.of("v0.0 bar",
                                                      "v0.1 foo",
                                                      "v2.1.0.4.5",
                                                      "2.15.0.1",
                                                      "2.15.1",
                                                      "2.15.2",
                                                      "v3.0.1",
                                                      "3.0.11.4",
                                                      "v3.0.12.5");

        assertThat(unordered.stream().sorted(Comparators.versionStringComparator()))
                .containsExactlyElementsOf(ordered);


    }

}