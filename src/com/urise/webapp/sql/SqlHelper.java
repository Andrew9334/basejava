package com.urise.webapp.sql;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.StorageException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public record SqlHelper(ConnectionFactory connectionFactory) {
    public void execute(String value) {
        execute(value, PreparedStatement::execute);
    }

    public <T> T execute(String value, SqlExecutor<T> executor) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(value)) {
            return executor.execute(ps);
        } catch (SQLException e) {
            SQLException sqlException = new SQLException();
            if (sqlException.getSQLState().equals("42710")) {
                throw new ExistStorageException("Resume is exist");
            }
            throw new StorageException(e);
        }
    }
}
