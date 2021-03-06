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
package org.eclipse.ditto.model.query.expression;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import org.eclipse.ditto.model.query.expression.visitors.ExistsFieldExpressionVisitor;
import org.eclipse.ditto.model.query.expression.visitors.FieldExpressionVisitor;
import org.eclipse.ditto.model.query.expression.visitors.FilterFieldExpressionVisitor;
import org.eclipse.ditto.model.query.expression.visitors.PolicyRestrictedFieldExpressionVisitor;

/**
 * Field expression for feature properties without a given feature id.
 */
public class FeaturePropertyExpressionImpl implements FilterFieldExpression, ExistsFieldExpression,
        PolicyRestrictedFieldExpression {

    private final String property;

    /**
     * Constructor.
     *
     * @param property the feature property property.
     */
    public FeaturePropertyExpressionImpl(final String property) {
        this.property = requireNonNull(property);
    }

    /**
     * @return the property.
     */
    public String getProperty() {
        return property;
    }

    @Override
    public <T> T acceptExistsVisitor(final ExistsFieldExpressionVisitor<T> visitor) {
        return visitor.visitFeatureProperty(property);
    }

    @Override
    public <T> T acceptFilterVisitor(final FilterFieldExpressionVisitor<T> visitor) {
        return visitor.visitFeatureProperty(property);
    }

    @Override
    public <T> T acceptPolicyRestrictedVisitor(final PolicyRestrictedFieldExpressionVisitor<T> visitor) {
        return visitor.visitFeatureProperty(property);
    }

    @Override
    public <T> T accept(final FieldExpressionVisitor<T> visitor) {
        return visitor.visitFeatureProperty(property);
    }

    @SuppressWarnings("squid:MethodCyclomaticComplexity")
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final FeaturePropertyExpressionImpl that = (FeaturePropertyExpressionImpl) o;
        return Objects.equals(property, that.property);
    }

    @SuppressWarnings("squid:S109")
    @Override
    public int hashCode() {
        return Objects.hash(property);
    }

    @Override
    public String toString() {
        return "FeaturePropertyExpression [property=" + property + "]";
    }
}
