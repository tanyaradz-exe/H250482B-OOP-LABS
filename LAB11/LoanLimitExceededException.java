public class LoanLimitExceededException extends LibraryException {
    public LoanLimitExceededException(String memberName, int limit) {
        super(memberName + " already has " + limit + " items on loan (maximum " + limit + ").");
    }
}
