package de.buun.haven.sql;

import de.buun.haven.BuildersUniverseException;
import de.buun.haven.Loggers;
import de.buun.haven.io.FileUtils;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteDatabase extends Database{

    private final String url;

    public SQLiteDatabase(File dir, String name){
        super();
        this.url = "jdbc:sqlite:" + dir.getAbsolutePath() + File.separator + name + ".db";
        File file = new File(dir,name + ".db");
        if(FileUtils.createFile(file) == null) Loggers.logError(new BuildersUniverseException("SQlLite-Database creation failed!"));
    }

    @Override
    public Connection connect() {
        Connection conn = null;
        try{
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(this.url);
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return conn;
    }
}
