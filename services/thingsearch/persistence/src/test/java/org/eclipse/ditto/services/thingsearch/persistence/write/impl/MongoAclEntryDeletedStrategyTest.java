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


import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.List;

import org.bson.conversions.Bson;
import org.eclipse.ditto.services.thingsearch.persistence.TestConstants;
import org.eclipse.ditto.signals.events.things.AclEntryDeleted;
import org.junit.Test;

public final class MongoAclEntryDeletedStrategyTest extends AbstractMongoEventToPersistenceStrategyTest {

    private final MongoAclEntryDeletedStrategy strategy = new MongoAclEntryDeletedStrategy();

    @Test
    public void thingUpdates() {
        final List<Bson> updates = strategy.thingUpdates(thingEvent(), indexLengthRestrictionEnforcer);
        assertThat(updates).hasSize(1);
    }

    @Test
    public void policyUpdates() {
        assertThat(strategy.policyUpdates(thingEvent(), policyEnforcer))
                .isEqualTo(Collections.emptyList());
    }

    private AclEntryDeleted thingEvent() {
        return setVersion(TestConstants.ThingEvent.ACL_ENTRY_DELETED);
    }

}
