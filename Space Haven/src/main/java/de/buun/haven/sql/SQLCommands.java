package de.buun.haven.sql;

import java.sql.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SQLCommands {

    private SQLCommands(){}

    public static boolean insertInto(Database database, String name, ValueSet set){
        StringBuilder sqls = new StringBuilder("INSERT INTO " + name + " VALUES(?,");
        Table<?> table = database.getTable(name);
        for (int i = 0; i < table.getColumn().length - 1; i++) {
            sqls.append("?,");
        }
        sqls = new StringBuilder(sqls.substring(0, sqls.length() - 1));
        sqls.append(")");

        try (Connection conn = database.connect(); PreparedStatement pstmt = conn.prepareStatement(sqls.toString())) {
            for (int i = 0; i < table.getColumn().length; i++) {
                pstmt.setObject(i + 1, set.getValue(i));
            }
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static boolean update(Database database, String name, ValueSet set){
        Table<?> table = database.getTable(name);
        String keyColumn = table.getColumn()[0].getName();
        String columns = String.join("=?,", Arrays.stream(table.getColumn()).map(ColumnData::getName).toArray(String[] :: new));
        String sql = "UPDATE " + name + " SET " + columns + " WHERE " + keyColumn + "='" + set.getValue(0) + "';";
        try(Connection connection = database.connect();
            PreparedStatement statement = connection.prepareStatement(sql)){
            for (int i = 0; i < table.getColumn().length; i++) {
                statement.setObject(i + 1, set.getValue(i));
            }
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean delete(Database database, String name, Object key){
        String keyColumn = database.getTable(name).getColumn()[0].getName();
        String sql = "DELETE FROM " + name + " WHERE " + keyColumn + "= '" + key + "'";
        try (Connection conn = database.connect(); PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean createTable(Database database, String name, ColumnData[] columns){
        StringBuilder builder = new StringBuilder();
        for (ColumnData column : columns) builder.append(column.getSQLCode()).append(", ");
        String sKey = columns[0].getName();
        String sql = "CREATE TABLE IF NOT EXISTS " + name + " (" + builder.toString() + " PRIMARY KEY(" + sKey + "))";
        try(Connection connection = database.connect(); Statement statement = connection.createStatement()){
            statement.execute(sql);
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static Map<Object, ValueSet> selectAll(Database database, String tableName, int columnLength){
        String sql = "SELECT * FROM " + tableName;
        Map<Object, ValueSet> map = new HashMap<>();
        try(Connection connection = database.connect();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)){

            while (resultSet.next()){
                Object key = resultSet.getObject(1);
                ValueSet set = new ValueSet(columnLength);
                for (int i = 0; i < columnLength; i++) {
                    set.setValue(0, resultSet.getObject(1));
                }
                map.put(key, set);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return map;
    }
}
