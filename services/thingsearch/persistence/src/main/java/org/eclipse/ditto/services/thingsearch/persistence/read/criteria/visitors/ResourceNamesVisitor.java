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
package org.eclipse.ditto.services.thingsearch.persistence.read.criteria.visitors;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import org.eclipse.ditto.model.query.criteria.Predicate;
import org.eclipse.ditto.model.query.criteria.visitors.CriteriaVisitor;
import org.eclipse.ditto.model.query.expression.ExistsFieldExpression;
import org.eclipse.ditto.model.query.expression.FilterFieldExpression;
import org.eclipse.ditto.services.thingsearch.persistence.read.expression.visitors.GetFieldIdentifierVisitor;

/**
 * Collects names of resources.
 */
public class ResourceNamesVisitor implements CriteriaVisitor<Set<String>> {

    private ResourceNamesVisitor() {
        // only internally instantiable
    }

    @Override
    public Set<String> visitAnd(final Stream<Set<String>> conjuncts) {
        return union(conjuncts);
    }

    @Override
    public Set<String> visitAny() {
        return Collections.emptySet();
    }

    @Override
    public Set<String> visitExists(final ExistsFieldExpression fieldExpression) {
        return Collections.singleton(GetFieldIdentifierVisitor.apply(fieldExpression));
    }

    @Override
    public Set<String> visitField(final FilterFieldExpression fieldExpression, final Predicate predicate) {
        return Collections.singleton(GetFieldIdentifierVisitor.apply(fieldExpression));
    }

    @Override
    public Set<String> visitNor(final Stream<Set<String>> negativeDisjoints) {
        return union(negativeDisjoints);
    }

    @Override
    public Set<String> visitOr(final Stream<Set<String>> disjoints) {
        return union(disjoints);
    }

    private Set<String> union(final Stream<Set<String>> conjuncts) {
        final Set<String> result = new HashSet<>();
        conjuncts.forEach(result::addAll);
        return result;
    }
}
