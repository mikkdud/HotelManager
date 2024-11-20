package pz.hotelmanager.commands;

/**
 * Represents a generic command in the hotel management system.
 * Implementations of this interface define specific operations that
 * can be executed within the system.
 */
public interface Command {
    /**
     * Executes the command with the provided arguments.
     *
     * @param args an array of strings representing arguments required for command execution
     */
    void execute(String[] args);
}
