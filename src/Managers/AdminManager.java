package Managers;

public interface AdminManager {
    void removeLearner(String userId);

    void removeCreator(String userId);

    void removeAdmin(String adminId);

    void changeLearnerPassword(String newPassword, String userId);

    void changeCreatorPassword(String newPassword, String userId);
}
