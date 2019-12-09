package org.victoryfoundation.sportsapp.domains;

public enum ActionType {
    CHECK_IN("CHECK-IN"),
    CHECK_OUT("CHECK-OUT"),
    DIET("DIET"),
    PRACTICE_MATCH("PRACTICE MATCH"),
    TRAINING("TRAINING"),
    GROUND_MARKING("GROUND_MARKING"),
    PROPS("PROPS"),
    ATTENDANCE("ATTENDANCE");

    private final String type;

    private ActionType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
