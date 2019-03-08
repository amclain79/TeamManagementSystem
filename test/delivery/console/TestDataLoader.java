package delivery.console;

import entity.Profile;
import entity.Project;
import entity.Team;
import gateway.ProjectStateManager;
import model.CreateProfileRequest;
import model.ProjectTypes.*;

public class TestDataLoader {
    public static void loadTestData(){
        ProjectStateManager psm = ProjectStateManager.getInstance();
        Project p = Project.getInstance();
        for(int t = 0; t < p.maxTeams-1; t++)
            psm.saveTeam(createTeam(t));
    }

    public static Team createTeam(int t){
        ProjectStateManager psm = ProjectStateManager.getInstance();
        Team team = new Team();
        team.teamName = "Team" + t;
        Project p = Project.getInstance();
        Profile manager = createManager();
        psm.saveProfile(manager);
        for(int m = 0; m < p.maxMembers-1; m++) {
            Profile profile = createProfile(m);
            team.addMember(profile.email);
            psm.saveProfile(profile);
        }
        return team;
    }

    private static Profile createManager() {
        Profile manager = new Profile();
        manager.role = Role.MANAGER;
        manager.name = "Manager";
        manager.email = "Manager@email.com";
        manager.education = "smart";
        manager.experience = "boss";
        return manager;
    }

    public static Profile createProfile(int m){
        String name = "Member" + m;
        String email = name + "@email.com";
        String edu = "edu" + m;
        String exp = "exp" + m;
        CreateProfileRequest cpr = new CreateProfileRequest(
                name, email, edu, exp
        );
        Profile p = new Profile(cpr);
        p.role = Role.MEMBER;
        return p;
    }
}
