package cards;

import java.util.*;

public class CardList<E> extends ArrayList<E> {
    public String toString() {
        StringBuilder output = new StringBuilder("(");

        for (int i = 0; i < size(); i++) {
            output.append(get(i));

            if (i < size() - 1) {
                output.append(", ");
            } else {
                output.append(")");
            }
        }
        return output.toString();
    }
}
