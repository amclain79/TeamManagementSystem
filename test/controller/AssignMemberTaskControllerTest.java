package controller;

import boundary.ILead;
import entity.Profile;
import entity.TeamTask;
import model.AssignMemberTaskRequest;
import model.CreateTeamFeedbackRequest;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ConcurrentHashMap;

import static org.junit.Assert.assertNotNull;

public class AssignMemberTaskControllerTest {
    private class FakeLeadInteractor implements ILead{

        @Override
        public TeamTask viewTeamTask(String e) {
            return null;
        }

        @Override
        public void createTeamFeedback(CreateTeamFeedbackRequest cfr) {

        }

        @Override
        public void assignMemberTask(AssignMemberTaskRequest mtr) {
            assignMemberTaskRequest = mtr;
        }

        @Override
        public ConcurrentHashMap<String, Profile> getMemberProfiles(String e) {
            return new ConcurrentHashMap<>();
        }
    }

    private AssignMemberTaskController controller;
    private static AssignMemberTaskRequest assignMemberTaskRequest;

    @Before
    public void setup(){
        controller = new AssignMemberTaskController(new FakeLeadInteractor());
    }

    @Test
    public void assignMemberTask(){
        controller.assignMemberTask(
                new AssignMemberTaskRequest(
                        5, "member@email.com", "description"
                )
        );
        assertNotNull(assignMemberTaskRequest);
    }

    @Test (expected = AssignMemberTaskController.InvalidDueDate.class)
    public void assignMemberTask_InvalidDueDate(){
        controller.assignMemberTask(
                new AssignMemberTaskRequest(
                        0, "member@email.com", "description"
                )
        );
    }

    @Test (expected = AssignMemberTaskController.InvalidDescription.class)
    public void assignMemberTask_InvalidDescription(){
        controller.assignMemberTask(
                new AssignMemberTaskRequest(
                        4, "member@email.com", ""
                )
        );
    }

    @Test
    public void getMemberProfiles(){
        ConcurrentHashMap<String, Profile> memberProfiles = controller.getMemberProfiles("lead@email.com");
        assertNotNull(memberProfiles);
    }
}
