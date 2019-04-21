package delivery.console;

import controller.*;
import entity.*;
import gateway.ProjectStateManager;
import interactor.*;
import model.*;
import model.ProjectTypes.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

//Temporary delivery system via the console.
public class main {

    public static BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

    //All
    public static String email;

    //Person
    public static PersonInteractor personInteractor = new PersonInteractor(ProjectStateManager.getInstance());
    public static LoginController loginController = new LoginController(personInteractor);
    public static boolean logout = false;

    //User
    public static UserMenu[] userMenu = UserMenu.values();
    public static UserInteractor userInteractor = new UserInteractor(ProjectStateManager.getInstance());
    public static CreateProjectController createProjectController = new CreateProjectController(userInteractor);
    public static CreateProfileController createProfileController = new CreateProfileController(userInteractor);
    public static CreateTeamController createTeamController = new CreateTeamController(userInteractor);
    public static QueryController queryController = new QueryController(userInteractor);
    public static JoinTeamController joinTeamController = new JoinTeamController(userInteractor);

    //Member
    public static MemberMenu[] memberMenu = MemberMenu.values();
    public static MemberInteractor memberInteractor = new MemberInteractor(ProjectStateManager.getInstance());
    public static ViewProfileController viewProfileController = new ViewProfileController(memberInteractor);
    public static ViewMemberTaskController viewMemberTaskController = new ViewMemberTaskController(memberInteractor);

    //Lead
    public static LeadMenu[] leadMenu = LeadMenu.values();
    public static LeadInteractor leadInteractor = new LeadInteractor(ProjectStateManager.getInstance());
    public static ViewTeamTaskController viewTeamTaskController = new ViewTeamTaskController(leadInteractor);

    //Manager
    public static ManagerMenu[] managerMenu = ManagerMenu.values();
    public static ManagerInteractor managerInteractor = new ManagerInteractor(ProjectStateManager.getInstance());
    public static ViewTeamFeedbacksController viewTeamFeedbacksController = new ViewTeamFeedbacksController(managerInteractor);

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
        List<Team> openTeams = joinTeamController.getOpenTeams();
        return displayOpenTeams(openTeams);
    }

    private static Role displayOpenTeams(List<Team> openTeams) throws IOException{
        Collections.sort(openTeams);
        Role result = Role.USER;
        while(true) {
            System.out.println("Open Teams");
            for (int i = 0; i < openTeams.size(); i++)
                System.out.println(String.format("%d: %s", i, openTeams.get(i).teamName));
            System.out.println(String.format("%d: Return to User Menu", openTeams.size()));
            int value = Integer.parseInt(read.readLine());

            if (value == openTeams.size())
                break;

            Team chosenTeam = openTeams.get(value);
            List<Profile> profiles = joinTeamController.getProfiles(chosenTeam);
            result = displayChosenTeamProfiles(profiles, chosenTeam);

            if(result == Role.MEMBER)
                break;
        }

        return result;
    }

    private static Role displayChosenTeamProfiles(List<Profile> profiles, Team chosenTeam) throws IOException{
        Role result = Role.USER;
        Collections.sort(profiles);
        while(true) {
            System.out.println(String.format("%s's Members", chosenTeam.teamName));
            for (int i = 0; i < profiles.size(); i++)
                System.out.println(String.format("%d: %s", i, profiles.get(i).name));
            System.out.println(String.format("%d: Join Team", profiles.size()));
            System.out.println(String.format("%d: Return to Team List", profiles.size()+1));
            int value = Integer.parseInt(read.readLine());

            if (value == profiles.size()) {
                joinTeamController.joinTeam(new JoinTeamRequest(chosenTeam, email));
                System.out.println("Member added to " + chosenTeam.teamName);
                result = Role.MEMBER;
                break;
            }

            if(value == profiles.size()+1)
                break;

            Profile chosenProfile = profiles.get(value);
            displayProfile(chosenProfile);
        }
        return result;
    }

    private static void displayProfile(Profile profile) throws IOException{
        System.out.println(profile.toString());
        System.out.println("Press enter to continue.");
        read.readLine();
    }

    private static void showMemberMenu() throws IOException{
        System.out.println("Member Menu");
        System.out.println("0: Logout");
        System.out.println("1: View Profile");
        System.out.println("2: View Task");
        int value = Integer.parseInt(read.readLine());
        switch(memberMenu[value]){
            case LOGOUT:
                logout = true;
                System.out.println("Logged out.");
                break;
            case VIEW_PROFILE:
                displayProfile(viewProfileController.viewProfile(email));
                break;
            case VIEW_TASK:
                displayTask(viewMemberTaskController.viewMemberTask(email));
                break;
        }
    }

    private static void displayTask(MemberTask memberTask) throws IOException {
        if(memberTask != null)
            System.out.println(memberTask.toString());
        else
            System.out.println("Could not find a member task.");
        System.out.println("Press enter to continue.");
        read.readLine();
    }

    private static void showLeadMenu() throws IOException{
        System.out.println("Lead Menu");
        System.out.println("0: Logout");
        System.out.println("1: View Team Task");
        int value = Integer.parseInt(read.readLine());
        switch(leadMenu[value]){
            case LOGOUT:
                logout = true;
                System.out.println("Logged out.");
                break;
            case VIEW_TASK:
                displayTeamTask(viewTeamTaskController.viewTeamTask(email));
                break;
        }
    }

    private static void displayTeamTask(TeamTask teamTask) {
        System.out.println(teamTask.toString());
    }

    private static void showManagerMenu() throws IOException{
        System.out.println("Manager Menu");
        System.out.println("0: Logout");
        System.out.println("1: View Team Feedback");
        int value = Integer.parseInt(read.readLine());
        switch(managerMenu[value]){
            case LOGOUT:
                logout = true;
                System.out.println("Logged out.");
                break;
            case VIEW_FEEDBACK:
                ConcurrentHashMap<String, TeamFeedback> teamFeedbacks = viewTeamFeedbacksController.viewTeamFeedbacks();
                displayTeamFeedback(selectTeamFeedback(teamFeedbacks));
                break;
        }
    }

    private static TeamFeedback selectTeamFeedback(ConcurrentHashMap<String, TeamFeedback> teamFeedbacks) throws IOException {
        List<String> keys = Collections.list(teamFeedbacks.keys());
        Collections.sort(keys);
        int menuID = 0;
        for(String teamName : keys){
            System.out.println(
                String.format(
                        "%d: %s",
                        menuID++,
                        teamName
                )
            );
        }
        int value = Integer.parseInt(read.readLine());
        return teamFeedbacks.get(keys.get(value));
    }

    private static void displayTeamFeedback(TeamFeedback teamFeedback) {
        System.out.println(teamFeedback.toString());
    }
}
