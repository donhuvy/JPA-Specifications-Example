package com.mscharhag.specifications.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.mscharhag.specifications.domain.Poll;
import com.mscharhag.specifications.domain.Poll_;

public class IsPopular extends AbstractSpecification<Poll> {

    private static final int POPULAR_COUNT = 100;
	
	@Override
	public boolean isSatisfiedBy(Poll poll) {
		return poll.getLockDate() == null && poll.getVotes().size() > POPULAR_COUNT;
	}	
	
	@Override
	public Predicate toPredicate(Root<Poll> poll, CriteriaBuilder cb) {
		return cb.and(
			cb.isNull(poll.get(Poll_.lockDate)),
			cb.greaterThan(cb.size(poll.get(Poll_.votes)), POPULAR_COUNT)
		);
	}
}
