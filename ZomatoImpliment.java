package com.project.fullstack1.implement;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.fullstack1.blueprint.Zomato;
import com.project.fullstack1.repository.ZomatoInterface;
import com.project.fullstack1.server.ZomatoService;
@Service
public class ZomatoImpliment  implements ZomatoService {
	private ZomatoInterface zomatoInt;

	public ZomatoImpliment(ZomatoInterface zomatoInt) {
		this.zomatoInt = zomatoInt;
	}
	

	@Override
	public Zomato saveRestaurents(Zomato obj) {
		return zomatoInt.save(obj);
	}

	@Override
	public List<Zomato> fetchAllRestaurents() {
		return zomatoInt.findAll();
	}

	@Override
	public Zomato fetchById(int restaurantId) throws Exception {
		// TODO Auto-generated method stub
		return  zomatoInt.findById(restaurantId).get(); 
	}

	@Override
	public Zomato updateRestaurents(int restaurantId, Zomato newVal) {
		// TODO Auto-generated method stub
		return zomatoInt.findById(restaurantId).get() ;
	}

	@Override
	public void deleteRestaurents(int id) {
		zomatoInt.deleteById(id);
			
}
}