import java.rmi.Naming;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * VotingClient interacts with the VotingServer to perform operations such as
 * registering as a voter, viewing candidates, casting votes, and viewing results.
 */
public class VotingClient {
    public static void main(String[] args) {
        try {
            VotingInterface voting = (VotingInterface) Naming.lookup("rmi://localhost/VotingSystem");
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("1. Register as a voter");
                System.out.println("2. View candidates");
                System.out.println("3. Cast your vote");
                System.out.println("4. View results");
                System.out.println("5. End voting");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter Voter ID: ");
                        String voterID = scanner.nextLine();
                        System.out.print("Enter your name: ");
                        String name = scanner.nextLine();
                        System.out.println(voting.registerVoter(voterID, name));
                        break;

                    case 2:
                        List<String> candidates = voting.getCandidates();
                        System.out.println("Candidates:");
                        for (String candidate : candidates) {
                            System.out.println("- " + candidate);
                        }
                        break;

                    case 3:
                        System.out.print("Enter Voter ID: ");
                        voterID = scanner.nextLine();
                        System.out.print("Enter Candidate Name: ");
                        String candidateName = scanner.nextLine();
                        System.out.println(voting.castVote(voterID, candidateName));
                        break;

                    case 4:
                        Map<String, Integer> results = voting.getResults();
                        System.out.println("Current Results:");
                        results.forEach((candidate, votes) -> System.out.println(candidate + ": " + votes + " votes"));
                        break;

                    case 5:
                        System.out.println(voting.endVoting());
                        break;

                    case 6:
                        System.exit(0);

                    default:
                        System.out.println("Invalid choice.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

