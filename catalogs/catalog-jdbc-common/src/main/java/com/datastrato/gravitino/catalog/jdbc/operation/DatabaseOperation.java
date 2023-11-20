/*
 * Copyright 2023 Datastrato.
 * This software is licensed under the Apache License version 2.
 */

package com.datastrato.gravitino.catalog.jdbc.operation;

import com.datastrato.gravitino.catalog.jdbc.JdbcSchema;
import com.datastrato.gravitino.catalog.jdbc.converter.JdbcExceptionConverter;
import com.datastrato.gravitino.exceptions.NoSuchSchemaException;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;

public interface DatabaseOperation {

  /**
   * Initializes the database operations.
   *
   * @param dataSource The data source to use for the operations.
   * @param exceptionMapper The exception mapper to use for the operations.
   * @throws RuntimeException
   */
  void initialize(final DataSource dataSource, final JdbcExceptionConverter exceptionMapper)
      throws RuntimeException;

  /**
   * Creates a database with the given name and comment.
   *
   * @param databaseName The name of the database to create.
   * @param comment The comment of the database to create.
   */
  void create(String databaseName, String comment, Map<String, String> properties);

  /**
   * @param databaseName The name of the database to check.
   * @param cascade If set to true, drops all the tables in the database as well.
   */
  void delete(String databaseName, boolean cascade);

  /** @return The list name of databases. */
  List<String> list();

  /**
   * @param databaseName The name of the database to check.
   * @return information object of the JDBC database.
   */
  JdbcSchema load(String databaseName) throws NoSuchSchemaException;
}