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
package org.eclipse.ditto.services.thingsearch.persistence.write.impl;

import java.util.Set;

import javax.annotation.concurrent.Immutable;

/**
 * This interface represents the combination of a resource and the subject IDs which are granted access to the resource.
 */
@Immutable
interface ResourcePermissions {

    /**
     * Returns the resource which is secured by permissions.
     *
     * @return the resource.
     */
    String getResource();

    /**
     * Returns the IDs of subjects which are granted to read the resource.
     *
     * @return an unmodifiable unsorted set containing the IDs or an empty set.
     */
    Set<String> getReadGrantedSubjectIds();

    /**
     * Returns the IDs of subjects which are revoked to read the resource.
     *
     * @return an unmodifiable unsorted set containing the IDs or an empty set.
     */
    Set<String> getReadRevokedSubjectIds();

    /**
     * Creates an ID to be used for a policy entry.
     *
     * @param thingId the ID of the thing to which the resource of this object belongs.
     * @return the policy entry ID.
     * @throws NullPointerException if {@code thingId} is {@code null}.
     * @throws IllegalArgumentException if {@code thingId} is empty.
     */
    String createPolicyEntryId(CharSequence thingId);

}
