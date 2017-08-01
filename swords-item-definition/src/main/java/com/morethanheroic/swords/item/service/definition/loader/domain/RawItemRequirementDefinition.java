package com.morethanheroic.swords.item.service.definition.loader.domain;

import com.morethanheroic.swords.item.domain.requirement.ItemRequirement;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.Getter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * A freshly loaded requirements data from the item's xml file.
 */
@Getter
@XmlAccessorType(XmlAccessType.FIELD)
@SuppressFBWarnings(value = "UWF_UNWRITTEN_FIELD", justification = "For some reason FindBugs doesn't detect the fields as being used.")
public class RawItemRequirementDefinition {

    private int amount;
    private ItemRequirement requirement;
}
