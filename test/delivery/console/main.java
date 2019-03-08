package delivery.console;

import controller.*;
import entity.Profile;
import entity.Team;
import gateway.ProjectStateManager;
import interactor.PersonInteractor;
import interactor.UserInteractor;
import model.CreateProfileRequest;
import model.CreateProjectRequest;
import model.CreateTeamRequest;
import model.ProjectTypes.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

//Temporary delivery system via the console.
public class main {

    public static BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
    //All
    public static String email;

    //Person
    public static PersonInteractor personInteractor = new PersonInteractor(ProjectStateManager.getInstance());
    public static LoginController loginController = new LoginController(personInteractor);


    //User
    public static boolean logout = false;
    public static UserMenu[] userMenu = UserMenu.values();
    public static UserInteractor userInteractor = new UserInteractor(ProjectStateManager.getInstance());
    public static CreateProjectController createProjectController = new CreateProjectController(userInteractor);
    public static CreateProfileController createProfileController = new CreateProfileController(userInteractor);
    public static CreateTeamController createTeamController = new CreateTeamController(userInteractor);
    public static QueryController queryController = new QueryController(userInteractor);
    public static JoinTeamController joinTeamController = new JoinTeamController(userInteractor);

    //Member
    public static MemberMenu[] memberMenu = MemberMenu.values();

    //Manager
    public static ManagerMenu[] managerMenu = ManagerMenu.values();

    public static void main(String[] args) throws IOException {
        createProject();
        TestDataLoader.loadTestData();
        login();
    }

    private static void createProject() throws IOException {

        System.out.println("Enter the maximum number of teams for the project.");
        int maxTeams = Integer.parseInt(read.readLine());
        System.out.println("Enter the maximum number of members per team.");
        int maxMembers = Integer.parseInt(read.readLine());
        System.out.println("Enter the minimum number of weekly project feedback.");
        int minFeedbacks = Integer.parseInt(read.readLine());
        CreateProjectRequest cpr = new CreateProjectRequest(
                maxTeams, maxMembers, minFeedbacks
        );

        createProjectController.createProject(cpr);

        System.out.println("Project Created.");
    }

    private static void login() throws IOException {

        while(true) {
            System.out.println("Enter your email to login.");
            email = read.readLine();

            Role role = loginController.login(email);

            logout = false;
            while (!logout) {
                switch (role) {
                    case PERSON:
                        role = createProfile(email);
                        break;
                    case USER:
                        role = showUserMenu();
                        break;
                    case MEMBER:
                        showMemberMenu();
                        break;
                    case LEAD:
                        showLeadMenu();
                        break;
                    case MANAGER:
                        showManagerMenu();
                }
            }
        }
    }

    private static Role createProfile(String email) throws IOException{
        System.out.println("First time login. Creating profile.");
        System.out.println("Enter your first and last name.");
        String name = read.readLine();
        System.out.println("Enter your education.");
        String education = read.readLine();
        System.out.println("Enter your experience.");
        String experience = read.readLine();
        CreateProfileRequest cpr = new CreateProfileRequest(
                name, email, education, experience
        );

        Role r = createProfileController.createProfile(cpr);

        System.out.println("Profile Created.");
        return r;
    }

    private static Role showUserMenu() throws IOException{
        Role role = Role.USER;
        boolean isMaxTeams = queryController.isMaxTeams();
        boolean areTeamsFull = queryController.areTeamsFull();
        System.out.println("User Menu");
        System.out.println("0: Logout");
        if(!isMaxTeams) System.out.println("1: Create Team");
        if(!areTeamsFull) System.out.println("2: Join Team");
        int value = Integer.parseInt(read.readLine());
        switch(userMenu[value]){
            case LOGOUT:
                logout = true;
                System.out.println("Logged out.");
                break;
            case CREATE_TEAM:
                if(!isMaxTeams) role = createTeam();
                break;
            case JOIN_TEAM:
                if(!areTeamsFull) role = joinTeam();
                break;
        }
        return role;
    }

    private static Role createTeam() throws IOException{
        System.out.println("Enter a unique team name.");
        String teamName = read.readLine();
        CreateTeamRequest r = new CreateTeamRequest(
                teamName, email
        );
        createTeamController.createTeam(r);
        System.out.println("Team created.");
        System.out.println("Member added.");
        return Role.MEMBER;
    }

    private static Role joinTeam() throws IOException{
        Role role = Role.USER;
        List<Team> openTeams = joinTeamController.getOpenTeams();
        System.out.println("Open Teams");
        displayOpenTeams(openTeams);
        return Role.MEMBER;
    }

    private static void displayOpenTeams(List<Team> openTeams) throws IOException{
        Collections.sort(openTeams);
        for(int i = 0; i < openTeams.size(); i++)
            System.out.println(String.format("%d: %s", i, openTeams.get(i).teamName));
        int value = Integer.parseInt(read.readLine());
        Team chosenTeam = openTeams.get(value);
        System.out.println(String.format("%s's Members", chosenTeam.teamName));
        List<Profile> profiles = joinTeamController.getProfiles(chosenTeam);
        displayTeamsProfiles(profiles);
    }

    private static void displayTeamsProfiles(List<Profile> profiles) throws IOException{
        Collections.sort(profiles);
        for(int i = 0; i < profiles.size(); i++)
            System.out.println(String.format("%d: %s", i, profiles.get(i).name));
        int value = Integer.parseInt(read.readLine());
        Profile chosenProfile = profiles.get(value);
        Profile profile = joinTeamController.getProfile(chosenProfile);
        displayProfile(profile);
    }

    private static void displayProfile(Profile profile) throws IOException{
        System.out.println(profile.toString());
        int value = Integer.parseInt(read.readLine());
    }

    private static void showMemberMenu() throws IOException{
        System.out.println("Member Menu");
        System.out.println("0: Logout");
        int value = Integer.parseInt(read.readLine());
        switch(memberMenu[value]){
            case LOGOUT:
                logout = true;
                System.out.println("Logged out.");
                break;
        }
    }

    private static void showLeadMenu() throws IOException{
        System.out.println("Lead Menu");
        read.readLine();
    }

    private static void showManagerMenu() throws IOException{
        System.out.println("Manager Menu");
        System.out.println("0: Logout");
        int value = Integer.parseInt(read.readLine());
        switch(managerMenu[value]){
            case LOGOUT:
                logout = true;
                System.out.println("Logged out.");
                break;
        }
    }
}
