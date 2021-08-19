package com.microservice.repository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.microservice.entity.Reservation;

@Repository
@Transactional
public class ReservationRepoImpl implements ReservationRepo {
	
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public void addReservation(Reservation obj) {
		try {
			entityManager.persist(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 
	@Override
	public void cancelReservation(int ticketNo,LocalDate cancelDate, double refundAmt) {
		Reservation resObj = getReservation(ticketNo);
		resObj.setTicketStatus("cancelled");
		resObj.setCancellationDate(cancelDate);
		resObj.setRefundAmount(refundAmt);
		entityManager.merge(resObj);
	}
	
	@Override
	public Reservation getReservation(int ticketNo) {
		Reservation resObj = entityManager.find(Reservation.class, ticketNo);
		return resObj;
	}

	@Override
	public int frequentTravelRoute() {
		String hql= "SELECT r.pathNo from Reservation r";
		Query query=entityManager.createQuery(hql);
		List<Integer> routes= query.getResultList();
		HashMap<Integer,Integer> frequent = new HashMap<Integer,Integer>();
		for(int routeNo:routes) {
			if(frequent.containsKey(routeNo)) {
				int k = frequent.get(routeNo);
				k++;
				frequent.put(routeNo, k);
			}else {
				frequent.put(routeNo, 1);
			}
		}
		Set<Integer> keySet = frequent.keySet();
		int maxCount=0;
		int mostFrequentRoute=0;
		for(int route:keySet) {
			if(frequent.get(route)>maxCount) {
				maxCount=frequent.get(route);
				mostFrequentRoute=route;
			}
		}
		return mostFrequentRoute;
	}

	@Override
	public List<Integer> lastMonthProfit(LocalDate date1, LocalDate date2) {
		String hql= "SELECT r.pathNo from Reservation r where r.bookingDate between :dt1 AND :dt2";
		Query query=entityManager.createQuery(hql);
		query.setParameter("dt1", date1);
		query.setParameter("dt2", date2);
		List<Integer> paths= query.getResultList();
		return paths;
	}

	@Override
	public int mostPreferredBus() {
		String hql= "SELECT r.pathNo from Reservation r";
		Query query=entityManager.createQuery(hql);
		List<Integer> paths= query.getResultList();
		HashMap<Integer,Integer> frequent = new HashMap<Integer,Integer>();
		for(int i:paths) {
			if(frequent.containsKey(i)) {
				int k = frequent.get(i);
				k++;
				frequent.put(i, k);
			}else {
				frequent.put(i, 1);
			}
		}
		Set<Integer> keySet = frequent.keySet();
		int maxCount=0;
		int mostFrequentBus=0;
		for(int i:keySet) {
			if(frequent.get(i)>maxCount) {
				maxCount=frequent.get(i);
				mostFrequentBus=i;
			}
		}
		return mostFrequentBus;
	}

	@Override
	public List<Reservation> dailyBooked(LocalDate date) {
		String hql="from Reservation where bookingDate=: dt";
		Query query=entityManager.createQuery(hql);
		query.setParameter("dt", date);
		List<Reservation> res= query.getResultList();
		return res;
	}

	@Override
	public List<Reservation> weeklyBooked(LocalDate date1, LocalDate date2) {
		String hql="from Reservation r where r.bookingDate between :dt1 AND :dt2";
		Query query=entityManager.createQuery(hql);
		query.setParameter("dt1", date1);
		query.setParameter("dt2", date2);
		List<Reservation> res= query.getResultList();
		return res;
	}

	@Override
	public List<String[]> availableSeats() {
		
		return null;
	}
	
	@Override
	public Reservation searchTicket(int ticketNo) {
		return getReservation(ticketNo);
	}


}
