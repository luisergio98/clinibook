/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.tads.dac.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author luis_
 */
public class ConnectionFactory {

    public static final int MYSQL = 0;
    public static final String URL = "jdbc:mysql://localhost:3306/db_clientes?useUnicode=yes&characterEncoding=UTF-8";
    public static final String NOME = "root";
    public static final String SENHA = "root";
    private static final String MySQLDriver = "com.mysql.jdbc.Driver";

    public static Connection getConnection()
            throws ClassNotFoundException, SQLException {

        Class.forName(MySQLDriver);

        return DriverManager.getConnection(URL, NOME, SENHA);
    }
}
