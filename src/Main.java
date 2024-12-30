import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        Scanner scanner = new Scanner(System.in);
        PatientDAO patientDAO = new PatientDAO();

        patientDAO.createTable();

        // Create some static Patient objects
        Patient patient1 = new Patient(1, "Devinda", 30, "Male", "123 Park St", "0715432215", "devinda@zone.com", "None", "2024-01-01");
        Patient patient2 = new Patient(2, "Sandali", 25, "Female", "456 Oak St", "0716359966", "sandali@zone.com", "Asthma", "2024-01-10");
        Patient patient3 = new Patient(3, "Disini", 45, "Female", "789 Main St", "0729035512", "disini@zone.com", "Diabetes", "2024-01-12");
        Patient patient4 = new Patient(4, "Dulmin", 60, "Male", "101 Chathem St", "0758649542", "dulmin@zone.com", "Hypertension", "2024-01-15");
        Patient patient5 = new Patient(5, "Hansi", 32, "Female", "202 Beach St", "0746353363", "hansi@zone.com", "None", "2024-01-20");
        Patient patient6 = new Patient(6, "Tharusha", 50, "Male", "303 Palm St", "0787051693", "tharusha@zone.com", "None", "2024-01-25");

        while (true){
            System.out.println("Choose an operation:");
            System.out.println("1. Add Patient");
            System.out.println("2. Get Patient by ID");
            System.out.println("3. Update Patient");
            System.out.println("4. Delete Patient");
            System.out.println("5. List all Patients");
            System.out.println("6. Exit");

           int choice = scanner.nextInt();
           scanner.nextLine();

           switch(choice){
               case 1:
                   // Static Add Patient operation
                   System.out.println("Adding new patients");
                   // We add static patient objects to the DAO
                   patientDAO.addPatient(patient1);
                   patientDAO.addPatient(patient2);
                   patientDAO.addPatient(patient3);
                   patientDAO.addPatient(patient4);
                   patientDAO.addPatient(patient5);
                   patientDAO.addPatient(patient6);
                   System.out.println("Patients added.");
                   break;

               case 2:
                   // Static Get Patient by ID operation
                   System.out.print("Enter patient ID to retrieve: ");
                   int id = scanner.nextInt();
                   Patient foundPatient = patientDAO.readPatientById(id);
                   if (foundPatient != null) {
                       System.out.println("Patient found: " + foundPatient);
                   } else {
                       System.out.println("Patient with ID " + id + " not found.");
                   }
                   break;

               case 3:
                   // Static Update Patient details
                   System.out.print("Updating patient record: ");
                   Patient updatedPatient = new Patient(5, "Hansi", 32, "Female", "202 Beach St", "0776353377", "hansi@zone.com", "None", "2024-01-20");
                   patientDAO.updatePatient(updatedPatient);
                   System.out.println("Patient updated.");
                   break;

               case 4:
                   //delete a patient
                   System.out.print("Enter the ID of the patient to delete: ");
                   int deleteId = scanner.nextInt();
                   patientDAO.deletePatient(deleteId);
                   System.out.println("Patient with ID " + deleteId + " deleted.");
                   break;

               case 5:
                   // Static List all Patients operation
                   System.out.println("Listing all patients:");
                   List<Patient> patients = patientDAO.getAllPatients();
                   patients.forEach(System.out::println);
                   break;

               case 6:
                   System.out.println("Exiting program.");
                   scanner.close();
                   return;  // Exit the loop and program

               default:
                   System.out.println("Invalid option. Please choose again.");
           }
        }

    }
}