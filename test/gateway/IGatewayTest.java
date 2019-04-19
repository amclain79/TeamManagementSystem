package gateway;

import entity.MemberTask;
import entity.Profile;
import entity.Team;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

public class IGatewayTest {
    private class CalledGetProfile extends RuntimeException {}
    private class CalledIsFirstProfile extends RuntimeException {}
    private class CalledSaveProfile extends RuntimeException {}
    private class CalledSaveTeam extends RuntimeException {}
    private class CalledIsUniqueTeamName extends RuntimeException {}
    private class CalledGetNumTeams extends RuntimeException{}
    private class CalledGetOpenTeams extends RuntimeException{}
    private class CalledGetProfiles extends RuntimeException{}
    private class CalledGetMemberTask extends RuntimeException{}
    private class CalledSaveMemberTask extends RuntimeException{}

    private class FakeProjectStateManager implements IGateway {

        @Override
        public Profile getProfile(String e) {
            throw new CalledGetProfile();
        }

        @Override
        public boolean isFirstProfile() {
            throw new CalledIsFirstProfile();
        }
        @Override
        public void saveProfile(Profile p) {
            throw new CalledSaveProfile();
        }

        @Override
        public void saveTeam(Team t) {
            throw new CalledSaveTeam();
        }

        @Override
        public boolean isUniqueTeamName(String n) {
            throw new CalledIsUniqueTeamName();
        }

        @Override
        public int getNumTeams() {
            throw new CalledGetNumTeams();
        }

        @Override
        public List<Team> getOpenTeams() {
            throw new CalledGetOpenTeams();
        }

        @Override
        public List<Profile> getProfiles(Team t) {
            throw new CalledGetProfiles();
        }

        @Override
        public MemberTask getMemberTask(String email){throw new CalledGetMemberTask();}

        @Override
        public void saveMemberTask(MemberTask task) { throw new CalledSaveMemberTask();}
    }

    private IGateway gateway;

    @Before
    public void setup(){
        gateway = new FakeProjectStateManager();
    }

    @Test (expected = CalledGetProfile.class)
    public void getProfile(){
        Profile p = gateway.getProfile("aaron.r.mclain@gmail.com");
    }

    @Test (expected = CalledIsFirstProfile.class)
    public void isFirstProfile(){
        boolean isFirst = gateway.isFirstProfile();
    }

    @Test (expected = CalledSaveProfile.class)
    public void saveProfile(){
        gateway.saveProfile(new Profile());
    }

    @Test (expected = CalledSaveTeam.class)
    public void saveTeam(){
        gateway.saveTeam(new Team());
    }

    @Test (expected = CalledIsUniqueTeamName.class)
    public void isUniqueTeamName(){
        gateway.isUniqueTeamName("teamName");
    }

    @Test (expected = CalledGetNumTeams.class)
    public void getNumTeams(){
        int numTeams = gateway.getNumTeams();
    }

    @Test (expected = CalledGetOpenTeams.class)
    public void getOpenTeams(){
        List<Team> openTeams = gateway.getOpenTeams();
    }

    @Test (expected = CalledGetProfiles.class)
    public void getProfiles(){
        List<Profile> profiles = gateway.getProfiles(new Team());
    }

    @Test(expected = CalledGetMemberTask.class)
    public void getMemberTask(){
        MemberTask memberTask = gateway.getMemberTask("test@test.com");
    }

    @Test(expected = CalledSaveMemberTask.class)
    public void saveMemberTask(){
        gateway.saveMemberTask(new MemberTask());
    }
}
