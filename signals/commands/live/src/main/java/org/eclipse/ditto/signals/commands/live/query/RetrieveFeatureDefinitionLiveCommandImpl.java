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
package org.eclipse.ditto.signals.commands.live.query;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;

import org.eclipse.ditto.model.base.headers.DittoHeaders;
import org.eclipse.ditto.signals.commands.base.Command;
import org.eclipse.ditto.signals.commands.things.query.RetrieveFeatureDefinition;

/**
 * An immutable implementation of {@link RetrieveFeatureDefinitionLiveCommand}.
 */
@ParametersAreNonnullByDefault
@Immutable
final class RetrieveFeatureDefinitionLiveCommandImpl
        extends AbstractQueryLiveCommand<RetrieveFeatureDefinitionLiveCommand,
        RetrieveFeatureDefinitionLiveCommandAnswerBuilder> implements RetrieveFeatureDefinitionLiveCommand {

    private final String featureId;

    private RetrieveFeatureDefinitionLiveCommandImpl(final RetrieveFeatureDefinition command) {
        super(command);
        featureId = command.getFeatureId();
    }

    /**
     * Returns an instance of {@code RetrieveFeatureDefinitionLiveCommandImpl}.
     *
     * @param command the command to base the result on.
     * @return the instance.
     * @throws NullPointerException if {@code command} is {@code null}.
     * @throws ClassCastException if {@code command} is not an instance of {@link RetrieveFeatureDefinition}.
     */
    @Nonnull
    public static RetrieveFeatureDefinitionLiveCommandImpl of(final Command<?> command) {
        return new RetrieveFeatureDefinitionLiveCommandImpl((RetrieveFeatureDefinition) command);
    }

    @Override
    public String getFeatureId() {
        return featureId;
    }

    @Override
    public RetrieveFeatureDefinitionLiveCommand setDittoHeaders(final DittoHeaders dittoHeaders) {
        return of(RetrieveFeatureDefinition.of(getThingId(), getFeatureId(), dittoHeaders));
    }

    @Nonnull
    @Override
    public RetrieveFeatureDefinitionLiveCommandAnswerBuilder answer() {
        return RetrieveFeatureDefinitionLiveCommandAnswerBuilderImpl.newInstance(this);
    }

    @Nonnull
    @Override
    public String toString() {
        return getClass().getSimpleName() + "[" + super.toString() + "]";
    }

}
