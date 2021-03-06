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
package org.eclipse.ditto.model.query.expression.visitors;

import org.eclipse.ditto.model.query.expression.PolicyRestrictedFieldExpression;

/**
 * Compositional interpreter of
 * {@link PolicyRestrictedFieldExpression}.
 */
public interface PolicyRestrictedFieldExpressionVisitor<T> {

    T visitAttribute(final String key);

    T visitFeature(final String featureId);

    T visitFeatureIdProperty(final String featureId, final String property);

    T visitFeatureProperty(final String property);
}
