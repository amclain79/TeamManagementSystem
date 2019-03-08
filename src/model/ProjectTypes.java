package model;

public class ProjectTypes {
    public enum Role{
        PERSON(0),
        USER(1),
        MEMBER(2),
        LEAD(3),
        MANAGER(4);
        private final int value;
        Role(int v){ value = v; }
        public int getValue() { return value; }
    }

    public enum ManagerMenu{
        LOGOUT(0);
        private final int value;
        ManagerMenu(int v){ value = v; }
        public int getValue() { return value; }
    }

    public enum UserMenu{
        LOGOUT(0),
        CREATE_TEAM(1),
        JOIN_TEAM(2);
        private final int value;
        UserMenu(int v){ value = v; }
        public int getValue() { return value; }
    }

    public enum MemberMenu{
        LOGOUT(0);
        private final int value;
        MemberMenu(int v){ value = v; }
        public int getValue() { return value; }
    }
}
