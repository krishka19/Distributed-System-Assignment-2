import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

/**
 * VotingInterface defines the methods exposed to clients via RMI.
 * These methods allow clients to interact with the voting system.
 */
public interface VotingInterface extends Remote {
    /**
     * Registers a voter with a unique ID and name.
     * @param voterID Unique identifier for the voter.
     * @param name Name of the voter.
     * @return Confirmation message for successful registration.
     * @throws RemoteException If a remote communication error occurs.
     */
    String registerVoter(String voterID, String name) throws RemoteException;

    /**
     * Validates whether a voter is eligible to vote.
     * @param voterID The unique ID of the voter.
     * @return True if the voter is eligible, false otherwise.
     * @throws RemoteException If a remote communication error occurs.
     */
    boolean validateVoter(String voterID) throws RemoteException;

    /**
     * Allows a voter to cast their vote for a candidate.
     * @param voterID The unique ID of the voter.
     * @param candidateName The name of the candidate being voted for.
     * @return Confirmation message for successful vote submission.
     * @throws RemoteException If a remote communication error occurs.
     */
    String castVote(String voterID, String candidateName) throws RemoteException;

    /**
     * Retrieves the current voting results.
     * @return A map containing candidate names and their respective vote counts.
     * @throws RemoteException If a remote communication error occurs.
     */
    Map<String, Integer> getResults() throws RemoteException;

    /**
     * Ends the voting process and announces the winner.
     * @return A message indicating the winner of the election.
     * @throws RemoteException If a remote communication error occurs.
     */
    String endVoting() throws RemoteException;

    /**
     * Retrieves the list of candidates participating in the election.
     * @return A list of candidate names.
     * @throws RemoteException If a remote communication error occurs.
     */
    List<String> getCandidates() throws RemoteException;
}
