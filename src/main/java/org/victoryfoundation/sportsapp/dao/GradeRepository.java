package org.victoryfoundation.sportsapp.dao;

import org.springframework.stereotype.Repository;
import org.victoryfoundation.sportsapp.domains.CoachScore;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class GradeRepository {

    @PersistenceContext
    private EntityManager em;

    public List<CoachScore> getScore(long startTime, long endTime) {
        Query scoreQuery = em.createNativeQuery("select c.coach_name, c.profile_image, sum(ad.score) from coach c, activity_detail ad, activity a where a.activity_id = ad.activity_id and c.coach_id = a.coach_id and a.status = 'CLOSED' and a.created_on >= :startTime and a.created_on <= :endTime group by c.coach_name, c.profile_image order by sum(ad.score) desc");
        scoreQuery.setParameter("startTime", startTime);
        scoreQuery.setParameter("endTime", endTime);
        List results = scoreQuery.getResultList();
        return (List<CoachScore>) results.stream().map(o -> {
            Object[] columns = (Object[]) o;
            CoachScore cs = new CoachScore();
            cs.setCoachName((String) columns[0]);
            cs.setCoachImage((String) columns[1]);
            cs.setScore(((BigDecimal) columns[2]).doubleValue());
            return cs;
        }).collect(Collectors.toList());
    }
}
