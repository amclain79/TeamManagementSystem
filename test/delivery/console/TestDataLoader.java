package delivery.console;

import entity.*;
import gateway.ProjectStateManager;
import model.CreateProfileRequest;
import model.ProjectTypes.*;
import java.time.LocalDate;

public class TestDataLoader {
    public static ProjectStateManager psm = ProjectStateManager.getInstance();
    public static Project p = Project.getInstance();
    static int countTeam = 0;
    static int countProfile = 0;
    static int countTask = 0;
    static int countNomination = 0;
    static int countMemberFeedback = 0;

    public static void loadTestData(){
        Profile manager = createManagerProfile();
        psm.saveProfile(manager);
        for(int t = 0; t < p.maxTeams-1; t++)
            psm.saveTeam(createTeam(countTeam++));
    }

    private static Profile createManagerProfile() {
        String name = "Manager0";
        Profile p = createProfile(0, name);
        p.role = Role.MANAGER;
        return p;
    }

    private static Profile createProfile(int m, String name) {
        String email = name + "@email.com";
        String edu = "edu" + m;
        String exp = "exp" + m;
        CreateProfileRequest cpr = new CreateProfileRequest(
                name, email, edu, exp
        );
        Profile p = new Profile(cpr);
        return p;
    }

    public static Team createTeam(int t){
        Team team = new Team();
        team.teamName = "Team" + t;
        if(!(t == 0)) {
            Profile lead = createTeamLead(t, team);
            createTeamTask(team, lead);
            createTeamFeedback(t, team);
        }
        for(int m = 0; m < p.maxMembers-2; m++) {
            Profile profile = createMemberProfile(countProfile++);
            team.addMember(profile.email);
            psm.saveProfile(profile);
            createMemberTask(countTask++, profile.email);
            createNomination(countNomination++, profile.email, team.teamName);
            createMemberFeedback(countMemberFeedback++, profile.email);
        }
        return team;
    }

    private static void createNomination(int n, String nominee, String teamName){
        Nomination nomination = new Nomination(nominee, teamName, "nominator" + n + "@email.com");
        psm.saveNomination(nomination);
    }

    private static Profile createTeamLead(int t, Team team) {
        Profile lead = createLeadProfile(t);
        team.addMember(lead.email);
        team.assignTeamLead(lead.email);
        psm.saveProfile(lead);
        return lead;
    }

    public static Profile createLeadProfile(int t){
        String name = "Lead" + t;
        Profile p = createProfile(t, name);
        p.role = Role.LEAD;
        return p;
    }

    private static void createTeamTask(Team team, Profile lead) {
        TeamTask teamTask = new TeamTask();
        teamTask.teamName = team.teamName;
        teamTask.dueDate = LocalDate.now();
        teamTask.description = "Description";
        psm.saveTeamTask(teamTask);
    }

    private static void createTeamFeedback(int t, Team team) {
        TeamFeedback teamFeedback = new TeamFeedback();
        teamFeedback.date = LocalDate.now();
        teamFeedback.teamName = team.teamName;
        teamFeedback.feedback = "feedback" + t;
        psm.saveTeamFeedback(teamFeedback);
    }

    private static void createMemberFeedback(int f, String member) {
        MemberFeedback memberFeedback = new MemberFeedback();
        memberFeedback.date = LocalDate.now();
        memberFeedback.memberEmail = member;
        memberFeedback.feedback = "feedback" + f;
        psm.saveMemberFeedback(memberFeedback);
    }

    public static Profile createMemberProfile(int m){
        String name = "Member" + m;
        Profile p = createProfile(m, name);
        p.role = Role.MEMBER;
        return p;
    }

    private static void createMemberTask(int i, String e) {
        MemberTask memberTask = new MemberTask();
        memberTask.memberEmail = e;
        memberTask.dueDate = LocalDate.now();
        memberTask.description = "Task" + i;
        psm.saveMemberTask(memberTask);
    }
}
