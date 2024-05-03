package daoImpl;

import dao.DistributorDAO;
import model.Distributor;
import model.Product;
import model.User;
import database.DatabaseConnection;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;



public class DistributorDAOImpl implements DistributorDAO {
    private final Connection connection = DatabaseConnection.getInstance().getConnection();

    @Override
    public List<Distributor> getAllDistributors() throws SQLException {
        List<Distributor> distributors = new ArrayList<>();
        String query = "SELECT * FROM DISTRIBUTOR";
        ResultSet rs = null;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            rs = statement.executeQuery();
            while (rs.next()) {
                Distributor distributor = new Distributor();
                distributor.setDistributorId(rs.getInt("id"));
                distributor.setName(rs.getString("name"));
                distributors.add(distributor);
            }
        } finally {
            if(rs != null) {
                rs.close();
            }
        }
        return distributors;
    }

    @Override
    public Distributor getDistributorById(int id) throws SQLException {
        String query = "SELECT * FROM DISTRIBUTOR WHERE id=?";
        ResultSet rs = null;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            rs = statement.executeQuery();
            if (rs.next()) {
                Distributor distributor = new Distributor();
                distributor.setDistributorId(rs.getInt("id"));
                distributor.setName(rs.getString("name"));
            }
        } finally {
            if(rs != null) {
                rs.close();
            }
        }
        return null;
    }

    @Override
    public Distributor getDistributorByName(String name) throws SQLException {
        String query = "SELECT * FROM DISTRIBUTOR WHERE name=?";
        ResultSet rs = null;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            rs = statement.executeQuery();
            if (rs.next()) {
                Distributor distributor = new Distributor();
                distributor.setDistributorId(rs.getInt("id"));
                distributor.setName(rs.getString("name"));
                return distributor;
            }
        } finally {
            if(rs != null) {
                rs.close();
            }
        }
        return null;
    }


    @Override
    public void addDistributor(Distributor distributor) throws SQLException {
        String sql = "INSERT INTO DISTRIBUTOR (name) VALUES (?);";

        try(PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, distributor.getName());
            statement.executeUpdate();
        }
    }

    @Override
    public void updateDistributor(Distributor newDistributor) throws SQLException {
        String sql = "UPDATE DISTRIBUTOR SET name=? WHERE id=?";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, newDistributor.getName());
            statement.setInt(2, newDistributor.getDistributorId());
            statement.executeUpdate();
        }
    }

    @Override
    public void deleteDistributor(int distributorId) throws SQLException {
        String sql = "DELETE FROM PRODUCT WHERE distributorId = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, distributorId);
            statement.executeUpdate();
        }
        sql = "DELETE FROM DISTRIBUTOR WHERE id = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, distributorId);
            statement.executeUpdate();
        }
    }
}
