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
package org.eclipse.ditto.services.gateway.security.authentication.jwt;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import javax.annotation.concurrent.Immutable;

import org.eclipse.ditto.model.policies.SubjectIssuer;

/**
 * Configuration for a {@link org.eclipse.ditto.model.policies.SubjectIssuer}.
 */
@Immutable
public final class JwtSubjectIssuerConfig {

    private final SubjectIssuer subjectIssuer;
    private final String jwtIssuer;
    private final String jwkResource;

    /**
     * Constructs a new {@code JwtSubjectIssuerConfig}.
     *
     * @param subjectIssuer the subject issuer
     * @param jwtIssuer the issuer from the JWT iss field.
     * @param jwkResource the JWK resource URL.
     */
    public JwtSubjectIssuerConfig(final SubjectIssuer subjectIssuer, final String jwtIssuer, final String jwkResource) {
        this.subjectIssuer = requireNonNull(subjectIssuer);
        this.jwtIssuer = requireNonNull(jwtIssuer);
        this.jwkResource = requireNonNull(jwkResource);
    }

    /**
     * Returns the subject issuer.
     *
     * @return the subject issuer
     */
    public SubjectIssuer getSubjectIssuer() {
        return subjectIssuer;
    }

    /**
     * Returns the JWT issuer.
     *
     * @return the JWT issuer.
     */
    public String getJwtIssuer() {
        return jwtIssuer;
    }

    /**
     * Returns the JWK resource URL.
     *
     * @return the JWK resource URL
     */
    public String getJwkResource() {
        return jwkResource;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final JwtSubjectIssuerConfig that = (JwtSubjectIssuerConfig) o;
        return Objects.equals(subjectIssuer, that.subjectIssuer) &&
                Objects.equals(jwtIssuer, that.jwtIssuer) &&
                Objects.equals(jwkResource, that.jwkResource);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subjectIssuer, jwtIssuer, jwkResource);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " [" +
                ", subjectIssuer=" + subjectIssuer +
                ", jwtIssuer=" + jwtIssuer +
                ", jwkResource=" + jwkResource +
                "]";
    }

}
