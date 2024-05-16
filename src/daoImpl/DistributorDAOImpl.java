package daoImpl;

import dao.DAOInterface;
import model.Distributor;
import database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DistributorDAOImpl implements DAOInterface<Distributor> {
    private final Connection connection = DatabaseConnection.getInstance().getConnection();

    @Override
    public List<Distributor> read() throws SQLException {
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


    public Distributor readById(int id) throws SQLException {
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


    public Distributor readByName(String name) throws SQLException {
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
    public void create(Distributor distributor) throws SQLException {
        String sql = "INSERT INTO DISTRIBUTOR (name) VALUES (?);";

        try(PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, distributor.getName());
            statement.executeUpdate();
            connection.commit();
        }
    }

    @Override
    public void update(Distributor newDistributor) throws SQLException {
        String sql = "UPDATE DISTRIBUTOR SET name=? WHERE id=?";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, newDistributor.getName());
            statement.setInt(2, newDistributor.getDistributorId());
            statement.executeUpdate();
            connection.commit();
        }
    }

    @Override
    public void delete(int distributorId) throws SQLException {
        String sql = "DELETE FROM PRODUCT WHERE distributorId = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, distributorId);
            statement.executeUpdate();
            connection.commit();
        }
        sql = "DELETE FROM DISTRIBUTOR WHERE id = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, distributorId);
            statement.executeUpdate();
            connection.commit();
        }
    }
}
