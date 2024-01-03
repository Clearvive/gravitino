/*
 * Copyright 2024 Datastrato Pvt Ltd.
 * This software is licensed under the Apache License version 2.
 */
package com.datastrato.gravitino.catalog.mysql;

import static com.datastrato.gravitino.catalog.PropertyEntry.integerOptionalPropertyEntry;
import static com.datastrato.gravitino.catalog.PropertyEntry.stringOptionalPropertyEntry;
import static com.datastrato.gravitino.catalog.PropertyEntry.stringReservedPropertyEntry;

import com.datastrato.gravitino.catalog.PropertyEntry;
import com.datastrato.gravitino.catalog.jdbc.JdbcTablePropertiesMetadata;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MysqlTablePropertiesMetadata extends JdbcTablePropertiesMetadata {
  public static final String ENGINE = "ENGINE";
  public static final String DEFAULT_CHARSET = "DEFAULT CHARSET";
  public static final String COLLATE = "COLLATE";
  public static final String AUTO_INCREMENT = "AUTO_INCREMENT";
  private static final Map<String, PropertyEntry<?>> PROPERTIES_METADATA;

  static {
    PROPERTIES_METADATA =
        Collections.unmodifiableMap(
            new HashMap<String, PropertyEntry<?>>() {
              {
                put(COMMENT, stringReservedPropertyEntry(COMMENT, "The table comment", true));
                put(
                    ENGINE,
                    stringOptionalPropertyEntry(ENGINE, "The table engine", false, null, false));
                put(
                    DEFAULT_CHARSET,
                    stringOptionalPropertyEntry(
                        DEFAULT_CHARSET, "The table character set", false, null, false));
                put(
                    COLLATE,
                    stringOptionalPropertyEntry(COLLATE, "The table collate", false, null, false));
                // auto_increment properties can only be specified when creating and cannot be
                // modified.
                put(
                    AUTO_INCREMENT,
                    integerOptionalPropertyEntry(
                        AUTO_INCREMENT, "The table auto increment offset", true, null, false));
              }
            });
  }

  @Override
  protected Map<String, PropertyEntry<?>> specificPropertyEntries() {
    return PROPERTIES_METADATA;
  }
}
