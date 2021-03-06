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
package org.eclipse.ditto.services.thingsearch.persistence.read.query;

import javax.annotation.concurrent.Immutable;

import org.eclipse.ditto.model.query.criteria.Criteria;
import org.eclipse.ditto.services.base.config.LimitsConfigReader;
import org.eclipse.ditto.services.thingsearch.persistence.read.AggregationBuilder;
import org.eclipse.ditto.services.thingsearch.persistence.read.AggregationBuilderFactory;

/**
 * Mongo implementation for {@link AggregationBuilderFactory}.
 */
@Immutable
public final class MongoAggregationBuilderFactory implements AggregationBuilderFactory {

    private final LimitsConfigReader limitsConfigReader;

    public MongoAggregationBuilderFactory(final LimitsConfigReader limitsConfigReader) {
        this.limitsConfigReader = limitsConfigReader;
    }

    public static AggregationBuilder newBuilder(final LimitsConfigReader limitsConfigReader) {
        return new PolicyRestrictedMongoSearchAggregation.Builder(limitsConfigReader);
    }

    @Override
    public AggregationBuilder newBuilder(final Criteria criteria) {
        return new PolicyRestrictedMongoSearchAggregation.Builder(limitsConfigReader).filterCriteria(criteria);
    }

    @Override
    public AggregationBuilder newCountBuilder(final Criteria criteria) {
        return new PolicyRestrictedMongoSearchAggregation.Builder(limitsConfigReader).filterCriteria(criteria).count(true);
    }

}
