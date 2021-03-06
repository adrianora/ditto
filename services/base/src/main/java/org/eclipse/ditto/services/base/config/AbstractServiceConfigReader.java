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
package org.eclipse.ditto.services.base.config;

import org.eclipse.ditto.services.utils.config.AbstractConfigReader;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

/**
 * Abstract base implementation of {@link ServiceConfigReader}.
 */
public class AbstractServiceConfigReader extends AbstractConfigReader implements ServiceConfigReader {

    private static final String DEFAULT_CONFIG_PREFIX = "ditto";
    private static final String PATH_CLUSTER = "cluster";
    private static final String PATH_HEALTH_CHECK = "health-check";
    private static final String PATH_HTTP = "http";
    private static final String PATH_METRICS = "metrics";
    protected static final String PATH_LIMITS = "limits";
    private static final String PATH_DEVOPS = "devops";

    private final Config rawConfig;

    /**
     * Create a service config reader with the default config prefix.
     *
     * @param config Overall config.
     * @param serviceName Name of service.
     */
    protected AbstractServiceConfigReader(final Config config, final String serviceName) {
        this(config, DEFAULT_CONFIG_PREFIX, serviceName);
    }

    /**
     * Create a service config reader.
     *
     * @param config Overall config.
     * @param prefix Prefix of service config.
     * @param serviceName Name of service.
     */
    protected AbstractServiceConfigReader(final Config config, final String prefix, final String serviceName) {
        super(getOrEmpty(config, path(prefix, serviceName)));
        this.rawConfig = config;
    }

    @Override
    public Config getRawConfig() {
        return rawConfig;
    }

    @Override
    public ClusterConfigReader cluster() {
        return new ClusterConfigReader(getChildOrEmpty(PATH_CLUSTER));
    }

    @Override
    public HealthConfigReader health() {
        return new HealthConfigReader(getChildOrEmpty(PATH_HEALTH_CHECK));
    }

    @Override
    public HttpConfigReader http() {
        return new HttpConfigReader(getChildOrEmpty(PATH_HTTP));
    }

    @Override
    public MetricsConfigReader metrics() {
        return new MetricsConfigReader(getChildOrEmpty(PATH_METRICS));
    }

    @Override
    public SuffixBuilderConfigReader mongoCollectionNameSuffix() {
        return SuffixBuilderConfigReader.fromRawConfig(rawConfig);
    }

    @Override
    public LimitsConfigReader limits() {
        return new DittoLimitsConfigReader(getChildOrEmpty(PATH_LIMITS));
    }

    @Override
    public DevOpsConfigReader devops() {
        return new DevOpsConfigReader(getChildOrEmpty(PATH_DEVOPS));
    }

    private static Config getOrEmpty(final Config config, final String path) {
        return config.hasPath(path) ? config.getConfig(path) : ConfigFactory.empty();
    }
}
