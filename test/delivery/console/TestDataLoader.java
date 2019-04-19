package delivery.console;

import entity.MemberTask;
import entity.Profile;
import entity.Project;
import entity.Team;
import gateway.ProjectStateManager;
import model.CreateProfileRequest;
import model.ProjectTypes.*;

import java.util.Date;

public class TestDataLoader {
    public static ProjectStateManager psm = ProjectStateManager.getInstance();
    public static Project p = Project.getInstance();
    static int countTeam = 0;
    static int countProfile = 0;
    static int countTask = 0;

    public static void loadTestData(){
        for(int t = 0; t < p.maxTeams-1; t++)
            psm.saveTeam(createTeam(countTeam++));
    }

    public static Team createTeam(int t){
        Team team = new Team();
        team.teamName = "Team" + t;
        Profile manager = createManager();
        psm.saveProfile(manager);
        for(int m = 0; m < p.maxMembers-1; m++) {
            Profile profile = createProfile(countProfile++);
            team.addMember(profile.email);
            MemberTask task = createMemberTask(countTask++, profile.email);
            psm.saveProfile(profile);
            psm.saveMemberTask(task);
        }
        return team;
    }

    private static MemberTask createMemberTask(int i, String e) {
        MemberTask t = new MemberTask();
        t.memberEmail = e;
        t.date = new Date();
        t.description = "Task" + i;
        return t;
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
