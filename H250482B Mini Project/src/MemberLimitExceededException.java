/** Checked exception: a member already holds the maximum number of loans. */
public class MemberLimitExceededException extends Exception {
    public MemberLimitExceededException(String memberId, int max) {
        super("Member " + memberId + " already has the maximum of " + max + " loans");
    }
}
