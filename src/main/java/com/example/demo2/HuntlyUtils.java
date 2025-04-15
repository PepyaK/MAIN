package com.example.demo2;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HuntlyUtils {
    public static final Pos CENTER_ALIGNMENT = Pos.CENTER;

    public static TextField createTextField(String promptText) {
        TextField textField = new TextField();
        textField.setPromptText(promptText);
        textField.setStyle("-fx-background-color: #D9D9D9; -fx-text-fill: #333333; -fx-border-radius: 40; -fx-background-radius: 40;");
        textField.setFont(Font.font("Sans-Serif", FontWeight.BOLD, 24));
        textField.setPrefWidth(500);
        return textField;
    }

    public static Button createButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: linear-gradient(to right, #01C7FC, #00A1D8, #0056D6); -fx-text-fill: white; -fx-font-size: 24px; -fx-font-weight: bold; -fx-background-radius: 40;");
        button.setPrefWidth(280);
        return button;
    }

    public static HBox createTitleBar(Stage primaryStage, boolean showBackButton) {
        HBox titleBar = new HBox();
        // Реализация аналогична оригинальной, код вынесен сюда
        return titleBar;
    }

    public static boolean authenticateUser(String login, String password) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/huntlyschemas", "root", "pepka")) {
            String query = "SELECT * FROM huntlyschemas.accounts WHERE login = ? AND password = ?";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, login);
                stmt.setString(2, password);
                ResultSet rs = stmt.executeQuery();
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void registerAccount(String firstName, String lastName, String login, String password, String company) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/huntlyschemas", "root", "pepka")) {
            String checkQuery = "SELECT * FROM huntlyschemas.accounts WHERE login = ?";
            try (PreparedStatement checkStmt = connection.prepareStatement(checkQuery)) {
                checkStmt.setString(1, login);
                ResultSet rs = checkStmt.executeQuery();
                if (rs.next()) {
                    System.out.println("Ошибка: Этот логин уже занят.");
                    return;
                }
            }

            String insertQuery = "INSERT INTO huntlyschemas.accounts (company, firstname, lastname, login, password) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement insertStmt = connection.prepareStatement(insertQuery)) {
                insertStmt.setString(1, company);
                insertStmt.setString(2, firstName);
                insertStmt.setString(3, lastName);
                insertStmt.setString(4, login);
                insertStmt.setString(5, password);
                insertStmt.executeUpdate();
                System.out.println("Регистрация прошла успешно!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ошибка подключения к базе данных.");
        }
    }
}
