# Distributed-System-Assignment-2

The Distributed Voting System is a Java RMI-based application that allows multiple clients to securely and interactively participate in an election or survey. Voters can register, view candidates, cast their votes, and view live results. The server manages the voting process, ensures data consistency, and announces the winner at the end of the voting session. The system demonstrates reliable communication, synchronized operations, and distributed functionality using Java RMI.

# How to Run the Application
Compile the Code: Ensure all .java files are in the same directory and run the following command in a terminal:
javac VotingInterface.java VotingServer.java VotingClient.java
Start the RMI Registry: Open a new terminal window and run:
rmiregistry

Start the Server: Open another terminal, navigate to the directory containing your .class files, and run:
java VotingServer
You should see:

Voting Server is ready.
Start the Client: Open yet another terminal, navigate to the same directory, and run:
java VotingClient
Interact with the Application:
Option 1: Register as a voter (provide a unique voter ID and name).
Option 2: View the list of candidates.
Option 3: Cast your vote for a candidate.
Option 4: View the current results (live vote counts).
Option 5: End voting and announce the winner.
Option 6: Exit the client.
Close the Application:
The server will continue running until manually stopped.
