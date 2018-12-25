package pl.matadini.sysmusic.server.common.util;

import java.util.List;
import java.util.Objects;

public class ListUtil {

    public static boolean isNullOrEmpty(List<?> list) {
        return Objects.isNull(list) || list.isEmpty();
    }

    private ListUtil() {

    }
}
