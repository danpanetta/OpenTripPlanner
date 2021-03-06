package org.opentripplanner.model.impl;

import org.junit.Test;
import org.opentripplanner.model.FeedInfo;
import org.opentripplanner.model.TransitEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.opentripplanner.model.impl.GenerateMissingIds.generateNoneExistentIds;

public class GenerateMissingIdsTest {
    private static final String ID_1 = "1";
    private static final String ID_4 = "4";
    private static final String ID_5 = "5";
    private static final String ID_6 = "6";

    @Test public void testGenerateNoneExistentIds() {
        List<? extends TransitEntity<String>> list;

        // An empty list should not cause any trouble (Exception)
        generateNoneExistentIds(Collections.<FeedInfo>emptyList());

        // Generate id for one value
        list = singletonList(newEntity());
        generateNoneExistentIds(list);
        assertEquals(ID_1, list.get(0).getId());

        // Given two entities with no id and max Íd = 4
        list = Arrays.asList(
                newEntity(),
                newEntity(ID_4),
                newEntity()
        );
        // When
        generateNoneExistentIds(list);
        // Then expect
        // First new id to be: maxId + 1 = 4+1 = 5
        assertEquals(ID_5, id(list, 0));
        // Existing to still be 4
        assertEquals(ID_4, id(list, 1));
        // Next to be 6
        assertEquals(ID_6, id(list, 2));
    }


    private static TransitEntity<String> newEntity() {
        return newEntity(null);
    }

    private static TransitEntity<String> newEntity(String id) {
        FeedInfo e = new FeedInfo();
        e.setId(id);
        return e;
    }

    private static String id(List<? extends TransitEntity<String>> list, int index) {
        return list.get(index).getId();
    }
}