package com.dbsys.rs.patient.controller;

import java.util.List;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dbsys.rs.lib.ApplicationException;
import com.dbsys.rs.lib.EntityRestMessage;
import com.dbsys.rs.lib.ListEntityRestMessage;
import com.dbsys.rs.lib.RestMessage;
import com.dbsys.rs.lib.entity.Penduduk;
import com.dbsys.rs.patient.service.PendudukService;

@Controller
@RequestMapping("/penduduk")
public class PendudukController {

	@Autowired
	private PendudukService pendudukService;
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public EntityRestMessage<Penduduk> save(@RequestBody Penduduk penduduk) throws ApplicationException, PersistenceException {
		penduduk = pendudukService.save(penduduk);
		return EntityRestMessage.createPenduduk(penduduk);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	@ResponseBody
	public RestMessage delete(@PathVariable Long id) throws ApplicationException, PersistenceException {
		pendudukService.delete(id);
		return RestMessage.success();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	@ResponseBody
	public EntityRestMessage<Penduduk> get(@PathVariable Long id) throws ApplicationException, PersistenceException {
		Penduduk penduduk = pendudukService.get(id);
		return EntityRestMessage.createPenduduk(penduduk);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/keyword/{keyword}")
	@ResponseBody
	public ListEntityRestMessage<Penduduk> get(@PathVariable String keyword) throws ApplicationException, PersistenceException {
		List<Penduduk> list = pendudukService.get(keyword);
		return ListEntityRestMessage.createListPenduduk(list);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ListEntityRestMessage<Penduduk> getAll() throws ApplicationException, PersistenceException {
		List<Penduduk> list = pendudukService.getAll();
		return ListEntityRestMessage.createListPenduduk(list);
	}
}
