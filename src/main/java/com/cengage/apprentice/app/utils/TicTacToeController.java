package com.cengage.apprentice.app.utils;

import source.TTT.Board;
import source.TTT.Game;
import source.TTT.GameRules;
import source.TTT.Player;
import source.TTT.Position;
import source.handles.MinimaxStrategyHandle;

import com.cengage.apprentice.app.TTT.GameRepository;
import com.cengage.apprentice.app.main.OrionRequest;
import com.cengage.apprentice.app.response.NewGameResponse;
import com.cengage.apprentice.app.response.OrionResponse;
import com.cengage.apprentice.app.response.UpdateGameResponse;

public class TicTacToeController {
    private static final String GAME = "game";
    private static final String MOVE = "move";
    private static final String PLAYER = "player";

    public OrionResponse newGame() {
        Player player1 = new Player("X", null);
        Player player2 = new Player("O", new MinimaxStrategyHandle());
        Game game = new Game(new Board(), player1, player2);
        GameRepository.put(game);
        return new NewGameResponse(game.getId());
    }

    public OrionResponse updateGame(OrionRequest request) {
        Game game = getGame(request);
        if(gameIsOver(game)){
            return new UpdateGameResponse(game);
        }
        Position move = getMove(request);
        String marker = request.getQueries().get(PLAYER);
        if (GameRules.isValidMove(move, game.getBoard())) {
            game.updateBoard(marker, move);
            if(gameIsOver(game))
                return new UpdateGameResponse(game);
            game.getPlayer2().makeMove(game);
        }
        return new UpdateGameResponse(game);
    }

    private boolean gameIsOver(Game game) {
        return GameRules.isGameOver(game.getBoard());
    }

    private Position getMove(OrionRequest request) {
        String moveString = request.getQueries().get(MOVE);
        int row = Integer.parseInt(moveString.substring(0, 1));
        int col = Integer.parseInt(moveString.substring(1));
        Position move = new Position(row, col);
        return move;
    }

    private Game getGame(OrionRequest request) {
        Game game = GameRepository.get(Integer.parseInt(request.getQueries()
                .get(GAME)));
        return game;
    }

}
