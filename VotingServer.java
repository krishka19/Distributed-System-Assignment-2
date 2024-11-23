import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * VotingServer implements the VotingInterface.
 * This class contains the logic for managing voter registration, votes, and results.
 */
public class VotingServer extends UnicastRemoteObject implements VotingInterface {
    private Map<String, String> registeredVoters; // Maps voter ID to voter name
    private Map<String, String> votes;           // Maps voter ID to candidate name
    private Map<String, Integer> candidates;     // Maps candidate name to vote count

    /**
     * Constructor to initialize data structures and candidates.
     * @throws RemoteException If a remote communication error occurs.
     */
    protected VotingServer() throws RemoteException {
        registeredVoters = new HashMap<>();
        votes = new HashMap<>();
        candidates = new HashMap<>();
        candidates.put("Candidate A", 0);
        candidates.put("Candidate B", 0);
        candidates.put("Candidate C", 0);
    }

    @Override
    public synchronized String registerVoter(String voterID, String name) throws RemoteException {
        if (registeredVoters.containsKey(voterID)) {
            return "Voter already registered.";
        }
        registeredVoters.put(voterID, name);
        System.out.println("New voter registered: " + name + " (ID: " + voterID + ")");
        return "Voter registered successfully.";
    }

    @Override
    public synchronized boolean validateVoter(String voterID) throws RemoteException {
        return registeredVoters.containsKey(voterID) && !votes.containsKey(voterID);
    }

    @Override
    public synchronized String castVote(String voterID, String candidateName) throws RemoteException {
        if (!validateVoter(voterID)) {
            return "You are not eligible to vote or have already voted.";
        }
        if (!candidates.containsKey(candidateName)) {
            return "Invalid candidate.";
        }
        votes.put(voterID, candidateName);
        candidates.put(candidateName, candidates.get(candidateName) + 1);
        System.out.println("Vote cast by Voter ID: " + voterID + " for " + candidateName);
        return "Vote cast successfully for " + candidateName + ".";
    }

    @Override
    public synchronized Map<String, Integer> getResults() throws RemoteException {
        System.out.println("Results requested.");
        return candidates;
    }

    @Override
    public synchronized String endVoting() throws RemoteException {
        String winner = candidates.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("No votes cast");
        System.out.println("Voting ended. Winner: " + winner);
        return "Voting has ended. Winner: " + winner + ".";
    }

    @Override
    public synchronized List<String> getCandidates() throws RemoteException {
        return new ArrayList<>(candidates.keySet());
    }

    public static void main(String[] args) {
        try {
            VotingServer server = new VotingServer();
            java.rmi.registry.LocateRegistry.createRegistry(1099);
            java.rmi.Naming.rebind("VotingSystem", server);
            System.out.println("Voting Server is ready.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


