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
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

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
    public static NominateLeadController nominateLeadController = new NominateLeadController(memberInteractor);
    public static CreateMemberFeedbackController createMemberFeedbackController = new CreateMemberFeedbackController(memberInteractor);

    //Lead
    public static LeadMenu[] leadMenu = LeadMenu.values();
    public static LeadInteractor leadInteractor = new LeadInteractor(ProjectStateManager.getInstance());
    public static ViewTeamTaskController viewTeamTaskController = new ViewTeamTaskController(leadInteractor);
    public static CreateTeamFeedbackController createTeamFeedbackController = new CreateTeamFeedbackController(leadInteractor);
    public static AssignMemberTaskController assignMemberTaskController = new AssignMemberTaskController(leadInteractor);
    public static ViewMemberFeedbackController viewMemberFeedbackController = new ViewMemberFeedbackController(leadInteractor);

    //Manager
    public static ManagerMenu[] managerMenu = ManagerMenu.values();
    public static ManagerInteractor managerInteractor = new ManagerInteractor(ProjectStateManager.getInstance());
    public static ViewTeamFeedbacksController viewTeamFeedbacksController = new ViewTeamFeedbacksController(managerInteractor);
    public static AssignTeamTaskController assignTeamTaskController = new AssignTeamTaskController(managerInteractor);
    public static AssignTeamLeadController assignTeamLeadController = new AssignTeamLeadController(managerInteractor);

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
        Team candidateTeam = nominateLeadController.getTeam(email);
        System.out.println("Member Menu");
        System.out.println("0: Logout");
        System.out.println("1: View Profile");
        System.out.println("2: View Task");
        if(!candidateTeam.hasLead())System.out.println("3: Nominate Team Lead");
        System.out.println("4: Create Feedback");
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
            case NOMINATE_LEAD:
                displayCandidates(nominateLeadController.getCandidateProfiles(email), candidateTeam);
                break;
            case CREATE_FEEDBACK:
                createFeedback();
                break;
        }
    }

    private static void createFeedback() throws IOException {
        System.out.println();
        System.out.println("Enter Your Feedback");
        String feedback = read.readLine();
        createMemberFeedbackController.createMemberFeedback(
                new MemberFeedbackRequest(
                        email, feedback
                )
        );
        System.out.println("Member Feedback Created");
    }

    private static void displayCandidates(List<Profile> candidates, Team candidateTeam) throws IOException {
        Collections.sort(candidates);
        boolean next = true;
        while(next) {
            System.out.println(String.format("%s's Candidates", candidateTeam.teamName));
            for (int i = 0; i < candidates.size(); i++) {
                System.out.println(String.format("%d: %s", i, candidates.get(i).name));
            }
            System.out.println(String.format("%d: Return to Member Menu", candidates.size()));
            int value = Integer.parseInt(read.readLine());

            if(value == candidates.size()) break;

            Profile candidate = candidates.get(value);
            next = displayCandidate(candidate, candidateTeam);
        }
    }

    private static boolean displayCandidate(Profile candidate, Team candidateTeam) throws IOException {
        System.out.println(candidate.toString());
        System.out.println("1: Nominate this candidate");
        System.out.println("2: Return to candidates list");
        int value = Integer.parseInt(read.readLine());
        boolean next = true;
        switch(value){
            case 1:
                nominateLeadController.nominateLead(
                    new NominationRequest(
                        candidate.email, candidateTeam.teamName, email
                    )
                );
                next = false;
                break;
            case 2:
                break;
        }
        return next;
    }

    private static void displayTask(MemberTask memberTask) throws IOException {
        if(memberTask != null) {
            System.out.println(memberTask.toString());
        } else {
            System.out.println("Could not find a member task.");
        }
        System.out.println("Press enter to continue.");
        read.readLine();
    }

    private static void showLeadMenu() throws IOException{
        System.out.println("Lead Menu");
        System.out.println("0: Logout");
        System.out.println("1: View Team Task");
        System.out.println("2: Create Team Feedback");
        System.out.println("3: Assign Member Task");
        System.out.println("4: View Member Feedback");
        int value = Integer.parseInt(read.readLine());
        switch(leadMenu[value]){
            case LOGOUT:
                logout = true;
                System.out.println("Logged out.");
                break;
            case VIEW_TASK:
                displayTeamTask(viewTeamTaskController.viewTeamTask(email));
                break;
            case CREATE_FEEDBACK:
                createTeamFeedback();
                break;
            case ASSIGN_TASK:
                displayMembersToAssign();
                break;
            case VIEW_FEEDBACK:
                displayMembersToView();
                break;
        }
    }

    private static void displayMembersToView() throws IOException {
        ConcurrentHashMap<String, Profile> profiles = viewMemberFeedbackController.getMemberProfiles(email);
        List<String> keys = Collections.list(profiles.keys());
        Collections.sort(keys);
        while(true){
            int menuID = 0;
            System.out.println("Members");
            for (String e : keys) {
                System.out.println(
                        String.format(
                                "%d: %s",
                                menuID++,
                                profiles.get(e).name
                        )
                );
            }
            System.out.println(
                    String.format(
                            "%d: %s",
                            menuID,
                            "Return to Lead Menu"
                    )
            );
            int value = Integer.parseInt(read.readLine());
            if(value == menuID) break;
            Profile chosenProfile = profiles.get(keys.get(value));
            viewMemberFeedback(chosenProfile);
        }
    }

    private static void viewMemberFeedback(Profile chosenProfile) throws IOException {
        System.out.println(String.format("Viewing Feedback for %s", chosenProfile.name));
        ConcurrentHashMap<String, MemberFeedback> feedbacks = viewMemberFeedbackController.viewMemberFeedback(email);
        MemberFeedback feedback = feedbacks.get(chosenProfile.email);
        if(feedback == null){
            System.out.println("Could not find member feedback.");
        } else {
            System.out.println(feedback.toString());
        }
        System.out.println("Press enter to continue...");
        read.readLine();
    }

    private static void displayMembersToAssign() throws IOException {
        ConcurrentHashMap<String, Profile> profiles = assignMemberTaskController.getMemberProfiles(email);
        List<String> keys = Collections.list(profiles.keys());
        Collections.sort(keys);
        while(true){
            int menuID = 0;
            System.out.println("Members");
            for (String e : keys) {
                System.out.println(
                        String.format(
                                "%d: %s",
                                menuID++,
                                profiles.get(e).name
                        )
                );
            }
            System.out.println(
                    String.format(
                            "%d: %s",
                            menuID,
                            "Return to Lead Menu"
                    )
            );
            int value = Integer.parseInt(read.readLine());
            if(value == menuID) break;
            Profile chosenProfile = profiles.get(keys.get(value));
            assignMemberTask(chosenProfile);
        }
    }

    private static void assignMemberTask(Profile chosenProfile) throws IOException {
        System.out.println(String.format("Assign Task To %s", chosenProfile.name));
        System.out.println("How many days to complete task?");
        int days = Integer.parseInt(read.readLine());
        System.out.println("Enter task description.");
        String description = read.readLine();
        assignMemberTaskController.assignMemberTask(
                new AssignMemberTaskRequest(
                        days, chosenProfile.email, description
                )
        );
        System.out.println("Member Task Assigned.");
    }

    private static void createTeamFeedback() throws IOException {
        System.out.println("Enter Team Feedback");
        String teamFeedback = read.readLine();
        createTeamFeedbackController.createTeamFeedback(
                new CreateTeamFeedbackRequest(
                        email, teamFeedback
                )
        );
        System.out.println("Team Feedback Created");
    }

    private static void displayTeamTask(TeamTask teamTask) {
        if(teamTask == null){
            System.out.println("Team Task Not Assigned");
        } else {
            System.out.println(teamTask.toString());
        }
    }

    private static void showManagerMenu() throws IOException{
        System.out.println("Manager Menu");
        System.out.println("0: Logout");
        System.out.println("1: View Team Feedback");
        System.out.println("2: Assign Team Task");
        System.out.println("3: Assign Team Lead");
        int value = Integer.parseInt(read.readLine());
        switch(managerMenu[value]){
            case LOGOUT:
                logout = true;
                System.out.println("Logged out.");
                break;
            case VIEW_FEEDBACK:
                ConcurrentHashMap<String, TeamFeedback> teamFeedbacks = viewTeamFeedbacksController.viewTeamFeedbacks();
                selectTeamFeedback(teamFeedbacks);
                break;
            case ASSIGN_TASK:
                displayTeamsWithLeads();
                break;
            case ASSIGN_LEAD:
                displayTeamsWithNominations();
                break;
        }
    }

    private static void displayTeamsWithNominations() throws IOException {
        ConcurrentHashMap<String, List<Profile>> nomineeProfilesByTeam = assignTeamLeadController.getNomineeProfilesByTeam();
        List<String> keys = Collections.list(nomineeProfilesByTeam.keys());
        Collections.sort(keys);
        boolean next = true;
        while(next){
            int menuID = 0;
            System.out.println("Teams With Nominations");
            for (String teamName : keys) {
                System.out.println(
                        String.format(
                                "%d: %s",
                                menuID++,
                                teamName
                        )
                );
            }
            System.out.println(
                    String.format(
                            "%d: %s",
                            menuID,
                            "Return to Manager Menu"
                    )
            );
            int value = Integer.parseInt(read.readLine());
            if(value == menuID) break;
            List<Profile> nomineeProfiles = nomineeProfilesByTeam.get(keys.get(value));
            next = displayNomineeProfiles(nomineeProfiles, keys.get(value));
        }
    }

    private static boolean displayNomineeProfiles(List<Profile> nomineeProfiles, String teamName) throws IOException {
        Collections.sort(nomineeProfiles);
        boolean next = true;
        while(next){
            int menuID = 0;
            System.out.println(String.format("%s's Nominees", teamName));
            for (Profile p : nomineeProfiles) {
                System.out.println(
                        String.format(
                                "%d: %s",
                                menuID++,
                                p.name
                        )
                );
            }
            System.out.println(
                    String.format(
                            "%d: %s",
                            menuID,
                            "Return to Team List"
                    )
            );
            int value = Integer.parseInt(read.readLine());
            if(value == menuID) break;
            Profile nomineeProfile = nomineeProfiles.get(value);
            next = displayNomineeProfile(nomineeProfile, teamName);
        }

        return next;
    }

    private static boolean displayNomineeProfile(Profile nomineeProfile, String teamName) throws IOException {
        boolean next = true;
        System.out.println(nomineeProfile.toString());
        System.out.println("0: Assign Team Lead");
        System.out.println("1: Return to Nominee Profiles");
        int value = Integer.parseInt(read.readLine());
        if(value == 0){
            assignTeamLeadController.assignTeamLead(
                    new AssignTeamLeadRequest(
                            nomineeProfile, teamName
                    )
            );
            next = false;
        }
        return next;
    }


    private static void displayTeamsWithLeads() throws IOException {
        List<Team> teamsWithLeads = assignTeamTaskController.getTeamsWithLeads();
        Collections.sort(teamsWithLeads);
        int lastSelection = teamsWithLeads.size();
        while(true){
            System.out.println("Teams With Leads");
            for(int i = 0; i < teamsWithLeads.size(); i++){
                System.out.println(String.format("%d: %s", i, teamsWithLeads.get(i).teamName));
            }
            System.out.println(String.format("%d: Return to Manager Menu", lastSelection));
            int selection = Integer.parseInt(read.readLine());

            if(selection == lastSelection) break;

            Team selectedTeam = teamsWithLeads.get(selection);

            assignTeamTask(selectedTeam);
        }
    }

    private static void assignTeamTask(Team selectedTeam) throws IOException {
        System.out.println("Enter task description.");
        String description = read.readLine();
        System.out.println("Enter number of days to complete task.");
        LocalDate dueDate = LocalDate.now().plusDays(Integer.parseInt(read.readLine()));
        assignTeamTaskController.assignTeamTask(
                new TeamTaskRequest(description, selectedTeam.teamName, dueDate)
        );
    }

    private static void selectTeamFeedback(ConcurrentHashMap<String, TeamFeedback> teamFeedbacks) throws IOException {
        List<String> keys = Collections.list(teamFeedbacks.keys());
        Collections.sort(keys);
        while(true){
            int menuID = 0;
            System.out.println("Teams With Feedback");
            for (String teamName : keys) {
                System.out.println(
                        String.format(
                                "%d: %s",
                                menuID++,
                                teamName
                        )
                );
            }
            System.out.println(
                    String.format(
                            "%d: %s",
                            menuID,
                            "Return to Manager Menu"
                    )
            );
            int value = Integer.parseInt(read.readLine());
            if(value == menuID) break;
            TeamFeedback feedback = teamFeedbacks.get(keys.get(value));
            displayTeamFeedback(feedback);
        }
    }

    private static void displayTeamFeedback(TeamFeedback teamFeedback) {
        System.out.println(teamFeedback.toString());
    }
}
