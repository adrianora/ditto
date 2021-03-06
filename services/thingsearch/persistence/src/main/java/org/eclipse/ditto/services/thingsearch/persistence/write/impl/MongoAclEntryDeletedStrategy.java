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

import java.util.Collections;
import java.util.List;

import org.bson.conversions.Bson;
import org.eclipse.ditto.model.base.auth.AuthorizationSubject;
import org.eclipse.ditto.services.thingsearch.persistence.write.IndexLengthRestrictionEnforcer;
import org.eclipse.ditto.signals.events.things.AclEntryDeleted;

/**
 * Strategy that creates {@link Bson} for {@link AclEntryDeleted} events.
 */
public final class MongoAclEntryDeletedStrategy extends MongoEventToPersistenceStrategy<AclEntryDeleted> {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Bson> thingUpdates(final AclEntryDeleted event,
            final IndexLengthRestrictionEnforcer indexLengthRestrictionEnforcer) {
        final AuthorizationSubject authorizationSubject = event.getAuthorizationSubject();
        return Collections.singletonList(AclUpdatesFactory.deleteAclEntry(authorizationSubject.getId()));
    }
}
