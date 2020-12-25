package com.example.dish.configuration;

import org.hibernate.dialect.MySQL57Dialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.type.StandardBasicTypes;

public class MySQLDishDialect extends MySQL57Dialect {

    public MySQLDishDialect() {
        super();
        registerFunction("regexp_like", new SQLFunctionTemplate(StandardBasicTypes.BOOLEAN, "?1 regexp ?2"));
    }
}
