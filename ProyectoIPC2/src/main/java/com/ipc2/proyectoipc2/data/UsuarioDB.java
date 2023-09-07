package com.ipc2.proyectoipc2.data;

import com.ipc2.proyectoipc2.model.Usuario;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioDB {
    private Connection conexion;
    public UsuarioDB(Connection conexion) {
        this.conexion = conexion;
    }
    public boolean crear(Usuario user) {
        String query = "INSERT INTO USER_BIBLIOTECA (_code, _name, user_name, _password, email) VALUES (?, ?, ?, ?, ?)";
        try (var preparedStatement = conexion.prepareStatement(query)) {
            preparedStatement.setInt(1, user.getIdUsuario());
            preparedStatement.setString(2, user.getNombre());
            preparedStatement.setString(3, user.getUsername());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.executeUpdate();
            System.out.println("Usuario creado");
            return true;
        } catch (SQLException e) {
            System.out.println("Error al crear usuario: " + e);
        }
        return false;
    }

    public void actualizar(Usuario user) {
        String query = "UPDATE USER_BIBLIOTECA SET _name = ?, user_name = ?, _password = ?, email = ? WHERE _code = ?";

        try (var preparedStatement = conexion.prepareStatement(query)) {
            preparedStatement.setString(1, user.getNombre());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setInt(5, user.getIdUsuario());
            preparedStatement.executeUpdate();
            System.out.println("Usuario actualizado");
        } catch (SQLException e) {
            System.out.println("Error al actualizar usuario: " + e);
        }
    }

    public void eliminar(int id) {
        String query = "DELETE FROM USER_BIBLIOTECA WHERE _code = ?";

        try (var preparedStatement = conexion.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Usuario eliminado");
        } catch (SQLException e) {
            System.out.println("Error al eliminar usuario: " + e);
        }
    }

    public List<Usuario> listar() {
        var usuarios = new ArrayList<Usuario>();
        try (var stmt = conexion.createStatement();
             var resultSet = stmt.executeQuery("SELECT *  FROM USER_BIBLIOTECA")) {

            while (resultSet.next()) {

                var id = resultSet.getInt("id_usuario");
                var nombre = resultSet.getString("nombre");
                var username = resultSet.getString("username");
                var password = resultSet.getString("password");
                var email = resultSet.getString("email");

                var usuario = new Usuario(id, nombre, username, password, email);
                usuarios.add(usuario);
            }
        }catch (SQLException e) {
            System.out.println("Error al consultar: " + e);
        }
        return usuarios;
    }

    public Optional<Usuario> obtenerUsuario(String username, String password) {
        String query = "SELECT * FROM USER_BIBLIOTECA WHERE user_name = ? AND _password = ?";
        Usuario usuario = null;

        try (var preparedStatement = conexion.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            try (var resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {

                    var id = resultSet.getInt("id_usuario");
                    var nombre = resultSet.getString("nombre");
                    var email = resultSet.getString("email");
                    usuario = new Usuario(id, nombre, username, password, email);
                }
            }
        }catch (SQLException e) {
            System.out.println("Error al consultar: " + e);
        }

        return Optional.ofNullable(usuario);
    }


}

