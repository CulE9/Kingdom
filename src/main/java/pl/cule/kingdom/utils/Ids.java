package pl.cule.kingdom.utils;

import java.util.Set;

public class Ids {

    public static int generateNewId(Set<Integer> existingIds) {
        if (existingIds.isEmpty()) {
            return 0;
        } else {
            Integer integer = existingIds.stream().max(Integer::compareTo).get();
            return integer + 1;
        }
    }
}
