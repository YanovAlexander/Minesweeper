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


import com.codenjoy.dojo.client.AbstractBoard;
import com.codenjoy.dojo.minesweeper.model.Elements;
import com.codenjoy.dojo.services.Point;
import com.codenjoy.dojo.services.PointImpl;

import java.util.LinkedList;
import java.util.List;

import static com.codenjoy.dojo.services.PointImpl.pt;

public class Board extends AbstractBoard<Elements> {

    @Override
    public Elements valueOf(char ch) {
        return Elements.valueOf(ch);
    }

    public boolean isBarrierAt(int x, int y) {
        return isAt(x, y, Elements.BORDER);
    }

    public Point getMe() {
        return get(Elements.DETECTOR).get(0);
    }

    public boolean isGameOver() {
        return !get(Elements.BANG).isEmpty();
    }

    public List<Point> getNear(int x, int y, Elements element) {
        List<Point> result = new LinkedList<>();

        if (pt(x, y).isOutOf(size)) {
            return result;
        }
        if (isAt(x - 1, y - 1, element)) {
            result.add(new PointImpl(x - 1, y - 1));
        }
        if (isAt(x    , y - 1, element)) {
            result.add(new PointImpl(x    , y - 1));
        }
        if (isAt(x + 1, y - 1, element)) {
            result.add(new PointImpl(x + 1, y - 1));
        }
        if (isAt(x - 1, y    , element)) {
            result.add(new PointImpl(x - 1, y    ));
        }
        if (isAt(x + 1, y    , element)) {
            result.add(new PointImpl(x + 1, y  ));
        }
        if (isAt(x - 1, y + 1, element)) {
            result.add(new PointImpl(x - 1, y + 1));
        }
        if (isAt(x    , y + 1, element)) {
            result.add(new PointImpl(x    , y + 1));
        }
        if (isAt(x + 1, y + 1, element)) {
            result.add(new PointImpl(x + 1, y + 1));
        }
        return result;
    }
}
