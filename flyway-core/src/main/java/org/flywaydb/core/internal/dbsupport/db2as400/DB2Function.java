/**
 * Copyright 2010-2015 Axel Fontaine
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.flywaydb.core.internal.dbsupport.db2as400;

import org.flywaydb.core.internal.dbsupport.DbSupport;
import org.flywaydb.core.internal.dbsupport.Function;
import org.flywaydb.core.internal.dbsupport.JdbcTemplate;
import org.flywaydb.core.internal.dbsupport.Schema;
import org.flywaydb.core.internal.util.StringUtils;

import java.sql.SQLException;

/**
 * Db2-specific function.
 */
public class DB2Function extends Function {
    /**
     * Creates a new Db2 function.
     *
     * @param jdbcTemplate The Jdbc Template for communicating with the DB.
     * @param dbSupport    The database-specific support.
     * @param schema       The schema this function lives in.
     * @param name         The name of the function.
     * @param args         The arguments of the function.
     */
    public DB2Function(JdbcTemplate jdbcTemplate, DbSupport dbSupport, Schema schema, String name, String... args) {
        super(jdbcTemplate, dbSupport, schema, name, args);
    }

    @Override
    protected void doDrop() throws SQLException {
        jdbcTemplate.execute("DROP FUNCTION "
                + dbSupport.quote(schema.getName(), name)
                + "(" + StringUtils.arrayToCommaDelimitedString(args) + ")");
    }

    @Override
    public String toString() {
        return super.toString() + "(" + StringUtils.arrayToCommaDelimitedString(args) + ")";
    }
}
