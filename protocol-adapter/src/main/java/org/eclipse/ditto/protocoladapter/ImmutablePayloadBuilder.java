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
package org.eclipse.ditto.protocoladapter;

import java.time.Instant;

import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import org.eclipse.ditto.json.JsonFieldSelector;
import org.eclipse.ditto.json.JsonPointer;
import org.eclipse.ditto.json.JsonValue;
import org.eclipse.ditto.model.base.common.HttpStatusCode;

/**
 * Mutable implementation of {@link PayloadBuilder} for building immutable {@link Payload} instances.
 */
@NotThreadSafe
final class ImmutablePayloadBuilder implements PayloadBuilder {

    @Nullable private final JsonPointer path;

    private JsonValue value;
    private HttpStatusCode status;
    private Long revision;
    @Nullable private Instant timestamp;
    private JsonFieldSelector fields;

    private ImmutablePayloadBuilder(@Nullable final JsonPointer path) {
        this.path = path;
    }

    /**
     */
    public static ImmutablePayloadBuilder of() {
        return new ImmutablePayloadBuilder(null);
    }

    public static ImmutablePayloadBuilder of(final JsonPointer path) {
        return new ImmutablePayloadBuilder(path);
    }

    @Override
    public PayloadBuilder withValue(final JsonValue value) {
        this.value = value;
        return this;
    }

    @Override
    public PayloadBuilder withStatus(final HttpStatusCode status) {
        this.status = status;
        return this;
    }

    @Override
    public PayloadBuilder withStatus(final int status) {
        this.status = HttpStatusCode.forInt(status) //
                .orElseThrow(() -> new IllegalArgumentException("Status code not supported!"));
        return this;
    }

    @Override
    public PayloadBuilder withRevision(final long revision) {
        this.revision = revision;
        return this;
    }

    @Override
    public PayloadBuilder withTimestamp(@Nullable final Instant timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    @Override
    public PayloadBuilder withFields(final JsonFieldSelector fields) {
        this.fields = fields;
        return this;
    }

    @Override
    public PayloadBuilder withFields(final String fields) {
        this.fields = JsonFieldSelector.newInstance(fields);
        return this;
    }

    @Override
    public Payload build() {
        return ImmutablePayload.of(path, value, status, revision, timestamp, fields);
    }

}
