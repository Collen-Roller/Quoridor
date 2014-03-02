package quoridor.backend.states;

public interface State {

    public boolean execute();

    public State transition(boolean b);
    
}