package quoridor.backend.states;

import quoridor.main.Quoridor;

public class Win implements State {

    @Override
    public boolean execute() {
        Quoridor.endGame();
        return true;
    }

    @Override
    public State transition(boolean b) {
        return null;
    }

}
