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

/**
 * Factory for creating {@link FieldExpression}s for thing search.
 */
public interface ThingsFieldExpressionFactory extends FieldExpressionFactory {

    /**
     * Return a exist field expression for the given feature id.
     *
     * @param featureId the feature id
     * @return the exist field expression
     */
    ExistsFieldExpression existsByFeatureId(final String featureId);

    /**
     * Return a filter field expression for the given feature property key.
     *
     * @param property the property path
     * @return the filter field expression
     */
    FilterFieldExpression filterByFeatureProperty(final String property);

    /**
     * Return a exist field expression for the given feature property key.
     *
     * @param property the property path
     * @return the exist field expression
     */
    ExistsFieldExpression existsByFeatureProperty(final String property);

    /**
     * Return a filter field expression for the given feature id and property key.
     *
     * @param featureId the feature id
     * @param property the property path
     * @return the filter field expression
     */
    FilterFieldExpression filterByFeatureProperty(final String featureId, final String property);

    /**
     * Return a exist field expression for the given feature id and property key.
     *
     * @param featureId the feature id
     * @param property the property path
     * @return the exist field expression
     */
    ExistsFieldExpression existsByFeatureProperty(final String featureId, final String property);

    /**
     * Return a sortOptions field expression for the given feature property key.
     *
     * @param featureId the feature id
     * @param property the property path
     * @return the sortOptions field expression
     */
    SortFieldExpression sortByFeatureProperty(final String featureId, final String property);

    /**
     * Return a filter field expression for the given attribute key.
     *
     * @param key the key
     * @return the filter field expression
     */
    FilterFieldExpression filterByAttribute(final String key);

    /**
     * Return a exist field expression for the given attribute key.
     *
     * @param key the key
     * @return the exist field expression
     */
    ExistsFieldExpression existsByAttribute(final String key);

    /**
     * Return a sortOptions field expression for the given attribute key.
     *
     * @param key the key
     * @return the sortOptions field expression
     */
    SortFieldExpression sortByAttribute(final String key);

    /**
     * @return a filter expression for the thingId field.
     */
    FilterFieldExpression filterByThingId();

    /**
     * @return a sortOptions expression for the thingId field.
     */
    SortFieldExpression sortByThingId();

    /**
     * @return a filter expression for acl field.
     */
    FilterFieldExpression filterByAcl();

    /**
     * @return a filter expression for global read field.
     */
    FilterFieldExpression filterByGlobalRead();

    /**
     *
     * @return a filter expression for the given namespace
     */
    FilterFieldExpression filterByNamespace();
}
