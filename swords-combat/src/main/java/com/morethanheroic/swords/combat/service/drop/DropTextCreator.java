package com.morethanheroic.swords.combat.service.drop;

import com.morethanheroic.swords.combat.domain.Drop;
import com.morethanheroic.swords.inventory.domain.IdentificationType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DropTextCreator {

    private static final String UNIDENTIFIED_ITEM_NAME = "Unidentified item";

    public String listAsText(final List<Drop> drops) {
        final StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < drops.size(); i++) {
            final Drop drop = drops.get(i);

            if (drop.isIdentified() == IdentificationType.IDENTIFIED) {
                stringBuilder.append(drop.getAmount()).append(" ").append(drop.getItem().getName());
            } else {
                stringBuilder.append(drop.getAmount()).append(" ").append(UNIDENTIFIED_ITEM_NAME);
            }

            if (lastDrop(i, drops.size())) {
                stringBuilder.append(", ");
            }
        }

        return stringBuilder.toString();
    }

    private boolean lastDrop(final int index, final int dropsLength) {
        return index + 1 != dropsLength;
    }
}
