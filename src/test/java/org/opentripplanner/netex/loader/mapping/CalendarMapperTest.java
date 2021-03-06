package org.opentripplanner.netex.loader.mapping;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.opentripplanner.graph_builder.DataImportIssueStore;
import org.opentripplanner.model.FeedScopedId;
import org.opentripplanner.model.calendar.ServiceCalendarDate;
import org.opentripplanner.netex.loader.util.HierarchicalMapById;
import org.opentripplanner.netex.loader.util.HierarchicalMultimap;
import org.opentripplanner.netex.support.DayTypeRefsToServiceIdAdapter;
import org.rutebanken.netex.model.DayTypeRefs_RelStructure;

import java.util.Collection;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.opentripplanner.model.calendar.ServiceCalendarDate.EXCEPTION_TYPE_REMOVE;


// TODO OTP2 - This needs to be fixed
public class CalendarMapperTest {
    private DayTypeRefsToServiceIdAdapter dayTypeRefs;
    private FeedScopedId serviceId = new FeedScopedId("a1", "A+B");
    private CalendarMapper subject;

    @Before
    public void setup() {
        subject = new CalendarMapper(
                MappingSupport.ID_FACTORY,
                new HierarchicalMultimap<>(),
                new HierarchicalMapById<>(),
                new HierarchicalMapById<>(),
                new DataImportIssueStore(false)
        );

        dayTypeRefs = DayTypeRefsToServiceIdAdapter.create(
                // TODO OTP2 add some data
                new DayTypeRefs_RelStructure()
        );
    }


    // Add one date exception when list is empty to ensure serviceId is not lost
    @Ignore
    @Test public void assertServiceDateWithTheCorrectServiceIDIsAddedForAnEmptySet() {
        Collection<ServiceCalendarDate> result = Collections.emptyList(); // subject.mapToCalendarDates(serviceId);

        assertEquals(1, result.size());
        assertEquals(EXCEPTION_TYPE_REMOVE, result.stream().findFirst().get().getExceptionType());
    }

    @Ignore
    @Test public void test() {

        Collection<ServiceCalendarDate> result = null; //subject.mapToCalendarDates(serviceId);

        assertEquals(1, result.size());
        assertEquals(EXCEPTION_TYPE_REMOVE, result.stream().findFirst().get().getExceptionType());
    }

}