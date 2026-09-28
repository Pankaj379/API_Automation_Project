package payload;

public class FamilyMemberPayload {

    private int relationshipId;
    private String relationshipType;
    private String firstName;
    private String middleName;
    private String lastName;
    private String dateOfBirth;
    private String phoneNumber;
    private String bloodGroup;
    private boolean isPrimary;
    private boolean canView;
    private boolean canEdit;
    private boolean canManageConsent;

    public FamilyMemberPayload(
            int relationshipId,
            String relationshipType,
            String firstName,
            String middleName,
            String lastName,
            String dateOfBirth,
            String phoneNumber,
            String bloodGroup,
            boolean isPrimary,
            boolean canView,
            boolean canEdit,
            boolean canManageConsent) {

        this.relationshipId = relationshipId;
        this.relationshipType = relationshipType;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.bloodGroup = bloodGroup;
        this.isPrimary = isPrimary;
        this.canView = canView;
        this.canEdit = canEdit;
        this.canManageConsent = canManageConsent;
    }

    public int getRelationshipId() {
        return relationshipId;
    }

    public String getRelationshipType() {
        return relationshipType;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public boolean isPrimary() {
        return isPrimary;
    }

    public boolean isCanView() {
        return canView;
    }

    public boolean isCanEdit() {
        return canEdit;
    }

    public boolean isCanManageConsent() {
        return canManageConsent;
    }
}