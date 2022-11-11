package UI;

import Core.Course.Course;
import Core.Users.Admin;
import Managers.UIManager;
import UI.Initializers.UserInitializer;
import UI.Utilities.CustomScanner;

import java.util.ArrayList;
import java.util.Scanner;

public class AdminOperations {
    private Admin currentAdmin;
    private UIManager uiManager = new UIManager();

    AdminOperations(Admin admin){
        this.currentAdmin = admin;
    }

    public void adminDashBoard(){
        while(true){
            System.out.println("********* Zlearn Control Center *********");
            System.out.println("1. User Control\n2. Courses Control\n3. Zlearn Courses\n0. Log out");
            String controlOption = CustomScanner.getOptions("1230");
            if(controlOption.equals("1")){
                openUserControl();
            }
            if(controlOption.equals("2")){
                openCourseControl();
            }
            if(controlOption.equals("3")){
                openZlearnCoursesControl();
            }
            if(controlOption.equals("0")){
                break;
            }
        }

    }

    private void openZlearnCoursesControl() {
    }

    private void openCourseControl() {
        while(true) {
            System.out.println("1. View All Course\n2.Add Course Category\n3.Remove Course Category\n0.Back");
            String inputOption = CustomScanner.getOptions("1230");
            if(inputOption.equals("1")){
                ArrayList<Course> courses = uiManager.getAllCourses();
                for(int i=1;i<=courses.size();i++){
                    System.out.println("["+i+"] "+courses.get(i-1).getCourseId()+"  "+courses.get(i-1).getCourseName());
                }
                int courseIndex = CustomScanner.getIntegetInput(1,courses.size()) - 1;
                viewCourseDetails(courses.get(courseIndex));
            }
            if(inputOption.equals("2")){
                System.out.println("Enter new Category : ");
                String newCategory = new Scanner(System.in).nextLine();
                currentAdmin.addCategoryToAllCategories(newCategory);
                System.out.println("Category Added !!!");
            }
            if(inputOption.equals("3")){
                System.out.println("Select Category to Delete : ");
                ArrayList<String> categories = uiManager.getCategories();
                for(int i=1;i<=categories.size();i++){
                    System.out.println("["+i+"] "+categories.get(i-1));
                }
                int categoryIndex = CustomScanner.getIntegetInput(1,categories.size());
                currentAdmin.deleteCategoryFromAllCategories(categories.get(categoryIndex-1));
            }
            if(inputOption.equals("0")){ break; }
        }
    }

    private void viewCourseDetails(Course course) {
        System.out.println("****** " + course.getCourseName() + " *******");
        System.out.println("Creator :" + uiManager.getCreatorName(course.getCreatorId()));
        System.out.println("Rating :" + course.getRating());
        System.out.println("Price :" + (course.getPrice() == 0 ? "Free" : course.getPrice()));
        System.out.println("-What You'll Learn-");
        ArrayList<String> learnings = course.getCourseLearnings();
        for (int i = 1; i < learnings.size(); i++) {
            System.out.println("[" + i + "] " + learnings.get(i - 1));
        }
        System.out.println("\n0. back");
        CustomScanner.getOptions("0");
    }

    private void openUserControl() {
        while(true) {
            System.out.println("******* User Control *******");
            System.out.println("- [R]emove User\n- change user [P]assword\n0. Back");
            String controlOption = CustomScanner.getOptions("rRpP0");
            if (controlOption.equals("r") || controlOption.equals("R")) {
                System.out.println("Remove [L]earner\nRemove [C]reator");
                String userType = CustomScanner.getOptions("LlCc");
                if(userType.equals("L") || userType.equals("l")){
                    removeLearner();
                }
                if(userType.equals("C") || userType.equals("c")){
                    removeCreator();
                }
            }
            if (controlOption.equals("p") || controlOption.equals("P")) {
                System.out.println("[L]earner password\n[C]reator password");
                String userType = CustomScanner.getOptions("LlCc");
                if(userType.equals("L") || userType.equals("l")){
                    changeLearnerPassword();
                }
                if(userType.equals("C") || userType.equals("c")){
                    changeCreatorPassword();
                }

            }
            if (controlOption.equals("0")) {
                break;
            }
        }
    }

    private void changeCreatorPassword() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Creator UserName : ");
        String userName = sc.next();
        System.out.println("Enter New Password : ");
        String newPassword = sc.next();
        currentAdmin.changeCreatorPassword(newPassword,userName);
    }

    private void changeLearnerPassword() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Learner UserName : ");
        String userName = sc.next();
        System.out.println("Enter New Password : ");
        String newPassword = sc.next();
        currentAdmin.changeLearnerPassword(newPassword,userName);
    }


    private void removeCreator() {
        System.out.println("Enter Creator UserName : ");
        String userName = new Scanner(System.in).next();
        currentAdmin.removeCreator(userName);
    }

    private void removeLearner() {
        System.out.println("Enter Learner UserName : ");
        String userName = new Scanner(System.in).next();
        currentAdmin.removeLearner(userName);
    }
}
