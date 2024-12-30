import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {

    // initializing the  paptient table
    public void createTable() {
        // Drop table if it exists
        String dropTableSQL = "DROP TABLE IF EXISTS patients";

        String query = "CREATE TABLE IF NOT EXISTS patients (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(255) NOT NULL, " +
                "age INT NOT NULL, " +
                "gender VARCHAR(10) NOT NULL, " +
                "address VARCHAR(255), " +
                "phone VARCHAR(20), " +
                "email VARCHAR(255), " +
                "medical_history TEXT, " +
                "admission_date DATE);";

        try (Connection conn = DBConnection.connect();
             Statement statement = conn.createStatement()) {
            statement.executeUpdate(dropTableSQL);
            statement.executeUpdate(query);
            System.out.println("Patients table created or already exists.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // create a new patient
    public void addPatient(Patient patient){
        String query = "INSERT INTO patients (name, age, gender, address, phone, email, medical_history, admission_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.connect();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, patient.getName());
            statement.setInt(2, patient.getAge());
            statement.setString(3, patient.getGender());
            statement.setString(4, patient.getAddress());
            statement.setString(5, patient.getPhone());
            statement.setString(6, patient.getEmail());
            statement.setString(7, patient.getMedicalHistory());
            statement.setString(8, patient.getAdmissionDate());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //read a patient by id
    public Patient readPatientById(int id) {
        String query = "SELECT * FROM patients WHERE id = ?";
        try (Connection conn = DBConnection.connect();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new Patient(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("gender"),
                        rs.getString("address"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("medical_history"),
                        rs.getString("admission_date")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //update the patient details
    public void updatePatient(Patient patient){
        String query = "UPDATE patients SET name = ?, age = ?, gender = ?, address = ?, phone = ?, email = ?, medical_history = ?, admission_date = ? WHERE id = ?";
        try (Connection conn = DBConnection.connect();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, patient.getName());
            statement.setInt(2, patient.getAge());
            statement.setString(3, patient.getGender());
            statement.setString(4, patient.getAddress());
            statement.setString(5, patient.getPhone());
            statement.setString(6, patient.getEmail());
            statement.setString(7, patient.getMedicalHistory());
            statement.setString(8, patient.getAdmissionDate());
            statement.setInt(9, patient.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //delete a patient by ID
    public void deletePatient(int id){
        String query = "DELETE FROM patients WHERE id = ?";
        try (Connection conn = DBConnection.connect();
             PreparedStatement statement = conn.prepareStatement(query)){
            statement.setInt(1, id);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //get all patients
    public List<Patient> getAllPatients(){
        List<Patient> patients = new ArrayList<>();
        String query = "SELECT * FROM patients";
        try (Connection conn = DBConnection.connect();
             Statement statement = conn.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Patient patient = new Patient(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("gender"),
                        rs.getString("address"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("medical_history"),
                        rs.getString("admission_date")
                );
                patients.add(patient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patients;
    }


}
