package com.codenjoy.dojo.minesweeper.client;

/*-
 * #%L
 * Codenjoy - it's a dojo-like platform from developers to developers.
 * %%
 * Copyright (C) 2016 Codenjoy
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */


import com.codenjoy.dojo.client.Direction;
import com.codenjoy.dojo.client.Solver;
import com.codenjoy.dojo.minesweeper.model.Elements;
import com.codenjoy.dojo.services.Dice;
import com.codenjoy.dojo.services.Point;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AlexanderoSolverTest {

    private Dice dice;
    private AlexanderoSolver ai;

    @Before
    public void setup() {
        dice = mock(Dice.class);
        ai = new AlexanderoSolver(dice);
    }

    private Board board(String board) {
        return (Board) new Board().forString(board);
    }

    @Test
    public void should() {
        asertAI("☼☼☼☼☼☼☼" +
                "☼*****☼" +
                "☼*****☼" +
                "☼*****☼" +
                "☼*☺***☼" +
                "☼*****☼" +
                "☼☼☼☼☼☼☼", Direction.UP);

        asertAI("☼☼☼☼☼☼☼" +
                "☼*****☼" +
                "☼*****☼" +
                "☼*☺***☼" +
                "☼* ***☼" +
                "☼*****☼" +
                "☼☼☼☼☼☼☼", Direction.LEFT);

        asertAI("☼☼☼☼☼☼☼" +
                "☼*****☼" +
                "☼*☺***☼" +
                "☼* ***☼" +
                "☼* ***☼" +
                "☼*****☼" +
                "☼☼☼☼☼☼☼", Direction.LEFT);

        asertAI("☼☼☼☼☼☼☼" +
                "☼*☺***☼" +
                "☼* ***☼" +
                "☼* ***☼" +
                "☼* ***☼" +
                "☼*****☼" +
                "☼☼☼☼☼☼☼", Direction.LEFT);
    }

    @Test
    public void shouldIfThereAreEmptySpaceThanGoForward(){
        asertAI("☼☼☼☼☼☼☼" +
                "☼*****☼" +
                "☼*****☼" +
                "☼*****☼" +
                "☼☺****☼" +
                "☼ ****☼" +
                "☼☼☼☼☼☼☼", Direction.RIGHT);
    }


    @Test
    public void shouldGetAllNeedToBeOpened(){
        ai.setBoard(board(
                "☼☼☼☼☼☼☼" +
                "☼*****☼" +
                "☼*****☼" +
                "☼*****☼" +
                "☼☺****☼" +
                "☼ ****☼" +
                "☼☼☼☼☼☼☼"));
        Set<Point> actual = ai.getAllSafeHiddenPoints();
        assertEquals("[[2,4], [2,5]]", actual.toString());
    }


    @Test
    public void shouldGetNear() {
        Board board = board(
                        " ☼☼☼☼☼ " +
                        "☼*****☼" +
                        "☼*****☼" +
                        "☼** **☼" +
                        "☼*****☼" +
                        "☼ **** " +
                        "☼☼☼☼☼☼☼");
        assertEquals("[[1,4], [2,4], [2,5]]", board.getNear(1, 5, Elements.HIDDEN).toString());
        assertEquals("[[2,1], [1,2], [2,2]]", board.getNear(1, 1, Elements.HIDDEN).toString());
        assertEquals("[[4,4], [5,4], [4,5]]", board.getNear(5, 5, Elements.HIDDEN).toString());
        assertEquals("[[4,1], [4,2], [5,2]]", board.getNear(5, 1, Elements.HIDDEN).toString());
        assertEquals("[[2,2], [3,2], [4,2], [2,3], [4,3], [2,4], [3,4], [4,4]]", board.getNear(3, 3, Elements.HIDDEN).toString());
        assertEquals("[]", board.getNear(50, 50, Elements.HIDDEN).toString());
    }


    private void asertAI(String board, Direction expected) {
        String actual = ai.get(board(board));
        assertEquals(expected.toString(), actual);
    }

    private void dice(Direction direction) {
        when(dice.next(anyInt())).thenReturn(direction.value());
    }
}
