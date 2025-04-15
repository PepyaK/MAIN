package com.example.demo2;

import java.sql.*;

public class Registration {

    public static void registerAccount(String firstName, String lastName, String login, String password, String company) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/huntlyschemas", "root", "pepka")) {

            // Проверка на существующий логин
            String checkQuery = "SELECT * FROM huntlyschemas.accounts WHERE login = ?";
            try (PreparedStatement checkStmt = connection.prepareStatement(checkQuery)) {
                checkStmt.setString(1, login);
                ResultSet rs = checkStmt.executeQuery();
                if (rs.next()) {
                    // Логин уже существует
                    System.out.println("Ошибка: Этот логин уже занят. Пожалуйста, выберите другой.");
                    return;
                }
            }

            // Добавление нового пользователя
            String insertQuery = "INSERT INTO huntlyschemas.accounts (company, firstname, lastname, login, password) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement insertStmt = connection.prepareStatement(insertQuery)) {
                insertStmt.setString(1, company);  // Заполняем поле company в зависимости от выбора
                insertStmt.setString(2, firstName);
                insertStmt.setString(3, lastName);
                insertStmt.setString(4, login);
                insertStmt.setString(5, password);
                insertStmt.executeUpdate();
            }

            // После успешной регистрации можно показать сообщение о завершении регистрации
            System.out.println("Информация: Регистрация прошла успешно!");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ошибка: Ошибка подключения к базе данных.");
        }
    }
}
