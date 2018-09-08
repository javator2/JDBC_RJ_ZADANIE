package Resolver;

import Model.Student;
import config.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StudentResolver extends AbstractResolver<Student> {

    private Connection connection = Database.getConnection();

    @Override
    public Student get(int id) {
        return null;
    }

    @Override
    public List<Student> get() {
        List<Student> listaStudentow = new ArrayList<>();
        try {

            Statement statement = connection.createStatement();
            String sql = " select * from student";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String lastname = resultSet.getString("lastname");
                listaStudentow.add(new Student(id, name, lastname));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaStudentow;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        String sql = "delete from student where id =" + id;

        Statement stm = null;
        try {
            stm = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stm.execute(sql);
    }

    @Override
    public boolean insert(Map<String, String> params) {
        return false;
    }

    @Override
    public boolean update(int id, Map<String, String> params) {
        return false;
    }
}
