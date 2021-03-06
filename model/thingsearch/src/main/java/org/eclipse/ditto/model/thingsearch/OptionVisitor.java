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
package org.eclipse.ditto.model.thingsearch;

/**
 * Interface for the visitor pattern to traverse through option nodes.
 */
public interface OptionVisitor {

    /**
     * Is called by a {@link LimitOption} in its {@link LimitOption#accept(OptionVisitor)} method.
     *
     * @param limitOption an instance of the {@link LimitOption}.
     */
    void visit(LimitOption limitOption);

    /**
     * Is called by a {@link SortOption} in its {@link SortOption#accept(OptionVisitor)} method.
     *
     * @param sortOption an instance of the {@link SortOption}.
     */
    void visit(SortOption sortOption);

    /**
     * Is called by a {@link Option} in its {@link Option#accept(OptionVisitor)} method.
     *
     * @param option an instance of the {@link Option}.
     */
    void visit(Option option); // general catch all for custom Node implementations

}
