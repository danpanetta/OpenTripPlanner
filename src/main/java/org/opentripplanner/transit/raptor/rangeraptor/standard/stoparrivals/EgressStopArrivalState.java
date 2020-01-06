package org.opentripplanner.transit.raptor.rangeraptor.standard.stoparrivals;

import org.opentripplanner.transit.raptor.api.transit.TransferLeg;
import org.opentripplanner.transit.raptor.api.transit.TripScheduleInfo;

import java.util.function.Consumer;

/**
 * The egress stop arrival state is responsible for sending arrival notifications.
 * This is used to update the destination arrivals.
 *
 * @param <T> The TripSchedule type defined by the user of the range raptor API.
 */
public final class EgressStopArrivalState<T extends TripScheduleInfo> extends StopArrivalState<T> {
    private final int round;
    private final TransferLeg egressLeg;
    private final Consumer<org.opentripplanner.transit.raptor.rangeraptor.standard.stoparrivals.EgressStopArrivalState<T>> transitCallback;

    EgressStopArrivalState(int round, TransferLeg egressLeg, Consumer<org.opentripplanner.transit.raptor.rangeraptor.standard.stoparrivals.EgressStopArrivalState<T>> transitCallback) {
        this.round = round;
        this.egressLeg = egressLeg;
        this.transitCallback = transitCallback;
    }

    public int round() {
        return round;
    }

    public int stop() {
        return egressLeg.stop();
    }


    public final TransferLeg egressLeg() {
        return egressLeg;
    }

    @Override
    public void arriveByTransit(int time, int boardStop, int boardTime, T trip) {
        super.arriveByTransit(time, boardStop, boardTime, trip);
        transitCallback.accept(this);
    }
}