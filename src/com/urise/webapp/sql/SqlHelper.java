package com.urise.webapp.sql;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.storage.SqlStorage;
import org.postgresql.util.PSQLException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {
    private final ConnectionFactory connectionFactory;

    public SqlHelper(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public void execute(String value) {
        execute(value, PreparedStatement::execute);
    }

    public <T> T execute(String value, SqlExecutor<T> executor) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(value)) {
            return executor.execute(ps);
        } catch (SQLException e) {
            if (e.getSQLState().equals("42710")) {
                throw new ExistStorageException("Resume is exist");
            }
            throw new StorageException(e);
        }
    }

    public <T> T transactionalExecute(SqlTransaction<T> executor) {
        try (Connection connection = connectionFactory.getConnection()) {
            try {
                connection.setAutoCommit(false);
                T res = executor.execute(connection);
                connection.commit();
                return res;
            } catch (SQLException e) {
                connection.rollback();
                throw returnException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static StorageException returnException(SQLException e) {
        if (e.getSQLState().equals("42710")) {
            return new ExistStorageException("Resume is exist");
        }
        return new StorageException(e);
    }
}
