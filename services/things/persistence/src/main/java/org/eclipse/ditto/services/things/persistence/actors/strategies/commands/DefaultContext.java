/*
 * Copyright (c) 2017 Contributors to the Eclipse Foundation
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.ditto.services.things.persistence.actors.strategies.commands;

import static org.eclipse.ditto.model.base.common.ConditionChecker.checkNotNull;

import java.util.Objects;
import java.util.function.Supplier;

import javax.annotation.concurrent.Immutable;

import org.eclipse.ditto.services.things.persistence.snapshotting.ThingSnapshotter;

import akka.event.DiagnosticLoggingAdapter;

/**
 * Holds the context required to execute the {@link CommandStrategy}s.
 */
@Immutable
public final class DefaultContext implements CommandStrategy.Context {

    private final String thingId;
    private final DiagnosticLoggingAdapter log;
    private final ThingSnapshotter<?, ?> thingSnapshotter;
    private final Runnable becomeCreatedRunnable;
    private final Runnable becomeDeletedRunnable;
    private final Runnable stopThisActorRunnable;
    private final Supplier<Boolean> isFirstMessageSupplier;

    private DefaultContext(final String theThingId,
            final DiagnosticLoggingAdapter theLog,
            final ThingSnapshotter<?, ?> theThingSnapshotter,
            final Runnable becomeCreatedRunnable,
            final Runnable becomeDeletedRunnable,
            final Runnable stopThisActorRunnable,
            final Supplier<Boolean> isFirstMessageSupplier) {

        thingId = checkNotNull(theThingId, "Thing ID");
        log = checkNotNull(theLog, "DiagnosticLoggingAdapter");
        thingSnapshotter = checkNotNull(theThingSnapshotter, "ThingSnapshotter");
        this.becomeCreatedRunnable = checkNotNull(becomeCreatedRunnable, "becomeCreatedRunnable");
        this.becomeDeletedRunnable = checkNotNull(becomeDeletedRunnable, "becomeDeletedRunnable");
        this.stopThisActorRunnable = checkNotNull(stopThisActorRunnable, "stopThisActorRunnable");
        this.isFirstMessageSupplier = checkNotNull(isFirstMessageSupplier, "isFirstMessageSupplier");
    }

    /**
     * Returns an instance of {@code DefaultContext}.
     *
     * @param thingId the ID of the Thing.
     * @param log the logging adapter to be used.
     * @param thingSnapshotter the snapshotter to be used.
     * @param becomeCreatedRunnable the runnable to be called in case a Thing is created.
     * @param becomeDeletedRunnable the runnable to be called in case a Thing is deleted.
     * @param isFirstMessageSupplier whether this message is the first.
     * @return the instance.
     * @throws NullPointerException if any argument is {@code null}.
     */
    public static DefaultContext getInstance(final String thingId,
            final DiagnosticLoggingAdapter log,
            final ThingSnapshotter<?, ?> thingSnapshotter,
            final Runnable becomeCreatedRunnable,
            final Runnable becomeDeletedRunnable,
            final Runnable stopThisActorRunnable,
            final Supplier<Boolean> isFirstMessageSupplier) {

        return new DefaultContext(thingId, log, thingSnapshotter, becomeCreatedRunnable, becomeDeletedRunnable,
                stopThisActorRunnable, isFirstMessageSupplier);
    }

    @Override
    public String getThingId() {
        return thingId;
    }

    @Override
    public DiagnosticLoggingAdapter getLog() {
        return log;
    }

    @Override
    public ThingSnapshotter<?, ?> getThingSnapshotter() {
        return thingSnapshotter;
    }

    @Override
    public Runnable getBecomeCreatedRunnable() {
        return becomeCreatedRunnable;
    }

    @Override
    public Runnable getBecomeDeletedRunnable() {
        return becomeDeletedRunnable;
    }

    @Override
    public Runnable getStopThisActorRunnable() {
        return stopThisActorRunnable;
    }

    @Override
    public boolean isFirstMessage() {
        return isFirstMessageSupplier.get();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final DefaultContext that = (DefaultContext) o;
        return Objects.equals(thingId, that.thingId) &&
                Objects.equals(log, that.log) &&
                Objects.equals(thingSnapshotter, that.thingSnapshotter) &&
                Objects.equals(becomeCreatedRunnable, that.becomeCreatedRunnable) &&
                Objects.equals(becomeDeletedRunnable, that.becomeDeletedRunnable) &&
                Objects.equals(stopThisActorRunnable, that.stopThisActorRunnable) &&
                Objects.equals(isFirstMessageSupplier, that.isFirstMessageSupplier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(thingId, log, thingSnapshotter, becomeCreatedRunnable, becomeDeletedRunnable,
                stopThisActorRunnable, isFirstMessageSupplier);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " [" +
                "thingId=" + thingId +
                ", log=" + log +
                ", thingSnapshotter=" + thingSnapshotter +
                ", becomeCreatedRunnable=" + becomeCreatedRunnable +
                ", becomeDeletedRunnable=" + becomeDeletedRunnable +
                ", stopThisActorRunnable=" + stopThisActorRunnable +
                ", isFirstMessageSupplier=" + isFirstMessageSupplier +
                "]";
    }

}
