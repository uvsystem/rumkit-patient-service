package com.dbsys.rs.patient.controller;

import java.sql.Date;
import java.sql.Time;
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
import com.dbsys.rs.lib.Kelas;
import com.dbsys.rs.lib.ListEntityRestMessage;
import com.dbsys.rs.lib.Penanggung;
import com.dbsys.rs.lib.RestMessage;
import com.dbsys.rs.lib.entity.Pasien;
import com.dbsys.rs.lib.entity.Pasien.KeadaanPasien;
import com.dbsys.rs.lib.entity.Pasien.Pendaftaran;
import com.dbsys.rs.lib.entity.Pasien.StatusPasien;
import com.dbsys.rs.patient.service.PasienService;

@Controller
@RequestMapping("/pasien")
public class PasienController {

	@Autowired
	private PasienService pasienService;

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public EntityRestMessage<Pasien> simpan(@RequestBody Pasien pasien) throws ApplicationException, PersistenceException {
		pasien = pasienService.simpan(pasien);
		return EntityRestMessage.createPasien(pasien);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/penduduk/{idPenduduk}/penanggung/{penanggung}/tanggal/{tanggal}/kode/{kode}/pendaftaran/{pendaftaran}/kelas/{kelas}/tujuan/{tujuan}")
	@ResponseBody
	public EntityRestMessage<Pasien> daftar(@PathVariable Long idPenduduk, @PathVariable Penanggung penanggung, @PathVariable Date tanggal, @PathVariable String kode, @PathVariable Pendaftaran pendaftaran, @PathVariable Kelas kelas, @PathVariable Long tujuan) throws ApplicationException, PersistenceException {
		Pasien pasien = pasienService.daftar(idPenduduk, penanggung, tanggal, kode, pendaftaran, kelas, tujuan);
		return EntityRestMessage.createPasien(pasien);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}/tanggal/{tanggal}/jam/{jam}/keadaan/{keadaan}/status/{status}")
	@ResponseBody
	public EntityRestMessage<Pasien> keluar(@PathVariable Long id, @PathVariable Date tanggal, @PathVariable Time jam,
			@PathVariable KeadaanPasien keadaan, @PathVariable StatusPasien status) throws ApplicationException, PersistenceException {
		Pasien pasien = pasienService.keluar(id, tanggal, jam, keadaan, status);
		return EntityRestMessage.createPasien(pasien);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}/jumlah/{jumlah}")
	@ResponseBody
	public EntityRestMessage<Pasien> bayar(@PathVariable Long id, @PathVariable Long jumlah) throws ApplicationException, PersistenceException {
		Pasien pasien = pasienService.bayar(id, jumlah);
		return EntityRestMessage.createPasien(pasien);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}/kelas/{kelas}")
	@ResponseBody
	public RestMessage ubahKelas(@PathVariable Long id, @PathVariable Kelas kelas) throws ApplicationException, PersistenceException {
		pasienService.ubahKelas(id, kelas);
		return RestMessage.success();
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}/penanggung/{penanggung}")
	@ResponseBody
	public RestMessage ubahKelas(@PathVariable Long id, @PathVariable Penanggung penanggung) throws ApplicationException, PersistenceException {
		pasienService.ubahPenanggung(id, penanggung);
		return RestMessage.success();
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	@ResponseBody
	public RestMessage hapus(@PathVariable Long id) throws ApplicationException, PersistenceException {
		pasienService.hapus(id);
		return  RestMessage.success();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	@ResponseBody
	public EntityRestMessage<Pasien> get(@PathVariable Long id) throws ApplicationException, PersistenceException {
		Pasien pasien = pasienService.get(id);
		return  EntityRestMessage.createPasien(pasien);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/kode/{kode}")
	@ResponseBody
	public EntityRestMessage<Pasien> get(@PathVariable String kode) throws ApplicationException, PersistenceException {
		Pasien pasien = pasienService.get(kode);
		return  EntityRestMessage.createPasien(pasien);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/penduduk/{id}")
	@ResponseBody
	public ListEntityRestMessage<Pasien> getByPenduduk(@PathVariable Long id) throws ApplicationException, PersistenceException {
		List<Pasien> list = pasienService.getByPenduduk(id);
		return ListEntityRestMessage.createListPasien(list);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/unit/{id}")
	@ResponseBody
	public ListEntityRestMessage<Pasien> getByUnit(@PathVariable Long id) throws ApplicationException, PersistenceException {
		List<Pasien> list = pasienService.getByUnit(id);
		return ListEntityRestMessage.createListPasien(list);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/medrek/{nomorMedrek}")
	@ResponseBody
	public ListEntityRestMessage<Pasien> getByMedrek(@PathVariable String nomorMedrek) throws ApplicationException, PersistenceException {
		List<Pasien> list = pasienService.getByMedrek(nomorMedrek);
		return ListEntityRestMessage.createListPasien(list);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{awal}/to/{akhir}")
	@ResponseBody
	public ListEntityRestMessage<Pasien> get(@PathVariable Date awal, @PathVariable Date akhir) throws ApplicationException, PersistenceException {
		List<Pasien> list = pasienService.get(awal, akhir);
		return ListEntityRestMessage.createListPasien(list);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/keyword/{keyword}")
	@ResponseBody
	public ListEntityRestMessage<Pasien> cari(@PathVariable String keyword) throws ApplicationException, PersistenceException {
		List<Pasien> list = pasienService.cari(keyword);
		return ListEntityRestMessage.createListPasien(list);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/keyword/{keyword}/guest")
	@ResponseBody
	public ListEntityRestMessage<Pasien> cariGuest(@PathVariable String keyword) throws ApplicationException, PersistenceException {
		List<Pasien> list = pasienService.cari(keyword, StatusPasien.PERAWATAN);
		return ListEntityRestMessage.createListPasien(list);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/test/test")
	@ResponseBody
	public RestMessage test() throws ApplicationException, PersistenceException {
		return RestMessage.success();
	}
}
