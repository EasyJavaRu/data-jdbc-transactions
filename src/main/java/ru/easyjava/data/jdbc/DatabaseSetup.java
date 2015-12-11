package ru.easyjava.data.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Creates database tables and initializes them with data.
 */
public final class DatabaseSetup {
    /**
     * Number of our client.
     */
    public static final int CLIENT_ID = 3;

    /**
     * Initial account value.
     */
    public static final int CLIENT_INITIAL_ACCOUNT = 100;

    /**
     * Query that creates order table.
     */
    private static final String CREATE_ITEM =
            "CREATE TABLE ORDER_ITEMS ("
                    + "ID IDENTITY PRIMARY KEY, "
                    + "CLIENT_ID BIGINT NOT NULL, "
                    + "ORDER_ID INTEGER NOT NULL, "
                    + "ITEM_ID INTEGER NOT NULL"
                    + ")";

    /**
     * Query that creates clients table.
     */
    private static final String CREATE_CLIENT =
            "CREATE TABLE CLIENTS ("
                    + "ID IDENTITY PRIMARY KEY, "
                    + "LOGIN VARCHAR(256) NOT NULL,"
                    + "ACCOUNT INTEGER NOT NULL"
                    + ")";

    /**
     * Client creation query.
     */
    private static final String ADD_CLIENT =
            "INSERT INTO CLIENTS(ID, LOGIN, ACCOUNT) VALUES(?, ?, ?)";

    /**
     * Do not construct me.
     */
    private DatabaseSetup() { }

    /**
     * Here we create tables and populate them with sample data.
     * @param db Database connection object.
     * @throws SQLException when something goes wrong.
     */
    public static void setUp(final Connection db) throws SQLException {
        try (Statement dataQuery = db.createStatement()) {
            dataQuery.execute(CREATE_ITEM);
            dataQuery.execute(CREATE_CLIENT);
        }
        try (PreparedStatement clientAddQuery
                     = db.prepareStatement(ADD_CLIENT)) {
            clientAddQuery.setInt(1, 1);
            clientAddQuery.setString(2, "test");
            clientAddQuery.setInt(CLIENT_ID, CLIENT_INITIAL_ACCOUNT);
            clientAddQuery.executeUpdate();
        }
    }

}
