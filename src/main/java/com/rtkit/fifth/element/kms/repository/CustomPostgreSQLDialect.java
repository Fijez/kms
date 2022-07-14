package com.rtkit.fifth.element.kms.repository;

import org.hibernate.dialect.PostgreSQL10Dialect;

public class CustomPostgreSQLDialect extends PostgreSQL10Dialect {

    public CustomPostgreSQLDialect() {
        registerFunction("fts", new PostgreSQLFTSFunction());
    }
}