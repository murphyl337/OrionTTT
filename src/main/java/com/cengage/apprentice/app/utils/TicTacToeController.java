package com.cengage.apprentice.app.utils;

import source.TTT.Board;
import source.TTT.Game;
import source.TTT.GameRules;
import source.TTT.Player;
import source.TTT.Position;

import com.cengage.apprentice.app.TTT.GameRepository;
import com.cengage.apprentice.app.main.OrionRequest;
import com.cengage.apprentice.app.response.NewGameResponse;
import com.cengage.apprentice.app.response.OrionResponse;
import com.cengage.apprentice.app.response.StatusCodeResponse;
import com.cengage.apprentice.app.response.UpdateGameResponse;

public class TicTacToeController {
    private static final String GAME = "game";
    private static final String MOVE = "move";
    private static final String PLAYER = "player";

    public OrionResponse newGame() {
        Player player1 = new Player("X", null);
        Player player2 = new Player("O", null);
        Game game = new Game(new Board(), player1, player2);
        GameRepository.put(game);
        return new NewGameResponse(game.getId());
    }

    public OrionResponse updateGame(OrionRequest request) {
        Game game = getGame(request);
        Position move = getMove(request);
        String marker = request.getQueries().get(PLAYER);
        if (game == null || move == null || marker == null) {
            return new StatusCodeResponse(500);
        }
        if (GameRules.isValidMove(move, game.getBoard())) {
            game.updateBoard(marker, move);
            game.setCurrentPlayer(game.getOtherPlayer(game.getCurrentPlayer()));
        }
        return new UpdateGameResponse(game);
    }

    private Position getMove(OrionRequest request) {
        String moveString = request.getQueries().get(MOVE);
        if (moveString == null) {
            return null;
        }
        try {
            int row = Integer.parseInt(moveString.split(",")[0]);
            int col = Integer.parseInt(moveString.split(",")[1]);
            Position move = new Position(row, col);
            return move;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private Game getGame(OrionRequest request) {
        try {
            Game game = GameRepository.get(Integer.parseInt(request
                    .getQueries().get(GAME)));
            return game;
        } catch (NumberFormatException e) {
            return null;
        }
    }

}
