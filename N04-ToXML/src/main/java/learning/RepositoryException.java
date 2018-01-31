package learning;


public class RepositoryException extends Exception {

  private static final long serialVersionUID = 2502106090589796819L;

  public RepositoryException(){}
  
  public RepositoryException(Throwable t) {
    super(t);
  }
  
  public RepositoryException(String message, Throwable t) {
    super(message, t);
  }
  
}
