package com.javasampleapproach.springrest.mysql.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javasampleapproach.springrest.mysql.model.Partner;
import com.javasampleapproach.springrest.mysql.repo.PartnerRepository;

@CrossOrigin(origins = "http://localhost:4200")

@RestController
@RequestMapping("/api")
public class PartnerController {

	@Autowired
	PartnerRepository repository;

	@GetMapping("/partners")
	public List<Partner> getAllPartners() {
		System.out.println("Get all Partners...");

		List<Partner> partners = new ArrayList<>();
		repository.findAll().forEach(partners::add);

		return partners;
	}

	@PostMapping(value = "/partners/create")
	public Partner postPartner(@RequestBody Partner partner) {

		Partner _partner = repository.save(new Partner(partner.getName(), partner.getAge()));
		return _partner;
	}

	@DeleteMapping("/partners/{id}")
	public ResponseEntity<String> deletePartner(@PathVariable("id") long id) {
		System.out.println("Delete Partner with ID = " + id + "...");

//		repository.deleteById(id);
		repository.delete(id);

		return new ResponseEntity<>("Partner has been deleted!", HttpStatus.OK);
	}

	@DeleteMapping("/partners/delete")
	public ResponseEntity<String> deleteAllPartners() {
		System.out.println("Delete All Partners...");

		repository.deleteAll();

		return new ResponseEntity<>("All Partners have been deleted!", HttpStatus.OK);
	}

	@GetMapping(value = "partners/age/{age}")
	public List<Partner> findByAge(@PathVariable int age) {

		List<Partner> partners = repository.findByAge(age);
		return partners;
	}

	@PutMapping("/partners/{id}")
	public ResponseEntity<Partner> updatePartner(@PathVariable("id") long id, @RequestBody Partner partner) {
		System.out.println("Update Partner with ID = " + id + "...");

		Optional<Partner> partnerData = repository.findById(id);
//		Optional<Partner> PartnerData = repository.findOne(id);

		if (partnerData.isPresent()) {
			Partner _partner = partnerData.get();
			_partner.setName(partner.getName());
			_partner.setAge(partner.getAge());
			_partner.setActive(partner.isActive());
			return new ResponseEntity<>(repository.save(_partner), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
