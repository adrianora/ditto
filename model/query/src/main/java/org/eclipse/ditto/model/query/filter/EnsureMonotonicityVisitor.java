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
package org.eclipse.ditto.model.query.filter;

import java.util.stream.Stream;

import org.eclipse.ditto.model.base.exceptions.InvalidRqlExpressionException;
import org.eclipse.ditto.model.base.headers.DittoHeaders;
import org.eclipse.ditto.model.base.json.JsonSchemaVersion;
import org.eclipse.ditto.model.query.criteria.Criteria;
import org.eclipse.ditto.model.query.criteria.Predicate;
import org.eclipse.ditto.model.query.criteria.visitors.CriteriaVisitor;
import org.eclipse.ditto.model.query.expression.ExistsFieldExpression;
import org.eclipse.ditto.model.query.expression.FilterFieldExpression;

/**
 * Throws an exception if the criteria contains negation (i. e., is not monotone) but the API forbids negation.
 */
public final class EnsureMonotonicityVisitor implements CriteriaVisitor<Void> {

    private final DittoHeaders dittoHeaders;
    private final boolean norIsForbidden;

    /**
     * Creates a test whether the criteria contains NOT for an incompatible API version in the command headers.
     *
     * @param dittoHeaders The command headers containing the API version to check.
     */
    private EnsureMonotonicityVisitor(final DittoHeaders dittoHeaders) {
        this.dittoHeaders = dittoHeaders;
        norIsForbidden = shouldForbidNorCriteria();
    }

    /**
     * Throws an exception if the criteria contains negation (i. e., is not monotone)
     * but the HTTP API forbids negation.
     *
     * @param criteria The criteria to check for negation.
     * @param dittoHeaders The command headers containing the API version to check.
     * @throws InvalidRqlExpressionException if criteria contains negation but dittoHeaders disallow it.
     */
    public static void apply(final Criteria criteria, final DittoHeaders dittoHeaders) {
        criteria.accept(new EnsureMonotonicityVisitor(dittoHeaders));
    }

    @Override
    public Void visitAnd(final Stream<Void> conjuncts) {
        // force the stream to evaluate criteria on children
        conjuncts.count();
        return null;
    }

    @Override
    public Void visitAny() {
        return null;
    }

    @Override
    public Void visitExists(final ExistsFieldExpression fieldExpression) {
        return null;
    }

    @Override
    public Void visitField(final FilterFieldExpression fieldExpression, final Predicate predicate) {
        return null;
    }

    @Override
    public Void visitNor(final Stream<Void> negativeDisjoints) {
        if (norIsForbidden) {
            final String message = String.format("The filter operation 'not' is not available in API versions >= %d. " +
                    "Please rephrase your search query without using 'not'.", getForbiddenSchemaVersion());
            throw InvalidRqlExpressionException.fromMessage(message, dittoHeaders);
        } else {
            // force the stream to evaluate criteria on children
            negativeDisjoints.count();
            return null;
        }
    }

    @Override
    public Void visitOr(final Stream<Void> disjoints) {
        // force the stream to evaluate criteria on children
        disjoints.count();
        return null;
    }

    private boolean shouldForbidNorCriteria() {
        // should forbid NorCriteria only for API >= 2
        return dittoHeaders.getSchemaVersion()
                .map(JsonSchemaVersion::toInt)
                .filter(v -> v >= JsonSchemaVersion.V_2.toInt())
                .isPresent();
    }

    private int getForbiddenSchemaVersion() {
        final JsonSchemaVersion defaultForbiddenVersion = JsonSchemaVersion.V_2;
        return dittoHeaders.getSchemaVersion().orElse(defaultForbiddenVersion).toInt();
    }

}
