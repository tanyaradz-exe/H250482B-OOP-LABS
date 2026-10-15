// Base class so callers can catch every library problem with one catch if they want
public class LibraryException extends Exception {
    public LibraryException(String message) { super(message); }
}
