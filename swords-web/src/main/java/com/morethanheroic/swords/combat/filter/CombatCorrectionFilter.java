package com.morethanheroic.swords.combat.filter;

import com.morethanheroic.swords.combat.repository.domain.CombatMapper;
import com.morethanheroic.swords.session.SessionAttributeType;
import com.morethanheroic.swords.user.domain.UserEntity;
import com.morethanheroic.swords.user.service.UserEntityFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//FIXME this is a huge hack...
@Log4j
@RequiredArgsConstructor
public class CombatCorrectionFilter implements Filter {

    private final CombatMapper combatMapper;
    private final UserEntityFactory userEntityFactory;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("Initialising combat correction filter.");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        final HttpSession session = ((HttpServletRequest) request).getSession();

        if (session.getAttribute(SessionAttributeType.USER_ID.name()) != null) {
            final UserEntity userEntity = userEntityFactory.getEntity((Integer) session.getAttribute(SessionAttributeType.USER_ID.name()));

            final int combatCount = combatMapper.getCombatCount(userEntity.getId());
            if (combatCount > 0) {
                if (userEntity.getActiveExplorationEvent() == 0 && userEntity.getActiveExplorationState() == 0) {
                    combatMapper.removeCombatForUser(userEntity.getId());
                } else {
                    if (combatCount > 1) {
                        combatMapper.removeOneCombatForUser(userEntity.getId());
                    }
                }
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        log.info("Destroyed combat correction filter.");
    }
}
