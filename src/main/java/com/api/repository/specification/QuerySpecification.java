package com.api.repository.specification;

import com.api.model.entity.Event;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class QuerySpecification {

    public static Specification<Event> searchEventByIdEventOrNameOrPlaceOrDateStartOrDateEndOrStatusOrCreatedOrTicketPriceOrTicketLimitOrTicketSold(String search) {
        return (root, query, criteriaBuilder) ->  {
            List<Predicate> predicates = new ArrayList<>();
            if (search != null && !search.isBlank()) {
                Predicate namePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + search.toLowerCase() + "%");
                Predicate placePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("place")), "%" + search.toLowerCase() + "%");
                Predicate statusPredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("status")), "%" + search.toLowerCase() + "%");
                Predicate idEventPredicate;
                Predicate limitPredicate;
                Predicate soldPredicate;
                Predicate combinedPredicate;
                try {
                    Long searchAsNumber = Long.parseLong(search);
                    idEventPredicate = criteriaBuilder.equal(root.get("idEvent"), searchAsNumber);
                    soldPredicate = criteriaBuilder.equal(root.get("ticketsSold"), searchAsNumber);
                    limitPredicate = criteriaBuilder.equal(root.get("ticketLimit"), searchAsNumber);
                    combinedPredicate = criteriaBuilder.or(
                            namePredicate, placePredicate, statusPredicate, idEventPredicate, soldPredicate, limitPredicate
                    );
                } catch (NumberFormatException e){
                    combinedPredicate = criteriaBuilder.or(
                            namePredicate, placePredicate, statusPredicate
                    );
                }
                predicates.add(combinedPredicate);
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
